package cane.brothers.spring.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.sql.JDBCType;

/**
 * Implementation of custom logic for a chat game repository.
 *
 * @author cane
 */
@RequiredArgsConstructor
class ChatGameRepositoryImpl implements ChatGameRepository {

    private final NamedParameterJdbcOperations template;

    private final GuessNumberSQLType guessSqlType = new GuessNumberSQLType();

    @Override
    @Transactional
    public void saveNewGame(Chat chat) {
        MapSqlParameterSource insertParams = new MapSqlParameterSource();
        insertParams.addValue("id", chat.getId(), JDBCType.OTHER.getVendorTypeNumber());
        insertParams.addValue("ordinal", chat.getAllGames().size(), JDBCType.INTEGER.getVendorTypeNumber());
        insertParams.addValue("complexity", chat.getCurrentGame().getComplexity(), JDBCType.INTEGER.getVendorTypeNumber());
        insertParams.addValue("secret", chat.getCurrentGame().getSecret().getDigits(), guessSqlType.getVendorTypeNumber(), guessSqlType.getName());
        template.update("INSERT INTO guess_game (chat_game, ordinal, complexity, secret) VALUES (:id, :ordinal, :complexity, :secret)", insertParams);

        MapSqlParameterSource updateParams = new MapSqlParameterSource();
        updateParams.addValue("id", chat.getId(), JDBCType.OTHER.getVendorTypeNumber());
        updateParams.addValue("version", chat.getVersion(), JDBCType.INTEGER.getVendorTypeNumber());
        final int updateCount = template.update("UPDATE chat_game SET version = :version + 1 WHERE id = :id AND version = :version", updateParams);
        if (updateCount != 1) {
            var msg = String.format("chat game %d was changed before a new guess game was given", chat.getChatId());
            throw new OptimisticLockingFailureException(msg);
        }
    }

    @Override
    @Transactional
    public void saveTurn(Chat chat) {
        var currentGame = chat.getCurrentGame();
        var lastTurn = currentGame.getLastTurn();
        MapSqlParameterSource insertParams = new MapSqlParameterSource();
        insertParams.addValue("bulls", lastTurn.getBulls(), JDBCType.INTEGER.getVendorTypeNumber());
        insertParams.addValue("cows", lastTurn.getCows(), JDBCType.INTEGER.getVendorTypeNumber());
        insertParams.addValue("guess", lastTurn.getGuess().getDigits(), guessSqlType.getVendorTypeNumber(), guessSqlType.getName());
        insertParams.addValue("game_id", currentGame.getGameId(), JDBCType.OTHER.getVendorTypeNumber());
        insertParams.addValue("move_time", lastTurn.getMoveTime(), JDBCType.TIMESTAMP_WITH_TIMEZONE.getVendorTypeNumber());
        insertParams.addValue("ordinal", currentGame.getTurns().size(), JDBCType.INTEGER.getVendorTypeNumber());
        template.update("INSERT INTO guess_turn (bulls, cows, guess, guess_game, move_time, ordinal) VALUES (:bulls, :cows, :guess, :game_id, :move_time, :ordinal)", insertParams);

        MapSqlParameterSource updateParams = new MapSqlParameterSource();
        updateParams.addValue("id", chat.getId(), JDBCType.OTHER.getVendorTypeNumber());
        updateParams.addValue("version", chat.getVersion(), JDBCType.INTEGER.getVendorTypeNumber());
        final int updateCount = template.update("UPDATE chat_game SET version = :version + 1 WHERE id = :id AND version = :version", updateParams);
        if (updateCount != 1) {
            var msg = String.format("chat game %d was changed before a new game turn was given", chat.getChatId());
            throw new OptimisticLockingFailureException(msg);
        }
    }
}
