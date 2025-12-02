# Sześć prostych pomysłów na projekt OOP w Javie (zakres: 01–08)

Poniżej znajdziesz sześć kompletnych, prostych pomysłów na projekty edukacyjne w Javie, które celowo dotykają zagadnień omawianych w materiałach od `01-paradygmaty-obiektowości.md` do `08-interfejsy-i-klasy-abstrakcyjne.md`. Każdy pomysł ma formę szczegółowej listy kontrolnej (checklisty) z sekwencją działań. W każdej liście uwzględniono też kroki związane z publikacją na GitHubie.

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

## Pomysł 1: Liga Uczelniana — terminarz i tabela wyników (sport)

Cel: prosta aplikacja do zarządzania ligą (zespoły, mecze, tabela), pokazująca klasy bazowe, dziedziczenie (różne dyscypliny), interfejsy (np. eksport wyników, rozgrywanie meczu) i polimorfizm przy generowaniu raportów.

### Lista kontrolna
1. Inicjalizacja projektu i pakietów [02][04]
   - Utwórz projekt JDK, katalog `src/main/java`. [02]
   - Pakiety: `league.domain`, `league.service`, `league.schedule`, `league.app`, `league.util`. [04]
   - Klasa uruchomieniowa `league.app.Main` z `public static void main(String[] args)`. [03]
2. Abstrakcja osób i ról [07][08][05]
   - Abstrakcyjna klasa `domain.Person` z polami `id`, `name`. [08][05b]
   - Klasy `Player` i `Coach` dziedziczące po `Person`. [07]
   - Konstruktory z walidacją (np. `name != null && !name.isBlank()`). [06]
3. Zespoły i dyscypliny [07][05b]
   - Klasa `domain.Team` z polami: `name`, `coach`, `players` (`List<Player>`). [05b]
   - Abstrakcyjna `domain.Sport` (np. nazwa, minimalna liczba graczy). [08]
   - Klasy konkretne: `Football`, `Basketball`. [07]
4. Interfejsy i kontrakty [08]
   - `domain.PlayableMatch` z metodami `play()` i `Score getScore()`. [08]
   - `domain.Exportable` z `String toCsv()`. [08]
5. Mecze i wyniki [03][05]
   - `domain.Score` (np. `homeGoals`, `awayGoals`). [05b]
   - `domain.Match` implementuje `PlayableMatch`, przechowuje `home`, `away`, `sport`, `date`, `score`. [08]
   - Kapsułkowanie: prywatne pola, publiczne gettery, brak setterów dla niezmienników. [05]
6. Terminarz i tabela [03][07]
   - `schedule.Scheduler` generuje pary meczów (round-robin). [03]
   - `service.StandingService` liczy punkty i sortuje tabelę (wykorzystaj polimorfizm jeśli sport ma inne zasady punktacji). [07]
7. Raportowanie [08]
   - `service.ReportingService` generuje CSV z meczów i tabeli, wołając `toCsv()` polimorficznie. [08]
8. Walidacje i konstruktory [06]
   - Sprawdzenia np. minimum graczy w `Team`, brak duplikatów graczy, niepuste nazwy. [06]
9. Scenariusz demo w `Main` [03]
   - Utwórz 4 zespoły, wygeneruj terminarz, „rozegraj” mecze (losowo), wydrukuj tabelę i CSV. [03]
10. Publikacja na GitHubie [02]
    - `git init`, dodaj `.gitignore` dla Javy, utwórz `README.md` z instrukcją uruchomienia. [02][03]
    - Małe commity: „feat: modele ligi”, „feat: scheduler”, „feat: raport CSV”. [02]
    - `git remote add origin <URL>`, `git push -u origin main`. [02]
11. Rozszerzenia (opcjonalnie) [07][08]
    - Interfejs `PointRule` jako strategia punktacji per sport. [08]
    - Klasa `Tournament` (puchar, fazy). [07]

Co ćwiczysz: hierarchie klas i interfejsy (07–08), konstrukcje i walidacje (06), kapsułkowanie (05), pakiety i `main` (04–03), praktykę Git (02), myślenie obiektowe (01).

---

## Pomysł 2: Aplikacja Trenera Personalnego — plany i dziennik (sport)

Cel: zarządzanie ćwiczeniami, planami i dziennikiem treningów, z interfejsami typu `Trackable` i `Exportable`, oraz klasą abstrakcyjną aktywności.

### Lista kontrolna
1. Struktura projektu [02][04]
   - Pakiety: `trainer.domain`, `trainer.service`, `trainer.app`. [04]
   - `trainer.app.App` z `main`. [03]
2. Abstrakcja aktywności [08]
   - `domain.AbstractActivity` (abstrakcyjna): `name`, `durationMin`, `calories()` (abstrakcyjne lub domyślne). [08]
   - Klasy konkretne: `Running`, `Cycling`, `StrengthTraining` (różne sposoby liczenia kalorii). [07]
3. Interfejsy [08]
   - `domain.Trackable` z `start()`, `stop()`, `boolean isActive()`. [08]
   - `domain.Exportable` z `String toCsv()`.
   - Niech aktywności implementują `Trackable` i `Exportable` (polimorfizm przy generowaniu dziennika). [08]
4. Plan i dziennik [05b][03]
   - `domain.WorkoutPlan` z listą aktywności i walidacją (brak nulli, czasy > 0). [06][05]
   - `domain.WorkoutLogEntry` (data, aktywność, spalone kalorie). [05b]
   - `service.LogService` do dodawania wpisów i eksportu CSV. [03]
5. Konstruktory i enkapsulacja [06][05]
   - Prywatne pola, gettery, niemodyfikowalne widoki kolekcji. [05]
   - Walidacje w konstruktorach (np. `durationMin > 0`). [06]
6. Demo w `App` [03]
   - Utwórz przykładowy plan, „odpal” aktywności, zbierz wpisy, wyeksportuj CSV. [03]
7. Publikacja GitHub [02]
   - `.gitignore`, `README.md` (opis, wymagania JDK, przykład wyjścia). [02]
   - Commity per krok: modele → interfejsy → serwisy → demo. [02]
8. Rozszerzenia (opcjonalnie) [07][08]
   - Interfejs `Measurable` (np. tętno, dystans) i klasy implementujące. [08]
   - Strategia `CalorieFormula`. [08]

---

## Pomysł 3: System Dziekanatu — zapisy na przedmioty (studia)

Cel: prosta obsługa studentów, przedmiotów, zapisów oraz generowanie list obecności/CSV; abstrakcyjny użytkownik, interfejsy `Gradable`, `Exportable`.

### Lista kontrolna
1. Setup i pakiety [02][04]
   - Pakiety: `dean.domain`, `dean.service`, `dean.app`, `dean.util`. [04]
   - `dean.app.Main` z `main`. [03]
2. Abstrakcja użytkownika [08][07]
   - Abstrakcyjna `domain.User` (pola: `id`, `fullName`, metody: `String role()`). [08]
   - `Student` i `Lecturer` dziedziczą po `User`. [07]
3. Przedmiot i grupa [05b][06]
   - `domain.Course` (kod, nazwa, ECTS, prowadzący). [05b]
   - `domain.Group` (kurs, limit miejsc, lista studentów). Waliduj limit i brak duplikatów. [06]
4. Interfejsy [08]
   - `domain.Gradable` z `grade(Student s, int value)`. [08]
   - `domain.Exportable` dla CSV (listy studentów, oceny). [08]
5. Zapisy i oceny [03]
   - `service.EnrollmentService` (zapis/wykreślenie, sprawdzenie miejsc). [03]
   - `service.GradeBookService` (wystawianie i eksport ocen). [03][08]
6. Kapsułkowanie i modyfikatory [05]
   - Prywatne pola, gettery, niemodyfikowalne kopie kolekcji, stałe `public static final` (np. max ECTS). [05][05b]
7. Konstruktory z walidacją [06]
   - Sprawdź puste nazwy, zakres ocen, limit miejsc > 0. [06]
8. Scenariusz demo [03]
   - Utwórz kursy, grupy, zapisz studentów, wystaw oceny, wygeneruj CSV. [03]
9. GitHub [02]
   - `.gitignore`, `README.md`, sensowne commity (feat: enrollment, feat: gradebook), `push`. [02]
10. Rozszerzenia (opcjonalnie) [07][08]
   - `interface Attendable` i rejestr obecności. [08]
   - `abstract Assessment` (kolokwium, projekt) jako hierarchia ocen. [07]

---

## Pomysł 4: Turniej Szachowy — parowanie i klasyfikacja (sport)

Cel: zarządzanie zawodnikami, rundami i wynikami; interfejsy punktacji, abstrakcyjny zawodnik, polimorfizm przy różnych systemach turniejowych.

### Lista kontrolna
1. Struktura [02][04]
   - Pakiety: `chess.domain`, `chess.tournament`, `chess.service`, `chess.app`. [04]
   - `chess.app.Main` z `main`. [03]
2. Zawodnik (abstrakcja) [08]
   - `domain.Competitor` (abstrakcyjna): `id`, `name`, `rating`. [08]
   - `domain.Player` extends `Competitor` (może zostać pusta konkretna klasa). [07]
3. Interfejsy [08]
   - `tournament.PairingStrategy` z `List<Match> pair(List<Player> players, int round)`. [08]
   - `tournament.ScoringRule` z `apply(Result r)` → punkty; implementacje: `Classic`, `Rapid`. [08]
   - `domain.Exportable` dla CSV. [08]
4. Podstawowe modele [05b]
   - `domain.Match` (white, black, `Result` enum: WHITE, BLACK, DRAW). [05b]
   - `domain.Standing` (suma punktów, Buchholz opcjonalnie). [05b]
5. Usługi [03]
   - `service.TournamentService` orkiestruje rundy: parowanie → wynik → aktualizacja tabeli. [03]
   - `service.ReportService` eksport CSV tabeli i wyników. [03][08]
6. Enkapsulacja i konstruktory [05][06]
   - Prywatne pola, gettery, walidacja ratingu (>= 0), niepuste imię. [06]
7. Demo [03]
   - 8 graczy, 5 rund, losowe wyniki, wydruk tabeli. [03]
8. GitHub [02]
   - Repo, `.gitignore`, `README.md` z opisem systemów punktacji i parowania. [02]
9. Rozszerzenia (opcjonalnie) [07][08]
   - Dodatkowe strategie parowania (np. szwajcarski vs. kołowy). [08]
   - `abstract Tournament` z klasami `RoundRobinTournament`, `SwissTournament`. [07]

---

## Pomysł 5: Wypożyczalnia Sprzętu Sportowego (sport)

Cel: zarządzanie magazynem rakiet, piłek, nart, itd.; interfejsy wypożyczania/zwrotu i eksportu; klasa abstrakcyjna sprzętu z różnymi podtypami.

### Lista kontrolna
1. Start i pakiety [02][04]
   - `sportsrent.domain`, `sportsrent.service`, `sportsrent.app`. [04]
   - `sportsrent.app.App` z `main`. [03]
2. Abstrakcja sprzętu [08]
   - `domain.Equipment` (abstrakcyjna): `id`, `name`, `condition` (enum), `String getDescription()` (abstr.). [08]
   - Klasy: `TennisRacket`, `SkiSet`, `FootballBall` z własnymi polami (np. rozmiar, długość). [07]
3. Interfejsy [08]
   - `domain.Rentable` (`rent()`, `returnBack()`, `isAvailable()`). [08]
   - `domain.Exportable` (`toCsv()`). [08]
   - Niech sprzęt implementuje oba interfejsy. [08]
4. Magazyn i operacje [03][05b]
   - `domain.Inventory` z listą sprzętu, metodami: `add()`, `findById()`, `available()`. [05b]
   - `service.RentalService` zarządza wypożyczeniami (prosty rejestr). [03]
5. Kapsułkowanie i walidacje [05][06]
   - Prywatne pola, stany wyliczeniowe, walidacja w konstruktorach (np. niepuste `id`). [06]
6. Demo [03]
   - Dodaj sprzęty, wypożycz, zwróć, wypisz CSV dostępnych. [03]
7. GitHub [02]
   - Repo, `.gitignore`, `README.md` (jak uruchomić, przykładowe wyjście). [02]
8. Rozszerzenia (opcjonalnie) [07][08]
   - Interfejs `Serviceable` (przegląd/serwis). [08]
   - Polityki opłat jako strategia (`PricingStrategy`). [08]

---

## Pomysł 6: Analizator Statystyk Meczowych — proste raporty (sport)

Cel: wczytanie prostych danych meczowych (np. z listy w pamięci), obliczenie metryk i wygenerowanie raportów z wykorzystaniem interfejsów raportujących i klas abstrakcyjnych dla źródeł danych.

### Lista kontrolna
1. Struktura [02][04]
   - Pakiety: `matchstats.domain`, `matchstats.report`, `matchstats.source`, `matchstats.app`. [04]
   - `matchstats.app.Main` z `main`. [03]
2. Modele domenowe [05b]
   - `domain.Team`, `domain.Match` (data, gospodarze, goście, wynik), `domain.PlayerStat` (opcjonalnie). [05b]
3. Abstrakcja źródła danych [08]
   - `source.DataSource` (abstrakcyjna) z `List<Match> loadMatches()`. [08]
   - Implementacje: `InMemorySource`, (opcjonalnie) `CsvSource`. [07]
4. Interfejsy raportów [08]
   - `report.Report` z `String render(List<Match> matches)`. [08]
   - Implementacje: `SummaryReport`, `TopScoringTeamsReport`. [08]
5. Serwis analityczny [03]
   - `report.ReportingService` łączy `DataSource` i `Report`, tworzy wydruk. [03]
6. Kapsułkowanie i konstrukcja [05][06]
   - Prywatne pola, gettery, walidacje (np. braki danych). [05][06]
7. Demo [03]
   - Utwórz `InMemorySource` z kilkoma meczami, wygeneruj 2 raporty i wydrukuj. [03]
8. GitHub [02]
   - Repo, `.gitignore`, `README.md` z przykładami raportu w kodzie blokowym. [02]
9. Rozszerzenia (opcjonalnie) [07][08]
   - Interfejs `Filter` i kompozycja filtrów (np. po drużynach). [08]
   - Abstrakcyjny `Formatter` i implementacje `CsvFormatter`, `JsonFormatter`. [07][08]

---

## Wskazówki ogólne do wszystkich 6 projektów
- Zanim zaczniesz kodować, zapisz mini‑diagram klas/interfejsów. [01]
- Trzymaj referencje w typach interfejsów lub klas bazowych; podstawiaj obiekty klas konkretnych (polimorfizm). [07][08]
- Dbaj o proste, małe konstruktory z walidacją argumentów i niezmienniki kolekcji. [06][05]
- Stosuj `public` tylko dla API, resztę trzymaj `private`/`package‑private`/`protected` zgodnie z potrzebą. [05]
- W `README.md` każdego projektu pokaż „Przykład użycia” i krótki fragment wyniku (np. CSV). [03][08]