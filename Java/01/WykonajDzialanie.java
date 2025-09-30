import java.util.Scanner;

public class WykonajDzialanie {
  public static void main(String[] args) {
    int liczba1, liczba2;
    String operacja;

    System.out.println("Podaj pierwsza liczbe:");
    liczba1 = getInt();

    System.out.println("Podaj druga liczbe:");
    liczba2 = getInt();

    System.out.println("Podaj operacje do wykonania (+ - * /):");
    operacja = getString();

    int wynik = 0;
    boolean nieprawidlowaOperacja = false;

    if (operacja.equals("+")) {
      wynik = liczba1 + liczba2;
    } else if (operacja.equals("-")) {
      wynik = liczba1 - liczba2;
    } else if (operacja.equals("*")) {
      wynik = liczba1 * liczba2;
    } else if (operacja.equals("/")) {
      if (liczba2 != 0) {
        wynik = liczba1 / liczba2;
      } else {
        nieprawidlowaOperacja = true;
        System.out.println("Mianownik nie moze byc zerem!");
      }
    } else {
      nieprawidlowaOperacja = true;
      System.out.println("Nieprawidlowa operacja!");
    }

    if (!nieprawidlowaOperacja) {
      System.out.println(
          liczba1 + " " + operacja + " " + liczba2 + " = " + wynik
      );
    }
  }

  public static int getInt() {
    return new Scanner(System.in).nextInt();
  }

  public static String getString() {
    return new Scanner(System.in).next();
  }
}