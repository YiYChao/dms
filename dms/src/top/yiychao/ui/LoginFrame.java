package top.yiychao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import top.yiychao.entity.User;
import top.yiychao.service.UserService;

/**   
* Copyright: Copyright (c) 2019 YiYChao
* 
* @ClassName LoginFrame.java
* @Description 用户登录窗口
*
* @version v1.0.0
* @author YiChao
* @date 2019年3月25日 下午10:21:21 
* <p>修改说明:</p>
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
		super("DMS-用户登录");
		userService = new UserService();
		ImageIcon icon = new ImageIcon("images/dms.png");
		this.setIconImage(icon.getImage());

		// 实例化组件
		panel = new JPanel();
		panel.setLayout(null);
		
		lbName = new JLabel("用户名:");
		lbPwd = new JLabel("密     码:");
		txtName = new JTextField(20);
		txtPwd = new JPasswordField(20);
		txtPwd.setEchoChar('*');
		
		btnOK = new JButton("登录");
		btnOK.addActionListener(new LoginListener());
		btnReset = new JButton("重置");
		btnReset.addActionListener(new ResetListener());
		btnRegist = new JButton("注册");
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
	* @Description 登录按钮监听
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月25日 下午10:43:12 
	* <p>修改说明:</p>
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
//					System.out.println("用户名或密码错误！");
					JOptionPane.showMessageDialog(null, "密码错误！请重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
					txtPwd.setText("");
				}
			}else {
//				System.out.println("该用户不存在，请先注册！");
				JOptionPane.showMessageDialog(null, "该用户不存在，请先注册！", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**   
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName ResetListener.java
	* @Description 重置按钮监听
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月25日 下午10:47:39 
	* <p>修改说明:</p>
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
	* @Description 注册按钮监听
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月25日 下午10:50:57 
	* <p>修改说明:</p>
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
