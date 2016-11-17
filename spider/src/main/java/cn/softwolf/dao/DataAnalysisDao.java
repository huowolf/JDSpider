package cn.softwolf.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.softwolf.pojo.Product;

/*
 * 分析抓取来的数据
 */
public class DataAnalysisDao {

	//找出销量最高的十条数据
	public List<Product> selectTopTen(){
		
		QueryRunner qr = new QueryRunner();
		Connection conn = JDBCUtil.getConnection();

		List<Product> products = new ArrayList<Product>();
		try {
			String sql = "SELECT * FROM product ORDER BY commentCount DESC LIMIT 0,10";
			products = qr.query(conn, sql, new BeanListHandler<Product>(Product.class));
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return products;
	}
}
