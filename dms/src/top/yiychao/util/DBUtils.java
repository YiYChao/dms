package top.yiychao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DBUtils.java
* @Description 数据库工具类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月21日 下午5:13:29
* <p>修改说明:</p>
*/
public class DBUtils {

	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	/**
	* @Function getConnection
	* @Description	获得连接
	*
	* @return Connection	返回连接对象
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3é月21日 下午5:43:25 
	* <p>修改说明:</p>
	 */
	public Connection getConnection() throws SQLException {
		String driver = Config.getValue("driver");
		String url = Config.getValue("url");
		String user = Config.getValue("user");
		String password = Config.getValue("password");
		try {
			Class.forName(driver);	//	指定驱动程序
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			throw new SQLException("连接失败！");
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
	* @date 2019年3月22日 下午9:13:25 
	* <p>æ·î½æ¼çå­æ§:</p>
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
		// 如果pstm不为空，关闭pstm
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
	* @Description	执行查询
	* 
	* @param preparedSql 预编译sql
	* @param param 参数数组
	* @return ResultSet	结果集
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月22日 下午9:13:25 
	* <p>修改说明:</p>
	 */
	public ResultSet executeQuery(String preparedSql, Object[] param) {
		try {
			pstm = conn.prepareStatement(preparedSql);	// é¾å³°ç·±prepareStatementçµç¡è
			if(param != null) {
				for(int i = 0; i < param.length; i++) {
					// 为预编译sql设置参数
					pstm.setObject(i + 1, param[i]);
				}
			}
			rs = pstm.executeQuery();	// 执行查询
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}	
	
	/**
	* @Function executeUpdate
	* @Description	执行修改，增、删、改操作
	* 
	* @param preparedSql 预编译slq
	* @param param 参数数组
	* @return int	操作结果
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月22日 下午9:33:25 
	* <p>修改说明:</p>
	 */
	public int executeUpdate(String preparedSql, Object[] param) {
		int num = 0;
		try {
			pstm = conn.prepareStatement(preparedSql);
			if(param != null) {
				for(int i = 0; i < param.length; i++) {
					// 为预编译sql设置参数
					pstm.setObject(i + 1, param[i]);
				}
			}
			num = pstm.executeUpdate();	// 执行sql语句
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}
