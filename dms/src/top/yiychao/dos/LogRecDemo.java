package top.yiychao.dos;

import top.yiychao.entity.LogRec;
import top.yiychao.service.LogRecService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LogRecDemo.java
* @Description 	��־�����࣬��ʾ��־���ݵĲɼ�����ʾ
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����2:51:28 
* <p>�޸�˵��:</p>
*/
public class LogRecDemo {

	/**
	* @Function main
	* @Description	��������ʵ����־���ݵĲɼ�����ʾ
	*
	* @param args	ϵͳĬ�ϲ���
	* @return void	��
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��16�� ����2:52:25 
	* <p>�޸�˵��:</p>
	 */
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
	}
}
