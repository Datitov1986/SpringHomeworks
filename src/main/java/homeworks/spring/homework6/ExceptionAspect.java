package homeworks.spring.homework6;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class ExceptionAspect {

    @Around("@annotation(RecoverException) && execution(* *(..))")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            RecoverException annotation = ((MethodSignature) joinPoint
                    .getSignature())
                    .getMethod()
                    .getAnnotation(RecoverException.class);
            if (Arrays.stream(annotation.noRecoverFor()).anyMatch(ex -> ex.isInstance(e))) {
                throw e;
            } else {
                return defaultValueForReturnType(joinPoint.getSignature().getDeclaringType());
            }
        }
    }

    private Object defaultValueForReturnType(Class<?> returnType) {
        if (returnType.equals(Void.TYPE)) {
            return null;
        } else if (returnType.equals((Boolean.TYPE))) {
            return false;
        } else if (returnType.equals(Character.TYPE)) {
            return '\u0000';
        } else if (returnType.equals(Byte.TYPE)
                || returnType.equals(Short.TYPE)
                || returnType.equals(Integer.TYPE)
                || returnType.equals(Long.TYPE)
                || returnType.equals(Float.TYPE)
                || returnType.equals(Double.TYPE)) {
            return 0;
        } else {
            return null;
        }
    }
}
