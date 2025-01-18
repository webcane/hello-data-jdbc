package cane.brothers.spring.hello;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

/**
 * Created by cane
 */
@Slf4j
@Component
@RequiredArgsConstructor
class ChatGameInitLoader {

    private final ChatRepository chatRepo;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        long chatId = 111L;

        Chat chat = chatRepo.findByChatId(chatId)
                .orElseGet(() -> chatRepo.save(new Chat(chatId)));
        log.info(chat.toString());


        // new game
        Game newGame = new Game(new GuessNumber(new int[]{1, 2, 3, 4}));
        chat.addNewGame(newGame);
        chatRepo.saveNewGame(chat);

        chat = chatRepo.findByChatId(chatId).orElseThrow();
        log.info(chat.toString());

        //Game curentGame = chat.getCurrentGame();
        Turn turn = Turn.builder()
                .guess(new GuessNumber(new int[]{2, 3, 4, 0}))
                .bulls(0)
                .cows(3)
                .moveTime(OffsetDateTime.now())
                .build();
        //curentGame.getTurns().add(turn);
        //chat = chatRepo.save(chat);
        chat.addTurn(turn);
        chatRepo.saveTurn(chat);
        log.info(chat.toString());

        chat = chatRepo.findByChatId(chatId).orElseThrow();
        log.info(chat.toString());

        // curentGame = chat.getCurrentGame();
        turn = Turn.builder()
                .guess(new GuessNumber(new int[]{3, 4, 5, 6}))
                .bulls(0)
                .cows(2)
                .moveTime(OffsetDateTime.now())
                .build();
        //   curentGame.getTurns().add(turn);
        chat.addTurn(turn);
        chatRepo.saveTurn(chat);

        //  chat = chatRepo.save(chat);
        // log.info(chat.toString());
        chat = chatRepo.findByChatId(chatId).orElseThrow();
        log.info(chat.toString());
    }
}
