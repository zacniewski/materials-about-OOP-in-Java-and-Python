# 7. Interfejsy. Implementowanie interfejsów.

## Teoria

### Czym jest interfejs?
Interfejs w Javie to **kontrakt** definiujący zestaw metod, które klasa implementująca musi dostarczyć. Interfejs określa **co** obiekt potrafi robić, ale nie **jak** to robi. Interfejsy umożliwiają osiągnięcie polimorfizmu bez dziedziczenia klas.

### Deklaracja interfejsu
```java
[public] interface NazwaInterfejsu [extends Interfejs1, Interfejs2] {
    // stałe (public static final — domyślnie)
    // metody abstrakcyjne (public abstract — domyślnie)
    // metody domyślne (default) — od Java 8
    // metody statyczne — od Java 8
    // metody prywatne — od Java 9
}
```

### Cechy interfejsów
- Wszystkie metody w interfejsie są domyślnie `public abstract` (do Java 7).
- Wszystkie pola w interfejsie są domyślnie `public static final` (stałe).
- Klasa może implementować **wiele interfejsów** (w przeciwieństwie do dziedziczenia — tylko jedna klasa bazowa).
- Interfejs może rozszerzać (extends) inne interfejsy.
- Od Java 8: metody `default` i `static`.
- Od Java 9: metody `private`.

### Implementowanie interfejsu
Klasa implementuje interfejs za pomocą słowa kluczowego `implements`:
```java
class MojaKlasa implements MojInterfejs {
    // implementacja wszystkich metod abstrakcyjnych interfejsu
}
```

### Metody domyślne (default) — Java 8+
Metody z implementacją w interfejsie. Klasy implementujące mogą je przesłonić lub użyć domyślnej implementacji:
```java
default void metoda() {
    // domyślna implementacja
}
```

### Metody statyczne w interfejsie — Java 8+
Metody statyczne należą do interfejsu, nie do klasy implementującej:
```java
static void metoda() {
    // implementacja
}
```

### Interfejs vs klasa abstrakcyjna
| Cecha                    | Interfejs                    | Klasa abstrakcyjna          |
|--------------------------|------------------------------|-----------------------------|
| Wielokrotna implementacja| Tak (wiele interfejsów)      | Nie (jedna klasa bazowa)    |
| Pola instancji           | Nie (tylko stałe)            | Tak                         |
| Konstruktory             | Nie                          | Tak                         |
| Metody z ciałem          | default, static, private     | Dowolne                     |
| Modyfikatory pól         | Tylko public static final    | Dowolne                     |

### Typowe interfejsy w Java API
- `Comparable<T>` — porównywanie obiektów (`compareTo`).
- `Iterable<T>` — iterowanie po elementach.
- `Serializable` — serializacja obiektów.
- `Cloneable` — klonowanie obiektów.
- `List<T>`, `Set<T>`, `Map<K,V>` — kolekcje.

---

## Przykłady

### Przykład 1 — Prosty interfejs i implementacja
```java
interface Rysowalne {
    void rysuj();
    double obliczPole();
}

class Kolo implements Rysowalne {
    private double promien;

    Kolo(double promien) {
        this.promien = promien;
    }

    @Override
    public void rysuj() {
        System.out.println("Rysuję koło o promieniu " + promien);
    }

    @Override
    public double obliczPole() {
        return Math.PI * promien * promien;
    }
}

class Kwadrat implements Rysowalne {
    private double bok;

    Kwadrat(double bok) {
        this.bok = bok;
    }

    @Override
    public void rysuj() {
        System.out.println("Rysuję kwadrat o boku " + bok);
    }

    @Override
    public double obliczPole() {
        return bok * bok;
    }
}

public class InterfejsPrzyklad {
    public static void main(String[] args) {
        Rysowalne[] figury = {
            new Kolo(5),
            new Kwadrat(4),
            new Kolo(3)
        };

        for (Rysowalne r : figury) {
            r.rysuj();
            System.out.printf("  Pole: %.2f%n", r.obliczPole());
        }
    }
}
```

### Przykład 2 — Klasa implementująca wiele interfejsów
```java
interface Latajace {
    void lec();
    double getMaxWysokosc();
}

interface Plywajace {
    void plyn();
    double getMaxGlebokosc();
}

interface Chodzace {
    void idz();
}

class Kaczka implements Latajace, Plywajace, Chodzace {
    private String imie;

    Kaczka(String imie) {
        this.imie = imie;
    }

    @Override
    public void lec() {
        System.out.println(imie + " leci!");
    }

    @Override
    public double getMaxWysokosc() {
        return 500; // metrów
    }

    @Override
    public void plyn() {
        System.out.println(imie + " płynie!");
    }

    @Override
    public double getMaxGlebokosc() {
        return 2; // metrów
    }

    @Override
    public void idz() {
        System.out.println(imie + " idzie!");
    }
}

public class WieleInterfejsow {
    public static void main(String[] args) {
        Kaczka kaczka = new Kaczka("Donald");

        kaczka.idz();
        kaczka.plyn();
        kaczka.lec();

        // Referencja typu interfejsu
        Latajace latajacy = kaczka;
        latajacy.lec();
        System.out.println("Max wysokość: " + latajacy.getMaxWysokosc() + " m");

        Plywajace plywajacy = kaczka;
        plywajacy.plyn();
    }
}
```

### Przykład 3 — Interfejs Comparable — sortowanie obiektów
```java
import java.util.Arrays;

class Student implements Comparable<Student> {
    private String imie;
    private double srednia;

    Student(String imie, double srednia) {
        this.imie = imie;
        this.srednia = srednia;
    }

    public String getImie() { return imie; }
    public double getSrednia() { return srednia; }

    @Override
    public int compareTo(Student other) {
        // Sortowanie malejąco po średniej
        return Double.compare(other.srednia, this.srednia);
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f)", imie, srednia);
    }
}

public class ComparablePrzyklad {
    public static void main(String[] args) {
        Student[] studenci = {
            new Student("Anna", 4.5),
            new Student("Jan", 3.8),
            new Student("Ewa", 4.9),
            new Student("Piotr", 4.2)
        };

        System.out.println("Przed sortowaniem:");
        System.out.println(Arrays.toString(studenci));

        Arrays.sort(studenci);

        System.out.println("\nPo sortowaniu (malejąco po średniej):");
        System.out.println(Arrays.toString(studenci));
    }
}
```

### Przykład 4 — Metody default w interfejsie (Java 8+)
```java
interface Logger {
    void log(String wiadomosc);

    // Metoda domyślna — klasy mogą ją przesłonić lub użyć domyślnej
    default void logInfo(String wiadomosc) {
        log("[INFO] " + wiadomosc);
    }

    default void logError(String wiadomosc) {
        log("[ERROR] " + wiadomosc);
    }

    default void logWarning(String wiadomosc) {
        log("[WARNING] " + wiadomosc);
    }
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String wiadomosc) {
        System.out.println("KONSOLA: " + wiadomosc);
    }
}

class FileLogger implements Logger {
    @Override
    public void log(String wiadomosc) {
        System.out.println("PLIK: " + wiadomosc);
        // W rzeczywistości zapisywałoby do pliku
    }

    @Override
    public void logError(String wiadomosc) {
        // Przesłonięcie metody default
        log("[!!!ERROR!!!] " + wiadomosc);
    }
}

public class DefaultMethodPrzyklad {
    public static void main(String[] args) {
        Logger console = new ConsoleLogger();
        console.logInfo("Aplikacja uruchomiona");
        console.logWarning("Mało pamięci");
        console.logError("Błąd połączenia");

        System.out.println();

        Logger file = new FileLogger();
        file.logInfo("Aplikacja uruchomiona");
        file.logError("Błąd krytyczny"); // przesłonięta metoda
    }
}
```

### Przykład 5 — Metody statyczne w interfejsie
```java
interface Walidator {
    boolean waliduj(String wartosc);

    // Metoda statyczna — fabryka walidatorów
    static Walidator emailValidator() {
        return wartosc -> wartosc != null && wartosc.contains("@") && wartosc.contains(".");
    }

    static Walidator dlugoscValidator(int minDlugosc) {
        return wartosc -> wartosc != null && wartosc.length() >= minDlugosc;
    }

    static Walidator niepustyValidator() {
        return wartosc -> wartosc != null && !wartosc.trim().isEmpty();
    }
}

public class StaticMethodInterfejs {
    public static void main(String[] args) {
        Walidator emailVal = Walidator.emailValidator();
        Walidator dlugoscVal = Walidator.dlugoscValidator(5);
        Walidator niepustyVal = Walidator.niepustyValidator();

        String[] testowe = {"jan@mail.com", "abc", "", null, "test@x.pl"};

        for (String s : testowe) {
            System.out.printf("'%s' → email: %b, długość>=5: %b, niepusty: %b%n",
                    s,
                    emailVal.waliduj(s),
                    dlugoscVal.waliduj(s),
                    niepustyVal.waliduj(s));
        }
    }
}
```

### Przykład 6 — Interfejs jako typ parametru (strategia)
```java
interface StrategiaSortowania {
    void sortuj(int[] tablica);
    String getNazwa();
}

class SortowanieBabelkowe implements StrategiaSortowania {
    @Override
    public void sortuj(int[] tablica) {
        for (int i = 0; i < tablica.length - 1; i++) {
            for (int j = 0; j < tablica.length - 1 - i; j++) {
                if (tablica[j] > tablica[j + 1]) {
                    int temp = tablica[j];
                    tablica[j] = tablica[j + 1];
                    tablica[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public String getNazwa() { return "Sortowanie bąbelkowe"; }
}

class SortowaniePrzezWybieranie implements StrategiaSortowania {
    @Override
    public void sortuj(int[] tablica) {
        for (int i = 0; i < tablica.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < tablica.length; j++) {
                if (tablica[j] < tablica[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = tablica[minIdx];
            tablica[minIdx] = tablica[i];
            tablica[i] = temp;
        }
    }

    @Override
    public String getNazwa() { return "Sortowanie przez wybieranie"; }
}

public class StrategiaPrzyklad {
    static void sortujIWyswietl(int[] dane, StrategiaSortowania strategia) {
        int[] kopia = dane.clone();
        System.out.print(strategia.getNazwa() + ": ");
        strategia.sortuj(kopia);
        for (int val : kopia) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] dane = {64, 25, 12, 22, 11};

        sortujIWyswietl(dane, new SortowanieBabelkowe());
        sortujIWyswietl(dane, new SortowaniePrzezWybieranie());
    }
}
```

### Przykład 7 — Interfejs z dziedziczeniem interfejsów
```java
interface Identyfikowalny {
    String getId();
}

interface Nazywalny {
    String getNazwa();
}

interface Opisywalny extends Identyfikowalny, Nazywalny {
    String getOpis();

    default String getPodsumowanie() {
        return "[" + getId() + "] " + getNazwa() + ": " + getOpis();
    }
}

class Zadanie implements Opisywalny {
    private String id;
    private String nazwa;
    private String opis;

    Zadanie(String id, String nazwa, String opis) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getNazwa() { return nazwa; }

    @Override
    public String getOpis() { return opis; }
}

public class DziedziczenieInterfejsow {
    public static void main(String[] args) {
        Zadanie z1 = new Zadanie("T-001", "Naprawa buga", "Naprawić NullPointerException w module X");
        Zadanie z2 = new Zadanie("T-002", "Nowa funkcja", "Dodać eksport do PDF");

        System.out.println(z1.getPodsumowanie());
        System.out.println(z2.getPodsumowanie());

        // Referencja typu interfejsu nadrzędnego
        Identyfikowalny ident = z1;
        System.out.println("ID: " + ident.getId());
    }
}
```

### Przykład 8 — Stałe w interfejsie
```java
interface KodyBledow {
    int OK = 200;
    int NOT_FOUND = 404;
    int SERVER_ERROR = 500;
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;

    static String getOpis(int kod) {
        switch (kod) {
            case OK: return "Sukces";
            case NOT_FOUND: return "Nie znaleziono";
            case SERVER_ERROR: return "Błąd serwera";
            case UNAUTHORIZED: return "Brak autoryzacji";
            case FORBIDDEN: return "Dostęp zabroniony";
            default: return "Nieznany kod: " + kod;
        }
    }
}

class OdpowiedzHTTP implements KodyBledow {
    private int kod;
    private String tresc;

    OdpowiedzHTTP(int kod, String tresc) {
        this.kod = kod;
        this.tresc = tresc;
    }

    void wyswietl() {
        System.out.printf("HTTP %d (%s): %s%n", kod, KodyBledow.getOpis(kod), tresc);
    }
}

public class StalePrzyklad {
    public static void main(String[] args) {
        OdpowiedzHTTP[] odpowiedzi = {
            new OdpowiedzHTTP(KodyBledow.OK, "Dane pobrane"),
            new OdpowiedzHTTP(KodyBledow.NOT_FOUND, "Strona nie istnieje"),
            new OdpowiedzHTTP(KodyBledow.SERVER_ERROR, "Wewnętrzny błąd"),
            new OdpowiedzHTTP(KodyBledow.UNAUTHORIZED, "Zaloguj się")
        };

        for (OdpowiedzHTTP o : odpowiedzi) {
            o.wyswietl();
        }
    }
}
```

### Przykład 9 — Interfejs jako callback
```java
interface ZdarzenieKlikniecia {
    void onClick(String elementId);
}

class Przycisk {
    private String id;
    private String etykieta;
    private ZdarzenieKlikniecia listener;

    Przycisk(String id, String etykieta) {
        this.id = id;
        this.etykieta = etykieta;
    }

    void setOnClickListener(ZdarzenieKlikniecia listener) {
        this.listener = listener;
    }

    void kliknij() {
        System.out.println("Kliknięto przycisk: " + etykieta);
        if (listener != null) {
            listener.onClick(id);
        }
    }
}

public class CallbackPrzyklad {
    public static void main(String[] args) {
        Przycisk btnZapisz = new Przycisk("btn-save", "Zapisz");
        Przycisk btnUsun = new Przycisk("btn-delete", "Usuń");

        // Implementacja przez klasę anonimową
        btnZapisz.setOnClickListener(new ZdarzenieKlikniecia() {
            @Override
            public void onClick(String elementId) {
                System.out.println("  → Zapisywanie danych... (element: " + elementId + ")");
            }
        });

        // Implementacja przez lambdę (bo interfejs ma jedną metodę abstrakcyjną)
        btnUsun.setOnClickListener(elementId ->
            System.out.println("  → Usuwanie danych... (element: " + elementId + ")")
        );

        btnZapisz.kliknij();
        btnUsun.kliknij();
    }
}
```

### Przykład 10 — Rozwiązywanie konfliktu metod default
```java
interface InterfejsA {
    default String powitanie() {
        return "Cześć z InterfejsA!";
    }
}

interface InterfejsB {
    default String powitanie() {
        return "Cześć z InterfejsB!";
    }
}

// Klasa implementująca oba interfejsy MUSI rozwiązać konflikt
class MojaKlasa implements InterfejsA, InterfejsB {
    @Override
    public String powitanie() {
        // Można wybrać jedną z implementacji lub stworzyć własną
        return InterfejsA.super.powitanie() + " & " + InterfejsB.super.powitanie();
    }
}

class KlasaA implements InterfejsA {
    // Używa domyślnej implementacji z InterfejsA
}

class KlasaB implements InterfejsB {
    @Override
    public String powitanie() {
        return "Własna implementacja w KlasaB!";
    }
}

public class KonfliktDefault {
    public static void main(String[] args) {
        MojaKlasa mk = new MojaKlasa();
        System.out.println("MojaKlasa: " + mk.powitanie());

        KlasaA ka = new KlasaA();
        System.out.println("KlasaA: " + ka.powitanie());

        KlasaB kb = new KlasaB();
        System.out.println("KlasaB: " + kb.powitanie());
    }
}
```
