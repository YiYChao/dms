package top.yiychao.dos;

import top.yiychao.entity.Transport;
import top.yiychao.service.TransportService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName TransportDemo.java
* @Description 物流测试类，实现物流数据信息的采集和显示
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月16日 下午3:37:58 
* <p>修改说明:</p>
*/
public class TransportDemo {

	public static void main(String[] args) {
		// 创建物流业务类
		TransportService transportService = new TransportService();
		// 创建一个物流对象数组，用于存放采集的三个物流信息
		Transport[] transports = new Transport[3];
		for(int i = 0; i < transports.length; i++) {
			System.out.println("第" + (i + 1) + "个物流数据采集:");
			transports[i] = transportService.inputTransport();
		}
		// 显示物流信息
		transportService.showTransport(transports);
	}
}
