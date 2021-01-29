package wang.michaelhai.rpncalculator.core.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.operators.impl.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OperatorFactoryImpl")
@ExtendWith(MockitoExtension.class)
class OperatorFactoryImplTest {
    @InjectMocks
    OperatorFactoryImpl operatorFactory;

    @ParameterizedTest
    @MethodSource
    @DisplayName("should create proper object")
    public void test(String input, Class<?> expectedType) {
        Operator operator = operatorFactory.create(input);
        assertEquals(expectedType, operator.getClass());
    }

    public static Stream<Arguments> test() {
        return Stream.of(
            Arguments.of("+", PlusOperator.class),
            Arguments.of("-", MinusOperator.class),
            Arguments.of("*", MultiplyOperator.class),
            Arguments.of("/", DivideOperator.class),
            Arguments.of("sqrt", SqrtOperator.class),
            Arguments.of("undo", UndoOperator.class),
            Arguments.of("clear", ClearOperator.class),
            Arguments.of("123", SimpleNumberOperator.class),
            Arguments.of("123.123", SimpleNumberOperator.class),
            Arguments.of("-123", SimpleNumberOperator.class)
        );
    }

    @Test
    @DisplayName("should throw error for invalid token")
    public void testInvalidToken() {
        assertThrows(UnsupportedTokenException.class, () -> operatorFactory.create("abcd"));
    }
}
