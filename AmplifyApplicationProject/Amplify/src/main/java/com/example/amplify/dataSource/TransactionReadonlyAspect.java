package com.example.amplify.dataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
@Aspect
@Component
@Order(0)
public class TransactionReadonlyAspect {

    @Around("@annotation(transactional)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, org.springframework.transaction.annotation.Transactional transactional) throws Throwable {

        try {
            if (transactional.readOnly()) {
                DatabaseContextHolder.setContext(DatabaseEnviroment.READONLY);
            }

            return proceedingJoinPoint.proceed();
        } finally {
            DatabaseContextHolder.reset();
        }

    }
}
*/