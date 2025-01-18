package cane.brothers.spring.hello;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;

/**
 * Chat guess game root aggregate
 */
@Data
@Table("chat_game")
class Chat {
    @Version
    int version;
    @Id
    private UUID id;
    private Long chatId;
    private Integer lastMessageId;
    private SortedSet<Game> allGames = new TreeSet<>(Comparator.comparingInt(Game::getOrdinal));

    Chat(Long chatId) {
        this.chatId = chatId;
    }

    @PersistenceCreator
    private Chat(UUID id, Long chatId, Integer lastMessageId, Collection<Game> allGames, int version) {
        this.id = id;
        this.chatId = chatId;
        this.lastMessageId = lastMessageId;
        this.allGames.addAll(allGames);
        this.version = version;
    }

    public Game getCurrentGame() {
        return allGames.last();
    }

    public boolean addNewGame(Game newGame) {
        return getAllGames().add(newGame);
    }

    public boolean addTurn(Turn newTurn) {
        return getCurrentGame().getTurns().add(newTurn);
    }


}
