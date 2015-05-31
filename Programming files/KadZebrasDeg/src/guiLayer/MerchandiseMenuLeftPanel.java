package guiLayer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class MerchandiseMenuLeftPanel extends JPanel {

	private JComboBox type;
	private JComboBox typeOfCourse;
	private JLabel type1;
	private JLabel typeOfCourse2;
	private JCheckBox isVegeterian;
	private JLabel name;
	private JTextField name1;
	private JLabel ingridients;
	private JScrollPane ingridients1;
	private JTextArea ingridients2;
	private JLabel alcoholConcentration;
	private JTextField alcoholConcentration1;
	private JLabel quantity;
	private JTextField quantity1;
	private JButton create;
	private ListenerForEverything listenerForEverything;
	
	
	public MerchandiseMenuLeftPanel() {
		
		Border innerBorder = BorderFactory.createTitledBorder("Create Merchandise");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		setPreferredSize(new Dimension(420,0));
		
		String[] typeOfMerchandise = {"Course", "Drink", "Miscellaneous"};
		type = new JComboBox(typeOfMerchandise);
		type1 = new JLabel("Type : ");
		String[] typeOfCourse1 = {"1st", "2nd", "Dessert"};
		typeOfCourse = new JComboBox(typeOfCourse1);
		typeOfCourse2 = new JLabel("Type of course : ");
		isVegeterian = new JCheckBox(new String("Vegetarian"));
		name = new JLabel("Name : ");
		name1 = new JTextField(10);
		ingridients = new JLabel("Ingridients : ");
		ingridients2 = new JTextArea(10,10);
		ingridients1 = new JScrollPane(ingridients2);
		alcoholConcentration = new JLabel("Alcohol Concentration : ");
		alcoholConcentration1 = new JTextField(10);
		quantity = new JLabel("Quantity : ");
		quantity1 = new JTextField(10);
		create = new JButton("Create");
		
		// First row first column
		gc.weightx = 0.1; // Size of of the cell width
		gc.weighty = 1;  // Size of of the cell height
		
		gc.gridwidth = 1; // How many cells are merged 
		
		gc.gridx = 0; // Positiion on x
		gc.gridy = 0; // Position on y
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(type1, gc);
		
		// First row second column
		gc.weightx = 1; // Size of of the cell width
		gc.weighty = 1;  // Size of of the cell height

		gc.gridwidth = 1; // How many cells are merged 

		gc.gridx = 1; // Positiion on x
		gc.gridy = 0; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 10, 0, 0);
		add(type, gc);
					
		// Second row first column
		gc.weightx = 0.1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 0; // Positiion on x
		gc.gridy = 1; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(typeOfCourse2, gc);

		// Second row second column 
		gc.weightx = 1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 1; // Positiion on x
		gc.gridy = 1; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 10, 0, 0);
		add(typeOfCourse, gc);

		

		//third row and second column
		gc.weightx = 1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 1; // Positiion on x
		gc.gridy = 2; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(isVegeterian, gc);

		// fourth row and first column
		gc.weightx = 0.1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 0; // Positiion on x
		gc.gridy = 3; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(name, gc);

		// fourth row and second column
		gc.weightx = 1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 1; // Positiion on x
		gc.gridy = 3; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 10, 0, 0);
		add(name1, gc);

		//fifth row and first column 
		gc.weightx = 0.1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 0; // Positiion on x
		gc.gridy = 4; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ingridients, gc);

		//fifth row and second column 
		gc.weightx = 1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 1; // Positiion on x
		gc.gridy = 4; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 10, 0, 0);
		add(ingridients1, gc);

		// sixth row and first column 
		gc.weightx = 0.1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 0; // Positiion on x
		gc.gridy = 5; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(alcoholConcentration, gc);

		//sixth row and second column 
		gc.weightx = 1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 1; // Positiion on x
		gc.gridy = 5; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 10, 0, 0);
		add(alcoholConcentration1, gc);

		// seventh row and first column 
		gc.weightx = 0.1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 0; // Positiion on x
		gc.gridy = 6; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(quantity, gc);

		// seventh row and second column 
		gc.weightx = 1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 1; // Positiion on x
		gc.gridy = 6; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 10, 0, 0);
		add(quantity1, gc);

		

		// eight row second column 
		gc.weightx = 1; 
		gc.weighty = 1;  

		gc.gridwidth = 1;  

		gc.gridx = 1; // Positiion on x
		gc.gridy = 7; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 120, 0, 0);
		add(create, gc);


	}
	
	public void setListenerForEverything(ListenerForEverything listener){
		this.listenerForEverything = listener;
	}
	
	
	
}
