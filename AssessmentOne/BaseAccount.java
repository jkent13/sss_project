/**
 * An abstract class to represent a basic bank account
 * <br>- Modified by: Josh Kent -
 * <br>- Student #: 11500926 -
 * @author Ken Lodge
 * @version 3.0 21/11/05
 */
public abstract class BaseAccount
    implements Comparable<BaseAccount>
{
    private static int nextAcctNumber = 1;
    protected String owner;
    protected int acctNumber;
    protected double balance;

    /**
     * Constructor, used only by subclasses, sets owner,
     * generates unique account number and zeroes balance.
     * @param name owner's name
     */
    protected BaseAccount(String name)
    {
        owner = name;
        acctNumber = nextAcctNumber++;
        balance = 0.0;
    }

    /**
     * Makes a deposit. Implementations will at least verify
     * the amount is positive before making the deposit.
     * @param amount the amount to deposit
     * @return true if amount is positive otherwise false
     */
    public abstract boolean deposit(double amount);

    /**
     * Implements the Comparable interface, making accounts
     * comparable through alphabetical ordering by owner's name.
     */
    public int compareTo(BaseAccount rhs)
    { 
    	// Use String compareTo method to sort accounts alphabetically by owner's name
    	if (owner.compareTo(rhs.owner) > 0) {
    		return 1;
    	}
    	else if (owner.compareTo(rhs.owner) < 0) {
    		return -1;
    	}
    	else {
    		return 0;
    	}
    }

    /**
     * Override equals to be consistent with compareTo
     */
    public boolean equals(Object rhs)
    { 
		if (rhs instanceof BaseAccount) {
			BaseAccount input = (BaseAccount) rhs;
			// Checks each data member for equality
			return (input.owner == owner) && (input.acctNumber == acctNumber)
					&& (input.balance == balance);
		}
		else {
			return false;
		}
	}

    /**
     * @return String representation of the object
     */
    public String toString()
    {
    	// Returns simple string representation of an account
    	return "Owner: " + owner + " - Account #" + acctNumber + " - Balance: $" + balance;
    } 
}
