package top.yiychao.entity;

import java.io.Serializable;
import java.util.Date;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName MatchedLogRec.java
* @Description 日志数据匹配类，对日志实体类进行匹配
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月16日 下午11:36:29 
* <p>修改说明:</p>
*/
public class MatchedLogRec implements Serializable{

	private LogRec login;	// 登录日志
	private LogRec logout;	// 登出日志
	
	// 获取用户登录名
	public String getUser() {
		return login.getUser();
	}
	
	// 登录时刻
	public Date getLoginTime() {
		return login.getTime();
	}
	
	// 登出时刻
	public Date getLogoutTime() {
		return logout.getTime();
	}
	
	// 登录记录
	public LogRec getLogin() {
		return login;
	}
	
	// 登出记录
	public LogRec getLogout() {
		return logout;
	}

	// 空构造
	public MatchedLogRec() {
	}

	// 有参构造
	public MatchedLogRec(LogRec login, LogRec logout) {
		if(login.getLogType() != LogRec.LOG_IN) {
			throw new RuntimeException("不是登录记录！");
		}
		if(logout.getLogType() != LogRec.LOG_OUT) {
			throw new RuntimeException("不是登出记录！");
		}
		if(!login.getUser().equals(logout.getUser())) {
			throw new RuntimeException("登录登出必须是同一用户！");
		}
		if(!login.getIp().equals(logout.getIp())) {
			throw new RuntimeException("登录登出必须是同一IP地址！");
		}
		this.login = login;
		this.logout = logout;
	}

	// 重写toString方法
	@Override
	public String toString() {
		return login.toString() + "|" + logout.toString();
	}
	
	
	
	
	
}
