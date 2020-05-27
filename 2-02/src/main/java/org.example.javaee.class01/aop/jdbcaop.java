package org.example.javaee.class01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Order(1) //设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component
public class jdbcaop {

    @Pointcut("execution(public * org.example.javaee.class01.jdbc.BlogMapper.*(..))")
    public void jdbcLog(){}

    @Before("jdbcLog()")
    public void beforMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("this method "+methodName+" begin. param<"+ args+">");
    }

    @After("jdbcLog()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("this method "+methodName+" end.");
    }

    @AfterReturning(value="jdbcLog()",returning="result")
    public void afterReturnMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("this method "+methodName+" end.result<"+result+">");
    }

    @AfterThrowing(value="jdbcLog()",throwing="ex")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("this method "+methodName+" end.ex message<"+ex+">");
    }

    @Around(value="jdbcLog()")
    public Object aroundMethod(ProceedingJoinPoint point){

        Object result = null;
        String methodName = point.getSignature().getName();
        try {
            //前置通知
            System.out.println("The method "+ methodName+" start. param<"+ Arrays.asList(point.getArgs())+">");
            //执行目标方法
            result = point.proceed();
            //返回通知
            System.out.println("The method "+ methodName+" end. result<"+ result+">");
        } catch (Throwable e) {
            //异常通知
            System.out.println("this method "+methodName+" end.ex message<"+e+">");
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("The method "+ methodName+" end.");
        return result;
    }

}
