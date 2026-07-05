# 5. Enkapsulacja. Modyfikatory dostępu.

## Teoria

### Czym jest enkapsulacja?
Enkapsulacja (hermetyzacja) to jeden z czterech filarów programowania obiektowego. Polega na:
- **Ukrywaniu wewnętrznego stanu obiektu** przed bezpośrednim dostępem z zewnątrz.
- **Udostępnianiu kontrolowanego interfejsu** (metody publiczne) do interakcji z obiektem.
- **Ochronie integralności danych** — walidacja wartości przed ich przypisaniem.

Enkapsulacja realizowana jest w Javie za pomocą **modyfikatorów dostępu** oraz wzorca **getter/setter**.

### Modyfikatory dostępu w Javie
Java posiada cztery poziomy dostępu:

| Modyfikator   | Klasa | Pakiet | Podklasa | Świat (inne pakiety) |
|---------------|:-----:|:------:|:--------:|:--------------------:|
| `private`     |  ✅   |   ❌   |    ❌    |         ❌           |
| (domyślny)    |  ✅   |   ✅   |    ❌    |         ❌           |
| `protected`   |  ✅   |   ✅   |    ✅    |         ❌           |
| `public`      |  ✅   |   ✅   |    ✅    |         ✅           |

### Szczegóły modyfikatorów

1. **`private`** — najbardziej restrykcyjny. Element dostępny tylko wewnątrz klasy, w której został zadeklarowany. Zalecany dla pól klasy.

2. **Domyślny (package-private)** — brak jawnego modyfikatora. Element dostępny dla wszystkich klas w tym samym pakiecie.

3. **`protected`** — element dostępny w tym samym pakiecie oraz w podklasach (nawet w innych pakietach).

4. **`public`** — element dostępny z dowolnego miejsca w programie.

### Wzorzec getter/setter
Gettery i settery to metody publiczne umożliwiające kontrolowany dostęp do prywatnych pól:
- **Getter** — metoda zwracająca wartość pola (`getX()`, `isX()` dla boolean).
- **Setter** — metoda ustawiająca wartość pola z opcjonalną walidacją (`setX(value)`).

### Zalety enkapsulacji
- **Kontrola dostępu** — można walidować dane przed przypisaniem.
- **Ukrywanie implementacji** — zmiana wewnętrznej logiki nie wpływa na kod klienta.
- **Łatwiejsze debugowanie** — dostęp do pól odbywa się przez metody, które można monitorować.
- **Niezmienność (immutability)** — można tworzyć obiekty, których stan nie zmienia się po utworzeniu.

### Klasy niezmienne (immutable)
Klasa niezmienna to klasa, której obiekty nie mogą zmienić stanu po utworzeniu:
- Wszystkie pola są `private final`.
- Brak setterów.
- Klasa oznaczona jako `final` (nie można jej dziedziczyć).
- Jeśli pola zawierają obiekty mutowalne, zwracane są ich kopie (defensive copy).

---

## Przykłady

### Przykład 1 — Enkapsulacja z getterami i setterami
```java
public class KontoBankowe {
    private String wlasciciel;
    private double saldo;
    private String numerKonta;

    public KontoBankowe(String wlasciciel, String numerKonta, double saldoPoczatkowe) {
        this.wlasciciel = wlasciciel;
        this.numerKonta = numerKonta;
        setSaldo(saldoPoczatkowe);
    }

    // Getter
    public String getWlasciciel() {
        return wlasciciel;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumerKonta() {
        return numerKonta;
    }

    // Setter z walidacją
    private void setSaldo(double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        } else {
            System.out.println("Saldo nie może być ujemne!");
        }
    }

    public void wplac(double kwota) {
        if (kwota > 0) {
            this.saldo += kwota;
            System.out.printf("Wpłacono %.2f zł. Nowe saldo: %.2f zł%n", kwota, saldo);
        } else {
            System.out.println("Kwota wpłaty musi być dodatnia!");
        }
    }

    public void wyplac(double kwota) {
        if (kwota > 0 && kwota <= saldo) {
            this.saldo -= kwota;
            System.out.printf("Wypłacono %.2f zł. Nowe saldo: %.2f zł%n", kwota, saldo);
        } else {
            System.out.println("Nieprawidłowa kwota lub brak środków!");
        }
    }

    public static void main(String[] args) {
        KontoBankowe konto = new KontoBankowe("Jan Kowalski", "PL123", 1000);
        System.out.println("Właściciel: " + konto.getWlasciciel());
        System.out.println("Saldo: " + konto.getSaldo());

        konto.wplac(500);
        konto.wyplac(200);
        konto.wyplac(5000); // brak środków

        // konto.saldo = -1000; // Błąd kompilacji! Pole jest private.
    }
}
```

### Przykład 2 — Porównanie: bez enkapsulacji vs z enkapsulacją
```java
// BEZ enkapsulacji — niebezpieczne!
class StudentBezEnkapsulacji {
    public String imie;
    public int wiek;
    public double srednia;
}

// Z enkapsulacją — bezpieczne
class StudentZEnkapsulacja {
    private String imie;
    private int wiek;
    private double srednia;

    public StudentZEnkapsulacja(String imie, int wiek, double srednia) {
        setImie(imie);
        setWiek(wiek);
        setSrednia(srednia);
    }

    public String getImie() { return imie; }
    public int getWiek() { return wiek; }
    public double getSrednia() { return srednia; }

    public void setImie(String imie) {
        if (imie != null && !imie.trim().isEmpty()) {
            this.imie = imie.trim();
        } else {
            throw new IllegalArgumentException("Imię nie może być puste!");
        }
    }

    public void setWiek(int wiek) {
        if (wiek >= 18 && wiek <= 100) {
            this.wiek = wiek;
        } else {
            throw new IllegalArgumentException("Wiek studenta musi być między 18 a 100!");
        }
    }

    public void setSrednia(double srednia) {
        if (srednia >= 2.0 && srednia <= 5.0) {
            this.srednia = srednia;
        } else {
            throw new IllegalArgumentException("Średnia musi być między 2.0 a 5.0!");
        }
    }
}

public class EnkapsulacjaPorownanie {
    public static void main(String[] args) {
        // Bez enkapsulacji — można ustawić nieprawidłowe wartości
        StudentBezEnkapsulacji s1 = new StudentBezEnkapsulacji();
        s1.imie = "";        // pusty string — brak walidacji
        s1.wiek = -5;        // ujemny wiek — brak walidacji
        s1.srednia = 99.9;   // niemożliwa średnia — brak walidacji
        System.out.println("Bez enkapsulacji: " + s1.imie + ", " + s1.wiek + ", " + s1.srednia);

        // Z enkapsulacją — walidacja chroni dane
        StudentZEnkapsulacja s2 = new StudentZEnkapsulacja("Anna", 22, 4.5);
        System.out.println("Z enkapsulacją: " + s2.getImie() + ", " + s2.getWiek() + ", " + s2.getSrednia());

        try {
            s2.setWiek(-5); // Wyjątek!
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}
```

### Przykład 3 — Wszystkie cztery modyfikatory dostępu
```java
package com.przyklad;

public class DemoModyfikatorow {
    public String publiczne = "publiczne";
    protected String chronione = "chronione";
    String pakietowe = "pakietowe";          // domyślny (package-private)
    private String prywatne = "prywatne";

    public void wyswietlWszystkie() {
        // Wewnątrz klasy — dostęp do wszystkich pól
        System.out.println("public: " + publiczne);
        System.out.println("protected: " + chronione);
        System.out.println("package-private: " + pakietowe);
        System.out.println("private: " + prywatne);
    }
}
```

```java
package com.przyklad;

// Klasa w TYM SAMYM pakiecie
public class KlasaWTymSamymPakiecie {
    public void test() {
        DemoModyfikatorow demo = new DemoModyfikatorow();
        System.out.println(demo.publiczne);   // ✅ OK
        System.out.println(demo.chronione);   // ✅ OK (ten sam pakiet)
        System.out.println(demo.pakietowe);   // ✅ OK (ten sam pakiet)
        // System.out.println(demo.prywatne); // ❌ Błąd kompilacji!
    }
}
```

```java
package com.inny;

import com.przyklad.DemoModyfikatorow;

// Klasa w INNYM pakiecie
public class KlasaWInnymPakiecie {
    public void test() {
        DemoModyfikatorow demo = new DemoModyfikatorow();
        System.out.println(demo.publiczne);   // ✅ OK
        // System.out.println(demo.chronione); // ❌ Błąd (nie jest podklasą)
        // System.out.println(demo.pakietowe); // ❌ Błąd (inny pakiet)
        // System.out.println(demo.prywatne);  // ❌ Błąd
    }
}
```

```java
package com.inny;

import com.przyklad.DemoModyfikatorow;

// Podklasa w INNYM pakiecie
public class PodklasaWInnymPakiecie extends DemoModyfikatorow {
    public void test() {
        System.out.println(publiczne);   // ✅ OK
        System.out.println(chronione);   // ✅ OK (podklasa — dostęp przez dziedziczenie)
        // System.out.println(pakietowe); // ❌ Błąd (inny pakiet)
        // System.out.println(prywatne);  // ❌ Błąd
    }
}
```

### Przykład 4 — Klasa niezmienna (immutable)
```java
public final class Adres {
    private final String ulica;
    private final String miasto;
    private final String kodPocztowy;

    public Adres(String ulica, String miasto, String kodPocztowy) {
        this.ulica = ulica;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
    }

    // Tylko gettery — brak setterów
    public String getUlica() { return ulica; }
    public String getMiasto() { return miasto; }
    public String getKodPocztowy() { return kodPocztowy; }

    // Zamiast modyfikować obiekt, tworzymy nowy
    public Adres zmienMiasto(String noweMiasto) {
        return new Adres(this.ulica, noweMiasto, this.kodPocztowy);
    }

    @Override
    public String toString() {
        return ulica + ", " + kodPocztowy + " " + miasto;
    }

    public static void main(String[] args) {
        Adres adres1 = new Adres("Długa 10", "Gdańsk", "80-001");
        System.out.println("Adres 1: " + adres1);

        // Nie można zmienić pól:
        // adres1.miasto = "Warszawa"; // Błąd kompilacji!

        // Tworzenie nowego obiektu z innym miastem
        Adres adres2 = adres1.zmienMiasto("Warszawa");
        System.out.println("Adres 2: " + adres2);
        System.out.println("Adres 1 (niezmieniony): " + adres1);
    }
}
```

### Przykład 5 — Enkapsulacja z walidacją w setterach
```java
public class Temperatura {
    private double wartoscCelsjusz;
    private static final double ZERO_BEZWZGLEDNE = -273.15;

    public Temperatura(double celsjusz) {
        setWartoscCelsjusz(celsjusz);
    }

    public double getWartoscCelsjusz() {
        return wartoscCelsjusz;
    }

    public double getWartoscFahrenheit() {
        return wartoscCelsjusz * 9.0 / 5.0 + 32;
    }

    public double getWartoscKelvin() {
        return wartoscCelsjusz - ZERO_BEZWZGLEDNE;
    }

    public void setWartoscCelsjusz(double celsjusz) {
        if (celsjusz < ZERO_BEZWZGLEDNE) {
            throw new IllegalArgumentException(
                "Temperatura nie może być niższa niż zero bezwzględne (" + ZERO_BEZWZGLEDNE + "°C)!");
        }
        this.wartoscCelsjusz = celsjusz;
    }

    public void setWartoscFahrenheit(double fahrenheit) {
        setWartoscCelsjusz((fahrenheit - 32) * 5.0 / 9.0);
    }

    @Override
    public String toString() {
        return String.format("%.2f°C = %.2f°F = %.2fK",
                wartoscCelsjusz, getWartoscFahrenheit(), getWartoscKelvin());
    }

    public static void main(String[] args) {
        Temperatura t = new Temperatura(100);
        System.out.println("Wrzenie wody: " + t);

        t.setWartoscCelsjusz(0);
        System.out.println("Zamarzanie wody: " + t);

        t.setWartoscFahrenheit(98.6);
        System.out.println("Temperatura ciała: " + t);

        try {
            t.setWartoscCelsjusz(-300); // poniżej zera bezwzględnego
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}
```

### Przykład 6 — Pole tylko do odczytu (read-only)
```java
public class Zamowienie {
    private static int nastepnyNumer = 1;

    private final int numer;           // tylko do odczytu — nadawany automatycznie
    private final String klient;       // tylko do odczytu
    private double kwota;
    private String status;

    public Zamowienie(String klient, double kwota) {
        this.numer = nastepnyNumer++;
        this.klient = klient;
        this.kwota = kwota;
        this.status = "NOWE";
    }

    // Gettery dla pól read-only
    public int getNumer() { return numer; }
    public String getKlient() { return klient; }
    public double getKwota() { return kwota; }
    public String getStatus() { return status; }

    // Setter tylko dla statusu — z walidacją przejść
    public void setStatus(String nowyStatus) {
        if (status.equals("ANULOWANE")) {
            System.out.println("Nie można zmienić statusu anulowanego zamówienia!");
            return;
        }
        this.status = nowyStatus;
    }

    @Override
    public String toString() {
        return String.format("Zamówienie #%d [%s] — %s, %.2f zł", numer, status, klient, kwota);
    }

    public static void main(String[] args) {
        Zamowienie z1 = new Zamowienie("Anna", 150.0);
        Zamowienie z2 = new Zamowienie("Jan", 299.99);

        System.out.println(z1);
        System.out.println(z2);

        z1.setStatus("W REALIZACJI");
        System.out.println(z1);

        z1.setStatus("ANULOWANE");
        z1.setStatus("NOWE"); // nie zadziała
        System.out.println(z1);
    }
}
```

### Przykład 7 — Dostęp pakietowy (domyślny)
```java
package com.gra;

// Klasa z dostępem pakietowym — widoczna tylko w pakiecie com.gra
class SilnikGry {
    private int fps;
    private boolean uruchomiony;

    SilnikGry(int fps) {
        this.fps = fps;
        this.uruchomiony = false;
    }

    void uruchom() {
        uruchomiony = true;
        System.out.println("Silnik uruchomiony z " + fps + " FPS");
    }

    void zatrzymaj() {
        uruchomiony = false;
        System.out.println("Silnik zatrzymany");
    }

    boolean czyUruchomiony() {
        return uruchomiony;
    }
}

// Klasa publiczna — fasada dla silnika
public class Gra {
    private SilnikGry silnik; // SilnikGry jest package-private
    private String nazwa;

    public Gra(String nazwa) {
        this.nazwa = nazwa;
        this.silnik = new SilnikGry(60);
    }

    public void start() {
        System.out.println("Uruchamianie gry: " + nazwa);
        silnik.uruchom();
    }

    public void stop() {
        System.out.println("Zatrzymywanie gry: " + nazwa);
        silnik.zatrzymaj();
    }

    public static void main(String[] args) {
        Gra gra = new Gra("Space Invaders");
        gra.start();
        gra.stop();
    }
}
```

### Przykład 8 — Protected w dziedziczeniu
```java
class Pojazd {
    private String marka;
    protected int predkosc;  // dostępne w podklasach
    protected int maxPredkosc;

    public Pojazd(String marka, int maxPredkosc) {
        this.marka = marka;
        this.maxPredkosc = maxPredkosc;
        this.predkosc = 0;
    }

    public String getMarka() { return marka; }
    public int getPredkosc() { return predkosc; }

    protected void zmienPredkosc(int delta) {
        int nowaPredkosc = predkosc + delta;
        if (nowaPredkosc < 0) nowaPredkosc = 0;
        if (nowaPredkosc > maxPredkosc) nowaPredkosc = maxPredkosc;
        this.predkosc = nowaPredkosc;
    }
}

class SamochodSportowy extends Pojazd {
    private boolean turbo;

    SamochodSportowy(String marka, int maxPredkosc) {
        super(marka, maxPredkosc);
        this.turbo = false;
    }

    void wlaczTurbo() {
        turbo = true;
        maxPredkosc += 50; // dostęp do protected pola
        System.out.println("Turbo włączone! Max prędkość: " + maxPredkosc + " km/h");
    }

    void przyspiesz() {
        int delta = turbo ? 30 : 15;
        zmienPredkosc(delta); // dostęp do protected metody
        System.out.println(getMarka() + " — prędkość: " + predkosc + " km/h");
    }
}

public class ProtectedPrzyklad {
    public static void main(String[] args) {
        SamochodSportowy auto = new SamochodSportowy("Ferrari", 300);
        auto.przyspiesz();
        auto.przyspiesz();
        auto.wlaczTurbo();
        auto.przyspiesz();
        auto.przyspiesz();
    }
}
```

### Przykład 9 — Defensive copy (kopia obronna)
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dziennik {
    private final String nazwa;
    private final List<String> wpisy;

    public Dziennik(String nazwa, List<String> wpisy) {
        this.nazwa = nazwa;
        // Defensive copy — kopiujemy listę, aby zewnętrzne zmiany nie wpływały na obiekt
        this.wpisy = new ArrayList<>(wpisy);
    }

    public String getNazwa() {
        return nazwa;
    }

    // Zwracamy niemodyfikowalną kopię
    public List<String> getWpisy() {
        return Collections.unmodifiableList(wpisy);
    }

    public void dodajWpis(String wpis) {
        wpisy.add(wpis);
    }

    public int getLiczbaWpisow() {
        return wpisy.size();
    }

    public static void main(String[] args) {
        List<String> poczatkoweWpisy = new ArrayList<>();
        poczatkoweWpisy.add("Wpis 1");
        poczatkoweWpisy.add("Wpis 2");

        Dziennik dziennik = new Dziennik("Mój dziennik", poczatkoweWpisy);

        // Modyfikacja oryginalnej listy NIE wpływa na dziennik (defensive copy)
        poczatkoweWpisy.add("Wpis zewnętrzny");
        System.out.println("Wpisy w dzienniku: " + dziennik.getWpisy());
        System.out.println("Oryginalna lista: " + poczatkoweWpisy);

        // Próba modyfikacji zwróconej listy — wyjątek!
        try {
            dziennik.getWpisy().add("Hacker wpis");
        } catch (UnsupportedOperationException e) {
            System.out.println("Nie można modyfikować listy zwróconej przez getter!");
        }

        // Prawidłowe dodanie wpisu
        dziennik.dodajWpis("Wpis 3");
        System.out.println("Po dodaniu: " + dziennik.getWpisy());
    }
}
```

### Przykład 10 — Enkapsulacja z wzorcem Builder
```java
public class Pizza {
    private final String rozmiar;
    private final boolean ser;
    private final boolean pepperoni;
    private final boolean pieczarki;
    private final boolean oliwki;
    private final boolean szynka;

    // Prywatny konstruktor — tworzenie tylko przez Builder
    private Pizza(Builder builder) {
        this.rozmiar = builder.rozmiar;
        this.ser = builder.ser;
        this.pepperoni = builder.pepperoni;
        this.pieczarki = builder.pieczarki;
        this.oliwki = builder.oliwki;
        this.szynka = builder.szynka;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pizza " + rozmiar + ": ");
        if (ser) sb.append("ser ");
        if (pepperoni) sb.append("pepperoni ");
        if (pieczarki) sb.append("pieczarki ");
        if (oliwki) sb.append("oliwki ");
        if (szynka) sb.append("szynka ");
        return sb.toString().trim();
    }

    // Klasa wewnętrzna Builder
    public static class Builder {
        private final String rozmiar; // wymagane
        private boolean ser = false;
        private boolean pepperoni = false;
        private boolean pieczarki = false;
        private boolean oliwki = false;
        private boolean szynka = false;

        public Builder(String rozmiar) {
            this.rozmiar = rozmiar;
        }

        public Builder ser() { this.ser = true; return this; }
        public Builder pepperoni() { this.pepperoni = true; return this; }
        public Builder pieczarki() { this.pieczarki = true; return this; }
        public Builder oliwki() { this.oliwki = true; return this; }
        public Builder szynka() { this.szynka = true; return this; }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    public static void main(String[] args) {
        Pizza margherita = new Pizza.Builder("średnia")
                .ser()
                .build();

        Pizza pepperoniPizza = new Pizza.Builder("duża")
                .ser()
                .pepperoni()
                .oliwki()
                .build();

        Pizza wegetarianska = new Pizza.Builder("mała")
                .ser()
                .pieczarki()
                .oliwki()
                .build();

        System.out.println(margherita);
        System.out.println(pepperoniPizza);
        System.out.println(wegetarianska);
    }
}
```
