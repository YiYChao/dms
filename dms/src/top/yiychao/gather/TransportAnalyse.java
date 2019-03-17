package top.yiychao.gather;

import top.yiychao.entity.DataBase;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.entity.Transport;

public class TransportAnalyse extends DataFilter implements IDataAnalyse{

	// ��������
	private Transport[] transSends;
	// �ͻ�����
	private Transport[] transIngs;
	// ��ǩ�ռ���
	private Transport[] transRecs;
	
	// �չ���
	public TransportAnalyse() {
	}
	
	public TransportAnalyse(Transport[] trans) {
		super(trans);
	}

	@Override
	public Object[] matchData() {
		// ��������ƥ������
		MathcedTransport[] mathcedTrans = new MathcedTransport[transSends.length];
		// ��־ƥ�������±��¼
		int index = 0;
		// ����ƥ�����
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
		//  ��ȡ���ݼ���
		Transport[] trans  = (Transport[]) this.getDatas();
		// ��������״̬ͳ�Ʋ�ͬ״̬����������
		int numSend = 0;
		int numTran = 0;
		int numRece = 0;
		// ����ͳ��
		for (Transport tran : trans) {
			if(tran.getTransportType() == Transport.SENDING) {
				numSend++;
			}else if(tran.getTransportType() == Transport.TRANSPORTING) {
				numTran++;
			}else if(tran.getTransportType() == Transport.RECEIVED) {
				numRece++;
			}
		}
		// ������ͬ����������
		transSends = new Transport[numSend];
		transIngs = new Transport[numTran];
		transRecs = new Transport[numRece];
		// �����±��¼
		int indexSend = 0;
		int indexTran = 0;
		int indexRece = 0;
		// ������������������й��ˣ���������״̬�ֱ���ڲ�ͬ��������
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
