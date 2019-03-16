package top.yiychao.entity;

import java.util.Date;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DataBase.java
* @Description ������Ϣʵ����
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����11:13:20 
* <p>�޸�˵��:</p>
*/
public class DataBase {

	// ID ��ʶ
	private int id;
	// ʱ��
	private Date time;
	// �ص�
	private String address;
	// ״̬
	private int type;
	
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
