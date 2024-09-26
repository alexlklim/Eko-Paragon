package com.alex.eko.paragon.utils.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class LoggingAspect {


    @Pointcut("execution(* com.alex.eko.paragon..*(..)) " +
            "&& !within(com.alex.eko.paragon.security..*)")
    public void applicationPackagePointcut() {
    }

    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String className = getSimpleClassName(joinPoint.getSignature().getDeclaringTypeName());
        String methodName = joinPoint.getSignature().getName();
        log.info("{} - {}", className, methodName);
    }

    @AfterReturning("applicationPackagePointcut()")
    public void logAfterReturning(JoinPoint joinPoint) {
        String className = getSimpleClassName(joinPoint.getSignature().getDeclaringTypeName());
        String methodName = joinPoint.getSignature().getName();
        log.info("{} - {}", className, methodName);
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String className = getSimpleClassName(joinPoint.getSignature().getDeclaringTypeName());
        String methodName = joinPoint.getSignature().getName();
        log.error("Exception in: {} - {} with message: {}", className, methodName, ex.getMessage());
    }

    private String getSimpleClassName(String fullClassName) {
        // Extracts the simple class name without the package path
        String[] parts = fullClassName.split("\\.");
        return parts[parts.length - 1];
    }
}
