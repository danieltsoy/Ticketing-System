
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//import simplemysql.SimpleMySQL;
//import simplemysql.SimpleMySQLResult;
//import sqlData.FetchData;
//import sqlData.WriteConfiguration;

public class TicketSystem extends JFrame {

	private JPanel contentPane;
	JPanel panel;
	private JTextField problemInfo;
	private JTextField emailAdd;
	static JTextField userNameInput = new JTextField();
	static JTextField passwordInput = new JTextField();
	static JTextField dataBaseInput = new JTextField();
	static JTextField hostNameInput = new JTextField();
	static JTextField ipAddressInput = new JTextField();
	//WriteConfiguration configCreate = new WriteConfiguration("C:\\ProgramData\\Testing\\test.txt");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketSystem frame = new TicketSystem();
					
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnknownHostException 
	 */
	public TicketSystem() throws UnknownHostException {
		JOptionPane test = new JOptionPane();
		JTextField userNameInput = new JTextField();
		JTextField passwordInput = new JTextField();
		JTextField dataBaseInput = new JTextField();
		JTextField hostNameInput = new JTextField();
		JTextField ipAddressInput = new JTextField();
		/*
		try {
			configCreate.writeToConfiguration(userNameInput.getText());
			System.out.println(userNameInput.getText());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		*/
		Object[] fields = {
			"Enter username:", userNameInput,
			"Enter password:", passwordInput,
			"Enter database name:", dataBaseInput,
			"Enter host name:", hostNameInput,
			"Enter IP address:", ipAddressInput,
		};
		test.showConfirmDialog(null,fields,"Information",test.OK_CANCEL_OPTION);

		String userName = System.getProperty("user.name");
		final String osName = System.getProperty("os.name");
		final String ipAddress = InetAddress.getLocalHost().getHostAddress();

	//	final SimpleMySQL mysql;		
	//	mysql = new SimpleMySQL();
	//	mysql.connect("192.168.0.136", "admin", "123456", "ticket_system");		//connects with SQL database

	//	SimpleMySQLResult mainCat;

		setTitle("Ticketing System - Prime Access");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSelectIssue = new JLabel("Select Issue:");
		lblSelectIssue.setBounds(10, 11, 77, 14);
		panel.add(lblSelectIssue);


		JComboBox mainCatBox = new JComboBox();
		mainCatBox.addItem("Please select an option");
	//	mainCat = mysql.Query ("SELECT * FROM categories WHERE cat_sub = 'no'");
		ComboItem cbItem;
	//	while (mainCat.next()){
	//		cbItem = new ComboItem(mainCat.getString("cat_name"),mainCat.getString("cat_id"));
	//		mainCatBox.addItem(cbItem);
	//	}
		mainCatBox.setBounds(124, 8, 165, 20);
		panel.add(mainCatBox);

		final JLabel lblSelectSubissue = new JLabel("Select Sub-Issue:");
		lblSelectSubissue.setBounds(10, 42, 100, 14);
		panel.add(lblSelectSubissue);

		final JComboBox subCatBox = new JComboBox();
		subCatBox.setBounds(124, 39, 165, 20);
		subCatBox.setEnabled(false);
		panel.add(subCatBox);

		mainCatBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox mainCatBox = (JComboBox)e.getSource();
				if(mainCatBox.getSelectedItem() == "Please select an option"){
					subCatBox.removeAllItems();
					subCatBox.setEnabled(false);
				}
				else{
					ComboItem selected = (ComboItem)mainCatBox.getSelectedItem();
					System.out.println(selected.getValue());
	//				final SimpleMySQLResult allCat = mysql.Query ("SELECT * FROM categories");
					subCatBox.removeAllItems();
					subCatBox.addItem("Please select an option");
	//				while (allCat.next()){
	//					if(allCat.getString("cat_level").equals(selected.getValue())){
	//						ComboItem cbItemSub = new ComboItem(allCat.getString("cat_name"),allCat.getString("cat_level"));
	//						subCatBox.addItem(cbItemSub);
							subCatBox.revalidate();
	//					} 
	//				}
					if(subCatBox.getItemCount() != 0)
						subCatBox.setEnabled(true);
					else
						subCatBox.setEnabled(false);
					panel.revalidate();
				}
			}
		});

		JLabel lblProblemSummary = new JLabel("Problem:");
		lblProblemSummary.setBounds(10, 73, 100, 14);
		panel.add(lblProblemSummary);

		problemInfo = new JTextField();
		problemInfo.setBounds(124, 70, 251, 20);
		problemInfo.setText("Disabled");
		problemInfo.setEnabled(false);
		panel.add(problemInfo);
		problemInfo.setColumns(10);


		//System.out.println(Inet4Address.getLocalHost().getHostAddress());

		final JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 228, 89, 23);
		btnSubmit.setEnabled(true);
		panel.add(btnSubmit);



		final JTextArea problemAddInfo = new JTextArea();
		problemAddInfo.setText("Please enter any additional information. (Error Messages)");
		problemAddInfo.setBounds(10, 111, 365, 75);
		problemAddInfo.setEnabled(false);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		problemAddInfo.setBorder(border);
		panel.add(problemAddInfo);


		JLabel lblFixWithin = new JLabel("Fix Within:");
		lblFixWithin.setBounds(10, 203, 100, 14);
		panel.add(lblFixWithin);

		String[] times = new String[]{"Select","This Week","24 Hours","2 Hours","30 Minutes", "Immediately"};
		final JComboBox fixTime = new JComboBox(times);
		fixTime.setEnabled(false);
		fixTime.setBounds(124, 197, 100, 20);
		panel.add(fixTime);

		JCheckBox chckbxEmailConfirmation = new JCheckBox("Email Confirmation");
		chckbxEmailConfirmation.setBounds(105, 228, 140, 23);
		panel.add(chckbxEmailConfirmation);

		emailAdd = new JTextField();
		emailAdd.setBounds(249, 229, 175, 20);
		emailAdd.setEnabled(false);
		emailAdd.setText("Disabled");
		panel.add(emailAdd);
		emailAdd.setColumns(10);

		chckbxEmailConfirmation.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					emailAdd.setEnabled(true);
					emailAdd.setText("");
				}
				else if(e.getStateChange() == ItemEvent.DESELECTED){
					emailAdd.setEnabled(false);
					emailAdd.setText("Disabled");
				}

				validate();
				repaint();
			}
		});

		problemAddInfo.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e) {
				problemAddInfo.setText("");
			}
			public void focusLost(FocusEvent arg0) {
				if(problemAddInfo.getText().equals(""))
					problemAddInfo.setText("Please enter any additional information. (Error Messages)");
			}
		});

		subCatBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				if(e.getItem() != "Please select an option"){
					problemInfo.setText("");
					problemInfo.setEnabled(true);

				}
				else{
					problemInfo.setEnabled(false);
					problemInfo.setText("");
				}
			}
		});

		fixTime.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e){
				if(e.getItem() != "Select")
					btnSubmit.setEnabled(true);
				else
					btnSubmit.setEnabled(true);
			}
		});

		problemInfo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { //watch for key strokes
				if(problemInfo.getText().length() <= 4){
					fixTime.setEnabled(false);
					problemAddInfo.setEnabled(false);
				}
				else
				{
					fixTime.setEnabled(true);
					problemAddInfo.setEnabled(true);
				}
			}
		});
		
		btnSubmit.addActionListener( new ActionListener()
		{
		    @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
		    {
		    	//System.out.println(osName + " " + ipAddress);
	//	    	mysql.Query("INSERT INTO testing (OS,IP) VALUES(\"" + osName + "\",\"" + ipAddress + "\")");
		    }
		});
		/*
		ActionListener updateClockAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Assumes clock is a JLabel
				timeDate.setText(new Date().toString()); 
			}
		};
		Timer t = new Timer(1000, updateClockAction);
		t.start();
		 */
	}
}
