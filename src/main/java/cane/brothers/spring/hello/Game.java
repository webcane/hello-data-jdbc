package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;

@Data
@Table("guess_game")
class Game {
    @Id
    private UUID gameId;
    private int complexity;
    private IGuessNumber secret;
    private int ordinal;
    private SortedSet<Turn> turns = new TreeSet<>(Comparator.comparingInt(Turn::getOrdinal));

    /**
     * Save constructor
     *
     * @param secret the secret number
     */
    Game(IGuessNumber secret) {
        this.complexity = secret.getComplexity();
        this.secret = secret;
    }

    /**
     * Load constructor
     *
     * @param gameId db entity id
     * @param complexity complexity
     * @param secret secret
     * @param ordinal ordinal of a game
     * @param turns collection of turns
     */
    @PersistenceCreator
    private Game(UUID gameId, int complexity, IGuessNumber secret, int ordinal, Collection<Turn> turns) {
        this.gameId = gameId;
        this.complexity = complexity;
        this.secret = secret;
        this.ordinal = ordinal;
        this.turns.addAll(turns);
    }

//    @Modifying
//    public void addTurn(Turn newTurn) {
//        turns.add(newTurn);
//        newTurn.setGuessGame(this);
//    }

    public Turn getLastTurn() {
        return turns.last();
    }
}
