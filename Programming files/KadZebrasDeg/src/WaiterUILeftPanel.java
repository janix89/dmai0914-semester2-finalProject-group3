import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class WaiterUILeftPanel extends JPanel {
	
	//private JComboBox years;
	private JComboBox months;
	private JComboBox days;
	private JLabel noOfSeatsLabel;
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel dayLabel;
	private JLabel nameLabel;
	private JLabel phoneNoLabel;
	private JTextField noOfSeatsTextField;
	private JTextField nameTextField;
	private JTextField phoneNoTextField;
	private JButton systemSudgestionBtn;
	private JButton makeReservationBtn;
	private JButton searchBtn;
	private JButton backBtn;
	private ListenerForEverything listenerForEverything;
	
	public WaiterUILeftPanel(){
		
		//years = new JComboBox();
		months = new JComboBox();
		days = new JComboBox();
		noOfSeatsLabel = new JLabel("No. of seats:");
		yearLabel = new JLabel("Year:");
		monthLabel = new JLabel("Month/Year:");
		dayLabel = new JLabel("Day:");
		nameLabel = new JLabel("Name:");
		phoneNoLabel = new JLabel("Phone No.:");
		noOfSeatsTextField = new JTextField(2);
		nameTextField = new JTextField(10);
		phoneNoTextField = new JTextField(10);
		systemSudgestionBtn = new JButton("System sudgestion");
		systemSudgestionBtn.setPreferredSize(new Dimension(150, 25));
		makeReservationBtn = new JButton("Make reservation");
		makeReservationBtn.setPreferredSize(new Dimension(150, 25));
		searchBtn = new JButton("Search");
		searchBtn.setPreferredSize(new Dimension(150, 25));
		backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(150, 25));
		
		
		
		int iYear = Calendar.getInstance().get(Calendar.YEAR);
		int iMonth = Calendar.getInstance().get(Calendar.MONTH);
		int iDay = Calendar.getInstance().get(Calendar.DATE);

		// Create a calendar object and set year and month
		Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);

		// Get the number of days in that month
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
		
		System.out.println(daysInMonth);
		/**
		DefaultComboBoxModel yearsComboBox = new DefaultComboBoxModel();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		yearsComboBox.addElement(year);
		yearsComboBox.addElement(year +1);
		years.setModel(yearsComboBox);
		*/
		DefaultComboBoxModel monthsComboBox = new DefaultComboBoxModel();
		String[] monthss = new DateFormatSymbols().getMonths();
	    for (int i = new Date().getMonth(); i <= 11; i++) {	
	      String month = monthss[i];
	      monthsComboBox.addElement(month + " " + Calendar.getInstance().get(Calendar.YEAR));
	      System.out.println("hjhjhj");
	    }
	    for (int i = 0;  i < new Date().getMonth(); i++) {
		      String month1 = monthss[i];
		      monthsComboBox.addElement(month1 + " " + (Calendar.getInstance().get(Calendar.YEAR) + 1));
		}
	    
	    months.setModel(monthsComboBox);
		
		DefaultComboBoxModel daysOfMonthComboBox = new DefaultComboBoxModel();
		Calendar c = Calendar.getInstance();
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int x = c.get(Calendar.DAY_OF_MONTH); x<= monthMaxDays; x++){
			daysOfMonthComboBox.addElement(x);
		}
		days.setModel(daysOfMonthComboBox);
		
		Border innerBorder = BorderFactory.createTitledBorder("Reservation");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		months.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String currentSelection = months.getSelectedItem().toString();
				String[] parts = currentSelection.split(" ");
				String monthOfSelection = parts[0];
				String yearOfSelection = parts[1];
				int yearInInt = Integer.parseInt(yearOfSelection);
				int monthInt = 12;
				if(monthOfSelection.equals("January")){
					monthInt = 0;
				}
				if(monthOfSelection.equals("February")){
					monthInt = 1;
				}
				if(monthOfSelection.equals("March")){
					monthInt = 2;
				}
				if(monthOfSelection.equals("April")){
					monthInt = 3;
				}
				if(monthOfSelection.equals("May")){
					monthInt = 4;
				}
				if(monthOfSelection.equals("June")){
					monthInt = 5;
				}
				if(monthOfSelection.equals("July")){
					monthInt = 6;
				}
				if(monthOfSelection.equals("August")){
					monthInt = 7;
				}
				if(monthOfSelection.equals("September")){
					monthInt = 8;
				}
				if(monthOfSelection.equals("October")){
					monthInt = 9;
				}
				if(monthOfSelection.equals("November")){
					monthInt = 10;
				}
				if(monthOfSelection.equals("December")){
					monthInt = 11;
				}


				// Create a calendar object and set year and month
				Calendar mycal1 = new GregorianCalendar(yearInInt, monthInt, 1);

				// Get the number of days in that month
				int daysInMonth = mycal1.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
				
				DefaultComboBoxModel daysOfMonthComboBox1 = new DefaultComboBoxModel();
				Calendar cal = Calendar.getInstance();
				if(monthInt != cal.get(Calendar.MONTH)){
					for(int x = 1; x<= daysInMonth; x++){
						daysOfMonthComboBox1.addElement(x);
					}
				}
				else{
					for(int x = cal.get(Calendar.DAY_OF_MONTH); x<= cal.getActualMaximum(Calendar.DAY_OF_MONTH); x++){
						daysOfMonthComboBox1.addElement(x);
					}
				}
				days.setModel(daysOfMonthComboBox1);
				
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "backBtnWaiterUILeftPanel");
				if(listenerForEverything != null){
					listenerForEverything.AnyEventOcurred(anyEvent);
				}
			}
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 20, 0, 0);
		add(noOfSeatsLabel, gc);
		
		//First row second column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 1;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 0, 0, 0);
		add(noOfSeatsTextField, gc);
		
		//Second row first column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 2;
		
		gc.gridx = 0;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(systemSudgestionBtn, gc);
		
		//Third row first column
		/**
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 2;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(yearLabel, gc);
		
		//Third row second column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 1;
		gc.gridy = 2;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(years, gc);
		*/
		//Fourth row first column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 3;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(monthLabel, gc);
		
		//Fourth row second column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 1;
		gc.gridy = 3;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(months, gc);
		
		//Fifth row first column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 4;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(dayLabel, gc);
		
		//Fifth row second column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 1;
		gc.gridy = 4;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(days, gc);
		
		//Sixth row first column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 5;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(nameLabel, gc);
		
		//Sixth row second column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 1;
		gc.gridy = 5;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameTextField, gc);
		
		//Seventh row first column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 6;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(phoneNoLabel, gc);
		
		//Seventh row second column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 1;
		gc.gridy = 6;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(phoneNoTextField, gc);
		
		//Eighth row first column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 2;
		
		gc.gridx = 0;
		gc.gridy = 7;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(makeReservationBtn, gc);
		
		//Ninth row first column
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 2;
		
		gc.gridx = 0;
		gc.gridy = 8;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(searchBtn, gc);
		
		//Tenth row first column
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 2;
		
		gc.gridx = 0;
		gc.gridy = 9;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 37, 0);
		add(backBtn, gc);
	}
	
	public void setListenerForEverything(ListenerForEverything listener){
		this.listenerForEverything = listener;
	}


}
