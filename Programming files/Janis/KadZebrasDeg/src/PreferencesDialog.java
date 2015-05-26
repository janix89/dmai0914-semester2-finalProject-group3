import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PreferencesDialog extends JDialog {

	
	private JLabel timeLabelOpens;
	private JLabel timeLabelClose;
	private JLabel dotsLabelOpens;
	private JLabel dotsLabelClose;
	private JComboBox<String> openingHourComboBox;
	private JComboBox<String> closingHourComboBox;
	private JComboBox<String> openingMinutesComboBox;
	private JComboBox<String> closingMinutesComboBox;
	private JButton saveButton;
	private JButton cancelButton;
	private ListenerForEverything listenerForEverything;
	
	
	public PreferencesDialog(JFrame parent){
		
		timeLabelOpens = new JLabel("Enter opening time:");
		timeLabelClose = new JLabel("Enter closing time:");
		dotsLabelOpens = new JLabel(":");
		dotsLabelClose = new JLabel(":");
		openingHourComboBox = new JComboBox<String>();
		closingHourComboBox = new JComboBox<String>();
		openingMinutesComboBox = new JComboBox<String>();
		closingMinutesComboBox = new JComboBox<String>();
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		saveButton.setPreferredSize(new Dimension(100, 25));
		cancelButton.setPreferredSize(new Dimension(100, 25));
		
		setModelForTime(openingHourComboBox, openingMinutesComboBox);
		setModelForTime(closingHourComboBox, closingMinutesComboBox);
		setComboBoxTimesFromFile();
		
		setSize(300, 200);
		setResizable(false);
		setAlwaysOnTop(true);
		setModal(true);
		setTitle("Preferences");
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "savePreferences");
				String fileName = "Times.txt";
				
				try {
					
					String selectedOpeningHour = openingHourComboBox.getSelectedItem().toString();
					String selectedOpeningMinutes = openingMinutesComboBox.getSelectedItem().toString();
					String selectedClosingHour = closingHourComboBox.getSelectedItem().toString();
					String selectedClosingMinutes = closingMinutesComboBox.getSelectedItem().toString();
					String bytes = selectedOpeningHour + "-" + selectedOpeningMinutes +  "-" + 
							selectedClosingHour + "-" + selectedClosingMinutes + "-end";
					byte[] buffer = bytes.getBytes();
					FileOutputStream outputStream = new FileOutputStream(fileName);
					outputStream.write(buffer);
					outputStream.close();
					System.out.println("Wrote " + buffer.length + " bytes");
					if(listenerForEverything != null){
						listenerForEverything.AnyEventOcurred(anyEvent);
					}
					
				}
		        catch(IOException ex) {
		            System.out.println(
		                "Error writing file '"
		                + fileName + "'");
		            
				}
				dispose();
				
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//First row
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 30, 0, 0);
		add(timeLabelOpens, gc);
		
		//////////////////
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 1;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(openingHourComboBox, gc);
		
		/////////////////
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 2;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dotsLabelOpens, gc);
		
		//////////////////
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 3;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 20);
		add(openingMinutesComboBox, gc);
		
		//Second row
		gc.weightx = 1;
		gc.weighty = 0.5;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 30, 20, 0);
		add(timeLabelClose, gc);
		
		//////////////////
		
		gc.weightx = 1;
		gc.weighty = 0.5;
		
		gc.gridwidth = 1;
		
		gc.gridx = 1;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 20, 0);
		add(closingHourComboBox, gc);
		
		/////////////////
		
		gc.weightx = 1;
		gc.weighty = 0.5;
		
		gc.gridwidth = 1;
		
		gc.gridx = 2;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 20, 0);
		add(dotsLabelClose, gc);
		
		//////////////////
		
		gc.weightx = 1;
		gc.weighty = 0.5;
		
		gc.gridwidth = 1;
		
		gc.gridx = 3;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 20, 20);
		add(closingMinutesComboBox, gc);
		
		/////////////////
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 4;
		
		gc.gridx = 0;
		gc.gridy = 2;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 30, 0, 0);
		add(saveButton, gc);
		
		/////////////////
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 4;
		
		gc.gridx = 1;
		gc.gridy = 2;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 30);
		add(cancelButton, gc);
	}
	
	public void setModelForTime(JComboBox comboBoxHour, JComboBox comboBoxMinutes){
		DefaultComboBoxModel hour = new DefaultComboBoxModel();
		DefaultComboBoxModel minutes = new DefaultComboBoxModel();
		for(int x = 0; x <= 59; x++){
			if(x < 10){
			minutes.addElement("0" + x);
			}
			else{
				minutes.addElement(x);
			}
		}
		for(int y = 0; y <= 23; y++){
			if(y < 10){
				hour.addElement("0" + y);
			}
			else{
				hour.addElement(y);
			}
		}
		comboBoxHour.setModel(hour);
		comboBoxMinutes.setModel(minutes);
	}
	
	
	public void setComboBoxTimesFromFile(){

		String fileName = "Times.txt";
		String b;
		try{
			FileReader f=new FileReader("Times.txt");
			BufferedReader br = new BufferedReader(f);
			b=br.readLine();
			br.close();
			//byte[] buffer = new byte[1000];
			 //FileInputStream inputStream = new FileInputStream(fileName);
			 //int total = 0;
	         //int nRead = 0;
	        // while((nRead = inputStream.read(buffer)) != -1) {
	        	// System.out.println(new String(buffer));
	        	// total += nRead;
	        // }
	         String[] parts = b.split("-");
	         String openH = parts[0];
	         String openM = parts[1];
	         String closeH = parts[2];
	         String closeM = parts[3];
	         String end = parts[4];
	         int intOpenH = Integer.parseInt(removeZeros(openH));
	         int intOpenM = Integer.parseInt(removeZeros(openM));
	         int intCloseH = Integer.parseInt(removeZeros(closeH));
	         int intCloseM = Integer.parseInt(removeZeros(closeM));
	        	 
	         
	         System.out.println(intOpenH + ":" + intOpenM + "asdjbjksbdf" + intCloseH + ":" + intCloseM + "knslvdn");
	         
	         
	         
	        // inputStream.close();
	         openingHourComboBox.setSelectedIndex(intOpenH);
        	 openingMinutesComboBox.setSelectedIndex(intOpenM);
        	 closingHourComboBox.setSelectedIndex(intCloseH);
        	 closingMinutesComboBox.setSelectedIndex(intCloseM);
        	 
        	 
	         //System.out.println("Read " + total + " bytes");
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");  
		}
	}
	
	public String removeZeros(String number){
		String numberConverted;
		if(number.substring(0, 0).equals("0")){
			numberConverted = number.substring(1, 1);
		}
		else{
			numberConverted = number;
		}
		return numberConverted;
	}
	
	public void setListenerForEverything(ListenerForEverything listener){
		this.listenerForEverything = listener;
	}
}
