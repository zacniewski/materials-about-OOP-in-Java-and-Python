### Zadania do wykładów (Java OOP)

#### Informacje organizacyjne
- Do każdego zadania należy dodać co najmniej jeden zrzut ekranu, który jednoznacznie potwierdza autora zrzutu (kodu).
- Za wszystkie poprawnie zrealizowane zadania ocena końcowa z wykładu zostanie podniesiona o `0,5` stopnia.

### Spis treści
1. [Zadanie nr 1 (środowisko Javy)](#zadanie-nr-1-środowisko-javy)
2. [Zadanie nr 2 (paradygmaty obiektowości)](#zadanie-nr-2-paradygmaty-obiektowości)
3. [Zadanie nr 3 (klasy i metody `main`)](#zadanie-nr-3-klasy-i-metody-main)
4. [Zadanie nr 4 (modułowość, pakiety i importy)](#zadanie-nr-4-modułowość-pakiety-i-importy)
5. [Zadanie nr 5 (modyfikatory dostępu i enkapsulacja)](#zadanie-nr-5-modyfikatory-dostępu-i-enkapsulacja)
6. [Zadanie nr 6 (pola klas i stan obiektu)](#zadanie-nr-6-pola-klas-i-stan-obiektu)
7. [Zadanie nr 7 (konstruktory i inicjalizacja obiektów)](#zadanie-nr-7-konstruktory-i-inicjalizacja-obiektów)
8. [Zadanie nr 8 (dziedziczenie i polimorfizm)](#zadanie-nr-8-dziedziczenie-i-polimorfizm)
9. [Zadanie nr 9 (interfejsy i klasy abstrakcyjne)](#zadanie-nr-9-interfejsy-i-klasy-abstrakcyjne)
10. [Zadanie nr 10 (podstawowe klasy biblioteki Java)](#zadanie-nr-10-podstawowe-klasy-biblioteki-java)
11. [Zadanie nr 11 (wyrażenia lambda i interfejsy funkcyjne)](#zadanie-nr-11-wyrażenia-lambda-i-interfejsy-funkcyjne)
12. [Zadanie nr 12 (testowanie kodu)](#zadanie-nr-12-testowanie-kodu)
13. [Zadanie nr 13 (SOLID)](#zadanie-nr-13-solid)
14. [Zadanie nr 14 (wyjątki)](#zadanie-nr-14-wyjątki)
15. [Zadanie nr 15 (referencje do obiektów)](#zadanie-nr-15-referencje-do-obiektów)

#### Zadanie nr 1 (środowisko Javy)
1. Sprawdź i zapisz w sprawozdaniu wyniki poleceń `java -version` oraz `javac -version`.
2. Utwórz i uruchom program `HelloWorld` z metodą `main`.
3. W sprawozdaniu opisz krótko różnicę między JDK, JRE i JVM.

#### Zadanie nr 2 (paradygmaty obiektowości)
1. Wyjaśnij 4 filary OOP własnymi słowami.
2. Napisz program, który:
   - wyświetli nazwy czterech filarów OOP w kolejności alfabetycznej,
   - wyświetli nazwy czterech filarów OOP w kolejności losowej.
3. W sprawozdaniu dodaj zrzut ekranu z działania programu.

#### Zadanie nr 3 (klasy i metody `main`)
1. Czym różni się klasa od obiektu?
2. Z czego składa się klasa?
3. Jak utworzyć nowy obiekt klasy?
4. Czy poniższa klasa jest poprawna?

```java
public class Pytanie {
    public static void main(String[] args) {
        System.out.println("Witaj!");
    }
}
```

5. Co zostanie wypisane na ekran?

```java
public class Punkt {
    private int x, y;

    public void ustawX(int wartoscX) {
        x = wartoscX;
    }

    public void ustawY(int wartoscY) {
        y = wartoscY;
    }

    public String toString() {
        return "X, Y: " + x + ", " + y;
    }

    public static void main(String[] args) {
        Punkt a = new Punkt();
        Punkt b = new Punkt();
        a.ustawX(10);
        a.ustawY(20);
        b.ustawX(0);
        b.ustawY(5);
        System.out.println(a);
        System.out.println(b);
    }
}
```

6. Napisz klasę `Osoba` z polami `wiek`, `imie`, `nazwisko`, setterami i metodą `toString()`. W `main` utwórz obiekt i wypisz go na ekran.

#### Zadanie nr 4 (modułowość, pakiety i importy)
1. Dodaj klasę w pakiecie `laboratoria.lab2.text` (np. `TextStats`) z metodą `countWords(String s)` i pokaż użycie przez import w osobnej klasie.
2. Utwórz dwa pakiety z klasą `Helper` w każdym i pokaż użycie obu przez pełne nazwy kwalifikowane.
3. Uruchom zadania 1–5 z folderu `laboratoria/lab2/zadania-1_5-do-działu-o-modułach-i-pakietach`.

#### Zadanie nr 5 (modyfikatory dostępu i enkapsulacja)
1. Jakie powinny być nazwy getterów i setterów dla pól:

```java
String tytul;
double mianownik;
boolean uzytkownikZalogowany;
```

2. Czym jest i do czego służy `this`?
3. Jaka jest różnica między `private`, `protected`, `public` i dostępem pakietowym?
4. Co zostanie wypisane na ekranie?

```java
public class PytanieZagadka {
    private int liczba;

    public void setLiczba(int liczba) {
        liczba = liczba;
    }

    public int getLiczba() {
        return liczba;
    }

    public static void main(String[] args) {
        PytanieZagadka o = new PytanieZagadka();
        o.setLiczba(100);
        System.out.println("Liczba wynosi: " + o.getLiczba());
    }
}
```

#### Zadanie nr 6 (pola klas i stan obiektu)
1. Jakie są domyślne wartości pól typów prostych i referencyjnych?
2. Kiedy może wystąpić `NullPointerException` i jak ograniczać to ryzyko?
3. Co zostanie wypisane na ekranie?

```java
public class PytanieWartosci {
    private int liczba;
    private boolean wartoscLogiczna;
    private String nazwa;

    public String toString() {
        return liczba + " " + wartoscLogiczna + " " + nazwa;
    }

    public static void main(String[] args) {
        PytanieWartosci o = new PytanieWartosci();
        System.out.println(o);
    }
}
```

4. Co się stanie po uruchomieniu poniższego kodu?

```java
public class UzycieWartosci {
    private int liczba;
    private String nazwa;

    private int getLiczba() {
        return liczba;
    }

    private String getNazwa() {
        return nazwa;
    }

    public static void main(String[] args) {
        UzycieWartosci o = new UzycieWartosci();
        System.out.println(o.getLiczba());
        System.out.println(o.getNazwa().toUpperCase());
    }
}
```

#### Zadanie nr 7 (konstruktory i inicjalizacja obiektów)
1. Do czego służą konstruktory?
2. Czym jest konstruktor domyślny i kiedy jest dodawany przez kompilator?
3. Czy klasa może mieć wiele konstruktorów? Jak działa przeciążanie konstruktorów?
4. Jakie reguły obowiązują przy `this(...)` i `super(...)`?
5. Przeanalizuj i odpowiedz: wynik kompilacji / uruchomienia dla przykładów z wykładu o konstruktorach.

#### Zadanie nr 8 (dziedziczenie i polimorfizm)
1. Wyjaśnij pojęcia: klasa bazowa, klasa pochodna, `extends`.
2. Co zostanie wypisane na ekranie? (dziedziczenie + `toString()`):

```java
class Osoba {
    public String imie;
    public String nazwisko;

    public String toString() {
        return "Osoba " + imie + " " + nazwisko;
    }
}

class Pracownik extends Osoba {
    public int numerId;
}
```

3. Wyjaśnij upcasting, downcasting i użycie `instanceof`.
4. Jaka jest rola `super` przy nadpisywaniu metod?
5. Jak działa kolejność wywołań konstruktorów w hierarchii dziedziczenia?

#### Zadanie nr 9 (interfejsy i klasy abstrakcyjne)
1. Kiedy wybrać interfejs, a kiedy klasę abstrakcyjną?
2. Jakie są domyślne modyfikatory pól i metod w interfejsie?
3. Rozwiąż konflikt metod `default` przy implementacji wielu interfejsów.
4. Wykonaj zadania programistyczne:
   - interfejsy `Drukowalne`, `Eksportowalne`, `Raportowalne`,
   - abstrakcyjna klasa `Figura` + interfejs `Skalowalne`,
   - konflikt metod domyślnych (`Loggable` i `Auditable`).

#### Zadanie nr 10 (podstawowe klasy biblioteki Java)
1. Przygotuj krótkie porównanie i przykłady użycia: `String`, `StringBuilder`, `Objects`, `Math`.
2. Pokaż różnicę między `equals()` i `==` na przykładzie typu referencyjnego.
3. Napisz program, który:
   - pobiera listę napisów,
   - usuwa wartości `null`,
   - sortuje alfabetycznie,
   - łączy elementy do jednego napisu z separatorem `, `.

#### Zadanie nr 11 (wyrażenia lambda i interfejsy funkcyjne)
1. Wyjaśnij, czym jest interfejs funkcyjny i podaj 3 przykłady z `java.util.function`.
2. Przepisz fragment z klasy anonimowej na lambdę.
3. Użyj `Predicate`, `Function` i `Consumer` w krótkim przykładzie przetwarzania listy napisów.

#### Zadanie nr 12 (testowanie kodu)
1. Napisz testy i metodę sprawdzającą, czy liczba jest parzysta.
2. Napisz testy i metodę zwracającą:
   - `-1` dla liczb ujemnych,
   - `0` dla zera,
   - `1` dla liczb dodatnich.
3. Napisz testy i metodę wyszukującą indeks liczby w tablicy (lub `-1`, gdy brak).

#### Zadanie nr 13 (SOLID)
1. Dla każdej zasady SOLID podaj własne, krótkie objaśnienie.
2. Wskaż w prostym przykładzie kodu naruszenie jednej wybranej zasady i zaproponuj refaktoryzację.
3. Napisz krótki przykład (2–4 klasy/interfejsy), który pokazuje `D` z SOLID (odwrócenie zależności).

#### Zadanie nr 14 (wyjątki)
1. Do czego służą wyjątki?
2. W której metodzie rzucono wyjątek na podstawie stack trace?
3. Jak działają `try-catch-finally`, `throw`, `throws` i `try-with-resources`?
4. Napisz klasę `Adres`, której konstruktor waliduje dane i rzuca checked `NieprawidlowyAdresException` z komunikatem zawierającym wszystkie błędy.

#### Zadanie nr 15 (referencje do obiektów)
1. Co to jest sterta i stos?
2. Czym różnią się typy prymitywne i referencyjne?
3. Co charakteryzuje obiekty niemutowalne?
4. Oceń mutowalność poniższych klas i uzasadnij.

```java
public class ZagadkaMutowalne {
    public final int x;

    public ZagadkaMutowalne(int x) {
        this.x = x;
    }
}
```

```java
public class ZagadkaMutowalne2 {
    private String komunikat;

    public void setKomunikat(String komunikat) {
        this.komunikat = komunikat;
    }

    public String getKomunikat() {
        return komunikat;
    }
}
```

```java
public class ZagadkaMutowalne3 {
    private final String[] slowa;

    public ZagadkaMutowalne3(String[] slowa) {
        this.slowa = slowa;
    }
}
```