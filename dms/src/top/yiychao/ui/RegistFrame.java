package top.yiychao.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import top.yiychao.entity.User;
import top.yiychao.service.UserService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName RegistFrame.java
* @Description 用户注册窗口
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月23日 下午4:21:41 
* <p>修改说明:</p>
*/
public class RegistFrame extends JFrame{

	private JPanel panel;
	private JLabel lbName,lbPwd,lbRePwd,lbSex,lbHobby,lbAddr,lbDegree;
	private JTextField txtName;
	private JPasswordField txtPwd,txtRePwd;
	private JRadioButton rbMale,reFemale;
	private JCheckBox ckbRead,ckbNet,ckbSwin,ckbTour;
	private JTextArea txtAddr;
	private JComboBox<String> cmbDegree;
	private JButton btnOK, btnCancle;
	private static User user;
	private static UserService userService;
	
	public RegistFrame() {
		super("用户注册");
		userService = new UserService();
		ImageIcon icon = new ImageIcon("images/dms.png");
		this.setIconImage(icon.getImage());	// 设置窗体图标
		// 设置面板为网格布局
		panel = new JPanel(new GridLayout(8, 1));
		// 实例化组件
		lbName = new JLabel("用  户  名:");
		lbPwd = new JLabel("密        码:");
		lbRePwd = new JLabel("确认密码:");
		lbSex = new JLabel("性         别:");
		lbHobby = new JLabel("爱         好:");
		lbAddr = new JLabel("地         址:");
		lbDegree = new JLabel("学         历:");
		
		txtName = new JTextField(16);
		txtPwd = new JPasswordField(16);
		txtRePwd = new JPasswordField(16);
		rbMale = new JRadioButton("男");
		reFemale = new JRadioButton("女");
		
		// 性别的单选逻辑
		ButtonGroup group = new ButtonGroup();
		group.add(rbMale);
		group.add(reFemale);
		
		ckbRead = new JCheckBox("阅读");
		ckbNet = new JCheckBox("上网");
		ckbSwin = new JCheckBox("游泳");
		ckbTour = new JCheckBox("旅游");
		
		txtAddr = new JTextArea(3,20);
		
		String[] str = new String[]{"小学","初中","高中","本科","硕士","博士"};
		cmbDegree = new JComboBox<String>(str);
		cmbDegree.setEditable(true); 	// 设置组合框可编辑
		
		btnOK = new JButton("确定");
		btnCancle = new JButton("重置");
		
		// 将组件分别放入面板，然后将面板放入主面板
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.add(lbName);
		p1.add(txtName);
		panel.add(p1);
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(lbPwd);
		p2.add(txtPwd);
		panel.add(p2);
		
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.add(lbRePwd);
		p3.add(txtRePwd);
		panel.add(p3);
		
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p4.add(lbSex);
		p4.add(rbMale);
		p4.add(reFemale);
		panel.add(p4);
		
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p5.add(lbHobby);
		p5.add(ckbRead);
		p5.add(ckbNet);
		p5.add(ckbSwin);
		p5.add(ckbTour);
		panel.add(p5);
		
		JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p6.add(lbAddr);
		p6.add(txtAddr);
		panel.add(p6);
		
		JPanel p7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p7.add(lbDegree);
		p7.add(cmbDegree);
		panel.add(p7);
		
		JPanel p8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p8.add(btnOK);
		p8.add(btnCancle);
		panel.add(p8);

		this.add(panel);
		this.setSize(310, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new RegistFrame();
	}
}
