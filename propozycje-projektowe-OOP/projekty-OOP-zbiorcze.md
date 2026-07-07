# Zbiorczy zestaw propozycji projektowych OOP

Ten dokument scala wszystkie propozycje projektowe z folderu `propozycje-projektowe-OOP` do jednej, ujednoliconej wersji. Projekty zostały uporządkowane tematycznie, dopracowane językowo i wyraźniej zróżnicowane pod względem domeny, akcentu OOP oraz poziomu trudności.

<a id="spis-tresci"></a>
## Spis treści

1. [Jak korzystać z tego dokumentu](#jak-korzystac-z-tego-dokumentu)
2. [Legenda zakresu materiału](#legenda-zakresu-materialu)
3. [Projekty: administracja i usługi](#projekty-administracja-i-uslugi)
   1. [System rezerwacji pokoi w hostelu](#projekt-1-system-rezerwacji-pokoi-w-hostelu)
   2. [System rezerwacji wizyt](#projekt-2-system-rezerwacji-wizyt)
   3. [Kino: rezerwacja i sprzedaż biletów](#projekt-3-kino-rezerwacja-i-sprzedaz-biletow)
   4. [Restauracja: stoliki, zamówienia i rachunki](#projekt-4-restauracja-stoliki-zamowienia-i-rachunki)
4. [Projekty: handel, media i finanse](#projekty-handel-media-i-finanse)
   1. [Menedżer biblioteki mediów](#projekt-5-menedzer-biblioteki-mediow)
   2. [Mini sklep internetowy](#projekt-6-mini-sklep-internetowy)
   3. [Mini bank: konta, transakcje i karty](#projekt-7-mini-bank-konta-transakcje-i-karty)
   4. [Kalkulator wydatków domowych](#projekt-8-kalkulator-wydatkow-domowych)
5. [Projekty: edukacja i produktywność](#projekty-edukacja-i-produktywnosc)
   1. [Planer zadań i raportów](#projekt-9-planer-zadan-i-raportow)
   2. [Mini system TODO z wyjątkami i kopiowaniem obiektów](#projekt-10-mini-system-todo-z-wyjatkami-i-kopiowaniem-obiektow)
   3. [System dziekanatu: zapisy na przedmioty](#projekt-11-system-dziekanatu-zapisy-na-przedmioty)
   4. [System ankiet i formularzy](#projekt-12-system-ankiet-i-formularzy)
6. [Projekty: sport i rekreacja](#projekty-sport-i-rekreacja)
   1. [System wypożyczalni rowerów miejskich](#projekt-13-system-wypozyczalni-rowerow-miejskich)
   2. [Liga uczelniana: terminarz i tabela wyników](#projekt-14-liga-uczelniana-terminarz-i-tabela-wynikow)
   3. [Aplikacja trenera personalnego](#projekt-15-aplikacja-trenera-personalnego)
   4. [Turniej szachowy: parowanie i klasyfikacja](#projekt-16-turniej-szachowy-parowanie-i-klasyfikacja)
   5. [Wypożyczalnia sprzętu sportowego](#projekt-17-wypozyczalnia-sprzetu-sportowego)
   6. [Analizator statystyk meczowych](#projekt-18-analizator-statystyk-meczowych)
7. [Projekty: transport, logistyka i infrastruktura](#projekty-transport-logistyka-i-infrastruktura)
   1. [Transport publiczny: linie, rozkłady i pojazdy](#projekt-19-transport-publiczny-linie-rozklady-i-pojazdy)
   2. [Firma kurierska: paczki, trasy i statusy](#projekt-20-firma-kurierska-paczki-trasy-i-statusy)
8. [Projekty: przyroda, monitoring i symulacje](#projekty-przyroda-monitoring-i-symulacje)
   1. [Symulator zoo](#projekt-21-symulator-zoo)
   2. [Monitor stacji pogodowej](#projekt-22-monitor-stacji-pogodowej)
   3. [Sieć stacji meteo](#projekt-23-siec-stacji-meteo)
   4. [Katalog roślin i stanowisk](#projekt-24-katalog-roslin-i-stanowisk)
9. [Wspólne wskazówki do wszystkich projektów](#wspolne-wskazowki-do-wszystkich-projektow)

<a id="jak-korzystac-z-tego-dokumentu"></a>
## Jak korzystać z tego dokumentu

- Wybierz projekt przede wszystkim po domenie, a nie tylko po nazwie.
- Sprawdź sekcję `Glowny akcent OOP`, bo to ona najlepiej pokazuje, czego dany temat uczy.
- Traktuj checklisty jako kolejność realizacji, nie jako sztywny kontrakt.
- Jeśli projekt ma status `rozszerzony`, zakłada wyjście poza podstawy i dotyka też testów, wyjątków, SOLID albo kopiowania obiektów.

<a id="legenda-zakresu-materialu"></a>
## Legenda zakresu materiału

- [01] Paradygmaty obiektowości
- [02] Środowisko programisty OOP
- [03] Klasy i metoda `main`
- [04] Pakiety i importy
- [05] Modyfikatory dostępu
- [05b] Pola klas
- [06] Konstruktory
- [07] Dziedziczenie i polimorfizm
- [08] Interfejsy i klasy abstrakcyjne
- [09] Testowanie kodu
- [10] SOLID
- [11] Wyjątki
- [12] Referencje do obiektów i kopiowanie

<a id="projekty-administracja-i-uslugi"></a>
## Projekty: administracja i usługi

<a id="projekt-1-system-rezerwacji-pokoi-w-hostelu"></a>
### Projekt 1: System rezerwacji pokoi w hostelu

**Poziom:** podstawowy  
**Główny akcent OOP:** klasy abstrakcyjne, interfejsy, enkapsulacja

**Cel projektu:** zbudować prosty system do zarządzania pokojami, gośćmi i rezerwacjami.

#### Lista kontrolna
1. Przygotuj projekt i pakiety [02][04]
   - Utwórz pakiety `hostel.domain`, `hostel.service`, `hostel.app`, `hostel.report`.
   - Dodaj klasę `hostel.app.Main` z przykładowym scenariuszem uruchomieniowym.
2. Zamodeluj pokój jako abstrakcję [08][05][05b]
   - Stwórz abstrakcyjną klasę `Room` z polami `id`, `beds`, `occupied`.
   - Dodaj metody wspólne: `occupy()`, `release()`, `isOccupied()`, `description()`.
3. Dodaj konkretne typy pokoi [07][06]
   - Zaimplementuj `SingleRoom`, `DormRoom`, `FamilyRoom`.
   - Waliduj liczbę łóżek i ograniczenia pojemności w konstruktorach.
4. Zdefiniuj kontrakty domenowe [08]
   - Dodaj interfejsy `Bookable` i `Exportable`.
   - Niech każdy pokój implementuje eksport do CSV i logikę dostępności.
5. Wprowadź encje gościa i rezerwacji [03][05]
   - Utwórz klasy `Guest` i `Reservation`.
   - Zadbaj o walidację danych, np. pustych nazw i niepoprawnych dat.
6. Zaimplementuj warstwę serwisową [03][07]
   - `ReservationService` odpowiada za tworzenie i anulowanie rezerwacji.
   - `ReportingService` generuje raport pokoi oraz raport rezerwacji.
7. Pokaż polimorfizm w praktyce [07][08]
   - Przechowuj pokoje w kolekcji typu `List<Room>` albo `List<Bookable>`.
   - W `Main` obsłuż kilka różnych pokoi jednym przepływem kodu.
8. Dopracuj finał projektu [02]
   - Dodaj `README.md` z opisem pakietów i przykładowym outputem.
   - Przygotuj repozytorium Git i pierwszy tag wersji demo.

<a id="projekt-2-system-rezerwacji-wizyt"></a>
### Projekt 2: System rezerwacji wizyt

**Poziom:** podstawowy  
**Główny akcent OOP:** relacje między obiektami, logika terminów, kolekcje

**Cel projektu:** obsłużyć kalendarz specjalistów, klientów i rezerwacje slotów czasowych.

#### Lista kontrolna
1. Zdefiniuj strukturę pakietów [02][04]
   - Użyj `booking.domain`, `booking.service`, `booking.app`, `booking.util`.
   - Przygotuj klasę `Main` pokazującą przepływ od wolnego terminu do rezerwacji.
2. Utwórz model osób [08][07]
   - Dodaj abstrakcyjną klasę `Person`.
   - Rozszerz ją klasami `Client` i `Specialist`.
3. Zamodeluj slot czasowy [05b][06]
   - Dodaj klasę `TimeSlot` z polami `start`, `end`, `occupied`.
   - Waliduj, że `end` jest późniejsze niż `start`.
4. Dodaj kontrakt rezerwacyjny [08]
   - Zdefiniuj interfejs `Schedulable`.
   - Niech `Specialist` udostępnia listę wolnych slotów i możliwość rezerwacji.
5. Zaimplementuj logikę biznesową [03]
   - `BookingService` sprawdza kolizje terminów i tworzy wpisy rezerwacji.
   - `ScheduleService` generuje kalendarz dnia lub tygodnia.
6. Zadbaj o kapsułkowanie [05]
   - Kolekcje slotów zwracaj jako kopie albo widoki niemodyfikowalne.
   - Ukryj szczegóły implementacyjne poza publicznym API.
7. Przygotuj eksport i raporty [08]
   - Dodaj `Exportable` do generowania grafików lub list rezerwacji.
   - Przygotuj prosty raport tekstowy i CSV.
8. Rozszerz projekt o dodatkowy wariant [07][08]
   - Dodaj typy specjalistów z odmienną długością wizyt.
   - Pokaż, jak polimorfizm upraszcza planowanie kalendarza.

<a id="projekt-3-kino-rezerwacja-i-sprzedaz-biletow"></a>
### Projekt 3: Kino: rezerwacja i sprzedaż biletów

**Poziom:** podstawowy  
**Główny akcent OOP:** modelowanie stanu i wariantów sal

**Cel projektu:** obsłużyć sale, seanse, miejsca oraz zakup lub anulowanie biletów.

#### Lista kontrolna
1. Zorganizuj projekt [02][04]
   - Utwórz pakiety `cinema.domain`, `cinema.service`, `cinema.app`, `cinema.report`.
   - Dodaj klasę startową z przykładowym dniem pracy kina.
2. Stwórz abstrakcję sali [08]
   - Utwórz abstrakcyjną klasę `Auditorium`.
   - Przygotuj podklasy `StandardAuditorium` i `IMAXAuditorium`.
3. Zamodeluj miejsce i seans [05b][06]
   - Dodaj klasy `Seat` oraz `Show`.
   - Waliduj numery rzędów, kolumn i czas rozpoczęcia.
4. Wprowadź interfejsy [08]
   - Dodaj `Bookable` dla miejsca lub biletu.
   - Dodaj `Exportable` dla raportów sprzedaży.
5. Zaimplementuj serwisy [03]
   - `SeatMapService` tworzy układ miejsc.
   - `TicketService` obsługuje rezerwacje, anulacje i sprzedaż.
6. Pokaż zmienność zachowania [07]
   - Różne typy sal mogą mieć inny układ miejsc albo inną politykę cen.
   - Zastosuj polimorfizm zamiast instrukcji warunkowych rozrzuconych po kodzie.
7. Przygotuj scenariusz demo [03]
   - Utwórz dwa seanse w różnych salach.
   - Zarezerwuj kilka miejsc i wygeneruj raport dzienny.
8. Domknij projekt dokumentacja [02]
   - Dodaj przykładową mape miejsc w `README.md`.
   - Opisz architekturę klas i założenia modelu.

<a id="projekt-4-restauracja-stoliki-zamowienia-i-rachunki"></a>
### Projekt 4: Restauracja: stoliki, zamówienia i rachunki

**Poziom:** podstawowy  
**Główny akcent OOP:** kompozycja, warianty pozycji menu, odpowiedzialność klas

**Cel projektu:** zarządzać menu, stolikami, zamówieniami i drukowaniem rachunków.

#### Lista kontrolna
1. Przygotuj strukturę pakietów [02][04]
   - Użyj `restaurant.domain`, `restaurant.order`, `restaurant.service`, `restaurant.app`.
   - Dodaj klasę `Main` pokazującą pełny cykl obsługi gościa.
2. Zamodeluj pozycje menu [08][07]
   - Stwórz abstrakcyjną klasę `MenuItem`.
   - Dodaj klasy `Dish`, `Drink`, opcjonalnie `Dessert`.
3. Dodaj encje stolika i zamówienia [05b][06]
   - Klasa `Table` przechowuje numer i liczbę miejsc.
   - Klasa `Order` przechowuje pozycje, status i powiązanie ze stolikiem.
4. Wprowadź kontrakty [08]
   - Dodaj interfejsy `Billable` i `Printable`.
   - Rachunek powinien byc tworzony z obiektów, a nie z luźnych stringów.
5. Zaimplementuj serwisy [03]
   - `MenuService` zarządza listą pozycji.
   - `OrderService` dodaje pozycje, zamyka rachunek i liczy sumę.
6. Zadbaj o spójność stanu [05][06]
   - Nie pozwalaj dodawac pozycji do zamkniętego zamówienia.
   - Waliduj cenę, ilość i numer stolika.
7. Wykorzystaj polimorfizm [07][08]
   - Różne typy pozycji menu mogą inaczej liczyć cenę brutto albo sposób prezentacji.
   - Dodaj różne typy zamówień, np. `DineInOrder` i `TakeawayOrder`.
8. Przygotuj demonstracje i eksport [03][08]
   - Wydrukuj dwa rachunki dla różnych scenariuszy.
   - Dodaj eksport rachunku do CSV albo plain text.

<a id="projekty-handel-media-i-finanse"></a>
## Projekty: handel, media i finanse

<a id="projekt-5-menedzer-biblioteki-mediow"></a>
### Projekt 5: Menedżer biblioteki mediów

**Poziom:** podstawowy  
**Główny akcent OOP:** dziedziczenie i wspólne API dla różnych typów mediów

**Cel projektu:** zarządzać kolekcja książek, filmów i albumów muzycznych.

#### Lista kontrolna
1. Zbuduj szkielet projektu [02][04]
   - Utwórz pakiety `media.domain`, `media.service`, `media.export`, `media.app`.
   - Dodaj klasę startowa `App`.
2. Utwórz abstrakcyjny model medium [08][05b]
   - Stwórz klasę `MediaItem` z polami `id`, `title`, `year`.
   - Dodaj metody `basicInfo()`, `details()` i `age()`.
3. Dodaj konkretne typy [07][06]
   - Zaimplementuj `Book`, `Movie`, `Album`.
   - Waliduj tytuł, rok oraz pola specyficzne, np. liczbę stron.
4. Zdefiniuj interfejsy [08]
   - Dodaj `Filterable` do prostego wyszukiwania.
   - Dodaj `Exportable` do CSV i opcjonalnie JSON.
5. Wprowadź repozytorium i serwisy [03][05]
   - `MediaRepository` trzyma dane w pamięci.
   - `LibraryService` obsługuje wyszukiwanie, filtrowanie i eksport.
6. Pokaż przewagę polimorfizmu [07]
   - Trzymaj media w jednej kolekcji typu `List<MediaItem>`.
   - Wywołuj `details()` bez sprawdzania typu obiektu.
7. Przygotuj warstwę demonstracyjną [03]
   - Dodaj kilka mediów, wyszukaj po frazie, wygeneruj raport.
   - Pokaż eksport w dwóch formatach, jeśli wdrożysz oba.
8. Rozszerz projekt [08]
   - Dodaj `Rateable` dla ocen.
   - Rozważ abstrakcyjną klasę `RatedMediaItem`.

<a id="projekt-6-mini-sklep-internetowy"></a>
### Projekt 6: Mini sklep internetowy

**Poziom:** średni  
**Główny akcent OOP:** strategie rabatowe, płatności, dokumenty sprzedaży

**Cel projektu:** zbudować uproszczony sklep z koszykiem, produktami, rabatami i płatnościami.
 
#### Lista kontrolna
1. Przygotuj moduły projektu [02][04]
   - Użyj pakietów `shop.domain`, `shop.pricing`, `shop.payment`, `shop.invoice`, `shop.app`.
   - Dodaj klasę `Main` z przykładowym zamówieniem.
2. Zaprojektuj abstrakcję produktu [08][07]
   - Stwórz abstrakcyjną klasę `Product`.
   - Dodaj konkretne typy `Book`, `Food`, `Electronic`.
3. Utwórz modele koszyka [05b][06]
   - Dodaj `CartItem` i `Cart`.
   - Waliduj ilość, cenę netto i stawkę podatku.
4. Wprowadź polityki rabatowe [08]
   - Dodaj interfejs `DiscountPolicy`.
   - Zaimplementuj `NoDiscount`, `PercentageDiscount`, `ThresholdFixedDiscount`.
5. Dodaj płatności jako kontrakty [08]
   - Zdefiniuj interfejs `Payable`.
   - Przygotuj klasy `CardPayment`, `BlikPayment`, `CashOnDelivery`.
6. Zbuduj warstwę dokumentów [03][08]
   - Klasa `Invoice` implementuje `Printable`.
   - Opcjonalnie dodaj `Exportable` do CSV.
7. Połącz wszystko serwisami [03][07]
   - `CartService` liczy sumy i nakłada rabaty.
   - `PaymentService` steruje autoryzacja i finalizacja zamówienia.
8. Zademonstruj różne scenariusze [03]
   - Pokaż dwa koszyki z różnymi politykami rabatowymi.
   - Opłać zamówienie dwiema metodami płatności.
9. Uporządkuj projekt [02]
   - Dodaj przykłady danych testowych i instrukcje uruchomienia.
   - Opisz w `README.md`, gdzie zastosowano polimorfizm i interfejsy.

<a id="projekt-7-mini-bank-konta-transakcje-i-karty"></a>
### Projekt 7: Mini bank: konta, transakcje i karty

**Poziom:** średni  
**Główny akcent OOP:** kontrola stanu, reguly biznesowe, strategie opłat i odsetek

**Cel projektu:** obsłużyć konta bankowe, przelewy, limity kart oraz historie operacji.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Użyj pakietów `bank.domain`, `bank.service`, `bank.report`, `bank.app`.
   - Dodaj klasę `Main` z kilkoma klientami i kontami.
2. Stwórz abstrakcję konta [08][07]
   - Dodaj abstrakcyjną klasę `Account`.
   - Zaimplementuj `CheckingAccount` i `SavingsAccount`.
3. Dodaj kontrakty operacyjne [08]
   - Zdefiniuj interfejs `Transactable`.
   - Dodaj `Exportable` dla historii operacji.
4. Zamodeluj transakcje i karty [05b][06]
   - Utwórz klasy `Transaction`, `Card`, `DebitCard`, `VirtualCard`.
   - Waliduj kwoty, saldo i limity dzienne.
5. Zaimplementuj serwisy [03]
   - `AccountService` obsługuje otwarcie kont i wpłaty.
   - `TransferService` pilnuje przelewów i niezmienników salda.
6. Pokaż warianty zachowań [07]
   - Różne typy kont inaczej naliczają odsetki albo opłaty.
   - Różne typy kart mogą mieć odmienne limity lub zasady autoryzacji.
7. Przygotuj raportowanie [08]
   - Generuj wyciąg tekstowy albo CSV.
   - Dodaj zestawienie operacji per konto.
8. Rozszerz projekt [08]
   - Dodaj `FeePolicy` i `InterestPolicy`.
   - Rozważ prosty `FraudDetector` jako abstrakcyjny komponent.

<a id="projekt-8-kalkulator-wydatkow-domowych"></a>
### Projekt 8: Kalkulator wydatków domowych

**Poziom:** rozszerzony  
**Główny akcent OOP:** odpowiedzialność klas, podstawy SOLID, walidacja i testy

**Cel projektu:** rejestrować wydatki, dzielić je na kategorie i generować podstawowe raporty.

#### Lista kontrolna
1. Przygotuj prosta architekturę [02][04][10]
   - Podziel kod na `finance.core`, `finance.report`, `finance.app`.
   - Rozdziel przechowywanie danych od generowania raportów.
2. Zamodeluj wydatki [08][07]
   - Utwórz abstrakcyjną klasę `Expense`.
   - Dodaj `FoodExpense`, `EntertainmentExpense`, `TransportExpense`.
3. Dodaj walidację i wyjątki [06][11]
   - Nie pozwalaj na ujemne kwoty.
   - Rzucaj `InvalidAmountException` lub `IllegalArgumentException`.
4. Wprowadź manager i generator raportów [03][10]
   - `ExpenseManager` zarządza listą wydatków.
   - `ReportGenerator` odpowiada tylko za podsumowania i prezentacje.
5. Pokaż polimorfizm [07]
   - Różne typy wydatków mogą inaczej liczyć podatek lub klasyfikację.
   - Utrzymuj kolekcję typu `List<Expense>`.
6. Zadbaj o bezpieczeństwo referencji [05][12]
   - Nie udostępniaj wewnętrznej listy do swobodnej modyfikacji.
   - Zwracaj kopie albo widoki niemodyfikowalne.
7. Dodaj test podstawowy [09]
   - Napisz jeden test liczący sumę wydatków.
   - Dołóż drugi test sprawdzający rzucanie wyjątku dla błędnej kwoty.
8. Przygotuj wersję demo [03]
   - W `Main` utwórz kilka wydatków i wydrukuj raport miesięczny.
   - Opisz przykład danych w `README.md`.

<a id="projekty-edukacja-i-produktywnosc"></a>
## Projekty: edukacja i produktywność

<a id="projekt-9-planer-zadan-i-raportow"></a>
### Projekt 9: Planer zadań i raportów

**Poziom:** podstawowy  
**Główny akcent OOP:** filtracja, strategie sortowania, modelowanie zadań

**Cel projektu:** zarządzać zadaniami z kategoriami, priorytetami i terminami.

#### Lista kontrolna
1. Zbuduj strukturę projektu [02][04]
   - Użyj pakietów `planner.domain`, `planner.service`, `planner.export`, `planner.app`.
   - Dodaj klasę `Main`.
2. Zamodeluj zadanie [08][05b]
   - Utwórz abstrakcyjną klasę `Task`.
   - Dodaj podklasy `WorkTask` i `PersonalTask`.
3. Dodaj walidację konstruktorów [06]
   - Sprawdź `id`, `title` i `dueDate`.
   - Ustal niezmienniki dla obiektów zadania.
4. Zdefiniuj kontrakty [08]
   - Dodaj `Exportable` i interfejs `Filter`.
   - Rozważ osobny interfejs `Printable`.
5. Zaimplementuj repozytorium i serwisy [03][05]
   - `TaskRepository` trzyma zadania w pamięci.
   - `TaskService` obsługuje CRUD, filtrowanie i sortowanie.
6. Pokaż wzorzec strategii [08][07]
   - Dodaj różne implementacje filtrów, np. po priorytecie i terminie.
   - Dodaj strategie sortowania, np. po dacie i po nazwie.
7. Wygeneruj raporty [03][08]
   - Dodaj eksport listy zadań do CSV.
   - Wydrukuj raport zadań opóźnionych i pilnych.
8. Przygotuj demonstracje [03]
   - Dodaj kilka zadań różnego typu.
   - Pokaż przefiltrowanie, posortowanie i eksport.

<a id="projekt-10-mini-system-todo-z-wyjatkami-i-kopiowaniem-obiektow"></a>
### Projekt 10: Mini system TODO z wyjątkami i kopiowaniem obiektów

**Poziom:** rozszerzony  
**Główny akcent OOP:** wyjątki, `equals/hashCode`, kopiowanie i referencje

**Cel projektu:** stworzyć mala aplikacje TODO, ktora poza podstawami ćwiczy też bezpieczne operowanie obiektami.

#### Lista kontrolna
1. Przygotuj minimalną architekturę [02][04]
   - Użyj pakietów `todo.model`, `todo.service`, `todo.app`.
   - Dodaj klasę `Main` i prosty zestaw danych testowych.
2. Zamodeluj zadanie [08][05]
   - Dodaj abstrakcyjną klasę `Task` z polami `id`, `description`, `done`.
   - Ukryj pola i wystaw tylko potrzebne API.
3. Dodaj specjalizacje [07][06]
   - Zaimplementuj `WorkTask` i `PersonalTask`.
   - Waliduj opis i termin w konstruktorach.
4. Wprowadź własne wyjątki [11]
   - Dodaj `TaskValidationException`.
   - Obsłuż sytuacje błędnego terminu lub pustego opisu.
5. Zaimplementuj kopiowanie i porównywanie [12]
   - Dodaj konstruktor kopiujący albo metodę `copy()`.
   - Nadpisz `equals()` i `hashCode()` zgodnie z przyjętą tożsamością zadania.
6. Zaimplementuj warstwę serwisową [03]
   - `TaskService` dodaje, usuwa i oznacza zadania jako wykonane.
   - Zadbaj o to, by serwis nie wystawiał wewnętrznej listy do modyfikacji.
7. Dodaj prosty przepływ błędów [03][11]
   - W `Main` obsłuż wyjątki blokiem `try-catch`.
   - Pokaż scenariusz poprawny i scenariusz bledny.
8. Rozszerz projekt testem lub porównaniem [09][12]
   - Sprawdź, czy kopia zadania zachowuje oczekiwane dane.
   - Zweryfikuj zachowanie `equals()` dla dwóch obiektów.

<a id="projekt-11-system-dziekanatu-zapisy-na-przedmioty"></a>
### Projekt 11: System dziekanatu: zapisy na przedmioty

**Poziom:** średni  
**Główny akcent OOP:** relacje wiele-do-wielu, ograniczenia zapisów, role użytkowników

**Cel projektu:** obsłużyć studentów, prowadzących, kursy, grupy i wpisy ocen.

#### Lista kontrolna
1. Przygotuj szkielet projektu [02][04]
   - Użyj pakietów `dean.domain`, `dean.service`, `dean.app`, `dean.report`.
   - Dodaj klasę `Main` pokazującą semestr od zapisów po oceny.
2. Zamodeluj użytkowników [08][07]
   - Stwórz abstrakcyjną klasę `User`.
   - Dodaj `Student` i `Lecturer`.
3. Dodaj kursy i grupy [05b][06]
   - Utwórz `Course` oraz `Group`.
   - Waliduj limit miejsc, ECTS i brak duplikatów studentów.
4. Zdefiniuj interfejsy [08]
   - Dodaj `Gradable` dla pracy z ocenami.
   - Dodaj `Exportable` dla list studentów i wyników.
5. Zaimplementuj serwisy [03]
   - `EnrollmentService` obsługuje zapisy i wypisania.
   - `GradeBookService` trzyma oceny i eksporty.
6. Zadbaj o niezmienniki [05][06]
   - Nie pozwalaj zapisać tego samego studenta dwa razy.
   - Nie pozwalaj wpisać oceny poza zakresem.
7. Pokaż polimorfizm i relacje [07]
   - Operuj na referencjach typu `User`, gdy ma to sens.
   - Oddziel odpowiedzialność kursu, grupy i serwisów.
8. Przygotuj scenariusz końcowy [03]
   - Utwórz kursy, zapisz studentów, wystaw oceny, wyeksportuj listę.
   - Dodaj `README.md` z opisem przepływu.

<a id="projekt-12-system-ankiet-i-formularzy"></a>
### Projekt 12: System ankiet i formularzy

**Poziom:** średni  
**Główny akcent OOP:** hierarchia pytań, walidacja odpowiedzi, agregacja wyników

**Cel projektu:** tworzyć ankiety z różnymi typami pytań i zbierać odpowiedzi respondentów.

#### Lista kontrolna
1. Ustal strukturę pakietów [02][04]
   - Przygotuj `survey.domain`, `survey.service`, `survey.app`, `survey.report`.
   - Dodaj klasę `Main` z przykładowa ankieta.
2. Zamodeluj pytania [08][07]
   - Utwórz abstrakcyjną klasę `Question`.
   - Zaimplementuj `SingleChoice`, `MultipleChoice`, `TextQuestion`.
3. Dodaj kontrakty [08]
   - Wprowadź `Answerable` z metodą walidacji odpowiedzi.
   - Dodaj `Exportable` dla wyników.
4. Dodaj modele danych [05b][06]
   - Utwórz `Survey`, `Response`, opcjonalnie `Answer`.
   - Waliduj odpowiedzi zgodnie z typem pytania.
5. Zaimplementuj serwisy [03]
   - `SurveyService` tworzy i konfiguruje ankiety.
   - `ResponseService` zapisuje odpowiedzi i liczy podstawowe statystyki.
6. Pokaż różnice zachowań [07]
   - Różne pytania walidują i formatują odpowiedzi inaczej.
   - Unikaj `instanceof` tam, gdzie może zadziałać polimorfizm.
7. Dodaj raportowanie [08]
   - Wygeneruj CSV z odpowiedziami albo podsumowaniem.
   - Dla pytań zamkniętych policz liczebności odpowiedzi.
8. Zbuduj demonstracje [03]
   - Utwórz ankietę z trzema typami pytań.
   - Dodaj kilka odpowiedzi i wydrukuj podsumowanie.

<a id="projekty-sport-i-rekreacja"></a>
## Projekty: sport i rekreacja

<a id="projekt-13-system-wypozyczalni-rowerow-miejskich"></a>
### Projekt 13: System wypożyczalni rowerów miejskich

**Poziom:** podstawowy  
**Główny akcent OOP:** interfejsy wypożyczania, hierarchia pojazdów, infrastruktura

**Cel projektu:** obsłużyć rowery klasyczne i elektryczne, stacje dokujące oraz wypożyczenia.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Użyj pakietów `citybike.domain`, `citybike.service`, `citybike.app`, `citybike.report`.
   - Dodaj klasę startowa `App`.
2. Stwórz abstrakcję pojazdu [08][07]
   - Utwórz klasę `Vehicle`.
   - Dodaj `Bike` i `EBike`.
3. Dodaj kontrakty [08]
   - Zdefiniuj `Rentable` oraz `Exportable`.
   - Niech pojazdy implementują oba interfejsy.
4. Zamodeluj infrastrukturę [05b][06]
   - Utwórz `DockingStation` z pojemnością i lista slotów.
   - Waliduj pojemność oraz duplikaty identyfikatorów.
5. Zaimplementuj logikę wypożyczeń [03]
   - `RentalService` wypożycza i przyjmuje zwroty.
   - `FleetService` raportuje stan floty.
6. Wykorzystaj polimorfizm [07]
   - Różne typy rowerów mogą mieć inne opisy albo inne ograniczenia.
   - Trzymaj je we wspólnej kolekcji typu bazowego.
7. Przygotuj demo [03]
   - Dodaj dwie stacje i kilka pojazdów.
   - Wykonaj sekwencję wypożyczeń, zwrotów i eksportu.
8. Rozszerz projekt [08]
   - Dodaj `Chargeable` dla rowerów elektrycznych.
   - Rozważ strategie naliczania opłat.

<a id="projekt-14-liga-uczelniana-terminarz-i-tabela-wynikow"></a>
### Projekt 14: Liga uczelniana: terminarz i tabela wyników

**Poziom:** średni  
**Główny akcent OOP:** kompozycja obiektów, strategie punktacji, raportowanie

**Cel projektu:** zarządzać zespołami, meczami, terminarzem i tabela ligowa.

#### Lista kontrolna
1. Zbuduj projekt [02][04]
   - Przygotuj `league.domain`, `league.service`, `league.schedule`, `league.app`.
   - Dodaj klasę `Main`.
2. Zamodeluj osoby i zespoly [08][07]
   - Stwórz abstrakcyjną klasę `Person`.
   - Dodaj `Player`, `Coach` i `Team`.
3. Zamodeluj dyscypliny i mecze [08][05b]
   - Dodaj abstrakcyjną klasę `Sport`.
   - Utwórz `Football`, `Basketball`, `Match`, `Score`.
4. Wprowadź kontrakty [08]
   - Dodaj `PlayableMatch` i `Exportable`.
   - Zadbaj, aby mecz mogł byc rozegrany i zapisany do raportu.
5. Zaimplementuj terminarz [03]
   - `Scheduler` generuje mecze round-robin.
   - `StandingService` wylicza tabele.
6. Dodaj warianty zasad [07][08]
   - Użyj `PointRule` jako strategii punktacji.
   - Różne sporty mogą przyznawać punkty inaczej.
7. Zbuduj scenariusz demo [03]
   - Utwórz co najmniej cztery drużyny.
   - Wygeneruj terminarz, rozegranie i raport końcowy.
8. Przygotuj opis projektu [02]
   - Dodaj diagram klas lub prosty rysunek zależności.
   - Opisz w `README.md`, gdzie jest os logiki rozgrywek.

<a id="projekt-15-aplikacja-trenera-personalnego"></a>
### Projekt 15: Aplikacja trenera personalnego

**Poziom:** podstawowy  
**Główny akcent OOP:** abstrakcja aktywności, dziennik treningów, kontrakty śledzenia

**Cel projektu:** przechowywać plany treningowe, aktywności i wpisy w dzienniku ćwiczeń.

#### Lista kontrolna
1. Przygotuj strukturę [02][04]
   - Użyj `trainer.domain`, `trainer.service`, `trainer.app`, `trainer.report`.
   - Dodaj klasę `App`.
2. Stwórz abstrakcję aktywności [08]
   - Dodaj `AbstractActivity` z polami `name`, `durationMin`.
   - Zaimplementuj `Running`, `Cycling`, `StrengthTraining`.
3. Dodaj interfejsy [08]
   - Wprowadź `Trackable` i `Exportable`.
   - Każda aktywność powinna umieć raportować swój stan.
4. Zamodeluj plan i log [05b][06]
   - Utwórz `WorkoutPlan` i `WorkoutLogEntry`.
   - Waliduj czas trwania i brak pustych danych.
5. Zaimplementuj serwisy [03]
   - `PlanService` zarządza aktywnościami w planie.
   - `LogService` zapisuje wykonane treningi.
6. Pokaż polimorfizm [07]
   - Różne aktywności inaczej liczą kalorie.
   - Zestawiaj je w jednej liście treningowej.
7. Przygotuj eksport [08]
   - Wygeneruj dziennik do CSV.
   - Dodaj raport tygodniowy z sumami czasu i kalorii.
8. Zademonstruj użycie [03]
   - Utwórz plan, wykonaj dwie aktywności, wydrukuj log.
   - Opisz wynik w `README.md`.

<a id="projekt-16-turniej-szachowy-parowanie-i-klasyfikacja"></a>
### Projekt 16: Turniej szachowy: parowanie i klasyfikacja

**Poziom:** średni  
**Główny akcent OOP:** strategie parowania i punktacji, organizacja rund

**Cel projektu:** obsłużyć zawodników, rundy, wyniki i klasyfikację turniejową.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Użyj `chess.domain`, `chess.tournament`, `chess.service`, `chess.app`.
   - Dodaj klasę `Main`.
2. Zamodeluj zawodników [08][07]
   - Stwórz abstrakcyjną klasę `Competitor`.
   - Dodaj konkretną klasę `Player`.
3. Dodaj modele turnieju [05b][06]
   - Utwórz `Match`, `Result`, `Standing`.
   - Waliduj rating i poprawne przypisanie zawodników.
4. Wprowadź interfejsy strategii [08]
   - Dodaj `PairingStrategy` i `ScoringRule`.
   - Przygotuj przynajmniej po jednej implementacji obu strategii.
5. Zaimplementuj serwis turniejowy [03]
   - `TournamentService` odpowiada za rundy i aktualizacje tabeli.
   - `ReportService` generuje raport z wyników.
6. Pokaż wymienność algorytmów [07][08]
   - Podmieniaj strategia parowania bez zmian w reszcie kodu.
   - To samo zrob dla zasad punktacji.
7. Przygotuj demo [03]
   - Dodaj ośmiu graczy i kilka rund.
   - Wydrukuj klasyfikację po każdej rundzie.
8. Rozszerz projekt [07]
   - Dodaj `Tournament` jako abstrakcyjną klasę bazową.
   - Rozważ `RoundRobinTournament` i `SwissTournament`.

<a id="projekt-17-wypozyczalnia-sprzetu-sportowego"></a>
### Projekt 17: Wypożyczalnia sprzętu sportowego

**Poziom:** podstawowy  
**Główny akcent OOP:** katalog sprzętu, wypożyczanie i kontrola stanu

**Cel projektu:** zarządzać stanem magazynowym różnego sprzętu sportowego.

#### Lista kontrolna
1. Zbuduj szkielet [02][04]
   - Przygotuj `sportsrent.domain`, `sportsrent.service`, `sportsrent.app`.
   - Dodaj klasę `App`.
2. Stwórz abstrakcję sprzętu [08][07]
   - Dodaj abstrakcyjną klasę `Equipment`.
   - Zaimplementuj `TennisRacket`, `SkiSet`, `FootballBall`.
3. Dodaj interfejsy [08]
   - Wprowadź `Rentable` i `Exportable`.
   - Opracuj jednolite API wypożyczenia i zwrotu.
4. Zamodeluj magazyn [05b][06]
   - Utwórz `Inventory`.
   - Waliduj identyfikatory, stan i duplikaty.
5. Zaimplementuj serwis wypożyczeń [03]
   - `RentalService` prowadzi prosty rejestr wypożyczeń.
   - `ReportingService` pokazuje sprzet dostępny i wypożyczony.
6. Pokaż zróżnicowane zachowania [07]
   - Różne typy sprzętu mogą mieć inne dane opisowe lub ograniczenia.
   - Użyj wspólnego typu bazowego do raportowania.
7. Przygotuj demo [03]
   - Dodaj kilka obiektów różnych klas.
   - Wypożycz, zwróć i wyeksportuj stan magazynu.
8. Dodaj opcjonalne rozszerzenie [08]
   - `Serviceable` dla przeglądów i napraw.
   - `PricingStrategy` dla stawek wypożyczenia.

<a id="projekt-18-analizator-statystyk-meczowych"></a>
### Projekt 18: Analizator statystyk meczowych

**Poziom:** średni  
**Główny akcent OOP:** źródła danych, raporty i kompozycja komponentow

**Cel projektu:** obliczac metryki meczowe i renderowac raporty na podstawie danych wczytanych z różnych źródeł.

#### Lista kontrolna
1. Przygotuj strukturę [02][04]
   - Użyj `matchstats.domain`, `matchstats.report`, `matchstats.source`, `matchstats.app`.
   - Dodaj klasę `Main`.
2. Zamodeluj dane [05b]
   - Dodaj `Team`, `Match`, opcjonalnie `PlayerStat`.
   - Zadbaj o czytelne modele wyniku i daty.
3. Dodaj abstrakcję źródła danych [08][07]
   - Stwórz abstrakcyjną klasę `DataSource`.
   - Zaimplementuj `InMemorySource`, opcjonalnie `CsvSource`.
4. Dodaj kontrakty raportów [08]
   - Wprowadź interfejs `Report`.
   - Dodaj `SummaryReport` i `TopScoringTeamsReport`.
5. Zaimplementuj serwis raportujący [03]
   - `ReportingService` laczy źródło danych z raportem.
   - Utrzymuj odpowiedzialności klas rozdzielone.
6. Pokaż polimorfizm [07]
   - Zmieniaj typ raportu bez przebudowy warstwy danych.
   - Zmieniaj źródło danych bez zmian w logice liczenia.
7. Przygotuj scenariusz demo [03]
   - Wygeneruj dwa raporty dla tego samego zestawu meczów.
   - Pokaż przykład wyjścia w `README.md`.
8. Rozszerz projekt [08]
   - Dodaj `Filter` dla zawężenia raportów do drużyny lub zakresu dat.
   - Dodaj `Formatter`, np. CSV i JSON.

<a id="projekty-transport-logistyka-i-infrastruktura"></a>
## Projekty: transport, logistyka i infrastruktura

<a id="projekt-19-transport-publiczny-linie-rozklady-i-pojazdy"></a>
### Projekt 19: Transport publiczny: linie, rozkłady i pojazdy

**Poziom:** średni  
**Główny akcent OOP:** modelowanie infrastruktury, rozkłady, różne typy pojazdów

**Cel projektu:** obsłużyć linie autobusowe i tramwajowe, przystanki, kursy oraz raporty.

#### Lista kontrolna
1. Przygotuj pakiety [02][04]
   - Użyj `transit.domain`, `transit.schedule`, `transit.service`, `transit.app`.
   - Dodaj klasę `Main`.
2. Stwórz abstrakcję pojazdu [08][07]
   - Dodaj abstrakcyjną klasę `Vehicle`.
   - Zaimplementuj `Bus` i `Tram`.
3. Zamodeluj linie i rozkład [05b][06]
   - Dodaj `Line`, `Stop`, `Timetable`.
   - Waliduj nazwy i brak duplikatów przystanków w linii.
4. Wprowadź kontrakty [08]
   - Dodaj `Exportable` do raportów.
   - Dodaj `Trackable` dla aktywnego kursu.
5. Zaimplementuj serwisy [03]
   - `RouteService` obsługuje przebieg linii.
   - `ReportingService` generuje rozkłady i raporty kursow.
6. Pokaż warianty zachowań [07]
   - Różne pojazdy mogą mieć inna pojemność i inne ograniczenia.
   - Wykorzystaj to w raportach lub logice kursu.
7. Przygotuj demo [03]
   - Dodaj dwie linie z różnymi typami pojazdów.
   - Wydrukuj rozkład i prosty raport kursu.
8. Rozszerz projekt [08]
   - Dodaj strategie czasu przejazdu dla godzin szczytu i poza szczytem.
   - Rozważ prosty generator opóźnień.

<a id="projekt-20-firma-kurierska-paczki-trasy-i-statusy"></a>
### Projekt 20: Firma kurierska: paczki, trasy i statusy

**Poziom:** średni  
**Główny akcent OOP:** stany przesyłek, śledzenie procesu, warianty paczek

**Cel projektu:** zarządzać paczkami, trasami i zmianami statusu przesyłek.

#### Lista kontrolna
1. Zbuduj projekt [02][04]
   - Przygotuj `courier.domain`, `courier.service`, `courier.app`, `courier.report`.
   - Dodaj klasę `Main`.
2. Zamodeluj przesyłkę [08][07]
   - Stwórz abstrakcyjną klasę `Parcel`.
   - Dodaj `StandardParcel` i `FragileParcel`.
3. Dodaj modele pomocnicze [05b][06]
   - Utwórz `Address`, `Route`, `Status`.
   - Waliduj wage, gabaryty i dane adresowe.
4. Wprowadź interfejsy [08]
   - Dodaj `Trackable` i `Exportable`.
   - Zadbaj o spójne API historii zdarzeń.
5. Zaimplementuj serwisy [03]
   - `RoutingService` przypisuje paczki do tras.
   - `TrackingService` zmienia statusy i zapisuje historie.
6. Pokaż polimorfizm [07]
   - Różne paczki mogą wymagać innych kroków logistycznych.
   - Zaszyj te różnice w klasach, nie w rozbudowanych `if-ach`.
7. Przygotuj raporty [08]
   - Generuj historie statusów do CSV.
   - Dodaj raport zbiorczy per trasa.
8. Zademonstruj działanie [03]
   - Utwórz kilka paczek i symuluj ich drogę do doręczenia.
   - Dodaj przykład outputu do `README.md`.

<a id="projekty-przyroda-monitoring-i-symulacje"></a>
## Projekty: przyroda, monitoring i symulacje

<a id="projekt-21-symulator-zoo"></a>
### Projekt 21: Symulator zoo

**Poziom:** podstawowy  
**Główny akcent OOP:** dziedziczenie, interfejsy zdolności, symulacja zachowań

**Cel projektu:** zasymulować podstawowe zachowania różnych zwierząt i ich aktywności.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Użyj `zoo.domain`, `zoo.service`, `zoo.app`, `zoo.report`.
   - Dodaj klasę `Simulator`.
2. Stwórz abstrakcję zwierzęcia [08][05b]
   - Dodaj abstrakcyjną klasę `Animal`.
   - Umieść w niej pola `name`, `age` i metody wspólne.
3. Rozbuduj hierarchie [07][06]
   - Dodaj `Mammal`, `Bird`, `Reptile`.
   - Dodaj konkretne klasy, np. `Lion`, `Penguin`, `Iguana`.
4. Wprowadź interfejsy zdolności [08]
   - Dodaj `Flyable`, `Swimmable`, `Trainable`.
   - Wybrane zwierzęta powinny implementować tylko sensowne kontrakty.
5. Zaimplementuj silnik symulacji [03]
   - `SimulationService` wykonuje tury aktywności.
   - `ReportingService` zapisuje wyniki aktywności.
6. Pokaż polimorfizm [07]
   - Uruchamiaj wspólne czynnosci przez typ bazowy `Animal`.
   - Zdolności wywołuj przez typy interfejsowe.
7. Zadbaj o walidację [05][06]
   - Pilnuj wieku, nazw i niezmienników stanu.
   - Zwracaj niemodyfikowalne widoki kolekcji aktywności.
8. Przygotuj demo [03]
   - Dodaj kilka zwierząt o różnych zdolnościach.
   - Uruchom kilka tur i wydrukuj raport.

<a id="projekt-22-monitor-stacji-pogodowej"></a>
### Projekt 22: Monitor stacji pogodowej

**Poziom:** rozszerzony  
**Główny akcent OOP:** interfejsy czujników, wyjątki, głęboka kopia

**Cel projektu:** gromadzić odczyty z czujników i reagować na nieprawidłowe pomiary.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Użyj `weather.domain`, `weather.service`, `weather.app`.
   - Dodaj klasę `Main`.
2. Zdefiniuj kontrakt czujnika [08]
   - Dodaj interfejs `Sensor` z metoda `readValue()`.
   - Rozważ osobny interfejs `Maintainable`.
3. Dodaj implementacje czujników [07]
   - Zaimplementuj `TemperatureSensor` i `HumiditySensor`.
   - Każdy czujnik powinien mieć identyfikator i lokalizację.
4. Wprowadź obsługę błędów [11]
   - Dodaj `SensorMalfunctionException`.
   - Rzucaj wyjątek dla nierealistycznych odczytów.
5. Zamodeluj agregacje [05b][12]
   - Utwórz `WeatherStation` przechowującą listę czujników.
   - Zadeklaruj czy stacja jest właścicielem tych obiektów.
6. Dodaj kopię zapasową [12]
   - Zaimplementuj głęboka kopie stacji wraz z czujnikami.
   - Wyjaśnij w kodzie, co jest kopiowane i dlaczego.
7. Zaimplementuj przepływ uruchomieniowy [03]
   - Wykonaj serie odczytów i obsłuż wyjątki.
   - Zapisz proste podsumowanie odczytów.
8. Dodaj element weryfikacji [09]
   - Przetestuj, czy kopia stacji nie współdzieli kolekcji z oryginałem.
   - Sprawdź zachowanie wyjątku dla skrajnego pomiaru.

<a id="projekt-23-siec-stacji-meteo"></a>
### Projekt 23: Sieć stacji meteo

**Poziom:** średni  
**Główny akcent OOP:** klasy pomiarów, dostawcy danych, agregacja raportów

**Cel projektu:** zarządzać wieloma stacjami i zestawieniami pomiarów różnych typow.

#### Lista kontrolna
1. Zorganizuj projekt [02][04]
   - Użyj `meteo.domain`, `meteo.source`, `meteo.report`, `meteo.app`.
   - Dodaj klasę `Main`.
2. Zamodeluj pomiary [08][07]
   - Stwórz abstrakcyjną klasę `Measurement`.
   - Dodaj `Temperature`, `Humidity`, `WindSpeed`.
3. Dodaj modele stacji i jednostek [05b][06]
   - Utwórz `Station` oraz enum `Unit`.
   - Waliduj zakresy i poprawne jednostki.
4. Wprowadź kontrakty [08]
   - Dodaj `DataProvider` i `Report`.
   - Przygotuj `InMemoryProvider` jako bazowa implementacje.
5. Zaimplementuj raportowanie [03]
   - `ReportingService` liczy min, max i średnia.
   - Wygeneruj co najmniej dwa różne raporty.
6. Pokaż polimorfizm [07]
   - Różne typy pomiarów powinny inaczej interpretować wartości.
   - Różne raporty powinny byc wymienne bez zmian w źródle danych.
7. Przygotuj scenariusz demonstracyjny [03]
   - Dodaj dwie stacje i zestaw różnych pomiarów.
   - Wydrukuj raport stacji i raport zbiorczy.
8. Rozszerz projekt [08]
   - Dodaj `Formatter` do CSV i JSON.
   - Rozważ abstrakcyjny `Aggregator` dla raportów dobowych i tygodniowych.

<a id="projekt-24-katalog-roslin-i-stanowisk"></a>
### Projekt 24: Katalog roślin i stanowisk

**Poziom:** rozszerzony  
**Główny akcent OOP:** relacje obiektów, wyjątki domenowe, porównywanie obiektów

**Cel projektu:** zarządzać roślinami, ich stanowiskami oraz podstawowa logika pielęgnacji.

#### Lista kontrolna
1. Przygotuj strukturę [02][04]
   - Użyj `garden.model`, `garden.logic`, `garden.app`.
   - Dodaj klasę `Main`.
2. Zamodeluj relacje [12][05]
   - Utwórz klasę `Location`.
   - Niech `Plant` posiada referencje do obiektu `Location`.
3. Dodaj hierarchie roślin [08][07]
   - Stwórz abstrakcyjną klasę `Plant`.
   - Dodaj `Tree` i `Flower`.
4. Wprowadź kontrakty [08]
   - Dodaj interfejs `Waterable`.
   - Rozważ prosty interfejs `Inspectable`.
5. Dodaj walidację i wyjątki [06][11]
   - Rzucaj `PlantDeadException` dla niepoprawnej pielęgnacji.
   - Waliduj gatunek, stan i lokalizację.
6. Zaimplementuj logikę porównywania [12]
   - Nadpisz `equals()` dla porównania roślin według przyjętej tożsamości.
   - Zdecyduj, czy lokalizacja jest częścią równości obiektu.
7. Dodaj warstwę logiki ogrodu [03][10]
   - `GardenService` odpowiada za podlewanie, przesadzanie i raportowanie.
   - Rozdziel model od logiki biznesowej.
8. Przygotuj demo [03]
   - Utwórz kilka roślin na różnych stanowiskach.
   - Pokaż scenariusz poprawnej i błędnej pielęgnacji.

<a id="wspolne-wskazowki-do-wszystkich-projektow"></a>
## Wspólne wskazówki do wszystkich projektów

1. Zacznij od prostego diagramu klas i interfejsów [01]
   - Nawet szkic na kartce lub np. w `draw.io` pozwoli szybciej wychwycić złe zależności.
2. Pilnuj odpowiedzialności klas [10]
   - Encje domenowe nie powinny przejmować roli całej aplikacji.
   - Serwisy powinny spinać przepływ, a nie przechowywać przypadkowy stan.
3. Projektuj najpierw kontrakty, potem implementacje [08]
   - Interfejs lub klasa abstrakcyjna powinny wynikać z rzeczywistej wspólnej odpowiedzialności.
4. Waliduj dane w konstruktorach i metodach [06][11]
   - Nie dopuszczaj obiektów w błędnym stanie.
5. Chroń kolekcje i stan wewnętrzny [05][12]
   - Zwracaj kopie albo niemodyfikowalne widoki.
   - Nie przekazuj referencji, jeśli użytkownik klasy nie powinien zmieniać stanu.
6. Pokaż polimorfizm w warstwie klienta [07][08]
   - Trzymaj referencje w typach bazowych lub interfejsach.
   - Unikaj nadmiarowych `if`, `switch` i `instanceof`, gdy zachowanie można przenieść do klas.
7. Dodaj małe, czytelne demo [03]
   - Jeden sensowny scenariusz w `main` jest lepszy niż dużo przypadkowych obiektów.
8. Dokumentuj założenia [02]
   - W `README.md` opisz cel projektu, strukturę pakietów i sposób uruchomienia.
9. Korzystaj z Git od początku [02]
   - Małe commity po kolejnych punktach checklisty bardzo ułatwiają prace i cofanie zmian.
