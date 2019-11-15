package be.cm.batodama.parkshark.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "be.cm.batodama.parkshark")
@EnableJpaRepositories(basePackages = "be.cm.batodama.parkshark.domain")
@EntityScan(basePackages = "be.cm.batodama.parkshark.domain")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
