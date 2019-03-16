package top.yiychao.service;

import java.util.Date;
import java.util.Scanner;

import top.yiychao.entity.LogRec;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.entity.Transport;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName TransportService.java
* @Description ����ҵ���࣬ʵ������������Ϣ�Ĳɼ�����ʾ����
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����3:27:14 
* <p>�޸�˵��:</p>
*/
public class TransportService {

	public Transport inputTransport() {
		// ����һ���Ӽ��̽������ݵ�ɨ����
		Scanner scanner = new Scanner(System.in);
		// ��ʾ�û�����ID��ʶ
		System.out.println("������ID��ʶ:");
		int id = scanner.nextInt();
		// ��ȡϵͳʱ��
		Date nowDate = new Date();
		// ��ʾ�û������ַ
		System.out.println("�������ַ:");
		String address = scanner.next();
		// ����״̬�ǲɼ�
		int type = LogRec.GATHER;
		
		// ��ʾ�û�������ﾭ����
		System.out.println("������ ���ﾭ����:");
		String handler = scanner.next();
		// ��ʾ�û������ջ���
		System.out.println("������ �ջ���:");
		String receiver = scanner.next();
		// ��ʾ�û�����������״̬
		System.out.println("����������״̬: 1������, 2�ͻ���, 3��ǩ��");
		int transportType = scanner.nextInt();
		
		// ����������Ϣ����
		Transport transport = new Transport(id, nowDate, address, type, handler, receiver, transportType);
		return transport;
	}
	
	/**
	* @Function showTransport
	* @Description	�����ʾ������Ϣ
	*
	* @param transports	����������������Ϣʵ����
	* @return void	��
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��16�� ����3:36:19 
	* <p>�޸�˵��:</p>
	 */
	public void showTransport(Transport...transports ) {
		for (Transport transport : transports) {
			if(transport != null) {
				System.out.println(transport.toString());
			}
		}
	}
	
	/**
	* @Function showMatchTransport
	* @Description	���ƥ���������Ϣ
	*
	* @param mathcedTransports	������Ϣƥ��ʵ�壬��������
	* @return void	��
	* @throws	����ʱ�쳣
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��17�� ����12:05:45 
	* <p>�޸�˵��:</p>
	 */
	public void showMatchTransport(MathcedTransport...mathcedTransports) {
		for (MathcedTransport mathcedTransport : mathcedTransports) {
			if(mathcedTransport != null) {
				System.out.println(mathcedTransport.toString());
			}
		}
	}
}
