  package controlLayer;
import modelLayer.Table;
import dbLayer.DBTable;
import exceptionsLayer.DatabaseException;

public class TableController {
	DBTable dBTable;
	
	public Table findTableByNo(int tableNo) throws DatabaseException{
		return dBTable.findTableByTableNo(tableNo);
	}
	
	public boolean checkIfExists(Table table){
		return table.isExists();
	}
	
	public boolean checkIfAvialable(Table table){
		return table.isAvailable();
	}
}
