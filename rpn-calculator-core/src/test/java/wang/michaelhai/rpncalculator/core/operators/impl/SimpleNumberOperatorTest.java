package wang.michaelhai.rpncalculator.core.operators.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

import java.util.Collections;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("SimpleNumberOperator")
class SimpleNumberOperatorTest {
    @Mock
    private CalculatorStack stack;

    @Test
    @DisplayName("should push a single number into stack")
    public void testRunSimpleNumberOperator() {
        SimpleNumberOperator simpleNumberOperator = new SimpleNumberOperator(new BigNumber("1"));
        simpleNumberOperator.setCalculatorStack(stack);
        simpleNumberOperator.run();

        verify(stack).modify(0, Collections.singletonList(new BigNumber("1")));
    }
}
