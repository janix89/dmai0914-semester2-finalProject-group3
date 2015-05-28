package controlLayer;
import java.util.ArrayList;

import modelLayer.Merchandise;
import modelLayer.Order;
import modelLayer.OrderLine;
import modelLayer.Staff;
import dbLayer.DBOrder;
import dbLayer.DBOrderLine;

public class OrderController {
	private MerchandiseController merchandiseController;
	private StaffController staffController;
	private DBMerchandise dBMerchandise;
	private TableController tableController;
	private ReservationController reservationController;
	private DBOrder dBOrder;
	private DBOrderLine dBOrderLine;
	private Merchandise mer;
	private Order order;
	
	public OrderController(){
		
	}
	
	public void makeOrder(String cprNo, boolean isActive){
		Staff waiter = staffController.findWaiterByCpr(cprNo);
		order = new Order();
		order.setActive(isActive);
	}
	
	public void findMerchandise(String name){
		mer = merchandiseController.findMerchandise(name);
		
	}
	
	public void addMerchandises(Merchandise mer, double quantity){
		OrderLine ol = new OrderLine();
		ol.setMerchandise(mer);
		ol.setQuantity(quantity);
		order.addOrderLine(ol);	
	}
	
	public void saveOrder(Order o){
		ArrayList<OrderLine> orderLines = o.getOrderLines();
		for(int x = 0; x < orderLines.size(); x++){
			OrderLine orderL = orderLines.get(x);
			dBOrderLine.insertOrderLine(orderL);
		}
		dBOrder.insertOrder(o);	
	}
	
	public Order findOrder(int tableNo){
		Order o = reservationController.
				tableController.findTableByNo(tableNo)
	}
}
