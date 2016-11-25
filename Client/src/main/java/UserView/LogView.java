package UserView;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import UserView.MemberRegisterView;
import WebPromotionView.WebPromotionUserView;
import common.UserType;
import runner.ClientRunner;
import uiController.HotelMainUiController;
import uiController.MemberRegisterUiController;
import uiController.WebAdminUserUiController;
import uiController.customerMainViewControllerImpl;
import uiController.webPromotionUserUiController;
import uiService.HotelMainUiService;
import uiService.LoginViewControllerService;
import uiService.MemberRegisterUiService;
import uiService.WebAdminUserUiService;
import uiService.customerMainViewControllerService;
import uiService.webPromotionUserUiService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import HotelWorkerView.HotelMainView;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;

public class LogView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JTextField textField;
	private LoginViewControllerService controller;
    public JLabel admin;
    public JLabel password;
    public JButton login;
    public JButton register;
    public JButton find;
    private JComboBox<String> comboBox_1;
    private UserType type;
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel label;
    private JLabel label2;
    private JLabel label3;
    private JPanel k;
    
    /**
	 * Create the panel.
	 */
	public LogView(LoginViewControllerService controller) {
		this.controller=controller;
		setLayout(new BorderLayout(0, 0));
		k=this;
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
		panel.add(panel1);
		
		panel4 = new JPanel();
		panel1.add(panel4);
		label=new JLabel();
		label.setPreferredSize(new Dimension(70,250));
		panel4.add(label);
		panel5 = new JPanel();
		panel1.add(panel5);
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(panel2);
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(panel3);
		init_optionpannel();
		init_loginpannel();
		this.validate();
	}
	public void init_optionpannel(){
	}
	public void init_loginpannel(){
		
		List<String> list=new ArrayList<String>();
		comboBox_1=new JComboBox<String>();
		comboBox_1.setPreferredSize(new Dimension(100,20));
		list.add("客户");
		list.add("酒店工作人员");
		list.add("网站营销人员");
		list.add("网站管理人员");
		for (String userType : list) {
			comboBox_1.addItem(userType);
		}
		comboBox_1.setToolTipText("");
		comboBox_1.setFont(UIManager.getFont("Button.font"));
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
			
				if(evt.getStateChange() == ItemEvent.SELECTED){
					
					String selected=(String) comboBox_1.getSelectedItem();
					
					//更换数据源
					controller.updateRegisterButton(selected);
				}
			}

		});
		panel5.add(comboBox_1);
		label3=new JLabel();
		label3.setPreferredSize(new Dimension(150,10));
		panel5.add(label3);
		
		admin = new JLabel("\u8D26\u53F7\u540D \uFF1A");
		admin.setForeground(new Color(0, 0, 0));
		admin.setFont(UIManager.getFont("Button.font"));
		panel2.add(admin);
		textField = new JTextField();
		textField.setFont(UIManager.getFont("Button.font"));
		textField.setColumns(15);
		panel2.add(textField);
		register = new JButton("\u6CE8\u518C\u8D26\u53F7");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.register();
			}
		});
		register.setFont(UIManager.getFont("Button.font"));
		panel2.add(register);
		password = new JLabel("\u5BC6\u7801 \uFF1A");
		panel3.add(password);
		passwordField = new JPasswordField(15);

		passwordField.setFont(UIManager.getFont("Button.font"));
		panel3.add(passwordField);
		login = new JButton("\u767B\u5F55");

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type= judgeUserType((String)comboBox_1.getSelectedItem());
				if(textField.getText().equals("")){					
					JOptionPane.showMessageDialog(k, "账号或密码输入有误！","", JOptionPane.ERROR_MESSAGE);
					return;
				}
				controller.login(textField.getText(),String.valueOf(passwordField.getPassword()),type);
			}
		});
		panel3.add(login);
		label2=new JLabel();
		label2.setPreferredSize(new Dimension(10000,250));
		panel3.add(label2);
	}
	
	private UserType judgeUserType(String str){
		if(str.equals("客户")){
			return UserType.Customer;
		}
		else if(str.equals("网站营销人员")){
			return UserType.WebPromotionWorker;
		}
		else if(str.equals("酒店工作人员")){
			return UserType.Hotelworker;
		}
		else{
			return UserType.WebManagementWorker;
		}
	}
	public void login(UserType type) throws RemoteException{
		if(type.equals(UserType.Customer)){
		   customerMainViewControllerService controller =  new customerMainViewControllerImpl(login.getText());
		   customerMainView view = new customerMainView(controller);
		   controller.setView(view);
		   ClientRunner.change(view);
		}
		else if(type.equals(UserType.WebPromotionWorker)){
			webPromotionUserUiService controller=new webPromotionUserUiController();
			WebPromotionUserView view=new WebPromotionUserView(controller);
			controller.setView(view);
			ClientRunner.change(view);
		}
		else if(type.equals(UserType.Hotelworker)){
			HotelMainUiService controller=new HotelMainUiController();
			HotelMainView view =new HotelMainView(controller);
			controller.setView(view);
			ClientRunner.change(view);
	    }
		else {
			WebAdminUserUiService controller=new WebAdminUserUiController();
			WebAdminUserView view=new WebAdminUserView(controller);
			controller.setView(view);
			ClientRunner.change(view);
	    }
	}
	public void register(){
		MemberRegisterUiService con=new MemberRegisterUiController();
		MemberRegisterView vie=new MemberRegisterView(con);
		con.setView(vie);
		ClientRunner.change(vie);
	}
	public void updateRegisterButton(String selected) {
		// TODO Auto-generated method stub
		if(selected!="客户"){
			register.setEnabled(false);
		}
		else{
			register.setEnabled(true);
		}
	}
}
