package top.yiychao.entity;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName User.java
* @Description 用户实体类
*
* @version v1.0.0
* @author YiChao
* @date 201年3月23日 下午4:03:25 
* <p>修改说明:</p>
*/
public class User {

	private int id;
	private String username;
	private String password;
	private int sex;
	private String hobby;
	private String address;
	private String degree;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getSex() {
		return sex;
	}
	
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public String getHobby() {
		return hobby;
	}
	
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public String getDegree() {
		return degree;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public User(int id, String username, String password, int sex, String hobby, String address, String degree) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.hobby = hobby;
		this.address = address;
		this.degree = degree;
	}
	public User(String username, String password, int sex, String hobby, String address, String degree) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.hobby = hobby;
		this.address = address;
		this.degree = degree;
	}

	
}
