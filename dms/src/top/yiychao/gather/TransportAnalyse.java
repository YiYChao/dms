package top.yiychao.gather;

import top.yiychao.entity.DataBase;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.entity.Transport;

public class TransportAnalyse extends DataFilter implements IDataAnalyse{

	// 发货集合
	private Transport[] transSends;
	// 送货集合
	private Transport[] transIngs;
	// 已签收集合
	private Transport[] transRecs;
	
	// 空构造
	public TransportAnalyse() {
	}
	
	public TransportAnalyse(Transport[] trans) {
		super(trans);
	}

	@Override
	public Object[] matchData() {
		// 创建物流匹配数组
		MathcedTransport[] mathcedTrans = new MathcedTransport[transSends.length];
		// 日志匹配数组下标记录
		int index = 0;
		// 数据匹配分析
		for (Transport send : transSends) {
			for (Transport tran : transIngs) {
				for (Transport rece : transRecs) {
					if((send.getReceiver().equals(tran.getReceiver())) && (send.getReceiver().equals(rece.getReceiver())) && 
							(send.getType() != DataBase.MATCH) &&  (rece.getType() != DataBase.MATCH)) {
						send.setType(DataBase.MATCH);
						tran.setType(DataBase.MATCH);
						rece.setType(DataBase.MATCH);
						mathcedTrans[index++] = new MathcedTransport(send, tran, rece);
					}
					
				}
				
			}
		}
		return mathcedTrans;
	}

	@Override
	public void doFilter() {
		//  获取数据集合
		Transport[] trans  = (Transport[]) this.getDatas();
		// 更具物流状态统计不同状态的物流个数
		int numSend = 0;
		int numTran = 0;
		int numRece = 0;
		// 遍历统计
		for (Transport tran : trans) {
			if(tran.getTransportType() == Transport.SENDING) {
				numSend++;
			}else if(tran.getTransportType() == Transport.TRANSPORTING) {
				numTran++;
			}else if(tran.getTransportType() == Transport.RECEIVED) {
				numRece++;
			}
		}
		// 创建不同的物流数组
		transSends = new Transport[numSend];
		transIngs = new Transport[numTran];
		transRecs = new Transport[numRece];
		// 数组下标记录
		int indexSend = 0;
		int indexTran = 0;
		int indexRece = 0;
		// 遍历，对物流数组进行过滤，根据物流状态分别放在不同的数组中
		for (Transport tran : trans) {
			if(tran.getTransportType() == Transport.SENDING) {
				transSends[indexSend++] = tran;
			}else if(tran.getTransportType() == Transport.TRANSPORTING) {
				transIngs[indexTran++] = tran;
			}else if(tran.getTransportType() == Transport.RECEIVED) {
				transRecs[indexRece++] = tran;
			}
		}
	}

}
