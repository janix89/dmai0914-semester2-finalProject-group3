package guiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;



public class ChefMenu extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pane1;
	private JTable table;
	private JScrollPane tablePane;
	private String[] columNames = {"Table","Minutes Elapsed","Time(minutes)","Status",""};
	private Object[][] data = {{"1","4","30","in process","show"},{"2","11","45","Started","show"}};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChefMenu frame = new ChefMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChefMenu() {
		
		try {
		
		pane1 = new JPanel();
		setBounds(400, 50, 450, 600);
		pane1.setBorder(BorderFactory.createTitledBorder(null, "chef menu", 0, TitledBorder.CENTER, Font.getFont(getName()), Color.BLACK));
		setContentPane(pane1);
		pane1.setLayout(null);
		
		/*pane2 = new JPanel();
		setBounds(400, 100, 450, 400);
		pane2.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(pane1);
		pane2.setLayout(null);
		pane1.add(pane2); */
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 527, 89, 23);
		pane1.add(btnBack);
		
		DefaultTableModel model = new DefaultTableModel() {

		    private static final long serialVersionUID = 1L;
		    String[] table = {"1", "2"};

		    @Override
		    public int getColumnCount() {
		         return table.length;
		    }

		    @Override
		    public boolean isCellEditable(int row, int col) {
		         return false;
		    }


		    @Override
		    public String getColumnName(int index) {
		        return table[index];
		    }
		};
		
		table = new JTable(data,columNames);
		table.setFillsViewportHeight(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
	table.setBounds(10, 44, 389, 467);
	TableColumn column = null;
	for (int i = 0; i < 5; i++) {
	    column = table.getColumnModel().getColumn(i);
	    if (i == 1) {
	        column.setPreferredWidth(100); //third column is bigger
	    } 
	    else if (i == 2) {
	        column.setPreferredWidth(75); //third column is bigger
	    }
	    else {
	        column.setPreferredWidth(50);
	    }
	}
		tablePane = new JScrollPane(table);
		tablePane.setBounds(9, 45, 400, 468);
		pane1.add(tablePane);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
