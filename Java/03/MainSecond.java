public class MainSecond {
    final int x = 10;  // pojawiło się 'final'

    public static void MainSecond(String[] args) {
        MainSecond myObj = new MainSecond();
        // myObj.x = 25; // po odkomentowaniu pojawi się błąd: cannot assign a value to a final variable
        System.out.println(myObj.x);
    }
}