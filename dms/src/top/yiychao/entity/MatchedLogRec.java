package top.yiychao.entity;

import java.util.Date;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName MatchedLogRec.java
* @Description ��־����ƥ���࣬����־ʵ�������ƥ��
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����11:36:29 
* <p>�޸�˵��:</p>
*/
public class MatchedLogRec {

	private LogRec login;	// ��¼��־
	private LogRec logout;	// �ǳ���־
	
	// ��ȡ�û���¼��
	public String getUser() {
		return login.getUser();
	}
	
	// ��¼ʱ��
	public Date getLoginTime() {
		return login.getTime();
	}
	
	// �ǳ�ʱ��
	public Date getLogoutTime() {
		return logout.getTime();
	}
	
	// ��¼��¼
	public LogRec getLogin() {
		return login;
	}
	
	// �ǳ���¼
	public LogRec getLogout() {
		return logout;
	}

	// �չ���
	public MatchedLogRec() {
	}

	// �вι���
	public MatchedLogRec(LogRec login, LogRec logout) {
		if(login.getLogType() != LogRec.LOG_IN) {
			throw new RuntimeException("���ǵ�¼��¼��");
		}
		if(logout.getLogType() != LogRec.LOG_OUT) {
			throw new RuntimeException("���ǵǳ���¼��");
		}
		if(!login.getUser().equals(logout.getUser())) {
			throw new RuntimeException("��¼�ǳ�������ͬһ�û���");
		}
		if(!login.getIp().equals(logout.getIp())) {
			throw new RuntimeException("��¼�ǳ�������ͬһIP��ַ��");
		}
		this.login = login;
		this.logout = logout;
	}

	// ��дtoString����
	@Override
	public String toString() {
		return login.toString() + "|" + logout.toString();
	}
	
	
	
	
	
}
