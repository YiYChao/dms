package top.yiychao.entity;

import java.util.Date;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName Transport.java
* @Description 物流信息实体类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月16日 下午3:15:03 
* <p>修改说明:</p>
*/
public class Transport {

	// ID 标识
	private int id;
	// 时间
	private Date time;
	// 地点
	private String address;
	//  状态
	private int type;
	// 经手人
	private String handler;
	// 收货人
	private String receiver;
	// 物流状态
	private int transportType;
	
	// 物流状态常量
	public static final int SENDING = 1;
	public static final int TRANSPORTING = 1;
	public static final int RECEIVED = 1;
	
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

	// 无参构造
	public Transport() {
		super();
	}

	// 有参构造
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

	// 重写toString方法
	@Override
	public String toString() {
		return "Transport [id=" + id + ", time=" + time + ", address=" + address + ", type=" + type + ", handler="
				+ handler + ", receiver=" + receiver + ", transportType=" + transportType + "]";
	}
}
