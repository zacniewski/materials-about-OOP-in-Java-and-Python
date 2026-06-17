/*
 To jest komentarz wielolinijkowy.
 Ponizej zaczyna sie klasa HelloWorldZKomentarzami
*/
public class HelloWorldZKomentarzami {
    /**
     * To jest komentarz dokumentujacy, jak działa metoda main.
     */
    public static void main(String[] args) {
        // to jest komentarz jednolinijkowy - ponizej wypisujemy tekst
        System.out.println("Witaj Świecie!"); // na koncu linii może byc komentarz

        /*
           Poniższa linia kodu nie zostanie wykonan, ponieważ
           została zakomentowana.
        */
        // System.out.println("Witajcie!");

        System.out.println(/* kolejny komentarz */ "Witajcie ponownie!");
    }
}