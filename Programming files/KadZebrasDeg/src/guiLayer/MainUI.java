package guiLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelLayer.Course;
import modelLayer.Drink;
import modelLayer.Merchandise;
import modelLayer.Miscellaneous;
import modelLayer.Order;
import modelLayer.OrderLine;
import modelLayer.Reservation;
import controlLayer.MerchandiseController;
import controlLayer.OrderController;
import controlLayer.ReservationController;
import dbLayer.DBOrder;
import dbLayer.DBOrderLine;
import exceptionsLayer.DatabaseException;

public class MainUI extends JFrame {

	private JPanel leftPanel;
	private JPanel rightPanel;
	private Container container;
	// private JMenuBar menuBar;
	// private JDialog dialog;
	private ReservationController reservationController;
	private MerchandiseController merchandiseController;
	private OrderController orderController;
	private String name;
	public MainUI() {

		super("MainUI");

		setPanelsForMainUI();
		// addMenuBar();
		orderController = new OrderController();
		reservationController = new ReservationController();
		merchandiseController = new MerchandiseController();
		setLayout(new BorderLayout());
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);

		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
	}

	// Added by Janis
	// Might not need it anymore
	/**
	 * public void addMenuBar() { menuBar = new JMenuBar(); JMenu menuOptions =
	 * new JMenu("Options"); menuBar.add(menuOptions); JMenuItem
	 * optionsMenuItemPreferences = new JMenuItem("Preferences");
	 * menuOptions.add(optionsMenuItemPreferences); this.setJMenuBar(menuBar);
	 * dialog = new PreferencesDialog(MainUI.this);
	 * 
	 * /** ((PreferencesDialog) dialog) .setListenerForEverything(new
	 * ListenerForEverything() {
	 * 
	 * @Override public void AnyEventOcurred(AnyEvent anyEvent) { if
	 *           (anyEvent.getButtonTrigered().equals( "savePreferences")) { if
	 *           (leftPanel instanceof WaiterUILeftPanel) { ((WaiterUILeftPanel)
	 *           leftPanel) .setTheCorrectTimesInComboBox(); }
	 *           leftPanel.revalidate(); leftPanel.repaint(); }
	 * 
	 *           } });
	 * 
	 *           optionsMenuItemPreferences.addActionListener(new
	 *           ActionListener() {
	 * @Override public void actionPerformed(ActionEvent e) {
	 *           dialog.setLocationRelativeTo(MainUI.this);
	 *           dialog.setVisible(true); } });
	 * 
	 *           }
	 */
	public void setTitle(String name) {
		super.setTitle(name);

	}

	public void setPanels(Component left, Component right) {
		remove(leftPanel);
		remove(rightPanel);
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.CENTER);

	}

	public void setPanelsForMainUI() {
		leftPanel = new MainUILeftPanel();
		rightPanel = new MainUIRightPanel();

		((MainUILeftPanel) leftPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent) {

						if (anyEvent.getButtonTrigered().equals("exitBtn")) {
							System.out.println("exitBtn");
							System.exit(0);
						}
						if (anyEvent.getButtonTrigered().equals(
								"managerMenuBtn")) {
							System.out.println("managerMenuBtn");
							setTitle("Manager menu");

						}
						if (anyEvent.getButtonTrigered()
								.equals("waiterMenuBtn")) {
							System.out.println("waiterMenuBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new WaiterUILeftPanel();
							rightPanel = new WaiterUIRightPanel();
							setPanelsForWaiterUI();
							setTitle("Waiter menu");
							setPanels(leftPanel, rightPanel);

							container.validate();
							container.repaint();

						}
						if (anyEvent.getButtonTrigered().equals("chefMenuBtn")) {
							/*
							 * System.out.println("chefMenuBtn");
							 * setTitle("Chef menu"); container =
							 * getContentPane(); container.removeAll();
							 * leftPanel = new ChefUIRightPanel(); rightPanel =
							 * new ChefUILeftPanel(); //
							 * setPanelsForWaiterUILeftPanel(); //
							 * setTitle("Waiter menu"); setPanels(leftPanel,
							 * rightPanel);
							 * 
							 * container.validate(); container.repaint();
							 */
						}
						if (anyEvent.getButtonTrigered().equals("tableMenuBtn")) {
							System.out.println("tableMenuBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new TableUILeftPanel();
							rightPanel = new TableUIRightPanel();
							setPanelsForTableMenu();
							setTitle("Table Menu");
							setPanels(leftPanel, rightPanel);

							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals(
								"merchandiseMenuBtn")) {
							System.out.println("merchandiseMenuBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MerchandiseMenuLeftPanel();
							rightPanel = new MerchandiseMenuRightPanel();
							MerchandiseController mc = new MerchandiseController();
							ArrayList<Merchandise> m = mc.getAllMerchandise();
							System.out.println(m.size());
							
							((MerchandiseMenuRightPanel)rightPanel).populateMerchandise(m);
							setPanelsForMerchandiseMenu();
							setTitle("Merchandise menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals("staffBtn")) {
							System.out.println("staffBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new StaffUILeftPanel();
							rightPanel = new StaffUIRightPanel();
							setPanelsForStaffMenu();
							setTitle("Staff Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
					}
				});
	}

	public void setPanelsForWaiterUI() {
		((WaiterUILeftPanel) leftPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals(
								"backBtnWaiterUILeftPanel")) {
							System.out.println("backBtnWaiterUILeftPanel");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MainUILeftPanel();
							rightPanel = new MainUIRightPanel();
							setPanelsForMainUI();
							setTitle("MainUI");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals(
								"WaiterUILeftPanelSystemSudgestionBtn")) {
							System.out
									.println("WaiterUILeftPanelSystemSudgestionBtn");

						}
						if (anyEvent.getButtonTrigered().equals(
								"WaiterUILeftPanelSearchBtn")) {
							System.out.println("WaiterUILeftPanelSearchBtn");

						}
						if (anyEvent.getButtonTrigered().equals(
								"WaiterUILeftPanelmakeReservationBtn")) {
							System.out
									.println("WaiterUILeftPanelmakeReservationBtn");
							// Added by Janis
							// Here you get the info from WaiterUILeftPanel

							name = anyEvent.getName();
							String phoneNo = anyEvent.getPhoneNo();
							String dayOfReservation = anyEvent.getDays();
							String monthAndYearOfReservation = anyEvent
									.getMonthsYear();
							String timeForReservation = anyEvent.getTime();
							int numberOfGuests = anyEvent.getNumberOfSeats();
							((WaiterUILeftPanel)leftPanel).createOrderAndAddTables();
							int option = JOptionPane.showConfirmDialog(MainUI.this, "Would you like to add order?");
							
							if(option ==  0){
								
								container = getContentPane();
								container.removeAll();
								leftPanel = new MakeOrderUILeftPanel();
								rightPanel = new MakeOrderUIRightPanel();
								setPanelsForMakeOrderBeginning();
								setPanelsForMakeOrderUILeftPanel();
								setTitle("Order Menu");
								setPanels(leftPanel, rightPanel);
								container.validate();
								container.repaint();
								System.out.println(name);
							}
							else{
							// Added by Janis
							// This is required to refresh the frame and panels
							// selected tables will become red or yellow after
							// reservation and
							// date checking when it will be implemented
							// Right now only red

							container = getContentPane();
							container.removeAll();
							leftPanel = new WaiterUILeftPanel();
							rightPanel = new WaiterUIRightPanel();
							setPanelsForWaiterUI();
							setTitle("Waiter menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
							}

						}
						if (anyEvent.getButtonTrigered().equals("makeOrder")) {
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							rightPanel = new MakeOrderUIRightPanel();
							setPanelsForMakeOrderBeginning();
							setPanelsForMakeOrderUILeftPanel();
							setTitle("Order Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}

					}
				});
		// Added by Janis
		// The line belove requires ArrayList of all tables in order to display
		// table buttons
		// ((WaiterUIRightPanel)rightPanel).setTheArrayListOfAllTables(allTables);

		((WaiterUIRightPanel) rightPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {

						if (anyEvent.getName().equals("RedTable")) {
							System.out.println("RedTable");
							ChoosenTableDialog d = new ChoosenTableDialog(
									MainUI.this, "Chosen table");
							setPanelForChosenTable(d);
							d.setLocationRelativeTo(MainUI.this);
							d.setTableNoLabel(anyEvent.getButtonTrigered());
							System.out.println(anyEvent.getButtonTrigered());
							d.setVisible(true);
							d.repaint();
							d.validate();
							// container.validate();
							// container.repaint();

						}
						// Added by Janis
						// This was my code for when table is selected and
						// deselected
						// It works like table added to arraylist and removed
						// It needs some methods in controller
						// If you think it could work ask me for it

						/**
						 * int table = Integer.parseInt(anyEvent
						 * .getButtonTrigered()); if
						 * (reservationController.getChosenTables().isEmpty() !=
						 * true) { ArrayList
						 * <Table>
						 * tbls = reservationController.getChosenTables(); for
						 * (Table t : tbls) { if(reservationController.
						 * checkIfTableHasBeenAlreadyAdded(table) == true) {
						 * tbls.remove(t); } else { Table tab =
						 * reservationController.checkTables(t.getTableNo());
						 * if(tab != null){ tbls.add(tab); } } } }
						 */
					}
				});
	}

	/**
	 * 
	 */
	public void setPanelsForMerchandiseMenu() {
		((MerchandiseMenuLeftPanel) leftPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals("backBtn")) {
							System.out.println("backBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MainUILeftPanel();
							rightPanel = new MainUIRightPanel();
							setPanelsForMainUI();
							setTitle("MainUI");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals("createBtn")) {
							System.out.println("create");
							
							if(!anyEvent.getName().equals(""))
							{
								int temp=0;
							if(anyEvent.getTypeOfMerchandise().equals("Course")){
								temp=1;
							}else
								if(anyEvent.getTypeOfMerchandise().equals("Miscellaneous")){
									temp=2;
								}else
								{
									temp=3;
								}
							merchandiseController.createMerchandise(anyEvent.getName(), 10, temp, anyEvent.getIngredients(), anyEvent.isVegetarian(), anyEvent.getQuantity(), anyEvent.getAlcoholConcentration(), anyEvent.getTypeOfCourse());
							
							}
							container = getContentPane();
							container.removeAll();
							leftPanel = new MerchandiseMenuLeftPanel();
							rightPanel = new MerchandiseMenuRightPanel();
							MerchandiseController mc = new MerchandiseController();
							ArrayList<Merchandise> m = mc.getAllMerchandise();
							System.out.println(m.size());
							
							((MerchandiseMenuRightPanel)rightPanel).populateMerchandise(m);
							setPanelsForMerchandiseMenu();
							setTitle("Merchandise menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
							
						}

					}
				});

		((MerchandiseMenuRightPanel) rightPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals("delete")) {
							System.out.println("delete");

						}
						if (anyEvent.getButtonTrigered().equals("updateBtn")) {
							System.out.println("update");
							Merchandise m= merchandiseController.findMerchandise(anyEvent.getName());
							int mType=1;
							if(m instanceof Course){
								((Course) m).setTypeOfCourse(anyEvent.getTypeOfCourse());
								((Course) m).setIsVegetarian(anyEvent.isVegetarian());
								((Course) m).setIngredients(anyEvent.getIngredients());					
								mType=1;
							}
							if(m instanceof Miscellaneous){
								((Miscellaneous) m).setMinQuantityInStock(5);
								((Miscellaneous) m).setQuantityInStock(10);	
								mType=2;
							}
							if(m instanceof Drink){
								((Drink) m).setAlcoholConcetration(anyEvent.getAlcoholConcentration());
								((Drink) m).setMinQuantityInStock(5);
								((Drink) m).setQuantityInStock(10);		
								mType=3;
							}
							merchandiseController.updateMerchandise(m, mType);
						}

					}
				});
	}

	public void setPanelsForStaffMenu() {
		((StaffUILeftPanel) leftPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals("backBtn")) {
							System.out.println("backBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MainUILeftPanel();
							rightPanel = new MainUIRightPanel();
							setPanelsForMainUI();
							setTitle("MainUI");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals("create")) {
							System.out.println("create");

						}

					}
				});

		((StaffUIRightPanel) rightPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals("delete")) {
							System.out.println("delete");

						}
						if (anyEvent.getButtonTrigered().equals("update")) {
							System.out.println("update");

						}

					}
				});
	}

	public void setPanelsForTableMenu() {
		((TableUILeftPanel) leftPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals("backBtn")) {
							System.out.println("backBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new TableUILeftPanel();
							rightPanel = new TableUIRightPanel();
							setPanelsForMainUI();
							setTitle("MainUI");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals("create")) {
							System.out.println("create");

						}

					}
				});

		((TableUIRightPanel) rightPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals("delete")) {
							System.out.println("delete");

						}
						if (anyEvent.getButtonTrigered().equals("update")) {
							System.out.println("update");

						}

					}
				});
	}

	public void setPanelForChosenTable(ChoosenTableDialog d) {
		// System.out.println("dispose");
		d.setListenerForEverything(new ListenerForEverything() {

			@Override
			public void AnyEventOcurred(AnyEvent anyEvent)
					throws DatabaseException {
				// System.out.println("dispose");
				if (anyEvent.getButtonTrigered().equals("backBtn")) {
					System.out.println("backBtn");
					d.dispose();
				}
				if (anyEvent.getButtonTrigered().equals("cancelOrder")) {
					int tableNo = anyEvent.getTableNo();
					System.out.println("cancelOrder");
					System.out.println(tableNo);
				}
				if (anyEvent.getButtonTrigered().equals("addMerchandise")) {
					int tableNo = anyEvent.getTableNo();
					System.out.println("addMerchandise");
					System.out.println(tableNo);
				}

			}
		});
	}

	public void setPanelsForMakeOrderBeginning() {
		((MakeOrderUIRightPanel) rightPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals("courseBtn")) {
							System.out.println("courseBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							rightPanel = new CourseUIRightPanel();
							setPanelsForMakeOrderUILeftPanel();
							setPanelsForCourseUIRightPanel();
							setTitle("Course Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();

						}
						if (anyEvent.getButtonTrigered().equals("drinkBtn")) {
							System.out.println("drinkBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							rightPanel = new DrinkUIRightPanel();
							setPanelsForMakeOrderUILeftPanel();
							setDrinkUIRightPanel();
							setTitle("Drink Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();

						}
						if (anyEvent.getButtonTrigered().equals("otherBtn")) {
							System.out.println("otherBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							ArrayList<Merchandise> allMerchandise=merchandiseController.getAllMerchandise();
							ArrayList<Merchandise> allMerchandiseDesert = new ArrayList<>();
							for (Merchandise m: allMerchandise){
								if(m instanceof Miscellaneous){
									allMerchandiseDesert.add(m);
								}
							}
							rightPanel = new OrderEndUIRightPanel(allMerchandiseDesert);
							
							
							//((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandiseDesert);
							// ((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandise);
							setPanelsForMakeOrderUILeftPanel();
							// Here should be listeners for OrderEnd
							setOrderEndUIRightPanel();
							setTitle("Merchandise Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();

						}
						if (anyEvent.getButtonTrigered().equals(
								"confirmOrderBtn")) {
							System.out.println("confirmOrderBtn");
						}

					}
				});
	}

	public void setPanelsForMakeOrderUILeftPanel() {
		((MakeOrderUILeftPanel) leftPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals("backBtn")
								&& rightPanel instanceof MakeOrderUIRightPanel) {
							System.out.println("backBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new WaiterUILeftPanel();
							rightPanel = new WaiterUIRightPanel();
							setPanelsForWaiterUI();
							setTitle("Waiter Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals("backBtn")
								&& rightPanel instanceof CourseUIRightPanel) {
							System.out.println("backBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							rightPanel = new MakeOrderUIRightPanel();
							setPanelsForMakeOrderUILeftPanel();
							setPanelsForMakeOrderBeginning();
							setTitle("Order Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals("backBtn")
								&& rightPanel instanceof OrderEndUIRightPanel) {
							System.out.println("backBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							rightPanel = new MakeOrderUIRightPanel();
							setPanelsForMakeOrderUILeftPanel();
							setPanelsForMakeOrderBeginning();
							setTitle("Order Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals("backBtn")
								&& rightPanel instanceof DrinkUIRightPanel) {
							System.out.println("backBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							rightPanel = new MakeOrderUIRightPanel();
							setPanelsForMakeOrderUILeftPanel();
							setPanelsForMakeOrderBeginning();
							setTitle("Order Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}

					}
				});
	}

	public void setPanelsForCourseUIRightPanel() {
		((CourseUIRightPanel) rightPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered()
								.equals("regular1stBtn")) {
							System.out.println("otherBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							ArrayList<Merchandise> allMerchandise=merchandiseController.getAllMerchandise();
							ArrayList<Merchandise> allMerchandiseDesert = new ArrayList<>();
							for (Merchandise m: allMerchandise){
								if(m instanceof Course && ((Course)m).getTypeOfCourse().equals("1st") && !((Course)m).getIsVegetarian()){
									allMerchandiseDesert.add(m);
								}
							}
							rightPanel = new OrderEndUIRightPanel(allMerchandiseDesert);
							
							
							//((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandiseDesert);
							// ((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandise);
							setPanelsForMakeOrderUILeftPanel();
							// Here should be listeners for OrderEnd
							setOrderEndUIRightPanel();
							setTitle("Merchandise Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals(
								"vegetarian1stBtn")) {
							System.out.println("otherBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							ArrayList<Merchandise> allMerchandise=merchandiseController.getAllMerchandise();
							ArrayList<Merchandise> allMerchandiseDesert = new ArrayList<>();
							for (Merchandise m: allMerchandise){
								if(m instanceof Course && ((Course)m).getTypeOfCourse().equals("1st") && ((Course)m).getIsVegetarian()){
									allMerchandiseDesert.add(m);
								}
							}
							rightPanel = new OrderEndUIRightPanel(allMerchandiseDesert);
							
							
							//((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandiseDesert);
							// ((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandise);
							setPanelsForMakeOrderUILeftPanel();
							// Here should be listeners for OrderEnd
							setOrderEndUIRightPanel();
							setTitle("Merchandise Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered()
								.equals("regular2ndBtn")) {
							System.out.println("otherBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							ArrayList<Merchandise> allMerchandise=merchandiseController.getAllMerchandise();
							ArrayList<Merchandise> allMerchandiseDesert = new ArrayList<>();
							for (Merchandise m: allMerchandise){
								if(m instanceof Course && ((Course)m).getTypeOfCourse().equals("2nd") && !((Course)m).getIsVegetarian()){
									allMerchandiseDesert.add(m);
								}
							}
							rightPanel = new OrderEndUIRightPanel(allMerchandiseDesert);
							
							
							//((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandiseDesert);
							// ((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandise);
							setPanelsForMakeOrderUILeftPanel();
							// Here should be listeners for OrderEnd
							setOrderEndUIRightPanel();
							setTitle("Merchandise Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals(
								"vegetarian2ndBtn")) {
							System.out.println("otherBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							ArrayList<Merchandise> allMerchandise=merchandiseController.getAllMerchandise();
							ArrayList<Merchandise> allMerchandiseDesert = new ArrayList<>();
							for (Merchandise m: allMerchandise){
								if(m instanceof Course && ((Course)m).getTypeOfCourse().equals("1st") && ((Course)m).getIsVegetarian()){
									allMerchandiseDesert.add(m);
								}
							}
							rightPanel = new OrderEndUIRightPanel(allMerchandiseDesert);
							
							
							//((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandiseDesert);
							// ((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandise);
							setPanelsForMakeOrderUILeftPanel();
							// Here should be listeners for OrderEnd
							setOrderEndUIRightPanel();
							setTitle("Merchandise Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals("desertBtn")) {
							System.out.println("otherBtn");
							container = getContentPane();
							container.removeAll();
							merchandiseController = new MerchandiseController();
							leftPanel = new MakeOrderUILeftPanel();
							ArrayList<Merchandise> allMerchandise=merchandiseController.getAllMerchandise();
							ArrayList<Merchandise> allMerchandiseDesert = new ArrayList<>();
							for (Merchandise m: allMerchandise){
								if(m instanceof Course && ((Course)m).getTypeOfCourse().equals("Dessert")){
									allMerchandiseDesert.add(m);
									
								}
							}
							System.out.println("Size: " + allMerchandiseDesert.size());
							rightPanel = new OrderEndUIRightPanel(allMerchandiseDesert);
							
							
							//((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandiseDesert);
							// ((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandise);
							setPanelsForMakeOrderUILeftPanel();
							// Here should be listeners for OrderEnd
							setOrderEndUIRightPanel();
							setTitle("Merchandise Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}

					}
				});
	}

	public void setDrinkUIRightPanel() {
		((DrinkUIRightPanel) rightPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals("alcoholicBtn")) {
							System.out.println("alcoholicBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							ArrayList<Merchandise> allMerchandise=merchandiseController.getAllMerchandise();
							ArrayList<Merchandise> allMerchandiseDesert = new ArrayList<>();
							for (Merchandise m: allMerchandise){
								if(m instanceof Drink && ((Drink)m).getAlcoholConcetration() > 0){
									allMerchandiseDesert.add(m);
								}
							}
							rightPanel = new OrderEndUIRightPanel(allMerchandiseDesert);
							
							
							//((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandiseDesert);
							// ((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandise);
							setPanelsForMakeOrderUILeftPanel();
							// Here should be listeners for OrderEnd
							setOrderEndUIRightPanel();
							setTitle("Merchandise Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}
						if (anyEvent.getButtonTrigered().equals(
								"nonAlcoholicBtn")) {
							System.out.println("nonAlcoholicBtn");
							container = getContentPane();
							container.removeAll();
							leftPanel = new MakeOrderUILeftPanel();
							ArrayList<Merchandise> allMerchandise=merchandiseController.getAllMerchandise();
							ArrayList<Merchandise> allMerchandiseDesert = new ArrayList<>();
							for (Merchandise m: allMerchandise){
								if(m instanceof Drink){
									allMerchandiseDesert.add(m);
								}
							}
							rightPanel = new OrderEndUIRightPanel(allMerchandiseDesert);
							
							
							//((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandiseDesert);
							// ((OrderEndUIRightPanel)rightPanel).setAllMerchandise(allMerchandise);
							setPanelsForMakeOrderUILeftPanel();
							// Here should be listeners for OrderEnd
							setOrderEndUIRightPanel();
							setTitle("Merchandise Menu");
							setPanels(leftPanel, rightPanel);
							container.validate();
							container.repaint();
						}

					}
				});
	}

	public void setOrderEndUIRightPanel() {
		((OrderEndUIRightPanel) rightPanel)
				.setListenerForEverything(new ListenerForEverything() {
					
					@Override
					public void AnyEventOcurred(AnyEvent anyEvent)
							throws DatabaseException {
						String nameOfCourse = anyEvent.getButtonTrigered();
						int dialog = JOptionPane
								.showConfirmDialog(MainUI.this,
										"Are you sure you want to add this Merchandise?");
						
						if (dialog == 0) {
							System.out.println("It means yes!");
							Reservation resTemp = reservationController.findReservationByName(name);
							System.out.println("ResTEmp: "+resTemp.getCustomerName());
							System.out.println("ResTEmp: "+resTemp.getOrder().getOrderId());
							int orderId =resTemp.getOrder().getOrderId();
							Order order = orderController.findOrderById(orderId);
							
							order = resTemp.getOrder();
							System.out.println("OrderID: "+orderId);
							
							orderController.setOrder(order);
							orderController.findMerchandise(nameOfCourse);
							((MakeOrderUILeftPanel)leftPanel).setOrderList(resTemp.getOrder().getOrderLines());
							((MakeOrderUILeftPanel)leftPanel).refresh();
							orderController.addMerchandises(1);
							System.out.println("Order Stuff MainUI: "+order.getId());
							orderController.saveOrder();
							
						} else {
							System.out.println("It means no!");
						}
					}
				});
	}
	

}
