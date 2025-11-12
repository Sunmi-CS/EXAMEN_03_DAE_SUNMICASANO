package com.saborgourmet.restaurante.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

     @Pointcut("execution(* com.saborgourmet.restaurante.controllers..*(..))")
    public void controladores() {}

     @Before("controladores()")
    public void logAntes(JoinPoint joinPoint) {
        System.out.println("➡️ Iniciando método: " + joinPoint.getSignature().toShortString());
    }

     @AfterReturning(pointcut = "controladores()", returning = "resultado")
    public void logDespues(JoinPoint joinPoint, Object resultado) {
        System.out.println("✅ Método completado: " + joinPoint.getSignature().toShortString());
    }

     @AfterThrowing(pointcut = "controladores()", throwing = "error")
    public void logError(JoinPoint joinPoint, Throwable error) {
        System.out.println("❌ Error en: " + joinPoint.getSignature().toShortString() +
                " -> " + error.getMessage());
    }
}
