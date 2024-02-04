package homeworks.spring.homework6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ExceptionAspect aspect = context.getBean(ExceptionAspect.class);
        ExampleTimerService time = context.getBean(ExampleTimerService.class);
        ExampleExceptionService exception = context.getBean(ExampleExceptionService.class);

        exception.exampleMethod(4,0);

        time.exampleMethod(10,5);

    }
}
