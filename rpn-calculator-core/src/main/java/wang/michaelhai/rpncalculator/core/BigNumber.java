package wang.michaelhai.rpncalculator.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public class BigNumber extends BigDecimal {
    private static final int MAX_DECIMAL = 15;

    public BigNumber(String s) {
        super(s);
    }

    public BigNumber add(BigNumber another) {
        return delegate(another, this::add);
    }

    public BigNumber subtract(BigNumber another) {
        return delegate(another, this::subtract);
    }

    public BigNumber multiply(BigNumber another) {
        return delegate(another, this::multiply);
    }

    public BigNumber divide(BigNumber another) {
        return delegate(
            another,
            (toDivide) -> this.divide(toDivide, MAX_DECIMAL, RoundingMode.FLOOR).stripTrailingZeros()
        );
    }

    /**
     * Calculate sqrt of the top element using the Babylonia method
     *
     * @return sqrt root of this BigNumber with maximum of 15 decimal places
     */
    public BigNumber sqrt() {
        BigDecimal next = this.setScale(MAX_DECIMAL, RoundingMode.FLOOR);
        BigDecimal previous;
        do {
            previous = new BigDecimal(next.toString());
            previous = previous.setScale(MAX_DECIMAL, RoundingMode.FLOOR);
            next = this.divide(previous, MAX_DECIMAL, RoundingMode.FLOOR)
                       .add(previous)
                       .divide(BigDecimal.valueOf(2), MAX_DECIMAL, RoundingMode.FLOOR);
        } while (!previous.equals(next));

        return new BigNumber(next.stripTrailingZeros().toString());
    }

    private BigNumber delegate(BigNumber number, Function<BigDecimal, BigDecimal> superFunction) {
        return new BigNumber(superFunction.apply(new BigDecimal(number.toString())).toString());
    }
}
