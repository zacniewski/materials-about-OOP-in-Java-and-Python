# Pogladowy projekt OOP: Turniej szachowy w Javie

Ten plik pokazuje przykladowa realizacje projektu `Turniej szachowy: parowanie i klasyfikacja` w Javie. Nie jest to kompletna aplikacja produkcyjna, tylko sensowny szkielet edukacyjny, na ktorym da sie cwiczyc:

- klasy abstrakcyjne,
- interfejsy strategii,
- kompozycje obiektow,
- polimorfizm,
- serwisy domenowe,
- organizacje pakietow.

## Cel projektu

Zbudowac prosta aplikacje konsolowa, ktora:

1. przechowuje liste zawodnikow,
2. paruje ich do rund,
3. zapisuje wyniki partii,
4. przelicza tabele turniejowa,
5. drukuje klasyfikacje po kazdej rundzie.

## Proponowana struktura pakietow

```text
src/main/java/
  chess/
    app/
      Main.java
    domain/
      Competitor.java
      Player.java
      Match.java
      MatchResult.java
      Standing.java
    tournament/
      PairingStrategy.java
      SequentialPairingStrategy.java
      ScoringRule.java
      ClassicScoringRule.java
    service/
      TournamentService.java
      ReportService.java
```

## Model domeny

### 1. `Competitor`

Abstrakcyjna klasa bazowa dla uczestnika turnieju.

```java
package chess.domain;

public abstract class Competitor {
    private final String id;
    private final String name;
    private final int rating;

    protected Competitor(String id, String name, int rating) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id cannot be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        if (rating < 0) {
            throw new IllegalArgumentException("rating cannot be negative");
        }
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public abstract String category();
}
```

### 2. `Player`

Najprostsza konkretna implementacja zawodnika.

```java
package chess.domain;

public class Player extends Competitor {
    public Player(String id, String name, int rating) {
        super(id, name, rating);
    }

    @Override
    public String category() {
        return "PLAYER";
    }
}
```

### 3. `MatchResult`

Enum opisujacy wynik partii.

```java
package chess.domain;

public enum MatchResult {
    WHITE_WIN,
    BLACK_WIN,
    DRAW,
    NOT_PLAYED
}
```

### 4. `Match`

Obiekt jednej partii w konkretnej rundzie.

```java
package chess.domain;

public class Match {
    private final int roundNumber;
    private final Player white;
    private final Player black;
    private MatchResult result;

    public Match(int roundNumber, Player white, Player black) {
        if (roundNumber <= 0) {
            throw new IllegalArgumentException("roundNumber must be positive");
        }
        if (white == null || black == null) {
            throw new IllegalArgumentException("players cannot be null");
        }
        if (white.getId().equals(black.getId())) {
            throw new IllegalArgumentException("player cannot play against themselves");
        }
        this.roundNumber = roundNumber;
        this.white = white;
        this.black = black;
        this.result = MatchResult.NOT_PLAYED;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public Player getWhite() {
        return white;
    }

    public Player getBlack() {
        return black;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        if (result == null) {
            throw new IllegalArgumentException("result cannot be null");
        }
        this.result = result;
    }
}
```

### 5. `Standing`

Reprezentacja wyniku zawodnika w klasyfikacji.

```java
package chess.domain;

public class Standing {
    private final Player player;
    private double points;
    private int gamesPlayed;

    public Standing(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public double getPoints() {
        return points;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void addPoints(double pointsToAdd) {
        this.points += pointsToAdd;
        this.gamesPlayed++;
    }
}
```

## Strategie

Tu jest najwazniejszy element OOP tego projektu: algorytmy sa wydzielone do interfejsow i mozna je podmieniac bez przepisywania serwisu turniejowego.

### 1. `PairingStrategy`

```java
package chess.tournament;

import chess.domain.Match;
import chess.domain.Player;
import java.util.List;

public interface PairingStrategy {
    List<Match> createPairings(List<Player> players, int roundNumber);
}
```

### 2. `SequentialPairingStrategy`

Najprostszy wariant: parowanie kolejnych zawodnikow z listy.

```java
package chess.tournament;

import chess.domain.Match;
import chess.domain.Player;
import java.util.ArrayList;
import java.util.List;

public class SequentialPairingStrategy implements PairingStrategy {
    @Override
    public List<Match> createPairings(List<Player> players, int roundNumber) {
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < players.size() - 1; i += 2) {
            matches.add(new Match(roundNumber, players.get(i), players.get(i + 1)));
        }
        return matches;
    }
}
```

### 3. `ScoringRule`

```java
package chess.tournament;

import chess.domain.MatchResult;

public interface ScoringRule {
    double pointsForWhite(MatchResult result);
    double pointsForBlack(MatchResult result);
}
```

### 4. `ClassicScoringRule`

Klasyczne zasady szachowe: wygrana `1.0`, remis `0.5`, przegrana `0.0`.

```java
package chess.tournament;

import chess.domain.MatchResult;

public class ClassicScoringRule implements ScoringRule {
    @Override
    public double pointsForWhite(MatchResult result) {
        return switch (result) {
            case WHITE_WIN -> 1.0;
            case DRAW -> 0.5;
            case BLACK_WIN, NOT_PLAYED -> 0.0;
        };
    }

    @Override
    public double pointsForBlack(MatchResult result) {
        return switch (result) {
            case BLACK_WIN -> 1.0;
            case DRAW -> 0.5;
            case WHITE_WIN, NOT_PLAYED -> 0.0;
        };
    }
}
```

## Serwisy

### `TournamentService`

To klasa spinajaca logike turnieju.

```java
package chess.service;

import chess.domain.Match;
import chess.domain.MatchResult;
import chess.domain.Player;
import chess.domain.Standing;
import chess.tournament.PairingStrategy;
import chess.tournament.ScoringRule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TournamentService {
    private final PairingStrategy pairingStrategy;
    private final ScoringRule scoringRule;
    private final List<Player> players = new ArrayList<>();
    private final List<Match> matches = new ArrayList<>();

    public TournamentService(PairingStrategy pairingStrategy, ScoringRule scoringRule) {
        this.pairingStrategy = pairingStrategy;
        this.scoringRule = scoringRule;
    }

    public void registerPlayer(Player player) {
        players.add(player);
    }

    public List<Match> createRound(int roundNumber) {
        List<Match> roundMatches = pairingStrategy.createPairings(players, roundNumber);
        matches.addAll(roundMatches);
        return List.copyOf(roundMatches);
    }

    public void registerResult(Match match, MatchResult result) {
        match.setResult(result);
    }

    public List<Standing> buildStandings() {
        Map<String, Standing> standings = new HashMap<>();

        for (Player player : players) {
            standings.put(player.getId(), new Standing(player));
        }

        for (Match match : matches) {
            if (match.getResult() == MatchResult.NOT_PLAYED) {
                continue;
            }

            Standing whiteStanding = standings.get(match.getWhite().getId());
            Standing blackStanding = standings.get(match.getBlack().getId());

            whiteStanding.addPoints(scoringRule.pointsForWhite(match.getResult()));
            blackStanding.addPoints(scoringRule.pointsForBlack(match.getResult()));
        }

        return standings.values()
                .stream()
                .sorted(Comparator
                        .comparingDouble(Standing::getPoints).reversed()
                        .thenComparing(s -> s.getPlayer().getRating(), Comparator.reverseOrder())
                        .thenComparing(s -> s.getPlayer().getName()))
                .toList();
    }
}
```

### `ReportService`

```java
package chess.service;

import chess.domain.Match;
import chess.domain.Standing;
import java.util.List;

public class ReportService {
    public void printRound(List<Match> matches) {
        System.out.println("Pairings:");
        for (Match match : matches) {
            System.out.printf(
                    "R%d: %s vs %s%n",
                    match.getRoundNumber(),
                    match.getWhite().getName(),
                    match.getBlack().getName()
            );
        }
    }

    public void printStandings(List<Standing> standings) {
        System.out.println("Standings:");
        int position = 1;
        for (Standing standing : standings) {
            System.out.printf(
                    "%d. %s - %.1f pts (%d games)%n",
                    position++,
                    standing.getPlayer().getName(),
                    standing.getPoints(),
                    standing.getGamesPlayed()
            );
        }
    }
}
```

## Klasa uruchomieniowa

```java
package chess.app;

import chess.domain.Match;
import chess.domain.MatchResult;
import chess.domain.Player;
import chess.service.ReportService;
import chess.service.TournamentService;
import chess.tournament.ClassicScoringRule;
import chess.tournament.SequentialPairingStrategy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TournamentService tournamentService =
                new TournamentService(new SequentialPairingStrategy(), new ClassicScoringRule());
        ReportService reportService = new ReportService();

        tournamentService.registerPlayer(new Player("P1", "Anna Nowak", 1820));
        tournamentService.registerPlayer(new Player("P2", "Jan Kowalski", 1760));
        tournamentService.registerPlayer(new Player("P3", "Maria Zielinska", 1910));
        tournamentService.registerPlayer(new Player("P4", "Piotr Wisniewski", 1680));

        List<Match> round1 = tournamentService.createRound(1);
        reportService.printRound(round1);

        tournamentService.registerResult(round1.get(0), MatchResult.WHITE_WIN);
        tournamentService.registerResult(round1.get(1), MatchResult.DRAW);

        reportService.printStandings(tournamentService.buildStandings());
    }
}
```

## Co ten projekt dobrze cwiczy w Javie

1. `abstract class` kontra `interface`
   - `Competitor` jest dobra baza pod wspolne dane i walidacje.
   - `PairingStrategy` i `ScoringRule` sa kontraktami wymiennych algorytmow.
2. Enkapsulacje
   - pola sa prywatne,
   - stan obiektow zmienia sie kontrolowanymi metodami.
3. Polimorfizm
   - `TournamentService` nie zna konkretnego algorytmu parowania ani punktacji.
4. Podzial odpowiedzialnosci
   - modele przechowuja dane,
   - strategie zawieraja algorytmy,
   - serwisy spinaja przeplyw.

## Mozliwe rozszerzenia

1. Dodac `SwissPairingStrategy`.
2. Dodac abstrakcyjna klase `Tournament`.
3. Dodac zapis i odczyt danych z pliku CSV.
4. Dodac walidacje nieparzystej liczby graczy i obsluge `bye`.
5. Dodac testy jednostkowe dla `ClassicScoringRule` i `TournamentService`.

## Minimalna wersja do oddania przez studenta

Jesli projekt ma byc krotszy, wystarczy:

- `Competitor`, `Player`,
- `Match`, `MatchResult`, `Standing`,
- `PairingStrategy`, `SequentialPairingStrategy`,
- `ScoringRule`, `ClassicScoringRule`,
- `TournamentService`,
- `Main` z jedna runda i tabela.
