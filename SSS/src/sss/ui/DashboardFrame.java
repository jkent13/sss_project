package sss.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DashboardFrame extends JFrame {
	
	@SuppressWarnings("unused")
	public DashboardFrame()
	{

	//-------------------Frame Details--------------------

			setTitle("Dashboard");
			setExtendedState(Frame.MAXIMIZED_BOTH);
			setLocationRelativeTo(null);

	//-------------------Full Screen Panel--------------------
	
			JPanel fullScreenPanel = new JPanel();
			fullScreenPanel.setBorder(new EmptyBorder(10,10,10,10));
			fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
			add(fullScreenPanel);
		
	//-------------------Load Image Resources--------------------
			
			URL dashboardLogoUrl = Main.class.getResource("/DashboardLogo.png");
			
	//--------------------Section Panels-----------------------

			JPanel leftPanel = new JPanel();
			TitledBorder leftPanelTitle = new TitledBorder("Left Panel:");
//			leftPanel.setBorder(leftPanelTitle);
			leftPanel.setLayout(new BorderLayout());
//			leftPanel.setLayout(new GridLayout(1,1,10,10));
			fullScreenPanel.add(leftPanel);

			JLabel memo = new JLabel("Live Summary of Today's Sales");
			leftPanel.add(memo, BorderLayout.NORTH);
			Font myFont = new Font("SansSerif",Font.BOLD, 28);
			memo.setFont(myFont);
			JButton lineGraph = new JButton("Line Graph Goes Here");
			leftPanel.add(lineGraph, BorderLayout.CENTER);
			
			JPanel middlePanel = new JPanel();
			TitledBorder middlePanelTitle = new TitledBorder("Middle Panel:");
//			middlePanel.setBorder(middlePanelTitle);
			middlePanel.setLayout(new GridLayout(2,1,10,10));
			fullScreenPanel.add(middlePanel);
			
			JPanel topMiddlePanel = new JPanel();
			topMiddlePanel.setLayout(new GridLayout(1,1,10,10));
			JLabel dashboardTextLabel = new JLabel(new ImageIcon(dashboardLogoUrl));
			topMiddlePanel.add(dashboardTextLabel);
			middlePanel.add(topMiddlePanel);
			
			JPanel bottomMiddlePanel = new JPanel();
			bottomMiddlePanel.setLayout(new BorderLayout());
			middlePanel.add(bottomMiddlePanel);
			JLabel watchListLabel = new JLabel("Item Watch List:");
			watchListLabel.setFont(myFont);
			bottomMiddlePanel.add(watchListLabel, BorderLayout.NORTH);
			
			JPanel barGraphPanel = new JPanel();
			barGraphPanel.setLayout(new GridLayout(1,3,10,10));
			JButton barGraph1 = new JButton("1st Go Here");
			barGraphPanel.add(barGraph1);
			JButton barGraph2 = new JButton("2nd Go Here");
			barGraphPanel.add(barGraph2);
			JButton barGraph3 = new JButton("3rd Go Here");
			barGraphPanel.add(barGraph3);
			bottomMiddlePanel.add(barGraphPanel);
			
			
			
			JPanel rightPanel = new JPanel();
			TitledBorder rightPanelTitle = new TitledBorder("Right Panel:");
//			rightPanel.setBorder(rightPanelTitle);
			rightPanel.setLayout(new GridLayout(1,1,10,10));
			fullScreenPanel.add(rightPanel);

			String[] colNames = {"Type","Time","Amount","Name"};
			Object[][] data = {
					{"Sale","4256985216","Cat","Pet"},
					{"Refund","4256985216","Cat","Pet"},
					{"Discount","4256985216","Cat","Pet"},
					{"Refund","4256985216","Cat","Pet"},
					{"Discount","4256985216","Cat","Pet"},
					{"Dsicount","4256985216","Cat","Pet"},
			};

			JTable lookUpTable = new JTable(data, colNames);
			JScrollPane scrlPane = new JScrollPane(lookUpTable);
			rightPanel.add(scrlPane);
			
			
	//--------------------Inside Left Panel-----------------------

			
			
			
			
			
			
			setVisible(true);
	}
}