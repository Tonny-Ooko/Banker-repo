package com.bank.masking.aspect;

import com.bank.masking.config.MaskingProperties;
import com.bank.masking.core.MaskingReflectionProcessor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class LoggingMaskingAspect {

    private final MaskingProperties properties;

    public LoggingMaskingAspect(MaskingProperties properties) {
        this.properties = properties;
    }

    @Around("execution(* org.slf4j.Logger.info(..))")
    public Object maskLogArguments(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] maskedArgs = Arrays.stream(joinPoint.getArgs())
                .map(arg -> MaskingReflectionProcessor.process(arg, properties))
                .toArray();

        return joinPoint.proceed(maskedArgs);
    }
}
