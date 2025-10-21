// Zadanie 4 (wersja uproszczona): brak klasy punktu – operujemy na parach liczb (x, y)
// Temat: proste funkcje statyczne działające na współrzędnych; brak @Override

public class Zad4_ImmutablePoint {

    // Zwraca nowy punkt jako tablicę [x, y]
    public static double[] movedBy(double x, double y, double dx, double dy) {
        return new double[] { x + dx, y + dy };
    }

    public static String formatPoint(double x, double y) {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        System.out.println("=== Zad4 (prosto): operacje na punktach bez klas domenowych ===");

        double x = 2.0, y = 3.5;
        System.out.println("p1 = " + formatPoint(x, y));

        double[] p2 = movedBy(x, y, 1.0, -2.0);
        System.out.println("p2 = " + formatPoint(p2[0], p2[1]));

        // Oryginalne (x, y) nie zmieniają się – pokazujemy ideę niezmienności poprzez tworzenie nowej pary
        System.out.println("p1 po operacji = " + formatPoint(x, y));
    }
}
