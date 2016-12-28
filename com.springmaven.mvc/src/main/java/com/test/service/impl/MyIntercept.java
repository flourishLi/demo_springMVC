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
     * ��������㶨�� 
     * ����һ�����������������������ʽ��һ��أ��÷������ٲ���Ҫ���������Ĵ��� 
     */  
    @Pointcut("execution(* com.test.service.impl.StudentServiceImpl.*(..))")  
    public void declareJoinPointerExpression() {}  
  
    //1��ǰ��֪ͨ�� ��Ŀ�귽����ʼ֮ǰִ�У�����Ҫ���߸÷���Ҫ���ĸ����ĸ�����ǰִ�У�  
    //@Before("execution(public int Spring4_AOP.aopAnnotation.*.*(int ,int))")  
    @Before("declareJoinPointerExpression()")  
    public void beforeMethod(JoinPoint joinPoint) {  
        System.out.println("before");  
    }  
  
    //2������֪ͨ����Ŀ�귽��ִ�к������Ƿ����쳣����ִ�е�֪ͨ  
    //ע�⣬�ں���֪ͨ�л����ܷ���Ŀ��ִ�еĽ��!!!��ִ�н����Ҫ������֪ͨ�����  
    //�������ӵ����������ػ����׳��쳣, ����֪ͨ����ִ��. ���ֻ�������ӵ㷵�ص�ʱ���¼��־, Ӧʹ�÷���֪ͨ�������֪ͨ.  
    //@After("execution(* Spring4_AOP.aopAnnotation.*.*(..))")  
    @After("declareJoinPointerExpression()")  
    public void afterMethod(JoinPoint joinPoint){  
        System.out.println("after");  
    }    
  
    //3������֪ͨ:�ڷ�������������ִ�еĴ��룬����֪ͨ�ǿ��Է��ʵ������ķ���ֵ�ģ�����  
    //@AfterReturning(pointcut = "execution(* Spring4_AOP.aopAnnotation.*.*(..))", returning = "result")  
    @AfterReturning(value = "declareJoinPointerExpression()", returning = "result")  
    public void afterReturning(JoinPoint joinPoint, Object result){  
        System.out.println("AfterReturning");  
    }  
  
    //4���쳣֪ͨ����Ŀ�귽�������쳣 ʱ��ִ�еĴ��룬���Է��ʵ��쳣�����ҿ���!!ָ���ڳ����ض��쳣ʱ��ִ��֪ͨ!!,������޸�ΪnullPointerException�ֻ�п�ָ���쳣�Ż�ִ��  
//    @AfterThrowing(pointcut = "execution(* Spring4_AOP.aopAnnotation.*.*(..))", throwing = "except")  
    @AfterThrowing(value = "declareJoinPointerExpression())", throwing = "except")  
    public void afterThrowing(JoinPoint joinPoint, Exception except){  
    	 String methodName = joinPoint.getSignature().getName();  
         System.out.println("The method " + methodName + "  occurs exception " + except);  
    }  

}
