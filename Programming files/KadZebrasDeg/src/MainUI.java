import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainUI extends JFrame {
	
	private MainUILeftPanel mainUILeftPanel;
	private MainUIRightPanel mainUIRightPanel;
	private ManagerUILeftPanel managerUILeftPanel;
	private ManagerUIRightPanel managerUIRightPanel;
	private WaiterUILeftPanel waiterUILeftPanel;
	private WaiterUIRightPanel waiterUIRightPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private Container container;

	
	public MainUI(){
		
		super("MainUI");
		
		
		
		mainUILeftPanel = new MainUILeftPanel();
		mainUIRightPanel = new MainUIRightPanel();
		leftPanel = mainUILeftPanel;
		rightPanel = mainUIRightPanel;
		
		setListenersForMainUI();
		
		
		setLayout(new BorderLayout());
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);
		
		
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(900, 600));

	}
	
	public void setTitle(String name){
		//super.removeAll();
		super.setTitle(name);
		
	}
	public void setPanels(Component left, Component right){
		remove(leftPanel);
		remove(rightPanel);
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.CENTER);

	}

	public void setListenersForManagerUI(){
		managerUIRightPanel.setListenerForEverything(new ListenerForEverything() {
			
			@Override
			public void AnyEventOcurred(AnyEvent anyEvent) {
				
				if(anyEvent.getButtonTrigered().equals("exitBtn")){
					System.exit(0);
				}
				if(anyEvent.getButtonTrigered().equals("managerMenuBtn")){	
					setTitle("Main UI");
					System.out.println("Manager menu");
				}
				if(anyEvent.getButtonTrigered().equals("waiterMenuBtn")){
					container =  getContentPane();
					container.removeAll();
					mainUILeftPanel = new MainUILeftPanel();
					mainUIRightPanel = new MainUIRightPanel();
					leftPanel = mainUILeftPanel;
					rightPanel = mainUIRightPanel;
					setListenersForMainUI();
					setTitle("Waiter menu");
					System.out.println("Waiter menu");
					setPanels(leftPanel, rightPanel);
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
	
	public void setListenersForMainUI(){
mainUILeftPanel.setListenerForEverything(new ListenerForEverything() {
			
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
					//setAllComponents();
					waiterUILeftPanel = new WaiterUILeftPanel();
					managerUIRightPanel = new ManagerUIRightPanel();
					leftPanel = waiterUILeftPanel;
					rightPanel = managerUIRightPanel;
					//setListenersForManagerUI();
					setListenersForWaiterUILeftPanel();
					setTitle("Waiter menu");
					setPanels(leftPanel, rightPanel);
					//setPanels(managerUILeftPanel, managerUIRightPanel);
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
	
	public void setListenersForWaiterUILeftPanel(){
		waiterUILeftPanel.setListenerForEverything(new ListenerForEverything() {
			
			@Override
			public void AnyEventOcurred(AnyEvent anyEvent) {
				if(anyEvent.getButtonTrigered().equals("backBtnWaiterUILeftPanel")){
					System.out.println("Back");
					container =  getContentPane();
					container.removeAll();
					mainUILeftPanel = new MainUILeftPanel();
					mainUIRightPanel = new MainUIRightPanel();
					leftPanel = mainUILeftPanel;
					rightPanel = mainUIRightPanel;
					setListenersForMainUI();
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
