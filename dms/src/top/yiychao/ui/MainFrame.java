package top.yiychao.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import top.yiychao.entity.DataBase;
import top.yiychao.entity.LogRec;
import top.yiychao.entity.MatchedLogRec;
import top.yiychao.entity.MatchedTableModel;
import top.yiychao.entity.MathcedTransport;
import top.yiychao.entity.Transport;
import top.yiychao.exception.DataAnalyseException;
import top.yiychao.gather.LogRecAnalyse;
import top.yiychao.gather.TransportAnalyse;
import top.yiychao.net.DmsNetServer;
import top.yiychao.service.LogRecService;
import top.yiychao.service.TransportService;
import top.yiychao.util.Config;

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

	// 声明界面组件
	private JMenuBar menuBar;
	private JMenu menuOperate, menuHelp, menuMatch;
	private JMenuItem miGather, miMatchLog, miMatchTran, miSave, miSend, miShow, miExit, miCheck, miAbout;
	private JTabbedPane tpGather, tpShowPane;
	private JPanel panel, pLog, pTran, pLogId, pName, pLocation, pIP, pLogStatus, pLogButton, pTransId, pAddress, pHandler, pReceiver, pTranStatus, pTranButton;
	private JLabel lbLogId, lbName, lbLocation, lbIP, lbLogStatus, lbTransId, lbAddress, lbHandler, lbReceiver, lbTranStatus;
	private JTextField txtLogId, txtName, txtLocation, txtIP, txtTransId, txtAddress, txtHandler, txtReceiver;
	private JRadioButton rbLogin, rbLogout;
	private JButton btnLogConfirm, btnLogReset, btnTranConfirm, btnTranReset, btnGather, btnMatchLog, btnSave, btnSend, btnShow;
	private JComboBox<String> cmbTranStatus;
	private JToolBar toolBar;
	private JScrollPane scrollPane;
	private CardLayout cardLayout;
	
	private LogRec logRec;
	private Transport trans;
	private ArrayList<LogRec> logList;
	private ArrayList<Transport> tranList;
	private ArrayList<MatchedLogRec> matchedLogs;
	private ArrayList<MathcedTransport> matchedTrans;
	
	private LogRecService logService;
	private TransportService tranService;
	
	private String serverIP;
	
	public MainFrame() {
		super("DMS系统客户端");
		ImageIcon icon = new ImageIcon("images/dms.png");
		this.setIconImage(icon.getImage());
		
		logList = new ArrayList<LogRec>();
		tranList = new ArrayList<Transport>();
		matchedLogs = new ArrayList<MatchedLogRec>();
		matchedTrans = new ArrayList<MathcedTransport>();
		logService = new LogRecService();
		tranService = new TransportService();
		
		// 初始化菜单
		initMenu();
		// 初始化工具栏
		initToolBar();
		
		cardLayout = new CardLayout();	// 卡片式布局
		panel = new JPanel();	// 主面板
		panel.setLayout(cardLayout);
		
		tpGather = new JTabbedPane(JTabbedPane.TOP);
		tpShowPane = new JTabbedPane(JTabbedPane.TOP);
		tpShowPane.addTab("日志", new JScrollPane());
		tpShowPane.addTab("物流", new JScrollPane());
		panel.add(tpGather, "gather");
		panel.add(tpShowPane, "show");
		
		this.add(panel, BorderLayout.CENTER);
		initLogGatherGUI();	// 初始化日志信息才接界面
		initTransGatherGUI();	// 初始化物流数据采集界面
		
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 开启更新表格数据的线程
		new UpdateTableThread().start();
		
		// 从配置文件中获取网络通信服务器的IP地址
		serverIP = Config.getValue("serverIP");
		
		new DmsNetServer();
	}
	
	/**
	* @Function initMenu
	* @Description 初始化菜单
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 上午10:17:47 
	* <p>修改说明:</p>
	 */
	private void initMenu() {
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menuOperate = new JMenu("操作");
		menuBar.add(menuOperate);
		
		miGather = new JMenuItem("采集数据");
		miGather.addActionListener(new GatherListener());
		menuOperate.add(miGather);
		
		menuMatch = new JMenu("数据匹配");
		miMatchLog = new JMenuItem("匹配日志数据");
		miMatchLog.addActionListener(new MatchLogListener());
		miMatchTran = new JMenuItem("匹配物流数据");
		miMatchTran.addActionListener(new MatchTranListener());
		menuMatch.add(miMatchLog);
		menuMatch.add(miMatchTran);
		menuOperate.add(menuMatch);
		
		miSave = new JMenuItem("保存数据");
		miSave.addActionListener(new SaveDataListener());
		menuOperate.add(miSave);
		
		miSend = new JMenuItem("发送数据");
		miSend.addActionListener(new SendDataListener());
		menuOperate.add(miSend);
		
		miShow = new JMenuItem("显示数据");
		miShow.addActionListener(new ShowDataListener());
		menuOperate.add(miShow);
		
		menuOperate.addSeparator(); 	// 添加分割线
		miExit = new JMenuItem("退出系统");
		miExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "您确定要退出系统吗？", "退出系统", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		menuOperate.add(miExit);
		
		menuHelp = new JMenu("帮助");
		menuBar.add(menuHelp);
		
		miCheck = new JMenuItem("查看帮助");
//		miCheck.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "本系统实现数据的采集、过滤分析匹配、保存、发送及显示功能", "帮助", JOptionPane.QUESTION_MESSAGE);
//			}
//		});
		// 使用Lambda表达式方式监听
		miCheck.addActionListener(e -> JOptionPane.showMessageDialog(null, "本系统实现数据的采集、过滤分析匹配、保存、发送及显示功能", "帮助", JOptionPane.QUESTION_MESSAGE));
		menuHelp.add(miCheck);
		
		miAbout = new JMenuItem("关于系统");
//		miAbout.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "版本：1.0版\n作者：易小超\n版权：小超工作室", "关于", JOptionPane.WARNING_MESSAGE);
//			}
//		});
		// 使用Lambda表达式方式监听
		miAbout.addActionListener(e -> JOptionPane.showMessageDialog(null, "版本：1.0版\n作者：易小超\n版权：小超工作室", "关于", JOptionPane.WARNING_MESSAGE));
		menuHelp.add(miAbout);
	}
	
	/**
	* @Function initToolBar
	* @Description 初始化工具栏方法
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 上午11:05:44 
	* <p>修改说明:</p>
	 */
	public void initToolBar() {
		toolBar = new JToolBar();
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		ImageIcon gatherIcon = new ImageIcon("images/gatherData.png");
		btnGather = new JButton("数据采集", gatherIcon);
		btnGather.addActionListener(new GatherListener());
		toolBar.add(btnGather);
		
		ImageIcon matchIcon = new ImageIcon("images/matchData.png");
		btnMatchLog = new JButton("匹配日志", matchIcon);
		btnMatchLog.addActionListener(new MatchLogListener());
		toolBar.add(btnMatchLog);
		
		btnGather = new JButton("匹配物流", matchIcon);
		btnGather.addActionListener(new MatchTranListener());
		toolBar.add(btnGather);
		
		ImageIcon saveIcon = new ImageIcon("images/saveData.png");
		btnSave = new JButton("保存数据", saveIcon);
		btnSave.addActionListener(new SaveDataListener());
		toolBar.add(btnSave);
		
		ImageIcon sendIcon = new ImageIcon("images/sendData.png");
		btnSend = new JButton("发送数据", sendIcon);
		btnSend.addActionListener(new SendDataListener());
		toolBar.add(btnSend);
		
		ImageIcon showIcon = new ImageIcon("images/showData.png");
		btnShow = new JButton("显示数据", showIcon);
		btnShow.addActionListener(new ShowDataListener());
		toolBar.add(btnShow);
		
	}
	
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description  数据采集监听类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 上午10:27:58 
	* <p>修改说明:</p>
	 */
	public class GatherListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(panel, "gather");
		}
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description 匹配日志监听类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 上午10:37:50 
	* <p>修改说明:</p>
	 */
	public class MatchLogListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			LogRecAnalyse logAn = new LogRecAnalyse(logList);
			logAn.doFilter();	// 日志数据过滤
			matchedLogs = logAn.matchData();	// 日志数据分析
			JOptionPane.showMessageDialog(null, "日志数据过滤、分析匹配成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description 匹配物流信息监听类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 上午10:38:55 
	* <p>修改说明:</p>
	 */
	public class MatchTranListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			TransportAnalyse tranaAn = new TransportAnalyse(tranList);
			tranaAn.doFilter();
			matchedTrans = tranaAn.matchData();
			JOptionPane.showMessageDialog(null, "物流数据过滤、分析匹配成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description 保存数据监听类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 上午10:44:41 
	* <p>修改说明:</p>
	 */
	public class SaveDataListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(matchedLogs != null && matchedLogs.size() > 0) {
				// 保存匹配的日志信息
				logService.saveMatchLog(matchedLogs);
//				logService.saveMatchLog2DB(matchedLogs);
				JOptionPane.showMessageDialog(null, "匹配的日志数据已保存到文件和数据库中！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "没有匹配的日志信息！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			if(matchedTrans != null && matchedTrans.size() > 0) {
				tranService.saveMatchTrab(matchedTrans);
//				tranService.saveMatchTran2DB(matchedTrans);
				JOptionPane.showMessageDialog(null, "匹配的物流数据已保存到文件和数据库中！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "没有匹配的物流信息！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description  显示数据监听类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 上午10:48:33 
	* <p>修改说明:</p>
	 */
	public class ShowDataListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(panel, "show");
			tpShowPane.removeAll();
			flushMatchedLogTable();		// 刷新日志信息表
			flushMatchedTransTable();	// 刷新物流信息表
		}
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description 重置按钮监听类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 下午12:16:11 
	* <p>修改说明:</p>
	 */
	public class ResetListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			txtName.setText("");
			txtLocation.setText("");
			txtIP.setText("");
			txtAddress.setText("");
			txtHandler.setText("");
			txtReceiver.setText("");
			txtLogId.setText("");
			txtTransId.setText("");
			rbLogin.setSelected(true);
			rbLogout.setSelected(false);
			cmbTranStatus.setSelectedIndex(0);
		}
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description  日志数据采集监听器
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月29日 下午1:16:19 
	* <p>修改说明:</p>
	 */
	public class GatherLogListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = Integer.parseInt(txtLogId.getText().trim());	// 日志Id
			Date time = new Date();
			String address = txtLocation.getText().trim();
			int type = DataBase.GATHER;
			String user = txtName.getText().trim();
			String ip = txtIP.getText().trim();
			int logTypr = rbLogin.isSelected() ? LogRec.LOG_IN : LogRec.LOG_OUT;
			logRec = new LogRec(id, time, address, type, user, ip, logTypr);
			logList.add(logRec);
			JOptionPane.showMessageDialog(null, "日志采集成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description 物流数据采集监听类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月29日 下午1:27:10 
	* <p>修改说明:</p>
	 */
	public class GatherTransListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = Integer.parseInt(txtTransId.getText().trim());
			Date time = new Date();
			String address = txtAddress.getText().trim();
			int type = DataBase.GATHER;
			String handler = txtHandler.getText().trim();
			String receiver = txtReceiver.getText().trim();
			int transType = cmbTranStatus.getSelectedIndex() + 1;
			trans = new Transport(id, time, address, type, handler, receiver, transType);
			tranList.add(trans);
			JOptionPane.showMessageDialog(null, "日志采集成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	* @Function initLogGatherGUI
	* @Description 初始化日志数据采集界面
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 上午11:39:44 
	* <p>修改说明:</p>
	 */
	private void initLogGatherGUI() {
		pLog = new JPanel();
		tpGather.add("日志", pLog);
		pLog.setLayout(new BoxLayout(pLog, BoxLayout.Y_AXIS));
		
		pLogId = new JPanel();
		pLog.add(pLogId);
		pLogId.setLayout((new FlowLayout(FlowLayout.CENTER, 5, 5)));
		
		lbLogId = new JLabel("日  志  ID：");
		pLogId.add(lbLogId);
		
		txtLogId = new JTextField();
		txtLogId.setPreferredSize(new Dimension(100, 20));
		pLogId.add(txtLogId);
		
		pName = new JPanel();
		pLog.add(pName);
		pName.setLayout((new FlowLayout(FlowLayout.CENTER, 5, 5)));
		lbName = new JLabel("用  户  名：");
		pName.add(lbName);
		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(100, 20));
		pName.add(txtName);
		
		pLocation = new JPanel();
		pLog.add(pLocation);
		lbLocation = new JLabel("登录地点：");
		pLocation.add(lbLocation);
		txtLocation = new JTextField();
		txtLocation.setPreferredSize(new Dimension(100, 20));
		pLocation.add(txtLocation);
		
		pIP = new JPanel();
		pLog.add(pIP);
		lbIP = new JLabel("登  录  IP：");
		pIP.add(lbIP);
		txtIP = new JTextField();
		txtIP.setPreferredSize(new Dimension(100, 20));
		pIP.add(txtIP);
		
		pLogStatus = new JPanel();
		pLog.add(pLogStatus);
		lbLogStatus = new JLabel("登录状态：");
		pLogStatus.add(lbLogStatus);
		
		rbLogin = new JRadioButton("登录");
		pLogStatus.add(rbLogin);
		rbLogin.setSelected(true);
		
		rbLogout = new JRadioButton("登出");
		pLogStatus.add(rbLogout);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rbLogin);
		buttonGroup.add(rbLogout);
		
		pLogButton = new JPanel();
		pLog.add(pLogButton);
		btnLogConfirm = new JButton("确认");
		btnLogConfirm.addActionListener(new GatherLogListener());
		pLogButton.add(btnLogConfirm);
		
		btnLogReset = new JButton("重置");
		btnLogReset.addActionListener(new ResetListener());
		pLogButton.add(btnLogReset);
		
	}
	
	/**
	* @Function initTransGatherGUI
	* @Description 初始化物流数据采集界面
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月28日 下午9:47:43 
	* <p>修改说明:</p>
	 */
	private void initTransGatherGUI() {
		pTran = new JPanel();
		tpGather.add("物流", pTran);
		pTran.setLayout(new BoxLayout(pTran, BoxLayout.Y_AXIS));
		
		pTransId = new JPanel();
		pTran.add(pTransId);
		
		lbTransId = new JLabel("物  流  ID：");
		pTransId.add(lbTransId);
		txtTransId = new JTextField();
		txtTransId.setPreferredSize(new Dimension(100, 20));
		pTransId.add(txtTransId);
		
		pAddress = new JPanel();
		pTran.add(pAddress);
		lbAddress = new JLabel("目  的  地：");
		pAddress.add(lbAddress);
		txtAddress = new JTextField();
		txtAddress.setPreferredSize(new Dimension(100, 20));
		pAddress.add(txtAddress);
		
		pHandler = new JPanel();
		pTran.add(pHandler);
		lbHandler = new JLabel("经  手  人：");
		pHandler.add(lbHandler);
		txtHandler = new JTextField();
		txtHandler.setPreferredSize(new Dimension(100, 20));
		pHandler.add(txtHandler);
		
		pReceiver = new JPanel();
		pTran.add(pReceiver);
		lbReceiver = new JLabel("收  货  人：");
		pReceiver.add(lbReceiver);
		txtReceiver = new JTextField();
		txtReceiver.setPreferredSize(new Dimension(100, 20));
		pReceiver.add(txtReceiver);
		
		pTranStatus = new JPanel();
		pTran.add(pTranStatus);
		lbTranStatus = new JLabel("物流状态：");
		pTranStatus.add(lbTranStatus);
		String[] tranStatus = new String[] {"发货中", "送货中", "已签收"};
		cmbTranStatus = new JComboBox<String>(tranStatus);
		cmbTranStatus.setPreferredSize(new Dimension(100, 20));
		pTranStatus.add(cmbTranStatus);
		
		pTranButton = new JPanel();
		pTran.add(pTranButton);
		btnTranConfirm = new JButton("确认");
		btnTranConfirm.addActionListener(new GatherTransListener());
		pTranButton.add(btnTranConfirm);
		
		btnTranReset = new JButton("重置");
		btnTranReset.addActionListener(new ResetListener());
		pTranButton.add(btnTranReset);
	}
	
	/**
	* @Function flushMatchedLogTable
	* @Description 刷新日志信息类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月29日 下午9:52:01 
	* <p>修改说明:</p>
	 */
	public void flushMatchedLogTable() {
		MatchedTableModel logModel = new MatchedTableModel(logService.readLogResult(), 1);
		JTable logTable = new JTable(logModel);
		scrollPane = new JScrollPane(logTable);
		tpShowPane.addTab("日志", scrollPane);
	}
	
	/**
	* @Function flushMatchedTransTable
	* @Description 刷新物流信息表
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月29日 下午9:51:26 
	* <p>修改说明:</p>
	 */
	public void flushMatchedTransTable() {
		MatchedTableModel transModel = new MatchedTableModel(tranService.readTranResult(), 0);
		JTable transTable = new JTable(transModel);
		scrollPane = new JScrollPane(transTable);
		tpShowPane.addTab("物流", scrollPane);
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description 线程类，每隔两分钟刷新一次显示数据表中的数据
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月30日 下午11:28:55 
	* <p>修改说明:</p>
	 */
	private class UpdateTableThread extends Thread{
		// 重写run方法
		public void run() {
			while(true) {
				// 移除所有的选项卡
				tpShowPane.removeAll();
				flushMatchedLogTable();	// 刷新日志信息
				flushMatchedTransTable();	// 刷新物流信息
				try {
					Thread.sleep(2*60*1000);  	// 线程挂起两分钟
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("刷新数据完毕！");
			}
		}
	}
	
	/**
	* Copyright: Copyright (c) 2019 YiYChao
	* 
	* @ClassName MainFrame.java
	* @Description 发送数据监听类
	*
	* @version v1.0.0
	* @author YiChao
	* @date 2019年3月31日 下午9:53:19 
	* <p>修改说明:</p>
	 */
	private class SendDataListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(matchedLogs != null && matchedLogs.size() > 0) {
					Socket logSocket = new Socket(serverIP, 8888);
					ObjectOutputStream logOutputStream = new ObjectOutputStream(logSocket.getOutputStream());
					logOutputStream.writeObject(matchedLogs);
					logOutputStream.flush();
					logOutputStream.close();
					matchedLogs.clear();
					JOptionPane.showMessageDialog(null, "匹配的日志数据已经发送到服务器", "提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "没有匹配的日志数据", "提示", JOptionPane.WARNING_MESSAGE);
				}
				if(matchedTrans != null && matchedTrans.size() > 0) {
					Socket tranSocket = new Socket(serverIP,8889);
					ObjectOutputStream tranOutputStream = new ObjectOutputStream(tranSocket.getOutputStream());
					tranOutputStream.writeObject(matchedTrans);
					tranOutputStream.flush();
					tranOutputStream.close();
					matchedTrans.clear();
					JOptionPane.showMessageDialog(null, "匹配的物流数据已经发送到服务器", "提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "没有物流的日志数据", "提示", JOptionPane.WARNING_MESSAGE);
				}
			} catch (IOException ex) {
				
			}
		}
	}
}
