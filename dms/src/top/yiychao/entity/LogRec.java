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
 * <p> �޸�˵��:</p>
 */
public class LogRec {

	// ID ��ʶ
	private int id;
	// ʱ��
	private Date time;
	// �ص�
	private String address;
	// ״̬
	private int type;
	// ��¼�û���
	private String user;
	// ��¼�û�����IP��ַ
	private String ip;
	// ��¼״̬����¼���ǳ�
	private int logType;

	public static final int LOG_IN = 1; 	// ��¼����
	public static final int LOG_OUT = 0; 	// �ǳ�����

	// ״̬����
	public static final int GATHER = 1; // �ɼ�
	public static final int MATCH = 2; 	// ƥ��
	public static final int RECORD = 3; // ��¼
	public static final int SEND = 4; 	// ����
	public static final int RECEIVE = 5; // ����
	public static final int WRITE = 6; 	// �鵵
	public static final int SAVE = 7; 	// ����

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

	// �չ���
	public LogRec() {
		super();
	}

	// �вι���
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

	// ��дtoString����
	@Override
	public String toString() {
		return "LogRec [id=" + id + ", time=" + time + ", address=" + address + ", type=" + type + ", user=" + user
				+ ", ip=" + ip + ", logType=" + logType + "]";
	}

}
