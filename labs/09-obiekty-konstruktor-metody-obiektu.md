# 9. Obiekty. Konstruktor. Metody obiektu.

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

### Rodzaje konstruktorów
1. **Konstruktor domyślny (bezargumentowy)** — generowany automatycznie lub definiowany ręcznie.
2. **Konstruktor parametryczny** — przyjmuje argumenty do inicjalizacji pól.
3. **Konstruktor kopiujący** — tworzy kopię istniejącego obiektu.

### Łańcuchowanie konstruktorów
Konstruktor może wywoływać inny konstruktor tej samej klasy za pomocą `this(...)` lub konstruktor klasy nadrzędnej za pomocą `super(...)`. Wywołanie musi być **pierwszą instrukcją** w konstruktorze.

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
public class Samochod {
    String marka;
    String model;
    int rok;
    String kolor;

    // Konstruktor domyślny (bezargumentowy)
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
        Samochod auto1 = new Samochod();
        auto1.wyswietl();

        // Modyfikacja pól po utworzeniu
        auto1.marka = "Toyota";
        auto1.model = "Corolla";
        auto1.rok = 2023;
        auto1.kolor = "Czerwony";
        auto1.wyswietl();

        // Drugi obiekt — niezależny stan
        Samochod auto2 = new Samochod();
        auto2.marka = "Honda";
        auto2.model = "Civic";
        auto2.wyswietl();
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

    // Konstruktor parametryczny
    Student(String imie, String nazwisko, int numerIndeksu, double srednia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerIndeksu = numerIndeksu;
        this.srednia = srednia;
    }

    boolean czyStypendiasta() {
        return srednia >= 4.5;
    }

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

    // Konstruktor pełny
    Prostokat(double szerokosc, double wysokosc, String kolor) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.kolor = kolor;
        System.out.println("Konstruktor pełny");
    }

    // Konstruktor bez koloru — łańcuchowanie
    Prostokat(double szerokosc, double wysokosc) {
        this(szerokosc, wysokosc, "Czarny"); // wywołanie konstruktora pełnego
        System.out.println("Konstruktor bez koloru");
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
        Prostokat p2 = new Prostokat(4, 6);
        System.out.println(p2);

        System.out.println("\n--- Tworzenie p3 ---");
        Prostokat p3 = new Prostokat(5);
        System.out.println(p3);

        System.out.println("\n--- Tworzenie p4 ---");
        Prostokat p4 = new Prostokat();
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

    // Konstruktor kopiujący
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
        System.out.println("Czy ten sam obiekt? " + (adres1 == adres2)); // false

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ksiazka ksiazka = (Ksiazka) obj;
        return Objects.equals(isbn, ksiazka.isbn);
    }

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
public class Punkt {
    private double x, y;

    Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Metoda zwracająca nowy obiekt
    Punkt przesuniete(double dx, double dy) {
        return new Punkt(this.x + dx, this.y + dy);
    }

    // Metoda przyjmująca obiekt jako parametr
    double odlegloscDo(Punkt inny) {
        double dx = this.x - inny.x;
        double dy = this.y - inny.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Metoda statyczna operująca na obiektach
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
        System.out.printf("Odległość p1-p2: %.2f%n", p1.odlegloscDo(p2));

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

    Pracownik(String imie, String nazwisko, Adres2 adres, Kontakt kontakt) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.kontakt = kontakt;
    }

    void wyswietlKarte() {
        System.out.println("=== Karta pracownika ===");
        System.out.println("Imię i nazwisko: " + imie + " " + nazwisko);
        System.out.println("Adres: " + adres);
        System.out.println("Kontakt: " + kontakt);
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
    private static int licznik = 0;
    private static int nastepneId = 1;

    private final int id;
    private String login;

    Uzytkownik(String login) {
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
        System.out.println("Użytkowników: " + Uzytkownik.getLiczbaUzytkownikow());

        Uzytkownik u1 = new Uzytkownik("admin");
        Uzytkownik u2 = new Uzytkownik("jan_k");
        Uzytkownik u3 = new Uzytkownik("anna_n");

        System.out.println("\n" + u1);
        System.out.println(u2);
        System.out.println(u3);
        System.out.println("\nŁącznie użytkowników: " + Uzytkownik.getLiczbaUzytkownikow());
    }
}
```

### Przykład 9 — Metoda clone() i interfejs Cloneable
```java
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

    @Override
    public Konfiguracja clone() {
        try {
            return (Konfiguracja) super.clone();
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
abstract class Napoj {
    private String nazwa;
    private double cena;

    Napoj(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    abstract String getOpis();

    @Override
    public String toString() {
        return nazwa + " (" + cena + " zł) — " + getOpis();
    }

    // Metoda fabrykująca — tworzy odpowiedni obiekt na podstawie typu
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
            Napoj napoj = Napoj.utworz(z);
            System.out.println("  " + napoj);
        }
    }
}
```
