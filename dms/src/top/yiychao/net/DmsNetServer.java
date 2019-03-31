package top.yiychao.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import top.yiychao.entity.MatchedLogRec;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.service.LogRecService;
import top.yiychao.service.TransportService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DmsNetServer.java
* @Description 接受客户端发送的数据并保存到数据库
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月31日 下午10:10:25 
* <p>修改说明:</p>
*/
public class DmsNetServer {

	public DmsNetServer() {
		// 开启监听8888端口的线程，接收日志数据
		new AcceptLogThread(8888).start();
		// 开启监听8889端口的线程，接收物流数据
		new AcceptTranThread(8889).start();
		System.out.println("网络服务器已经开启。。。");
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName DmsNetServer.java
	* @Description 接收日志数据的线程类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月31日 下午10:13:05 
	* <p>修改说明:</p>
	 */
	public class AcceptLogThread extends Thread{
		private ServerSocket serverSocket;
		private Socket socket;
		private LogRecService logRecService;
		private ObjectInputStream ois;
		
		public AcceptLogThread(int port) {
			logRecService = new LogRecService();
			try {
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(this.isAlive()) {
				try {
					socket = serverSocket.accept();
					if(socket != null) {
						ois = new ObjectInputStream(socket.getInputStream());
						// 反序列化，得到匹配日志列表
						ArrayList<MatchedLogRec> matchedLogs =  (ArrayList<MatchedLogRec>) ois.readObject();
						logRecService.saveMatchLog2DB(matchedLogs);	// 将数据保存到数据库
						System.out.println("日志数据成功写入数据库。。。");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public class AcceptTranThread extends Thread{
		private ServerSocket serverSocket;
		private Socket socket;
		private TransportService tranService;
		private ObjectInputStream ois;
		
		public AcceptTranThread(int port) {
			tranService = new TransportService();
			try {
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(this.isAlive()) {
				try {
					socket = serverSocket.accept();
					if(socket != null) {
						ois = new ObjectInputStream(socket.getInputStream());
						// 反序列化，得到匹配物流列表
						ArrayList<MathcedTransport> matchedTran = (ArrayList<MathcedTransport>) ois.readObject();
						tranService.saveMatchTran2DB(matchedTran);	//	将数据保存到数据库
						System.out.println("物流数据成功写入数据库。。。");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		}
			
	}
	
}
