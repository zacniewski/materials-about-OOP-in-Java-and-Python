# Poglądowy projekt OOP: Turniej szachowy w Pythonie

Ten plik pokazuje ten sam pomysł projektowy co wersja 'javowa', ale zapisany po 'pythonowemu'. Celem nie jest wierne kopiowanie stylu Javy, tylko pokazanie, jak te same decyzje obiektowe przenieść do Pythona:

- klasy bazowe,
- protokoły lub klasy strategii,
- serwisy domenowe,
- małe, czytelne modele,
- prosty scenariusz uruchomieniowy.

## Cel projektu

Zbudować aplikację konsolową, która:

1. rejestruje graczy,
2. tworzy pary na rundę,
3. zapisuje wyniki partii,
4. buduje klasyfikację,
5. drukuje stan turnieju.

## Proponowana struktura katalogów

```text
chess/
  app.py
  domain.py
  strategies.py
  services.py
```

Można też rozbić to na więcej plików, ale do nauki Python OOP taka uproszczona struktura jest wystarczająca.

## Model domeny

### `Competitor`

W Pythonie dobrze sprawdza się klasa bazowa z `ABC`.

```python
from abc import ABC, abstractmethod


class Competitor(ABC):
    def __init__(self, player_id: str, name: str, rating: int) -> None:
        if not player_id.strip():
            raise ValueError("player_id cannot be blank")
        if not name.strip():
            raise ValueError("name cannot be blank")
        if rating < 0:
            raise ValueError("rating cannot be negative")

        self._id = player_id
        self._name = name
        self._rating = rating

    @property
    def player_id(self) -> str:
        return self._id

    @property
    def name(self) -> str:
        return self._name

    @property
    def rating(self) -> int:
        return self._rating

    @abstractmethod
    def category(self) -> str:
        raise NotImplementedError
```

### `Player`

```python
class Player(Competitor):
    def category(self) -> str:
        return "PLAYER"
```

### `MatchResult`

```python
from enum import Enum


class MatchResult(Enum):
    WHITE_WIN = "WHITE_WIN"
    BLACK_WIN = "BLACK_WIN"
    DRAW = "DRAW"
    NOT_PLAYED = "NOT_PLAYED"
```

### `Match`

W Pythonie warto użyć `dataclass`, ale z walidacją w `__post_init__`.

```python
from dataclasses import dataclass, field


@dataclass
class Match:
    round_number: int
    white: Player
    black: Player
    result: MatchResult = field(default=MatchResult.NOT_PLAYED)

    def __post_init__(self) -> None:
        if self.round_number <= 0:
            raise ValueError("round_number must be positive")
        if self.white.player_id == self.black.player_id:
            raise ValueError("player cannot play against themselves")
```

### `Standing`

```python
from dataclasses import dataclass


@dataclass
class Standing:
    player: Player
    points: float = 0.0
    games_played: int = 0

    def add_points(self, value: float) -> None:
        self.points += value
        self.games_played += 1
```

## Strategie

Tutaj też warto utrzymać ten sam pomysł co w Javie: algorytmy są wymiennymi obiektami.

### `PairingStrategy`

```python
from abc import ABC, abstractmethod


class PairingStrategy(ABC):
    @abstractmethod
    def create_pairings(self, players: list[Player], round_number: int) -> list[Match]:
        raise NotImplementedError
```

### `SequentialPairingStrategy`

```python
class SequentialPairingStrategy(PairingStrategy):
    def create_pairings(self, players: list[Player], round_number: int) -> list[Match]:
        matches: list[Match] = []
        for index in range(0, len(players) - 1, 2):
            matches.append(Match(round_number, players[index], players[index + 1]))
        return matches
```

### `ScoringRule`

```python
class ScoringRule(ABC):
    @abstractmethod
    def points_for_white(self, result: MatchResult) -> float:
        raise NotImplementedError

    @abstractmethod
    def points_for_black(self, result: MatchResult) -> float:
        raise NotImplementedError
```

### `ClassicScoringRule`

```python
class ClassicScoringRule(ScoringRule):
    def points_for_white(self, result: MatchResult) -> float:
        if result == MatchResult.WHITE_WIN:
            return 1.0
        if result == MatchResult.DRAW:
            return 0.5
        return 0.0

    def points_for_black(self, result: MatchResult) -> float:
        if result == MatchResult.BLACK_WIN:
            return 1.0
        if result == MatchResult.DRAW:
            return 0.5
        return 0.0
```

## Serwisy

### `TournamentService`

```python
class TournamentService:
    def __init__(self, pairing_strategy: PairingStrategy, scoring_rule: ScoringRule) -> None:
        self._pairing_strategy = pairing_strategy
        self._scoring_rule = scoring_rule
        self._players: list[Player] = []
        self._matches: list[Match] = []

    def register_player(self, player: Player) -> None:
        self._players.append(player)

    def create_round(self, round_number: int) -> list[Match]:
        round_matches = self._pairing_strategy.create_pairings(self._players, round_number)
        self._matches.extend(round_matches)
        return list(round_matches)

    def register_result(self, match: Match, result: MatchResult) -> None:
        match.result = result

    def build_standings(self) -> list[Standing]:
        standings = {
            player.player_id: Standing(player=player)
            for player in self._players
        }

        for match in self._matches:
            if match.result == MatchResult.NOT_PLAYED:
                continue

            standings[match.white.player_id].add_points(
                self._scoring_rule.points_for_white(match.result)
            )
            standings[match.black.player_id].add_points(
                self._scoring_rule.points_for_black(match.result)
            )

        return sorted(
            standings.values(),
            key=lambda item: (-item.points, -item.player.rating, item.player.name),
        )
```

### `ReportService`

```python
class ReportService:
    def print_round(self, matches: list[Match]) -> None:
        print("Pairings:")
        for match in matches:
            print(f"R{match.round_number}: {match.white.name} vs {match.black.name}")

    def print_standings(self, standings: list[Standing]) -> None:
        print("Standings:")
        for index, standing in enumerate(standings, start=1):
            print(
                f"{index}. {standing.player.name} - "
                f"{standing.points:.1f} pts ({standing.games_played} games)"
            )
```

## Przykladowe uruchomienie

```python
def main() -> None:
    tournament_service = TournamentService(
        pairing_strategy=SequentialPairingStrategy(),
        scoring_rule=ClassicScoringRule(),
    )
    report_service = ReportService()

    tournament_service.register_player(Player("P1", "Anna Nowak", 1820))
    tournament_service.register_player(Player("P2", "Jan Kowalski", 1760))
    tournament_service.register_player(Player("P3", "Maria Zielinska", 1910))
    tournament_service.register_player(Player("P4", "Piotr Wisniewski", 1680))

    round_one = tournament_service.create_round(1)
    report_service.print_round(round_one)

    tournament_service.register_result(round_one[0], MatchResult.WHITE_WIN)
    tournament_service.register_result(round_one[1], MatchResult.DRAW)

    report_service.print_standings(tournament_service.build_standings())


if __name__ == "__main__":
    main()
```

## Co ten projekt dobrze ćwiczy w Pythonie

1. Różnice między stylem 'javowym' i 'pythonowym'
   - Python pozwala napisać krótszy model, ale nadal można zachować czysty podział odpowiedzialności.
2. Klasy bazowe abstrakcyjne
   - `ABC` dobrze nadaje się do pokazywania kontraktów.
3. `dataclass`
   - Dobrze pasuje do prostych modeli typu `Match` i `Standing`.
4. Strategia jako obiekt
   - To nadal ten sam wzorzec co w Javie, tylko zapisany krocej.

## Rozszerzenia

1. Dodać `SwissPairingStrategy`.
2. Dodać obsługę nieparzystej liczby graczy i `bye`.
3. Dodać eksport tabeli do CSV.
4. Dodać testy w `pytest`.
5. Rozbić projekt na osobne pliki per klasa.

## Minimalna wersja do oddania przez studenta

Wystarczy:

- `Competitor`, `Player`,
- `MatchResult`, `Match`, `Standing`,
- `PairingStrategy`, `SequentialPairingStrategy`,
- `ScoringRule`, `ClassicScoringRule`,
- `TournamentService`,
- `main()`.
