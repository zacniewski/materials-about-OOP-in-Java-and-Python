public class WypiszParzysteFor {
  public static void main(String[] args) {
    for (int x = 0; x <= 20; x += 2) {
      System.out.print(x + " ");
    }

    // spowoduje blad kompilacji - zmienna x juz tutaj nie istnieje!
    //System.out.println("x na koncu jest rowne " + x);
  }
}
