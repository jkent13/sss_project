/* WatchedProduct Class
 * 
 * Data structure used to represent a product being watched from the dashboard
 * 
 * Original Author: Josh Kent
 */
package sss.domain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WatchedProduct extends JPanel implements Serializable {

	// ==========================================================================
	// Variables
	// ==========================================================================
	
	private final Color colourOne = new Color(169, 22, 219);
	private final Color colourTwo = new Color(72, 219, 22);
	private final Color colourThree = new Color(22, 170, 219);
	private final Color colourFour = new Color(230, 21, 90);
	
	private String productName;
	private String productCode;
	private int watchedNumber;
	private int originalQuantity;
	private int currentQuantity;
	private float proportionFilled;
	
	
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	public WatchedProduct(String productCode, int originalQuantity, int watchedNumber) {
		this.productCode = productCode;
		this.originalQuantity = originalQuantity;
		if(watchedNumber > 0 && watchedNumber < 4) {
			this.watchedNumber = watchedNumber;
		}
		else {
			watchedNumber = 4;
		}
	}
	
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	public String getProductCode() {
		return productCode;
	}
	
	
	
	public int getOriginalQuantity() {
		return originalQuantity;
	}
	
	
	
	public int getCurrentQuantity() {
		return currentQuantity;
	}
	
	
	
	public float getProportionFilled() {
		calculateProportionFilled();
		return proportionFilled;
	}
	
	
	
	public String getProductName() {
		return productName;
	}
	
	
	
	// ==========================================================================
	// Setter Methods
	// ==========================================================================
	
	
	
	public void setCurrentQuantity(int value) {
		if(value <= originalQuantity) {
			this.currentQuantity = value;	
			calculateProportionFilled();
		}
		else {
			originalQuantity = value;
			currentQuantity = value;
			calculateProportionFilled();
		}
	}
	
	
	
	public void setProductName(String name) {
		productName = name;
	}
	
	
	
	
	public void setWatchedNumber(int value) {
		if(value > 0 && value < 4) {
			watchedNumber = value;
		}
		else {
			watchedNumber = 4;
		}
	}
	// ==========================================================================
	// Other Methods
	// ==========================================================================
	
	
	
	public void findCurrentQuantity() {
		// TODO read from file/db and listen to sales?
		// poll db?
		// set currentQuantity and recalculate proportionFilled
		// repaint
	}
	
	
	
	public void calculateProportionFilled() {
		proportionFilled = currentQuantity * 1.0F / originalQuantity * 1.0F;
		if(proportionFilled > originalQuantity) {
			proportionFilled = originalQuantity;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth()-1;
		int height = getHeight()-1;
		
		int portion = (int) (proportionFilled * (height/7.0 * 6.0));
		
		int xRectOutline = width/10;
		int yRectOutline = height/7;
		int rectOutlineHeight = (int) (height/7.0 * 6.0);
		int rectOutlineWidth = (int)(width/10 * 8.7);
		
		int xRectFilled = width/10;
		int yRectFilled = height - portion;
		int rectFilledHeight = portion;
		int rectFilledWidth = (int)(width/10 * 8.7);
				
		FontMetrics fm = g.getFontMetrics();
		int stringWidth = fm.stringWidth(productCode);
		int stringAscent = fm.getAscent();
		
		int xString = (width / 2) - stringWidth / 2;
		int yString = height/14;
		
		g.drawString(productCode, xString, yString);
		
		stringWidth = fm.stringWidth(currentQuantity + "/" + originalQuantity);
		xString = (width / 2) - stringWidth / 2;
		yString = height/14 + stringAscent + 3;
		
		g.drawString(currentQuantity + "/" + originalQuantity, xString, yString);
		
		switch(watchedNumber) {
		case 1 :
			g.setColor(colourOne);
			break;
		case 2 :
			g.setColor(colourTwo);
			break;
		case 3 :
			g.setColor(colourThree);
			break;
		default :
			g.setColor(colourFour);
			break;
		}
		g.fillRect(xRectFilled, yRectFilled, rectFilledWidth, rectFilledHeight);
		g.setColor(Color.BLACK);
		g.drawRect(xRectOutline, yRectOutline, rectOutlineWidth, rectOutlineHeight);

	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(140,300);
	}
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(140, 300);
	}
	
}
