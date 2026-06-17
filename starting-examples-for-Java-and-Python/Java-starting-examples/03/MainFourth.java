/*
You will often see Java programs that have either static or public attributes and methods.
In the example above, we created a static method, which means that it can be accessed without creating an object of the class,
unlike public, which can only be accessed by objects:
 */

public class MainFourth {
    // Metoda nr 1 - Static method
    static void myStaticMethod() {
        System.out.println("Static methods can be called without creating objects");
    }

    // Metoda nr 2 - Public method
    public void myPublicMethod() {
        System.out.println("Public methods must be called by creating objects");
    }

    // Main method
    public static void main(String[] args) {
        myStaticMethod(); // Call the static method
        // myPublicMethod(); This would compile an error

        MainFourth myObj = new MainFourth(); // Create an object of MainFourth
        myObj.myPublicMethod(); // Call the public method on the object
    }
}