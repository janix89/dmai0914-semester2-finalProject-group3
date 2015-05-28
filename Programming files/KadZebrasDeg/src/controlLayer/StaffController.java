package controlLayer;
import modelLayer.Staff;
import dbLayer.DBStaff;
import exceptionsLayer.DatabaseException;

public class StaffController {
	DBStaff dbStaff;
	
	public Staff findWaiterByCpr(String cprNo) throws DatabaseException{
		return dbStaff.findStaff(cprNo);
	}
}
