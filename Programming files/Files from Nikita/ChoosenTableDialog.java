import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ChoosenTableDialog extends JDialog {

	private JLabel tableNo;
	private JLabel order;
	private JLabel numbersTable;
	private JLabel total;
	private JButton edit;
	private JButton addMerchandise;
	private JButton cancelOrder;
	private JButton back;
	private JTable table;
	private JScrollPane scroll;
	private int[] numbersOfTables;

	public ChoosenTableDialog(JFrame parent, String title) {
		super(parent, title);

		tableNo = new JLabel("Table No :");
		order = new JLabel("Order");
		numbersTable = new JLabel();
		table = new JTable(new ChosenTableModel());
		scroll = new JScrollPane(table);
		edit = new JButton("Edit");
		addMerchandise = new JButton("Add Merchandise");
		cancelOrder = new JButton ("Cancel Order");
		back = new JButton("Back");
		total  = new JLabel("Total");
		
		
		
		
		
		setSize(600, 600);
		setResizable(false);
		setAlwaysOnTop(true);
		setModal(true);
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//first row first column
		gc.weightx = 1; // Size of of the cell width
		gc.weighty = 1;  // Size of of the cell height
		
		gc.gridwidth = 1; // How many cells are merged 
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 0; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 20, 0, 0);
		add(tableNo, gc);
		
		// Second row first column
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 1; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 20, 0, 0);
		add(order, gc);
				
		//Third row 		
		gc.weightx = 1; 
		gc.weighty = 1;  

		gc.gridwidth = 2;  

		gc.gridx = 0; // Positiion on x
		gc.gridy = 2; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(20, 0, 0, 0);
		add(scroll, gc);	
		
		// fourth row first column 
		
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 3; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(back, gc);

		//fourth row second column 
		
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 3; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(total, gc);
		
//		fifth row first column 
		
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 4; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(addMerchandise, gc);
		
//		sixth row frist column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 5; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(cancelOrder, gc);
		
//		sixth row second column 
		gc.weightx = 1; 
		gc.weighty = 1;  
		
		gc.gridwidth = 1;  
		
		gc.gridx = 1; // Positiion on x
		gc.gridy = 5; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(back, gc);

	}



}
