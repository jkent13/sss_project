/* TopSellerFilter Class
 * 
 * Data structure used to represent the collective user input from the Top Seller UI
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

public class TopSellerFilter {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	String startDate;
	String endDate;
	int limit;
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public TopSellerFilter() {
		
	}
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	public String getStartDate(){
		return startDate;
	}
	
	
	
	public String getEndDate(){
		return endDate;
	}
	
	
	
	public int getLimit(){
		return limit;
	}
	
	
	
	// ==========================================================================
	// Setter Methods
	// ==========================================================================
	
	
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	public void setLimit(int limit) {
		this.limit = limit;
	}

}
