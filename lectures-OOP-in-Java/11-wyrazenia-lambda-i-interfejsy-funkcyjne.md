# Wykład 11: Wyrażenia lambda i interfejsy funkcyjne

## Spis treści

- [1. Po co w ogóle lambda?](#1-po-co-w-ogóle-lambda)
- [2. Interfejs funkcyjny](#2-interfejs-funkcyjny)
- [3. Składnia lambd](#3-składnia-lambd)
- [4. Wbudowane interfejsy funkcyjne z `java.util.function`](#4-wbudowane-interfejsy-funkcyjne-z-javautilfunction)
- [5. Lambda jako argument metody](#5-lambda-jako-argument-metody)
- [6. `Comparator` i sortowanie](#6-comparator-i-sortowanie)
- [7. Referencje do metod](#7-referencje-do-metod)
- [8. Zasięg zmiennych i "effectively final"](#8-zasięg-zmiennych-i-effectively-final)
- [9. Lambdy a programowanie obiektowe](#9-lambdy-a-programowanie-obiektowe)
- [10. Lambdy i Stream API - krótki wstęp](#10-lambdy-i-stream-api---krótki-wstęp)
- [11. Dobre praktyki](#11-dobre-praktyki)
- [12. Typowe błędy](#12-typowe-błędy)
- [13. Przykłady zadań](#13-przykłady-zadań)
- [14. Mini-quiz](#14-mini-quiz)
- [15. Podsumowanie](#15-podsumowanie)
- [Uzupełnienia](#uzupełnienia)
- [Podsumowanie](#podsumowanie)
- [Pytania kontrolne](#pytania-kontrolne)
- [Laboratorium](#laboratorium)

Wyrażenia lambda są naturalnym rozwinięciem programowania obiektowego w Javie. Pozwalają przekazywać zachowania jako argumenty, upraszczają kod oparty o interfejsy jednomeetodowe i dobrze łączą się z kolekcjami, komparatorami oraz strumieniami.

## 1. Po co w ogóle lambda?

Przed wprowadzeniem lambd w Javie częstym rozwiązaniem były:
- osobne małe klasy implementujące interfejs,
- klasy anonimowe,
- duża ilość kodu pomocniczego tylko po to, by przekazać "co zrobić".

Lambda pozwala zapisać to samo krócej i czytelniej.

Przykład porównawczy:

```java
Comparator<String> byLengthOld = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return Integer.compare(a.length(), b.length());
    }
};

Comparator<String> byLengthLambda = (a, b) -> Integer.compare(a.length(), b.length());
```

## 2. Interfejs funkcyjny

Interfejs funkcyjny to taki interfejs, który ma dokładnie jedną metodę abstrakcyjną.

Przykład:

```java
@FunctionalInterface
public interface Printer {
    void print(String text);
}
```

Adnotacja `@FunctionalInterface`:
- nie jest obowiązkowa,
- ale pomaga kompilatorowi wykrywać błędy,
- komunikuje intencję autora kodu.

Lambda może zastąpić implementację takiego interfejsu:

```java
Printer printer = text -> System.out.println(">> " + text);
printer.print("Java");
```

## 3. Składnia lambd

Ogólny schemat:

```java
(parametry) -> wyrazenie
```

albo:

```java
(parametry) -> {
    instrukcje;
}
```

Przykłady:

```java
() -> System.out.println("Hello");
(x) -> x * x;
(a, b) -> a + b;
(name) -> {
    System.out.println("Cześć " + name);
};
```

Zasady praktyczne:
- jeśli parametr jest jeden, nawiasy można pominąć,
- jeśli ciało ma jedną instrukcję zwracającą wynik, nawiasy klamrowe i `return` można pominąć,
- typy parametrów zwykle są wywnioskowane przez kompilator.

## 4. Wbudowane interfejsy funkcyjne z `java.util.function`

Najważniejsze:
- `Predicate<T>` - przyjmuje `T`, zwraca `boolean`,
- `Function<T, R>` - przyjmuje `T`, zwraca `R`,
- `Consumer<T>` - przyjmuje `T`, nic nie zwraca,
- `Supplier<T>` - nic nie przyjmuje, zwraca `T`,
- `UnaryOperator<T>` - `T -> T`,
- `BinaryOperator<T>` - `(T, T) -> T`.

### `Predicate<T>`

```java
Predicate<Integer> isAdultAge = age -> age >= 18;
System.out.println(isAdultAge.test(20)); // true
```

### `Function<T, R>`

```java
Function<String, Integer> lengthFunction = text -> text.length();
System.out.println(lengthFunction.apply("Java"));
```

### `Consumer<T>`

```java
Consumer<String> logger = msg -> System.out.println("LOG: " + msg);
logger.accept("Start programu");
```

### `Supplier<T>`

```java
Supplier<Double> randomValue = () -> Math.random();
System.out.println(randomValue.get());
```

## 5. Lambda jako argument metody

To najważniejszy praktyczny przypadek użycia.

```java
public class TextProcessor {
    public static void process(String text, Consumer<String> action) {
        action.accept(text);
    }

    public static void main(String[] args) {
        process("Java", value -> System.out.println(value.toUpperCase()));
        process("OOP", value -> System.out.println("[" + value + "]"));
    }
}
```

Korzyść:
- jedna metoda może wykonywać różne zachowania bez tworzenia wielu klas.

## 6. `Comparator` i sortowanie

Jedno z najczęstszych zastosowań lambd.

```java
List<String> names = new ArrayList<>();
names.add("Joanna");
names.add("Jan");
names.add("Aleksandra");

names.sort((a, b) -> Integer.compare(a.length(), b.length()));
System.out.println(names);
```

Z użyciem metod pomocniczych:

```java
names.sort(Comparator.comparingInt(String::length));
```

## 7. Referencje do metod

Jeśli lambda tylko wywołuje istniejącą metodę, można użyć krótszego zapisu.

Rodzaje:
- `ClassName::staticMethod`,
- `object::instanceMethod`,
- `ClassName::instanceMethod`,
- `ClassName::new`.

Przykłady:

```java
Consumer<String> printer = System.out::println;
Function<String, Integer> parser = Integer::parseInt;
Supplier<List<String>> listFactory = ArrayList::new;
```

Przykład w praktyce:

```java
List<String> values = Arrays.asList("3", "1", "2");
values.stream()
      .map(Integer::parseInt)
      .sorted()
      .forEach(System.out::println);
```

## 8. Zasięg zmiennych i "effectively final"

Lambda może korzystać ze zmiennych lokalnych tylko wtedy, gdy są one finalne albo "effectively final".

```java
String prefix = "Wynik: ";
Consumer<Integer> consumer = value -> System.out.println(prefix + value);
```

To zadziała, bo `prefix` nie zmienia wartości.

To już nie:

```java
int counter = 0;
// counter++;
// Runnable r = () -> System.out.println(counter);
```

Powód: lambda nie może dowolnie modyfikować lokalnego stanu metody, bo utrudniałoby to przewidywalność kodu.

## 9. Lambdy a programowanie obiektowe

Lambda nie zastępuje klas i obiektów. Uzupełnia je.

W OOP nadal modelujemy:
- stan,
- odpowiedzialności,
- relacje między obiektami.

Lambda jest szczególnie przydatna wtedy, gdy:
- chcemy przekazać mały fragment zachowania,
- nie chcemy tworzyć osobnej klasy tylko dla jednej metody,
- pracujemy z kolekcjami i operacjami przetwarzania danych.

```mermaid
flowchart LR
    A[Obiekt / klasa] --> B[stan + zachowanie]
    B --> C[interfejs funkcyjny]
    C --> D[lambda]
    D --> E[przekazanie zachowania do metody]
```

## 10. Lambdy i Stream API - krótki wstęp

Pełne omówienie strumieni można zrobić osobno, ale warto znać podstawowy wzorzec:

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

int sum = numbers.stream()
        .filter(n -> n % 2 == 0)
        .map(n -> n * 10)
        .reduce(0, Integer::sum);

System.out.println(sum);
```

Tu lambda służy do:
- filtrowania,
- przekształcania,
- redukcji danych.

## 11. Dobre praktyki

- Używaj lambd do krótkich zachowań.
- Gdy lambda robi się długa i wieloliniowa, rozważ zwykłą metodę.
- Nie upychaj całej logiki biznesowej w strumieniach.
- Nadawaj znaczące nazwy interfejsom funkcyjnym własnej produkcji.
- Jeśli pasuje gotowy interfejs z `java.util.function`, nie twórz własnego bez potrzeby.
- Dla czytelności wybieraj prostotę zamiast "sprytnego" zapisu.

## 12. Typowe błędy

- Mylenie lambd z wielowątkowością - lambda nie oznacza automatycznie kodu asynchronicznego.
- Nadużywanie strumieni tam, gdzie zwykła pętla jest czytelniejsza.
- Tworzenie własnych interfejsów funkcyjnych mimo istnienia `Predicate`, `Function` czy `Consumer`.
- Ukrywanie skomplikowanej logiki w jednej, trudnej do debugowania lambdzie.

## 13. Przykłady zadań

### Zadanie 1 - filtrowanie listy

```java
public static List<String> filter(List<String> data, Predicate<String> predicate) {
    List<String> result = new ArrayList<>();
    for (String item : data) {
        if (predicate.test(item)) {
            result.add(item);
        }
    }
    return result;
}
```

Wywołanie:

```java
List<String> names = Arrays.asList("Ala", "Ola", "Jan", "Anna");
List<String> longNames = filter(names, name -> name.length() >= 4);
System.out.println(longNames);
```

### Zadanie 2 - strategia rabatu

```java
@FunctionalInterface
interface DiscountStrategy {
    double apply(double price);
}

public class Shop {
    public static double finalPrice(double price, DiscountStrategy strategy) {
        return strategy.apply(price);
    }

    public static void main(String[] args) {
        double result = finalPrice(100.0, p -> p * 0.9);
        System.out.println(result);
    }
}
```

## 14. Mini-quiz

1. Czym różni się interfejs funkcyjny od zwykłego interfejsu?
2. Kiedy lepiej użyć lambdy, a kiedy zwykłej metody?
3. Co robi `Predicate<T>`?
4. Dlaczego zmienna lokalna używana w lambdzie musi być effectively final?
5. Co oznacza zapis `System.out::println`?

## 15. Podsumowanie

Po tym module student powinien:
- rozumieć, czym jest interfejs funkcyjny,
- umieć zapisać prostą lambdę,
- korzystać z podstawowych interfejsów z `java.util.function`,
- stosować lambdy do sortowania, filtrowania i przekazywania zachowań,
- rozumieć związek lambd z nowoczesnym stylem programowania w Javie.

---

## Uzupełnienia

### Wbudowane interfejsy funkcyjne (`java.util.function`)

| Interfejs | Metoda | Sygnatura | Przykład |
|---|---|---|---|
| `Function<T,R>` | `apply` | `T -> R` | `Function<String,Integer> len = String::length;` |
| `BiFunction<T,U,R>` | `apply` | `(T,U) -> R` | `BiFunction<Integer,Integer,Integer> add = (a, b) -> a + b;` |
| `UnaryOperator<T>` | `apply` | `T -> T` | `UnaryOperator<String> up = String::toUpperCase;` |
| `BinaryOperator<T>` | `apply` | `(T,T) -> T` | `BinaryOperator<Integer> max = Math::max;` |
| `Predicate<T>` | `test` | `T -> boolean` | `Predicate<String> pusty = String::isEmpty;` |
| `BiPredicate<T,U>` | `test` | `(T,U) -> boolean` | `BiPredicate<String,Integer> dl = (s, n) -> s.length() == n;` |
| `Consumer<T>` | `accept` | `T -> void` | `Consumer<String> print = System.out::println;` |
| `BiConsumer<T,U>` | `accept` | `(T,U) -> void` | `map.forEach((k, v) -> System.out.println(k + "=" + v));` |
| `Supplier<T>` | `get` | `() -> T` | `Supplier<List<String>> s = ArrayList::new;` |

Istnieją też wersje dla typów prostych (bez autoboxingu): `IntPredicate`, `IntFunction<R>`, `ToIntFunction<T>`, `IntUnaryOperator` itd.

### Cztery rodzaje referencji do metod

| Rodzaj | Składnia | Przykład | Równoważna lambda |
|---|---|---|---|
| Metoda statyczna | `Klasa::metodaStatyczna` | `Integer::parseInt` | `s -> Integer.parseInt(s)` |
| Metoda konkretnego obiektu | `obiekt::metoda` | `System.out::println` | `x -> System.out.println(x)` |
| Metoda dowolnego obiektu danego typu | `Klasa::metodaInstancyjna` | `String::toUpperCase` | `s -> s.toUpperCase()` |
| Konstruktor | `Klasa::new` | `ArrayList::new` | `() -> new ArrayList<>()` |

---

## Podsumowanie

| Pojęcie | Znaczenie |
|---|---|
| lambda | anonimowa funkcja `(x) -> ...` |
| interfejs funkcyjny | interfejs z jedną metodą abstrakcyjną |
| referencja do metody | skrót `Klasa::metoda` |
| Stream API | potok operacji `filter`/`map`/`reduce` |

## Pytania kontrolne

1. Czym jest interfejs funkcyjny?
2. Jakie zmienne lokalne można użyć w lambdzie?
3. Wymień cztery rodzaje referencji do metod.

## Laboratorium

Zadania praktyczne do tego wykładu: [Laboratorium 11](../labs-OOP-in-Java/11-wyrazenia-lambda-i-interfejsy-funkcyjne.md)
