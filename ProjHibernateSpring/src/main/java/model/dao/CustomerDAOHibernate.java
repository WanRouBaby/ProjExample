package model.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import model.CustomerBean;
import model.CustomerDAO;

@Component("customerDAO")
public class CustomerDAOHibernate implements CustomerDAO {
	@Autowired
	private SessionFactory sessionFactory;

//	public CustomerDAOHibernate(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		try {
			// SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
			// sessionFactory.getCurrentSession().beginTransaction();

			// https://stackoverflow.com/questions/16047829/proxy-cannot-be-cast-to-class

			CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

			// select
			CustomerBean select = customerDAO.select("Alex");
			System.out.println(select);

//			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Transactional
	@Override
	public CustomerBean select(String custid) {
		return this.getSession().get(CustomerBean.class, custid);
	}

	@Override
	public boolean update(byte[] password, String email, Date birth, String custid) {
		CustomerBean bean = this.select(custid);
		if (bean != null) {
			bean.setPassword(password);
			bean.setEmail(email);
			bean.setBirth(birth);
			// this.getSession().save(bean);
			return true;
		}
		return false;
	}

}
