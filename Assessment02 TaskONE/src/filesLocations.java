//Amethyst Mayer
//Student No.: 11519210
//Class: ITC313
//Last Modified: 24/08/2014
//Sam Fletcher
//Assessment Two

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;


public class filesLocations{

	private int location;
	private int xAxis;
	private int yAxis;
	private int scalingSize;

	
	public static void main(String[] args) throws Exception{        
		
		
		try {
			//import textfile.
			File f = new File("Ass1_Task1_POIs.txt");
			@SuppressWarnings("resource")
			Scanner mapList = new Scanner(f);

			//Creates a Array List.
			ArrayList <filesLocations> mList = new ArrayList<filesLocations>();

			//Checks if the file has a next file.
			while(mapList.hasNext()){
				@SuppressWarnings("resource")
				Scanner line = new Scanner (mapList.nextLine());
				int location = line.nextInt();
				int x = line.nextInt();
				int y = line.nextInt();
				filesLocations type = new filesLocations(location, x, y, 0);
				mList.add(type);
			}

			//Printing the details in toString.
			for(filesLocations dotPoints: mList){
				System.out.println(dotPoints.toString());
			}

			//Creating a mapWindow Window.
			mapWindow myFrame = new mapWindow(mList);
			myFrame.setTitle("Assessment Two: Task One");
			myFrame.setSize(440, 300);
			myFrame.setLocationRelativeTo(null);
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			myFrame.setVisible(true);
		} 

		catch (IOException e) 
		{
			// Catch any problems.
			System.out.println(e);
		}
	} //end of main
	

	//
	//A constructor setting the location, x axis, y axis and scalingSize.
	public filesLocations(int location, int xAxis, int yAxis, int scalingSize)
	{
		this.location = location;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.scalingSize = scalingSize;
	}

	
	//A getter that retrieves location.
	public int getLocation() 
	{
		return location;
	}

	//A setter that sets location.
	public void setLocation(int location) 
	{
		this.location = location;
	}

	//A getter that retrieves xAxis.
	public int getXAxis() 
	{
		return xAxis;
	}

	//A setter that sets xAxis.
	public void setXAxis(int xAxis) 
	{
		this.xAxis = xAxis;
	}

	//A getter that retrieves yAxis.
	public int getYAxis() 
	{
		return yAxis;
	}

	//A setter that sets setYAxis.
	public void setYAxis(int yAxis) 
	{
		this.yAxis = yAxis;
	}

	//A getter that retrieves scalingSize.
	public int getScalingSize()
	{
		return scalingSize;
	}
	
	//A setter that sets scalingSize.
	public void setScalingSize(int scalingSize)
	{
		this.scalingSize = scalingSize;
	}
	
	//Returns a String description of the File.
	public String toString()
	{
		return "Building Type: " + this.location + ", at the X axis of: " + this.xAxis + ", and the Y axis of: " + this.yAxis;
	}
	
}//end of class.