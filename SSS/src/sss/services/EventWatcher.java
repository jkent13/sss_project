/* EventWatcher Class
 * 
 * Singleton class that is responsible for watching the Register for any EventItems 
 * that occur. It captures all EventItems in a daily collection, and passes them 
 * through to the DashboardController class for display on the DashboardFrame.
 * 
 * Current-day events are also saved and reloaded when the system is closed, but events for 
 * previous days are discarded.
 * 
 * Original Author: Josh Kent
 */

package sss.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import sss.domain.EventItem;
import sss.domain.EventItemListener;

public class EventWatcher implements EventItemListener {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private static EventWatcher instance;
	private EventItemListener dashboardController;
	
	private Calendar todayDate = new GregorianCalendar();
	private ArrayList<EventItem> dailyEvents = new ArrayList<>();
	
	
	
	// ==========================================================================
	// Constructor and Singleton Access Method
	// ==========================================================================
	
	
	
	public EventWatcher() {
		initialise();
	}
	
	public static EventWatcher getInstance() {
		if(instance == null) {
			instance = new EventWatcher();
		}
		return instance;
	}
	
	
	
	// ==========================================================================
	// Core Methods
	// ==========================================================================
	
	
	
	@SuppressWarnings("unchecked")
	public void initialise() {
		
		// Set date to midnight today
		todayDate.set(Calendar.HOUR_OF_DAY, 0);
		todayDate.set(Calendar.MINUTE, 0);
		todayDate.set(Calendar.SECOND, 0);
		todayDate.set(Calendar.MILLISECOND, 0);
		
		try {
			// Read in events from file
			File eventData = new File("data/eventData.dat");
			if(eventData.exists()) {
				
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(eventData));
				Calendar fileDate = (Calendar) input.readObject();
				
				// Discard old events if it contains events for previous day
				if(fileDate.before(todayDate)) {
					dailyEvents.clear();
				}
				else {
					dailyEvents = (ArrayList<EventItem>) input.readObject();
				}
				
				input.close();
			}
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "Error: could not read eventData.dat", "File Read Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "Error: could not find EventItem class", "Class Not Found", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	public void registerEventItemListener(EventItemListener listener) {
		dashboardController = listener;
		// Every time dashboard registers, it is a new instance and must be populated
		// with today's events
		for(EventItem evt: dailyEvents) {
			dashboardController.notify(evt);
		}
	}
	
	
	
	public void saveEvents() {
		// If there are events to save
		if(!dailyEvents.isEmpty()) {
			try {
				File file = new File("data/eventData.dat");
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
				
				output.writeObject(todayDate); // Write today's date
				output.writeObject(dailyEvents); // Write collection of events
				
				output.close();
			}
			catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "Error: daily event list could not be saved", 
						"Could not save events", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	
	// ==========================================================================
	// Event Publishing Methods
	// ==========================================================================
	
	
	
	@Override
	public void notify(EventItem event) {
		postEvent(event);
	}
	
	
	
	private void postEvent(EventItem newEvent) {
		// Add Event to collection
		dailyEvents.add(newEvent);
		
		// Forward event to dashboard if available
		if(dashboardController != null) {
			dashboardController.notify(newEvent);
		}
	}
	
}
