Źródło: https://kursjava.com/klasy/modyfikatory-dostepu/

Od samego początku nauki języka Java używaliśmy słowa kluczowego `public` – poprzedzało one nazwy naszych klas oraz metod.  
W rozdziale o klasach zaczęliśmy także używać słowa kluczowego `private`.   
W tym rozdziale dowiemy się, jakie jest ich znaczenie w odniesieniu do pól i metod klas.
W odniesieniu do klas możemy nie stosować `public` (chociaż dotąd zawsze tak robiliśmy w naszych programach).   
Jakie to słowo kluczowe ma znaczenie podczas definiowania klas dowiemy się pod koniec rozdziału o klasach.   

### Modyfikatory dostępu public oraz private

Słowa kluczowe `public` oraz `private` to modyfikatory dostępu.

Modyfikatory dostępu służą do ustawiania zakresu widoczności pól oraz metod klasy.  
Dzięki nim możemy określić, jak będzie wyglądało użytkowanie naszej klasy oraz kto, i w jakich okolicznościach, będzie mógł z pól i metod tej klasy korzystać.

W Javie istnieją cztery modyfikatory dostępu:

    public
    protected
    domyślny
    private

W tym rozdziale skupimy się na modyfikatorach `public` oraz `private`.  
O modyfikatorze domyślnym (który nie ma własnego słowa kluczowego) opowiemy sobie pod koniec rozdziału o klasach, a o modyfikatorze `protected` w rozdziale o dziedziczeniu.

Gdy definiujemy pole bądź metodę z modyfikatorem `public`, to oznajmiamy, że to pole lub metoda jest dostępne dla całego "zewnętrznego świata" – każdy może się do takiego pola odnieść i wywołać taką metodę. Mówimy wtedy, że pole lub metoda jest publiczne.

Z drugiej jednak strony, nasza klasa może zawierać także pola i metody, których nie chcemy udostępniać – mają one być prywatne dla naszej klasy – tylko z poziomu tej klasy powinniśmy móc tymi polami i metodami zarządzać. Takie pola i metody definiuje się korzystając z modyfikatora dostępu `private`.

Pytanie: wobec kogo stosowane są modyfikatory dostępu? Kogo mamy na myśli mówiąc "zewnętrzny świat"?

Modyfikatory dostępu regulują dostęp do pól i metod klas, gdy odnosimy się do nich z innych klas. Przejdziemy teraz przez kilka prostych przykładów, które wyjaśnią działanie modyfikatorów dostępu.

### Modyfikatory `public` oraz `private` – przykład – klasa `Ksiazka`

Przykład będzie bazował na klasie `Ksiazka` – przyjrzyjmy się jej definicji:
```java
    public class Ksiazka {
      public String tytul; // 1
      public String autor; // 2
      private double cena; // 3 
      
      public void ustawCene(double nowaCena) { // 4
        if (czyCenaJestPoprawna(nowaCena)) { // 5
          cena = nowaCena;
        } else {
          System.out.println("Cena " + nowaCena + " jest nieprawidlowa!");
        }
      }
      
      public String toString() { // 6
        return "Ksiazka o tytule " + tytul +
            ", ktorej autorem jest " + autor + ", kosztuje " + cena;
      }
      
      private boolean czyCenaJestPoprawna(double cenaDoSprawdzenia) { // 7
        return cenaDoSprawdzenia > 0;
      }
    }
```

Klasa ta zawiera:

    dwa publiczne pola tytul (1) oraz autor (2),
    jedno prywatne pole cena (3),
    dwie publiczne metody ustawCene (4) oraz toString (6),
    jedną prywatną metodę czyCenaJestPoprawna (7).

Metoda `ustawCene` służy do ustawiania wartości pola cena. Nie chcieliśmy bezpośrednio udostępniać pola cena światu zewnętrznemu, ponieważ zawsze przed ustawieniem ceny chcemy sprawdzić, czy jest ona poprawna.  
Osiągamy to przez sprawdzenie poprawności ceny za pomocą prywatnej metody `czyCenaJestPoprawna`, z której korzystamy w metodzie `ustawCene` (5).
Znamy już znaczenie metody `toString` – zwraca ona tekstową reprezentację obiektu klasy `Ksiazka`.  
Prywatna metoda `czyCenaJestPoprawna` zwraca `true`, jeżeli podana cena jest większa od zera, a przeciwnym razie – `false`.

### Użycie pól i metod publicznych klasy Ksiazka

Mając tak zdefiniowaną klasę Ksiazka, spójrzmy, jak możemy korzystać z jej pól i metod, biorąc pod uwagę modyfikatory dostępu, które zostały w niej użyte:
Nazwa pliku: Ksiegarnia.java
```java
    public class Ksiegarnia {
      public static void main(String[] args) {
        Ksiazka lokomotywa = new Ksiazka(); // 1
        lokomotywa.tytul = "Lokomotywa"; // 2
        lokomotywa.autor = "Julian Tuwim"; // 3
        lokomotywa.ustawCene(29.99); // 4
        System.out.println(lokomotywa); // 5
      }
    }
```
Ten prosty przykład tworzy nowy obiekt klasy Ksiazka (1), a następnie, za pomocą kropki, odnosi się zarówno do pól `tytul` oraz `autor` obiektu `lokomotywa`, a także wywołuje metodę `ustawCena`.

Wiemy już, że aby wywołać metodę na rzecz pewnego obiektu, po nazwie tego obiektu piszemy kropkę, a następnie metodę, którą chcemy wywołać (4).  
W ten sam sposób możemy odnieść się do publicznych pól obiektu i przypisać im wartość, tak jak zrobilśmy to w powyższym przykładzie, nadając polom `tytul` oraz autor wartości, odpowiednio, "Lokomotywa" (2) oraz "Julian Tuwim" (3).

Na końcu program wypisuje na ekran tekstową reprezentację obiektu lokomotywa (jak wiemy, zostanie tutaj automatycznie użyta metoda `toString`). Na ekranie zobaczymy:
Ksiazka o tytule Lokomotywa, ktorej autorem jest Julian Tuwim, kosztuje 29.99

Jak widzimy, ustawione przez nas wartości w polach `tytul` oraz `autor` zostały zapamiętane w obiekcie `lokomotywa`.

### Próba użycia pola i metody private klasy Ksiazka spoza tej klasy

Co by się stało, gdybyśmy spróbowali przypisać wartość do prywatnego pola cena lub wywołać prywatną metodę `czyCenaJestPoprawna` z klasy `Ksiazka`? Sprawdźmy:

```java
public class Ksiegarnia {
  public static void main(String[] args) {
    Ksiazka lokomotywa = new Ksiazka();
    lokomotywa.tytul = "Lokomotywa";
    lokomotywa.autor = "Julian Tuwim";
    lokomotywa.ustawCene(29.99);
    System.out.println(lokomotywa);
    lokomotywa.cena = -10;
    lokomotywa.czyCenaJestPoprawna(0);
  }
}
```
Próba kompilacji powyższej wersji klasy Ksiegarnia kończy się błędem – kompilator wypisuje na ekran dwa znaleziony problemy:
```sh
Ksiegarnia.java:13: error: cena has private access in Ksiazka
        lokomotywa.cena = -10;
                  ^
Ksiegarnia.java:14: error: czyCenaJestPoprawna(double) has private access in Ksiazka
        lokomotywa.czyCenaJestPoprawna(0);
                  ^
2 errors
```
Kompilator zaprotestował, ponieważ próbowaliśmy odnieść się do prywatnego pola i prywatnej metody klasy `Ksiazka`. 
Widzimy tutaj modyfikatory dostępu w akcji – w klasie `Ksiazka` zdefiniowaliśmy pole cena oraz metodę `czyCenaJestPoprawna` z użyciem modyfikatora `private`. 
To pole i metoda są więc prywatne dla klasy `Ksiazka` i nikt nie powinien mieć do nich dostępu spoza klasy `Ksiazka`.

Użycie modyfikatora `private` chroni nas przed potencjalnymi, niechcianymi zmianami pola cena w obiektach typu `Ksiazka`, a także korzystania z metody `czyCenaJestPoprawna`, która jest własnością klasy `Ksiazka` i nie powinna być stosowana przez inne klasy.

### Dostęp do prywatnych pól oraz metod w klasie `Ksiazka`.  

Zauważmy jednak, że w klasie `Ksiazka` mamy dostęp zarówno do prywatnego pola cena, jak i do prywatnej metody `czyCenaJestPoprawna` – ma to oczywiście całkowity sens, ponieważ to właśnie klasa Ksiazka definiuje to pole i tę metodę, więc wewnątrz tej klasy mamy dostęp do jej prywatnych pól oraz metod.

Co by się jednak stało, gdyby klasa `Ksiazka` miała metodę, która jak argument przyjmowałaby obiekt tej samej klasy (tzn. obiekt klasy `Ksiazka`)?   
Czy moglibyśmy w tej metodzie odnieść się do prywatnych pól i metod?

Spójrzmy na omawiany powyżej przypadek – dodamy do klasy `Ksiazka` metodę, której argumentem będzie obiekt klasy `Ksiazka`:
Nazwa pliku: Ksiazka.java
```java
    public class Ksiazka {
      public String tytul;
      public String autor;
      private double cena;
      public boolean czyTakaSamaCena(Ksiazka innaKsiazka) {
        return cena != innaKsiazka.cena;
      }
      // definicje metod ustawCene, toString, oraz czyCenaJestPoprawna,
      // zostaly pominiete
    }
```

Nowa metoda klasy Ksiazka o nazwie `czyTakaSamaCena` przyjmuje jako argument obiekt tej samej klasy – klasy `Ksiazka`. 
Wewnątrz tej metody próbujemy odnieść się do prywatnego pola cena obiektu `innaKsiazka`, by porównać jego cenę z wartością pola cena obiektu, na rzecz którego tę metodę wywołamy.

Pytanie: czy powyższy kod jest poprawny?

Tak – kod skompiluje się bez problemów i będzie działał zgodnie z założeniami – spójrzmy na przykład użycia nowej metody:
Nazwa pliku: Ksiegarnia.java
```java
    public class Ksiegarnia {
      public static void main(String[] args) {
        Ksiazka lokomotywa = new Ksiazka();
        lokomotywa.tytul = "Lokomotywa";
        lokomotywa.autor = "Julian Tuwim";
        lokomotywa.ustawCene(29.99);
        Ksiazka ptasieRadio = new Ksiazka(); // 1
        ptasieRadio.tytul = "Ptasie Radio";
        ptasieRadio.autor = "Julian Tuwim";
        ptasieRadio.ustawCene(19.99);
        if (lokomotywa.czyTakaSamaCena(ptasieRadio)) { // 2
          System.out.println("Ksiazki kosztuja tyle samo.");
        } else {
          System.out.println("Cena ksiazek nie jest taka sama.");
        }
      }
    }
```
W klasie `Ksiegarnia` tworzymy drugi obiekt typu `Ksiazka` (1) o nazwie `ptasieRadio`.  
Następnie, na rzecz obiektu lokomotywa, wywołujemy metodę `czyTakaSamaCena` (2), której argumentem będzie utworzony właśnie obiekt `ptasieRadio`.  
Na podstawie wyniku wykonania tej metody wypisujemy na ekran komunikat – w tym przypadku zobaczymy:
> Cena ksiazek nie jest taka sama.

Spójrzmy jeszcze raz na klasę `Ksiazka`:
```java
    public class Ksiazka {
      public String tytul;
      public String autor;
      private double cena;
      public boolean czyTakaSamaCena(Ksiazka innaKsiazka) {
        return cena != innaKsiazka.cena;
      }
      // definicje metod ustawCene, toString, oraz czyCenaJestPoprawna,
      // zostaly pominiete
    }
```
Dlaczego metoda `czyTakaSamaCena` nie powoduje błędów kompilacji takich jak wtedy, gdy próbowaliśmy z klasy `Ksiegarnia` (w poprzednim podrozdziale) odnieść się do pola cena oraz metody `czyCenaJestPoprawna`?

Wynika to z faktu, że do prywatnego pola cena obiektu przesłanego jako argument odnosimy się z kontekstu klasy `Ksiazka`, a obiekt ten jest właśnie typu `Ksiazka`.

W obrębie tej samej klasy mamy dostęp do prywatnych pól oraz metod, gdy działamy na obiektach tej klasy.

W poprzednim rozdziale próbowaliśmy użyć prywatnego pola i metody obiektu Ksiazka w metodzie main klasy `Ksiegarnia` – taka operacja jest zabroniona, ze względu na to, że użyliśmy modyfikatora private.

Jednakże w powyższym przypadku w metodzie `czyTakaSamaCena`, działamy na prywatnym polu wewnątrz klasy Ksiazka – tej samej klasie, która jest typem przesłanego jako argument obiektu.  
W takim przypadku, mamy prawo dostępu zarówno do prywatnych pól, jak i metod.

### Kiedy stosować modyfikatory dostępu

Do czego są nam potrzebne modyfikatory dostępu? Czy nie moglibyśmy ustawić wszystkich pól w naszych klasach jako publiczne? Moglibyśmy.  
Ale czy jest to dobry pomysł?

Spójrzmy ponownie na definicję metody `czyCenaJestPoprawna` z klasy `Ksiazka`:
```java
    private boolean czyCenaJestPoprawna(double cenaDoSprawdzenia) {
      return cenaDoSprawdzenia > 0;
    }
```  

Wyobraźmy sobie, że postanawiamy zmienić metodę `czyCenaJestPoprawna`, by nie pozwalała na podanie ceny niższej niż 10 zł. Jeżeli metoda ta byłaby publiczna – czyli dalibyśmy przyzwolenie na korzystanie z niej przez inne klasy – to moglibyśmy, zmieniając zachowanie metody czyCenaJestPoprawna, spowodować, że inne miejsca naszego programu, które polegały na konkretnym działaniu tej metody, przestałyby działać poprawnie.

Z polami klas jest podobnie – dla przykładu, gdybyśmy w klasie `Ksiazka` zmienili dostęp z `private` na `public` pola `cena`, to nie bylibyśmy w stanie zapewnić, że każdy obiekt klasy `Ksiazka` będzie miał zawsze poprawną (tzn. powyżej zera) cenę. Gdyby definicja pola cena wyglądała następująco:
```java
    public double cena;
```  

to nic nie stałoby na przeszkodzie, aby klasy, które tworzyłyby obiekty klasy Ksiazka, napisały kod podobny do poniższego:
```java
    Ksiazka pewnaKsiazka = new Ksiazka();
    pewnaKsiazka.cena = -10;
```  

Dzięki temu, że pole cena jest polem prywatnym, jedyny sposób na ustawienie jego wartości to użycie dedykowanej do tego celu metody `ustawCene`, która sprawdza, czy cena, którą chcemy ustawić, jest poprawna:
```java
    public void ustawCene(double nowaCena) {
      if (czyCenaJestPoprawna(nowaCena)) {
        cena = nowaCena;
      } else {
        System.out.println("Cena " + nowaCena + " jest nieprawidlowa!");
      }
    }
```  
To, co jest tutaj także ważne, to to, że ukrywamy przed użytkownikiem wnętrze naszej klasy, jej implementację, sposób, w jaki robi to, co do niej należy. Jeżeli w pewnym momencie chcielibyśmy przechowywać cenę w euro zamiast w złotówkach, to możemy zmienić metodę ustawCene, by wykonała wymagane w tym celu przeliczenia. Ta zmiana byłaby transparentna dla użytkownika i nie powinna spowodować żadnych problemów. Z drugiej strony, gdyby pole cena było polem publicznym, to odebralibyśmy sobie możliwość wszelkich zmian w naszej klasie odnośnie przetrzymywanej ceny bez bardzo prawdopodobnego zepsucia działania wszystkich klas w naszych programach, które z klasy Ksiazka korzystały.

W takim razie co powinno być w naszej klasie publiczne, a co prywatne? Ogólne zasady są proste i powinniśmy się do nich stosować:

    Wszystkie pola klas powinny być prywatne – klasa powinna udostępniać publiczne metody, których będzie można używać w celu ustawiania i pobierania wartości pól klasy (o ile jest takie wymaganie) – w kolejnym podrozdziale zobaczymy przykłady takich metod.
    Wszystkie metody, które są używane wewnętrznie przez klasę, powinny być prywatne.
    Tylko metody, z których mają korzystać użytkownicy klas, mogą być publiczne.

Podsumowując:
Powinno nam zależeć, by nasze klasy udostępniały tak mało ze swoich pól (czyli żadnych, zgodnie z regułą numer 1), jak to możliwe, oraz tylko te metody, które są wymagane, by nasze klasy spełniały swoje zadania (były używalne przez inne części naszego programu).

Takie podejście do tworzenia klas, w którym ukrywamy przed użytkownikami tych klas ich wewnętrzną implementację i udostępniamy zestaw metod, z których użytkownicy tych klas mają korzystać, nazywamy **enkapsulacją**.  
Jest to powszechny sposób projektowania klas (nie tylko w języku Java) i na rozmowach kwalifikacyjnych często pada pytanie "Czym jest enkapsulacja?". 
Wyjątkiem od zdefiniowanej powyżej pierwszej reguły są pola i metody statyczne, które już po części znamy.