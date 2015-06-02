package controlLayer;

import java.util.ArrayList;

import modelLayer.Order;
import modelLayer.Reservation;
import modelLayer.Table;
import dbLayer.DBConnect;
import dbLayer.DBReservation;
import dbLayer.DBTable;
import exceptionsLayer.DatabaseException;

public class ReservationController {
	private DBReservation dBReservation;
	private TableController tableController;
	private ArrayList<Table> chosenTables;
	private DBTable dbTable;

	public ReservationController() {
		dbTable = new DBTable();
		dBReservation = new DBReservation();
		tableController = new TableController();
		chosenTables = new ArrayList<>();
	}

	public Reservation makeReservation(String customersName, String phoneNo,
			String reservationDate, String reservedTime, int numberOfGuests,
			Order order) throws DatabaseException {
		Reservation res = new Reservation();
		res.setCustomerName(customersName);
		res.setPhoneNo(phoneNo);
		res.setNumberOfGuests(numberOfGuests);
		res.setReservationDate(reservationDate);
		res.setReservedTime(reservedTime);
		res.setOrder(order);
		for (Table t : chosenTables) {// you add here the tables to the
										// reservation
			// can be improved to popup some error or smth
			// TO BE IMPROVED
			Table tbl = checkTables(t.getTableNo());
			if (tbl != null)
				res.addTable(tbl.getTableNo());
		}
		if (confirmReservation(res) != -1)
			return res;
		else
			System.out.println("Success!");
		// Just for test
		for (Table t : chosenTables) {
			System.out.println("Table in arrayList : " + t.getTableNo());
		}

		return res;
	}

	public Table checkTables(int tableNo) throws DatabaseException {
		Table tbl;
		tbl = tableController.findTableByNo(tableNo);
		if (tableController.checkIfExists(tbl))
			if (tableController.checkIfAvialable(tbl))
				return tbl;
		return null;
	}

	public int confirmReservation(Reservation res) throws DatabaseException {
		try {
			DBConnect.startTransaction();
			dBReservation.insertReservation(res);
			DBConnect.commitTransaction();
			return 1;
		} catch (Exception e) {
			DBConnect.rollbackTransaction();
		}
		return -1;
	}

	public Reservation findReservationByName(String name)
			throws DatabaseException {
		Reservation res = null;
		res = dBReservation.findReservationByName(name);
		return res;
	}

	// Returns all reservations with specified table no
	public Reservation findReservationByTableNo(int tableNo) {
		ArrayList<Reservation> aLR = dBReservation
				.findReservationByTableNo(tableNo);
		boolean isFound = false;
		Reservation res = null;
		int i = 0;
		if (aLR.isEmpty() == false) {
			while (!isFound) {
				res = aLR.get(i);
				if (res.getOrder().isActive() == true) {
					isFound = true;
				} else {
					i++;
				}
			}
		}
		return res;
	}

	public void addTableToReservation(Table table) {
		chosenTables.add(table);
		dbTable.updateTable(chosenTables.get(chosenTables.size() - 1)
				.getTableNo(), chosenTables.get(chosenTables.size() - 1));
	}

	/**
	 * Code I added public ArrayList
	 * <Table>
	 * getChosenTables(){ return chosenTables; } public void
	 * removeTableFromChosenTables(Table table){ chosenTables.remove(table); }
	 * public boolean checkIfTableHasBeenAlreadyAdded(int table){ for(Table t :
	 * chosenTables){ if(t.getTableNo() == table){ return true; } } return
	 * false; }
	 */
}
