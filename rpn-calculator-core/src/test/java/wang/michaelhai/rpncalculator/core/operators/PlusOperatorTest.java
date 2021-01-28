package wang.michaelhai.rpncalculator.core.operators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
    private StackPeeker stackPeeker;
    @Mock
    private StackModifier stackModifier;

    @Test
    @DisplayName("should be able to pop 2 numbers and push the addition")
    public void testPlusWillPop2NumbersAndPushTheAddition() {
        when(stackPeeker.peek(2)).thenReturn(Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(2)));

        plusOperator.run();

        verify(stackModifier).modify(2, Collections.singletonList(BigDecimal.valueOf(3)));
    }

    @Test
    @DisplayName("should be able to throw Insufficient parameters exception")
    public void testInsufficientParameters() {
        when(stackPeeker.peek(2)).thenReturn(Collections.emptyList());

        assertThrows(InsufficientParametersException.class, plusOperator::run);
    }
}
