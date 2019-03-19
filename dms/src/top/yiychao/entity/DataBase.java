package top.yiychao.entity;

import java.util.Date;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DataBase.java
* @Description 基础信息实体类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月16日 下午11:13:20 
* <p>修改说明:</p>
*/
public class DataBase {

	// ID 标识
	private int id;
	// 时间
	private Date time;
	// 地点
	private String address;
	// 状态
	private int type;
	
	// 状态常量
	public static final int GATHER = 1; // 采集
	public static final int MATCH = 2; 	// 匹配
	public static final int RECORD = 3; // 记录
	public static final int SEND = 4; 	// 发送
	public static final int RECEIVE = 5; // 接收
	public static final int WRITE = 6; 	// 归档
	public static final int SAVE = 7; 	// 保存
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public DataBase() {
		super();
	}

	public DataBase(int id, Date time, String address, int type) {
		super();
		this.id = id;
		this.time = time;
		this.address = address;
		this.type = type;
	}

	@Override
	public String toString() {
		return "DataBase [id=" + id + ", time=" + time + ", address=" + address + ", type=" + type + "]";
	}
}
