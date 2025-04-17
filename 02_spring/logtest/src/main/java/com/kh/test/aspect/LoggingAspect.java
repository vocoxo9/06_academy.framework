package com.kh.test.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect		// Aspect 선언
@Component	// 빈 등록
public class LoggingAspect {
	
	// 롬복 없이 로그 객체 사용. Logger 사용
	private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	// * 기본 패키지 경로 내의 모든 메소드를 대상
	@Pointcut("execution(* com.kh.test..*.*(..))")
	private void pointcut() {}
	
	// * 호출 전 메소드명, 파라미터 정보를 로그로 출력
	@Before("pointcut()")
	public void beforeAdvice(JoinPoint joinPoint) {
		// MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		// Method method = methodSignature.getMethod();
		// Object[] parameter = joinPoint.getArgs();
		
		// log.info("============= Before Advide =============");
		// log.info("method info :: {}", methodSignature);
		log.info("method name :: {}", joinPoint.getSignature().getName());
		log.info("parameters :: {}", Arrays.toString(joinPoint.getArgs()));
	}
	
	// * 메소드 실행 시간 출력
	@Around("pointcut()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();	// 실행 전 시간 측정
		
		Object execute = joinPoint.proceed();
		
		long end = System.currentTimeMillis();		// 실행 후 시간 측정
		
		// log.info("============= Around Advide =============");
		log.info("execution time :: {}", (end-start));
		
		return execute;
	}
}
