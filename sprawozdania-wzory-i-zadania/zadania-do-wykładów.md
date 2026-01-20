### Zadania do wykładów

### Spis treści
1. [Zadanie nr 1 (rozgrzewka przed OOP i praktyka z Markdownem)](#zadanie-nr-1-rozgrzewka-przed-oop-i-praktyka-z-markdownem---proszę-przesłać-do-06102025-r)
2. [Zadanie nr 2 (klasy i obiekty)](#zadanie-nr-2-klasy-i-obiekty---proszę-przesłać-do-13102025-r)
3. [Zadanie nr 3 (importy i pakiety)](#zadanie-nr-3-importy-i-pakiety---proszę-przesłać-do-20102025-r)
4. [Zadanie nr 4 (pola klas, modyfikatory dostępu)](#zadanie-nr-4-pola-klas-modyfikatory-dostępu---proszę-przesłać-do-29102025-r)
5. [Zadanie nr 5 (konstruktory)](#zadanie-nr-5-konstruktory---proszę-przesłać-do-05112025-r)
6. [Zadanie nr 6 (dziedziczenie i polimorfizm)](#zadanie-nr-6-dziedziczenie-i-polimorfizm---proszę-przesłać-do-14112025-r)
7. [Zadanie nr 7 (interfejsy i klasy abstrakcyjne)](#zadanie-nr-7-interfejsy-i-klasy-abstrakcyjne---proszę-przesłać-do-30112025-r)
8. [Zadanie nr 8 (testowanie kodu)](#zadanie-nr-8-testowanie-kodu---proszę-przesłać-do-14122025-r)
9. [Zadanie nr 9 (wyjątki)](#zadanie-nr-9-wyjątki---proszę-przesłać-do-18012026-r)
10. [Zadanie nr 10 (referencje do obiektów)](#zadanie-nr-10-referencje-do-obiektów---proszę-przesłać-do-28012026-r)

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

#### Zadanie nr 7 (interfejsy i klasy abstrakcyjne) - proszę przesłać do 30.11.2025 r.


Pytania sprawdzające (odnieś się do materiału „08-interfejsy-i-klasy-abstrakcyjne.md”):

1) Kiedy użyć interfejsu, a kiedy klasy abstrakcyjnej? Podaj po 2–3 typowe przypadki i krótkie uzasadnienie. Wymień co najmniej dwie różnice semantyczne i dwie różnice techniczne między `interface` i `abstract class`.

2) Jakie są domyślne modyfikatory dla pól i metod w interfejsie? Dokończ zdania i wyjaśnij konsekwencje:
- „Pola w interfejsie są domyślnie … … … i …”
- „Metody bez ciała w interfejsie są domyślnie … …”
- „Czy w interfejsie można mieć metody z ciałem? Jeśli tak, to jakie (wymień rodzaje i od której wersji Javy)?”

3) Metody `default`, `static` i `private` w interfejsach:
- Jaka jest różnica między `default` a `static` w interfejsie?
- Po co wprowadzono prywatne metody w interfejsach (Java 9)? Podaj przykład sytuacji, w której są pomocne.

4) Konflikt metod domyślnych w wielu interfejsach – jak rozwiązać? Rozważ kod:
```java
interface A { default void f() { System.out.println("A.f"); } }
interface B { default void f() { System.out.println("B.f"); } }

class C implements A, B {
  // TODO: napraw kompilację i zdecyduj, którą wersję f() wywołać.
}
```
Wyjaśnij, dlaczego kompilator zgłasza konflikt i pokaż poprawną implementację klasy `C`.

5) Czy można tworzyć instancje klas abstrakcyjnych? Jak mimo to można używać typu abstrakcyjnego w kodzie klienta? Podaj przykład.

6) Czy klasa może dziedziczyć po wielu klasach w Javie? A czy może implementować wiele interfejsów? Wyjaśnij pojęcie „wielodziedziczenia typu” na przykładzie.

7) Słowo kluczowe `final` a abstrakcja i interfejsy: czy `final` ma sens na metodach interfejsu? Co oznacza `final` na klasie abstrakcyjnej i dlaczego to rzadkie połączenie? Podaj krótkie uzasadnienie.

8) Słowa kluczowe `abstract` i `private`: czy metoda może być jednocześnie `abstract` i `private`? Uzasadnij odpowiedź. A co z `abstract` i `static`?


Zadania programistyczne (wykonaj w Javie, dołącz kod i krótkie wyjaśnienia):

1) Interfejsy „czynności” – drukowanie i eksport
- Zdefiniuj interfejs `Drukowalne` z metodą `void drukuj()`.
- Zdefiniuj interfejs `Eksportowalne` z metodą `String eksportuj()` oraz metodą domyślną `default void zapiszDoPliku(String sciezka)`, która wypisze na konsolę symulację zapisu: `Zapis do pliku <sciezka>: <dane>`.
- Zdefiniuj interfejs `Raportowalne` rozszerzający oba powyższe.
- Zaimplementuj klasy `Raport` i `Faktura`, które spełniają `Raportowalne`. Pokaż w `main`, że można te obiekty traktować polimorficznie jako `Raportowalne` i wywołać `drukuj()` oraz `zapiszDoPliku()`.

2) Abstrakcyjna hierarchia „Figura” + interfejs „Skalowalne”
- Utwórz klasę abstrakcyjną `Figura` z polem `nazwa`, metodą abstrakcyjną `double pole()` i nieabstrakcyjną `String opis()` zwracającą np. `Figura(nazwa=...)`.
- Zaimplementuj klasy `Kolo(r)` i `Prostokat(a,b)`.
- Zdefiniuj interfejs `Skalowalne` z metodą `void skaluj(double k)`. Niech `Kolo` i `Prostokat` implementują ten interfejs (skalowanie zmienia odpowiednio promień lub boki).
- W `main` utwórz listę `Figura` z różnymi figurami, wypisz pola przed i po skalowaniu (polimorfizm + interfejs).

3) Konflikt metod domyślnych – rozwiąż świadomie
- Zdefiniuj `interface Loggable { default void log(String msg){ System.out.println("[LOG] "+msg); } }`
- Zdefiniuj `interface Auditable { default void log(String msg){ System.out.println("[AUDIT] "+msg); } }`
- Zaimplementuj klasę `AkcjaSystemowa` implementującą oba interfejsy. Rozwiąż konflikt nadpisując `log` i wybierając jawnie, którą domyślną implementację użyć (lub połącz obie). Pokaż działanie.


#### Zadanie nr 8 (testowanie kodu) - proszę przesłać do 14.12.2025 r.

1. Napisz testy oraz metodę, która odpowiada na pytanie, czy podana liczba jest parzysta.

2. Napisz testy oraz metodę, która przyjmuje liczbę całkowitą jako argument i zwraca:

    -1, jeżeli podana liczba jest ujemna,  
    0, jeżeli podana liczba jest równa 0,  
    1, jeżeli podana liczba jest dodatnia.


3. Napisz testy oraz metodę, która przyjmuje jako argument tablicę liczb oraz liczbę i zwraca indeks w tej tablicy, pod którym znajduje się liczba podana jako drugi argument.   
   Jeżeli podanej liczby nie ma w tablicy, metoda powinna zwrócić liczbę -1.  
   Przykłady:

    Dla argumentów { 1, 10, 200, 1000 }, 200 – metoda powinna zwrócić 2, ponieważ liczba 200 jest trzecim elementem podanej tablicy, a jej indeks to 2 (bo, jak na pewno pamiętamy, indeksy zaczynamy liczyć od 0).  
    Dla argumentów { 1, 10, 200, 1000 }, 500 – metoda powinna zwrócić -1, ponieważ liczby 500 nie ma w podanej tablicy.


> Pisząc testy w poniższych zadaniach, pamiętaj o:

    - wzięciu pod uwagę różnych przypadków testowych i rozdzieleniu ich na osobne metody testujące,
    - odpowiednim nazewnictwie metod testujących,
    - ustrukturyzowaniu metod testujących w taki sposób, by były czytelne i jasno przekazywały, na jakim przypadku testowym działają,
    - napisaniu metody, którą będziesz testował, w taki sposób, by była testowalna.

#### Zadanie nr 9 (wyjątki) - proszę przesłać do 18.01.2026 r.

1. Do czego służą wyjątki?  
2. W której z metod wymienionych w poniższym stack trace rzucony został wyjątek?  
```text
Exception in thread "main" java.lang.IllegalArgumentException
	at Pytania.innaMetoda(Pytania.java:13)
	at Pytania.pewnaMetoda(Pytania.java:9)
	at Pytania.main(Pytania.java:5)
```
3. Jak w języku Java obsługuje się wyjątki?
4. Do czego służy sekcja finally i czy jest wymagana?
5. Jak rzuca się wyjątki?
6. Do czego służy słowo kluczowe throws?
7. Jaką regułę muszą spełniać klasy, aby były klasami wyjątków?
8. Do czego służy try-with-resources i jak się tego mechanizmu używa?
9. Napisz program z klasą Adres, która będzie miała podane poniżej pola, które będą ustawiane w konstruktorze klasy Adres. 
Konstruktor powinien sprawdzić wszystkie podane wartości i rzucić wyjątek NieprawidlowyAdresException rodzaju Checked, jeżeli któraś z wartości będzie nieprawidłowa.  
Uwaga: komunikat rzucanego wyjątku powinien zawierać informację o wszystkich nieprawidłowych wartościach przekazanych do konstruktora – dla przykładu, jeżeli ulica i miasto będą miały wartość null, to komunikat wyjątku powinien być następujący: "Ulica nie może być nullem. Miasto nie może być nullem".  
Pola klasy:
```text
    String ulica – wartość nieprawidłowa to null,
    int numerDomu – wartość nieprawidłowa to liczba <= 0,
    String kodPocztowy – wartość nieprawidłowa to null,
    String miasto – wartość nieprawidłowa to null.
```

#### Zadanie nr 10 (referencje do obiektów) - proszę przesłać do 28.01.2026 r.
1. Co to jest sterta i stos?
2. Czym różnią się typy prymitywne od typów referencyjnych (złożonych)?
3. Co charakteryzuje obiekty niemutowalne?
4. Czy i dlaczego, obiekty poniższej klasy są, lub nie są, niemutowalne?
```java
    public class ZagadkaMutowalne {
      public final int x;
      public ZagadkaMutowalne(int x) {
        this.x = x;
      }
    }
```
5. Czy i dlaczego, obiekty poniższej klasy są, lub nie są, niemutowalne?

Czy, i dlaczego, obiekty poniższej klasy są, lub nie są, niemutowalne?
```java
    public class ZagadkaMutowalne2 {
      private String komunikat;
      public void setKomunikat(String komunikat) {
        this.komunikat = komunikat;
      }
      public String getKomunikat() {
        return komunikat;
      }
    }
```
6. Czy i dlaczego, obiekty poniższej klasy są, lub nie są, niemutowalne?

Czy, i dlaczego, obiekty poniższej klasy są, lub nie są, niemutowalne?
```java
    public class ZagadkaMutowalne3 {
      private final String[] slowa;
      public ZagadkaMutowalne3(String[] slowa) {
        this.slowa = slowa;
      }
    }
```