import laboratoria.lab2.math.BasicMath;
import laboratoria.lab2.geometry.Point;
import laboratoria.lab2.geometry.DistanceUtils;
import laboratoria.lab2.text.StringJoiner;

// Zadanie 5 — praca z podpakietami i porządkowanie kodu
public class Zad5_Podpakiety {
    public static void main(String[] args) {
        int sum = BasicMath.add(7, 13);
        Point p1 = new Point(2, 1);
        Point p2 = new Point(5, 5);
        double dist = DistanceUtils.distance(p1, p2);
        String items = StringJoiner.join("apple", "banana", "cherry");

        String message = StringJoiner.join("sum=" + sum, "dist=" + dist, "items=" + items);
        System.out.println(message);
    }
}