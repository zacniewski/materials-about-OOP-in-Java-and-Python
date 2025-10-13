// Zadanie 1: Demo metod statycznych – bez obiektów

class DemoMetod {
    public static void przywitaj(String imie) {
        System.out.println("Cześć, " + imie + "!");
    }

    public static void info() {
        System.out.println("DemoMetod: pokazuje proste metody statyczne bez tworzenia obiektów.");
    }
}

public class Zad1_DemoMetod {
    public static void main(String[] args) {
        // wywołania metod statycznych – bez tworzenia obiektu
        DemoMetod.info();
        DemoMetod.przywitaj("Artur");
    }
}
