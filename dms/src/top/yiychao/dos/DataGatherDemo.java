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
* @Description 测试日志、物流数据的分析
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月17日 下午9:39:01 
* <p>修改说明:</p>
*/
public class DataGatherDemo {

	public static void main(String[] args) {
		// 创建一个日志业务类
		LogRecService logRecService = new LogRecService();
		// 创建一个日志对象数组，用于存放采集的三个日志信息
		LogRec[] logs = new LogRec[3];
		for(int i = 0; i < logs.length; i++) {
			System.out.println("第" + ( i + 1) + "个日志数据采集：");
			logs[i] = logRecService.inputLog();
		}
		// 创建日志数据分析对象
		LogRecAnalyse logAn = new LogRecAnalyse(logs);
		// 日志数据过滤
		logAn.doFilter();
		// 日志数据分析
		Object[] objs = logAn.matchData();
		// 判断objs数组是否是配置日志数组
		if(objs instanceof MatchedLogRec[]) {
			// 将对象数组强制类型转换成配置日志数组
			MatchedLogRec[] matchedLogs = (MatchedLogRec[]) objs;
			logRecService.showMathcedLog(matchedLogs);
		}
		
		// 创建物流业务类
		TransportService transportService = new TransportService();
		// 创建一个物流对象数组，用于存放采集的三个物流信息
		Transport[] transports = new Transport[3];
		for(int i = 0; i < transports.length; i++) {
			System.out.println("第" + (i + 1) + "个物流数据采集:");
			transports[i] = transportService.inputTransport();
		}
		// 创建日志数据分析对象
		TransportAnalyse transAn = new TransportAnalyse(transports);
		// 日志数据过滤
		transAn.doFilter();
		// 日志数据分析
		objs = transAn.matchData();
		// 判断objs数组是否是配置日志数组
		if(objs instanceof MathcedTransport[]) {
			// 将对象数组强制类型转换成配置日志数组
			MathcedTransport[] matchedTrans = (MathcedTransport[]) objs;
			transportService.showMatchTransport(matchedTrans);
		}
	}
}
