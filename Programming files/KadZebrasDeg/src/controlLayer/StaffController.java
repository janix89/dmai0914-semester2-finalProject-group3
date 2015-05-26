package controlLayer;
import modelLayer.*;
import dbLayer.*;

public class StaffController {
	DBStaff dbStaff;
	
	public Waiter findWaiterByCpr(String cprNo){
		return dbStaff.findWaiterByCpr(cprNo);
	}
}
