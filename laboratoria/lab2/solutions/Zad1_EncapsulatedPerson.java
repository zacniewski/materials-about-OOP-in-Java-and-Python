// Zadanie 1 (prosto, bez wyjątków): brak klas domenowych – tylko metody statyczne
// Założenie: dane wejściowe są poprawne; nie rzucamy wyjątków.

public class Zad1_EncapsulatedPerson {

    // Publiczna metoda tworząca prosty napis opisujący osobę (bez klasy Person)
    public static String opisOsoby(String name, int age) {
        // Przyjmujemy, że name i age są poprawne (brak walidacji i wyjątków)
        String cleaned = (name == null) ? "" : name.trim();
        return "Osoba: " + cleaned + ", wiek: " + age;
    }

    // Demo działania w metodzie main – tylko poprawne przykłady
    public static void main(String[] args) {
        System.out.println("=== Zad1 (prosto, bez wyjątków): funkcje statyczne ===");

        System.out.println(opisOsoby("Ala", 20));
        System.out.println(opisOsoby("Alicja", 21));
        System.out.println(opisOsoby("Jan", 30));
    }
}
