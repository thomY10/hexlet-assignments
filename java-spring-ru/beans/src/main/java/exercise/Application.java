package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;


// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @Scope("prototype")
    public Daytime getDayTime() { // имя метода не важно

        int hour = LocalDateTime.now().getHour();

        if (hour >= 6 && hour <= 22) {
            return new Day();
        } else {
            return new Night();
        }
    }
    // END
}
