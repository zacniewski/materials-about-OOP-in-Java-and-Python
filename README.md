# Materials about OOP in Java and Python
Wstęp do programowania zorientowanego obiektowego z wykorzystaniem języków Java i Python


## 🛠 Narzędzia i Technologie
Projekt wykorzystuje języki Java i Python. Celem jest zapoznanie użytkowników z językami Java i Python oraz skupienie się na progamowaniu zorientowanym obiektowo.  


### 01. Instalacja i uruchomienie programu 'Hello world'
- oficjalna strona [Pythona](https://www.python.org/),  
- oficjalna strona [Javy](https://www.java.com/).

Po poprawnej instalacji powinien wyświetlić się numer wersji:  
```sh
# dla Javy
$ java -version
openjdk version "21.0.7" 2025-04-15
OpenJDK Runtime Environment (build 21.0.7+6-Ubuntu-0ubuntu124.04)
OpenJDK 64-Bit Server VM (build 21.0.7+6-Ubuntu-0ubuntu124.04, mixed mode, sharing)

# dla Pythona (Linux)
$ python3 --version
Python 3.12.3

# dla Pythona (Windows)
$ python --version
Python 3.12.3
```
- uruchamianie programów:

```sh
# dla Pythona
$ python3 01-hello-world.py 
Hello world!


# dla Javy (na początek niewłaściwa nazwa)
$ javac 01-hello-world.java 
01-hello-world.java:1: error: class Main is public, should be declared in a file named Main.java

# po zmianie nazwy pliku, na taką samą jak nazwa klasy
# `javac` to kompilator języka Java
$ javac Main.java 
$ java Main
Hello World