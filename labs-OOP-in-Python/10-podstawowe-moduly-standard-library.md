# 10. Podstawowe moduły standard library

## Teoria

### Moduły szczególnie ważne dla OOP
- `dataclasses`
- `collections`
- `datetime`
- `pathlib`
- `typing`
- `copy`
- `enum`

### Dlaczego warto znać standard library?
Bo często gotowe narzędzia rozwiązują problem lepiej niż własna implementacja.

## Przykłady

### Przykład 1 - `dataclasses`

```python
from dataclasses import dataclass


@dataclass
class Product:
    name: str
    price: float
```

### Przykład 2 - `collections.Counter`

```python
from collections import Counter

votes = Counter(["python", "java", "python"])
print(votes["python"])
```

### Przykład 3 - `datetime`

```python
from datetime import date, timedelta

today = date.today()
print(today + timedelta(days=7))
```

### Przykład 4 - `pathlib`

```python
from pathlib import Path

path = Path("data") / "report.txt"
print(path)
```

### Przykład 5 - `copy`

```python
import copy

data = [[1, 2], [3, 4]]
clone = copy.deepcopy(data)
```

## Zadania

1. Utwórz `@dataclass` `Student`.
2. Użyj `Counter` do policzenia wystąpień ocen.
3. Użyj `Path` do zbudowania ścieżki do pliku raportu.
4. Użyj `Enum` do opisania statusu zamówienia.
5. Pokaż różnicę między `copy.copy` i `copy.deepcopy`.

## Checklista

- Czy znasz kilka kluczowych modułów standard library?
- Czy umiesz użyć `dataclass`?
- Czy rozumiesz, kiedy przydaje się `pathlib` i `copy`?
