package top.yiychao.dos;

import java.util.ArrayList;
import java.util.Date;

import top.yiychao.entity.DataBase;
import top.yiychao.entity.LogRec;
import top.yiychao.entity.MatchedLogRec;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.entity.Transport;
import top.yiychao.service.LogRecService;
import top.yiychao.service.TransportService;

/**
 * @Title DBDemo.java
 * @Description	测试匹配的日志、物流信息的数据库保存和查询功能
 *
 * @throws	无
 *
 * @version v1.0.0
 * @author YiChao
 * @date 2019年3月22日 上午11:24:58
 * <p>修改说明:</p>
 */
public class DBDemo {

	public static void main(String[] args) {
		LogRecService logRecService = new LogRecService();
		ArrayList<MatchedLogRec> matchLogs = new ArrayList<MatchedLogRec>();
		matchLogs.add(new MatchedLogRec(new LogRec(1001, new Date(), "太原", DataBase.GATHER, "zhangsan", "192.168.25.123", 1),
				new LogRec(1002, new Date(), "太原", DataBase.GATHER, "zhangsan", "192.168.25.123", 0)));
		matchLogs.add(new MatchedLogRec(new LogRec(1003, new Date(), "北京", DataBase.GATHER, "lisi", "192.168.25.165", 1),
				new LogRec(1004, new Date(), "北京", DataBase.GATHER, "lisi", "192.168.25.165", 0)));
		matchLogs.add(new MatchedLogRec(new LogRec(1005, new Date(), "青岛", DataBase.GATHER, "wangwu", "192.168.25.159", 1),
				new LogRec(1006, new Date(), "青岛", DataBase.GATHER, "wangwu", "192.168.25.159", 0)));
		// 保存匹配的日志信息到数据库中
		logRecService.saveMatchLog2DB(matchLogs);
		ArrayList<MatchedLogRec> logList = logRecService.readMatchedLogFromDB();
		logRecService.showMathcedLog(logList);
		
		TransportService tranService = new TransportService();
		ArrayList<MathcedTransport> matchTrans= new ArrayList<MathcedTransport>();
		matchTrans.add(new MathcedTransport(new Transport(2001, new Date(), "太原", DataBase.GATHER, "zhangsan", "chao1", 1), 
				new Transport(2002, new Date(), "北京", DataBase.GATHER, "lisi", "chao1", 2), 
				new Transport(2003, new Date(), "青岛", DataBase.GATHER, "wangwu", "chao1", 3)));
		matchTrans.add(new MathcedTransport(new Transport(2004, new Date(), "太原", DataBase.GATHER, "zhangsan", "chao1", 1), 
				new Transport(2005, new Date(), "北京", DataBase.GATHER, "lisi", "chao1", 2), 
				new Transport(2006, new Date(), "青岛", DataBase.GATHER, "wangwu", "chao1", 3)));
		tranService.saveMatchTran2DB(matchTrans);
		ArrayList<MathcedTransport> tanList = tranService.readMatchedTrabFromDB();
		tranService.showMatchTransport(tanList);
	}
}
