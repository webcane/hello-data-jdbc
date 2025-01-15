package cane.brothers.spring.hello;


import cane.brothers.game.IGuessNumber;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
class ArrayToGuessNumberConverter implements Converter<Integer[], IGuessNumber> {

    @Override
    public IGuessNumber convert(Integer[] digits) {
        int[] d = ArrayUtils.toPrimitive(digits);
        return new GuessNumber(d);
    }
}
