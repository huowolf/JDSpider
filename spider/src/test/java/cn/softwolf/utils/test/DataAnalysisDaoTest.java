package cn.softwolf.utils.test;

import java.util.List;

import org.junit.Test;

import cn.softwolf.dao.DataAnalysisDao;
import cn.softwolf.pojo.Product;

public class DataAnalysisDaoTest {

	DataAnalysisDao dao = new DataAnalysisDao();
	
	@Test
	public void testSelectTopTen() {
		List<Product> products =  dao.selectTopTen();
		
		for (Product product : products) {
			System.out.println(product);
		}
	}

}
