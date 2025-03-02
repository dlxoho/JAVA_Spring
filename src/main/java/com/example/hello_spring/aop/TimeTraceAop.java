package com.example.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {

  // 메인 비즈니스 로직 말고 덜 중요한(?) 관심사를 별도로 모듈화하여 관리
  // MemberController 는 프록시 MemberServiceController 를 호출하게 된다. ( 라라벨 미들웨어랑 비슷한듯? 하다 )
  // Aspect : 흩어진 관심사를 모듈화
  // Target : Aspect 를 적용하는곳 (@Around)
  // Advice : 어떤 로직이 실행되는지 구현체
  // JoinPoint : Advice가 적용될 위치, 시점
  // PointCut : JoinPoint의 상세스펙을 정의한 것.
  @Around("execution(* hello.hellospring..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    System.out.println("Start:" + start);
    try {
      Object result = joinPoint.proceed();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("End:" + joinPoint.toString() + " " + timeMs + "ms");
    }
    return null;
  }

}
