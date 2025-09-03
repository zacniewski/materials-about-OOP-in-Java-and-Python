# Klasy i metody

W tym rozdziale opisujemy klasy, ich elementy składowe oraz metody klas i obiektów w językach Java i Python. Na końcu znajdują się odnośniki do przykładowych programów z komentarzami/dokumentacją.

## 1. Co to jest klasa?
Klasa to szablon (przepis) opisujący:
- stan obiektów (pola/atrybuty),
- zachowanie (metody),
- reguły tworzenia obiektów (konstruktory/`__init__`).

Obiekt to instancja klasy – konkretny byt w czasie wykonania programu, posiadający własny, odrębny stan.

## 2. Elementy składowe klasy
- Pola/atrybuty:
  - Java: zmienne instancyjne i statyczne (klasowe). Modyfikatory dostępu: `private`, `package-private` (brak), `protected`, `public`.
  - Python: atrybuty instancji (tworzone zwykle w `__init__`) i atrybuty klasowe (w ciele klasy). Konwencje: `_name` (wewnętrzne), `__name` (name mangling).
- Właściwości (Python): `@property`, `@x.setter` pozwalają kontrolować dostęp jak do pola, zachowując enkapsulację.
- Stałe:
  - Java: `static final` (np. `static final double PI = 3.1415;`).
  - Python: konwencja UPPER_CASE (np. `PI = 3.1415`).
- Inicjalizatory:
  - Java: blok inicjalizacyjny instancji `{ ... }` i statyczny `static { ... }` (rzadziej używane).
  - Python: logika inicjalizacji zwykle w `__init__` albo w metodach klasowych.
- Konstruktory / inicjalizator instancji:
  - Java: metody o nazwie klasy, bez typu zwracanego; mogą być przeciążane.
  - Python: metoda `__init__(self, ...)` wywoływana po utworzeniu obiektu.

## 3. Metody
- Metody instancyjne: działają na konkretnej instancji.
  - Java: zwykłe metody, dostęp do pól przez `this`.
  - Python: pierwszy parametr to `self`.
- Metody klasowe (klasowe/statyczne):
  - Java: `static` – należą do klasy; brak dostępu do `this`.
  - Python: 
    - `@classmethod` – pierwszy parametr `cls` (referencja do klasy),
    - `@staticmethod` – bez `self/cls`.
- Przeciążanie i przesłanianie:
  - Java: przeciążanie metod (różne listy parametrów) i przesłanianie w klasach pochodnych.
  - Python: brak formalnego przeciążania sygnatur; można użyć wartości domyślnych lub `*args/**kwargs`. Przesłanianie działa przez dziedziczenie.
- Modyfikatory dostępu (Java): kontrolują widoczność metod/pól.
- Dokumentacja:
  - Java: Javadoc – komentarze `/** ... */` nad klasami i metodami.
  - Python: docstring – potrójne cudzysłowy `""" ... """` w definicji modułu, klasy i metod.

## 4. Enkapsulacja i spójność
- Ukrywaj stan (`private`, właściwości) i ujawniaj stabilne API.
- Zapewniaj niezmienniki (walidacja w setterach/konstruktorach).
- Minimalizuj sprzężenia – przyjmuj interfejsy, zwracaj typy ogólne, nie ujawniaj pól bezpośrednio.

## 5. Przykłady kodu
- Java: zobacz folder `Java/03` – zawiera klasy z pełnymi komentarzami Javadoc oraz `Main.java` z użyciem metod instancyjnych i statycznych.
- Python: zobacz folder `Python/03` – zawiera klasę z docstringami, właściwościami i metodami `@classmethod` oraz `@staticmethod` oraz skrypt demonstracyjny.

Powiązane zagadnienia: enkapsulacja (modyfikatory dostępu, właściwości), przeciążanie/przesłanianie, klasy abstrakcyjne i interfejsy.
