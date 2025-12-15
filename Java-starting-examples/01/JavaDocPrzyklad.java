import java.util.Scanner;

public class JavaDocPrzyklad {
  public static void main(String[] args) {
    System.out.println("Podaj liczbe");

    int x = getInt();

    System.out.println("Ta liczba do kwadratu to " + policzKwadrat(x));
  }

  /**
   * Metoda czeka na podanie przez uzytkownika wartosci, po czym ja zwraca.
   * @return Wartosc podana przez uzytkownika.
   */
  public static int getInt() {
    return new Scanner(System.in).nextInt();
  }

  /**
   * Zwraca przeslana liczbe podniesiona do kwadratu.
   *
   * Przyklad: dla argumentu liczba rownego 5, zwroci 25.
   *
   * @param liczba Liczba, ktora chcemy podniesc do kwadratu.
   * @return Liczba podniesiona do kwadratu.
   */
  public static double policzKwadrat(int liczba) {
    return liczba * liczba;
  }
}
