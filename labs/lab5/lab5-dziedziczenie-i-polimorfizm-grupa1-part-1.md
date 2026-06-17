# Laboratorium 5 — Dziedziczenie i polimorfizm (Java), część nr 1

Poniższe zadania odnoszą się do materiału z pliku `07-dziedziczenie-i-polimorfizm.md`. Celem jest przećwiczenie:
- podstaw dziedziczenia (`extends`),
- polimorfizmu (traktowanie obiektu klasy pochodnej jako bazowej),
- rzutowania (cast) i bezpiecznych sprawdzeń,
- nadpisywania metod (method overriding),
- użycia słowa kluczowego `super` (konstruktor i metody),
- klas i metod abstrakcyjnych oraz `final`.

Każde zadanie może składać się z kilku plików `.java` (jedna klasa w jednym pliku).  
Plik z metodą `main` zachowuje sugerowaną nazwę.  
Poniżej znajdziesz precyzyjny opis każdego zadania.

---

## Zadanie 1 — Proste dziedziczenie (Osoba → Pracownik)

Nawiązując do sekcji 2 materiału:
- Utwórz klasę `Osoba` z polami publicznymi `imie`, `nazwisko` oraz metodą `toString()` zwracającą ciąg `"Osoba <imie> <nazwisko>"`.
- Utwórz klasę `Pracownik` dziedziczącą po `Osoba` z dodatkowym polem `numerIdentyfikatora` (typ `int`).
- W metodzie `main` pokaż, że obiekt `Pracownik` ma dostęp do pól z klasy bazowej oraz że wypisanie obiektu wykorzystuje `toString()` odziedziczone z `Osoba`.

Kryteria akceptacji:
- Kompiluje się i uruchamia, wypisując dwie linie podobne do podanych w notatkach.
- `Pracownik` nie redefiniuje `toString()` w tym zadaniu.

Sugerowana nazwa pliku: `Zad1_SimpleInheritance.java`.

---

## Zadanie 2 — Polimorfizm w akcji (wejście do budynku)

Na podstawie sekcji 3:
- Utwórz metodę `wejdzDoBudynku(Osoba osoba)`, która wypisuje tekst: `"W budynku jest " + osoba`.
- W `main` utwórz obiekty `Osoba` i `Pracownik` oraz wywołaj `wejdzDoBudynku(osoba)` i `wejdzDoBudynku(pracownik)`.

Kryteria akceptacji:
- Drukowane są 2 linie z poprawnymi reprezentacjami tekstowymi obiektów.
- Widać, że `Pracownik` może być przekazany tam, gdzie oczekuje się `Osoba` (polimorfizm).

Sugerowana nazwa pliku: `Zad2_PolymorphismBuilding.java`.

---

## Zadanie 3 — Rzutowanie: bezpieczne i niebezpieczne

Na podstawie sekcji 3 (część o rzutowaniu):
- Utwórz zmienną referencyjną typu `Osoba`, przypisz do niej nowy obiekt `Pracownik` i pokaż, że bez rzutowania nie można ustawić `numerIdentyfikatora`.
- Następnie wykonaj poprawne rzutowanie do `Pracownik` i ustaw `numerIdentyfikatora`.
- Dodatkowo pokaż bezpieczne użycie `instanceof` przed rzutowaniem.
- (Opcjonalnie) Skomentuj, dlaczego rzutowanie obiektu faktycznie będącego `Osoba` na `Pracownik` skończy się `ClassCastException`.

Kryteria akceptacji:
- Program kompiluje się i uruchamia, prezentując działające rzutowanie i bezpieczne sprawdzenie `instanceof`.

Sugerowana nazwa pliku: `Zad3_CastingDemo.java`.

---

## Zadanie 4 — Nadpisywanie metody `toString()` (method overriding)

Na podstawie sekcji 4:
- W klasie `Pracownik` nadpisz metodę `toString()` tak, aby zwracała: `"Pracownik <imie> <nazwisko>, identyfikator: <numerIdentyfikatora>"`.
- Utwórz referencję typu `Osoba`, przypisz do niej obiekt `Pracownik` i wywołaj `toString()`.

Kryteria akceptacji:
- Wydruk pokazuje, że mimo typu referencji `Osoba`, wykonana została metoda z klasy `Pracownik` (dynamiczne wiązanie).

Sugerowana nazwa pliku: `Zad4_OverrideToString.java`.

---


Uwagi organizacyjne:
- Jedna klasa „zadaniowa” z `main` w pliku o sugerowanej nazwie (np. `Zad1_SimpleInheritance.java`).  
- Klasy pomocnicze rozdziel do osobnych plików (bez pakietów), aby ułatwić czytelność i ponowne użycie między przykładami.
- Sugerowane nazwy dodatkowych plików klasowych:
  - Zadanie 1: `Zad1_Osoba.java`, `Zad1_Pracownik.java`
  - Zadanie 2: `Zad2_Osoba.java`, `Zad2_Pracownik.java`
  - Zadanie 3: `Zad3_Osoba.java`, `Zad3_Pracownik.java`
  - Zadanie 4: `Zad4_Osoba.java`, `Zad4_Pracownik.java`
- Komentarze w kodzie mile widziane — wyjaśniaj kroki istotne dla zrozumienia mechanizmów.
