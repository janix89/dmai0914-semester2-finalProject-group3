package guiLayer;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import modelLayer.Order;

public class MakeOrderMenuTableModel extends AbstractTableModel {
	
	private ArrayList<Order> al;
	private String[] colNames = {"Name", "Type", "Type of course", "Quantity"};

	
	public MakeOrderMenuTableModel() {
		al = new ArrayList<Order>();
	
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return al.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Order order = al.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return "Change this";
		case 1:
			return "Change this";
		case 2:
			return "Change this";
		case 3:
			return "Change this";
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}
	/**
	public void setDataGGG(){
		Order o = new Order();
		for(int i = 0; i < 5; i++){
		al.add(o);
		}
	}
	*/
	
	public Class<?> getColumnClass(int col) {
		switch(col) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Integer.class;
		default:
			return null;
		}
	}

}
