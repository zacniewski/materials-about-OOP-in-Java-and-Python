# 11. Klasy abstrakcyjne

## Teoria

### Czym jest klasa abstrakcyjna?
Klasa abstrakcyjna to klasa oznaczona modyfikatorem `abstract`, która **nie może być bezpośrednio instancjonowana** (nie można utworzyć jej obiektu za pomocą `new`). Służy jako **baza** dla klas pochodnych, definiując wspólny interfejs i częściową implementację.

### Deklaracja klasy abstrakcyjnej
```java
abstract class NazwaKlasy {
    // pola (zwykłe i statyczne)
    // konstruktory
    // metody abstrakcyjne (bez ciała)
    // metody konkretne (z ciałem)
}
```

### Metody abstrakcyjne
Metoda abstrakcyjna to metoda zadeklarowana ze słowem `abstract`, **bez ciała** (bez implementacji). Każda nieabstrakcyjna podklasa **musi** dostarczyć implementację wszystkich odziedziczonych metod abstrakcyjnych.

```java
abstract void obliczPole(); // brak ciała — średnik zamiast nawiasów klamrowych
```

### Cechy klas abstrakcyjnych
- Mogą zawierać zarówno metody abstrakcyjne, jak i konkretne (z implementacją).
- Mogą mieć **konstruktory** (wywoływane przez `super()` w podklasach).
- Mogą mieć **pola** (instancji i statyczne) z dowolnymi modyfikatorami dostępu.
- Mogą implementować interfejsy (częściowo lub w pełni).
- Klasa z choćby jedną metodą abstrakcyjną **musi** być oznaczona jako `abstract`.
- Podklasa abstrakcyjna nie musi implementować wszystkich metod abstrakcyjnych.

### Klasa abstrakcyjna vs interfejs
| Cecha                    | Klasa abstrakcyjna          | Interfejs                    |
|--------------------------|-----------------------------|------------------------------|
| Dziedziczenie            | Jedno (extends)             | Wielokrotne (implements)     |
| Pola instancji           | Tak                         | Nie (tylko stałe)            |
| Konstruktory             | Tak                         | Nie                          |
| Metody z ciałem          | Tak                         | default, static, private     |
| Modyfikatory dostępu     | Dowolne                     | public (domyślnie)           |
| Kiedy używać             | Wspólna baza z częściową impl. | Kontrakt bez stanu        |

### Kiedy używać klasy abstrakcyjnej?
- Gdy klasy pochodne mają **wspólny stan** (pola) i **wspólne zachowanie** (metody konkretne).
- Gdy chcesz wymusić implementację pewnych metod w podklasach.
- Gdy hierarchia klas ma relację „jest" (is-a) z częściowo wspólną logiką.

---

## Przykłady

### Przykład 1 — Prosta klasa abstrakcyjna
```java
abstract class Figura {
    String nazwa;

    Figura(String nazwa) {
        this.nazwa = nazwa;
    }

    // Metoda abstrakcyjna — każda podklasa musi ją zaimplementować
    abstract double obliczPole();
    abstract double obliczObwod();

    // Metoda konkretna — wspólna dla wszystkich podklas
    void wyswietlInfo() {
        System.out.printf("%s: pole=%.2f, obwód=%.2f%n", nazwa, obliczPole(), obliczObwod());
    }
}

class Kolo extends Figura {
    double promien;

    Kolo(double promien) {
        super("Koło");
        this.promien = promien;
    }

    @Override
    double obliczPole() { return Math.PI * promien * promien; }

    @Override
    double obliczObwod() { return 2 * Math.PI * promien; }
}

class Prostokat extends Figura {
    double a, b;

    Prostokat(double a, double b) {
        super("Prostokąt");
        this.a = a;
        this.b = b;
    }

    @Override
    double obliczPole() { return a * b; }

    @Override
    double obliczObwod() { return 2 * (a + b); }
}

class Trojkat extends Figura {
    double a, b, c, h;

    Trojkat(double a, double b, double c, double h) {
        super("Trójkąt");
        this.a = a; this.b = b; this.c = c; this.h = h;
    }

    @Override
    double obliczPole() { return 0.5 * a * h; }

    @Override
    double obliczObwod() { return a + b + c; }
}

public class KlasaAbstrakcyjnaPrzyklad {
    public static void main(String[] args) {
        // Figura f = new Figura("Test"); // Błąd! Nie można instancjonować klasy abstrakcyjnej

        Figura[] figury = {
            new Kolo(5),
            new Prostokat(4, 6),
            new Trojkat(3, 4, 5, 4)
        };

        for (Figura f : figury) {
            f.wyswietlInfo();
        }
    }
}
```

### Przykład 2 — Klasa abstrakcyjna z konstruktorem i polami
```java
abstract class Pracownik {
    private String imie;
    private String stanowisko;
    private double stawkaBazowa;

    Pracownik(String imie, String stanowisko, double stawkaBazowa) {
        this.imie = imie;
        this.stanowisko = stanowisko;
        this.stawkaBazowa = stawkaBazowa;
    }

    String getImie() { return imie; }
    String getStanowisko() { return stanowisko; }
    double getStawkaBazowa() { return stawkaBazowa; }

    // Metoda abstrakcyjna — każdy typ pracownika liczy wynagrodzenie inaczej
    abstract double obliczWynagrodzenie();

    void wyswietlPasekPlacowy() {
        System.out.printf("%-15s %-20s Wynagrodzenie: %.2f zł%n",
                imie, "[" + stanowisko + "]", obliczWynagrodzenie());
    }
}

class PracownikEtatowy extends Pracownik {
    PracownikEtatowy(String imie, double stawkaMiesieczna) {
        super(imie, "Etatowy", stawkaMiesieczna);
    }

    @Override
    double obliczWynagrodzenie() {
        return getStawkaBazowa(); // stała pensja
    }
}

class PracownikGodzinowy extends Pracownik {
    private int przepracowaneGodziny;

    PracownikGodzinowy(String imie, double stawkaGodzinowa, int godziny) {
        super(imie, "Godzinowy", stawkaGodzinowa);
        this.przepracowaneGodziny = godziny;
    }

    @Override
    double obliczWynagrodzenie() {
        int nadgodziny = Math.max(0, przepracowaneGodziny - 160);
        int normalne = przepracowaneGodziny - nadgodziny;
        return normalne * getStawkaBazowa() + nadgodziny * getStawkaBazowa() * 1.5;
    }
}

class PracownikProwizyjny extends Pracownik {
    private double sprzedaz;
    private double procentProwizji;

    PracownikProwizyjny(String imie, double bazowa, double sprzedaz, double procent) {
        super(imie, "Prowizyjny", bazowa);
        this.sprzedaz = sprzedaz;
        this.procentProwizji = procent;
    }

    @Override
    double obliczWynagrodzenie() {
        return getStawkaBazowa() + sprzedaz * procentProwizji;
    }
}

public class PracownicyPrzyklad {
    public static void main(String[] args) {
        Pracownik[] pracownicy = {
            new PracownikEtatowy("Anna", 6000),
            new PracownikGodzinowy("Jan", 35, 180),
            new PracownikProwizyjny("Ewa", 3000, 50000, 0.05)
        };

        System.out.println("=== Paski płacowe ===");
        double suma = 0;
        for (Pracownik p : pracownicy) {
            p.wyswietlPasekPlacowy();
            suma += p.obliczWynagrodzenie();
        }
        System.out.printf("Łączne wynagrodzenia: %.2f zł%n", suma);
    }
}
```

### Przykład 3 — Wzorzec Template Method
```java
abstract class RaportGenerator {
    // Template Method — definiuje szkielet algorytmu
    final void generujRaport() {
        zbierzDane();
        przetworzDane();
        formatujRaport();
        wyswietlRaport();
    }

    // Metody abstrakcyjne — podklasy definiują szczegóły
    abstract void zbierzDane();
    abstract void przetworzDane();
    abstract void formatujRaport();

    // Metoda konkretna — wspólna implementacja
    void wyswietlRaport() {
        System.out.println("--- Raport wygenerowany ---\n");
    }
}

class RaportSprzedazy extends RaportGenerator {
    @Override
    void zbierzDane() {
        System.out.println("[Sprzedaż] Pobieranie danych z bazy sprzedaży...");
    }

    @Override
    void przetworzDane() {
        System.out.println("[Sprzedaż] Obliczanie sum i średnich...");
    }

    @Override
    void formatujRaport() {
        System.out.println("[Sprzedaż] Formatowanie jako tabela...");
    }
}

class RaportMagazynowy extends RaportGenerator {
    @Override
    void zbierzDane() {
        System.out.println("[Magazyn] Skanowanie stanów magazynowych...");
    }

    @Override
    void przetworzDane() {
        System.out.println("[Magazyn] Identyfikacja braków...");
    }

    @Override
    void formatujRaport() {
        System.out.println("[Magazyn] Formatowanie jako lista alertów...");
    }
}

public class TemplateMethodPrzyklad {
    public static void main(String[] args) {
        RaportGenerator raport1 = new RaportSprzedazy();
        raport1.generujRaport();

        RaportGenerator raport2 = new RaportMagazynowy();
        raport2.generujRaport();
    }
}
```

### Przykład 4 — Hierarchia klas abstrakcyjnych
```java
abstract class Pojazd {
    private String marka;
    private int rokProdukcji;

    Pojazd(String marka, int rokProdukcji) {
        this.marka = marka;
        this.rokProdukcji = rokProdukcji;
    }

    String getMarka() { return marka; }
    int getRokProdukcji() { return rokProdukcji; }

    abstract String getTyp();
    abstract int getLiczbaPasazerow();

    @Override
    public String toString() {
        return String.format("%s %s (%d), pasażerów: %d",
                getTyp(), marka, rokProdukcji, getLiczbaPasazerow());
    }
}

// Klasa abstrakcyjna dziedzicząca po abstrakcyjnej
abstract class PojazdSilnikowy extends Pojazd {
    private int mocKM;

    PojazdSilnikowy(String marka, int rok, int mocKM) {
        super(marka, rok);
        this.mocKM = mocKM;
    }

    int getMocKM() { return mocKM; }

    @Override
    public String toString() {
        return super.toString() + ", moc: " + mocKM + " KM";
    }
}

class Samochod extends PojazdSilnikowy {
    private int liczbaDrzwi;

    Samochod(String marka, int rok, int mocKM, int liczbaDrzwi) {
        super(marka, rok, mocKM);
        this.liczbaDrzwi = liczbaDrzwi;
    }

    @Override
    String getTyp() { return "Samochód"; }

    @Override
    int getLiczbaPasazerow() { return 5; }
}

class Motocykl extends PojazdSilnikowy {
    Motocykl(String marka, int rok, int mocKM) {
        super(marka, rok, mocKM);
    }

    @Override
    String getTyp() { return "Motocykl"; }

    @Override
    int getLiczbaPasazerow() { return 2; }
}

class Rower extends Pojazd {
    Rower(String marka, int rok) {
        super(marka, rok);
    }

    @Override
    String getTyp() { return "Rower"; }

    @Override
    int getLiczbaPasazerow() { return 1; }
}

public class HierarchiaAbstrakcyjna {
    public static void main(String[] args) {
        Pojazd[] pojazdy = {
            new Samochod("Toyota", 2023, 150, 5),
            new Motocykl("Honda", 2022, 100),
            new Rower("Trek", 2024)
        };

        for (Pojazd p : pojazdy) {
            System.out.println(p);
        }
    }
}
```

### Przykład 5 — Klasa abstrakcyjna z metodami konkretnymi i abstrakcyjnymi
```java
import java.util.ArrayList;
import java.util.List;

abstract class Kolekcja<T> {
    protected List<T> elementy = new ArrayList<>();

    void dodaj(T element) {
        elementy.add(element);
    }

    void usun(T element) {
        elementy.remove(element);
    }

    int rozmiar() {
        return elementy.size();
    }

    boolean czyPusta() {
        return elementy.isEmpty();
    }

    // Metoda abstrakcyjna — różne kolekcje wyświetlają się inaczej
    abstract void wyswietl();

    // Metoda abstrakcyjna — różne kolekcje filtrują inaczej
    abstract List<T> filtruj(String kryterium);
}

class KolekcjaKsiazek extends Kolekcja<String> {
    @Override
    void wyswietl() {
        System.out.println("📚 Biblioteka (" + rozmiar() + " książek):");
        for (int i = 0; i < elementy.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + elementy.get(i));
        }
    }

    @Override
    List<String> filtruj(String kryterium) {
        List<String> wynik = new ArrayList<>();
        for (String ksiazka : elementy) {
            if (ksiazka.toLowerCase().contains(kryterium.toLowerCase())) {
                wynik.add(ksiazka);
            }
        }
        return wynik;
    }
}

public class KolekcjaAbstrakcyjna {
    public static void main(String[] args) {
        KolekcjaKsiazek biblioteka = new KolekcjaKsiazek();
        biblioteka.dodaj("Czysty kod — Robert C. Martin");
        biblioteka.dodaj("Java. Efektywne programowanie — Joshua Bloch");
        biblioteka.dodaj("Wzorce projektowe — Gang of Four");
        biblioteka.dodaj("Java. Podstawy — Cay Horstmann");

        biblioteka.wyswietl();

        System.out.println("\nKsiążki zawierające 'Java':");
        List<String> javaBooks = biblioteka.filtruj("Java");
        javaBooks.forEach(b -> System.out.println("  → " + b));
    }
}
```

### Przykład 6 — Klasa abstrakcyjna implementująca interfejs
```java
interface Eksportowalny {
    String eksportujDoCSV();
    String eksportujDoJSON();
}

abstract class DaneRaportowe implements Eksportowalny {
    private String tytul;

    DaneRaportowe(String tytul) {
        this.tytul = tytul;
    }

    String getTytul() { return tytul; }

    // Implementacja jednej metody interfejsu
    @Override
    public String eksportujDoCSV() {
        return "tytul," + tytul + "\n" + getDaneCSV();
    }

    // Druga metoda pozostaje abstrakcyjna — podklasy muszą ją zaimplementować
    // eksportujDoJSON() — nie implementujemy tutaj

    abstract String getDaneCSV();
}

class RaportFinansowy extends DaneRaportowe {
    private double przychod;
    private double koszty;

    RaportFinansowy(String tytul, double przychod, double koszty) {
        super(tytul);
        this.przychod = przychod;
        this.koszty = koszty;
    }

    @Override
    String getDaneCSV() {
        return "przychod," + przychod + "\nkoszty," + koszty + "\nzysk," + (przychod - koszty);
    }

    @Override
    public String eksportujDoJSON() {
        return String.format("{\"tytul\":\"%s\",\"przychod\":%.2f,\"koszty\":%.2f,\"zysk\":%.2f}",
                getTytul(), przychod, koszty, przychod - koszty);
    }
}

public class AbstrakcjaZInterfejsem {
    public static void main(String[] args) {
        RaportFinansowy raport = new RaportFinansowy("Q1 2025", 150000, 95000);

        System.out.println("CSV:");
        System.out.println(raport.eksportujDoCSV());

        System.out.println("\nJSON:");
        System.out.println(raport.eksportujDoJSON());
    }
}
```

### Przykład 7 — Klasa abstrakcyjna z polami i logiką walidacji
```java
abstract class Konto {
    private String numer;
    private double saldo;

    Konto(String numer, double saldoPoczatkowe) {
        this.numer = numer;
        this.saldo = saldoPoczatkowe;
    }

    String getNumer() { return numer; }
    double getSaldo() { return saldo; }

    void wplac(double kwota) {
        if (kwota <= 0) throw new IllegalArgumentException("Kwota musi być dodatnia");
        saldo += kwota;
    }

    void wyplac(double kwota) {
        if (kwota <= 0) throw new IllegalArgumentException("Kwota musi być dodatnia");
        if (kwota > getMaxWyplata()) {
            System.out.println("Przekroczono limit wypłaty!");
            return;
        }
        if (kwota > saldo) {
            System.out.println("Brak środków!");
            return;
        }
        saldo -= kwota;
    }

    // Każdy typ konta ma inny limit
    abstract double getMaxWyplata();
    abstract String getTypKonta();
    abstract double obliczOdsetki();

    void naliczOdsetki() {
        double odsetki = obliczOdsetki();
        saldo += odsetki;
        System.out.printf("[%s] Naliczono odsetki: %.2f zł%n", getTypKonta(), odsetki);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f zł", getTypKonta(), numer, saldo);
    }
}

class KontoOszczednosciowe extends Konto {
    KontoOszczednosciowe(String numer, double saldo) {
        super(numer, saldo);
    }

    @Override
    double getMaxWyplata() { return 5000; }

    @Override
    String getTypKonta() { return "Oszczędnościowe"; }

    @Override
    double obliczOdsetki() { return getSaldo() * 0.04; } // 4% rocznie
}

class KontoBiezace extends Konto {
    KontoBiezace(String numer, double saldo) {
        super(numer, saldo);
    }

    @Override
    double getMaxWyplata() { return 50000; }

    @Override
    String getTypKonta() { return "Bieżące"; }

    @Override
    double obliczOdsetki() { return getSaldo() * 0.01; } // 1% rocznie
}

public class KontaPrzyklad {
    public static void main(String[] args) {
        Konto[] konta = {
            new KontoOszczednosciowe("OSZ-001", 10000),
            new KontoBiezace("BIE-001", 25000)
        };

        for (Konto k : konta) {
            System.out.println(k);
            k.naliczOdsetki();
            System.out.println(k);
            System.out.println();
        }
    }
}
```

### Przykład 8 — Klasa abstrakcyjna jako baza dla gry
```java
abstract class PostacGry {
    private String nazwa;
    private int hp;
    private int maxHp;

    PostacGry(String nazwa, int maxHp) {
        this.nazwa = nazwa;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    String getNazwa() { return nazwa; }
    int getHp() { return hp; }
    boolean czyZyje() { return hp > 0; }

    void otrzymajObrazenia(int obrazenia) {
        hp = Math.max(0, hp - obrazenia);
        System.out.printf("  %s otrzymuje %d obrażeń! (HP: %d/%d)%n", nazwa, obrazenia, hp, maxHp);
    }

    void ulecz(int wartosc) {
        hp = Math.min(maxHp, hp + wartosc);
        System.out.printf("  %s uleczony o %d! (HP: %d/%d)%n", nazwa, wartosc, hp, maxHp);
    }

    abstract int atakuj();
    abstract String getKlasa();

    @Override
    public String toString() {
        return String.format("[%s] %s HP: %d/%d", getKlasa(), nazwa, hp, maxHp);
    }
}

class Wojownik extends PostacGry {
    Wojownik(String nazwa) { super(nazwa, 120); }

    @Override
    int atakuj() {
        int obrazenia = 15 + (int)(Math.random() * 10);
        System.out.println(getNazwa() + " atakuje mieczem! (" + obrazenia + " dmg)");
        return obrazenia;
    }

    @Override
    String getKlasa() { return "Wojownik"; }
}

class Mag extends PostacGry {
    Mag(String nazwa) { super(nazwa, 80); }

    @Override
    int atakuj() {
        int obrazenia = 20 + (int)(Math.random() * 15);
        System.out.println(getNazwa() + " rzuca kulę ognia! (" + obrazenia + " dmg)");
        return obrazenia;
    }

    @Override
    String getKlasa() { return "Mag"; }
}

public class GraPrzyklad {
    public static void main(String[] args) {
        PostacGry gracz = new Wojownik("Arthas");
        PostacGry wrog = new Mag("Gandalf");

        System.out.println("=== WALKA ===");
        System.out.println(gracz);
        System.out.println(wrog);

        int runda = 1;
        while (gracz.czyZyje() && wrog.czyZyje()) {
            System.out.println("\n--- Runda " + runda++ + " ---");
            wrog.otrzymajObrazenia(gracz.atakuj());
            if (wrog.czyZyje()) {
                gracz.otrzymajObrazenia(wrog.atakuj());
            }
        }

        System.out.println("\n=== WYNIK ===");
        if (gracz.czyZyje()) {
            System.out.println(gracz.getNazwa() + " wygrywa!");
        } else {
            System.out.println(wrog.getNazwa() + " wygrywa!");
        }
    }
}
```

### Przykład 9 — Klasa abstrakcyjna z metodą final
```java
abstract class Walidator {
    // Metoda final — nie może być przesłonięta
    final boolean waliduj(String wartosc) {
        if (wartosc == null) {
            System.out.println("Wartość nie może być null!");
            return false;
        }
        if (wartosc.trim().isEmpty()) {
            System.out.println("Wartość nie może być pusta!");
            return false;
        }
        return walidujSpecyficznie(wartosc);
    }

    // Metoda abstrakcyjna — specyficzna walidacja
    abstract boolean walidujSpecyficznie(String wartosc);
    abstract String getNazwa();
}

class EmailWalidator extends Walidator {
    @Override
    boolean walidujSpecyficznie(String wartosc) {
        return wartosc.contains("@") && wartosc.contains(".");
    }

    @Override
    String getNazwa() { return "Email"; }
}

class PeselWalidator extends Walidator {
    @Override
    boolean walidujSpecyficznie(String wartosc) {
        return wartosc.length() == 11 && wartosc.chars().allMatch(Character::isDigit);
    }

    @Override
    String getNazwa() { return "PESEL"; }
}

public class WalidatorPrzyklad {
    public static void main(String[] args) {
        Walidator[] walidatory = { new EmailWalidator(), new PeselWalidator() };
        String[] testy = {"jan@mail.com", "abc", null, "", "12345678901", "1234"};

        for (Walidator w : walidatory) {
            System.out.println("=== " + w.getNazwa() + " ===");
            for (String t : testy) {
                System.out.printf("  '%s' → %b%n", t, w.waliduj(t));
            }
        }
    }
}
```

### Przykład 10 — Klasa abstrakcyjna z enum
```java
abstract class Platnosc {
    private double kwota;
    private String waluta;

    Platnosc(double kwota, String waluta) {
        this.kwota = kwota;
        this.waluta = waluta;
    }

    double getKwota() { return kwota; }
    String getWaluta() { return waluta; }

    abstract boolean przetworz();
    abstract String getMetoda();
    abstract double getProwizja();

    double getKwotaZProwizja() {
        return kwota + getProwizja();
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f %s (prowizja: %.2f %s, razem: %.2f %s)",
                getMetoda(), kwota, waluta, getProwizja(), waluta, getKwotaZProwizja(), waluta);
    }
}

class PlatnoscKarta extends Platnosc {
    PlatnoscKarta(double kwota) { super(kwota, "PLN"); }

    @Override
    boolean przetworz() {
        System.out.println("Przetwarzanie płatności kartą...");
        return true;
    }

    @Override
    String getMetoda() { return "Karta"; }

    @Override
    double getProwizja() { return getKwota() * 0.015; } // 1.5%
}

class PlatnoscPrzelew extends Platnosc {
    PlatnoscPrzelew(double kwota) { super(kwota, "PLN"); }

    @Override
    boolean przetworz() {
        System.out.println("Przetwarzanie przelewu...");
        return true;
    }

    @Override
    String getMetoda() { return "Przelew"; }

    @Override
    double getProwizja() { return 0; } // bez prowizji
}

class PlatnoscBlik extends Platnosc {
    PlatnoscBlik(double kwota) { super(kwota, "PLN"); }

    @Override
    boolean przetworz() {
        System.out.println("Przetwarzanie BLIK...");
        return true;
    }

    @Override
    String getMetoda() { return "BLIK"; }

    @Override
    double getProwizja() { return 0.50; } // stała prowizja
}

public class PlatnosciPrzyklad {
    public static void main(String[] args) {
        Platnosc[] platnosci = {
            new PlatnoscKarta(100),
            new PlatnoscPrzelew(500),
            new PlatnoscBlik(50)
        };

        for (Platnosc p : platnosci) {
            System.out.println(p);
            p.przetworz();
            System.out.println();
        }
    }
}
```
