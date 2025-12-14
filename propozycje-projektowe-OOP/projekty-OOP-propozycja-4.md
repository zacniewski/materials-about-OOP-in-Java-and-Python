# Osiem nowych pomysłów na projekt OOP w Javie (zakres: 01–08)

Poniżej znajdziesz osiem nowych, unikalnych pomysłów na projekty edukacyjne w Javie. Każdy został tak zaplanowany, aby ćwiczyć zagadnienia z materiałów od `01-paradygmaty-obiektowości.md` do `08-interfejsy-i-klasy-abstrakcyjne.md`. Każdy pomysł zawiera listę kontrolną (checklistę) z kolejnością działań i wskazaniem, które rozdziały ćwiczysz w danym kroku.

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

## Pomysł 1: System Rezerwacji Wizyt (gabinet/serwis)

Cel: zarządzanie kalendarzem specjalistów (np. fryzjer, mechanik), klientami i rezerwacjami, z prostą polityką dostępności i eksportem grafiku.

### Lista kontrolna
1. Inicjalizacja projektu i pakietów [02][04]
   - Pakiety: `booking.domain`, `booking.service`, `booking.app`, `booking.util`. [04]
   - `booking.app.Main` z `public static void main(String[] args)`. [03]
2. Abstrakcja użytkownika systemu [08][05]
   - `abstract class Person` z polami `id`, `fullName`. [08][05b]
   - Klasy konkretne: `Client`, `Specialist` (np. typ usługi). [07]
3. Sloty czasowe i dostępność [05b][06]
   - `domain.TimeSlot` (data, godzina od/do, `occupied`). [05b]
   - Walidacja spójności czasu w konstruktorze. [06]
4. Kontrakty domenowe [08]
   - `domain.Schedulable` z metodami `reserve(TimeSlot)`, `cancel(TimeSlot)`, `availableSlots()`. [08]
   - `domain.Exportable` z `String toCsv()`. [08]
5. Usługi rezerwacji [03][07]
   - `service.BookingService` kojarzy `Client` z `Specialist`, pilnuje kolizji slotów (polimorficzne `availableSlots()`). [07]
6. Kapsułkowanie i modyfikatory [05]
   - Prywatne pola, gettery, niemodyfikowalne widoki kolekcji. [05]
7. Konstruktory i niezmienniki [06]
   - Sprawdzenia nulli, pustych nazw, kolizji czasów. [06]
8. Scenariusz demo [03]
   - Dodaj 2 specjalistów z grafikiem, 3 klientów, wykonaj kilka rezerwacji, wygeneruj CSV. [03][08]
9. Rozszerzenia (opcjonalnie) [07][08]
   - `interface PricingPolicy` (różne stawki godzinowe). [08]
   - `abstract Notification` i implementacje: `EmailNotification`, `SmsNotification`. [07]
10. GitHub [02]
    - Repo, `.gitignore`, `README.md` z przykładowym grafikiem. [02]

Co ćwiczysz: hierarchie osób, kontrakty kalendarza (07–08), enkapsulację i niezmienniki (05–06), pakiety i `main` (04–03), praktykę narzędzi (02), myślenie obiektowe (01).

---

## Pomysł 2: Kino — rezerwacja i sprzedaż biletów

Cel: zarządzanie salami, seansami, miejscami i rezerwacjami biletów, z różnymi typami sal lub projekcji.

### Lista kontrolna
1. Struktura i pakiety [02][04]
   - `cinema.domain`, `cinema.service`, `cinema.app`, `cinema.util`. [04]
   - `cinema.app.Main` z `main`. [03]
2. Abstrakcja sali/projekcji [08]
   - `abstract class Auditorium` (nazwa, liczba rzędów, układ). [08][05b]
   - Implementacje: `StandardAuditorium`, `IMAXAuditorium` (np. inna siatka miejsc). [07]
3. Seanse i miejsca [05b][06]
   - `domain.Show` (film, sala, czas startu). [05b]
   - `domain.Seat` (rząd, miejsce, status). Walidacje w konstruktorach. [06]
4. Interfejsy [08]
   - `domain.Bookable` (`book()`, `cancel()`, `isAvailable()`) dla miejsca/seansu. [08]
   - `domain.Exportable` (`toCsv()` dla raportu sprzedaży). [08]
5. Serwisy [03]
   - `service.SeatMapService` generuje mapę miejsc dla sali. [03]
   - `service.TicketService` zarządza rezerwacjami i sprzedażą. [03]
6. Dostęp i kapsułkowanie [05]
   - Prywatne pola, kontrolowany mutowalny stan miejsc. [05]
7. Polimorfizm [07]
   - Różne sale wpływają na sposób tworzenia map miejsc i cenę (opcjonalnie). [07]
8. Demo [03]
   - Zdefiniuj 2 sale, 2 seanse, zrób rezerwacje i eksport raportu CSV. [03]
9. Rozszerzenia (opcjonalnie) [07][08]
   - `PricingStrategy` (dzień tygodnia, strefy miejsc). [08]
   - `abstract Ticket` z podklasami `StandardTicket`, `PremiumTicket`. [07]
10. GitHub [02]
    - Repo, `.gitignore`, `README.md` z mapą miejsc (ASCII). [02]

---

## Pomysł 3: Restauracja — obsługa stolików i zamówień

Cel: zarządzanie menu, stolikami, zamówieniami i rachunkami; polimorfizm dla pozycji menu i kanałów zamówień.

### Lista kontrolna
1. Setup [02][04]
   - Pakiety: `restaurant.domain`, `restaurant.order`, `restaurant.service`, `restaurant.app`. [04]
   - `restaurant.app.Main` z `main`. [03]
2. Abstrakcja pozycji menu [08]
   - `abstract class MenuItem` (nazwa, cena netto, stawka VAT, `grossPrice()`). [08][05b]
   - Podklasy: `Dish`, `Drink` (np. różne stawki VAT). [07]
3. Interfejsy i kontrakty [08]
   - `order.Billable` (sumowanie pozycji). [08]
   - `order.Printable` (`print()` rachunku). [08]
4. Modele zamówień [05b][06]
   - `domain.Table` (nr, liczba miejsc). [05b]
   - `order.Order` (stolik, pozycje, status). Walidacje w konstruktorach. [06]
5. Serwisy [03]
   - `service.MenuService` i `service.OrderService` (dodawanie pozycji, zamykanie rachunku). [03]
6. Modyfikatory i kapsułkowanie [05]
   - Prywatne pola, kontrola mutacji kolekcji pozycji. [05]
7. Polimorfizm [07]
   - Kanały zamówień (opcjonalnie): `DineInOrder`, `TakeawayOrder` (różnice w opłatach). [07]
8. Demo [03]
   - Zbuduj menu, zrealizuj 2 zamówienia, wydrukuj rachunki. [03]
9. Rozszerzenia (opcjonalnie) [07][08]
   - `DiscountPolicy` dla rachunków. [08]
   - `abstract ReceiptFormatter` z `PlainTextFormatter`, `CsvFormatter`. [07]
10. GitHub [02]
    - Repo, `.gitignore`, `README.md` z przykładowym rachunkiem. [02]

---

## Pomysł 4: Transport Publiczny — linie, rozkłady, pojazdy

Cel: modelowanie linii autobusowych/tramwajowych, przystanków, rozkładów i pojazdów; raporty z opóźnień/tras.

### Lista kontrolna
1. Struktura [02][04]
   - Pakiety: `transit.domain`, `transit.schedule`, `transit.service`, `transit.app`. [04]
   - `transit.app.Main` z `main`. [03]
2. Abstrakcja pojazdu [08]
   - `abstract class Vehicle` (id, pojemność, `String kind()`). [08]
   - `Bus`, `Tram` jako podklasy. [07]
3. Modele rozkładu [05b]
   - `domain.Line` (nazwa, lista przystanków). [05b]
   - `schedule.Timetable` (godziny odjazdów per przystanek). [05b]
4. Interfejsy [08]
   - `domain.Exportable` (`toCsv()` rozkładów/tras). [08]
   - `service.Trackable` (`start()`, `stop()`, `isActive()`) dla monitorowania kursu. [08]
5. Serwisy [03]
   - `service.RouteService` (wyznaczanie przejazdu po przystankach). [03]
   - `service.ReportingService` (raporty CSV). [03]
6. Kapsułkowanie i walidacje [05][06]
   - Walidacja pustych nazw, duplikatów przystanków, dodatniej pojemności. [06]
7. Polimorfizm [07]
   - Różne pojazdy mają różne ograniczenia (np. pojemność), wpływające na kalkulacje raportów. [07]
8. Demo [03]
   - Zdefiniuj 2 linie, dodaj kursy, wygeneruj raport rozkładu. [03]
9. Rozszerzenia (opcjonalnie) [07][08]
   - Strategia obliczania czasu przejazdu (szczyt/poza szczytem). [08]
   - `abstract Tracker` z implementacjami (np. losowe opóźnienia). [07]
10. GitHub [02]
    - Repo, `.gitignore`, `README.md` z przykładowym rozkładem. [02]

---

## Pomysł 5: Mini Bank — konta, transakcje, karty

Cel: prosty system bankowy obsługujący różne typy kont, transakcje i karty; zasady autoryzacji i limity.

### Lista kontrolna
1. Setup [02][04]
   - Pakiety: `bank.domain`, `bank.service`, `bank.app`, `bank.util`. [04]
   - `bank.app.Main` z `main`. [03]
2. Abstrakcja konta [08]
   - `abstract class Account` (nr, właściciel, saldo). [08]
   - `CheckingAccount`, `SavingsAccount` (różne zasady naliczania opłat/odsetek). [07]
3. Interfejsy [08]
   - `domain.Transactable` (`deposit()`, `withdraw()`, `transferTo()`). [08]
   - `domain.Exportable` (historia operacji do CSV). [08]
4. Karty i autoryzacje [05b][07]
   - `domain.Card` (nr, limit dzienny), podklasy: `DebitCard`, `VirtualCard`. [07]
   - Prosta weryfikacja limitów. [06]
5. Serwisy [03]
   - `service.AccountService`, `service.TransferService` (przelewy, validacje). [03]
6. Modyfikatory/kapsułkowanie [05]
   - Prywatne pola, kontrola stanu, niemodyfikowalne widoki. [05]
7. Polimorfizm [07]
   - Różne typy kont implementują inaczej opłaty/odsetki. [07]
8. Demo [03]
   - Utwórz konta, wykonaj wpłaty/przelewy, wygeneruj wyciąg CSV. [03]
9. Rozszerzenia (opcjonalnie) [07][08]
   - `FeePolicy` i `InterestPolicy` jako strategie. [08]
   - `abstract FraudDetector` z prostą implementacją. [07]
10. GitHub [02]
    - Repo, `.gitignore`, `README.md` z przykładowym wyciągiem. [02]

---

## Pomysł 6: System Ankiet — formularze, pytania, odpowiedzi

Cel: tworzenie ankiet z różnymi typami pytań, zbieranie odpowiedzi i generowanie raportów.

### Lista kontrolna
1. Struktura [02][04]
   - Pakiety: `survey.domain`, `survey.service`, `survey.app`. [04]
   - `survey.app.Main` z `main`. [03]
2. Abstrakcja pytania [08]
   - `abstract class Question` (treść, obowiązkowe?). [08]
   - Podklasy: `SingleChoice`, `MultipleChoice`, `TextQuestion`. [07]
3. Interfejsy [08]
   - `domain.Answerable` (`boolean validateAnswer(...)`). [08]
   - `domain.Exportable` (`toCsv()` wyników). [08]
4. Modele [05b]
   - `domain.Survey` (tytuł, lista pytań). [05b]
   - `domain.Response` (respondent, odpowiedzi). [05b]
5. Serwisy [03]
   - `service.SurveyService` (tworzenie ankiet, dodawanie pytań). [03]
   - `service.ResponseService` (rejestracja odpowiedzi, prosta agregacja). [03]
6. Kapsułkowanie/walidacje [05][06]
   - Walidacja odpowiedzi zgodnie z typem pytania. [06]
7. Polimorfizm [07]
   - Różne pytania odmiennie walidują/formatują odpowiedzi. [07]
8. Demo [03]
   - Utwórz ankietę z 3 typami pytań, zbierz 3 odpowiedzi, wygeneruj CSV. [03]
9. Rozszerzenia (opcjonalnie) [07][08]
   - `ScoringStrategy` (np. quiz punktowany). [08]
   - `abstract Renderer` (tekst/CSV). [07]
10. GitHub [02]
    - Repo, `.gitignore`, `README.md` z przykładowym eksportem. [02]

---

## Pomysł 7: Firma Kurierska — paczki, trasy, statusy

Cel: zarządzanie przesyłkami, punktami nadania/odbioru i trasami kurierów; śledzenie statusów przesyłek.

### Lista kontrolna
1. Setup [02][04]
   - Pakiety: `courier.domain`, `courier.service`, `courier.app`. [04]
   - `courier.app.Main` z `main`. [03]
2. Abstrakcja przesyłki [08]
   - `abstract class Parcel` (id, waga, gabaryt). [08]
   - `StandardParcel`, `FragileParcel` (różne ograniczenia/przetwarzanie). [07]
3. Interfejsy [08]
   - `domain.Trackable` (`start()`, `stop()`, `isActive()`) dla trasy. [08]
   - `domain.Exportable` (historia statusów do CSV). [08]
4. Modele i walidacje [05b][06]
   - `domain.Address`, `domain.Route`, `domain.Status` (enum). [05b]
   - Walidacje pól (niepuste kody, dodatnia waga). [06]
5. Serwisy [03]
   - `service.RoutingService` (przypisywanie paczek do tras). [03]
   - `service.TrackingService` (zmiany statusów). [03]
6. Modyfikatory/kapsułkowanie [05]
   - Prywatne pola, gettery, niemodyfikowalne widoki list statusów. [05]
7. Polimorfizm [07]
   - Różne typy paczek wymagają różnych zasad (np. delikatne z dodatkowymi krokami). [07]
8. Demo [03]
   - Utwórz kilka paczek i tras, zasymuluj doręczenia, wygeneruj CSV. [03]
9. Rozszerzenia (opcjonalnie) [07][08]
   - Strategia wyceny (`PricingStrategy`). [08]
   - `abstract Notifier` (np. email/SMS o statusie). [07]
10. GitHub [02]
    - Repo, `.gitignore`, `README.md` z przykładową historią statusów. [02]

---

## Pomysł 8: Sieć Stacji Meteo — pomiary i raporty

Cel: zarządzanie stacjami meteo i pomiarami (temperatura, wilgotność, wiatr), raportowanie i agregacja danych.

### Lista kontrolna
1. Struktura [02][04]
   - Pakiety: `meteo.domain`, `meteo.source`, `meteo.report`, `meteo.app`. [04]
   - `meteo.app.Main` z `main`. [03]
2. Abstrakcja stacji/pomiaru [08]
   - `abstract class Measurement` (czas, jednostka, wartość). [08]
   - Podklasy: `Temperature`, `Humidity`, `WindSpeed`. [07]
3. Interfejsy [08]
   - `source.DataProvider` (`List<Measurement> readAll()`). [08]
   - `report.Report` (`String render(List<Measurement> ms)`). [08]
4. Modele [05b]
   - `domain.Station` (id, lokalizacja, lista pomiarów). [05b]
   - `domain.Unit` (enum). [05b]
5. Serwisy [03]
   - `report.ReportingService` (agregacje: min/avg/max). [03]
   - `source.InMemoryProvider` jako przykładowe źródło. [03]
6. Kapsułkowanie/walidacje [05][06]
   - Prywatne pola, walidacja jednostek i zakresów. [06]
7. Polimorfizm [07]
   - Różne typy pomiarów renderują się/interpretują inaczej. [07]
8. Demo [03]
   - Dodaj 2 stacje, generuj kilka pomiarów, wydrukuj 2 raporty. [03]
9. Rozszerzenia (opcjonalnie) [07][08]
   - `Formatter` (CSV/JSON) jako interfejs/abstrakcja. [08]
   - `abstract Aggregator` (np. dobowy/tygodniowy). [07]
10. GitHub [02]
    - Repo, `.gitignore`, `README.md` z przykładowym raportem. [02]

---

## Wskazówki ogólne
- Zanim zaczniesz, narysuj krótki diagram klas/interfejsów. [01]
- Trzymaj referencje w typach interfejsów/klas bazowych, podstawiaj obiekty konkretnych klas (polimorfizm). [07][08]
- Waliduj argumenty w konstruktorach/metodach i chroń niezmienniki kolekcji (niemodyfikowalne kopie). [06][05]
- Ustal rozsądne modyfikatory dostępu; unikaj „wycieku” szczegółów implementacyjnych. [05]
- W `README.md` dołącz sekcję „Przykład użycia” z krótkim wyjściem (np. CSV). [03][08]