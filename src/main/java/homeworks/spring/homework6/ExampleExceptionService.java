package homeworks.spring.homework6;

import org.springframework.stereotype.Service;

@Service
public class ExampleExceptionService {
    @RecoverException(noRecoverFor = {IllegalArgumentException.class})
    public double exampleMethod(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Делить на ноль нельзя!!!!");
        }
        return a / b;
    }
}
