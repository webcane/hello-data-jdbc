package cane.brothers.spring.hello;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.LinkedList;

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
        long chatId = 111L;

        final Chat newChat = Chat.builder()
                .chatId(chatId)
                .allGames(new LinkedList<>())
                .build();
        Chat chat = chatRepo.findByChatId(chatId)
                .orElseGet(() -> chatRepo.save(newChat));
        log.info(chat.toString());

        // new game
        Game newGame = Game.builder()
                .complexity(4)
                .secret(new GuessNumber(new int[]{1, 2, 3, 4}))
                .turns(new LinkedList<>())
                .build();
        chat.getAllGames().add(newGame);
        chat = chatRepo.save(chat);

        Game curentGame = chat.getCurrentGame().get();
        Turn turn = Turn.builder()
                .guess(new GuessNumber(new int[]{2, 3, 4, 0}))
                .bulls(0)
                .cows(3)
                .moveTime(OffsetDateTime.now())
                .build();
        curentGame.getTurns().add(turn);
        chat = chatRepo.save(chat);
        log.info(chat.toString());

        curentGame = chat.getCurrentGame().get();
        turn = Turn.builder()
                .guess(new GuessNumber(new int[]{3, 4, 5, 6}))
                .bulls(0)
                .cows(2)
                .moveTime(OffsetDateTime.now())
                .build();
        curentGame.getTurns().add(turn);
        chat = chatRepo.save(chat);
        log.info(chat.toString());
    }
}
