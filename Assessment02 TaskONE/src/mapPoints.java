//Amethyst Mayer
//Student No.: 11519210
//Class: ITC313
//Last Modified: 24/08/2014
//Sam Fletcher
//Assessment Two

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class mapPoints extends JPanel 
{
	
	private ArrayList <filesLocations> mList;
	public int scaling = 1;
	
	public mapPoints(ArrayList <filesLocations> mList)
	{
		this.mList = mList;
	}
	
	public void mapArray(ArrayList <filesLocations> mList)
	{
		this.mList = mList;
	}
	public void setScaleIn() 
	{
		if (scaling < 5)
		{
		scaling++; 
		repaint();
		}
	}
	
	public void setScaleOut()
	{
		if (scaling > 1)
		{
		scaling--;
		repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		//Loops through the mList Array.
		for (int i = 0; i <  mList.size(); i++)
		{
			//Checks if the locations value equals 1, is so draws a RED Oval, where the X axis and Y axis cross.
			if (mList.get(i).getLocation() == 1)
			{
				g.setColor(Color.RED);
				g.fillOval(mList.get(i).getXAxis() * scaling, mList.get(i).getYAxis() * scaling, 4 * scaling, 4 * scaling);
			}
			//Checks if the locations value equals 2, is so draws a BLACK Oval, where the X axis and Y axis cross.
			else if (mList.get(i).getLocation() == 2)
			{
				g.setColor(Color.BLACK);
				g.fillOval(mList.get(i).getXAxis() * scaling, mList.get(i).getYAxis() * scaling, 4 * scaling, 4 * scaling);
			}
			//Checks if the locations value equals 3, is so draws a BLUE Oval, where the X axis and Y axis cross.
			else if (mList.get(i).getLocation() == 3)
			{
				g.setColor(Color.BLUE);
				g.fillOval(mList.get(i).getXAxis() * scaling, mList.get(i).getYAxis() * scaling, 4 * scaling, 4 * scaling);
			}
			//Checks if the locations value equals 4, is so draws a GREEN Oval, where the X axis and Y axis cross.
			else if (mList.get(i).getLocation() == 4)
			{
				g.setColor(Color.GREEN);
				g.fillOval(mList.get(i).getXAxis() * scaling, mList.get(i).getYAxis() * scaling, 4 * scaling, 4 * scaling);
			}
			//Checks if the locations value equals 5, is so draws a YELLOW Oval, where the X axis and Y axis cross.
			else if (mList.get(i).getLocation() == 5)
			{
				g.setColor(Color.YELLOW);
				g.fillOval(mList.get(i).getXAxis() * scaling, mList.get(i).getYAxis() * scaling, 4 * scaling, 4 * scaling);
			}

		}

	}	
}
