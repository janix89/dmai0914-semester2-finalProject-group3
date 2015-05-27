package modelLayer;

import java.util.ArrayList;

public class Reservation {
private ArrayList<Integer> tables;
private String customerName;
private String phoneNo;
private String reservationDate;
private int numberOfGuests;
private String reservationRegistrationDate;
private String reservationTime;
private Order preorder;
private int orderId;
//constructor
public Reservation(){

}


//sets and gets
public ArrayList<Integer> getTables() {return tables;}
public void setTables(ArrayList<Integer> tables) {this.tables = tables;}
public String getPhoneNo() {return phoneNo;}
public void setPhoneNo(String phoneNo) {this.phoneNo = phoneNo;}
public String getReservationDate() {return reservationDate;}
public void setReservationDate(String reservationDate) {this.reservationDate = reservationDate;}
public Order getPreorder() {return preorder;}
public void setPreorder(Order preorder) {this.preorder = preorder;}
public String getCustomerName() {return customerName;}
public void setCustomerName(String customerName) {this.customerName = customerName;}
public int getNumberOfGuests() {return numberOfGuests;}
public void setNumberOfGuests(int numberOfGuests) {this.numberOfGuests = numberOfGuests;}
public String getReservationRegistrationDate() {return reservationRegistrationDate;}
public void setReservationRegistrationDate(String reservationRegistrationDate) {this.reservationRegistrationDate = reservationRegistrationDate;}
public String getReservedTime() {return reservationTime;}
public void setReservedTime(String reservedTime) {this.reservationTime = reservedTime;}
}
