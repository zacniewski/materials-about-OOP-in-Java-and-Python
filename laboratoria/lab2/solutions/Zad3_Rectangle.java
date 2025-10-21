// Zadanie 3 (prosto, bez wyjątków): proste gettery/settery szerokości i wysokości + funkcje area/perimeter
// Założenie: dane są poprawne; nie walidujemy i nie rzucamy wyjątków.

public class Zad3_Rectangle {

    // Prosty „stan” przechowywany w polach statycznych (dla demonstracji getterów/setterów)
    private static double width;
    private static double height;

    // Settery (proste, bez walidacji)
    public static void setWidth(double w) {
        width = w;
    }

    public static void setHeight(double h) {
        height = h;
    }

    // Gettery
    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    // Wersje funkcyjne – działają na parametrach
    public static double area(double width, double height) {
        return width * height;
    }

    public static double perimeter(double width, double height) {
        return 2 * (width + height);
    }

    // Wersje korzystające z aktualnego „stanu” (setterów)
    public static double area() {
        return width * height;
    }

    public static double perimeter() {
        return 2 * (width + height);
    }

    public static void main(String[] args) {
        System.out.println("=== Zad3 (prosto, bez wyjątków): gettery/settery + area/perimeter ===");

        // Wariant 1: czysto funkcyjny (na parametrach)
        System.out.println("3x4 -> area= " + area(3, 4) + ", perim= " + perimeter(3, 4));

        // Wariant 2: z wykorzystaniem prostych setterów/getterów (stan statyczny)
        setWidth(5);
        setHeight(2);
        System.out.println("width=" + getWidth() + ", height=" + getHeight() +
                " -> area= " + area() + ", perim= " + perimeter());

        setWidth(1.5);
        setHeight(2.5);
        System.out.println("width=" + getWidth() + ", height=" + getHeight() +
                " -> area= " + area() + ", perim= " + perimeter());
    }
}
