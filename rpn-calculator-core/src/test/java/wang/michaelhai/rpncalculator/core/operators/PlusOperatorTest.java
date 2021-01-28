package wang.michaelhai.rpncalculator.core.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStack;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PlusOperator")
public class PlusOperatorTest {
    @InjectMocks
    private PlusOperator plusOperator;
    @Mock
    private CalculatorStack stack;

    @Test
    @DisplayName("should be able to pop 2 numbers and push the addition")
    public void testPlusWillPop2NumbersAndPushTheAddition() {
        when(stack.peek(2)).thenReturn(Arrays.asList(new BigNumber("1"), new BigNumber("2")));

        plusOperator.run();

        verify(stack).modify(2, Collections.singletonList(new BigNumber("3")));
    }

    @Test
    @DisplayName("should be able to throw Insufficient parameters exception")
    public void testInsufficientParameters() {
        when(stack.peek(2)).thenReturn(Collections.emptyList());

        assertThrows(InsufficientParametersException.class, plusOperator::run);
    }
}
