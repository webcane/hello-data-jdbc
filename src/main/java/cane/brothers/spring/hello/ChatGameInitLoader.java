package cane.brothers.spring.hello;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by cane
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatGameInitLoader {

    private final ChatRepository chatRepo;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {

        long chatId = 123L;

        // new game
        Set<Game> allGames = new LinkedHashSet<>();
        final Chat newChat = Chat.builder()
                .chatId(chatId)
                .allGames(allGames)
                .build();
        Chat chat = chatRepo.findByChatId(chatId)
                .orElseGet(() -> chatRepo.save(newChat));
        log.info(chat.toString());

        var ordinal = chat.getMaxOrdinal();

        Game newGame = Game.builder()
                .ordinal(++ordinal)
                .complexity(4).secret(new GuessNumber(new int[]{1, 2, 3, 4}))
                .build();

        chat.getAllGames().add(newGame);
        chat = chatRepo.save(chat);
        log.info(chat.toString());

        Game newGame2 = Game.builder()
                .ordinal(++ordinal)
                .complexity(4).secret(new GuessNumber(new int[]{9, 8, 7, 6}))
                .build();

        chat.getAllGames().add(newGame2);
        chat = chatRepo.save(chat);
        log.info(chat.toString());
    }
}
