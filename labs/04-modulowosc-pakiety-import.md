# 4. Modułowość. Pakiety. Mechanizm importowania klas.

## Teoria

### Czym jest modułowość?
Modułowość to zasada projektowania oprogramowania polegająca na dzieleniu programu na niezależne, wymienne moduły. Każdy moduł odpowiada za określoną funkcjonalność i komunikuje się z innymi modułami przez dobrze zdefiniowane interfejsy. W Javie modułowość realizowana jest przede wszystkim przez **pakiety** (packages).

### Pakiety w Javie
Pakiet (package) to mechanizm grupowania powiązanych klas i interfejsów. Pakiety pełnią następujące funkcje:
- **Organizacja kodu** — logiczne grupowanie klas o podobnej funkcjonalności.
- **Unikanie konfliktów nazw** — dwie klasy o tej samej nazwie mogą istnieć w różnych pakietach.
- **Kontrola dostępu** — modyfikatory dostępu współpracują z pakietami (np. dostęp pakietowy — domyślny).
- **Enkapsulacja na poziomie pakietu** — ukrywanie szczegółów implementacji.

### Deklaracja pakietu
Deklaracja pakietu musi być **pierwszą instrukcją** w pliku źródłowym (przed importami i deklaracjami klas):
```java
package com.firma.projekt.modul;
```

### Konwencja nazewnicza pakietów
- Nazwy pakietów pisane są **małymi literami**.
- Stosuje się odwróconą notację domenową: `com.firma.projekt`.
- Struktura pakietów odpowiada strukturze katalogów na dysku.

### Mechanizm importowania klas
Import pozwala używać klas z innych pakietów bez podawania pełnej nazwy kwalifikowanej:

1. **Import konkretnej klasy:**
   ```java
   import java.util.ArrayList;
   ```

2. **Import wszystkich klas z pakietu (wildcard):**
   ```java
   import java.util.*;
   ```

3. **Import statyczny — import metod i pól statycznych:**
   ```java
   import static java.lang.Math.PI;
   import static java.lang.Math.*;
   ```

4. **Pełna nazwa kwalifikowana (bez importu):**
   ```java
   java.util.ArrayList<String> lista = new java.util.ArrayList<>();
   ```

### Pakiet `java.lang`
Pakiet `java.lang` jest importowany automatycznie — nie trzeba go jawnie importować. Zawiera klasy fundamentalne: `String`, `System`, `Math`, `Object`, `Integer`, `Double`, `Boolean`, itp.

### Najważniejsze pakiety standardowe
| Pakiet             | Zawartość                                      |
|--------------------|------------------------------------------------|
| `java.lang`        | Klasy podstawowe (String, Math, System, Object)|
| `java.util`        | Kolekcje, Scanner, Random, Date                |
| `java.io`          | Operacje wejścia/wyjścia na plikach            |
| `java.nio`         | Nowe API wejścia/wyjścia                       |
| `java.math`        | BigDecimal, BigInteger                         |
| `java.time`        | API daty i czasu (od Java 8)                   |
| `java.net`         | Programowanie sieciowe                         |
| `java.sql`         | Dostęp do baz danych (JDBC)                    |

### System modułów (Java 9+)
Od Javy 9 wprowadzono **Java Platform Module System (JPMS)**, który pozwala na definiowanie modułów za pomocą pliku `module-info.java`. Moduł deklaruje, które pakiety eksportuje i od jakich modułów zależy.

### Konflikty nazw
Gdy dwie klasy o tej samej nazwie pochodzą z różnych pakietów, należy użyć pełnej nazwy kwalifikowanej dla co najmniej jednej z nich:
```java
java.util.Date dataUtil = new java.util.Date();
java.sql.Date dataSql = new java.sql.Date(System.currentTimeMillis());
```

---

## Przykłady

### Przykład 1 — Deklaracja pakietu i podstawowy import
```java
package com.szkola.matematyka;

import java.util.Scanner;

public class KalkulatorMatematyczny {
    public double dodaj(double a, double b) {
        return a + b;
    }

    public double odejmij(double a, double b) {
        return a - b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KalkulatorMatematyczny kalk = new KalkulatorMatematyczny();

        System.out.println("2 + 3 = " + kalk.dodaj(2, 3));
        System.out.println("10 - 4 = " + kalk.odejmij(10, 4));

        scanner.close();
    }
}
```

### Przykład 2 — Import wielu klas z tego samego pakietu
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportWieluKlas {
    public static void main(String[] args) {
        // ArrayList z java.util
        List<String> lista = new ArrayList<>();
        lista.add("Java");
        lista.add("Python");
        lista.add("C++");

        // HashMap z java.util
        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("Java", 1995);
        mapa.put("Python", 1991);
        mapa.put("C++", 1985);

        System.out.println("Lista języków: " + lista);
        System.out.println("Mapa języków: " + mapa);
    }
}
```

### Przykład 3 — Import z użyciem wildcard (*)
```java
import java.util.*; // importuje wszystkie klasy z java.util

public class ImportWildcard {
    public static void main(String[] args) {
        List<Integer> liczby = new ArrayList<>();
        Random random = new Random(42);

        for (int i = 0; i < 10; i++) {
            liczby.add(random.nextInt(100));
        }

        Collections.sort(liczby);
        System.out.println("Posortowane liczby: " + liczby);

        // Scanner też jest w java.util
        Scanner scanner = new Scanner(System.in);
        // scanner.close();
    }
}
```

### Przykład 4 — Import statyczny
```java
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.abs;

public class ImportStatyczny {
    public static void main(String[] args) {
        double promien = 5.0;

        // Bez importu statycznego: Math.PI, Math.pow(...)
        // Z importem statycznym: PI, pow(...)
        double pole = PI * pow(promien, 2);
        double obwod = 2 * PI * promien;

        System.out.printf("Promień: %.1f%n", promien);
        System.out.printf("Pole koła: %.4f%n", pole);
        System.out.printf("Obwód koła: %.4f%n", obwod);

        // Odległość między punktami
        double x1 = 3, y1 = 4, x2 = 7, y2 = 1;
        double odleglosc = sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
        System.out.printf("Odległość: %.4f%n", odleglosc);

        System.out.println("Wartość bezwzględna z -7: " + abs(-7));
    }
}
```

### Przykład 5 — Rozwiązywanie konfliktu nazw
```java
// Obie klasy mają nazwę "Date", ale są w różnych pakietach
// import java.util.Date;
// import java.sql.Date;  // Konflikt!

public class KonfliktNazw {
    public static void main(String[] args) {
        // Rozwiązanie: użycie pełnych nazw kwalifikowanych
        java.util.Date dataUtil = new java.util.Date();
        java.sql.Date dataSql = new java.sql.Date(System.currentTimeMillis());

        System.out.println("java.util.Date: " + dataUtil);
        System.out.println("java.sql.Date: " + dataSql);

        // Alternatywnie: zaimportować jedną, drugą pisać pełną nazwą
        // import java.util.Date;
        // Date dataUtil2 = new Date();
        // java.sql.Date dataSql2 = new java.sql.Date(...);
    }
}
```

### Przykład 6 — Struktura pakietów (symulacja projektu)
```java
// Plik: com/sklep/model/Produkt.java
package com.sklep.model;

public class Produkt {
    private String nazwa;
    private double cena;

    public Produkt(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public String getNazwa() { return nazwa; }
    public double getCena() { return cena; }

    @Override
    public String toString() {
        return nazwa + " — " + cena + " zł";
    }
}
```

```java
// Plik: com/sklep/serwis/SerwisProduktow.java
package com.sklep.serwis;

import com.sklep.model.Produkt;
import java.util.ArrayList;
import java.util.List;

public class SerwisProduktow {
    private List<Produkt> produkty = new ArrayList<>();

    public void dodaj(Produkt p) {
        produkty.add(p);
    }

    public List<Produkt> znajdzTansze(double maxCena) {
        List<Produkt> wynik = new ArrayList<>();
        for (Produkt p : produkty) {
            if (p.getCena() <= maxCena) {
                wynik.add(p);
            }
        }
        return wynik;
    }

    public void wyswietlWszystkie() {
        for (Produkt p : produkty) {
            System.out.println("  " + p);
        }
    }
}
```

```java
// Plik: com/sklep/Main.java
package com.sklep;

import com.sklep.model.Produkt;
import com.sklep.serwis.SerwisProduktow;

public class Main {
    public static void main(String[] args) {
        SerwisProduktow serwis = new SerwisProduktow();
        serwis.dodaj(new Produkt("Laptop", 3500.0));
        serwis.dodaj(new Produkt("Mysz", 49.99));
        serwis.dodaj(new Produkt("Klawiatura", 129.99));
        serwis.dodaj(new Produkt("Monitor", 1200.0));

        System.out.println("Wszystkie produkty:");
        serwis.wyswietlWszystkie();

        System.out.println("\nProdukty do 200 zł:");
        for (Produkt p : serwis.znajdzTansze(200)) {
            System.out.println("  " + p);
        }
    }
}
```

### Przykład 7 — Klasa narzędziowa (utility) w osobnym pakiecie
```java
// Plik: utils/MathUtils.java
package utils;

public class MathUtils {
    // Prywatny konstruktor — nie można tworzyć instancji
    private MathUtils() {}

    public static int nwd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int nww(int a, int b) {
        return Math.abs(a * b) / nwd(a, b);
    }

    public static boolean czyPierwsza(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static long silnia(int n) {
        if (n < 0) throw new IllegalArgumentException("n musi być >= 0");
        long wynik = 1;
        for (int i = 2; i <= n; i++) {
            wynik *= i;
        }
        return wynik;
    }
}
```

```java
// Plik: Main.java
import utils.MathUtils;

public class Main {
    public static void main(String[] args) {
        System.out.println("NWD(12, 8) = " + MathUtils.nwd(12, 8));
        System.out.println("NWW(12, 8) = " + MathUtils.nww(12, 8));
        System.out.println("Czy 17 jest pierwsza? " + MathUtils.czyPierwsza(17));
        System.out.println("5! = " + MathUtils.silnia(5));
    }
}
```

### Przykład 8 — Import statyczny z własnej klasy
```java
package constants;

public class AppConstants {
    public static final String APP_NAME = "MójProgram";
    public static final String VERSION = "1.0.0";
    public static final int MAX_USERS = 100;
    public static final double TAX_RATE = 0.23;

    public static double obliczPodatek(double kwotaNetto) {
        return kwotaNetto * TAX_RATE;
    }

    public static double obliczBrutto(double kwotaNetto) {
        return kwotaNetto + obliczPodatek(kwotaNetto);
    }
}
```

```java
import static constants.AppConstants.*;

public class UzycieStatycznychStalych {
    public static void main(String[] args) {
        System.out.println("Aplikacja: " + APP_NAME + " v" + VERSION);
        System.out.println("Max użytkowników: " + MAX_USERS);

        double netto = 1000.0;
        System.out.printf("Netto: %.2f zł%n", netto);
        System.out.printf("Podatek (%.0f%%): %.2f zł%n", TAX_RATE * 100, obliczPodatek(netto));
        System.out.printf("Brutto: %.2f zł%n", obliczBrutto(netto));
    }
}
```

### Przykład 9 — Podpakiety
```java
// Pakiet główny
package com.firma;

public class Firma {
    private String nazwa;

    public Firma(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }
}
```

```java
// Podpakiet — pracownicy
package com.firma.hr;

public class Pracownik {
    private String imie;
    private String stanowisko;

    public Pracownik(String imie, String stanowisko) {
        this.imie = imie;
        this.stanowisko = stanowisko;
    }

    @Override
    public String toString() {
        return imie + " (" + stanowisko + ")";
    }
}
```

```java
// Podpakiet — finanse
package com.firma.finanse;

public class Faktura {
    private int numer;
    private double kwota;

    public Faktura(int numer, double kwota) {
        this.numer = numer;
        this.kwota = kwota;
    }

    @Override
    public String toString() {
        return "Faktura #" + numer + ": " + kwota + " zł";
    }
}
```

```java
// Klasa główna importująca z podpakietów
package com.firma;

import com.firma.hr.Pracownik;
import com.firma.finanse.Faktura;

public class Main {
    public static void main(String[] args) {
        Firma firma = new Firma("TechCorp");
        Pracownik p = new Pracownik("Anna Nowak", "Programista");
        Faktura f = new Faktura(1001, 5000.0);

        System.out.println("Firma: " + firma.getNazwa());
        System.out.println("Pracownik: " + p);
        System.out.println(f);
    }
}
```

### Przykład 10 — Moduły Java 9+ (module-info.java)
```java
// Plik: module-info.java (moduł com.sklep.model)
module com.sklep.model {
    exports com.sklep.model;  // eksportuje pakiet — inne moduły mogą go używać
}
```

```java
// Plik: module-info.java (moduł com.sklep.app)
module com.sklep.app {
    requires com.sklep.model;  // zależy od modułu com.sklep.model
}
```

```java
// Plik: com/sklep/model/Towar.java
package com.sklep.model;

public class Towar {
    private String nazwa;
    private double cena;

    public Towar(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public String getNazwa() { return nazwa; }
    public double getCena() { return cena; }

    @Override
    public String toString() {
        return nazwa + " (" + cena + " zł)";
    }
}
```

```java
// Plik: com/sklep/app/Aplikacja.java
package com.sklep.app;

import com.sklep.model.Towar;

public class Aplikacja {
    public static void main(String[] args) {
        Towar t = new Towar("Kawa", 25.99);
        System.out.println("Towar: " + t);
    }
}
```
