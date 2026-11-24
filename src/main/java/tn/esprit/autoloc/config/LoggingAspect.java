package tn.esprit.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // Avant chaque méthode de la couche service
    @Before("execution(* tn.esprit.autoloc.services.*.*(..))")
    public void logAvantMethode(JoinPoint joinPoint) {
        String nomMethode = joinPoint.getSignature().getName();
        Object[] parametres = joinPoint.getArgs();

        log.info("[LOGGING] Appel de la méthode : {}", nomMethode);
        log.info("[LOGGING] Paramètres : {}", Arrays.toString(parametres));
    }

    // Après chaque méthode de la couche service
    @After("execution(* tn.esprit.autoloc.services.*.*(..))")
    public void logApresMethode(JoinPoint joinPoint) {
        log.info("[LOGGING] Fin d'exécution de la méthode : {}", joinPoint.getSignature().getName());
    }
}