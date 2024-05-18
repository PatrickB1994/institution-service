package com.mdsl.institutionservice.shared;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectControl
{
	@Pointcut("within(com.mdsl.institutionservice.controller..*)")
	private void applicationControllers()
	{
	}

	@Pointcut("within(com.mdsl.institutionservice.service..*)")
	private void applicationServices()
	{
	}

	@Before("applicationControllers()")
	private void logControllerEntry(JoinPoint joinPoint)
	{
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		log.info("Enter Method {}.{} Params: {}", className, joinPoint.getSignature().getName(), joinPoint.getArgs());
	}

	@AfterReturning(value = "applicationServices()", returning = "result")
	private void logControllerReturn(JoinPoint joinPoint, Object result)
	{
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		log.info("Return Method {}.{} Result: {}.", className, joinPoint.getSignature().getName(), result);
	}

	@Before("applicationServices()")
	private void logServiceEntry(JoinPoint joinPoint)
	{
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		log.trace("Enter Method {}.{} Params: {}", className, joinPoint.getSignature().getName(), joinPoint.getArgs());
	}

	@AfterReturning(value = "applicationControllers()", returning = "result")
	private void logServiceReturn(JoinPoint joinPoint, Object result)
	{
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		log.trace("Return Method {}.{} Result: {}.", className, joinPoint.getSignature().getName(), result);
	}
}
