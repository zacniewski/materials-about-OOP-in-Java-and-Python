# Lab 3 – Zadania: modyfikatory dostępu oraz gettery/settery 

Założenia ogólne:
- Brak wyjątków; zakładamy, że dane wejściowe są poprawne.
- Nie używamy adnotacji `@Override`.
- Każde zadanie realizujemy w osobnym pliku `.java` z metodą `main`, tak aby dało się uruchomić niezależnie.

---

## Zadanie 1. UserProfile – prywatne pola i publiczne gettery/settery

Napisz klasę `Zad1_UserProfile_Nazwisko`, która:
- posiada prywatne pola: `username` (String) oraz `email` (String);
- udostępnia publiczne metody dostępowe: `getUsername()`, `setUsername(String)`, `getEmail()`, `setEmail(String)`;
- posiada metodę `describe()`, która zwraca krótki opis obiektu, np. `UserProfile{username='ala', email='ala@example.com'}`;
- w `main` utwórz obiekt, ustaw wartości pól przez settery i wypisz: wartości odczytane getterami oraz wynik `describe()`.

Wskazówki: Pokaż prostą enkapsulację: pola prywatne, a dostęp przez gettery/settery.

---

## Zadanie 2. Temperature – getter tylko-do-odczytu dla wartości pochodnej

Napisz klasę `Zad2_Temperature_Nazwisko`, która:
- posiada prywatne pole `celsius` (double) reprezentujące temperaturę w stopniach Celsjusza;
- udostępnia settery/gettery dla `celsius`: `setCelsius(double)`, `getCelsius()`;
- udostępnia tylko getter (bez settera) dla temperatury w Fahrenheitach: `getFahrenheit()`;
- konwersję do Fahrenheitów zrealizuj prywatną metodą pomocniczą (np. `toFahrenheit(double c)`);
- w `main` ustaw kilka wartości `celsius` i pokaż wynik `getCelsius()` oraz `getFahrenheit()`.

Wskazówki: To przykład „właściwości pochodnej” – `Fahrenheit` liczone jest na podstawie `celsius` i można je tylko odczytać.

---

## Zadanie 3. AccessLevels – demonstracja modyfikatorów dostępu

Napisz klasę `Zad3_AccessLevelsDemo_Nazwisko`, która zawiera pola o różnych modyfikatorach dostępu:
- `public String publicName;`
- `protected int protectedId;`
- (package-private) `String packageLevel;` – bez słowa kluczowego;
- `private String privateSecret;`

Zaimplementuj:
- publiczne gettery/settery wyłącznie dla pola prywatnego (`privateSecret`): `getPrivateSecret()`, `setPrivateSecret(String)`;
- metodę `summary()`, która zwraca napis z wartościami wszystkich pól.

W `main`:
- ustaw wartości pól public, protected oraz package-private bezpośrednio (działanie w tej samej „przestrzeni” pliku),
- pole prywatne ustaw przez setter, a odczytaj przez getter,
- wypisz wynik `summary()` i wartość odczytaną getterem.

---

## Zadanie 4. WriteOnlySettings – własność write-only + kontrolowany odczyt

Napisz klasę `Zad4_WriteOnlySettings_Nazwisko`, która:
- posiada prywatne pola: `volume` (int) oraz `darkMode` (boolean);
- udostępnia settery: `setVolume(int)`, `setDarkMode(boolean)`;
- nie udostępnia klasycznego gettera dla `volume` (write-only), ale ma metodę `describe()`, która zwraca całościowy, kontrolowany opis stanu;
- w `main` ustaw różne wartości i wypisz wynik `describe()` przed i po zmianach.

Wskazówki: Pokaż, że czasem odczyt jest „kontrolowany” przez dedykowaną metodę, a nie przez bezpośredni getter (np. ze względów projektowych).

---

## Zadanie 5. ProductStock – enkapsulacja i operacja na dwóch obiektach

Napisz klasę `Zad5_ProductStock_Nazwisko`, która:
- posiada prywatne pola: `name` (String), `price` (double), `quantity` (int);
- udostępnia gettery/settery dla tych pól oraz metodę `describe()` zwracającą ich czytelny opis;
- udostępnia statyczną metodę `transferQuantity(Zad5_ProductStock from, Zad5_ProductStock to, int amount)`, która przenosi sztuki między obiektami.

W `main`:
- utwórz dwa produkty, ustaw nazwy, ceny i ilości,
- wypisz ich stan przed transferem,
- wykonaj `transferQuantity` dla ustalonej liczby sztuk,
- wypisz stan po transferze.

Wskazówki: To ćwiczenie z enkapsulacji oraz prostej współpracy obiektów poprzez metodę statyczną operującą na ich polach.
