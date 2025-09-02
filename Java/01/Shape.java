// Demonstracja filarów OOP w Javie

// Abstrakcja: interfejs definiuje kontrakt
interface Drawable {
    void draw();
}

// Abstrakcja + częściowa implementacja: klasa abstrakcyjna
abstract class Shape implements Drawable {
    // Enkapsulacja: prywatne pole
    private String name;

    protected Shape(String name) {
        this.name = name;
    }

    public String getName() { // kontrolowany dostęp
        return name;
    }

    // Polimorfizm przez przesłanianie: każda figura inaczej oblicza pole
    public abstract double area();

    @Override
    public void draw() {
        // Wspólne zachowanie z możliwością rozszerzenia w podklasach
        System.out.println("Rysuję kształt: " + name);
    }
}

// Dziedziczenie + przesłanianie metod
class Rectangle extends Shape {
    private double width;   // enkapsulacja
    private double height;  // enkapsulacja

    public Rectangle(double width, double height) {
        super("Prostokąt");
        this.width = width;
        this.height = height;
    }

    // Getter/Setter mogą kontrolować poprawność – tu prosto
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Rysuję prostokąt o bokach " + width + " x " + height);
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super("Koło");
        this.radius = radius;
    }

    public double getRadius() { return radius; }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    // Nie przesłaniamy draw() – użyjemy domyślnej implementacji z Shape
}
