import laboratoria.lab2.geometry.*; // import z gwiazdką: ładuje wszystkie publiczne typy z pakietu geometry

// Zadanie 4 — import wieloznaczny (z gwiazdką)
// Plusy: krótszy zapis, gdy korzystamy z wielu typów z tego samego pakietu.
// Minusy: mniej czytelne źródło pochodzenia typów, możliwe kolizje nazw przy większych projektach.
public class Zad4_WildcardImport {
    public static void main(String[] args) {
        Point a = new Point(1, 2);
        Point b = new Point(4, 6);
        Point c = new Point(-2, -3);

        System.out.println("a=" + a + ", b=" + b + ", c=" + c);
        System.out.println("dist(a, b) = " + DistanceUtils.distance(a, b));
        System.out.println("dist(b, c) = " + DistanceUtils.distance(b, c));
        System.out.println("dist(a, c) = " + DistanceUtils.distance(a, c));
    }
}