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
