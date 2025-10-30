### Zadania do wykładów

#### Zadanie nr 1 (rozgrzewka przed OOP i praktyka z Markdownem) - proszę przesłać do 06.10.2025 r.
  - z pomocą dowolnego języka programowania napisz program, który po uruchomieniu:  
    - wyświetli w kolejności alfabetycznej nazwy czterech filarów OOP,  
    - wyświetli w kolejności losowej nazwy czterech filarów OOP,  
    - w obu ww. przypadkach wskazane jest użycie wbudowanych w dany język programowania metod,  
    - w pliku sprawozdania "wykładowego" proszę umieścić zrzut ekranu z działającego programu.  

#### Zadanie nr 2 (klasy i obiekty) - proszę przesłać do 13.10.2025 r.
1). Czym różni się klasa od obiektu?
2). Z czego składają się klasy?
3). Jak utworzyć nowy obiekt klasy?
4). Czy poniższa klasa jest poprawna?  

```java
public class Pytanie {
    public static void main(String[] args) {
        System.out.println( "Witaj!" );
    }
}
```

5). Co zostanie wypisane na ekran?

```java
public class Punkt {
    private int x , y ;
    
    public void ustawX( int wartoscX) {
        x = wartoscX;
    }
    
    public void ustawY( int wartoscY) {
        y = wartoscY;
    }
    
    public String toString() {
        return "X, Y: " + x + ", " + y ;
    }
    
    public static void main(String[] args) {
        Punkt a = new Punkt();
        Punkt b = new Punkt();
        a.ustawX( 10 );
        a.ustawY( 20 );
        b.ustawX( 0 );
        b.ustawY( 5 );
        System.out.println(a);
        System.out.println(b);
    }
}
```

6). Napisz klasę `Osoba`, która będzie zawierała:
   1. Trzy pola: `wiek`, `imie`, `nazwisko`. Użyj odpowiednich typów.
   2. Trzy metody, w których będziesz ustawiał wartości pól klasy: `ustawWiek`, `ustawImie`,
       `ustawNazwisko`. Argumenty tych metod powinny nazywać się **wartoscWieku** ,
       **imieOsoby** , **nazwiskoOsoby**.
   3. Metodę `toString`, która będzie zwracała informacje o imieniu, nazwisko, oraz wieku
       osoby.
   4. Metodę main, w której utworzysz jeden obiekt klasy Osoba i nadasz mu wartości
       za pomocą metod `ustawWiek`, `ustawImie`, oraz `ustawNazwisko`, a następnie, za pomocą
       `System.out.println`, wypiszesz utworzony obiekt typu `Osoba` na ekran.


#### Zadanie nr 3 (importy i pakiety) - proszę przesłać do 20.10.2025 r.

1) Dodaj nową klasę w pakiecie `laboratoria.lab2.text`, np. `TextStats` z metodą `countWords(String s)`. Pokaż import i użycie w osobnej klasie demonstracyjnej.  Kod klasy oraz wynik działania programu umieść w sprawozdaniu z wykładu.  
2) Utwórz dwa pakiety z klasą `Helper` w każdym pakiecie. Pokaż, jak odwołać się do obu bez aliasów (pełne nazwy kwalifikowane). Kody klas oraz wynik działania programu(ów) umieść w sprawozdaniu z wykładu.  
3) Uruchom zadania od 1 do 5 z folderu `laboratoria/lab2/zadania-1_5-do-działu-o-modułach-i-pakietach`, ale przedtem zmień nazwę każdego pliku, aby była w postaci `Zad2_StaticImport_Nazwisko.java`.  Wyniki działania programów umieść w sprawozdaniu z wykładu.  


#### Zadanie nr 4 (pola klas, modyfikatory dostępu) - proszę przesłać do 29.10.2025 r.


1. Jakie powinny być nazwy getterów i setterów następujących pól?  
```java
    String tytul;
    double mianownik;
    boolean uzytkownikZalogowany;
```
2. Czym jest i do czego służy this?  
3. Kiedy możemy zobaczyć błąd Null Pointer Exception?
4. Jak uchronić się przed potencjalnym błędem Null Pointer Exception?
5. Co zostanie wypisane na ekranie w poniższym programie?
```java
    public class PytanieZagadka {
      private int liczba;
      
      public void setLiczba(int liczba) {
        liczba = liczba;
      }
      
      public int getLiczba() {
        return liczba;
      }
      
      public static void main(String[] args) {
        PytanieZagadka o = new PytanieZagadka();
        o.setLiczba(100);
        System.out.println("Liczba wynosi: " + o.getLiczba());
      }
    }
```
6. Jaki będzie efekt próby kompilacji poniższych klas?
```java
        public class PytanieMetody {
          public void setNazwa(String nazwa) {
            this.nazwa = nazwa;
          }
          
          public String getNazwa() {
            return nazwa;
          }
          
          private String nazwa;
        }
```
i
```java
        
        public class PytaniePola {
          private int x = y;
          private int y = 0;
        }
```
7. Co zostanie wypisane na ekranie w poniższym programie?
```java
    public class PytanieObiekty {
      private String nazwa;
      public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
      }
      public String getNazwa() {
        return nazwa;
      }
      public static void main(String[] args) {
        PytanieObiekty o1 = new PytanieObiekty();
        PytanieObiekty o2 = new PytanieObiekty();
        o1.setNazwa("Pewna nazwa");
        o2.setNazwa("Inna nazwa");
        System.out.println(o1.getNazwa());
        System.out.println(o2.getNazwa());
      }
    }
```
8. Co zostanie wypisane na ekranie w poniższym programie?
```java
    public class PytanieWartosci {
      private int liczba;
      private boolean wartoscLogiczna;
      private String nazwa;
      public String toString() {
        return liczba + " " + wartoscLogiczna + " " + nazwa;
      }
      public static void main(String[] args) {
        PytanieWartosci o = new PytanieWartosci();
        System.out.println(o);
      }
    }
```

9. Co zostanie wypisane na ekranie w poniższym programie?
```java
    public class UzycieWartosci {
      private int liczba;
      private String nazwa;
      private int getLiczba() {
        return liczba;
      }
      private String getNazwa() {
        return nazwa;
      }
      public static void main(String[] args) {
        UzycieWartosci o = new UzycieWartosci();
        System.out.println(o.getLiczba());
        System.out.println(o.getNazwa().toUpperCase());
      }
    }
```  


#### Zadanie nr 5 (konstruktory) - proszę przesłać do 05.11.2025 r.
1. Do czego służą konstruktory?
2. Czy każda klasa posiada konstruktor?
3. Jak zdefiniować konstruktor?
4. Czym jest konstruktor domyślny i kiedy jest definiowany?
5. Czy klasa może mieć wiele konstruktorów?
6. Jak z jednego konstruktora wywołać inny konstruktor?
7. Jaki warunek musi spełniać wywołanie jednego konstruktora z drugiego konstruktora?
8. Jaki będzie wynik kompilacji i uruchomienia poniższego kodu?

```java
public class PytanieKonstruktor {
  private int x;
  public PytanieKonstruktor(int x) {
    this.x = x;
  }
  public void setX(int x) {
    this.x = x;
  }
  public String toString() {
    return "x = " + x;
  }
  public static void main(String[] args) {
    PytanieKonstruktor o = new PytanieKonstruktor();
    System.out.println(o);
  }
}
```

9. Ile konstruktorów posiada poniższa klasa?

```java
public class PytanieKonstruktor {
  private int pewnePole;
}
```

10. Czy poniższa klasa ma domyślny konstruktor?

```java
public class PytanieKonstruktor {
  private int pewnePole;
    
  public PytanieKonstruktor() {
  }
}
```

11. Jaki będzie wynik kompilacji i uruchomienia poniższej klasy?

```java
public class PytanieKonstruktor {
  private final int liczba;
  private final String nazwa;
  public PytanieKonstruktor(int liczba) {
    this.liczba = liczba;
  }
  public PytanieKonstruktor(int liczba, String nazwa) {
    this.liczba = liczba;
    this.nazwa = nazwa;
  }
  public static void main(String[] args) {
    PytanieKonstruktor o = new PytanieKonstruktor(10, "Tekst");
  }
}
```

12. Jaki będzie wynik kompilacji i uruchomienia poniższej klasy?

```java
public class PytanieKonstruktor {
  private final int liczba;
  public PytanieKonstruktor() {
    System.out.println("Wywolano konstruktor bez argumentow.");
    this(0);
  }
  public PytanieKonstruktor(int liczba) {
    this.liczba = liczba;
  }
  public static void main(String[] args) {
    PytanieKonstruktor o = new PytanieKonstruktor(10);
  }
}
```

13. Jaki będzie wynik kompilacji i uruchomienia poniższej klasy?

```java
public class PytanieKonstruktor {
  private final int liczba;
  private final String nazwa;
  public PytanieKonstruktor(int liczba) {
    this(liczba, "brak nazwy");
    this.liczba = liczba;
  }
  public PytanieKonstruktor(int liczba, String nazwa) {
    this.liczba = liczba;
    this.nazwa = nazwa;
  }
  public static void main(String[] args) {
    PytanieKonstruktor o = new PytanieKonstruktor(10, "Tekst");
  }
}
```

14. Jaki będzie wynik kompilacji i uruchomienia poniższej klasy?

```java
public class PytanieKonstruktor {
  private int x;
  public void PytanieKonstruktor(int x) {
    this.x = x;
  }
  public String toString() {
    return "x = " + x;
  }
  public static void main(String[] args) {
    PytanieKonstruktor o = new PytanieKonstruktor();
    System.out.println(o);
  }
}
```

15. Jaki będzie wynik kompilacji i uruchomienia poniższej klasy?

```java
public class PytanieKonstruktor {
  private int x;
  public Pytaniekonstruktor(int x) {
    this.x = x;
  }
  public String toString() {
    return "x = " + x;
  }
  public static void main(String[] args) {
    PytanieKonstruktor o = new PytanieKonstruktor();
    System.out.println(o);
  }
}
```

16. Jaki będzie wynik kompilacji i uruchomienia poniższej klasy?

```java
public class PytanieKonstruktor {
  private int x;
  public PytanieKonstruktor() {
    x = 10;
  }
  public PytanieKonstruktor(int x) {
    x = x;
  }
  public String toString() {
    return "x = " + x;
  }
  public static void main(String[] args) {
    PytanieKonstruktor o1 = new PytanieKonstruktor();
    PytanieKonstruktor o2 = new PytanieKonstruktor(20);
    System.out.println(o1);
    System.out.println(o2);
  }
}
```

#### Zadanie nr 6 (dziedziczenie i polimorfizm) - proszę przesłać do 14.11.2025 r.

1) Wyjaśnij pojęcia: klasa bazowa (nadrzędna), klasa pochodna (podrzędna), słowo kluczowe `extends`. Podaj własny przykład zdania typu „X jest Y”, które ilustruje relację dziedziczenia.

2) Co zostanie wypisane na ekranie?
```java
class Osoba {
  public String imie;
  public String nazwisko;
  public String toString() {
    return "Osoba " + imie + " " + nazwisko;
  }
}

class Pracownik extends Osoba {
  public int numerId;
}

public class PytanieDziedziczenie1 {
  public static void main(String[] args) {
    Pracownik p = new Pracownik();
    p.imie = "Anna";
    p.nazwisko = "Nowak";
    p.numerId = 42;
    System.out.println(p);
  }
}
```

3) Czy poniższy kod skompiluje się? Jeśli tak, co wypisze program? Zwróć uwagę na polimorfizm (upcasting).
```java
class Osoba {
  public String imie, nazwisko;
  public String toString() { return imie + " " + nazwisko; }
}

class Pracownik extends Osoba { public int nr; }

public class PytaniePolimorfizm1 {
  static void przywitaj(Osoba o) {
    System.out.println("Witaj, " + o);
  }
  
  public static void main(String[] args) {
    Pracownik p = new Pracownik();
    p.imie = "Jan"; p.nazwisko = "Kowalski"; p.nr = 7;
    przywitaj(p); // przekazujemy Pracownika tam, gdzie oczekiwana jest Osoba
  }
}
```

4) Nadpisywanie metod a `super`: jaki będzie wynik uruchomienia?
```java
class Osoba {
  public String imie, nazwisko;
  public String toString() { 
  return "Osoba(" + imie + " " + nazwisko + ")"; 
  }
}

class Pracownik extends Osoba {
  public int id;
  public String toString() {
    return super.toString() + ", id=" + id;
  }
}

public class PytanieOverride1 {
  public static void main(String[] args) {
    Pracownik p = new Pracownik();
    p.imie = "Ola"; p.nazwisko = "Lis"; p.id = 101;
    System.out.println(p);
  }
}
```

5) Kolejność wywołań konstruktorów w dziedziczeniu – co zostanie wypisane?
```java
class Baza {
  Baza() { 
  System.out.println("konstruktor Baza"); 
  }
}

class Pochodna extends Baza {
  Pochodna() { System.out.println("konstruktor Pochodna"); }
}

public class PytanieKonstruktory1 {
  public static void main(String[] args) {
    new Pochodna();
  }
}
```

6) Czy poniższy kod się skompiluje? Jeśli nie – dlaczego? Jak naprawić?
```java
class Baza {
  private final int x;
  Baza(int x) { 
  this.x = x; 
  }
}

class Pochodna extends Baza {
  Pochodna() { /* domyślnie wołane jest super() */ }
}

public class PytanieKonstruktory2 { 
  public static void main(String[] args) { 
    new Pochodna(); } 
}
```

7) Dostęp do pól prywatnych a dziedziczenie: jaki będzie wynik kompilacji?
```java
class Baza {
  private int sekretny = 123;
}

class Pochodna extends Baza {
  public void pokaz() {
    // System.out.println(sekretny); // próbujemy odwołać się do pola z Baza
  }
}
public class PytanieDostep1 { 
public static void main(String[] args) { 
  new Pochodna().pokaz(); 
  } 
}
```
Czy odkomentowanie linii spowoduje błąd? Wyjaśnij różnicę między `private`, `protected` i `public` w kontekście dziedziczenia.

8) `final` w dziedziczeniu: oceń poprawność i wyjaśnij.
```java
class Baza { 
  public final void f() {} 
}

class Pochodna extends Baza {
  // public void f() {} // próba
}

final class NieDoDziedziczenia {}
// class X extends NieDoDziedziczenia {} // próba
```
Czy zakomentowane linie można odkomentować, by kod się kompilował? Dlaczego?

9) Klasy i metody abstrakcyjne: co jest nie tak? Jak to naprawić?
```java
abstract class Zwierze { 
  abstract void dajGlos(); 
}

class Kot extends Zwierze { /* brak dajGlos */ }

public class PytanieAbstrakcja1 {
  public static void main(String[] args) {
    // Zwierze z = new Zwierze();
    Zwierze k = new Kot();
    // k.dajGlos();
  }
}
```
Wskaż, które linie powodują błąd kompilacji i dlaczego. Zaproponuj poprawną implementację.

10) Rzutowanie (downcasting) i `instanceof`: co zostanie wypisane, a czy dojdzie do wyjątku?
```java
class Osoba { 
  public String toString(){ 
    return "Osoba"; 
  } 
}

class Pracownik extends Osoba { 
  public String toString(){ 
  return "Pracownik"; 
  } 
}

public class PytanieRzutowanie1 {
  public static void main(String[] args) {
    Osoba a = new Pracownik();
    System.out.println(a); // A
    if (a instanceof Pracownik) {
      Pracownik p = (Pracownik) a;
      System.out.println(p); // B
    }
    Osoba b = new Osoba();
    Pracownik q = (Pracownik) b; // C
    System.out.println(q);
  }
}
```
Oznacz linie A, B, C i opisz: co zostanie wypisane, a gdzie (jeśli w ogóle) wystąpi wyjątek i jaki.
