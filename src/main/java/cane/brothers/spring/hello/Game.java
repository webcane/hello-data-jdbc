package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Builder
@Table("guess_game")
record Game(
        @Id
        UUID gameId,
        int complexity,
        IGuessNumber secret,
        AggregateReference<Chat, UUID> chat) {

//    private LinkedList<GuessTurn> turns;
}
