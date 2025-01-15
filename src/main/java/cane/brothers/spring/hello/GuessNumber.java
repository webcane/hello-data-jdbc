package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;

record GuessNumber(int[] digits) implements IGuessNumber {

    @Override
    public int getComplexity() {
        return digits.length;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int[] getDigits() {
        return this.digits;
    }
}
