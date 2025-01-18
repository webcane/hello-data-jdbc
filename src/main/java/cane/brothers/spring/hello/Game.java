package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Table("guess_game")
class Game {
    @Id
    private UUID gameId;
    private int complexity;
    private IGuessNumber secret;
    @Column("chat_game_key")
    private int ordinal;
    @Transient
    private Chat chatGame;
    private List<Turn> turns;

//    @Modifying
//    public void addTurn(Turn newTurn) {
//        turns.add(newTurn);
//        newTurn.setGuessGame(this);
//    }
}
