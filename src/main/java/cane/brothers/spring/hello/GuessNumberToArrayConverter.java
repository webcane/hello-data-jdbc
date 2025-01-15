package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
class GuessNumberToArrayConverter implements Converter<IGuessNumber, int[]> {
    @Override
    public int[] convert(IGuessNumber guessNumber) {
        return guessNumber.getDigits();
    }
}
