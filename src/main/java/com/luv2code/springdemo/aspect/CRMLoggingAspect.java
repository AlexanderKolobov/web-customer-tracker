package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect
{
    private Logger myLogger = Logger.getLogger( getClass().getName() );

    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("within(com.luv2code.springdemo.dao.*)")
    private void forDaoPackage(){}

    @Pointcut( "forControllerPackage() || forServicePackage() || forDaoPackage()" )
    private void forAppFlow() {}

    @Before("forAppFlow()")
    private void before( JoinPoint joinPoint ) {
        String method = joinPoint.getSignature().toShortString();
        myLogger.info( "======>> in @Before: calling method: " + method );

        Object[] args = joinPoint.getArgs();

        Arrays.stream( args ).forEach( o -> myLogger.info( "====>> argument: " + o ) );

    }

    @AfterReturning(pointcut = "forAppFlow()",
                    returning = "result")
    private void afterReturning( JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        myLogger.info( "======>> in @AfterReturning: calling method: " + method );

        myLogger.info( "====>> result: " + result );
    }

}
