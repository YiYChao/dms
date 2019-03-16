package top.yiychao.entity;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName MathcedTransport.java
* @Description ��������ƥ���࣬������ʵ�����ƥ��
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����11:54:21 
* <p>�޸�˵��:</p>
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

	// �޲ι���
	public MathcedTransport() {
		super();
	}

	// �вι���
	public MathcedTransport(Transport send, Transport trans, Transport receive) {
		if(send.getTransportType() != Transport.SENDING) {
			throw new RuntimeException("���Ƿ�����¼��");
		}
		if(trans.getTransportType() != Transport.TRANSPORTING) {
			throw new RuntimeException("�����ͻ���¼��");
		}
		if(receive.getTransportType() != Transport.RECEIVED) {
			throw new RuntimeException("����ǩ�ռ�¼��");
		}
		this.send = send;
		this.trans = trans;
		this.receive = receive;
	}

	// ��дtoString����
	@Override
	public String toString() {
		return  send.toString() + "|" + trans.toString() + "|" + receive.toString();
	}
}
