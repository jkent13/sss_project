/* DatabaseSetupPanel Class
 * 
 * Panel that is passed as an argument to a JOptionPane when performing first time 
 * database set-up
 * 
 * Original Author: Josh Kent
 */

package sss.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DatabaseSetupPanel extends JPanel {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private JTextField pathTextField = new JTextField();
	private JTextField userNameTextField = new JTextField();
	private JPasswordField passwordTextField = new JPasswordField();
	
	private JLabel titleLabelOne = new JLabel("In order to use this software, a "
			+ "connection to a MySQL database needs to be configured.");
	private JLabel titleLabelTwo = new JLabel("Please enter your database"
			+ " details below:");
	
	private JLabel pathLabel  = new JLabel("Path:");
	private JLabel userNameLabel = new JLabel("Username:");
	private JLabel passwordLabel = new JLabel("Password:");
	
	private JPanel titlePanel = new JPanel(new GridLayout(2,1,5,5));
	private JPanel mainContentPanel = new JPanel(new GridLayout(3,2,5,5));
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public DatabaseSetupPanel() {
		
		pathLabel.setToolTipText("E.g. //localhost/sss");
		userNameLabel.setToolTipText("E.g. root");
		
		setLayout(new BorderLayout());
		
		titlePanel.add(titleLabelOne);
		titlePanel.add(titleLabelTwo);
		
		mainContentPanel.add(pathLabel);
		mainContentPanel.add(pathTextField);
		mainContentPanel.add(userNameLabel);
		mainContentPanel.add(userNameTextField);
		mainContentPanel.add(passwordLabel);
		mainContentPanel.add(passwordTextField);
		
		add(titlePanel, BorderLayout.NORTH);
		add(mainContentPanel, BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(550, 150));
		setMinimumSize(new Dimension(550, 150));
	}
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	public String getPath() {
		return pathTextField.getText();
	}
	
	
	
	public String getUserName() {
		return userNameTextField.getText();
	}
	
	
	
	public String getPassword() {
		return new String(passwordTextField.getPassword());
	}
	
	
	
	// ==========================================================================
	// Other Methods
	// ==========================================================================
	
	
	
	public void clearAllTextFields() {
		pathTextField.setText("");
		userNameTextField.setText("");
		passwordTextField.setText("");
	}
	
}
