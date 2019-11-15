package be.cm.batodama.parkshark.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "be.cm.batodama.parkshark")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
