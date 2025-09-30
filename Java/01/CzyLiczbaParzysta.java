import java.util.Scanner;

public class CzyLiczbaParzysta {
  public static void main(String[] args) {
    int liczba;
    boolean czyParzysta;

    System.out.println("Prosze podac liczbe:");
    liczba = getInt();

    if (liczba % 2 == 0) {
      czyParzysta = true;
    } else {
      czyParzysta = false;
    }

    System.out.println("Czy liczba parzysta: " + czyParzysta);
  }

  public static int getInt() {
    return new Scanner(System.in).nextInt();
  }
}