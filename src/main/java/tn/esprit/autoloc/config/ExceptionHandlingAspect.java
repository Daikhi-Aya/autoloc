package tn.esprit.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionHandlingAspect {

    @AfterThrowing(
            pointcut = "execution(* tn.esprit.autoloc.services.*.*(..))",
            throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        log.error("[EXCEPTION] Méthode concernée : {}", joinPoint.getSignature().getName());
        log.error("[EXCEPTION] Type de l'exception : {}", exception.getClass().getName());
        log.error("[EXCEPTION] Message d'erreur : {}", exception.getMessage());
    }
}
