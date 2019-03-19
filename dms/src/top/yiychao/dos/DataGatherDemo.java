package top.yiychao.dos;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
* <p>修改说明:数组修改为集合泛型</p>
*/
public class DataGatherDemo {

	public static void main(String[] args) {
		// 创建一个日志业务类
		LogRecService logRecService = new LogRecService();
		// 创建一个日志对象数组，用于存放采集的三个日志信息
		ArrayList<LogRec> logs = new ArrayList<LogRec>();
		for(int i = 0; i < 3; i++) {
			System.out.println("第" + ( i + 1) + "个日志数据采集：");
			logs.add(logRecService.inputLog());
		}
		// 创建日志数据分析对象
		LogRecAnalyse logAn = new LogRecAnalyse(logs);
		// 日志数据过滤
		logAn.doFilter();
		// 日志数据分析
		ArrayList<MatchedLogRec> objs1 = logAn.matchData();
		// 判断objs数组是否是配置日志数组
		if(objs1 instanceof ArrayList) {
			// 将对象数组强制类型转换成配置日志数组
			ArrayList<MatchedLogRec> matchedLogs = (ArrayList<MatchedLogRec>) objs1;
			logRecService.showMathcedLog(matchedLogs);
		}
		
		// 创建物流业务类
		TransportService transportService = new TransportService();
		// 创建一个物流对象数组，用于存放采集的三个物流信息
		ArrayList<Transport> transports = new ArrayList<Transport>();
		for(int i = 0; i < 3; i++) {
			System.out.println("第" + (i + 1) + "个物流数据采集:");
			transports.add(transportService.inputTransport());
		}
		// 创建日志数据分析对象
		TransportAnalyse transAn = new TransportAnalyse(transports);
		// 日志数据过滤
		transAn.doFilter();
		// 日志数据分析
		ArrayList<MathcedTransport> objs2 = transAn.matchData();
		// 判断objs数组是否是配置日志数组
		if(objs2 instanceof ArrayList) {
			// 将对象数组强制类型转换成配置日志数组
			ArrayList<MathcedTransport> matchedTrans = (ArrayList<MathcedTransport>) objs2;
			transportService.showMatchTransport(matchedTrans);
		}
	}
}
