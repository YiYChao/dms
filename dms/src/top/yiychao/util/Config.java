package top.yiychao.util;

import java.io.FileInputStream;
import java.util.Properties;
/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName Config.java
* @Description 配置类，读取属性文件中的数据
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月21日 下午5:03:16 
* <p>修改说明:</p>
*/
public class Config {

	private static Properties pro = null;
	static {
		try {
			pro = new Properties();
			pro.load(new FileInputStream("config/mysql.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* @Function getValue
	* @Description	根据Key获取值
	*
	* @return LogRec	日志实体类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月16日 下午1:55:12 
	* <p>修改说明:</p>
	 */
	public static String getValue(String Key) {
		return pro.get(Key).toString();
	}
	
}
