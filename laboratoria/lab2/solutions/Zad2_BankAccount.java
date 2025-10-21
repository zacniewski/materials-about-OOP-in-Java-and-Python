// Zadanie 2 (prosto, bez wyjątków): statyczna symulacja konta
// Założenie: dane są poprawne; nie rzucamy wyjątków ani nie walidujemy.

public class Zad2_BankAccount {

    // "Stan" konta przechowywany w polach statycznych (na potrzeby prostego przykładu)
    private static String owner;
    private static double balance;

    public static void reset(String newOwner, double initialBalance) {
        // Przyjmujemy poprawne dane
        owner = (newOwner == null) ? "" : newOwner.trim();
        balance = initialBalance;
    }

    public static void deposit(double amount) {
        // Przyjmujemy dodatnią kwotę
        balance += amount;
    }

    public static void withdraw(double amount) {
        // Przyjmujemy, że środki i kwota są poprawne
        balance -= amount;
    }

    public static double getBalance() {
        return balance;
    }

    public static String getOwner() {
        return owner;
    }

    public static void main(String[] args) {
        System.out.println("=== Zad2 (prosto, bez wyjątków): statyczna symulacja konta ===");

        reset("Jan Kowalski", 500.0);
        System.out.println("Właściciel: " + getOwner() + ", saldo: " + getBalance());

        deposit(250.0);
        System.out.println("Po wpłacie: " + getBalance());

        withdraw(100.0);
        System.out.println("Po wypłacie: " + getBalance());
    }
}
