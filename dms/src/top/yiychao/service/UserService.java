package top.yiychao.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import top.yiychao.entity.User;
import top.yiychao.util.DBUtils;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName UserService.java
* @Description 用户业务逻辑
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月23日 下午4:04:10 
* <p>修改说明:</p>
*/
public class UserService {

	/**
	* @Function findUserByName
	* @Description	通过用户名查询用户
	*
	* @param username	用户名
	* @return User 用户实体
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月23日 下午4:11:47 
	* <p>修改说明:</p>
	 */
	public User findUserByName(String username) {
		User user = null;
		DBUtils db = new DBUtils();
		try {
			db.getConnection();	// 获得连接
			String sql = "SELECT * FROM user WHERE username = ?";
			// 设置参数
			Object[] param = new Object[] {username};	// 设置参数
			ResultSet rs = db.executeQuery(sql, param);	// 执行查询
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.closeAll(); 	// 释放数据库资源
		}
		return user;
	}
	
	/**
	* @Function saveUser
	* @Description 添加用户
	*
	* @param user 用户实体
	* @return 返回布尔类型的结果，成功true，失败false
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月23日 下午4:19:08 
	* <p>修改说明:</p>
	 */
	public boolean saveUser(User user) {
		boolean flag = false;
		DBUtils db = new DBUtils();
		try {
			db.getConnection();
			String sql  = "insert into user(usernaem,password,sex,hobby,address,degree) values(?,?,?,?,?,?)";
			Object[] param = new Object[] {user.getUsername(),user.getPassword(),user.getSex(),user.getHobby(),user.getAddress(),user.getDegree()};
			int rs = db.executeUpdate(sql, param);	// 执行添加
			if(rs > 0) {
				flag = true;	// 添加成功，设置返回值为true
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.closeAll(); 	// 释放资源
		}
		return flag;
	}
	
}
