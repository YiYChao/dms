package top.yiychao.dos;

import java.util.Scanner;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DataInput.java
* @Description �洢�ɼ�������
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��16�� ����1:07:57 
* <p>�޸�˵��:</p>
*/
public class DataInput {

	/**
	* @Function main
	* @Description	��������ʵ�����ݴ洢
	*
	* @param args	ϵͳĬ�ϲ���
	* @return void	��
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��16�� ����1:09:53 
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
		// ѭ������ɼ�������
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] +" ");
		}
		System.out.println();
	}
}
