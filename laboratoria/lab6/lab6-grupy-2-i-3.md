# Laboratorium 6 - rekordy, pieczęci i operator `instanceof`

## Rekordy

Pierwotnie język Java próbował wcielać w życie filozofię, według
której "wszystko jest obiektem". I choć nawet w pierwotnych wersjach
Javy ten ideał nie został osiągnięty (ze względu na wydajność typy
proste w rodzaju `int` albo `float`, reprezentujace wartości
numeryczne, zachowują się inaczej, niż obiekty), praktyka pokazała, że
obok obiektów (czyli czegoś, co ma "tożsamość, stan i zachowanie")
warto również dysponować pojęciem *złożonych wartości* (albo
*rekordów*).

Złożone wartości różnią się od obiektów tym, że ich tożsamość opiera
się nie na ich lokalizacji w pamięci, a na wartościach ich pól.  Tego
rodzaju efekt można uzyskać, przeciążając metody `boolean
equals(Object x)` oraz `int hashCode()` klasy `java.lang.Object`
(będacej klasą bazową każdej klasy definiowanej w Javie). Z powodu tej
implementacyjnej zbieżności tego rodzaju twory określa się niekiedy
mianem *value objects*. Warto jednak myśleć o pojęciach wartości i
obiektów jako o pojęciach rozłącznych, tzn. że dana zmienna odnosi się
albo do obiektu, albo do wartości (i w tym rozumieniu określenie
*value object* staje się *oksymoronem*).

Dojście do tego rozróżnienia zajęło twórcom Javy sporo czasu, i o ile
sam język Java narodził się w 1996 roku, o tyle składnia do
definiowania rekordów weszła do języka (jako *lukier składniowy*)
dopiero w 2021 roku, natomiast głębokie zmiany w architekturze maszyny
wirtualnej Javy zaczęły być dokonywane - w ramach projektu Valhalla -
od roku 2014, i w chwili pisania tych słów (listopad 2025) jeszcze nie
zostały zintegrowane z oficjalnie wydawanymi wersjami JVM.

(Osoby zainteresowane mogą w wolnej chwili obejrzeć prezentację
[Briana Goetza](https://www.youtube.com/watch?v=eL1yyTwu4hc) o rozwoju
projektu Valhalla)

Lukier składniowy do definiowania rekordów w Javie działa w taki sposób,
że kiedy napiszemy

```java
record Point2D(double x, double y) {}
```

kompilator zinterpretuje to jako klasę o następującej definicji:

```java
public final class Point2D extends java.lang.Record {

    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Point2D p) {
            return Double.compare(this.x, p.x) == 0
                && Double.compare(this.y, p.y) == 0;
	    }
		return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2D[x=" + x + ", y=" + y + "]";
    }
}
```

Widzimy, że w rozwinięciu pojawia się słowo kluczowe `final`, i jest
użyte w dwóch sensach:
- `final class` ozncza, że po tej klasie nie można dziedziczyć
- `final` przy zmiennej składowej oznacza, że wartość tej zmiennej
  po inicjalizacji nie może zostać zmieniona.

### Zadanie 1.

Napisz w sprawozdaniu, w jaki sposób kompilator zinterpretuje
definicję rekordu

```java
record Point3D(double x, double y, double z) {}
```
jako klasy i odpowiedz na pytanie: w jakich miejscach definicja
klasy `Point3D` różni się od definicji klasy `Point2D`?

## Klasy zapieczętowane

W rozwinięciu definicji klasy `Point2D` w metodzie `equals` pojawił
się operator `instanceof`, który sprawdza, czy dany obiekt jest
instancją danej klasy, albo czy implementuje określony interfejs.

W wielu sytuacjach użycie tego operatora jest *anty-wzorcem*, i
zazwyczaj najlepiej komunikować się z klasą za pomocą interfejsu,
stosując polimorfizm. (Implementacja metody `equals` jest wyjątkiem
od tej reguły.)

Warto jednak zwrócić uwagę, że polimorfizm w Javie powoduje, że
kod staje się trudniejszy w analizie, ponieważ:
- jego zachowanie będzie zależało od kodu, który jest rozproszony
  po wielu różnych klasach
- kod pozostaje *otwarty na rozszerzenia*, co oznacza, że w systemie
  mogą pojawić się nowe klasy, dodające nowe zachowanie.
  
Bycie otwartym na rozszerzenia zazwyczaj jest uznawane za zaletę
programowania obiektowego. Bywa jednak tak, że dziedzina obiektów, dla
których tworzymy system, jest zamknięta, i nie chcemy umożliwiać
dodawania nowych elementów implementujących interfejs.

Do tego celu służą w języku Javy dwa mechanizmy:
- *typy wyliczeniowe* (`enum`)
- *klasy zapieczętowane* (`sealed`)

Na dzisiejszych laboratoriach zapoznamy się z klasami zapieczętowanymi.

### Zadanie 2.

Mamy dany zapieczętowany interfejs:

```java
sealed interface Expression permits Constant, Sum, Product {
  float value();
}
```
- zdefiniuj klasę `Constant` implementującą interfejs `Expression`,
  mającą pole (prywatne, finalne) typu `float`, inicjalizowane poprzez
  parametr konstruktora.  Metoda `value()` powinna zwracać wartość
  tego pola.
  
- zdefiniuj klasę `Sum` implementującą interfejs `Expression`, mającą
  dwa pola (publiczne, finalne) typu `Expression`, inicjalizowane
  poprzez parametry konstruktora. Metoda `value()` powinna zwracać
  sumę wartości zwróconych przez wartości `value()` obu pól

- zdefiniuj klasę `Product` analogicznie do `Sum`, z tą różnicą, że
  metoda `value()` ma tym razem zwracać iloczyn.

- zdefiniuj klasę `Evaluator`, zawierającą metodę `main`, z której
  wypiszesz na konsolę wartość wyrażenia `(new Product(new Sum(new
  Constant(2), new Constant(2)), new Constant(2))).value()`. Uruchom
  kod i stwierdź, czy wynik jest zgodny z oczekiwaniami.

- dodaj do klasy `Evaluator` metodę statyczną `evaluate` pobierającą
  obiekt typu `Expression`, i używający operatora `instanceof`
  (względnie `switch ... case` w formie podanej
  [tutaj](https://docs.oracle.com/en/java/javase/17/language/pattern-matching-switch.html)
  na odpowiednio nowej wersji Javy), który używa funkcji `value()`
  tylko dla klasy `Constant`, natomiast dla klas `Sum` i `Product` -
  odpowiednio - operatorów `+` i `*`.  (Wskazówka: należy
  rekurencyjnie wywołać metodę `evaluate` na obu operandach sumy i
  iloczynu).  Wywołaj tę metodę z klasy `main` na tym samym/takim
  samym obiekcie, jak w poprzednim podpunkcie i stwierdź, czy wynik
  jest zgodny z oczekiwaniami.

Swoje definicje wklej do sprawozdania i odpowiedz na pytanie:
- jakie są zalety używania polimorfizmu (pierwsze rozwiązanie), a
  jakie - używania operatora `instanceof` na zapieczętowanej klasie?

