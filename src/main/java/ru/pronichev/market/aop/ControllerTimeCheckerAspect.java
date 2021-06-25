package ru.pronichev.market.aop;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ControllerTimeCheckerAspect {

    private StopWatch stopWatch;

    @Before("execution(public * ru.pronichev.market.services..*(..))")
    public void allMethodsStartCheckTime(JoinPoint joinPoint) {
        stopWatch = createStopWatch(getTaskName(joinPoint));
        stopWatch.start();
    }

    @After("execution(public * ru.pronichev.market.services..*(..))")
    public void allMethodsEndCheckTime() {
        stopWatch.stop();
        log.info(stopWatch.shortSummary());
    }

    private String getTaskName(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        return String.format("Controller: %s, method: %s",
                signature.getDeclaringType().getSimpleName(),
                signature.getMethod().getName()
        );
    }

    private StopWatch createStopWatch(String id) {
        return new StopWatch(id) {
            @Override
            @NonNull
            public String shortSummary() {
                try {
                    return String.format("%s completed for %d ms", super.shortSummary(), getLastTaskTimeMillis());
                } catch (IllegalStateException e) {
                    return super.shortSummary();
                }
            }
        };
    }
} 