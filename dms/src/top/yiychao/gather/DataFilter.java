package top.yiychao.gather;

import top.yiychao.entity.DataBase;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DataFilter.java
* @Description 数据过滤抽象类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月17日 下午8:37:00 
* <p>修改说明:</p>
*/
public abstract class DataFilter {

	// 数据集合
	private DataBase[] datas;

	public DataBase[] getDatas() {
		return datas;
	}

	public void setDatas(DataBase[] datas) {
		this.datas = datas;
	}

	public DataFilter() {
	}

	public DataFilter(DataBase[] datas) {
		this.datas = datas;
	}
	
	/**
	* @Function doFilter
	* @Description	数据过滤抽象方法
	*
	* @return 空
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月17日 下午8:40:50 
	* <p>修改说明:</p>
	 */
	public abstract void doFilter();
	
}
