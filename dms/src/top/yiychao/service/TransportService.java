package top.yiychao.service;

import java.util.Date;
import java.util.Scanner;

import top.yiychao.entity.LogRec;
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
* <p>修改说明:</p>
*/
public class TransportService {

	public Transport inputTransport() {
		// 建立一个从键盘接收数据的扫描器
		Scanner scanner = new Scanner(System.in);
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
		
		// 创建物流信息对象
		Transport transport = new Transport(id, nowDate, address, type, handler, receiver, transportType);
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
}
