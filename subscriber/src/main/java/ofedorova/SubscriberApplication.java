package ofedorova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SubscriberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriberApplication.class, args);
    }
}
