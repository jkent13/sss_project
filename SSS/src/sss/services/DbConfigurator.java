package sss.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import sss.ui.DatabaseSetupPanel;
import sss.ui.Main;

public class DbConfigurator {
	private static final String dbPathPrefix = "jdbc:mysql:";
	private static String dbPath;
	private static String userName;
	private static String password;
	
	private static DbConfiguration config; 
	
	private DbConfigurator() {
	}
	
	public static void loadConfig() {
		File configFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "config/db.dat");
		if(configFile.exists()) {
			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(configFile));
				config = (DbConfiguration) input.readObject();
				input.close();
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error: could not read db.dat", "File Read Error", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error: could not find DbConfiguration class", "Class Not Found", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		else {
			createConfig();
		}
	}
	
	public static DbConfiguration getConfig() {
		if(config != null) {
			return config;
		}
		else {
			loadConfig();
			return config;
		}
		
		
	}
	
	public static void createConfig() {
		DatabaseSetupPanel panel = new DatabaseSetupPanel();
		int response = JOptionPane.showConfirmDialog(null, panel, "Setup DB Connection", 
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(response == JOptionPane.YES_OPTION) {
			boolean isPathNull = panel.getPath().equals("");
			boolean isUserNameNull = panel.getUserName().equals("");
			
			if(!(isPathNull && isUserNameNull)) {
				dbPath = dbPathPrefix + panel.getPath();
				userName = panel.getUserName();
				password = panel.getPassword();
				
				config = new DbConfiguration(dbPath, userName, password);
				saveConfig(config);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Error: A necessary field has been "
						+ "left blank!", "Blank Field", JOptionPane.ERROR_MESSAGE);
				createConfig();
			}
			
		}
		else {
			System.exit(0);
		}
	}
	
	public static void saveConfig(DbConfiguration config) {
		File configDirectory = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "config");
		if(!configDirectory.exists()) {
			configDirectory.mkdir();
		}
		
		try {
			File configFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "config/db.dat");
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(configFile));
			output.writeObject(config);
			output.close();
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "Error: the database configuration could not be saved", 
					"Could not save DB config", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}		
	}
}
