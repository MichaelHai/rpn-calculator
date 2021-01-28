package wang.michaelhai.rpncalculator.core.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

import static org.mockito.Mockito.verify;

@DisplayName("Clear operator will clear the stack")
@ExtendWith(MockitoExtension.class)
class ClearOperatorTest {
    @Test
    @DisplayName("should clear the stack")
    public void testClear(@Mock CalculatorStack stack) {
        ClearOperator clearOperator = new ClearOperator();
        clearOperator.setCalculatorStack(stack);

        clearOperator.run();

        verify(stack).clear();
    }
}
