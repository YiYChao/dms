package top.yiychao.entity;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName MathcedTransport.java
* @Description 物流数据匹配类，对物流实体进行匹配
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月16日 下午11:54:21 
* <p>修改说明:</p>
*/
public class MathcedTransport {

	private Transport send;
	private Transport trans;
	private Transport receive;
	
	public Transport getSend() {
		return send;
	}
	
	public void setSend(Transport send) {
		this.send = send;
	}
	
	public Transport getTrans() {
		return trans;
	}
	
	public void setTrans(Transport trans) {
		this.trans = trans;
	}
	
	public Transport getReceive() {
		return receive;
	}
	
	public void setReceive(Transport receive) {
		this.receive = receive;
	}

	// 无参构造
	public MathcedTransport() {
		super();
	}

	// 有参构造
	public MathcedTransport(Transport send, Transport trans, Transport receive) {
		if(send.getTransportType() != Transport.SENDING) {
			throw new RuntimeException("不是发货记录！");
		}
		if(trans.getTransportType() != Transport.TRANSPORTING) {
			throw new RuntimeException("不是送货记录！");
		}
		if(receive.getTransportType() != Transport.RECEIVED) {
			throw new RuntimeException("不是签收记录！");
		}
		this.send = send;
		this.trans = trans;
		this.receive = receive;
	}

	// 重写toString方法
	@Override
	public String toString() {
		return  send.toString() + "|" + trans.toString() + "|" + receive.toString();
	}
}
