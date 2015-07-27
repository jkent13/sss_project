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
	private boolean isBuilt = false;
	
	private PrintWriter fileWriter;
	private File textFile;
	
	private String[] receiptHeader;
	private String[] saleHeader;
	private String[] saleDetails;
	private String[] saleFooter;
	private String[] receiptFooter;
	
	private final String[] breakLine = {" ",
									"______________________________________________", // 46 underscores
									" "};

	public ReceiptPrinter(FormattedSale fs) {
		this.receiptHeader = fs.getReceiptHeader();
		this.saleHeader = fs.getSaleHeader();
		this.saleDetails = fs.getSaleDetails();
		this.saleFooter = fs.getSaleFooter();
		this.receiptFooter = fs.getReceiptFooter();
		isBuilt = true;
	}
	
	public void printToFile() {
		if(isBuilt) {
			try {
				String[] saleHeaderLine = saleHeader[0].split(": ");
				textFile = new File("Receipt " + saleHeaderLine[1] + ".txt");
				fileWriter = new PrintWriter(textFile);
				
				for(String rh: receiptHeader) {
					fileWriter.println(rh);
				}
				fileWriter.println();
				
				for(String sh: saleHeader) {
					fileWriter.println(sh);
				}
				fileWriter.println();
				
				for(int i = 0; i < saleDetails.length; i++) {
					if((i+1) % 4 == 1)
						fileWriter.println(saleDetails[i]);
					else if ((i+1) % 4 == 2)
						fileWriter.print(saleDetails[i]);
					else if ((i+1) % 4 == 3)
						fileWriter.print("\t\t\t" + saleDetails[i]);
					else {
						fileWriter.print("\t\t" + saleDetails[i]);
						fileWriter.println();
						fileWriter.println();
					}
				}
				fileWriter.println();
				
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
				fileWriter.println();
				
				for(String rf: receiptFooter) {
					fileWriter.println(rf);
				}
				
				fileWriter.close();
				JOptionPane.showMessageDialog(null, "Receipt saved to text file", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error: Write to text file failed!", "File Not Found", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
    public int print(Graphics g, PageFormat pf, int page) throws
                                                        PrinterException {
    	if(!isBuilt) {
    		return NO_SUCH_PAGE;
    	}
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        g2d.drawLine(5, 5, (int)pf.getImageableWidth() - 2, 5);
        g2d.drawLine(5, 5, 5, (int)pf.getImageableHeight() - 2);
        g2d.drawLine((int)pf.getImageableWidth() - 2, 5, (int)pf.getImageableWidth() - 2
        		, (int)pf.getImageableHeight() - 2);
        g2d.drawLine(5, (int)pf.getImageableHeight() - 2, (int)pf.getImageableWidth() - 2, (int)pf.getImageableHeight() - 2);
             
        FontMetrics fm = g2d.getFontMetrics();
        int x = 25;
        int y = 32;
        
        // PRINTING RECEIPT HEADER
        for (int i = 0; i < receiptHeader.length; i++) {
        	java.awt.geom.Rectangle2D r = fm.getStringBounds(receiptHeader[i], g2d);
        	g.drawString(receiptHeader[i], ((int)pf.getWidth() - (int)r.getWidth()) - 45, y);
        	
        	if (i == receiptHeader.length - 1) {
        		y += 32;
        	}
        	else {
        		y += 16;
        	}
        }
        
        // PRINTING SALE HEADER
        for (int i = 0; i < saleHeader.length; i++) {
        	g.drawString(saleHeader[i], x, y);
        	if (i == saleHeader.length - 1) {
        		y += 32;
        	}
        	else {
        		y += 16;
        	}
        }
        
        // PRINTING SALE DETAILS
        for (int i = 0; i < saleDetails.length; i++) {
        	if ((i+1) % 4 == 1) {
        		g.drawString(saleDetails[i], x, y);
        		y += 16;
        	}
        	else if ((i+1) % 4 == 2) {
        		g.drawString(saleDetails[i], x + 15, y);
        		y += 16;
        	}
        	else if ((i+1) % 4 == 3){
        		java.awt.geom.Rectangle2D r = fm.getStringBounds(saleDetails[i], g2d);
        		g.drawString(saleDetails[i], ((int)pf.getWidth() - (int)r.getWidth()) - 275, y - 16);
        		// y += 4;
        	}
        	else {
        		java.awt.geom.Rectangle2D r = fm.getStringBounds(saleDetails[i], g2d);
        		g.drawString(saleDetails[i], ((int)pf.getWidth() - (int)r.getWidth()) - 375, y - 16);
        		y += 4;
        	}
        }
        
        // PRINTING BREAKLINE
        for (int i = 0; i < breakLine.length; i++) {
        	g.drawString(breakLine[i], x, y);
        	y += 16;
        }
        
        // PRINTING SALE FOOTER
        for (int i = 0; i < saleFooter.length; i++) {
        	if((i+1) % 2 == 1) {
        		g.drawString(saleFooter[i], x, y);
            	y += 16;
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
        		y += 16;
        	}
        	else {
        		g.drawString(receiptFooter[i], x, y);
        	}
        }

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    public void printReceipt() {
    	PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                 job.print();
            } catch (PrinterException ex) {
             /* The job did not successfully complete */
            }
        }
        else {
        	printToFile();
        }
    }
}
