package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@Table("guess_game")
class Game {
        @Id
        private UUID gameId;
        private int complexity;
        private IGuessNumber secret;
        private AggregateReference<Chat, UUID> chat;

//    private LinkedList<GuessTurn> turns;
}
