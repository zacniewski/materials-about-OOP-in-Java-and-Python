# 13. Dziedziczenie. Mechanizm kontroli głębokości dziedziczenia.

## Teoria

### Czym jest dziedziczenie?
Dziedziczenie to mechanizm OOP pozwalający tworzyć nowe klasy (podklasy) na bazie istniejących (nadklasy). Podklasa **dziedziczy** pola i metody nadklasy oraz może dodawać nowe lub przesłaniać odziedziczone. Realizuje relację **„jest"** (is-a).

```java
class Podklasa extends Nadklasa { ... }
```

### Dziedziczenie w Javie — zasady
- Java wspiera **dziedziczenie jednokrotne** — klasa może dziedziczyć po dokładnie jednej klasie.
- Każda klasa dziedziczy (pośrednio lub bezpośrednio) po `java.lang.Object`.
- Podklasa dziedziczy wszystkie pola i metody nadklasy (oprócz konstruktorów).
- Pola i metody `private` są dziedziczone, ale **niedostępne** bezpośrednio w podklasie.
- Konstruktory **nie są dziedziczone**, ale mogą być wywoływane przez `super(...)`.

### Słowo kluczowe `extends`
Deklaruje, że klasa dziedziczy po innej klasie:
```java
class Pies extends Zwierze { ... }
```

### Słowo kluczowe `super`
- `super(...)` — wywołanie konstruktora nadklasy (musi być pierwszą instrukcją konstruktora).
- `super.metoda()` — wywołanie metody nadklasy.
- `super.pole` — dostęp do pola nadklasy (gdy jest przesłonięte).

### Kontrola głębokości dziedziczenia

#### Słowo kluczowe `final`
- **`final class`** — klasa nie może być dziedziczona.
- **`final method`** — metoda nie może być przesłonięta w podklasie.

#### Sealed classes (Java 17+)
Klasy zapieczętowane (`sealed`) ograniczają, które klasy mogą je dziedziczyć:
```java
sealed class Figura permits Kolo, Prostokat, Trojkat { ... }
```
Podklasy muszą być `final`, `sealed` lub `non-sealed`.

### Kolejność wywoływania konstruktorów
Konstruktory są wywoływane **od góry hierarchii w dół**:
1. Konstruktor `Object`
2. Konstruktor nadklasy
3. Konstruktor podklasy

### Rzutowanie typów (casting)
- **Upcasting** (w górę) — automatyczne, bezpieczne: `Zwierze z = new Pies();`
- **Downcasting** (w dół) — wymaga jawnego rzutowania: `Pies p = (Pies) z;`
- Przed downcastingiem należy sprawdzić typ za pomocą `instanceof`.

### Kompozycja vs dziedziczenie
- **Dziedziczenie** — relacja „jest" (is-a). Pies JEST zwierzęciem.
- **Kompozycja** — relacja „ma" (has-a). Samochód MA silnik.
- Zasada: **preferuj kompozycję nad dziedziczenie** (Effective Java, Joshua Bloch).

---

## Przykłady

### Przykład 1 — Podstawowe dziedziczenie
```java
class Osoba {
    protected String imie;
    protected int wiek;

    Osoba(String imie, int wiek) {
        this.imie = imie;
        this.wiek = wiek;
        System.out.println("Konstruktor Osoba");
    }

    void przedstawSie() {
        System.out.println("Jestem " + imie + ", mam " + wiek + " lat.");
    }
}

class Student extends Osoba {
    private String uczelnia;

    Student(String imie, int wiek, String uczelnia) {
        super(imie, wiek); // wywołanie konstruktora nadklasy
        this.uczelnia = uczelnia;
        System.out.println("Konstruktor Student");
    }

    void studiuj() {
        System.out.println(imie + " studiuje na " + uczelnia);
    }

    @Override
    void przedstawSie() {
        super.przedstawSie();
        System.out.println("Studiuję na " + uczelnia);
    }
}

class Doktorant extends Student {
    private String promotor;

    Doktorant(String imie, int wiek, String uczelnia, String promotor) {
        super(imie, wiek, uczelnia);
        this.promotor = promotor;
        System.out.println("Konstruktor Doktorant");
    }

    void badaj() {
        System.out.println(imie + " prowadzi badania pod kierunkiem " + promotor);
    }
}

public class DziedziczeniePodstawy {
    public static void main(String[] args) {
        System.out.println("--- Tworzenie Doktoranta ---");
        Doktorant d = new Doktorant("Anna", 28, "Politechnika", "prof. Kowalski");

        System.out.println();
        d.przedstawSie();
        d.studiuj();
        d.badaj();
    }
}
```

### Przykład 2 — Hierarchia dziedziczenia
```java
class Pojazd {
    protected String marka;
    protected int rok;

    Pojazd(String marka, int rok) {
        this.marka = marka;
        this.rok = rok;
    }

    String getInfo() {
        return marka + " (" + rok + ")";
    }
}

class PojazdSilnikowy extends Pojazd {
    protected int mocKM;

    PojazdSilnikowy(String marka, int rok, int mocKM) {
        super(marka, rok);
        this.mocKM = mocKM;
    }

    @Override
    String getInfo() {
        return super.getInfo() + ", " + mocKM + " KM";
    }
}

class Samochod extends PojazdSilnikowy {
    private int liczbaDrzwi;

    Samochod(String marka, int rok, int mocKM, int drzwi) {
        super(marka, rok, mocKM);
        this.liczbaDrzwi = drzwi;
    }

    @Override
    String getInfo() {
        return "Samochód: " + super.getInfo() + ", " + liczbaDrzwi + " drzwi";
    }
}

class Ciezarowka extends PojazdSilnikowy {
    private double ladownosc;

    Ciezarowka(String marka, int rok, int mocKM, double ladownosc) {
        super(marka, rok, mocKM);
        this.ladownosc = ladownosc;
    }

    @Override
    String getInfo() {
        return "Ciężarówka: " + super.getInfo() + ", ładowność: " + ladownosc + " t";
    }
}

class Rower extends Pojazd {
    private int liczbaGodzin;

    Rower(String marka, int rok, int biegow) {
        super(marka, rok);
        this.liczbaGodzin = biegow;
    }

    @Override
    String getInfo() {
        return "Rower: " + super.getInfo() + ", " + liczbaGodzin + " biegów";
    }
}

public class HierarchiaDziedziczenia {
    public static void main(String[] args) {
        Pojazd[] pojazdy = {
            new Samochod("Toyota Corolla", 2023, 150, 5),
            new Ciezarowka("MAN TGX", 2022, 480, 25),
            new Rower("Trek", 2024, 21)
        };

        for (Pojazd p : pojazdy) {
            System.out.println(p.getInfo());
        }
    }
}
```

### Przykład 3 — Klasa final (nie można dziedziczyć)
```java
final class Stala {
    private final String nazwa;
    private final double wartosc;

    Stala(String nazwa, double wartosc) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
    }

    String getNazwa() { return nazwa; }
    double getWartosc() { return wartosc; }

    @Override
    public String toString() {
        return nazwa + " = " + wartosc;
    }
}

// Błąd kompilacji — nie można dziedziczyć po klasie final:
// class MojaStala extends Stala { }

public class FinalClassPrzyklad {
    public static void main(String[] args) {
        Stala pi = new Stala("PI", 3.14159265);
        Stala e = new Stala("E", 2.71828182);

        System.out.println(pi);
        System.out.println(e);

        // Przykłady klas final w Java API:
        // String, Integer, Double, Boolean — wszystkie są final
        System.out.println("\nString jest final: " + String.class.getModifiers());
    }
}
```

### Przykład 4 — Sealed classes (Java 17+)
```java
// Klasa zapieczętowana — tylko wymienione klasy mogą dziedziczyć
sealed class Ksztalt permits Kolo, Prostokat, Trojkat {
    String kolor;

    Ksztalt(String kolor) {
        this.kolor = kolor;
    }

    abstract double pole();
}

// final — nie można dalej dziedziczyć
final class Kolo extends Ksztalt {
    double promien;

    Kolo(String kolor, double promien) {
        super(kolor);
        this.promien = promien;
    }

    @Override
    double pole() { return Math.PI * promien * promien; }

    @Override
    public String toString() {
        return String.format("Koło(%s, r=%.1f, pole=%.2f)", kolor, promien, pole());
    }
}

// final
final class Prostokat extends Ksztalt {
    double a, b;

    Prostokat(String kolor, double a, double b) {
        super(kolor);
        this.a = a;
        this.b = b;
    }

    @Override
    double pole() { return a * b; }

    @Override
    public String toString() {
        return String.format("Prostokąt(%s, %.1fx%.1f, pole=%.2f)", kolor, a, b, pole());
    }
}

// non-sealed — można dalej dziedziczyć
non-sealed class Trojkat extends Ksztalt {
    double podstawa, wysokosc;

    Trojkat(String kolor, double podstawa, double wysokosc) {
        super(kolor);
        this.podstawa = podstawa;
        this.wysokosc = wysokosc;
    }

    @Override
    double pole() { return 0.5 * podstawa * wysokosc; }

    @Override
    public String toString() {
        return String.format("Trójkąt(%s, pole=%.2f)", kolor, pole());
    }
}

// Można dziedziczyć po non-sealed
class TrojkatRownoboczny extends Trojkat {
    TrojkatRownoboczny(String kolor, double bok) {
        super(kolor, bok, bok * Math.sqrt(3) / 2);
    }
}

public class SealedPrzyklad {
    public static void main(String[] args) {
        Ksztalt[] ksztalty = {
            new Kolo("czerwony", 5),
            new Prostokat("niebieski", 4, 6),
            new Trojkat("zielony", 3, 8),
            new TrojkatRownoboczny("żółty", 6)
        };

        for (Ksztalt k : ksztalty) {
            System.out.println(k);
        }
    }
}
```

### Przykład 5 — Upcasting i downcasting
```java
class Zwierze {
    String nazwa;
    Zwierze(String nazwa) { this.nazwa = nazwa; }
    void jedz() { System.out.println(nazwa + " je."); }
}

class Pies extends Zwierze {
    Pies(String nazwa) { super(nazwa); }
    void szczekaj() { System.out.println(nazwa + ": Hau!"); }
}

class Kot extends Zwierze {
    Kot(String nazwa) { super(nazwa); }
    void mruczaj() { System.out.println(nazwa + ": Mrrr..."); }
}

public class CastingPrzyklad {
    public static void main(String[] args) {
        // Upcasting — automatyczne, bezpieczne
        Zwierze z1 = new Pies("Burek");   // Pies → Zwierze
        Zwierze z2 = new Kot("Mruczek");  // Kot → Zwierze

        z1.jedz();  // OK — metoda z Zwierze
        z2.jedz();

        // z1.szczekaj(); // Błąd! Typ Zwierze nie ma metody szczekaj()

        // Downcasting — jawne, wymaga sprawdzenia
        if (z1 instanceof Pies) {
            Pies pies = (Pies) z1;
            pies.szczekaj(); // OK po rzutowaniu
        }

        if (z2 instanceof Kot) {
            Kot kot = (Kot) z2;
            kot.mruczaj();
        }

        // Niebezpieczny downcasting — ClassCastException
        try {
            Pies pies = (Pies) z2; // z2 to Kot, nie Pies!
        } catch (ClassCastException e) {
            System.out.println("Błąd rzutowania: " + e.getMessage());
        }

        // Pattern matching (Java 16+)
        if (z1 instanceof Pies p) {
            p.szczekaj();
        }
    }
}
```

### Przykład 6 — Dziedziczenie a konstruktory
```java
class A {
    A() { System.out.println("Konstruktor A()"); }
    A(String msg) { System.out.println("Konstruktor A(\"" + msg + "\")"); }
}

class B extends A {
    B() {
        super(); // niejawne wywołanie A()
        System.out.println("Konstruktor B()");
    }

    B(String msg) {
        super(msg); // jawne wywołanie A(String)
        System.out.println("Konstruktor B(\"" + msg + "\")");
    }
}

class C extends B {
    C() {
        super("z C"); // jawne wywołanie B(String)
        System.out.println("Konstruktor C()");
    }
}

public class KonstruktoryDziedziczenie {
    public static void main(String[] args) {
        System.out.println("--- new A() ---");
        new A();

        System.out.println("\n--- new B() ---");
        new B();

        System.out.println("\n--- new B(\"test\") ---");
        new B("test");

        System.out.println("\n--- new C() ---");
        new C();
    }
}
```

### Przykład 7 — Kompozycja zamiast dziedziczenia
```java
// ZŁE podejście — dziedziczenie (Stos NIE JEST ArrayList)
// class Stos extends ArrayList<Integer> { ... }

// DOBRE podejście — kompozycja (Stos MA listę)
class Stos<T> {
    private java.util.ArrayList<T> elementy = new java.util.ArrayList<>();

    void push(T element) {
        elementy.add(element);
    }

    T pop() {
        if (isEmpty()) throw new RuntimeException("Stos jest pusty!");
        return elementy.remove(elementy.size() - 1);
    }

    T peek() {
        if (isEmpty()) throw new RuntimeException("Stos jest pusty!");
        return elementy.get(elementy.size() - 1);
    }

    boolean isEmpty() {
        return elementy.isEmpty();
    }

    int size() {
        return elementy.size();
    }

    @Override
    public String toString() {
        return "Stos" + elementy;
    }
}

public class KompozycjaVsDziedziczenie {
    public static void main(String[] args) {
        Stos<Integer> stos = new Stos<>();
        stos.push(10);
        stos.push(20);
        stos.push(30);

        System.out.println(stos);
        System.out.println("peek: " + stos.peek());
        System.out.println("pop: " + stos.pop());
        System.out.println("pop: " + stos.pop());
        System.out.println(stos);

        // Gdyby Stos dziedziczył po ArrayList, użytkownik mógłby:
        // stos.add(0, 999); // wstawić element na dowolną pozycję — łamie zasadę stosu!
        // stos.remove(0);   // usunąć z dołu — łamie zasadę stosu!
    }
}
```

### Przykład 8 — Metoda final zapobiegająca przesłonięciu
```java
class BazaDanych {
    // Metoda final — podklasy nie mogą zmienić logiki połączenia
    final void polacz() {
        System.out.println("Nawiązywanie połączenia...");
        walidujPoswiadczenia();
        otworz();
        System.out.println("Połączono!");
    }

    // Metody do przesłonięcia
    void walidujPoswiadczenia() {
        System.out.println("  Walidacja domyślna...");
    }

    void otworz() {
        System.out.println("  Otwieranie połączenia domyślnego...");
    }
}

class MySQLBaza extends BazaDanych {
    // Nie można przesłonić polacz() — jest final

    @Override
    void walidujPoswiadczenia() {
        System.out.println("  Walidacja MySQL...");
    }

    @Override
    void otworz() {
        System.out.println("  Otwieranie połączenia MySQL na porcie 3306...");
    }
}

class PostgreSQLBaza extends BazaDanych {
    @Override
    void walidujPoswiadczenia() {
        System.out.println("  Walidacja PostgreSQL...");
    }

    @Override
    void otworz() {
        System.out.println("  Otwieranie połączenia PostgreSQL na porcie 5432...");
    }
}

public class FinalMethodPrzyklad {
    public static void main(String[] args) {
        BazaDanych mysql = new MySQLBaza();
        mysql.polacz();

        System.out.println();

        BazaDanych postgres = new PostgreSQLBaza();
        postgres.polacz();
    }
}
```

### Przykład 9 — Wielopoziomowe dziedziczenie
```java
class Istota {
    String nazwa;
    Istota(String nazwa) { this.nazwa = nazwa; }
    void istniej() { System.out.println(nazwa + " istnieje."); }
}

class Organizm extends Istota {
    boolean zywy;
    Organizm(String nazwa) { super(nazwa); this.zywy = true; }
    void oddychaj() { System.out.println(nazwa + " oddycha."); }
}

class Zwierze2 extends Organizm {
    Zwierze2(String nazwa) { super(nazwa); }
    void poruszajSie() { System.out.println(nazwa + " się porusza."); }
}

class Ssak extends Zwierze2 {
    Ssak(String nazwa) { super(nazwa); }
    void karmMlekiem() { System.out.println(nazwa + " karmi mlekiem."); }
}

class Czlowiek extends Ssak {
    String zawod;
    Czlowiek(String nazwa, String zawod) {
        super(nazwa);
        this.zawod = zawod;
    }
    void pracuj() { System.out.println(nazwa + " pracuje jako " + zawod + "."); }
}

public class WielopoziomoweDziedziczenie {
    public static void main(String[] args) {
        Czlowiek jan = new Czlowiek("Jan", "programista");

        // Jan ma dostęp do metod z KAŻDEGO poziomu hierarchii
        jan.istniej();       // z Istota
        jan.oddychaj();      // z Organizm
        jan.poruszajSie();   // z Zwierze2
        jan.karmMlekiem();   // z Ssak
        jan.pracuj();        // z Czlowiek

        System.out.println("\nHierarchia klas:");
        Class<?> klasa = jan.getClass();
        while (klasa != null) {
            System.out.println("  " + klasa.getSimpleName());
            klasa = klasa.getSuperclass();
        }
    }
}
```

### Przykład 10 — Dziedziczenie z interfejsami
```java
interface Serializowalny {
    String serializuj();
}

interface Drukowalny {
    void drukuj();
}

class Dokument {
    protected String tytul;
    protected String tresc;

    Dokument(String tytul, String tresc) {
        this.tytul = tytul;
        this.tresc = tresc;
    }
}

class Artykul extends Dokument implements Serializowalny, Drukowalny {
    private String autor;

    Artykul(String tytul, String tresc, String autor) {
        super(tytul, tresc);
        this.autor = autor;
    }

    @Override
    public String serializuj() {
        return String.format("{\"tytul\":\"%s\",\"autor\":\"%s\",\"tresc\":\"%s\"}",
                tytul, autor, tresc);
    }

    @Override
    public void drukuj() {
        System.out.println("=============================");
        System.out.println("  " + tytul);
        System.out.println("  Autor: " + autor);
        System.out.println("-----------------------------");
        System.out.println("  " + tresc);
        System.out.println("=============================");
    }
}

public class DziedziczenieZInterfejsami {
    public static void main(String[] args) {
        Artykul art = new Artykul(
            "Programowanie obiektowe",
            "OOP to paradygmat programowania...",
            "Jan Kowalski"
        );

        art.drukuj();
        System.out.println("\nJSON: " + art.serializuj());

        // Referencje różnych typów
        Dokument dok = art;
        Serializowalny ser = art;
        Drukowalny druk = art;

        System.out.println("\nSerializacja: " + ser.serializuj());
        druk.drukuj();
    }
}
```
