package top.yiychao.util;
/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DBUtils.java
* @Description 数据库访问工具类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月21日 下午5:13:29
* <p>修改说明:</p>
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DBUtils.java
* @Description 数据库访问工具类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月21日 下午5:39:37 
* <p>修改说明:</p>
*/
public class DBUtils {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	* @Function getConnection
	* @Description	获得数据库连接
	*
	* @return Connection	获得的连接
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月21日 下午5:43:25 
	* <p>修改说明:</p>
	 */
	public Connection getConnection() throws SQLException {
		String driver = Config.getValue("driver");
		String url = Config.getValue("url");
		String user = Config.getValue("user");
		String password = Config.getValue("password");
		try {
			Class.forName(driver);	//	指定数据库驱动程序
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			throw new SQLException("驱动错误或连接失败！");
		}		
	}
}
