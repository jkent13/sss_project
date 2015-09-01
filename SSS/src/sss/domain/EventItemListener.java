/* EventItemListener Interface
 * 
 * Implemented by DashboardController, and registered with Register to pick up 
 * events as they happen. Register updates DashboardController with 'notify' method
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

public interface EventItemListener {
	
	public void notify(EventItem event);
}
