package top.yiychao.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.sun.jmx.snmp.Timestamp;

import top.yiychao.entity.LogRec;
import top.yiychao.entity.MatchedLogRec;
import top.yiychao.util.DBUtils;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LogRecService.java
* @Description 日志业务类，实现日志数据信息额采集和显示功能
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月16日 下午1:53:16 
* <p>修改说明:增加显示泛型集合数据的功能，增加文件的读写</p>
*/
public class LogRecService {

	/**
	* @Function inputLog
	* @Description	日志数据采集
	*
	* @return LogRec	日志实体类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月16日 下午1:55:12 
	* <p>修改说明:</p>
	 */
	public LogRec inputLog() {
		// 建立一个从键盘接收数据的扫描器
		Scanner scanner = new Scanner(System.in);
		// 创建日志对象
		LogRec log = null;
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
			
			// 提示用户输入用户名
			System.out.println("请输入 登录用户名:");
			String user = scanner.next();
			// 提示用户输入主机IP
			System.out.println("请输入 主机IP:");
			String ip = scanner.next();
			// 提示用户数输入登录、登出状态
			System.out.println("请输入登录状态: 1是登录, 2是登出");
			int logType = scanner.nextInt();
			
			// 初始化日志对象
			log = new LogRec(id, nowDate, address, type, user, ip, logType);
		} catch (Exception e) {
			System.out.println("采集的日志信息不合法！");
		}
		// 返回日志对象
		return log;
	}
	
	/**
	* @Function showLog
	* @Description	输出日志信息
	*
	* @param logRecs	不定参数
	* @return void	空
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月16日 下午1:57:42 
	* <p>修改说明:</p>
	 */
	public void showLog(LogRec...logRecs ) {
		for (LogRec log : logRecs) {
			if(log != null) {
				System.out.println(log.toString());
			}
		}
	}
	
	/**
	* @Function showMathcedLog
	* @Description	输出匹配日志信息
	*
	* @param matchedLogRecs	日志信息匹配实体，不定参数
	* @return void	空
	* @throws	运行时异常
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月17日 上午12:07:34 
	* <p>修改说明:</p>
	 */
	public void showMathcedLog(MatchedLogRec...matchedLogRecs) {
		for (MatchedLogRec matchedLogRec : matchedLogRecs) {
			if(matchedLogRec != null) {
				System.out.println(matchedLogRec.toString());
			}
		}
	}
	
	/**
	 * @Function showMathcedLog
	 * @Description	输出匹配日志信息
	 *
	 * @param matchedLogRecs	日志信息匹配实体，集合泛型
	 * @return void	空
	 * @throws	运行时异常
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月19日 下午5:27:34 
	 * <p>修改说明:</p>
	 */
	public void showMathcedLog(ArrayList<MatchedLogRec> matchedLogRecs) {
//		for (MatchedLogRec matchedLogRec : matchedLogRecs) {
//			if(matchedLogRec != null) {
//				System.out.println(matchedLogRec.toString());
//			}
//		}
		// 使用lambda表达式输出集合中的数据
		matchedLogRecs.forEach(e -> System.out.println(e.toString()));
	}
	
	/**
	 * @Function saveMatchLog
	 * @Description	匹配日志信息保存到文件
	 *
	 * @param matchedLogs	日志信息匹配实体，集合泛型
	 * @return void	空
	 * @throws	无
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月21日 下午3:15:34 
	 * <p>修改说明:</p>
	 */
	public void saveMatchLog(ArrayList<MatchedLogRec> matchLogs) {
		try (ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream("temp/MatchLogs.txt",true))){
			
			for (MatchedLogRec matchedLogRec : matchLogs) {
				if(matchedLogRec != null) {
					obs.writeObject(matchedLogRec);
					obs.flush();
				}
			}
			// 文件末尾保存一个null对象，代表文件结束
			obs.writeObject(null);
			obs.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Function readMatchLog
	 * @Description	读取保存到文件的匹配日志信息
	 *
	 * @return ArrayList<MatchedLogRec>	集合泛型
	 * @throws	无
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月21日 下午3:20:34 
	 * <p>修改说明:</p>
	 */
	public ArrayList<MatchedLogRec> readMatchLog() {
		ArrayList<MatchedLogRec> matchLogs = new ArrayList<MatchedLogRec>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temp/MatchLogs.txt"))){
			MatchedLogRec logRec;
			while((logRec = (MatchedLogRec)ois.readObject()) != null) {
				matchLogs.add(logRec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchLogs;
	}
	
	/**
	 * 
	 * @Function saveMatchLog2DB
	 * @Description	 匹配日志信息保存到数据库
	 *
	 * @param matchLogs 集合泛型
	 * @throws	
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月22日 上午10:11:59
	 * <p>修改说明:</p>
	 */
	public void saveMatchLog2DB(ArrayList<MatchedLogRec> matchLogs) {
		DBUtils db = new DBUtils();
		try {
			db.getConnection();	// 获得数据库连接
			for (MatchedLogRec matchedLog : matchLogs) {
				LogRec login = matchedLog.getLogin();
				LogRec logout = matchedLog.getLogout();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String sql = "INSERT INTO gather_logrec(id, time, address, type, username, ip, logtype) VALUES(?,?,?,?,?,?,?) ";
				Object[] param = new Object[] {login.getId(),java.sql.Timestamp.valueOf(format.format(login.getTime())), login.getAddress(), login.getType(),
						login.getUser(),login.getIp(), login.getLogType()};
				db.executeUpdate(sql, param);
				
				param = new Object[] {logout.getId(), java.sql.Timestamp.valueOf(format.format(logout.getTime())), logout.getAddress(), logout.getType(),
						logout.getUser(), logout.getIp(), logout.getLogType()};
				db.executeUpdate(sql, param);
				// 保存匹配日志的Id
				sql = "INSERT INTO matched_logrec(login_id, logout_id) VALUES(?,?)";
				param = new Object[] {login.getId(), logout.getId()};
				db.executeUpdate(sql, param);
			}
			db.closeAll();	// 关闭数据库连接，释放资源
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Function readMatchedLogFromDB
	 * @Description	从数据库读匹配信息
	 *
	 * @return 集合泛型
	 * @throws	无
	 *
	 * @version v1.0.0
	 * @author YiChao
	 * @date 2019年3月22日 上午11:02:56
	 * <p>修改说明:</p>
	 */
	public ArrayList<MatchedLogRec> readMatchedLogFromDB(){
		ArrayList<MatchedLogRec> matchedLogRecs = new ArrayList<MatchedLogRec>();
		DBUtils db = new DBUtils();
		try {
			db.getConnection();
			String sql  = "SELECT i.id,i.time,i.address,i.type,i.username,i.ip,i.logtype,o.id,o.time,o.address,o.type,o.username,o.ip,o.logtype "
					+ "FROM matched_logrec m, gather_logrec i, gather_logrec o WHERE m.login_id = i.id AND m.logout_id = o.id";
			ResultSet rs = db.executeQuery(sql, null);
			while(rs.next()) {
				LogRec login = new LogRec(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7));
				LogRec logout = new LogRec(rs.getInt(8), rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14));
				
				matchedLogRecs.add(new MatchedLogRec(login, logout));	// 添加匹配登录信息到集合
			}
			db.closeAll(); 	// 释放资源
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matchedLogRecs;
	}
	
	/**
	* @Function readLogResult
	* @Description 获取数据空中的所有匹配日志信息
	*
	* @return 返回一个ResultSet
	* 
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月29日 下午2:13:23 
	* <p>修改说明:</p>
	 */
	public ResultSet readLogResult() {
		DBUtils db = new DBUtils();
		ResultSet rs = null;
		try {
			Connection conn = db.getConnection();
			Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * FROM gather_logrec";
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
