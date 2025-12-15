/*
 To jest komentarz wielolinijkowy.
 Ponizej zaczyna sie klasa HelloWorldZKomentarzami
*/
public class HelloWorldZKomentarzami {
    /**
     * To jest komentarz dokumentujacy, jak dziala metoda main.
     */
    public static void main(String[] args) {
        // to jest komentarz jednolinijkowy - ponizej wypisujemy tekst
        System.out.println("Witaj Swiecie!"); // na koncu lini moze byc komentarz

        /*
           Ponizsza linia kodu nie zostanie wykonan, poniewaz
           zostala zakomentowana.
        */
        // System.out.println("Witajcie!");

        System.out.println(/* kolejny komentarz */ "Witajcie ponownie!");
    }
}