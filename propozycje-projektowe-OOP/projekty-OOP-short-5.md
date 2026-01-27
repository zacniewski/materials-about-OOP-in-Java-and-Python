# Cztery krótkie i proste propozycje projektów OOP (zakres: 01–12)

Poniżej znajdziesz cztery nowe, skondensowane propozycje projektów. Są one krótsze niż poprzednie, skupiają się na fundamentach i wprowadzają obsługę wyjątków oraz operacje na referencjach.

Legenda rozdziałów (odniesienia w nawiasach kwadratowych):
- [01] Paradygmaty obiektowości
- [02] Środowisko programisty OOP
- [03] Klasy i metoda `main`
- [04] Pakiety i importy
- [05] Modyfikatory dostępu i Pola [05b]
- [06] Konstruktory
- [07] Dziedziczenie i polimorfizm
- [08] Interfejsy i klasy abstrakcyjne
- [09] Testowanie kodu (podstawy)
- [10] SOLID (podstawowe zasady)
- [11] Wyjątki (obsługa i rzucanie)
- [12] Referencje do obiektów (płytkie/głębokie kopie, porównywanie)

---

## Pomysł 1: Mini-System Zarządzania Zadaniami (Todo)

Cel: prosta aplikacja do śledzenia zadań osobistych i zawodowych.

### Lista kontrolna:
1. **Inicjalizacja i pakiety [02][04]**: Struktura `todo.model`, `todo.service`, `todo.app`.
2. **Model zadania [03][05][05b]**: Klasa abstrakcyjna `Task` (id, description, isDone). Prywatne pola.
3. **Specjalizacja zadań [07][06]**: `WorkTask` (deadline) oraz `PersonalTask` (priority). Walidacja w konstruktorach (np. opis nie może być pusty).
4. **Interfejsy [08]**: `Storable` (metoda `save()`) lub `Completable`.
5. **Obsługa błędów [11]**: Rzucanie `TaskValidationException`, gdy opis jest za krótki lub deadline jest w przeszłości.
6. **Zarządzanie referencjami [12]**: Metoda kopiująca zadanie (płytka kopia) oraz porównywanie zadań za pomocą `equals()` i `hashCode()`.
7. **Demo w Main [03]**: Tworzenie listy zadań, oznaczanie jako wykonane, obsługa wyjątków w bloku `try-catch`.

---

## Pomysł 2: Kalkulator Wydatków Domowych

Cel: Rejestrowanie wydatków z podziałem na kategorie i prostym raportowaniem.

### Lista kontrolna:
1. **Struktura [04]**: Pakiety `finance.core`, `finance.report`.
2. **Kategorie wydatków [07][08]**: Klasa abstrakcyjna `Expense` i implementacje `FoodExpense`, `EntertainmentExpense`.
3. **Konstruktory i walidacja [06][11]**: Nie pozwalaj na ujemne kwoty – rzuć `IllegalArgumentException` lub własny `InvalidAmountException`.
4. **SOLID (Single Responsibility) [10]**: Oddzielna klasa `ExpenseManager` do przechowywania listy i osobna `ReportGenerator` do wyświetlania statystyk.
5. **Polimorfizm [07]**: Metoda `calculateTax()` nadpisana w różnych typach wydatków.
6. **Referencje [12]**: Przekazywanie listy wydatków do generatora raportów (zastanów się nad niemodyfikowalnością listy).
7. **Testy [09]**: Napisz jeden prosty test sprawdzający, czy suma wydatków liczy się poprawnie.

---

## Pomysł 3: Monitor Stacji Pogodowej

Cel: Gromadzenie odczytów z różnych typów czujników temperatury i wilgotności.

### Lista kontrolna:
1. **Model czujnika [08][05]**: Interfejs `Sensor` z metodą `double readValue()`.
2. **Implementacje [07]**: `TemperatureSensor`, `HumiditySensor`. Każdy ma unikalne ID i lokalizację.
3. **Wyjątki [11]**: Jeśli odczyt jest poza realistycznym zakresem (np. -100°C), rzuć `SensorMalfunctionException`.
4. **Agregacja [12]**: Klasa `WeatherStation` przechowuje listę referencji do obiektów typu `Sensor`.
5. **SOLID (Interface Segregation) [10]**: Jeśli czujnik jest serwisowalny, dodaj osobny interfejs `Maintainable`.
6. **Klonowanie/Referencje [12]**: Zaimplementuj metodę tworzącą kopię zapasową stacji wraz z jej czujnikami (głęboka kopia).
7. **Uruchomienie [03]**: Symulacja pętli odczytów w `Main`.

---

## Pomysł 4: Katalog Roślin i Stanowisk

Cel: Zarządzanie roślinami w ogrodzie i przypisanie ich do konkretnych stanowisk (gleba, nasłonecznienie).

### Lista kontrolna:
1. **Relacje obiektów [12]**: Klasa `Plant` posiada referencję do obiektu `Location` (stanowisko).
2. **Enkapsulacja [05]**: Ukrycie pól klasy `Plant`. Użycie `this` w konstruktorach. [06]
3. **Hierarchia [07]**: `Tree` i `Flower` dziedziczą po `Plant`. Różne metody pielęgnacji (`care()`).
4. **Kontrakty [08]**: Interfejs `Waterable` z metodą `water()`.
5. **Wyjątki [11]**: Próba podlania rośliny, która już uschła, rzuca `PlantDeadException`.
6. **Porównywanie [12]**: Sprawdzanie czy dwie rośliny są tego samego gatunku (`equals`).
7. **Organizacja [04]**: Rozdzielenie modeli roślin od logiki ogrodu (pakiety `garden.model`, `garden.logic`).

Co ćwiczysz w tych projektach: 
- Wszystkie podstawy (01-08).
- Bezpieczeństwo kodu poprzez wyjątki (11).
- Świadome operowanie pamięcią i obiektami poprzez referencje (12).
- Czystość kodu dzięki prostym zasadom SOLID (10).
