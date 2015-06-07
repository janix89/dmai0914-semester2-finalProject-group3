package guiLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;

import modelLayer.Table;
import controlLayer.ReservationController;
import controlLayer.TableController;
import exceptionsLayer.DatabaseException;

public class MainUI extends JFrame {

	private JPanel leftPanel;
	private JPanel rightPanel;
	private Container container;
	private JMenuBar menuBar;
	private JDialog dialog;
	private ReservationController reservationController;

	public MainUI() {

		super("MainUI");

		setPanelsForMainUI();
		//addMenuBar();
		reservationController = new ReservationController();

		setLayout(new BorderLayout());
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);

		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(900, 600));

	}
	//Added by Janis
	//Might not need it anymore
	/**
	public void addMenuBar() {
		menuBar = new JMenuBar();
		JMenu menuOptions = new JMenu("Options");
		menuBar.add(menuOptions);
		JMenuItem optionsMenuItemPreferences = new JMenuItem("Preferences");
		menuOptions.add(optionsMenuItemPreferences);
		this.setJMenuBar(menuBar);
		dialog = new PreferencesDialog(MainUI.this);

		/**
		 * ((PreferencesDialog) dialog) .setListenerForEverything(new
		 * ListenerForEverything() {
		 * 
		 * @Override public void AnyEventOcurred(AnyEvent anyEvent) { if
		 *           (anyEvent.getButtonTrigered().equals( "savePreferences")) {
		 *           if (leftPanel instanceof WaiterUILeftPanel) {
		 *           ((WaiterUILeftPanel) leftPanel)
		 *           .setTheCorrectTimesInComboBox(); } leftPanel.revalidate();
		 *           leftPanel.repaint(); }
		 * 
		 *           } });
		 
		optionsMenuItemPreferences.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setLocationRelativeTo(MainUI.this);
				dialog.setVisible(true);
			}
		});

	}
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
							System.exit(0);
						}
						if (anyEvent.getButtonTrigered().equals(
								"managerMenuBtn")) {
							setTitle("Manager menu");

						}
						if (anyEvent.getButtonTrigered()
								.equals("waiterMenuBtn")) {
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
							setTitle("Chef menu");
							container = getContentPane();
							container.removeAll();
							leftPanel = new ChefUIRightPanel();
							rightPanel = new ChefUILeftPanel();
							// setPanelsForWaiterUILeftPanel();
							// setTitle("Waiter menu");
							setPanels(leftPanel, rightPanel);

							container.validate();
							container.repaint();

						}
						if (anyEvent.getButtonTrigered().equals("tableMenuBtn")) {
							setTitle("Table menu");
						}
						if (anyEvent.getButtonTrigered().equals(
								"merchandiseMenuBtn")) {
							setTitle("Merchandise menu");

						}
					}
				});
	}

	public void setPanelsForWaiterUI() {
		((WaiterUILeftPanel) leftPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent) throws DatabaseException {
						if (anyEvent.getButtonTrigered().equals(
								"backBtnWaiterUILeftPanel")) {
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

						}
						if (anyEvent.getButtonTrigered().equals(
								"WaiterUILeftPanelSearchBtn")) {

						}
						if (anyEvent.getButtonTrigered().equals(
								"WaiterUILeftPanelmakeReservationBtn")) {
							//Added by Janis
							//Here you get the info from WaiterUILeftPanel
							
							String name = anyEvent.getName();
							String phoneNo = anyEvent.getPhoneNo();
							String dayOfReservation = anyEvent.getDays();
							String monthAndYearOfReservation = anyEvent.getMonthsYear();
							String timeForReservation = anyEvent.getTime();
							int numberOfGuests = anyEvent.getNumberOfSeats();
							
							//Added by Janis
							//This is required to refresh the frame and panels
							//selected tables will become red or yellow after reservation and
							//date checking when it will be implemented
							//Right now only red
							
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
				});
		//Added by Janis
		//The line belove requires ArrayList of all tables in order to display table buttons
		//((WaiterUIRightPanel)rightPanel).setTheArrayListOfAllTables(allTables);

		((WaiterUIRightPanel) rightPanel)
				.setListenerForEverything(new ListenerForEverything() {

					@Override
					public void AnyEventOcurred(AnyEvent anyEvent) throws DatabaseException {
						
						
						if(anyEvent.getName().equals("RedTable")){
							ChoosenTableDialog d = new ChoosenTableDialog(MainUI.this, "Chosen table");
							d.setLocationRelativeTo(MainUI.this);
							d.setTableNoLabel(anyEvent.getButtonTrigered());
							System.out.println(anyEvent.getButtonTrigered());
							d.setVisible(true);
							d.repaint();
							d.revalidate();
							
							
						}
						//Added by Janis
						//This was my code for when table is selected and deselected
						//It works like table added to arraylist and removed
						//It needs some methods in controller
						//If you think it could work ask me for it
						
						/**
						int table = Integer.parseInt(anyEvent
								.getButtonTrigered());
						if (reservationController.getChosenTables().isEmpty() != true) {
							ArrayList<Table> tbls = reservationController.getChosenTables();
							for (Table t : tbls) {
								if(reservationController.checkIfTableHasBeenAlreadyAdded(table) == true) {
									tbls.remove(t);
								} else {
									Table tab = reservationController.checkTables(t.getTableNo());
									if(tab != null){
									tbls.add(tab);
									}
								}
							}
						}
						*/
					}
				});
	}
}
