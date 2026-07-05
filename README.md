# Materials about OOP in Java and Python

Wstęp do programowania zorientowanego obiektowo z wykorzystaniem języków Java i Python.

## Cel repozytorium

Repozytorium porządkuje materiały do nauki OOP, ze szczególnym naciskiem na Javę. Folder `lectures-OOP-in-Java` został uporządkowany do **15 kolejno ponumerowanych plików Markdown**. Układ jest teraz liniowy: od środowiska, przez model obiektowy i abstrakcję, aż po biblioteki standardowe, lambdy, testowanie, SOLID, wyjątki i referencje.

## Zakres kursu

> Tematy są zgodne z typowym zakresem przedmiotu "Programowanie obiektowe", ale zostały rozszerzone o praktykę projektową, testowanie oraz nowocześniejsze elementy Javy.

Po przerobieniu materiału student powinien:
- rozumieć model obiektowy Javy oraz rolę klas, obiektów i referencji,
- projektować klasy z poprawną enkapsulacją i czytelnym interfejsem publicznym,
- stosować konstruktory, dziedziczenie, polimorfizm, interfejsy i klasy abstrakcyjne,
- znać podstawowe klasy biblioteki standardowej i świadomie z nich korzystać,
- rozumieć wyrażenia lambda i interfejsy funkcyjne,
- pisać prostsze testy jednostkowe,
- znać podstawy projektowania zgodnego z SOLID,
- świadomie obsługiwać wyjątki i pracować z referencjami do obiektów.

## Szczegółowy program nauczania OOP w Javie

| Nr | Temat | Zakres i cele | Plik |
|---|---|---|---|
| 1 | Środowisko języka Java | JDK, JRE, JVM, proces kompilacji, uruchamianie programu, podstawowa organizacja pracy z projektem. | [01-srodowisko-javy.md](lectures-OOP-in-Java/01-srodowisko-javy.md) |
| 2 | Paradygmaty obiektowości | Czym jest OOP, cztery filary OOP, klasy, obiekty, abstrakcja problemu. | [02-paradygmaty-obiektowosci.md](lectures-OOP-in-Java/02-paradygmaty-obiektowosci.md) |
| 3 | Klasy, obiekty i metoda `main` | Definicja klasy, pola, metody, instancje obiektów, podstawowa struktura programu w Javie. | [03-klasy-i-metody-main.md](lectures-OOP-in-Java/03-klasy-i-metody-main.md) |
| 4 | Modułowość, pakiety i importy | Pakiety, importy, `static import`, konflikt nazw, podstawy organizacji większego projektu. | [04-modulowosc-pakiety-i-importy.md](lectures-OOP-in-Java/04-modulowosc-pakiety-i-importy.md) |
| 5 | Modyfikatory dostępu i enkapsulacja | `public`, `private`, `protected`, package-private, kontrola dostępu i projektowanie API klasy. | [05-modyfikatory-dostepu-i-enkapsulacja.md](lectures-OOP-in-Java/05-modyfikatory-dostepu-i-enkapsulacja.md) |
| 6 | Pola klas i stan obiektu | Pola instancyjne, stan obiektu, różnice między polami a zmiennymi lokalnymi, trwałość danych obiektu. | [06-pola-klas-i-stan-obiektu.md](lectures-OOP-in-Java/06-pola-klas-i-stan-obiektu.md) |
| 7 | Konstruktory i inicjalizacja obiektów | Konstruktory domyślne i parametryczne, przeciążanie, `this(...)`, `super(...)`, walidacja, pola `final`. | [07-konstruktory-i-inicjalizacja-obiektow.md](lectures-OOP-in-Java/07-konstruktory-i-inicjalizacja-obiektow.md) |
| 8 | Dziedziczenie i polimorfizm | `extends`, przesłanianie metod, relacja `is-a`, rzutowanie, `super`, zalety i ograniczenia dziedziczenia. | [08-dziedziczenie-i-polimorfizm.md](lectures-OOP-in-Java/08-dziedziczenie-i-polimorfizm.md) |
| 9 | Interfejsy i klasy abstrakcyjne | Kontrakty, częściowa implementacja, kompozycja, porównanie interfejsu z klasą abstrakcyjną, przykłady projektowe. | [09-interfejsy-i-klasy-abstrakcyjne.md](lectures-OOP-in-Java/09-interfejsy-i-klasy-abstrakcyjne.md) |
| 10 | Podstawowe klasy biblioteki Java | `Object`, `String`, wrappery, `Math`, `Arrays`, `Collections`, `List`, `Set`, `Map`, `Optional`, `java.time`. | [10-podstawowe-klasy-biblioteki-java.md](lectures-OOP-in-Java/10-podstawowe-klasy-biblioteki-java.md) |
| 11 | Wyrażenia lambda i interfejsy funkcyjne | Lambdy, `@FunctionalInterface`, `Predicate`, `Function`, `Consumer`, `Supplier`, referencje do metod, wstęp do Stream API. | [11-wyrazenia-lambda-i-interfejsy-funkcyjne.md](lectures-OOP-in-Java/11-wyrazenia-lambda-i-interfejsy-funkcyjne.md) |
| 12 | Testowanie kodu | Testy jednostkowe, scenariusze testowe, testowalność kodu, myślenie o projekcie pod kątem weryfikacji działania. | [12-testowanie-kodu.md](lectures-OOP-in-Java/12-testowanie-kodu.md) |
| 13 | SOLID - zasady projektowania obiektowego | Jeden scalony materiał o SOLID: wersja skrócona, rozwinięta i diagramowa, plus DRY, KISS i YAGNI. | [13-solid-zasady-projektowania-obiektowego.md](lectures-OOP-in-Java/13-solid-zasady-projektowania-obiektowego.md) |
| 14 | Wyjątki i obsługa błędów | `try`, `catch`, `finally`, `throw`, `throws`, checked vs unchecked, własne wyjątki i dobre praktyki. | [14-wyjatki.md](lectures-OOP-in-Java/14-wyjatki.md) |
| 15 | Referencje do obiektów i niemutowalność | Referencje, współdzielenie obiektów, kopiowanie, obiekty immutable, stos i sterta, czas życia obiektów. | [15-referencje-do-obiektow.md](lectures-OOP-in-Java/15-referencje-do-obiektow.md) |

## Proponowana kolejność realizacji

1. Moduły 1-4: fundament języka, organizacji kodu i modelu obiektowego.
2. Moduły 5-7: enkapsulacja, stan obiektu i poprawna inicjalizacja.
3. Moduły 8-9: dziedziczenie, polimorfizm, interfejsy i abstrakcje.
4. Moduły 10-11: praktyczne narzędzia nowoczesnej Javy - biblioteka standardowa i lambdy.
5. Moduł 12: testowanie po zbudowaniu pierwszych większych modeli obiektowych.
6. Moduł 13: zasady projektowe SOLID jako etap dojrzalszego projektowania.
7. Moduły 14-15: odporność kodu na błędy oraz świadoma praca z referencjami.

## Uwagi organizacyjne

- Folder `lectures-OOP-in-Java` zawiera obecnie dokładnie 15 plików Markdown.
- Trzy wcześniejsze pliki zaczynające się od `10-SOLID` zostały scalone do jednego modułu.
- Numeracja plików jest teraz ciągła i odpowiada kolejności realizacji kursu.
- Materiały można prowadzić jako kurs 15-tygodniowy albo jako blok wykład + laboratorium.
