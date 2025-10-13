import laboratoria.lab2.math.BasicMath;

// Zadanie 1 — zwykły import klasy z pakietu
public class Zad1_DemoPakietow {
    public static void main(String[] args) {
        int a = 12;
        int b = 5;
        System.out.println("add(" + a + ", " + b + ") = " + BasicMath.add(a, b));
        System.out.println("sub(" + a + ", " + b + ") = " + BasicMath.sub(a, b));
        System.out.println("mul(" + a + ", " + b + ") = " + BasicMath.mul(a, b));
        System.out.println("div(" + a + ", " + b + ") = " + BasicMath.div(a, b));
    }
}