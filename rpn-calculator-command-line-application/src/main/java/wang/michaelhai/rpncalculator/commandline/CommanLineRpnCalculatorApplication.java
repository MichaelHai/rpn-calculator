package wang.michaelhai.rpncalculator.commandline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CommanLineRpnCalculatorApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CommanLineRpnCalculatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("RPN calculator started");
    }
}
