package top.yiychao.gather;

import top.yiychao.entity.DataBase;
import top.yiychao.entity.LogRec;
import top.yiychao.entity.MatchedLogRec;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LogRecAnalyse.java
* @Description ��־���ݷ�����
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��17�� ����8:42:05 
* <p>�޸�˵��:</p>
*/
public class LogRecAnalyse extends DataFilter implements IDataAnalyse{

	// ��¼����
	private LogRec[] logins;
	
	// �ǳ�����
	private LogRec[] logouts;

	// �չ���
	public LogRecAnalyse() {
	}
	
	public LogRecAnalyse(LogRec[] logRecs) {
		super(logRecs);
	}



	@Override
	public Object[] matchData() {
		// ������־ƥ������
		MatchedLogRec[] matchedLogs = new MatchedLogRec[logins.length];
		// ��־ƥ�������±��¼
		int index = 0;
		// ����ƥ�����
		for (LogRec in : logins) {
			for (LogRec out : logouts) {
				if((in.getUser().equals(out.getUser())) && (in.getIp().equals(out.getIp()) && out.getType() != DataBase.MATCH)) {
					// �޸�in��out��־״̬����Ϊ��ƥ�䡱
					in.setType(DataBase.MATCH);
					out.setType(DataBase.MATCH);
					// ��ӵ�ƥ��������
					matchedLogs[index++] = new MatchedLogRec(in, out);
				}
			}
		}
		return matchedLogs;
	}

	@Override
	public void doFilter() {
		// ��ȡ���ݼ���
		LogRec[] logs = (LogRec[]) this.getDatas();
		// ������־��¼״̬ͳ�Ʋ�ͬ״̬����־����
		int numIn = 0;
		int numOut = 0;
		// ����ͳ��
		for (LogRec rec : logs) {
			if(rec.getLogType() == LogRec.LOG_IN) {
				numIn++;
			} else if(rec.getLogType() == LogRec.LOG_OUT){
				numOut++;
			}
		}
		// ������¼���ǳ�����
		logins = new LogRec[numIn];
		logouts = new LogRec[numOut];
		// ����С���¼
		int indexIn = 0;
		int indexOut = 0;
		// ����������־���ݽ��й��ˣ�������־��¼״̬�ֱ���ڲ�ͬ��������
		for (LogRec rec : logs) {
			if(rec.getLogType() == LogRec.LOG_IN) {
				logins[indexIn++] = rec;
			} else if(rec.getLogType() == LogRec.LOG_OUT){
				logouts[indexOut++] = rec;
			}
		}
	}

}
