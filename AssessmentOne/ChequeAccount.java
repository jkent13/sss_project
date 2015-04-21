/**
 * ChequeAccount implementation of the BaseAccount class
 * @author Josh Kent
 * <br>Student #: 11500926
 */
public class ChequeAccount extends BaseAccount {
	
	// Represents the amount of allowable overdraw
	private double creditLimit; 
	
	// Constant to represent transaction fee incurred by deposits and withdrawals
	private final double TRANSACTION_CHARGE = 0.3; 
	
	/**
	 * Create a new ChequeAccount object.
	 * <br>Default credit limit is 0.0
	 * @param name
	 * 	The owner of the account's name
	 */
	public ChequeAccount(String name) {
		super(name);
		creditLimit = 0.0;
	}
	
	/**
	 * Make a deposit into a ChequeAccount
	 * @param amount
	 * 	The amount to deposit
	 * @return
	 * 	True if the amount to deposit is positive AND the amount + the current balance + the credit limit is greater or equal to the transaction charge (prevents overdraw).
	 * 	<br>False if the amount to deposit is negative OR the amount + the current balance + the credit limit is less than the transaction charge (prevents overdraw).
	 */
	public boolean deposit(double amount) {
		if (amount > 0 && (balance + amount + creditLimit >= TRANSACTION_CHARGE)) {
			balance += (amount - TRANSACTION_CHARGE);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Make a withdrawal from a ChequeAccount
	 * @param amount
	 * 	The amount to withdraw
	 * @return
	 * 	True if the amount is positive AND the amount + the transaction charge is less than or equal to the balance + the credit limit (prevents overdraw).
	 * 	<br>False if the amount is negative OR the amount + the transaction charge is greater than the balance + the credit limit (prevents overdraw).
	 */
	public boolean withdraw(double amount) {
		if (amount > 0 && (amount + TRANSACTION_CHARGE) <= balance + creditLimit) {
			balance -= (amount + TRANSACTION_CHARGE);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Setter method for the account's credit limit
	 * @param newLimit
	 * 	The new credit limit to be set
	 * @return
	 * 	True if the new limit is positive
	 * 	<br>False if the new limit is negative
	 */
	public boolean setCreditLimit(double newLimit) {
		if(newLimit > 0) {
			creditLimit = newLimit;
			return true;
		}
		else {
			return false;
		}
	}
}
