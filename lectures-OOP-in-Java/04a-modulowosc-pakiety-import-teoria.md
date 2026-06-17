# Modułowość. Pakiety. Mechanizm importowania klas (Java)

Dokument stanowi krótkie kompendium wiedzy potrzebnej do pracy z większymi projektami w Javie. Zawiera teorię, praktyczne wskazówki oraz przykłady oparte na kodzie z folderu `laboratoria/lab2`.


## 1. Pakiety (packages)
Pakiet to logiczna przestrzeń nazw dla klas. Pozwala porządkować kod, zapobiegać konfliktom nazw i kontrolować widoczność.

- Deklaracja pakietu zawsze jest w pierwszej linii pliku źródłowego:
  ```java
  package laboratoria.lab2.math;
  ```
- Struktura katalogów musi odpowiadać nazwie pakietu (kropki → katalogi):
  - `laboratoria.lab2.math.BasicMath` → plik: `laboratoria/lab2/math/BasicMath.java`
- Konwencje nazewnicze:
  - małe litery, nazwa zaczyna się często od odwróconej domeny: `pl.umg.projekt.modul`
  - jednoznaczne i opisowe nazwy pakietów

Widoczność a pakiety:
- `public` — klasa/metoda/pole widoczne w całej aplikacji.
- brak modyfikatora (tzw. package-private) — widoczne tylko wewnątrz tego samego pakietu.
- `protected` — dla dziedziczenia; poza zakresem tego skrótu, ale warte zapamiętania.

Korzyści z pakietów:
- porządek i czytelność kodu,
- brak konfliktów nazw między pakietami (np. dwie klasy `Utils` w różnych pakietach),
- możliwość grupowania powiązanych tematów (np. `geometry`, `math`, `text`).


## 2. Mechanizm importowania klas
Import jest skrótem składniowym umożliwiającym używanie krótkich nazw klas zamiast pełnych kwalifikacji (FQN — Fully Qualified Name).

Rodzaje importów:
1) Import pojedynczej klasy:
   ```java
   import laboratoria.lab2.math.BasicMath; // tylko ta klasa
   ```
2) Import „z gwiazdką” (on-demand):
   ```java
   import laboratoria.lab2.geometry.*; // Point, DistanceUtils, itd.
   ```
   Uwaga: gwiazdka działa tylko na poziomie pakietu, nie obejmuje podpakietów.
3) Static import — import metod/pól statycznych bez podawania nazwy klasy:
   ```java
   import static laboratoria.lab2.math.BasicMath.add;
   import static laboratoria.lab2.math.BasicMath.*; // add, sub, mul, div, ...
   ```
4) Brak importu — użycie wprost pełnej nazwy klasy:
   ```java
   java.util.List<String> list = new java.util.ArrayList<>();
   ```

Kiedy import nie jest potrzebny:
- dla klas z tego samego pakietu,
- dla klas z pakietu `java.lang` (np. `String`, `Math`).

Potencjalne konflikty:
- Dwie klasy o tej samej nazwie z różnych pakietów — rozwiązanie: użyj FQN przynajmniej dla jednej z nich.


## 3. Modułowość (JPMS — Java Platform Module System)
Moduły wprowadzono w Javie 9. Moduł grupuje pakiety i jednoznacznie deklaruje zależności między modułami.

Cechy modułów:
- każdy moduł ma plik `module-info.java` w katalogu źródłowym modułu;
- definiuje, które pakiety są eksportowane (`exports`), i jakie moduły są wymagane (`requires`).

Przykładowy `module-info.java`:
```java
module pl.umg.lab2 {
    exports laboratoria.lab2.math;
    exports laboratoria.lab2.geometry;
    // requires inny.modul; // jeśli potrzebne
}
```

- `exports laboratoria.lab2.math;` — pakiet jest „publiczny” dla innych modułów.
- Jeśli pakiet nie jest eksportowany, jego publiczne klasy nie są widoczne poza modułem (public + nieeksportowany = w praktyce „wewnętrzny” w skali modułu).

Classpath vs module-path:
- tradycyjne projekty używają classpath (bez modułów),
- projekty modułowe kompiluje się i uruchamia z użyciem module-path i `module-info.java`.

W małych projektach edukacyjnych często wystarczy klasyczny układ z pakietami bez modułów. W większych — warto rozważyć JPMS dla lepszej enkapsulacji i kontroli zależności.


## 4. Przykłady (na bazie folderu laboratoria/lab2)

### 4.1. Import pojedynczych klas
Plik: `laboratoria/lab2/Zad1_DemoPakietow.java` (fragment ideowy)
```java
import laboratoria.lab2.math.BasicMath;
import laboratoria.lab2.geometry.Point;

public class Zad1_DemoPakietow {
    public static void main(String[] args) {
        int s = BasicMath.add(2, 3);
        Point p = new Point(1, 2);
        System.out.println("s = " + s + ", p = (" + p.x + ", " + p.y + ")");
    }
}
```

### 4.2. Wildcard import (pakiet, bez podpakietów)
```java
import laboratoria.lab2.geometry.*; // Point, DistanceUtils

public class PrzykladGeometry {
    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        double d = DistanceUtils.distance(a, b); // 5.0
        System.out.println("d = " + d);
    }
}
```

### 4.3. Static import metod
```java
import static laboratoria.lab2.math.BasicMath.*; // add, sub, mul, div

public class PrzykladStaticImport {
    public static void main(String[] args) {
        int x = add(10, 5);      // zamiast BasicMath.add(10, 5)
        int y = mul(3, 7);
        System.out.println(x + ", " + y);
    }
}
```

### 4.4. Konflikt nazw — dwie klasy Utils w różnych pakietach
Plik: `laboratoria/lab2/Zad3_KonfliktNazw.java` (idea)
```java
import laboratoria.lab2.a.utils.Utils;        // Utils A
import laboratoria.lab2.b.utils.Utils as BUtils; // (Uwaga: w Javie nie ma aliasów! — to pseudokod)

public class KonfliktNazw {
    public static void main(String[] args) {
        // W Javie należy użyć pełnych nazw kwalifikowanych,
        // gdy istnieje konflikt nazw:
        laboratoria.lab2.a.utils.Utils.print("A");
        laboratoria.lab2.b.utils.Utils.print("B");
    }
}
```
Wyjaśnienie: Java NIE wspiera aliasów importów. Jeśli masz dwie klasy o tej samej nazwie, użyj pełnej nazwy pakietu w miejscu użycia.

### 4.5. Import a podpakiety
Import z pakietu nie obejmuje jego podpakietów. Przykładowo:
```java
import laboratoria.lab2.a.*; // nie importuje laboratoria.lab2.a.utils
```
Aby użyć klasy z `laboratoria.lab2.a.utils.Utils`, musisz:
```java
import laboratoria.lab2.a.utils.Utils;
```


## 5. Dobre praktyki
- Trzymaj strukturę katalogów w zgodzie z deklaracjami `package`.
- Unikaj nadmiernego używania wildcard importów w bibliotekach publicznych — utrudniają analizę zależności.
- W przypadku konfliktów nazw używaj pełnych nazw kwalifikowanych (FQN).
- Grupuj klasy według odpowiedzialności (np. `geometry`, `math`, `text`).
- W projektach większych rozważ JPMS (moduły) dla lepszej enkapsulacji i kontroli zależności.


## 6. Szybkie FAQ
- Czy muszę zawsze pisać `package`? — Tak, jeśli chcesz korzystać z pakietów. Klasy „bez pakietu” lądują w tzw. unnamed package — to niezalecane w większych projektach.
- Czy `import java.util.*;` importuje `java.util.concurrent.*`? — Nie. Gwiazdka nie sięga do podpakietów.
- Czy `public` w klasie wystarczy, żeby była dostępna wszędzie? — Tak w modelu classpath, ale w projektach modułowych dodatkowo pakiet musi być `exports` w `module-info.java`.


---
Materiał uzupełnia zadania i rozwiązania w folderze `laboratoria/lab2` i może służyć jako „ściąga” podczas ćwiczeń.