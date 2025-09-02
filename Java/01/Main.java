import java.util.*;

public class Main {
  public static void main(String[] args) {
    // Przykład filarów OOP w praktyce
    List<Shape> shapes = new ArrayList<>();
    shapes.add(new Rectangle(3, 4)); // dziedziczenie + enkapsulacja
    shapes.add(new Circle(2));       // polimorfizm przez interfejs/klasę bazową

    for (Shape s : shapes) {
      s.draw(); // wywoła odpowiednią implementację (polimorfizm)
      System.out.printf("%s ma pole: %.2f\n", s.getName(), s.area());
    }
  }
}
