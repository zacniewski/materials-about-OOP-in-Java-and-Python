# Lab 2 — Modułowość. Pakiety. Mechanizm importowania klas (Java)

Poniżej znajdziesz 5 zadań dotyczących organizacji kodu w pakietach oraz używania importów w języku Java.  
Do każdego zadania przygotowny został osobny plik z rozwiązaniem (klasa z metodą `main`), a klasy pomocnicze umieszczono w sensownie nazwanych pakietach.  
Wzorcowe rozwiązania znajdują się w plikach w tym samym folderze.

Wskazówki ogólne:
- Używaj deklaracji pakietów, np. `package laboratoria.lab2.math;` wewnątrz plików źródłowych z klasami pomocniczymi.
- Gdy korzystasz z klasy z innego pakietu, dodaj na górze pliku `import ...;` lub `import static ...;` dla metod/pól statycznych.
- Unikaj cyklicznych zależności między pakietami.

---

## Zadanie 1. Prosty pakiet z funkcjami matematycznymi i zwykły import
Utwórz pakiet `laboratoria.lab2.math` z klasą `BasicMath`, zawierającą publiczne metody statyczne: `add`, `sub`, `mul`, `div` (z kontrolą dzielenia przez zero). Następnie utwórz klasę uruchomieniową (z `main`) w osobnym pliku (domyślny pakiet lub inny pakiet aplikacyjny), która:
- zaimportuje `laboratoria.lab2.math.BasicMath` zwykłym importem (`import laboratoria.lab2.math.BasicMath;`),
- wywoła kilka metod i wypisze wyniki na standardowe wyjście.

## Zadanie 2. Static import metod
Korzystając z klasy `BasicMath` z Zadania 1, utwórz program, który użyje mechanizmu static import: `import static laboratoria.lab2.math.BasicMath.*;`. Dodatkowo przygotuj pakiet `laboratoria.lab2.geometry` z klasą `Point` (z polami `x`, `y`) i klasą `DistanceUtils` z metodą `public static double distance(Point a, Point b)`. W programie:
- zaimportuj statycznie metody `add`, `sub`, `mul`, `div` oraz `distance`,
- oblicz odległość między dwoma punktami oraz kilka działań na liczbach, wypisz wyniki.

## Zadanie 3. Konflikt nazw klas i w pełni kwalifikowane nazwy
Przygotuj dwa pakiety: `laboratoria.lab2.a.utils` oraz `laboratoria.lab2.b.utils`, w każdym umieść klasę `Utils` z metodą statyczną `name()` zwracającą różny tekst. Napisz program, który:
- pokaże, że zwykły import obu `Utils` prowadzi do konfliktu,
- rozwiąże problem przez użycie w pełni kwalifikowanych nazw przy wywołaniu metod (bez aliasów),
- wypisze wynik obu wywołań na ekranie.

## Zadanie 4. Import wieloznaczny (z gwiazdką)
Utwórz program, który użyje `import laboratoria.lab2.geometry.*;` i skorzysta zarówno z `Point`, jak i z `DistanceUtils`. Program powinien:
- utworzyć kilka punktów,
- policzyć odległości i wypisać wyniki,
- krótko skomentować w kodzie, jakie są plusy i minusy używania importu z gwiazdką.

## Zadanie 5. Praca z podpakietami, porządkowanie kodu
Dodaj pakiet `laboratoria.lab2.text` z klasą `StringJoiner` (metoda `public static String join(String... parts)` łącząca fragmenty tekstu przecinkami). Napisz program, który:
- zaimportuje klasy/metody z różnych podpakietów (`math`, `geometry`, `text`),
- połączy wyniki obliczeń w jeden komunikat (np. „sum=..., dist=..., items=...”),
- zaprezentuje czytelny podział odpowiedzialności między pakietami.

---

Uwaga: Pliki z rozwiązaniami (po jednym na zadanie) zostały dołączone w tym folderze. Możesz je uruchamiać niezależnie jako małe programy demonstracyjne.