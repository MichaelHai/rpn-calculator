package wang.michaelhai.rpncalculator.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RPNCalculatorConfiguration.class)
@DirtiesContext
class CalculationServiceImplTest {
    @Autowired
    private CalculationService calculationService;

    @Test
    public void test1() {
        List<BigDecimal> result = calculationService.process("5 2");

        assertEquals(Arrays.asList(BigDecimal.valueOf(5), BigDecimal.valueOf(2)), result);
    }
}
