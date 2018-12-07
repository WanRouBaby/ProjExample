package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component(value = "productService")
@Transactional
public class ProductService {
	@Autowired
	private ProductDAO productDao;

	// public ProductService(ProductDAO productDao) {
	// this.productDao = productDao;
	// }

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		try {
//			SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
//			sessionFactory.getCurrentSession().beginTransaction();

			ProductService productService = (ProductService) context.getBean("productService");

			// select
			List<ProductBean> select = productService.select(null);
			System.out.println(select);

//			productDao.update("B", 20.0, new Date(), 2, 1);
			ProductBean bean = new ProductBean();
			bean.setId(1);
			bean.setName("AAA");
			bean.setPrice(11.0);
			bean.setMake(new Date());
			bean.setExpire(1);
//			System.out.println("bean = " + productService.update(bean));

			ProductBean bean2 = new ProductBean();
			bean2.setId(2);
			bean2.setName("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
			bean2.setPrice(22.0);
			bean2.setMake(new Date());
			bean2.setExpire(2);
//			System.out.println("bean2 = " + productService.update(bean2));

//			productService.insert(bean);

//			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	@Transactional(readOnly = true)
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		if (bean != null && bean.getId() != 0) {
			ProductBean temp = productDao.select(bean.getId());
			if (temp != null) {
				result = new ArrayList<ProductBean>();
				result.add(temp);
			}
		} else {
			result = productDao.select();
		}
		return result;
	}

	@Transactional
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = productDao.insert(bean);
		}
		return result;
	}

	@Transactional
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = productDao.update(bean.getName(), bean.getPrice(), bean.getMake(), bean.getExpire(), bean.getId());
		}
		return result;
	}

	@Transactional
	public boolean delete(ProductBean bean) {
		boolean result = false;
		if (bean != null) {
			result = productDao.delete(bean.getId());
		}
		return result;
	}

}
