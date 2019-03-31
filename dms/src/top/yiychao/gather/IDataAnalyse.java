package top.yiychao.gather;

import java.util.ArrayList;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName IDataAnalyse.java
* @Description 数据分析接口
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月17日 下午8:34:31 
* <p>修改说明:使用@FunctionalInterface注解将该接口定义为函数式接口</p>
*/
@FunctionalInterface
public interface IDataAnalyse {

	/**
	* @Function matchData
	* @Description	进行数据匹配
	*
	* @return
	* @return Object[]
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月17日 下午8:36:15 
	* <p>修改说明:使用泛型迭代升级数据分析接口</p>
	 */
	ArrayList<?> matchData();
}
