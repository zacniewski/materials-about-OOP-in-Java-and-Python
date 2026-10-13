# 2. Paradygmaty obiektowości

## Teoria

### Czym jest programowanie obiektowe (OOP)?
Programowanie obiektowe (Object-Oriented Programming, OOP) to paradygmat programowania, w którym program jest organizowany wokół **obiektów** — struktur łączących dane (pola/atrybuty) z operacjami na tych danych (metody). OOP stanowi alternatywę dla programowania proceduralnego, w którym program jest sekwencją instrukcji operujących na danych globalnych.

### Cztery filary OOP
Programowanie obiektowe opiera się na czterech fundamentalnych zasadach:

1. **Abstrakcja** — ukrywanie szczegółów implementacji i eksponowanie jedynie istotnych cech obiektu. Użytkownik klasy nie musi znać wewnętrznej logiki — korzysta z uproszczonego interfejsu.

2. **Enkapsulacja (hermetyzacja)** — łączenie danych i metod w jedną jednostkę (klasę) oraz kontrolowanie dostępu do wewnętrznego stanu obiektu za pomocą modyfikatorów dostępu (`private`, `protected`, `public`).

3. **Dziedziczenie** — mechanizm pozwalający tworzyć nowe klasy na bazie istniejących. Klasa potomna (podklasa) dziedziczy pola i metody klasy bazowej (nadklasy), co umożliwia ponowne wykorzystanie kodu.

4. **Polimorfizm** — zdolność obiektów różnych klas do reagowania na te same komunikaty (wywołania metod) w różny sposób. Polimorfizm pozwala traktować obiekty podklas jako obiekty klasy bazowej.

### Klasa a obiekt
- **Klasa** — szablon (blueprint) definiujący strukturę i zachowanie obiektów. Określa, jakie pola i metody będą miały obiekty danego typu.
- **Obiekt** — konkretna instancja klasy, istniejąca w pamięci w czasie wykonywania programu. Każdy obiekt ma własny stan (wartości pól).

### Paradygmat proceduralny vs obiektowy
| Cecha                  | Proceduralny                  | Obiektowy                        |
|------------------------|-------------------------------|----------------------------------|
| Organizacja kodu       | Funkcje/procedury             | Klasy i obiekty                  |
| Dane                   | Globalne lub lokalne          | Enkapsulowane w obiektach        |
| Ponowne użycie kodu    | Wywołania funkcji             | Dziedziczenie, kompozycja        |
| Rozszerzalność         | Trudna przy dużych projektach | Łatwa dzięki polimorfizmowi      |

### Zasady projektowania obiektowego (SOLID) — wprowadzenie
- **S** — Single Responsibility Principle (zasada jednej odpowiedzialności)
- **O** — Open/Closed Principle (zasada otwarte-zamknięte)
- **L** — Liskov Substitution Principle (zasada podstawienia Liskov)
- **I** — Interface Segregation Principle (zasada segregacji interfejsów)
- **D** — Dependency Inversion Principle (zasada odwrócenia zależności)

### Relacje między obiektami
- **Asocjacja** — ogólna relacja między obiektami (np. Student uczęszcza na Kurs).
- **Agregacja** — relacja „ma" (has-a), gdzie część może istnieć niezależnie od całości.
- **Kompozycja** — silna forma agregacji, gdzie część nie istnieje bez całości.
- **Dziedziczenie** — relacja „jest" (is-a).

---

## Przykłady

### Przykład 1 — Prosta klasa i obiekt
```java
public class Samochod {
    String marka;
    String model;
    int rokProdukcji;

    void przedstawSie() {
        System.out.println("Jestem " + marka + " " + model + " z roku " + rokProdukcji);
    }

    public static void main(String[] args) {
        Samochod auto1 = new Samochod();
        auto1.marka = "Toyota";
        auto1.model = "Corolla";
        auto1.rokProdukcji = 2020;
        auto1.przedstawSie();

        Samochod auto2 = new Samochod();
        auto2.marka = "Honda";
        auto2.model = "Civic";
        auto2.rokProdukcji = 2022;
        auto2.przedstawSie();
    }
}
```

### Przykład 2 — Abstrakcja — ukrywanie szczegółów
```java
public class KontoBankowe {
    private double saldo;
    private String numerKonta;

    public KontoBankowe(String numerKonta, double saldoPoczatkowe) {
        this.numerKonta = numerKonta;
        this.saldo = saldoPoczatkowe;
    }

    // Użytkownik nie musi wiedzieć, jak wewnętrznie działa wpłata
    public void wplac(double kwota) {
        if (kwota > 0) {
            saldo += kwota;
            System.out.println("Wpłacono: " + kwota + " zł. Saldo: " + saldo + " zł.");
        } else {
            System.out.println("Kwota musi być dodatnia!");
        }
    }

    public void wyplac(double kwota) {
        if (kwota > 0 && kwota <= saldo) {
            saldo -= kwota;
            System.out.println("Wypłacono: " + kwota + " zł. Saldo: " + saldo + " zł.");
        } else {
            System.out.println("Nieprawidłowa kwota lub brak środków!");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public static void main(String[] args) {
        KontoBankowe konto = new KontoBankowe("PL123456", 1000.0);
        konto.wplac(500);
        konto.wyplac(200);
        System.out.println("Aktualne saldo: " + konto.getSaldo() + " zł");
    }
}
```

### Przykład 3 — Enkapsulacja
```java
public class Osoba {
    private String imie;
    private int wiek;

    public Osoba(String imie, int wiek) {
        this.imie = imie;
        setWiek(wiek);
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        if (imie != null && !imie.isEmpty()) {
            this.imie = imie;
        }
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        if (wiek >= 0 && wiek <= 150) {
            this.wiek = wiek;
        } else {
            System.out.println("Nieprawidłowy wiek: " + wiek);
        }
    }

    @Override
    public String toString() {
        return "Osoba{imie='" + imie + "', wiek=" + wiek + "}";
    }

    public static void main(String[] args) {
        Osoba osoba = new Osoba("Anna", 25);
        System.out.println(osoba);

        osoba.setWiek(-5);   // Nieprawidłowy wiek
        osoba.setWiek(30);   // OK
        System.out.println(osoba);
    }
}
```

### Przykład 4 — Dziedziczenie
```java
class Zwierze {
    String nazwa;
    int wiek;

    Zwierze(String nazwa, int wiek) {
        this.nazwa = nazwa;
        this.wiek = wiek;
    }

    void jedz() {
        System.out.println(nazwa + " je.");
    }

    void spij() {
        System.out.println(nazwa + " śpi.");
    }

    void przedstawSie() {
        System.out.println("Jestem " + nazwa + ", mam " + wiek + " lat.");
    }
}

class Pies extends Zwierze {
    String rasa;

    Pies(String nazwa, int wiek, String rasa) {
        super(nazwa, wiek);
        this.rasa = rasa;
    }

    void szczekaj() {
        System.out.println(nazwa + " szczeka: Hau hau!");
    }

    @Override
    void przedstawSie() {
        System.out.println("Jestem pies " + nazwa + ", rasa: " + rasa + ", wiek: " + wiek);
    }
}

class Kot extends Zwierze {
    boolean czyDomowy;

    Kot(String nazwa, int wiek, boolean czyDomowy) {
        super(nazwa, wiek);
        this.czyDomowy = czyDomowy;
    }

    void mruczaj() {
        System.out.println(nazwa + " mruczy: Mrrr...");
    }
}

public class DziedziczeniePrzyklad {
    public static void main(String[] args) {
        Pies pies = new Pies("Burek", 5, "Labrador");
        pies.przedstawSie();
        pies.jedz();
        pies.szczekaj();

        System.out.println();

        Kot kot = new Kot("Mruczek", 3, true);
        kot.przedstawSie();
        kot.spij();
        kot.mruczaj();
    }
}
```

### Przykład 5 — Polimorfizm
```java
class Figura {
    String nazwa;

    Figura(String nazwa) {
        this.nazwa = nazwa;
    }

    double obliczPole() {
        return 0;
    }

    void wyswietlInfo() {
        System.out.printf("%s — pole: %.2f%n", nazwa, obliczPole());
    }
}

class Kolo extends Figura {
    double promien;

    Kolo(double promien) {
        super("Koło");
        this.promien = promien;
    }

    @Override
    double obliczPole() {
        return Math.PI * promien * promien;
    }
}

class Prostokat extends Figura {
    double szerokosc, wysokosc;

    Prostokat(double szerokosc, double wysokosc) {
        super("Prostokąt");
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }

    @Override
    double obliczPole() {
        return szerokosc * wysokosc;
    }
}

class Trojkat extends Figura {
    double podstawa, wysokosc;

    Trojkat(double podstawa, double wysokosc) {
        super("Trójkąt");
        this.podstawa = podstawa;
        this.wysokosc = wysokosc;
    }

    @Override
    double obliczPole() {
        return 0.5 * podstawa * wysokosc;
    }
}

public class PolimorfizmPrzyklad {
    public static void main(String[] args) {
        // Tablica referencji typu bazowego przechowująca różne podklasy
        Figura[] figury = {
            new Kolo(5),
            new Prostokat(4, 6),
            new Trojkat(3, 8)
        };

        // Polimorfizm — ta sama metoda, różne zachowanie
        for (Figura f : figury) {
            f.wyswietlInfo();
        }
    }
}
```

### Przykład 6 — Klasa z wieloma obiektami (różny stan)
```java
public class Student {
    private String imie;
    private String kierunek;
    private double srednia;

    public Student(String imie, String kierunek, double srednia) {
        this.imie = imie;
        this.kierunek = kierunek;
        this.srednia = srednia;
    }

    public boolean czyStypendiasta() {
        return srednia >= 4.5;
    }

    @Override
    public String toString() {
        return imie + " (" + kierunek + "), średnia: " + srednia
               + (czyStypendiasta() ? " [STYPENDIUM]" : "");
    }

    public static void main(String[] args) {
        Student[] studenci = {
            new Student("Anna", "Informatyka", 4.8),
            new Student("Jan", "Matematyka", 3.9),
            new Student("Ewa", "Fizyka", 4.6),
            new Student("Piotr", "Informatyka", 4.2)
        };

        for (Student s : studenci) {
            System.out.println(s);
        }
    }
}
```

### Przykład 7 — Kompozycja (relacja „ma")
```java
class Silnik {
    private int mocKM;
    private String typ;

    Silnik(int mocKM, String typ) {
        this.mocKM = mocKM;
        this.typ = typ;
    }

    void uruchom() {
        System.out.println("Silnik " + typ + " (" + mocKM + " KM) uruchomiony.");
    }

    void zatrzymaj() {
        System.out.println("Silnik zatrzymany.");
    }
}

class Samochod2 {
    private String marka;
    private Silnik silnik; // kompozycja — samochód MA silnik

    Samochod2(String marka, Silnik silnik) {
        this.marka = marka;
        this.silnik = silnik;
    }

    void jedz() {
        System.out.println(marka + " rusza.");
        silnik.uruchom();
    }

    void zatrzymaj() {
        System.out.println(marka + " się zatrzymuje.");
        silnik.zatrzymaj();
    }
}

public class KompozycjaPrzyklad {
    public static void main(String[] args) {
        Silnik silnik = new Silnik(150, "benzyna");
        Samochod2 auto = new Samochod2("Volkswagen Golf", silnik);

        auto.jedz();
        auto.zatrzymaj();
    }
}
```

### Przykład 8 — Porównanie podejścia proceduralnego i obiektowego
```java
// Podejście PROCEDURALNE
class ProceduralneKolo {
    public static double obliczPole(double promien) {
        return Math.PI * promien * promien;
    }

    public static double obliczObwod(double promien) {
        return 2 * Math.PI * promien;
    }
}

// Podejście OBIEKTOWE
class ObiektKolo {
    private double promien;

    ObiektKolo(double promien) {
        this.promien = promien;
    }

    double obliczPole() {
        return Math.PI * promien * promien;
    }

    double obliczObwod() {
        return 2 * Math.PI * promien;
    }

    @Override
    public String toString() {
        return String.format("Koło(r=%.2f, pole=%.2f, obwód=%.2f)",
                promien, obliczPole(), obliczObwod());
    }
}

public class ProceduralneVsObiektowe {
    public static void main(String[] args) {
        // Proceduralne
        double promien = 5.0;
        System.out.println("Proceduralne:");
        System.out.println("  Pole: " + ProceduralneKolo.obliczPole(promien));
        System.out.println("  Obwód: " + ProceduralneKolo.obliczObwod(promien));

        // Obiektowe
        ObiektKolo kolo = new ObiektKolo(5.0);
        System.out.println("Obiektowe:");
        System.out.println("  " + kolo);
    }
}
```

### Przykład 9 — Agregacja (relacja luźna)
```java
import java.util.ArrayList;
import java.util.List;

class Wykladowca {
    private String imie;

    Wykladowca(String imie) {
        this.imie = imie;
    }

    String getImie() {
        return imie;
    }
}

class Kurs {
    private String nazwa;
    private Wykladowca wykladowca; // agregacja — wykładowca istnieje niezależnie od kursu

    Kurs(String nazwa, Wykladowca wykladowca) {
        this.nazwa = nazwa;
        this.wykladowca = wykladowca;
    }

    void info() {
        System.out.println("Kurs: " + nazwa + ", prowadzący: " + wykladowca.getImie());
    }
}

public class AgregacjaPrzyklad {
    public static void main(String[] args) {
        Wykladowca dr = new Wykladowca("dr Jan Kowalski");

        // Ten sam wykładowca prowadzi wiele kursów
        Kurs kurs1 = new Kurs("Programowanie obiektowe", dr);
        Kurs kurs2 = new Kurs("Algorytmy i struktury danych", dr);

        kurs1.info();
        kurs2.info();

        // Wykładowca istnieje niezależnie od kursów
        System.out.println("Wykładowca: " + dr.getImie());
    }
}
```

### Przykład 10 — Klasa z metodami operującymi na stanie obiektu
```java
public class Licznik {
    private int wartosc;
    private final int maksimum;

    public Licznik(int maksimum) {
        this.wartosc = 0;
        this.maksimum = maksimum;
    }

    public void zwieksz() {
        if (wartosc < maksimum) {
            wartosc++;
        } else {
            System.out.println("Osiągnięto maksimum: " + maksimum);
        }
    }

    public void zmniejsz() {
        if (wartosc > 0) {
            wartosc--;
        } else {
            System.out.println("Licznik jest już na zero.");
        }
    }

    public void resetuj() {
        wartosc = 0;
    }

    public int getWartosc() {
        return wartosc;
    }

    public static void main(String[] args) {
        Licznik licznik = new Licznik(5);

        for (int i = 0; i < 7; i++) {
            licznik.zwieksz();
            System.out.println("Wartość: " + licznik.getWartosc());
        }

        licznik.resetuj();
        System.out.println("Po resecie: " + licznik.getWartosc());
    }
}
```

---

## Informacje uzupełniające do zadań

### Kompilacja i uruchamianie programu z wieloma klasami w jednym pliku
- W jednym pliku `.java` może być **tylko jedna** klasa `public` i jej nazwa musi być identyczna z nazwą pliku (np. `public class PolimorfizmPrzyklad` → plik `PolimorfizmPrzyklad.java`).
- Pozostałe klasy w tym samym pliku deklarujemy bez modyfikatora `public` (np. `class Pies extends Zwierze`).
- Kompilacja i uruchomienie z terminala:
```bash
javac PolimorfizmPrzyklad.java   # tworzy pliki .class dla wszystkich klas z pliku
java PolimorfizmPrzyklad         # uruchamia klasę zawierającą metodę main
```
- Od Javy 11 można też uruchomić plik bez osobnej kompilacji: `java PolimorfizmPrzyklad.java`.

### Modyfikatory dostępu — ściąga
| Modyfikator   | Ta sama klasa | Ten sam pakiet | Podklasa | Wszędzie |
|---------------|:-------------:|:--------------:|:--------:|:--------:|
| `private`     | ✔             | ✘              | ✘        | ✘        |
| (brak)        | ✔             | ✔              | ✘*       | ✘        |
| `protected`   | ✔             | ✔              | ✔        | ✘        |
| `public`      | ✔             | ✔              | ✔        | ✔        |

\* podklasa z innego pakietu nie ma dostępu; w tym samym pakiecie — ma.

Uwaga: pola `private` klasy bazowej **są** częścią obiektu podklasy, ale podklasa nie ma do nich bezpośredniego dostępu — musi użyć getterów albo pola `protected`.

### Konstruktory, `this` i `super`
- Konstruktor ma nazwę klasy i nie ma typu zwracanego. Jeśli nie napiszesz żadnego, kompilator doda bezparametrowy konstruktor domyślny.
- `this.pole = parametr;` — rozróżnia pole obiektu od parametru o tej samej nazwie.
- `super(...)` — wywołuje konstruktor nadklasy; musi być **pierwszą instrukcją** konstruktora podklasy. Jeśli nadklasa nie ma konstruktora bezparametrowego, wywołanie `super(...)` jest obowiązkowe.
- `super.metoda()` — wywołuje wersję metody z nadklasy (przydatne przy rozszerzaniu, a nie całkowitym zastępowaniu działania):
```java
@Override
String getDescription() {
    return super.getDescription() + ", zasięg: " + zasiegKm + " km";
}
```

### Walidacja w konstruktorze i metodach
Konstruktor może sprawdzać dane tak samo jak setter. Gdy dane są niepoprawne, można ustawić wartość bezpieczną i wypisać komunikat:
```java
public Konto(double saldoPoczatkowe) {
    if (saldoPoczatkowe < 0) {
        System.out.println("Saldo początkowe nie może być ujemne — ustawiono 0.");
        this.saldo = 0;
    } else {
        this.saldo = saldoPoczatkowe;
    }
}
```
Alternatywnie można zgłosić wyjątek: `throw new IllegalArgumentException("Saldo nie może być ujemne");` (wyjątki omawiamy szerzej w dalszych zajęciach).

### Pole logiczne (`boolean`) jako stan obiektu
Pole typu `boolean` dobrze opisuje stan „tak/nie” (np. dostępność). Getter dla pola logicznego zwyczajowo nazywa się `isXxx()`:
```java
private boolean wlaczony = false;

public boolean isWlaczony() { return wlaczony; }

public void wlacz() {
    if (wlaczony) {
        System.out.println("Urządzenie jest już włączone.");
    } else {
        wlaczony = true;
        System.out.println("Włączono urządzenie.");
    }
}
```

### Metoda `toString()` i adnotacja `@Override`
- Każda klasa dziedziczy po `Object` metodę `toString()`. Domyślnie zwraca ona coś w rodzaju `Book@1b6d3586`, dlatego warto ją nadpisać.
- `System.out.println(obiekt)` oraz `"tekst" + obiekt` automatycznie wywołują `toString()`.
- `@Override` informuje kompilator, że metoda ma nadpisywać metodę z nadklasy — jeśli pomylisz nazwę lub parametry, kompilator zgłosi błąd. Przy nadpisywaniu nie wolno zawęzić dostępu (np. `toString()` musi pozostać `public`).
- `String.format("%s ma %d drzwi, cena %.2f zł", marka, drzwi, cena)` — formatowanie: `%s` tekst, `%d` liczba całkowita, `%.2f` liczba zmiennoprzecinkowa z 2 miejscami, `%b` boolean, `%n` nowa linia (to samo w `System.out.printf`).

### Dziedziczenie — co jest dziedziczone?
- Dziedziczone są pola i metody (poza `private`, do których brak bezpośredniego dostępu) — **nie** są dziedziczone konstruktory.
- Podklasa może: używać odziedziczonych metod, dodawać nowe pola/metody, nadpisywać metody (`@Override`).
- Java pozwala dziedziczyć tylko po **jednej** klasie (`extends` jednej nadklasy). Hierarchia może być wielopoziomowa, np. `Vehicle -> Car -> ElectricCar`.
- Operator `instanceof` sprawdza faktyczny typ obiektu: `if (v instanceof Car) { ... }`.

### Polimorfizm — typ referencji a typ obiektu
```java
Vehicle v = new ElectricCar("Tesla", 2023, 4, 500); // referencja Vehicle, obiekt ElectricCar
v.getDescription();   // wywoła wersję z ElectricCar (wiązanie dynamiczne, w czasie działania)
// v.getZasieg();     // BŁĄD kompilacji — typ referencji Vehicle nie zna tej metody
```
- **Typ referencji** decyduje, *jakie* metody można wywołać (sprawdza kompilator).
- **Typ obiektu** decyduje, *która wersja* metody zostanie wykonana (decyzja w czasie działania programu).
- Dzięki temu pętla `for (Vehicle v : pojazdy)` obsługuje każdy nowy typ pojazdu bez zmiany kodu pętli (zasada otwarte-zamknięte).

### Kompozycja vs agregacja w kodzie
| Cecha                         | Kompozycja                                   | Agregacja                                   |
|-------------------------------|----------------------------------------------|---------------------------------------------|
| Kto tworzy część?             | Zwykle obiekt-całość (w konstruktorze)       | Część tworzona na zewnątrz i przekazywana   |
| Czy część żyje bez całości?   | Nie                                          | Tak                                         |
| Czy część może być współdzielona? | Nie                                      | Tak (np. jeden wykładowca — wiele kursów)   |

Aby wymusić kompozycję, utwórz część wewnątrz konstruktora, oznacz pole jako `private final` i nie dodawaj settera — po utworzeniu obiektu nie da się podmienić części:
```java
class Dom {
    private final Pokoj pokoj; // final — przypisanie tylko raz, w konstruktorze

    Dom(String nazwaPokoju, double metraz) {
        this.pokoj = new Pokoj(nazwaPokoju, metraz); // część tworzona przez całość
    }

    void opisz() {
        System.out.println("Dom zawiera: " + pokoj.opis()); // delegowanie pracy do części
    }
}
```

### Listy (`ArrayList`) — przydatne w zadaniu z koszykiem
Tablica ma stały rozmiar, a `ArrayList` rośnie automatycznie:
```java
import java.util.ArrayList;
import java.util.List;

List<String> nazwy = new ArrayList<>();
nazwy.add("Chleb");            // dodanie elementu
nazwy.add("Mleko");
System.out.println(nazwy.size());   // 2 — liczba elementów
System.out.println(nazwy.get(0));   // "Chleb" — element o indeksie 0
nazwy.remove("Mleko");               // usunięcie elementu
for (String n : nazwy) {             // przejście po liście
    System.out.println(n);
}
```
- W `< >` podajemy typ obiektowy: `List<Double>`, `List<Produkt>` (nie `List<double>`).
- Lista może przechowywać obiekty własnej klasy, np. `List<Produkt> produkty`.

Przykład — ten sam problem (lista ocen) w dwóch stylach:
```java
import java.util.ArrayList;
import java.util.List;

// Styl PROCEDURALNY — dane przekazywane do metod statycznych
class OcenyProceduralnie {
    static void dodajOcene(List<Double> oceny, double ocena) {
        oceny.add(ocena);
    }

    static double srednia(List<Double> oceny) {
        double suma = 0;
        for (double o : oceny) {
            suma += o;
        }
        return oceny.isEmpty() ? 0 : suma / oceny.size();
    }
}

// Styl OBIEKTOWY — dane ukryte wewnątrz obiektu
class Dziennik {
    private final List<Double> oceny = new ArrayList<>();

    void dodajOcene(double ocena) {
        if (ocena >= 2.0 && ocena <= 5.0) {
            oceny.add(ocena);
        } else {
            System.out.println("Nieprawidłowa ocena: " + ocena);
        }
    }

    double srednia() {
        double suma = 0;
        for (double o : oceny) {
            suma += o;
        }
        return oceny.isEmpty() ? 0 : suma / oceny.size();
    }
}

public class OcenyPorownanie {
    public static void main(String[] args) {
        List<Double> oceny = new ArrayList<>();
        OcenyProceduralnie.dodajOcene(oceny, 4.0);
        OcenyProceduralnie.dodajOcene(oceny, 5.0);
        oceny.add(100.0); // nic nie chroni danych przed błędną modyfikacją!
        System.out.println("Proceduralnie: " + OcenyProceduralnie.srednia(oceny));

        Dziennik d = new Dziennik();
        d.dodajOcene(4.0);
        d.dodajOcene(5.0);
        d.dodajOcene(100.0); // odrzucone — enkapsulacja chroni stan
        System.out.println("Obiektowo: " + d.srednia());
    }
}
```

### Typowe błędy
- Brak `super(...)` w konstruktorze podklasy, gdy nadklasa ma tylko konstruktor z parametrami → błąd kompilacji.
- Odwołanie do pola `private` nadklasy w podklasie → błąd kompilacji (użyj gettera lub `protected`).
- Porównywanie tekstów przez `==` zamiast `equals()` (np. `marka.equals("Toyota")`).
- Wywołanie metody na `null` (pole obiektowe niezainicjalizowane w konstruktorze) → `NullPointerException`.
- Kilka klas `public` w jednym pliku → błąd kompilacji.


## Zadania laboratoryjne

Poniższe zadania ćwiczą cztery filary OOP (abstrakcję, enkapsulację, dziedziczenie, polimorfizm) oraz relacje między obiektami (asocjację, agregację, kompozycję) omówione w części teoretycznej. Rozwiązując kolejne zadania, korzystaj z przykładów 1-10 jako wzorca stylu kodu (pola `private`, konstruktory, metody `toString()`, klasa `main` z testowym wywołaniem).

1. Zaprojektuj klasę `Book` z polami `title`, `author`, `available` oraz metodami `borrow()` i `returnBook()`.
   - Pola powinny być `private` (enkapsulacja) — udostępnij je przez gettery, a stan `available` modyfikuj wyłącznie wewnątrz metod `borrow()` i `returnBook()`.
   - Metoda `borrow()` powinna wypożyczać książkę tylko wtedy, gdy `available == true` (ustawia wtedy `available = false` i wypisuje komunikat); w przeciwnym razie powinna poinformować, że książka jest już wypożyczona.
   - Metoda `returnBook()` powinna działać odwrotnie — zwraca książkę (`available = true`), a jeśli książka nie była wypożyczona, wypisuje stosowny komunikat.
   - Dodaj konstruktor inicjalizujący `title`, `author` oraz ustawiający `available` na `true`, a także metodę `toString()` prezentującą stan obiektu.
   - W metodzie `main` utwórz kilka obiektów `Book`, przetestuj wypożyczenie, próbę ponownego wypożyczenia tej samej książki oraz zwrot.

2. Zaimplementuj klasę `BankAccount` z prywatnym polem `balance` i metodami `deposit(double)` oraz `withdraw(double)` z walidacją danych.
   - `deposit(double kwota)` powinna akceptować tylko kwoty dodatnie — dla kwoty ujemnej lub zerowej wypisz komunikat o błędzie i nie zmieniaj salda (por. przykład 2 — `KontoBankowe`).
   - `withdraw(double kwota)` powinna dodatkowo sprawdzać, czy na koncie jest wystarczająco środków (`kwota <= balance`); jeśli nie, wypisz komunikat o braku środków i nie modyfikuj salda.
   - Dodaj konstruktor przyjmujący saldo początkowe (z walidacją, że nie jest ujemne) oraz metodę `getBalance()`.
   - Przetestuj w `main` scenariusze: poprawną wpłatę, wypłatę większą niż saldo oraz wpłatę ujemnej kwoty.

3. Utwórz hierarchię `Vehicle -> Car` i pokaż w `main`, które pola/metody są dziedziczone.
   - Klasa bazowa `Vehicle` powinna mieć wspólne pola (np. `marka`, `rokProdukcji`) oraz metodę, np. `getDescription()`, zwracającą podstawowy opis pojazdu (wzoruj się na przykładzie 4 — `Zwierze`/`Pies`/`Kot`).
   - Klasa `Car` powinna dziedziczyć po `Vehicle` (`extends Vehicle`), wywoływać konstruktor nadklasy przez `super(...)` i dodawać własne pole, np. `liczbaDrzwi`.
   - W `main` utwórz obiekt `Car`, wywołaj na nim zarówno metodę odziedziczoną z `Vehicle`, jak i metodę zdefiniowaną tylko w `Car`, i w komentarzach zaznacz, które elementy pochodzą z klasy bazowej, a które są specyficzne dla `Car`.

4. Dodaj klasę `ElectricCar`, która przesłania metodę `getDescription()` i zademonstruj polimorfizm na tablicy `Vehicle[]`.
   - `ElectricCar` powinna dziedziczyć po `Vehicle` (ewentualnie po `Car` z zadania 3) i nadpisywać (`@Override`) metodę `getDescription()`, dodając informację specyficzną dla pojazdu elektrycznego, np. o zasięgu baterii (por. przykład 5 — `Figura`/`Kolo`/`Prostokat`/`Trojkat`).
   - Utwórz tablicę typu `Vehicle[]` zawierającą różne podklasy (`Car`, `ElectricCar` itd.) i w pętli `for` wywołaj na każdym elemencie `getDescription()` — zwróć uwagę, że mimo tego samego typu referencji (`Vehicle`) wywoływana jest metoda właściwa dla faktycznego typu obiektu (polimorfizm dynamiczny).
   - Dodaj krótki komentarz wyjaśniający, dlaczego to jest przykład polimorfizmu, a nie zwykłego wywołania metody.

5. Przygotuj przykład relacji kompozycji: klasa `Computer` zawiera obiekt `Processor`. Pokaż tworzenie i użycie tej relacji.
   - `Processor` powinien mieć własne pola (np. `model`, `liczbaRdzeni`) oraz metodę np. `wykonajObliczenia()`.
   - `Computer` powinien przechowywać pole typu `Processor` jako `private` i tworzyć/otrzymywać jego instancję w swoim konstruktorze — zgodnie z definicją kompozycji (przykład 7 — `Silnik`/`Samochod2`) procesor nie powinien istnieć niezależnie od komputera, tzn. nie powinno być możliwości podmiany procesora po utworzeniu obiektu `Computer`.
   - Dodaj metodę `Computer.uruchom()`, która deleguje część pracy do obiektu `Processor` (np. wywołuje `wykonajObliczenia()`).
   - W `main` zademonstruj utworzenie `Computer` i wywołanie jego metod, a w komentarzu krótko wyjaśnij różnicę między tą kompozycją a agregacją z przykładu 9 (`Kurs`/`Wykladowca`), gdzie obiekt może istnieć niezależnie od tego, kto go używa.

6. Porównaj podejście proceduralne i obiektowe: ten sam mini-problem (np. obsługa koszyka) zapisz w obu stylach i opisz różnice w 5-7 zdaniach.
   - Wersja proceduralna: zaimplementuj logikę koszyka (dodawanie produktów, obliczanie sumy) za pomocą statycznych metod operujących na strukturach danych przekazywanych jako parametry (podobnie jak `ProceduralneKolo` w przykładzie 8).
   - Wersja obiektowa: zaimplementuj klasę (np. `Koszyk`) przechowującą listę produktów jako pole oraz metody operujące na własnym stanie obiektu (np. `dodajProdukt()`, `obliczSume()`), podobnie jak `ObiektKolo` w przykładzie 8.
   - W `main` uruchom obie wersje na tych samych danych wejściowych i porównaj wyniki.
   - W opisie (5-7 zdań, w komentarzu lub osobnym pliku tekstowym) odnieś się m.in. do: czytelności kodu, łatwości rozszerzania (np. dodania rabatów), ochrony danych przed niepożądaną modyfikacją (enkapsulacja) oraz tego, który styl lepiej sprawdziłby się przy rozroście projektu.
