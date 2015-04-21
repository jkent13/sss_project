/**
 * SavingsAccount implementation of the BaseAccount class
 * @author Josh Kent
 * <br>Student #: 11500926
 */
public class SavingsAccount extends BaseAccount {
	/**
	 * Create a new SavingsAccount object
	 * @param name
	 *  The owner of the account's name
	 */
	public SavingsAccount(String name) {
		super(name);
	}
	
	
	/**
	 * Make a deposit into a SavingsAccount
	 * @param amount
	 * 	The amount to deposit
	 * @return
	 * 	True if the amount is positive (successful deposit).
	 * 	<br>False if the amount is negative (failure to deposit)
	 */
	public boolean deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Make a withdrawal from a SavingsAccount
	 * @param amount
	 * 	The amount to withdraw
	 * @return
	 * 	True if the amount is positive AND the amount is less than or equal to the balance. 
	 *  <br>False if the amount is negative or greater than the balance.
	 */
	public boolean withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Adds interest to a SavingsAccount balance
	 * @param interestRate
	 * 	The interest rate to add to the balance (in the format of 4.5 to represent 4.5%)
	 * @return
	 * 	True if the interest rate is positive.
	 * 	<br>False if the interest rate is negative.
	 */
	public boolean addInterest(double interestRate) {
		if (interestRate > 0) {
			balance += (balance * (interestRate / 100.0));
			return true;
		}
		else {
			return false;
		}
	}

}
