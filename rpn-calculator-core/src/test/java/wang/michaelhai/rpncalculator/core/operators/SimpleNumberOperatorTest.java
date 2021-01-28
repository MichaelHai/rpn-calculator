package wang.michaelhai.rpncalculator.core.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.stack.StackModifier;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("SimpleNumberOperator")
class SimpleNumberOperatorTest {
    @Mock
    private StackModifier modifier;

    @Test
    @DisplayName("should push a single number into stack")
    public void testRunSimpleNumberOperator() {
        SimpleNumberOperator simpleNumberOperator = new SimpleNumberOperator(modifier, BigDecimal.ONE);
        simpleNumberOperator.run();

        verify(modifier).modify(0, Collections.singletonList(BigDecimal.ONE));
    }
}
