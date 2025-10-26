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