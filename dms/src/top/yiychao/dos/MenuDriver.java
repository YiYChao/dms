package top.yiychao.dos;

import java.util.Scanner;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName MenuDriver.java
* @Description ��ʾ�˵�
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����12:18:13 
* <p>�޸�˵��:</p>
*/
public class MenuDriver {

	/**
	* @Function main
	* @Description	��������ʵ�ֲ˵�����
	*
	* @param args	ϵͳĬ�ϲ���
	* @return void	��
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��16�� ����12:23:04 
	* <p>�޸�˵��:</p>
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			// ����˵�
			System.out.println("****************************************");
			System.out.println("* 1�����ݲɼ�				 2������ƥ��*");
			System.out.println("* 3�����ݼ�¼				 4��������ʾ*");
			System.out.println("* 5�����ݷ���				 0���˳�Ӧ��*");
			System.out.println("****************************************");
			// ��ʾ�û�����Ҫ������ѡ��˵�
			System.out.print("������˵���(0~5):");
			// ���ռ��������ѡ��
			int choice = scanner.nextInt();
			switch(choice) {
			case 1:
				System.out.println("���ݲɼ���...");
				break;
			case 2:
				System.out.println("����ƥ����...");
				break;
			case 3:
				System.out.println("���ݼ�¼��...");
				break;
			case 4:
				System.out.println("������ʾ��...");
				break;
			case 5:
				System.out.println("���ݷ�����...");
				break;
			case 0:
				// Ӧ�ó����˳�
				System.exit(0);
			default:
				System.out.println("��������ȷ�Ĳ˵���(0~5)!");
			}
		}
		
	}
}
