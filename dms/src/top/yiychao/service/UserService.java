package top.yiychao.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import top.yiychao.entity.User;
import top.yiychao.util.DBUtils;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName UserService.java
* @Description �û�ҵ���߼�
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��23�� ����4:04:10 
* <p>�޸�˵��:</p>
*/
public class UserService {

	/**
	* @Function findUserByName
	* @Description	ͨ���û�����ѯ�û�
	*
	* @param username	�û���
	* @return User �û�ʵ��
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��23�� ����4:11:47 
	* <p>�޸�˵��:</p>
	 */
	public User findUserByName(String username) {
		User user = null;
		DBUtils db = new DBUtils();
		try {
			db.getConnection();	// �������
			String sql = "SELECT * FROM user WHERE username = ?";
			// ���ò���
			Object[] param = new Object[] {username};	// ���ò���
			ResultSet rs = db.executeQuery(sql, param);	// ִ�в�ѯ
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.closeAll(); 	// �ͷ����ݿ���Դ
		}
		return user;
	}
	
	/**
	* @Function saveUser
	* @Description ����û�
	*
	* @param user �û�ʵ��
	* @return ���ز������͵Ľ�����ɹ�true��ʧ��false
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��23�� ����4:19:08 
	* <p>�޸�˵��:</p>
	 */
	public boolean saveUser(User user) {
		boolean flag = false;
		DBUtils db = new DBUtils();
		try {
			db.getConnection();
			String sql  = "insert into user(username,password,sex,hobby,address,degree) values(?,?,?,?,?,?)";
			Object[] param = new Object[] {user.getUsername(),user.getPassword(),user.getSex(),user.getHobby(),user.getAddress(),user.getDegree()};
			int rs = db.executeUpdate(sql, param);	// ִ�����
			if(rs > 0) {
				flag = true;	// ��ӳɹ������÷���ֵΪtrue
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.closeAll(); 	// �ͷ���Դ
		}
		return flag;
	}
	
}
