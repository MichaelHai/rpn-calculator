package wang.michaelhai.rpncalculator.commandline;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import wang.michaelhai.rpncalculator.core.CalculationService;
import wang.michaelhai.rpncalculator.core.InvalidOperationException;

import java.io.PrintStream;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommandLineRpnCalculatorApplication implements CommandLineRunner {
    private final CalculationService calculationService;
    private final CommandLinePrinter formatter;

    public static void main(String[] args) {
        SpringApplication.run(CommandLineRpnCalculatorApplication.class, args);
    }

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("RPN calculator started");
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    formatter.printStack(calculationService.process(line));
                } catch (InvalidOperationException e) {
                    formatter.printError(e);
                }
            }
        }
    }
}
