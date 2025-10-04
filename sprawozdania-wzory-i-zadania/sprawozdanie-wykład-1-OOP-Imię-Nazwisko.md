## I. Dokumentacja do wykładu. nr 1 - "Paradygmaty obiektowości"
## II. Imię i nazwisko - grupa ABC, semestr III
## III. Przedmiot - "Programowanie obiektowe"

## IV. Opis zadania do realizacji
Do zrealizowania były następujące zadania:  
  - z pomocą dowolnego języka programowania napisz program, który po uruchomieniu:  
    - wyświetli w kolejności alfabetycznej nazwy czterech filarów OOP,  
    - wyświetli w kolejności losowej nazwy czterech filarów OOP,  
    - w obu ww. przypadkach wskazane jest użycie wbudowanych w dany język programowania metod.  

## V. Technologie wykorzystane w zadaniu
  - Java,  
  - Python

## VI. Realizacja zadania
<br>

#### 1. Kod Javy (lub Pythona)
W zadaniu wykorzystano .... (krótko opisać, co zostało użyte).

Kod wykorzystany do rozwiązania zadania (zadań):  

```java
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
```
- lub można skorzystać z innego języka, np.:  

```python
# Calculate interest to track the growth of an investment


def invest(amount, rate, years):
    """Display year on year growth of an initial investment"""
    for year in range(1, years + 1):
        amount = amount * (1 + rate)
        print(f"year {year}: ${amount:,.2f}")


amount = float(input("Enter a principal amount: "))
rate = float(input("Enter an anual rate of return: "))
years = int(input("Enter a number of years: "))

invest(amount, rate, years)
```

#### 2. Zrzuty ekranu pokazujące wynik działania aplikacji/skryptu:  
![dowolny tekst alterntywny](../images/dandelion.jpg)

#### 3. Inne
  - wpisujemy informacje dotyczące zadania, które uważamy za istotne,  
  - ...