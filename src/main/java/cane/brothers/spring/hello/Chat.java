package cane.brothers.spring.hello;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Builder
@Table("chat_game")
record Chat(
        @Id
        UUID id,
        Long chatId,
        Integer lastMessageId
//    @Column("chat_game")
//    private Game currentGame;
) {
}
