package guiLayer;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modelLayer.Table;
import exceptionsLayer.DatabaseException;


public class WaiterUIRightPanel extends JPanel {
	
	private GridBagLayout gl;
	private int a;
	private int c;
	private double number;
	private ScrollPane sp;
	private ListenerForEverything listenerForEverything;
	private int i;
	private ArrayList<Table> existingTables;
	private ArrayList<Table> allTables;
	
	public WaiterUIRightPanel(){
		existingTables = new ArrayList<>();
		allTables = new ArrayList<>();
		
		//Added by Janis
		//creates the hardcoded tables
		createSomeTables();
		
		number = 0;
		if(allTables.isEmpty() != true){
		for(Table t : allTables){
			if(t.isExists() == true){
				number ++;
				existingTables.add(t);
			}
		}
		}
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//Added by Janis
		//This makes the table buttons in panel to split equally between x and y axis
		double columnNo =  Math.sqrt(number);
		
		
		Border innerBorder = BorderFactory.createTitledBorder("Tables");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
	        for (i = 0; i < number; ++i) {
	            JButton b = new JButton(String.valueOf(existingTables.get(i).getTableNo()));
	         	gc.weightx = 1;
				gc.weighty = 1;

				gc.gridwidth = 1;

				gc.gridx = a;
				gc.gridy = c;

				gc.fill = GridBagConstraints.BOTH;
				gc.anchor = GridBagConstraints.CENTER;
				gc.insets = new Insets(0, 0, 0, 0);
				add(b, gc);
				
				//Added by Janis
				//This is the place where squere root takes action
				if(a < columnNo -1) {
					a++;
					//System.out.println(a);
				}
				else{
					a = 0;
					c ++;
					System.out.println(c);
				}
				if(existingTables.get(i).isAvailable() == true){
	            b.setBackground(Color.GREEN);
	            b.setForeground(Color.BLUE);
				}
				else {
					b.setBackground(Color.RED);
		            b.setForeground(Color.BLUE);
				}
	            
	            b.addActionListener(new java.awt.event.ActionListener() {
	                public void actionPerformed(java.awt.event.ActionEvent e) {
	                	if(b.getBackground().equals(Color.RED)){
	                		AnyEvent anyEvent = new AnyEvent(this,  b.getLabel());
		    				if (listenerForEverything != null) {
		    					try {
		    						anyEvent.setName("RedTable");
									listenerForEverything.AnyEventOcurred(anyEvent);
								} catch (DatabaseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		    				}
	                	}
	                	if(b.getBackground().equals(Color.GREEN) == true || b.getBackground().equals(Color.BLUE)){
	                	AnyEvent anyEvent = new AnyEvent(this,  b.getLabel());
	    				if (listenerForEverything != null) {
	    					try {
	    						anyEvent.setName("GreenButton");
								listenerForEverything.AnyEventOcurred(anyEvent);
								if(b.getBackground().equals(Color.BLUE) != true){
								b.setBackground(Color.BLUE);
								b.setForeground(Color.WHITE);
								}
								else {
									b.setBackground(Color.GREEN);
						            b.setForeground(Color.BLUE);
								}
								
							} catch (DatabaseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	    				}
	               
	                	}
	                }
	            });
	            
	        }
	}
	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}
	
	//Added by Janis
	// This for adding some hardcoded tables
	
	/**/
	public void createSomeTables(){
		Table t1 = new Table();
		t1.setExists(true);
		t1.setTableNo(1);
		t1.setAvailable(true);
		Table t2 = new Table();
		t2.setExists(false);
		t2.setTableNo(2);
		t2.setAvailable(true);
		Table t3 = new Table();
		t3.setExists(true);
		t3.setTableNo(3);
		t3.setAvailable(false);
		Table t4 = new Table();
		t4.setExists(true);
		t4.setTableNo(4);
		t4.setAvailable(false);
		Table t5 = new Table();
		t5.setExists(true);
		t5.setTableNo(5);
		t5.setAvailable(true);
		//allTables = new ArrayList<>();
		allTables.add(t1);
		allTables.add(t2);
		allTables.add(t3);
		allTables.add(t4);
		allTables.add(t5);
	}
	
	public void setTheArrayListOfAllTables(ArrayList<Table> allTables){
		this.allTables = allTables;
	}
	

}
