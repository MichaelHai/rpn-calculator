package wang.michaelhai.rpncalculator.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wang.michaelhai.rpncalculator.core.TestUtils.assertBigNumberList;

@SpringBootTest(classes = RPNCalculatorConfiguration.class)
class CalculationServiceImplTest {
    @Autowired
    private CalculationService calculationService;

    @ParameterizedTest
    @MethodSource
    @DirtiesContext
    public void scenarioTest(Stream<TestStep> steps) {
        steps.forEach(
            step -> {
                try {
                    List<BigNumber> result = calculationService.process(step.getInput());
                    assertBigNumberList(result, step.getStackStatus());
                } catch (InvalidOperationException e) {
                    assertEquals(step.getErrorOperator(), e.getErrorOperator());
                    assertEquals(step.getErrorPosition(), e.getErrorPosition());
                    assertBigNumberList(e.getStackStatus(), step.getStackStatus());
                }
            }
        );
    }

    public static Stream<Arguments> scenarioTest() {
        return Stream.of(
            Arguments.of(scenario1()),
            Arguments.of(scenario2()),
            Arguments.of(scenario3()),
            Arguments.of(scenario4()),
            Arguments.of(scenario5()),
            Arguments.of(scenario6()),
            Arguments.of(scenario7()),
            Arguments.of(scenario8())
        );
    }

    private static Stream<TestStep> scenario1() {
        return Stream.of(TestStep.of("5 2", "5", "2"));
    }

    private static Stream<TestStep> scenario2() {
        return Stream.of(
            TestStep.of("2 sqrt", "1.414213562373095"),
            TestStep.of("clear 9 sqrt", "3")
        );
    }

    private static Stream<TestStep> scenario3() {
        return Stream.of(
            TestStep.of("5 2 -", "3"),
            TestStep.of("3 -", "0"),
            TestStep.of("clear")
        );
    }

    private static Stream<TestStep> scenario4() {
        return Stream.of(
            TestStep.of("5 4 3 2", "5", "4", "3", "2"),
            TestStep.of("undo undo *", "20"),
            TestStep.of("5 *", "100"),
            TestStep.of("undo", "20", "5")
        );
    }

    private static Stream<TestStep> scenario5() {
        return Stream.of(
            TestStep.of("7 12 2 /", "7", "6"),
            TestStep.of("*", "42"),
            TestStep.of("4 /", "10.5")
        );
    }

    private static Stream<TestStep> scenario6() {
        return Stream.of(
            TestStep.of("1 2 3 4 5", "1", "2", "3", "4", "5"),
            TestStep.of("*", "1", "2", "3", "20"),
            TestStep.of("clear 3 4 -", "-1")
        );
    }

    private static Stream<TestStep> scenario7() {
        return Stream.of(
            TestStep.of("1 2 3 4 5", "1", "2", "3", "4", "5"),
            TestStep.of("* * * *", "120")
        );
    }

    private static Stream<TestStep> scenario8() {
        return Stream.of(
            TestStep.of("1 2 3 * 5 + * * 6 5", "*", 14, "11")
        );
    }


    @Data
    private static class TestStep {
        private String input;
        private String[] stackStatus;
        private String errorOperator;
        private int errorPosition;


        public TestStep(String input, String[] stackStatus) {
            this.input = input;
            this.stackStatus = stackStatus;
        }

        public static TestStep of(String input, String... stackStatus) {
            return new TestStep(input, stackStatus);
        }

        public static TestStep of(String input, String errorOperator, int errorPosition, String... stackStatus) {
            TestStep step = TestStep.of(input, stackStatus);
            step.errorOperator = errorOperator;
            step.errorPosition = errorPosition;
            return step;
        }
    }
}
