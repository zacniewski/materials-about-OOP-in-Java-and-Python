public class ObwodPoleKola {
  public static void main(String[] args) {
    int promienKola = 8;
    double pi = 3.14;

    double poleKola = pi * promienKola * promienKola;
    double obwodKola = 2 * pi * promienKola;

    System.out.println("Pole kola wynosi: " + poleKola);
    System.out.println("Obwod kola wynosi: " + obwodKola);
  }
}