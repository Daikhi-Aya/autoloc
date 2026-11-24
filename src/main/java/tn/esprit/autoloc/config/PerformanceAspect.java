package tn.esprit.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    private static final long SEUIL_MS = 1000;

    @Around("execution(* tn.esprit.autoloc.services.*.*(..))")
    public Object mesurerTempsExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String nomMethode = joinPoint.getSignature().getName();
        long debut = System.currentTimeMillis();

        try {
            // exécute la vraie méthode et retourne son résultat
            return joinPoint.proceed();
        } finally {
            long duree = System.currentTimeMillis() - debut;
            log.info("[PERFORMANCE] La méthode {} s'est exécutée en {} ms", nomMethode, duree);

            if (duree > SEUIL_MS) {
                log.warn("[PERFORMANCE] ATTENTION : la méthode {} est lente ({} ms > {} ms)",
                        nomMethode, duree, SEUIL_MS);
            }
        }
    }
}