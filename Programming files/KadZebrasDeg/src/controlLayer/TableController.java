  package controlLayer;
import java.util.ArrayList;

import modelLayer.Table;
import dbLayer.DBTable;
import exceptionsLayer.DatabaseException;

public class TableController {
	DBTable dBTable=new DBTable();
	
	public Table findTableByNo(int tableNo) throws DatabaseException{
		return dBTable.findTableByTableNo(tableNo);
	}
	
	public boolean checkIfExists(Table table){
		return table.isExists();
	}
	
	public boolean checkIfAvialable(Table table){
		return table.isAvailable();
	}
	public ArrayList<Table> getAllTables(){
		return dBTable.getAllTables();
	}
	public void updateTable(int tableNo, Table table){
		dBTable.updateTable(tableNo, table);
	}
	
	public void insertTable(Table table){
		try {
			dBTable.insertTable(table);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

