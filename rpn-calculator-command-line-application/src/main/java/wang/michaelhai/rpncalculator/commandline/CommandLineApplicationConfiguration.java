package wang.michaelhai.rpncalculator.commandline;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintStream;

@Configuration
public class CommandLineApplicationConfiguration {
    @Bean
    public PrintStream printStream() {
        return System.out;
    }
}
