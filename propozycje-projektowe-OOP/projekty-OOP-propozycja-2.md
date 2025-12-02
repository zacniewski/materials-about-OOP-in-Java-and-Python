# Dwa pomysły na projekt OOP w Javie (zakres: 01–08)

Poniżej znajdziesz dwa kompletne pomysły na projekty edukacyjne w Javie, które celowo dotykają zagadnień omawianych w materiałach od `01-paradygmaty-obiektowości.md` do `08-interfejsy-i-klasy-abstrakcyjne.md`. Każdy pomysł ma formę listy kontrolnej (checklisty) z sekwencją działań i krótkim uzasadnieniem, co dany krok ćwiczy.

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

## Pomysł 1: System Wypożyczalni Rowerów Miejskich

Cel: zaprojektować prosty system do zarządzania rowerami (klasycznymi i elektrycznymi), stacjami dokującymi i wypożyczeniami, demonstrując kontrakty, dziedziczenie, polimorfizm i kapsułkowanie.

### Lista kontrolna
1. Skonfiguruj projekt i strukturę katalogów [02][04]
   - Utwórz prosty projekt JDK z katalogiem `src/main/java`. [02]
   - Zdefiniuj pakiety: `citybike.domain`, `citybike.service`, `citybike.app`, `citybike.util`. [04]
   - Dodaj klasę uruchomieniową `citybike.app.App` z metodą `main`. [03]

2. Zaprojektuj abstrakcję bazową pojazdu [07][08][05]
   - Utwórz klasę abstrakcyjną `domain.Vehicle` z polami chronionymi: `id` (String), `locked` (boolean). [08][05]
   - Dodaj konstruktory ustawiające `id`, domyślnie `locked=true`. [06]
   - Zdefiniuj metody: `lock()`, `unlock()`, `isLocked()`, `String getDescription()` (abstrakcyjna). [08]
   - Zabezpiecz dostęp modyfikatorami (`protected` dla pól, `public` dla API). [05]

3. Zamodeluj różne typy rowerów [07][05b]
   - Klasa `domain.Bike` extends `Vehicle` z polem `gearCount` (int). [07][05b]
   - Klasa `domain.EBike` extends `Vehicle` z polami `batteryWh` (int), `assistLevel` (enum). [07][05b]
   - Obie nadpisują `getDescription()` i dodają specyficzne zachowania (np. `increaseAssist()`). [07]

4. Zdefiniuj interfejsy — umiejętności/kontrakty [08]
   - `domain.Rentable` z metodami: `rent()`, `returnBack()`, `boolean isAvailable()`. [08]
   - `domain.Exportable` z `String toCsv()` (nawiązanie do laboratorium o eksportowaniu). [08]
   - Niech `Bike` i `EBike` implementują `Rentable` i `Exportable`. [08]

5. Zaprojektuj stacje i infrastrukturę [03][05b]
   - Klasa `domain.DockingStation` z polami: `name` (String), `capacity` (int), `slots` (List<Vehicle>). [05b]
   - Metody: `dock(Vehicle)`, `undock(String vehicleId)`, `List<Vehicle> available()`. [03]

6. Usługi domenowe i polimorfizm w praktyce [07]
   - `service.RentalService` zarządza wypożyczeniami: potrafi znaleźć pojazd po `id`, wywołać `rent()`/`returnBack()` polimorficznie. [07]
   - `service.ReportingService` generuje raport CSV z całej floty, wołając `toCsv()` (polimorfizm przez interfejs). [08]

7. Kapsułkowanie i niezmienniki [05][05b]
   - Upewnij się, że pola są prywatne/protected, a kolekcje zwracane są jako kopie/niemodyfikowalne widoki. [05]
   - Dodaj stałe `public static final` np. `DEFAULT_STATION_CAPACITY`. [05b]

8. Konstrukcja obiektów i walidacje [06]
   - Dodaj walidację w konstruktorach (np. `gearCount > 0`, `batteryWh >= 0`). [06]
   - Rozważ konstruktor kopiujący lub wzorzec builder dla `EBike` (opcjonalnie). [06]

9. Warstwa uruchomieniowa/demonstracyjna [03]
   - W `App.main` utwórz stacje, dodaj różne rowery, wykonaj kilka wypożyczeń/zwrotów. [03]
   - Wygeneruj raport CSV i wydrukuj w konsoli. [03][08]

10. Testy ręczne/techniczne (opcjonalnie) [02]
    - Dodaj proste testy jednostkowe lub skrypty uruchomieniowe. [02]

11. Dalsze rozszerzenia (opcjonalne) [07][08]
    - Interfejs `Chargeable` dla urządzeń z baterią i implementacja w `EBike`. [08]
    - Strategia opłat (`PricingStrategy` jako interfejs) z różnymi implementacjami. [08]

Co ćwiczysz: abstrakcje i interfejsy (08), dziedziczenie i polimorfizm (07), konstrukcje i walidacje (06), kapsułkowanie i modyfikatory (05), świadomość pakietów i importów (04), podstawy klas i `main` (03), osadzenie w realnym narzędziu (02), myślenie obiektowe (01).

---

## Pomysł 2: Mini Sklep Internetowy (koszyk + płatności + faktury)

Cel: zaprojektować miniaturowy sklep z koszykiem, produktami różnych typów, mechanizmem rabatów i wieloma formami płatności. Projekt akcentuje interfejsy, klasy abstrakcyjne i polimorfizm.

### Lista kontrolna
1. Inicjalizacja projektu i pakietów [02][04]
   - Utwórz projekt z pakietami: `shop.domain`, `shop.pricing`, `shop.payment`, `shop.invoice`, `shop.app`. [04]
   - Dodaj `shop.app.Main` z `public static void main`. [03]

2. Abstrakcja produktu [08][07][05b]
   - Klasa abstrakcyjna `domain.Product` z polami: `sku` (String), `name` (String), `netPrice` (BigDecimal), `taxRate` (BigDecimal). [08][05b]
   - Metody: `BigDecimal netPrice()`, `BigDecimal taxAmount()`, `BigDecimal grossPrice()` (częściowo/lub w pełni zaimplementowane). [08]
   - Klasy konkretne: `Book`, `Food`, `Electronic` (różne reguły podatkowe/rabaty). [07]

3. Interfejsy rozszerzające możliwości [08]
   - `domain.Taxable` (już implikowane w `Product`, ale wypchnij kontrakt do interfejsu dla spójności). [08]
   - `invoice.Printable` z metodą `print()` do drukowania faktury/rachunku. [08]
   - `payment.Payable` jako kontrakt metod `authorize()`, `capture()`, `refund()`; implementacje: `CardPayment`, `BlikPayment`, `CashOnDelivery`. [08]

4. Koszyk i pozycje [03][05b]
   - `domain.CartItem` (product + quantity) z walidacją w konstruktorze. [06][05b]
   - `domain.Cart` z kolekcją pozycji, metodami dodawania/usuwania, liczenia sum netto/podatku/brutto. [03]
   - Zadbaj o kapsułkowanie kolekcji i niezmienniki (ilości > 0). [05]

5. Rabaty jako strategia [08][07]
   - Interfejs `pricing.DiscountPolicy` z `BigDecimal apply(BigDecimal subtotal)`. [08]
   - Implementacje: `NoDiscount`, `PercentageDiscount`, `ThresholdFixedDiscount`. [08]
   - Użyj polimorfizmu do podmiany polityki rabatowej w `Cart`. [07]

6. Generowanie dokumentów sprzedaży [08]
   - Klasa `invoice.Invoice` implementująca `Printable`, potrafi tworzyć czytelną reprezentację tekstową dokumentu. [08]
   - Rozważ `Exportable` do generacji CSV/JSON z danymi zamówienia. [08]

7. Obsługa płatności [08]
   - Zaimplementuj `Payable` w klasach płatności, dodaj proste stany (AUTHORIZED/CAPTURED/REFUNDED). [08]
   - Zaprojektuj `payment.PaymentService` orkiestrujący proces: autoryzacja → obciążenie → w razie potrzeby zwrot. [03]

8. Modyfikatory dostępu i pola statyczne [05][05b]
   - Użyj `private` dla pól, publicznych getterów, metod pakietowych, jeśli potrzebne. [05]
   - Dodaj `public static final BigDecimal TAX_23 = ...` itp. w klasie narzędziowej `Tax`. [05b]

9. Konstruktory i walidacje [06]
   - Waliduj `sku`, `name`, ceny i stawki podatkowe w konstruktorach. [06]
   - Rozważ konstruktor kopiujący dla `CartItem`. [06]

10. Scenariusz demo w `Main` [03]
    - Zbuduj koszyk z produktami, zastosuj politykę rabatową, wygeneruj fakturę i „opłać” zamówienie różnymi metodami. [03][08]

11. Rozszerzenia (opcjonalnie) [07][08]
    - Łańcuch odpowiedzialności dla promocji (lista `DiscountPolicy` stosowana sekwencyjnie). [08]
    - Interfejs `Returnable` dla obsługi zwrotów produktów. [08]

Co ćwiczysz: hierarchie klas i kontrakty (07–08), konstrukcję i walidację obiektów (06), pola i stałe (05b), kontrolę dostępu i kapsułkowanie (05), pakiety i importy (04), klasę uruchomieniową (03), praktykę narzędzi (02), myślenie obiektowe (01).

---

## Publikacja na GitHubie — lista kontrolna (dla obu pomysłów)

1. Przygotuj repozytorium Git [02]
   - Zainicjalizuj repozytorium: `git init` w katalogu projektu. [02]
   - Dodaj `.gitignore` dla Javy (np. ignoruj `target/`/`out/`, `.idea/`, `.classpath`, `.project`, `.settings/`, pliki systemowe). [02]
   - Ustaw nazwę i e‑mail w Git (globalnie lub lokalnie), jeżeli nie są skonfigurowane. [02]

2. Dokumentacja projektu [02][03][04]
   - Dodaj `README.md` z krótkim opisem, wymaganiami (np. wersja JDK), sposobem uruchomienia (`citybike.app.App` lub `shop.app.Main`). [03]
   - Opisz strukturę pakietów zgodnie z przyjętym podziałem (`domain`, `service`, `payment`, `invoice`, `app`, itp.). [04]
   - (Opcjonalnie) Dodaj `LICENSE` (np. MIT/Apache‑2.0) i `CONTRIBUTING.md`. [02]

3. Historia zmian i jakość commitów [02]
   - Rób częste, małe commity z opisowymi wiadomościami (np. konwencja `feat:`, `fix:`, `docs:`). [02]
   - W opisie commitów nawiązuj do realizowanych punktów checklisty (np. „Pomysł 1 — pkt 3: Bike/EBike”). [02]

4. Zdalne repozytorium i pierwszy push [02]
   - Utwórz puste repo na GitHubie i dodaj jako `origin`: `git remote add origin <URL>`. [02]
   - Wypchnij gałąź główną: `git branch -M main` oraz `git push -u origin main` (lub `master`). [02]

5. Praca na gałęziach (zalecane) [02]
   - Twórz gałęzie funkcjonalne dla większych kroków (np. `feature/rental-service`, `feature/payment-service`). [02]
   - Otwieraj Pull Requesty do `main`, opisując zmiany i odnosząc się do checklisty/kroków. [02]

6. Automatyzacja (opcjonalnie) [02]
   - Dodaj GitHub Actions do budowania projektu (np. Maven/Gradle) i (jeśli są) uruchamiania testów. [02]

7. Wydania i tagi (opcjonalnie) [02]
   - Oznaczaj kamienie milowe tagami (`v0.1`, `v0.2`) i twórz GitHub Releases z krótkim opisem zmian. [02]

8. Prezentacja efektów (rekomendowane)
   - W `README.md` dodaj sekcję „Przykład użycia” z fragmentem kodu i przykładowym wyjściem (np. raport CSV, wydruk faktury). [03][08]
   - Dołącz diagram klas/zrzuty ekranu w katalogu `docs/` lub `images/` i podlinkuj je w `README.md`. [01]

## Wskazówki ogólne do obu projektów
- Zanim zaczniesz pisać kod, narysuj prosty diagram klas i interfejsów; pomoże zachować spójność kontraktów. [01]
- Zadbaj o spójne pakiety i minimalny „leak” szczegółów implementacyjnych poza API publiczne. [04][05]
- Stosuj konstrukcje defensywne (walidacja argumentów w konstruktorach i metodach). [06]
- Demonstruj polimorfizm: trzymaj referencje w typach interfejsów/klas bazowych, a podstawiaj obiekty konkretnych klas. [07][08]
- Dodawaj krótkie komentarze Javadoc przy interfejsach i klasach abstrakcyjnych — wyjaśniają kontrakt i oczekiwania. [08]
