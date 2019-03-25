package top.yiychao.ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName MainFrame.java
* @Description ������
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��25�� ����10:11:21 
* <p>�޸�˵��:</p>
*/
public class MainFrame extends JFrame{

	public MainFrame() {
		super("DMS�ͻ���ϵͳ");
		ImageIcon icon = new ImageIcon("images/dms.png");
		this.setIconImage(icon.getImage());
		
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}
