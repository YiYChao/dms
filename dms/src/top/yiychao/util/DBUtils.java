package top.yiychao.util;
/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DBUtils.java
* @Description 鏁版嵁搴撹闂伐鍏风被
*
* @version v1.0.0
* @author YiChao
* @date 2019骞�3鏈�21鏃� 涓嬪崍5:13:29
* <p>淇敼璇存槑:</p>
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
* @Description 鏁版嵁搴撹闂伐鍏风被
*
* @version v1.0.0
* @author YiChao
* @date 2019骞�3鏈�21鏃� 涓嬪崍5:39:37 
* <p>淇敼璇存槑:</p>
*/
public class DBUtils {

	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	/**
	* @Function getConnection
	* @Description	鑾峰緱鏁版嵁搴撹繛鎺�
	*
	* @return Connection	鑾峰緱鐨勮繛鎺�
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019骞�3鏈�21鏃� 涓嬪崍5:43:25 
	* <p>淇敼璇存槑:</p>
	 */
	public Connection getConnection() throws SQLException {
		String driver = Config.getValue("driver");
		String url = Config.getValue("url");
		String user = Config.getValue("user");
		String password = Config.getValue("password");
		try {
			Class.forName(driver);	//	鎸囧畾鏁版嵁搴撻┍鍔ㄧ▼搴�
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			throw new SQLException("椹卞姩閿欒鎴栬繛鎺ュけ璐ワ紒");
		}		
	}
	
	/**
	* @Function closeAll
	* @Description	閲婃斁璧勬簮
	*
	* @return void	绌�
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019骞�3鏈�22鏃� 涓婂崍9:13:25 
	* <p>淇敼璇存槑:</p>
	 */
	public void closeAll() {
		// 濡傛灉rs涓嶄负绌猴紝鍏抽棴rs
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 濡傛灉pstm涓嶇┖锛屽叧闂璸stm
		if(pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 濡傛灉conn涓嶄负绌猴紝鍏抽棴conn
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
	* @Description	杩涜鏌ヨ鎿嶄綔
	* 
	* @param preparedSql
	* @param param 鍙傛暟瀵硅薄
	* @return void	绌�
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019骞�3鏈�22鏃� 涓婂崍9:13:25 
	* <p>淇敼璇存槑:</p>
	 */
	public ResultSet executeQuery(String preparedSql, Object[] param) {
		try {
			pstm = conn.prepareStatement(preparedSql);	// 鑾峰緱prepareStatement瀵硅薄
			if(param != null) {
				for(int i = 0; i < param.length; i++) {
					// 涓洪缂栬瘧SQL璁剧疆鍙傛暟
					pstm.setObject(i + 1, param[i]);
				}
			}
			rs = pstm.executeQuery();	// 鎵цSQL璇彞
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}	
	
	/**
	* @Function executeUpdate
	* @Description	鎵ц澧炲垹鏀圭殑鎿嶄綔
	* 
	* @param preparedSql
	* @param param 鍙傛暟瀵硅薄
	* @return void	绌�
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019骞�3鏈�22鏃� 涓婂崍9:33:25 
	* <p>淇敼璇存槑:</p>
	 */
	public int executeUpdate(String preparedSql, Object[] param) {
		int num = 0;
		try {
			pstm = conn.prepareStatement(preparedSql);
			if(param != null) {
				for(int i = 0; i < param.length; i++) {
					// 涓洪缂栬瘧SQL璁剧疆鍙傛暟
					pstm.setObject(i + 1, param[i]);
				}
			}
			num = pstm.executeUpdate();	// 鎵ц鏇存柊鎿嶄綔
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}
