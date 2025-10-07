# Zadania: Klasy oraz ich elementy składowe, metody klasy (Java)

Wersja dostosowana: bez klas/metod abstrakcyjnych, bez prywatnych pól i setterów/getterów; tylko publiczne metody statyczne. 
Klasy pomocnicze traktujemy jako zbiory funkcji (metody static), a dane przekazujemy parametrami.
Dla każdego zadania tworzymy w osobnym pliku `.java` klasę o nazwie w postaci Zadanie1_Zacniewski, klasa ta powinna mieć metodę `main`,
aby dało się ją skompilować i uruchomić niezależnie od pozostałych.

Klasę pomocniczą dla danego zadania tworzymy nad klasą z numerem zadania, bez słowa kluczowego `public`.  

—
Zadanie 1. Demo metod statycznych
• Utwórz klasę pomocniczą DemoMetod, w której będą wyłącznie publiczne metody statyczne: info(), przywitaj(String imie).
• W metodzie main pokaż wywołania obu metod bez tworzenia obiektów.  

Zadanie 2. Koło – obwód i pole jako funkcje statyczne
• Utwórz klasę pomocniczą KoloUtils z publiczną stałą public static final double PI = 3.14159.
• Dodaj publiczne statyczne metody: obwod(double promien) oraz pole(double promien) z prostą walidacją promienia (> 0).
• Dodaj publiczną statyczną metodę porownajPole(double r1, double r2), która zwróci 1 gdy pierwsze ma większe pole, -1 gdy drugie, 0 gdy równe,  
można skorzystać z metody [compare](https://www.geeksforgeeks.org/java/double-compare-method-in-java-with-examples/),  
• W metodzie main zademonstruj działanie metod na kilku przykładach.  
• Można rzucić wyjątek dla niedodatnich promieni:  
```java
        if (b == 0.0) {
            throw new IllegalArgumentException("Dzielenie przez zero jest niedozwolone");
        }
```  

Zadanie 3. MathUtils – tylko publiczne metody statyczne
• Utwórz klasę pomocniczą MathUtils (zwykłą, nieabstrakcyjną, bez konstruktora) z metodami: max(int a, int b), min(int a, int b), avg(int a, int b) zwracającą średnią (double).
• Pokaż użycie w metodzie main – wywołania bez tworzenia obiektu.
• Można użyć `ternary` [operator](https://www.w3schools.com/java/java_conditions_shorthand.asp).  

Zadanie 4. Kalkulator – podstawowe operacje (statyczne)
• Utwórz klasę pomocniczą CalcUtils zawierającą wyłącznie publiczne metody statyczne: add(double a, double b), sub(double a, double b), mul(double a, double b), div(double a, double b) – jeśli b == 0, rzuć IllegalArgumentException, oraz pow(double a, int n) do potęgowania całkowitego n≥0.
• W metodzie main zademonstruj działania kalkulatora na kilku przykładach, bez tworzenia obiektów.
• Rzucanie wyjątku przy dzieleniu przez zero:  
```java
if (b == 0.0) {
            throw new IllegalArgumentException("Dzielenie przez zero jest niedozwolone");
        }
```

Zadanie 5. Prosty licznik identyfikatorów (statyczny)
• Utwórz klasę pomocniczą IdGenerator z publicznym statycznym polem total oraz publiczną statyczną metodą nextId(), która zwraca kolejne liczby 1, 2, 3, … i zwiększa total.
• Napisz metodę main, która kilka razy wywoła nextId() i wypisze otrzymane wartości oraz aktualny total.

