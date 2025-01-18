package cane.brothers.spring.hello;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Builder
@Table("chat_game")
class Chat {
    @Version
    int version;
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

//    @Modifying
//    public void addGame(Game newGame) {
//        allGames.add(newGame);
//        newGame.setChatGame(this);
//    }
}
