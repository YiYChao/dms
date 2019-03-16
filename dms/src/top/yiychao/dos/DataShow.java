package top.yiychao.dos;

import java.util.Scanner;

/**
 * Copyright: Copyright (c) 2019 YiYChao
 * 
 * @ClassName DataShow.java
 * @Description 显示采集的数据
 *
 * @version v1.0.0
 * @author YiChao
 * @date 2019年3月16日 下午1:23:00
 *       <p>
 *       修改说明:
 *       </p>
 */
public class DataShow {

	/**
	 * @Function main
	 * @Description 主方法，显示采集的数据
	 *
	 * @param args 系统默认参数
	 * @return void 空
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月16日 下午1:23:27
	 * <p> 修改说明:</p>
	 */
	public static void main(String[] args) {
		// 声明一个整型数组
		int[] data = new int[10];
		// 建立一个从键盘接收数据的扫描器
		Scanner scanner = new Scanner(System.in);
		// 循环采集10个数据
		for (int i = 0; i < data.length; i++) {
			System.out.print("请输入第" + (i + 1) + "个数据:");
			data[i] = scanner.nextInt();
		}
		// 使用foreach语句显示采集的数据
		int i = 0;
		for (int e : data) {
			System.out.print(e + "  ");
			// 控制每行显示5个数据
			i++;
			// 当i是5的倍数时换行
			if (i % 5 == 0) {
				System.out.println();
			}
		}
	}
}
