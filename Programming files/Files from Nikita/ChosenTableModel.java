import javax.swing.table.AbstractTableModel;


public class ChosenTableModel extends AbstractTableModel {

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		switch(columnIndex){
		
		case(0):
			return String.class;
		case(1):
			return String.class;
		case(2):
			return Integer.class;
		case(3):
			return String.class;
		
				default: return Integer.class;	
		}
		
	}
	
	
	@Override
	public String getColumnName(int column) {
		switch(column){
		
		case(0):
			return "Type";
		case(1):
			return "Name";
		case(2):
			return "Qty";
		case(3) :
			return "Price";
		default :
			return "Time(minutes)";
		}
	
	}


}
