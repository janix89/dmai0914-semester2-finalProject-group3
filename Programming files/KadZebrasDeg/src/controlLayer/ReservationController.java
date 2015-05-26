package controlLayer;
import modelLayer.*;
import dbLayer.*;


public class ReservationController {
	private DBReservation dbReservation;
	private TableController tableCTR;
	public Reservation createReservation(String customerName, String phoneNo, int amountOfPeople, ArrayList<Integer> tables){
		Reservation res = new Reservation(customerName, phoneNo, amountOfPeople);
		for(int i : tables){// you add here the tables to the reservation
			// can be improved to popup some error or smth
			// TO BE IMPROVED
			Table tbl = checkTables(i);
			if(tbl != null)
				res.addTable(tbl);
		}
		if(confirmReservation(res))
			return res;
		else return null;
	}
	
	public Table checkTables(int tableNo){
		Table tbl;
		tbl = tableCTR.findTableByNo(tableNo);
		if(tableCTR.checkIfExists(tbl))
			if(tableCTR.checkIfAvialable(tbl))
				return tbl;
		return null;
	}
	
	private boolean confirmReservation(Reservation res){
		return dbReservation.insertReservation(res);
	}
}
