/**
 * Reprezentuje produkt w sklepie.
 * <p>
 * Przykład pokazuje:
 * - pola prywatne i statyczne,
 * - konstruktor(y), enkapsulację (gettery/settery z walidacją),
 * - metody instancyjne i statyczne,
 * - Javadoc dla klasy i metod.
 */
public class Product {
    /** Globalny licznik utworzonych produktów. */
    private static int COUNTER = 0;

    /** Domyślna stawka podatku VAT. */
    public static final double DEFAULT_VAT = 0.23;

    /** Unikalny identyfikator produktu. */
    private final int id;

    /** Nazwa produktu (niepusta). */
    private String name;

    /** Cena netto (>= 0). */
    private double netPrice;

    /** Stawka VAT (0..1). */
    private double vatRate;

    /**
     * Tworzy nowy produkt z domyślną stawką VAT.
     *
     * @param name     nazwa produktu (niepusta)
     * @param netPrice cena netto (>= 0)
     * @throws IllegalArgumentException gdy dane są niepoprawne
     */
    public Product(String name, double netPrice) {
        this(name, netPrice, DEFAULT_VAT);
    }

    /**
     * Tworzy nowy produkt z podaną stawką VAT.
     *
     * @param name     nazwa produktu (niepusta)
     * @param netPrice cena netto (>= 0)
     * @param vatRate  stawka VAT (0..1)
     * @throws IllegalArgumentException gdy dane są niepoprawne
     */
    public Product(String name, double netPrice, double vatRate) {
        validateName(name);
        validateNetPrice(netPrice);
        validateVat(vatRate);
        this.id = ++COUNTER;
        this.name = name;
        this.netPrice = netPrice;
        this.vatRate = vatRate;
    }

    /**
     * Zwraca identyfikator produktu.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Zwraca nazwę produktu.
     * @return nazwa (niepusta)
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nazwę produktu.
     * @param name nowa nazwa (niepusta)
     */
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    /**
     * Zwraca cenę netto.
     */
    public double getNetPrice() {
        return netPrice;
    }

    /**
     * Ustawia cenę netto.
     * @param netPrice cena (>= 0)
     */
    public void setNetPrice(double netPrice) {
        validateNetPrice(netPrice);
        this.netPrice = netPrice;
    }

    /**
     * Zwraca stawkę VAT.
     */
    public double getVatRate() {
        return vatRate;
    }

    /**
     * Ustawia stawkę VAT.
     * @param vatRate stawka (0..1)
     */
    public void setVatRate(double vatRate) {
        validateVat(vatRate);
        this.vatRate = vatRate;
    }

    /**
     * Oblicza cenę brutto (instancyjnie), stosując aktualne pola obiektu.
     * @return cena brutto
     */
    public double grossPrice() {
        return netPrice * (1.0 + vatRate);
    }

    /**
     * Metoda statyczna obliczająca cenę brutto na podstawie argumentów.
     * @param net cena netto
     * @param vat stawka VAT
     * @return cena brutto
     */
    public static double grossOf(double net, double vat) {
        if (net < 0) throw new IllegalArgumentException("net < 0");
        if (vat < 0 || vat > 1) throw new IllegalArgumentException("vat out of range");
        return net * (1.0 + vat);
    }

    /**
     * Zwraca liczbę utworzonych produktów.
     */
    public static int createdCount() {
        return COUNTER;
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is blank");
        }
    }

    private static void validateNetPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price < 0");
        }
    }

    private static void validateVat(double vat) {
        if (vat < 0 || vat > 1) {
            throw new IllegalArgumentException("vat out of range");
        }
    }

    /**
     * Reprezentacja tekstowa produktu.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", netPrice=" + netPrice +
                ", vatRate=" + vatRate +
                '}';
    }
}
