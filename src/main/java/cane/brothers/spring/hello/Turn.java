package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Builder
@Data
@Table("guess_turn")
public class Turn {
        private IGuessNumber guess;
        private int bulls;
        private int cows;
        private OffsetDateTime moveTime;
        @Column("chat_game_key")
        private int chatOrdinal;
        @Transient
        private Game guessGame;
        @Column("guess_game_key")
        private int gameOrdinal;
}
