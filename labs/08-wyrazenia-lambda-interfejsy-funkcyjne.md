# 8. Wyrażenia lambda. Interfejsy funkcyjne.

## Teoria

### Czym jest wyrażenie lambda?
Wyrażenie lambda to zwięzły sposób zapisu **anonimowej funkcji** — funkcji bez nazwy, którą można przekazać jako argument lub przypisać do zmiennej. Lambdy zostały wprowadzone w **Java 8** i są ściśle powiązane z interfejsami funkcyjnymi.

### Składnia wyrażenia lambda
```java
(parametry) -> wyrażenie
(parametry) -> { blok instrukcji }
```

Warianty:
- Bez parametrów: `() -> System.out.println("Hello")`
- Jeden parametr (nawiasy opcjonalne): `x -> x * 2`
- Wiele parametrów: `(a, b) -> a + b`
- Z blokiem instrukcji: `(a, b) -> { int suma = a + b; return suma; }`

### Interfejs funkcyjny
Interfejs funkcyjny to interfejs posiadający **dokładnie jedną metodę abstrakcyjną** (SAM — Single Abstract Method). Może mieć dowolną liczbę metod `default` i `static`. Adnotacja `@FunctionalInterface` jest opcjonalna, ale zalecana — kompilator sprawdzi, czy interfejs spełnia warunek SAM.

```java
@FunctionalInterface
interface MojInterfejs {
    int oblicz(int a, int b);  // jedyna metoda abstrakcyjna
}
```

### Wbudowane interfejsy funkcyjne (`java.util.function`)

| Interfejs            | Metoda             | Opis                                    |
|----------------------|--------------------|-----------------------------------------|
| `Predicate<T>`       | `test(T) → boolean`| Test warunku                           |
| `Function<T,R>`      | `apply(T) → R`    | Transformacja T → R                     |
| `Consumer<T>`        | `accept(T) → void`| Konsumpcja wartości (bez zwracania)     |
| `Supplier<T>`        | `get() → T`       | Dostarczanie wartości (bez argumentów)  |
| `UnaryOperator<T>`   | `apply(T) → T`    | Operacja jednoargumentowa T → T         |
| `BinaryOperator<T>`  | `apply(T,T) → T`  | Operacja dwuargumentowa (T,T) → T       |
| `BiFunction<T,U,R>`  | `apply(T,U) → R`  | Funkcja dwuargumentowa (T,U) → R        |
| `BiPredicate<T,U>`   | `test(T,U) → boolean`| Test warunku z dwoma argumentami     |

### Referencje do metod (Method References)
Skrócona forma lambdy, gdy lambda jedynie wywołuje istniejącą metodę:

| Typ                          | Składnia                    | Odpowiednik lambda          |
|------------------------------|-----------------------------|-----------------------------|
| Metoda statyczna             | `Klasa::metoda`             | `x -> Klasa.metoda(x)`     |
| Metoda instancji (obiekt)    | `obiekt::metoda`            | `x -> obiekt.metoda(x)`    |
| Metoda instancji (typ)       | `Klasa::metoda`             | `(obj, x) -> obj.metoda(x)`|
| Konstruktor                  | `Klasa::new`                | `x -> new Klasa(x)`        |

### Domknięcia (Closures)
Lambda może odwoływać się do zmiennych lokalnych z otaczającego zakresu, ale muszą one być **efektywnie finalne** (effectively final) — ich wartość nie może się zmieniać po inicjalizacji.

---

## Przykłady

### Przykład 1 — Podstawowe wyrażenia lambda
```java
@FunctionalInterface
interface Operacja {
    int wykonaj(int a, int b);
}

@FunctionalInterface
interface Powitanie {
    String powitaj(String imie);
}

@FunctionalInterface
interface Akcja {
    void wykonaj();
}

public class LambdaPodstawy {
    public static void main(String[] args) {
        // Lambda z dwoma parametrami
        Operacja dodawanie = (a, b) -> a + b;
        Operacja mnozenie = (a, b) -> a * b;
        Operacja potega = (a, b) -> (int) Math.pow(a, b);

        System.out.println("5 + 3 = " + dodawanie.wykonaj(5, 3));
        System.out.println("5 * 3 = " + mnozenie.wykonaj(5, 3));
        System.out.println("2 ^ 10 = " + potega.wykonaj(2, 10));

        // Lambda z jednym parametrem
        Powitanie formalne = imie -> "Szanowny Panie/Pani " + imie;
        Powitanie nieformalne = imie -> "Cześć, " + imie + "!";

        System.out.println(formalne.powitaj("Kowalski"));
        System.out.println(nieformalne.powitaj("Anna"));

        // Lambda bez parametrów
        Akcja pozdrowienie = () -> System.out.println("Witaj, świecie!");
        pozdrowienie.wykonaj();
    }
}
```

### Przykład 2 — Predicate — testowanie warunków
```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicatePrzyklad {
    static <T> List<T> filtruj(List<T> lista, Predicate<T> warunek) {
        List<T> wynik = new ArrayList<>();
        for (T element : lista) {
            if (warunek.test(element)) {
                wynik.add(element);
            }
        }
        return wynik;
    }

    public static void main(String[] args) {
        List<Integer> liczby = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> parzysta = n -> n % 2 == 0;
        Predicate<Integer> wiekszeOd5 = n -> n > 5;

        System.out.println("Parzyste: " + filtruj(liczby, parzysta));
        System.out.println("Większe od 5: " + filtruj(liczby, wiekszeOd5));

        // Łączenie predykatów: and, or, negate
        System.out.println("Parzyste i > 5: " + filtruj(liczby, parzysta.and(wiekszeOd5)));
        System.out.println("Parzyste lub > 5: " + filtruj(liczby, parzysta.or(wiekszeOd5)));
        System.out.println("Nieparzyste: " + filtruj(liczby, parzysta.negate()));

        // Predicate na Stringach
        List<String> imiona = List.of("Anna", "Jan", "Aleksandra", "Ewa", "Andrzej");
        Predicate<String> naA = s -> s.startsWith("A");
        Predicate<String> dluzszNiz4 = s -> s.length() > 4;

        System.out.println("Na 'A': " + filtruj(imiona, naA));
        System.out.println("Dłuższe niż 4: " + filtruj(imiona, dluzszNiz4));
    }
}
```

### Przykład 3 — Function — transformacja danych
```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionPrzyklad {
    static <T, R> List<R> mapuj(List<T> lista, Function<T, R> funkcja) {
        List<R> wynik = new ArrayList<>();
        for (T element : lista) {
            wynik.add(funkcja.apply(element));
        }
        return wynik;
    }

    public static void main(String[] args) {
        List<String> imiona = List.of("anna", "jan", "ewa", "piotr");

        // String → String (wielka litera)
        Function<String, String> doWielkiej = s -> s.substring(0, 1).toUpperCase() + s.substring(1);
        System.out.println("Wielkie litery: " + mapuj(imiona, doWielkiej));

        // String → Integer (długość)
        Function<String, Integer> dlugosc = String::length;
        System.out.println("Długości: " + mapuj(imiona, dlugosc));

        // Integer → String
        List<Integer> liczby = List.of(1, 2, 3, 4, 5);
        Function<Integer, String> doGwiazdek = n -> "*".repeat(n);
        System.out.println("Gwiazdki: " + mapuj(liczby, doGwiazdek));

        // Składanie funkcji: andThen i compose
        Function<Integer, Integer> podwoj = x -> x * 2;
        Function<Integer, Integer> dodaj10 = x -> x + 10;

        Function<Integer, Integer> podwojPotemDodaj = podwoj.andThen(dodaj10);
        Function<Integer, Integer> dodajPotemPodwoj = podwoj.compose(dodaj10);

        System.out.println("podwój potem dodaj10 (5): " + podwojPotemDodaj.apply(5)); // (5*2)+10 = 20
        System.out.println("dodaj10 potem podwój (5): " + dodajPotemPodwoj.apply(5)); // (5+10)*2 = 30
    }
}
```

### Przykład 4 — Consumer i Supplier
```java
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.Random;

public class ConsumerSupplierPrzyklad {
    public static void main(String[] args) {
        // Consumer — przyjmuje wartość, nic nie zwraca
        Consumer<String> wypisz = s -> System.out.println(">> " + s);
        Consumer<String> wypiszWielkie = s -> System.out.println(">> " + s.toUpperCase());

        wypisz.accept("hello");
        wypiszWielkie.accept("hello");

        // Łączenie konsumentów: andThen
        Consumer<String> oba = wypisz.andThen(wypiszWielkie);
        oba.accept("test");

        // Iteracja z Consumer
        List<String> jezyki = List.of("Java", "Python", "C++", "Kotlin");
        jezyki.forEach(j -> System.out.println("Język: " + j));

        // Supplier — nie przyjmuje argumentów, zwraca wartość
        Random random = new Random(42);
        Supplier<Integer> losowa = () -> random.nextInt(100);
        Supplier<String> timestamp = () -> java.time.LocalTime.now().toString();

        System.out.println("\nLosowe liczby:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + losowa.get());
        }

        System.out.println("Czas: " + timestamp.get());

        // Supplier jako fabryka obiektów
        Supplier<List<String>> listaFactory = ArrayList::new;
        List<String> nowaLista = listaFactory.get();
        nowaLista.add("Element");
        System.out.println("Nowa lista: " + nowaLista);
    }
}
```

### Przykład 5 — Referencje do metod (Method References)
```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;

public class ReferencjeDoMetod {
    static int podwoj(int x) {
        return x * 2;
    }

    static int dodaj(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        List<String> imiona = Arrays.asList("anna", "jan", "ewa", "piotr");

        // 1. Referencja do metody statycznej
        // Lambda: x -> Integer.parseInt(x)
        Function<String, Integer> parse = Integer::parseInt;
        System.out.println("Parse '42': " + parse.apply("42"));

        // 2. Referencja do metody instancji konkretnego obiektu
        String tekst = "Hello World";
        // Lambda: () -> tekst.toUpperCase()
        Supplier<String> wielkie = tekst::toUpperCase;
        System.out.println("Wielkie: " + wielkie.get());

        // 3. Referencja do metody instancji dowolnego obiektu danego typu
        // Lambda: s -> s.toUpperCase()
        Function<String, String> doWielkiej = String::toUpperCase;
        System.out.println("Do wielkiej: " + doWielkiej.apply("java"));

        // 4. Referencja do konstruktora
        // Lambda: s -> new StringBuilder(s)
        Function<String, StringBuilder> sbFactory = StringBuilder::new;
        StringBuilder sb = sbFactory.apply("Hello");
        System.out.println("StringBuilder: " + sb);

        // Użycie w forEach
        System.out.println("\nImiona:");
        imiona.stream()
              .map(String::toUpperCase)
              .forEach(System.out::println);

        // Referencja do własnej metody statycznej
        Function<Integer, Integer> podwojFn = ReferencjeDoMetod::podwoj;
        System.out.println("Podwój 5: " + podwojFn.apply(5));

        BiFunction<Integer, Integer, Integer> dodajFn = ReferencjeDoMetod::dodaj;
        System.out.println("Dodaj 3+7: " + dodajFn.apply(3, 7));
    }
}

// Potrzebny import dla Supplier (użyty bez importu w kodzie powyżej)
interface Supplier<T> { T get(); }
```

### Przykład 6 — Lambda z kolekcjami (sort, forEach, removeIf)
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaKolekcje {
    public static void main(String[] args) {
        // Sortowanie z Comparator (lambda)
        List<String> imiona = new ArrayList<>(Arrays.asList("Zofia", "Anna", "Jan", "Ewa", "Bartosz"));

        // Sortowanie alfabetyczne
        imiona.sort((a, b) -> a.compareTo(b));
        System.out.println("Alfabetycznie: " + imiona);

        // Sortowanie po długości
        imiona.sort((a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("Po długości: " + imiona);

        // Sortowanie z Comparator.comparing
        imiona.sort(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
        System.out.println("Po długości, potem alfabetycznie: " + imiona);

        // Sortowanie odwrotne
        imiona.sort(Comparator.reverseOrder());
        System.out.println("Odwrotnie: " + imiona);

        // forEach
        System.out.println("\nforEach:");
        imiona.forEach(i -> System.out.println("  - " + i));

        // removeIf
        List<Integer> liczby = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        liczby.removeIf(n -> n % 2 == 0); // usuń parzyste
        System.out.println("\nPo usunięciu parzystych: " + liczby);

        // replaceAll
        List<String> slowa = new ArrayList<>(Arrays.asList("java", "python", "kotlin"));
        slowa.replaceAll(String::toUpperCase);
        System.out.println("Wielkie litery: " + slowa);
    }
}
```

### Przykład 7 — Stream API z lambdami
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPrzyklad {
    public static void main(String[] args) {
        List<Integer> liczby = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter + collect
        List<Integer> parzyste = liczby.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Parzyste: " + parzyste);

        // map + collect
        List<Integer> kwadraty = liczby.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Kwadraty: " + kwadraty);

        // filter + map + collect (łańcuch)
        List<String> wynik = liczby.stream()
                .filter(n -> n > 5)
                .map(n -> "Liczba: " + n)
                .collect(Collectors.toList());
        System.out.println("Większe od 5: " + wynik);

        // reduce — suma
        int suma = liczby.stream()
                .reduce(0, Integer::sum);
        System.out.println("Suma: " + suma);

        // reduce — iloczyn
        int iloczyn = liczby.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Iloczyn: " + iloczyn);

        // count, min, max
        long ile = liczby.stream().filter(n -> n > 5).count();
        System.out.println("Ile > 5: " + ile);

        liczby.stream().max(Integer::compareTo)
              .ifPresent(max -> System.out.println("Max: " + max));

        // Stringi
        List<String> imiona = Arrays.asList("Anna", "Jan", "Aleksandra", "Ewa", "Andrzej", "Adam");
        String polaczone = imiona.stream()
                .filter(s -> s.startsWith("A"))
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println("Na 'A': " + polaczone);
    }
}
```

### Przykład 8 — Własny interfejs funkcyjny
```java
@FunctionalInterface
interface Konwerter<F, T> {
    T konwertuj(F zrodlo);
}

@FunctionalInterface
interface Walidator<T> {
    boolean waliduj(T wartosc);

    default Walidator<T> and(Walidator<T> other) {
        return wartosc -> this.waliduj(wartosc) && other.waliduj(wartosc);
    }

    default Walidator<T> or(Walidator<T> other) {
        return wartosc -> this.waliduj(wartosc) || other.waliduj(wartosc);
    }

    default Walidator<T> negate() {
        return wartosc -> !this.waliduj(wartosc);
    }
}

public class WlasnyInterfejsFunkcyjny {
    public static void main(String[] args) {
        // Konwerter
        Konwerter<String, Integer> doInt = Integer::parseInt;
        Konwerter<String, Double> doDouble = Double::parseDouble;
        Konwerter<Integer, String> doHex = n -> "0x" + Integer.toHexString(n);
        Konwerter<Double, String> zaokraglij = d -> String.format("%.2f", d);

        System.out.println("'123' → int: " + doInt.konwertuj("123"));
        System.out.println("'3.14' → double: " + doDouble.konwertuj("3.14"));
        System.out.println("255 → hex: " + doHex.konwertuj(255));
        System.out.println("3.14159 → zaokr: " + zaokraglij.konwertuj(3.14159));

        // Walidator z łączeniem
        Walidator<String> niepusty = s -> s != null && !s.isEmpty();
        Walidator<String> minDlugosc5 = s -> s != null && s.length() >= 5;
        Walidator<String> zawieraAt = s -> s != null && s.contains("@");

        Walidator<String> emailBasic = niepusty.and(minDlugosc5).and(zawieraAt);

        String[] testy = {"jan@mail.com", "abc", "", "a@b", "test@example.org"};
        for (String t : testy) {
            System.out.printf("'%s' → email: %b%n", t, emailBasic.waliduj(t));
        }
    }
}
```

### Przykład 9 — Domknięcia (Closures) i effectively final
```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;

public class DomknieciaPrzyklad {
    public static void main(String[] args) {
        // Lambda przechwytuje zmienną z otaczającego zakresu
        int mnoznik = 3; // effectively final
        IntUnaryOperator pomnoz = x -> x * mnoznik;

        System.out.println("5 * 3 = " + pomnoz.applyAsInt(5));
        System.out.println("10 * 3 = " + pomnoz.applyAsInt(10));

        // mnoznik = 4; // Błąd kompilacji! Zmienna musi być effectively final

        // Fabryka funkcji z domknięciem
        IntUnaryOperator dodaj5 = stworzDodawacz(5);
        IntUnaryOperator dodaj10 = stworzDodawacz(10);

        System.out.println("dodaj5(3) = " + dodaj5.applyAsInt(3));   // 8
        System.out.println("dodaj10(3) = " + dodaj10.applyAsInt(3)); // 13

        // Lambda przechwytująca listę (referencja jest effectively final)
        List<String> log = new ArrayList<>();
        Runnable logujAkcje = () -> {
            log.add("Akcja wykonana o " + java.time.LocalTime.now());
        };

        logujAkcje.run();
        logujAkcje.run();
        System.out.println("Log: " + log);
    }

    static IntUnaryOperator stworzDodawacz(int wartosc) {
        // wartosc jest przechwycona przez domknięcie
        return x -> x + wartosc;
    }
}
```

### Przykład 10 — Praktyczny przykład: przetwarzanie listy obiektów
```java
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

class Produkt {
    String nazwa;
    String kategoria;
    double cena;

    Produkt(String nazwa, String kategoria, double cena) {
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) — %.2f zł", nazwa, kategoria, cena);
    }
}

public class PraktycznyPrzyklad {
    public static void main(String[] args) {
        List<Produkt> produkty = Arrays.asList(
            new Produkt("Laptop", "Elektronika", 3500),
            new Produkt("Mysz", "Elektronika", 50),
            new Produkt("Kawa", "Spożywcze", 25),
            new Produkt("Monitor", "Elektronika", 1200),
            new Produkt("Herbata", "Spożywcze", 15),
            new Produkt("Klawiatura", "Elektronika", 130),
            new Produkt("Chleb", "Spożywcze", 5)
        );

        // Filtrowanie: produkty elektroniczne
        Predicate<Produkt> elektronika = p -> p.kategoria.equals("Elektronika");
        System.out.println("Elektronika:");
        produkty.stream().filter(elektronika).forEach(p -> System.out.println("  " + p));

        // Sortowanie po cenie
        System.out.println("\nPo cenie (rosnąco):");
        produkty.stream()
                .sorted(Comparator.comparingDouble(p -> p.cena))
                .forEach(p -> System.out.println("  " + p));

        // Mapowanie: nazwy produktów
        List<String> nazwy = produkty.stream()
                .map(p -> p.nazwa)
                .collect(Collectors.toList());
        System.out.println("\nNazwy: " + nazwy);

        // Suma cen
        double suma = produkty.stream()
                .mapToDouble(p -> p.cena)
                .sum();
        System.out.printf("\nSuma cen: %.2f zł%n", suma);

        // Grupowanie po kategorii
        Map<String, List<Produkt>> grupy = produkty.stream()
                .collect(Collectors.groupingBy(p -> p.kategoria));
        System.out.println("\nGrupy:");
        grupy.forEach((kat, lista) -> {
            System.out.println("  " + kat + ":");
            lista.forEach(p -> System.out.println("    - " + p));
        });

        // Średnia cena per kategoria
        Map<String, Double> srednieCeny = produkty.stream()
                .collect(Collectors.groupingBy(
                        p -> p.kategoria,
                        Collectors.averagingDouble(p -> p.cena)));
        System.out.println("\nŚrednie ceny:");
        srednieCeny.forEach((kat, sr) -> System.out.printf("  %s: %.2f zł%n", kat, sr));
    }
}
```
