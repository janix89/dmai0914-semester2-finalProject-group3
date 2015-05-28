import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class LaunchingClass {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try{
					new TestFrame();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
}
