package top.yiychao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import top.yiychao.entity.User;
import top.yiychao.service.UserService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LoginFrame.java
* @Description �û���¼����
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��25�� ����10:21:21 
* <p>�޸�˵��:</p>
*/
public class LoginFrame extends JFrame{
	private JPanel panel;
	private JLabel lbName,lbPwd;
	private JTextField txtName;
	private JPasswordField txtPwd;
	private JButton btnOK,btnReset,btnRegist;
	
	private static User user;
	private UserService userService;
	
	public LoginFrame() {
		super("DMS-�û���¼");
		userService = new UserService();
		ImageIcon icon = new ImageIcon("images/dms.png");
		this.setIconImage(icon.getImage());

		// ʵ�������
		panel = new JPanel();
		panel.setLayout(null);
		
		lbName = new JLabel("�û���:");
		lbPwd = new JLabel("��     ��:");
		txtName = new JTextField(20);
		txtPwd = new JPasswordField(20);
		txtPwd.setEchoChar('*');
		
		btnOK = new JButton("��¼");
		btnOK.addActionListener(new LoginListener());
		btnReset = new JButton("����");
		btnReset.addActionListener(new ResetListener());
		btnRegist = new JButton("ע��");
		btnRegist.addActionListener(new RegistListener());
		
		lbName.setBounds(30, 30, 60, 25);
		lbPwd.setBounds(30, 60, 60, 25);
		txtName.setBounds(95, 30, 120, 25);
		txtPwd.setBounds(95, 60, 120, 25);
		btnOK.setBounds(30, 90, 60, 25);
		btnReset.setBounds(95, 90, 60, 25);
		btnRegist.setBounds(160, 90, 60, 25);
		
		panel.add(lbName);
		panel.add(lbPwd);
		panel.add(txtName);
		panel.add(txtPwd);
		panel.add(btnOK);
		panel.add(btnReset);
		panel.add(btnRegist);
		
		this.add(panel);
		this.setSize(250, 170);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**   
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName LoginListener.java
	* @Description ��¼��ť����
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��25�� ����10:43:12 
	* <p>�޸�˵��:</p>
	*/
	public class LoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			user = userService.findUserByName(txtName.getText().trim());
			if(user != null) {
				if(user.getPassword().equals(new String(txtPwd.getPassword()))) {
					LoginFrame.this.setVisible(false);
					new MainFrame();
				}else {
					System.out.println("�û������������");
					txtPwd.setText("");
				}
			}else {
				System.out.println("���û������ڣ�����ע�ᣡ");
			}
		}
	}
	
	/**   
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName ResetListener.java
	* @Description ���ð�ť����
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��25�� ����10:47:39 
	* <p>�޸�˵��:</p>
	*/
	public class ResetListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			txtName.setText("");
			txtPwd.setText("");
		}
	}
	
	/**   
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName RegiserListener.java
	* @Description ע�ᰴť����
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019��3��25�� ����10:50:57 
	* <p>�޸�˵��:</p>
	*/
	public class RegistListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			LoginFrame.this.setVisible(false);
			new RegistFrame();
		}
		
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}

}
