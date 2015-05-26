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
		
		table = new JTable(model);
			pane1.add(new JScrollPane(table)); 
	     //   pack(); 
			setDefaultCloseOperation(EXIT_ON_CLOSE); 
		table.setBounds(10, 24, 389, 467);
		
		//pane1.add(table);
		pane1.setVisible(true);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
