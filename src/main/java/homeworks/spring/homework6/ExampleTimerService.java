package homeworks.spring.homework6;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ExampleTimerService {

    @Timer
    public void exampleMethod(int a, int b) {
        long result = 1;
        for (int i = 1; i < a; i++) {
            result *= i;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a / b);
        System.out.println(result);

    }
}
