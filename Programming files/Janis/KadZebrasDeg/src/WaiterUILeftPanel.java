import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	// private JComboBox years;
	private JComboBox months;
	private JComboBox days;
	private JComboBox time;
	private JLabel noOfSeatsLabel;
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel dayLabel;
	private JLabel nameLabel;
	private JLabel phoneNoLabel;
	private JLabel timeLabel;
	private JTextField noOfSeatsTextField;
	private JTextField nameTextField;
	private JTextField phoneNoTextField;
	private JButton systemSudgestionBtn;
	private JButton makeReservationBtn;
	private JButton searchBtn;
	private JButton backBtn;
	private ListenerForEverything listenerForEverything;
	Date currentTime;
	DefaultComboBoxModel timeComboBox;

	public WaiterUILeftPanel() {

		// years = new JComboBox();
		months = new JComboBox();
		days = new JComboBox();
		time = new JComboBox();
		noOfSeatsLabel = new JLabel("No. of seats:");
		yearLabel = new JLabel("Year:");
		monthLabel = new JLabel("Month/Year:");
		dayLabel = new JLabel("Day:");
		nameLabel = new JLabel("Name:");
		phoneNoLabel = new JLabel("Phone No.:");
		timeLabel = new JLabel("Time: ");
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
		currentTime = new Date();

		Border innerBorder = BorderFactory.createTitledBorder("Reservation");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		// sets months and years in combo box
		setMonthAndYearInComboBox();

		// sets days in combo box
		setDaysInDaysComboBox();

		// sets the time in combo box
		setTheCorrectTimesInComboBox();

		time.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// timeComboBox.addElement("now");

			}
		});

		// Sets the right amount of days in days combo box after choosing one of
		// the months.
		months.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String currentSelection = months.getSelectedItem().toString();
				String[] parts = currentSelection.split(" ");
				String monthOfSelection = parts[0];
				String yearOfSelection = parts[1];
				int yearInInt = Integer.parseInt(yearOfSelection);
				int monthInt = 12;
				// gets the name of the month from the combo box and finds the
				// right integer of month
				if (monthOfSelection.equals("January")) {
					monthInt = 0;
				}
				if (monthOfSelection.equals("February")) {
					monthInt = 1;
				}
				if (monthOfSelection.equals("March")) {
					monthInt = 2;
				}
				if (monthOfSelection.equals("April")) {
					monthInt = 3;
				}
				if (monthOfSelection.equals("May")) {
					monthInt = 4;
				}
				if (monthOfSelection.equals("June")) {
					monthInt = 5;
				}
				if (monthOfSelection.equals("July")) {
					monthInt = 6;
				}
				if (monthOfSelection.equals("August")) {
					monthInt = 7;
				}
				if (monthOfSelection.equals("September")) {
					monthInt = 8;
				}
				if (monthOfSelection.equals("October")) {
					monthInt = 9;
				}
				if (monthOfSelection.equals("November")) {
					monthInt = 10;
				}
				if (monthOfSelection.equals("December")) {
					monthInt = 11;
				}

				// Create a calendar object and set year and month
				Calendar mycal1 = new GregorianCalendar(yearInInt, monthInt, 1);

				// Get the number of days in that month
				int daysInMonth = mycal1
						.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

				// Checks if the month is current month or any other and then
				// sets the right amount of days
				// in combo box after the month was chosen and prevents system
				// from choosing past days.
				DefaultComboBoxModel daysOfMonthComboBox1 = new DefaultComboBoxModel();
				Calendar cal = Calendar.getInstance();
				if (monthInt != cal.get(Calendar.MONTH)) {
					for (int x = 1; x <= daysInMonth; x++) {
						daysOfMonthComboBox1.addElement(x);
					}
				} else {
					for (int x = cal.get(Calendar.DAY_OF_MONTH); x <= cal
							.getActualMaximum(Calendar.DAY_OF_MONTH); x++) {
						daysOfMonthComboBox1.addElement(x);
					}
				}
				days.setModel(daysOfMonthComboBox1);

			}
		});

		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this,
						"backBtnWaiterUILeftPanel");
				if (listenerForEverything != null) {
					listenerForEverything.AnyEventOcurred(anyEvent);
				}
			}
		});

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		// First row first column
		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 20, 0, 0);
		add(noOfSeatsLabel, gc);

		// First row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 0, 0, 0);
		add(noOfSeatsTextField, gc);

		// Second row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 1;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(systemSudgestionBtn, gc);

		// Third row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 2;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(monthLabel, gc);

		// Third row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 2;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(months, gc);

		// Fourth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 3;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(dayLabel, gc);

		// Fourth row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 3;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(days, gc);

		// Fifth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 4;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(timeLabel, gc);

		// Fifth row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 4;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(time, gc);

		// Sixth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 5;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(nameLabel, gc);

		// Sixth row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 5;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameTextField, gc);

		// Seventh row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 6;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(phoneNoLabel, gc);

		// Seventh row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 6;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(phoneNoTextField, gc);

		// Eighth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 7;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(makeReservationBtn, gc);

		// Ninth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 8;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(searchBtn, gc);

		// Tenth row first column

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

	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}

	// set month and year in combo box
	public void setMonthAndYearInComboBox() {
		int iYear = Calendar.getInstance().get(Calendar.YEAR);
		int iMonth = Calendar.getInstance().get(Calendar.MONTH);
		int iDay = Calendar.getInstance().get(Calendar.DATE);

		// Create a calendar object and set year and month
		Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);

		// Get the number of days in that month
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

		// sets the months and year in combo box when this panel is created
		// (allows bookings 1 year in future)
		// This project uses Date and time from the system
		DefaultComboBoxModel monthsComboBox = new DefaultComboBoxModel();
		String[] monthss = new DateFormatSymbols().getMonths();
		for (int i = new Date().getMonth(); i <= 11; i++) {
			String month = monthss[i];
			monthsComboBox.addElement(month + " "
					+ Calendar.getInstance().get(Calendar.YEAR));
		}
		for (int i = 0; i < new Date().getMonth(); i++) {
			String month1 = monthss[i];
			monthsComboBox.addElement(month1 + " "
					+ (Calendar.getInstance().get(Calendar.YEAR) + 1));
		}

		months.setModel(monthsComboBox);
	}

	public void setDaysInDaysComboBox() {
		DefaultComboBoxModel daysOfMonthComboBox = new DefaultComboBoxModel();
		Calendar c = Calendar.getInstance();
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int x = c.get(Calendar.DAY_OF_MONTH); x <= monthMaxDays; x++) {
			daysOfMonthComboBox.addElement(x);
		}
		days.setModel(daysOfMonthComboBox);
	}

	public void setTheCorrectTimesInComboBox() {
		String time = getOpeningAnClosingTime();
		System.out.println("time <<<>>>>  " + time);
		String bothTimes[] = time.split("-");
		String timeO = bothTimes[0];
		String timeC = bothTimes[1];
		Date dateTime = new Date();
		int currentMinutes = dateTime.getMinutes();
		int currentHours = dateTime.getHours();
		String currentMinutesString = "0";
		String currentHoursString = "0";
		if (currentMinutes < 10) {
			currentMinutesString = "0" + currentMinutes;
		} else {
			currentMinutesString = "" + currentMinutes;
		}
		if (currentHours < 10) {
			currentHoursString = "0" + currentHours;
		} else {
			currentHoursString = "" + currentHours;
		}
		String curT = "" + currentHoursString + "" + currentMinutesString;
		System.out
				.println("String curT = + currentHoursString +  + currentMinutesString; "
						+ curT);
		time = checkWhichTimeToUse(timeO, timeC, curT);

		String splitTime[] = time.split("-");
		System.out.println("time >>>>  " + time);
		String OHour = splitTime[0].substring(0, 2);
		System.out.println("splitTime[0].substring(2, 4)  " + splitTime[0]);
		String OMinutes = splitTime[0].substring(2, 4);
		String CHour = splitTime[1].substring(0, 2);

		String CMinutes = splitTime[1].substring(2, 4);
		int convertedOT = Integer.parseInt(splitTime[0]);
		int convertedCT = Integer.parseInt(splitTime[1]);
		int closeHour = Integer.parseInt(CHour);
		if (convertedOT < convertedCT && convertedCT - convertedOT < 100) {
			System.out.println("1");
			ifLessThanHour();
		}
		if (convertedCT > convertedOT && convertedCT - convertedOT < 100) {
			System.out.println(convertedCT - convertedOT);
			System.out.println("1.1");
			ifLessThanHour();
		} else {
			System.out.println("sooooooooosdvvhig");
			if (convertedOT < convertedCT && convertedCT - convertedOT == 100
					|| convertedCT > convertedOT
					&& convertedOT - convertedCT == 100) {
				ifOneHour(OHour, OMinutes);
				System.out.println("2");
			} else {
				System.out.println("sooooooooosdvvhig  2");
				if (convertedOT < convertedCT
						&& convertedCT - convertedOT <= 200) {
					System.out.println(convertedCT - convertedOT);
					System.out.println("3.1");
					ifLessThanThreeHours(OHour, OMinutes, CHour, CMinutes);

				}
				if (convertedCT < convertedOT
						&& convertedOT - convertedCT <= 200) {
					System.out.println(convertedOT - convertedCT);
					System.out.println("3.2");
					ifLessThanThreeHours(OHour, OMinutes, CHour, CMinutes);

				} else {
					System.out.println("sooooooooosdvvhig     3");
					if (convertedOT < convertedCT) {
						System.out.println("hereeee!");
						ifOpenLessThanClose(OHour, OMinutes, CHour, CMinutes);
						System.out.println("4");
					} else {
						if (convertedOT == convertedCT) {
							ifOpenEqualsClose(OHour, OMinutes, CHour, CMinutes);
							System.out.println("5");
						} else {
							if (convertedCT < convertedOT && closeHour >= 1) {
								ifCloseLessThanOpenAndCloseHourIsHigherOrEqualToOne(
										OHour, OMinutes, CHour, CMinutes);
								System.out.println("6");

							} else {
								if (convertedCT < convertedOT && closeHour == 0) {
									ifCloseIsLessThanOpenAndCloseHourEqualsZero(
											OHour, OMinutes, CHour, CMinutes);
									System.out.println("7");
								}
							}
						}
					}
				}
			}
		}
	}

	public String checkWhichTimeToUse(String openTime, String closeTime,
			String currentTime) {

		int open = Integer.parseInt(openTime);
		int close = Integer.parseInt(closeTime);
		System.out.println("String currentTime: " + currentTime);
		int current = Integer.parseInt(currentTime);
		String time = openTime + "-" + closeTime;
		System.out.println("String time = openTime + - + closeTime;:"
				+ openTime + "-" + closeTime);
		int timeHours = Integer.parseInt(currentTime.substring(0, 2));
		int timeMinutes = 0;
		String timeMinutesString = "";
		timeMinutes = adoptCurrentTimeToSystem(openTime);
		System.out.println("timeMinutes:" + timeMinutes);
		if (close < open) {
			if (current > open || current < close) {
				timeMinutesString = "" + timeMinutes;
				if (timeMinutes == 0 && timeHours < 23) {
					timeHours = +1;
					timeMinutesString = "00";
				}
				if (timeMinutes == 0 && timeHours == 23) {
					timeHours = 0;
					timeMinutesString = "00";
				}
				if (timeHours < 10) {
					time = "0" + timeHours;
				}
				if (timeMinutes < 10) {
					time = time + "0" + timeMinutesString;
				}
				if (timeHours >= 10) {
					time = "" + timeHours + "" + timeMinutesString;
				}
			}
		}
		if (open < close) {
			System.out.println("yes" + current + ">" + open + "&&" + current
					+ "<" + close);
			if (current > open && current < close) {

				timeMinutesString = "" + timeMinutes;
				if (timeMinutes == 0 && timeHours < 23) {
					timeHours = timeHours + 1;
					timeMinutesString = "00";
				}
				if (timeMinutes == 0 && timeHours == 23) {
					timeHours = 0;
					timeMinutesString = "00";
				}
				if (timeHours < 10) {
					time = "0" + timeHours + "" + timeMinutesString;
				}
				if (timeMinutes < 10) {
					time = timeHours + "0" + timeMinutesString;
				}
				if (timeHours >= 10) {
					time = "" + timeHours + "" + timeMinutesString;
				}
			}
		}
		System.out.println("ashjgsdf ???" + time);
		return time + "-" + closeTime;
	}

	public String getOpeningAnClosingTime() {
		String fileName = "Times.txt";
		String b;
		int intOpenH = 0;
		int intOpenM = 0;
		int intCloseH = 0;
		int intCloseM = 0;
		String allNumbersToString = "";
		try {
			FileReader f = new FileReader("Times.txt");
			BufferedReader br = new BufferedReader(f);
			b = br.readLine();
			br.close();

			String[] parts = b.split("-");
			String openH = parts[0];
			String openM = parts[1];
			String closeH = parts[2];
			String closeM = parts[3];
			String end = parts[4];
			String aNTS = openH + openM + "-" + closeH + closeM;
			allNumbersToString = aNTS;
			intOpenH = Integer.parseInt(removeZeros(openH));
			intOpenM = Integer.parseInt(removeZeros(openM));
			intCloseH = Integer.parseInt(removeZeros(closeH));
			intCloseM = Integer.parseInt(removeZeros(closeM));
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
		// String allNumbersToString = "" + intOpenH + intOpenM + "-" +
		// intCloseH + intCloseM;
		return allNumbersToString;
	}

	public String removeZeros(String number) {
		String numberConverted;
		if (number.substring(0, 0).equals("0")) {
			numberConverted = number.substring(1, 1);
		} else {
			numberConverted = number;
		}
		return numberConverted;
	}

	public int adoptCurrentTimeToSystem(String openingTime) {
		Date time = new Date();
		String currentTime = "" + time.getHours() + "" + time.getMinutes();
		System.out.println("  + time.getHours()+  + time.getMinutes()" + ""
				+ time.getHours() + "" + time.getMinutes());
		int complete = Integer.parseInt(currentTime);
		int currentMinutes = time.getMinutes();

		int adoptedMinutes = 0;
		int openTime = Integer.parseInt(openingTime);
		System.out.println("complete > openTime " + complete + ">" + openTime);
		if (complete > openTime) {
			if (currentMinutes > 0 && currentMinutes <= 15) {
				adoptedMinutes = 15;
			}
			if (currentMinutes > 15 && currentMinutes <= 30) {
				adoptedMinutes = 30;
			}
			if (currentMinutes > 30 && currentMinutes <= 45) {
				adoptedMinutes = 45;
			}
		}
		return adoptedMinutes;
	}

	public void ifLessThanHour() {
		timeComboBox = new DefaultComboBoxModel();
		timeComboBox.addElement("Now");
		time.setModel(timeComboBox);
	}

	public void ifOneHour(String oH, String oM) {
		timeComboBox = new DefaultComboBoxModel();
		timeComboBox.addElement("Now");
		timeComboBox.addElement(oH + ":" + oM);
		time.setModel(timeComboBox);
	}

	public void ifLessThanThreeHours(String oH, String oM, String cH, String cM) {
		int newOpeningTime = Integer.parseInt(oH + oM);
		int newClosingTime = Integer.parseInt(cH + cM);
		timeComboBox = new DefaultComboBoxModel();
		timeComboBox.addElement("Now");
		if (newClosingTime < newOpeningTime) {
			if (Integer.parseInt(cH) == 1) {
				for (int y = 0; y <= Integer.parseInt(cH); y += 15) {
					if (y == 0) {
						timeComboBox.addElement((Integer.parseInt(cH) - 1)
								+ ":" + "00");
					} else {
						timeComboBox.addElement((Integer.parseInt(cH) - 1)
								+ ":" + y);
					}
				}
				for (int y = Integer.parseInt(oM); y <= 45; y += 15) {
					if (y == 0) {
						timeComboBox.addElement((Integer.parseInt(oH)) + ":"
								+ "00");
					} else {
						timeComboBox.addElement((Integer.parseInt(oH)) + ":"
								+ y);
					}
				}
			}
			if (Integer.parseInt(cH) == 0) {
				for (int y = Integer.parseInt(oM); y <= 45; y += 15) {
					if (y == 0) {
						timeComboBox.addElement(oH + ":" + "00");
					} else {
						timeComboBox.addElement(oH + ":" + y);
					}
				}
				for (int y = 0; y <= Integer.parseInt(cM); y += 15) {
					if (y == 0) {
						timeComboBox.addElement(23 + ":" + "00");
					} else {
						timeComboBox.addElement(23 + ":" + y);
					}
				}
			}
		}
		if (newOpeningTime < newClosingTime) {
			for (int y = Integer.parseInt(oM); y <= 45; y += 15) {
				if (y == 0) {
					timeComboBox.addElement(oH + ":" + "00");
				} else {
					timeComboBox.addElement(oH + ":" + y);
				}
			}
			for (int y = 0; y <= Integer.parseInt(cM); y += 15) {
				if (y == 0) {
					timeComboBox.addElement((Integer.parseInt(cH) - 1) + ":"
							+ "00");
				} else {
					timeComboBox.addElement((Integer.parseInt(cH) - 1) + ":"
							+ y);
				}
			}
		}
		time.setModel(timeComboBox);
	}

	public void ifOpenLessThanClose(String oH, String oM, String cH, String cM) {
		// int newOpeningTime = Integer.parseInt(oH + oM);
		// int newClosingTime = Integer.parseInt(cH + cM);
		System.out.println("gets here");
		timeComboBox = new DefaultComboBoxModel();
		timeComboBox.addElement("Now");
		for (int y = Integer.parseInt(oM); y <= 45; y += 15) {
			System.out.println(oH + ":" + y);
			if (y == 0) {
				if (Integer.parseInt(oH) < 10) {
					timeComboBox.addElement("0" + Integer.parseInt(oH) + ":"
							+ "00");
				} else {
					timeComboBox.addElement(Integer.parseInt(oH) + ":" + "00");
				}
			} else {
				if (Integer.parseInt(oH) < 10) {
					timeComboBox.addElement("0" + Integer.parseInt(oH) + ":"
							+ y);
				} else {
					timeComboBox.addElement(Integer.parseInt(oH) + ":" + y);
				}
			}
		}
		for (int x = Integer.parseInt(oH) + 1; x < Integer.parseInt(cH) - 2; x++) {
			for (int y = 0; y <= 45; y += 15) {
				if (y == 0) {
					if (x < 10) {
						timeComboBox.addElement("0" + x + ":" + "00");
					} else {
						timeComboBox.addElement(x + ":" + "00");
					}
				} else {
					if (x < 10) {
						timeComboBox.addElement("0" + x + ":" + y);
					} else {
						timeComboBox.addElement(x + ":" + y);
					}
				}
			}
		}
		for (int y = 0; y <= Integer.parseInt(cM); y += 15) {
			if (y == 0) {
				if ((Integer.parseInt(cH) - 1) < 10) {
					timeComboBox.addElement("0" + (Integer.parseInt(cH) - 1)
							+ ":" + "00");
				} else {
					timeComboBox.addElement((Integer.parseInt(cH) - 1) + ":"
							+ "00");
				}
			} else {
				if ((Integer.parseInt(cH) - 1) < 10) {
					timeComboBox.addElement("0" + (Integer.parseInt(cH) - 1)
							+ ":" + y);
				} else {
					timeComboBox.addElement((Integer.parseInt(cH) - 1) + ":"
							+ y);
				}
			}
		}
		time.setModel(timeComboBox);
	}

	public void ifOpenEqualsClose(String oH, String oM, String cH, String cM) {
		timeComboBox = new DefaultComboBoxModel();
		timeComboBox.addElement("Now");
		for (int x = 0; x <= 23; x++) {
			for (int y = 0; y <= 45; y += 15) {
				if (y == 0) {
					if (x < 10) {
						timeComboBox.addElement("0" + x + ":" + "00");
					} else {
						timeComboBox.addElement(x + ":" + "00");
					}
				} else {
					if (x < 10) {
						timeComboBox.addElement("0" + x + ":" + y);
					} else {
						timeComboBox.addElement(x + ":" + y);
					}
				}
			}
		}
		time.setModel(timeComboBox);
	}

	public void ifCloseLessThanOpenAndCloseHourIsHigherOrEqualToOne(String oH,
			String oM, String cH, String cM) {
		timeComboBox = new DefaultComboBoxModel();
		timeComboBox.addElement("Now");
		for (int x = 0; x < Integer.parseInt(cH) - 1; x++) {
			for (int y = Integer.parseInt(cM); y <= 45; y += 15) {
				if (y == 0) {
					if (x < 10) {
						timeComboBox.addElement("0" + x + ":" + "00");
					} else {
						timeComboBox.addElement(x + ":" + "00");
						System.out.println("00" + ":" + "00");
					}
				} else {
					if (x < 10) {
						timeComboBox.addElement("0" + x + ":" + y);
					} else {
						timeComboBox.addElement(x + ":" + y);
					}
				}
			}
		}

		for (int y = 0; y <= Integer.parseInt(cM); y += 15) {
			if (y == 0) {
				timeComboBox
						.addElement((Integer.parseInt(cH) - 1) + ":" + "00");
			} else {
				timeComboBox.addElement((Integer.parseInt(cH) - 1) + ":" + y);
			}
		}
		for (int y = Integer.parseInt(oM); y <= 45; y += 15) {
			if (y == 0) {
				timeComboBox.addElement(Integer.parseInt(oH) + ":" + "00");
			} else {
				timeComboBox.addElement(Integer.parseInt(oH) + ":" + y);
			}
		}
		for (int x = Integer.parseInt(oH) + 1; x <= 23; x++) {
			for (int y = 0; y <= 45; y += 15) {
				if (y == 0) {
					timeComboBox.addElement(x + ":" + "00");
				} else {
					timeComboBox.addElement(x + ":" + y);
				}
			}
		}
		time.setModel(timeComboBox);
	}

	public void ifCloseIsLessThanOpenAndCloseHourEqualsZero(String oH,
			String oM, String cH, String cM) {
		timeComboBox = new DefaultComboBoxModel();
		timeComboBox.addElement("Now");
		for (int y = Integer.parseInt(oM); y <= 45; y += 15) {
			if (y == 0) {
				if (Integer.parseInt(oH) < 10) {
					timeComboBox.addElement("0" + Integer.parseInt(oH) + ":"
							+ "00");
				} else {
					timeComboBox.addElement(Integer.parseInt(oH) + ":" + "00");
				}
			} else {
				if (Integer.parseInt(oH) < 10) {
					timeComboBox.addElement("0" + Integer.parseInt(oH) + ":"
							+ y);
				} else {
					timeComboBox.addElement(Integer.parseInt(oH) + ":" + y);
				}
			}
		}
		for (int x = Integer.parseInt(oH) + 1; x < 23; x++) {
			for (int y = 0; y <= 45; y += 15) {
				if (y == 0) {
					if (x < 10) {
						timeComboBox.addElement("0" + x + ":" + "00");
					} else {
						timeComboBox.addElement(x + ":" + "00");
					}
				} else {
					if (x < 10) {
						timeComboBox.addElement("0" + x + ":" + y);
					} else {
						timeComboBox.addElement(x + ":" + y);
					}
				}
			}
		}
		for (int y = 0; y <= Integer.parseInt(cM); y += 15) {
			if (y == 0) {
				timeComboBox.addElement(23 + ":" + "00");
			} else {
				timeComboBox.addElement(23 + ":" + y);
			}
		}
		time.setModel(timeComboBox);
	}

	public void revalidatePanel() {
		this.revalidate();
		this.repaint();
	}
}
