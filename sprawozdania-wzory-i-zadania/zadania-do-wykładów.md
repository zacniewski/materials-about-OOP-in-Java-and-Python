### Zadania do wykładów

#### Zadanie nr 1 (rozgrzewka przed OOP i praktyka z Markdownem) - proszę przesłać do 06.10.2025 r.
  - z pomocą dowolnego języka programowania napisz program, który po uruchomieniu:  
    - wyświetli w kolejności alfabetycznej nazwy czterech filarów OOP,  
    - wyświetli w kolejności losowej nazwy czterech filarów OOP,  
    - w obu ww. przypadkach wskazane jest użycie wbudowanych w dany język programowania metod,  
    - w pliku sprawozdania "wykładowego" proszę umieścić zrzut ekranu z działającego programu.  

#### Zadanie nr 2 (klsy i obiekty) - proszę przesłać do 13.10.2025 r.
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
3) Uruchom zadania od 1 do 5 z folderu `zadania-1_5-do-działu-o-modułach-i-pakietach`, ale przedtem zmień nazwę każdego pliku, aby była w postaci `Zad2_StaticImport_Nazwisko.java`.  Wyniki działania programów umieść w sprawozdaniu z wykładu.  



