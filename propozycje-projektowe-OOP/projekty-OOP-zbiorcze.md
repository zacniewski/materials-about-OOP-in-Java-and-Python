# Zbiorczy zestaw propozycji projektowych OOP

Ten dokument scala wszystkie propozycje projektowe z folderu `propozycje-projektowe-OOP` do jednej, ujednoliconej wersji. Projekty zostały uporządkowane tematycznie, dopracowane językowo i wyraźniej zróżnicowane pod względem domeny, akcentu OOP oraz poziomu trudności.

## Spis treści

1. [Jak korzystać z tego dokumentu](#jak-korzystac-z-tego-dokumentu)
2. [Legenda zakresu materiału](#legenda-zakresu-materialu)
3. [Projekty: administracja i usługi](#projekty-administracja-i-uslugi)
   1. [System rezerwacji pokoi w hostelu](#projekt-1-system-rezerwacji-pokoi-w-hostelu)
   2. [System rezerwacji wizyt](#projekt-2-system-rezerwacji-wizyt)
   3. [Kino: rezerwacja i sprzedaż biletów](#projekt-3-kino-rezerwacja-i-sprzedaz-biletow)
   4. [Restauracja: stoliki, zamowienia i rachunki](#projekt-4-restauracja-stoliki-zamowienia-i-rachunki)
4. [Projekty: handel, media i finanse](#projekty-handel-media-i-finanse)
   1. [Menedzer biblioteki mediow](#projekt-5-menedzer-biblioteki-mediow)
   2. [Mini sklep internetowy](#projekt-6-mini-sklep-internetowy)
   3. [Mini bank: konta, transakcje i karty](#projekt-7-mini-bank-konta-transakcje-i-karty)
   4. [Kalkulator wydatkow domowych](#projekt-8-kalkulator-wydatkow-domowych)
5. [Projekty: edukacja i produktywnosc](#projekty-edukacja-i-produktywnosc)
   1. [Planer zadan i raportow](#projekt-9-planer-zadan-i-raportow)
   2. [Mini system TODO z wyjatkami i kopiowaniem obiektow](#projekt-10-mini-system-todo-z-wyjatkami-i-kopiowaniem-obiektow)
   3. [System dziekanatu: zapisy na przedmioty](#projekt-11-system-dziekanatu-zapisy-na-przedmioty)
   4. [System ankiet i formularzy](#projekt-12-system-ankiet-i-formularzy)
6. [Projekty: sport i rekreacja](#projekty-sport-i-rekreacja)
   1. [System wypozyczalni rowerow miejskich](#projekt-13-system-wypozyczalni-rowerow-miejskich)
   2. [Liga uczelniana: terminarz i tabela wynikow](#projekt-14-liga-uczelniana-terminarz-i-tabela-wynikow)
   3. [Aplikacja trenera personalnego](#projekt-15-aplikacja-trenera-personalnego)
   4. [Turniej szachowy: parowanie i klasyfikacja](#projekt-16-turniej-szachowy-parowanie-i-klasyfikacja)
   5. [Wypozyczalnia sprzetu sportowego](#projekt-17-wypozyczalnia-sprzetu-sportowego)
   6. [Analizator statystyk meczowych](#projekt-18-analizator-statystyk-meczowych)
7. [Projekty: transport, logistyka i infrastruktura](#projekty-transport-logistyka-i-infrastruktura)
   1. [Transport publiczny: linie, rozklady i pojazdy](#projekt-19-transport-publiczny-linie-rozklady-i-pojazdy)
   2. [Firma kurierska: paczki, trasy i statusy](#projekt-20-firma-kurierska-paczki-trasy-i-statusy)
8. [Projekty: przyroda, monitoring i symulacje](#projekty-przyroda-monitoring-i-symulacje)
   1. [Symulator zoo](#projekt-21-symulator-zoo)
   2. [Monitor stacji pogodowej](#projekt-22-monitor-stacji-pogodowej)
   3. [Siec stacji meteo](#projekt-23-siec-stacji-meteo)
   4. [Katalog roslin i stanowisk](#projekt-24-katalog-roslin-i-stanowisk)
9. [Wspolne wskazowki do wszystkich projektow](#wspolne-wskazowki-do-wszystkich-projektow)

## Jak korzystac z tego dokumentu

- Wybierz projekt przede wszystkim po domenie, a nie tylko po nazwie.
- Sprawdz sekcje `Glowny akcent OOP`, bo to ona najlepiej pokazuje, czego dany temat uczy.
- Traktuj checklisty jako kolejnosc realizacji, nie jako sztywny kontrakt.
- Jesli projekt ma status `rozszerzony`, zaklada wyjscie poza podstawy i dotyka tez testow, wyjatkow, SOLID albo kopiowania obiektow.

## Legenda zakresu materialu

- [01] Paradygmaty obiektowosci
- [02] Srodowisko programisty OOP
- [03] Klasy i metoda `main`
- [04] Pakiety i importy
- [05] Modyfikatory dostepu
- [05b] Pola klas
- [06] Konstruktory
- [07] Dziedziczenie i polimorfizm
- [08] Interfejsy i klasy abstrakcyjne
- [09] Testowanie kodu
- [10] SOLID
- [11] Wyjatki
- [12] Referencje do obiektow i kopiowanie

## Projekty: administracja i uslugi

### Projekt 1: System rezerwacji pokoi w hostelu

**Poziom:** podstawowy  
**Glowny akcent OOP:** klasy abstrakcyjne, interfejsy, enkapsulacja

**Cel projektu:** zbudowac prosty system do zarzadzania pokojami, goscmi i rezerwacjami.

#### Lista kontrolna
1. Przygotuj projekt i pakiety [02][04]
   - Utworz pakiety `hostel.domain`, `hostel.service`, `hostel.app`, `hostel.report`.
   - Dodaj klase `hostel.app.Main` z przykladowym scenariuszem uruchomieniowym.
2. Zamodeluj pokoj jako abstrakcje [08][05][05b]
   - Stworz abstrakcyjna klase `Room` z polami `id`, `beds`, `occupied`.
   - Dodaj metody wspolne: `occupy()`, `release()`, `isOccupied()`, `description()`.
3. Dodaj konkretne typy pokoi [07][06]
   - Zaimplementuj `SingleRoom`, `DormRoom`, `FamilyRoom`.
   - Waliduj liczbe lozek i ograniczenia pojemnosci w konstruktorach.
4. Zdefiniuj kontrakty domenowe [08]
   - Dodaj interfejsy `Bookable` i `Exportable`.
   - Niech kazdy pokoj implementuje eksport do CSV i logike dostepnosci.
5. Wprowadz encje goscia i rezerwacji [03][05]
   - Utworz klasy `Guest` i `Reservation`.
   - Zadbaj o walidacje danych, np. pustych nazw i niepoprawnych dat.
6. Zaimplementuj warstwe serwisowa [03][07]
   - `ReservationService` odpowiada za tworzenie i anulowanie rezerwacji.
   - `ReportingService` generuje raport pokoi oraz raport rezerwacji.
7. Pokaz polimorfizm w praktyce [07][08]
   - Przechowuj pokoje w kolekcji typu `List<Room>` albo `List<Bookable>`.
   - W `Main` obsluz kilka roznych pokoi jednym przeplywem kodu.
8. Dopracuj final projektu [02]
   - Dodaj `README.md` z opisem pakietow i przykladowym outputem.
   - Przygotuj repozytorium Git i pierwszy tag wersji demo.

### Projekt 2: System rezerwacji wizyt

**Poziom:** podstawowy  
**Glowny akcent OOP:** relacje miedzy obiektami, logika terminow, kolekcje

**Cel projektu:** obsluzyc kalendarz specjalistow, klientow i rezerwacje slotow czasowych.

#### Lista kontrolna
1. Zdefiniuj strukture pakietow [02][04]
   - Uzyj `booking.domain`, `booking.service`, `booking.app`, `booking.util`.
   - Przygotuj klase `Main` pokazujaca przeplyw od wolnego terminu do rezerwacji.
2. Utworz model osob [08][07]
   - Dodaj abstrakcyjna klase `Person`.
   - Rozszerz ja klasami `Client` i `Specialist`.
3. Zamodeluj slot czasowy [05b][06]
   - Dodaj klase `TimeSlot` z polami `start`, `end`, `occupied`.
   - Waliduj, ze `end` jest pozniejsze niz `start`.
4. Dodaj kontrakt rezerwacyjny [08]
   - Zdefiniuj interfejs `Schedulable`.
   - Niech `Specialist` udostepnia liste wolnych slotow i mozliwosc rezerwacji.
5. Zaimplementuj logike biznesowa [03]
   - `BookingService` sprawdza kolizje terminow i tworzy wpisy rezerwacji.
   - `ScheduleService` generuje kalendarz dnia lub tygodnia.
6. Zadbaj o kapsulkowanie [05]
   - Kolekcje slotow zwracaj jako kopie albo widoki niemodyfikowalne.
   - Ukryj szczegoly implementacyjne poza publicznym API.
7. Przygotuj eksport i raporty [08]
   - Dodaj `Exportable` do generowania grafikow lub list rezerwacji.
   - Przygotuj prosty raport tekstowy i CSV.
8. Rozszerz projekt o dodatkowy wariant [07][08]
   - Dodaj typy specjalistow z odmienna dlugoscia wizyt.
   - Pokaz, jak polimorfizm upraszcza planowanie kalendarza.

### Projekt 3: Kino: rezerwacja i sprzedaz biletow

**Poziom:** podstawowy  
**Glowny akcent OOP:** modelowanie stanu i wariantow sal

**Cel projektu:** obsluzyc sale, seanse, miejsca oraz zakup lub anulowanie biletow.

#### Lista kontrolna
1. Zorganizuj projekt [02][04]
   - Utworz pakiety `cinema.domain`, `cinema.service`, `cinema.app`, `cinema.report`.
   - Dodaj klase startowa z przykladowym dniem pracy kina.
2. Stworz abstrakcje sali [08]
   - Utworz abstrakcyjna klase `Auditorium`.
   - Przygotuj podklasy `StandardAuditorium` i `IMAXAuditorium`.
3. Zamodeluj miejsce i seans [05b][06]
   - Dodaj klasy `Seat` oraz `Show`.
   - Waliduj numery rzedow, kolumn i czas rozpoczecia.
4. Wprowadz interfejsy [08]
   - Dodaj `Bookable` dla miejsca lub biletu.
   - Dodaj `Exportable` dla raportow sprzedazy.
5. Zaimplementuj serwisy [03]
   - `SeatMapService` tworzy uklad miejsc.
   - `TicketService` obsluguje rezerwacje, anulacje i sprzedaz.
6. Pokaz zmiennosc zachowania [07]
   - Rozne typy sal moga miec inny uklad miejsc albo inna polityke cen.
   - Zastosuj polimorfizm zamiast instrukcji warunkowych rozrzuconych po kodzie.
7. Przygotuj scenariusz demo [03]
   - Utworz dwa seanse w roznych salach.
   - Zarezerwuj kilka miejsc i wygeneruj raport dzienny.
8. Domknij projekt dokumentacja [02]
   - Dodaj przykladowa mape miejsc w `README.md`.
   - Opisz architekture klas i zalozenia modelu.

### Projekt 4: Restauracja: stoliki, zamowienia i rachunki

**Poziom:** podstawowy  
**Glowny akcent OOP:** kompozycja, warianty pozycji menu, odpowiedzialnosc klas

**Cel projektu:** zarzadzac menu, stolikami, zamowieniami i drukowaniem rachunkow.

#### Lista kontrolna
1. Przygotuj strukture pakietow [02][04]
   - Uzyj `restaurant.domain`, `restaurant.order`, `restaurant.service`, `restaurant.app`.
   - Dodaj klase `Main` pokazujaca pelny cykl obslugi goscia.
2. Zamodeluj pozycje menu [08][07]
   - Stworz abstrakcyjna klase `MenuItem`.
   - Dodaj klasy `Dish`, `Drink`, opcjonalnie `Dessert`.
3. Dodaj encje stolika i zamowienia [05b][06]
   - Klasa `Table` przechowuje numer i liczbe miejsc.
   - Klasa `Order` przechowuje pozycje, status i powiazanie ze stolikiem.
4. Wprowadz kontrakty [08]
   - Dodaj interfejsy `Billable` i `Printable`.
   - Rachunek powinien byc tworzony z obiektow, a nie z luźnych stringow.
5. Zaimplementuj serwisy [03]
   - `MenuService` zarzadza lista pozycji.
   - `OrderService` dodaje pozycje, zamyka rachunek i liczy sume.
6. Zadbaj o spojnosc stanu [05][06]
   - Nie pozwalaj dodawac pozycji do zamknietego zamowienia.
   - Waliduj cene, ilosc i numer stolika.
7. Wykorzystaj polimorfizm [07][08]
   - Rozne typy pozycji menu moga inaczej liczyc cene brutto albo sposob prezentacji.
   - Dodaj rozne typy zamowien, np. `DineInOrder` i `TakeawayOrder`.
8. Przygotuj demonstracje i eksport [03][08]
   - Wydrukuj dwa rachunki dla roznych scenariuszy.
   - Dodaj eksport rachunku do CSV albo plain text.

## Projekty: handel, media i finanse

### Projekt 5: Menedzer biblioteki mediow

**Poziom:** podstawowy  
**Glowny akcent OOP:** dziedziczenie i wspolne API dla roznych typow mediow

**Cel projektu:** zarzadzac kolekcja ksiazek, filmow i albumow muzycznych.

#### Lista kontrolna
1. Zbuduj szkielet projektu [02][04]
   - Utworz pakiety `media.domain`, `media.service`, `media.export`, `media.app`.
   - Dodaj klase startowa `App`.
2. Utworz abstrakcyjny model medium [08][05b]
   - Stworz klase `MediaItem` z polami `id`, `title`, `year`.
   - Dodaj metody `basicInfo()`, `details()` i `age()`.
3. Dodaj konkretne typy [07][06]
   - Zaimplementuj `Book`, `Movie`, `Album`.
   - Waliduj tytul, rok oraz pola specyficzne, np. liczbe stron.
4. Zdefiniuj interfejsy [08]
   - Dodaj `Filterable` do prostego wyszukiwania.
   - Dodaj `Exportable` do CSV i opcjonalnie JSON.
5. Wprowadz repozytorium i serwisy [03][05]
   - `MediaRepository` trzyma dane w pamieci.
   - `LibraryService` obsluguje wyszukiwanie, filtrowanie i eksport.
6. Pokaz przewage polimorfizmu [07]
   - Trzymaj media w jednej kolekcji typu `List<MediaItem>`.
   - Wywoluj `details()` bez sprawdzania typu obiektu.
7. Przygotuj warstwe demonstracyjna [03]
   - Dodaj kilka mediow, wyszukaj po frazie, wygeneruj raport.
   - Pokaz eksport w dwoch formatach, jesli wdrozysz oba.
8. Rozszerz projekt [08]
   - Dodaj `Rateable` dla ocen.
   - Rozwaz abstrakcyjna klase `RatedMediaItem`.

### Projekt 6: Mini sklep internetowy

**Poziom:** sredni  
**Glowny akcent OOP:** strategie rabatowe, platnosci, dokumenty sprzedazy

**Cel projektu:** zbudowac uproszczony sklep z koszykiem, produktami, rabatami i platnosciami.

#### Lista kontrolna
1. Przygotuj moduly projektu [02][04]
   - Uzyj pakietow `shop.domain`, `shop.pricing`, `shop.payment`, `shop.invoice`, `shop.app`.
   - Dodaj klase `Main` z przykladowym zamowieniem.
2. Zaprojektuj abstrakcje produktu [08][07]
   - Stworz abstrakcyjna klase `Product`.
   - Dodaj konkretne typy `Book`, `Food`, `Electronic`.
3. Utworz modele koszyka [05b][06]
   - Dodaj `CartItem` i `Cart`.
   - Waliduj ilosc, cene netto i stawke podatku.
4. Wprowadz polityki rabatowe [08]
   - Dodaj interfejs `DiscountPolicy`.
   - Zaimplementuj `NoDiscount`, `PercentageDiscount`, `ThresholdFixedDiscount`.
5. Dodaj platnosci jako kontrakty [08]
   - Zdefiniuj interfejs `Payable`.
   - Przygotuj klasy `CardPayment`, `BlikPayment`, `CashOnDelivery`.
6. Zbuduj warstwe dokumentow [03][08]
   - Klasa `Invoice` implementuje `Printable`.
   - Opcjonalnie dodaj `Exportable` do CSV.
7. Polacz wszystko serwisami [03][07]
   - `CartService` liczy sumy i naklada rabaty.
   - `PaymentService` steruje autoryzacja i finalizacja zamowienia.
8. Zademonstruj rozne scenariusze [03]
   - Pokaz dwa koszyki z roznymi politykami rabatowymi.
   - Oplac zamowienie dwiema metodami platnosci.
9. Uporzadkuj projekt [02]
   - Dodaj przyklady danych testowych i instrukcje uruchomienia.
   - Opisz w `README.md`, gdzie zastosowano polimorfizm i interfejsy.

### Projekt 7: Mini bank: konta, transakcje i karty

**Poziom:** sredni  
**Glowny akcent OOP:** kontrola stanu, reguly biznesowe, strategie oplat i odsetek

**Cel projektu:** obsluzyc konta bankowe, przelewy, limity kart oraz historie operacji.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Uzyj pakietow `bank.domain`, `bank.service`, `bank.report`, `bank.app`.
   - Dodaj klase `Main` z kilkoma klientami i kontami.
2. Stworz abstrakcje konta [08][07]
   - Dodaj abstrakcyjna klase `Account`.
   - Zaimplementuj `CheckingAccount` i `SavingsAccount`.
3. Dodaj kontrakty operacyjne [08]
   - Zdefiniuj interfejs `Transactable`.
   - Dodaj `Exportable` dla historii operacji.
4. Zamodeluj transakcje i karty [05b][06]
   - Utworz klasy `Transaction`, `Card`, `DebitCard`, `VirtualCard`.
   - Waliduj kwoty, saldo i limity dzienne.
5. Zaimplementuj serwisy [03]
   - `AccountService` obsluguje otwarcie kont i wplaty.
   - `TransferService` pilnuje przelewow i niezmiennikow salda.
6. Pokaz warianty zachowan [07]
   - Rozne typy kont inaczej naliczaja odsetki albo oplaty.
   - Rozne typy kart moga miec odmienne limity lub zasady autoryzacji.
7. Przygotuj raportowanie [08]
   - Generuj wyciag tekstowy albo CSV.
   - Dodaj zestawienie operacji per konto.
8. Rozszerz projekt [08]
   - Dodaj `FeePolicy` i `InterestPolicy`.
   - Rozwaz prosty `FraudDetector` jako abstrakcyjny komponent.

### Projekt 8: Kalkulator wydatkow domowych

**Poziom:** rozszerzony  
**Glowny akcent OOP:** odpowiedzialnosc klas, podstawy SOLID, walidacja i testy

**Cel projektu:** rejestrowac wydatki, dzielic je na kategorie i generowac podstawowe raporty.

#### Lista kontrolna
1. Przygotuj prosta architekture [02][04][10]
   - Podziel kod na `finance.core`, `finance.report`, `finance.app`.
   - Rozdziel przechowywanie danych od generowania raportow.
2. Zamodeluj wydatki [08][07]
   - Utworz abstrakcyjna klase `Expense`.
   - Dodaj `FoodExpense`, `EntertainmentExpense`, `TransportExpense`.
3. Dodaj walidacje i wyjatki [06][11]
   - Nie pozwalaj na ujemne kwoty.
   - Rzucaj `InvalidAmountException` lub `IllegalArgumentException`.
4. Wprowadz manager i generator raportow [03][10]
   - `ExpenseManager` zarzadza lista wydatkow.
   - `ReportGenerator` odpowiada tylko za podsumowania i prezentacje.
5. Pokaz polimorfizm [07]
   - Rozne typy wydatkow moga inaczej liczyc podatek lub klasyfikacje.
   - Utrzymuj kolekcje typu `List<Expense>`.
6. Zadbaj o bezpieczenstwo referencji [05][12]
   - Nie udostepniaj wewnetrznej listy do swobodnej modyfikacji.
   - Zwracaj kopie albo widoki niemodyfikowalne.
7. Dodaj test podstawowy [09]
   - Napisz jeden test liczacy sume wydatkow.
   - Doloz drugi test sprawdzajacy rzucanie wyjatku dla blednej kwoty.
8. Przygotuj wersje demo [03]
   - W `Main` utworz kilka wydatkow i wydrukuj raport miesieczny.
   - Opisz przyklad danych w `README.md`.

## Projekty: edukacja i produktywnosc

### Projekt 9: Planer zadan i raportow

**Poziom:** podstawowy  
**Glowny akcent OOP:** filtracja, strategie sortowania, modelowanie zadan

**Cel projektu:** zarzadzac zadaniami z kategoriami, priorytetami i terminami.

#### Lista kontrolna
1. Zbuduj strukture projektu [02][04]
   - Uzyj pakietow `planner.domain`, `planner.service`, `planner.export`, `planner.app`.
   - Dodaj klase `Main`.
2. Zamodeluj zadanie [08][05b]
   - Utworz abstrakcyjna klase `Task`.
   - Dodaj podklasy `WorkTask` i `PersonalTask`.
3. Dodaj walidacje konstruktorow [06]
   - Sprawdz `id`, `title` i `dueDate`.
   - Ustal niezmienniki dla obiektow zadania.
4. Zdefiniuj kontrakty [08]
   - Dodaj `Exportable` i interfejs `Filter`.
   - Rozwaz osobny interfejs `Printable`.
5. Zaimplementuj repozytorium i serwisy [03][05]
   - `TaskRepository` trzyma zadania w pamieci.
   - `TaskService` obsluguje CRUD, filtrowanie i sortowanie.
6. Pokaz wzorzec strategii [08][07]
   - Dodaj rozne implementacje filtrow, np. po priorytecie i terminie.
   - Dodaj strategie sortowania, np. po dacie i po nazwie.
7. Wygeneruj raporty [03][08]
   - Dodaj eksport listy zadan do CSV.
   - Wydrukuj raport zadan opoznionych i pilnych.
8. Przygotuj demonstracje [03]
   - Dodaj kilka zadan roznego typu.
   - Pokaz przefiltrowanie, posortowanie i eksport.

### Projekt 10: Mini system TODO z wyjatkami i kopiowaniem obiektow

**Poziom:** rozszerzony  
**Glowny akcent OOP:** wyjatki, `equals/hashCode`, kopiowanie i referencje

**Cel projektu:** stworzyc mala aplikacje TODO, ktora poza podstawami cwiczy tez bezpieczne operowanie obiektami.

#### Lista kontrolna
1. Przygotuj minimalna architekture [02][04]
   - Uzyj pakietow `todo.model`, `todo.service`, `todo.app`.
   - Dodaj klase `Main` i prosty zestaw danych testowych.
2. Zamodeluj zadanie [08][05]
   - Dodaj abstrakcyjna klase `Task` z polami `id`, `description`, `done`.
   - Ukryj pola i wystaw tylko potrzebne API.
3. Dodaj specjalizacje [07][06]
   - Zaimplementuj `WorkTask` i `PersonalTask`.
   - Waliduj opis i termin w konstruktorach.
4. Wprowadz wlasne wyjatki [11]
   - Dodaj `TaskValidationException`.
   - Obsluz sytuacje blednego terminu lub pustego opisu.
5. Zaimplementuj kopiowanie i porownywanie [12]
   - Dodaj konstruktor kopiujacy albo metode `copy()`.
   - Nadpisz `equals()` i `hashCode()` zgodnie z przyjeta tozsamoscia zadania.
6. Zaimplementuj warstwe serwisowa [03]
   - `TaskService` dodaje, usuwa i oznacza zadania jako wykonane.
   - Zadbaj o to, by serwis nie wystawial wewnetrznej listy do modyfikacji.
7. Dodaj prosty przeplyw bledow [03][11]
   - W `Main` obsluz wyjatki blokiem `try-catch`.
   - Pokaz scenariusz poprawny i scenariusz bledny.
8. Rozszerz projekt testem lub porownaniem [09][12]
   - Sprawdz, czy kopia zadania zachowuje oczekiwane dane.
   - Zweryfikuj zachowanie `equals()` dla dwoch obiektow.

### Projekt 11: System dziekanatu: zapisy na przedmioty

**Poziom:** sredni  
**Glowny akcent OOP:** relacje wiele-do-wielu, ograniczenia zapisow, role uzytkownikow

**Cel projektu:** obsluzyc studentow, prowadzacych, kursy, grupy i wpisy ocen.

#### Lista kontrolna
1. Przygotuj szkielet projektu [02][04]
   - Uzyj pakietow `dean.domain`, `dean.service`, `dean.app`, `dean.report`.
   - Dodaj klase `Main` pokazujaca semestr od zapisow po oceny.
2. Zamodeluj uzytkownikow [08][07]
   - Stworz abstrakcyjna klase `User`.
   - Dodaj `Student` i `Lecturer`.
3. Dodaj kursy i grupy [05b][06]
   - Utworz `Course` oraz `Group`.
   - Waliduj limit miejsc, ECTS i brak duplikatow studentow.
4. Zdefiniuj interfejsy [08]
   - Dodaj `Gradable` dla pracy z ocenami.
   - Dodaj `Exportable` dla list studentow i wynikow.
5. Zaimplementuj serwisy [03]
   - `EnrollmentService` obsluguje zapisy i wypisania.
   - `GradeBookService` trzyma oceny i eksporty.
6. Zadbaj o niezmienniki [05][06]
   - Nie pozwalaj zapisac tego samego studenta dwa razy.
   - Nie pozwalaj wpisac oceny poza zakresem.
7. Pokaz polimorfizm i relacje [07]
   - Operuj na referencjach typu `User`, gdy ma to sens.
   - Oddziel odpowiedzialnosc kursu, grupy i serwisow.
8. Przygotuj scenariusz koncowy [03]
   - Utworz kursy, zapisz studentow, wystaw oceny, wyeksportuj liste.
   - Dodaj `README.md` z opisem przeplywu.

### Projekt 12: System ankiet i formularzy

**Poziom:** sredni  
**Glowny akcent OOP:** hierarchia pytan, walidacja odpowiedzi, agregacja wynikow

**Cel projektu:** tworzyc ankiety z roznymi typami pytan i zbierac odpowiedzi respondentow.

#### Lista kontrolna
1. Ustal strukture pakietow [02][04]
   - Przygotuj `survey.domain`, `survey.service`, `survey.app`, `survey.report`.
   - Dodaj klase `Main` z przykladowa ankieta.
2. Zamodeluj pytania [08][07]
   - Utworz abstrakcyjna klase `Question`.
   - Zaimplementuj `SingleChoice`, `MultipleChoice`, `TextQuestion`.
3. Dodaj kontrakty [08]
   - Wprowadz `Answerable` z metoda walidacji odpowiedzi.
   - Dodaj `Exportable` dla wynikow.
4. Dodaj modele danych [05b][06]
   - Utworz `Survey`, `Response`, opcjonalnie `Answer`.
   - Waliduj odpowiedzi zgodnie z typem pytania.
5. Zaimplementuj serwisy [03]
   - `SurveyService` tworzy i konfiguruje ankiety.
   - `ResponseService` zapisuje odpowiedzi i liczy podstawowe statystyki.
6. Pokaz roznice zachowan [07]
   - Rozne pytania waliduja i formatuja odpowiedzi inaczej.
   - Unikaj `instanceof` tam, gdzie moze zadzialac polimorfizm.
7. Dodaj raportowanie [08]
   - Wygeneruj CSV z odpowiedziami albo podsumowaniem.
   - Dla pytan zamknietych policz liczebnosci odpowiedzi.
8. Zbuduj demonstracje [03]
   - Utworz ankiete z trzema typami pytan.
   - Dodaj kilka odpowiedzi i wydrukuj podsumowanie.

## Projekty: sport i rekreacja

### Projekt 13: System wypozyczalni rowerow miejskich

**Poziom:** podstawowy  
**Glowny akcent OOP:** interfejsy wypozyczania, hierarchia pojazdow, infrastruktura

**Cel projektu:** obsluzyc rowery klasyczne i elektryczne, stacje dokujace oraz wypozyczenia.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Uzyj pakietow `citybike.domain`, `citybike.service`, `citybike.app`, `citybike.report`.
   - Dodaj klase startowa `App`.
2. Stworz abstrakcje pojazdu [08][07]
   - Utworz klase `Vehicle`.
   - Dodaj `Bike` i `EBike`.
3. Dodaj kontrakty [08]
   - Zdefiniuj `Rentable` oraz `Exportable`.
   - Niech pojazdy implementuja oba interfejsy.
4. Zamodeluj infrastrukture [05b][06]
   - Utworz `DockingStation` z pojemnoscia i lista slotow.
   - Waliduj pojemnosc oraz duplikaty identyfikatorow.
5. Zaimplementuj logike wypozyczen [03]
   - `RentalService` wypozycza i przyjmuje zwroty.
   - `FleetService` raportuje stan floty.
6. Wykorzystaj polimorfizm [07]
   - Rozne typy rowerow moga miec inne opisy albo inne ograniczenia.
   - Trzymaj je we wspolnej kolekcji typu bazowego.
7. Przygotuj demo [03]
   - Dodaj dwie stacje i kilka pojazdow.
   - Wykonaj sekwencje wypozyczen, zwrotow i eksportu.
8. Rozszerz projekt [08]
   - Dodaj `Chargeable` dla rowerow elektrycznych.
   - Rozwaz strategie naliczania oplat.

### Projekt 14: Liga uczelniana: terminarz i tabela wynikow

**Poziom:** sredni  
**Glowny akcent OOP:** kompozycja obiektow, strategie punktacji, raportowanie

**Cel projektu:** zarzadzac zespolami, meczami, terminarzem i tabela ligowa.

#### Lista kontrolna
1. Zbuduj projekt [02][04]
   - Przygotuj `league.domain`, `league.service`, `league.schedule`, `league.app`.
   - Dodaj klase `Main`.
2. Zamodeluj osoby i zespoly [08][07]
   - Stworz abstrakcyjna klase `Person`.
   - Dodaj `Player`, `Coach` i `Team`.
3. Zamodeluj dyscypliny i mecze [08][05b]
   - Dodaj abstrakcyjna klase `Sport`.
   - Utworz `Football`, `Basketball`, `Match`, `Score`.
4. Wprowadz kontrakty [08]
   - Dodaj `PlayableMatch` i `Exportable`.
   - Zadbaj, aby mecz mogl byc rozegrany i zapisany do raportu.
5. Zaimplementuj terminarz [03]
   - `Scheduler` generuje mecze round-robin.
   - `StandingService` wylicza tabele.
6. Dodaj warianty zasad [07][08]
   - Uzyj `PointRule` jako strategii punktacji.
   - Rozne sporty moga przyznawac punkty inaczej.
7. Zbuduj scenariusz demo [03]
   - Utworz co najmniej cztery druzyny.
   - Wygeneruj terminarz, rozegranie i raport koncowy.
8. Przygotuj opis projektu [02]
   - Dodaj diagram klas lub prosty rysunek zaleznosci.
   - Opisz w `README.md`, gdzie jest os logiki rozgrywek.

### Projekt 15: Aplikacja trenera personalnego

**Poziom:** podstawowy  
**Glowny akcent OOP:** abstrakcja aktywnosci, dziennik treningow, kontrakty sledzenia

**Cel projektu:** przechowywac plany treningowe, aktywnosci i wpisy w dzienniku cwiczen.

#### Lista kontrolna
1. Przygotuj strukture [02][04]
   - Uzyj `trainer.domain`, `trainer.service`, `trainer.app`, `trainer.report`.
   - Dodaj klase `App`.
2. Stworz abstrakcje aktywnosci [08]
   - Dodaj `AbstractActivity` z polami `name`, `durationMin`.
   - Zaimplementuj `Running`, `Cycling`, `StrengthTraining`.
3. Dodaj interfejsy [08]
   - Wprowadz `Trackable` i `Exportable`.
   - Kazda aktywnosc powinna umiec raportowac swoj stan.
4. Zamodeluj plan i log [05b][06]
   - Utworz `WorkoutPlan` i `WorkoutLogEntry`.
   - Waliduj czas trwania i brak pustych danych.
5. Zaimplementuj serwisy [03]
   - `PlanService` zarzadza aktywnosciami w planie.
   - `LogService` zapisuje wykonane treningi.
6. Pokaz polimorfizm [07]
   - Rozne aktywnosci inaczej licza kalorie.
   - Zestawiaj je w jednej liscie treningowej.
7. Przygotuj eksport [08]
   - Wygeneruj dziennik do CSV.
   - Dodaj raport tygodniowy z sumami czasu i kalorii.
8. Zademonstruj uzycie [03]
   - Utworz plan, wykonaj dwie aktywnosci, wydrukuj log.
   - Opisz wynik w `README.md`.

### Projekt 16: Turniej szachowy: parowanie i klasyfikacja

**Poziom:** sredni  
**Glowny akcent OOP:** strategie parowania i punktacji, organizacja rund

**Cel projektu:** obsluzyc zawodnikow, rundy, wyniki i klasyfikacje turniejowa.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Uzyj `chess.domain`, `chess.tournament`, `chess.service`, `chess.app`.
   - Dodaj klase `Main`.
2. Zamodeluj zawodnikow [08][07]
   - Stworz abstrakcyjna klase `Competitor`.
   - Dodaj konkretna klase `Player`.
3. Dodaj modele turnieju [05b][06]
   - Utworz `Match`, `Result`, `Standing`.
   - Waliduj rating i poprawne przypisanie zawodnikow.
4. Wprowadz interfejsy strategii [08]
   - Dodaj `PairingStrategy` i `ScoringRule`.
   - Przygotuj przynajmniej po jednej implementacji obu strategii.
5. Zaimplementuj serwis turniejowy [03]
   - `TournamentService` odpowiada za rundy i aktualizacje tabeli.
   - `ReportService` generuje raport z wynikow.
6. Pokaz wymiennosc algorytmow [07][08]
   - Podmieniaj strategia parowania bez zmian w reszcie kodu.
   - To samo zrob dla zasad punktacji.
7. Przygotuj demo [03]
   - Dodaj osmiu graczy i kilka rund.
   - Wydrukuj klasyfikacje po kazdej rundzie.
8. Rozszerz projekt [07]
   - Dodaj `Tournament` jako abstrakcyjna klase bazowa.
   - Rozwaz `RoundRobinTournament` i `SwissTournament`.

### Projekt 17: Wypozyczalnia sprzetu sportowego

**Poziom:** podstawowy  
**Glowny akcent OOP:** katalog sprzetu, wypozyczanie i kontrola stanu

**Cel projektu:** zarzadzac stanem magazynowym roznego sprzetu sportowego.

#### Lista kontrolna
1. Zbuduj szkielet [02][04]
   - Przygotuj `sportsrent.domain`, `sportsrent.service`, `sportsrent.app`.
   - Dodaj klase `App`.
2. Stworz abstrakcje sprzetu [08][07]
   - Dodaj abstrakcyjna klase `Equipment`.
   - Zaimplementuj `TennisRacket`, `SkiSet`, `FootballBall`.
3. Dodaj interfejsy [08]
   - Wprowadz `Rentable` i `Exportable`.
   - Opracuj jednolite API wypozyczenia i zwrotu.
4. Zamodeluj magazyn [05b][06]
   - Utworz `Inventory`.
   - Waliduj identyfikatory, stan i duplikaty.
5. Zaimplementuj serwis wypozyczen [03]
   - `RentalService` prowadzi prosty rejestr wypozyczen.
   - `ReportingService` pokazuje sprzet dostepny i wypozyczony.
6. Pokaz zroznicowane zachowania [07]
   - Rozne typy sprzetu moga miec inne dane opisowe lub ograniczenia.
   - Uzyj wspolnego typu bazowego do raportowania.
7. Przygotuj demo [03]
   - Dodaj kilka obiektow roznych klas.
   - Wypozycz, zwroc i wyeksportuj stan magazynu.
8. Dodaj opcjonalne rozszerzenie [08]
   - `Serviceable` dla przegladow i napraw.
   - `PricingStrategy` dla stawek wypozyczenia.

### Projekt 18: Analizator statystyk meczowych

**Poziom:** sredni  
**Glowny akcent OOP:** zrodla danych, raporty i kompozycja komponentow

**Cel projektu:** obliczac metryki meczowe i renderowac raporty na podstawie danych wczytanych z roznych zrodel.

#### Lista kontrolna
1. Przygotuj strukture [02][04]
   - Uzyj `matchstats.domain`, `matchstats.report`, `matchstats.source`, `matchstats.app`.
   - Dodaj klase `Main`.
2. Zamodeluj dane [05b]
   - Dodaj `Team`, `Match`, opcjonalnie `PlayerStat`.
   - Zadbaj o czytelne modele wyniku i daty.
3. Dodaj abstrakcje zrodla danych [08][07]
   - Stworz abstrakcyjna klase `DataSource`.
   - Zaimplementuj `InMemorySource`, opcjonalnie `CsvSource`.
4. Dodaj kontrakty raportow [08]
   - Wprowadz interfejs `Report`.
   - Dodaj `SummaryReport` i `TopScoringTeamsReport`.
5. Zaimplementuj serwis raportujacy [03]
   - `ReportingService` laczy zrodlo danych z raportem.
   - Utrzymuj odpowiedzialnosci klas rozdzielone.
6. Pokaz polimorfizm [07]
   - Zmieniaj typ raportu bez przebudowy warstwy danych.
   - Zmieniaj zrodlo danych bez zmian w logice liczenia.
7. Przygotuj scenariusz demo [03]
   - Wygeneruj dwa raporty dla tego samego zestawu meczow.
   - Pokaz przyklad wyjscia w `README.md`.
8. Rozszerz projekt [08]
   - Dodaj `Filter` dla zawazenia raportow do druzyny lub zakresu dat.
   - Dodaj `Formatter`, np. CSV i JSON.

## Projekty: transport, logistyka i infrastruktura

### Projekt 19: Transport publiczny: linie, rozklady i pojazdy

**Poziom:** sredni  
**Glowny akcent OOP:** modelowanie infrastruktury, rozklady, rozne typy pojazdow

**Cel projektu:** obsluzyc linie autobusowe i tramwajowe, przystanki, kursy oraz raporty.

#### Lista kontrolna
1. Przygotuj pakiety [02][04]
   - Uzyj `transit.domain`, `transit.schedule`, `transit.service`, `transit.app`.
   - Dodaj klase `Main`.
2. Stworz abstrakcje pojazdu [08][07]
   - Dodaj abstrakcyjna klase `Vehicle`.
   - Zaimplementuj `Bus` i `Tram`.
3. Zamodeluj linie i rozklad [05b][06]
   - Dodaj `Line`, `Stop`, `Timetable`.
   - Waliduj nazwy i brak duplikatow przystankow w linii.
4. Wprowadz kontrakty [08]
   - Dodaj `Exportable` do raportow.
   - Dodaj `Trackable` dla aktywnego kursu.
5. Zaimplementuj serwisy [03]
   - `RouteService` obsluguje przebieg linii.
   - `ReportingService` generuje rozklady i raporty kursow.
6. Pokaz warianty zachowan [07]
   - Rozne pojazdy moga miec inna pojemnosc i inne ograniczenia.
   - Wykorzystaj to w raportach lub logice kursu.
7. Przygotuj demo [03]
   - Dodaj dwie linie z roznymi typami pojazdow.
   - Wydrukuj rozklad i prosty raport kursu.
8. Rozszerz projekt [08]
   - Dodaj strategie czasu przejazdu dla godzin szczytu i poza szczytem.
   - Rozwaz prosty generator opoznien.

### Projekt 20: Firma kurierska: paczki, trasy i statusy

**Poziom:** sredni  
**Glowny akcent OOP:** stany przesylek, sledzenie procesu, warianty paczek

**Cel projektu:** zarzadzac paczkami, trasami i zmianami statusu przesylek.

#### Lista kontrolna
1. Zbuduj projekt [02][04]
   - Przygotuj `courier.domain`, `courier.service`, `courier.app`, `courier.report`.
   - Dodaj klase `Main`.
2. Zamodeluj przesylke [08][07]
   - Stworz abstrakcyjna klase `Parcel`.
   - Dodaj `StandardParcel` i `FragileParcel`.
3. Dodaj modele pomocnicze [05b][06]
   - Utworz `Address`, `Route`, `Status`.
   - Waliduj wage, gabaryty i dane adresowe.
4. Wprowadz interfejsy [08]
   - Dodaj `Trackable` i `Exportable`.
   - Zadbaj o spojne API historii zdarzen.
5. Zaimplementuj serwisy [03]
   - `RoutingService` przypisuje paczki do tras.
   - `TrackingService` zmienia statusy i zapisuje historie.
6. Pokaz polimorfizm [07]
   - Rozne paczki moga wymagac innych krokow logistycznych.
   - Zaszyj te roznice w klasach, nie w rozbudowanych `if-ach`.
7. Przygotuj raporty [08]
   - Generuj historie statusow do CSV.
   - Dodaj raport zbiorczy per trasa.
8. Zademonstruj dzialanie [03]
   - Utworz kilka paczek i symuluj ich droge do doreczenia.
   - Dodaj przyklad outputu do `README.md`.

## Projekty: przyroda, monitoring i symulacje

### Projekt 21: Symulator zoo

**Poziom:** podstawowy  
**Glowny akcent OOP:** dziedziczenie, interfejsy zdolnosci, symulacja zachowan

**Cel projektu:** zasymulowac podstawowe zachowania roznych zwierzat i ich aktywnosci.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Uzyj `zoo.domain`, `zoo.service`, `zoo.app`, `zoo.report`.
   - Dodaj klase `Simulator`.
2. Stworz abstrakcje zwierzecia [08][05b]
   - Dodaj abstrakcyjna klase `Animal`.
   - Umiesc w niej pola `name`, `age` i metody wspolne.
3. Rozbuduj hierarchie [07][06]
   - Dodaj `Mammal`, `Bird`, `Reptile`.
   - Dodaj konkretne klasy, np. `Lion`, `Penguin`, `Iguana`.
4. Wprowadz interfejsy zdolnosci [08]
   - Dodaj `Flyable`, `Swimmable`, `Trainable`.
   - Wybrane zwierzeta powinny implementowac tylko sensowne kontrakty.
5. Zaimplementuj silnik symulacji [03]
   - `SimulationService` wykonuje tury aktywnosci.
   - `ReportingService` zapisuje wyniki aktywnosci.
6. Pokaz polimorfizm [07]
   - Uruchamiaj wspolne czynnosci przez typ bazowy `Animal`.
   - Zdolnosci wywoluj przez typy interfejsowe.
7. Zadbaj o walidacje [05][06]
   - Pilnuj wieku, nazw i niezmiennikow stanu.
   - Zwracaj niemodyfikowalne widoki kolekcji aktywnosci.
8. Przygotuj demo [03]
   - Dodaj kilka zwierzat o roznych zdolnosciach.
   - Uruchom kilka tur i wydrukuj raport.

### Projekt 22: Monitor stacji pogodowej

**Poziom:** rozszerzony  
**Glowny akcent OOP:** interfejsy czujnikow, wyjatki, gleboka kopia

**Cel projektu:** gromadzic odczyty z czujnikow i reagowac na nieprawidlowe pomiary.

#### Lista kontrolna
1. Przygotuj projekt [02][04]
   - Uzyj `weather.domain`, `weather.service`, `weather.app`.
   - Dodaj klase `Main`.
2. Zdefiniuj kontrakt czujnika [08]
   - Dodaj interfejs `Sensor` z metoda `readValue()`.
   - Rozwaz osobny interfejs `Maintainable`.
3. Dodaj implementacje czujnikow [07]
   - Zaimplementuj `TemperatureSensor` i `HumiditySensor`.
   - Kazdy czujnik powinien miec identyfikator i lokalizacje.
4. Wprowadz obsluge bledow [11]
   - Dodaj `SensorMalfunctionException`.
   - Rzucaj wyjatek dla nierealistycznych odczytow.
5. Zamodeluj agregacje [05b][12]
   - Utworz `WeatherStation` przechowujaca liste czujnikow.
   - Zadeklaruj czy stacja jest wlascicielem tych obiektow.
6. Dodaj kopie zapasowa [12]
   - Zaimplementuj gleboka kopie stacji wraz z czujnikami.
   - Wyjasnij w kodzie, co jest kopiowane i dlaczego.
7. Zaimplementuj przeplyw uruchomieniowy [03]
   - Wykonaj serie odczytow i obsluz wyjatki.
   - Zapisz proste podsumowanie odczytow.
8. Dodaj element weryfikacji [09]
   - Przetestuj, czy kopia stacji nie wspoldzieli kolekcji z oryginalem.
   - Sprawdz zachowanie wyjatku dla skrajnego pomiaru.

### Projekt 23: Siec stacji meteo

**Poziom:** sredni  
**Glowny akcent OOP:** klasy pomiarow, dostawcy danych, agregacja raportow

**Cel projektu:** zarzadzac wieloma stacjami i zestawieniami pomiarow roznych typow.

#### Lista kontrolna
1. Zorganizuj projekt [02][04]
   - Uzyj `meteo.domain`, `meteo.source`, `meteo.report`, `meteo.app`.
   - Dodaj klase `Main`.
2. Zamodeluj pomiary [08][07]
   - Stworz abstrakcyjna klase `Measurement`.
   - Dodaj `Temperature`, `Humidity`, `WindSpeed`.
3. Dodaj modele stacji i jednostek [05b][06]
   - Utworz `Station` oraz enum `Unit`.
   - Waliduj zakresy i poprawne jednostki.
4. Wprowadz kontrakty [08]
   - Dodaj `DataProvider` i `Report`.
   - Przygotuj `InMemoryProvider` jako bazowa implementacje.
5. Zaimplementuj raportowanie [03]
   - `ReportingService` liczy min, max i srednia.
   - Wygeneruj co najmniej dwa rozne raporty.
6. Pokaz polimorfizm [07]
   - Rozne typy pomiarow powinny inaczej interpretowac wartosci.
   - Rozne raporty powinny byc wymienne bez zmian w zrodle danych.
7. Przygotuj scenariusz demonstracyjny [03]
   - Dodaj dwie stacje i zestaw roznych pomiarow.
   - Wydrukuj raport stacji i raport zbiorczy.
8. Rozszerz projekt [08]
   - Dodaj `Formatter` do CSV i JSON.
   - Rozwaz abstrakcyjny `Aggregator` dla raportow dobowych i tygodniowych.

### Projekt 24: Katalog roslin i stanowisk

**Poziom:** rozszerzony  
**Glowny akcent OOP:** relacje obiektow, wyjatki domenowe, porownywanie obiektow

**Cel projektu:** zarzadzac roslinami, ich stanowiskami oraz podstawowa logika pielegnacji.

#### Lista kontrolna
1. Przygotuj strukture [02][04]
   - Uzyj `garden.model`, `garden.logic`, `garden.app`.
   - Dodaj klase `Main`.
2. Zamodeluj relacje [12][05]
   - Utworz klase `Location`.
   - Niech `Plant` posiada referencje do obiektu `Location`.
3. Dodaj hierarchie roslin [08][07]
   - Stworz abstrakcyjna klase `Plant`.
   - Dodaj `Tree` i `Flower`.
4. Wprowadz kontrakty [08]
   - Dodaj interfejs `Waterable`.
   - Rozwaz prosty interfejs `Inspectable`.
5. Dodaj walidacje i wyjatki [06][11]
   - Rzucaj `PlantDeadException` dla niepoprawnej pielegnacji.
   - Waliduj gatunek, stan i lokalizacje.
6. Zaimplementuj logike porownywania [12]
   - Nadpisz `equals()` dla porownania roslin wedlug przyjetej tozsamosci.
   - Zdecyduj, czy lokalizacja jest czescia rownosci obiektu.
7. Dodaj warstwe logiki ogrodu [03][10]
   - `GardenService` odpowiada za podlewanie, przesadzanie i raportowanie.
   - Rozdziel model od logiki biznesowej.
8. Przygotuj demo [03]
   - Utworz kilka roslin na roznych stanowiskach.
   - Pokaz scenariusz poprawnej i blednej pielegnacji.

## Wspolne wskazowki do wszystkich projektow

1. Zacznij od prostego diagramu klas i interfejsow [01]
   - Nawet szkic na kartce lub w draw.io pozwoli szybciej wychwycic zle zaleznosci.
2. Pilnuj odpowiedzialnosci klas [10]
   - Encje domenowe nie powinny przejmowac roli calej aplikacji.
   - Serwisy powinny spinac przeplyw, a nie przechowywac przypadkowy stan.
3. Projektuj najpierw kontrakty, potem implementacje [08]
   - Interfejs lub klasa abstrakcyjna powinny wynikac z rzeczywistej wspolnej odpowiedzialnosci.
4. Waliduj dane w konstruktorach i metodach [06][11]
   - Nie dopuszczaj obiektow w blednym stanie.
5. Chroń kolekcje i stan wewnetrzny [05][12]
   - Zwracaj kopie albo niemodyfikowalne widoki.
   - Nie przekazuj referencji, jesli uzytkownik klasy nie powinien zmieniac stanu.
6. Pokaz polimorfizm w warstwie klienta [07][08]
   - Trzymaj referencje w typach bazowych lub interfejsach.
   - Unikaj nadmiarowych `if`, `switch` i `instanceof`, gdy zachowanie mozna przeniesc do klas.
7. Dodaj male, czytelne demo [03]
   - Jeden sensowny scenariusz w `main` jest lepszy niz duzo przypadkowych obiektow.
8. Dokumentuj zalozenia [02]
   - W `README.md` opisz cel projektu, strukture pakietow i sposob uruchomienia.
9. Korzystaj z Git od poczatku [02]
   - Male commity po kolejnych punktach checklisty bardzo ulatwiaja prace i cofanie zmian.
