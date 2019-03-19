package top.yiychao.dos;

import java.util.ArrayList;
import java.util.Scanner;

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
		// 建立一个存储日志数据的泛型集合
		ArrayList<LogRec> logRecList = new ArrayList<LogRec>();
		// 建立一个存储物流数据的泛型集合
		ArrayList<Transport> transportList = new ArrayList<Transport>();
		
		// 建立日志业务类和物流业务类
		LogRecService logRecService = new LogRecService();
		TransportService tranService = new TransportService();
		
		// 日志数据匹配集合和物流数据匹配集合
		ArrayList<MatchedLogRec> matchedLogs = null;
		ArrayList<MathcedTransport> mathcedTrans = null;
		
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
				case 1:{
					System.out.println("请输入数据采集类型：1.日志		2.物流");
					int type = scanner.nextInt();
					if(type == 1) {
						System.out.println("正在采集日志数据，请输入正确信息，确保数据的正常采集！");
						// 采集日志并放入集合
						LogRec log = logRecService.inputLog();
						logRecList.add(log);
					}else if(type == 2) {
						System.out.println("正在采集物流数据，请输入正确信息，确保数据的正常采集！");
						// 采集物流并放入集合
						Transport transport = tranService.inputTransport();
						transportList.add(transport);
					}
				}
					break;
				case 2:{
					System.out.println("请输入数据匹配类型：1.日志		2.物流");
					int type = scanner.nextInt();
					if(type == 1) {
						System.out.println("正在进行日志数据过滤匹配...");
						// 创建日志分析对象
						LogRecAnalyse logAn = new LogRecAnalyse(logRecList);
						// 日志过滤并分析
						logAn.doFilter();
						matchedLogs = logAn.matchData();
						System.out.println("日志数据过滤匹配完成！");
					}else if(type == 2) {
						System.out.println("正在进行物流数据过滤匹配...");
						// 创建物流分析对象
						TransportAnalyse tranAn = new TransportAnalyse(transportList);
						// 物流过滤并分析
						tranAn.doFilter();
						mathcedTrans = tranAn.matchData();
						System.out.println("日志数据过滤匹配完成！");
					}
				}
					break;
				case 3:
					System.out.println("数据记录中...");
					break;
				case 4:
					System.out.println("显示匹配数据中...");
					if(matchedLogs == null || matchedLogs.size() == 0) {
						System.out.println("匹配的日志记录是0条");
					}else {
						logRecService.showMathcedLog(matchedLogs);
					}
					
					if(mathcedTrans == null || mathcedTrans.size() == 0) {
						System.out.println("匹配的日志记录是0条");
					}else {
						tranService.showMatchTransport(mathcedTrans);
					}
					
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
