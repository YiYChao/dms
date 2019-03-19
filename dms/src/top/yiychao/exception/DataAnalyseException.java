package top.yiychao.exception;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DataBase.java
* @Description 自定义数据分析异常类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月19日 下午04:45:20 
* <p>修改说明:</p>
*/
public class DataAnalyseException extends Exception {

	// 空构造
	public DataAnalyseException() {
		
	}
	
	// 有参构造
	public DataAnalyseException(String msg) {
		super(msg);
	}
}
