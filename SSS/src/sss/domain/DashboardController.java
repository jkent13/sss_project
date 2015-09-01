/* DashboardController Class
 * 
 * Controls application logic for Dashboard interactions
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.awt.Color;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.jfree.chart.ChartPanel;

import sss.services.DbReader;
import sss.services.FetchQuantityChangesTask;
import sss.services.FetchSaleDataTask;
import sss.services.SqlBuilder;
import sss.ui.DashboardFrame;
import sss.ui.WatchProductFrame;

public class DashboardController implements EventItemListener {

	// ==========================================================================
	// Variables
	// ==========================================================================



	private DashboardFrame frame;
	private ChartPanel cp;

	private WatchedProduct watchedProductOne;
	private WatchedProduct watchedProductTwo;
	private WatchedProduct watchedProductThree;

	private JTextPane eventFeedPane = new JTextPane();
	private SimpleAttributeSet separatorAttributes = new SimpleAttributeSet();
	private SimpleAttributeSet saleEventAttributes = new SimpleAttributeSet();
	private SimpleAttributeSet refundEventAttributes = new SimpleAttributeSet();
	private SimpleAttributeSet stockEmptyEventAttributes = new SimpleAttributeSet();

	private Register register;

	private ScheduledExecutorService service = Executors.newScheduledThreadPool(2); 



	// ==========================================================================
	// Constructor
	// ==========================================================================



	public DashboardController(DashboardFrame dashFrame) {
		frame = dashFrame;
		initialise();
	}



	// ==========================================================================
	// Core Methods
	// ==========================================================================



	public void initialise() {
		try {
			// Read in watched products from file
			File dashData = new File("data/dashData.dat");
			if(dashData.exists()) {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(dashData));
				int noObjects = input.readInt();
				WatchedProduct[] watchedProducts = new WatchedProduct[3];
				for(int i = 0; i < noObjects; i++) {
					watchedProducts[i] = (WatchedProduct) input.readObject();
				}
				input.close();
				if(watchedProducts[0] != null) {
					watchedProductOne = watchedProducts[0];
					watchedProductOne.setWatchedNumber(1);
				}
				if(watchedProducts[1] != null) {
					watchedProductTwo = watchedProducts[1];
					watchedProductTwo.setWatchedNumber(2);
				}
				if(watchedProducts[2] != null) {
					watchedProductThree = watchedProducts[2];
					watchedProductThree.setWatchedNumber(3);
				}
			}
			else {
				watchedProductOne = null;
				watchedProductTwo = null;
				watchedProductThree = null;
			}
			// Start threads for Watch List and Sale Chart
			startBackgroundThreads();

			// Set eventFeedPane properties
			eventFeedPane.setEditable(false);
			eventFeedPane.setMargin(new Insets(10,10,10,10));

			// Set Attributes for Big Sale Events
			StyleConstants.setForeground(saleEventAttributes, SaleEventItem.EVENT_COLOR);
			StyleConstants.setBold(saleEventAttributes, true);
			StyleConstants.setFontFamily(saleEventAttributes, "SansSerif");
			StyleConstants.setFontSize(saleEventAttributes, 14);

			// Set Attributes for Refund Events
			StyleConstants.setForeground(refundEventAttributes, RefundEventItem.EVENT_COLOR);
			StyleConstants.setBold(refundEventAttributes, true);
			StyleConstants.setFontFamily(refundEventAttributes, "SansSerif");
			StyleConstants.setFontSize(refundEventAttributes, 14);

			// Set Attributes for Stock Empty Events
			StyleConstants.setForeground(stockEmptyEventAttributes, StockEmptyEventItem.EVENT_COLOR);
			StyleConstants.setBold(stockEmptyEventAttributes, true);
			StyleConstants.setFontFamily(stockEmptyEventAttributes, "SansSerif");
			StyleConstants.setFontSize(stockEmptyEventAttributes, 14);

			// Separator (dashes between events) Attributes'
			StyleConstants.setForeground(separatorAttributes, Color.BLACK);
			StyleConstants.setFontFamily(separatorAttributes, "SansSerif");
			StyleConstants.setFontSize(separatorAttributes, 20);

			// Register as an event listener with the Register instance (to be notified when events occur)
			register = Register.getInstance();
			register.registerEventListener(this);
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "Error: could not read dashData.dat", "File Read Error", JOptionPane.ERROR_MESSAGE);
			watchedProductOne = null;
			watchedProductTwo = null;
			watchedProductThree = null;
		}
		catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "Error: could not find WatchedProduct class", "Class Not Found", JOptionPane.ERROR_MESSAGE);
			watchedProductOne = null;
			watchedProductTwo = null;
			watchedProductThree = null;
		}
	}



	public void shutdown() {
		// Shutdown threads
		service.shutdown();

		// Count how many products are in the Watch List
		int i = 0;
		if(watchedProductOne != null) {
			i++;
		}
		if(watchedProductTwo != null) {
			i++;
		}
		if(watchedProductThree != null) {
			i++;
		}

		// If there are some products being watched, write to file
		if(i > 0) {
			try {
				File file = new File("data/dashData.dat");
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
				output.writeInt(i); // Writes the number of objects to follow (used when reading)

				if(watchedProductOne != null) {
					output.writeObject(watchedProductOne);
				}
				if(watchedProductTwo != null) {
					output.writeObject(watchedProductTwo);
				}
				if(watchedProductThree != null) {
					output.writeObject(watchedProductThree);
				}
				output.close();
			}
			catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "Error: the watched products could not be saved", 
						"Could not save watch list state", JOptionPane.ERROR_MESSAGE);
			}
		}
	}



	@SuppressWarnings("unused")
	public void getProductData(int buttonNo) {
		try {
			// Create table model to hold products
			String[] colNames = {"Code", "Name", "Cost Price", "Sale Price", "QOH"};
			NonEditableTableModel productData = new NonEditableTableModel();
			productData.setColumnIdentifiers(colNames);
			
			// Get query
			String selectAllProducts = SqlBuilder.getAllProducts();
			// Execute query
			ResultSet allProducts = DbReader.executeQuery(selectAllProducts);

			// Add all product info into table model
			if(allProducts.next()) {
				do {
					productData.addRow(new Object[] {
							allProducts.getString("prod_code"),
							allProducts.getString("prod_name"),
							new BigDecimal(allProducts.getDouble("prod_cost_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN),
							new BigDecimal(allProducts.getDouble("prod_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN),
							allProducts.getInt("prod_qoh")				
					});
				} while(allProducts.next());
			}
			
			allProducts.close(); // Close ResultSet
			
			// This frame allows users to view products and pick one to watch
			WatchProductFrame watchFrame = new WatchProductFrame(productData, this, buttonNo);

		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not read product information from "
					+ "database", "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}



	public void startBackgroundThreads() {
		try {
			// Updates sale chart every 60 seconds
			service.scheduleWithFixedDelay(new FetchSaleDataTask(cp, this), 0, 60, TimeUnit.SECONDS);
			// Updates watch list every 60 seconds (after initial 30 second delay)
			service.scheduleWithFixedDelay(new FetchQuantityChangesTask(this), 30, 60, TimeUnit.SECONDS);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem starting the background database reader threads", "SQL Error", JOptionPane.ERROR_MESSAGE);
			frame.dispose(); 
		}
		catch (RuntimeException re) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem starting the background database reader threads", "SQL Error", JOptionPane.ERROR_MESSAGE);
			frame.dispose();
		}
	}
	
	
	
	// ==========================================================================
	// Setter Methods
	// ==========================================================================
	
	
	
	public void setWatchedProductOne(WatchedProduct one) {
		watchedProductOne = one;
		frame.setWatchedProductOne(watchedProductOne);
	}
	
	

	public void setWatchedProductTwo(WatchedProduct two) {
		watchedProductTwo = two;
		frame.setWatchedProductTwo(watchedProductTwo);
	}

	
	
	public void setWatchedProductThree(WatchedProduct three) {
		watchedProductThree = three;
		frame.setWatchedProductThree(watchedProductThree);
	}

	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	public WatchedProduct getWatchedProductOne() {
		return watchedProductOne;
	}

	
	
	public WatchedProduct getWatchedProductTwo() {
		return watchedProductTwo;
	}
	
	

	public WatchedProduct getWatchedProductThree() {
		return watchedProductThree;
	}

	
	
	public JTextPane getEventFeedPane() {
		return eventFeedPane;
	}

	
	
	// ==========================================================================
	// Refresh/Update Methods
	// ==========================================================================
	
	
	
	public void refreshWatchedProducts() {
		frame.refreshWatchedProducts();
	}

	
	
	public void updateChart(ChartPanel panel) {
		frame.updateChart(panel);
	}



	// ==========================================================================
	// Implemented from EventItemListener Interface
	// ==========================================================================
	
	
	
	@Override
	public void notify(EventItem event) {
		StyledDocument doc = eventFeedPane.getStyledDocument();
		SimpleAttributeSet style = null;
		
		switch(event.getType()) {
		case 0 : 
			style = saleEventAttributes;
			break;
		case 1 :
			style = refundEventAttributes;
			break;
		case 2 : 
			style = stockEmptyEventAttributes;
			break;
		}
		try {
			doc.insertString(doc.getLength(), event.toString(), style);
			doc.insertString(doc.getLength(), EventItem.EVENT_SEPARATOR, separatorAttributes);
		}
		catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
}
