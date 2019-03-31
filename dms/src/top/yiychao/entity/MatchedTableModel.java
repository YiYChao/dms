package top.yiychao.entity;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName MatchedTableModel.java
* @Description 表格模型类
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月29日 下午1:49:58 
* <p>修改说明:</p>
*/
public class MatchedTableModel extends AbstractTableModel{

	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private int sign;	// 标志位,0物流，1日志
	private SimpleDateFormat format;
	
	public MatchedTableModel(ResultSet rs, int sign) {
		this.rs = rs;
		this.sign = sign;
		try {
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			rsmd = null;
		}
		format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	}
	
	/**
	 * 获取表格的行数
	 */
	@Override
	public int getRowCount() {
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			return 0;
		}
	}

	/**
	 * 获取表格的列数
	 */
	@Override
	public int getColumnCount() {
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			return 0;
		}
	}

	/**
	 * 获取指定位置的值
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			rs.absolute(rowIndex + 1);
			if(columnIndex == 1) {
				return format.format(rs.getDate(columnIndex + 1));
			}
			return rs.getObject(columnIndex + 1);
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * 获取表头信息
	 */
	public String getColumnName(int column) {
		String[] logArray = {"日志ID", "采集时间", "采集地点", "状态", "用户名", "IP", "日志类型"};
		String[] tranArray = {"物流ID", "采集时间", "目的地", "状态", "经手人", "收货人", "物流类型"};
		return sign == 1 ? logArray[column] : tranArray[column];
	}
}
