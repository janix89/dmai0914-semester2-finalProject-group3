import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainUI extends JFrame {
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	private Container container;
	private JMenuBar menuBar;
	private JDialog dialog;

	
	public MainUI(){
		
		super("MainUI");
		
		
		setPanelsForMainUI();
		addMenuBar();
		
		
		setLayout(new BorderLayout());
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);
		
		
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(900, 600));

	}
	
	public void addMenuBar(){
		menuBar = new JMenuBar();
		JMenu menuOptions = new JMenu("Options");
		menuBar.add(menuOptions);
		JMenuItem optionsMenuItemPreferences = new JMenuItem("Preferences");
		menuOptions.add(optionsMenuItemPreferences);
		this.setJMenuBar(menuBar);
		dialog = new PreferencesDialog(MainUI.this);
		
		((PreferencesDialog) dialog).setListenerForEverything(new ListenerForEverything() {
			@Override
			public void AnyEventOcurred(AnyEvent anyEvent) {
				if(anyEvent.getButtonTrigered().equals("savePreferences")){
					if(leftPanel instanceof WaiterUILeftPanel){
						((WaiterUILeftPanel)leftPanel).setTheCorrectTimesInComboBox();
					}
					leftPanel.revalidate();
					leftPanel.repaint();
				}
				
			}
		});
		optionsMenuItemPreferences.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setLocationRelativeTo(MainUI.this);
				dialog.setVisible(true);
			}
		});
		
		
	}
	
	public void setTitle(String name){
		super.setTitle(name);
		
	}
	
	public void setPanels(Component left, Component right){
		remove(leftPanel);
		remove(rightPanel);
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.CENTER);

	}

	public void setPanelsForMainUI(){
		leftPanel = new MainUILeftPanel();
		rightPanel = new MainUIRightPanel();
		
		
		((MainUILeftPanel) leftPanel).setListenerForEverything(new ListenerForEverything() {
			
			@Override
			public void AnyEventOcurred(AnyEvent anyEvent) {
				
				if(anyEvent.getButtonTrigered().equals("exitBtn")){
					System.exit(0);
				}
				if(anyEvent.getButtonTrigered().equals("managerMenuBtn")){	
					setTitle("Manager menu");
					System.out.println("Manager menu");
					
				}
				if(anyEvent.getButtonTrigered().equals("waiterMenuBtn")){
					container =  getContentPane();
					container.removeAll();
					leftPanel = new WaiterUILeftPanel();
					rightPanel = new ManagerUIRightPanel();
					setPanelsForWaiterUILeftPanel();
					setTitle("Waiter menu");
					setPanels(leftPanel, rightPanel);
					System.out.println("Waiter menu");
					
					container.validate();
					container.repaint();
					
					
				}
				if(anyEvent.getButtonTrigered().equals("chefMenuBtn")){
					setTitle("Chef menu");
					System.out.println("Chef menu");
				}
				if(anyEvent.getButtonTrigered().equals("tableMenuBtn")){
					setTitle("Table menu");
					System.out.println("Table menu");
				}
				if(anyEvent.getButtonTrigered().equals("merchandiseMenuBtn")){
					setTitle("Merchandise menu");
					System.out.println("Merchandise menu");
				}
			}
		});
	}
	
	public void setPanelsForWaiterUILeftPanel(){
		((WaiterUILeftPanel) leftPanel).setListenerForEverything(new ListenerForEverything() {
			
			@Override
			public void AnyEventOcurred(AnyEvent anyEvent) {
				if(anyEvent.getButtonTrigered().equals("backBtnWaiterUILeftPanel")){
					System.out.println("Back");
					container =  getContentPane();
					container.removeAll();
					leftPanel = new MainUILeftPanel();
					rightPanel = new MainUIRightPanel();
					setPanelsForMainUI();
					setTitle("MainUI");
					System.out.println("MainUI");
					setPanels(leftPanel, rightPanel);
					container.validate();
					container.repaint();
				}
				
			}
		});
	}
}
