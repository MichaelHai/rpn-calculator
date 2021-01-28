package wang.michaelhai.rpncalculator.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = RPNCalculatorConfiguration.class)
@ConditionalOnMissingBean(CalculationService.class)
public class RPNCalculatorConfiguration {
}
