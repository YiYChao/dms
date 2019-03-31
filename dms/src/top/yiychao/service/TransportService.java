package top.yiychao.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.sun.jmx.snmp.Timestamp;

import top.yiychao.entity.LogRec;
import top.yiychao.entity.MatchedLogRec;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.entity.Transport;
import top.yiychao.util.DBUtils;

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
//		for (MathcedTransport mathcedTransport : mathcedTransports) {
//			if(mathcedTransport != null) {
//				System.out.println(mathcedTransport.toString());
//			}
//		}
		// 使用lambda表达式输出集合中的数据
		mathcedTransports.forEach(e -> System.out.println(e.toString()));
	}
	
	/**
	 * @Function saveMatchTrab
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
		try (ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream("temp/MatchTrans.txt",true))){
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
	 * @Function readMatchTran
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
		try (ObjectInputStream obs = new ObjectInputStream(new FileInputStream("temp/MatchTrans.txt"))){
			MathcedTransport matchTran;
			while((matchTran = (MathcedTransport)obs.readObject()) != null) {
				matchTrans.add(matchTran);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return matchTrans;
	}
	
	/**
	 * @Function saveMatchTran2DB
	 * @Description	保存匹配物流信息到数据库
	 *
	 * @param matchTrans 集合泛型
	 * @throws	
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月22日 上午11:12:20
	 * <p>修改说明:</p>
	 */
	public void saveMatchTran2DB(ArrayList<MathcedTransport> matchTrans) {
		DBUtils db = new DBUtils();
		try {
			db.getConnection();	// 获得数据库连接
			for (MathcedTransport matchedTran : matchTrans) {
				Transport send = matchedTran.getSend();
				Transport trans = matchedTran.getTrans();
				Transport receive = matchedTran.getReceive();
				
				String sql = "INSERT INTO gather_transport(id, time, address, type, handler, receiver, transport_type) VALUES(?,?,?,?,?,?,?) ";
				
				Object[] param = new Object[] {send.getId(), new java.sql.Timestamp(send.getTime().getTime()), send.getAddress(), send.getType(),
						send.getHandler(),send.getReceiver(),send.getTransportType()};
				db.executeUpdate(sql, param);
				
				param = new Object[] {trans.getId(), new java.sql.Timestamp(trans.getTime().getTime()), trans.getAddress(), trans.getType(),
						trans.getHandler(),trans.getReceiver(),trans.getTransportType()};
				db.executeUpdate(sql, param);
				
				param = new Object[] {receive.getId(), new java.sql.Timestamp(receive.getTime().getTime()), receive.getAddress(), receive.getType(),
						receive.getHandler(),receive.getReceiver(),receive.getTransportType()};
				db.executeUpdate(sql, param);
				
				// 保存匹配日志的Id
				sql = "INSERT INTO matched_transport(send_id, trans_id, received_id) VALUES(?,?,?)";
				param = new Object[] {send.getId(), trans.getId(), receive.getId()};
				db.executeUpdate(sql, param);
			}
			db.closeAll();	// 关闭数据库连接，释放资源
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Function readMatchedLogFromDB
	 * @Description	从数据库读取匹配的物流信息
	 *
	 * @return
	 * @throws	
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月22日 上午11:13:14
	 * <p>修改说明:</p>
	 */
	public ArrayList<MathcedTransport> readMatchedTrabFromDB(){
		ArrayList<MathcedTransport> matchedTrans = new ArrayList<MathcedTransport>();
		DBUtils db = new DBUtils();
		try {
			db.getConnection();
			String sql  = "SELECT s.id, s.time, s.address, s.type, s.handler, s.receiver, s.transport_type,t.id, t.time, t.address, t.type, t.handler, t.receiver, t.transport_type,"
					+ "r.id, r.time, r.address, r.type, r.handler, r.receiver, r.transport_type "
					+ "FROM matched_transport m, gather_transport s, gather_transport t,gather_transport r WHERE m.send_id=s.id AND m.trans_id=t.id AND m.received_id=r.id";
			ResultSet rs = db.executeQuery(sql, null);
			while(rs.next()) {
				Transport send = new Transport(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7));
				Transport tans = new Transport(rs.getInt(8), rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14));
				Transport receive = new Transport(rs.getInt(15), rs.getDate(16), rs.getString(17), rs.getInt(18), rs.getString(19), rs.getString(20), rs.getInt(21));
				
				matchedTrans.add(new MathcedTransport(send, tans, receive));	// 添加匹配登录信息到集合
			}
			db.closeAll(); 	// 释放资源
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matchedTrans;
	}
	
	/**
	* @Function readTranResult
	* @Description 读取数据库中所有匹配物流信息
	*
	* @return 返回一个ResultSet
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月29日 下午2:14:57 
	* <p>修改说明:</p>
	 */
	public ResultSet readTranResult() {
		DBUtils db = new DBUtils();
		ResultSet rs = null;
		try {
			Connection conn = db.getConnection();
			Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * FROM gather_transport";
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
