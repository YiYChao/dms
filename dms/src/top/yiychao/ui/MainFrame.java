package top.yiychao.ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName MainFrame.java
* @Description 主窗口
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月25日 下午10:11:21 
* <p>修改说明:</p>
*/
public class MainFrame extends JFrame{

	public MainFrame() {
		super("DMS客户端系统");
		ImageIcon icon = new ImageIcon("images/dms.png");
		this.setIconImage(icon.getImage());
		
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}
