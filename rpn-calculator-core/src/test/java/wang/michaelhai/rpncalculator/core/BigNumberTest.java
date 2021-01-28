package wang.michaelhai.rpncalculator.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BigNumber")
class BigNumberTest {
    @Test
    @DisplayName("should be able to add")
    public void testAdd() {
        BigNumber number1 = new BigNumber("1");
        BigNumber number2 = new BigNumber("2");

        assertEquals(new BigNumber("3"), number1.add(number2));
    }

    @ParameterizedTest
    @MethodSource("testSqrt")
    @DisplayName("should be able to sqrt")
    public void testSqrt(String input, String expect) {
        BigNumber number = new BigNumber(input);
        assertEquals(new BigNumber(expect), number.sqrt());
    }

    public static Stream<Arguments> testSqrt() {
        return Stream.of(
            Arguments.of("4", "2"),
            Arguments.of("3", "1.732050807568877"),
            Arguments.of("5", "2.236067977499789"),
            Arguments.of("1.44", "1.2")
        );
    }

}
