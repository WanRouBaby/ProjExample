package i18n;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoBean implements MessageSourceAware {

	@SuppressWarnings("unused")
	private MessageSource bundle;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.bundle = messageSource;
	}

//	public void method() {
//		String message1 = bundle.getMessage("program.error", null, Locale.TAIWAN);
//		String message2 = bundle.getMessage("program.error", null, Locale.US);
//	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		String message1 = context.getMessage("program.error", null, Locale.TAIWAN);
		String message2 = context.getMessage("program.error", null, Locale.US);
		System.out.println(message1);
		System.out.println(message2);
		((ConfigurableApplicationContext) context).close();
	}
}
