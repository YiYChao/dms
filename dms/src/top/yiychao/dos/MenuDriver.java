package top.yiychao.dos;

import java.util.Scanner;

/**
 * Copyright: Copyright (c) 2019 YiYChao
 * 
 * @ClassName MenuDriver.java
 * @Description 显示菜单
 *
 * @version v1.0.0
 * @author YiChao
 * @date 2019年3月16日 下午12:18:13
 * <p>修改说明:</p>
 */
public class MenuDriver {

	/**
	 * @Function main
	 * @Description 主方法，实现菜单驱动
	 *
	 * @param args 系统默认参数
	 * @return void 空
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月16日 下午12:23:04
	 *  <p>修改说明: </p>
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			while (true) {
				// 输出菜单
				System.out.println("****************************************");
				System.out.println("* 1、数据采集				 2、数据匹配*");
				System.out.println("* 3、数据记录				 4、数据显示*");
				System.out.println("* 5、数据发送				 0、退出应用*");
				System.out.println("****************************************");
				// 提示用户输入要操作的选项菜单
				System.out.print("请输入菜单项(0~5):");
				// 接收键盘输入的选项
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					System.out.println("数据采集中...");
					break;
				case 2:
					System.out.println("数据匹配中...");
					break;
				case 3:
					System.out.println("数据记录中...");
					break;
				case 4:
					System.out.println("数据显示中...");
					break;
				case 5:
					System.out.println("数据发送中...");
					break;
				case 0:
					// 应用程序退出
					System.exit(0);
				default:
					System.out.println("请输入正确的菜单项(0~5)!");
				}
			}
		} catch (Exception e) {
			System.out.println("您输入的数据不合法！");
		}
	}
}
