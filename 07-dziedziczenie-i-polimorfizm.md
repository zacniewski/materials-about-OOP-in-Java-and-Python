Źródło: https://kursjava.com/dziedziczenie-i-polimorfizm/czym-jest-dziedziczenie/

### 1. Dziedziczenie – wprowadzenie

Dziedziczenie w językach obiektowych to tworzenie hierarchii klas. Kolejne klasy w hierarchii posiadają zarówno pola, jak i metody klas, które je poprzedzają, oraz mogą zawierać nowe pola i metody.

Przykładowa hierarchia klas reprezentujących zwierzęta mogłaby wyglądać następująco:

![hier](images/hierarchia_dziedziczenia.png) 

Strzałka na rysunku oznacza, że jedna klasa dziedziczy po drugiej – po stronie grotu strzałki znajduje się klasa nadrzędna. Na przykład: klasa `Kot` dziedziczy (jest klasą‑dzieckiem) po klasie `Ssak`, a klasa `Ssak` dziedziczy po klasie `Zwierzę`.

Bardzo istotna obserwacja: każdy kot to zwierzę, ale nie każde zwierzę to kot (bo może być nim pies). Podobne zależności są pomiędzy pozostałymi klasami na tym rysunku – każdy ptak to zwierzę, ale nie każde zwierzę to ptak (bo może nim być ssak) itd.

---

### 2. Pierwszy przykład dziedziczenia

Gdy klasa dziedziczy po innej klasie, mówimy, że klasa ta rozszerza tę klasę. Taką klasę będziemy nazywać klasą podrzędną. Klasę rozszerzaną będziemy nazywać klasą bazową bądź klasą nadrzędną. W języku Java do zaznaczenia, że dana klasa ma dziedziczyć po innej klasie, używamy słowa kluczowego `extends`, po którym następuje nazwa klasy nadrzędnej (klasy‑rodzica).

Spójrzmy na prosty przykład dziedziczenia.

Nazwa pliku: `prostyprzyklad/Osoba.java`

```java
package prostyprzyklad;

public class Osoba {
  public String imie;
  public String nazwisko;
  public String toString() {
    return "Osoba " + imie + " " + nazwisko;
  }
}
```

Nazwa pliku: `prostyprzyklad/Pracownik.java`

```java
package prostyprzyklad;

public class Pracownik extends Osoba { // 1
  public int numerIdentyfikatora;
  public static void main(String[] args) {
    Osoba pewnaOsoba = new Osoba();
    pewnaOsoba.imie = "Jan";
    pewnaOsoba.nazwisko = "Kowalski";
    System.out.println(pewnaOsoba);

    Pracownik pewienPracownik = new Pracownik();
    pewienPracownik.imie = "Joanna"; // 2
    pewienPracownik.nazwisko = "Sikorska"; // 3
    pewienPracownik.numerIdentyfikatora = 1234;
    System.out.println(pewienPracownik); // 4
  }
}
```

Klasa `Osoba` to prosta klasa zawierająca pola `imie` i `nazwisko` oraz metodę `toString`, która zwraca połączone imię i nazwisko osoby.

Klasa `Pracownik` rozszerza klasę `Osoba` (1) – zwróć uwagę na słowo kluczowe `extends` oraz nazwę klasy, która po nim następuje. Oznacza to, że klasa `Pracownik`, poza polem `numerIdentyfikatora`, które jest w niej zdefiniowane, posiada także pola `imie` oraz `nazwisko`, odziedziczone z klasy `Osoba`. Dzięki temu możemy te pola ustawić w obiekcie `pewienPracownik` (2) (3). To jednak nie koniec – klasa `Pracownik` dziedziczy także metodę `toString` – dzięki temu, w linii (4), zamiast wypisać na ekran nazwę klasy i „hash code” obiektu w postaci `prostyprzyklad.Pracownik@f6f4d33`, zobaczymy imię oraz nazwisko przechowywane w obiekcie `pewienPracownik`:

```
Osoba Jan Kowalski
Osoba Joanna Sikorska
```

Należy zwrócić uwagę, że klasa `Osoba` nie wie o istnieniu klasy `Pracownik`. Co ważniejsze, obiekty klasy `Osoba` nie posiadają pola `numerIdentyfikatora`, ponieważ pole to zdefiniowane jest w klasie podrzędnej `Pracownik`, i tylko obiekty klasy `Pracownik` mają to pole.

Podobnie jak w przypadku hierarchii ze zwierzętami z początku tego rozdziału, tutaj także możemy zauważyć, iż każdy `Pracownik` to `Osoba`, ale nie każda `Osoba` to `Pracownik`, bo możemy utworzyć obiekt klasy `Osoba`, a obiekt klasy `Osoba` to obiekt klasy `Osoba`, a nie `Pracownik`. Mało tego, moglibyśmy dodać wiele nowych klas dziedziczących po klasie `Osoba`, i wtedy to rozgraniczenie byłoby jeszcze wyraźniejsze.

Dziedzicząc klasy, musimy zwrócić uwagę na kilka reguł i właściwości, o których po kolei sobie opowiemy. Zanim jednak porozmawiamy o szczegółach, zobaczymy przykład wykorzystania polimorfizmu.

> Jeżeli korzystasz z IntelliJ IDEA, to możesz szybko przejść do klasy bazowej naciskając na klawiaturze przycisk Ctrl i klikając lewym przyciskiem myszy na nazwę klasy bazowej.

---

### 3. Polimorfizm w akcji

Polimorfizm to bardzo potężny i często wykorzystywany w językach obiektowych mechanizm. Pozwala on na traktowanie obiektów pewnej klasy jako obiektów innej klasy, jeżeli jedna z tych klas dziedziczy po drugiej klasie (pośrednio bądź bezpośrednio).

Wracając do przykładu z poprzedniego rozdziału, obiekt klasy `Pracownik` mógłby zostać potraktowany jako reprezentant klasy `Osoba`, ponieważ klasa `Pracownik` rozszerza klasę `Osoba` – zachodzi więc tutaj zależność „Pracownik jest Osobą”.

Jak to wygląda w praktyce? W poniższej klasie metoda `wejdzDoBudynku` w klasie `Budynek` oczekuje jako argumentu obiektu typu `Osoba`:

Nazwa pliku: `prostyprzyklad/Budynek.java`

```java
package prostyprzyklad;

public class Budynek {
  public static void main(String[] args) {
    Osoba osoba = new Osoba();
    osoba.imie = "Jan";
    osoba.nazwisko = "Kowalski";

    Pracownik pracownik = new Pracownik();
    pracownik.imie = "Joanna";
    pracownik.nazwisko = "Sikorska";
    pracownik.numerIdentyfikatora = 1234;

    wejdzDoBudynku(osoba);     // 1
    wejdzDoBudynku(pracownik); // 2
  }

  public static void wejdzDoBudynku(Osoba osoba) { // 3
    System.out.println("W budynku jest " + osoba);
  }
}
```

Wynik uruchomienia programu:

```
W budynku jest Osoba Jan Kowalski
W budynku jest Osoba Joanna Sikorska
```

Zauważmy, że metoda `wejdzDoBudynku` oczekuje argumentu typu `Osoba` (3). W metodzie `main` najpierw wywołujemy ją z argumentem `osoba` typu `Osoba` (1), ale w kolejnej linii (2) wywołujemy tę samą metodę z argumentem innego typu – obiektem `pracownik` typu `Pracownik`!

Kompilator języka Java nie zgłasza problemów podczas kompilacji, a Maszyna Wirtualna Java nie rzuca wyjątku w trakcie działania programu, ponieważ powyższy kod jest całkowicie legalny i ilustruje polimorfizm w akcji. Kod działa, ponieważ każdy obiekt klasy `Pracownik` może być traktowany jako obiekt klasy `Osoba` ze względu na to, że jedna z tych klas dziedziczy (rozszerza) drugą klasę.

Czy moglibyśmy zmienić argument metody `wejdzDoBudynku` z `Osoba` na `Pracownik`? Tak, ale wtedy kod przestałby się kompilować – problemem byłaby linia (1). Wynika to z faktu, że o ile każdy `Pracownik` to `Osoba`, to nie każda `Osoba` to `Pracownik`. Kompilator wypisałby następujący błąd:

```
prostyprzyklad\Budynek.java:14: error: incompatible types: Osoba cannot be converted to Pracownik
    wejdzDoBudynku(osoba);
```

Przykład metody `wejdzDoBudynku` pokazuje, że argument tej metody raz wskazuje w pamięci na obiekt typu `Osoba`, a drugi raz – na obiekt typu `Pracownik`. Oznacza to, że zmienne typu bazowego możemy stosować do wskazywania na obiekty klas pochodnych – spójrzmy na poniższy przykład:

```java
Osoba innaOsoba = new Pracownik();
innaOsoba.imie = "Artur";
innaOsoba.nazwisko = "Strzelecki";
```

Powyższy fragment kodu jest prawidłowy. Jak już wiemy, każdy `Pracownik` to `Osoba`. Korzystamy ze zmiennej `innaOsoba`, która może przechowywać referencję do obiektu typu `Osoba`, ale obiekt, jaki faktycznie tworzymy i którego adres przypisujemy do tej zmiennej, jest typu `Pracownik`. Nie jest to jednak problem, ponieważ klasa `Pracownik` dziedziczy po klasie `Osoba`.

Ustawiliśmy powyżej `imie` i `nazwisko` – te pola są w klasie `Osoba`. A gdybyśmy spróbowali ustawić pole `numerIdentyfikatora`?

```java
// blad!
innaOsoba.numerIdentyfikatora = 4321;
```

Ta linia spowodowałaby następujący błąd kompilatora:

```
prostyprzyklad\Budynek.java:21: error: cannot find symbol
    innaOsoba.numerIdentyfikatora = 4321;
             ^
  symbol:   variable numerIdentyfikatora
  location: variable innaOsoba of type Osoba
```

Co prawda tworzymy obiekt typu `Pracownik`, w której to klasie zdefiniowane jest pole `numerIdentyfikatora`, ale do odnoszenia się do tego obiektu używamy referencji typu `Osoba` – a obiekty klasy `Osoba` pola `numerIdentyfikatora` nie posiadają.

Jak zobaczymy w kolejnych rozdziałach, jest sposób na ustawienie pola `numerIdentyfikatora` za pomocą referencji typu `Osoba`. W tym celu należy skorzystać z mechanizmu rzutowania, który poznaliśmy już w poprzednich rozdziałach – rzutowaliśmy na przykład liczby typu całkowitego na liczby rzeczywiste. Rzutowanie odbywa się poprzez napisanie typu docelowego w nawiasach przed rzutowaną wartością. Zobaczmy, jak moglibyśmy poprawić powyższy przykład, by zadziałał:

```java
((Pracownik) innaOsoba).numerIdentyfikatora = 4321;
```

Dzięki takiemu zapisowi, kod jest poprawny i jesteśmy w stanie ustawić wartość pola `numerIdentyfikatora`. Poprzez użycie rzutowania powiedzieliśmy kompilatorowi:

> „Wiem, że zmienna `innaOsoba` jest typu `Osoba`, ale tak naprawdę wskazuje ona na obiekt klasy pochodnej o nazwie `Pracownik`, proszę więc o pozwolenie na kompilację i ustawienie pola `numerIdentyfikatora` na moją odpowiedzialność”.

Dlaczego „na naszą odpowiedzialność”? Ponieważ zmienna `innaOsoba` mogłaby wskazywać na obiekt klasy `Osoba`, a nie `Pracownik` – wtedy rzutowanie byłoby niemożliwe i działanie programu zakończyłoby się błędem. Działanie programu, a nie kompilacja – zauważmy tutaj, że kompilator nie jest w stanie nas uchronić przed próbą nieprawidłowego rzutowania – o potencjalnym problemie dowiemy się dopiero po uruchomieniu programu:

Fragment pliku `Budynek.java`:

```java
Osoba kolejnaOsoba = new Osoba();
// kompilacja ok, ale blad wykonania!
((Pracownik) kolejnaOsoba).numerIdentyfikatora = 5555;
```

Kompilacja tego fragmentu kodu zakończy się bez błędów – kompilator w tym przypadku nie uchroni nas przed potencjalnym błędem, który w tym przypadku ewidentnie popełniliśmy, co widać na ekranie po uruchomieniu programu:

```
Exception in thread "main" java.lang.ClassCastException: class prostyprzyklad.Osoba cannot be cast to class prostyprzyklad.Pracownik 
        at prostyprzyklad.Budynek.main(Budynek.java:26)
```

Problem występuje, ponieważ próbujemy ustawić pole `numerIdentyfikator` rzutując obiekt `kolejnaOsoba` na typ `Pracownik`, jednak jest to niemożliwe – zmienna `kolejnaOsoba` wskazuje na obiekt typu `Osoba`, a nie `Pracownik`.

---

### 4. Przykład method overriding (nadpisywania metod)

Z polimorfizmem i dziedziczeniem wiąże się także bardzo ważny mechanizm zwany method overriding (nadpisywanie metod), o którym opowiemy sobie więcej w kolejnych rozdziałach, a na razie zobaczymy jeden przykład, który wyjaśni, na czym ten mechanizm polega.

Method overriding to tworzenie w klasie pochodnej takiej samej metody, jaka już znajduje się w klasie nadrzędnej (z możliwością pewnych zmian, które poznamy wkrótce). Gdy metoda ta zostanie wywołana na zmiennej typu bazowego, to wykonana zostanie nie metoda z typu bazowego, lecz z typu obiektu, na który ta zmienna faktycznie wskazuje w pamięci.

Dla przykładu, załóżmy, że dodamy do klasy `Pracownik` metodę `toString` – ta metoda będzie wypisywała nie tylko imię i nazwisko (jak już to robi metoda `toString` w klasie `Osoba`), ale także identyfikator pracownika.

Nazwa pliku: `prostyprzyklad/Pracownik.java`

```java
package prostyprzyklad;

public class Pracownik extends Osoba {
  public int numerIdentyfikatora;
  
  public String toString() {
    return "Pracownik " + imie + " " + nazwisko +
        ", identyfikator: " + numerIdentyfikatora; 
  }
  // metoda main zostala pominieta
}
```

Przypomnijmy jeszcze, jak wygląda metoda `toString` z klasy bazowej `Osoba`:

Nazwa pliku: `prostyprzyklad/Osoba.java`

```java
package prostyprzyklad;

public class Osoba {
  public String imie;
  public String nazwisko;
  public String toString() {
    return "Osoba " + imie + " " + nazwisko;
  }
}
```

Gdy teraz utworzymy obiekt klasy `Osoba` i obiekt klasy `Pracownik` i wypiszemy ich tekstową reprezentację na ekran, to zobaczymy następujący komunikat:

Fragment metody `main` z pliku `Pracownik.java`:

```java
Osoba pewnaOsoba = new Osoba();
pewnaOsoba.imie = "Jan";
pewnaOsoba.nazwisko = "Kowalski";
System.out.println(pewnaOsoba.toString());

Pracownik pewienPracownik = new Pracownik();
pewienPracownik.imie = "Joanna";
pewienPracownik.nazwisko = "Sikorska";
pewienPracownik.numerIdentyfikatora = 1234;
System.out.println(pewienPracownik.toString());
```

```
Osoba Jan Kowalski
Pracownik Joanna Sikorska, identyfikator: 1234
```

Na razie nie jest to nic nowego – obiekt `pewnaOsoba` został zamieniony na tekst za pomocą metody `toString` z klasy `Osoba`, a obiekt `pewienPracownik` – nową metodą `toString` z klasy `Pracownik`.

Spójrzmy jednak, co się stanie, jeżeli do referencji do typu `Osoba` przypiszemy obiekt typu `Pracownik` i wtedy użyjemy metody `toString`:

Fragment metody `main` z pliku `Pracownik.java`:

```java
Osoba innaOsoba = new Pracownik();
innaOsoba.imie = "Adrian";
innaOsoba.nazwisko = "Sochacki";
System.out.println(innaOsoba.toString());
```

Ten fragment kodu spowoduje wypisanie na ekran komunikatu:

```
Pracownik Adrian Sochacki, identyfikator: 0
```

Tutaj zachodzi „magia” mechanizmu method overriding – pomimo że typ zmiennej `innaOsoba` to `Osoba`, a nie `Pracownik`, została użyta metoda `toString` zaimplementowana w klasie `Pracownik`, ponieważ faktyczny obiekt wskazywany przez zmienną `innaOsoba` jest właśnie typu `Pracownik`.

Mechanizm ten działa automatycznie i daje ogromne możliwości. Jest on jedną z podstaw programowania zorientowanego obiektowo. Jest kilka istotnych zasad dotyczących nadpisywania metod (method overriding), które trzeba mieć na uwadze – opowiemy sobie o nich dokładnie w jednym z kolejnych rozdziałów.

> Nie należy mylić nadpisywania metod z przeciążaniem metod (method overriding vs. method overloading). Przeciążanie metod poznaliśmy w rozdziale o metodach – polega ono na tworzeniu metod o tej samej nazwie, ale różniących się argumentach. Z kolei w nadpisywaniu metod lista parametrów jest taka sama (z pewnymi wyjątkami, które poznamy wkrótce).

---

### 5. Zasady i uwagi dotyczące dziedziczenia

Dziedziczenie to dość skomplikowany mechanizm – w kolejnych rozdziałach dokładnie sobie o nim opowiemy. Najpierw jednak skrótowo przedstawmy różne związane z nim zagadnienia.

Widzieliśmy już, jak rozszerza się klasę – korzystamy w tym celu ze słowa kluczowego `extends`, po którym następuje nazwa klasy bazowej:

```java
public class Pracownik extends Osoba {
  // ...
}
```

Tak zdefiniowana klasa `Pracownik` staje się pochodną klasy `Osoba`. Mówimy też, że klasa `Pracownik` rozszerza klasę `Osoba`. Klasa `Osoba` natomiast jest klasą bazową, bądź rodzicem, dla klasy `Pracownik`. Pomiędzy klasami zachodzi relacja „Pracownik jest Osobą”, dzięki czemu możemy traktować obiekty klasy `Pracownik` jak obiekty klasy `Osoba` (polimorfizm). Nie działa to jednak w drugą stronę – każdy `Pracownik` to `Osoba`, ale nie każda `Osoba` to `Pracownik`. Najlepiej widoczne byłoby to, gdybyśmy utworzyli nową klasę `Uczen`, która rozszerzałaby klasę `Osoba`. Każdy `Pracownik` to `Osoba`, każdy `Uczen` to `Osoba`, ale nie każda `Osoba` to `Pracownik` – może być przecież `Uczniem`.

- W języku Java klasa może dziedziczyć tylko po jednej klasie – nie ma możliwości rozszerzenia więcej niż jednej klasy (tzw. pojedyncze dziedziczenie).
- W rozdziale o klasach wspomnieliśmy o klasie `Object`. Jest to specjalna klasa w języku Java – wszystkie klasy dziedziczą po klasie `Object` – pośrednio bądź bezpośrednio. Jeżeli klasa nie definiuje, że rozszerza jakąś klasę, to automatycznie rozszerza klasę `Object`.
- Klasa `Pracownik` dziedziczy po klasie `Osoba`, oraz po wszystkich poprzednich klasach w hierarchii, pola oraz metody publiczne i te oznaczone modyfikatorem `protected`.
- Modyfikator `protected` udostępnia pola i metody klasom pochodnym, ale też klasom w tym samym pakiecie (nawet jeśli nie dziedziczą). Pół prywatnych (`private`) oraz pól z dostępem domyślnym (brak modyfikatora) nie dziedziczy się.
- Odziedziczone metody niestatyczne możemy nadpisywać (method overriding). Tylko metody niestatyczne można nadpisywać.
- Z dziedziczeniem wiąże się słowo kluczowe `super` – służy ono do dwóch celów: wywoływania konstruktora klasy bazowej oraz do korzystania z metody bądź pola z klasy bazowej.
- Poza tym klasy i metody mogą być abstrakcyjne lub finalne. Obiekty klas abstrakcyjnych nie mogą być tworzone – należy takie klasy rozszerzyć. Klasy `final` nie mogą być rozszerzane. Metody abstrakcyjne nie mają ciał i trzeba je zaimplementować w klasie pochodnej, a metod `final` nie można nadpisywać.
- Zamiast rozszerzać klasę abstrakcyjną, można utworzyć nienazwaną klasę (anonymous class) w miejscu użycia.

---

### 6. Pojedyncze dziedziczenie – przykłady i błędy kompilacji

W języku Java każda klasa może bezpośrednio rozszerzać tylko jedną klasę. Próba kompilacji poniższej klasy `Kot` zakończyłaby się błędem:

```java
public class Zwierze {
  // ...
}

public class NajlepszyPrzyjacielCzlowieka {
  // ...
}

public class Kot extends Zwierze, NajlepszyPrzyjacielCzlowieka {
  // ...
}
```

Komunikat zwracany przez kompilator:

```
Kot.java:1: error: '{' expected
class Kot extends Zwierze, NajlepszyPrzyjacielCzlowieka {
                         ^
1 error
```

Kompilator spodziewał się klamry otwierającej ciało klasy `Kot` zamiast przecinka i nazwy kolejnej klasy, którą chcieliśmy rozszerzyć.

Klasa nie może bezpośrednio rozszerzać więcej niż jednej klasy, ale pośrednio tak – tzn. klasy mogą mieć w swojej hierarchii dziedziczenia wiele klas:

```java
public class Zwierze {
  // ...
}

public class Ssak extends Zwierze {
  // ..
}

public class Kot extends Ssak {
  // ...
}

public class Dachowiec extends Kot {
  // ...
}
```

W powyższym przypadku klasa `Dachowiec` bezpośrednio rozszerza klasę `Kot`, a ponadto ma w swojej hierarchii dziedziczenia klasy `Ssak` oraz `Zwierze` (a także klasę `Object`). Innymi słowy, klasa `Dachowiec` pośrednio dziedziczy po tych klasach.

Pamiętaj, że w języku Java klasy mogą rozszerzać maksymalnie jedną, wybraną przez Ciebie klasę, tzn. po słowie kluczowym `extends` możesz umieścić nazwę co najwyżej jednej klasy.

---

### 7. Modyfikatory dostępu a dziedziczenie – przykład z pojazdami

Gdy rozszerzamy klasę, dziedziczymy po niej pola i metody, które mają modyfikatory `public` lub `protected`.

Spójrzmy na poniższe klasy.

Nazwa pliku: `pojazdy/Pojazd.java`

```java
package pojazdy;
public class Pojazd {
  public void jedz() { // 1
    System.out.println("Pojazd jedzie.");
  }
}
```

Nazwa pliku: `pojazdy/Samochod.java`

```java
package pojazdy;

public class Samochod extends Pojazd {
  protected int liczbaKol; // 2
}
```

Nazwa pliku: `pojazdy/SamochodWyscigowy.java`

```java
package pojazdy;

public class SamochodWyscigowy extends Samochod {
  public SamochodWyscigowy() {
    this.liczbaKol = 4; // 3
  }
  public String toString() {
    return "Samochod wyscigowy, liczba kol: " + liczbaKol; // 4
  }
}
```

Klasa `SamochodWyscigowy` rozszerza klasę `Samochod`. Klasa `Samochod` posiada jedno pole z modyfikatorem `protected` (2), które klasa `SamochodWyscigowy` po niej dziedziczy – ustawiamy je w konstruktorze tej klasy (3) oraz wypisujemy w metodzie `toString` (4).

Ponadto klasa `SamochodWyscigowy` dziedziczy pośrednio po klasie `Pojazd`, która ma jedną publiczną metodę `jedz` (1). Tę metodę klasa `SamochodWyscigowy` także dziedziczy. Zobaczmy, jak moglibyśmy użyć tej klasy.

Nazwa pliku: `pojazdy/TorWyscigowy.java`

```java
package pojazdy;
public class TorWyscigowy {
  public static void main(String[] args) {
    SamochodWyscigowy wyscigowy = new SamochodWyscigowy();
    System.out.println(wyscigowy);
    wyscigowy.jedz();
  }
}
```

Wynik działania programu:

```
Samochod wyscigowy, liczba kol: 4
Pojazd jedzie.
```

Zarówno pól i metod prywatnych, jak i tych z dostępem domyślnym (tzn. gdy nie mają one zdefiniowanego żadnego modyfikatora dostępu), nie dziedziczy się. Próba odniesienia się z klasy pochodnej do pola bądź metody `private`, zdefiniowanych w klasie bazowej, kończy się błędem kompilacji:

```java
package pojazdy;

public class Pojazd {
  private String rejestracja; // 1
  public void jedz() {
    System.out.println("Pojazd jedzie.");
  }
}

package pojazdy;

public class SamochodWyscigowy extends Samochod {
  public SamochodWyscigowy() {
    this.liczbaKol = 4;
    // blad!
    // pole rejestracja jest prywatne, więc nie jest dziedziczone!
    this.rejestracja = "KJ-777"; // 2
  }
  public String toString() {
    return "Samochod wyscigowy, liczba kol: " + liczbaKol;
  }
}
```

Do klasy `Pojazd` dodaliśmy prywatne pole `rejestracja` (1). Próba ustawienia tego pola w konstruktorze klasy `SamochodWyscigowy` kończy się błędem kompilacji:

```
Error:(8, 9) java: rejestracja has private access in pojazdy.Pojazd
```

> Jeżeli klasa pochodna jest w tym samym pakiecie, co jej klasa bazowa, to będzie mimo wszystko miała dostęp do pól i metod z domyślnym modyfikatorem dostępu – w końcu takie właśnie zastosowanie ma ten modyfikator.
