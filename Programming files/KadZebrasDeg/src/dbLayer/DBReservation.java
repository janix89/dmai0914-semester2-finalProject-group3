package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Order;
import modelLayer.OrderLine;
import modelLayer.Reservation;
import modelLayer.Table;
import exceptionsLayer.DatabaseException;

public class DBReservation implements IFDBReservation {
	private Connection con;

	public DBReservation() {
		con = DBConnect.getInstance().getDBcon();
	}

	@Override
	public int insertReservation(Reservation reservation)
			throws DatabaseException {
		int rc = -1;
		int id = 0;
		String query = "";

		query = "INSERT INTO Reservation(customerName, phoneNo, reservationDate, numberOfGuests, registrationDate, reservationTime, oId) VALUES('"
				+ reservation.getCustomerName()
				+ "','"
				+ reservation.getPhoneNo()
				+ "','"
				+ reservation.getReservationDate()
				+ "','"
				+ reservation.getNumberOfGuests()
				+ "','"
				+ reservation.getReservationDate()
				+ "','"
				+ reservation.getReservedTime()
				+ "','"
				+ reservation.getOrder().getOrderId() + "')";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query, stmt.RETURN_GENERATED_KEYS);
			id = new GeneratedKey().getGeneratedKey(stmt);
			insertIntoReservedTable(reservation, id);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Reservation not inserted");
			throw new DatabaseException(
					"Something else is wrong in DBReservation");
		}
		return (rc);
	}

	private int insertIntoReservedTable(Reservation reservation, int id)
			throws DatabaseException {
		String query = "";
		int rc = -1;
		if(reservation.getTables()!=null)
		for (Integer t : reservation.getTables()) {
			query = "Insert INTO ReservedTable(rId, tId) VALUES ('" + id
					+ "','" + t+"') Update Ttable set isAvailable=0 where tableNo="+t;
		}
		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Reservation not inserted");
			throw new DatabaseException(
					"Something else is wrong in DBReservation");
		}
		return (rc);
	}

	@Override
	public ArrayList<Reservation> getAllReservations() {
		return miscWhere("");
	}

	@Override
	public Reservation findReservation(int id) throws DatabaseException {
		String wClause = " id = '" + id + "'";
		return singleWhere(wClause);
	}

	@Override
	public Reservation findReservationByName(String name)
			throws DatabaseException {
		String wClause = " customerName = '" + name + "'";
		return singleWhere(wClause);
	}

	public ArrayList<Reservation> findReservationByTableNo(int tableNo) {
		String wClause = " tableNo = " + tableNo;
		return miscWhere(wClause);
	}

	@Override
	public int updateReservation(String name, Reservation reservation)
			throws DatabaseException {
		// New: using a prepared statement (note, this prepared statement is not
		// reused, but it could be.)
		String q = "update reservation set customerName=?, phoneNo=?, reservationDate=?, numberOfGuests=?, registrationDate=?, reservationTime=?, oId=? where name="
				+ name;
		int res = 0;
		int id = 0;
		int rc = -1;
		String query = "delete from reservedTables where id=" + id;
		try (PreparedStatement s = DBConnect.getInstance().getDBcon()
				.prepareStatement(q)) {
			s.setString(1, reservation.getCustomerName());
			s.setString(2, reservation.getPhoneNo());
			s.setString(3, reservation.getReservationDate());
			s.setInt(4, reservation.getNumberOfGuests());
			s.setString(5, reservation.getReservationRegistrationDate());
			s.setString(6, reservation.getReservedTime());
			s.setInt(7, reservation.getOrder().getOrderId());
			res = s.executeUpdate();
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query, stmt.RETURN_GENERATED_KEYS);
			id = new GeneratedKey().getGeneratedKey(stmt);
			insertIntoReservedTable(reservation, id);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Reservation not inserted");
			throw new DatabaseException(
					"Something else is wrong in DBReservation");
		} catch (NullPointerException npe) {

		}

		return res;
	}

	// private method
	private Reservation getReservation(String wClause) {
		ResultSet results;
		Reservation reservationObj = new Reservation();

		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				reservationObj = buildReservation(results);
				stmt.close();
			} else {
				reservationObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return reservationObj;
	}

	// course misc where
	private ArrayList<Reservation> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Reservation> list = new ArrayList<Reservation>();
		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Reservation reservationObj = new Reservation();
				reservationObj = buildReservation(results);
				list.add(reservationObj);
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
	private Reservation singleWhere(String wClause) {
		ResultSet results;
		Reservation reservationObj = new Reservation();
		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				reservationObj = buildReservation(results);
				stmt.close();
			} else {
				reservationObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return reservationObj;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT *  FROM Reservation";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an employee object
	private Reservation buildReservation(ResultSet results) {
		Reservation reservationObj = new Reservation();
		Order tempOrder = null;
		DBOrder dbOrder = new DBOrder();
		try {
			reservationObj.setCustomerName(results.getString("customerName"));
			reservationObj.setPhoneNo(results.getString("phoneNo"));
			reservationObj.setReservationDate(results
					.getString("reservationDate"));
			reservationObj.setNumberOfGuests(results.getInt("numberOfGuests"));
			reservationObj.setReservationRegistrationDate(results
					.getString("registrationDate"));
			reservationObj
					.setReservedTime(results.getString("reservationTime"));
			tempOrder = dbOrder.findOrder(results.getInt("oId"));
			reservationObj.setOrder(tempOrder);			
			System.out.println("TempOrder: "+ tempOrder.getOrderId());
		} catch (Exception e) {
			System.out.println("Error in building the reservation object");
		}
		return reservationObj;
	}

}
