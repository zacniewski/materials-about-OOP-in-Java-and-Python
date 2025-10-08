// Zadanie 5: Prosty licznik identyfikatorów (statyczny)

class IdGenerator {
    public static int total = 0; // brak enkapsulacji: tylko przykład pracy na statycznych polach

    public static int nextId() {
        total++;
        return total;
    }
}

public class Zad5_LicznikStatyczny {
    public static void main(String[] args) {
        int a = IdGenerator.nextId();
        int b = IdGenerator.nextId();
        int c = IdGenerator.nextId();

        System.out.println("a id = " + a);
        System.out.println("b id = " + b);
        System.out.println("c id = " + c);
        System.out.println("Łącznie wygenerowano: " + IdGenerator.total);
    }
}
