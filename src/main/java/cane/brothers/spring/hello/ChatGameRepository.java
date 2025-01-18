package cane.brothers.spring.hello;

/**
 * A repository fragment to perform custom logic, including a special way to chat games in the database.
 *
 * @author cane
 */
interface ChatGameRepository {

    void saveNewGame(Chat chat);

    void saveTurn(Chat chat);
}
