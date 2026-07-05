# 1. Środowisko języka Java

## Teoria

### Czym jest Java?
Java to wysokopoziomowy, obiektowy język programowania opracowany przez firmę Sun Microsystems (obecnie Oracle) w 1995 roku. Java jest językiem **kompilowanym do bytecode'u**, który jest następnie wykonywany przez maszynę wirtualną Javy (JVM — Java Virtual Machine). Dzięki temu Java realizuje zasadę **„Write Once, Run Anywhere"** (WORA) — kod napisany raz działa na każdej platformie posiadającej JVM.

### Architektura platformy Java
Platforma Java składa się z kilku kluczowych elementów:

- **JDK (Java Development Kit)** — zestaw narzędzi deweloperskich zawierający kompilator (`javac`), debugger, narzędzia do dokumentacji (`javadoc`) oraz JRE.
- **JRE (Java Runtime Environment)** — środowisko uruchomieniowe zawierające JVM oraz biblioteki standardowe potrzebne do uruchamiania programów.
- **JVM (Java Virtual Machine)** — maszyna wirtualna odpowiedzialna za wykonywanie bytecode'u. JVM zarządza pamięcią (Garbage Collector), ładowaniem klas i optymalizacją kodu w czasie wykonywania (JIT — Just-In-Time Compilation).

### Proces kompilacji i uruchamiania
```
Kod źródłowy (.java) → kompilator javac → Bytecode (.class) → JVM → Wykonanie programu
```

1. Programista pisze kod w pliku z rozszerzeniem `.java`.
2. Kompilator `javac` tłumaczy kod źródłowy na bytecode zapisywany w plikach `.class`.
3. JVM interpretuje (lub kompiluje JIT) bytecode i wykonuje program.

### Wersje Javy
Java jest rozwijana w cyklach wydawniczych. Najważniejsze wersje:
- **Java SE 8 (2014)** — wyrażenia lambda, Stream API, interfejsy funkcyjne.
- **Java SE 11 (2018)** — wersja LTS (Long-Term Support), `var` w lambdach, HTTP Client API.
- **Java SE 17 (2021)** — LTS, sealed classes, pattern matching for instanceof.
- **Java SE 21 (2023)** — LTS, virtual threads, record patterns.

### Struktura programu w Javie
Każdy program w Javie musi zawierać co najmniej jedną klasę z metodą `main`. Nazwa pliku musi odpowiadać nazwie klasy publicznej.

### Narzędzia i IDE
Popularne środowiska programistyczne (IDE) dla Javy:
- **IntelliJ IDEA** — najbardziej zaawansowane IDE dla Javy (JetBrains).
- **Eclipse** — darmowe, open-source IDE.
- **NetBeans** — darmowe IDE wspierane przez Apache.
- **Visual Studio Code** — lekki edytor z rozszerzeniami dla Javy.

### Konwencje nazewnicze w Javie
| Element         | Konwencja              | Przykład                  |
|-----------------|------------------------|---------------------------|
| Klasa           | PascalCase             | `MojaKlasa`              |
| Metoda          | camelCase              | `obliczPole()`           |
| Zmienna         | camelCase              | `liczbaElementow`        |
| Stała           | UPPER_SNAKE_CASE       | `MAX_ROZMIAR`            |
| Pakiet          | lowercase              | `com.firma.projekt`      |

---

## Przykłady

### Przykład 1 — Pierwszy program „Hello World"
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Witaj, świecie!");
    }
}
```
**Kompilacja i uruchomienie:**
```bash
javac HelloWorld.java
java HelloWorld
```

### Przykład 2 — Komentarze w Javie
```java
public class Komentarze {
    public static void main(String[] args) {
        // To jest komentarz jednoliniowy

        /*
         * To jest komentarz
         * wieloliniowy (blokowy)
         */

        /**
         * To jest komentarz dokumentacyjny (Javadoc).
         * Używany do generowania dokumentacji.
         */
        System.out.println("Komentarze nie wpływają na działanie programu.");
    }
}
```

### Przykład 3 — Typy danych prymitywne
```java
public class TypyDanych {
    public static void main(String[] args) {
        byte wiekKota = 12;              // 8-bit, zakres: -128 do 127
        short rok = 2025;                // 16-bit
        int populacja = 1500000;         // 32-bit
        long dystans = 9876543210L;      // 64-bit (sufiks L)
        float pi = 3.14f;               // 32-bit zmiennoprzecinkowy (sufiks f)
        double precyzyjnePi = 3.141592653589793; // 64-bit zmiennoprzecinkowy
        char litera = 'A';              // 16-bit znak Unicode
        boolean czyPrawda = true;        // wartość logiczna

        System.out.println("byte: " + wiekKota);
        System.out.println("short: " + rok);
        System.out.println("int: " + populacja);
        System.out.println("long: " + dystans);
        System.out.println("float: " + pi);
        System.out.println("double: " + precyzyjnePi);
        System.out.println("char: " + litera);
        System.out.println("boolean: " + czyPrawda);
    }
}
```

### Przykład 4 — Zmienne i operatory
```java
public class ZmienneIOperatory {
    public static void main(String[] args) {
        int a = 10;
        int b = 3;

        // Operatory arytmetyczne
        System.out.println("Dodawanie: " + (a + b));    // 13
        System.out.println("Odejmowanie: " + (a - b));   // 7
        System.out.println("Mnożenie: " + (a * b));      // 30
        System.out.println("Dzielenie: " + (a / b));     // 3 (dzielenie całkowite!)
        System.out.println("Reszta: " + (a % b));        // 1

        // Operatory porównania
        System.out.println("a > b: " + (a > b));         // true
        System.out.println("a == b: " + (a == b));       // false
        System.out.println("a != b: " + (a != b));       // true

        // Operatory logiczne
        boolean x = true, y = false;
        System.out.println("x && y: " + (x && y));       // false
        System.out.println("x || y: " + (x || y));       // true
        System.out.println("!x: " + (!x));               // false

        // Operator przypisania złożonego
        int c = 5;
        c += 3;  // c = c + 3 → 8
        c *= 2;  // c = c * 2 → 16
        System.out.println("c = " + c);
    }
}
```

### Przykład 5 — Instrukcje warunkowe
```java
public class InstrukcjeWarunkowe {
    public static void main(String[] args) {
        int temperatura = 25;

        // if-else
        if (temperatura > 30) {
            System.out.println("Jest gorąco!");
        } else if (temperatura > 20) {
            System.out.println("Jest przyjemnie.");
        } else if (temperatura > 10) {
            System.out.println("Jest chłodno.");
        } else {
            System.out.println("Jest zimno!");
        }

        // Operator trójargumentowy (ternary)
        String wynik = (temperatura > 20) ? "Ciepło" : "Zimno";
        System.out.println("Pogoda: " + wynik);

        // switch
        int dzienTygodnia = 3;
        switch (dzienTygodnia) {
            case 1: System.out.println("Poniedziałek"); break;
            case 2: System.out.println("Wtorek"); break;
            case 3: System.out.println("Środa"); break;
            case 4: System.out.println("Czwartek"); break;
            case 5: System.out.println("Piątek"); break;
            case 6: System.out.println("Sobota"); break;
            case 7: System.out.println("Niedziela"); break;
            default: System.out.println("Nieprawidłowy dzień");
        }
    }
}
```

### Przykład 6 — Pętle
```java
public class Petle {
    public static void main(String[] args) {
        // Pętla for
        System.out.println("Pętla for:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("  i = " + i);
        }

        // Pętla while
        System.out.println("Pętla while:");
        int j = 1;
        while (j <= 5) {
            System.out.println("  j = " + j);
            j++;
        }

        // Pętla do-while
        System.out.println("Pętla do-while:");
        int k = 1;
        do {
            System.out.println("  k = " + k);
            k++;
        } while (k <= 5);

        // Pętla for-each (rozszerzona for)
        System.out.println("Pętla for-each:");
        String[] owoce = {"Jabłko", "Banan", "Czereśnia"};
        for (String owoc : owoce) {
            System.out.println("  " + owoc);
        }

        // break i continue
        System.out.println("break i continue:");
        for (int i = 1; i <= 10; i++) {
            if (i == 3) continue; // pomija iterację gdy i == 3
            if (i == 7) break;    // przerywa pętlę gdy i == 7
            System.out.println("  i = " + i);
        }
    }
}
```

### Przykład 7 — Tablice
```java
public class Tablice {
    public static void main(String[] args) {
        // Deklaracja i inicjalizacja tablicy
        int[] liczby = {10, 20, 30, 40, 50};

        // Dostęp do elementów
        System.out.println("Pierwszy element: " + liczby[0]);
        System.out.println("Długość tablicy: " + liczby.length);

        // Iteracja po tablicy
        for (int i = 0; i < liczby.length; i++) {
            System.out.println("liczby[" + i + "] = " + liczby[i]);
        }

        // Tablica dwuwymiarowa
        int[][] macierz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("\nMacierz:");
        for (int[] wiersz : macierz) {
            for (int wartosc : wiersz) {
                System.out.print(wartosc + " ");
            }
            System.out.println();
        }
    }
}
```

### Przykład 8 — Wczytywanie danych od użytkownika (Scanner)
```java
import java.util.Scanner;

public class WczytywanieDanych {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj swoje imię: ");
        String imie = scanner.nextLine();

        System.out.print("Podaj swój wiek: ");
        int wiek = scanner.nextInt();

        System.out.println("Cześć, " + imie + "! Masz " + wiek + " lat.");

        scanner.close();
    }
}
```

### Przykład 9 — Metody statyczne
```java
public class MetodyStatyczne {

    public static int dodaj(int a, int b) {
        return a + b;
    }

    public static double poleProstokata(double szerokosc, double wysokosc) {
        return szerokosc * wysokosc;
    }

    public static boolean czyParzysta(int liczba) {
        return liczba % 2 == 0;
    }

    public static void wypiszPowitanie(String imie) {
        System.out.println("Witaj, " + imie + "!");
    }

    public static void main(String[] args) {
        System.out.println("2 + 3 = " + dodaj(2, 3));
        System.out.println("Pole: " + poleProstokata(5.0, 3.0));
        System.out.println("Czy 4 jest parzysta? " + czyParzysta(4));
        System.out.println("Czy 7 jest parzysta? " + czyParzysta(7));
        wypiszPowitanie("Anna");
    }
}
```

### Przykład 10 — Formatowanie wyjścia (printf i String.format)
```java
public class Formatowanie {
    public static void main(String[] args) {
        String imie = "Jan";
        int wiek = 25;
        double srednia = 4.567;

        // printf — formatowane wyjście
        System.out.printf("Imię: %s, Wiek: %d%n", imie, wiek);
        System.out.printf("Średnia: %.2f%n", srednia);  // 2 miejsca po przecinku
        System.out.printf("Liczba: %010d%n", 42);       // wypełnienie zerami

        // String.format — zwraca sformatowany String
        String info = String.format("Student %s ma %.1f średnią.", imie, srednia);
        System.out.println(info);
    }
}
```
