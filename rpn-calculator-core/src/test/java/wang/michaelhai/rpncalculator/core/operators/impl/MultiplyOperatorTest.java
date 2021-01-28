package wang.michaelhai.rpncalculator.core.operators.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("MultiplyOperator")
class MultiplyOperatorTest {
    @Test
    @DisplayName("should pop 2 values and push the result")
    public void test(@Mock CalculatorStack stack) {
        when(stack.peek(2)).thenReturn(Arrays.asList(new BigNumber("3"), new BigNumber("2")));

        MultiplyOperator multiplyOperator = new MultiplyOperator();
        multiplyOperator.setCalculatorStack(stack).run();

        verify(stack).modify(2, Collections.singletonList(new BigNumber("6")));
    }
}
