import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SkeletonForPanels extends JPanel {

	private JLabel label;
	private JTextField field;
	private JButton button;
	private JTable table;
	private JButton button1;
	

	public SkeletonForPanels() {

		label = new JLabel("Change this!");
		field = new JTextField(10); // Change the number according how big you
									// want it
		button = new JButton("Change this!");
		table = new JTable(); // Later you will need to extend JTable and make
		//You can ignore Overrides at the moment 
		table.setModel(new TableModel() {
			
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 10;
			}
			
			@Override
			public String getColumnName(int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub
				
			}
		});						// it custom with your own model
		button1 = new JButton("Change this!");

		// You can set the size of the buttons
		button.setPreferredSize(new Dimension(150, 25));
		button1.setPreferredSize(new Dimension(150, 25));

		// You can set the width of panel if you need
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		// Sets the line around the other contents in JPanel

		Border innerBorder = BorderFactory.createTitledBorder("Change this");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		// Sets the layout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// First row firts column

		// sets how big is the width of cell according to the rest cells
		gc.weightx = 1;

		// sets how big is the height of the cell according to the rest cells
		gc.weighty = 1;

		// If you have more cells in the first row like we will have and the
		// next
		// row has only one cell occupied you can set how many cells will
		// occupy current cell
		gc.gridwidth = 1;

		// position of your component on x axis
		gc.gridx = 0;
		// position of your component on y axis
		gc.gridy = 0;

		// position of the content in the cell
		gc.fill = GridBagConstraints.CENTER;
		// position of the cell in the panel
		gc.anchor = GridBagConstraints.CENTER;
		// if you need to move your cell further from the edges or the rest of
		// the cells
		// you can ad empty spaces measured in pixels(i think) (top, left,
		// bottom, right)
		gc.insets = new Insets(0, 0, 0, 0);
		// adds the component to panel. In this case it's "label"
		add(label, gc);

		// First row second column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(field, gc);

		// First row third column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 2;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(button, gc);
		
		//Second row center
		
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 3;

		gc.gridx = 0;
		gc.gridy = 1;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(table, gc);
		
		//Third row first column
		
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 3;

		gc.gridx = 0;
		gc.gridy = 2;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(button1, gc);

	}

}
