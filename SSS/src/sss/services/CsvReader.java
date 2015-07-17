package sss.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CsvReader {
	
	private boolean validFile = false;
	
	private static ArrayList<String> productCodes = new ArrayList<String>();
	private static ArrayList<Integer> quantities = new ArrayList<Integer>();
	private static ArrayList<BigDecimal> costPrices = new ArrayList<BigDecimal>();
	private static ArrayList<BigDecimal> prices = new ArrayList<BigDecimal>();
	
	public static void read(int format, File file) {
		try {
			String[] tokens;
			String nextLine;
			Scanner fileScanner = new Scanner(file);
			
			switch(format) {
			case 1:
				while(fileScanner.hasNext()) {
					nextLine = fileScanner.nextLine();
					tokens = nextLine.split(",");
					if(tokens.length == 2) {
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Error: The file is not in the correct format", "Invalid Format", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
			
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: The file does not exist", "File Not Found", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
}
