### Konstruktory w Java — Notatka z przykładami 

---

### Spis treści
- [Czym jest konstruktor](#czym-jest-konstruktor)
- [Sygnatura konstruktora](#sygnatura-konstruktora)
- [Konstruktor domyślny (no-arg)](#konstruktor-domyślny-no-arg)
- [Przeciążanie konstruktorów](#przeciążanie-konstruktorów)
- [Łańcuchowanie: this(...) i super(...)](#łańcuchowanie-this-i-super)
- [Modyfikatory dostępu konstruktorów](#modyfikatory-dostępu-konstruktorów)
- [Walidacja w konstruktorze i rzucanie wyjątków](#walidacja-w-konstruktorze-i-rzucanie-wyjątków)
- [Konstruktor kopiujący i defensywne kopiowanie](#konstruktor-kopiujący-i-defensywne-kopiowanie)
- [Klasy niemutowalne a konstruktor](#klasy-niemutowalne-a-konstruktor)
- [Statyczne metody wytwórcze vs. konstruktor](#statyczne-metody-wytwórcze-vs-konstruktor)
- [Dziedziczenie a konstruktory](#dziedziczenie-a-konstruktory)
- [Rekordy (record) w Java](#rekordy-record-w-java)
- [Dobre praktyki](#dobre-praktyki)
- [Najczęstsze błędy](#najczęstsze-błędy)
- [Mini-FAQ](#mini-faq)
- [Ćwiczenia](#ćwiczenia)

---

### Czym jest konstruktor
Konstruktor to specjalna metoda wywoływana podczas tworzenia obiektu za pomocą `new`. Służy do inicjalizacji stanu obiektu (ustawiania pól na wartości początkowe) i może wykonywać walidację.

- Nie zwraca wartości (nawet `void`).
- Nosi nazwę klasy.
- Może być przeciążany (wiele konstruktorów o różnych listach parametrów).

Przykład:
```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

---

### Sygnatura konstruktora
Sygnatura obejmuje nazwę (identyczną z klasą) oraz listę parametrów.

```java
public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}
```

Uwaga: Nie można określić typu zwracanego. Modyfikatory dopuszczalne to m.in. `public`, `protected`, pakietowy (brak słowa kluczowego), `private`.

---

### Konstruktor domyślny (no-arg)
Jeśli nie zdefiniujesz żadnego konstruktora, kompilator doda publiczny konstruktor bezargumentowy.

- Znika on automatycznie, gdy dodasz jakikolwiek własny konstruktor.
- Jeśli chcesz mieć i no-arg, i parametryczny, zdefiniuj oba.

```java
public class BankAccount {
    private String owner;
    private double balance;

    // No-arg (jawny)
    public BankAccount() {
        this.owner = "Unknown";
        this.balance = 0.0;
    }

    // Parametryczny
    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }
}
```

---

### Przeciążanie konstruktorów
Możesz tworzyć wiele konstruktorów różniących się listą parametrów, a wspólną logikę dziedziczyć przez łańcuchowanie `this(...)`.

```java
public class Course {
    private final String title;
    private final int ects;
    private final String[] tags;

    public Course(String title, int ects) {
        this(title, ects, new String[0]);
    }

    public Course(String title, int ects, String[] tags) {
        this.title = title;
        this.ects = ects;
        this.tags = tags != null ? java.util.Arrays.copyOf(tags, tags.length) : new String[0];
    }
}
```

---

### Łańcuchowanie: this(...) i super(...)
- `this(...)` wywołuje inny konstruktor tej samej klasy (musi być pierwszą instrukcją).
- `super(...)` wywołuje konstruktor klasy bazowej (również pierwsza instrukcja). Jeśli go nie podasz, wywołane zostanie domyślne `super()`.

```java
class Base {
    protected final String id;
    public Base(String id) { this.id = id; }
}

class Derived extends Base {
    private final int level;

    public Derived(String id) {
        this(id, 0); // this(...)
    }

    public Derived(String id, int level) {
        super(id);   // super(...)
        this.level = level;
    }
}
```

---

### Modyfikatory dostępu konstruktorów
- `public` — każdy może tworzyć obiekt.
- Pakietowy (brak słowa kluczowego) — tylko w tym samym pakiecie.
- `protected` — dostęp w pakiecie i w podklasach.
- `private` — ogranicza tworzenie obiektów (np. wzorzec Singleton lub statyczne metody wytwórcze).

```java
public class Utility {
    private Utility() { /* zapobieganie instancjonowaniu */ }
}
```

---

### Walidacja w konstruktorze i rzucanie wyjątków
Waliduj parametry i rzucaj sensowne wyjątki (np. `IllegalArgumentException`, `NullPointerException`).

```java
public class Employee {
    private final String name;
    private final double salary;

    public Employee(String name, double salary) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("salary must be >= 0");
        }
        this.name = name;
        this.salary = salary;
    }
}
```

---

### Konstruktor kopiujący i defensywne kopiowanie
Przy polach-referencjach do kolekcji/tablic stosuj defensywne kopiowanie, aby uniknąć współdzielenia stanu między obiektami.

```java
import java.util.Arrays;

public class Course {
    private final String title;
    private final int ects;
    private final String[] tags;

    // Konstruktor parametryczny (defensywne kopiowanie)
    public Course(String title, int ects, String[] tags) {
        this.title = title;
        this.ects = ects;
        this.tags = tags != null ? Arrays.copyOf(tags, tags.length) : new String[0];
    }

    // Konstruktor kopiujący
    public Course(Course other) {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }
        this.title = other.title;
        this.ects = other.ects;
        this.tags = Arrays.copyOf(other.tags, other.tags.length);
    }
}
```

---

### Klasy niemutowalne a konstruktor
W klasach niemutowalnych pola są `final`, brak setterów, a konstruktor nadaje ostateczne wartości. Unikaj udostępniania wewnętrznych, modyfikowalnych struktur bez kopii.

```java
public final class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
```

---

### Statyczne metody wytwórcze vs. konstruktor
Czasem lepiej użyć statycznej metody wytwórczej:
- Może mieć opisową nazwę (`from`, `of`, `withDefault`).
- Może zwracać podklasę lub obiekt z puli.
- Może cache’ować instancje.

```java
public class Color {
    private final int r, g, b;
    private Color(int r, int g, int b) { this.r = r; this.g = g; this.b = b; }

    public static Color ofRgb(int r, int g, int b) {
        return new Color(r, g, b);
    }
}
```

---

### Dziedziczenie a konstruktory
Konstruktor podklasy zawsze (jawnie lub niejawnie) wywołuje konstruktor nadklasy. Jeśli nadklasa nie ma konstruktora no-arg, podklasa musi jawnie wywołać `super(...)`.

```java
class PersonBase {
    protected final String name;
    public PersonBase(String name) { this.name = name; }
}

class Student extends PersonBase {
    private final String indexNo;
    public Student(String name, String indexNo) {
        super(name);
        this.indexNo = indexNo;
    }
}
```

---

### Rekordy (record) w Java
`record` automatycznie generuje tzw. kanoniczny konstruktor oraz `equals`, `hashCode`, `toString`.

```java
public record Point(int x, int y) {
    // Możesz doprecyzować walidację w konstruktorze kanonicznym:
    public Point {
        if (x < 0 || y < 0) throw new IllegalArgumentException("coords must be >= 0");
    }
}
```

---

### Dobre praktyki
- Inicjalizuj wszystkie wymagane pola w konstruktorze.
- Waliduj argumenty jak najbliżej miejsca użycia (tu: w konstruktorze).
- Do wspólnej logiki używaj łańcuchowania `this(...)`.
- Stosuj defensywne kopiowanie dla tablic/kolekcji przekazywanych z zewnątrz.
- Nie wykonuj w konstruktorze operacji, które mogą łatwo się nie powieść (I/O, sieć) – rozważ metody inicjalizacyjne/fabryczne.
- Dla klas niemutowalnych używaj `final` i nie ujawniaj wewnętrznych referencji.

---

### Najczęstsze błędy
- Brak no-arg po dodaniu konstruktora parametrycznego, gdy framework go wymaga.
- Niewywołanie `super(...)` w podklasie, gdy nadklasa nie ma no-arg.
- Współdzielenie tablic/kolekcji zamiast defensywnego kopiowania.
- Walidacja zbyt późno (pozostawienie obiektu w nieprawidłowym stanie).

---

### Mini-FAQ
- Czy konstruktor może zwracać wartość? — Nie, nie ma typu zwracanego.
- Czy można wywołać metodę w konstruktorze? — Tak, ale uważaj na metody wirtualne (mogą działać na nie w pełni zainicjalizowanym obiekcie).
- Czy można przeciążać konstruktory? — Tak, to powszechny wzorzec.
- Czy można mieć prywatny konstruktor? — Tak (np. Singleton, klasy narzędziowe, metody fabryczne).

---

### Ćwiczenia
1) Napisz klasę `Person` z konstruktorami: no-arg (ustawia domyślne wartości) i parametrycznym (`name`, `age`). Dodaj walidację.
```java
public class Person {
    private String name;
    private int age;

    public Person() {
        this("Unknown", 0);
    }

    public Person(String name, int age) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name!");
        if (age < 0) throw new IllegalArgumentException("age!");
        this.name = name;
        this.age = age;
    }
}
```

2) Zaimplementuj klasę `Rectangle` z przeciążonymi konstruktorami: `(w, h)` oraz `square(side)` jako statyczna metoda wytwórcza.
```java
public class Rectangle {
    private final double w, h;
    public Rectangle(double w, double h) {
        if (w <= 0 || h <= 0) throw new IllegalArgumentException("dims!");
        this.w = w; this.h = h;
    }
    public static Rectangle square(double side) { return new Rectangle(side, side); }
}
```

3) Dodaj do klasy `Course` konstruktor kopiujący i upewnij się, że tablice/kolekcje są kopiowane defensywnie.
```java
public class Course {
    private final String title;
    private final int ects;
    private final String[] tags;

    public Course(String title, int ects, String[] tags) {
        this.title = title;
        this.ects = ects;
        this.tags = tags != null ? java.util.Arrays.copyOf(tags, tags.length) : new String[0];
    }

    public Course(Course other) {
        if (other == null) throw new IllegalArgumentException("other!");
        this.title = other.title;
        this.ects = other.ects;
        this.tags = java.util.Arrays.copyOf(other.tags, other.tags.length);
    }
}
```
