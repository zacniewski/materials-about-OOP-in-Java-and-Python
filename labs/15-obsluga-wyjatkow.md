# 15. Mechanizm obsługi wyjątków. Typy wyjątków.

## Teoria

### Czym jest wyjątek?
Wyjątek (exception) to zdarzenie, które zakłóca normalny przepływ wykonywania programu. W Javie wyjątki są **obiektami** — instancjami klas dziedziczących po `java.lang.Throwable`.

### Hierarchia wyjątków w Javie
```
Throwable
├── Error                    (błędy systemowe — nie łapiemy)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── ...
└── Exception                (wyjątki — łapiemy i obsługujemy)
    ├── RuntimeException     (unchecked — nie wymagają deklaracji)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── ArithmeticException
    │   ├── ClassCastException
    │   ├── IllegalArgumentException
    │   └── ...
    ├── IOException          (checked — wymagają deklaracji)
    ├── SQLException
    └── ...
```

### Typy wyjątków

1. **Checked exceptions** — wyjątki sprawdzane w czasie kompilacji. Muszą być obsłużone (`try-catch`) lub zadeklarowane (`throws`). Dziedziczą po `Exception` (ale nie po `RuntimeException`).
   - `IOException`, `FileNotFoundException`, `SQLException`, `ClassNotFoundException`

2. **Unchecked exceptions (Runtime)** — wyjątki niesprawdzane w czasie kompilacji. Dziedziczą po `RuntimeException`. Nie wymagają jawnej obsługi.
   - `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`, `IllegalArgumentException`

3. **Errors** — poważne błędy systemowe, których zazwyczaj nie obsługujemy. Dziedziczą po `Error`.
   - `OutOfMemoryError`, `StackOverflowError`

### Mechanizm try-catch-finally
```java
try {
    // kod, który może rzucić wyjątek
} catch (TypWyjatku1 e) {
    // obsługa wyjątku typu 1
} catch (TypWyjatku2 e) {
    // obsługa wyjątku typu 2
} finally {
    // kod wykonywany ZAWSZE (niezależnie od wyjątku)
}
```

### Słowo kluczowe `throw`
Służy do **rzucania** wyjątku:
```java
throw new IllegalArgumentException("Nieprawidłowy argument");
```

### Słowo kluczowe `throws`
Deklaruje, że metoda **może rzucić** wyjątek checked:
```java
void czytajPlik(String sciezka) throws IOException { ... }
```

### Try-with-resources (Java 7+)
Automatyczne zamykanie zasobów implementujących `AutoCloseable`:
```java
try (FileReader fr = new FileReader("plik.txt")) {
    // użycie zasobu
} // fr.close() wywoływane automatycznie
```

### Multi-catch (Java 7+)
Łapanie wielu typów wyjątków w jednym bloku catch:
```java
catch (IOException | SQLException e) { ... }
```

### Tworzenie własnych wyjątków
Własne wyjątki tworzy się przez dziedziczenie po `Exception` (checked) lub `RuntimeException` (unchecked).

---

## Przykłady

### Przykład 1 — Podstawowy try-catch
```java
public class TryCatchPodstawy {
    public static void main(String[] args) {
        // ArithmeticException
        try {
            int wynik = 10 / 0;
            System.out.println("Wynik: " + wynik); // nie wykona się
        } catch (ArithmeticException e) {
            System.out.println("Błąd arytmetyczny: " + e.getMessage());
        }

        // ArrayIndexOutOfBoundsException
        try {
            int[] tablica = {1, 2, 3};
            System.out.println(tablica[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indeks poza zakresem: " + e.getMessage());
        }

        // NullPointerException
        try {
            String tekst = null;
            System.out.println(tekst.length());
        } catch (NullPointerException e) {
            System.out.println("Null pointer: " + e.getMessage());
        }

        // NumberFormatException
        try {
            int liczba = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("Błąd parsowania: " + e.getMessage());
        }

        System.out.println("\nProgram kontynuuje działanie po obsłudze wyjątków.");
    }
}
```

### Przykład 2 — Wiele bloków catch i finally
```java
public class WieleCatch {
    static double podziel(String licznikStr, String mianownikStr) {
        try {
            int licznik = Integer.parseInt(licznikStr);
            int mianownik = Integer.parseInt(mianownikStr);
            double wynik = (double) licznik / mianownik;

            if (mianownik == 0) {
                throw new ArithmeticException("Dzielenie przez zero!");
            }

            return wynik;
        } catch (NumberFormatException e) {
            System.out.println("Błąd: nieprawidłowy format liczby — " + e.getMessage());
            return Double.NaN;
        } catch (ArithmeticException e) {
            System.out.println("Błąd arytmetyczny: " + e.getMessage());
            return Double.POSITIVE_INFINITY;
        } catch (Exception e) {
            System.out.println("Nieoczekiwany błąd: " + e.getMessage());
            return Double.NaN;
        } finally {
            System.out.println("  [finally] Operacja dzielenia zakończona.");
        }
    }

    public static void main(String[] args) {
        System.out.println("10 / 3 = " + podziel("10", "3"));
        System.out.println();
        System.out.println("10 / 0 = " + podziel("10", "0"));
        System.out.println();
        System.out.println("abc / 3 = " + podziel("abc", "3"));
    }
}
```

### Przykład 3 — Rzucanie wyjątków (throw)
```java
public class ThrowPrzyklad {

    static void sprawdzWiek(int wiek) {
        if (wiek < 0) {
            throw new IllegalArgumentException("Wiek nie może być ujemny: " + wiek);
        }
        if (wiek > 150) {
            throw new IllegalArgumentException("Wiek nie może przekraczać 150: " + wiek);
        }
        System.out.println("Wiek " + wiek + " jest prawidłowy.");
    }

    static double pierwiastek(double liczba) {
        if (liczba < 0) {
            throw new ArithmeticException("Nie można obliczyć pierwiastka z liczby ujemnej: " + liczba);
        }
        return Math.sqrt(liczba);
    }

    static String pobierzElement(String[] tablica, int indeks) {
        if (tablica == null) {
            throw new NullPointerException("Tablica nie może być null!");
        }
        if (indeks < 0 || indeks >= tablica.length) {
            throw new IndexOutOfBoundsException(
                "Indeks " + indeks + " poza zakresem [0, " + (tablica.length - 1) + "]");
        }
        return tablica[indeks];
    }

    public static void main(String[] args) {
        // Test sprawdzWiek
        try { sprawdzWiek(25); } catch (Exception e) { System.out.println("Błąd: " + e.getMessage()); }
        try { sprawdzWiek(-5); } catch (Exception e) { System.out.println("Błąd: " + e.getMessage()); }
        try { sprawdzWiek(200); } catch (Exception e) { System.out.println("Błąd: " + e.getMessage()); }

        System.out.println();

        // Test pierwiastek
        System.out.println("√16 = " + pierwiastek(16));
        try { pierwiastek(-4); } catch (Exception e) { System.out.println("Błąd: " + e.getMessage()); }

        System.out.println();

        // Test pobierzElement
        String[] owoce = {"Jabłko", "Banan", "Czereśnia"};
        System.out.println("Element [1]: " + pobierzElement(owoce, 1));
        try { pobierzElement(owoce, 10); } catch (Exception e) { System.out.println("Błąd: " + e.getMessage()); }
        try { pobierzElement(null, 0); } catch (Exception e) { System.out.println("Błąd: " + e.getMessage()); }
    }
}
```

### Przykład 4 — Checked exceptions i throws
```java
import java.io.*;

public class CheckedExceptions {

    // Metoda deklaruje, że może rzucić IOException
    static String czytajPlik(String sciezka) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(sciezka));
        StringBuilder sb = new StringBuilder();
        String linia;
        while ((linia = reader.readLine()) != null) {
            sb.append(linia).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    // Metoda obsługuje wyjątek wewnętrznie
    static String czytajPlikBezpiecznie(String sciezka) {
        try {
            return czytajPlik(sciezka);
        } catch (FileNotFoundException e) {
            System.out.println("Plik nie znaleziony: " + sciezka);
            return "";
        } catch (IOException e) {
            System.out.println("Błąd odczytu: " + e.getMessage());
            return "";
        }
    }

    public static void main(String[] args) {
        // Musimy obsłużyć checked exception
        try {
            String zawartosc = czytajPlik("nieistniejacy.txt");
            System.out.println(zawartosc);
        } catch (IOException e) {
            System.out.println("Złapano IOException: " + e.getMessage());
        }

        // Lub użyć metody, która obsługuje wyjątek wewnętrznie
        String wynik = czytajPlikBezpiecznie("test.txt");
        System.out.println("Wynik: " + (wynik.isEmpty() ? "(pusty)" : wynik));
    }
}
```

### Przykład 5 — Try-with-resources
```java
import java.io.*;

public class TryWithResources {

    static void zapiszDoPliku(String sciezka, String tresc) {
        // Automatyczne zamykanie zasobu
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sciezka))) {
            writer.write(tresc);
            System.out.println("Zapisano do pliku: " + sciezka);
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
        // writer.close() wywoływane automatycznie!
    }

    static String czytajZPliku(String sciezka) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(sciezka))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                sb.append(linia).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Plik nie istnieje: " + sciezka);
        } catch (IOException e) {
            System.out.println("Błąd odczytu: " + e.getMessage());
        }
        return sb.toString();
    }

    // Wiele zasobów w jednym try
    static void kopiujPlik(String zrodlo, String cel) {
        try (
            BufferedReader reader = new BufferedReader(new FileReader(zrodlo));
            BufferedWriter writer = new BufferedWriter(new FileWriter(cel))
        ) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                writer.write(linia);
                writer.newLine();
            }
            System.out.println("Skopiowano " + zrodlo + " → " + cel);
        } catch (IOException e) {
            System.out.println("Błąd kopiowania: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        zapiszDoPliku("/tmp/test_wyjatki.txt", "Linia 1\nLinia 2\nLinia 3");
        String zawartosc = czytajZPliku("/tmp/test_wyjatki.txt");
        System.out.println("Odczytano:\n" + zawartosc);
    }
}
```

### Przykład 6 — Własne wyjątki (custom exceptions)
```java
// Checked exception
class NiewystarczajaceSrodkiException extends Exception {
    private double brakujacaKwota;

    NiewystarczajaceSrodkiException(double brakujaca) {
        super("Brakuje " + brakujaca + " zł na koncie");
        this.brakujacaKwota = brakujaca;
    }

    double getBrakujacaKwota() {
        return brakujacaKwota;
    }
}

// Unchecked exception
class NieprawidlowyNumerKontaException extends RuntimeException {
    NieprawidlowyNumerKontaException(String numer) {
        super("Nieprawidłowy numer konta: " + numer);
    }
}

class KontoBankowe {
    private String numer;
    private double saldo;

    KontoBankowe(String numer, double saldo) {
        if (numer == null || numer.length() != 10) {
            throw new NieprawidlowyNumerKontaException(numer);
        }
        this.numer = numer;
        this.saldo = saldo;
    }

    void wyplac(double kwota) throws NiewystarczajaceSrodkiException {
        if (kwota > saldo) {
            throw new NiewystarczajaceSrodkiException(kwota - saldo);
        }
        saldo -= kwota;
        System.out.printf("Wypłacono %.2f zł. Saldo: %.2f zł%n", kwota, saldo);
    }

    @Override
    public String toString() {
        return String.format("Konto %s: %.2f zł", numer, saldo);
    }
}

public class WlasneWyjatki {
    public static void main(String[] args) {
        // Unchecked — nie wymaga try-catch
        try {
            KontoBankowe zle = new KontoBankowe("123", 1000);
        } catch (NieprawidlowyNumerKontaException e) {
            System.out.println("Błąd: " + e.getMessage());
        }

        KontoBankowe konto = new KontoBankowe("1234567890", 1000);
        System.out.println(konto);

        // Checked — wymaga try-catch lub throws
        try {
            konto.wyplac(500);
            konto.wyplac(800); // za mało środków
        } catch (NiewystarczajaceSrodkiException e) {
            System.out.println("Błąd: " + e.getMessage());
            System.out.printf("Brakuje: %.2f zł%n", e.getBrakujacaKwota());
        }

        System.out.println(konto);
    }
}
```

### Przykład 7 — Multi-catch i re-throw
```java
public class MultiCatchReThrow {

    static int parsujIpodziel(String liczbaStr, String dzielnikStr) throws Exception {
        try {
            int liczba = Integer.parseInt(liczbaStr);
            int dzielnik = Integer.parseInt(dzielnikStr);
            return liczba / dzielnik;
        } catch (NumberFormatException | ArithmeticException e) {
            // Multi-catch — jeden blok dla wielu typów
            System.out.println("Złapano: " + e.getClass().getSimpleName() + ": " + e.getMessage());

            // Re-throw — ponowne rzucenie wyjątku (lub opakowanie)
            throw new Exception("Błąd w parsujIpodziel: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("10 / 2 = " + parsujIpodziel("10", "2"));
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }

        System.out.println();

        try {
            parsujIpodziel("abc", "2");
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
            System.out.println("Przyczyna: " + e.getCause());
        }

        System.out.println();

        try {
            parsujIpodziel("10", "0");
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}
```

### Przykład 8 — Łańcuch wyjątków (Exception chaining)
```java
class BladBazyDanychException extends Exception {
    BladBazyDanychException(String msg, Throwable przyczyna) {
        super(msg, przyczyna);
    }
}

class BladSerwisuException extends Exception {
    BladSerwisuException(String msg, Throwable przyczyna) {
        super(msg, przyczyna);
    }
}

class BazaDanych {
    static String zapytanie(String sql) throws BladBazyDanychException {
        try {
            if (sql.contains("DROP")) {
                throw new SecurityException("Niedozwolona operacja!");
            }
            if (sql.contains("INVALID")) {
                throw new RuntimeException("Błąd składni SQL");
            }
            return "Wynik zapytania: " + sql;
        } catch (Exception e) {
            throw new BladBazyDanychException("Błąd wykonania zapytania: " + sql, e);
        }
    }
}

class Serwis {
    static String pobierzDane(String id) throws BladSerwisuException {
        try {
            return BazaDanych.zapytanie("SELECT * FROM users WHERE id=" + id);
        } catch (BladBazyDanychException e) {
            throw new BladSerwisuException("Nie udało się pobrać danych dla id=" + id, e);
        }
    }
}

public class LancuchWyjatkow {
    public static void main(String[] args) {
        // Sukces
        try {
            System.out.println(Serwis.pobierzDane("42"));
        } catch (BladSerwisuException e) {
            System.out.println("Błąd: " + e.getMessage());
        }

        // Błąd — łańcuch wyjątków
        try {
            Serwis.pobierzDane("INVALID");
        } catch (BladSerwisuException e) {
            System.out.println("\n=== Łańcuch wyjątków ===");
            Throwable t = e;
            int poziom = 0;
            while (t != null) {
                System.out.println("  ".repeat(poziom) + t.getClass().getSimpleName() + ": " + t.getMessage());
                t = t.getCause();
                poziom++;
            }
        }
    }
}
```

### Przykład 9 — Hierarchia własnych wyjątków
```java
// Bazowy wyjątek aplikacji
class AplikacjaException extends Exception {
    private String kod;

    AplikacjaException(String kod, String msg) {
        super(msg);
        this.kod = kod;
    }

    String getKod() { return kod; }
}

class WalidacjaException extends AplikacjaException {
    private String pole;

    WalidacjaException(String pole, String msg) {
        super("VALIDATION_ERROR", msg);
        this.pole = pole;
    }

    String getPole() { return pole; }
}

class AutoryzacjaException extends AplikacjaException {
    AutoryzacjaException(String msg) {
        super("AUTH_ERROR", msg);
    }
}

class NieZnalezionoException extends AplikacjaException {
    NieZnalezionoException(String zasob, String id) {
        super("NOT_FOUND", "Nie znaleziono " + zasob + " o id=" + id);
    }
}

public class HierarchiaWyjatkow {
    static void walidujEmail(String email) throws WalidacjaException {
        if (email == null || !email.contains("@")) {
            throw new WalidacjaException("email", "Nieprawidłowy email: " + email);
        }
    }

    static void sprawdzUprawnienia(String rola) throws AutoryzacjaException {
        if (!"ADMIN".equals(rola)) {
            throw new AutoryzacjaException("Wymagana rola ADMIN, posiadana: " + rola);
        }
    }

    static String znajdzUzytkownika(int id) throws NieZnalezionoException {
        if (id != 1) {
            throw new NieZnalezionoException("Użytkownik", String.valueOf(id));
        }
        return "Jan Kowalski";
    }

    public static void main(String[] args) {
        String[][] testy = {
            {"email", "nieprawidlowy"},
            {"auth", "USER"},
            {"find", "999"},
            {"email", "jan@mail.com"},
            {"auth", "ADMIN"},
            {"find", "1"}
        };

        for (String[] test : testy) {
            try {
                switch (test[0]) {
                    case "email": walidujEmail(test[1]); System.out.println("Email OK: " + test[1]); break;
                    case "auth": sprawdzUprawnienia(test[1]); System.out.println("Autoryzacja OK"); break;
                    case "find": System.out.println("Znaleziono: " + znajdzUzytkownika(Integer.parseInt(test[1]))); break;
                }
            } catch (AplikacjaException e) {
                // Łapiemy bazowy typ — obsługuje wszystkie podtypy
                System.out.printf("[%s] %s%n", e.getKod(), e.getMessage());
            }
        }
    }
}
```

### Przykład 10 — Praktyczny przykład: walidacja formularza
```java
import java.util.ArrayList;
import java.util.List;

class BladWalidacji {
    String pole;
    String komunikat;

    BladWalidacji(String pole, String komunikat) {
        this.pole = pole;
        this.komunikat = komunikat;
    }

    @Override
    public String toString() {
        return pole + ": " + komunikat;
    }
}

class WalidacjaFormularzaException extends Exception {
    private List<BladWalidacji> bledy;

    WalidacjaFormularzaException(List<BladWalidacji> bledy) {
        super("Formularz zawiera " + bledy.size() + " błędów");
        this.bledy = bledy;
    }

    List<BladWalidacji> getBledy() { return bledy; }
}

class FormularzRejestracji {
    private String imie;
    private String email;
    private String haslo;
    private int wiek;

    FormularzRejestracji(String imie, String email, String haslo, int wiek) {
        this.imie = imie;
        this.email = email;
        this.haslo = haslo;
        this.wiek = wiek;
    }

    void waliduj() throws WalidacjaFormularzaException {
        List<BladWalidacji> bledy = new ArrayList<>();

        if (imie == null || imie.trim().isEmpty()) {
            bledy.add(new BladWalidacji("imie", "Imię jest wymagane"));
        } else if (imie.length() < 2) {
            bledy.add(new BladWalidacji("imie", "Imię musi mieć min. 2 znaki"));
        }

        if (email == null || !email.contains("@") || !email.contains(".")) {
            bledy.add(new BladWalidacji("email", "Nieprawidłowy format email"));
        }

        if (haslo == null || haslo.length() < 8) {
            bledy.add(new BladWalidacji("haslo", "Hasło musi mieć min. 8 znaków"));
        } else {
            if (!haslo.matches(".*[A-Z].*")) {
                bledy.add(new BladWalidacji("haslo", "Hasło musi zawierać wielką literę"));
            }
            if (!haslo.matches(".*[0-9].*")) {
                bledy.add(new BladWalidacji("haslo", "Hasło musi zawierać cyfrę"));
            }
        }

        if (wiek < 13 || wiek > 120) {
            bledy.add(new BladWalidacji("wiek", "Wiek musi być między 13 a 120"));
        }

        if (!bledy.isEmpty()) {
            throw new WalidacjaFormularzaException(bledy);
        }
    }

    @Override
    public String toString() {
        return String.format("Formularz{imie='%s', email='%s', wiek=%d}", imie, email, wiek);
    }
}

public class WalidacjaFormularza {
    static void rejestruj(FormularzRejestracji formularz) {
        System.out.println("Próba rejestracji: " + formularz);
        try {
            formularz.waliduj();
            System.out.println("✅ Rejestracja udana!\n");
        } catch (WalidacjaFormularzaException e) {
            System.out.println("❌ " + e.getMessage() + ":");
            for (BladWalidacji b : e.getBledy()) {
                System.out.println("   • " + b);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        rejestruj(new FormularzRejestracji("", "zly-email", "abc", 5));
        rejestruj(new FormularzRejestracji("Jan", "jan@mail.com", "haslo123", 25));
        rejestruj(new FormularzRejestracji("Anna", "anna@mail.com", "Silne1Haslo", 30));
    }
}
```
