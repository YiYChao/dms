package top.yiychao.service;

import java.util.Date;
import java.util.Scanner;

import top.yiychao.entity.LogRec;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LogRecService.java
* @Description ��־ҵ���࣬ʵ����־������Ϣ��ɼ�����ʾ����
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����1:53:16 
* <p>�޸�˵��:</p>
*/
public class LogRecService {

	/**
	* @Function inputLog
	* @Description	��־���ݲɼ�
	*
	* @return LogRec	��־ʵ����
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��16�� ����1:55:12 
	* <p>�޸�˵��:</p>
	 */
	public LogRec inputLog() {
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
		
		// ��ʾ�û������û���
		System.out.println("������ ��¼�û���:");
		String user = scanner.next();
		// ��ʾ�û���������IP
		System.out.println("������ ����IP:");
		String ip = scanner.next();
		// ��ʾ�û��������¼���ǳ�״̬
		System.out.println("�������¼״̬: 1�ǵ�¼, 2�ǵǳ�");
		int logType = scanner.nextInt();
		
		// ������־����
		LogRec log = new LogRec(id, nowDate, address, type, user, ip, logType);
		// ������־����
		return log;
	}
	
	/**
	* @Function showLog
	* @Description	�����־��Ϣ
	*
	* @param logRecs	��������
	* @return void	��
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��16�� ����1:57:42 
	* <p>�޸�˵��:</p>
	 */
	public void showLog(LogRec...logRecs ) {
		for (LogRec log : logRecs) {
			if(log != null) {
				System.out.println(log.toString());
			}
		}
	}
}
