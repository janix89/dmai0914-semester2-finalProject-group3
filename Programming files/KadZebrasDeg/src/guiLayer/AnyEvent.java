package guiLayer;
import java.util.EventObject;


public class AnyEvent extends EventObject {
	private String buttonTrigered;
	
	//Added by Janis
	//Some more fields for reservation
	private int numberOfSeats;
	private String name;
	private String phoneNo;
	private String monthsYear;
	private String days;
	private String time;
	private int tableNo;
	
	public AnyEvent(Object source){
		super(source);
	}
	
	public AnyEvent(Object source, String buttonTrigered){
		super(source);
		this.buttonTrigered = buttonTrigered;
	}

	public String getButtonTrigered() {
		return buttonTrigered;
	}

	public void setButtonTrigered(String buttonTrigered) {
		this.buttonTrigered = buttonTrigered;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonthsYear() {
		return monthsYear;
	}

	public void setMonthsYear(String monthsYear) {
		this.monthsYear = monthsYear;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
}
