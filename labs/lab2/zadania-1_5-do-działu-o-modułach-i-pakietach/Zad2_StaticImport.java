import static laboratoria.lab2.math.BasicMath.*;
import laboratoria.lab2.geometry.Point;
import static laboratoria.lab2.geometry.DistanceUtils.distance;

// Zadanie 2 — static import metod
public class Zad2_StaticImport {
    public static void main(String[] args) {
        int a = 9, b = 4;
        System.out.println("add = " + add(a, b));
        System.out.println("sub = " + sub(a, b));
        System.out.println("mul = " + mul(a, b));
        System.out.println("div = " + div(a, b));

        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        System.out.println("distance(p1, p2) = " + distance(p1, p2));
    }
}