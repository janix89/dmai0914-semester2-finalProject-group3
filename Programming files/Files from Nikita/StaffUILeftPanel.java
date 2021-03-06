import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class StaffUILeftPanel extends JPanel {
	private Label profession;
	private Label name;
	private Label bankAccount;
	private Label adress;
	private Label phone ;
	private Label quantity;
	private Label exists;
	private JComboBox typeOfStaff;
	private JComboBox exists2;
	private JTextField name1;
	private JTextField bankAccount1;
	private JTextField adress1;
	private JTextField phone1;
	private JTextField quantity1;
	private JButton create;
	private ListenerForEverything listenerForEverything;
	
	
	
	

	public StaffUILeftPanel(){
		
		setPreferredSize(new Dimension(420,0));
		
		Border innerBorder = BorderFactory.createTitledBorder("Create staff");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		profession = new Label("Profession : ");
		name = new Label("Name : ");
		bankAccount = new Label("Bank Account : ");
		adress = new Label("Adress : ");
		phone = new Label("Phone : ");
		quantity = new Label("Quantity : ");
		exists = new Label("Exists : ");
		String[] staffType = {"Waiter", "Cook", "Manager"};
		String[] existsOrNot = {"Yes", "No"};
		typeOfStaff = new JComboBox(staffType);
		exists2 = new JComboBox(existsOrNot);
		name1 = new JTextField(10);
		bankAccount1 =new JTextField(10);
		adress1 = new JTextField(10);
		phone1 = new JTextField(10);
		quantity1 = new JTextField(10);
	    create = new JButton("Create");
		
		// First row first column
		gc.weightx = 1; // Size of of the cell width
		gc.weighty = 1;  // Size of of the cell height
		
		gc.gridwidth = 1; // How many cells are merged 
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 0; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(profession, gc);
		
		// First row second column
		gc.weightx = 1; // Size of of the cell width
		gc.weighty = 1;  // Size of of the cell height
		
		gc.gridwidth = 1; // How many cells are merged 
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 0; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(typeOfStaff, gc);
		
		// Second row first column
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 1; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(name, gc);
		
		// Second row second column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 1; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(name1, gc);
		
		//thrid row first column
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 2; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(bankAccount, gc);
		
		//third row and second column
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 2; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(bankAccount1, gc);
		
		// fourth row and first column
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 3; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(adress, gc);
		
		// fourth row and second column
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 3; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(adress1, gc);
		
		//fifth row and first column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 4; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(phone, gc);
		
		//fifth row and second column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 4; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(phone1, gc);
		
		// sixth row and first column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 5; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(quantity, gc);
		
		//sixth row and second column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 5; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(quantity1, gc);
		
		// seventh row and first column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 6; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(exists, gc);
		
		// seventh row and second column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 6; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(exists2, gc);
		
		// eight row first column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 7; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(create, gc);
		
		
	}
	public void setListenerForEverything(ListenerForEverything listener){
		this.listenerForEverything = listener;
	}
	
	
}
