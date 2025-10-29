# Lab 4 – Zadania: konstruktory (grupa 1)

Źródło merytoryczne: https://kursjava.com/klasy/konstruktory/

Założenia ogólne:
- Każde zadanie realizujemy w osobnym pliku `.java` z metodą `main`, tak aby dało się uruchomić niezależnie.
- W nazwach klas i plików zastąp `Nazwisko` swoim nazwiskiem, np. `Zad1_Person_Nowak` w pliku `Zad1_Person_Nowak.java`.
- Dbaj o sensowną enkapsulację pól, walidację danych wejściowych oraz czytelne wypisywanie stanu obiektów.

---

## Zadanie 1. Domyślny i parametryczny konstruktor – klasa `Person`

Napisz klasę `Zad1_Person_Nazwisko`, która reprezentuje osobę.
- Pola prywatne: `firstName` (String), `lastName` (String), `age` (int).
- Konstruktory:
  - domyślny (bezargumentowy) ustawiający wartości: `firstName = "John"`, `lastName = "Doe"`, `age = 18`;
  - parametryczny: `Person(String firstName, String lastName, int age)`.
- Metody:
  - `describe()` zwraca czytelny opis, np. `Person{firstName='Ala', lastName='Kowalska', age=21}`.

W `main`:
- Utwórz obiekt przez konstruktor domyślny i wypisz `describe()`.
- Utwórz obiekt przez konstruktor parametryczny i wypisz `describe()`.
- Krótko skomentuj (w komentarzach w kodzie), kiedy i dlaczego użyć którego konstruktora.

Wskazówki: Pokaż różnicę między inicjalizacją „z góry” (default) i „na wejściu” (parametry).

---

## Zadanie 2. Przeciążanie konstruktorów + delegacja przez `this()` – klasa `Rectangle`

Napisz klasę `Zad2_Rectangle_Nazwisko`, która reprezentuje prostokąt.
- Pola prywatne: `width` (double), `height` (double).
- Konstruktory (przeciążone):
  - `Rectangle()` – ustawia domyślnie `width = 1.0`, `height = 1.0`;
  - `Rectangle(double size)` – kwadrat: `width = height = size`;
  - `Rectangle(double width, double height)` – pełne ustawienie.
- Zaimplementuj delegację konstruktorów z użyciem `this(...)`, tak aby nie duplikować logiki.
- Metody: `area()` i `perimeter()`, oraz `describe()` z czytelnym opisem.

W `main`:
- Utwórz trzy prostokąty trzema różnymi konstruktorami i wypisz wyniki `describe()`, `area()` i `perimeter()`.

Wskazówki: Pamiętaj o kolejności wywołań – `this(...)` musi być pierwszą instrukcją w konstruktorze.

---

## Zadanie 3. Walidacja w konstruktorze + `IllegalArgumentException` – klasa `BankAccount`

Napisz klasę `Zad3_BankAccount_Nazwisko`.
- Pola prywatne: `owner` (String), `balance` (double).
- Konstruktor parametryczny: `BankAccount(String owner, double initialBalance)`.
  - Walidacje w konstruktorze:
    - `owner` nie może być `null`/pusty – w przeciwnym razie rzuć `IllegalArgumentException` z czytelnym komunikatem;
    - `initialBalance` nie może być ujemny – w przeciwnym razie rzuć `IllegalArgumentException`.
- Metody: `deposit(double amount)`, `withdraw(double amount)` z podstawową walidacją, `describe()`.

W `main`:
- Pokaż poprawną inicjalizację i kilka operacji.
- Spróbuj utworzyć konto z niepoprawnymi danymi w bloku `try/catch` i wypisz przechwycony komunikat błędu.

Wskazówki: Konstruktor to dobre miejsce na zapewnienie niezmienników obiektu od pierwszej chwili życia.

---

## Zadanie 4. Konstruktor kopiujący vs. klon powierzchowny – klasa `Course`

Napisz klasę `Zad4_Course_Nazwisko`.
- Pola prywatne: `title` (String), `ects` (int), `tags` (String[]).
- Konstruktory:
  - parametryczny: `Course(String title, int ects, String[] tags)` – sklonuj tablicę `tags`, aby nie współdzielić referencji;
  - kopiujący: `Course(Course other)` – powinien tworzyć nową tablicę `tags` z kopiami wartości (tzw. „głęboki”/bezpieczny dla tego poziomu) i skopiować pozostałe pola.
- Metody: `addTag(String tag)` (rozszerz tablicę o nowy element), `describe()` zwracające opis wraz z listą tagów.

W `main`:
- Utwórz oryginalny kurs, potem kopię przez konstruktor kopiujący.
- Zmień `tags` w oryginale (np. `addTag("new")`) i pokaż, że stan kopii nie zmienił się.

Wskazówki: Pokaż różnicę między „kopią przez referencję” a bezpiecznym kopiowaniem danych w konstruktorze.

---

## Zadanie 5. Łańcuch inicjalizacji: pola, inicjalizatory, konstruktor, `super(...)` – hierarchia `Employee`

Stwórz dwie klasy: `Zad5_Employee_Nazwisko` (klasa bazowa) i `Zad5_Manager_Nazwisko` (podklasa).

`Zad5_Employee_Nazwisko`:
- Pole prywatne: `name` (String).
- Inicjalizator instancyjny (blok `{ ... }`) wypisujący np. `Init: Employee`.
- Konstruktor parametryczny `Employee(String name)` wypisujący np. `Ctor: Employee(name)` i ustawiający pole `name`.
- `describe()` zwraca `Employee{name='...'}`.

`Zad5_Manager_Nazwisko` dziedziczy po `Zad5_Employee_Nazwisko`:
- Dodatkowe pole prywatne: `teamSize` (int).
- Inicjalizator instancyjny wypisujący `Init: Manager`.
- Konstruktor `Manager(String name, int teamSize)` wywołujący `super(name)`, wypisujący `Ctor: Manager(name, teamSize)` i ustawiający `teamSize`.
- Nadpisane `describe()` zwracające `Manager{name='...', teamSize=...}`.

W `main`:
- Utwórz obiekt `Manager` i zaobserwuj (na podstawie wypisów), w jakiej kolejności wykonywane są: inicjalizatory i konstruktory w klasie bazowej i pochodnej.
- Wypisz wynik `describe()`.

Wskazówki: Zademonstruj kolejność: najpierw inicjalizacja pól i bloki inicjalizujące klasy bazowej, potem konstruktor bazowy (`super(...)`), następnie inicjalizacja i konstruktor klasy pochodnej.

---

Powodzenia! Pamiętaj, że to konstruktor odpowiada za poprawne „wprowadzenie” obiektu w życie – ustawienie niezmienników, domyślnych wartości i zabezpieczenie przed niespójnością danych.