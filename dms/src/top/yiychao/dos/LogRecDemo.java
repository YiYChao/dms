package top.yiychao.dos;

import top.yiychao.entity.LogRec;
import top.yiychao.service.LogRecService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LogRecDemo.java
* @Description 	日志测试类，演示日志数据的采集及显示
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月16日 下午2:51:28 
* <p>修改说明:</p>
*/
public class LogRecDemo {

	/**
	* @Function main
	* @Description	主方法，实现日志数据的采集及显示
	*
	* @param args	系统默认参数
	* @return void	空
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月16日 下午2:52:25 
	* <p>修改说明:</p>
	 */
	public static void main(String[] args) {
		// 创建一个日志业务类
		LogRecService logRecService = new LogRecService();
		// 创建一个日志对象数组，用于存放采集的两个日志信息
		LogRec[] logs = new LogRec[2];
		for(int i = 0; i < logs.length; i++) {
			System.out.println("第" + ( i + 1) + "个日志数据采集：");
			logs[i] = logRecService.inputLog();
		}
		// 显示日志信息
		logRecService.showLog(logs);
	}
}
