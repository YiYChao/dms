package top.yiychao.dos;

import top.yiychao.entity.LogRec;
import top.yiychao.entity.Transport;
import top.yiychao.service.LogRecService;
import top.yiychao.service.TransportService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName EntityDataDemo.java
* @Description ���Լ̳�֮��ķ���
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����11:29:37 
* <p>�޸�˵��:</p>
*/
public class EntityDataDemo {

	public static void main(String[] args) {
		// ����һ����־ҵ����
		LogRecService logRecService = new LogRecService();
		// ����һ����־�������飬���ڴ�Ųɼ���������־��Ϣ
		LogRec[] logs = new LogRec[2];
		for(int i = 0; i < logs.length; i++) {
			System.out.println("��" + ( i + 1) + "����־���ݲɼ���");
			logs[i] = logRecService.inputLog();
		}
		// ��ʾ��־��Ϣ
		logRecService.showLog(logs);
		
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
