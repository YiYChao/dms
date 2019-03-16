package top.yiychao.entity;

import java.util.Date;

/**
 * Copyright: Copyright (c) 2019 YiYChao
 * 
 * @ClassName LogRec.java
 * @Description 日志实体类，用户记录用户的登录和登出状态
 *
 * @version v1.0.0
 * @author YiChao
 * @date 2019年3月16日 下午1:37:10
 * <p> 修改说明:</p>
 */
public class LogRec {

	// ID 标识
	private int id;
	// 时间
	private Date time;
	// 地点
	private String address;
	// 状态
	private int type;
	// 登录用户名
	private String user;
	// 登录用户主机IP地址
	private String ip;
	// 登录状态：登录、登出
	private int logType;

	public static final int LOG_IN = 1; 	// 登录常量
	public static final int LOG_OUT = 0; 	// 登出常量

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	// 空构造
	public LogRec() {
		super();
	}

	// 有参构造
	public LogRec(int id, Date time, String address, int type, String user, String ip, int logType) {
		super();
		this.id = id;
		this.time = time;
		this.address = address;
		this.type = type;
		this.user = user;
		this.ip = ip;
		this.logType = logType;
	}

	// 重写toString方法
	@Override
	public String toString() {
		return "LogRec [id=" + id + ", time=" + time + ", address=" + address + ", type=" + type + ", user=" + user
				+ ", ip=" + ip + ", logType=" + logType + "]";
	}

}
