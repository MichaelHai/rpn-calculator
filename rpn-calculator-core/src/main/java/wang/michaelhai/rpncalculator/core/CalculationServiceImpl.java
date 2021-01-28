package wang.michaelhai.rpncalculator.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.michaelhai.rpncalculator.core.operators.Operator;
import wang.michaelhai.rpncalculator.core.operators.OperatorFactory;
import wang.michaelhai.rpncalculator.core.stack.CalculatorStackService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CalculationServiceImpl implements CalculationService {
    private final OperatorFactory operatorFactory;
    private final CalculatorStackService stackService;

    @Override
    public List<BigDecimal> process(String input) {
        String[] tokens = input.trim().split("\\s+");
        log.debug("Start processing input: {}", Arrays.toString(tokens));
        Arrays.stream(tokens)
              .map(operatorFactory::create)
              .forEach(Operator::run);
        return stackService.listAll();
    }
}
