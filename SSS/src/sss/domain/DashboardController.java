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
	
	public DashboardController(DashboardFrame dashFrame) {
		frame = dashFrame;
		initialise();
	}
	
	@SuppressWarnings("unused")
	public void getProductData(int button) {
		try {
		String[] colNames = {"Code", "Name", "Cost Price", "Sale Price", "QOH"};
		NonEditableTableModel productData = new NonEditableTableModel();
		productData.setColumnIdentifiers(colNames);
		String selectAllProducts = SqlBuilder.getAllProducts();
		ResultSet allProducts = DbReader.executeQuery(selectAllProducts);
	
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
			WatchProductFrame watchFrame = new WatchProductFrame(productData, this, button);
			
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not read product information from "
					+ "database", "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void initialise() {
		try {
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
			startBackgroundThreads();
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
			
			// Separator Attributes'
			StyleConstants.setForeground(separatorAttributes, Color.BLACK);
			StyleConstants.setFontFamily(separatorAttributes, "SansSerif");
			StyleConstants.setFontSize(separatorAttributes, 20);

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
		service.shutdown();
		System.out.println("Threads Shutdown");
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
		if(i > 0) {
			try {
				File file = new File("data/dashData.dat");
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
				output.writeInt(i);
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
	
	public void refreshWatchedProducts() {
		frame.refreshWatchedProducts();
	}
	
	public void updateChart(ChartPanel panel) {
		frame.updateChart(panel);
	}
	
	public void startBackgroundThreads() {
		
		try {
			service.scheduleWithFixedDelay(new FetchSaleDataTask(cp, this), 0, 60, TimeUnit.SECONDS);
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
		System.out.println("Data Fetch Threads Started!");
	}

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
