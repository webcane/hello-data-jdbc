package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Table("guess_game")
class Game {
    @Id
    @Column("game_id")
    private UUID gameId;
    @Column("complexity")
    private int complexity;
    @Column("secret")
    private IGuessNumber secret;
    @Column("ordinal")
    private int ordinal;
    private List<Turn> turns;
}
