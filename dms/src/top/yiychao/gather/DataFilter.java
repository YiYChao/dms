package top.yiychao.gather;

import top.yiychao.entity.DataBase;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName DataFilter.java
* @Description ���ݹ��˳�����
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��17�� ����8:37:00 
* <p>�޸�˵��:</p>
*/
public abstract class DataFilter {

	// ���ݼ���
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
	* @Description	���ݹ��˳��󷽷�
	*
	* @return ��
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��17�� ����8:40:50 
	* <p>�޸�˵��:</p>
	 */
	public abstract void doFilter();
	
}
