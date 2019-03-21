package top.yiychao.entity;

import java.io.Serializable;
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
 * <p> 修改说明: 实现类的继承</p>
 */
public class LogRec extends DataBase implements Serializable{

	// 登录用户名
	private String user;
	// 登录用户主机IP地址
	private String ip;
	// 登录状态：登录、登出
	private int logType;

	public static final int LOG_IN = 1; 	// 登录常量
	public static final int LOG_OUT = 0; 	// 登出常量


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
		super(id, time, address, type);
		this.user = user;
		this.ip = ip;
		this.logType = logType;
	}

	// 重写toString方法
	@Override
	public String toString() {
		return "LogRec [id=" + this.getId() + ", time=" + this.getTime() + ", address=" + this.getAddress() + ", type=" + this.getType()
		+ ", user=" + user + ", ip=" + ip + ", logType=" + logType + "]";
	}

}
