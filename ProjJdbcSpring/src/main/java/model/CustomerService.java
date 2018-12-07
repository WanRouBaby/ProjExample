package model;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		CustomerService customerService = (CustomerService) context.getBean("customerService");
		CustomerBean bean = customerService.login("Alex", "A");
		System.out.println("bean = " + bean);
		System.out.println("Password = " + new String(bean.getPassword(), "UTF-8"));
		((ConfigurableApplicationContext) context).close();
	}

	@Autowired
	private CustomerDAO customerDao;

	public boolean changePassword(String username, String oldPassword, String newPassword) {
		CustomerBean bean = this.login(username, oldPassword);
		if (bean != null) {
			if (newPassword != null && newPassword.length() != 0) {
				byte[] temp = newPassword.getBytes(); // user input
				return customerDao.update(temp, bean.getEmail(), bean.getBirth(), username);
			}
		}
		return false;
	}

	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerDao.select(username);
		if (bean != null) {
			if (password != null && password.length() != 0) {
				byte[] temp = password.getBytes(); // user input
				byte[] pass = bean.getPassword(); // database
				if (Arrays.equals(pass, temp)) {
					return bean;
				}
			}
		}
		return null;
	}
}
