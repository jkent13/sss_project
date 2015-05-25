//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class testGUI {

	public static void main(String[] args) {
		JFrame myFrame = new JFrame();
		myFrame.setTitle("Testing Out Swing");
		myFrame.setSize(600,300);
//		myFrame.setLayout(new FlowLayout());
		myFrame.setLayout(new GridLayout(2,1,20,10));
//		myFrame.setLayout(new BorderLayout());
		//Parameters: numRows, numColumns, Hgap, Vgap
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
//		myFrame.setVisible(true);
		
		
		//MainTOP Panel
		JPanel mainTopPanel = new JPanel();
//		TitledBorder mainTopTitle = new TitledBorder("Main Top Panel:");
//		mainTopPanel.setBorder(mainTopTitle);
		mainTopPanel.setLayout(new GridLayout(1,2,10,10));
		myFrame.add(mainTopPanel);
		
		JPanel mainBottomPanel = new JPanel();
//		TitledBorder bottomTopTitle = new TitledBorder("Main Bottom Panel:");
//		mainBottomPanel.setBorder(bottomTopTitle);
		mainBottomPanel.setLayout(new GridLayout(1,2,10,10));
		myFrame.add(mainBottomPanel);
		
		JPanel leftPanel = new JPanel();
		TitledBorder leftPanelTitle = new TitledBorder("Left Panel:");
		leftPanel.setBorder(leftPanelTitle);
		JButton leftPanelButton = new JButton("Left Button");
		
		mainTopPanel.add(leftPanel);
		leftPanel.add(leftPanelButton);
		
		JPanel rightPanel = new JPanel();
		TitledBorder rightPanelTitle = new TitledBorder("Right Panel:");
		rightPanel.setBorder(rightPanelTitle);
		rightPanel.setLayout(new GridLayout(3,4,10,10));
		JButton rightPanelButton = new JButton("Right Button");
		
		mainTopPanel.add(rightPanel);
		rightPanel.add(rightPanelButton);
		
		JButton Button0 = new JButton("0");
		JButton Button1 = new JButton("1");
		JButton Button2 = new JButton("2");
		JButton Button3 = new JButton("3");
		JButton Button4 = new JButton("4");
		JButton Button5 = new JButton("5");
		JButton Button6 = new JButton("6");
		JButton Button7 = new JButton("7");
		JButton Button8 = new JButton("8");
		JButton Button9 = new JButton("9");
		
		rightPanel.add(Button0);
		rightPanel.add(Button1);
		rightPanel.add(Button2);
		rightPanel.add(Button3);
		rightPanel.add(Button4);
		rightPanel.add(Button5);
		rightPanel.add(Button6);
		rightPanel.add(Button7);
		rightPanel.add(Button8);
		rightPanel.add(Button9);
		
		JPanel bottomPanel = new JPanel();
		TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
		bottomPanel.setBorder(bottomPanelTitle);
		JButton bottomPanelButton = new JButton("Bottom Button");
		
		mainBottomPanel.add(bottomPanel);
		bottomPanel.add(bottomPanelButton);
		

		myFrame.setVisible(true);

	}

}
