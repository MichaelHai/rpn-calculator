package wang.michaelhai.rpncalculator.core.operators.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("SqrtOperator")
class SqrtOperatorTest {
    private SqrtOperator sqrtOperator;
    @Mock
    private CalculatorStack stack;

    @BeforeEach
    public void setup() {
        sqrtOperator = new SqrtOperator();
        sqrtOperator.setCalculatorStack(stack);
    }

    @Test
    @DisplayName("should be able to pop 1 numbers and push the sqrt of it")
    public void testSqrtWillPop2NumbersAndPushTheAddition() {
        when(stack.peek(1)).thenReturn(Collections.singletonList(new BigNumber("4")));

        sqrtOperator.run();

        verify(stack).modify(1, Collections.singletonList(new BigNumber("2")));
    }

    @Test
    @DisplayName("should throw exception when input is negative")
    public void testNegative() {
        when(stack.peek(1)).thenReturn(Collections.singletonList(new BigNumber("-4")));

        assertThrows(SqrtOnNegativeException.class, sqrtOperator::run);
    }

}
