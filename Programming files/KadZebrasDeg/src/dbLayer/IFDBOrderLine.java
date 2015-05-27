package dbLayer;

import java.util.ArrayList;

import modelLayer.OrderLine;
import exceptionsLayer.DatabaseException;

public interface IFDBOrderLine {
//create one
	public int insertOrderLine(OrderLine orderLine, int mType) throws DatabaseException;
//read all
	public ArrayList<OrderLine> getAllOrderLines();
//read one
	public OrderLine findOrderLine(int id) throws DatabaseException;
//update one
	public int updateOrderLine(int id, OrderLine ol, int mType) throws DatabaseException;
}
