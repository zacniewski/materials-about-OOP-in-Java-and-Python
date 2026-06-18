# 3. Klasy oraz ich elementy składowe, metody klasy

## Teoria

### Czym jest klasa?
Klasa to podstawowa jednostka organizacji kodu w Javie. Jest szablonem (blueprintem), który definiuje:
- **Pola (atrybuty)** — zmienne przechowujące stan obiektu.
- **Metody** — funkcje definiujące zachowanie obiektu.
- **Konstruktory** — specjalne metody wywoływane przy tworzeniu obiektu.
- **Bloki inicjalizacyjne** — kod wykonywany podczas tworzenia obiektu lub ładowania klasy.
- **Klasy wewnętrzne** — klasy zdefiniowane wewnątrz innej klasy.

### Składnia deklaracji klasy
```java
[modyfikator_dostępu] [modyfikatory] class NazwaKlasy [extends KlasaBazowa] [implements Interfejs1, Interfejs2] {
    // pola
    // konstruktory
    // metody
    // klasy wewnętrzne
}
```

### Pola klasy
Pola to zmienne zadeklarowane wewnątrz klasy, ale poza metodami:
- **Pola instancji** — każdy obiekt ma własną kopię. Deklarowane bez słowa `static`.
- **Pola statyczne (klasowe)** — wspólne dla wszystkich obiektów danej klasy. Deklarowane ze słowem `static`.
- **Stałe** — pola oznaczone `final`, których wartość nie może się zmienić po inicjalizacji.

### Metody klasy
Metody definiują operacje, które można wykonać na obiekcie lub klasie:
- **Metody instancji** — operują na polach konkretnego obiektu, wywoływane na rzecz obiektu.
- **Metody statyczne** — należą do klasy, nie do obiektu. Wywoływane przez `NazwaKlasy.nazwaMetody()`. Nie mają dostępu do pól instancji ani do `this`.
- **Metody z parametrami** — przyjmują argumenty wejściowe.
- **Metody zwracające wartość** — mają typ zwracany inny niż `void`.

### Sygnatura metody
Sygnatura metody składa się z nazwy metody i listy typów parametrów. Java rozróżnia metody na podstawie sygnatury (nie na podstawie typu zwracanego).

### Słowo kluczowe `this`
`this` to referencja do bieżącego obiektu. Używane do:
- Rozróżnienia pól klasy od parametrów metody o tej samej nazwie.
- Wywołania innego konstruktora tej samej klasy (`this(...)`).
- Przekazania bieżącego obiektu jako argumentu.

### Słowo kluczowe `static`
Elementy statyczne należą do klasy, nie do instancji:
- **Pole statyczne** — jedna kopia współdzielona przez wszystkie obiekty.
- **Metoda statyczna** — może być wywołana bez tworzenia obiektu.
- **Blok statyczny** — wykonywany raz, przy ładowaniu klasy.

### Modyfikator `final`
- **Pole `final`** — wartość nie może być zmieniona po inicjalizacji (stała).
- **Metoda `final`** — nie może być przesłonięta w podklasie.
- **Klasa `final`** — nie może być dziedziczona.

### Typy zwracane przez metody
- `void` — metoda nie zwraca wartości.
- Typ prymitywny (`int`, `double`, `boolean`, itp.) — metoda zwraca wartość prymitywną.
- Typ obiektowy (`String`, `List`, własna klasa) — metoda zwraca referencję do obiektu.

---

## Przykłady

### Przykład 1 — Klasa z polami i metodami instancji
```java
public class Ksiazka {
    String tytul;
    String autor;
    int liczbaStron;
    boolean przeczytana;

    void przeczytaj() {
        przeczytana = true;
        System.out.println("Przeczytano: \"" + tytul + "\"");
    }

    void wyswietlInfo() {
        System.out.println("Tytuł: " + tytul);
        System.out.println("Autor: " + autor);
        System.out.println("Strony: " + liczbaStron);
        System.out.println("Przeczytana: " + (przeczytana ? "Tak" : "Nie"));
    }

    public static void main(String[] args) {
        Ksiazka k = new Ksiazka();
        k.tytul = "Czysty kod";
        k.autor = "Robert C. Martin";
        k.liczbaStron = 464;

        k.wyswietlInfo();
        System.out.println();
        k.przeczytaj();
        k.wyswietlInfo();
    }
}
```

### Przykład 2 — Metody z parametrami i wartością zwracaną
```java
public class Kalkulator {

    int dodaj(int a, int b) {
        return a + b;
    }

    int odejmij(int a, int b) {
        return a - b;
    }

    double pomnoz(double a, double b) {
        return a * b;
    }

    double podziel(double a, double b) {
        if (b == 0) {
            System.out.println("Błąd: dzielenie przez zero!");
            return 0;
        }
        return a / b;
    }

    boolean czyDodatnia(int liczba) {
        return liczba > 0;
    }

    public static void main(String[] args) {
        Kalkulator kalk = new Kalkulator();

        System.out.println("5 + 3 = " + kalk.dodaj(5, 3));
        System.out.println("10 - 4 = " + kalk.odejmij(10, 4));
        System.out.println("2.5 * 4.0 = " + kalk.pomnoz(2.5, 4.0));
        System.out.println("10 / 3 = " + kalk.podziel(10, 3));
        System.out.println("10 / 0 = " + kalk.podziel(10, 0));
        System.out.println("Czy 5 jest dodatnia? " + kalk.czyDodatnia(5));
    }
}
```

### Przykład 3 — Użycie `this`
```java
public class Prostokat {
    private double szerokosc;
    private double wysokosc;

    // this rozróżnia pole klasy od parametru
    Prostokat(double szerokosc, double wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }

    double obliczPole() {
        return this.szerokosc * this.wysokosc;
    }

    double obliczObwod() {
        return 2 * (this.szerokosc + this.wysokosc);
    }

    // Metoda zwracająca this (fluent API / method chaining)
    Prostokat powieksz(double mnoznik) {
        this.szerokosc *= mnoznik;
        this.wysokosc *= mnoznik;
        return this;
    }

    void wyswietl() {
        System.out.printf("Prostokąt %.1f x %.1f, pole=%.2f, obwód=%.2f%n",
                szerokosc, wysokosc, obliczPole(), obliczObwod());
    }

    public static void main(String[] args) {
        Prostokat p = new Prostokat(5, 3);
        p.wyswietl();

        // Method chaining dzięki return this
        p.powieksz(2).powieksz(1.5);
        p.wyswietl();
    }
}
```

### Przykład 4 — Pola i metody statyczne
```java
public class Pracownik {
    private String imie;
    private double pensja;

    // Pole statyczne — wspólne dla wszystkich obiektów
    private static int liczbaPracownikow = 0;
    private static double sumaPensji = 0;

    Pracownik(String imie, double pensja) {
        this.imie = imie;
        this.pensja = pensja;
        liczbaPracownikow++;
        sumaPensji += pensja;
    }

    // Metoda instancji
    void wyswietl() {
        System.out.println(imie + " — pensja: " + pensja + " zł");
    }

    // Metody statyczne — operują na danych klasowych
    static int getLiczbaPracownikow() {
        return liczbaPracownikow;
    }

    static double getSredniaPensja() {
        if (liczbaPracownikow == 0) return 0;
        return sumaPensji / liczbaPracownikow;
    }

    public static void main(String[] args) {
        new Pracownik("Anna", 5000);
        new Pracownik("Jan", 6000);
        new Pracownik("Ewa", 7000);

        System.out.println("Liczba pracowników: " + Pracownik.getLiczbaPracownikow());
        System.out.printf("Średnia pensja: %.2f zł%n", Pracownik.getSredniaPensja());
    }
}
```

### Przykład 5 — Stałe (`final`) i blok statyczny
```java
public class Konfiguracja {
    // Stałe — wartości niezmienne
    static final String NAZWA_APLIKACJI = "MojaAplikacja";
    static final int WERSJA_MAJOR = 2;
    static final int WERSJA_MINOR = 5;
    static final double MAX_ROZMIAR_PLIKU_MB = 10.0;

    // Blok statyczny — wykonywany raz przy ładowaniu klasy
    static {
        System.out.println("Ładowanie konfiguracji...");
        System.out.println("Aplikacja: " + NAZWA_APLIKACJI);
        System.out.println("Wersja: " + WERSJA_MAJOR + "." + WERSJA_MINOR);
    }

    static String getWersja() {
        return WERSJA_MAJOR + "." + WERSJA_MINOR;
    }

    public static void main(String[] args) {
        System.out.println("Wersja aplikacji: " + Konfiguracja.getWersja());
        System.out.println("Max rozmiar pliku: " + MAX_ROZMIAR_PLIKU_MB + " MB");

        // Nie można zmienić wartości final:
        // WERSJA_MAJOR = 3; // Błąd kompilacji!
    }
}
```

### Przykład 6 — Metody z różnymi typami zwracanymi
```java
public class OperacjeNaTekstach {

    // Zwraca String
    String odwroc(String tekst) {
        return new StringBuilder(tekst).reverse().toString();
    }

    // Zwraca int
    int policzSamogloski(String tekst) {
        int licznik = 0;
        for (char c : tekst.toLowerCase().toCharArray()) {
            if ("aeiouyąęó".indexOf(c) != -1) {
                licznik++;
            }
        }
        return licznik;
    }

    // Zwraca boolean
    boolean czyPalindrom(String tekst) {
        String oczyszczony = tekst.replaceAll("\\s+", "").toLowerCase();
        return oczyszczony.equals(new StringBuilder(oczyszczony).reverse().toString());
    }

    // Zwraca tablicę
    String[] podzielNaSlowa(String tekst) {
        return tekst.split("\\s+");
    }

    // void — nie zwraca wartości
    void wyswietlStatystyki(String tekst) {
        System.out.println("Tekst: \"" + tekst + "\"");
        System.out.println("Długość: " + tekst.length());
        System.out.println("Samogłoski: " + policzSamogloski(tekst));
        System.out.println("Palindrom: " + czyPalindrom(tekst));
        System.out.println("Odwrócony: " + odwroc(tekst));
    }

    public static void main(String[] args) {
        OperacjeNaTekstach op = new OperacjeNaTekstach();

        op.wyswietlStatystyki("kajak");
        System.out.println();
        op.wyswietlStatystyki("Programowanie");

        System.out.println("\nSłowa:");
        String[] slowa = op.podzielNaSlowa("Java jest super");
        for (String s : slowa) {
            System.out.println("  - " + s);
        }
    }
}
```

### Przykład 7 — Klasa z wieloma konstruktorami (przeciążanie konstruktorów)
```java
public class Punkt {
    private double x;
    private double y;

    // Konstruktor domyślny
    Punkt() {
        this(0, 0); // wywołanie innego konstruktora przez this()
    }

    // Konstruktor z parametrami
    Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Konstruktor kopiujący
    Punkt(Punkt inny) {
        this(inny.x, inny.y);
    }

    double odlegloscOd(Punkt inny) {
        double dx = this.x - inny.x;
        double dy = this.y - inny.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    double odlegloscOdPoczatku() {
        return odlegloscOd(new Punkt(0, 0));
    }

    @Override
    public String toString() {
        return String.format("(%.1f, %.1f)", x, y);
    }

    public static void main(String[] args) {
        Punkt p1 = new Punkt();          // (0.0, 0.0)
        Punkt p2 = new Punkt(3, 4);      // (3.0, 4.0)
        Punkt p3 = new Punkt(p2);        // kopia p2

        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.printf("Odległość p2 od początku: %.2f%n", p2.odlegloscOdPoczatku());
        System.out.printf("Odległość p1 od p2: %.2f%n", p1.odlegloscOd(p2));
    }
}
```

### Przykład 8 — Blok inicjalizacyjny instancji
```java
public class Identyfikator {
    private static int nastepneId = 1;
    private int id;
    private String nazwa;

    // Blok inicjalizacyjny instancji — wykonywany PRZED konstruktorem
    {
        id = nastepneId++;
        System.out.println("Blok inicjalizacyjny: przydzielono id=" + id);
    }

    Identyfikator(String nazwa) {
        this.nazwa = nazwa;
        System.out.println("Konstruktor: nazwa=" + nazwa);
    }

    @Override
    public String toString() {
        return "ID=" + id + ", nazwa=" + nazwa;
    }

    public static void main(String[] args) {
        Identyfikator a = new Identyfikator("Alfa");
        Identyfikator b = new Identyfikator("Beta");
        Identyfikator c = new Identyfikator("Gamma");

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
```

### Przykład 9 — Metody operujące na tablicach
```java
public class StatystykaTablic {

    static double srednia(int[] tablica) {
        if (tablica.length == 0) return 0;
        int suma = 0;
        for (int val : tablica) {
            suma += val;
        }
        return (double) suma / tablica.length;
    }

    static int maksimum(int[] tablica) {
        int max = tablica[0];
        for (int i = 1; i < tablica.length; i++) {
            if (tablica[i] > max) {
                max = tablica[i];
            }
        }
        return max;
    }

    static int minimum(int[] tablica) {
        int min = tablica[0];
        for (int i = 1; i < tablica.length; i++) {
            if (tablica[i] < min) {
                min = tablica[i];
            }
        }
        return min;
    }

    static int[] filtrujWieksze(int[] tablica, int prog) {
        int count = 0;
        for (int val : tablica) {
            if (val > prog) count++;
        }
        int[] wynik = new int[count];
        int idx = 0;
        for (int val : tablica) {
            if (val > prog) wynik[idx++] = val;
        }
        return wynik;
    }

    public static void main(String[] args) {
        int[] dane = {12, 45, 3, 67, 23, 89, 5, 34};

        System.out.println("Średnia: " + srednia(dane));
        System.out.println("Maksimum: " + maksimum(dane));
        System.out.println("Minimum: " + minimum(dane));

        int[] duze = filtrujWieksze(dane, 30);
        System.out.print("Większe od 30: ");
        for (int val : duze) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
```

### Przykład 10 — Klasa z metodami varargs
```java
public class MetodyVarargs {

    // Metoda z varargs — zmienna liczba argumentów
    static int suma(int... liczby) {
        int wynik = 0;
        for (int l : liczby) {
            wynik += l;
        }
        return wynik;
    }

    static String polacz(String separator, String... elementy) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elementy.length; i++) {
            sb.append(elementy[i]);
            if (i < elementy.length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    static double srednia(double... wartosci) {
        if (wartosci.length == 0) return 0;
        double suma = 0;
        for (double w : wartosci) {
            suma += w;
        }
        return suma / wartosci.length;
    }

    public static void main(String[] args) {
        System.out.println("Suma(): " + suma());
        System.out.println("Suma(1,2,3): " + suma(1, 2, 3));
        System.out.println("Suma(10,20,30,40): " + suma(10, 20, 30, 40));

        System.out.println(polacz(", ", "Java", "Python", "C++"));
        System.out.println(polacz(" -> ", "Start", "Środek", "Koniec"));

        System.out.printf("Średnia: %.2f%n", srednia(4.5, 3.8, 5.0, 4.2));
    }
}
```
