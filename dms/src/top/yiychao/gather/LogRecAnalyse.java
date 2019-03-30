package top.yiychao.gather;

import java.util.ArrayList;

import top.yiychao.entity.DataBase;
import top.yiychao.entity.LogRec;
import top.yiychao.entity.MatchedLogRec;
import top.yiychao.exception.DataAnalyseException;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LogRecAnalyse.java
* @Description 日志数据分析类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月17日 下午8:42:05 
* <p>修改说明:将数组修改为泛型集合</p>
*/
public class LogRecAnalyse extends DataFilter implements IDataAnalyse{

	// 登录集合
	private ArrayList<LogRec> logins = new ArrayList<LogRec>();
	
	// 登出集合
	private ArrayList<LogRec> logouts = new ArrayList<LogRec>();

	// 空构造
	public LogRecAnalyse() {
	}
	
	public LogRecAnalyse(ArrayList<LogRec> logRecs) {
		super(logRecs);
	}



	@Override
	public ArrayList<MatchedLogRec> matchData() {
		// 创建日志匹配数组
		ArrayList<MatchedLogRec> matchedLogs = new ArrayList<MatchedLogRec>();
		// 数据匹配分析匹配
		for (LogRec in : logins) {
			for (LogRec out : logouts) {
				if((in.getUser().equals(out.getUser())) && (in.getIp().equals(out.getIp()) && out.getType() != DataBase.MATCH)) {
					// 修改in和out日志状态类型为“匹配”
					in.setType(DataBase.MATCH);
					out.setType(DataBase.MATCH);
					// 添加到匹配集合中
					matchedLogs.add(new MatchedLogRec(in, out));
				}
			}
		}
		try {
			if(matchedLogs.size() == 0) {
				// 没有匹配的数据，则抛出自定义异常
				throw new DataAnalyseException("没有匹配的日志数据！");
			}
		} catch (DataAnalyseException e) {
			e.printStackTrace();
		}
		return matchedLogs;
	}

	@Override
	public void doFilter() {
		// 获取数据集合
		ArrayList<LogRec> logs = (ArrayList<LogRec>) this.getDatas();
		
		// 遍历统计,对日志数据进行过滤
		for (LogRec rec : logs) {
			if(rec.getLogType() == LogRec.LOG_IN) {
				// 添加到“登录”日志集合中
				logins.add(rec);
			} else if(rec.getLogType() == LogRec.LOG_OUT){
				// 添加到“登出”日志集合中
				logouts.add(rec);
			}
		}
	}
}
