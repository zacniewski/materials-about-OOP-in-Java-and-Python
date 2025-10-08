// Zadanie 3: MathUtils – tylko publiczne metody statyczne

class MathUtils {
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    public static double avg(int a, int b) {
        return (a + b) / 2.0;
    }
}

public class Zad3_MathUtils {
    public static void main(String[] args) {
        System.out.println("max(3, 7) = " + MathUtils.max(3, 7));
        System.out.println("min(3, 7) = " + MathUtils.min(3, 7));
        System.out.println("avg(3, 7) = " + MathUtils.avg(3, 7));
    }
}
