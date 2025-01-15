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
        // clean-up
        for (Game game : gameRepo.findAll()) {
            gameRepo.delete(game);
        }
        for (Chat chat : chatRepo.findAll()) {
            chatRepo.delete(chat);
        }

        // new game
        Chat newChat = Chat.builder().chatId(123L).build();
        newChat = chatRepo.save(newChat);
        log.info(newChat.toString());

        Game newGame = Game.builder()
                .complexity(4).secret(new GuessNumber(new int[]{9, 8, 7, 6}))
                .chat(AggregateReference.to(newChat.id()))
                .build();
        newGame = gameRepo.save(newGame);
        log.info(newGame.toString());

        gameRepo.findByChat(newChat)
                .ifPresentOrElse(a -> log.info("a: " + a), null);
    }


}
