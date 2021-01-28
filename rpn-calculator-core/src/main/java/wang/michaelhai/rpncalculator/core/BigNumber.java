package wang.michaelhai.rpncalculator.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public class BigNumber extends BigDecimal {
    private static int MAX_DECIMAL = 15;

    public BigNumber(String s) {
        super(s);
    }

    public BigNumber add(BigNumber another) {
        return delegate(another, this::add);
    }

    public BigNumber subtract(BigNumber another) {
        return delegate(another, this::subtract);
    }

    /**
     * Calculate sqrt of the top element using the Babylonia method
     *
     * @return sqrt root of this BigNumber with maximum of 15 decimal places
     */
    public BigNumber sqrt() {
        int scaleToUse = MAX_DECIMAL + 3;
        BigDecimal next = this.setScale(scaleToUse, RoundingMode.FLOOR); // more scale to make it precision on the last
        BigDecimal previous;
        do {
            previous = new BigDecimal(next.toString());
            previous = previous.setScale(scaleToUse, RoundingMode.FLOOR);
            next = this.divide(previous, scaleToUse, RoundingMode.FLOOR)
                       .add(previous)
                       .divide(BigDecimal.valueOf(2), scaleToUse, RoundingMode.FLOOR);
        } while (!previous.equals(next));

        return new BigNumber(next.setScale(MAX_DECIMAL, RoundingMode.FLOOR).stripTrailingZeros().toString());
    }

    private BigNumber delegate(BigNumber number, Function<BigDecimal, BigDecimal> superFunction) {
        return new BigNumber(superFunction.apply(new BigDecimal(number.toString())).toString());
    }
}
