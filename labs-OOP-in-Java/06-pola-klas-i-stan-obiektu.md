# 6. Pola klas i stan obiektu

> To laboratorium odpowiada wykładowi `06-pola-klas-i-stan-obiektu.md` i rozwija temat rozpoczęty w laboratorium 3.

## Teoria

### Czym jest stan obiektu?
Stan obiektu to zbiór wartości przechowywanych w jego polach w danym momencie działania programu. Dwa obiekty tej samej klasy mogą mieć ten sam zestaw metod, ale zupełnie inny stan.

```mermaid
classDiagram
class Konto {
  -owner: String
  -balance: double
  +deposit(amount)
  +withdraw(amount)
}
```

Jeżeli utworzymy dwa obiekty `Konto`, każdy będzie miał własny `owner` i własne `balance`.

### Rodzaje pól

#### Pola instancyjne
- należą do konkretnego obiektu,
- każdy obiekt ma własną kopię,
- służą do przechowywania stanu.

#### Pola statyczne
- należą do klasy,
- są współdzielone przez wszystkie obiekty,
- nadają się do liczników, stałych wspólnych lub konfiguracji klasowej.

#### Pola `final`
- mogą być przypisane tylko raz,
- pomagają budować obiekty niezmienne i bezpieczniejsze w użyciu.

### Modyfikator `final` - zmienna, metoda, klasa

Modyfikator `final` można zastosować nie tylko do pola klasy, ale także do zmiennej lokalnej, parametru metody, samej metody oraz całej klasy:

| Gdzie użyto `final`? | Co to oznacza? |
|---|---|
| zmienna/pole/parametr | wartość można przypisać tylko raz |
| metoda | metody nie można nadpisać (zmienić jej implementacji) w podklasie |
| klasa | po klasie nie można dziedziczyć (nie da się utworzyć podklasy) |

```java
public final class Kolor {           // final klasa - nie da się po niej dziedziczyć
    private final int r, g, b;        // final pola - przypisywane tylko raz, w konstruktorze

    public Kolor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public final String opisz() {     // final metoda - nie można jej nadpisać
        return r + "," + g + "," + b;
    }
}
```

### Wartości domyślne pól
Jeśli pole klasy nie zostanie jawnie zainicjalizowane, Java nada mu wartość domyślną:

| Typ pola | Wartość domyślna |
|---|---|
| `int`, `long`, `short`, `byte` | `0` |
| `double`, `float` | `0.0` |
| `boolean` | `false` |
| `char` | `'\0'` |
| typ referencyjny | `null` |

Uwaga: zmienne lokalne w metodach **nie** dostają wartości domyślnych automatycznie.

### Pole klasy a zmienna lokalna

| Cecha | Pole klasy | Zmienna lokalna |
|---|---|---|
| Gdzie istnieje? | w obiekcie lub klasie | wewnątrz metody |
| Czas życia | tak długo, jak żyje obiekt | tylko podczas wykonania metody |
| Wartość domyślna | tak | nie |
| Dostęp | przez metody klasy | tylko w danym bloku |

### Dobra praktyka
Pola powinny opisywać **trwały stan obiektu**, a nie chwilowe wartości pomocnicze. Jeśli coś istnieje tylko podczas jednego obliczenia, zwykle powinno być zmienną lokalną.

## Przykłady

### Przykład 1 - dwa obiekty, dwa różne stany

```java
public class Book {
    private String title;
    private boolean borrowed;

    public Book(String title) {
        this.title = title;
        this.borrowed = false;
    }

    public void borrow() {
        borrowed = true;
    }

    public void printState() {
        System.out.println(title + " | borrowed=" + borrowed);
    }

    public static void main(String[] args) {
        Book b1 = new Book("Clean Code");
        Book b2 = new Book("Effective Java");

        b1.borrow();

        b1.printState();
        b2.printState();
    }
}
```

### Przykład 2 - pole statyczne jako licznik obiektów

```java
public class Student {
    private String name;
    private static int counter = 0;

    public Student(String name) {
        this.name = name;
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        new Student("Anna");
        new Student("Jan");
        new Student("Ola");

        System.out.println("Liczba studentów: " + Student.getCounter());
    }
}
```

### Przykład 3 - pole `final`

```java
public class Country {
    private final String code;
    private String name;

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public void rename(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return code + " -> " + name;
    }
}
```

### Przykład 4 - pułapka współdzielenia stanu przez pole statyczne

```java
public class BankFeeCalculator {
    private static double feeRate = 0.02;

    public static void setFeeRate(double feeRate) {
        BankFeeCalculator.feeRate = feeRate;
    }

    public static double calculate(double amount) {
        return amount * feeRate;
    }
}
```

Jeśli jedna część programu zmieni `feeRate`, zmiana wpłynie na wszystkich użytkowników klasy. To bywa zaletą albo źródłem błędów - zależnie od projektu.

### Przykład 5 - `final` metoda i `final` klasa

```java
public class Vehicle {
    private int speed;

    public final void printSpeed() { // final metoda - nie da się jej nadpisać
        System.out.println("Speed: " + speed + " km/h");
    }
}

// Próba nadpisania metody printSpeed zakończy się błędem kompilacji:
// class Car extends Vehicle {
//     @Override
//     public void printSpeed() { ... } // error: printSpeed() in Car cannot override printSpeed() in Vehicle
// }

public final class ImmutablePoint { // final klasa - nie da się po niej dziedziczyć
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

// Próba dziedziczenia po ImmutablePoint zakończy się błędem kompilacji:
// class Point3D extends ImmutablePoint { ... } // error: cannot inherit from final ImmutablePoint
```

Klasa `Vehicle` udostępnia metodę `printSpeed` oznaczoną jako `final` - żadna klasa dziedzicząca po `Vehicle` nie będzie mogła jej nadpisać. Klasa `ImmutablePoint` jest oznaczona jako `final` w całości - nie da się po niej w ogóle utworzyć podklasy, co dodatkowo gwarantuje, że jej obiekty pozostaną niezmienne.

## Diagram: obiekty tej samej klasy, różny stan

```mermaid
flowchart LR
    A[Klasa Student] --> B[student1: name=Anna, year=1]
    A --> C[student2: name=Jan, year=2]
    A --> D[student3: name=Ola, year=1]
```

## Zadania laboratoryjne

1. Napisz klasę `Product` z polami instancyjnymi `name`, `price`, `category`, `available` oraz metodą `printInfo()`.
2. Dodaj do klasy `Product` statyczne pole `productCount`, które zwiększa się przy tworzeniu każdego obiektu.
3. Utwórz klasę `LibraryMember` z polem `final memberId` i metodą wypisującą dane czytelnika.
4. Napisz klasę `VisitCounter`, która pokazuje różnicę między zmienną lokalną a polem klasy podczas wielokrotnego wywołania metody.
5. Zaimplementuj klasę `ScoreBoard`, gdzie pola instancyjne opisują wynik jednego meczu, a pole statyczne liczy wszystkie rozegrane mecze.
6. Przygotuj krótki eksperyment: utwórz kilka obiektów tej samej klasy i pokaż, że każdy ma własny stan, ale współdzieli pola statyczne.
7. Napisz klasę `ImmutableColor` oznaczoną jako `final`, z polami `final` `r`, `g`, `b` inicjalizowanymi w konstruktorze oraz metodą `final describe()`. Spróbuj (w komentarzu) utworzyć podklasę tej klasy oraz nadpisać metodę `describe` i zapisz, jaki błąd kompilacji otrzymujesz.

### Szczegółowa lista kontrolna (checklist)

Poniższa lista kontrolna pomaga zweryfikować, czy każde zadanie zostało wykonane poprawnie i kompletnie.

#### Zadanie 1 — Klasa `Product`
- [ ] Klasa posiada pola instancyjne `name`, `price`, `category`, `available` z odpowiednimi typami (`String`, `double`, `String`, `boolean`).
- [ ] Metoda `printInfo()` wypisuje wszystkie pola w czytelnej formie.
- [ ] Utworzono co najmniej dwa obiekty o różnych wartościach pól.

#### Zadanie 2 — Statyczne pole `productCount`
- [ ] Pole `productCount` jest zadeklarowane jako `static`.
- [ ] Licznik jest zwiększany w konstruktorze (każde utworzenie obiektu go inkrementuje).
- [ ] Do licznika odwołujemy się przez nazwę klasy (`Product.productCount` lub statyczny getter).
- [ ] Wypisana wartość licznika zgadza się z liczbą utworzonych obiektów.

#### Zadanie 3 — Klasa `LibraryMember` z polem `final`
- [ ] Pole `memberId` jest `final` i jest inicjalizowane w konstruktorze lub przy deklaracji.
- [ ] Nie istnieje setter dla `memberId`; próba ponownego przypisania kończy się błędem kompilacji.
- [ ] Metoda wypisująca dane czytelnika pokazuje wszystkie pola.

#### Zadanie 4 — Klasa `VisitCounter` — zmienna lokalna vs pole
- [ ] Metoda zawiera zmienną lokalną oraz modyfikuje pole klasy.
- [ ] Metoda jest wywoływana co najmniej 3 razy.
- [ ] Wynik pokazuje, że zmienna lokalna resetuje się przy każdym wywołaniu, a pole zachowuje wartość.

#### Zadanie 5 — Klasa `ScoreBoard`
- [ ] Pola instancyjne opisują wynik jednego meczu (np. drużyny i liczba punktów).
- [ ] Pole statyczne zlicza wszystkie utworzone mecze.
- [ ] Utworzono kilka meczów i wypisano zarówno ich wyniki, jak i łączną liczbę meczów.

#### Zadanie 6 — Eksperyment ze stanem obiektów
- [ ] Utworzono co najmniej 3 obiekty tej samej klasy.
- [ ] Zmiana pola instancyjnego jednego obiektu nie wpływa na pozostałe.
- [ ] Zmiana pola statycznego jest widoczna we wszystkich obiektach.
- [ ] Wynik eksperymentu został krótko opisany.

#### Zadanie 7 — Klasa `ImmutableColor` z `final` metodą i `final` klasą
- [ ] Klasa `ImmutableColor` jest zadeklarowana jako `final`.
- [ ] Pola `r`, `g`, `b` są `final` i inicjalizowane w konstruktorze.
- [ ] Metoda `describe()` jest zadeklarowana jako `final`.
- [ ] W komentarzu umieszczono próbę utworzenia podklasy `ImmutableColor` oraz treść błędu kompilacji.

#### Ogólna weryfikacja końcowa
- [ ] Cały kod kompiluje się bez błędów i ostrzeżeń.
- [ ] Program został uruchomiony, a wyniki są zgodne z oczekiwaniami.
- [ ] Nazwy klas, metod i zmiennych są zgodne z konwencjami Javy.
- [ ] Odpowiedzi opisowe są zapisane w repozytorium (np. w pliku `README.md` lub komentarzach).

## Rozszerzenie

Przepisz jedną z klas z laboratorium 3 tak, aby:
- pola były prywatne,
- stan był modyfikowany tylko przez metody,
- pola, które nie powinny się zmieniać, zostały oznaczone jako `final`.

## Podsumowanie

Po tym laboratorium student powinien:
- rozumieć, że pola reprezentują stan obiektu,
- odróżniać pola instancyjne od statycznych,
- wiedzieć, kiedy używać `final`,
- rozumieć konsekwencje współdzielenia danych na poziomie klasy.
