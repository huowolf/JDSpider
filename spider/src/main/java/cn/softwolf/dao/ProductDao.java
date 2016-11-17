package cn.softwolf.dao;

import java.sql.Connection;
import java.sql.SQLException;


import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

import cn.softwolf.pojo.Product;

/*
 * 将抓取的数据存放到数据库的DAO方法
 */
public class ProductDao {

	// 插入商品详情页抓取到的数据(id,name,image)
	public void InsertProduct(Product p) {

		InsertId(p.getId());//先插入id
		
		QueryRunner qr = new QueryRunner();
		Connection conn = JDBCUtil.getConnection();

		try {
			//根据id更新其他字段
			String sql = "UPDATE product SET name=?,image=? WHERE id=?";
			qr.update(conn, sql, p.getName(), p.getImage(), p.getId());
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//插入商品评论页抓取到的数据
	public void InsertComment(Product p){
		
		InsertId(p.getId());//先插入id
		
		QueryRunner qr = new QueryRunner();
		Connection conn = JDBCUtil.getConnection();
		
		try{
			String sql = "UPDATE product SET commentCount=?,praiseRate=? WHERE id=?";
			qr.update(conn, sql, p.getCommentCount(),p.getPraiseRate(),p.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//插入商品价格页抓取到的数据
	public void InsertPrice(Product p){
		
		InsertId(p.getId());//先插入id
		
		QueryRunner qr = new QueryRunner();
		Connection conn = JDBCUtil.getConnection();
		
		try{
			String sql = "UPDATE product SET price=? WHERE id=?";
			qr.update(conn, sql, p.getPrice(),p.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//插入商品的id	
	/*
	 * 必须要互斥（synchronized），否则会造成重复插入，报主键冲突异常
	 * 但是加了这个关键字以后，程序运行时间明显变长。。。
	 */
	public synchronized void InsertId(String id){
		
		QueryRunner qr = new QueryRunner();
		Connection conn = JDBCUtil.getConnection();
		
		/*
		 * 如果商品id不存在，那么就插入商品id
		 */
		try{
			String sql = "SELECT Count(*) FROM product WHERE id=?";
			Object[] count = qr.query(conn, sql, new ArrayHandler(), id);
			Long exist = (Long)count[0];		
			
			if(exist == 0){	//代表id未被插入
				String sql1 = "INSERT INTO product (id) VALUES (?)";
				qr.update(conn, sql1, id);
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
