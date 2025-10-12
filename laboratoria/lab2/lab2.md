# Laboratorium 2 — Interfejsy i ergonomia programowania obiektowego

## Cel zajęć
Celem laboratorium jest:
- utrwalenie podstaw tworzenia klas i obiektów w Javie,
- wprowadzenie pojęcia **interfejsu**
- zrozumienie mechanizmu **wymienności implementacji**,

---

## Wymagania wstępne
- Znajomość podstawowych konstrukcji języka Java (klasy, metody, `main`).
- Środowisko z obsługą Swinga (np. IntelliJ, Eclipse, VS Code z odpowiednią konfiguracją).

---

## Rozgrzewka — Hello World

Zapisz poniższy kod do pliku `HelloWorld.java` i go uruchom.

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```
Pytanie kontrolne: co oznacza słówko `static`?
(O słówku `public` będziemy mówić na kolejnych zajęciach)

## Zadanie 1 - prosty program okienkowy

Zapisz poniższy kod do pliku `CounterApp.java` i go uruchom.

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CounterApp {

    JFrame frame = new JFrame("Licznik");
    JButton button = new JButton("Kliknij!");
    JLabel label = new JLabel("0");

    public static void main(String[] args) {
        new CounterApp().run();
    }

    void run() {
        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
```

Efekt działania programu opisz w sprawozdaniu.

## Zadanie 2 - dodawanie akcji do guzika

Podpięcie akcji do guzika we frameworku Swing wymaga stworzenia klasy,
która *implementuje* interfejs `ActionListener`. Interfejs ten wymaga,
by implementująca go klasa definiowała metodę o następującym
prototypie:

```java
void actionPerformed(ActionEvent e)
```

W pliku `CounterApp.java`, za nazwą klasy, ale przed otwierajacą
klamrą, tj. w linii

```
public class CounterApp {
```

dopisz frazę `implements ActionListener`, aby linia miała postać

```
public class CounterApp implements ActionListener {
```

Następnie w ciele klasy dodaj następujący kod:

```
    int count = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText(Integer.toString(count));
    }
```

Teraz aby podpiąć ten kod do guzika, należy w funkcji `run` klasy
`CounterApp` dopisać (gdzieś po utworzeniu obiektów `button` i
`label`) następującą linię:

```java
button.addActionListener(this);
```
W jaki sposób dodanie tej linii zmieniło działanie aplikacji?
(Co teraz się dzieje po wciśnięciu guzika?)

## Zadanie 3 - wiele liczników

W poprzednim zadaniu widzieliśmy, że jeżeli chcemy budować aplikacje
graficzne w frameworku Swing, musimy zaimplementować interfejs
`ActionListener`. 

Teraz zdefiniujemy sobie nasz własny interfejs, służący do zbudowania
*licznika*. Tworzymy plik `Counter.java`, do którego dodajemy:

```java
public interface Counter {
  void increase();
  int currentValue();
}
```
Jak widzimy, interfejs ten składa się z dwóch metod. Pierwsza z nich
zwiększa wartość licznika, a druga pobiera aktualną wartość.

Stwórzmy dwie implementacje tego interfejsu - pierwszą w pliku
`SimpleCounter.java`:

```java
public class SimpleCounter implements Counter {
  int counter = 0;
  public void increase() {
    ++counter;
  }
  
  public int currentValue() {
    return counter;
  }
}
```

i drugą w pliku `DoubleCounter.java`:

```java
public class DoubleCounter implements Counter {
  int counter = 0;
  public void increase() {
    counter += 2;
  }
  
  public int currentValue() {
    return counter;
  }
}
```

Teraz dodajmy do klasy `CounterApp` następujące pole:

```java
    JComboBox<Counter> counters = JComboBox<>(new Counter[]{
      new SimpleCounter(), 
      new DoubleCounter(),
    });
```

i w metodzie `run` dodajmy linijkę:

```java
        frame.add(counters);
```

Wreszcie zastąp wcześniejszą implementację metody `actionPerformed`
następującą (przy okazji skasuj pole `count` dodane w zadaniu 2):

```java
    @Override
    public void actionPerformed(ActionEvent e) {
        Counter counter = (Counter) counters.getSelectedItem();
        counter.increase();
        label.setText(Integer.toString(counter.currentValue()));
        frame.pack();
    }
```

Jeżeli program się uruchamia w takiej postaci, opisz jego działanie
i wklej zawartość pliku `CounterApp.java` do sprawozdania.

## Zadanie 4 - własny licznik

Dodaj do projektu nową klasę, `OverflowCounter`, który jest zwiększany
o 1 (tak jak `SimpleCounter`), ale przy osiągnięciu wartości 10
zostaje wyzerowany. Podepnij ten licznik do aplikacji, dodając go
do tablicy przekazywanej do konstruktora klasy `JComboBox`.
