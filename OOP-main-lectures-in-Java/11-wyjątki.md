# Wyjątki w Java

## Spis treści
- [Stack trace](#stack-trace)
- [Zakres zmiennych definiowanych w bloku try](#zakres-zmiennych-definiowanych-w-bloku-try)
- [Metoda getInt i obsługa wyjątków](#metoda-getint-i-obsługa-wyjątków)
- [Definiowanie własnych wyjątków](#definiowanie-własnych-wyjątków)
- [Przerywanie wykonania bloku kodu przez wyjątki](#przerywanie-wykonania-bloku-kodu-przez-wyjątki)
- [Rzucanie wyjątków i nieosiągalny kod](#rzucanie-wyjątków-i-nieosiągalny-kod)
- [Rzucanie wyjątków a wartość zwracana z metody](#rzucanie-wyjątków-a-wartość-zwracana-z-metody)
- [Rzucanie wyjątków w try, catch, i finally](#rzucanie-wyjątków-w-try-catch-i-finally)
- [Ponowne rzucanie wyjątku](#ponowne-rzucanie-wyjątku)
- [Treść wyjątku, stack trace i inne pola i metody](#treść-wyjątku-stack-trace-i-inne-pola-i-metody)
- [Jak rozpoznać wyjątki Checked i Unchecked?](#jak-rozpoznać-wyjątki-checked-i-unchecked)
- [Dlaczego istnieją dwa rodzaje wyjątków?](#dlaczego-istnieją-dwa-rodzaje-wyjątków)
- [Sprawdzanie wyjątków rzucanych przez metodę](#sprawdzanie-wyjątków-rzucanych-przez-metodę)
- [Jak sprawdzić rodzaj wyjątku](#jak-sprawdzić-rodzaj-wyjątku)
- [Błędy kompilacji podczas braku obsługi wyjątków Checked](#błędy-kompilacji-podczas-braku-obsługi-wyjątków-checked)
- [Błąd Error](#błąd-error)
- [Łapanie wyjątków za pomocą klasy bazowej](#łapanie-wyjątków-za-pomocą-klasy-bazowej)
- [Łapanie kilku wyjątków za pomocą znaku |](#łapanie-kilku-wyjątków-za-pomocą-znaku-)
- [Kolejność łapania wyjątków ma znaczenie](#kolejność-łapania-wyjątków-ma-znaczenie)
- [Silent catch](#silent-catch)
- [Pomijanie try..catch w metodzie main](#pomijanie-trycatch-w-metodzie-main)
- [Dlaczego używać mechanizmu wyjątków?](#dlaczego-używać-mechanizmu-wyjątków)
- [Wady wyjątków](#wady-wyjątków)
- [Podstawy wyjątków](#podstawy-wyjątków)
- [Łapanie wyjątków](#łapanie-wyjątków)
- [Rodzaje wyjątków](#rodzaje-wyjątków)
- [Definiowanie i rzucanie wyjątków](#definiowanie-i-rzucanie-wyjątków)
- [Sprawdzanie rzucanych wyjątków i ich rodzaju](#sprawdzanie-rzucanych-wyjątków-i-ich-rodzaju)

Skorzystajmy z następującego przykładu:
Nazwa pliku: WypiszWynikDzielenia.java

```java
public class WypiszWynikDzielenia {
  public static void main(String[] args) {
    wypiszWynikDzielenia(10, 0);
    wypiszWynikDzielenia(25, 5);
  }
  public static void wypiszWynikDzielenia(int x, int y) {
    if (y == 0) {
      System.out.println("Nie mozna dzielic przez 0!");
      return;
    }
    System.out.println("Wynik dzielenia: " + (x / y));
  }
}
```

W metodzie wypiszWynikDzielenia wykonujemy sprawdzenie, czy liczba przekazana w argumencie y nie jest przypadkiem równa 0 – w takim przypadku nie możemy wykonać dzielenia.

Załóżmy, że nasza metoda ma zwracać wartość dzielenia, a nie ją wypisywać – zmodyfikujmy nasz przykład:
Nazwa pliku: ZwrocWynikDzielenia.java

```java
public class ZwrocWynikDzielenia {
  public static void main(String[] args) {
    System.out.println(podziel(10, 0));
    System.out.println(podziel(25, 5));
  }
  public static int podziel(int x, int y) {
    return x / y;
  }
}
```

Metodę wypiszWynikDzielenia zastąpiliśmy metodą podziel, która zamiast wypisywać wynik na ekran, zwraca go. Jaki będzie wynik działania powyższego kodu? Na ekranie zobaczymy komunikat o błędzie dzielenia przez zero:
Exception in thread "main" java.lang.ArithmeticException: / by zero
```text
    at ZwrocWynikDzielenia.podziel(ZwrocWynikDzielenia.java:8)
    at ZwrocWynikDzielenia.main(ZwrocWynikDzielenia.java:3)
```

Ten błąd to właśnie wyjątek, który został spowodowany nieprawidłowym działaniem naszego programu – w tym przypadku, Maszyna Wirtualna Java poinformowała nas, że wystąpił błąd dzielenia przez zero, a ten konkretny wyjątek nazywa się ArithmeticException.

Wyjątki (exceptions) to sytuacje, w których coś w programie poszło nie tak. Gdy zajdzie taka sytuacja, mówimy, że został rzucony wyjątek. Wyjątki mogą być rzucane zarówno przez Maszynę Wirtualna Java, jak i przez nas – programistów – co zobaczymy w dalszej części rozdziału.

Bardzo ważną cechą wyjątków jest to, że są to tak naprawdę klasy – mają one swoją nazwę, konstruktory, pola i metody. Co cechuje klasę, że może być traktowana jako wyjątek? Klasa taka musi rozszerzać klasę Throwable lub jedną z jej pochodnych, o czym wkrótce dokładniej sobie opowiemy. Rzucanie wyjątków sprowadza się do utworzenia słowem kluczowym new obiektu konkretnej klasy wyjątku i "rzucenie" go za pomocą słowa kluczowego throw. Zajmiemy się tymi zagadnieniami w kolejnych rozdziałach.

Z wyjątkami można się spotkać w innych sytuacjach:

```text
StringIndexOutOfBoundsException – gdy próbujemy odnieść się do znaku w zmiennej typu String za pomocą metody charAt przekazując indeks znaku wychodzący poza zakres stringu,
ArrayIndexOutOfBoundsException – gdy odnosimy się do nieistniejącego elementu tablicy,
NullPointerException – gdy próbujemy odnosić się do pól bądź metod niezainicjalizowanego obiektu, tzn. gdy zmienna typu złożonego wskazywała na null.
```


Inne sytuacje, w których moglibyśmy natknąć się na wyjątek, to na przykład:

```text
podanie ujemnego wieku podczas tworzenia obiektu typu Osoba,
próba otwarcia pliku, który nie istnieje,
zerwanie połączenia z internetem podczas próby wysłania danych na serwer,
podanie nieprawidłowego hasła podczas łączenia się do serwera baz danych,
i wiele innych.
```

Sytuacje wyjątkowe możemy obsługiwać dzięki mechanizmowi łapania wyjątków, który poznamy w tym rozdziale.
## Stack trace

Zauważmy w przykładzie powyżej, jak Maszyna Wirtualna Java prezentuje wyjątek:
Exception in thread "main" java.lang.ArithmeticException: / by zero
```text
    at ZwrocWynikDzielenia.podziel(ZwrocWynikDzielenia.java:8)
    at ZwrocWynikDzielenia.main(ZwrocWynikDzielenia.java:3)
```

Po rodzaju wyjątku i skojarzonym z nim komunikatem w pierwszej linii, następują informacje o ścieżce wykonania programu, która doprowadziła do występienia tego wyjątku.

Jest to tzw. stack trace – ścieżka wykonań metod, które doprowadziły do błędu. Często będziemy analizować stack trace'y programując w Javie.

Stack trace powinno się śledzić się od dołu, ponieważ prezentowana w nim kolejność metod jest odwrotna do kolejności wykonywania tych metod – ostatnia metoda (ta na dole stack trace'a) została wywołana jako pierwsza, a ta na samej górze – jako ostatnia – i to w niej rzucony został wyjątek.

W praktyce jednak patrzymy zazwyczaj na kilka pierwszych linii stack trace'a, bo zazwyczaj wystarczają nam one do zrozumienia dlaczego, a przynajmniej gdzie, wystąpił wyjątek.

W naszym przypadku najpierw wywołana została metoda main z klasy ZwrocWynikDzielenia:
at ZwrocWynikDzielenia.main(ZwrocWynikDzielenia.java:3)

W nawiasach mamy dodatkowo podany plik, z którego klasa pochodzi, a także linię kodu, w której wykonanie metody przeszło do kolejnej metody – ta metoda, jak widzimy patrząc dalej na stack trace, to podziel:
at ZwrocWynikDzielenia.podziel(ZwrocWynikDzielenia.java:8)

Więcej metod nie zostało wywołanych – oznacza to, że wyjątek został rzucony właśnie w metodzie podziel. Dodatkowo mamy także podany numer 8 w nawiasach – to numer linii programu (a nie linii metody), w której wyjątek został rzucony. Są to bardzo przydatne informacje dla nas, programistów, podczas analizy błędów zaistniałych w naszych programach – dzięki stack trace'om łatwiej znaleźć miejsce, gdzie program zadziałał nieprawidłowo.

Jeżeli spojrzymy ponownie na kod naszej klasy:
Nazwa pliku: ZwrocWynikDzielenia.java

```java
public class ZwrocWynikDzielenia {
  public static void main(String[] args) {
    System.out.println(podziel(10, 0));
    System.out.println(podziel(25, 5));
  }
  public static int podziel(int x, int y) {
    return x / y;
  }
}
```

to zobaczymy, że linia nr 3 umieszczona w nawiasach w stack trace odnosi się do:

```java
System.out.println(podziel(10, 0));
```

a linia 8 do:

```java
return x / y;
```

Oznaczenia linii w stack trace zgadzają się z wykonaniem programu, które doprowadziło do zaistnienia wyjątku ArithmeticException:

```text
program zostaje uruchomiony – rozpoczyna się wykonywanie metody main,
w pierwszej linii metody main (zauważmy, że jest to jednocześnie trzecia linia całego programu) następuje wywołanie metody podziel z argumentami 10 i 0,
wykonanie programu przechodzi do metody podziel,
z racji tego, że podaliśmy 0 jako argument y, Maszyna Wirtualna Java rzuca wyjątek ArithmeticException, gdy próbujemy wykonać dzielenie przez 0 – dzieje się to w ósmej linii programu,
program kończy działanie, a na ekran zostaje wypisany zaistniały błąd: typ wyjątku, jego komunikat, oraz omówiony już stack trace.
```

Zastanówmy się teraz, jak powinien działać nasz program, aby obsłużyć sytuację, gdy przesłany zostanie nieprawidłowy argument. Czy program powinien:

```text
kończyć się błędem tak jak do tej pory?
zwracać 0?
zwracać jakąś inną wartość, np. najmniejszą z możliwych wartości, jakie może przechowywać zmienna typu int?
```

Żadne z powyższych rozwiązań nie jest dobre – nie chcemy, aby program kończył się błędem, a zwracanie pewnej wartości na sztywno nie jest dobrym rozwiązaniem, bo przecież zarówno zero, jak i najmniejsza liczba, jaką może przechowywać typ int, mogą potencjalnie być wynikiem działania metody podziel.

Co zatem możemy poradzić na taką sytuację wyjątkową? Wiedząc już, czym są wyjątki, możemy obsłużyć wyjątek dzielenia przez zero, by nasz program nie kończył się błędem. Spójrzmy na zmodyfikowany przykład aby zobaczyć mechanizm łapania wyjątków w akcji:
Nazwa pliku: ZwrocWynikDzieleniaWyjatek.java

```java
public class ZwrocWynikDzieleniaWyjatek {
  public static void main(String[] args) {
    try {
      System.out.println(podziel(10, 0));
    } catch (ArithmeticException e) {
      System.out.println("Nie wolno dzielic przez 0!");
    }
  }
  public static int podziel(int a, int b) {
    return a / b;
  }
}
```

Tym razem na ekranie zobaczymy:
Nie wolno dzielic przez 0!

W metodzie main użyliśmy mechanizmu try..catch do łapania i obsługiwania wyjątków. W naszym przypadku, gdy w metodzie podziel próbujemy wykonać dzielenie przez 0, Maszyna Wirtualna Java rzuci wyjątek ArithmeticException, który następnie możemy obsłużyć dzięki skorzystaniu z try..catch.

Używając instrukcji try spodziewamy się, że w instrukcjach objętych przez try coś może pójść nie tak (ale nie musi). To, co powinno się zadziać w przypadku napotkania konkretnego problemu (i tylko wtedy), umieszczamy w sekcji catch:

```text
najpierw w nawiasach podajemy typ wyjątku, którego się spodziewamy i który chcemy obsłużyć – w naszym przypadku jest to błąd ArithmeticException; po typie następuje nazwa zmiennej, za pomocą której do tego obiektu-wyjątku będziemy się mogli odnieść – zazwyczaj, tak jak powyżej, ta zmienna nazywana jest po prostu e,
następnie, w nawiasach klamrowych umieszczamy instrukcje, które chcemy wykonać wyłącznie w przypadku, gdy dany wyjątek zostanie rzucony – w powyższym przykładzie jest to tylko jedna instrukcja informująca, że nie można dzielić przez 0.
```

Mechanizm łapania wyjątków ma następującą składnię:

```java
try {
  // instrukcje ktore moga potencjalnie zakonczyc sie wyjatkiem
} catch (TypWyjatku dowolnaNazwa) {
  // instrukcje, gdy zajdzie wyjątek TypWyjatku
} catch (KolejnyTypWyjatku dowolnaNazwa2) {
  // instrukcje, gdy zajdzie wyjątek KolejnyTypWyjatku
} finally {
  // instrukcje, ktore maja byc wykonane niezaleznie od tego,
  // czy wyjatek zostal zlapany, czy nie
}
```

Jak widzimy, możemy zdefiniować obsłużenie większej liczby wyjątków poprzez dopisywanie kolejnych bloków catch – może ich być dowolnie wiele. W nawiasach dla każdego z bloków catch podajemy, jaki typ wyjątku chcemy obsłużyć oraz nadajemy mu nazwę, ponieważ sam wyjątek jest obiektem (zmienną), którą możemy odpytać o informacje związane z zaistniałym błędem. Dla przykładu, możemy wyświetlić komunikat błędu:
fragment pliku ZwrocWynikDzieleniaWyjatek.java

```java
try {
  System.out.println(podziel(10, 0));
} catch (ArithmeticException e) {
  System.out.println("Nie wolno dzielic przez 0!");
  System.out.println("Wystapil blad: " + e.getMessage());
}
```

W wyniku czego zobaczymy na ekranie:
Nie wolno dzielic przez 0!
Wystapil blad: / by zero

Fragment wypisanego tekstu "/ by zero" to opis błędu, który wystąpił. Opis ten przechowywany jest w obiekcie-wyjątku, który nazwaliśmy e. Metoda getMessage ten opis zwraca.

Mechanizm łapania wyjątków oferuje jeszcze jedną funkcjonalność – możemy w opcjonalnym bloku finally umieścić instrukcje, które mają zawsze się wykonać, niezależnie od tego, czy wyjątek zostanie złapany, czy nie. Blok finally zazwyczaj używany jest do czyszczenia zasobów, np. zamykania połączeń z bazą danych czy zamykania otwartych plików.

Istotne jest zrozumienie kolejności wykonywania instrukcji w blokach try..catch..finally:

```java
Najpierw wywoływane są instrukcje w bloku try. Jeżeli któraś z instrukcji spowoduje rzucenie wyjątku, to:
    Wykonanie bloku try zostaje przerwane – wszystkie instrukcje następujące po instrukcji, która spowodowała wyjątek, nie zostaną wykonane.
    Typ rzuconego wyjątku jest dopasowywany do wyjątków zdefiniowanych w sekcjach catch. Zostają wykonane instrukcje przyporządkowane do pierwszej sekcji catch, do której rzucony wyjątek został dopasowany. Jeżeli wyjątek, który wystąpił, nie jest obsługiwany w żadnym z bloków catch, to przechodzimy do kroku 2.
Wywoływane są instrukcje z bloku finally, jeżeli blok ten jest obecny, niezależnie od tego, czy wyjątek wystąpił, czy nie.
```

Spójrzmy na dwa poniższe przykłady:
fragment pliku ZwrocWynikDzieleniaWyjatek.java

```java
try {
  System.out.println("Zaraz podzielimy 10 przez 2");
  System.out.println("Wynik dzielenia: " + podziel(10, 2));
  System.out.println("Podzielilismy 10 przez 2");
} catch (ArithmeticException e) {
  System.out.println("Wystapil blad podczas dzielenia przez 2!");
} finally {
  System.out.println("Blok try..catch..finally zakonczony!");
}
```

Ten przykład spowoduje wypisanie na ekran:
Zaraz podzielimy 10 przez 2
Wynik dzielenia: 5
Podzielilismy 10 przez 2
Blok try..catch..finally zakonczony!

Jak widzimy, komunikat z sekcji catch nie został wypisany, ponieważ żaden wyjątek nie wystąpił.

Spójrzmy na drugi, ciekawszy przykład:
fragment pliku ZwrocWynikDzieleniaWyjatek.java

```java
try {
  System.out.println("Zaraz podzielimy 10 przez ZERO!");
  System.out.println("Wynik dzielenia: " + podziel(10, 0));
  System.out.println("Podzielilismy 10 przez ZERO!"); // 1
} catch (ArithmeticException e) {
  System.out.println("Wystapil blad podczas dzielenia przez ZERO!"); // 2
} finally {
  System.out.println("Blok try..catch..finally zakonczony!");
}
```

Tym razem na ekranie zobaczymy:
Zaraz podzielimy 10 przez ZERO!
Wystapil blad podczas dzielenia przez ZERO!
Blok try..catch..finally zakonczony!

Tym razem nie została wykonana instrukcja (1), która następowała po instrukcji dzielenia. Nie zobaczyliśmy na ekranie napisu "Podzielilismy 10 przez ZERO!", ponieważ w momencie rzucenia wyjątku wykonanie programu przeszło od razu do obsługi wyjątku. Z racji tego, że wyjątek wystąpił, zobaczyliśmy napis wypisywany w obsłudze wyjątku (2).

Zwróćmy uwagę, że niezależnie od tego, czy wyjątek wystąpił, czy nie, w obu przykładach wykonany został kod z części finally.
Kod z bloku finally nie zostanie wykonany w szczególnym przypadku – gdy użyjemy metody exit z klasy System, ponieważ natychmiast kończy ona nasz program.

## Zakres zmiennych definiowanych w bloku try

Wielokrotnie przy okazji omawiania instrukcji warunkowych, pętli, metod itp. widzieliśmy, że zmienne definiowane wewnątrz bloków kodu są niewidoczne poza tymi blokami, jeżeli nie wskazuje na nie żadna referencja spoza tego bloku. Jeżeli zdefiniujemy zmienną w bloku try, to po zakończeniu tego bloku przestanie ona istnieć – nie będziemy mieli do niej dostępu nawet w sekcjach catch i finally – spójrzmy na przykład:
fragment metody main z pliku ZwrocWynikDzieleniaWyjatek.java

```java
try {
  int wynik = podziel(10, 2);
} catch (ArithmeticException e) {
  System.out.println("Blad dzielenia, zmienna wynik ma wartosc: " + wynik);
} finally {
  System.out.println("Sekcja finally: wynik wynosi " + wynik);
}
```

Ten fragment kodu powoduje następujące błędy kompilacji:
```text
ZwrocWynikDzieleniaWyjatek.java:36: error: cannot find symbol
  System.out.println("Blad dzielenia, zmienna wynik ma wartosc: " + wynik);

                                                                    ^

  symbol:   variable wynik
  location: class ZwrocWynikDzieleniaWyjatek
ZwrocWynikDzieleniaWyjatek.java:38: error: cannot find symbol
```

```text
  System.out.println("Sekcja finally: wynik wynosi " + wynik);
                                                       ^

  symbol:   variable wynik
  location: class ZwrocWynikDzieleniaWyjatek
2 errors
```
W sekcjach catch i finally próbujemy odnieść się do nieistniejącej zmiennej – zmienna wynik istnieje jedynie w bloku try, bo w nim została zdefiniowana.

W praktyce często zachodzi potrzeba korzystania "na zewnątrz" sekcji try z tworzonych w niej zmiennych, czy też w sekcji finally. W takich przypadkach należy zdefiniować zmienną przed sekcją try:
fragment metody main z pliku ZwrocWynikDzieleniaWyjatek.java

```java
int wynik = 0;
try {
  wynik = podziel(10, 2);
} catch (ArithmeticException e) {
  System.out.println("Blad dzielenia, zmienna wynik ma wartosc: " + wynik);
} finally {
  System.out.println("Sekcja finally: wynik wynosi " + wynik);
}
System.out.println("Po try wynik wynosi " + wynik);
```

Wynik działania:
Sekcja finally: wynik wynosi 5
Po try wynik wynosi 5

Tym razem kod skompilował i wykonał się bez problemów.

Zauważmy jeszcze jedną istotną cechę powyższego kodu – zmienna wynik została zainicjalizowana wartością 0. Jeżeli byśmy tego nie zrobili, to kod ponownie by się nie skompilował. Kompilator zgłosiłby błąd variable wynik might not have been initialized. Powodem błędu byłby fakt, że metoda podziel może rzucić wyjątek i nie zwrócić żadnej wartości – w takim przypadku zmiennej wynik nie zostałaby przypisana żadna wartość, a jak wiemy z rozdziału o zmiennych – zmienne lokalne muszą być zainicjalizowane wartością przed użyciem. Dlatego przed blokiem try, w linii, w której definiujemy zmienną wynik, nadajemy jej od razu wstępną wartość. Jeżeli korzystalibyśmy z zmiennej typu złożonego, to jako wartość domyślną moglibyśmy przypisać np. null.
## Metoda getInt i obsługa wyjątków

W poprzednich rozdziałach wielokrotnie korzystaliśmy z metody getInt, która miała za zadanie zwrócić pobraną od użytkownika liczbę. Co się jednak działo w sytuacjach, gdy przez przypadek podaliśmy nieprawidłową liczbę?

```java
import java.util.Scanner;
public class GetIntObslugaWyjatkow {
  public static void main(String[] args) {
    System.out.print("Podaj liczbe: ");
    int x = getInt();
    int kwadrat = x * x;
    System.out.println("Kwadrat tej liczby wynosi " + kwadrat);
  }
  public static int getInt() {
    return new Scanner(System.in).nextInt();
  }
}
```

Jeżeli po uruchomieniu powyższego programu podamy np. "kot" zamiast liczby, to program zakończy się następującym błędem:
Podaj liczbe: kot
Exception in thread "main" java.util.InputMismatchException
```text
    at java.base/java.util.Scanner.throwFor(Scanner.java:939)
    at java.base/java.util.Scanner.next(Scanner.java:1594)
    at java.base/java.util.Scanner.nextInt(Scanner.java:2258)
    at java.base/java.util.Scanner.nextInt(Scanner.java:2212)
    at GetIntObslugaWyjatkow.getInt(GetIntObslugaWyjatkow.java:13)
    at GetIntObslugaWyjatkow.main(GetIntObslugaWyjatkow.java:6)
```

Teraz już wiemy, że ten błąd to rzucony wyjątek. W tym przypadku, nazywa się on InputMismatchException, które zdefiniowany jest w bibliotece standardowej w pakiecie java.util.

Wiedząc już, jak obsługiwać wyjątki, możemy zmodyfikować nasz program, by brał pod uwagę możliwość podania przez użytkownika nieprawidłowej liczby. Jak jednak powinniśmy taką sytuację obsłużyć?

Możemy w pętli próbować pobrać od użytkownika liczbę – jeżeli wyjątek zostanie rzucony, poinformujemy użytkownika o nieprawidłowo podanej wartości i spróbujemy pobrać ją kolejny raz w następnym obiegu pętli. Gdy użytkownik poda poprawną liczbę, zmienimy zmienną warunkującą wykonanie pętli, by pętla już więcej się nie wykonywała.

Spójrzmy, jak mogłaby wyglądać implementacja:
Nazwa pliku: GetIntObslugaWyjatkow.java

```java
import java.util.InputMismatchException; // 1
import java.util.Scanner;
public class GetIntObslugaWyjatkow {
  public static void main(String[] args) {
    boolean wartoscPodana = false; // 2
    int x = 0;
    while (!wartoscPodana) { // 3
      try {
        System.out.print("Podaj liczbe: ");
        x = getInt(); // 4
        wartoscPodana = true; // 5
      } catch (InputMismatchException e) { // 6
        System.out.println("To nie jest liczba!");
      }
    }
    int kwadrat = x * x;
    System.out.println("Kwadrat tej liczby wynosi " + kwadrat);
  }
  public static int getInt() {
    return new Scanner(System.in).nextInt();
  }
}
```

Najpierw dodajemy importowanie typu wyjątku, który będziemy łapać (1). Wyjątek ten nie znajduje się w pakiecie java.lang, lecz java.util, dlatego musimy sami dodać jego import do naszego programu.

Następnie definiujemy w metodzie main zmienne wartoscPodana i x (2). Pierwsza będzie wskazywała, czy użytkownik podał już poprawną wartość, czy jeszcze nie. Druga zmienna przechowuje wartość od użytkownika.

W pętli while (3) wykonujemy próbę pobrania liczby od użytkownika (4) tak długo, aż nie poda on poprawnej liczby. Jeżeli użytkownik poda poprawną liczbę, to w linii (5) ustawimy wartoscPodana na true, dzięki czemu pętla nie wykona się więcej razy. Jeżeli jednak użytkownik poda np. literę zamiast liczby, to wywołanie metody getInt zakończy się rzuceniem wyjątku InputMismatchException, który obsługujemy w linii (6). W tym przypadku informujemy użytkownika, że podał nieprawidłową liczbę. Instrukcja try..catch się kończy i przechodzimy do kolejnego obiegu pętli – w zależności od tego, jaką wartość podał użytkownik, wykona się ona ponownie lub zakończy działanie.

Na końcu programu liczymy kwadrat pobranej liczby i wypisujemy wynik.

Przykładowe wykonanie tego programu:
```text
Podaj liczbe: kot
To nie jest liczba!
Podaj liczbe: pies
To nie jest liczba!
Podaj liczbe: 5
Kwadrat tej liczby wynosi 25
```
W poprzednich przykładach, wyjątek ArithmeticException rzucany był przez Maszynę Wirtualną Java, jednak nie jest to jedyna możliwość rzucania wyjątków – my, jako programiści, możemy sami rzucać wyjątki z naszych metod.

Rzucanie wyjątków odbywa się poprzez użycie słowa kluczowego throw, po którym następuje tworzenie obiektu wyjątku takiego typu, jaki chcemy rzucić. Spójrzmy na przykład obsługi sytuacji, gdy ktoś poda ujemny wiek podczas tworzenia obiektu typu Osoba:
Nazwa pliku: Osoba.java

```java
public class Osoba {
  private String imie;
  private String nazwisko;
  private int wiek;
  public Osoba(String imie, String nazwisko, int wiek) {
    this.imie = imie;
    this.nazwisko = nazwisko;
    if (wiek <= 0) {
      throw new IllegalArgumentException("Wiek nie moze byc ujemny."); // 1
    }
    this.wiek = wiek;
  }
  public static void main(String[] args) {
    try {
      Osoba o = new Osoba("Jan", "Nowak", -1); // 2
    } catch (IllegalArgumentException e) { // 3
      System.out.println("Wystapil blad! " + e.getMessage());
    }
  }
}
```

Wynik działania tego programu to:
Wystapil blad! Wiek nie moze byc ujemny.

W konstruktorze klasy Osoba sprawdzamy, czy wiek jest niepoprawny – jeżeli tak (1), to rzucamy wyjątek za pomocą składni:

```java
throw new IllegalArgumentException("Wiek nie moze byc ujemny.");
```

Zauważmy, że po słowie kluczowym throw umieszczamy wyjątek, jaki ma zostać rzucony – w tym przypadku tworzymy nowy wyjątek typu IllegalArgumentException, zdefiniowany w bibliotece standardowej Java. Jako parametr konstruktora możemy podać opcjonalny komunikat błędu.

Rzucać możemy wyjątki dowolnego typu – zarówno te zdefiniowane już w bibliotece standardowej Java jak i zdefiniowane przez nas. Wyjątek IllegalArgumentException to często stosowany wyjątek mający na celu wskazanie, że pewne dane są nieprawidłowe, tak jak w powyższym przypadku. Wiek nie może być ujemny, a taką wartość przekazujemy do konstruktora klasy Osoba (2). Wyjątek łapiemy w sekcji catch (3) i obsługujemy, wypisując na ekran komunikat.

Wyjątek to nic innego jak obiekt konkretnej klasy – tej, której wyjątek chcemy zasygnalizować. Tworzymy go tak jak wszystkie obiekty do tej pory – za pomocą słowa kluczowego new:

```java
throw new IllegalArgumentException("Dzielnik nie moze byc rowny 0.");
```

Równie dobrze moglibyśmy powyższy kod zapisać jako:

```java
IllegalArgumentException exc =
    new IllegalArgumentException("Dzielnik nie moze byc rowny 0.");
throw exc;
```

Stosujemy jednak to pierwsze podejście, ponieważ jest krótsze.
## Definiowanie własnych wyjątków

Możemy zdefiniować na własne potrzeby nowe typy wyjątków, ale najpierw wyjaśnijmy, czym w ogóle są wyjątki?

Wyjątki to pochodne klasy Throwable lub jednej z jej klas pochodnych, np. Exception lub RuntimeException. Klasy te są zdefiniowane w bibliotece standardowej Java. To właśnie jedna bądź druga z tych klas pochodnych jest używana jako klasa bazowa dla wyjątków definiowanych przez programistów. Kiedy stosować każdą z nich zobaczymy w jednym z kolejnych rozdziałów, a w tym skupimy się na dziedziczeniu po klasie Exception.

Gdy definiujemy w blokach catch wyjątki do obsłużenia, muszą być one pochodnymi klasy Exception lub RuntimeException (pośrednio bądź bezpośrednio) – inaczej kod się sie skompiluje. Spójrzmy na najprostszy przykład zdefiniowania własnego wyjątku:
Nazwa pliku: NieprawidlowyWiekException.java

```java
class NieprawidlowyWiekException extends Exception {
}
```

Zdefiniowaliśmy tutaj nową klasę wyjątków – NieprawidlowyWiekException – od teraz możemy łapać wyjątki tego typu w blokach catch. Aby wskazać, że nasz wyjątek dziedziczy po (rozszerza) klasę Exception, użyliśmy słowa kluczowego extends, poznanego w poprzednim rozdziale.
Zgodnie z konwencją nazewniczą klas wyjątków, na końcu nazwy wyjątku dodaliśmy słowo Exception.

Możemy także w prosty sposób umożliwić zapisywanie w wyjątku naszego typu komunikatu błędu. Utwórzmy jeszcze jedną klasę wyjątków:
Nazwa pliku: NieprawidlowaWartoscException.java

```java
public class NieprawidlowaWartoscException extends Exception {
  public NieprawidlowaWartoscException(String message) {
    // wywolaj konstruktor z klasy bazowej (czyli z Exception)
    super(message);
  }
}
```

W tej klasie zdefiniowaliśmy konstruktor, który przyjmuje jako argument komunikat błędu – przesyłamy go do konstruktora klasy bazowej za pomocą słowa kluczowego super z poprzedniego rozdziału. Konstruktor z klasy bazowej, Exception, zapisze komunikat w polu, które będzie dostępne za pomocą metody getMessage. getMessage to metoda, którą nasza klasa wyjątku dziedziczy po klasie bazowej.

Spróbujmy dodać do konstruktora klasy Osoba walidację pól imie oraz wiek – sprawdzimy, czy mają wartość null – jeżeli tak, to rzucimy wyjątek NieprawidlowaWartoscException z odpowiednim komunikatem. Użyjemy także wyjątku NieprawidlowyWiekException:
Nazwa pliku: Osoba.java

```java
public class Osoba {
  private String imie;
  private String nazwisko;
  private int wiek;
  public Osoba(String imie, String nazwisko, int wiek) {
    if (imie == null) { // 1
      throw new NieprawidlowaWartoscException(
          "Imie nie moze byc puste."
      );
    }
    if (nazwisko == null) { // 2
      throw new NieprawidlowaWartoscException(
          "Nazwisko nie moze byc puste."
      );
    }
    if (wiek <= 0) {
      throw new NieprawidlowyWiekException();
    }
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.wiek = wiek;
  }
  public static void main(String[] args) {
    try {
      Osoba o = new Osoba("Jan", "Nowak", -1);
    } catch (IllegalArgumentException e) {
      System.out.println("Wystapil blad! " + e.getMessage());
    }
  }
}
```

Dodaliśmy do konstruktora sprawdzanie wartości imie (1) oraz nazwisko (2). Jednakże podczas próby kompilacji klasy kompilator zaczyna zgłaszać problemy:
Osoba.java:8: error: unreported exception NieprawidlowaWartoscException; must be caught or declared to be thrown
```java
  throw new NieprawidlowaWartoscException(
  ^
```
Osoba.java:14: error: unreported exception NieprawidlowaWartoscException; must be caught or declared to be thrown
```java
  throw new NieprawidlowaWartoscException(
  ^
```
Osoba.java:20: error: unreported exception NieprawidlowyWiekException; must be caught or declared to be thrown
```java
  throw new NieprawidlowyWiekException();
  ^
```
3 errors

Co się stało? Używaliśmy już wcześniej wyjątków i nie napotkaliśmy takiego błędu. Otóż w naszym kodzie brakuje jeszcze jednego elementu.

Wyjątki dzielą się na dwa rodzaje – Checked oraz Unchecked Exceptions. O różnicy opowiemy sobie w kolejnym rozdziale. To, co musimy teraz wiedzieć, to to, że w przeciwieństwie do wyjątków Unchecked, takich jak IllegalArgumentException łapanych wcześniej w tym rozdziale, potencjał rzucenia wyjątków rodzaju Checked musi zostać zdefiniowany w sygnaturze metody, która może go rzucić. Służy do tego słowo kluczowe throws.
W kolejnym rozdziale dokładnie sobie omówimy wyjątki Checked oraz Unchecked. Dla dociekliwych – wyjątki Unchecked to wyjątki, które dziedziczą po klasie Runtime Exception. Pozostałe wyjątki to wyjątki Checked.

Spójrzmy na sygnaturę poprawionego konstruktora:

```java
public Osoba(String imie, String nazwisko, int wiek)
    throws NieprawidlowaWartoscException, NieprawidlowyWiekException {
```

Po nawiasie zamykającym definicję argumentów konstruktora, a przed klamrą { otwierająca ciało metody, napisaliśmy słowo kluczowe throws, po którym wypisaliśmy, rozdzielone przecinkami, nazwy typów wyjątków, które nasza metoda może rzucić. Jest to wymagane, gdy istnieje potencjał rzucenia wyjątku rodzaju Checked – kompilator Java nie pozwoli skompilować kodu, jeżeli zabraknie klauzuli throws, gdy kompilator zauważy, że metoda może rzucić wyjątek/wyjątki.
Kompilator wie, że wyjątki mogą być rzucone, bo analizując podczas kompilacji kod konstruktora klasy Osoba widzi użycie słowa kluczowego throw do rzucenia wyjątków.
Nie pomyl słów kluczowych throw i throws – pierwsze rzuca wyjątek, a drugie służy do zdefiniowania, jakie wyjątki metoda może rzucić.

Czy teraz kod klasy Osoba się skompiluje? Jeszcze nie – zobaczymy następujący komunikat kompilatora:
Osoba.java:31: error: unreported exception NieprawidlowaWartoscException; must be caught or declared to be thrown
```java
  Osoba o = new Osoba("Jan", "Nowak", -1);
            ^
```
1 error

Tym razem kompilator wiedząc, że konstruktor klasy Osoba może rzucić wyjątki, oczekuje od nas, że korzystając z tego konstruktora weźmiemy te potencjalne wyjątki pod uwagę – innymi słowy, kompilator oczekuje użycia try..catch i obsługi wyjątków NieprawidlowaWartoscException oraz NieprawidlowyWiekException (chociaż w powyższym komunikacie widnieje nazwa tylko jednego z nich, to po dodaniu jego obsługi w catch kompilator przy kolejnej próbie kompilacji wskazałby, że wyjątek NieprawidlowyWiekException także należy obsłużyć).
Ponownie widzimy tutaj różnicę między wyjątkami Checked i Unchecked. Wcześniej w rozdziale, gdy metoda podziel mogła rzucić wyjątek, nie musieliśmy tego wyjątku obsługiwać, bo IllegalArgumentException to wyjątek rodzaju Unchecked. W konstruktorze klasy Osoba korzystamy natomiast z wyjątków rodzaju Checked, które muszą zostać obsłużone – stąd powyższy błąd kompilacji.

Spójrzmy na kompletny przykład wraz z dodaną obsługą wyjątków za pomocą try..catch:
Nazwa pliku: Osoba.java

```java
public class Osoba {
  private String imie;
  private String nazwisko;
  private int wiek;
  public Osoba(String imie, String nazwisko, int wiek)
      throws NieprawidlowaWartoscException, NieprawidlowyWiekException {
    if (imie == null) {
      throw new NieprawidlowaWartoscException(
          "Imie nie moze byc puste."
      );
    }
    if (nazwisko == null) {
      throw new NieprawidlowaWartoscException(
          "Nazwisko nie moze byc puste."
      );
    }
    if (wiek <= 0) {
      throw new NieprawidlowyWiekException();
    }
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.wiek = wiek;
  }
  public static void main(String[] args) {
    try {
      Osoba o = new Osoba("Jan", "Nowak", -1);
    } catch (NieprawidlowaWartoscException e) { // 1
      System.out.println("Nieprawidlowa wartosc: " + e.getMessage());
    } catch (NieprawidlowyWiekException e) { // 2
      System.out.println("Nieprawidlowy wiek!");
    }
    try {
      Osoba o = new Osoba(null, "Nowak", 30);
    } catch (NieprawidlowaWartoscException e) { // 1
      System.out.println("Nieprawidlowa wartosc: " + e.getMessage());
    } catch (NieprawidlowyWiekException e) { // 2
      System.out.println("Nieprawidlowy wiek!");
    }
  }
}
```

Powyższy program używa dwóch nowych typów wyjątków, które rzucane są w konstruktorze klasy Osoba. Wyjątki te obsługiwane są następnie w ciele metody main (1) i (2).

W przypadku obsługi wyjątku typu NieprawidlowaWartoscException, do wypisywanego na ekran komunikatu dodajemy treść błędu, która zawarta jest w wyjątku – umieściliśmy ją tam rzucając wyjątek w konstruktorze klasy Osoba. Wiadomość ta zawiera informację, która wartość jest nieprawidłowa. Ta wiadomość zwracana jest przez metodę getMessage.

## Przerywanie wykonania bloku kodu przez wyjątki

Chociaż widzieliśmy w poprzednim rozdziale, jak rzucanie wyjątku wpływa na wykonanie bloku kodu, w którym wyjątek wystąpił, to warto jeszcze raz omówić to zagadnienie.

W momencie rzucenia wyjątku przerywany jest aktualnie wykonywany blok kodu. W przypadku konstruktora klasy Osoba, gdy okaże się, że przesłane imię jest nullem, wykonanie konstruktora klasy Osoba natychmiast się kończy – kolejne pola nie będą już sprawdzane – konstruktor kończy działanie. Nasz program kontynuuje wykonanie od sekcji catch, która odpowiedzialna jest za obsłużenie tego konkretnego wyjątku.

Oznacza to, że jeżeli przekazalibyśmy do konstruktora klasy Osoba wartość null dla imienia, null dla nazwiska, oraz -1 dla wieku, to rzucony zostałby tylko jeden wyjątek – ten, który zasygnalizowałby nieprawidłowe imię, ponieważ to to pole sprawdzamy jako pierwsze:
konstruktor klasy Osoba z pliku Osoba.java

```java
public Osoba(String imie, String nazwisko, int wiek)
    throws NieprawidlowaWartoscException, NieprawidlowyWiekException {
  if (imie == null) {
    throw new NieprawidlowaWartoscException( // 1
        "Imie nie moze byc puste."
    );
  }
  if (nazwisko == null) {
    throw new NieprawidlowaWartoscException( // 2
        "Nazwisko nie moze byc puste."
    );
  }
  if (wiek <= 0) {
    throw new NieprawidlowyWiekException(); // 3
  }
  this.imie = imie; // 4
  this.nazwisko = nazwisko;
  this.wiek = wiek;
}
```

Poniższe wywołanie konstruktora:

```java
Osoba o = new Osoba(null, null, -1);
```

Spowoduje, że wykona się tylko fragment konstruktora do linii oznaczonej jako (1) – od tej linii wykonanie dalszej części ciała konstruktora zostanie przerwane, ponieważ rzucony zostanie wyjątek.

Następujące wywołanie:

```java
Osoba o = new Osoba("Jan", null, -1);
```

Spowoduje, że wykona się tylko fragment konstruktora do linii oznaczonej jako (2).

Idąc dalej tym tropem:

```java
Osoba o = new Osoba("Jan", "Nowak", -1);
```

W tym przypadku, konstruktor zostanie przerwany w linii (3).

W żadnym z powyższych przypadków nigdy nie dojdzie do wykonania kodu zaczynającego się od linii (4), a co ważniejsze, obiekt typu Osoba nie zostanie utworzony.

Dopiero poniższe wywołanie konstruktora, które zawiera poprawne wartości dla imienia, nazwiska, oraz wieku, spowoduje wykonanie całego ciała konstruktora klasy Osoba oraz utworzenie i zwrócenie nowego obiektu klasy Osoba:

```java
Osoba o = new Osoba("Jan", "Nowak", 30);
```

## Rzucanie wyjątków i nieosiągalny kod

W związku z tym, że rzucenie wyjątku przerywa aktualnie wykonywany blok kodu, kompilator jest w stanie wykryć sytuacje, w których pewien fragment kodu nigdy nie miałby szansy się wykonać z powodu rzucania wyjątku.

Poniższy przykład w ogóle się nie kompiluje – kompilator zgłasza błąd, ponieważ instrukcja System.out.println nie ma szansy się wykonać – jest przed nią rzucany wyjątek IllegalArgumentException:
Nazwa pliku: NieosiagalnyKodRzucanyWyjatek.java

```java
public class NieosiagalnyKodRzucanyWyjatek {
  public static void main(String[] args) {
    throw new IllegalArgumentException("Fajrant!");
    System.out.println("Witaj Swiecie?");
  }
}
```

Komunikat błędu:
NieosiagalnyKodRzucanyWyjatek.java:5: error: unreachable statement
```java
System.out.println("Witaj Swiecie?");
^
```
## Rzucanie wyjątków a wartość zwracana z metody

Gdy poznawaliśmy metody, dowiedzieliśmy się, że metoda zawsze musi zwrócić wartość jeżeli definiuje typ zwracany inny niż void. W przeciwnym razie kompilacja naszego kodu zakończy się błędem:
Nazwa pliku: Rozdzial_07__Metody.BrakReturn.java

```java
public class BrakReturn {
  public static void main(String[] args) {
    int liczbaDoKwadratu = kwadratLiczby(5);
  }
  public static int kwadratLiczby(int x) {
    int wynik = x * x;
    // ups! zapomnielismy zwrocic wynik!
  }
}
```

Ten kod kończy się błędem kompilacji missing return statement – zapomnieliśmy zwrócić wartość z metody kwadratLiczby, a musimy to zrobić – sygnatura metody wskazuje, że metoda ta zwraca wartość int.

W rozdziale o metodach wspomniałem o wyjątku od tej reguły – metoda nie musi zwrócić wartości, jeżeli rzuci wyjątek. Ma to taki sens, że skoro coś w metodzie poszło nie tak, skoro wystąpił jakiś błąd, to metoda zamiast zwrócić wartość może rzucić wyjątek:
Nazwa pliku: WyjatekZamiastReturn.java

```java
public class WyjatekZamiastReturn {
  public static void main(String[] args) {
    try {
      System.out.println(podziel(10, 0));
    } catch (IllegalArgumentException e) {
      System.out.println("Wystpil wyjatek " + e.getMessage());
    }
  }
  public static int podziel(int x, int y) {
    if (y == 0) {
      throw new IllegalArgumentException("Dzielnik nie moze byc rowny 0.");
    }
    return x / y;
  }
}
```

Ten kod kompiluje się bez błędów pomimo, że istnieje taka ścieżka wykonania metody podziel, w której nie zwróci ona wartości – jeżeli y będzie równe zero, to rzucony zostanie wyjątek IllegalArgumentException. Rzucenie wyjątku powoduje natychmiastowe zakończenie działania metody podziel bez zwracania jakiejkolwiek wartości z metody. Wykonanie programu powraca do metody main, gdzie działanie kontynuowane jest w sekcji catch, w której obsługujemy złapany wyjątek. Metoda podziel nie zwraca co prawda wartości, ale rzuca wyjątek, co stanowi wyjątek od reguły, że metoda zawsze musi zwracać wartość, jeżeli definiuje zwracany typ inny niż void.
Zauważ, że wcześniej w tym rozdziale mówiłem, że jeżeli metoda rzuca wyjątek, to musimy go zdefiniować dodając po nazwie i argumentach metody słowo kluczowe throws oraz nazwy wyjątków, które metoda może rzucić. W powyższym przykładzie jednak nie ma throws, a kod działa – jak już wspomniałem w tym rozdziale, istnieją dwa rodzaje wyjątków – takie, które trzeba definiować za pomocą throws i te, których nie trzeba – porozmawiamy o tym w jednym z kolejnych rozdziałów.
## Rzucanie wyjątków w try, catch, i finally

Wyjątki możemy rzucać w dowolnych blokach kodu – także w sekcji try, catch, a także finally.

Dla przykładu, moglibyśmy pobrać od użytkownika wiek osoby do utworzenia. Jeżeli ten wiek już w momencie pobrania od użytkownika jest nieprawidłowy (ujemny), to możemy rzucić w sekcji try wyjątek, by przejść natychmiast do sekcji catch obsługującej taką sytuację:
Nazwa pliku PobierzWiekOdUzytkownika.java

```java
import java.util.Scanner;
public class PobierzWiekOdUzytkownika {
  public static void main(String[] args) {
    try {
      System.out.print("Podaj wiek osoby: ");
      int wiek = getInt(); // 1
      if (wiek <= 0) {
        throw new NieprawidlowyWiekException(); // 2
      }
      Osoba osoba = new Osoba("Jan", "Nowak", wiek); // 3
      System.out.println("Obiekt utworzony!");
    } catch (NieprawidlowaWartoscException e) {
      System.out.println("Nieprawidlowa wartosc: " + e.getMessage());
    } catch (NieprawidlowyWiekException e) { // 4
      System.out.println("Nieprawidlowy wiek!");
    }
  }
  public static int getInt() {
    return new Scanner(System.in).nextInt();
  }
}
```

W tym przykładzie korzystamy z metody getInt, której używamy już od kilku rozdziałów. W metodzie main pobieramy od użytkownika wiek osoby do utworzenia (1).

Zanim w ogóle przystąpimy do tworzenia obiektu typu Osoba w linii (3), najpierw sprawdzamy pobraną od użytkownika liczbę. Jeżeli jest nieprawidłowym wiekiem, to w ogóle nie będziemy próbowali utworzyć obiektu typu Osoba. Zamiast tego, od razu rzucimy wyjątek NieprawidlowyWiekException (2), a wykonanie programu przejdzie do sekcji catch, która ten wyjątek obsługuje (4).

Przykład wykonania z podaniem poprawnego i niepoprawnego wieku:
Podaj wiek osoby: 35
Obiekt utworzony!
Podaj wiek osoby: -5
Nieprawidlowy wiek!

Wyjątki mogą być też rzucane w sekcji catch. Czasem chcemy wykonać pewną akcję związaną z obsługą wyjątku, a potem rzucić go ponownie, co zobaczymy w kolejnym rozdziale.

Wyjątki mogą równie dobrze być rzucone w sekcji finally – jeżeli wykonujemy w nich kod bądź wywołujemy metody, które potencjalnie mogą zakończyć się wyjątkiem, to musimy mieć to na uwadze, opakowując taki kod ponownie w try..catch lub sygnalizując metodom, które z naszego kodu korzystają, że to one powinny się tymi potencjalnymi wyjątkami zająć – o tym zagadnieniu także porozmawiamy w jednym z kolejnych rozdziałów.
## Ponowne rzucanie wyjątku

Złapanie wyjątku nie musi oznaczać zakończenia obsługi sytuacji wyjątkowych – złapany wyjątek można rzucić ponownie (exception rethrow), by został obsłużony przez metodę "wyżej" (tzn. jedną z wcześniejszych metod, które doprowadziły do wykonania metody, w której wyjątek był obsługiwany).

Jaki jest sens takiego działania? Możemy chcieć obsłużyć pewien wyjątek w dany sposób, a także dać szansę na jego obsłużenie w jednej z wcześniejszych metod.

Dla przykładu – wywołując metodę, która rzuca wyjątek, łapiemy go i zapisujemy do pliku logu informację, że wystąpił błąd. Moglibyśmy też umieścić w obiekcie wyjątku dodatkowe informacje o okolicznościach błędu. Następnie rzucamy ten wyjątek "dalej", by został obsłużony ponownie, potencjalnie już bez ponownego rzucania.

Do ponownego rzucania wyjątku stosuje się po prostu słowo kluczowe throw, po którym następuje wyjątek, który chcemy "rzucić dalej".

```java
public static void glownaMetoda() {
  try {
    pewnaMetoda();
  } catch (PewienWyjatek e) {
    // ponowna obsluga wyjatku PewienWyjatek
  }
}
public static void pewnaMetoda() throws PewienWyjatek {
  try {
    // ...
    // instrukcje ktora moga spowodowac PewienWyjatek
    // ...
  } catch (PewienWyjatek e) {
    // zapisz informacje o bledzie do pliku logu 
    log.error("Wystapil blad " + e.getMessage());
    // rzuc wyjatek dalej 
    throw e;
  }
}
```

W tym przykładzie glownaMetoda wywołuje metodę pewnaMetoda i spodziewa się potencjalnego wyjątku PewienWyjatek, ponieważ pewnaMetoda deklaruje za pomocą throws, że taki wyjątek może rzucić. Dlatego też glownaMetoda zawiera try..catch i obsługę PewienWyjatek.

pewnaMetoda wykonuje pewne instrukcje, które mogą skutkować rzuceniem wyjątku PewienWyjatek. Obsługujemy go w sekcji catch, po czym rzucamy go "dalej" – teraz obsłuży go metoda glownaMetoda.
## Treść wyjątku, stack trace i inne pola i metody
Wszystkie klasy w tym rozdziale znajdują się w jednym pliku: ZawartoscWyjatkowPrzyklady.java.

Wyjątki to klasy jak wszystkie inne – mogą mieć własne konstruktory, metody, i pola. Ich cechą specjalną jest to, że rozszerzają klasę Exception.

Najprostszym wyjątkiem jest klasa, która nie definiuje żadnych pól ani metod (pamiętajmy, że klasa ta otrzyma automatycznie domyślny konstruktor):

```java
class WyjatekBezTresciException extends Exception {}
```

Ta klasa jest już gotowa do użycia – możemy rzucać wyjątki tego typu za pomocą throw i łapać za pomocą catch. Mogłoby się wydawać, że taka klasa nie jest specjalnie przydatna, ale jest całkiem odwrotnie – dobrze nazwana klasa wyjątku bez żadnej dodatkowej treści może tak samo spełniać swoje zadanie, jak klasy wyjątków ze szczegółowymi komunikatami o błędzie. W poprzednim rozdziale widzieliśmy taki właśnie przypadek – klasa NieprawidlowyWiekException była pusta – sama jej nazwa i rzucenie takiego wyjątku daje nam wystarczającą informację, co i dlaczego się stało.

Klasy wyjątków dziedziczą po klasie Exception kilka metod. Jedną z nich jest getMessage, która zwraca treść (komunikat) wyjątku. Ta treść może być podana podczas rzucania wyjątku – musimy wtedy udostępnić w klasie naszego wyjątku konstruktor, który przyjmie tą wiadomość i przekaże ją do konstruktora klasy bazowej. Spójrzmy na kolejny przykład wyjątku, który pozwala na zapisanie w wyjątku komunikatu:

```java
class WyjatekZKomunikatemException extends Exception {
  public WyjatekZKomunikatemException(String message) {
    // przekaz tresc wyjatku do konstruktora klasy bazowej,
    // ktory umiesci ja w polu message, ktore bedzie dostepne
    // za pomoca metody getMessage
    super(message);
  }
}
```

Przykładowe utworzenie wyjątku z komunikatem:

```java
try {
  throw new WyjatekZKomunikatemException("Co tu sie wyprawia?!");
} catch (WyjatekZKomunikatemException e) {
  System.out.println("Wyjatek zawiera komunikat: " + e.getMessage());
}
```

Wynik:
```text
Wyjatek zawiera komunikat: Co tu sie wyprawia?!
```

Jeżeli utworzymy wyjątek bez komunikatu, to getMessage zwróci null – zobaczymy to na przykładzie wyjątku WyjatekBezTresciException:

```java
try {
  throw new WyjatekBezTresciException();
} catch (WyjatekBezTresciException e) {
  System.out.println("Wyjatek zawiera komunikat: " + e.getMessage());
}
```

Wynik:
Wyjatek zawiera komunikat: null

Czasem tworząc nowy typ wyjątku chcemy w nim mieć możliwość zapisać dodatkowe informacje, którą mogą pomóc podczas próby jego obsługi, lub by móc zapisać je do pliku logu w celu późniejszej analizy, co poszło nie tak. Nic nie stoi na przeszkodzie, aby klasa wyjątku definiowała nowe pola, które takie dane będą przechowywać:

```java
class WyjatekZDodatkowymiDanymiException extends Exception {
  private int pewnaWartosc;
  private String innaWartosc;
  public WyjatekZDodatkowymiDanymiException(
      int pewnaWartosc, String innaWartosc) {
    this.pewnaWartosc = pewnaWartosc;
    this.innaWartosc = innaWartosc;
  }
  public String getMessage() {
    return "Wartosci zapisane w tym wyjatku: " +
        pewnaWartosc + " " + innaWartosc;
  }
}
```

Ta klasa wyjątku pozwala na skojarzenie z nim wartości int oraz String poprzez przekazanie ich do konstruktora. Te wartości możemy potem zobaczyć korzystając z przeładowanej metody getMessage (o przeładowaniu metod mówiliśmy w rozdziale o dziedziczeniu). Przykładowe użycie mogłoby wyglądać następująco:

```java
try {
  int pewnaWartosc = 10;
  String innaWartosc = "test";
  throw new WyjatekZDodatkowymiDanymiException(pewnaWartosc, innaWartosc);
} catch (WyjatekZDodatkowymiDanymiException e) {
  System.out.println("Wyjatek zawiera komunikat: " + e.getMessage());
}
```

Wynik:
Wyjatek zawiera komunikat: Wartosci zapisane w tym wyjatku: 10 test

Moglibyśmy też dodać do powyższej klasy gettery, które zwracałyby wartość pola liczbowego i typu String, jeżeli potrzebowalibyśmy mieć możliwość bezpośredniego odniesienia się do nich.

Inną metodą, którą wyjątki dziedziczą z klasy bazowej, jest printStackTrace. Metoda ta wypisuje na standardowe wyjście hierarchię wywołań metod w programie do momentu wystąpienia wyjątku:

```java
try {
  throw new WyjatekZKomunikatemException("Co tu sie wyprawia?!");
} catch (WyjatekZKomunikatemException e) {
  System.out.println("Wyjatek zawiera komunikat: " + e.getMessage());
  e.printStackTrace();
}
```

Wywołanie e.printStackTrace spowoduje pojawienie się na standardowym wyjściu następujących komunikatów:
```text
WyjatekZKomunikatemException: Co tu sie wyprawia?!
	at ZawartoscWyjatkowPrzyklady.main(ZawartoscWyjatkowPrzyklady.java:32)
```

Stack trace opisywałem na początku rozdziału o wyjątkach. W pierwszej linii znajduje się nazwa klasy wyjątku, po której następuje komunikat wyjątku. Następnie podane są metody i numery linii, który były po kolei wywoływane do momentu, w którym wystąpił wyjątek (te metody posortowane są, patrząc od góry, od ostatniej wywołanej do pierwszej). Umożliwia to prześledzenia wykonania programu aż do zaistnienia błędu i ułatwia analizę okoliczności, w jakich napotkany został problem.

Jak już kilka razy wspominałem, istnieją dwa typy wyjątków:

```text
checked exceptions,
unchecked exceptions.
```

Główną różnicą pomiędzy tymi dwoma rodzajami wyjątków jest to, że gdy zamierzamy rzucić wyjątek tego pierwszego rodzaju (checked), to musimy go umieścić w klauzuli throws w sygnaturze naszej metody, natomiast w przypadku Unchecked Exceptions nie musimy tego robić (chociaż możemy).

To jest właśnie powód tego, że w niektórych przykładach w tym rozdziale korzystaliśmy z throws do zaznaczenia wyjątków rzucanych przez metodę, a w innych nie.

W przykładzie z dzieleniem liczb:
Nazwa pliku: ZwrocWynikDzielenia.java

```java
public class ZwrocWynikDzielenia {
  public static void main(String[] args) {
    System.out.println(podziel(10, 0));
    System.out.println(podziel(25, 5));
  }
  public static int podziel(int x, int y) {
    return x / y;
  }
}
```

Pomimo, że wywołanie metody podziel może zakończyć się wyjątkiem ArithmeticException, nie korzystamy ani z throws do zaznaczenia tego faktu, ani nie musimy tego wyjątku obsługiwać. To dlatego, że ArithmeticException jest przykładem wyjątku Unchecked.

Z drugiej jednak strony, w przykładach w klasie Osoba, w której konstruktorze rzucaliśmy wyjątki NieprawidlowyWiekException i NieprawidlowaWartoscException, zaznaczyliśmy ten fakt za pomocą throws w sygnaturze konstruktora, a w metodzie main tej klasy, gdzie korzystaliśmy z tego konstruktora, stosowaliśmy try..catch:
fragment pliku Osoba.java

```java
public Osoba(String imie, String nazwisko, int wiek)
    throws NieprawidlowaWartoscException, NieprawidlowyWiekException {
  if (imie == null) {
    throw new NieprawidlowaWartoscException(
        "Imie nie moze byc puste."
    );
  }
  // ... pozostaly fragment konstruktora został pominiety
}
public static void main(String[] args) {
  try {
    Osoba o = new Osoba("Jan", "Nowak", -1);
  } catch (NieprawidlowaWartoscException e) {
    System.out.println("Nieprawidlowa wartosc: " + e.getMessage());
  } catch (NieprawidlowyWiekException e) {
    System.out.println("Nieprawidlowy wiek!");
  }
}
```

## Jak rozpoznać wyjątki Checked i Unchecked?

O tym, czy wyjątek to wyjątek Checked czy Unchecked decyduje tylko jedna właściwość – czy klasa wyjątku dziedziczy po klasie RuntimeException.
Wyjątki Unchecked nazywane są także runtime exceptions.

Co oznacza powyższe rozróżnienie?

Wyjątki to pochodne klasy Exception – klasa RuntimeException także jest pochodną klasy Exception. Jeżeli wyjątek w swojej hierarchii dziedziczenia nie ma klasy RuntimeException, to jest wyjątkiem Checked i trzeba go definiować w klauzuli throws w sygnaturze metody i obsługiwać w try..catch. Jeżeli ma w hierarchii klasę RuntimeException, to jest rodzaju Unchecked. Spójrzmy na kilka przykładów zdefiniowania własnych wyjątków:

```java
// checked exceptions
class MojWyjatek extends Exception {}
class MojKolejnyWyjatek extends MojWyjatek {}
// unchcked exceptions
class MojRuntimeWyjatek extends RuntimeException {}
class MojKolejnyRuntimeWyjatek extends MojRuntimeWyjatek {}
```

Pierwsze dwa wyjątki mają następującą hierarchię klas (począwszy od klasy Exception):

```text
Exception
    MojWyjatek
        MojKolejnyWyjatek
```

MojKolejnyWyjatek jest wyjątkiem rodzaju Checked, ponieważ ma w swojej hierarchii typ Exception oraz nie ma typu RuntimeException (podobnie jak typ MojWyjatek).

Z kolei dwa ostatnie wyjątki mają następującą hierarchię klas (począwszy od klasy Exception):

```text
Exception
    RuntimeException
        MojRuntimeWyjatek
            MojKolejnyRuntimeWyjatek
```

Wyjątki te mają w swojej hierarchii klasę RuntimeException, są więc wyjątkami rodzaju Unchecked i nie trzeba ich definiować w klauzuli throws.

Wyjątki Unchecked można łapać tak samo w try..catch jak wyjątki Checked – widzieliśmy już taki przykład podczas obsługi metody podziel, gdy występował potencjał dzielenia przez 0, oraz używając wyjątku IllegalArgumentException w pierwszej wersji klasy Osoba – ten wyjątek także jest wyjątkiem Unchecked.
## Dlaczego istnieją dwa rodzaje wyjątków?

Idea wyjątków Checked jest taka, że są to wyjątki, które zazwyczaj można obsłużyć, i pozwolić na dalsze wykonywanie programu, np. gdy użytkownik poda nieprawidłową nazwę pliku do otwarcia możemy mu pozwolić ponownie spróbować ją podać. Takie wyjątki chcemy obsługiwać i zaznaczamy potencjał ich rzucenia w sygnaturze metody za pomocą słowa kluczowego throws. Jest to informacja dla każdego użytkownika naszej metody:

"Jeśli będziesz korzystał z tej metody, to mogą się pojawić takie a takie wyjątki – powinieneś wziąć je pod uwagę i obsłużyć wedle własnego uznania."

Wyjątki Unchecked są często spowodowane błędnym stanem naszego programu i mogłoby być ciężko zareagować na nie w odpowiedni sposób – zamiast tego pozwalamy im zostać przetworzonymi przez Maszynę Wirtualną Java, nie podejmując się sami próby ich obsługi. Takie wyjątki powinny zostać zauważone, a kod, które je powoduje, naprawiony, zamiast próbować obsłużyć je w kodzie za pomocą try..catch. Nie oznacza to jednak, że nie możemy obsługiwać wyjątków Unchecked w try..catch – możemy, jeżeli mamy taką potrzebę.
## Sprawdzanie wyjątków rzucanych przez metodę

Korzystając z różnych bibliotek, w tym ze Standardowej Biblioteki Java, możemy czasem mieć potrzebę sprawdzić, jakie wyjątki dana metoda może rzucić – aby to zrobić, wystarczy zajrzeć do dokumentacji klasy, do której dana metoda należy.

Dla przykładu – używana już przez nas metoda charAt z klasy String może rzucić wyjątek IndexOutOfBoundsException. Dokładny opis można znaleźć w Java Doc. Z racji tego, że wyjątek ten jest rodzaju Unchecked, to korzystając z metody charAt nie musieliśmy stosować try..catch do łapania i obsługi tego wyjątku w poprzednich rozdziałach.

Jeżeli korzystamy z IntelliJ IDEA, możemy także sprawdzić wyjątki rzucane przez metodę poprzez naciśnięcie i przytrzymanie przycisku Ctrl na klawiaturze i kliknięcie lewym przyciskiem myszy nazwy metody – spowoduje to przejście do definicji tej metody, gdzie będziemy mogli spojrzeć na jej sygnaturę i sprawdzić, czy korzysta ze słowa kluczowego throws do zadeklarowania potencjału rzucenia pewnych wyjątków.

Wykorzystując powyższy sposób, spójrzmy na metodę charAt z klasy String:

```text
/**
 * Returns the {@code char} value at the
 * specified index. An index ranges from {@code 0} to
 * {@code length() - 1}. The first {@code char} value of the sequence
 * is at index {@code 0}, the next at index {@code 1},
 * and so on, as for array indexing.
 *
 * <p>If the {@code char} value specified by the index is a
 * <a href="Character.html#unicode">surrogate</a>, the surrogate
 * value is returned.
 *
 * @param      index   the index of the {@code char} value.
 * @return     the {@code char} value at the specified index of this string.
 *             The first {@code char} value is at index {@code 0}.
 * @exception  IndexOutOfBoundsException  if the {@code index}
 *             argument is negative or not less than the length of this
 *             string.
 */
public char charAt(int index) {
    if (isLatin1()) {
        return StringLatin1.charAt(value, index);
    } else {
        return StringUTF16.charAt(value, index);
    }
}
```

Jest to fragment klasy String ze standardowej biblioteki Java. Jak widzimy, sygnatura metody charAt nie zawiera throws, czyli nie rzuca wyjątków rodzaju Checked – nie trzeba więc stosować try..catch, gdy z niej korzystamy.

Warto jednak zauważyć, iż autorzy języka Java zawarli w komentarzu dokumentacyjnym sekcję @exception, w której napisali, że metoda ta może rzucić wyjątek IndexOutOfBoundsException. Jest to co prawda wyjątek Unchecked i nie musimy go obsługiwać, jednak gdybyśmy chcieli to zrobić, to mamy tutaj informację, że taki wyjątek może zostać rzucony i jeżeli mamy taką potrzebę, to możemy go obsłużyć.
Jak widzisz, w kodzie tej metody nie ma bezpośredniego rzucania wyjątku IndexOutOfBoundsException – skąd więc taki komentarz twórców języka Java? Otóż wyjątek ten może rzucić jedna z metod wywoływanych przez tą metodę, a dokładniej StringLatin1.charAt.
## Jak sprawdzić rodzaj wyjątku

Sprawdzanie rodzaju wyjątku sprowadza się do analizy jego hierarchii dziedziczenia – jeżeli jest w niej zawarta klasa RuntimeException, oznacza to, że jest to wyjątek Unchecked i nie trzeba go obsługiwać w try..catch.

W przeciwnym razie wyjątek jest rodzaju Checked i jeżeli metoda, z której korzystamy, rzuca go, to musimy go prędzej czy później obsłużyć. Jak zobaczymy w jednym z kolejnych rozdziałów nie zawsze musimy koniecznie od razu obsługiwać wyjątki rodzaju Checked.

Jak jednak sprawdzić hierarchię dziedziczenia wyjątku? Najłatwiej sprawdzić to w dokumentacji – jeżeli korzystamy z klasy ze Standardowej Biblioteki Java, to informację o klasach znajdziemy w Java Doc. Dla przykładu, dla wyjątku IllegalArgumentException hierarchia dziedziczenia wygląda następująco:

```java
java.lang.Object
    java.lang.Throwable
        java.lang.Exception
            java.lang.RuntimeException
                java.lang.IllegalArgumentException
```

źródło: oficjalna dokumentacja Java Doc – klasa Illegal ArgumentException

Widzimy w tej hierarchii klasę RuntimeException, więc klasa IllegalArgumentException jest przedstawicielką wyjątków rodzaju Unchecked.

Jeżeli korzystamy z wyjątków z innej biblioteki, to musimy sprawdzić to w dokumentacji tej biblioteki w internecie.
## Błędy kompilacji podczas braku obsługi wyjątków Checked

Jeżeli skorzystamy z metody, która rzuca wyjątki rodzaju Checked, a nie obsłużymy ich w try..catch, to nasza klasa się nie skompiluje – będzie to dla nas informacja, że brakuje obsługi wyjątków – widzieliśmy taki przypadek w klasie Osoba w tym rozdziale. Definiując, że konstruktor klasy Osoba może rzucić dwa wyjątki rodzaju Checked:

```java
public Osoba(String imie, String nazwisko, int wiek)
    throws NieprawidlowaWartoscException, NieprawidlowyWiekException {
```

i korzystając z poniższego fragmentu kodu:

```java
Osoba o = new Osoba("Jan", "Nowak", -1);
```

spowoduje, że próba kompilacji zakończy się następującym błędem:
```text
Osoba.java:31: error: unreported exception NieprawidlowaWartoscException; must be caught or declared to be thrown

  Osoba o = new Osoba("Jan", "Nowak", -1);
            ^
```
1 error

Gdy korzystamy z IntelliJ IDEA to łatwo wykryć sytuację, gdy korzystamy z kodu, który powinien zostać opakowany w blok try..catch jeszcze zanim spróbujemy skompilować nasz kod. IntelliJ IDEA jako mądre narzędzie analizuje nasz kod na bieżąco i wykrywa, że zapomnieliśmy o złapaniu wyjątków – podkreśli wtedy fragment kodu, który może rzucić nieobsłużone wyjątki, dając nam znać, że powinniśmy to zrobić:
IntelliJ IDEA informuje o braku zlapania wyjatkow
## Błąd Error

Istnieje jeszcze trzeci rodzaj wyjątków, które są pochodnymi klasy Error. Podobnie jak klasa Exception, dziedziczy ona bezpośrednio po Throwable, czyli klasie, która jest klasa nadrzędną wszystkich wyjątków.
Chociaż Throwable to ta właściwa klasa nadrzędna dla wszystkich wyjątków, to w praktyce nigdy z niej bezpośrednio nie korzystamy – zamiast tego jako klas bazowych dla naszych wyjątków używamy klasy Exception bądź jej pochodnych.

Wyjątki dziedziczące po Error to błędy krytyczne, których za bardzo nie da się obsłużyć i nie powinniśmy próbować tego robić. Sami też takich wyjątków nie będziemy nigdy rzucać. Taki błąd to np. OutOfMemoryError, który występuje gdy skończy się pamięć komputera przeznaczona dla naszego programu. My, jako autorzy programu, nie możemy nic na wystąpienie takiego wyjątku poradzić. To, co możemy i powinniśmy zrobić, to przeanalizować dlaczego pamięć się skończyła:

```text
Czy po prostu za mało pamięci zostało przeznaczone na działanie naszego programu?
Czy nasz program nie jest optymalnie napisany?
Czy w programie występuje błąd, który powoduje niemożliwość zwalniania pamięci przez Garbage Collector (mechanizm odpowiedzialny za zwalnianie nieużywanej pamięci w naszych programach)?
```

Wystąpienie wyjątku typu Error to poważniejszy problem, który trzeba przeanalizować – jego obsługa w kodzie albo nie ma sensu, albo wręcz nie jest możliwa.

W poprzednim rozdziale rzucaliśmy wyjątki za pomocą słowa kluczowego throw, po którym następował wyjątek. Wyjątek to po prostu obiekt konkretnej klasy wyjątku. Gdy łapiemy wyjątek w sekcji catch, podajemy typ wyjątku, jaki chcemy obsłużyć, oraz nazwę zmiennej, za pomocą której będziemy się do tego obiektu-wyjątku odnosić.

We wszystkich przykładach do tej pory nazywaliśmy tą zmienną po prostu e, np.:
fragment metody main z pliku Osoba.java

```java
try {
  Osoba o = new Osoba("Jan", "Nowak", -1);
} catch (NieprawidlowaWartoscException e) { // 1
  System.out.println("Nieprawidlowa wartosc: " + e.getMessage());
} catch (NieprawidlowyWiekException e) { // 2
  System.out.println("Nieprawidlowy wiek!");
}
```

W tym przykładzie spodziewamy się dwóch różnych wyjątków, które chcemy obsłużyć. Do każdego z nich w odpowiedniej sekcji catch będziemy mogli odnieść się za pomocą zmiennej o nazwie e (1) (2). Obiekt, na który ta zmienna będzie wskazywać w każdym z tych przypadków, to obiekt klasy wyjątku utworzonego w konstruktorze klasy Osoba:
fragment pliku Osoba.java – konstruktora klasy Osoba

```java
public Osoba(String imie, String nazwisko, int wiek)
    throws NieprawidlowaWartoscException, NieprawidlowyWiekException {
  if (imie == null) {
    throw new NieprawidlowaWartoscException( // 3
        "Imie nie moze byc puste."
    );
  }
  if (nazwisko == null) {
    throw new NieprawidlowaWartoscException( // 4
        "Nazwisko nie moze byc puste."
    );
  }
  if (wiek <= 0) {
    throw new NieprawidlowyWiekException(); // 5
  }
  this.imie = imie;
  this.nazwisko = nazwisko;
  this.wiek = wiek;
}
```

Gdy przekazane imię lub nazwisko będzie nullem (3) (4), to zmienna e z pierwszej sekcji catch (1) będzie wskazywała na obiekt typu NieprawidlowaWartoscException utworzony w (3) bądź (4). Analogicznie, jeżeli wiek będzie nieprawidłowy, to zmienna e w drugiej sekcji catch (2) będzie odnosiła się do obiektu typu NieprawidlowyWiekException tworzonego i rzucanego w (5).

W sekcji catch zmienna wyjątku może mieć dowolną nazwę, niekoniecznie musi to być e. Taka nazwa jest jednak używana dla wygody, tym bardziej, że nie ma ona specjalnie znaczenia – typ i treść wyjątku ma większe znaczenie, niż nazwa zmiennej, której chwilowo używamy do odnoszenia się do niego.
## Łapanie wyjątków za pomocą klasy bazowej

Załóżmy, że mamy metodę, która może rzucić wiele wyjątków, ale my, jako osoby używające tej metody, nie chcemy obsługiwać osobno każdego z nich. Zamiast wypisywać poszczególne wyjątki, możemy zamiast tego złapać wyjątek nadrzędny dla naszych wyjątków (którym zawsze jest wyjątek typu Exception) i w jednym miejscu obsłużyć wszystkie błędy. Spójrzmy jak by to wyglądało w klasie Osoba:
fragment metody main z pliku Osoba.java

```java
try {
  Osoba o = new Osoba("Joanna", "Strzelczyk", -1);
} catch (Exception e) {
  System.out.println("Wystapil blad! Komunikat bledu: " + e.getMessage());
}
try {
  Osoba o = new Osoba(null, "Strzelczyk", 30);
} catch (Exception e) {
  System.out.println("Wystapil blad! Komunikat bledu: " + e.getMessage());
}
```

Zamiast łapać konkretne wyjątki, złapaliśmy po prostu wszystkie wyjątki korzystając z klasy bazowej wyjątków – klasy Exception. Kod działa, ponieważ zarówno wyjątek NieprawidlowaWartoscException, jak i NieprawidlowyWiekException, bazują na typie Exception, więc kwalifikują się do złapania. Widzimy tutaj dziedziczenie i polimorfizm w akcji – korzystamy z klasy bazowej, a potencjalnie działamy na obiektach klas pochodnych. Klasa Exception jest "bardziej ogólna" niż dwa pozostałe wyjątki.

Zapisując kod w ten sposób mówimy kompilatorowi:

"Nieważne czy to będzie NieprawidlowaWartoscException czy NieprawidlowyWiekException, każdy z nich to Exception i chcę je obsłużyć w tej jednej sekcji catch".

W wyniku działania tego fragmentu kodu zobaczymy na ekranie:
```text
Wystapil blad! Komunikat bledu: null
Wystapil blad! Komunikat bledu: Imie nie moze byc puste.
```

W pierwszej linii widzimy, że komunikat błędu jest nullem – wynika to z faktu, że tworząc wyjątek typu NieprawidlowyWiekException w konstruktorze klasy Osoba nie podajemy żadnego komunikatu błędu.

Możemy także złapać konkretne wyjątki, a na końcu podać Exception, by obsłużyć wyjątki pewnego rodzaju, a pozostałe obsłużyć w bloku obsługi ogólnego wyjątku Exception:

```java
try {
  Osoba o = new Osoba("Jan", "Nowak", -1);
} catch (NieprawidlowyWiekException e) {
  System.out.println("Nieprawidlowy wiek!");
} catch (Exception e) {
  System.out.println("Wystapil blad! Komunikat bledu: " + e.getMessage());
}
try {
  Osoba o = new Osoba(null, "Nowak", 30);
} catch (NieprawidlowyWiekException e) {
  System.out.println("Nieprawidlowy wiek!");
} catch (Exception e) {
  System.out.println("Wystapil blad! Komunikat bledu: " + e.getMessage());
}
```

Ten fragment kodu spowodowałby wypisanie na ekran:
```text
Nieprawidlowy wiek!
Wystapil blad! Komunikat bledu: Imie nie moze byc puste.
```

Używanym tutaj "wyjątkiem ogólnym" nie musi być Exception, lecz dowolny typ wyjątku, który byłby w hierarchii dziedziczenia używanych przez nas klas wyjątków w klasie Osoba. Dla przykładu, jeżeli wyjątki NieprawidlowaWartoscException i NieprawidlowyWiekException dziedziczyłyby nie bezpośrednio po Exception, lecz po innym utworzonym przez nas typie wyjątków, np. BladWalidacjiDanychOsobyException, to moglibyśmy używać tego typu wyjątku w sekcji catch, aby złapać oba rodzaje wyjątków pochodnych od tego nowego typu wyjątku.
## Łapanie kilku wyjątków za pomocą znaku |

Jest jeszcze inna składnia pozwalająca na łapanie wykluczających się wyjątków – możemy rozdzielić je w klauzuli catch za pomocą znaku | (pionowa kreska, ang. pipe):
fragment metody main z pliku Osoba.java

```java
try {
  Osoba o = new Osoba("Adrian", "Sochacki", 30);
} catch (NieprawidlowaWartoscException | NieprawidlowyWiekException e) {
  System.out.println("Nieprawidlowa wartosc: " + e.getMessage());
}
```

W jednej sekcji catch łapiemy dwa wyjątki, rozdzielając nazwy ich klas znakiem |. Wyjątki te nie mogą dziedziczyć po sobie – jeżeli spróbowalibyśmy w ten sposób umieścić w catch np. wyjątki Exception | NieprawidlowaWartoscException, to kod nie skompilowałby się, ponieważ typ wyjątku Exception jest typem bazowym wyjątku NieprawidlowaWartoscException.

Typ Exception jest "bardziej ogólny", więc już samo jego umieszczenie w sekcji catch powoduje, że i wyjątek NieprawidlowyWiekException, który jest klasą pochodną od Exception, zostanie złapany. Kompilator wykryłby taką sytuację i zgłosiłby błąd.
## Kolejność łapania wyjątków ma znaczenie

Kolejność obsługiwania wyjątków w blokach catch ma znaczenie – bardziej ogólne wyjątki muszą zawsze następować po mniej ogólnych. Najbardziej ogólnymi wyjątkami są wyjątki typu Exception (ponieważ wszystkie wyjątki bazują na tym typie), a mniej ogólne to te, które dziedziczą po klasie Exception. Możemy rozszerzać inne wyjątki zdefiniowane w bibliotece standardowej Java, a także nasze własne wyjątki, więc musimy pamiętać o hierarchii. Jeżeli kolejność będzie nieprawidłowa, kompilator zaprotestuje:

```java
try {
  Osoba o = new Osoba(null, "Nowak", 30);
} catch (Exception e) {
  System.out.println("Wystapil blad! Komunikat bledu: " + e.getMessage());
} catch (NieprawidlowyWiekException e) {
  System.out.println("Nieprawidlowy wiek!");
}
```

Ten fragment kodu powoduje błąd kompilacji:
Error: java: exception NieprawidlowyWiekException has already been caught

Komunikat mówi o tym, że już obsłużyliśmy wyjątek typu NieprawidlowyWiekException – nastąpiło to w pierwszym bloku catch – wyjątek ten został dopasowany do pierwszego bloku catch, ponieważ wyjątek NieprawidlowyWiekException jest klasą pochodną klsay Exception.

Innymi słowy, sekcja catch z "bardziej ogólnym" wyjątkiem Exception obsługuje wszystkie wyjątki klasy Exception i jej klas pochodnych. W związku z tym, sekcja catch z wyjątkiem NieprawidlowyWiekException nigdy nie miałaby szansy zostać wykonana. Kompilator wykrywa ten problem i nie pozwala na skompilowanie takiego kodu.
Pamiętajmy, by sekcje catch zawsze zawierały najbardziej szczegółowe (najniżej w hierarchii dziedziczenia) wyjatki na początku, a najbardziej ogólne – na końcu. Obsługa wyjątków za pomocą typu Exception powinna zawsze być ostatnią sekcją catch, ponieważ załapią się do niej wszystkie rodzaje wyjątków w związku z tym, że klasa ta jest klasą bazową dla wszystkich wyjątków.

Możemy zadać teraz pytanie: a co, jeżeli nie chcemy obsługiwać wyjątków (bądź obsłużyć tylko część z nich)? Czy zawsze musimy obsłużyć wszystkie możliwe wyjątki rzucane przez daną metodę (zadeklarowane za pomocą throws)?

Nie, ale w takim przypadku musimy w metodzie, która nie chce obsłużyć jakiegoś wyjątku (bądź wszystkich), zawrzeć informację, że może ona rzucić dany wyjątek za pomocą poznanego słowa kluczowego throws. Spójrzmy na przykład (korzystający z klasy Osoba):
fragment klasy Osoba.java

```java
public static Osoba stworzPelnoletniaOsobe(String pierwszeImie, String nazwisko)
    throws NieprawidlowaWartoscException { // 1
  Osoba result = null;
  try {
    result = new Osoba(pierwszeImie, nazwisko, 18);
  } catch (NieprawidlowyWiekException e) { // 2
    // nic nie robimy, bo podajemy poprawny wiek - nie zakladamy bledu
  }
  return result;
}
```

Zdefiniowaliśmy powyżej nową metodą – stworzPelnoletniaOsobe. Ma ona na celu stworzenie obiektu klasy Osoba, która ma wiek równy 18.  
Nie chcemy w tej metodzie obsługiwać przypadku, gdy ktoś poda nieprawidłowe imię bądź nazwisko – w związku z tym, by kompilator nie protestował, że nie obsłużyliśmy wyjątku NieprawidlowaWartoscException, dodaliśmy klauzulę throws do metody stworzPelnoletniaOsobe (1) – oznacza to, że ten, kto wywoła metodę stworzPelnoletniOsobea, będzie musiał:

```text
obsłużyć wyjątek NieprawidlowaWartoscException lub
także zdefiniować, że rzuca wyjątek NieprawidlowaWartoscException, jezeli nie będzie chciał go obsłużyć.
```

Możemy powiedzieć, że metoda stworzPelnoletniaOsoba oddelegowuje obsługę wyjątku NieprawidlowaWartoscException do metody, która będzie z niej korzystała.

Zauważmy, że sekcja catch w powyższej metodzie nie zawiera żadnych instrukcji (2) – zakładamy, że wyjątek NieprawidlowyWiekException nie zostanie rzucony, ponieważ podajemy poprawny wiek. Nie zmienia to faktu, że musimy obsługę tego wyjątku zawrzeć w tej metodzie, ponieważ kompilator języka Java nie jest w stanie wywnioskować, że w tym przypadku wyjątek na pewno nie będzie rzucony.

W metodzie main dodajemy poniższy fragment kodu, w którym korzystamy z metody stworzPelnoletniaOsobe:
fragment metody main z klasy Osoba.java

```java
try {
  Osoba osobaPelnoletnia = stworzPelnoletniaOsobe("Jan", null);
} catch (NieprawidlowaWartoscException e) {
  System.out.println("Nieprawidlowa wartosc: " + e.getMessage());
}
```

Ponieważ metoda stworzPelnoletniaOsobe deklaruje za pomocą throws, że może rzucić wyjątek NieprawidlowaWartoscException, stosujemy try..catch w powyższym fragmencie kodu. Nie obsługujemy wyjątku NieprawidlowyWiekException, ponieważ obsługą tego wyjątku zajmuje się metoda stworzPelnoletniaOsobe.
Tak naprawdę to nie metoda stworzPelnoletniaOsobe, lecz konstruktor klasy Osoba, rzuca wyjątek NieprawidlowaWartoscException, ale z racji tego, że metoda stworzPelnoletniaOsobe tego wyjątku nie obsługuje, to wywołując tą metodę może zajść sytuacja, która spowoduje pojawienie się takiego wyjątku.
## Silent catch

Obsługa wyjątków często nie jest łatwa – trzeba się zastanowić, jak program powinien zachować się, gdy wystąpi pewien wyjątek:

```text
Czy program powinien kontynuować działanie?
Czy użytkownik powinien zostać powiadomiony o błędzie?
Czy program powinien odczekać i spróbować ponownie wykonać kod, który spowodował wyjątek?
```

Obsługa wyjątków zawsze wiąże się z wymogiem napisania dodatkowego kodu, co nierzadko zajmuje sporo czasu.

Czasem możemy mieć chęć po prostu złapać wyjątki w sekcji catch, ale nie pisać żadnego kodu, który by je obsługiwał:

```java
try {
  Osoba o = new Osoba("Adrian", "Sochacki", 30);
} catch (Exception e) {
  // zlap wszystkie wyjatki i sie nie przejmuj!
}
```

Tak zapisany kod zaoszczędza nam czas kosztem problemów, które ze sobą niesie. Powyższy sposób zapisu kodu to tzw. silent catch. Ja spotkałem się jeszcze z polskim określeniem "połykanie wyjątków".

Przez to, że nie obsługujemy wyjątków, nie mamy sposobu aby się dowiedzieć, że coś poszło w naszym programie nie tak, bo wszelkie błędy wynikłe z wykonania danej metody są ignorowane.

Może to powodować niestabilne działanie programu i bardzo trudne do wychwycenia błędy, których analiza, znalezienie, i naprawienie, zajmą dużo więcej czasu, niż napisanie dobrego kodu obsługi wyjątku na samym początku tworzenia programu.

Czasem napotkasz się na sytuacje, w których po prostu nie będziesz miał potrzeby obsługi wyjątków lub będziesz pewien, że nie będą rzucone – wtedy silent catch może być dobrym rozwiązaniem. Miej jednak na uwadze, że ignorowanie błędów ze względu na trudność ich obsługi to nie wymówka, aby tego nie robić.
## Pomijanie try..catch w metodzie main

Może się zdarzyć sytuacja, w której będziemy w metodzie main korzystać z metody, która może rzucić wyjątek rodzaju Checked i z jakiegoś powodu nie będziemy chcieli tego wyjątku obsługiwać. W takim przypadku kompilator zgłosi błąd widząc, że wyjątek rodzaju Checked nie jest obsługiwany:
Nazwa pliku: MainUzywaThrows.java

```java
class PewienWyjatekException extends Exception {
}
public class MainUzywaThrows {
  public static void main(String[] args) {
    pewnaMetoda(); // 1
  }
  public static void pewnaMetoda() throws PewienWyjatekException {
    // pewne instrukcje ktore powoduja rzucenie wyjatku
    throw new PewienWyjatekException();
  }
}
```

W metodzie main wywołujemy metodę pewnaMetoda (1), która rzuca wyjątek PewienWyjatekException. Brak obsługi tego wyjątku powoduje błąd kompilacji tej klasy:
MainUzywaThrows.java:7: error: unreported exception PewienWyjatekException; must be caught or declared to be thrown
```java
pewnaMetoda();
           ^
```
1 error

Nie musimy jednak tego wyjątku obsługiwać w metodzie main – jak wiemy z jednego z poprzednich rozdziałów, jeżeli metoda nie chce obsłużyć wyjątku, to musi zadeklarować potencjał jego rzucenia w swojej własnej sygnaturze:

```java
public static void main(String[] args) throws PewienWyjatekException {
  pewnaMetoda();
}
```

Tak zapisana metoda w powyższym programie powoduje, że kod kompiluje się bez błędów.

Pytanie: kto w takim razie obsłuży ten wyjątek? Ten, kto wywołuje metodę main w naszym programie! A jest to nikt inny jak Maszyna Wirtualna Java – gdy rozpoczyna ona wykonanie naszego programu, wywołuje metodę main. Jeżeli rzucony zostanie nieobsłużony wyjątek, to "złapie" go Maszyna Wirtualna Java i wyświetli komunikat na standardowym wyjściu.

Wynik działania tego programu:
Exception in thread "main" PewienWyjatekException
```text
    at MainUzywaThrows.pewnaMetoda(MainUzywaThrows.java:12)
    at MainUzywaThrows.main(MainUzywaThrows.java:7)
```

Wyjątek zostaje rzucony w metodzie pewnaMetoda. Jako, że metoda, która ją wywołała, czyli main, nie obsługuje tego wyjątku (nie korzysta z try..catch), a zamiast tego sama deklaruje za pomocą throws, że taki wyjątek może być rzucony, wędruje on dalej. Dalej jest już tylko Maszyna Wirtualna Java, która wywołała naszą metodę main. Wyjątek zostaje obsłużony przez Maszynę Wirtualną Java w ten sposób, że po prostu jego treść i stack trace zostają wypisane na standardowe wyjście.

Od wersji Java 1.7 dostępna jest nowa wersja instrukcji try..catch..finally nazywana try-with-resources (try z zasobami). Została ona wprowadzona dla wygody programistów, by zautomatyzować zamykanie różnego rodzaju zasobów takich jak obiekty klas odpowiedzialnych za np. odczytywanie danych z pliku.

Różnicę pomiędzy "standardowym" try..catch..finally oraz try-with-resources zobaczymy na przykładzie programu, którego jedynym zadaniem będzie wypisanie na ekran zawartości pliku znajdującego się na dysku komputera.

Aby odczytać z dysku plik w języku Java, musimy:

```text
Utworzyć obiekt typu File, który będzie skojarzony z plikiem na dysku.
Utworzyć strumień danych, który będzie mógł czytać dane z pliku, na który wskazuje obiekt File. Strumieniem danych w naszych przykładach będzie obiekt klasy FileReader – klasa ta pozwala na czytanie z pliku znak po znaku.
Zamknąć strumień po zakończeniu pracy na nim wywołując jego metodę close.
```

Dodatkowo, gdy korzystamy z klas strumieni, takich jak FileReader, musimy obsłużyć wyjątki, które mogą wystąpić. Oznacza to, że w naszym programie będziemy musieli skorzystać z try..catch do obsługi potencjalnych sytuacji wyjątkowych. Wyjątkiem bazowym używanym w metodach klas, które mają coś wspólnego z działaniami na plikach, jest IOException (Input/Output Exception), i taki też wyjątek będziemy łapać.

Poniżej znajdują się dwa przykłady – pierwszy korzysta ze "starego" podejścia, natomiast drugi używa nowego mechanizmu try-with-resources. Oba programy mają za zadanie odczytać plik z dysku znak po znaku i wypisać go na ekran. Spójrzmy najpierw na przykład korzystający ze zwykłego try..catch..finally:
Nazwa pliku: CzytaniePlikuTryCatch.java

```java
import java.io.File; // 1
import java.io.FileReader;
import java.io.IOException;
public class CzytaniePlikuTryCatch {
  public static void main(String[] args) {
    File f = new File("C:/programowanie/powitanie.txt"); // 2
    FileReader fileReader = null; // 3
    try {
      fileReader = new FileReader(f); // 4
      int odczytanyZnak;
      while ((odczytanyZnak = fileReader.read()) != -1) { // 5
        System.out.print((char) odczytanyZnak); // 6
      }
    } catch (IOException e) { // 7
      System.out.println(e.getMessage());
    } finally {
      try {
        if (fileReader != null) {
          fileReader.close(); // 8
        }
      } catch (IOException e) { // 9
        System.out.println(
            "Blad podczas zamykania strumienia: " + e.getMessage()
        );
      }
    }
  }
}
```

Ten program wypisuje na ekran zawartość pliku powitanie.txt znajdującego się w katalogu C:\programowanie:
Witaj
Swiecie
!

Krótka analiza tego programu:

```text
Klasy do pracy z plikami, z których skorzystamy, znajdują się w pakiecie java.io w Bibliotece Standardowej Java.
Tworzymy obiekt klasy File przekazując jako argument konstruktora lokalizację pliku, z którym ten obiekt będzie skojarzony.
Definiujemy obiekt klasy FileReader, który będzie służył do odczytania pliku. Zmienna fileReader znajduje się przed blokiem try..catch ponieważ będziemy z niej chcieli skorzystać w bloku finally. Gdybyśmy umieścili definicję tej zmiennej wewnątrz bloku try, to zmienna ta byłaby dostępna jedynie w tym bloku.
Tworzymy obiekt typu FileReader, który skojarzony będzie z plikiem, na który wskazuje utworzony wcześniej obiekt typu File. Tworzenie tego obiektu jest w bloku try, ponieważ może zostać rzucony wyjątek FileNotFoundException – gdy plik, na który wskazuje przekazany do konstruktora obiekt typu File, nie zostanie znaleziony.
W warunku pętli wykonujemy dwie operacje: przypisujemy do zmiennej odczytanyZnak wartość zwróconą z metody fileReader.read(), która zwraca kod liczbowy przeczytanego znaku, a następnie porównujemy całość tego wyrażenia do -1. Metoda read zwraca wartość -1 w przypadku, gdy przeczytany został już cały plik – będzie to oznaczało koniec działania pętli.
Jeżeli przeczytany kod znaku nie był liczbą -1, to wypisujemy go na ekran, rzutując go najpierw do wartości typu char. Musimy to zrobić, ponieważ metoda read z poprzedniego punktu zwraca nie faktyczny znak w pliku, lecz jego liczbową reprezentację.
Jeżeli wystąpi wyjątek, to łapiemy go i wypisujemy na ekran komunikat błędu.
Na końcu programu powinniśmy zamknąć obiekt fileReader, który służył do czytania z pliku, za pomocą metody close. Najpierw sprawdzamy, czy fileReader nie jest nullem ponieważ w bloku try mógł wystąpić wyjątek podczas próby utworzenia tego obiektu i mógł on nie zostać zainicjalizowany inną wartością niż null, a na nullowym obiekcie nie chcemy wywołać metody close.
Sama metoda close także może rzucić wyjątek IOException, jeżeli z jakiegoś powodu nie powiedzie się próba zamknięcia zasobu, na której ją wywołujemy. Zauważmy, że w sekcji finally ponownie korzystamy z try..catch, by złapać ewentualny wyjątek związany z próbą zamknięcia obiektu fileReader.
```

Dlaczego instrukcja fileReader.close(); nie jest częścią głównego bloku try..catch? Jeżeli instrukcja ta byłaby po pętli czytającej plik, a w trakcie czytania wystąpiłby wyjątek, to nigdy nie doszłoby do próby zamknięcia strumienia fileReader. Chcemy mieć pewność, że metoda close zostanie wywołana na rzecz obiektu fileReader niezależnie od tego, czy czytanie pliku się powiodło, czy nie – dlatego instrukcja zamykania jest w sekcji finally.

Porównaj powyższy program z jego drugą wersją, która korzysta z mechanizmu try-with-resources:
Nazwa pliku: CzytaniePlikuTryWithResources.java

```java
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class CzytaniePlikuTryWithResources {
  public static void main(String[] args) {
    File f = new File("C:/programowanie/powitanie.txt");
    try (FileReader fileReader = new FileReader(f)) {
      int odczytanyZnak;
      while ((odczytanyZnak = fileReader.read()) != -1) {
        System.out.print((char) odczytanyZnak);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
```

Ta wersja jest zdecydowanie krótsza – zauważ, że w ogóle nie ma w niej sekcji finally, a sekcja try ma dodatek w postaci tworzenia obiektu fileReader w nawiasach:

```java
try (FileReader fileReader = new FileReader(f)) {
```

To właśnie ten fragment świadczy o tym, że jest to try-with-resources. Naszym zasobem w tym przypadku jest strumień fileReader typu FileReader. Tak utworzony obiekt jest dostępny w bloku try – odczytywanie i wypisywanie znaku na ekran nie różni się pomiędzy obydwoma programami.

Ta wersja nie ma sekcji finally, ponieważ obiekt fileReader zostanie automatycznie zamknięty (zostanie na nim wywołana metoda close) po zakończeniu bloku try..catch dla naszej wygody i krótszego kodu.
Nie wszystkie klasy mają metodę close – skąd w takim razie wiemy, które klasy mogą być użyte jako "zasoby" w try-with-resources? Zależy to od tego, czy dana klasa implementuje interfejs Closeable lub AutoCloseable.

Mechanizm wyjątków ma zarówno zalety, jak i wady.
## Dlaczego używać mechanizmu wyjątków?

Mechanizm wyjątków ma dwie podstawowe zalety:

```text
Możemy obsłużyć dowolne sytuacje, które uznamy za nieprawidłowe, bez potrzeby stosowania rozwiązań z np. zwracaniem specjalnej wartości (jak w przykładzie z dzieleniem).
Przenosi odpowiedzialność obsługi błędu do tego, kto używa kod, który potencjalnie rzuca wyjątek. Zamiast w funkcji podziel wypisywać na ekran, że nie można dzielić przez 0 bądź zwracać specjalną wartość w takim przypadku, pozwalamy temu, kto wywołuje metodę podziel, na odpowiednie obsłużenie takiego przypadku. Jest to o tyle ważne, że w bardziej skomplikowanych przypadkach może nie być jednego uniwersalnego sposobu na obsłużenie danego błędu – rzucenie wyjątku pozwoli, by w różnych sytuacjach można było odpowiednio na dany błąd zareagować.
```

## Wady wyjątków

W poprzednich rozdziałach widzieliśmy kilka cech wyjątków rodzaju Checked:

```text
jeżeli w wyniku wywołania metody może zostać rzucony wyjątek rodzaju Checked, a nie chcemy go obsługiwać, to musimy skorzystać ze słowa kluczowego throws, aby zaznaczyć, że dana metoda może taki wyjątek rzucić,
jeżeli nie obsłużymy sytuacji, w której może być rzucony wyjątek rodzaju Checked i nie skorzystamy z throws, to nasz kod się nie skompiluje – kompilator zgłosi błąd.
```

Jeżeli wywołujemy metodę, która wywołuje kolejną metodę itd. i ostatnia z tych metod może rzucić wyjątek rodzaju Checked, a obsłużyć go chcemy dopiero na samej górze tego stosu wywołań metod, to wszystkie metody po drodze muszą zawierać klauzulę throws – inaczej nasz kod się nie skompiluje. Powoduje to potencjalny narzut, ponieważ teraz w każdym miejscu naszego programu, w którym wywołamy którąś z tych metod, będziemy musieli korzystać z bloku try..catch.

Ponadto, wymóg deklaracji rzucanego wyjątku i obsługi go uniemożliwia łatwą modyfikację już napisanych metod. Załóżmy, że mamy metodę pewnaMetoda, z której korzystamy w wielu miejscach naszego programu. W pewnym momencie musimy ją zmodyfikować i wykorzystać metodę z pewnej biblioteki, którą dodaliśmy do naszego projektu. Jeżeli metoda z tej biblioteki może rzucić wyjątek rodzaju Checked, to musimy albo dodać jego obsługę w naszej metodzie pewnaMetoda, albo dodać do jej sygnatury throws – ale to spowoduje, że wszystkie miejsca w naszym programie, które wcześniej korzystały z metody pewnaMetoda, przestaną się kompilować, jeżeli nie korzystały z try..catch.

Częstym zarzutem odnośnie wyjątków jest ich nadużycie – niekiedy metody definiują rzucanie wielu wyjątków, które potem trzeba obsługiwać. Czasem takie wyjątki powinny po prostu być rodzaju Unchecked zamiast Checked. Czasem takie sytuacje rozwiązuje się po prostu poprzez złapanie wszystkich wyjątków za pomocą klasy Exception:

```java
try {
  metodaMogacaRzucicWieleWyjatkow();
} catch (Exception e) {
  // .. zbiorcza obsluga wyjatkow
}
```

Jeżeli wyjątek ma być obsłużony wyżej w hierarchii wykonań, to zamiast go obsługiwać, jest on rzucany dalej, ale tym razem metody wyżej mają do obsługi jeden znormalizowany wyjątek.

Poza tym, poprzez ciche łapanie wyjątków (o którym wspominałem w jednym z wcześniejszych rozdziałów) możemy spowodować, że nasz program będzie zawierał ciężkie do wykrycia błędy. Chociaż takie przypadki to ewidentna wina programisty, a nie samego mechanizmu wyjątków.

Podsumowując – jeżeli w naszym programie chcemy zasygnalizować problem, który można rozwiązać i po którym program może dalej działać, to korzystajmy z wyjątków rodzaju Checked, a w przeciwnym razie rzucajmy wyjątki Unchecked. Unikajmy cichego łapania wyjątków i pamiętajmy, jakie konsekwencje niesie ze sobą dodanie rzucania wyjątków Checked przez już istniejące i używane metody.

## Podstawy wyjątków

```text
Wyjątki (exceptions) to sytuacje, w których coś w programie poszło nie tak.
Gdy wystąpi wyjątek, mówimy, że został on rzucony.
Wyjątki to obiekty klas. Jak każda klasa mają one swoją nazwę, konstruktory, pola i metody.
Dzięki wyjątkom możemy obsłużyć dowolne sytuacje, które uznamy za nieprawidłowe, bez potrzeby stosowania rozwiązań z np. zwracaniem specjalnej wartości.
Spotkaliśmy już się z paroma wyjątkami, np. ArrayIndexOutOfBoundsException i NullPointerException.
Klasy wyjątków to klasy rozszerzające klasę Throwable (lub jej pochodną np. Exception).
Wyjątki mogą zawierać komunikat informujący o zaistniałym błędzie, który możemy pobrać za pomocą metody getMessage.
Stack trace to ścieżka wykonań metod, które doprowadziły do błędu. Kolejność metod w stack trace jest odwrotna do kolejności ich wykonywania. Metoda na dole została wykonana jako pierwsza, a ta na górze jako ostatnia i w niej rzucony został wyjątek:
```

Exception in thread "main" java.lang.ArithmeticException: / by zero
```text
    at ZwrocWynikDzielenia.podziel(ZwrocWynikDzielenia.java:8)
    at ZwrocWynikDzielenia.main(ZwrocWynikDzielenia.java:3)
```
## Łapanie wyjątków

```java
// Wyjątki łapiemy (obsługujemy) za pomocą mechanizmu try..catch..finally:
    try {
      // instrukcje ktore moga potencjalnie zakonczyc sie wyjatkiem
    } catch (TypWyjatku dowolnaNazwa) {
      // instrukcje, gdy zajdzie wyjątek TypWyjatku
    } catch (KolejnyTypWyjatku dowolnaNazwa2) {
      // instrukcje, gdy zajdzie wyjątek KolejnyTypWyjatku
    } finally {
      // instrukcje, ktore maja byc wykonane niezaleznie od tego,
      // czy wyjatek zostal zlapany, czy nie
    }
```

Używając try..catch spodziewamy się, że w instrukcjach objętych przez try coś może pójść nie tak (ale nie musi). To, co powinno się zadziać w przypadku napotkania konkretnego problemu (i tylko wtedy), umieszczamy w sekcji catch.
W sekcji catch podajemy typ wyjątku, jaki chcemy obsłużyć, oraz nazwę zmiennej, za pomocą której będziemy się do tego obiektu-wyjątku odnosić.
Gdy występuje wyjątek, jego typ dopasowywany jest do listy wyjątków z obecnych sekcji catch. Jeżeli wyjątek zostanie dopasowany, wykonywany jest kod powiązany z tą sekcją catch, która ten konkretny typ wyjątku obsługuje.
W opcjonalnym bloku finally możemy umieścić instrukcje, które mają zawsze się wykonać, niezależnie od tego, czy wyjątek zostanie złapany, czy nie. Blok finally zazwyczaj używany jest do czyszczenia zasobów, np. zamykania otwartych plików.
Gdy wystąpi wyjątek, aktualnie wykonywany blok kodu zostaje przerwany. Dalsze wykonanie programu kontynuowane jest w sekcji catch, jeżeli jest obecna i dopasowany zostanie do niej typ wyjątku do obsłużenia.
Silent catch to łapanie wyjątków bez ich obsługi – powinniśmy wystrzegać się takich sytuacji, ponieważ mogą prowadzić do trudnych do wykrycia i analizy błędów.
Wyjątki do złapania definiowane w catch muszą być pochodnymi klasy Throwable, lub, jak to zazwyczaj ma miejsce, którejś z jej klas pochodnych: Exception lub RuntimeException (pośrednio bądź bezpośrednio) – inaczej kod się nie skompiluje.
Zmienne definiowane wewnątrz bloku try po zakończeniu tego bloku przestają istnieć. Aby zmienna była dostępna poza try, należy ją zdefiniować i zainicjalizować przed try:
```java
    int wynik = 0;
    try {
      wynik = podziel(10, 2);
    } catch (ArithmeticException e) {
      System.out.println("Blad dzielenia!");
    } finally {
      System.out.println("Sekcja finally: wynik wynosi " + wynik);
    }
    System.out.println("Po try wynik wynosi " + wynik);
```
Zamiast łapać kilka wyjątków, które może rzucić metoda, możemy złapać wyjątek nadrzędny dla tych wyjątków (którym zawsze jest wyjątek typu Exception) i w jednym miejscu obsłużyć wszystkie błędy:
```java

    try {
      Osoba o = new Osoba("Joanna", "Strzelczyk", -1);
    } catch (Exception e) {
      System.out.println(
          "Wystapil blad! Komunikat bledu: " + e.getMessage()
      );
    }
```
W powyższym przykładzie złapaliśmy wszystkie wyjątki korzystając z klasy bazowej wyjątków – Exception. Klasa ta jest "bardziej ogólna" niż inne klasy wyjątków, ponieważ jest ich rodzicem (dziedziczenie). Zapisując kod w ten sposób mówimy kompilatorowi: "Nieważne czy to będzie NieprawidlowaWartoscException czy NieprawidlowyWiekException, każdy z nich to Exception i chcę je obsłużyć w tej jednej sekcji catch".
"Wyjątkiem ogólnym" powyżej nie musi być Exception, lecz dowolny typ wyjątku, który byłby w hierarchii dziedziczenia używanych przez nas klas wyjątków, które chcemy złapać.
Możemy także złapać kilka wykluczających się typów wyjątków za pomocą znaku | (pionowa kreska, ang. pipe):
```java
    try {
      Osoba o = new Osoba("Adrian", "Sochacki", 30);
    } catch (NieprawidlowaWartoscException | NieprawidlowyWiekException e) {
      System.out.println("Nieprawidlowa wartosc: " + e.getMessage());
    }
```
Kolejność obsługiwania wyjątków w blokach catch ma znaczenie – bardziej ogólne wyjątki muszą zawsze następować po mniej ogólnych. Najbardziej ogólnymi wyjątkami są wyjątki typu Exception (ponieważ wszystkie wyjątki bazują na tym typie), a mniej ogólne to te, które dziedziczą po klasie Exception:
```java
    try {
      Osoba o = new Osoba(null, "Nowak", 30);
    } catch (Exception e) {
      System.out.println(
          "Wystapil blad! Komunikat bledu: " + e.getMessage()
      );
    } catch (NieprawidlowyWiekException e) { // blad kompilacji
      System.out.println("Nieprawidlowy wiek!");
    }
```    
Ten fragment kodu powoduje błąd kompilacji:
```text
Error: java: exception NieprawidlowyWiekException has already been caught
```
Sekcje catch muszą zawierać najbardziej szczegółowe (najniżej w hierarchii dziedziczenia) wyjątki na początku, a najbardziej ogólne na końcu.
Od wersja Java 1.7 możemy korzystać z nowego mechanizmu try-with-resources, który ułatwia pracę z zasobami poprzez ich automatyczne zamykanie po zakończeniu bloku try. Klasy, które możemy używać w try-with-resources to te klasy, które implementują interfejs AutoCloseable lub Closeable. Przykład użycia:
```

```java
File f = new File("C:/programowanie/powitanie.txt");
try (FileReader fileReader = new FileReader(f)) {
  int odczytanyZnak;
  while ((odczytanyZnak = fileReader.read()) != -1) {
    System.out.print((char) odczytanyZnak);
  }
} catch (IOException e) {
  System.out.println(e.getMessage());
}
```

## Rodzaje wyjątków

```text
Istnieją dwa typy wyjątków: Checked exceptions oraz Unchecked exceptions.
Różnica pomiędzy tymi rodzajami wyjątków jest taka, że potencjał rzucenia przez metodę wyjątku typu Checked musi być umieszczony w klauzuli throws w sygnaturze metody. W przypadku wyjątków Unchecked nie musimy tego robić.
O tym, czy wyjątek to jest rodzaju Checked czy Unchecked decyduje to, czy klasa wyjątku dziedziczy po klasie RuntimeException. RuntimeException to klasa pochodna od Exception.
Jeżeli klasa wyjątku ma w hierarchii dziedziczenia klasę RuntimeException, to jest wyjątkiem typu Unchecked.
Przykłady klas wyjątków rodzaju Checked:
    class MojWyjatek extends Exception {}
    class MojKolejnyWyjatek extends MojWyjatek {}
Wyjątki te mają następującą hierarchię dziedziczenia (począwszy od klasy Exception):
    Exception
        MojWyjatek
            MojKolejnyWyjatek
MojKolejnyWyjatek jest wyjątkiem rodzaju Checked, ponieważ ma w swojej hierarchii typ Exception oraz nie ma typu RuntimeException (podobnie jak wyjątek MojWyjatek).
Przykład wyjątków Unchecked:
    class MojRuntimeWyjatek extends RuntimeException {}
    class MojKolejnyRuntimeWyjatek extends MojRuntimeWyjatek {}
Te klasy wyjątków mają następującą hierarchię dziedziczenia (począwszy od Exception):
    Exception
        RuntimeException
            MojRuntimeWyjatek
                MojKolejnyRuntimeWyjatek
Klasy te mają w swojej hierarchii klasę RuntimeException, są więc wyjątkami rodzaju Unchecked.
Wyjątki Checked zazwyczaj chcemy obsłużyć, a potencjał ich rzucenia przez metodę zaznaczamy w sygnaturze metody za pomocą słowa kluczowego throws. Jest to informacja dla każdego użytkownika tej metody: "Jeśli będziesz korzystał z tej metody, to mogą się pojawić takie a takie wyjątki – powinieneś wziąć je pod uwagę i obsłużyć wedle własnego uznania."
Wyjątki Unchecked są często spowodowane błędnym stanem naszego programu i mogłoby być ciężko zareagować na nie w odpowiedni sposób.
Istnieje jeszcze trzeci rodzaj wyjątków, które są pochodnymi klasy Error. Wyjątki te to błędy krytyczne, których za bardzo nie da się obsłużyć, np. OutOfMemoryError.
```

## Definiowanie i rzucanie wyjątków

```text
Jeżeli nasza metoda może rzucić wyjątki rodzaju Checked, to musimy zaznaczyć to w sygnaturze tej metody za pomocą słowa kluczowego throws:
    public Osoba(String imie, String nazwisko, int wiek)
        throws NieprawidlowaWartoscException, NieprawidlowyWiekException {
Powyższy konstruktor klasy Osoba może rzucić wyjątek NieprawidlowyWiekException lub NieprawidlowaWartoscException.
Korzystając z metody, która sygnalizuje, że może rzucić wyjątek, musimy:
    umieścić wykonanie takiej metody w bloku try..catch i obsłużyć rzucane wyjątki lub
    do sygnatury metody, która korzysta z metody, która rzuca wyjątki, także dodać throws, oddelegowując w ten sposób obsługę wyjątków do kolejnej metody, która będzie z tej metody korzystać.
Rzucanie wyjątków odbywa się poprzez użycie słowa kluczowego throw, po którym następuje tworzenie obiektu wyjątku takiego typu, jaki chcemy rzucić:
    throw new IllegalArgumentException("Wiek nie moze byc ujemny.");
throw i throws to dwa różne słowa kluczowe – pierwsze stosujemy do rzucania wyjątków, a drugie to sygnalizowania w sygnaturze metody, że może ona rzucić pewne wyjątki.
W momencie rzucenia wyjątku przerywany jest aktualnie wykonywany blok kodu.
Metoda nie musi zwrócić wartości za pomocą return, jeżeli rzuci wyjątek.
Wyjątki możemy rzucić ponownie za pomocą throw (exception rethrow):
    try {
      // ...
      // instrukcje ktora moga spowodowac PewienWyjatek
      // ...
    } catch (PewienWyjatek e) {
      // zapisz informacje o bledzie do pliku logu 
      log.error("Wystapil blad " + e.getMessage());
      // rzuc wyjatek dalej 
      throw e;
    }
Wyjątki to obiekty klasy, więc mogą mieć własne konstruktory, metody, i pola.
Cechą specjalną wyjątków jest jest to, że rozszerzają (pośrednio bądź bezpośrednio) klasę Exception:
    class NieprawidlowyWiekException extends Exception {
    }
Zgodnie z konwencją nazewniczą klas wyjątków, na końcu nazwy takiej klasy dodajemy słowo Exception.
Możemy w prosty sposób umożliwić zapisywanie w wyjątku komunikatu błędu. Aby to osiągnąć, należy do klasy wyjątku dodać konstruktor, który przyjmie komunikat, a następnie przekaże go do konstruktora klasy nadrzędnej, gdzie zostanie zapamiętany w polu message. Komunikat wyjątku będzie później dostępny za pomocą metody getMessage.
```

```java
public class NieprawidlowaWartoscException extends Exception {
  public NieprawidlowaWartoscException(String message) {
    // wywolaj konstruktor z klasy bazowej (czyli z Exception)
    super(message);
  }
}
```

## Sprawdzanie rzucanych wyjątków i ich rodzaju

```text
Aby sprawdzić, jakie wyjątki może rzucić metoda, należy zajrzeć do dokumentacji tej metody w Java Doc jeżeli jest to metoda należąca do Biblioteki Standardowej Java, lub do odpowiedniej dokumentacji biblioteki, z której ta metoda pochodzi.
W komentarzach dokumentacyjnych potencjał rzucenia przez metodę wyjątku opisywany jest za pomocą sekcji @exception.
Sprawdzanie rodzaju wyjątku sprowadza się do analizy jego hierarchii dziedziczenia – jeżeli jest w niej zawarta klasa RuntimeException, oznacza to, że jest to wyjątek Unchecked i nie trzeba go obsługiwać w try..catch.
Hierarchię dziedziczenia można sprawdzić w dokumentacji – jeżeli korzystamy z klasy ze Standardowej Biblioteki Java, to informację o klasach znajdziemy w Java Doc.
Dla wyjątku IllegalArgumentException hierarchia dziedziczenia wygląda następująco:
```

```java
java.lang.Object
    java.lang.Throwable
        java.lang.Exception
            java.lang.RuntimeException
                java.lang.IllegalArgumentException
```

