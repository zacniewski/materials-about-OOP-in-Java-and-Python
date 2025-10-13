// Zadanie 3 — konflikt nazw i w pełni kwalifikowane nazwy
// Uwaga: w Javie nie ma aliasów importów dla klas, więc rozstrzygamy konflikt nazw
// stosując w pełni kwalifikowane nazwy podczas wywołań.

public class Zad3_KonfliktNazw {
    public static void main(String[] args) {
        String aName = laboratoria.lab2.a.utils.Utils.name();
        String bName = laboratoria.lab2.b.utils.Utils.name();
        System.out.println(aName);
        System.out.println(bName);
    }
}