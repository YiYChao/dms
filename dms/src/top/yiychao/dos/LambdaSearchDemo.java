package top.yiychao.dos;

import java.util.ArrayList;

import top.yiychao.entity.MatchedLogRec;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.service.LogRecService;
import top.yiychao.service.TransportService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LambdaSearchDemo.java
* @Description 使用Lambda表达式实现查找指定的匹配信息并显示
*
* @version v1.0.0
* @author YiChao
* @date 2019年4月1日 上午12:30:25 
* <p>修改说明:</p>
*/
public class LambdaSearchDemo {

	public static void main(String[] args) {
		LogRecService logService = new LogRecService();
		ArrayList<MatchedLogRec> logList = logService.readMatchedLogFromDB();
		logService.showMathcedLog(logList);
		System.out.println("-------------------------------------------------------------");
		System.out.println("查找ID=1005的日志信息");
		logList.stream().filter(e -> e.getLogin().getId() == 1005 || e.getLogout().getId() == 1005).forEach(p -> System.out.println(p.toString()));
		System.out.println("=============================================================");
		TransportService tranService = new TransportService();
		ArrayList<MathcedTransport> tranList = tranService.readMatchedTrabFromDB();
		tranService.showMatchTransport(tranList);
		System.out.println("-------------------------------------------------------------");
		System.out.println("查找ID=2005的日志信息");
		tranList.stream().filter(e -> e.getSend().getId() == 2005 || e.getTrans().getId() == 2005 || e.getReceive().getId() == 2005).forEach(p -> System.out.println(p.toString()));
	}
}
