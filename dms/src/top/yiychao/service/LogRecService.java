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
		for (MatchedLogRec matchedLogRec : matchedLogRecs) {
			if(matchedLogRec != null) {
				System.out.println(matchedLogRec.toString());
			}
		}
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
}
