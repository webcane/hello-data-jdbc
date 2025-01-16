package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("guess_game")
class Game {
    private int complexity;
    private IGuessNumber secret;
    @Column("chat_game_key")
    private int ordinal;
}
