//Amethyst Mayer
//Student No.: 11519210
//Class: ITC313
//Last Modified: 24/08/2014
//Sam Fletcher
//Assessment Two

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class mapWindow extends JFrame 
{
	private mapPoints mainPanel = new mapPoints(new ArrayList <filesLocations>());
	//Creates a Button labeled Zoom In.
	private JButton zoomIn = new JButton("Zoom In");
	//Creates a Button labeled Zoom Out.
	private JButton zoomOut = new JButton("Zoom Out");
	//Creating a mapPoints panel.
	public mapWindow(ArrayList <filesLocations> mList)
	{
		@SuppressWarnings("unused")
		//Creates a Panel.
		JPanel buttonsPanel = new JPanel();
		this.add(zoomIn, BorderLayout.WEST);
		zoomIn.addActionListener(new zoomButtonsListener());
		this.add(zoomOut, BorderLayout.EAST);
		zoomOut.addActionListener(new zoomButtonsListener());
		mainPanel.mapArray(mList);
		this.add(mainPanel);
	}
	

	class zoomButtonsListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == zoomIn)
			{
				mainPanel.setScaleIn();
			}
			else
			{
				mainPanel.setScaleOut();
			}
		}
	}
}
