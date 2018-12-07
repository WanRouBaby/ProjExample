package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAdvice {

	@Pointcut(value = "execution(* aop.DemoAOP.method*(..))")
	private void pointcut1() {
	}

	@Before(value = "pointcut1()")
	public void logBefore(JoinPoint point) {
		System.out.println("before : calling " + point.getTarget().getClass() + "." + point.getSignature().getName()
				+ "() wuth argument " + point.getArgs()[0]);
	}

	@Around(value = "pointcut1()")
	public Object logAround(ProceedingJoinPoint pPoint) throws Throwable {
		System.out.println("around : calling " + pPoint.getTarget().getClass() + "." + pPoint.getSignature().getName()
				+ "() wuth argument " + pPoint.getArgs()[0]);
		Object result = pPoint.proceed();
		System.out.println("around : result = " + result);
		return result;
	}

	@AfterReturning(value = "pointcut1()", returning = "result")
	public void logAfter(JoinPoint point, Object result) {
		System.out.println("after returing : result = " + result);
	}

	@AfterThrowing(value = "pointcut1()", throwing = "exception")
	public void logThrow(JoinPoint point, Throwable exception) {
		System.out.println("after throwing : exception = " + exception);
	}
}
