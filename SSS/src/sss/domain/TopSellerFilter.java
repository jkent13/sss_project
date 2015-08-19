package sss.domain;

public class TopSellerFilter {
	String startDate;
	String endDate;
	int limit;
	
	public TopSellerFilter() {
		
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public String getStartDate(){
		return startDate;
	}
	
	public String getEndDate(){
		return endDate;
	}
	
	public int getLimit(){
		return limit;
	}
}
