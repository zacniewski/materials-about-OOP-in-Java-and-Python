"""
Moduł demonstracyjny do rozdziału "Klasy i metody" (Python).

Pokazuje:
- atrybuty instancji i klasy,
- docstringi modułu/klasy/metod (PEP 257),
- __init__, @property z walidacją, @classmethod, @staticmethod,
- prostą reprezentację __repr__ i __str__.
"""
from __future__ import annotations

from dataclasses import dataclass


class Product:
    """Reprezentuje produkt w sklepie.

    Atrybuty:
        name: Nazwa produktu (niepusta).
        net_price: Cena netto (>= 0).
        vat_rate: Stawka VAT (0..1). Domyślnie DEFAULT_VAT.
    """

    # Atrybut klasowy (stała wg konwencji):
    DEFAULT_VAT: float = 0.23

    # Prosty licznik instancji (dla przykładu metody @classmethod)
    _COUNTER: int = 0

    def __init__(self, name: str, net_price: float, vat_rate: float | None = None) -> None:
        """Inicjalizuje nowy obiekt Product.

        Args:
            name: Nazwa (niepusta).
            net_price: Cena netto (>= 0).
            vat_rate: Stawka VAT (0..1). Gdy None, używana jest DEFAULT_VAT.
        """
        self.name = name  # przejdzie przez setter z walidacją
        self.net_price = net_price  # setter
        self.vat_rate = self.DEFAULT_VAT if vat_rate is None else vat_rate  # setter
        type(self)._COUNTER += 1

    # Enkapsulacja przez właściwości:
    @property
    def name(self) -> str:
        """Nazwa produktu (niepusta)."""
        return self._name

    @name.setter
    def name(self, value: str) -> None:
        if not value or not value.strip():
            raise ValueError("name is blank")
        self._name = value

    @property
    def net_price(self) -> float:
        """Cena netto (>= 0)."""
        return self._net_price

    @net_price.setter
    def net_price(self, value: float) -> None:
        if value < 0:
            raise ValueError("net_price < 0")
        self._net_price = float(value)

    @property
    def vat_rate(self) -> float:
        """Stawka VAT (0..1)."""
        return self._vat_rate

    @vat_rate.setter
    def vat_rate(self, value: float) -> None:
        if not (0 <= value <= 1):
            raise ValueError("vat_rate out of range")
        self._vat_rate = float(value)

    def gross_price(self) -> float:
        """Zwraca cenę brutto liczona na podstawie bieżących pól instancji."""
        return self.net_price * (1.0 + self.vat_rate)

    @staticmethod
    def gross_of(net: float, vat: float) -> float:
        """Statyczna funkcja pomocnicza do liczenia ceny brutto.

        Args:
            net: Cena netto (>= 0).
            vat: Stawka VAT (0..1).
        """
        if net < 0:
            raise ValueError("net < 0")
        if not (0 <= vat <= 1):
            raise ValueError("vat out of range")
        return float(net) * (1.0 + float(vat))

    @classmethod
    def created_count(cls) -> int:
        """Zwraca liczbę utworzonych instancji Product."""
        return cls._COUNTER

    def __repr__(self) -> str:  # debugowa, nieformalna
        return f"Product(name={self.name!r}, net_price={self.net_price!r}, vat_rate={self.vat_rate!r})"

    def __str__(self) -> str:  # przyjazna
        return f"Product '{self.name}' netto={self.net_price:.2f}, VAT={self.vat_rate:.0%}"


if __name__ == "__main__":
    """Prosty pokaz użycia.

    Uruchom: python3 Python/03/product.py
    """
    apple = Product("Jabłko", 2.50)
    cheese = Product("Ser", 10.0, 0.08)

    print(apple)
    print("Brutto (apple):", apple.gross_price())

    print(cheese)
    print("Brutto (cheese):", cheese.gross_price())

    print("Brutto (100, default VAT):", Product.gross_of(100, Product.DEFAULT_VAT))
    print("Utworzono produktów:", Product.created_count())
