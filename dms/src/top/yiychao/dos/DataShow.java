package top.yiychao.dos;

import java.util.Scanner;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DataShow.java
* @Description ��ʾ�ɼ�������
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����1:23:00 
* <p>�޸�˵��:</p>
*/
public class DataShow {

	/**
	* @Function main
	* @Description	����������ʾ�ɼ�������
	*
	* @param args	ϵͳĬ�ϲ���
	* @return void	��
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��16�� ����1:23:27 
	* <p>�޸�˵��:</p>
	 */
	public static void main(String[] args) {
		// ����һ����������
		int[] data = new int[10];
		// ����һ���Ӽ��̽������ݵ�ɨ����
		Scanner scanner = new Scanner(System.in);
		// ѭ���ɼ�10������
		for(int i = 0; i < data.length; i++) {
			System.out.print("�������" + (i+1) + "������:");
			data[i] = scanner.nextInt();
		}
		// ʹ��foreach�����ʾ�ɼ�������
		int i = 0;
		for(int e : data) {
			System.out.print(e + "  ");
			// ����ÿ����ʾ5������
			i++;
			// ��i��5�ı���ʱ����
			if(i % 5 == 0) {
				System.out.println();
			}
		}
	}
}
