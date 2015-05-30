import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;


class CheffMenu extends JPanel
{
	private JTable table;
	private JScrollPane scrollPane,scrollPane1;
	private String[] columnNames= new String[3];
	private String[][] dataValues=new String[3][3] ;
	JButton button = new JButton("Show");
	JButton backButton = new JButton("Back");
	public CheffMenu()
	{

		setSize(500,700);



		columnNames=new String[] {"Table" , "Minutes elapsed" , "Time(minutes)", "Status", ""};
		dataValues = new String[][]              {
				{"--","---","----","----"},
				{"---","----","---","---"},
				{"----","-----","----","---"}
		};
		TableModel model=new myTableModel("owntable");
		table =new JTable( );
		table.setModel(model);
		table.getColumn("").setCellRenderer(new ButtonRenderer());
		table.getColumn("").setCellEditor(new ButtonEditor(new JCheckBox()));

		scrollPane=new JScrollPane(table);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		
		Border innerBorder = BorderFactory.createTitledBorder("CheffMenu");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10, 0, 0, 0);
		add(scrollPane, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(10, 20, 0, 0);
		add(backButton, gc);
		
		

		button.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						JOptionPane.showMessageDialog(null,"Button Clicked in JTable Cell");
					}
				}
				);
	}
	
	
	
	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "Show" : value.toString());
			return this;
		}
	}
	
	
	
	class ButtonEditor extends DefaultCellEditor {
		private String label;
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}
		
		
		public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column) {
			label = (value == null) ? "Show" : value.toString();
			button.setText(label);
			return button;
		}
		
		
		public Object getCellEditorValue() {
			return new String(label);
		}
	}
	
	
	
	public class myTableModel extends DefaultTableModel
	{
		String dat;
		JButton button=new JButton("Show");
		myTableModel(String tname){
			super(dataValues,columnNames);
			dat=tname;
		}

		public boolean isCellEditable(int row,int cols)
		{
			if( dat=="owntable"){
				if(cols==0){return false;}
			}
			return true;
		}
	}         

}