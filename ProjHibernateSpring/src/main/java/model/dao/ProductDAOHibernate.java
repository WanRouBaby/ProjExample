package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import model.ProductBean;
import model.ProductDAO;

@Component("productDAO")
public class ProductDAOHibernate implements ProductDAO {
	@Autowired
	// @Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

//	public ProductDAOHibernate(SessionFactory sessionFactory) {
//	this.sessionFactory = sessionFactory;
//	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		try {
			//	SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
			//	sessionFactory.getCurrentSession().beginTransaction();
			
			// https://stackoverflow.com/questions/16047829/proxy-cannot-be-cast-to-class

			// scope="singleton"
			ProductDAO productDao = (ProductDAO) context.getBean("productDAO");
			ProductDAO productDao1 = (ProductDAO) context.getBean("productDAO");
			System.out.println(productDao);
			System.out.println(productDao1);
			
			// select
			ProductBean select = productDao.select(1);
			System.out.println(select);

//			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Transactional
	@Override
	public ProductBean select(int id) {
		return this.getSession().get(ProductBean.class, id);
	}

	@Override
	public List<ProductBean> select() {
		return this.getSession().createQuery("FROM ProductBean", ProductBean.class).list();
	}

	@Override
	public ProductBean insert(ProductBean bean) {
		if (bean != null) {
			ProductBean select = this.select(bean.getId());
			if (select == null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public ProductBean update(String name, double price, Date make, int expire, int id) {
		ProductBean bean = this.select(id);
		if (bean != null) {
			bean.setName(name);
			bean.setPrice(price);
			bean.setMake(make);
			bean.setExpire(expire);
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		ProductBean bean = this.select(id);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

}
