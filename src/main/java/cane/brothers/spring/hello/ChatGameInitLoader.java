package cane.brothers.spring.hello;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;

/**
 * Created by cane
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatGameInitLoader {

    private final ChatRepository chatRepo;
    private final GameRepository gameRepo;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {

        // new game
        final Chat newChat = Chat.builder().chatId(123L).build();
        Chat chat = chatRepo.findByChatId(123L)
                .orElseGet(() -> chatRepo.save(newChat));
        log.info(chat.toString());

        Game newGame = Game.builder()
                .complexity(4).secret(new GuessNumber(new int[]{9, 8, 7, 6}))
                .chat(AggregateReference.to(chat.getId()))
                .build();
        newGame = gameRepo.save(newGame);
        log.info(newGame.toString());

        chat.setGame(AggregateReference.to(newGame.getGameId()));
        chat = chatRepo.save(chat);
        log.info(chat.toString());

        newGame = Game.builder()
                .complexity(4).secret(new GuessNumber(new int[]{1, 2, 3, 4}))
                .chat(AggregateReference.to(chat.getId()))
                .build();
        newGame = gameRepo.save(newGame);
        log.info(newGame.toString());

        chat.setGame(AggregateReference.to(newGame.getGameId()));
        chat = chatRepo.save(chat);
        log.info(chat.toString());

        gameRepo.findByChat(chat)
                .forEach(a -> log.info("a: " + a));
    }


}
