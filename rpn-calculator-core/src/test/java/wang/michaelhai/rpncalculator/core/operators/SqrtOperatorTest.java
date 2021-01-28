package wang.michaelhai.rpncalculator.core.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.BigNumber;
import wang.michaelhai.rpncalculator.core.stack.StackModifier;
import wang.michaelhai.rpncalculator.core.stack.StackPeeker;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("SqrtOperator")
class SqrtOperatorTest {
    @InjectMocks
    private SqrtOperator sqrtOperator;
    @Mock
    private StackPeeker stackPeeker;
    @Mock
    private StackModifier stackModifier;

    @Test
    @DisplayName("should be able to pop 1 numbers and push the sqrt of it")
    public void testPlusWillPop2NumbersAndPushTheAddition() {
        when(stackPeeker.peek(1)).thenReturn(Collections.singletonList(new BigNumber("4")));

        sqrtOperator.run();

        verify(stackModifier).modify(1, Collections.singletonList(new BigNumber("2")));
    }

}
