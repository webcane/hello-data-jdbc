package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Builder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Builder
@Table("guess_turn")
public record Turn(
        IGuessNumber guess,
        int bulls,
        int cows,
        OffsetDateTime moveTime,
        @Column("chat_game_key")
        int chatOrdinal,
        @Column("guess_game_key")
        int gameOrdinal) {
}
