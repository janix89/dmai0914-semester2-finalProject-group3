import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class TestFrame extends JFrame {
	private JPanel panel;
	
	public TestFrame(){
		
		panel = new SkeletonForPanels();
		
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);

		setSize(450, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(450, 600));
	}
	
	
	
}
