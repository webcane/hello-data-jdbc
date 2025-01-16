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
    @Column("complexity")
    private int complexity;
    @Column("secret")
    private IGuessNumber secret;
    @Column("ordinal")
    private int ordinal;
}
