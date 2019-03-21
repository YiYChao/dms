package top.yiychao.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import top.yiychao.entity.LogRec;
import top.yiychao.entity.MatchedLogRec;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.entity.Transport;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName TransportService.java
* @Description 物流业务类，实现物流数据信息的采集及显示功能
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月16日 下午3:27:14 
* <p>修改说明:增加显示泛型集合数据的功能</p>
*/
public class TransportService {

	public Transport inputTransport() {
		// 建立一个从键盘接收数据的扫描器
		Scanner scanner = new Scanner(System.in);
		// 创建物流信息对象
		Transport transport = null;
		try {
			// 提示用户输入ID标识
			System.out.println("请输入ID标识:");
			int id = scanner.nextInt();
			// 获取系统时间
			Date nowDate = new Date();
			// 提示用户输入地址
			System.out.println("请输入地址:");
			String address = scanner.next();
			// 数据状态是采集
			int type = LogRec.GATHER;
			
			// 提示用户输入货物经手人
			System.out.println("请输入 货物经手人:");
			String handler = scanner.next();
			// 提示用户输入收货人
			System.out.println("请输入 收货人:");
			String receiver = scanner.next();
			// 提示用户数输入物流状态
			System.out.println("请输入物流状态: 1发货中, 2送货中, 3已签收");
			int transportType = scanner.nextInt();
			
			// 初始化物流信息对象
			transport = new Transport(id, nowDate, address, type, handler, receiver, transportType);
		} catch (Exception e) {
			System.out.println("采集的日志信息不合法！");
		}
		return transport;
	}
	
	/**
	* @Function showTransport
	* @Description	输出显示物流信息
	*
	* @param transports	不定参数，物流信息实体类
	* @return void	空
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月16日 下午3:36:19 
	* <p>修改说明:</p>
	 */
	public void showTransport(Transport...transports ) {
		for (Transport transport : transports) {
			if(transport != null) {
				System.out.println(transport.toString());
			}
		}
	}
	
	/**
	* @Function showMatchTransport
	* @Description	输出匹配的物流信息
	*
	* @param mathcedTransports	物流信息匹配实体，不定参数
	* @return void	空
	* @throws	运行时异常
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月17日 上午12:05:45 
	* <p>修改说明:</p>
	 */
	public void showMatchTransport(MathcedTransport...mathcedTransports) {
		for (MathcedTransport mathcedTransport : mathcedTransports) {
			if(mathcedTransport != null) {
				System.out.println(mathcedTransport.toString());
			}
		}
	}
	
	/**
	 * @Function showMatchTransport
	 * @Description	输出匹配的物流信息
	 *
	 * @param mathcedTransports	物流信息匹配实体，泛型集合
	 * @return void	空
	 * @throws	运行时异常
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月19日 下午5:29:45 
	 * <p>修改说明:</p>
	 */
	public void showMatchTransport(ArrayList<MathcedTransport> mathcedTransports) {
		for (MathcedTransport mathcedTransport : mathcedTransports) {
			if(mathcedTransport != null) {
				System.out.println(mathcedTransport.toString());
			}
		}
	}
	
	/**
	 * @Function showMathcedLog
	 * @Description	匹配物流信息保存到文件
	 *
	 * @param matchedLogs	物流信息匹配实体，集合泛型
	 * @return void	空
	 * @throws	无
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月21日 下午3:25:34 
	 * <p>修改说明:</p>
	 */
	public void saveMatchTrab(ArrayList<MathcedTransport> matchTrans) {
		try (ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream("MatchTrans.txt",true))){
			for (MathcedTransport mathcedTransport : matchTrans) {
				if( mathcedTransport != null) {
					obs.writeObject(mathcedTransport);
					obs.flush();
				}
			}
			obs.writeObject(null);
			obs.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Function showMathcedLog
	 * @Description	读取保存到文件的匹配物流信息
	 *
	 * @return 物流信息匹配实体，集合泛型
	 * @throws	无
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月21日 下午3:30:34 
	 * <p>修改说明:</p>
	 */
	public ArrayList<MathcedTransport> readMatchTran() {
		ArrayList<MathcedTransport> matchTrans = new ArrayList<MathcedTransport>();
		try (ObjectInputStream obs = new ObjectInputStream(new FileInputStream("MatchTrans.txt"))){
			MathcedTransport matchTran;
			while((matchTran = (MathcedTransport)obs.readObject()) != null) {
				matchTrans.add(matchTran);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return matchTrans;
	}
}
