package cn.softwolf.utils.test;


import org.junit.Test;

import cn.softwolf.dao.ProductDao;
import cn.softwolf.pojo.Product;

public class ProductDaoTest {

	ProductDao dao = new ProductDao();
	
	@Test
	public void testInsertId() {
		dao.InsertId("001");
	}

	@Test
	public void testInsertPrice(){
		Product p = new Product();
		p.setId("001");
		p.setPrice(68.5);
		dao.InsertPrice(p);
	}
}
