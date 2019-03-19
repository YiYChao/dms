package top.yiychao.gather;

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
* <p>修改说明:</p>
*/
public class LogRecAnalyse extends DataFilter implements IDataAnalyse{

	// 登录集合
	private LogRec[] logins;
	
	// 登出集合
	private LogRec[] logouts;

	// 空构造
	public LogRecAnalyse() {
	}
	
	public LogRecAnalyse(LogRec[] logRecs) {
		super(logRecs);
	}



	@Override
	public Object[] matchData() {
		// 创建日志匹配数组
		MatchedLogRec[] matchedLogs = new MatchedLogRec[logins.length];
		// 日志匹配数组下标记录
		int index = 0;
		// 数据匹配分析
		for (LogRec in : logins) {
			for (LogRec out : logouts) {
				if((in.getUser().equals(out.getUser())) && (in.getIp().equals(out.getIp()) && out.getType() != DataBase.MATCH)) {
					// 修改in和out日志状态类型为“匹配”
					in.setType(DataBase.MATCH);
					out.setType(DataBase.MATCH);
					// 添加到匹配数组中
					matchedLogs[index++] = new MatchedLogRec(in, out);
				}
			}
		}
		try {
			if(index == 0) {
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
		LogRec[] logs = (LogRec[]) this.getDatas();
		// 根据日志登录状态统计不同状态的日志个数
		int numIn = 0;
		int numOut = 0;
		// 遍历统计
		for (LogRec rec : logs) {
			if(rec.getLogType() == LogRec.LOG_IN) {
				numIn++;
			} else if(rec.getLogType() == LogRec.LOG_OUT){
				numOut++;
			}
		}
		// 创建登录、登出数组
		logins = new LogRec[numIn];
		logouts = new LogRec[numOut];
		// 数组小标记录
		int indexIn = 0;
		int indexOut = 0;
		// 遍历，对日志数据进行过滤，根据日志登录状态分别放在不同的数组里
		for (LogRec rec : logs) {
			if(rec.getLogType() == LogRec.LOG_IN) {
				logins[indexIn++] = rec;
			} else if(rec.getLogType() == LogRec.LOG_OUT){
				logouts[indexOut++] = rec;
			}
		}
	}

}
