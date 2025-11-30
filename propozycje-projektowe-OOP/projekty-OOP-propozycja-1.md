# Cztery proste pomysły na projekt OOP w Javie (zakres: 01–08)

Poniżej znajdziesz cztery kompletne, proste pomysły na projekty edukacyjne w Javie. Każdy został tak zaplanowany, aby ćwiczyć zagadnienia z materiałów od `01-paradygmaty-obiektowości.md` do `08-interfejsy-i-klasy-abstrakcyjne.md`. Każdy pomysł zawiera listę kontrolną (checklistę) z kolejnością działań i krótkim wskazaniem, które rozdziały są ćwiczone na danym etapie.

Legenda rozdziałów (odniesienia w nawiasach kwadratowych):
- [01] Paradygmaty obiektowości
- [02] Środowisko programisty OOP
- [03] Klasy i metoda `main`
- [04] Pakiety i importy (04a/04b)
- [05] Modyfikatory dostępu
- [05b] Pola klas
- [06] Konstruktory
- [07] Dziedziczenie i polimorfizm
- [08] Interfejsy i klasy abstrakcyjne

---

## Pomysł 1: System Rezerwacji Pokoi w Hostelu

Cel: prosty system do zarządzania pokojami (pojedyncze, wieloosobowe), gośćmi i rezerwacjami. Akcent na klasy abstrakcyjne, interfejsy, polimorfizm oraz kapsułkowanie.

### Lista kontrolna
1. Inicjalizacja projektu i pakietów [02][04]
   - Utwórz projekt JDK 17+ z katalogiem `src/main/java`. [02]
   - Pakiety: `hostel.domain`, `hostel.service`, `hostel.app`, `hostel.util`. [04]
   - Dodaj klasę uruchomieniową `hostel.app.Main` z `public static void main`. [03]

2. Abstrakcja pokoju [08][05][05b]
   - Klasa abstrakcyjna `hostel.domain.Room` z polami: `id` (String), `beds` (int), `occupied` (boolean). [08][05b]
   - Metody: `boolean isOccupied()`, `void occupy()`, `void release()`, `String description()` (abstrakcyjna). [08]
   - Dostęp: pola `private/protected`, publiczne metody. [05]

3. Konkretne typy pokoi [07][06]
   - `SingleRoom` oraz `DormRoom` (wielkie sale, dodatkowe pole `lockers`), oba rozszerzają `Room`. [07]
   - Walidacje w konstruktorach (`beds > 0`, `lockers >= 0`). [06]
   - Nadpisują `description()`. [07]

4. Interfejsy – kontrakty biznesowe [08]
   - `hostel.domain.Bookable` z metodami: `book()`, `cancel()`, `boolean isAvailable()`. [08]
   - `hostel.domain.Exportable` z `String toCsv()` (nawiązanie do laboratorium o eksporcie). [08]
   - Pokoje implementują `Bookable` i `Exportable`. [08]

5. Goście i rezerwacje [03][05b]
   - `Guest` z danymi: `id`, `name`, `email` (walidacje). [06][05b]
   - `Reservation` łącząca `Guest` i `Room`, z terminami (`LocalDate` od/do). [03]
   - Kapsułkowanie: kolekcje zwracaj jako kopie/niemodyfikowalne widoki. [05]

6. Serwisy domenowe [07]
   - `ReservationService`: tworzenie/anulowanie rezerwacji, sprawdzanie dostępności (polimorficzne `isAvailable()`). [07]
   - `ReportingService`: generowanie raportu CSV z listą pokoi/rezerwacji (`toCsv()`). [08]

7. Warstwa demonstracyjna [03]
   - W `Main` zainicjuj kilka pokoi, gości, utwórz rezerwacje, wygeneruj krótkie raporty. [03]

8. Modyfikatory, pola i niezmienniki [05][05b]
   - Upewnij się, że pola są `private/protected`, dodaj stałe `public static final` (np. `MAX_DORM_BEDS`). [05b]

9. Rozszerzenia (opcjonalne) [07][08]
   - Interfejs `Priced` (cena za noc) + strategia wyceny (sezon/weekday-weekend). [08]
   - Klasa abstrakcyjna `PricedRoom` łącząca `Room` z ceną bazową. [08]

10. Kroki Git/GitHub [02]
    - Utwórz repo: `git init`, dodaj `.gitignore` dla Java/IntelliJ, `README.md` z opisem. [02]
    - Pierwszy commit, utwórz zdalne repo na GitHubie, `git remote add origin ...`, `git push -u origin main`. [02]
    - Stwórz branch `feature/booking`, pracuj i twórz PR na GitHubie, opisuj commity wg konwencji. [02]
    - Oznacz wersję demo tagiem `v0.1.0`. [02]

Co ćwiczysz: abstrakcje i interfejsy (08), dziedziczenie i polimorfizm (07), konstrukcje i walidacje (06), kapsułkowanie i modyfikatory (05), pola i stałe (05b), pakiety i importy (04), klasę `main` (03), praktykę narzędzi (02), myślenie obiektowe (01).

---

## Pomysł 2: Menedżer Biblioteki Multimediów

Cel: aplikacja do zarządzania kolekcją książek, filmów i albumów muzycznych. Kładzie nacisk na dziedziczenie, interfejsy, klasy abstrakcyjne oraz walidację konstruktorów.

### Lista kontrolna
1. Struktura projektu [02][04]
   - Pakiety: `media.domain`, `media.service`, `media.app`, `media.export`. [04]
   - `media.app.App` z metodą `main`. [03]

2. Abstrakcyjny element kolekcji [08][05b]
   - `abstract class MediaItem` z polami: `id` (String), `title` (String), `year` (int). [08][05b]
   - Metody wspólne: `age()`, `String basicInfo()`, `String details()` (abstrakcyjna). [08]

3. Klasy konkretne [07]
   - `Book` (autor, liczba stron), `Movie` (reżyser, długość w minutach), `Album` (artysta, liczba utworów) – wszystkie rozszerzają `MediaItem`. [07]
   - Nadpisują `details()` i dodają walidacje konstruktorów. [06]

4. Interfejsy funkcjonalne domeny [08]
   - `export.Exportable` z `toCsv()` i/lub `toJson()`. [08]
   - `domain.Filterable` z `boolean matches(String query)` – dla prostego wyszukiwania. [08]

5. Repozytorium i serwisy [03][05]
   - `MediaRepository` (proste in-memory, lista elementów z metodami `add`, `remove`, `findById`, `search`). [03]
   - Zadbaj o kapsułkowanie kolekcji i defensywne kopie. [05]
   - `LibraryService` wykorzystuje polimorfizm przez interfejsy (`Exportable`, `Filterable`). [07][08]

6. Warstwa demonstracyjna [03]
   - W `App.main` dodaj kilka pozycji, wyszukaj po słowie kluczowym, wyeksportuj CSV/JSON. [03]

7. Modyfikatory i stałe [05][05b]
   - Dodaj stałe np. `MIN_YEAR`, `MAX_TITLE_LEN`. [05b]
   - Używaj `private` dla pól i publicznych getterów. [05]

8. Rozszerzenia (opcjonalne) [07][08]
   - Interfejs `Rateable` (oceny użytkowników) z polimorficznym liczeniem średniej. [08]
   - Abstrakcyjna `RatedMediaItem` jako baza dla elementów z oceną. [08]

9. Kroki Git/GitHub [02]
   - README z krótkim opisem i przykładowym outputem CSV. [02]
   - Branch `feature/export-json`; PR z opisem zmian; tag `v0.1.0`. [02]

Co ćwiczysz: projekt hierarchii (07–08), konstrukcje i walidacje (06), pola i dostęp (05/05b), pakiety (04), `main` (03), workflow Git (02), myślenie obiektowe (01).

---

## Pomysł 3: Symulator ZOO

Cel: symulacja podstawowych zachowań różnych zwierząt. Świetny do pokazania dziedziczenia, polimorfizmu oraz interfejsów opisujących „zdolności”.

### Lista kontrolna
1. Start projektu [02][04]
   - Pakiety: `zoo.domain`, `zoo.service`, `zoo.app`. [04]
   - `zoo.app.Simulator` z `main`. [03]

2. Baza abstrakcyjna [08][05b]
   - `abstract class Animal` z polami: `name` (String), `age` (int). [08][05b]
   - Metody: `eat()`, `sleep()`, `String sound()` (abstrakcyjna), `String info()`. [08]

3. Hierarchia klas [07]
   - `Mammal`, `Bird`, `Reptile` rozszerzają `Animal` (mogą dodawać własne pola np. `furColor`, `wingspan`). [07]
   - Przykładowe konkretne: `Lion`, `Elephant`, `Eagle`, `Penguin`, `Iguana`. [07]

4. Interfejsy – zdolności [08]
   - `Swimmable`, `Flyable`, `Trainable` z odpowiednimi metodami (`swim()`, `fly()`, `performTrick()`). [08]
   - Wykorzystaj polimorfizm: kolekcje typu interfejsowego i wywołania zachowań na miksie obiektów. [07]

5. Enkapsulacja i niezmienniki [05][05b]
   - Pola prywatne + gettery; walidacje w konstruktorach (`age >= 0`). [06]
   - Stałe np. `MAX_AGE_MAMMAL`. [05b]

6. Mini-silnik symulacji [03]
   - `SimulationService`: harmonogramuje „tury” (jedzenie, sen, dźwięk), generuje raport z aktywności. [03]
   - `ReportingService`: CSV/JSON z aktywności zwierząt (interfejs `Exportable`). [08]

7. Demo [03]
   - W `Simulator.main` zainicjuj różne zwierzęta, przypisz zdolności, uruchom kilka tur, wydrukuj raport. [03]

8. Rozszerzenia (opcjonalne) [07][08]
   - Klasa abstrakcyjna `Habitat` (woda/ląd/powietrze) i przypisywanie zwierząt do siedlisk. [08]
   - Interfejs `Feedable` z różnymi strategiami karmienia. [08]

9. Kroki Git/GitHub [02]
   - README z rysunkiem prostego diagramu klas (PNG w repo). [02]
   - Branch `feature/simulation-cycle`, PR, opis zmian, tag `v0.1.0`. [02]

Co ćwiczysz: klasy abstrakcyjne i interfejsy (08), dziedziczenie/polimorfizm (07), konstruktory (06), kapsułkowanie (05), pola/stałe (05b), pakiety i `main` (04–03), workflow Git (02), modelowanie obiektów (01).

---

## Pomysł 4: Planer Zadań i Raportów (Task Planner)

Cel: prosty planer zadań z kategoriami i priorytetami, eksportem raportów oraz możliwością wprowadzania różnych strategii sortowania/filtracji.

### Lista kontrolna
1. Inicjalizacja i pakiety [02][04]
   - Pakiety: `planner.domain`, `planner.service`, `planner.app`, `planner.export`. [04]
   - `planner.app.Main` z `main`. [03]

2. Model domeny [08][05b]
   - `abstract class Task` z polami: `id` (String), `title` (String), `dueDate` (LocalDate), `priority` (enum). [08][05b]
   - Metody: `boolean isOverdue()`, `String summary()` (abstrakcyjna). [08]
   - Klasy konkretne: `WorkTask`, `PersonalTask` (dodatkowe pola, np. `project`, `location`). [07]

3. Interfejsy i klasy wspierające [08]
   - `export.Exportable` (`toCsv()`) i `export.Printable` (`print()`), implementowane przez `Task` lub klasy pomocnicze. [08]
   - `planner.domain.Filter` (interfejs) z metodą `boolean test(Task t)` – różne implementacje filtrów. [08]

4. Repozytorium i serwisy [03][05]
   - `TaskRepository` (lista zadań, metody CRUD, zwracanie niemodyfikowalnych widoków). [03][05]
   - `TaskService` do dodawania, filtrowania, sortowania (strategia sortowania/filtrów przez interfejsy). [07][08]

5. Konstruktory i walidacja [06]
   - Sprawdź `id` niepuste, `title` niepuste, `dueDate != null`. [06]
   - Dodaj konstruktor kopiujący dla `Task` lub wybranej podklasy. [06]

6. Warstwa demonstracyjna [03]
   - W `Main` dodaj kilka zadań, przefiltruj po priorytecie, wyeksportuj CSV, wydrukuj listę. [03]

7. Modyfikatory i pola statyczne [05][05b]
   - Prywatne pola, publiczne gettery; stałe np. `DEFAULT_SORT = BY_DUE_DATE`. [05b]

8. Rozszerzenia (opcjonalne) [07][08]
   - `RecurringTask` jako klasa abstrakcyjna z generowaniem kolejnych terminów. [08]
   - Interfejs `Notifiable` i „fałszywa” implementacja powiadomień w konsoli. [08]

9. Kroki Git/GitHub [02]
   - README z krótkim opisem i sekcją „Usage”. [02]
   - Branch `feature/filters-sorting`, PR, opis zmian, tag `v0.1.0`. [02]

Co ćwiczysz: projekt interfejsów i klas abstrakcyjnych (08), polimorfizm/dziedziczenie (07), walidacje konstruktorów (06), enkapsulację i pola (05/05b), pakiety i `main` (04–03), podstawy narzędzi (02), myślenie obiektowe (01).

---

## Wskazówki wspólne do wszystkich czterech projektów
- Na początku każdego projektu narysuj prosty diagram klas/interfejsów (np. w draw.io) i dodaj go do repo. [01]
- Używaj jasno nazwanych pakietów i minimalizuj widoczność elementów (domyślnie `private`). [04][05]
- Waliduj dane wejściowe w konstruktorach i metodach (zwracaj `IllegalArgumentException` przy złych danych). [06]
- Demonstruj polimorfizm w kodzie klienta: trzymaj referencje w typach bazowych/interfejsach. [07][08]
- Dodaj krótkie Javadoc do interfejsów i klas abstrakcyjnych (opis kontraktu i założeń). [08]