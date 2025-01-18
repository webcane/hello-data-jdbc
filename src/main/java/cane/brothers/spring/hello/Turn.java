package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Builder
@Data
@Table("guess_turn")
class Turn {
    private IGuessNumber guess;
    private int bulls;
    private int cows;
    private OffsetDateTime moveTime;
    private int ordinal;
}
