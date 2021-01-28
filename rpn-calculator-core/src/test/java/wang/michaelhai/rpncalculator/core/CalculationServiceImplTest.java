package wang.michaelhai.rpncalculator.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static wang.michaelhai.rpncalculator.core.TestUtils.assertBigNumberList;

@SpringBootTest(classes = RPNCalculatorConfiguration.class)
class CalculationServiceImplTest {
    @Autowired
    private CalculationService calculationService;

    @Test
    @DirtiesContext
    public void scenario1() {
        List<BigNumber> result = calculationService.process("5 2");

        assertBigNumberList(result, "5", "2");
    }

    @Test
    @DirtiesContext
    public void scenario2() {
        List<BigNumber> result = calculationService.process("2 sqrt");

        assertBigNumberList(result, "1.414213562373095");

        result = calculationService.process("clear 9 sqrt");

        assertBigNumberList(result, "3");
    }

    @Test
    @DirtiesContext
    public void scenario3() {
        List<BigNumber> result = calculationService.process("5 2 -");

        assertBigNumberList(result ,"3");

        result = calculationService.process("3 -");

        assertBigNumberList(result, "0");

        result = calculationService.process("clear");

        assertTrue(result.isEmpty());
    }

    @Test
    @DirtiesContext
    public void scenario4() {
        List<BigNumber> result = calculationService.process("5 4 3 2");

        assertBigNumberList(result, "5", "4", "3", "2");

        result = calculationService.process("undo undo *");

        assertBigNumberList(result, "20");

        result = calculationService.process("5 *");

        assertBigNumberList(result, "100");

        result = calculationService.process("undo");

        assertBigNumberList(result, "20", "5");
    }
}
