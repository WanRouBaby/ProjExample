package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAOP {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		DemoAOP bean = (DemoAOP) context.getBean("demoAOP");
		System.out.println(bean.method1("hahahahaha"));
		((ConfigurableApplicationContext) context).close();
	}
}
