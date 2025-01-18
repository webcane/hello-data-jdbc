package cane.brothers.spring.hello;

/**
 * A repository fragment to perform custom logic, including a special way to chat games in the database.
 *
 * @author cane
 */
public interface ChatGameRepository {

    void newGame(Chat chat, Game game);
}
