// Zadanie 4: Kalkulator – podstawowe operacje (public static)

class CalcUtils {
    public static double add(double a, double b) {
        return a + b;
    }

    public static double sub(double a, double b) {
        return a - b;
    }

    public static double mul(double a, double b) {
        return a * b;
    }

    public static double div(double a, double b) {
        if (b == 0.0) {
            throw new IllegalArgumentException("Dzielenie przez zero jest niedozwolone");
        }
        return a / b;
    }

    // Potęgowanie dla całkowitych n >= 0
    public static double pow(double a, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("W tej wersji n musi być >= 0");
        }
        double result = 1.0;
        for (int i = 0; i < n; i++) {
            result *= a;
        }
        return result;
    }
}

public class Zad4_Kalkulator {
    public static void main(String[] args) {
        System.out.println("Dodawanie: 5 + 3 = " + CalcUtils.add(5, 3));
        System.out.println("Odejmowanie: 5 - 3 = " + CalcUtils.sub(5, 3));
        System.out.println("Mnożenie: 5 * 3 = " + CalcUtils.mul(5, 3));
        System.out.println("Dzielenie: 15 / 3 = " + CalcUtils.div(15, 3));
        System.out.println("Potęgowanie: 2^10 = " + CalcUtils.pow(2, 10));

        // Przykład obsługi błędu: odkomentuj, aby zobaczyć wyjątek
        // System.out.println(CalcUtils.div(5, 0));
    }
}
