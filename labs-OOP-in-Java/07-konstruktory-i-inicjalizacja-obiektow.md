# 7. Konstruktory i inicjalizacja obiektów

## Teoria

### Czym jest obiekt?
Obiekt to konkretna **instancja klasy**, istniejąca w pamięci w czasie wykonywania programu. Każdy obiekt posiada:
- **Stan** — wartości pól (atrybutów) obiektu.
- **Zachowanie** — metody, które obiekt może wykonywać.
- **Tożsamość** — unikalna referencja w pamięci (adres).

### Tworzenie obiektu
Obiekt tworzy się za pomocą operatora `new`, który:
1. Alokuje pamięć na stercie (heap).
2. Wywołuje konstruktor klasy.
3. Zwraca referencję do nowo utworzonego obiektu.

```java
NazwaKlasy obiekt = new NazwaKlasy(argumenty);
```

### Konstruktor
Konstruktor to specjalna metoda wywoływana podczas tworzenia obiektu. Cechy konstruktora:
- Ma **taką samą nazwę** jak klasa.
- **Nie ma typu zwracanego** (nawet `void`).
- Może być przeciążany (wiele konstruktorów z różnymi parametrami).
- Jeśli nie zdefiniujemy żadnego konstruktora, Java dostarcza **domyślny konstruktor bezargumentowy**.
- Jeśli zdefiniujemy jakikolwiek konstruktor, domyślny nie jest generowany automatycznie.

> **Uwaga terminologiczna (zgodnie z wykładem 7):** *konstruktor domyślny* w ścisłym sensie to ten **wygenerowany przez kompilator** (bez argumentów, z pustym ciałem). Konstruktor bezargumentowy napisany ręcznie (jak w Przykładzie 1) nazywamy **konstruktorem bezargumentowym (no-arg)** — działa podobnie, ale to my decydujemy, co robi.

```java
class A { }                 // brak konstruktorów -> kompilator dodaje: A() { super(); }
class B {
    B(int x) { }            // jest własny konstruktor -> kompilator NIE dodaje B()
}
// new A();   // OK
// new B();   // błąd kompilacji: constructor B in class B cannot be applied to given types
```

### Rodzaje konstruktorów
1. **Konstruktor domyślny (bezargumentowy)** — generowany automatycznie lub definiowany ręcznie.
2. **Konstruktor parametryczny** — przyjmuje argumenty do inicjalizacji pól.
3. **Konstruktor kopiujący** — tworzy kopię istniejącego obiektu.

### Łańcuchowanie konstruktorów
Konstruktor może wywoływać inny konstruktor tej samej klasy za pomocą `this(...)` lub konstruktor klasy nadrzędnej za pomocą `super(...)`. Wywołanie musi być **pierwszą instrukcją** w konstruktorze. W jednym konstruktorze może wystąpić **tylko jedno** takie wywołanie (albo `this(...)`, albo `super(...)`).

### Pola `final` a konstruktor
Pole `final`, któremu nie nadano wartości przy deklaracji, **musi** zostać zainicjalizowane w każdym konstruktorze (kompilator to sprawdza). Patrz Przykład 8 (`id`) oraz wykład 7, sekcja 4.

### Kolejność inicjalizacji
1. Pola dostają wartości domyślne (`0`, `false`, `null`).
2. Wykonuje się `super(...)` (konstruktor nadklasy).
3. Inicjalizatory pól (`int x = 5;`) i bloki `{ ... }` — w kolejności, w jakiej stoją w kodzie.
4. Reszta ciała konstruktora.

```java
class Demo {
    int a = 1;                                  // (3) inicjalizator pola
    { System.out.println("blok, a=" + a); }     // (3) blok inicjalizacyjny — po polu a
    Demo() { System.out.println("konstruktor"); } // (4) ciało konstruktora na końcu
}
// new Demo(); wypisze: "blok, a=1", potem "konstruktor"
```

### Metody obiektu
Metody obiektu (instancji) operują na stanie konkretnego obiektu:
- `toString()` — tekstowa reprezentacja obiektu.
- `equals(Object obj)` — porównanie logiczne obiektów.
- `hashCode()` — kod skrótu obiektu.
- `clone()` — tworzenie kopii obiektu.
- `getClass()` — informacja o klasie obiektu.
- `finalize()` — wywoływana przed usunięciem obiektu przez GC (deprecated od Java 9).

### Cykl życia obiektu
1. **Tworzenie** — `new` + konstruktor.
2. **Użycie** — wywoływanie metod, dostęp do pól.
3. **Utrata referencji** — obiekt staje się nieosiągalny.
4. **Garbage Collection** — JVM automatycznie zwalnia pamięć.

### Słowo kluczowe `this`
- `this` — referencja do bieżącego obiektu.
- `this.pole` — dostęp do pola bieżącego obiektu.
- `this(...)` — wywołanie innego konstruktora tej samej klasy.
- `this` jako argument — przekazanie bieżącego obiektu do metody.

---

## Przykłady

### Przykład 1 — Tworzenie obiektów i konstruktor domyślny
```java
// Pola bez modyfikatora dostępu (dostęp pakietowy) — tylko dla prostoty przykładu.
// W kolejnych przykładach (i w wykładzie) pola są private i ustawiane przez konstruktor.
public class Samochod {
    String marka;
    String model;
    int rok;
    String kolor;

    // Konstruktor bezargumentowy napisany RĘCZNIE (nie jest to konstruktor
    // generowany przez kompilator) — ustawia sensowne wartości początkowe.
    // Bez niego pola miałyby wartości domyślne: null dla String, 0 dla int.
    Samochod() {
        this.marka = "Nieznana";
        this.model = "Nieznany";
        this.rok = 2000;
        this.kolor = "Biały";
        System.out.println("Utworzono samochód (konstruktor domyślny)");
    }

    void wyswietl() {
        System.out.printf("%s %s (%d), kolor: %s%n", marka, model, rok, kolor);
    }

    public static void main(String[] args) {
        Samochod auto1 = new Samochod(); // new -> przydział pamięci -> wywołanie konstruktora
        auto1.wyswietl();                // Nieznana Nieznany (2000), kolor: Biały

        // Modyfikacja pól po utworzeniu — działa, ale jest niewygodna i podatna na błędy.
        // Właśnie ten problem rozwiązuje konstruktor parametryczny (Przykład 2).
        auto1.marka = "Toyota";
        auto1.model = "Corolla";
        auto1.rok = 2023;
        auto1.kolor = "Czerwony";
        auto1.wyswietl();

        // Drugi obiekt — niezależny stan
        Samochod auto2 = new Samochod();
        auto2.marka = "Honda";
        auto2.model = "Civic";
        auto2.wyswietl(); // Honda Civic (2000), kolor: Biały — rok i kolor z konstruktora
    }
}
```

### Przykład 2 — Konstruktor parametryczny
```java
public class Student {
    private String imie;
    private String nazwisko;
    private int numerIndeksu;
    private double srednia;

    // Konstruktor parametryczny — obiekt od razu powstaje w pełni zainicjalizowany.
    // this.imie = pole obiektu, imie = parametr konstruktora (ta sama nazwa,
    // dlatego potrzebne jest this, aby je rozróżnić).
    // UWAGA: skoro jest własny konstruktor, wywołanie new Student() się NIE skompiluje.
    Student(String imie, String nazwisko, int numerIndeksu, double srednia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerIndeksu = numerIndeksu;
        this.srednia = srednia;
    }

    boolean czyStypendiasta() {
        return srednia >= 4.5;
    }

    // @Override — nadpisujemy metodę toString() odziedziczoną z klasy Object;
    // jest ona wywoływana automatycznie np. przez System.out.println(obiekt).
    @Override
    public String toString() {
        return String.format("[%d] %s %s, średnia: %.2f%s",
                numerIndeksu, imie, nazwisko, srednia,
                czyStypendiasta() ? " ★" : "");
    }

    public static void main(String[] args) {
        Student s1 = new Student("Anna", "Kowalska", 12345, 4.8);
        Student s2 = new Student("Jan", "Nowak", 12346, 3.9);
        Student s3 = new Student("Ewa", "Wiśniewska", 12347, 4.5);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
```

### Przykład 3 — Przeciążanie konstruktorów i łańcuchowanie (this)
```java
public class Prostokat {
    private double szerokosc;
    private double wysokosc;
    private String kolor;

    // Konstruktor pełny ("główny") — JEDYNE miejsce, w którym przypisujemy pola.
    // Pozostałe konstruktory delegują do niego przez this(...), dzięki czemu
    // nie powielamy kodu (por. klasa Film w wykładzie 7, sekcja 3).
    Prostokat(double szerokosc, double wysokosc, String kolor) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.kolor = kolor;
        System.out.println("Konstruktor pełny");
    }

    // Konstruktor bez koloru — łańcuchowanie
    Prostokat(double szerokosc, double wysokosc) {
        this(szerokosc, wysokosc, "Czarny"); // wywołanie konstruktora pełnego — MUSI być pierwszą instrukcją
        System.out.println("Konstruktor bez koloru"); // instrukcje PO this(...) są dozwolone
    }

    // Konstruktor kwadratu
    Prostokat(double bok) {
        this(bok, bok); // wywołanie konstruktora dwuargumentowego
        System.out.println("Konstruktor kwadratu");
    }

    // Konstruktor domyślny
    Prostokat() {
        this(1.0); // wywołanie konstruktora kwadratu
        System.out.println("Konstruktor domyślny");
    }
    // Łańcuch dla new Prostokat(): () -> (bok) -> (szer, wys) -> (szer, wys, kolor).
    // Komunikaty wypisują się w ODWROTNEJ kolejności: najpierw kończy się
    // konstruktor pełny, a na końcu bezargumentowy.
    // Uwaga: przeciążone konstruktory muszą różnić się listą parametrów —
    // nie mogłyby istnieć jednocześnie Prostokat(double szer) i Prostokat(double bok).

    double obliczPole() {
        return szerokosc * wysokosc;
    }

    @Override
    public String toString() {
        return String.format("Prostokąt(%.1f x %.1f, %s, pole=%.2f)",
                szerokosc, wysokosc, kolor, obliczPole());
    }

    public static void main(String[] args) {
        System.out.println("--- Tworzenie p1 ---");
        Prostokat p1 = new Prostokat(5, 3, "Czerwony");
        System.out.println(p1);

        System.out.println("\n--- Tworzenie p2 ---");
        Prostokat p2 = new Prostokat(4, 6); // int 4 i 6 są automatycznie rozszerzane do double
        System.out.println(p2);

        System.out.println("\n--- Tworzenie p3 ---");
        Prostokat p3 = new Prostokat(5);
        System.out.println(p3);

        System.out.println("\n--- Tworzenie p4 ---");
        Prostokat p4 = new Prostokat();
        // Oczekiwane wypisanie: Konstruktor pełny, Konstruktor bez koloru,
        // Konstruktor kwadratu, Konstruktor domyślny
        System.out.println(p4);
    }
}
```

### Przykład 4 — Konstruktor kopiujący
```java
public class Adres {
    private String ulica;
    private String miasto;
    private String kodPocztowy;

    Adres(String ulica, String miasto, String kodPocztowy) {
        this.ulica = ulica;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
    }

    // Konstruktor kopiujący — tworzy NOWY obiekt z tymi samymi wartościami pól.
    // Mamy dostęp do inny.ulica, mimo że pole jest private: modyfikator private
    // ogranicza dostęp do KLASY, a nie do konkretnego obiektu.
    // Kopiowanie String jest bezpieczne, bo String jest niemutowalny. Gdyby pole
    // było tablicą/listą, trzeba by ją skopiować (defensywne kopiowanie —
    // patrz wykład 7, sekcja 7.9), inaczej oba obiekty współdzieliłyby dane.
    // W praktyce warto też sprawdzić, czy inny != null.
    Adres(Adres inny) {
        this(inny.ulica, inny.miasto, inny.kodPocztowy);
    }

    void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    @Override
    public String toString() {
        return ulica + ", " + kodPocztowy + " " + miasto;
    }

    public static void main(String[] args) {
        Adres adres1 = new Adres("Długa 10", "Gdańsk", "80-001");
        Adres adres2 = new Adres(adres1); // kopia

        System.out.println("Adres 1: " + adres1);
        System.out.println("Adres 2: " + adres2);
        System.out.println("Czy ten sam obiekt? " + (adres1 == adres2)); // false — == porównuje referencje

        // Dla porównania: przypisanie referencji NIE tworzy kopii (patrz Zadanie 5)
        Adres alias = adres1; // alias i adres1 wskazują na TEN SAM obiekt
        System.out.println("alias == adres1? " + (alias == adres1)); // true

        // Zmiana kopii nie wpływa na oryginał
        adres2.setMiasto("Warszawa");
        System.out.println("\nPo zmianie kopii:");
        System.out.println("Adres 1: " + adres1); // Gdańsk — bez zmian
        System.out.println("Adres 2: " + adres2); // Warszawa
    }
}
```

### Przykład 5 — Metody toString(), equals(), hashCode()
```java
import java.util.Objects;

public class Ksiazka {
    private String tytul;
    private String autor;
    private String isbn;

    Ksiazka(String tytul, String autor, String isbn) {
        this.tytul = tytul;
        this.autor = autor;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "\"" + tytul + "\" — " + autor + " (ISBN: " + isbn + ")";
    }

    // equals — RÓWNOŚĆ LOGICZNA: dwie książki uznajemy za równe, gdy mają ten sam ISBN.
    // Parametr musi być typu Object (inaczej byłoby to przeciążenie, a nie nadpisanie).
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                                   // ta sama referencja
        if (obj == null || getClass() != obj.getClass()) return false;  // null lub inna klasa
        Ksiazka ksiazka = (Ksiazka) obj;                                // rzutowanie — już bezpieczne
        return Objects.equals(isbn, ksiazka.isbn);                      // porównanie bezpieczne dla null
    }

    // Kontrakt: jeśli a.equals(b) == true, to a.hashCode() == b.hashCode().
    // Dlatego hashCode liczymy z TYCH SAMYCH pól, których używa equals (tu: isbn).
    // Bez tego obiekty źle działałyby w HashSet / HashMap.
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    public static void main(String[] args) {
        Ksiazka k1 = new Ksiazka("Czysty kod", "Robert C. Martin", "978-0132350884");
        Ksiazka k2 = new Ksiazka("Czysty kod", "Robert C. Martin", "978-0132350884");
        Ksiazka k3 = new Ksiazka("Wzorce projektowe", "Gang of Four", "978-0201633610");

        System.out.println("k1: " + k1);
        System.out.println("k2: " + k2);
        System.out.println("k3: " + k3);

        System.out.println("\nk1 == k2: " + (k1 == k2));           // false (różne referencje)
        System.out.println("k1.equals(k2): " + k1.equals(k2));     // true (ten sam ISBN)
        System.out.println("k1.equals(k3): " + k1.equals(k3));     // false

        System.out.println("\nk1.hashCode(): " + k1.hashCode());
        System.out.println("k2.hashCode(): " + k2.hashCode());     // taki sam jak k1
        System.out.println("k3.hashCode(): " + k3.hashCode());
    }
}
```

### Przykład 6 — Obiekt jako parametr i wartość zwracana
```java
// Klasa Punkt nie ma setterów, a metody nie zmieniają x i y — zamiast modyfikować
// obiekt, zwracają NOWY obiekt (styl niemutowalny, por. wykład 7, sekcja 7.10).
public class Punkt {
    private double x, y;

    Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Metoda zwracająca nowy obiekt — bieżący obiekt (this) pozostaje bez zmian
    Punkt przesuniete(double dx, double dy) {
        return new Punkt(this.x + dx, this.y + dy);
    }

    // Metoda przyjmująca obiekt jako parametr — do metody trafia KOPIA REFERENCJI,
    // czyli metoda widzi ten sam obiekt, który przekazał wywołujący
    double odlegloscDo(Punkt inny) {
        double dx = this.x - inny.x;
        double dy = this.y - inny.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Metoda statyczna — wywołujemy ją na klasie (Punkt.srodek(...)), nie ma w niej this
    static Punkt srodek(Punkt a, Punkt b) {
        return new Punkt((a.x + b.x) / 2, (a.y + b.y) / 2);
    }

    @Override
    public String toString() {
        return String.format("(%.1f, %.1f)", x, y);
    }

    public static void main(String[] args) {
        Punkt p1 = new Punkt(0, 0);
        Punkt p2 = new Punkt(3, 4);

        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.printf("Odległość p1-p2: %.2f%n", p1.odlegloscDo(p2)); // 5.00

        Punkt p3 = p1.przesuniete(5, 5);
        System.out.println("p1 przesunięty o (5,5): " + p3);
        System.out.println("p1 (niezmieniony): " + p1);

        Punkt srodek = Punkt.srodek(p1, p2);
        System.out.println("Środek p1-p2: " + srodek);
    }
}
```

### Przykład 7 — Obiekt zawierający inne obiekty (kompozycja)
```java
// W jednym pliku .java może być tylko jedna klasa publiczna (Pracownik) i plik musi
// nazywać się tak jak ona (Pracownik.java). Pozostałe klasy są pakietowe.
// Nazwa Adres2 — aby nie kolidować z klasą Adres z Przykładu 4.
class Adres2 {
    private String ulica;
    private String miasto;

    Adres2(String ulica, String miasto) {
        this.ulica = ulica;
        this.miasto = miasto;
    }

    @Override
    public String toString() {
        return ulica + ", " + miasto;
    }
}

class Kontakt {
    private String telefon;
    private String email;

    Kontakt(String telefon, String email) {
        this.telefon = telefon;
        this.email = email;
    }

    @Override
    public String toString() {
        return "tel: " + telefon + ", email: " + email;
    }
}

public class Pracownik {
    private String imie;
    private String nazwisko;
    private Adres2 adres;       // obiekt w obiekcie
    private Kontakt kontakt;    // obiekt w obiekcie

    // Konstruktor przyjmuje gotowe obiekty składowe — zapisujemy REFERENCJE do nich
    // (nie kopie). Zmiana obiektu adres na zewnątrz będzie widoczna w pracowniku.
    // Jeśli tego nie chcemy, w konstruktorze trzeba wykonać kopię (np. konstruktorem kopiującym).
    Pracownik(String imie, String nazwisko, Adres2 adres, Kontakt kontakt) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.kontakt = kontakt;
    }

    void wyswietlKarte() {
        System.out.println("=== Karta pracownika ===");
        System.out.println("Imię i nazwisko: " + imie + " " + nazwisko);
        System.out.println("Adres: " + adres);       // niejawne wywołanie adres.toString()
        System.out.println("Kontakt: " + kontakt);   // niejawne wywołanie kontakt.toString()
        System.out.println("========================");
    }

    public static void main(String[] args) {
        Adres2 adres = new Adres2("Długa 10", "Gdańsk");
        Kontakt kontakt = new Kontakt("123-456-789", "jan@firma.pl");

        Pracownik p = new Pracownik("Jan", "Kowalski", adres, kontakt);
        p.wyswietlKarte();
    }
}
```

### Przykład 8 — Statyczny licznik obiektów
```java
public class Uzytkownik {
    // Pola static należą do KLASY — jest jedna wspólna kopia dla wszystkich obiektów.
    // Inicjalizowane są raz, przy załadowaniu klasy (przed utworzeniem pierwszego obiektu).
    private static int licznik = 0;
    private static int nastepneId = 1;

    // Pole final bez wartości przy deklaracji — MUSI zostać ustawione w konstruktorze
    // (wykład 7, sekcja 4). Po utworzeniu obiektu id nie da się już zmienić.
    private final int id;
    private String login;

    Uzytkownik(String login) {
        // nastepneId++ (postinkrementacja): najpierw przypisz bieżącą wartość, potem zwiększ
        this.id = nastepneId++;
        this.login = login;
        licznik++;
        System.out.println("Utworzono użytkownika #" + id + " (" + login + ")");
    }

    static int getLiczbaUzytkownikow() {
        return licznik;
    }

    @Override
    public String toString() {
        return "Użytkownik #" + id + " [" + login + "]";
    }

    public static void main(String[] args) {
        System.out.println("Użytkowników: " + Uzytkownik.getLiczbaUzytkownikow()); // 0

        Uzytkownik u1 = new Uzytkownik("admin");
        Uzytkownik u2 = new Uzytkownik("jan_k");
        Uzytkownik u3 = new Uzytkownik("anna_n");

        System.out.println("\n" + u1);
        System.out.println(u2);
        System.out.println(u3);
        System.out.println("\nŁącznie użytkowników: " + Uzytkownik.getLiczbaUzytkownikow()); // 3
    }
}
```

### Przykład 9 — Metoda clone() i interfejs Cloneable
```java
// Implementacja interfejsu Cloneable (interfejsy — w dalszych wykładach) to "znacznik":
// bez niego Object.clone() rzuca CloneNotSupportedException.
// UWAGA: clone() NIE wywołuje konstruktora. Kopiuje pola "bit po bicie" (kopia płytka):
// pola referencyjne (tablice, listy) byłyby współdzielone przez oryginał i kopię.
// W praktyce częściej poleca się konstruktor kopiujący (Przykład 4).
public class Konfiguracja implements Cloneable {
    private String nazwa;
    private int wartosc;
    private boolean aktywna;

    Konfiguracja(String nazwa, int wartosc, boolean aktywna) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
        this.aktywna = aktywna;
    }

    void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    void setAktywna(boolean aktywna) {
        this.aktywna = aktywna;
    }

    // Nadpisujemy protected Object.clone() jako public i zwracamy typ Konfiguracja
    // (kowariantny typ zwracany), aby nie trzeba było rzutować przy wywołaniu.
    @Override
    public Konfiguracja clone() {
        try {
            return (Konfiguracja) super.clone(); // płytka kopia — tu wystarczy, bo pola to String, int, boolean
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return String.format("Konfiguracja{nazwa='%s', wartość=%d, aktywna=%b}",
                nazwa, wartosc, aktywna);
    }

    public static void main(String[] args) {
        Konfiguracja oryg = new Konfiguracja("Produkcja", 100, true);
        Konfiguracja kopia = oryg.clone();

        System.out.println("Oryginał: " + oryg);
        System.out.println("Kopia:    " + kopia);
        System.out.println("Czy ten sam obiekt? " + (oryg == kopia)); // false

        // Zmiana kopii nie wpływa na oryginał
        kopia.setWartosc(200);
        kopia.setAktywna(false);

        System.out.println("\nPo modyfikacji kopii:");
        System.out.println("Oryginał: " + oryg);
        System.out.println("Kopia:    " + kopia);
    }
}
```

### Przykład 10 — Fabryka obiektów (Factory Method)
```java
// Przykład wybiega w przód: klasy abstrakcyjne i dziedziczenie (extends) będą omawiane
// w kolejnych wykładach. Tu ważne są dwie rzeczy związane z konstruktorami:
//  1) klasa abstrakcyjna MA konstruktor, choć nie da się napisać new Napoj(...),
//  2) podklasy muszą wywołać super(...), bo Napoj nie ma konstruktora bezargumentowego.
abstract class Napoj {
    private String nazwa;
    private double cena;

    // Konstruktor wywoływany wyłącznie z podklas przez super(nazwa, cena)
    Napoj(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    abstract String getOpis();

    @Override
    public String toString() {
        return nazwa + " (" + cena + " zł) — " + getOpis();
    }

    // Metoda fabrykująca (statyczna metoda wytwórcza, wykład 7, sekcja 7.11) — tworzy
    // odpowiedni obiekt na podstawie typu. Wywołujący nie musi znać konkretnych klas
    // ani ich konstruktorów; typ zwracany to Napoj, a faktyczny obiekt to Kawa/Herbata/Sok.
    static Napoj utworz(String typ) {
        switch (typ.toLowerCase()) {
            case "kawa":     return new Kawa();
            case "herbata":  return new Herbata();
            case "sok":      return new Sok();
            default: throw new IllegalArgumentException("Nieznany typ napoju: " + typ);
        }
    }
}

class Kawa extends Napoj {
    // super(...) musi być pierwszą instrukcją — najpierw inicjalizowana jest część z Napoj
    Kawa() { super("Kawa", 8.50); }

    @Override
    String getOpis() { return "Czarna kawa arabica"; }
}

class Herbata extends Napoj {
    Herbata() { super("Herbata", 5.00); }

    @Override
    String getOpis() { return "Herbata zielona"; }
}

class Sok extends Napoj {
    Sok() { super("Sok", 6.00); }

    @Override
    String getOpis() { return "Sok pomarańczowy 100%"; }
}

public class FabrykaPrzyklad {
    public static void main(String[] args) {
        String[] zamowienia = {"kawa", "herbata", "sok", "kawa"};

        System.out.println("Zamówienia:");
        for (String z : zamowienia) {
            Napoj napoj = Napoj.utworz(z); // zamiast new Kawa() / new Herbata() / new Sok()
            System.out.println("  " + napoj);
        }
    }
}
```


## Zadania laboratoryjne

1. Napisz klasę `Student` z trzema konstruktorami: domyślnym, z samym `name` oraz z `name` i `indexNumber`.
2. Zastosuj przeciążanie konstruktorów w klasie `Rectangle` i użyj `this(...)` do delegowania między konstruktorami.
3. Utwórz klasę `Car` z walidacją danych wejściowych w konstruktorze (np. rok produkcji i pojemność silnika).
4. Utwórz klasę `Product` z polem `private final int id` oraz polami `name` i `price`. Pole `id` ma być nadawane automatycznie w konstruktorze na podstawie statycznego licznika (`1, 2, 3, ...`), a statyczna metoda `getCount()` ma zwracać liczbę utworzonych produktów (por. Przykład 8).
5. Zaimplementuj konstruktor kopiujący w klasie `AddressBookEntry` i pokaż różnicę między kopią a referencją do tego samego obiektu.
6. Napisz testowy `main`, który tworzy obiekty wszystkimi konstruktorami i wypisuje ich stan po inicjalizacji.

### Szczegółowa lista kontrolna (checklist)

Poniższa lista kontrolna pomaga zweryfikować, czy każde zadanie zostało wykonane poprawnie i kompletnie.

#### Zadanie 1 — Klasa `Student` z trzema konstruktorami
- [ ] Istnieją konstruktory: bezargumentowy, `Student(String name)` i `Student(String name, String indexNumber)`.
- [ ] Konstruktor domyślny ustawia sensowne wartości domyślne (bez pozostawiania `null`, jeśli to możliwe).
- [ ] Każdy konstruktor został użyty w `main`.

#### Zadanie 2 — Klasa `Rectangle` i `this(...)`
- [ ] Klasa posiada co najmniej dwa przeciążone konstruktory.
- [ ] Konstruktory delegują do jednego „głównego” konstruktora przez `this(...)`.
- [ ] Wywołanie `this(...)` jest pierwszą instrukcją w konstruktorze.
- [ ] Logika inicjalizacji nie jest zduplikowana.

#### Zadanie 3 — Klasa `Car` z walidacją
- [ ] Konstruktor sprawdza rok produkcji (np. nie z przyszłości i nie sprzed wynalezienia samochodu).
- [ ] Konstruktor sprawdza pojemność silnika (wartość dodatnia).
- [ ] Przy błędnych danych rzucany jest wyjątek (np. `IllegalArgumentException`) z czytelnym komunikatem.
- [ ] Przetestowano zarówno poprawne, jak i niepoprawne dane.

#### Zadanie 4 — Klasa `Product` z polem `final` i statycznym licznikiem
- [ ] Pole `id` jest zadeklarowane jako `private final int` i przypisywane wyłącznie w konstruktorze.
- [ ] Klasa ma pole `static` (np. `nextId` lub `count`) wspólne dla wszystkich obiektów.
- [ ] Kolejno tworzone produkty otrzymują identyfikatory `1, 2, 3, ...`.
- [ ] Metoda statyczna `getCount()` zwraca poprawną liczbę utworzonych obiektów (wywołana na klasie: `Product.getCount()`).
- [ ] Klasa nie ma settera dla `id` (próba `this.id = ...` poza konstruktorem nie kompiluje się).
- [ ] W `main` utworzono co najmniej trzy produkty i wypisano je przez `toString()`.

#### Zadanie 5 — Konstruktor kopiujący w `AddressBookEntry`
- [ ] Konstruktor przyjmuje obiekt `AddressBookEntry` i kopiuje jego pola.
- [ ] Pokazano, że zmiana kopii nie zmienia oryginału.
- [ ] Pokazano, że przypisanie referencji (`b = a`) powoduje współdzielenie tego samego obiektu.

#### Zadanie 6 — Testowy `main`
- [ ] Utworzono obiekty z użyciem wszystkich zdefiniowanych konstruktorów.
- [ ] Stan każdego obiektu jest wypisywany (np. przez `toString()`).
- [ ] Program kompiluje się i uruchamia bez błędów.

#### Ogólna weryfikacja końcowa
- [ ] Cały kod kompiluje się bez błędów i ostrzeżeń.
- [ ] Program został uruchomiony, a wyniki są zgodne z oczekiwaniami.
- [ ] Nazwy klas, metod i zmiennych są zgodne z konwencjami Javy.
- [ ] Odpowiedzi opisowe są zapisane w repozytorium (np. w pliku `README.md` lub komentarzach).
