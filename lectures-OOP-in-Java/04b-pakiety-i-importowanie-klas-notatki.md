# Pakiety i importowanie klas w Javie — notatki na podstawie kursjava.com

Źródło inspiracji: https://kursjava.com/klasy/pakiety-i-importowanie-klas/

Niniejszy materiał syntetyzuje zagadnienia związane z pakietami i mechanizmem importowania klas w Javie. Treść została opracowana własnymi słowami, rozszerzona o przykłady zgodne ze strukturą katalogu laboratoria/lab2.


## 1. Po co są pakiety?
Pakiety porządkują kod i tworzą przestrzeń nazw dla klas. Dzięki nim:
- unikamy konfliktów nazw (np. dwie klasy Utils w różnych pakietach),
- grupujemy powiązane tematy (np. geometry, math, text),
- kontrolujemy widoczność elementów (package‑private vs public). 

Konwencja nazewnicza: małe litery, zwykle odwrócona domena, np. pl.umg.projekt.modul. Struktura katalogów odpowiada nazwie pakietu (kropki → podkatalogi).

Przykład deklaracji na górze pliku źródłowego:
```java
package laboratoria.lab2.math;
```

Plik powinien znaleźć się pod ścieżką: laboratoria/lab2/math/NazwaKlasy.java


## 2. Importowanie klas — rodzaje i kiedy używać
Import pozwala używać krótkich nazw klas zamiast pełnych kwalifikacji (FQN — Fully Qualified Name).

Najczęstsze warianty:
1) Import pojedynczej klasy:
```java
import laboratoria.lab2.math.BasicMath; // tylko ta klasa
```

2) Import wieloznaczny (z „gwiazdką”):
```java
import laboratoria.lab2.geometry.*; // Point, DistanceUtils, itp.
```
Uwaga: gwiazdka dotyczy tylko danego pakietu — nie obejmuje podpakietów.

3) Static import (metody/pola statyczne bez nazwy klasy):
```java
import static laboratoria.lab2.math.BasicMath.add;
import static laboratoria.lab2.math.BasicMath.*; // add, sub, mul, div, ...
```

4) Bez importu — użycie pełnej nazwy:
```java
java.util.List<String> list = new java.util.ArrayList<>();
```

Import nie jest potrzebny, gdy:
- klasa jest w tym samym pakiecie,
- klasa pochodzi z java.lang (np. String, Math).


## 3. Przykłady praktyczne (spójne z folderem laboratoria/lab2)

A) Import pojedynczych klas
```java
import laboratoria.lab2.math.BasicMath;
import laboratoria.lab2.geometry.Point;

public class DemoA {
    public static void main(String[] args) {
        int s = BasicMath.add(2, 3);
        Point p = new Point(1, 2);
        System.out.println("s = " + s + ", p = " + p);
    }
}
```

B) Import z gwiazdką (pakiet, bez podpakietów)
```java
import laboratoria.lab2.geometry.*; // Point, DistanceUtils

public class DemoB {
    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        double d = DistanceUtils.distance(a, b);
        System.out.println("d = " + d); // 5.0
    }
}
```

C) Static import metod
```java
import static laboratoria.lab2.math.BasicMath.*; // add, sub, mul, div
import static laboratoria.lab2.geometry.DistanceUtils.distance;
import laboratoria.lab2.geometry.Point;

public class DemoC {
    public static void main(String[] args) {
        int x = add(10, 5);   // zamiast BasicMath.add(10, 5)
        int y = mul(3, 7);
        System.out.println(x + ", " + y);

        Point p1 = new Point(0, 0);
        Point p2 = new Point(6, 8);
        System.out.println("dist = " + distance(p1, p2)); // 10.0
    }
}
```

D) Konflikt nazw (dwie klasy Utils w różnych pakietach)
W Javie nie ma aliasów importów dla klas. Jeśli wystąpi konflikt nazw, użyj pełnej nazwy kwalifikowanej (FQN) przynajmniej dla jednej z klas:
```java
public class DemoD {
    public static void main(String[] args) {
        String aName = laboratoria.lab2.a.utils.Utils.name();
        String bName = laboratoria.lab2.b.utils.Utils.name();
        System.out.println(aName);
        System.out.println(bName);
    }
}
```

E) Import a podpakiety
```java
import laboratoria.lab2.a.*; // nie importuje laboratoria.lab2.a.utils
// Aby korzystać z klasy Utils w podpakiecie:
import laboratoria.lab2.a.utils.Utils;
```


## 4. Najczęstsze błędy i pułapki
- Niezgodność package ↔ ścieżka katalogów (błąd kompilacji). 
- Przekonanie, że import z gwiazdką obejmuje podpakiety — nie obejmuje.
- Konflikty nazw przy wielu bibliotekach: bez aliasów; używamy FQN.
- Zbyt agresywne wildcardy utrudniają analizę zależności w dużych projektach.


## 5. Dobre praktyki
- Trzymaj strukturę katalogów w zgodzie z deklaracjami package.
- W kodzie bibliotecznym preferuj jawne importy nad wildcardami.
- Grupuj klasy według odpowiedzialności (math, geometry, text, …).
- Dokumentuj nietypowe decyzje (np. FQN z powodu konfliktów nazw).


## 6. Mini‑ćwiczenia
1) Dodaj klasę TextStats w pakiecie laboratoria.lab2.text z metodą countWords(String s). Użyj jej w małej klasie demo.
2) Utwórz dwa pakiety z klasą Helper w każdym i pokaż, jak odwołać się do obu przy pomocy FQN.
3) Dla praktyki ze static import przepnij wywołania BasicMath.add/sub/mul/div na formę bez nazwy klasy.


## 7. FAQ
- Czy muszę wpisywać package na górze każdego pliku? Tak, jeśli chcesz używać pakietów (unikaj „unnamed package”).
- Czy import java.util.*; importuje java.util.concurrent.*? Nie — podpakiety nie są objęte wildcardem.
- Czy public wystarczy, by klasa była widoczna wszędzie? W modelu classpath tak, ale w projektach modułowych dodatkowo pakiet musi być eksportowany w module-info.java.

---
Notatki powstały na podstawie materiału z kursjava.com; zachęcam do lektury źródła dla szerszego kontekstu i dodatkowych przykładów.