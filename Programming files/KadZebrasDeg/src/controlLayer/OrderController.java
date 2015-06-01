package controlLayer;

import java.util.ArrayList;

import modelLayer.Merchandise;
import modelLayer.Order;
import modelLayer.OrderLine;
import modelLayer.Reservation;
import modelLayer.Staff;
import dbLayer.DBOrder;
import dbLayer.DBOrderLine;
import exceptionsLayer.DatabaseException;

public class OrderController {
	private MerchandiseController merchandiseController;
	private StaffController staffController;
	private TableController tableController;
	private ReservationController reservationController;
	private DBOrderLine dBOrderLine;
	private Merchandise mer;
	private Order order;
	private DBOrder dbOrder;
	
	
	public OrderController() {
			dbOrder = new DBOrder();
			dBOrderLine = new DBOrderLine();
			merchandiseController=new MerchandiseController();
			staffController = new StaffController();
			tableController =new TableController();
			reservationController = new ReservationController();
	}

	public void makeOrder(String cprNo, boolean isActive) throws DatabaseException {
		Staff waiter = staffController.findWaiterByCpr(cprNo);
		order.setActive(isActive);
	}
	public ArrayList<Order> getAllOrders(){
		return dbOrder.getAllOrders();
	}
	public void insertOrder(Order order){
		try {
			dbOrder.insertOrder(order);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void findMerchandise(String name) {
		mer = merchandiseController.findMerchandise(name);
	}

	public void addMerchandises(double quantity) {
		OrderLine ol = new OrderLine();
		ol.setMerchandise(mer);
		ol.setQuantity(quantity);
		ol.setOrderId(order.getOrderId());
		order.addOrderLine(ol);
	}

	public void saveOrder() throws DatabaseException {
		ArrayList<OrderLine> orderLines = order.getOrderLines();
		for (int x = 0; x < orderLines.size(); x++) {
			OrderLine orderL = orderLines.get(x);
			dBOrderLine.insertOrderLine(orderL);
		}
		System.out.println("Order Stuff: "+order.getId());
		dbOrder.insertOrder(order);
	}

	public Order findOrder(int tableNo) {
		Reservation res = reservationController
				.findReservationByTableNo(tableNo);
		return res.getOrder();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order findOrderById(int orderId) {		
		try {
			return dbOrder.findOrder(orderId);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

}
