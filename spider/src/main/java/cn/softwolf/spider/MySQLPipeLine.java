package cn.softwolf.spider;


import cn.softwolf.dao.ProductDao;
import cn.softwolf.pojo.Product;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MySQLPipeLine implements Pipeline{
	
	ProductDao productDao = new ProductDao();
	
	public void process(ResultItems resultItems, Task task) {
		
		Product p1 = resultItems.get("itemDetil");
		if(p1 != null){
			productDao.InsertProduct(p1);
		}
			
		Product p2= resultItems.get("itemComment");
		if(p2!=null){
			productDao.InsertComment(p2);
		}
		
			
		Product p3= resultItems.get("itemPrice");
		if(p3!=null){
			productDao.InsertPrice(p3);
		
		}
		
		
	}

}
