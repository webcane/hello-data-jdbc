package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Table("guess_turn")
public record Turn(
        @Id
        @Column("turn_id")
        UUID turnId,
        @Column("guess")
        IGuessNumber guess,
        @Column("bulls")
        int bulls,
        @Column("cows")
        int cows,
        @Column("ordinal")
        int ordinal,
        @Column("move_time")
        OffsetDateTime turnTime) {
}
