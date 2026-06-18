# 6. Podstawowe klasy dostępne w pakietach języka obiektowego

## Teoria

### Klasa `Object`
Każda klasa w Javie dziedziczy (bezpośrednio lub pośrednio) po klasie `java.lang.Object`. Najważniejsze metody:
- `toString()` — zwraca tekstową reprezentację obiektu.
- `equals(Object obj)` — porównuje obiekty pod względem równości logicznej.
- `hashCode()` — zwraca kod skrótu obiektu (ważny dla kolekcji opartych na haszowaniu).
- `getClass()` — zwraca obiekt `Class` reprezentujący klasę obiektu.
- `clone()` — tworzy kopię obiektu (wymaga implementacji `Cloneable`).

### Klasa `String`
`String` jest klasą **niezmienną** (immutable). Każda operacja modyfikująca tekst tworzy nowy obiekt. Najważniejsze metody: `length()`, `charAt()`, `substring()`, `indexOf()`, `contains()`, `replace()`, `split()`, `trim()`, `toUpperCase()`, `toLowerCase()`, `equals()`, `compareTo()`.

### Klasy opakowujące (Wrapper classes)
Każdy typ prymitywny ma odpowiadającą mu klasę opakowującą:

| Prymitywny | Opakowujący  |
|------------|-------------|
| `byte`     | `Byte`      |
| `short`    | `Short`     |
| `int`      | `Integer`   |
| `long`     | `Long`      |
| `float`    | `Float`     |
| `double`   | `Double`    |
| `char`     | `Character` |
| `boolean`  | `Boolean`   |

Java automatycznie konwertuje między typami prymitywnymi a opakowującymi (**autoboxing** i **unboxing**).

### Klasa `Math`
Klasa `java.lang.Math` zawiera metody statyczne do operacji matematycznych: `abs()`, `max()`, `min()`, `pow()`, `sqrt()`, `round()`, `ceil()`, `floor()`, `random()`, stałe `PI` i `E`.

### Klasa `StringBuilder` i `StringBuffer`
- `StringBuilder` — mutowalna sekwencja znaków, szybsza (niesynchronizowana).
- `StringBuffer` — mutowalna sekwencja znaków, bezpieczna wątkowo (synchronizowana).
Metody: `append()`, `insert()`, `delete()`, `reverse()`, `toString()`.

### Kolekcje (`java.util`)
- `ArrayList` — dynamiczna tablica, szybki dostęp po indeksie.
- `LinkedList` — lista dwukierunkowa, szybkie wstawianie/usuwanie.
- `HashMap` — mapa klucz-wartość oparta na haszowaniu.
- `HashSet` — zbiór unikalnych elementów.
- `TreeMap` / `TreeSet` — posortowane kolekcje.

### Klasy do obsługi daty i czasu (`java.time`)
Od Java 8: `LocalDate`, `LocalTime`, `LocalDateTime`, `Duration`, `Period`, `DateTimeFormatter`.

### Klasa `Arrays` i `Collections`
Klasy narzędziowe z metodami statycznymi do operacji na tablicach i kolekcjach: `sort()`, `binarySearch()`, `fill()`, `asList()`, `unmodifiableList()`.

---

## Przykłady

### Przykład 1 — Klasa Object: toString(), equals(), hashCode()
```java
public class Produkt {
    private String nazwa;
    private double cena;

    public Produkt(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Produkt{nazwa='" + nazwa + "', cena=" + cena + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produkt produkt = (Produkt) obj;
        return Double.compare(produkt.cena, cena) == 0
                && nazwa.equals(produkt.nazwa);
    }

    @Override
    public int hashCode() {
        int result = nazwa.hashCode();
        result = 31 * result + Double.hashCode(cena);
        return result;
    }

    public static void main(String[] args) {
        Produkt p1 = new Produkt("Laptop", 3500.0);
        Produkt p2 = new Produkt("Laptop", 3500.0);
        Produkt p3 = new Produkt("Mysz", 49.99);

        System.out.println("p1: " + p1);
        System.out.println("p1.equals(p2): " + p1.equals(p2));  // true
        System.out.println("p1 == p2: " + (p1 == p2));          // false (różne referencje)
        System.out.println("p1.equals(p3): " + p1.equals(p3));  // false
        System.out.println("p1.hashCode(): " + p1.hashCode());
        System.out.println("p2.hashCode(): " + p2.hashCode());
        System.out.println("Klasa: " + p1.getClass().getName());
    }
}
```

### Przykład 2 — Klasa String — metody
```java
public class StringMetody {
    public static void main(String[] args) {
        String tekst = "  Programowanie Obiektowe w Javie  ";

        System.out.println("Oryginalny: '" + tekst + "'");
        System.out.println("trim(): '" + tekst.trim() + "'");
        System.out.println("length(): " + tekst.length());
        System.out.println("toUpperCase(): " + tekst.trim().toUpperCase());
        System.out.println("toLowerCase(): " + tekst.trim().toLowerCase());
        System.out.println("charAt(2): " + tekst.trim().charAt(2));
        System.out.println("indexOf('Obiektowe'): " + tekst.indexOf("Obiektowe"));
        System.out.println("contains('Java'): " + tekst.contains("Java"));
        System.out.println("startsWith('  Pro'): " + tekst.startsWith("  Pro"));
        System.out.println("replace('Javie', 'Pythonie'): " + tekst.trim().replace("Javie", "Pythonie"));
        System.out.println("substring(0, 15): " + tekst.trim().substring(0, 15));

        // split
        String csv = "Jan,Kowalski,25,Gdańsk";
        String[] czesci = csv.split(",");
        for (String c : czesci) {
            System.out.println("  -> " + c);
        }

        // Porównywanie stringów
        String a = "Java";
        String b = "java";
        System.out.println("equals: " + a.equals(b));                    // false
        System.out.println("equalsIgnoreCase: " + a.equalsIgnoreCase(b)); // true
        System.out.println("compareTo: " + a.compareTo(b));               // ujemna (J < j)
    }
}
```

### Przykład 3 — Klasy opakowujące i autoboxing
```java
public class WrapperClasses {
    public static void main(String[] args) {
        // Autoboxing: int → Integer
        Integer liczba = 42;
        Double pi = 3.14;
        Boolean prawda = true;

        // Unboxing: Integer → int
        int wartosc = liczba;
        double wartoscPi = pi;

        System.out.println("Integer: " + liczba);
        System.out.println("Double: " + pi);
        System.out.println("Boolean: " + prawda);

        // Parsowanie ze Stringa
        int x = Integer.parseInt("123");
        double y = Double.parseDouble("3.14");
        boolean z = Boolean.parseBoolean("true");
        System.out.println("Parsed int: " + x);
        System.out.println("Parsed double: " + y);
        System.out.println("Parsed boolean: " + z);

        // Konwersja na String
        String s1 = Integer.toString(42);
        String s2 = String.valueOf(3.14);
        System.out.println("toString: " + s1 + ", valueOf: " + s2);

        // Stałe
        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
        System.out.println("Double.MAX_VALUE: " + Double.MAX_VALUE);

        // Porównywanie — uwaga na cache Integer (-128 do 127)
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println("127 == 127: " + (a == b));   // true (cache)
        System.out.println("128 == 128: " + (c == d));   // false (poza cache!)
        System.out.println("128.equals(128): " + c.equals(d)); // true
    }
}
```

### Przykład 4 — Klasa Math
```java
public class MathPrzyklad {
    public static void main(String[] args) {
        System.out.println("PI = " + Math.PI);
        System.out.println("E = " + Math.E);

        System.out.println("abs(-7) = " + Math.abs(-7));
        System.out.println("max(10, 20) = " + Math.max(10, 20));
        System.out.println("min(10, 20) = " + Math.min(10, 20));
        System.out.println("pow(2, 10) = " + Math.pow(2, 10));
        System.out.println("sqrt(144) = " + Math.sqrt(144));
        System.out.println("cbrt(27) = " + Math.cbrt(27));

        System.out.println("round(3.7) = " + Math.round(3.7));
        System.out.println("ceil(3.2) = " + Math.ceil(3.2));
        System.out.println("floor(3.9) = " + Math.floor(3.9));

        System.out.println("log(Math.E) = " + Math.log(Math.E));
        System.out.println("log10(1000) = " + Math.log10(1000));

        System.out.println("sin(PI/2) = " + Math.sin(Math.PI / 2));
        System.out.println("cos(0) = " + Math.cos(0));

        // Losowa liczba z zakresu [1, 6] (symulacja kostki)
        for (int i = 0; i < 5; i++) {
            int kostka = (int) (Math.random() * 6) + 1;
            System.out.println("Rzut kostką: " + kostka);
        }
    }
}
```

### Przykład 5 — StringBuilder
```java
public class StringBuilderPrzyklad {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");

        sb.append(" World");
        sb.append("!");
        System.out.println("append: " + sb);

        sb.insert(5, ",");
        System.out.println("insert: " + sb);

        sb.replace(0, 5, "Witaj");
        System.out.println("replace: " + sb);

        sb.delete(5, 6); // usunięcie przecinka
        System.out.println("delete: " + sb);

        sb.reverse();
        System.out.println("reverse: " + sb);

        // Wydajność: StringBuilder vs String concatenation
        long start = System.nanoTime();
        StringBuilder sbPerf = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sbPerf.append("a");
        }
        long sbTime = System.nanoTime() - start;

        start = System.nanoTime();
        String sPerf = "";
        for (int i = 0; i < 10000; i++) { // mniej iteracji — String jest DUŻO wolniejszy
            sPerf += "a";
        }
        long sTime = System.nanoTime() - start;

        System.out.printf("StringBuilder (100k): %.2f ms%n", sbTime / 1_000_000.0);
        System.out.printf("String concat (10k): %.2f ms%n", sTime / 1_000_000.0);
    }
}
```

### Przykład 6 — ArrayList i LinkedList
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class ListyPrzyklad {
    public static void main(String[] args) {
        // ArrayList
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Banan");
        arrayList.add("Jabłko");
        arrayList.add("Czereśnia");
        arrayList.add("Ananas");

        System.out.println("ArrayList: " + arrayList);
        System.out.println("Element [1]: " + arrayList.get(1));
        System.out.println("Rozmiar: " + arrayList.size());
        System.out.println("Zawiera 'Jabłko': " + arrayList.contains("Jabłko"));

        Collections.sort(arrayList);
        System.out.println("Posortowana: " + arrayList);

        arrayList.remove("Banan");
        System.out.println("Po usunięciu 'Banan': " + arrayList);

        // LinkedList — jako kolejka
        LinkedList<String> kolejka = new LinkedList<>();
        kolejka.addLast("Klient 1");
        kolejka.addLast("Klient 2");
        kolejka.addLast("Klient 3");

        System.out.println("\nKolejka: " + kolejka);
        System.out.println("Pierwszy: " + kolejka.getFirst());
        System.out.println("Obsłużony: " + kolejka.removeFirst());
        System.out.println("Kolejka po obsłudze: " + kolejka);

        // Iteracja
        System.out.println("\nIteracja po ArrayList:");
        for (String owoc : arrayList) {
            System.out.println("  - " + owoc);
        }
    }
}
```

### Przykład 7 — HashMap i HashSet
```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapaIZbior {
    public static void main(String[] args) {
        // HashMap
        Map<String, Integer> oceny = new HashMap<>();
        oceny.put("Anna", 5);
        oceny.put("Jan", 4);
        oceny.put("Ewa", 5);
        oceny.put("Piotr", 3);

        System.out.println("Mapa ocen: " + oceny);
        System.out.println("Ocena Anny: " + oceny.get("Anna"));
        System.out.println("Czy zawiera 'Jan': " + oceny.containsKey("Jan"));

        // Iteracja po mapie
        System.out.println("\nWszystkie oceny:");
        for (Map.Entry<String, Integer> wpis : oceny.entrySet()) {
            System.out.println("  " + wpis.getKey() + " → " + wpis.getValue());
        }

        // getOrDefault
        System.out.println("Ocena Marka: " + oceny.getOrDefault("Marek", 0));

        // HashSet — zbiór unikalnych elementów
        Set<String> jezyki = new HashSet<>();
        jezyki.add("Java");
        jezyki.add("Python");
        jezyki.add("C++");
        jezyki.add("Java"); // duplikat — nie zostanie dodany

        System.out.println("\nZbiór języków: " + jezyki);
        System.out.println("Rozmiar: " + jezyki.size()); // 3
        System.out.println("Zawiera 'Java': " + jezyki.contains("Java"));

        jezyki.remove("C++");
        System.out.println("Po usunięciu C++: " + jezyki);
    }
}
```

### Przykład 8 — LocalDate, LocalTime, LocalDateTime
```java
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DataICzas {
    public static void main(String[] args) {
        // LocalDate
        LocalDate dzisiaj = LocalDate.now();
        LocalDate urodziny = LocalDate.of(2000, 5, 15);

        System.out.println("Dzisiaj: " + dzisiaj);
        System.out.println("Urodziny: " + urodziny);
        System.out.println("Rok: " + dzisiaj.getYear());
        System.out.println("Miesiąc: " + dzisiaj.getMonth());
        System.out.println("Dzień: " + dzisiaj.getDayOfMonth());
        System.out.println("Dzień tygodnia: " + dzisiaj.getDayOfWeek());

        // Period — różnica między datami
        Period wiek = Period.between(urodziny, dzisiaj);
        System.out.println("Wiek: " + wiek.getYears() + " lat");

        // LocalTime
        LocalTime teraz = LocalTime.now();
        LocalTime alarm = LocalTime.of(7, 30);
        System.out.println("\nTeraz: " + teraz);
        System.out.println("Alarm: " + alarm);

        // LocalDateTime
        LocalDateTime dataICzas = LocalDateTime.now();
        System.out.println("\nData i czas: " + dataICzas);

        // Formatowanie
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        System.out.println("Sformatowane: " + dataICzas.format(formatter));

        // Operacje na datach
        LocalDate za7Dni = dzisiaj.plusDays(7);
        LocalDate miesiacTemu = dzisiaj.minusMonths(1);
        System.out.println("\nZa 7 dni: " + za7Dni);
        System.out.println("Miesiąc temu: " + miesiacTemu);

        long dniDoUrodzin = ChronoUnit.DAYS.between(dzisiaj,
                urodziny.withYear(dzisiaj.getYear() + 1));
        System.out.println("Dni do urodzin: " + dniDoUrodzin);
    }
}
```

### Przykład 9 — Arrays — metody narzędziowe
```java
import java.util.Arrays;

public class ArraysPrzyklad {
    public static void main(String[] args) {
        int[] liczby = {5, 2, 8, 1, 9, 3, 7};

        System.out.println("Oryginalna: " + Arrays.toString(liczby));

        // Sortowanie
        int[] kopia = Arrays.copyOf(liczby, liczby.length);
        Arrays.sort(kopia);
        System.out.println("Posortowana: " + Arrays.toString(kopia));

        // Wyszukiwanie binarne (tablica musi być posortowana)
        int indeks = Arrays.binarySearch(kopia, 7);
        System.out.println("Indeks 7 w posortowanej: " + indeks);

        // Wypełnianie
        int[] zera = new int[5];
        Arrays.fill(zera, 42);
        System.out.println("Wypełniona 42: " + Arrays.toString(zera));

        // Porównywanie tablic
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[] c = {1, 2, 4};
        System.out.println("a.equals(b): " + Arrays.equals(a, b)); // true
        System.out.println("a.equals(c): " + Arrays.equals(a, c)); // false

        // Kopiowanie fragmentu
        int[] fragment = Arrays.copyOfRange(liczby, 2, 5);
        System.out.println("Fragment [2,5): " + Arrays.toString(fragment));

        // Konwersja na listę
        String[] owoce = {"Jabłko", "Banan", "Czereśnia"};
        var lista = Arrays.asList(owoce);
        System.out.println("Lista: " + lista);
    }
}
```

### Przykład 10 — Random i Scanner
```java
import java.util.Random;
import java.util.Scanner;

public class RandomIScannerPrzyklad {
    public static void main(String[] args) {
        Random random = new Random();

        // Losowe liczby
        System.out.println("Losowy int: " + random.nextInt());
        System.out.println("Losowy int [0,100): " + random.nextInt(100));
        System.out.println("Losowy double [0,1): " + random.nextDouble());
        System.out.println("Losowy boolean: " + random.nextBoolean());

        // Symulacja rzutu 10 kostkami
        System.out.print("Rzuty kostką: ");
        for (int i = 0; i < 10; i++) {
            System.out.print((random.nextInt(6) + 1) + " ");
        }
        System.out.println();

        // Random z ziarnem (seed) — powtarzalne wyniki
        Random r1 = new Random(42);
        Random r2 = new Random(42);
        System.out.println("r1: " + r1.nextInt(100) + ", r2: " + r2.nextInt(100)); // te same!

        // Scanner — parsowanie tekstu
        String dane = "Jan 25 4.5";
        Scanner sc = new Scanner(dane);
        String imie = sc.next();
        int wiek = sc.nextInt();
        double srednia = sc.nextDouble();
        System.out.printf("%nParsowanie: imię=%s, wiek=%d, średnia=%.1f%n", imie, wiek, srednia);
        sc.close();
    }
}
```
