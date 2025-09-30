## I. Dokumentacja do lab. nr 1 - "Podstawy WWW" <a id="start"></a>
## II. Imię i nazwisko - grupa ABC, semestr III
## III. Przedmiot - "Programowanie obiektowe"

## IV. Opis zadania do realizacji
Do zrealizowania były następujące zadania:  
  - opis zadania nr 1 ...,  
  - opis zadania nr 1 ...,  
  - opis zadania nr 1 ...,  

## V. Technologie wykorzystane w zadaniu
  - Java,  
  - Python

## VI. Realizacja zadania
<br>

#### 1. Kod HTML
W zadaniu wykorzystano .... (krótko opisać, co z HTMLa zostało użyte).

Przykładowy kod HTML do pokazania (taki, który uważamy za ciekawy, warty pokazania):  

```html
    <html>
        <head>
            <title>Test</title>
        </head>
        <body>
            <!-- Site navigation menu -->
            <ul class="navbar">
              <li><a href="index.html">Home page</a>
              <li><a href="letter.html">Letter</a>
              <li><a href="book.html">Technical book</a>
              <li><a href="form.html">Form</a>
            </ul>
        </body>
```
<br>

#### 2. Zrzuty ekranu pokazujące wynik działania aplikacji/skryptu/strony HTML:  
![obrazek ze zrzutu ekranu](images/dandelion.jpg)

#### 2a. Struktura projektu:  
![struktura-projektu](images/struktura.png)

<br>

#### 3. Kod CSS (taki, który uważamy za ciekawy, warty pokazania). 
W zadaniu wykorzystano .... (krótko opisać, co z CSSa zostało użyte).

Przykładowy kod CSS do pokazania (taki, który uważamy za ciekawy, warty zaprezentowania):  

```css
  caption {
    caption-side: bottom;
    padding: 10px;
    font-weight: bold;
    color: red;
  }
```
<br>

#### 4. Kod Javascript
W zadaniu wykorzystano .... (krótko opisać, co z JSa zostało użyte).

Przykładowy kod JS do pokazania (taki, który uważamy za ciekawy, warty zaprezentowania):  

```js
const d = new Date();
document.getElementById("demo").innerHTML = d.getFullYear();
```

## VII. Dodatkowe informacje o zadaniu
Można tu wpisać informację o elementach, które nie zostały wymienione we wcześniejszych punktach.  

#### 1. Tabele w Markdownie

| Syntax        | Description     |
|:-------------:|:---------------:|
|    Header     |      Title      |
|   Paragraph   |      Text       |
<br>

#### 2. Linki
  - do stron: What is a [markup language](https://www.semrush.com/blog/markup-language/)?
  - do innych sekcji w dokumencie: 
      - w miejscu do którego chcemy się przenieść tworzymy znacznik `a` z atrybutem `id`, czyli np. `<a id="start"></a>`,  
      - tworzymy link do ww. znacznika za pomocą składni `[tekst linku](#id-w-znaczniku-a)`, w naszym przypadku może to być np. [Link do początku dokumentacji](#start).  
<br>
  
#### 3. Inne
  - wpisujemy informacje dotyczące projektu, które uważamy za istotne,  
  - ...