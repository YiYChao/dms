package top.yiychao.entity;

import java.util.Date;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName Transport.java
* @Description ������Ϣʵ����
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����3:15:03 
* <p>�޸�˵��:</p>
*/
public class Transport {

	// ID ��ʶ
	private int id;
	// ʱ��
	private Date time;
	// �ص�
	private String address;
	//  ״̬
	private int type;
	// ������
	private String handler;
	// �ջ���
	private String receiver;
	// ����״̬
	private int transportType;
	
	// ����״̬����
	public static final int SENDING = 1;
	public static final int TRANSPORTING = 1;
	public static final int RECEIVED = 1;
	
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
	
	public String getHandler() {
		return handler;
	}
	
	public void setHandler(String handler) {
		this.handler = handler;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public int getTransportType() {
		return transportType;
	}
	
	public void setTransportType(int transportType) {
		this.transportType = transportType;
	}

	// �޲ι���
	public Transport() {
		super();
	}

	// �вι���
	public Transport(int id, Date time, String address, int type, String handler, String receiver, int transportType) {
		super();
		this.id = id;
		this.time = time;
		this.address = address;
		this.type = type;
		this.handler = handler;
		this.receiver = receiver;
		this.transportType = transportType;
	}

	// ��дtoString����
	@Override
	public String toString() {
		return "Transport [id=" + id + ", time=" + time + ", address=" + address + ", type=" + type + ", handler="
				+ handler + ", receiver=" + receiver + ", transportType=" + transportType + "]";
	}
}
