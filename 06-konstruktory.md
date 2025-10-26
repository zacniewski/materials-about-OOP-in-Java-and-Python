Źródło: https://kursjava.com/klasy/konstruktory/

### 1. Konstruktory

Do tej pory tworzyliśmy obiekty klas używając słowa kluczowego `new`, po którym następowała nazwa konstruktora klasy, której obiekt chcieliśmy utworzyć, nawiasy i średnik:

```java
Produkt czeresnie = new Produkt();
```

Po utworzeniu obiektu, nadawaliśmy jego polom wartości albo poprzez settery, albo przez bezpośrednie odwołanie się do tych pól (tego drugiego sposobu nie będziemy już stosować na rzecz wykorzystania setterów):

```java
czeresnie.setCena(8.0);
czeresnie.setNazwa("Czeresnie");
```

Takie inicjalizowanie pól obiektu jest co prawda jedną z możliwości, ale niezbyt wygodną – gdybyśmy mieli do ustawienia pięć pól, to musielibyśmy na rzecz danego obiektu wywołać pięć setterów, by ustawić każde z nich. Mamy jednak inną możliwość – napisać własny konstruktor.

Język Java, jak i inne języki obiektowe, udostępnia specjalny rodzaj metod nazywanych konstruktorami, które służą do inicjalizacji obiektów klasy.

Konstruktory mają dwie cechy specjalne, które wyróżniają je na tle innych metod:

- nazwy konstruktorów są zawsze takie same, jak nazwa klasy, w której się znajdują – dla przykładu, konstruktor klasy `Produkt` będzie nazywał się `Produkt`,
- konstruktory nie zwracają żadnej wartości (nie stosujemy nawet `void`) – podczas definiowania konstruktora po prostu omijamy zwracany typ.

> Poznaliśmy konwencję nazewniczą obiektów w Javie – wedle niej, nazwy zmiennych i metod powinny zaczynać się od małej litery  
> – konstruktory są wyjątkiem od tej reguły, ponieważ muszą nazywać się tak samo, jak nazwa klasy – a jak wiemy, nazwy klas, także zgodnie z konwencją, zawsze zaczynamy od wielkiej litery.

Zobaczmy pierwszy przykład konstruktora w klasie `Produkt`:

Plik: `Produkt.java`

```java
public class Produkt {
  private double cena;
  private String nazwa;
  //(1)(2) (3)       (4)
  public Produkt(double cena, String nazwa) {
    this.cena = cena; // 5
    this.nazwa = nazwa; // 6
  }
  // settery i gettery zostaly pominiete
  public String toString() {
    return "Produkt o nazwie " + nazwa + " kosztuje " + cena;
  }
}
```

Klasa `Produkt` posiada jeden konstruktor – jego cechy to:

- publiczny dostęp (1) – każdy będzie mógł tworzyć obiekty typu `Produkt` za pomocą tego konstruktora,
- brak zwracanego typu (2) – zazwyczaj, pomiędzy modyfikatorami a nazwą metody, umieszczamy typ wartości, którą metoda zwraca – w przypadku konstruktorów pomijamy całkowicie zwracany typ (nie piszemy nawet słowa `void`),
- nazwa jest taka sama, jak nazwa klasy w której konstruktor się znajduje (3) – nazwa konstruktora to `Produkt`, ponieważ zawarty jest w klasie o nazwie `Produkt`,
- dwa argumenty (4) – wartości, którymi zostaną zainicjalizowane pola `cena` (5) oraz `nazwa` (6) – jak widzimy, korzystamy w konstruktorze z poznanego już słowa kluczowego `this`, by odwołać się do pól obiektu, który tworzymy. Dzięki użyciu słowa kluczowego `this` możemy nazwać argumenty konstruktora tak samo, jak nazwy pól naszej klasy.

Zobaczmy powyższy konstruktor klasy `Produkt` w akcji:

Plik: `Warzywniak.java`

```java
public class Warzywniak {
  public static void main(String[] args) {
    Produkt papryka = new Produkt(5.0, "Papryka"); // 1
    System.out.println(papryka);
  }
}
```

Tym razem nawiasy po nazwie klasy, której obiekt tworzymy, nie są puste – w nawiasach zawarliśmy argumenty konstruktora (1) utworzonego w klasie `Produkt`.

Po uruchomieniu tego programu, na ekranie zobaczymy:

```
Produkt o nazwie Papryka kosztuje 5.0
```

Wartości przesłane jako argumenty konstruktora (1) zostały przypisane do pól `cena` i `nazwa`, co widać po wypisaniu tekstowej reprezentacji obiektu `papryka` na ekran.

### 2. Domyślny konstruktor

Wróćmy do klasy `Sklep`, z której niedawno korzystaliśmy – tworzyliśmy w niej dwa obiekt typu `Produkt`:

```java
public class Sklep {
  public static void main(String[] args) {
    Produkt czeresnie = new Produkt();
    Produkt herbata = new Produkt();
    czeresnie.setCena(8.0);
    czeresnie.setNazwa("Czeresnie");
    herbata.setCena(12.0);
    herbata.setNazwa("Herbata czarna");
    System.out.println("Nazwa pierwszego produktu to: " + czeresnie.getNazwa());
    System.out.println("Cena pierwszego produktu to: " + czeresnie.getCena());
    System.out.println(herbata);
  }
}
```

Spróbujmy jeszcze raz skompilować i uruchomić powyższy program. Niespodziewanie, próba kompilacji kończy się następującym błędem:

```
Sklep.java:3: error: constructor Produkt in class Produkt cannot be applied to given types;
        Produkt czeresnie = new Produkt();
                            ^
  required: double,String
  found: no arguments
  reason: actual and formal argument lists differ in length

Sklep.java:4: error: constructor Produkt in class Produkt cannot be applied to given types;
        Produkt herbata = new Produkt();
                          ^
  required: double,String
  found: no arguments
  reason: actual and formal argument lists differ in length
2 errors
```

Wygląda na to, że dodanie do klasy `Produkt` konstruktora spowodowało, że klasa `Sklep` przestała działać – dlaczego tak się stało?

Każda klasa, którą napiszemy w języku Java, będzie miała konstruktor – niezależnie od tego, czy go napiszemy, czy nie.

Taki konstruktor nazywany jest konstruktorem domyślnym i jest dla nas generowany automatycznie przez kompilator języka Java w jednym, konkretnym przypadku: gdy my, jako autorzy klasy, nie dostarczymy sami konstruktora dla klasy.

Konstruktor domyślny:

- nie przyjmuje żadnych argumentów,
- nie wykonuje żadnych instrukcji – jego ciało jest puste,
- występuje tylko w tych klasach, w których nie został zdefiniowany żaden konstruktor przez programistę.

Tworząc obiekty klas, które pisaliśmy w tym rozdziale, korzystaliśmy cały czas, nie wiedząc o tym, z konstruktorów domyślnych, wygenerowanych dla nas przez kompilator, na przykład w klasie `Sklep`:

```java
Produkt czeresnie = new Produkt();
Produkt herbata = new Produkt();
```

W powyższym fragmencie kodu, używaliśmy konstruktora domyślnego. Po dodaniu do klasy `Produkt` poniższego konstruktora:

```java
public Produkt(double cena, String nazwa) {
  this.cena = cena;
  this.nazwa = nazwa;
}
```

kompilator nie musiał już generować konstruktora domyślnego dla naszej klasy `Produkt`, ponieważ sami dostarczyliśmy konstruktor tej klasy. Z tego powodu, klasa `Sklep` przestała działać, ponieważ wykorzystywany w niej do tej pory konstruktor domyślny (przyjmujący zero argumentów) przestał istnieć w klasie `Produkt`!

Co możemy zatem zrobić, aby kod klasy `Sklep` zaczął działać? Mamy dwa wyjścia:

1. Przepisać tworzenie obiektów w klasie `Sklep`, by korzystało z nowego konstruktora.
2. Dodać do klasy `Produkt` drugi konstruktor, który nie przyjmuje żadnych argumentów i nie wykonuje żadnych operacji – będzie to w zasadzie odwzorowanie konstruktora domyślnego, z którego do tej pory korzystaliśmy.

Spróbujmy dodać drugi konstruktor do klasy `Produkt`:

Plik: `Produkt.java`

```java
public class Produkt {
  private double cena;
  private String nazwa;
  public Produkt() { // 1
  }
  public Produkt(double cena, String nazwa) {
    this.cena = cena;
    this.nazwa = nazwa;
  }
  // settery i gettery zostaly pominiete
  public String toString() {
    return "Produkt o nazwie " + nazwa + " kosztuje " + cena;
  }
}
```

Do klasy `Produkt` dodaliśmy drugi konstruktor (1), który nie przyjmuje żadnych argumentów i nie wykonuje żadnych instrukcji. Gdy teraz spróbujemy skompilować i uruchomić klasę `Sklep`, na ekranie zobaczymy:

```
Nazwa pierwszego produktu to: Czeresnie
Cena pierwszego produktu to: 8.0
Produkt o nazwie Herbata czarna kosztuje 12.0
```

Dodatkowy konstruktor, wzorowany na konstruktorze domyślnym, spowodował, że kod zaczął ponownie działać. Jak widać, klasa może mieć więcej niż jeden konstruktor.

### 3. Przeładowanie konstruktora

Klasy mogą mieć wiele konstruktorów – tyle, ile uznamy za stosowne. Z racji tego, że konstruktory są metodami, to aby mieć więcej niż jeden konstruktor, każdy z nich musi różnić się liczbą, typem, lub kolejnością argumentów (co wiemy z rozdziału o przeładowywaniu metod).

Kiedy możemy potrzebować więcej niż jednego konstruktora? W poprzednim przykładzie mieliśmy dwa konstruktory – pierwszy nie przyjmował argumentów i nie wykonywał żadnych operacji, a drugi inicjalizował oba pola obiektu przesłanymi do niego argumentami:

```java
public Produkt() {
}
public Produkt(double cena, String nazwa) {
  this.cena = cena;
  this.nazwa = nazwa;
}
```

Czasami możemy chcieć zainicjalizować od razu pola obiektu pewnym wartościami – innym razem możemy nie chcieć od razu podawać wszystkich wartości, tylko uzupełnić je później.

Często spotykanym przypadkiem w klasach, które mają wiele pól, jest posiadanie kilku konstruktorów, z których każdy inicjalizuje inny zestaw pól – od konstruktora, który zainicjalizuje wszystkie, do takiego, który nie zainicjalizuje żadnych. Często w takich przypadkach polom niezainicjalizowanym przypisywane są pewne domyślne (dla obiektów danej klasy) wartości. Spójrzmy na poniższy przykład:

Plik: `Film.java`

```java
public class Film {
  private String tytul;
  private String rezyser;
  private double cenaBiletu;
  public Film() { // 1
    this.tytul = "<nienazwany film>"; // 5
    this.rezyser = "<brak rezysera>"; // 5
    this.cenaBiletu = 20.0; // 5
  }
  public Film(String tytul) { // 2
    this.tytul = tytul;
    this.rezyser = "<brak rezysera>"; // 5
    this.cenaBiletu = 20.0; // 5
  }
  public Film(String tytul, String rezyser) { // 3
    this.tytul = tytul;
    this.rezyser = rezyser;
    this.cenaBiletu = 20.0; // 5
  }
  public Film(String tytul, String rezyser, double  cenaBiletu) { // 4
    this.tytul = tytul;
    this.rezyser = rezyser;
    this.cenaBiletu = cenaBiletu;
  }
}
```

Klasa `Film` ma cztery konstruktory (1) (2) (3) (4) – pozwalają one na ustawienie pewnych pól, przesyłając ich wartości jako argumenty, a pozostałe inicjalizują wartościami domyślnymi (5). Mamy więc cztery sposoby na utworzenie obiektu klasy `Film`:

```java
Film tajemniczyFilm = new Film();
Film rambo = new Film("Rambo");
Film zrodlo = new Film("Zrodlo", "Darren Aronofsky");
Film cicheMiejsce = new Film("Ciche miejsce", "John Krasinski", 25.0);
```

Powyższy fragment kodu wykorzystuje każdy z czterech konstruktorów klasy `Film`.

Wróćmy do konstruktorów – widzimy w kodzie klasy `Film`, że wielokrotnie powtarzamy te same fragmenty kodu – każdy konstruktor inicjalizuje wszystkie pola tworzonego obiektu.

W poprzednich rozdziałach uczyliśmy się, że duplikacja kodu nie jest dobrą praktyką – na szczęście, w tym przypadku język Java udostępnia nam sposób na zwięźlejsze zapisanie kodu klasy `Film`.

Zamiast w każdym konstruktorze ustawiać wszystkie pola, możemy oddelegować ustawienie ich innemu konstruktorowi tej samej klasy – wywołamy go jak każdą inną metodę, podając argumenty.

Aby wywołać z konstruktora inny konstruktor, korzystamy z poznanego już słowa kluczowego `this`, po którym następują nawiasy i ewentualne argumenty – spójrzmy na drugą wersję klasy `Film`:

Plik: `Film.java`

```java
public class Film {
  private String tytul;
  private String rezyser;
  private double cenaBiletu;
  public Film() {
    this("<nienazwany film>", "<brak rezysera>", 20.0); // 1
  }
  public Film(String tytul) {
    this(tytul, "<brak rezysera>", 20.0); // 2
  }
  public Film(String tytul, String rezyser) {
    this(tytul, rezyser, 20.0); // 3
  }
  public Film(String tytul, String rezyser, double cenaBiletu) { // 4
    this.tytul = tytul;
    this.rezyser = rezyser;
    this.cenaBiletu = cenaBiletu;
  }
}
```

W każdym z konstruktorów (1) (2) (3), poza czwartym, skorzystaliśmy z możliwości wywołania innego konstruktora – konstruktorem, który wywołujemy, jest czwarty konstruktor (4), który inicjalizuje wszystkie pola tworzonego obiektu przesłanymi do niego argumentami. Każdy z pozostałych konstruktorów wywołuje go z odpowiednimi wartościami – część wartości pochodzi z argumentów tych konstruktorów, a część ustawiamy "na sztywno" jako wartości domyślne. W ten sposób skróciliśmy znacząco kod.

Istnieją dwie zasady odnośnie wywoływania innych konstruktorów:

- Możemy wywołać tylko jeden inny konstruktor z danego konstruktora (chociaż możemy z wywoływanego konstruktora wywołać kolejny).
- Wywołanie innego konstruktora musi być pierwszą instrukcją w danym konstruktorze (poza komentarzami).

Spójrzmy najpierw na punkt 1 – poniższy kod spowodowałby błąd kompilacji:

```java
public Film() {
  this("<nienazwany film>", "<brak rezysera>", 20.0);
  // blad kompilacji! nie mozemy wywolac kolejnego konstruktora
  this("<nienazwany film>");
}
```

Jednakże moglibyśmy zapisać konstruktory naszej klasy `Film` w następujący, poprawny sposób:

```java
public Film() {
  this("<nienazwany film>"); // 1
}
public Film(String tytul) {
  this(tytul, "<brak rezysera>"); // 2
}
public Film(String tytul, String rezyser) {
  this(tytul, rezyser, 20.0); // 3
}
public Film(String tytul, String rezyser, double cenaBiletu) { // 4
  this.tytul = tytul;
  this.rezyser = rezyser;
  this.cenaBiletu = cenaBiletu;
}
```

W tym (poprawnie działającym) przypadku pierwszy (1) konstruktor wywołuje drugi (2), drugi korzysta z trzeciego (3), który, finalnie, używa konstruktora czwartego (4).

Druga zasada wspominała o kolejności instrukcji – wywołanie innego konstruktora musi być zawsze pierwszą instrukcją w konstruktorze – poniższy przykład zakończy się błędem kompilacji:

```java
public Film() {
  // blad kompilacji! wywolanie innego konstruktora, o ile jest on 
  //  uzywany, musi byc pierwsza instrukcja konstruktora
  System.out.println("Wywolales konstruktor bezargumentowy!");
  this("<nienazwany film>", "<brak rezysera>", 20.0);
}
```

Błąd kompilacji, który zobaczymy, to:

```
Film.java:8: error: call to this must be first statement in constructor
      this("", "", 20.0);
          ^
1 error
```

Nic nie stoi jednak na przeszkodzie, by nasze konstruktory zawierały instrukcje po wywołaniu innego konstruktora, na przykład:

```java
public Film() {
  // tym razem kod jest poprawny – po wywolaniu innego konstruktora,
  //  ten konstruktor moze zawierac inne instrukcje
  this("<nienazwany film>", "<brak rezysera>", 20.0);
  System.out.println("Wywolales konstruktor bezargumentowy!");
}
```

### 4. Inicjalizacja pól finalnych w konstruktorach

W rozdziale trzecim "Zmienne" nauczyliśmy się, czym są stałe i jak je definiować.

Stałe to takie zmienne, którym wartość nadajemy tylko raz i nie możemy kolejny raz przypisać im wartości. Stałe definiujemy poprzedzając ich nazwę słowem kluczowym `final`:

```java
final double LICZBA_PI = 3.14;
final String PONIEDZIALEK = "Poniedzialek";
// blad kompilacji!
// error: cannot assign a value to final variable LICZBA_PI
LICZBA_PI = 5;
// blad kompilacji!
// error: cannot assign a value to final variable PONIEDZIALEK
PONIEDZIALEK = "Piatek";
```

Po zainicjalizowaniu stałych `LICZBA_PI` oraz `PONIEDZIALEK`, wszelkie próby nadania im nowej wartości kończą się przytoczonym powyżej błędem kompilacji.

Pola klas także mogą być stałe – po nadaniu im raz wartości, zachowają ją i nie będziemy mogli przypisać im innej wartości – spójrzmy na poniższy przykład:

Plik: `Pojazd.java`

```java
public class Pojazd {
  private final String marka; // 1
  private final String numerRejestracyjny; // 2
  private final int rokProdukcji; // 3
  public String toString() {
    return "Pojazd marki " + marka +
        ", numer rejestracjny " + numerRejestracyjny +
        ", wyprodukowany w " + rokProdukcji + " roku.";
  }
  public String getMarka() {
    return marka;
  }
  public String getNumerRejestracyjny() {
    return numerRejestracyjny;
  }
  public int getRokProdukcji() {
    return rokProdukcji;
  }
}
```

Nasza klasa zawiera:

- trzy stałe pola: `marka` (1), `numerRejestracyjny` (2), oraz `rokProdukcji` (3),
- metodę `toString`,
- po jednym getterze na każde pole,
- domyślny konstruktor, który zostanie dla nas automatycznie wygenerowany z racji tego, że sami nie dostarczyliśmy dla klasy `Pojazd` żadnego konstruktora.

Zauważmy, że nasza klasa nie ma setterów – nie miałyby sensu, skoro wszystkie pola klasy `Pojazd` są stałe.

Pytanie: czy powyższa klasa skompiluje się poprawnie?

Spróbujmy:

```
Pojazd.java:2: error: variable marka not initialized in the default constructor
    private final String marka;
                         ^
Pojazd.java:3: error: variable numerRejestracyjny not initialized in the default constructor
    private final String numerRejestracyjny;
                         ^
Pojazd.java:4: error: variable rokProdukcji not initialized in the default constructor
    private final int rokProdukcji;
                      ^
3 errors
```

Próba kompilacji zakończyła się błędami – kompilator protestuje, ponieważ nigdzie w naszej klasie nie inicjalizujemy stałych pól tej klasy. Moglibyśmy zmienić kod klasy na następujący:

```java
public class Pojazd {
  private final String marka = "Toyota";
  private final String numerRejestracyjny = "123456";
  private final int rokProdukcji = 1997;
  // gettery i metoda toString zostaly pominiete
}
```

W tej wersji klasy `Pojazd` przypisujemy do stałych pól klasy `Pojazd` pewne wartości – powyższa klasa skompiluje się bez problemów.

Problem jednak nadal występuje, ponieważ… klasa `Pojazd` jest nieużywalna, o ile nie zakładamy, że wszystkie pojazdy, jakie kiedykolwiek będziemy potrzebowali, będą Toyotami z numerem rejestracyjnym 123456, wyprodukowanymi w roku 1997.

W tej chwili wszystkie obiekty klasy `Pojazd`, jakie byśmy utworzyli, miałyby takie same wartości przypisane do każdego pola – wszystkie te pola są stałe i raz po przypisaniu im wartości nie możemy już nadać im innej wartości.

Aby rozwiązać ten problem, możemy skorzystać z konstruktorów, ponieważ stałe pola, których nie zainicjalizujemy od razu wartością, możemy jeszcze zainicjalizować w konstruktorach – spójrzmy na finalną wersję klasy `Pojazd`:

Plik: `Pojazd.java`

```java
public class Pojazd {
  private final String marka;
  private final String numerRejestracyjny;
  private final int rokProdukcji;
  // 1
  public Pojazd(String marka, String numerRejestracyjny, int rokProdukcji) {
    this.marka = marka; // 2
    this.numerRejestracyjny = numerRejestracyjny; // 3
    this.rokProdukcji = rokProdukcji; // 4
  }
  public String toString() {
    return "Pojazd marki " + marka +
        ", numer rejestracjny " + numerRejestracyjny +
        ", wyprodukowany w " + getRokProdukcji() + " roku.";
  }
  public String getMarka() {
    return marka;
  }
  public String getNumerRejestracyjny() {
    return numerRejestracyjny;
  }
  public int getRokProdukcji() {
    return rokProdukcji;
  }
}
```

Do klasy `Pojazd` dodaliśmy konstruktor (1), który przyjmuje trzy argumenty – wartości tych argumentów użyjemy do zainicjalizowania stałych pól tworzonego obiektu. Będą to, odpowiednio, pola `marka` (2), `numerRejestracyjny` (3), oraz `rokProdukcji` (4).

Powyższy kod kompiluje się bez błędów – co prawda nie nadajemy stałym polom od razu wartości w liniach, w których je definiujemy, ale robimy to w konstruktorze – kompilator analizując kod naszej klasy wie, że nie ma możliwości na utworzenie obiektu klasy `Pojazd`, który miałby niezainicjalizowane stałe pola – pola, które muszą być zainicjalizowane. Wszystkie obiekty tworzone będą za pomocą konstruktora, który te wszystkie stałe pola uzupełnia wartościami.

A gdybyśmy dodali jeszcze jeden konstruktor, który inicjalizowałby tylko dwa z trzech stałych pól? Na przykład, załóżmy, że do klasy `Pojazd` dodajemy jeszcze jeden, następujący konstruktor:

```java
public Pojazd(String marka, String numerRejestracyjny) {
  this.marka = marka;
  this.numerRejestracyjny = numerRejestracyjny;
}
```

Gdybyśmy teraz spróbowali skompilować klasę `Pojazd`, to zobaczylibyśmy ponownie błąd o potencjalnym braku inicjalizacji pola `rokProdukcji`:

```
Pojazd.java:9: error: variable rokProdukcji might not have been initialized
}
^
1 error
```

Błąd wynika z faktu, że tym razem istnieje sposób na utworzenie obiektu klasy `Pojazd`, który miałby niezainicjalizowane, stałe pole – kompilator wychwycił ten problem i zasygnalizował go powyższym błędem już na etapie kompilacji.

Spójrzmy jeszcze na wykorzystanie (poprawnej) wersji klasy `Pojazd`:

```java
Pojazd motor = new Pojazd("Harley", "978654", 2017);
Pojazd samochod = new Pojazd("Toyota", "123456", 1997);
System.out.println(motor);
System.out.println(samochod);
```

Wynik na ekranie:

```
Pojazd marki Harley, numer rejestracjny 978654, wyprodukowany w 2017 roku.
Pojazd marki Toyota, numer rejestracjny 123456, wyprodukowany w 1997 roku.
```

Zgodnie z konwencją, stałe zazwyczaj zapisujemy wielkimi literami ze słowami rozdzielonymi znakami podkreślenia. Ta konwencja dotyczy stałych, które mają niezmienne, w pewien sposób uniwersalne wartości, jak na przykład liczba Pi.

W przypadku pól klas nie stosujemy tej konwencji – pola klas definiujemy jako `final`, by zapobiec potencjalnemu przypisaniu do pola klasy innej wartości, lecz sama wartość, jako taka, nie musi stanowić uniwersalnej, stałej wartości – dlatego stałych pól w klasie `Pojazd` nie nazywaliśmy wielkimi literami, lecz, tak jak zawsze, używając camelCase'a.

### 5. Prywatne konstruktory

Konstruktory nie muszą być publiczne – możemy napisać klasę, która będzie zawierała prywatny konstruktor:

Plik: `Powitanie.java`

```java
public class Powitanie {
  private Powitanie() {
  }
  public void powitaj(String imie) {
    System.out.println("Witaj " + imie);
  }
}
```

Zauważ, że klasa `Powitanie` posiada jeden konstruktor, który jest prywatny. Jeżeli spróbujemy utworzyć obiekt tej klasy, to kompilator zgłosi błąd:

Plik: `UzywaniePowitania.java`

```java
public class UzywaniePowitania {
  public static void main(String[] args) {
    Powitanie powitanie = new Powitanie();
  }
}
```

```
UzywaniePowitania.java:3: error: Powitanie() has private access in Powitanie
    Powitanie powitanie = new Powitanie();
                          ^
1 error
```

Kompilator nie zezwala na kompilację klasy `UzywaniePowitania`, ponieważ próbujemy w jej metodzie `main` utworzyć obiekt typu `Powitanie`, co jest niemożliwe – nie możemy w innej klasie odnieść się do prywatnego konstruktora klasy `Powitanie`.

Jaki jest zatem sens tworzenia prywatnych konstruktorów, skoro nie możemy z nich skorzystać?

Jest jedna klasa, która może użyć konstruktora, pomimo tego, że jest prywatny – jest to ta klasa, w której jest on zdefiniowany! W końcu do prywatnych pól i metod (a konstruktor to rodzaj specjalnej metody) mamy dostęp z wnętrza klasy, w której są one zdefiniowane:

Plik: `Powitanie.java`

```java
public class Powitanie {
  public static final Powitanie INSTANCE = new Powitanie();
  private Powitanie() {
  }
  public void powitaj(String imie) {
    System.out.println("Witaj " + imie);
  }
}
```

Do klasy `Powitanie` dodano nowe pole statyczne o nazwie `INSTANCE`, które jest typu `Powitanie`.

Pola i metody statyczne są wspólne dla wszystkie obiektów danej klasy – przeznaczę na ich omówienie jeden z kolejnych podrozdziałów. Możemy się do nich odnosić za pomocą nazwy klasy, w której są zdefiniowane.

Zauważ, że wywołujemy prywatny konstruktor w celu utworzenia obiektu klasy `Powitanie`:

```java
public static final Powitanie INSTANCE = new Powitanie();
```

Kod klasy `Powitanie`, zapisany w ten sposób, powoduje, że istnieje tylko jeden obiekt tej klasy – ten utworzony w powyższej linii. Jeżeli inne klasy będą chciały korzystać z klasy `Powitanie`, to będą musiały używać tego jednego, konkretnego obiektu, ponieważ nie będą mogły utworzyć nowych obiektów tej klasy ze względu na prywatny konstruktor. Jest to wzorzec projektowy o nazwie Singleton.

Wzorce projektowe to opisane sposoby na zaimplementowanie w kodzie źródłowym rozwiązania pewnego problemu. W przypadku wzorca Singleton, celem jest posiadanie klasy, która używana jest przez inne klasy za pomocą dokładnie jednej instancji (jednego obiektu) tej klasy. Stosując prywatny konstruktor uniemożliwiamy innym klasom na tworzenie obiektów tego typu, a dostarczając jeden publiczny obiekt, utworzony przez tę klasę, zapewniamy, że wszystkie inne klasy będą musiały z niego korzystać:

Plik: `UzywaniePowitania.java`

```java
public class UzywaniePowitania {
  public static void main(String[] args) {
    Powitanie powitanie = Powitanie.INSTANCE;
    powitanie.powitaj("Bonifacy");
  }
}
```

```
Witaj Bonifacy
```

Jest to jedno z zastosowań prywatnych konstruktorów. Ponadto, moglibyśmy napisać klasę, która będzie zawierała zarówno konstruktory publiczne, jak i prywatne. Publiczne konstruktory byłyby używane spoza tej klasy, a prywatne byłyby do użytku wewnętrznego.

Innym przykładem jest użycie konstruktorów prywatnych w klasach, które stosują wzorzec projektowy „Builder”. Służy on do utworzenia obiektu klasy w taki sposób, aby sprowadzało się ono do wywołania kilku metod, za pomocą których ustawimy pola skojarzone z obiektem danej klasy. Gdy ustawimy wszystkie pola, wywołujemy metodę `build` „obiektu-buildera”, która zwraca utworzony obiekt. Więcej o tym wzorcu opowiem Ci w przyszłości w rozdziale o klasach zagnieżdżonych.

Klasy, które posiadają jedynie prywatne konstruktory, nie mogą być rozszerzane, tzn. inna klasa nie może po nich dziedziczyć. Wyjątkiem od tej reguły są klasy zagnieżdżone, które będą tematem jednego z rozdziałów w przyszłości, natomiast dziedziczenie jest tematem rozdziału dziesiątego.

### 6. Podsumowanie

- Konstruktory to specjalny rodzaj metod, które służą do inicjalizacji obiektów klasy.
- Konstruktory mają dwie cechy specjalne, które wyróżniają je na tle innych metod:
  - nazwy konstruktorów są zawsze takie same, jak nazwa klasy – konstruktor klasy `Produkt` będzie nazywał się `Produkt`,
  - konstruktory nie zwracają żadnej wartości (nie stosujemy nawet `void`) – podczas definiowania konstruktora omijamy zwracany typ.
- Poniższa klasa `Produkt` posiada jeden publiczny konstruktor (1), który nie ma zwracanego typu (nie zostało użyte nawet słowo `void`). Nazwa jest taka sama, jak nazwa klasy:

```java
public class Produkt {
  private double cena;
  private String nazwa;
  public Produkt(double cena, String nazwa) { // 1
    this.cena = cena;
    this.nazwa = nazwa;
  }
  public String toString() {
    return "Produkt o nazwie " + nazwa + " kosztuje " + cena;
  }
}
```

- Użycie konstruktora (1) i wynik działania poniższego kodu:

```java
Produkt papryka = new Produkt(5.0, "Papryka"); // 1
System.out.println(papryka);
```

```
Produkt o nazwie Papryka kosztuje 5.0
```

- Każda klasa, którą napiszemy w języku Java, ma konstruktor – niezależnie od tego, czy go napiszemy, czy nie – taki konstruktor nazywamy domyślnym.
- Konstruktor domyślny jest dla nas generowany automatycznie przez kompilator w przypadku, gdy my, jako autorzy klasy, nie dostarczymy sami konstruktora dla tej klasy.
- Konstruktor domyślny:
  - nie przyjmuje żadnych argumentów,
  - nie wykonuje żadnych instrukcji – jego ciało jest puste,
  - występuje tylko w tych klasach, w których nie został zdefiniowany żaden konstruktor przez programistę.
- Poniższa, pusta klasa, posiada konstruktor domyślny:

```java
public class PustaKlasaZDomyslnymKonstruktorem {
}
```

- Ponieważ konstruktor domyślny będzie automatycznie wygenerowany, możemy napisać:

```java
PustaKlasaZDomyslnymKonstruktorem obiekt = new PustaKlasaZDomyslnymKonstruktorem();
```

- Klasy mogą mieć wiele konstruktorów – muszą się one jednak różnić liczbą, typem, lub kolejnością argumentów.
- Z konstruktora możemy wywołać inny konstruktor tej samej klasy – w takim przypadku korzystamy z poznanego już słowa kluczowego `this`, po którym następują nawiasy i ewentualne argumenty:

```java
public class Film {
  private String tytul;
  private String rezyser;
  private double cenaBiletu;
  public Film() {
    this("<nienazwany film>", "<brak rezysera>", 20.0); // 1
  }
  public Film(String tytul) {
    this(tytul, "<brak rezysera>", 20.0); // 2
  }
  public Film(String tytul, String rezyser) {
    this(tytul, rezyser, 20.0); // 3
  }
  public Film(String tytul, String rezyser, double cenaBiletu) { // 4
    this.tytul = tytul;
    this.rezyser = rezyser;
    this.cenaBiletu = cenaBiletu;
  }
}
```

- Istnieją dwie zasady odnośnie wywoływania innych konstruktorów:
  - Możemy wywołać tylko jeden inny konstruktor z danego konstruktora (chociaż możemy z wywoływanego konstruktora wywołać kolejny):

```java
public Film() {
  this("<nienazwany film>", "<brak rezysera>", 20.0);
  // blad kompilacji! nie mozemy wywolac kolejnego konstruktora
  this("<nienazwany film>");
}
```

  - Wywołanie innego konstruktora musi być pierwszą instrukcją w danym konstruktorze (poza komentarzami):

```java
public Film() {
  // blad kompilacji! wywolanie innego konstruktora, o ile jest on 
  //  uzywany, musi byc pierwsza instrukcja konstruktora
  System.out.println("Wywolales konstruktor bezargumentowy!");
  this("<nienazwany film>", "<brak rezysera>", 20.0);
}
```

- Pola klas mogą być stałe, tzn. zdefiniowane z modyfikatorem `final` – raz po przypisaniu im wartości nie będziemy już mogli nadać im innej wartości.
- Pola `final` możemy zainicjalizować w konstruktorach:

```java
public class Pojazd {
  private final String marka;
  private final String numerRejestracyjny;
  private final int rokProdukcji;
  public Pojazd(String marka, String numerRejestracyjny, int rokProdukcji) {
    this.marka = marka;
    this.numerRejestracyjny = numerRejestracyjny;
    this.rokProdukcji = rokProdukcji;
  }
  // gettery zostaly pominiete
}
```

- Gdybyśmy dodali jeszcze jeden konstruktor, który inicjalizowałby tylko dwa z trzech stałych pól (taki, jak poniżej), to kod powyższej klasy `Pojazd` przestałby się kompilować:

```java
public Pojazd(String marka, String numerRejestracyjny) {
    this.marka = marka;
    this.numerRejestracyjny = numerRejestracyjny;
}
```

```
Pojazd.java:9: error: variable rokProdukcji might not have been initialized
}
^
1 error
```

- Błąd wynika z faktu, że istnieje teraz sposób na utworzenie obiektu klasy `Pojazd`, który miałby niezainicjalizowane, stałe pole – kompilator wychwycił ten problem i zasygnalizował go powyższym błędem na etapie kompilacji.
- Pola `final`, o ile nie są uniwersalnymi stałymi z punktu widzenia naszego programu, nazywamy tak jak inne zmienne, czyli korzystając z konwencji camelCase. Stałe, które wyznaczają pewną niezmienną wartość, jak na przykład liczba Pi, nazywamy wielkimi literami z podkreśleniem jako separator słów, np. `LICZBA_PI`.
- Konstruktory mogą być prywatne – przydaje się to w przypadku wzorców projektowych, takich jak Singleton oraz Builder. Konstruktory prywatne mogą być używane przez klasy, w których zostały zdefiniowane.

