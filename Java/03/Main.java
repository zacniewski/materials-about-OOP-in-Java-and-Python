/**
 * Program demonstracyjny do rozdziału "Klasy i metody".
 */
public class Main {
    /**
     * Główna metoda programu.
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        Product apple = new Product("Jabłko", 2.50);
        Product cheese = new Product("Ser", 10.0, 0.08);

        System.out.println(apple);
        System.out.println("Brutto (apple): " + apple.grossPrice());

        System.out.println(cheese);
        System.out.println("Brutto (cheese): " + cheese.grossPrice());

        double gross = Product.grossOf(100, Product.DEFAULT_VAT);
        System.out.println("Brutto (100, default VAT): " + gross);

        System.out.println("Utworzono produktów: " + Product.createdCount());
    }
}
