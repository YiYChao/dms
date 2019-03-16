package top.yiychao.dos;

import top.yiychao.entity.LogRec;
import top.yiychao.entity.Transport;
import top.yiychao.service.LogRecService;
import top.yiychao.service.TransportService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName EntityDataDemo.java
* @Description 测试继承之后的方法
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月16日 下午11:29:37 
* <p>修改说明:</p>
*/
public class EntityDataDemo {

	public static void main(String[] args) {
		// 创建一个日志业务类
		LogRecService logRecService = new LogRecService();
		// 创建一个日志对象数组，用于存放采集的两个日志信息
		LogRec[] logs = new LogRec[2];
		for(int i = 0; i < logs.length; i++) {
			System.out.println("第" + ( i + 1) + "个日志数据采集：");
			logs[i] = logRecService.inputLog();
		}
		// 显示日志信息
		logRecService.showLog(logs);
		
		// 创建物流业务类
		TransportService transportService = new TransportService();
		// 创建一个物流对象数组，用于存放采集的三个物流信息
		Transport[] transports = new Transport[3];
		for(int i = 0; i < transports.length; i++) {
			System.out.println("第" + (i + 1) + "个物流数据采集:");
			transports[i] = transportService.inputTransport();
		}
		// 显示物流信息
		transportService.showTransport(transports);
		
	}
}
