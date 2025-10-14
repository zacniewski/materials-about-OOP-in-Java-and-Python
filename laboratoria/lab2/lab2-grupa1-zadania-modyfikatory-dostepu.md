# Zadania: modyfikatory dostępu bez klas domenowych (proste wersje)

Wersja uproszczona: bez klas domenowych, bez adnotacji `@Override`, bez rzucania wyjątków – zakładamy naiwnie, że dane wejściowe są poprawne.  
Używamy publicznych metod statycznych (można dodać proste gettery/settery tam, gdzie ma to sens) wewnątrz pojedynczej klasy z metodą `main`, tak aby każdy plik `.java` dało się skompilować i uruchomić niezależnie.


## Zadanie 1. Prosty opis osoby (funkcje statyczne)

- Napisz klasę `Zad1_EncapsulatedPerson_Nazwisko` z metodą publiczną statyczną: `opisOsoby(String name, int age)` — zwraca napis w formacie: "Osoba: <imię>, wiek: <wiek>".
- Nie dodawaj walidacji ani wyjątków – przyjmij, że dane są poprawne.
- W `main` pokaż kilka wywołań z poprawnymi danymi.
- Brak klas domenowych i brak `@Override`.

Oczekiwane korzyści: pokazanie różnicy między `public` a `private` na prostych funkcjach (opcjonalnie można dodać prywatne pomocnicze metody bez wyjątków).

---

## Zadanie 2. Symulacja konta (wyłącznie metody statyczne)

- Napisz klasę `Zad2_BankAccount_Nazwisko`,  
- Przyjmij, że „stan konta” to pola statyczne (np. `owner`, `balance`).
- Zapewnij publiczne metody: `reset(String newOwner, double initialBalance)`, `deposit(double amount)`, `withdraw(double amount)`, `getBalance()` (opcjonalnie `getOwner()`).
- Nie wprowadzaj walidacji ani wyjątków – zakładamy poprawne dane i operacje.
- W `main` wykonaj serię operacji (wpłata, wypłata) i wypisz efekty.
- Brak klas domenowych i brak `@Override`.

Oczekiwane korzyści: demonstracja enkapsulacji przez kontrolę dostępu do stanu nawet przy braku walidacji.

---

## Zadanie 3. Prostokąt: proste gettery/settery + pole i obwód

- Napisz klasę `Zad3_Rectangle_Nazwisko`,  
- Dodaj statyczne pola przechowujące stan: `width` oraz `height`.
- Zapewnij publiczne metody dostępowe: settery `setWidth(double)`, `setHeight(double)` oraz gettery `getWidth()`, `getHeight()`.
- Udostępnij dwie formy obliczeń:
  - funkcje na parametrach: `area(double width, double height)`, `perimeter(double width, double height)`,
  - funkcje korzystające z aktualnego stanu: `area()`, `perimeter()` (działają na `width` i `height` ustawionych setterami).
- Nie dodawaj walidacji ani wyjątków — zakładamy poprawne dane.
- W `main` pokaż oba warianty użycia: obliczenia na parametrach oraz po ustawieniu szerokości i wysokości setterami.
- Brak klas domenowych i brak `@Override`.

Oczekiwane korzyści: demonstracja enkapsulacji przez kontrolowany dostęp do prostego stanu oraz użycie modyfikatorów dostępu w praktyce, nadal bez rozbudowanych klas domenowych.

---

## Zadanie 4. Operacje na punkcie bez klasy domenowej

- Napisz klasę `Zad4_ImmutablePoint_Nazwisko`,  
- Reprezentuj punkt jako parę liczb `(x, y)` przekazywaną parametrami i zwracaną jako tablica `double[2]`.
- Zaimplementuj:
  - `movedBy(double x, double y, double dx, double dy)` — zwraca nowy punkt przesunięty o `(dx, dy)`;
  - `formatPoint(double x, double y)` — zwraca napis w stylu "(x, y)".
- W `main` zaprezentuj, że oryginalne `(x, y)` nie zmieniają się po utworzeniu nowej pary.
- Brak klas domenowych i brak `@Override`. 

Oczekiwane korzyści: pokazanie idei „niemutowalności” (niezmienności) poprzez tworzenie nowych wartości zamiast modyfikowania istniejących.
