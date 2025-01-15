package cane.brothers.spring.hello;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;

@Data
@Builder
@Table("chat_game")
class Chat {
    @Id
    private UUID id;
    private Long chatId;
    private Integer lastMessageId;
    private List<Game> allGames;

    public Optional<Game> getCurrentGame() {
        return allGames == null ? Optional.empty() :
                allGames.stream()
                        .max(Comparator.comparing(Game::getOrdinal));
    }

    public int getMaxOrdinal() {
        return allGames == null ? 0 : getCurrentGame().map(Game::getOrdinal).orElse(0);
    }
}
