package guiLayer;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import modelLayer.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import controlLayer.*;

import javax.swing.JCheckBox;
public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField passwordField;
	private StaffController sc;
	private Staff logInStuff=null;
	public LogIn() {
		JCheckBox cRemember = new JCheckBox("Remember username");
		JLabel lblIncorect = new JLabel("");
		sc=new StaffController();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(87, 78, 100, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(87, 103, 100, 14);
		contentPane.add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(157, 75, 100, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 103, 100, 20);
		contentPane.add(passwordField);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(81, 62, 233, 71);
		contentPane.add(panel);
		
		//read the username
		String b;
		 try {
			 File file = new File("username.txt");
			 if(file.exists()){
			FileReader f=new FileReader("username.txt");
			BufferedReader br = new BufferedReader(f);
			b=br.readLine();
			if(b!=null)
			if(!b.equals("")){
					tfUsername.setText(b);
					cRemember.setSelected(true);
					}
			 }
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//remember username
				PrintWriter writer;
				try {
					if(!tfUsername.getText().trim().equals("") && cRemember.isSelected()){
					writer = new PrintWriter("username.txt", "UTF-8");
					writer.print(tfUsername.getText());
					writer.close();
					}
					if(!cRemember.isSelected()){
					writer = new PrintWriter("username.txt", "UTF-8");
					cRemember.setSelected(false);
					writer.print("");
					writer.close();
					}
					writer = new PrintWriter("usernameToRemember.ini", "UTF-8");
					writer.print(tfUsername.getText());
					writer.close();
		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
				for(Staff s : sc.getAllStaff()){
					lblIncorect.setText(s.getUsername()+"/"+s.getPassword());
				if(s.getUsername().equals(tfUsername.getText())){
					if(passwordField.getText().equals(s.getPassword()))
					{
						
						//Replace with menu
						SideBar or=new SideBar();
						or.setVisible(true);
						//or.setLocationRelativeTo(null);
						setVisible(false);
						logInStuff=s;
						lblIncorect.setText("Logged in.");
					}
				}				
				}
				if(logInStuff==null) lblIncorect.setText("Incorect username and/or password");
				}
			
		});
		btnLogIn.setBounds(156, 178, 89, 23);
		contentPane.add(btnLogIn);
		
		JButton btnCantLogIn = new JButton("Can't log in ? Inform management.");
		btnCantLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Send an e-mail to: management@mycompany.com");
			}
		});
		btnCantLogIn.setBounds(201, 238, 233, 23);
		contentPane.add(btnCantLogIn);
		
		JLabel lblNewLabel = new JLabel("Waiter Board");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(157, 11, 138, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kad  Zebras Deg");
		lblNewLabel_1.setBounds(143, 36, 138, 14);
		contentPane.add(lblNewLabel_1);
		
		
		lblIncorect.setBounds(87, 134, 226, 14);
		contentPane.add(lblIncorect);
		
		
		cRemember.setBounds(87, 155, 182, 23);
		contentPane.add(cRemember);
	}
}
