package wang.michaelhai.rpncalculator.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RPNCalculatorConfiguration.class)
class CalculationServiceImplTest {
    @Autowired
    private CalculationService calculationService;

    @Test
    @DirtiesContext
    public void test1() {
        List<BigNumber> result = calculationService.process("5 2");

        assertEquals(Arrays.asList(new BigNumber("5"), new BigNumber("2")), result);
    }

    @Test
    @DirtiesContext
    public void test2() {
        List<BigNumber> result = calculationService.process("2 sqrt");

        assertEquals(Collections.singletonList(new BigNumber("1.414213562373095")), result);
    }
}
