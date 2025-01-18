package cane.brothers.spring.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of custom logic for a chat game repository.
 *
 * @author cane
 */
@RequiredArgsConstructor
public class ChatGameRepositoryImpl implements ChatGameRepository {

    private final NamedParameterJdbcOperations template;

    @Override
    @Transactional
    public void newGame(Chat chat, Game game) {

        Map<String, Object> insertParams = new HashMap<>();
        insertParams.put("chat_game", chat);
        insertParams.put("name", "Party Hat");
        template.update("INSERT INTO guess_game (MINION, NAME) VALUES (:id, :name)", insertParams);
        template.update("INSERT INTO guess_game (chat_game, chat_game_key, complexity, secret) VALUES (?, ?, ?, ?)", insertParams);
//
//        Map<String, Object> updateParams = new HashMap<>();
//        updateParams.put("id", minion.id);
//        updateParams.put("version", minion.version);
//        final int updateCount = template.update("UPDATE MINION SET VERSION = :version + 1 WHERE ID = :id AND VERSION = :version", updateParams);
//        if (updateCount != 1) {
//            throw new OptimisticLockingFailureException("Minion was changed before a Party Hat was given");
//        }
    }
}
