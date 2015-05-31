package guiLayer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import modelLayer.Table;
import controlLayer.TableController;
import exceptionsLayer.DatabaseException;

public class TableUILeftPanel extends JPanel {

	private JLabel tableNo;
	private JTextField tableNo1;
	private JLabel numberOfSeats;
	private JTextField numberOfSeats1;
	private JLabel tableNoNorth;
	private JComboBox tableNoNorth1;
	private JLabel tableNoEast;
	private JComboBox tableNoEast1;
	private JLabel tableNoSouth;
	private JComboBox tableNoSouth1;
	private JLabel tableNoWest;
	private JComboBox tableNoWest1;
	private JButton create;
	private ListenerForEverything listenerForEverything;
	private JButton backBtn;
	private TableController tableController;
	public TableUILeftPanel() {
		tableController = new TableController();
		setPreferredSize(new Dimension(420, 0));

		Border innerBorder = BorderFactory.createTitledBorder("Create table");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		String[] tables = new String[tableController.getAllTables().size()];
		for(int i=0;i<tableController.getAllTables().size();i++){
			tables[i]=""+tableController.getAllTables().get(i).getTableNo();
		}	
		tableNo = new JLabel("Table No.: ");
		tableNo1 = new JTextField(10);
		numberOfSeats = new JLabel("No. of  seats : ");
		numberOfSeats1 = new JTextField(10);
		tableNoNorth = new JLabel("Table No. on North : ");
		tableNoNorth1 = new JComboBox(tables);
		tableNoEast = new JLabel("Table No. on East : ");
		tableNoEast1 = new JComboBox(tables);
		tableNoSouth = new JLabel("Table No. on South : ");
		tableNoSouth1 = new JComboBox(tables);
		tableNoWest = new JLabel("Table No. on West : ");
		tableNoWest1 = new JComboBox(tables);
		create = new JButton("Create");
		backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(150, 25));
		create.setPreferredSize(new Dimension(150, 25));

		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AnyEvent anyEvent = new AnyEvent(this, "backBtn");
				if (listenerForEverything != null) {
					try {
						listenerForEverything.AnyEventOcurred(anyEvent);
					} catch (DatabaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AnyEvent anyEvent = new AnyEvent(this, "create");
				if (listenerForEverything != null) {
					try {
						listenerForEverything.AnyEventOcurred(anyEvent);
						if(!tableNo1.getText().equals("") && !numberOfSeats1.getText().equals("")){
							Table table = new Table();
							table.setAvailable(true);
							table.setTableNo(Integer.parseInt(tableNo1.getText().toString()));
							table.setNoOfSeats(Integer.parseInt(numberOfSeats1.getText()));
							table.setExists(true);
							if(tableNoEast1.getSelectedItem()!=null)
							if(!tableNoEast1.getSelectedItem().toString().equals(""))
							table.setTableOnTheEast(Integer.parseInt(tableNoEast1.getSelectedItem().toString()));
							if(tableNoNorth1.getSelectedItem()!=null)
							if(!tableNoNorth1.getSelectedItem().toString().equals(""))
							table.setTableOnTheNorth(Integer.parseInt(tableNoNorth1.getSelectedItem().toString()));
							if(tableNoWest1.getSelectedItem()!=null)
							if(!tableNoWest1.getSelectedItem().toString().equals(""))
							table.setTableOnTheWest(Integer.parseInt(tableNoWest1.getSelectedItem().toString()));
							if(tableNoSouth1.getSelectedItem()!=null)
							if(!tableNoSouth1.getSelectedItem().toString().equals(""))
							table.setTableOnTheSouth(Integer.parseInt(tableNoSouth1.getSelectedItem().toString()));
							
							
							tableController.insertTable(table);
						}
					} catch (DatabaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		// First row first column
		gc.weightx = 1; // Size of of the cell width
		gc.weighty = 0.2; // Size of of the cell height

		gc.gridwidth = 1; // How many cells are merged

		gc.gridx = 0; // Positiion on x
		gc.gridy = 0; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tableNo, gc);

		// First row second column
		gc.weightx = 1; // Size of of the cell width
		gc.weighty = 0.2; // Size of of the cell height

		gc.gridwidth = 1; // How many cells are merged

		gc.gridx = 1; // Positiion on x
		gc.gridy = 0; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 15, 0, 0);
		add(tableNo1, gc);

		// Second row first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 1; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(numberOfSeats, gc);

		// Second row second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 1; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 15, 0, 0);
		add(numberOfSeats1, gc);

		// thrid row first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 2; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tableNoNorth, gc);

		// third row and second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 2; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 15, 0, 0);
		add(tableNoNorth1, gc);

		// fourth row and first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 3; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tableNoEast, gc);

		// fourth row and second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 3; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 15, 0, 0);
		add(tableNoEast1, gc);

		// fifth row and first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 4; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tableNoSouth, gc);

		// fifth row and second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 4; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 15, 0, 0);
		add(tableNoSouth1, gc);

		// sixth row and first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 5; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tableNoWest, gc);

		// sixth row and second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 5; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 15, 0, 0);
		add(tableNoWest1, gc);

		// seventh row and first column
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 2;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 6; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(create, gc);

		// eight row first column
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 2;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 7; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(10, 0, 0, 0);
		add(backBtn, gc);

	}

	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}

}
