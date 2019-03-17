package top.yiychao.dos;

import top.yiychao.entity.LogRec;
import top.yiychao.entity.MatchedLogRec;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.entity.Transport;
import top.yiychao.gather.LogRecAnalyse;
import top.yiychao.gather.TransportAnalyse;
import top.yiychao.service.LogRecService;
import top.yiychao.service.TransportService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DataGatherDemo.java
* @Description ������־���������ݵķ���
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��17�� ����9:39:01 
* <p>�޸�˵��:</p>
*/
public class DataGatherDemo {

	public static void main(String[] args) {
		// ����һ����־ҵ����
		LogRecService logRecService = new LogRecService();
		// ����һ����־�������飬���ڴ�Ųɼ���������־��Ϣ
		LogRec[] logs = new LogRec[3];
		for(int i = 0; i < logs.length; i++) {
			System.out.println("��" + ( i + 1) + "����־���ݲɼ���");
			logs[i] = logRecService.inputLog();
		}
		// ������־���ݷ�������
		LogRecAnalyse logAn = new LogRecAnalyse(logs);
		// ��־���ݹ���
		logAn.doFilter();
		// ��־���ݷ���
		Object[] objs = logAn.matchData();
		// �ж�objs�����Ƿ���������־����
		if(objs instanceof MatchedLogRec[]) {
			// ����������ǿ������ת����������־����
			MatchedLogRec[] matchedLogs = (MatchedLogRec[]) objs;
			logRecService.showMathcedLog(matchedLogs);
		}
		
		// ��������ҵ����
		TransportService transportService = new TransportService();
		// ����һ�������������飬���ڴ�Ųɼ�������������Ϣ
		Transport[] transports = new Transport[3];
		for(int i = 0; i < transports.length; i++) {
			System.out.println("��" + (i + 1) + "���������ݲɼ�:");
			transports[i] = transportService.inputTransport();
		}
		// ������־���ݷ�������
		TransportAnalyse transAn = new TransportAnalyse(transports);
		// ��־���ݹ���
		transAn.doFilter();
		// ��־���ݷ���
		objs = transAn.matchData();
		// �ж�objs�����Ƿ���������־����
		if(objs instanceof MathcedTransport[]) {
			// ����������ǿ������ת����������־����
			MathcedTransport[] matchedTrans = (MathcedTransport[]) objs;
			transportService.showMatchTransport(matchedTrans);
		}
	}
}
