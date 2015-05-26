package controlLayer;
import modelLayer.*;
import dbLayer.*;

public class TableController {
	DBTable dbTable;
	public Table findTableByNo(int tableNo){
		return dbTable.findTableByNo(tableNo);
	}
	
	public boolean checkIfExists(Table table){
		return table.getExists();
	}
	
	public boolean checkIfAvialable(Table table){
		return table.getIsAvailable();
	}
}
