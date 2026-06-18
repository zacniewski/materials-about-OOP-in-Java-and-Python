# 10. Powoływanie obiektu. Zmienne referencyjne.

## Teoria

### Zmienne referencyjne
W Javie zmienne typów obiektowych nie przechowują samego obiektu, lecz **referencję** (odniesienie) do obiektu znajdującego się na stercie (heap). Referencja to w uproszczeniu „adres" obiektu w pamięci.

```java
String tekst = new String("Hello");
// tekst — zmienna referencyjna przechowująca referencję do obiektu String
```

### Typy prymitywne vs referencyjne
| Cecha              | Typ prymitywny          | Typ referencyjny            |
|--------------------|-------------------------|-----------------------------|
| Przechowuje        | Wartość bezpośrednio    | Referencję do obiektu       |
| Pamięć             | Stos (stack)            | Referencja na stosie, obiekt na stercie |
| Wartość domyślna   | `0`, `false`, `'\0'`    | `null`                      |
| Porównanie `==`    | Porównuje wartości      | Porównuje referencje (adresy)|
| Przykłady          | `int`, `double`, `boolean` | `String`, `Integer`, własne klasy |

### Wartość `null`
`null` oznacza, że zmienna referencyjna **nie wskazuje na żaden obiekt**. Próba wywołania metody na `null` powoduje `NullPointerException`.

### Przypisanie referencji
Przypisanie zmiennej referencyjnej do innej zmiennej **kopiuje referencję**, nie obiekt. Obie zmienne wskazują na ten sam obiekt:
```java
Punkt a = new Punkt(1, 2);
Punkt b = a;  // b wskazuje na TEN SAM obiekt co a
```

### Przekazywanie do metod
Java stosuje **przekazywanie przez wartość** (pass by value):
- Dla typów prymitywnych — kopiowana jest wartość.
- Dla typów referencyjnych — kopiowana jest referencja (nie obiekt). Metoda może modyfikować obiekt przez referencję, ale nie może zmienić, na co wskazuje oryginalna zmienna.

### Porównywanie obiektów
- `==` — porównuje **referencje** (czy wskazują na ten sam obiekt).
- `equals()` — porównuje **zawartość** obiektów (jeśli metoda jest prawidłowo nadpisana).

### Garbage Collector (GC)
Gdy żadna zmienna referencyjna nie wskazuje na obiekt, staje się on kandydatem do usunięcia przez Garbage Collector. GC automatycznie zwalnia pamięć — programista nie musi ręcznie dealokować obiektów.

### Operator `instanceof`
Sprawdza, czy obiekt jest instancją danej klasy lub implementuje dany interfejs:
```java
if (obiekt instanceof MojaKlasa) { ... }
```

---

## Przykłady

### Przykład 1 — Referencje i przypisanie
```java
public class ReferencjePrzyklad {
    public static void main(String[] args) {
        // Tworzenie obiektu — referencja wskazuje na obiekt na stercie
        int[] tablica1 = {1, 2, 3, 4, 5};

        // Kopiowanie referencji — OBA wskazują na TĘ SAMĄ tablicę
        int[] tablica2 = tablica1;

        System.out.println("tablica1[0] = " + tablica1[0]); // 1
        System.out.println("tablica2[0] = " + tablica2[0]); // 1

        // Zmiana przez tablica2 wpływa na tablica1!
        tablica2[0] = 99;
        System.out.println("\nPo zmianie tablica2[0] = 99:");
        System.out.println("tablica1[0] = " + tablica1[0]); // 99!
        System.out.println("tablica2[0] = " + tablica2[0]); // 99

        // Czy to ten sam obiekt?
        System.out.println("tablica1 == tablica2: " + (tablica1 == tablica2)); // true

        // Nowa tablica — inna referencja
        int[] tablica3 = {1, 2, 3, 4, 5};
        System.out.println("tablica1 == tablica3: " + (tablica1 == tablica3)); // false
    }
}
```

### Przykład 2 — Referencje do obiektów własnych klas
```java
class Osoba {
    String imie;
    int wiek;

    Osoba(String imie, int wiek) {
        this.imie = imie;
        this.wiek = wiek;
    }

    @Override
    public String toString() {
        return imie + " (" + wiek + ")";
    }
}

public class ReferencjeObiektow {
    public static void main(String[] args) {
        Osoba a = new Osoba("Anna", 25);
        Osoba b = a; // b wskazuje na TEN SAM obiekt

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("a == b: " + (a == b)); // true — ta sama referencja

        // Zmiana przez b wpływa na a
        b.wiek = 30;
        System.out.println("\nPo b.wiek = 30:");
        System.out.println("a: " + a); // Anna (30)!
        System.out.println("b: " + b); // Anna (30)

        // Przypisanie nowego obiektu do b
        b = new Osoba("Jan", 40);
        System.out.println("\nPo b = new Osoba(\"Jan\", 40):");
        System.out.println("a: " + a); // Anna (30) — bez zmian
        System.out.println("b: " + b); // Jan (40)
        System.out.println("a == b: " + (a == b)); // false — różne obiekty
    }
}
```

### Przykład 3 — Wartość null i NullPointerException
```java
public class NullPrzyklad {
    public static void main(String[] args) {
        String tekst = null; // zmienna nie wskazuje na żaden obiekt

        System.out.println("tekst == null: " + (tekst == null)); // true

        // Bezpieczne sprawdzenie przed użyciem
        if (tekst != null) {
            System.out.println("Długość: " + tekst.length());
        } else {
            System.out.println("tekst jest null — nie można wywołać metody!");
        }

        // NullPointerException
        try {
            int dlugosc = tekst.length(); // NPE!
        } catch (NullPointerException e) {
            System.out.println("Złapano NullPointerException: " + e.getMessage());
        }

        // Przypisanie wartości
        tekst = "Hello";
        System.out.println("Po przypisaniu: " + tekst.length()); // 5

        // Utrata referencji — obiekt staje się kandydatem do GC
        tekst = null;
        // Obiekt "Hello" nie jest już osiągalny
    }
}
```

### Przykład 4 — Przekazywanie przez wartość (pass by value)
```java
class Pudełko {
    int zawartosc;

    Pudełko(int zawartosc) {
        this.zawartosc = zawartosc;
    }

    @Override
    public String toString() {
        return "Pudełko(" + zawartosc + ")";
    }
}

public class PassByValue {

    // Typ prymitywny — kopia wartości
    static void zmienLiczbe(int x) {
        x = 999;
        System.out.println("  Wewnątrz metody: x = " + x);
    }

    // Typ referencyjny — kopia referencji (ale obiekt jest współdzielony)
    static void zmienZawartosc(Pudełko p) {
        p.zawartosc = 999;
        System.out.println("  Wewnątrz metody: " + p);
    }

    // Próba zmiany referencji — nie wpływa na oryginalną zmienną
    static void zmienReferencje(Pudełko p) {
        p = new Pudełko(777);
        System.out.println("  Wewnątrz metody (nowa ref): " + p);
    }

    public static void main(String[] args) {
        // Test 1: typ prymitywny
        int liczba = 42;
        System.out.println("Przed: liczba = " + liczba);
        zmienLiczbe(liczba);
        System.out.println("Po: liczba = " + liczba); // 42 — bez zmian!

        System.out.println();

        // Test 2: zmiana zawartości obiektu
        Pudełko box = new Pudełko(10);
        System.out.println("Przed: " + box);
        zmienZawartosc(box);
        System.out.println("Po: " + box); // Pudełko(999) — zmienione!

        System.out.println();

        // Test 3: zmiana referencji w metodzie
        Pudełko box2 = new Pudełko(10);
        System.out.println("Przed: " + box2);
        zmienReferencje(box2);
        System.out.println("Po: " + box2); // Pudełko(10) — bez zmian!
    }
}
```

### Przykład 5 — Porównywanie: == vs equals()
```java
public class PorownywanieObiektow {
    public static void main(String[] args) {
        // String — specjalny przypadek (String pool)
        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");

        System.out.println("=== String ===");
        System.out.println("s1 == s2: " + (s1 == s2));           // true (String pool)
        System.out.println("s1 == s3: " + (s1 == s3));           // false (new tworzy nowy obiekt)
        System.out.println("s1.equals(s3): " + s1.equals(s3));   // true (porównanie zawartości)

        // Integer — cache dla wartości -128 do 127
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        System.out.println("\n=== Integer ===");
        System.out.println("i1 == i2 (100): " + (i1 == i2));     // true (cache)
        System.out.println("i3 == i4 (200): " + (i3 == i4));     // false (poza cache)
        System.out.println("i3.equals(i4): " + i3.equals(i4));   // true

        // Własna klasa
        class Punkt {
            int x, y;
            Punkt(int x, int y) { this.x = x; this.y = y; }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (!(obj instanceof Punkt)) return false;
                Punkt p = (Punkt) obj;
                return x == p.x && y == p.y;
            }
        }

        Punkt p1 = new Punkt(3, 4);
        Punkt p2 = new Punkt(3, 4);

        System.out.println("\n=== Punkt ===");
        System.out.println("p1 == p2: " + (p1 == p2));           // false
        System.out.println("p1.equals(p2): " + p1.equals(p2));   // true
    }
}
```

### Przykład 6 — Tablica obiektów i referencje
```java
class Konto {
    private String nazwa;
    private double saldo;

    Konto(String nazwa, double saldo) {
        this.nazwa = nazwa;
        this.saldo = saldo;
    }

    void wplac(double kwota) { saldo += kwota; }

    @Override
    public String toString() {
        return nazwa + ": " + saldo + " zł";
    }
}

public class TablicaReferencji {
    public static void main(String[] args) {
        // Tablica referencji — początkowo wszystkie null
        Konto[] konta = new Konto[3];
        System.out.println("konta[0] == null: " + (konta[0] == null)); // true

        // Inicjalizacja
        konta[0] = new Konto("Anna", 1000);
        konta[1] = new Konto("Jan", 2000);
        konta[2] = konta[0]; // konta[2] wskazuje na TEN SAM obiekt co konta[0]

        System.out.println("\nPrzed wpłatą:");
        for (Konto k : konta) {
            System.out.println("  " + k);
        }

        // Wpłata przez konta[2] wpływa na konta[0]
        konta[2].wplac(500);

        System.out.println("\nPo wpłacie 500 przez konta[2]:");
        for (Konto k : konta) {
            System.out.println("  " + k);
        }

        System.out.println("\nkonta[0] == konta[2]: " + (konta[0] == konta[2])); // true
        System.out.println("konta[0] == konta[1]: " + (konta[0] == konta[1])); // false
    }
}
```

### Przykład 7 — Operator instanceof
```java
class Zwierze {
    String nazwa;
    Zwierze(String nazwa) { this.nazwa = nazwa; }
    void jedz() { System.out.println(nazwa + " je."); }
}

class Pies extends Zwierze {
    Pies(String nazwa) { super(nazwa); }
    void szczekaj() { System.out.println(nazwa + ": Hau hau!"); }
}

class Kot extends Zwierze {
    Kot(String nazwa) { super(nazwa); }
    void mruczaj() { System.out.println(nazwa + ": Mrrr..."); }
}

public class InstanceofPrzyklad {
    static void identyfikuj(Zwierze z) {
        System.out.print(z.nazwa + " jest ");
        if (z instanceof Pies) {
            System.out.println("psem.");
            ((Pies) z).szczekaj(); // rzutowanie w dół (downcasting)
        } else if (z instanceof Kot) {
            System.out.println("kotem.");
            ((Kot) z).mruczaj();
        } else {
            System.out.println("zwierzęciem.");
        }
    }

    public static void main(String[] args) {
        Zwierze[] zwierzeta = {
            new Pies("Burek"),
            new Kot("Mruczek"),
            new Zwierze("Rybka"),
            new Pies("Rex")
        };

        for (Zwierze z : zwierzeta) {
            identyfikuj(z);
            System.out.println();
        }

        // Pattern matching for instanceof (Java 16+)
        Zwierze z = new Pies("Azor");
        if (z instanceof Pies pies) { // automatyczne rzutowanie
            pies.szczekaj();
        }
    }
}
```

### Przykład 8 — Garbage Collection i utrata referencji
```java
public class GarbageCollectionDemo {
    private String nazwa;

    GarbageCollectionDemo(String nazwa) {
        this.nazwa = nazwa;
        System.out.println("Utworzono: " + nazwa);
    }

    @Override
    public String toString() {
        return "Obiekt(" + nazwa + ")";
    }

    public static void main(String[] args) {
        // Obiekt 1 — referencja w zmiennej
        GarbageCollectionDemo obj1 = new GarbageCollectionDemo("Alfa");

        // Obiekt 2 — referencja w zmiennej
        GarbageCollectionDemo obj2 = new GarbageCollectionDemo("Beta");

        // Obiekt 3 — brak referencji → kandydat do GC
        new GarbageCollectionDemo("Gamma"); // natychmiast nieosiągalny

        System.out.println("\nobj1 = " + obj1);
        System.out.println("obj2 = " + obj2);

        // Utrata referencji do "Alfa"
        obj1 = obj2; // "Alfa" staje się nieosiągalny → kandydat do GC
        System.out.println("\nPo obj1 = obj2:");
        System.out.println("obj1 = " + obj1); // Beta
        System.out.println("obj2 = " + obj2); // Beta

        // Utrata obu referencji do "Beta"
        obj1 = null;
        obj2 = null;
        // "Beta" staje się nieosiągalny → kandydat do GC

        // Sugestia dla GC (nie gwarantuje natychmiastowego działania)
        System.gc();
        System.out.println("\nPo utracie wszystkich referencji i System.gc()");
    }
}
```

### Przykład 9 — Referencja typu bazowego do obiektu podklasy
```java
class Figura {
    String nazwa;

    Figura(String nazwa) {
        this.nazwa = nazwa;
    }

    double pole() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s, pole=%.2f", nazwa, pole());
    }
}

class Kolo extends Figura {
    double promien;

    Kolo(double promien) {
        super("Koło");
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
        super("Kwadrat");
        this.bok = bok;
    }

    @Override
    double pole() {
        return bok * bok;
    }
}

public class ReferencjaTypuBazowego {
    public static void main(String[] args) {
        // Referencja typu bazowego wskazuje na obiekt podklasy
        Figura f1 = new Kolo(5);      // Figura ← Kolo
        Figura f2 = new Kwadrat(4);   // Figura ← Kwadrat

        System.out.println(f1); // Koło, pole=78.54
        System.out.println(f2); // Kwadrat, pole=16.00

        // Polimorfizm — metoda pole() wywoływana z podklasy
        Figura[] figury = {new Kolo(3), new Kwadrat(5), new Kolo(7)};
        double sumaPol = 0;
        for (Figura f : figury) {
            sumaPol += f.pole();
            System.out.println("  " + f);
        }
        System.out.printf("Suma pól: %.2f%n", sumaPol);

        // Rzutowanie w dół (downcasting)
        if (f1 instanceof Kolo) {
            Kolo kolo = (Kolo) f1;
            System.out.println("Promień koła: " + kolo.promien);
        }
    }
}
```

### Przykład 10 — Współdzielenie i niezależność obiektów
```java
import java.util.ArrayList;
import java.util.List;

class ListaZakupow {
    private String nazwa;
    private List<String> produkty;

    ListaZakupow(String nazwa) {
        this.nazwa = nazwa;
        this.produkty = new ArrayList<>();
    }

    // Konstruktor kopiujący — głęboka kopia
    ListaZakupow(ListaZakupow inna) {
        this.nazwa = inna.nazwa + " (kopia)";
        this.produkty = new ArrayList<>(inna.produkty); // nowa lista z tymi samymi elementami
    }

    void dodaj(String produkt) {
        produkty.add(produkt);
    }

    @Override
    public String toString() {
        return nazwa + ": " + produkty;
    }
}

public class WspoldzieleniePrzyklad {
    public static void main(String[] args) {
        ListaZakupow lista1 = new ListaZakupow("Lista tygodniowa");
        lista1.dodaj("Chleb");
        lista1.dodaj("Mleko");

        // Współdzielenie referencji — TEN SAM obiekt
        ListaZakupow lista2 = lista1;
        lista2.dodaj("Masło");

        System.out.println("Współdzielenie (lista1 == lista2):");
        System.out.println("  " + lista1); // zawiera Masło!
        System.out.println("  " + lista2);
        System.out.println("  Czy ten sam obiekt? " + (lista1 == lista2)); // true

        // Niezależna kopia
        ListaZakupow lista3 = new ListaZakupow(lista1); // konstruktor kopiujący
        lista3.dodaj("Jajka");

        System.out.println("\nNiezależna kopia:");
        System.out.println("  " + lista1); // BEZ Jajek
        System.out.println("  " + lista3); // Z Jajkami
        System.out.println("  Czy ten sam obiekt? " + (lista1 == lista3)); // false
    }
}
```
