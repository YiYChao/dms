package top.yiychao.dos;

import top.yiychao.entity.Transport;
import top.yiychao.service.TransportService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName TransportDemo.java
* @Description ���������࣬ʵ������������Ϣ�Ĳɼ�����ʾ
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����3:37:58 
* <p>�޸�˵��:</p>
*/
public class TransportDemo {

	public static void main(String[] args) {
		// ��������ҵ����
		TransportService transportService = new TransportService();
		// ����һ�������������飬���ڴ�Ųɼ�������������Ϣ
		Transport[] transports = new Transport[3];
		for(int i = 0; i < transports.length; i++) {
			System.out.println("��" + (i + 1) + "���������ݲɼ�:");
			transports[i] = transportService.inputTransport();
		}
		// ��ʾ������Ϣ
		transportService.showTransport(transports);
	}
}
