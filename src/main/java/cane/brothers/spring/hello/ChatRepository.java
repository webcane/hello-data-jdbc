package cane.brothers.spring.hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface ChatRepository extends CrudRepository<Chat, UUID>, ChatGameRepository {

    Optional<Chat> findByChatId(@Param("chat_id") Long chatId);
}
