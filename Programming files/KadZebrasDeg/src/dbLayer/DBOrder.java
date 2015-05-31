package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Course;
import modelLayer.Order;
import modelLayer.Staff;
import exceptionsLayer.DatabaseException;

public class DBOrder implements IFDBOrder {
	private Connection con;

	public DBOrder() {
		con = DBConnect.getInstance().getDBcon();
	}

	@Override
	public int insertOrder(Order order) throws DatabaseException {
		int rc = -1;
		String query = "";
		query = "INSERT INTO SaleOrder(totalPrice, isPaid, isActive, sId) VALUES ('"
				+ order.getTotalPrice()
				+ "','"
				+ order.isPaid()
				+ "','"
				+ order.isActive()
				+ "','"
				+ order.getStaff().getStaffId()
				+ "')";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query, stmt.RETURN_GENERATED_KEYS);
			int id = new GeneratedKey().getGeneratedKey(stmt);
			order.setOrderId(id);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("SaleOrder not inserted");
			throw new DatabaseException("Something else is wrong in DBOrder");
		}
		return (rc);
	}

	@Override
	public ArrayList<Order> getAllOrders() {
		return miscWhere("");
	}

	@Override
	public Order findOrder(int id) throws DatabaseException {
		String wClause = " id = '" + id + "'";
		return singleWhere(wClause);
	}

	@Override
	public int updateOrder(int id, Order o) {
		// New: using a prepared statement (note, this prepared statement is not
		// reused, but it could be.)
		String q = "update saleorder set totalPrice=?, isPaid=?, isActive=?, sId=? where id="
				+ id;
		int res = 0;
		try (PreparedStatement s = DBConnect.getInstance().getDBcon()
				.prepareStatement(q)) {
			s.setDouble(1, o.getTotalPrice());
			s.setBoolean(2, o.isPaid());
			s.setBoolean(3, o.isActive());
			s.setInt(4, o.getStaff().getStaffId());
			res = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {

		}
		return res;
	}

	// private method
	private Order Order(String wClause) {
		ResultSet results;
		Order orderObj = new Order();

		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				orderObj = buildOrder(results);
				stmt.close();
			} else {
				orderObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return orderObj;
	}

	// course misc where
	private ArrayList<Order> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Order> list = new ArrayList<Order>();
		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Order orderObj = new Order();
				orderObj = buildOrder(results);
				list.add(orderObj);
			}// end while
			stmt.close();
		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Single where is used when we only select one thing
	private Order singleWhere(String wClause) {
		ResultSet results;
		Order orderObj = new Order();
		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				orderObj = buildOrder(results);
				stmt.close();
			} else {
				orderObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return orderObj;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT *  FROM SaleOrder";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an staff object
	private Order buildOrder(ResultSet results) {
		Order orderObj = new Order();
		Staff staff = new Staff();
		try {
			orderObj.setTotalPrice(results.getFloat("totalPrice"));
			orderObj.setPaid(results.getBoolean("isPaid"));
			orderObj.setActive(results.getBoolean("isActive"));
			staff = new DBStaff().findStaffById(results.getInt("sId"));
			orderObj.setStaff(staff);
		} catch (Exception e) {
			System.out.println("Error in building the order object");
		}
		return orderObj;
	}

}
