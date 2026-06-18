# 12. Przeciążanie oraz przesłanianie metod

## Teoria

### Przeciążanie metod (Overloading)
Przeciążanie metod polega na definiowaniu w tej samej klasie **wielu metod o tej samej nazwie**, ale z **różnymi listami parametrów** (różna liczba, typy lub kolejność parametrów). Kompilator wybiera odpowiednią wersję metody na podstawie argumentów wywołania — jest to **polimorfizm statyczny** (compile-time).

Zasady przeciążania:
- Metody muszą różnić się **sygnaturą** (nazwa + lista typów parametrów).
- Typ zwracany **nie** jest częścią sygnatury — nie wystarczy zmienić tylko typ zwracany.
- Modyfikatory dostępu mogą się różnić.
- Przeciążanie dotyczy metod w **tej samej klasie** lub w klasie i jej podklasie.

### Przesłanianie metod (Overriding)
Przesłanianie metod polega na dostarczeniu w podklasie **nowej implementacji** metody odziedziczonej z klasy bazowej. Metoda w podklasie musi mieć **identyczną sygnaturę** i **kompatybilny typ zwracany**. Jest to **polimorfizm dynamiczny** (runtime) — JVM wybiera metodę na podstawie rzeczywistego typu obiektu.

Zasady przesłaniania:
- Sygnatura metody musi być **identyczna** (nazwa, typy i kolejność parametrów).
- Typ zwracany musi być **taki sam lub kowariantny** (podtyp oryginalnego typu).
- Modyfikator dostępu nie może być **bardziej restrykcyjny** (np. `public` → `private` jest zabronione).
- Metoda nie może rzucać **szerszych** wyjątków checked niż oryginał.
- Metody `static`, `final` i `private` **nie mogą** być przesłonięte.
- Adnotacja `@Override` jest opcjonalna, ale **zalecana** — kompilator sprawdzi poprawność.

### Porównanie: przeciążanie vs przesłanianie

| Cecha              | Przeciążanie (Overloading)     | Przesłanianie (Overriding)      |
|--------------------|--------------------------------|---------------------------------|
| Gdzie              | Ta sama klasa                  | Podklasa                        |
| Sygnatura          | Różna (inne parametry)         | Identyczna                      |
| Typ zwracany       | Może być inny                  | Taki sam lub kowariantny        |
| Wiązanie           | Statyczne (compile-time)       | Dynamiczne (runtime)            |
| Polimorfizm        | Statyczny                      | Dynamiczny                      |
| Adnotacja          | Brak                           | `@Override`                     |

### Kowariantny typ zwracany
Od Java 5 metoda przesłaniająca może zwracać **podtyp** typu zwracanego przez metodę bazową:
```java
class Bazowa { Object metoda() { ... } }
class Pochodna extends Bazowa { String metoda() { ... } } // String jest podtypem Object
```

### Słowo kluczowe `super`
W przesłoniętej metodzie można wywołać oryginalną implementację z klasy bazowej za pomocą `super.nazwaMetody()`.

---

## Przykłady

### Przykład 1 — Przeciążanie metod (różna liczba parametrów)
```java
public class Kalkulator {

    int dodaj(int a, int b) {
        System.out.println("dodaj(int, int)");
        return a + b;
    }

    int dodaj(int a, int b, int c) {
        System.out.println("dodaj(int, int, int)");
        return a + b + c;
    }

    double dodaj(double a, double b) {
        System.out.println("dodaj(double, double)");
        return a + b;
    }

    String dodaj(String a, String b) {
        System.out.println("dodaj(String, String)");
        return a + b;
    }

    int dodaj(int... liczby) {
        System.out.println("dodaj(int... varargs)");
        int suma = 0;
        for (int l : liczby) suma += l;
        return suma;
    }

    public static void main(String[] args) {
        Kalkulator k = new Kalkulator();

        System.out.println("Wynik: " + k.dodaj(2, 3));
        System.out.println("Wynik: " + k.dodaj(2, 3, 4));
        System.out.println("Wynik: " + k.dodaj(2.5, 3.7));
        System.out.println("Wynik: " + k.dodaj("Hello", " World"));
        System.out.println("Wynik: " + k.dodaj(1, 2, 3, 4, 5));
    }
}
```

### Przykład 2 — Przeciążanie metod (różne typy parametrów)
```java
public class Wyswietlacz {

    void wyswietl(int wartosc) {
        System.out.println("int: " + wartosc);
    }

    void wyswietl(double wartosc) {
        System.out.printf("double: %.2f%n", wartosc);
    }

    void wyswietl(String wartosc) {
        System.out.println("String: \"" + wartosc + "\"");
    }

    void wyswietl(boolean wartosc) {
        System.out.println("boolean: " + (wartosc ? "PRAWDA" : "FAŁSZ"));
    }

    void wyswietl(int[] tablica) {
        System.out.print("int[]: [");
        for (int i = 0; i < tablica.length; i++) {
            System.out.print(tablica[i]);
            if (i < tablica.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    void wyswietl(String etykieta, Object wartosc) {
        System.out.println(etykieta + " = " + wartosc);
    }

    public static void main(String[] args) {
        Wyswietlacz w = new Wyswietlacz();

        w.wyswietl(42);
        w.wyswietl(3.14);
        w.wyswietl("Hello");
        w.wyswietl(true);
        w.wyswietl(new int[]{1, 2, 3});
        w.wyswietl("Wiek", 25);
    }
}
```

### Przykład 3 — Przesłanianie metod (Override)
```java
class Zwierze {
    String nazwa;

    Zwierze(String nazwa) {
        this.nazwa = nazwa;
    }

    void wydajDzwiek() {
        System.out.println(nazwa + " wydaje dźwięk.");
    }

    void jedz() {
        System.out.println(nazwa + " je.");
    }

    @Override
    public String toString() {
        return "Zwierzę: " + nazwa;
    }
}

class Pies extends Zwierze {
    Pies(String nazwa) { super(nazwa); }

    @Override
    void wydajDzwiek() {
        System.out.println(nazwa + ": Hau hau!");
    }

    @Override
    public String toString() {
        return "Pies: " + nazwa;
    }
}

class Kot extends Zwierze {
    Kot(String nazwa) { super(nazwa); }

    @Override
    void wydajDzwiek() {
        System.out.println(nazwa + ": Miau!");
    }

    @Override
    public String toString() {
        return "Kot: " + nazwa;
    }
}

class Krowa extends Zwierze {
    Krowa(String nazwa) { super(nazwa); }

    @Override
    void wydajDzwiek() {
        System.out.println(nazwa + ": Muuu!");
    }
}

public class PrzeslanianiePrzyklad {
    public static void main(String[] args) {
        Zwierze[] zwierzeta = {
            new Pies("Burek"),
            new Kot("Mruczek"),
            new Krowa("Mućka"),
            new Zwierze("Rybka")
        };

        for (Zwierze z : zwierzeta) {
            System.out.println(z);
            z.wydajDzwiek();
            System.out.println();
        }
    }
}
```

### Przykład 4 — Użycie super do wywołania metody bazowej
```java
class Pracownik {
    private String imie;
    private double stawka;

    Pracownik(String imie, double stawka) {
        this.imie = imie;
        this.stawka = stawka;
    }

    String getImie() { return imie; }

    double obliczWynagrodzenie() {
        return stawka;
    }

    String getInfo() {
        return imie + ", wynagrodzenie: " + obliczWynagrodzenie() + " zł";
    }
}

class Kierownik extends Pracownik {
    private double premia;

    Kierownik(String imie, double stawka, double premia) {
        super(imie, stawka);
        this.premia = premia;
    }

    @Override
    double obliczWynagrodzenie() {
        // Wywołanie metody bazowej + dodanie premii
        return super.obliczWynagrodzenie() + premia;
    }

    @Override
    String getInfo() {
        return super.getInfo() + " (w tym premia: " + premia + " zł)";
    }
}

class Dyrektor extends Kierownik {
    private double udzialWZyskach;

    Dyrektor(String imie, double stawka, double premia, double udzial) {
        super(imie, stawka, premia);
        this.udzialWZyskach = udzial;
    }

    @Override
    double obliczWynagrodzenie() {
        return super.obliczWynagrodzenie() + udzialWZyskach;
    }

    @Override
    String getInfo() {
        return super.getInfo() + " + udział w zyskach: " + udzialWZyskach + " zł";
    }
}

public class SuperPrzyklad {
    public static void main(String[] args) {
        Pracownik p = new Pracownik("Anna", 5000);
        Kierownik k = new Kierownik("Jan", 7000, 2000);
        Dyrektor d = new Dyrektor("Ewa", 10000, 3000, 5000);

        System.out.println(p.getInfo());
        System.out.println(k.getInfo());
        System.out.println(d.getInfo());
    }
}
```

### Przykład 5 — Kowariantny typ zwracany
```java
class Dokument {
    String tytul;

    Dokument(String tytul) {
        this.tytul = tytul;
    }

    // Metoda zwracająca kopię
    Dokument kopiuj() {
        System.out.println("Kopiowanie dokumentu...");
        return new Dokument(this.tytul + " (kopia)");
    }

    @Override
    public String toString() {
        return "Dokument: " + tytul;
    }
}

class Raport extends Dokument {
    String autor;

    Raport(String tytul, String autor) {
        super(tytul);
        this.autor = autor;
    }

    // Kowariantny typ zwracany — Raport zamiast Dokument
    @Override
    Raport kopiuj() {
        System.out.println("Kopiowanie raportu...");
        return new Raport(this.tytul + " (kopia)", this.autor);
    }

    @Override
    public String toString() {
        return "Raport: " + tytul + " (autor: " + autor + ")";
    }
}

public class KowariantnePrzyklad {
    public static void main(String[] args) {
        Dokument dok = new Dokument("Umowa");
        Dokument kopiaDoc = dok.kopiuj();
        System.out.println(kopiaDoc);

        System.out.println();

        Raport rap = new Raport("Raport Q1", "Jan Kowalski");
        Raport kopiaRap = rap.kopiuj(); // Zwraca Raport, nie Dokument
        System.out.println(kopiaRap);
    }
}
```

### Przykład 6 — Przeciążanie konstruktorów
```java
public class Kolor {
    private int r, g, b;
    private String nazwa;

    // Konstruktor z RGB
    Kolor(int r, int g, int b) {
        this.r = Math.min(255, Math.max(0, r));
        this.g = Math.min(255, Math.max(0, g));
        this.b = Math.min(255, Math.max(0, b));
        this.nazwa = String.format("#%02X%02X%02X", this.r, this.g, this.b);
    }

    // Konstruktor z nazwy
    Kolor(String nazwa) {
        this.nazwa = nazwa;
        switch (nazwa.toLowerCase()) {
            case "czerwony": r = 255; g = 0; b = 0; break;
            case "zielony":  r = 0; g = 255; b = 0; break;
            case "niebieski": r = 0; g = 0; b = 255; break;
            case "biały":    r = 255; g = 255; b = 255; break;
            case "czarny":   r = 0; g = 0; b = 0; break;
            default:         r = 128; g = 128; b = 128; nazwa = "szary";
        }
    }

    // Konstruktor z jednej wartości (odcień szarości)
    Kolor(int szarosc) {
        this(szarosc, szarosc, szarosc);
    }

    // Konstruktor kopiujący
    Kolor(Kolor inny) {
        this(inny.r, inny.g, inny.b);
        this.nazwa = inny.nazwa + " (kopia)";
    }

    @Override
    public String toString() {
        return String.format("%s (R=%d, G=%d, B=%d)", nazwa, r, g, b);
    }

    public static void main(String[] args) {
        Kolor k1 = new Kolor(255, 128, 0);
        Kolor k2 = new Kolor("czerwony");
        Kolor k3 = new Kolor(200);
        Kolor k4 = new Kolor(k2);

        System.out.println(k1);
        System.out.println(k2);
        System.out.println(k3);
        System.out.println(k4);
    }
}
```

### Przykład 7 — Przeciążanie vs przesłanianie razem
```java
class Figura {
    String nazwa;

    Figura(String nazwa) {
        this.nazwa = nazwa;
    }

    // Metoda do przesłonięcia
    double pole() {
        return 0;
    }

    // Przeciążone metody wyswietl
    void wyswietl() {
        System.out.printf("%s: pole = %.2f%n", nazwa, pole());
    }

    void wyswietl(String prefix) {
        System.out.printf("%s %s: pole = %.2f%n", prefix, nazwa, pole());
    }

    void wyswietl(boolean szczegolowo) {
        if (szczegolowo) {
            System.out.printf("[SZCZEGÓŁY] %s: pole = %.4f, klasa = %s%n",
                    nazwa, pole(), getClass().getSimpleName());
        } else {
            wyswietl();
        }
    }
}

class Kolo extends Figura {
    double promien;

    Kolo(double promien) {
        super("Koło(r=" + promien + ")");
        this.promien = promien;
    }

    @Override
    double pole() {
        return Math.PI * promien * promien;
    }
}

class Kwadrat extends Figura {
    double bok;

    Kwadrat(double bok) {
        super("Kwadrat(a=" + bok + ")");
        this.bok = bok;
    }

    @Override
    double pole() {
        return bok * bok;
    }
}

public class OverloadVsOverride {
    public static void main(String[] args) {
        Figura[] figury = { new Kolo(5), new Kwadrat(4) };

        for (Figura f : figury) {
            f.wyswietl();                    // przeciążenie 1
            f.wyswietl(">>>");              // przeciążenie 2
            f.wyswietl(true);               // przeciążenie 3
            System.out.println();
        }
    }
}
```

### Przykład 8 — Przesłanianie equals() i hashCode()
```java
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Punkt {
    private final int x;
    private final int y;

    Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Punkt)) return false;
        Punkt other = (Punkt) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        Punkt p1 = new Punkt(3, 4);
        Punkt p2 = new Punkt(3, 4);
        Punkt p3 = new Punkt(5, 6);

        System.out.println("p1.equals(p2): " + p1.equals(p2)); // true
        System.out.println("p1.equals(p3): " + p1.equals(p3)); // false
        System.out.println("p1.hashCode() == p2.hashCode(): " + (p1.hashCode() == p2.hashCode()));

        // HashSet korzysta z equals() i hashCode()
        Set<Punkt> zbior = new HashSet<>();
        zbior.add(p1);
        zbior.add(p2); // duplikat — nie zostanie dodany
        zbior.add(p3);
        System.out.println("Zbiór: " + zbior); // 2 elementy
    }
}
```

### Przykład 9 — Przesłanianie toString() w hierarchii
```java
class Osoba {
    protected String imie;
    protected int wiek;

    Osoba(String imie, int wiek) {
        this.imie = imie;
        this.wiek = wiek;
    }

    @Override
    public String toString() {
        return imie + " (wiek: " + wiek + ")";
    }
}

class Student extends Osoba {
    private String uczelnia;
    private double srednia;

    Student(String imie, int wiek, String uczelnia, double srednia) {
        super(imie, wiek);
        this.uczelnia = uczelnia;
        this.srednia = srednia;
    }

    @Override
    public String toString() {
        return super.toString() + " — student " + uczelnia + ", średnia: " + srednia;
    }
}

class Doktorant extends Student {
    private String tematPracy;

    Doktorant(String imie, int wiek, String uczelnia, double srednia, String temat) {
        super(imie, wiek, uczelnia, srednia);
        this.tematPracy = temat;
    }

    @Override
    public String toString() {
        return super.toString() + ", temat: \"" + tematPracy + "\"";
    }
}

public class ToStringHierarchia {
    public static void main(String[] args) {
        Osoba o = new Osoba("Anna", 30);
        Student s = new Student("Jan", 22, "UG", 4.5);
        Doktorant d = new Doktorant("Ewa", 28, "PG", 4.8, "Sztuczna inteligencja");

        System.out.println(o);
        System.out.println(s);
        System.out.println(d);
    }
}
```

### Przykład 10 — Metody, których nie można przesłonić (final, static, private)
```java
class Bazowa {
    // Metoda final — NIE MOŻNA przesłonić
    final void metodaFinal() {
        System.out.println("Bazowa.metodaFinal() — nie można przesłonić");
    }

    // Metoda statyczna — NIE JEST przesłaniana, lecz UKRYWANA (hiding)
    static void metodaStatyczna() {
        System.out.println("Bazowa.metodaStatyczna()");
    }

    // Metoda prywatna — NIE JEST dziedziczona
    private void metodaPrywatna() {
        System.out.println("Bazowa.metodaPrywatna()");
    }

    // Metoda zwykła — MOŻNA przesłonić
    void metodaZwykla() {
        System.out.println("Bazowa.metodaZwykla()");
    }

    void wywolajPrywatna() {
        metodaPrywatna(); // dostęp wewnątrz klasy
    }
}

class Pochodna extends Bazowa {
    // final — błąd kompilacji:
    // void metodaFinal() { } // Cannot override final method

    // Ukrywanie metody statycznej (hiding, nie overriding)
    static void metodaStatyczna() {
        System.out.println("Pochodna.metodaStatyczna()");
    }

    // To jest NOWA metoda, nie przesłonięcie (bo bazowa jest private)
    private void metodaPrywatna() {
        System.out.println("Pochodna.metodaPrywatna()");
    }

    @Override
    void metodaZwykla() {
        System.out.println("Pochodna.metodaZwykla()");
    }
}

public class NieMoznaPrzeslonic {
    public static void main(String[] args) {
        Bazowa b = new Pochodna();

        b.metodaFinal();      // Bazowa — final
        b.metodaZwykla();     // Pochodna — przesłonięta (polimorfizm)

        // Metody statyczne — wiązanie statyczne (typ zmiennej, nie obiektu)
        Bazowa.metodaStatyczna();   // Bazowa
        Pochodna.metodaStatyczna(); // Pochodna

        b.wywolajPrywatna();  // Bazowa.metodaPrywatna() — prywatna nie jest dziedziczona
    }
}
```
