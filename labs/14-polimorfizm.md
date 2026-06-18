# 14. Polimorfizm

## Teoria

### Czym jest polimorfizm?
Polimorfizm (z gr. „wiele form") to zdolność obiektów różnych klas do reagowania na **te same komunikaty** (wywołania metod) w **różny sposób**. Jest jednym z czterech filarów OOP i kluczowym mechanizmem umożliwiającym pisanie elastycznego, rozszerzalnego kodu.

### Rodzaje polimorfizmu w Javie

1. **Polimorfizm statyczny (compile-time)** — realizowany przez **przeciążanie metod** (overloading). Kompilator wybiera metodę na podstawie typów argumentów.

2. **Polimorfizm dynamiczny (runtime)** — realizowany przez **przesłanianie metod** (overriding). JVM wybiera metodę na podstawie **rzeczywistego typu obiektu** (nie typu zmiennej referencyjnej).

### Wiązanie dynamiczne (Dynamic Binding / Late Binding)
Gdy metoda jest wywoływana na referencji typu bazowego, JVM w czasie wykonywania sprawdza **rzeczywisty typ obiektu** i wywołuje odpowiednią wersję metody. To jest serce polimorfizmu dynamicznego.

```java
Zwierze z = new Pies();  // typ zmiennej: Zwierze, typ obiektu: Pies
z.wydajDzwiek();          // wywoła Pies.wydajDzwiek(), nie Zwierze.wydajDzwiek()
```

### Polimorfizm przez interfejsy
Interfejsy umożliwiają polimorfizm bez wspólnej klasy bazowej. Różne klasy implementujące ten sam interfejs mogą być traktowane jednolicie.

### Polimorfizm parametryczny (Generics)
Typy generyczne pozwalają pisać kod działający z różnymi typami danych, zachowując bezpieczeństwo typów:
```java
List<String> lista = new ArrayList<>();
```

### Zasady polimorfizmu
- Polimorfizm działa tylko dla **metod instancji** (nie statycznych).
- Pola **nie są polimorficzne** — dostęp do pól zależy od typu zmiennej, nie obiektu.
- Metody `private`, `static` i `final` nie uczestniczą w polimorfizmie dynamicznym.

### Korzyści z polimorfizmu
- **Rozszerzalność** — nowe klasy można dodawać bez modyfikacji istniejącego kodu.
- **Elastyczność** — kod operuje na abstrakcjach, nie na konkretnych implementacjach.
- **Zasada Open/Closed** — kod jest otwarty na rozszerzenia, zamknięty na modyfikacje.

---

## Przykłady

### Przykład 1 — Polimorfizm dynamiczny — podstawy
```java
class Instrument {
    String nazwa;

    Instrument(String nazwa) {
        this.nazwa = nazwa;
    }

    void graj() {
        System.out.println(nazwa + " wydaje dźwięk.");
    }
}

class Gitara extends Instrument {
    Gitara() { super("Gitara"); }

    @Override
    void graj() {
        System.out.println("🎸 Gitara: brzdęk brzdęk!");
    }
}

class Pianino extends Instrument {
    Pianino() { super("Pianino"); }

    @Override
    void graj() {
        System.out.println("🎹 Pianino: ding dong ding!");
    }
}

class Bebny extends Instrument {
    Bebny() { super("Bębny"); }

    @Override
    void graj() {
        System.out.println("🥁 Bębny: bum bum tss!");
    }
}

class Skrzypce extends Instrument {
    Skrzypce() { super("Skrzypce"); }

    @Override
    void graj() {
        System.out.println("🎻 Skrzypce: wiii wiii!");
    }
}

public class PolimorfizmPodstawy {
    // Metoda przyjmuje typ bazowy — działa z KAŻDĄ podklasą
    static void zagrajKoncert(Instrument[] instrumenty) {
        System.out.println("=== KONCERT ===");
        for (Instrument i : instrumenty) {
            i.graj(); // polimorfizm — każdy instrument gra inaczej
        }
        System.out.println("=== KONIEC ===");
    }

    public static void main(String[] args) {
        Instrument[] orkiestra = {
            new Gitara(),
            new Pianino(),
            new Bebny(),
            new Skrzypce(),
            new Gitara()
        };

        zagrajKoncert(orkiestra);
    }
}
```

### Przykład 2 — Polimorfizm przez interfejsy
```java
interface Platnosc {
    boolean zaplac(double kwota);
    String getNazwa();
}

class Gotowka implements Platnosc {
    private double portfel;

    Gotowka(double portfel) { this.portfel = portfel; }

    @Override
    public boolean zaplac(double kwota) {
        if (kwota <= portfel) {
            portfel -= kwota;
            System.out.printf("Zapłacono gotówką: %.2f zł (zostało: %.2f zł)%n", kwota, portfel);
            return true;
        }
        System.out.println("Brak gotówki!");
        return false;
    }

    @Override
    public String getNazwa() { return "Gotówka"; }
}

class KartaKredytowa implements Platnosc {
    private double limit;
    private double wykorzystane;

    KartaKredytowa(double limit) { this.limit = limit; this.wykorzystane = 0; }

    @Override
    public boolean zaplac(double kwota) {
        if (wykorzystane + kwota <= limit) {
            wykorzystane += kwota;
            System.out.printf("Zapłacono kartą: %.2f zł (wykorzystano: %.2f/%.2f zł)%n",
                    kwota, wykorzystane, limit);
            return true;
        }
        System.out.println("Przekroczono limit karty!");
        return false;
    }

    @Override
    public String getNazwa() { return "Karta kredytowa"; }
}

class Blik implements Platnosc {
    @Override
    public boolean zaplac(double kwota) {
        System.out.printf("Zapłacono BLIK: %.2f zł%n", kwota);
        return true;
    }

    @Override
    public String getNazwa() { return "BLIK"; }
}

public class PolimorfizmInterfejsy {
    // Metoda nie wie, jaka konkretna płatność zostanie użyta
    static void dokonajZakupu(String produkt, double cena, Platnosc platnosc) {
        System.out.println("Kupuję: " + produkt + " za " + cena + " zł [" + platnosc.getNazwa() + "]");
        platnosc.zaplac(cena);
        System.out.println();
    }

    public static void main(String[] args) {
        Platnosc gotowka = new Gotowka(200);
        Platnosc karta = new KartaKredytowa(1000);
        Platnosc blik = new Blik();

        dokonajZakupu("Kawa", 15.50, gotowka);
        dokonajZakupu("Laptop", 3500, karta);
        dokonajZakupu("Książka", 49.99, blik);
    }
}
```

### Przykład 3 — Polimorfizm w kolekcjach
```java
import java.util.ArrayList;
import java.util.List;

abstract class Figura {
    abstract double pole();
    abstract String getNazwa();

    @Override
    public String toString() {
        return String.format("%-12s pole = %8.2f", getNazwa(), pole());
    }
}

class Kolo extends Figura {
    double r;
    Kolo(double r) { this.r = r; }
    @Override double pole() { return Math.PI * r * r; }
    @Override String getNazwa() { return "Koło(r=" + r + ")"; }
}

class Prostokat extends Figura {
    double a, b;
    Prostokat(double a, double b) { this.a = a; this.b = b; }
    @Override double pole() { return a * b; }
    @Override String getNazwa() { return "Prost(" + a + "x" + b + ")"; }
}

class Trojkat extends Figura {
    double p, h;
    Trojkat(double p, double h) { this.p = p; this.h = h; }
    @Override double pole() { return 0.5 * p * h; }
    @Override String getNazwa() { return "Trój(p=" + p + ")"; }
}

public class PolimorfizmKolekcje {
    public static void main(String[] args) {
        // Lista przechowuje różne typy figur — polimorfizm
        List<Figura> figury = new ArrayList<>();
        figury.add(new Kolo(5));
        figury.add(new Prostokat(4, 6));
        figury.add(new Trojkat(3, 8));
        figury.add(new Kolo(10));
        figury.add(new Prostokat(2, 2));

        // Polimorficzne przetwarzanie
        double sumaPol = 0;
        for (Figura f : figury) {
            System.out.println(f);
            sumaPol += f.pole();
        }
        System.out.printf("\nSuma pól: %.2f%n", sumaPol);

        // Znajdź figurę o największym polu
        Figura max = figury.get(0);
        for (Figura f : figury) {
            if (f.pole() > max.pole()) max = f;
        }
        System.out.println("Największa: " + max);
    }
}
```

### Przykład 4 — Polimorfizm a wiązanie dynamiczne
```java
class A {
    String kto() { return "A"; }
    void info() { System.out.println("Jestem " + kto()); }
}

class B extends A {
    @Override
    String kto() { return "B"; }
}

class C extends B {
    @Override
    String kto() { return "C"; }
}

public class WiazanieDynamiczne {
    public static void main(String[] args) {
        A obj1 = new A();
        A obj2 = new B(); // typ zmiennej: A, typ obiektu: B
        A obj3 = new C(); // typ zmiennej: A, typ obiektu: C

        // Wiązanie dynamiczne — metoda kto() wywoływana z rzeczywistego typu
        obj1.info(); // "Jestem A"
        obj2.info(); // "Jestem B" (nie A!)
        obj3.info(); // "Jestem C" (nie A!)

        System.out.println("\nTypy:");
        System.out.println("obj1.getClass(): " + obj1.getClass().getSimpleName());
        System.out.println("obj2.getClass(): " + obj2.getClass().getSimpleName());
        System.out.println("obj3.getClass(): " + obj3.getClass().getSimpleName());
    }
}
```

### Przykład 5 — Wzorzec Strategy z polimorfizmem
```java
interface StrategiaRabatu {
    double obliczCene(double cenaBazowa);
    String getOpis();
}

class BezRabatu implements StrategiaRabatu {
    @Override
    public double obliczCene(double cenaBazowa) { return cenaBazowa; }
    @Override
    public String getOpis() { return "Brak rabatu"; }
}

class RabatProcentowy implements StrategiaRabatu {
    private double procent;
    RabatProcentowy(double procent) { this.procent = procent; }

    @Override
    public double obliczCene(double cenaBazowa) { return cenaBazowa * (1 - procent / 100); }
    @Override
    public String getOpis() { return "Rabat " + procent + "%"; }
}

class RabatKwotowy implements StrategiaRabatu {
    private double kwota;
    RabatKwotowy(double kwota) { this.kwota = kwota; }

    @Override
    public double obliczCene(double cenaBazowa) { return Math.max(0, cenaBazowa - kwota); }
    @Override
    public String getOpis() { return "Rabat " + kwota + " zł"; }
}

class RabatVIP implements StrategiaRabatu {
    @Override
    public double obliczCene(double cenaBazowa) {
        if (cenaBazowa > 1000) return cenaBazowa * 0.7;
        if (cenaBazowa > 500) return cenaBazowa * 0.8;
        return cenaBazowa * 0.9;
    }
    @Override
    public String getOpis() { return "Rabat VIP (progowy)"; }
}

class Zamowienie {
    private String produkt;
    private double cena;
    private StrategiaRabatu rabat;

    Zamowienie(String produkt, double cena, StrategiaRabatu rabat) {
        this.produkt = produkt;
        this.cena = cena;
        this.rabat = rabat;
    }

    void wyswietl() {
        double cenaKoncowa = rabat.obliczCene(cena);
        System.out.printf("%-15s Cena: %8.2f → %8.2f zł [%s]%n",
                produkt, cena, cenaKoncowa, rabat.getOpis());
    }
}

public class StrategyPrzyklad {
    public static void main(String[] args) {
        Zamowienie[] zamowienia = {
            new Zamowienie("Laptop", 3500, new RabatProcentowy(10)),
            new Zamowienie("Mysz", 50, new BezRabatu()),
            new Zamowienie("Monitor", 1200, new RabatKwotowy(200)),
            new Zamowienie("Klawiatura", 800, new RabatVIP())
        };

        for (Zamowienie z : zamowienia) {
            z.wyswietl();
        }
    }
}
```

### Przykład 6 — Polimorfizm z tablicą typów bazowych
```java
abstract class Pracownik {
    protected String imie;

    Pracownik(String imie) { this.imie = imie; }

    abstract double obliczPensje();

    @Override
    public String toString() {
        return String.format("%-20s pensja: %8.2f zł", imie + " [" + getTyp() + "]", obliczPensje());
    }

    abstract String getTyp();
}

class Etatowy extends Pracownik {
    private double pensjaMiesieczna;
    Etatowy(String imie, double pensja) { super(imie); this.pensjaMiesieczna = pensja; }
    @Override double obliczPensje() { return pensjaMiesieczna; }
    @Override String getTyp() { return "Etat"; }
}

class Zleceniobiorca extends Pracownik {
    private double stawkaGodzinowa;
    private int godziny;
    Zleceniobiorca(String imie, double stawka, int godz) {
        super(imie); this.stawkaGodzinowa = stawka; this.godziny = godz;
    }
    @Override double obliczPensje() { return stawkaGodzinowa * godziny; }
    @Override String getTyp() { return "Zlecenie"; }
}

class Stazista extends Pracownik {
    Stazista(String imie) { super(imie); }
    @Override double obliczPensje() { return 0; }
    @Override String getTyp() { return "Staż"; }
}

public class PolimorfizmTablica {
    public static void main(String[] args) {
        Pracownik[] zespol = {
            new Etatowy("Anna Kowalska", 8000),
            new Zleceniobiorca("Jan Nowak", 50, 120),
            new Etatowy("Ewa Wiśniewska", 6500),
            new Stazista("Piotr Młody"),
            new Zleceniobiorca("Maria Stara", 75, 80)
        };

        double suma = 0;
        for (Pracownik p : zespol) {
            System.out.println(p);
            suma += p.obliczPensje();
        }
        System.out.printf("\nŁączny koszt wynagrodzeń: %.2f zł%n", suma);
    }
}
```

### Przykład 7 — Polimorfizm z metodą fabrykującą
```java
abstract class Powiadomienie {
    protected String odbiorca;
    protected String tresc;

    Powiadomienie(String odbiorca, String tresc) {
        this.odbiorca = odbiorca;
        this.tresc = tresc;
    }

    abstract void wyslij();

    // Metoda fabrykująca — polimorficzne tworzenie obiektów
    static Powiadomienie utworz(String typ, String odbiorca, String tresc) {
        switch (typ.toLowerCase()) {
            case "email": return new EmailPowiadomienie(odbiorca, tresc);
            case "sms":   return new SmsPowiadomienie(odbiorca, tresc);
            case "push":  return new PushPowiadomienie(odbiorca, tresc);
            default: throw new IllegalArgumentException("Nieznany typ: " + typ);
        }
    }
}

class EmailPowiadomienie extends Powiadomienie {
    EmailPowiadomienie(String odbiorca, String tresc) { super(odbiorca, tresc); }

    @Override
    void wyslij() {
        System.out.println("📧 Email do " + odbiorca + ": " + tresc);
    }
}

class SmsPowiadomienie extends Powiadomienie {
    SmsPowiadomienie(String odbiorca, String tresc) { super(odbiorca, tresc); }

    @Override
    void wyslij() {
        System.out.println("📱 SMS do " + odbiorca + ": " + tresc);
    }
}

class PushPowiadomienie extends Powiadomienie {
    PushPowiadomienie(String odbiorca, String tresc) { super(odbiorca, tresc); }

    @Override
    void wyslij() {
        System.out.println("🔔 Push do " + odbiorca + ": " + tresc);
    }
}

public class FabrykaPowiadomien {
    public static void main(String[] args) {
        String[][] dane = {
            {"email", "jan@mail.com", "Witaj, Janie!"},
            {"sms", "+48123456789", "Kod: 1234"},
            {"push", "user_42", "Nowa wiadomość"},
            {"email", "ewa@mail.com", "Raport gotowy"}
        };

        for (String[] d : dane) {
            Powiadomienie p = Powiadomienie.utworz(d[0], d[1], d[2]);
            p.wyslij(); // polimorfizm — każdy typ wysyła inaczej
        }
    }
}
```

### Przykład 8 — Pola nie są polimorficzne
```java
class Rodzic {
    String pole = "Rodzic";

    String metoda() {
        return "Rodzic.metoda()";
    }
}

class Dziecko extends Rodzic {
    String pole = "Dziecko"; // przesłania (hiding), nie przesłanianie

    @Override
    String metoda() {
        return "Dziecko.metoda()";
    }
}

public class PolaNiePolimorficzne {
    public static void main(String[] args) {
        Rodzic obj = new Dziecko();

        // Metoda — polimorfizm dynamiczny (typ obiektu)
        System.out.println("metoda(): " + obj.metoda()); // "Dziecko.metoda()"

        // Pole — BEZ polimorfizmu (typ zmiennej)
        System.out.println("pole: " + obj.pole); // "Rodzic" (nie "Dziecko"!)

        // Jawne rzutowanie
        Dziecko dziecko = (Dziecko) obj;
        System.out.println("dziecko.pole: " + dziecko.pole); // "Dziecko"
    }
}
```

### Przykład 9 — Polimorfizm z Comparable i Comparator
```java
import java.util.*;

class Produkt implements Comparable<Produkt> {
    String nazwa;
    double cena;
    int ocena;

    Produkt(String nazwa, double cena, int ocena) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.ocena = ocena;
    }

    @Override
    public int compareTo(Produkt other) {
        return this.nazwa.compareTo(other.nazwa); // domyślnie po nazwie
    }

    @Override
    public String toString() {
        return String.format("%-15s %7.2f zł  ★%d", nazwa, cena, ocena);
    }
}

public class PolimorfizmSortowanie {
    public static void main(String[] args) {
        List<Produkt> produkty = new ArrayList<>(Arrays.asList(
            new Produkt("Monitor", 1200, 4),
            new Produkt("Laptop", 3500, 5),
            new Produkt("Mysz", 50, 3),
            new Produkt("Klawiatura", 130, 4),
            new Produkt("Słuchawki", 250, 5)
        ));

        // Sortowanie domyślne (Comparable — po nazwie)
        Collections.sort(produkty);
        System.out.println("Po nazwie:");
        produkty.forEach(System.out::println);

        // Polimorfizm przez Comparator — różne strategie sortowania
        produkty.sort(Comparator.comparingDouble(p -> p.cena));
        System.out.println("\nPo cenie:");
        produkty.forEach(System.out::println);

        produkty.sort(Comparator.comparingInt(p -> -p.ocena));
        System.out.println("\nPo ocenie (malejąco):");
        produkty.forEach(System.out::println);
    }
}
```

### Przykład 10 — Polimorfizm w praktyce: system logowania
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

interface Logger {
    void log(String poziom, String wiadomosc);
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String poziom, String wiadomosc) {
        String czas = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] [%-5s] %s%n", czas, poziom, wiadomosc);
    }
}

class ListLogger implements Logger {
    private List<String> logi = new ArrayList<>();

    @Override
    public void log(String poziom, String wiadomosc) {
        logi.add("[" + poziom + "] " + wiadomosc);
    }

    List<String> getLogi() { return logi; }
}

class FilterLogger implements Logger {
    private Logger delegate;
    private String minPoziom;

    FilterLogger(Logger delegate, String minPoziom) {
        this.delegate = delegate;
        this.minPoziom = minPoziom;
    }

    private int priorytet(String poziom) {
        switch (poziom) {
            case "DEBUG": return 0;
            case "INFO":  return 1;
            case "WARN":  return 2;
            case "ERROR": return 3;
            default: return -1;
        }
    }

    @Override
    public void log(String poziom, String wiadomosc) {
        if (priorytet(poziom) >= priorytet(minPoziom)) {
            delegate.log(poziom, wiadomosc);
        }
    }
}

public class SystemLogowania {
    // Metoda nie wie, jaki konkretny logger jest użyty — polimorfizm
    static void uruchomAplikacje(Logger logger) {
        logger.log("INFO", "Aplikacja uruchomiona");
        logger.log("DEBUG", "Ładowanie konfiguracji...");
        logger.log("INFO", "Konfiguracja załadowana");
        logger.log("WARN", "Mało pamięci");
        logger.log("ERROR", "Nie można połączyć z bazą danych");
        logger.log("INFO", "Ponowna próba połączenia...");
    }

    public static void main(String[] args) {
        System.out.println("=== Console Logger ===");
        uruchomAplikacje(new ConsoleLogger());

        System.out.println("\n=== Filtered Logger (WARN+) ===");
        uruchomAplikacje(new FilterLogger(new ConsoleLogger(), "WARN"));

        System.out.println("\n=== List Logger ===");
        ListLogger listLogger = new ListLogger();
        uruchomAplikacje(listLogger);
        System.out.println("Zebrane logi: " + listLogger.getLogi().size());
        listLogger.getLogi().forEach(l -> System.out.println("  " + l));
    }
}
```
