## 09. Testowanie kodu

Źródło: https://kursjava.com/testowanie-kodu/

## Spis treści

- [1. Wstęp do testowania](#1-wstęp-do-testowania)
- [2. Pierwsze testy](#2-pierwsze-testy)
  - [Testy w osobnych metodach](#testy-w-osobnych-metodach)
  - [Informowanie tylko o błędnym działaniu](#informowanie-tylko-o-błędnym-działaniu)
  - [Więcej przypadków testowych](#więcej-przypadków-testowych)
  - [Duplikacja kodu](#duplikacja-kodu)
- [3. Given .. when .. then](#3-given--when--then)
- [4. Przykłady testów](#4-przykłady-testów)
  - [Wartość bezwzględna](#wartość-bezwzględna)
  - [Metoda sprawdzająca, czy tablica zawiera element](#metoda-sprawdzająca-czy-tablica-zawiera-element)

### 1. Wstęp do testowania
Pisząc kod naszych programów, powinniśmy upewnić się, że działa on prawidłowo – w tym celu przeprowadzamy testy.

Testy najmniejszych jednostek naszych programów, czyli metod, nazywane są testami jednostkowymi. Mają one za zadanie sprawdzić, że dana metoda działa tak, jak tego oczekiwaliśmy.

Testy jednostkowe także są metodami – są to metody, które wywołują testowaną przez nie metodę z różnymi parametrami i sprawdzają jej wynik. Jeżeli nie jest zgodny z oczekiwaniami, informują o błędzie.

Pisanie testów jednostkowych jest bardzo ważną umiejętnością. Pisanie testów nie jest proste – kod, który będziemy testować, musi być testowalny, tzn. musi być napisany z myślą o tym, że będzie testowany. Może się to wydawać dziwne, ale już teraz wiemy, po siedmiu rozdziałach tego kursu, że to samo w programowaniu prawie zawsze można zapisać na kilka sposobów.

W związku z tym, że pisanie testów jest umiejętnością, która wymaga praktyki, warto od teraz pisać testy jednostkowe do naszych programów. Aby sprawnie pisać testy jednostkowe, musimy zacząć myśleć o tworzeniu kodu pod kątem tego, by był testowalny. Wymaga to innego podejścia do tworzenia kodu, ponieważ zawsze mamy na względzie dostarczenie takiego rozwiązania, które będzie można (łatwo) przetestować.

### 2. Pierwsze testy

Jak już wcześniej wspomniano, testy to metody, które wywołują testowaną metodę. Spróbujmy w takim razie napisać nasze pierwsze testy jednostkowe – obiektem naszych testów będzie metoda, która podnosi przesłaną do niej liczbę całkowitą do kwadratu i zwraca wynik. Zacznijmy od napisania tej metody:
Nazwa pliku: TestowanieDoKwadratu.java

    public class TestowanieDoKwadratu {
      public static int doKwadratu(int x) {
        return x * x;
      }
    }

Nasz program jest bardzo prosty – nie ma jeszcze nawet metody main, ale ma za to metodę doKwadratu, która przyjmuje argument typu int i zwraca wartość typu int – w tym przypadku, jest to wartość argumentu x podniesiona do kwadratu.

Jak możemy przetestować, że nasza metoda działa poprawnie? Moglibyśmy dopisać metodę main i w niej wywołać metodę doKwadratu i wypisać jej wynik na ekran:
Nazwa pliku: TestowanieDoKwadratu.java

    public class TestowanieDoKwadratu {
      public static void main(String[] args) {
        System.out.println(doKwadratu(20));
      }
      public static int doKwadratu(int x) {
        return x * x;
      }
    }

Program uruchamia się i wypisuje poprawną wartość na ekranie: 400. Metoda main testuje naszą metodę, jednak nie jest to rozwiązanie docelowe, ponieważ możemy je ulepszyć na trzy sposoby:

    Po pierwsze, chcielibyśmy umieszczać testy z różnymi danymi wejściowymi w osobnych metodach, a nie w metodzie main.
    Po drugie, lepiej byłoby, gdyby testy informowały nas tylko o przypadkach błędnych – gdy wszystko jest w porządku i testowana metoda zadziałała poprawnie, nie chcemy widzieć nic na ekranie. Interesują nas przypadki, gdy coś poszło nie tak.
    Po trzecie, chcielibyśmy mieć więcej przypadków testowych, w najlepszym przypadku pokrywających wszystkie możliwości, a na pewno przypadki szczególne.

Dostosujmy nasz program krok po kroku zgodnie z powyższymi wytycznymi.

#### Testy w osobnych metodach

Przenieśmy test do osobnej metody:
Nazwa pliku: TestowanieDoKwadratu.java

    public class TestowanieDoKwadratu {
      public static void main(String[] args) {
        doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu(); // (1)
      }
      public static int doKwadratu(int x) {
        return x * x;
      }
      // (2)
      public static void doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu() {
        System.out.println(doKwadratu(20));
      }
    }

Przenieśliśmy test do osobnej metody o bardzo długiej nazwie (2). Istnieją różne konwencje, które definiują, jak powinny być nazywane metody testowe – jedną z nich jest konwencja, wedle której metody powinny być nazywane w następujący sposób:

nazwaTestowanejMetody_daneWejściowe_spodziewanyWynik

    Pierwszy człon nazwy metody testowej to nazwa metody, którą testujemy – w naszym programie testujemy metodę o nazwie doKwadratu, więc metoda, która ją testuje, zaczyna się właśnie od "doKwadratu".
    W środku nazwy metody testowej umieszczamy krótki opis danych wejściowych – nasza metoda testowa testuje podnoszenie do kwadratu liczby dodatniej – stąd drugi człon nazwy to "wartoscDodatnia".
    Na końcu umieszczamy krótki opis wyniku, jakiego się spodziewamy, że testowana metoda zwróci dla danych wejściowych – w naszym przypadku oczekujemy, że metoda doKwadratu dla wartości dodatniej zwróci "wartoscPodniesionaDoKwadratu".

Dodatkowo, skoro przenieśliśmy test metody doKwadratu do osobnej metody, to musimy teraz w jakiś sposób wywołać test tej metody. Test ten znajduje się teraz w nowej metodzie o nazwie doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu, więc musimy wywołać tą metodę w metodzie main (1), aby przeprowadzić test metody doKwadratu.
W jednych z kolejnych rozdziałów tego kursu nauczymy się, jak wydzielać testy do osobnych plików i jak uruchamiać je w prosty sposób przy użyciu biblioteki JUnit.

Zwróćmy uwagę, że metoda testowa nic nie zwraca – jej zwracany typ to void. Metody testowe nie powinny zwracać wartości, ponieważ nie jest to do niczego wymagane.

#### Informowanie tylko o błędnym działaniu

Test jest już w osobnej metodzie – teraz zajmiemy się drugim opisanym powyżej punktem: chcielibyśmy, aby nasz test komunikował tylko informacje o przypadkach testowych, które nie przeszły testu – jeżeli wszystko poszło dobrze, to test powinien po prostu "siedzieć cicho".

Jak możemy to osiągnąć? Wystarczy, że sprawdzimy wynik metody – jeżeli będzie inny, niż się spodziewamy, wtedy wypiszemy na ekran komunikat informujący, że testowana przez nas metoda zwróciła dla danego argumentu inny wynik, niż oczekiwaliśmy:
Nazwa pliku: TestowanieDoKwadratu.java

    public class TestowanieDoKwadratu {
      public static void main(String[] args) {
        doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu();
      }
      public static int doKwadratu(int x) {
        return x * x;
      }
      public static void doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu() {
        int wynik = doKwadratu(20); // (1)
        if (wynik != 400) { // (2)
          // (3)
          System.out.println(
              "Dla liczby 20 wyliczono nieprawidlowy kwadrat: " + wynik
          );
        }
      }
    }

Nasz program nie wypisuje już po prostu wartości podniesionej do kwadratu. Zamiast tego:

    Najpierw wyliczamy kwadrat liczby 20 i zapisujemy wynik w zmiennej wynik (1).
    Następnie, w instrukcji warunkowej sprawdzamy otrzymany wynik – jeżeli jest inny, niż się spodziewamy (2), wypiszemy na ekran informację.
    Jeżeli metoda doKwadratu niepoprawnie wyliczy kwadrat liczby 20, to wypisujemy na ekran komunikat informujący, że testowana metoda zadziałała niepoprawnie (3), bo źle wykonała swoje zadanie (zwróciła błędny wynik). Świadczy to o tym, że test nie zakończył się sukcesem.

Jeżeli metoda doKwadratu zadziała poprawnie, to test nic nie wypisze. Dzięki temu będziemy informowani tylko w tych przypadkach, gdy coś w naszym kodzie nie działa bądź przestało działać.

#### Więcej przypadków testowych

Jeden test to zazwyczaj za mało, by przetestować wszystkie przypadki. Warto zastanowić się zawsze nad przypadkami szczególnymi, np.:

    przypadkami, które wydaje nam się, że nigdy nie wystąpią,
    przypadkami skrajnych danych wejściowych,
    przypadkami z nieprawidłowymi danymi wejściowymi.

Poza tym testujemy też oczywiście metody używając "spodziewanych" argumentów.

Ważne jest także, aby nie duplikować przypadków testowych – jeżeli testujemy metodę doKwadratu i napisaliśmy test, który sprawdza, czy metoda ta dla argumentu 5 kończy się sukcesem (tzn. metoda doKwadratu działa poprawnie), to nie ma potrzeby pisania testu, który sprawdzi wynik metody doKwadratu dla liczby 10 – test z liczbą 5 pokrył już podobny przypadek.

Dopiszmy jeszcze dwa testy do metody doKwadratu:

    sprawdzimy, jak metoda zachowa się dla argumentu, którym będzie liczbą ujemna,
    sprawdzimy, co stanie się dla argumentu równego zero.

Nazwa pliku: TestowanieDoKwadratu.java

    public class TestowanieDoKwadratu {
      public static void main(String[] args) {
        doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu();
        doKwadratu_wartoscUjemna_wartoscPodniesionaDoKwadratu(); // (1)
        doKwadratu_liczbaZero_zero(); // (2)
      }
      public static int doKwadratu(int x) {
        return x * x;
      }
      public static void doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu() {
        int wynik = doKwadratu(20);
        if (wynik != 400) {
          System.out.println(
               "Dla liczby 20 wyliczono nieprawidlowy kwadrat: " + wynik
           );
        }
      }
      // (3)
      public static void doKwadratu_wartoscUjemna_wartoscPodniesionaDoKwadratu() {
        int wynik = doKwadratu(-5);
        if (wynik != 25) {
          System.out.println(
              "Dla liczby -5 wyliczono nieprawidlowy kwadrat: " + wynik
          );
        }
      }
      // (4)
      public static void doKwadratu_liczbaZero_zero() {
        int wynik = doKwadratu(0);
        if (wynik != 0) {
          System.out.println(
              "Dla liczby 0 wyliczono nieprawidlowy kwadrat: " + wynik
          );
        }
      }
    }

W ostatecznej wersji naszego programu dodaliśmy dwa nowe testy (3) (4) oraz wywołaliśmy je w metodzie main (1) (2). Po uruchomieniu nasz program nic nie wypisuje – świadczy to o tym, że nasza metoda doKwadratu działa poprawnie!
Czy możemy być pewni, że faktycznie tak jest? Czy nie zapomnieliśmy o jeszcze jakimś przypadku testowym? Do zastanowienia jako zadanie!

#### Duplikacja kodu

Wróćmy jeszcze na chwilę do finalnej wersji kodu z poprzedniego rozdziału – spójrzmy na kod naszych testów:

    public static void doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu() {
      int wynik = doKwadratu(20);
      if (wynik != 400) {
        System.out.println(
            "Dla liczby 20 wyliczono nieprawidlowy kwadrat: " + wynik
        );
      }
    }
    public static void doKwadratu_wartoscUjemna_wartoscPodniesionaDoKwadratu() {
      int wynik = doKwadratu(-5);
      if (wynik != 25) {
        System.out.println(
            "Dla liczby -5 wyliczono nieprawidlowy kwadrat: " + wynik
        );
      }
    }
    public static void doKwadratu_liczbaZero_zero() {
      int wynik = doKwadratu(0);
      if (wynik != 0) {
        System.out.println(
            "Dla liczby 0 wyliczono nieprawidlowy kwadrat: " + wynik
        );
      }
    }

Widzimy, że każda metoda testowa napisana jest w podobny sposób – najpierw wywołujemy testowaną metodę, a potem sprawdzamy, czy wynik jest poprawny. Czy moglibyśmy w takim razie jakoś uprościć powyższe metody i pozbyć się duplikacji kodu?

Moglibyśmy napisać kolejną metodę, której zadaniem będzie sprawdzenie, czy przesłane do niej argumenty są sobie równe. Jeżeli nie, metoda wypisze na ekran komunikat. Następnie moglibyśmy tej nowej metody użyć w naszych testach. Spójrzmy na kolejną wersję naszego programu:
Nazwa pliku: TestowanieDoKwadratu2.java

    public class TestowanieDoKwadratu2 {
      public static void main(String[] args) {
        doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu();
        doKwadratu_wartoscUjemna_wartoscPodniesionaDoKwadratu();
        doKwadratu_liczbaZero_zero();
      }
      public static int doKwadratu(int x) {
        return x * x;
      }
      public static void doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu() {
        int wynik = doKwadratu(20);
        assertEquals(400, wynik); // (1)
      }
      public static void doKwadratu_wartoscUjemna_wartoscPodniesionaDoKwadratu() {
        int wynik = doKwadratu(-5);
        assertEquals(25, wynik); // (2)
      }
      public static void doKwadratu_liczbaZero_zero() {
        int wynik = doKwadratu(0);
        assertEquals(0, wynik); // (3)
      }
      //                    (4)              (5)          (6)
      public static void assertEquals(int expected, int actual) {
        if (expected != actual) { // (7)
          System.out.println("Spodziewano sie liczby " + actual +
            ", ale otrzymano: " + expected);
        }
      }
    }

Nowa metoda assertEquals (4) przyjmuje dwa parametry typu int o nazwach expected (5) oraz actual (6). Jej jedynym zadaniem jest wypisanie komunikatu, gdy liczby przesłane jako argumenty nie są sobie równe (7). Dzięki tej metodzie, mogliśmy znacząco skrócić nasze metody testowe – metody assertEquals używamy teraz do sprawdzenia wyniku działania metody doKwadratu (1) (2) (3).

Nazwa metody (assert – zapewnij) oraz nazwy jej argumentów nie są przypadkowe. Wiele bibliotek wspierających testy jednostkowe udostępnia metodę o właśnie takiej nazwie i takich argumentach – wykonują one bardzo podobne zadanie jak nasza powyższa metoda assertEquals (chociaż komunikuje nierówność argumentów w inny sposób, który poznamy w jednym z kolejnych rozdziałów).ram krok po kroku zgodnie z powyższymi wytycznymi.jest umiejętnością, która wymaga praktyki, warto od teraz pisać testy jednostkowe do naszych programów. Aby sprawnie pisać testy jednostkowe, musimy zacząć myśleć o tworzeniu kodu pod kątem tego, by był testowalny. Wymaga to innego podejścia do tworzenia kodu, ponieważ zawsze mamy na względzie dostarczenie takiego rozwiązania, które będzie można (łatwo) przetestować.

### 3. Given .. when .. then

Istnieje popularna konwencja definiująca, jak testy jednostkowe powinny być ustrukturyzowane. Zakłada ona, iż:

    Na początku testu przygotowujemy dane, które posłużą jako dane wejściowe, na których testowana metoda będzie działać. Ta część testu nazywa się given.
    Następnie, wywołujemy testowaną metodę na danych przygotowanych w punkcie pierwszym. Ta część testu to when.
    Na końcu testu sprawdzamy, czy wynik zwrócony przez metodę wywołaną w punkcie drugim jest zgodne z naszymi oczekiwaniami. Ta ostatnia część testu to then.

Konstruując test w ten sposób dostajemy logiczną całość:

    dla takich a takich danych (given),
    gdy wykonam taką metodę (when),
    powinienem otrzymać taki a taki rezultat (then).

Spójrzmy na przykład jednego z testów z poprzedniego programu napisanego w taki właśnie sposób. Dla porównania dodana została także poprzednia wersja tego testu:

    public static void doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu() {
      int wynik = doKwadratu(20);
      assertEquals(400, wynik);
    }
    public static void doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu2() {
      // given
      int liczba = 20;
      // when
      int wynik = doKwadratu(liczba);
      // then
      assertEquals(400, wynik);
    }

Druga metoda stosuje opisaną powyżej konwencję – komentarzami oddzieliliśmy od siebie poszczególne części testu. Ze względu na czytelność, testy są często pisane w ten właśnie sposób.

Nasz test jest prosty i w zasadzie nie ma potrzeby na tworzenie zmiennej liczba tylko po to, by przypisać do niej wartość i użyć jej jako argument metody doKwadratu. Celem było ukazanie przykładu konwencji given .. when .. then. Mniej skomplikowane testy możemy zapisywać zwięźlej, nie korzystając z konwencji given .. when .. then. Wszystko zależy od tego, którą wersję z testów uważamy za czytelniejszą i łatwiejszą do zrozumienia przez innych programistów.


Dlaczego testy jednostkowe są tak ważne? Wynika to z następujących faktów:

    testy jednostkowe pozwalają nam na wyizolowanie i przetestowanie najmniejszych części naszego programu, a co za tym idzie, upewnienie się, że dostarczamy działające rozwiązanie o wysokiej jakości,
    testy stoją na straży przed potencjalnym wprowadzeniem błędów (bugów) w kodzie w przyszłości – po modyfikacji kodu, np. związanej z dodaniem nowej funkcjonalności, uruchamiamy testy i sprawdzamy, czy w wyniku naszych zmian nie zepsuliśmy systemu,
    testy wymuszają styl tworzenia kodu, który jest czytelniejszy, łatwiejszy w utrzymaniu i zmianie,
    posiadanie zestawu testów pozwala na wprowadzanie zmian do kodu bez obawy, że coś zepsujemy – po wprowadzeniu zmiany możemy uruchomić testy i upewnić się, że testy przechodzą bez błędów,
    testy służą jako dokumentacja dla innych programistów – jeżeli otestujemy nasz kod, to można, na podstawie analizy kodu testów, dowiedzieć się, jak działa funkcjonalność dostarczana przez metody, które są przez te testy testowane.


Co zrobić, aby kod był testowalny i co to w ogóle znaczy?

Testowalny kod to taki, do którego można bez problemu przygotować zestaw dokładnych testów jednostkowych. Metody muszą spełniać trzy podstawowe zasady, aby były testowalne:

    muszą być zwięzłe, tzn. krótkie – im krótsze, tym lepsze (kilka – kilkanaście linii kodu),
    muszą robić jedną rzecz,
    nie mogą zależeć od użytkownika.

Dlaczego testowalny kod musi spełniać takie wymogi? Im mniej akcji wykonuje metoda, tym mniej testów jednostkowych będzie wymagała, i tym prostsze one będą. Jeżeli napiszemy metodę na 100 linii kodu, która będzie robiła kilkanaście rzeczy, trudno będzie napisać testy jednostkowe, które sprawdzą wszystkie możliwe ścieżki wykonania i przetestują wszystkie funkcjonalności.

Dodatkowo, metody, które wymagają od programisty wykonania jakiejś akcji, są niewygodne, ponieważ nie chcemy być zmuszeni do interakcji z naszymi testami, które będziemy często uruchamiać i których mogą być tysiące.

Powinniśmy dążyć do rozbicia dużych metod na małe, wydzielone jednostki, które otestujemy, a następnie, będąc upewnieni, że działają, będziemy z nich korzystać w kolejnych metodach.
Małe metody to maksymalnie kilka-kilkanaście linijek kodu.

Spójrzmy na poniższą metodę i zastanówmy się nad następującymi pytaniami:

    Czy taką metodę łatwo przetestować?
    Ile operacji wykonuje ta metoda?
    Czy możemy ją usprawnić, aby można ją było przetestować w łatwy sposób?

Nazwa pliku: TestowanieCzyParzystaWersja1.java

    import java.util.Scanner;
    public class TestowanieCzyParzysta {
      public static void main(String[] args) {
        czyParzysta();
      }
      public static void czyParzysta() {
        System.out.println(
            "Prosze podac liczbe - sprawdze, czy jest parzysta."
        );
        int liczba = getInt();
        if (liczba % 2 == 0) {
          System.out.println("Ta liczba jest parzysta.");
        } else {
          System.out.println("Ta liczba jest nieparzysta");
        }
      }
      public static int getInt() {
        return new Scanner(System.in).nextInt();
      }
    }

    Bez interakcji użytkownika metoda ta jest nie do przetestowania. Pyta ona użytkownika o liczbę i czeka na jej podanie. W obecnej formie, aby ją przetestować, musielibyśmy uruchomić powyższy program wielokrotnie, wpisując w oknie konsoli różne liczby i sprawdzić wynik wypisany na ekranie.
    Metoda ta wykonuje wiele operacji:
        Wypisuje komunikat z prośbą o podanie liczby.
        Pobiera od użytkownika liczbę przy użyciu metody getInt.
        Sprawdza parzystość liczby.
        Na podstawie parzystości liczby wypisuje komunikat.
    Zastanówmy się, co moglibyśmy usprawnić w naszej metodzie?
        Najważniejsze to przenieść pobieranie liczby od użytkownika poza metodę czyParzysta. Dzięki temu nasza metoda nie będzie już uzależniona od użytkownika.
        Skoro nie będziemy pobierać liczby w metodzie czyParzysta, to musimy jakoś przesłać do tej metody liczbę do sprawdzenia – łatwo możemy to osiągnąć, gdy zmienimy metodę, by przyjmowała jeden argument typu int.
        Nasza metoda nie powinna wypisywać na ekran wyniku sprawdzenia parzystości – ktokolwiek będzie używał naszej metody powinien sam zadecydować, co chce z tą informacją (czy liczba jest parzysta czy nie) zrobić. Skoro tak, to nasza metoda powinna zwrócić informację o tym, czy liczba jest parzysta czy nie – osiągniemy to poprzez zmianę zwracanego przez metodę typu z void na boolean (prawda / fałsz).

Spójrzmy na usprawnioną wersję powyższego program:
Nazwa pliku: TestowanieCzyParzystaWersja2.java

    import java.util.Scanner;
    public class TestowanieCzyParzystaWersja2 {
      public static void main(String[] args) {
        // (1)
        System.out.println(
            "Prosze podac liczbe - sprawdze, czy jest parzysta."
        );
        int liczba = getInt(); // (2)
        if (czyParzysta(liczba)) {
          System.out.println("Ta liczba jest parzysta."); // (3)
        } else {
          System.out.println("Ta liczba jest nieparzysta"); // (4)
        }
      }
      //        (5)          (6)
      public static boolean czyParzysta(int x) {
        return x % 2 == 0; // (7)
      }
      public static int getInt() {
        return new Scanner(System.in).nextInt();
      }
    }

Wykonane zmiany:

    Przeniesienie wypisania prośby o podanie liczby (1).
    Przeniesienie pobierania liczby od użytkownika (2).
    Przeniesienie wypisywania komunikatu zależnego od parzystości liczby (3) (4).
    Zmiana typu zwracanego przez metodę czyParzysta z void na boolean (5).
    Dodanie argumentu do metody czyParzysta, który ma być sprawdzony pod kątem parzystości (6).
    Zwrócenie informacji, czy przesłana w argumencie liczba jest parzysta, czy nie (7).

Dzięki powyższym zmianom, metoda czyParzysta bardzo się uprościła. Wykonuje ona teraz jedno konkretne zadanie, którym jest sprawdzenie parzystości podanego do niej argumentu i zwrócenie wyniku. Taką metodę łatwo przetestować – wystarczy napisać testy, które wywołają metodę czyParzysta z różnymi wartościami i sprawdzą wynik. Takie testy będą: szybkie, proste, i nie będą wymagały w trakcie wykonywania żadnych akcji od programisty.

Dobre testy jednostkowe charakteryzują się następującymi cechami:

    sprawdzają wszystkie możliwe ścieżki wykonania metody – by mieć pewność, że metoda będzie działała w różnych warunkach,
    są krótkie – a dzięki temu łatwe w zrozumieniu i utrzymaniu,
    są czytelne i schludnie napisane – wymóg, który powinien spełniać każdy kod,
    pozwalają na zrozumienie testowanego kodu – dzięki temu będą dodatkowo służyć jako dokumentacja tego, co testują,
    są szybkie – abyśmy nie musieli czekać kilka minut na to, aż się wykonają,
    jeden zestaw danych testowych na jedną metodę testową – zazwyczaj jeden przypadek testowy testowany jest w jednej metodzie testującej – zamiast wykonywać wszystkie testy w jednej metodzie, chcemy odseparować od siebie różne przypadki testowe.

Można zadać teraz dwa pytania:

    a co z testami testów?
    co dzieje się w przypadku, jeżeli musimy zmienić kod, przez co testy przestaną działać?

W pierwszym przypadku odpowiedź jest bardzo prosta:
Testy testują kod produkcyjny – kod produkcyjny testuje testy.

Oznacza to, że jeżeli źle napiszemy testy, to nie będą one działać. Istnieje tutaj zależność działania kodu testów od kodu produkcyjnego i poprawności kodu produkcyjnego od działania testów.

A jeżeli kod produkcyjny się zmienia i testy przestają działać? Wtedy zmieniamy testy by odzwierciedlały zmiany w kodzie produkcyjnym, dodajemy nowe, usuwamy stare, jeżeli są już niepotrzebne. Należy pamiętać, że o testy należy dbać tak samo, jak o kod produkcyjny, jeżeli nie bardziej – w końcu to one stoją na straży naszego kodu. 


Poniżej przedstawionych zostało kilka przykładów testów jednostkowych różnych metod.

### 4. Przykłady testów

#### Wartość bezwzględna

Spójrzmy na prosty przykład testów metody, której zadaniem jest zwrócenie wartości bezwględnej przesłanej liczby.
Wartość bezwzględna to wartość liczby bez uwzględnienia jej znaku.
Dla liczb parzystych jest to ta sama liczba. Przykład: wartość bezwzględna liczby 10 to 10.
Dla liczb ujemnych jest to liczba bez minusa. Przykład: wartość bezwzględna liczby -5 to 5.

Zanim jednak przejdziemy do kodu źródłowego, zastanówmy się jak przetestowalibyśmy taką metodę? Jakie przypadki testowe wzięlibyśmy pod uwagę?
Nazwa pliku: TestowanieWartoscBezwzgledna.java

    public class TestowanieWartoscBezwzgledna {
      public static void main(String[] args) {
        wartoscBezwgledna_liczbaDodatnia_zwrociDodatniaWartosc(); // (1)
        wartoscBezwgledna_liczbaUjemna_zwrociDodatniaWartosc();   // (2)
      }
      public static int wartoscBezwgledna(int x) { // (3)
        return x < 0 ? -x : x;
      }
      public static void
          wartoscBezwgledna_liczbaDodatnia_zwrociDodatniaWartosc() { // (4)
        int rezultat = wartoscBezwgledna(20); // (5)
        if (rezultat != 20) { // (6)
          System.out.println( // (7)
            "Dla wartosci 20 otrzymano nieprawidlowa wartosc: " + rezultat
          );
        }
      }
      public static void
          wartoscBezwgledna_liczbaUjemna_zwrociDodatniaWartosc() { // (8)
        int rezultat = wartoscBezwgledna(-1); // (9)
        if (rezultat != 1) { // (10)
          System.out.println( // (11)
            "Dla wartosci -1 otrzymano nieprawidlowa wartosc: " + rezultat
          );
        }
      }
    }

Przejdźmy krok po kroku przez powyższy kod:

    Metoda, którą chcemy przetestować, to wartoscBezwzgledna (3). Jest to prosta metoda wykonując jedno zadanie – dla podanej jako argument liczby zwraca wartość bezwzględną tej liczby.
    Metoda wartoscBezwzgledna testowana jest przez dwie metody (4) (8).
    Dane testowe pierwszej metody to liczba dodatnia (5), a drugiej metody – liczba ujemna (9).
    Obie metody testowe sprawdzają, czy wynik jest nieprawidłowy (6) (10) – jeśli tak, to wypisują informację na ekran (7) (11).
    Metody testowe wywołujemy w metodzie main (1) (2).

#### Metoda sprawdzająca, czy tablica zawiera element

Chcielibyśmy napisać metodę, która będzie przyjmowała jako argument tablicę liczb i odpowiadała na pytanie, czy w tej tablicy znajduje się dana liczba.

Jakie przypadki testowe powinniśmy przygotować?

Powinniśmy na pewno sprawdzić następujące przypadki:

    Co się stanie, jeżeli tablica jest pusta? – zawsze warto sprawdzić zachowanie na "pustych" danych.
    Co się stanie, jeżeli niepusta tablica nie zawiera szukanego elementu? – podczas wyszukiwania elementów warto sprawdzić co dzieje się, gdy element nie istnieje.
    Co się stanie, jeżeli niepusta tablica zawiera szukany element? – "normalny" przypadek.
    Co się stanie, jeżeli niepusta tablica zawiera szukany element wyłącznie na samym początku tablicy? – w przypadku operacji na tablicach warto brać pod uwagę przypadki testowe sprawdzające zachowanie biorące pod uwagę pierwszy element tablicy.
    Co się stanie, jeżeli niepusta tablica zawiera szukany element wyłącznie na samym końcu tablicy? – jak wyżej, ale tym razem bierzemy pod uwagę element końcowy.

Poniższy kod źródłowy przedstawia, jak moglibyśmy napisać powyższy program wraz z testami:
Nazwa pliku: TestowanieCzyElementWTablicy.java

    public class TestowanieCzyElementWTablicy {
      public static void main(String[] args) {
        czyZawieraElement_pustaTablica_zwrociFalse();
        czyZawieraElement_brakSzukanegoElementu_zwrociFalse();
        czyZawieraElement_zawieraSukanyElement_zwrociTrue();
        czyZawieraElement_zawieraSukanyElementNaPoczatku_zwrociTrue();
        czyZawieraElement_zawieraSukanyElementNaKoncu_zwrociTrue();
      }
      public static boolean czyZawieraElement(int[] tablica, int liczba) {
        for (int i = 0; i < tablica.length; i++) {
          if (tablica[i] == liczba) {
            return true;
          }
        }
        return false;
      }
      public static void czyZawieraElement_pustaTablica_zwrociFalse() {
        // given
        int[] pustaTablica = {};
        int liczba = 5;
        // when
        boolean czyZawiera = czyZawieraElement(pustaTablica, liczba);
        // then
        if (czyZawiera) {
          System.out.println(
              "Blad! Pusta tablica nie powinna nic zawierac."
          );
        }
      }
      public static void czyZawieraElement_brakSzukanegoElementu_zwrociFalse() {
        // given
        int[] tablica = {-20, 100, 500};
        int liczba = 128;
        // when
        boolean czyZawiera = czyZawieraElement(tablica, liczba);
        // then
        if (czyZawiera) {
          System.out.println(
              "Blad! Element 128 nie powinien byc znaleziony."
          );
        }
      }
      public static void czyZawieraElement_zawieraSukanyElement_zwrociTrue() {
        // given
        int[] tablica = {2, 4, 8, 16, 32, 64, 128, 256};
        int liczba = 128;
        // when
        boolean czyZawiera = czyZawieraElement(tablica, liczba);
        // then
        if (!czyZawiera) {
          System.out.println(
              "Blad! Element 128 powinien byc znaleziony."
          );
        }
      }
      public static void czyZawieraElement_zawieraSukanyElementNaPoczatku_zwrociTrue() {
        // given
        int[] tablica = {100, 200, 300};
        int liczba = 100;
        // when
        boolean czyZawiera = czyZawieraElement(tablica, liczba);
        // then
        if (!czyZawiera) {
          System.out.println(
              "Blad! Element 100 powinien byc znaleziony."
          );
        }
      }
      public static void czyZawieraElement_zawieraSukanyElementNaKoncu_zwrociTrue() {
        // given
        int[] tablica = {100, 200, 300};
        int liczba = 300;
        // when
        boolean czyZawiera = czyZawieraElement(tablica, liczba);
        // then
        if (!czyZawiera) {
          System.out.println(
              "Blad! Element 300 powinien byc znaleziony."
          );
        }
      }
    }

Testy zapisaliśmy zgodnie z notacją given .. when .. then – najpierw przygotowujemy dane, następnie wywołujemy testowaną metodą i zapisujemy jej wynik, a na końcu sprawdzamy wynik.

Powyższe testy są proste – mogłyby również być zapisane w bardziej zwięzły sposób, na przykład:

    public static void czyZawieraElement_zawieraSukanyElementNaKoncu_zwrociTrue() {
      if (!czyZawieraElement(new int[] {100, 200, 300}, 300)) {
        System.out.println(
            "Blad! Element 300 powinien byc znaleziony."
        );
      }
    }

Powyższy przykład pomija sekcje "given", "when" oraz "then" i znacząco skraca test – jest on na tyle prosty, że taka postać mogłaby być preferowana, ale w ramach ćwiczenia i przykładu użyta została konwencja given .. when .. then.
Pamiętajmy! Testy powinny być czytelne, ale krótsze testy to mniej kodu do zrozumienia i utrzymania. Należy zawsze zastanowić się czy napisany przez nas kod testu zyskałby na rozbiciu go na sekcje given .. when .. then, czy może lepiej byłoby napisać krótki i prosty test bez używania tej konwencji.

Test Driven Development to metodologia tworzenia oprogramowania, w której centralnym punktem są testy.

Testy kodu produkcyjnego piszemy... przed napisaniem kodu produkcyjnego! Najpierw zastanawiamy się, co nasza metoda będzie robić. Następnie piszemy test, który ma to sprawdzić. Uruchamiamy go i widzimy, że zakończył się błędem – nic dziwnego! Testowana przez niego metoda jeszcze nie istnieje. Teraz przystępujemy do pisania testowanej metody tak, aby nasz test przeszedł. Jeżeli już nam się to uda, piszemy kolejny test, testujący kolejny aspekt bądź używający innego zestawu danych, i tak w kółko, aż kod produkcyjny uznamy za gotowy.

Więcej informacji o TDD można znaleźć w internecie.


    Testy mają na celu sprawdzenie, czy nasz kod działa zgodnie z założeniami.
    Testy jednostkowe testują najmniejsze jednostki naszych programów – metody.
    Testy jednostkowe to także metody – są to metody, które uruchamiają metodę, którą testują, z różnymi parametrami i sprawdzają, czy wynik działania testowanej metody jest taki, jak zakładaliśmy.
    Musimy tworzyć nasze metody w taki sposób, by były one testowalne. Oznacza to, że nasze metody:
        powinny być krótkie (kilka do kilkunastu linii kodu) – im mniej metoda robi, tym łatwiej ją przetestować, ponieważ potrzeba mniejszej liczby przypadków testowych,
        powinny robić jedną, ustaloną rzecz – wtedy przypadki testowe będą łatwiejsze do przygotowania,
        nie powinny oczekiwać na żadne akcje użytkownika – ponieważ wtedy wykonanie testów będzie za każdym razem oczekiwało działań od użytkownika.
    Testy jednostkowe są ważne, ponieważ:
        pozwalają nam na wyizolowanie i przetestowanie najmniejszych części naszego programu,
        wymuszają styl tworzenia kodu, który jest czytelniejszy i łatwiejszy w utrzymaniu (ponieważ metody są krótsze, a co za tym idzie, łatwiej zrozumieć, co się w nich dzieje),
        pomagają nam dostarczać działający kod,
        posiadanie zestawu testów pozwala na wprowadzanie zmian do kodu bez obawy, że coś zepsujemy – po wprowadzeniu zmiany możemy uruchomić testy i upewnić się, że przechodzą one bez błędów
    Testy testują kod produkcyjny – kod produkcyjny testuje testy.
    O testy należy dbać niemniej, niż o kod produkcyjny.
    Dobre testy jednostkowe:
        sprawdzają wszystkie możliwe ścieżki wykonania metody,
        są krótkie,
        są czytelne i schludnie napisane,
        są szybkie.
    Różne przypadki testowe powinny być obsługiwane w osobnych metodach testujących.
    Testy powinny informować jedynie o błędnych przypadkach, tzn. takich, gdzie zakładana wartość była inna, niż faktyczny rezultat wykonania testowanej metody.
    Powinniśmy przygotowywać wiele przypadków testowych, by mieć pewność, że nasza metoda będzie działała zgodnie z oczekiwaniami niezależnie od danych wejściowych.
    Nie powinniśmy duplikować przypadków testowych – każdy test powinien testować pewien określony przypadek. Dla przykładu – nie ma sensu testować, czy metoda podnosząca liczbę do kwadratu zwróci poprawną wartość dla liczb 1, 2, 3 itd. – wystarczy test dla liczby np. 5.
    Istnieje wiele konwencji nazewniczych testów – jedna z nich zakłada, że najpierw podajemy nazwę testowanej metody, następnie dane wejściowe, a na końcu opisujemy spodziewany wynik:
        nazwaTestowanejMetody_daneWejściowe_spodziewanyWynik
        na przykład:
        doKwadratu_wartoscDodatnia_wartoscPodniesionaDoKwadratu
    Typem zwracanym metod testowych powinien być void.
    Podczas pisania testów często używa się tzw. asercji. Są to metody, które sprawdzają pewien warunek, na przykład czy dwie liczby są sobie równe. Jeżeli nie, informują o błędzie, a w przeciwnym razie, gdy warunek jest spełniony, nie wykonują żadnych akcji. Przykład:
        public static void assertEquals(int expected, int actual) {
          if (expected != actual) {
            System.out.println("Spodziewano sie liczby " + actual +
              ", ale otrzymano: " + expected);
          }
        }
    Given .. when .. then to konwencja definiująca, jak testy jednostkowe powinny być ustrukturyzowane. Najpierw przygotowujemy dane wejściowe dla testu (given), następnie wywołujemy testowaną metodę z użyciem przygotowanych danych (when), a na końcu sprawdzamy wynik (then):
        dla takich a takich danych (given),
        gdy wykonam taką metodę (when),
        powinienem otrzymać taki a taki rezultat (then).

Przykład testu o strukturze given .. when .. then:

    public static void czyZawieraElement_brakSzukanegoElementu_zwrociFalse() {
      // given
      int[] tablica = {-20, 100, 500};
      int liczba = 128;
      // when
      boolean czyZawiera = czyZawieraElement(tablica, liczba);
      // then
      if (czyZawiera) {
        System.out.println("Blad! Element 128 nie powinien byc znaleziony.");
      }
    }

    TDD, czyli Test Driven Development, to metodologia tworzenia oprogramowania, w której najpierw zaczynamy tworzenie kodu od napisania testu, a dopiero potem piszemy kod, które ten test ma spełnić. Działamy w ten sposób aż do momentu, aż nie uznamy kodu produkcyjnego za gotowy.


