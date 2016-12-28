package com.test.service.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyIntercept {
	  /** 
     * 重用切入点定义 
     * 定义一个方法，用于声明切入点表达式，一般地，该方法中再不需要添入其它的代码 
     */  
    @Pointcut("execution(* com.test.service.impl.StudentServiceImpl.*(..))")  
    public void declareJoinPointerExpression() {}  
  
    //1、前置通知： 在目标方法开始之前执行（就是要告诉该方法要在哪个类哪个方法前执行）  
    //@Before("execution(public int Spring4_AOP.aopAnnotation.*.*(int ,int))")  
    @Before("declareJoinPointerExpression()")  
    public void beforeMethod(JoinPoint joinPoint) {  
        System.out.println("before");  
    }  
  
    //2、后置通知：在目标方法执行后（无论是否发生异常），执行的通知  
    //注意，在后置通知中还不能访问目标执行的结果!!!，执行结果需要到返回通知里访问  
    //无论连接点是正常返回还是抛出异常, 后置通知都会执行. 如果只想在连接点返回的时候记录日志, 应使用返回通知代替后置通知.  
    //@After("execution(* Spring4_AOP.aopAnnotation.*.*(..))")  
    @After("declareJoinPointerExpression()")  
    public void afterMethod(JoinPoint joinPoint){  
        System.out.println("after");  
    }    
  
    //3、返回通知:在方法正常结束后执行的代码，返回通知是可以访问到方法的返回值的！！！  
    //@AfterReturning(pointcut = "execution(* Spring4_AOP.aopAnnotation.*.*(..))", returning = "result")  
    @AfterReturning(value = "declareJoinPointerExpression()", returning = "result")  
    public void afterReturning(JoinPoint joinPoint, Object result){  
        System.out.println("AfterReturning");  
    }  
  
    //4、异常通知：在目标方法出现异常 时会执行的代码，可以访问到异常对象：且可以!!指定在出现特定异常时在执行通知!!,如果是修改为nullPointerException里，只有空指针异常才会执行  
//    @AfterThrowing(pointcut = "execution(* Spring4_AOP.aopAnnotation.*.*(..))", throwing = "except")  
    @AfterThrowing(value = "declareJoinPointerExpression())", throwing = "except")  
    public void afterThrowing(JoinPoint joinPoint, Exception except){  
    	 String methodName = joinPoint.getSignature().getName();  
         System.out.println("The method " + methodName + "  occurs exception " + except);  
    }  

}
