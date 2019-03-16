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
* <p>�޸�˵��: ʵ����ļ̳�</p>
*/
public class Transport extends DataBase{

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
		super(id, time, address, type);
		this.handler = handler;
		this.receiver = receiver;
		this.transportType = transportType;
	}

	// ��дtoString����
	@Override
	public String toString() {
		return "LogRec [id=" + this.getId() + ", time=" + this.getTime() + ", address=" + this.getAddress() + ", type=" + this.getType()
				+ ", handler=" + handler + ", receiver=" + receiver + ", transportType=" + transportType + "]";
	}
}
