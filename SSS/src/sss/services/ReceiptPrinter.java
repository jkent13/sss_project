package sss.services;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import sss.domain.FormattedSale;

public class ReceiptPrinter implements Printable {
	private boolean isBuilt = false;
	
	private String[] receiptHeader;
	private String[] saleHeader;
	private String[] saleDetails;
	private String[] saleFooter;
	private String[] receiptFooter;
	
	private final String[] breakLine = {" ",
									"______________________________________",
									" "};

	public ReceiptPrinter(FormattedSale fs) {
		this.receiptHeader = fs.getReceiptHeader();
		this.saleHeader = fs.getSaleHeader();
		this.saleDetails = fs.getSaleDetails();
		this.saleFooter = fs.getSaleFooter();
		this.receiptFooter = fs.getReceiptFooter();
		isBuilt = true;
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
        
//        g2d.drawLine(25, 5, 25, (int)pf.getImageableHeight() - 5);
//        g2d.drawLine((int)pf.getImageableWidth() - 25, 5, (int)pf.getImageableWidth() - 25, (int)pf.getImageableHeight() - 5);
        
        FontMetrics fm = g2d.getFontMetrics();
        int x = 25;
        int y = 32;
        
//        System.out.println("Height: " + pf.getImageableHeight());
//        System.out.println("Width: " + pf.getImageableWidth());
        
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
        	if ((i+1) % 3 == 1) {
        		g.drawString(saleDetails[i], x, y);
        		y += 16;
        	}
        	else if ((i+1) % 3 == 2) {
        		g.drawString(saleDetails[i], x + 15, y);
        		y += 16;
        	}
        	else {
        		java.awt.geom.Rectangle2D r = fm.getStringBounds(saleDetails[i], g2d);
        		g.drawString(saleDetails[i], ((int)pf.getWidth() - (int)r.getWidth()) - 325, y - 16);
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
        		g.drawString(saleFooter[i], ((int)pf.getWidth() - (int)r.getWidth()) - 325, y - 16);
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
//        for(int i = 0; i < receiptLines.length; i++) {
//        	java.awt.geom.Rectangle2D r = fm.getStringBounds(receiptLines[i], g2d);
////            x = ((int)pf.getWidth() - (int)r.getWidth()) / 2;
//            if (i < 7) {
//            	g.drawString(receiptLines[i], ((int)pf.getWidth() - (int)r.getWidth()) - 45, y);
//                if (i == 6) {
//                	y += 48;
//                }
//            }	
//            else if (i == receiptLines.length - 3)
//            	g.drawString(receiptLines[i], x, ((int)pf.getHeight() - (int)r.getHeight()) - 16);
//            else {
//            	g.drawString(receiptLines[i], x, y);
//            }
//            y += 16;
//        }
        

        /* Now we perform our rendering */
        

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
    }
}
