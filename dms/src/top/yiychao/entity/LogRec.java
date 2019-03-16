package top.yiychao.entity;

import java.util.Date;

/**
 * Copyright: Copyright (c) 2019 YiYChao
 * 
 * @ClassName LogRec.java
 * @Description ��־ʵ���࣬�û���¼�û��ĵ�¼�͵ǳ�״̬
 *
 * @version v1.0.0
 * @author YiChao
 * @date 2019��3��16�� ����1:37:10
 * <p> �޸�˵��: ʵ����ļ̳�</p>
 */
public class LogRec extends DataBase{

	// ��¼�û���
	private String user;
	// ��¼�û�����IP��ַ
	private String ip;
	// ��¼״̬����¼���ǳ�
	private int logType;

	public static final int LOG_IN = 1; 	// ��¼����
	public static final int LOG_OUT = 0; 	// �ǳ�����


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

	// �չ���
	public LogRec() {
		super();
	}

	// �вι���
	public LogRec(int id, Date time, String address, int type, String user, String ip, int logType) {
		super(id, time, address, type);
		this.user = user;
		this.ip = ip;
		this.logType = logType;
	}

	// ��дtoString����
	@Override
	public String toString() {
		return "LogRec [id=" + this.getId() + ", time=" + this.getTime() + ", address=" + this.getAddress() + ", type=" + this.getType()
		+ ", user=" + user + ", ip=" + ip + ", logType=" + logType + "]";
	}

}
