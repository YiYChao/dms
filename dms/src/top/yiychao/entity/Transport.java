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
* <p>修改说明: 实现类的继承</p>
*/
public class Transport extends DataBase{

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
		super(id, time, address, type);
		this.handler = handler;
		this.receiver = receiver;
		this.transportType = transportType;
	}

	// 重写toString方法
	@Override
	public String toString() {
		return "LogRec [id=" + this.getId() + ", time=" + this.getTime() + ", address=" + this.getAddress() + ", type=" + this.getType()
				+ ", handler=" + handler + ", receiver=" + receiver + ", transportType=" + transportType + "]";
	}
}
