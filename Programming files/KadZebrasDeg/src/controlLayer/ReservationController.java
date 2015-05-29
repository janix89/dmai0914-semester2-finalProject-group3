package controlLayer;

import java.util.ArrayList;

import modelLayer.Reservation;
import modelLayer.Table;
import dbLayer.DBReservation;
import exceptionsLayer.DatabaseException;

public class ReservationController {
	private DBReservation dBReservation;
	private TableController tableController;

	public ReservationController() {

	}

	public Reservation makeReservation(String customersName, String phoneNo,
			String reservationDate, String reservedTime, int numberOfGuests, ArrayList<Table> tables)
			throws DatabaseException {
		Reservation res = new Reservation();
		res.setCustomerName(customersName);
		res.setPhoneNo(phoneNo);
		res.setNumberOfGuests(numberOfGuests);
		res.setReservationDate(reservationDate);
		res.setReservedTime(reservedTime);
		for (Table t : tables) {// you add here the tables to the reservation
			// can be improved to popup some error or smth
			// TO BE IMPROVED
			Table tbl = checkTables(t.getTableNo());
			if (tbl != null)
				res.addTable(tbl.getTableNo());
		}
		if (confirmReservation(res) != -1)
			return res;
		else
			return null;
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

		return dBReservation.insertReservation(res);
	}

	public Reservation findReservationByName(String name)
			throws DatabaseException {
		Reservation res = null;
		// Reservation res = dbReservation.findReservationByName(name);
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
}
