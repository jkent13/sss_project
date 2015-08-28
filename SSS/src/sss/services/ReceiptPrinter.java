/* ReceiptPrinter Class
 * 
 * Responsible for drawing a FormattedSale onto a page that can be printed
 * Also creates print jobs to send to standard A4 printers and is able to print to text file 
 * 
 * Original Author: Josh Kent
 */
package sss.services;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import sss.domain.FormattedSale;

public class ReceiptPrinter implements Printable {

	// ==========================================================================
	// Variables
	// ==========================================================================



	private boolean isBuilt = false;			// ReceiptPrinter needs to build a receipt before it can print
	// Set to true in constructor

	private PrintWriter fileWriter;				// Enables a receipt to be written to text file
	private File textFile;								// File object for fileWriter

	private String[] receiptHeader;				// Header of receipt, first to be printed
	private String[] saleHeader;					// Header of sale, second to be printed
	private String[] saleDetails;					// Details of sale (line items), third to be printed
	private String[] saleFooter;					// Bottom of sale (totals), fourth to be printed
	private String[] receiptFooter;				// Bottom of receipt, last to be printed

	// Fixed value used to separate the sale details from the sale footer
	private final String[] breakLine = {" ",
			"______________________________________________", // 46 underscores
	" "};



	// ==========================================================================
	// Constructor
	// ==========================================================================



	/**
	 * Builds a ReceiptPrinter from a FormattedSale
	 * @param fs a FormattedSale to be printed
	 */
	public ReceiptPrinter(FormattedSale fs) {
		this.receiptHeader = fs.getReceiptHeader();
		this.saleHeader = fs.getSaleHeader();
		this.saleDetails = fs.getSaleDetails();
		this.saleFooter = fs.getSaleFooter();
		this.receiptFooter = fs.getReceiptFooter();
		isBuilt = true;
	}



	// ==========================================================================
	// Core Methods
	// ==========================================================================



	/**
	 * Uses a PrintWriter to create a text file version of the receipt
	 */
	public void printToFile() {
		if(isBuilt) {
			try {
				String[] saleHeaderLine = saleHeader[0].split(": ");
				// Names the text file after the receipt no e.g. Receipt 165210.txt
				textFile = new File("Receipt " + saleHeaderLine[1] + ".txt"); 
				fileWriter = new PrintWriter(textFile);

				// Write receipt header to file
				for(String rh: receiptHeader) {
					fileWriter.println(rh);
				}
				fileWriter.println(); // Blank line

				// Write sale header to file
				for(String sh: saleHeader) {
					fileWriter.println(sh);
				}
				fileWriter.println(); // Blank line

				// Write sale details to file (requires formatting using \t tabs)
				for(int i = 0; i < saleDetails.length; i++) {
					if((i+1) % 4 == 1)
						fileWriter.println(saleDetails[i]);
					else if ((i+1) % 4 == 2)
						fileWriter.print(saleDetails[i]);
					else if ((i+1) % 4 == 3)
						fileWriter.print("\t\t\t" + saleDetails[i]);
					else {
						fileWriter.print("\t\t" + saleDetails[i]);
						fileWriter.println(); // At the end of every line item, add two new lines
						fileWriter.println();
					}
				}
				fileWriter.println(); // Blank line

				// Write sale footer to file (requires formatting using \t tabs)
				for(int i = 0; i < saleFooter.length; i++) {
					if((i+1) % 2 == 1)
						fileWriter.print(saleFooter[i]);
					else if (i == 3) {
						fileWriter.print("\t\t\t" + saleFooter[i]);
						fileWriter.println();
					}
					else if (i == 5){
						fileWriter.print("\t\t\t\t" + saleFooter[i]);
						fileWriter.println();
					}
					else {
						fileWriter.print(" \t\t" + saleFooter[i]);
						fileWriter.println();
					}
				}
				fileWriter.println(); // Blank line

				// Write receipt footer to file
				for(String rf: receiptFooter) {
					fileWriter.println(rf);
				}

				// Close fileWriter
				fileWriter.close();
				JOptionPane.showMessageDialog(null, "Receipt saved to text file", "Save Successful", JOptionPane.INFORMATION_MESSAGE);

			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error: Write to text file failed!", "File Not Found", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}



	/**
	 * Print method implemented under the Printable interface. Draws the receipt onto a virtual page
	 */
	public int print(Graphics g, PageFormat pf, int page) throws
	PrinterException {
		if(!isBuilt) {
			return NO_SUCH_PAGE;
		}
		if (page > 0) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY()); 

		// Draw simple line border
		g2d.drawLine(5, 5, (int)pf.getImageableWidth() - 2, 5);
		g2d.drawLine(5, 5, 5, (int)pf.getImageableHeight() - 2);
		g2d.drawLine((int)pf.getImageableWidth() - 2, 5, (int)pf.getImageableWidth() - 2
				, (int)pf.getImageableHeight() - 2);
		g2d.drawLine(5, (int)pf.getImageableHeight() - 2, (int)pf.getImageableWidth() - 2, (int)pf.getImageableHeight() - 2);

		FontMetrics fm = g2d.getFontMetrics();
		int x = 25; // A good x value to start printing (25 px in from the side of the page)
		int y = 32; // a good y value to start printing (32 px down from the top of the page)

		// PRINTING RECEIPT HEADER
		for (int i = 0; i < receiptHeader.length; i++) {
			java.awt.geom.Rectangle2D r = fm.getStringBounds(receiptHeader[i], g2d);
			g.drawString(receiptHeader[i], ((int)pf.getWidth() - (int)r.getWidth()) - 45, y);

			if (i == receiptHeader.length - 1) {
				y += 32; // Move two lines worth if the last line of the receipt header was printed
			}
			else {
				y += 16; // Move one line down
			}
		}

		// PRINTING SALE HEADER
		for (int i = 0; i < saleHeader.length; i++) {
			g.drawString(saleHeader[i], x, y);
			if (i == saleHeader.length - 1) {
				y += 32; // Move two lines worth if the last line of the sale header was printed
			}
			else {
				y += 16; // Move one line down
			}
		}

		// PRINTING SALE DETAILS
		for (int i = 0; i < saleDetails.length; i++) {
			if ((i+1) % 4 == 1) {
				g.drawString(saleDetails[i], x, y);
				y += 16; // Move one line down
			}
			else if ((i+1) % 4 == 2) {
				g.drawString(saleDetails[i], x + 15, y);
				y += 16; // Move one line down
			}
			else if ((i+1) % 4 == 3){
				java.awt.geom.Rectangle2D r = fm.getStringBounds(saleDetails[i], g2d);
				g.drawString(saleDetails[i], ((int)pf.getWidth() - (int)r.getWidth()) - 275, y - 16);
			}
			else {
				java.awt.geom.Rectangle2D r = fm.getStringBounds(saleDetails[i], g2d);
				g.drawString(saleDetails[i], ((int)pf.getWidth() - (int)r.getWidth()) - 375, y - 16);
				y += 4; // Add an extra 4 px between separate line items
			}
		}

		// PRINTING BREAKLINE
		for (int i = 0; i < breakLine.length; i++) {
			g.drawString(breakLine[i], x, y);
			y += 16; // Move one line down
		}

		// PRINTING SALE FOOTER
		for (int i = 0; i < saleFooter.length; i++) {
			if((i+1) % 2 == 1) {
				g.drawString(saleFooter[i], x, y);
				y += 16; // Move one line down
			}
			else {
				java.awt.geom.Rectangle2D r = fm.getStringBounds(saleFooter[i], g2d);
				g.drawString(saleFooter[i], ((int)pf.getWidth() - (int)r.getWidth()) - 275, y - 16);
			}
		}

		// PRINTING RECEIPT FOOTER
		for (int i = 0; i < receiptFooter.length; i++) {
			if (i == 0) {
				java.awt.geom.Rectangle2D r = fm.getStringBounds(receiptFooter[i], g2d);
				y = ((int)pf.getHeight() - (int)r.getHeight()) - 48; 
				g.drawString(receiptFooter[i], x, y);
				y += 16; // Move one line down
			}
			else {
				g.drawString(receiptFooter[i], x, y);
			}
		}

		return PAGE_EXISTS;
	}



	/**
	 * Main printing method that presents the user with a printer dialog box. If the user select OK to print, the 
	 * print method is called. If the user cancels the dialog, the printToFile method is called
	 */
	public void printReceipt() {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean sendToPrinter = job.printDialog();
		if (sendToPrinter) {
			try {
				job.print();
			} catch (PrinterException ex) {
				JOptionPane.showMessageDialog(null, "Error: There was a problem with printing the receipt", "Print Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			printToFile();
		}
	}

}