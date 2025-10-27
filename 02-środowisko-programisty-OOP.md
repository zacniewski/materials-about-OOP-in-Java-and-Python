# 02 — Środowisko programisty OOP oraz podstawy składni (Java i Python)

Ten wykład wprowadza podstawowe elementy środowiska programisty obiektowego oraz pokazuje najważniejsze elementy składni języków Java i Python w kontekście programowania obiektowego.

## 1. Ekosystem Javy: JDK, JRE, JVM

- JVM (Java Virtual Machine)
  - Maszyna wirtualna, która uruchamia bajtkod Javy (pliki `.class`, archiwa `.jar`).
  - Zapewnia niezależność od systemu operacyjnego („write once, run anywhere”).
- JRE (Java Runtime Environment)
  - Środowisko uruchomieniowe: JVM + biblioteki standardowe (bez kompilatora). Dziś rzadziej instalowane oddzielnie; JDK zazwyczaj wystarcza do wszystkiego.
- JDK (Java Development Kit)
  - Zestaw narzędzi programistycznych: kompilator `javac`, pakowacz `jar`, dokumentacja `javadoc`, narzędzia diagnostyczne (`jcmd`, `jmap`, `jstack`), itd.
  - Do pracy programistycznej instalujemy JDK (np. OpenJDK/Temurin/Zulu). 

Sprawdzenie instalacji:
- `java -version` — wersja JVM/JRE (uruchamianie).
- `javac -version` — wersja kompilatora.

Kompilacja i uruchamianie (koncept):
- Kod źródłowy `.java` → `javac` → bajtkod `.class` → uruchomienie: `java NazwaKlasy` (lub `java -cp …` ustawia classpath).
- Archiwizacja do `jar`: `jar cf app.jar -C out/production .` i uruchomienie `java -jar app.jar` (gdy `MANIFEST.MF` zawiera `Main-Class`).

Classpath:
- Ścieżka, po której JVM szuka klas/bibliotek (`-cp` lub zmienna środowiskowa `CLASSPATH`).
- W większych projektach zarządzany automatycznie przez narzędzia budowania (Maven/Gradle) lub IDE.

Rozszerzenie (praktyka i pogłębienie):
- Dystrybucje JDK: Temurin (Adoptium), Oracle, Zulu, Corretto — do nauki zwykle polecany Temurin lub Zulu.
- Wersje LTS: 17 i 21 (stabilne i długo wspierane). Upewnij się, że IDE oraz projekt używają tej samej wersji.
- `jshell`: REPL dla Javy (szybkie testy fragmentów kodu). Uruchom: `jshell` → wpisuj wyrażenia/metody.
- JPMS (Java Platform Module System): moduły (`module-info.java`) porządkują zależności i eksporty pakietów.
- JVM w skrócie: classloadery (bootstrap/system/application), JIT (C1, C2), kod natywny, hotspoty, inlining.
- Garbage Collector: G1 (domyślny), ZGC, Shenandoah — różne profile opóźnień i przepustowości.
- Narzędzia: `jlink` (tworzy zredukowaną runtime image), `jpackage` (instalatory), `jcmd/jmap/jstack` (diagnostyka), `jfr` (Java Flight Recorder profilowanie).
- Kompilacja ręczna (prosty przykład):
  - `javac -d out src/com/example/Main.java`
  - Uruchamianie: `java -cp out com.example.Main`
- Tworzenie JAR (ręcznie):
  - Plik `MANIFEST.MF` z `Main-Class: com.example.Main`
  - `jar cfm app.jar MANIFEST.MF -C out .` → `java -jar app.jar`

## 2. Ekosystem Pythona: interpreter, standard library, pip, virtualenv

- Python Interpreter
  - Program wykonujący kod źródłowy `.py`. Najpopularniejsza implementacja: CPython.
  - Uruchamianie: `python`, `python3` lub bezpośrednie `python plik.py`.
- Standard Library (stdlib)
  - Bogaty zestaw modułów („baterie w zestawie”): `math`, `datetime`, `pathlib`, `json`, itd.
- Menedżery pakietów i środowiska:
  - `pip` — instalacja bibliotek z PyPI: `pip install numpy`.
  - `venv` — wirtualne środowiska izolujące zależności projektu: `python -m venv .venv`, aktywacja i praca lokalnie.
  - (opcjonalnie) `pyenv` — zarządza wieloma wersjami Pythona w systemie.

REPL/tryb interaktywny:
- Uruchom `python` bez argumentów, aby szybko testować fragmenty kodu.

Rozszerzenie (praktyka i pogłębienie):
- CPython vs PyPy: CPython jest referencyjną implementacją Pythona; PyPy ma JIT i bywa szybszy w długotrwałych pętlach.
- Dystrybucja pakietów: sdist (`.tar.gz`) i wheel (`.whl` — prekompilowane binaria); preferuj wheels na docelowej platformie.
- `venv` (tworzenie/aktywacja):
  - Linux/macOS: `python3 -m venv .venv` → `source .venv/bin/activate`
  - Windows (PowerShell): `py -3 -m venv .venv` → `.venv\\Scripts\\Activate.ps1`
- Reprodukowalność: zamrażaj zależności poleceniem `pip freeze > requirements.txt` i instaluj `pip install -r requirements.txt`.
- `pyproject.toml`: nowoczesna deklaracja metadanych i budowania pakietów (PEP 517/518); narzędzia: `hatch`, `poetry`, `pdm`.
- Zarządzanie wersjami Pythona: `pyenv` (lokalne/globalne wersje), alternatywy: `asdf`, `conda` (z własnymi środowiskami).
- Izolacja narzędzi deweloperskich: `pipx` (instaluje CLI w odseparowanych środowiskach), `uv` (szybkie instalacje/uruchomienia).
- Przydatne REPL-e: `ipython` (historia, autouzupełnianie), `bpython`.

## 3. IDE i narzędzia wspierające

- IntelliJ IDEA (Java), Eclipse, NetBeans; dla Pythona: PyCharm, VS Code (+ rozszerzenia Python). 
- Formatowanie i jakość kodu:
  - Java: `google-java-format`, `Checkstyle`, `SpotBugs`.
  - Python: `black`, `flake8`, `ruff`, `mypy` (statyczna analiza typów).
- Budowanie/projekty:
  - Java: Maven, Gradle (zarządzanie zależnościami, kompilacja, testy, pakowanie).
  - Python: `pip`, `setuptools`/`hatchling`, `pytest` do testów.
- Dokumentacja:
  - Java: `javadoc` generuje dokumentację z komentarzy.
  - Python: `pydoc`, `Sphinx` do dokumentów API.

Rozszerzenie (praktyka i pogłębienie):
- Konfiguracja SDK/interpreterów:
  - IntelliJ: File → Project Structure → Project SDK (np. Temurin 21); ustaw Language Level.
  - PyCharm/VS Code: wskaż interpreter `.venv` projektu; dla VS Code ustaw `python.defaultInterpreterPath`.
- Run/Debug:
  - Java: konfiguracje Run z klasą `main`; debug z breakpointami, podgląd zmiennych.
  - Python: konfiguracje z parametrami; debug w PyCharm/VS Code.
- Testy w IDE:
  - Java: JUnit 5; integracja z Gradle/Maven.
  - Python: `pytest` z wykrywaniem testów; raport pokrycia.
- Formatowanie i analiza w IDE:
  - Java: Checkstyle, SpotBugs, Sonarlint; auto-format wg `google-java-format`.
  - Python: `black` (format), `ruff`/`flake8` (lint), `mypy` (typy).
- Pre-commit hooks: automatyzują format/lint przed commitem (`pre-commit` dla Pythona, `spotless`/`git hooks` dla Javy).
- CI (zarys): GitHub Actions/CI do uruchamiania testów i linterów na PR.

## 4. Uruchamianie przykładów w tym repozytorium

- Java
  - Zobacz: `Java/01/HelloWorldZKomentarzami.java`, `Java/01/WypisywanieZmiennychNaEkran.java`.
  - Typowo w IDE: kliknij „Run” na metodzie `main` lub klasie z `public static void main(String[] args)`.
- Python
  - Zobacz: `Python/01/01-hello-world.py` i pozostałe pliki w `Python/01/`.
  - Uruchamianie: `python Python/01/01-hello-world.py`.

Rozszerzenie (praktyka i pogłębienie):
- Java z linii poleceń (w katalogu repozytorium):
  - Kompilacja: `javac Java/01/HelloWorldZKomentarzami.java`
  - Uruchomienie: `java -cp Java/01 HelloWorldZKomentarzami` (gdy plik nie ma deklaracji `package`).
  - Uwaga na `CLASSPATH` i katalog roboczy; używaj `-cp` jawnie.
- Python z `venv`:
  - Utwórz środowisko: `python -m venv .venv` i aktywuj (patrz sekcja 2).
  - Zainstaluj zależności (jeśli są): `pip install -r requirements.txt`.
  - Uruchom przykłady: `python Python/01/01-hello-world.py`.
- Typowe problemy i wskazówki:
  - Kodowanie: preferuj UTF-8 (w Javie dodaj `-Dfile.encoding=UTF-8` jeśli systemowe inne; w edytorze ustaw UTF-8).
  - Różnice CRLF/LF: ustaw auto-konwersję w Git (`.gitattributes`) lub edytorze.
  - PATH: upewnij się, że `java/javac` oraz `python` wskazują oczekiwane wersje (`where java`/`which java`).

## 5. Podstawy składni — Java (skrót)

Komentarze:
- Jednolinijkowe: `// …`
- Wielolinijkowe: `/* … */`
- Dokumentacyjne: `/** … */` (Javadoc)

Struktura klasy i metoda `main`:
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Zmienne i typy proste:
- Typy prymitywne: `int`, `long`, `double`, `float`, `boolean`, `char`, `byte`, `short`.
- Typ referencyjny: `String` i wszystkie obiekty klas.
- Przykład:
```java
int wiek = 20;
double pi = 3.1415;
boolean student = true;
String imie = "Ala";
```

Operatory:
- Arytmetyczne: `+ - * / %`
- Porównania: `== != < > <= >=`
- Logiczne: `&& || !`

Instrukcje warunkowe i pętle:
```java
if (wiek >= 18) {
    System.out.println("Pełnoletni");
} else {
    System.out.println("Niepełnoletni");
}

for (int i = 0; i < 5; i++) {
    System.out.println(i);
}

while (student) {
    // …
    break;
}

switch (day) {
    case 1 -> System.out.println("Poniedziałek");
    case 2 -> System.out.println("Wtorek");
    default -> System.out.println("Inny dzień");
}
```

Funkcje/metody i przeciążanie:
```java
public static int dodaj(int a, int b) { return a + b; }
public static double dodaj(double a, double b) { return a + b; }
```

Klasy i obiekty (minimalny przykład OOP):
```java
public class Osoba {
    private String imie;     // enkapsulacja: pole prywatne
    private int wiek;

    public Osoba(String imie, int wiek) { // konstruktor
        this.imie = imie;
        this.wiek = wiek;
    }

    public String getImie() { return imie; } // metoda dostępu (getter)
    public void urodziny() { this.wiek++; }  // metoda modyfikująca stan
}
```

Pakiety i importy (zarys):
```java
package com.example.model;

import java.util.List;
```

Konwencje nazewnicze:
- Klasy: `PascalCase` (np. `BankAccount`)
- Metody i pola: `camelCase` (np. `getBalance`)
- Stałe: `UPPER_SNAKE_CASE` (np. `MAX_SIZE`)

Rozszerzenie (praktyka i pogłębienie):
- Tablice i kolekcje:
  - Tablice prymitywów rzadko w codziennej pracy; częściej kolekcje z `java.util`.
  - `List`, `Set`, `Map` i implementacje: `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`, `TreeMap`.
  - Przykład:
    ```java
    List<String> imiona = new ArrayList<>();
    imiona.add("Ala");
    for (String s : imiona) { System.out.println(s); }
    ```
- Generyki (uogólnienia):
  - Bezpieczeństwo typów w kolekcjach i API: `List<Integer>`, `Map<String, Person>`.
  - Metoda generyczna:
    ```java
    public static <T> T first(List<T> xs) { return xs.get(0); }
    ```
- Wyjątki i obsługa błędów:
  - `try { ... } catch (IOException e) { ... } finally { ... }`
  - `try-with-resources` dla auto-zamykania zasobów (`AutoCloseable`):
    ```java
    try (var reader = Files.newBufferedReader(Path.of("plik.txt"))) {
        System.out.println(reader.readLine());
    } catch (IOException e) {
        e.printStackTrace();
    }
    ```
- `enum`, `record`, `var`:
  - `enum` dla skończonych zbiorów:
    ```java
    enum Dzien { PON, WTO, SRO }
    ```
  - `record` (niezmiennicze nośniki danych):
    ```java
    public record Point(int x, int y) {}
    ```
  - `var` (lokalna inferencja typów, Java 10+): `var count = 0;` (typ nadal statyczny i ustalony przez kompilator).
- `static`, `this`, `super` i metody instancyjne:
  - `static` należy do klasy (np. `Math.max`); metody instancyjne operują na stanie obiektu (`this`). `super` odwołuje się do klasy bazowej.
- Nadpisywanie i kontrakty obiektów:
  - Zazwyczaj nadpisujemy `toString`, `equals`, `hashCode` w modelach domenowych (np. z użyciem `Objects.equals/hash`).
- Lambdy i strumienie (Java 8+):
  - Przykład:
    ```java
    var sum = List.of(1,2,3,4).stream()
        .filter(x -> x % 2 == 0)
        .mapToInt(Integer::intValue)
        .sum();
    ```

## 6. Podstawy składni — Python (skrót)

Komentarze i docstringi:
- Jednolinijkowe: `# …`
- Docstring (trzykrotne cudzysłowy w funkcjach/klasach/modułach) do dokumentacji

Hello World:
```python
print("Hello, World!")
```

Zmienne i typy (dynamiczne):
```python
wiek = 20
pi = 3.1415
student = True
imie = "Ala"
```

F-stringi i operatory:
```python
print(f"{imie} ma {wiek} lat")
# operatory: + - * / // % **; porównania: == != < > <= >=; logiczne: and or not
```

Instrukcje warunkowe i pętle:
```python
if wiek >= 18:
    print("Pełnoletni")
else:
    print("Niepełnoletni")

for i in range(5):
    print(i)

while student:
    # …
    break
```

Funkcje:
```python
def dodaj(a: int, b: int) -> int:
    """Zwraca sumę a i b."""
    return a + b
```

Klasy i obiekty (minimalny przykład OOP):
```python
class Osoba:
    def __init__(self, imie: str, wiek: int) -> None:
        self._imie = imie   # konwencja: podkreślenie sugeruje "prywatność"
        self._wiek = wiek

    @property
    def imie(self) -> str:
        return self._imie

    def urodziny(self) -> None:
        self._wiek += 1
```

Moduły i importy (zarys):
```python
from datetime import date
import math as m
```

Konwencje PEP 8:
- Nazwy modułów i funkcji: `snake_case`, klasy: `PascalCase`, stałe: `UPPER_SNAKE_CASE`.
- Wcięcia: 4 spacje; znacząca biała spacja (blok kodu = wcięcie).

Rozszerzenie (praktyka i pogłębienie):
- Podstawowe struktury danych:
  - Listy: `xs = [1, 2, 3]`; metody: `append`, `extend`, `pop`.
  - Krotki (niemutowalne): `pt = (10, 20)` — dobre jako klucze lub rekordy bez zmian.
  - Słowniki: `prices = {"apple": 3.5}`; metody: `get`, `items`, `update`.
  - Zbiory: `ids = {1, 2, 3}`; operacje: `| & -` (suma, iloczyn, różnica).
- Comprehensions i generatory:
  - Listy: `[x*x for x in range(10) if x%2==0]`
  - Słowniki: `{w: len(w) for w in ["ala", "kot"]}`
  - Generatory (leniwe): `(x*x for x in range(10))`
- Wyjątki i obsługa błędów:
  ```python
  try:
      1/0
  except ZeroDivisionError as e:
      print(e)
  else:
      print("bez wyjątku")
  finally:
      print("zawsze")
  ```
- Menedżery kontekstu (`with`) i pliki:
  ```python
  from pathlib import Path
  p = Path("plik.txt")
  with p.open("w", encoding="utf-8") as f:
      f.write("hello")
  ```
- Typowanie i `dataclasses`:
  ```python
  from dataclasses import dataclass
  from typing import List, Dict, Optional

  @dataclass
  class Person:
      name: str
      age: int
      tags: List[str] = None
  ```
- Moduły i pakiety, uruchamianie jako skrypt vs import:
  ```python
  def main() -> None:
      print("start")

  if __name__ == "__main__":
      main()
  ```

## 7. Java vs Python — szybkie porównanie pod OOP

- Typowanie: Java — statyczne i silne (kompilacja), Python — dynamiczne i silne (adnotacje typów opcjonalne).
- Kompilacja vs interpretacja: Java kompiluje do bajtkodu (JVM), Python uruchamia kod źródłowy (kompiluje do `.pyc` w locie dla wydajności).
- Struktura: W Javie wszystko w klasach; w Pythonie kod modułowy, klasy używane w razie potrzeby.
- Biblioteki: Oba mają bogate standardowe biblioteki; Java — ekosystem Maven Central; Python — PyPI.

Rozszerzenie (praktyka i pogłębienie):
- Wydajność i uruchamianie:
  - Java: JIT kompiluje hotspoty do kodu natywnego, długotrwałe procesy często bardzo szybkie.
  - Python: CPython ma GIL ograniczający wielowątkowy CPU-bound; optymalizacja przez wektoryzację (NumPy), multiprocessing lub alternatywne runtime'y (PyPy).
- Współbieżność:
  - Java: bogaty model wątków, `CompletableFuture`, `ExecutorService`, strumienie równoległe; współczesne `virtual threads` (Project Loom, Java 21+) upraszczają IO-bound.
  - Python: `asyncio` dla IO-bound, `threading` do zadań IO, `multiprocessing` dla CPU-bound.
- Zarządzanie pamięcią:
  - Java: GC (G1/ZGC/Shenandoah), możliwość tuningu; brak ręcznego zwalniania.
  - Python: licznik referencji + cykliczny GC; ważne unikanie referencji zwrotnych lub użycie `weakref`.
- Dystrybucja i wdrażanie:
  - Java: JAR/JLink/JPackage, konteneryzacja (JRE image ~40–80 MB po jlink), JVM wszędzie gdzie jest JRE.
  - Python: skrypty, pakiety na PyPI, kontenery; binarne bundlery (`pyinstaller`, `briefcase`) lub serwery (uvicorn/gunicorn) dla aplikacji web.
- Testy i ekosystem narzędzi:
  - Java: JUnit 5, Mockito, AssertJ; buildy Maven/Gradle z pipeline'ami CI.
  - Python: pytest, hypothesis (property-based), tox/nox do matrix testów.
- Typowanie i ergonomia:
  - Java: silna weryfikacja w czasie kompilacji, IDE refaktoryzują niezawodnie.
  - Python: szybka iteracja, mypy/pyright dla adnotacji przy większych bazach kodu.
- Interoperacyjność:
  - Java: JNI/JNA do natywnego C/C++; integracje z Kotlin/Scala na JVM.
  - Python: łatwa integracja z C/C++ (`ctypes`, `cffi`, `cython`), bogata warstwa naukowa.

## 8. Dobre praktyki pracy ze środowiskiem

- Java
  - Zakładaj projekt z Gradle/Maven, używaj `src/main/java` i `src/test/java`.
  - Ustal wersję JDK (np. 17 lub 21 LTS) i konfiguruj ją w IDE.
  - Używaj `javadoc` w publicznym API; uruchamiaj testy jednostkowe (JUnit).
  - Zarządzanie wersjami JDK: `SDKMAN!` lub `asdf` (łatwe przełączanie się między 17/21).
  - Kontrola jakości w buildzie: włącz `spotless` (format), `checkstyle` (lint), `spotbugs` (analiza statyczna).
  - Bezpieczeństwo zależności: w Maven/Gradle uruchamiaj skan (`owasp-dependency-check`, `gradle-versions-plugin`).
  - Publikacja artefaktów: `jlink/jpackage` do tworzenia dystrybucji; używaj kontenerów (Docker) z minimalną bazą JRE.
- Python
  - Zawsze twórz `venv` dla projektu; zapisuj zależności w `requirements.txt` (lub użyj `pyproject.toml` z `poetry`/`hatch`).
  - Formatowanie: `black`; analiza: `ruff`/`flake8`; typy: `mypy`/`pyright`; testy: `pytest`.
  - Reprodukowalność: używaj `requirements.txt` z wersjami pinowanymi; rozważ `pip-tools` (`pip-compile`).
  - Bezpieczeństwo zależności: `pip-audit`, `safety` (sprawdzanie CVE); w CI uruchamiaj skan.
  - Dystrybucja: budowanie kółek (`python -m build`) i publikacja (`twine`); dla aplikacji — konteneryzacja.

Rozszerzenie (praktyka i checklisty):
- Higiena repozytorium:
  - `.gitignore` dopasowane do języka/IDE (`.idea/`, `.venv/`, `target/`, `build/`, `__pycache__/`).
  - `.editorconfig` (spójne wcięcia, koniec linii, kodowanie UTF-8).
  - `README.md` z instrukcjami uruchomienia, `LICENSE`, prosty `CONTRIBUTING.md`.
- Hooki pre-commit:
  - Python: `pre-commit` z hookami: `black`, `ruff`, `mypy`, `pyupgrade`.
  - Java: `spotlessApply` jako pre-commit lub hook Git; dodatkowo `checkstyle` w `pre-push`.
- CI (zarys minimalny):
  - Uruchamiaj build i testy na push/PR; generuj raporty pokrycia (JaCoCo dla Javy, `coverage.py` dla Pythona).
  - Uruchom lintery (Checkstyle/SpotBugs, ruff/black/mypy) i skan zależności.
- Małe checklisty startowe:
  - Java:
    - [ ] Ustawiona wersja JDK (17/21) i Language Level w IDE
    - [ ] Gradle/Maven skonfigurowane, testy JUnit działają
    - [ ] Format/lint włączony lokalnie i w CI
    - [ ] `README` opisuje komendy build/uruchomienia
  - Python:
    - [ ] Utworzone i aktywne `venv`
    - [ ] Zaległości zainstalowane z `requirements.txt` lub `pyproject.toml`
    - [ ] `black`/`ruff`/`mypy` skonfigurowane, testy `pytest` działają
    - [ ] `README` opisuje komendy uruchomienia i aktywację `venv`

## 9. Ćwiczenia do samodzielnego wykonania (dla chętnych)

1) Java: Utwórz klasę `BankAccount` z polami `owner` i `balance`, metodami `deposit`, `withdraw` (z walidacją). Napisz klasę `Main`, która tworzy obiekt i demonstruje działanie.
2) Python: Utwórz klasę `BankAccount` z analogiczną funkcjonalnością, dodaj `__str__` do czytelnego wydruku. Uruchom wirtualne środowisko i plik `main.py` z demonstracją.
3) Porównaj, jak inicjalizujesz obiekty, jak enkapsulujesz stan i jak drukujesz informacje w obu językach.

## 10. Źródła i dalsza lektura

- Java
  - The Java Tutorials (Oracle), Java Language Specification
  - Dokumentacja JDK 17/21, artykuły o JVM (HotSpot), Garbage Collector
- Python
  - Python.org — Tutorial, Library Reference
  - PEP 8 (Style Guide), PEP 484 (Type Hints)

W repozytorium znajdziesz dodatkowe przykłady w katalogach `Java/` i `Python/`, a w pliku `README.md` — spis tematów całego kursu. 