"""
Demonstracja filarów OOP w Pythonie:
- Abstrakcja: ABC (Abstract Base Class)
- Enkapsulacja: konwencje i @property
- Dziedziczenie
- Polimorfizm: wspólne API dla różnych klas + duck typing
"""
from __future__ import annotations
from abc import ABC, abstractmethod
from math import pi
from typing import Iterable


# Abstrakcja: klasa bazowa definiująca kontrakt
class Shape(ABC):
    def __init__(self, name: str) -> None:
        # Enkapsulacja: konwencja z podkreśleniem ("chronione"), w Pythonie to umowa
        self._name = name

    @property
    def name(self) -> str:
        # Kontrolowany dostęp; można dodać walidację w setterze
        return self._name

    @abstractmethod
    def area(self) -> float:
        ...

    def draw(self) -> None:
        # Wspólne zachowanie
        print(f"Rysuję kształt: {self._name}")


class Rectangle(Shape):
    def __init__(self, width: float, height: float) -> None:
        super().__init__("Prostokąt")
        self._width = width
        self._height = height

    @property
    def width(self) -> float:
        return self._width

    @property
    def height(self) -> float:
        return self._height

    def area(self) -> float:
        return self._width * self._height

    def draw(self) -> None:
        print(f"Rysuję prostokąt o bokach {self._width} x {self._height}")


class Circle(Shape):
    def __init__(self, radius: float) -> None:
        super().__init__("Koło")
        self._radius = radius

    @property
    def radius(self) -> float:
        return self._radius

    def area(self) -> float:
        return pi * self._radius * self._radius


# Polimorfizm: funkcja działa dla dowolnych obiektów z metodami draw() i area()
# (duck typing), ale tu używamy typu bazowego Shape

def render_and_report(shapes: Iterable[Shape]) -> None:
    for s in shapes:
        s.draw()
        print(f"{s.name} ma pole: {s.area():.2f}")


if __name__ == "__main__":
    shapes = [Rectangle(3, 4), Circle(2)]
    render_and_report(shapes)
