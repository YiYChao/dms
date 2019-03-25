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
* @Description �û�ע�ᴰ��
*
* @version v1.0.0
* @author YiChao
* @date 2019��3��23�� ����4:21:41 
* <p>�޸�˵��:</p>
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
		super("�û�ע��");
		userService = new UserService();
		ImageIcon icon = new ImageIcon("images/dms.png");
		this.setIconImage(icon.getImage());	// ���ô���ͼ��
		// �������Ϊ���񲼾�
		panel = new JPanel(new GridLayout(8, 1));
		// ʵ�������
		lbName = new JLabel("��  ��  ��:");
		lbPwd = new JLabel("��        ��:");
		lbRePwd = new JLabel("ȷ������:");
		lbSex = new JLabel("��         ��:");
		lbHobby = new JLabel("��         ��:");
		lbAddr = new JLabel("��         ַ:");
		lbDegree = new JLabel("ѧ         ��:");
		
		txtName = new JTextField(16);
		txtPwd = new JPasswordField(16);
		txtRePwd = new JPasswordField(16);
		rbMale = new JRadioButton("��");
		reFemale = new JRadioButton("Ů");
		
		// �Ա�ĵ�ѡ�߼�
		ButtonGroup group = new ButtonGroup();
		group.add(rbMale);
		group.add(reFemale);
		
		ckbRead = new JCheckBox("�Ķ�");
		ckbNet = new JCheckBox("����");
		ckbSwin = new JCheckBox("��Ӿ");
		ckbTour = new JCheckBox("����");
		
		txtAddr = new JTextArea(3,20);
		
		String[] str = new String[]{"Сѧ","����","����","����","˶ʿ","��ʿ"};
		cmbDegree = new JComboBox<String>(str);
		cmbDegree.setEditable(true); 	// ������Ͽ�ɱ༭
		
		btnOK = new JButton("ȷ��");
		btnCancle = new JButton("����");
		
		// ������ֱ������壬Ȼ�������������
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
