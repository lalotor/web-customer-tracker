package com.luv2code.springdemo.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

  private Logger myLogger = Logger.getLogger(getClass().getName());

  @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
  private void forControllerPackage() {}

  @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
  private void forServicePackage() {}

  @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
  private void forDaoPackage() {}

  @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
  private void forAppFlow() {}

  @Before("forAppFlow()")
  public void before(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().toShortString();
    myLogger.info("In @Before calling method = " + method);

    Arrays.stream(joinPoint.getArgs()).forEach(arg -> myLogger.info("Arg = " + arg));
  }

  @AfterReturning(
      pointcut = "forAppFlow()",
      returning = "result"
  )
  public void afterReturningFindAccountAdvice(JoinPoint joinPoint, Object result) {
    String method = joinPoint.getSignature().toShortString();
    myLogger.info("In @AfterReturning on method = " + method);

    myLogger.info("result = " + result);
  }

}