# Programowanie Obiektowe - Laboratorium 1

## Cel

Celem laboratorium jest zaznajomienie z koncepcjami programowania
obiektowego w językach pozbawionych wsparcia dla tego paradygmatu.

W szczególności uczestnicy odświeżą sobie koncepcję pliku w systemie
operacyjnym UNIX.

## Narzędzia

Do przeprowadzenia laboratorium niezbędny będzie kompilator języka C
(np. gcc albo clang).

Wszystkie koncepcje związane z językiem C zostaną wyjaśnione w
niniejszej instrukcji.

## Wprowadzenie

Jednym z kluczowych rozwiązań projektowych systemu UNIX jest
reprezentowanie różnych zasobów komputera (oraz samego systemu
operacyjnego) w systemie plików.

Słowo "plik" najczęściej jest kojarzone z pewnym ciągięm bajtów
zapisanym na dysku, dostępnym pod określoną nazwą umieszczoną w
hierarchicznej strukturze katalogów.

W systemie UNIX pojęcie pliku należy jednak rozumieć nieco szerzej,
mianowicie jako coś, co *implementuje* pewien prosty *interfejs*, a
konkretnie coś, co po otwarciu za pomocą funkcji systemowej `open`
może być odczytywane za pomocą funkcji `read` i zapsywane za pomocą
funkcji `write`.

Oprócz wspomnianych plików na dysku, tego rodzaju obiekty obejmują
takie byty, jak np. porty szeregowe i inne urządzenia wejścia/wyjścia,
urządzenia blokowe w rodzaju dysków twardych (które system operacyjny
może widzieć jako ciąg bajtów, a nie strukturę z plikami i katalogami)
czy pseudourządzenia zwracające informacje o sprzęcie
(np. `/proc/cpuinfo`), generujące liczby losowe (`/dev/urandom`) albo
ignorujące wszystkie otrzymane dane (`/dev/null`) 0 - i wiele, wiele
innych.

W systemie UNIX funkcje `read` i `write` posiadają następujące
prototypy:

```
ssize_t read(int fd, void *buf, size_t count);
ssize_t write(int fd, const void *buf, size_t count);
```

Należy je rozumieć następująco. Funkcja `read` pobiera trzy argument:
jedną liczbę całkowitą oznaczającą deskryptor pliku (`fd` - file
descriptor), zwracaną przez funkcję `open`; lokalizację tablicy
(`buf` - bufor), do której mają zostać przeczytane dane z pliku, oraz
maksymalną liczbę bajtów, którą owa tablica jest w stanie
pomieścić. Funkcja zwraca wartość typu `ssize_t`, która oznacza liczbę
faktycznie wczytanych bajtów. (Zapis `ssize_t` jest skrótem
od `signed size_t`, co oznacza, że liczba bajtów może być ujemna
- wartości mniejsze od 0 oznaczają błąd, zaś wartość 0 oznacza,
że osiągnięty został koniec pliku.)

Analogicznie funkcja `write` pobiera deskryptor pliku, adres w
pamięci, z którego dane mają zostać zapisane, oraz liczbe bajtów do
zapisania, i zwraca liczbę bajtów, które udało się zapisać - albo
wartość ujemną w przypadku błędu.

## Kod źródłowy

Ze względu na to, że język C nie dostarcza mechanizmów modularyzacji,
aby uniknąć konfliktu nazw z funkcjami systemowymi (takimi jak `read`,
`write` czy `open`), nazwy naszych funkcji będą się rozpoczynać od
przedrostka `IO_`.

```
// Zaczniemy - jak to zwykle bywa w przypadku programów
// w języku C, od załączenia plików nagłówkowych oraz
// zdefiniowania pomocniczych makr:

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <string.h>

#define NELEMS(array) (sizeof(array)/sizeof(array[0]))
#define STRINGS_EQUAL(a, b) (!strcasecmp(a, b))
#define OUT(msg, ...) printf(msg"\n", ## __VA_ARGS__)

// standardowo dla każdego procesu system UNIX otwiera
// 3 deskryptory strumieni. 0 oznacza standardowe wejście,
// 1 standardowe wyjście, a 2 - strumień błędów
enum {
  STDIN = 0,
  STDOUT = 1,
  STDERR = 2,
};

// Zaczniemy od deklaracji struktury, która będzie 
// reprezentować plik. Deklaracja jest potrzebna, żeby
// móc odwoływać się
struct IO_struct;

// Następnie definiujemy typy dla funkcji `read` i `write`:
typedef int (*ReadProc_t)(struct IO_struct *, char *, int); 
typedef int (*WriteProc_t)(struct IO_struct *, const char *, int); 

// Teraz możemy podać definicję struktury IO_struct:
typedef struct IO_struct { 
  ReadProc_t read; 
  WriteProc_t write; 
  void *data; 
} IO_struct; 

// Zdefiniowawszy strukturę, możemy zdefiniować nasze wersje
// generycznych funkcji `read` i `write`:
int IO_read(IO_struct *io, char *buffer, int maxlength) { 
  return io->read(io, buffer, maxlength); 
} 
 
int IO_write(IO_struct *io, const char *buffer, int length) { 
  return io->write(io, buffer, length); 
} 

// Teraz możemy stworzyć poszczególne implementacje
// dla funkcji "read" i "write". Zaczniemy od zbudowania
// funkcji, które po prostu zapinają się do standardowego
// wejścia i wyjścia w systemie UNIX.
int STDIO_read(IO_struct *io, char *output, int max_length) {
  return (int) read(STDIN, output, (size_t) max_length);
}

int STDIO_write(IO_struct *io, const char *input, int length) {
  return (int) write(STDOUT, input, length);
}


// Funkcje powinny być dostępne w tablicy deskryptorów:
struct {
  const char *name;
  IO_struct io;
} descriptors[] = {
  {
    .name = "stdio",
    .io = {
      .read = STDIO_read,
      .write = STDIO_write,
      .data = NULL,
    }
  },
};

// Wreszcie definiujemy sobie funkcję IO_open.
IO_struct *IO_open(const char *name) {
  for (int i = 0; i < NELEMS(descriptors); ++i) {
    if (STRINGS_EQUAL(descriptors[i].name, name)) {
      return &descriptors[i].io;
    }
  }
  return NULL;
}

// Uruchom skompilowany program, wpisując np.:
// ./nazwa-programu stdio
int main(int argc, const char *argv[]) {
	
  if (argc < 2) {
    OUT("Użycie: %s <nazwa-pseudo-pliku>", argv[0]);
    OUT();
    OUT("Dostępne adresy: ");
    for (int i = 0; i < NELEMS(descriptors); ++i) {
      OUT("\t%s", descriptors[i].name);
    }
    return -1;
  }

  IO_struct *io = IO_open(argv[1]);

  while (true) {
    char c;
    int result = IO_read(io, &c, sizeof(c));
    if (result <= 0) {
      perror("read");
      break;
    }
    result = IO_write(io, &c, sizeof(c));
    if (result <= 0) {
      perror("write");
      break;
    }
  }

  return 0;
}
```

## Zadania

### Zadanie 1 - zapoznać się z kodem źródłowym programu

W sprawozdaniu należy wpisać, z jakich części składa się program.
Gdyby jakieś fragmnty kodu źródłowego były niejasne, skonsultować z
prowadzącym zajęcia.

Należy również opisać przewidywane zachowanie programu po jego
uruchomieniu.

### Zadanie 2 - skompilować i uruchomić przykładowy program

Czy program działą zgodnie z oczekiwaniami?

### Zadanie 3 - dodać sterownik pseudourządzenia analogicznego do `/dev/null`

Należy zdefiniować nową parę funkcji, dajmy na to, `NULL_read` oraz
`NULL_write`, i dodać odpowiednie wartości do tablicy `descriptors`.

Dodatkowo należy zmodyfikować główną pętlę w taki sposób, żeby
można było przetestować działanie naszego "urządzenia zerowego".

### Zadanie 4 - dodać do programu sterownik pseudourządzenia generującego liczby (pseudo)losowe

Należy zdefiniować nową parę funkcji, dajmy na to, `RANDOM_read` oraz
`RANDOM_write`, i dodać odpowiednie wartości do tablicy `descriptors`.

Można użyć generatora liczb pseudolosowych dostępnych w bibliotece
standradowej języka C (funkcje `rand`, `rand_r`, `srand`). Można użyć
pola `data` struktury `IO_struct` do przechowywania zarodka generatora.

### Zadanie 5 (dla chętnych) - zobaczyć, jak w bibliotece standardowej języka C zaimplementowano `FILE *`.

Opisany w tej instrukcji mechanizm jest wykorzystywany w bibliotece
standardowej języka C (`stdio.h`), na przykład przez funkcję
`fprintf`. Czy jesteś w stanie przeanalizować dostępną w systemie
strukturę plików nagłówkowych, żeby dotrzeć do definicji struktury
`FILE`?
