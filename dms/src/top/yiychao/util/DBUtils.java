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
import java.util.Date;
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
	PreparedStatement pstm = null;
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
	
	/**
	* @Function closeAll
	* @Description	释放资源
	*
	* @return void	空
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月22日 上午9:13:25 
	* <p>修改说明:</p>
	 */
	public void closeAll() {
		// 如果rs不为空，关闭rs
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 如果pstm不空，关闭pstm
		if(pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 如果conn不为空，关闭conn
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	* @Function executeQuery
	* @Description	进行查询操作
	* 
	* @param preparedSql
	* @param param 参数对象
	* @return void	空
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月22日 上午9:13:25 
	* <p>修改说明:</p>
	 */
	public ResultSet executeQuery(String preparedSql, Object[] param) {
		try {
			pstm = conn.prepareStatement(preparedSql);	// 获得prepareStatement对象
			if(param != null) {
				for(int i = 0; i < param.length; i++) {
					// 为预编译SQL设置参数
					pstm.setObject(i + 1, param[i]);
				}
			}
			rs = pstm.executeQuery();	// 执行SQL语句
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}	
	
	/**
	* @Function executeUpdate
	* @Description	执行增删改的操作
	* 
	* @param preparedSql
	* @param param 参数对象
	* @return void	空
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月22日 上午9:33:25 
	* <p>修改说明:</p>
	 */
	public int executeUpdate(String preparedSql, Object[] param) {
		int num = 0;
		try {
			pstm = conn.prepareStatement(preparedSql);
			if(param != null) {
				for(int i = 0; i < param.length; i++) {
					// 为预编译SQL设置参数
					pstm.setObject(i + 1, param[i]);
				}
			}
			num = pstm.executeUpdate();	// 执行更新操作
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}
