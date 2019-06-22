package ben.learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        log.info("即將開始遊戲");

        SpringApplication.run(Main.class, args);

        // springboot application會自動幫我們打開context
        // ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);
        // context.close();
    }
}
