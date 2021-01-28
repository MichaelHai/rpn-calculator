package wang.michaelhai.rpncalculator.core.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import wang.michaelhai.rpncalculator.core.operators.impl.PlusOperator;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("OperatorFactoryImpl")
@ExtendWith(MockitoExtension.class)
class OperatorFactoryImplTest {
    @InjectMocks
    OperatorFactoryImpl operatorFactory;

    @Test
    @DisplayName("should create PlusOperator for +")
    public void testPlus() {
        Operator operator = operatorFactory.create("+");
        assertTrue(operator instanceof PlusOperator);
    }
}
