// Zadanie 2: Koło – obwód i pole jako funkcje statyczne

class KoloUtils {
    public static final double PI = 3.14159; // stała klasy

    public static double obwod(double promien) {
        if (promien <= 0) throw new IllegalArgumentException("Promień musi być > 0");
        return 2 * PI * promien;
    }

    public static double pole(double promien) {
        if (promien <= 0) throw new IllegalArgumentException("Promień musi być > 0");
        return PI * promien * promien;
    }

    // Zwraca 1 jeżeli r1 ma większe pole, -1 jeżeli r2 ma większe pole, 0 jeżeli równe
    public static int porownajPole(double r1, double r2) {
        double p1 = pole(r1);
        double p2 = pole(r2);
        return Double.compare(p1, p2);
    }
}

public class Zad2_Kolo {
    public static void main(String[] args) {
        double r1 = 2.0;
        double r2 = 3.5;

        System.out.println("r1: obwód=" + KoloUtils.obwod(r1) + ", pole=" + KoloUtils.pole(r1));
        System.out.println("r2: obwód=" + KoloUtils.obwod(r2) + ", pole=" + KoloUtils.pole(r2));

        int cmp = KoloUtils.porownajPole(r1, r2);
        if (cmp == 0) {
            System.out.println("Pola są równe.");
        } else if (cmp > 0) {
            System.out.println("Większe pole ma koło o promieniu r1=" + r1);
        } else {
            System.out.println("Większe pole ma koło o promieniu r2=" + r2);
        }
    }
}
