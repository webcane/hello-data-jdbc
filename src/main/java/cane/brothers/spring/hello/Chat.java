package cane.brothers.spring.hello;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@Table("chat_game")
class Chat {
    @Id
    private UUID id;
    private Long chatId;
    private Integer lastMessageId;
    private AggregateReference<Game, UUID> game;
}
