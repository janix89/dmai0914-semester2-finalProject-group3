package controlLayer;

import java.util.ArrayList;

import modelLayer.Table;
import dbLayer.DBConnect;
import dbLayer.DBTable;
import exceptionsLayer.DatabaseException;

public class TableController {
	DBTable dBTable = new DBTable();

	public Table findTableByNo(int tableNo) throws DatabaseException {
		return dBTable.findTableByTableNo(tableNo);
	}

	public boolean checkIfExists(Table table) {
		return table.isExists();
	}

	public boolean checkIfAvialable(Table table) {
		return table.isAvailable();
	}

	public ArrayList<Table> getAllTables() {
		return dBTable.getAllTables();
	}

	public void updateTable(int tableNo, Table table) {
		try {
			DBConnect.startTransaction();
			dBTable.updateTable(tableNo, table);
			DBConnect.commitTransaction();
		} catch (Exception e) {
			DBConnect.rollbackTransaction();
			e.printStackTrace();
		}
	}

	public void insertTable(Table table) {
		try {
			DBConnect.startTransaction();
			dBTable.insertTable(table);
			DBConnect.commitTransaction();
		} catch (DatabaseException e) {
			DBConnect.rollbackTransaction();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
