package top.yiychao.gather;

import java.util.ArrayList;

import top.yiychao.entity.DataBase;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.entity.Transport;
import top.yiychao.exception.DataAnalyseException;
/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LogRecAnalyse.java
* @Description 物流数据分析类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月17日 下午8:49:05 
* <p>修改说明:将数组修改为泛型集合</p>
*/
public class TransportAnalyse extends DataFilter implements IDataAnalyse{

	// 发货集合
	private ArrayList<Transport> transSends = new ArrayList<Transport>();
	// 送货集合
	private ArrayList<Transport> transIngs = new ArrayList<Transport>();
	// 已签收集合
	private ArrayList<Transport> transRecs = new ArrayList<Transport>();
	
	// 空构造
	public TransportAnalyse() {
	}
	
	public TransportAnalyse(ArrayList<Transport> trans) {
		super(trans);
	}

	@Override
	public ArrayList<MathcedTransport> matchData() {
		// 创建物流匹配数组
		ArrayList<MathcedTransport> mathcedTrans = new ArrayList<MathcedTransport>();
		// 数据匹配分析
		for (Transport send : transSends) {
			for (Transport tran : transIngs) {
				for (Transport rece : transRecs) {
					if((send.getReceiver().equals(tran.getReceiver())) && (send.getReceiver().equals(rece.getReceiver())) && 
							(send.getType() != DataBase.MATCH) &&  (rece.getType() != DataBase.MATCH)) {
						send.setType(DataBase.MATCH);
						tran.setType(DataBase.MATCH);
						rece.setType(DataBase.MATCH);
						mathcedTrans.add(new MathcedTransport(send, tran, rece));
					}
					
				}
				
			}
		}
		try {
			if(mathcedTrans.size() == 0) {
				// 没有匹配的数据，则抛出自定义异常
				throw new DataAnalyseException("没有匹配的物流数据！");
			}
		} catch (DataAnalyseException e) {
			e.printStackTrace();
		}
		return mathcedTrans;
	}

	@Override
	public void doFilter() {
		//  获取数据集合
		ArrayList<Transport> trans  = (ArrayList<Transport>) this.getDatas();
		// 遍历统计,对物流数据进行过滤，根据物流状态分别放在不同的集合中
		for (Transport tran : trans) {
			if(tran.getTransportType() == Transport.SENDING) {
				transSends.add(tran);
			}else if(tran.getTransportType() == Transport.TRANSPORTING) {
				transIngs.add(tran);
			}else if(tran.getTransportType() == Transport.RECEIVED) {
				transRecs.add(tran);
			}
		}
	}

}
