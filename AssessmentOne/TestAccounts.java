/**
 * TestAccounts class for testing SavingsAccount and ChequeAccount objects
 * <br>- Modified by: Josh Kent -
 * <br>- Student #: 11500926 -
 */
import java.util.Arrays;
public class TestAccounts
{
    public static void main(String[] args)
    {
        BaseAccount[] list = new BaseAccount[5];

        list[0] = new SavingsAccount("Fred");
        list[1] = new ChequeAccount("Jim");
        list[2] = new ChequeAccount("Sue");
        list[3] = new SavingsAccount("Jim");
        list[4] = new SavingsAccount("Jill");

        int i;

        // In the following loops do NOT use your knowledge
        // of which member of list[] is of which type

        for (i = 0; i < 5; i++) {
        	/* Insert code setting 50.0 credit limits */
        	if(list[i] instanceof ChequeAccount) { // Sets credit limits only on ChequeAccounts
        		ChequeAccount currentAccount = (ChequeAccount) list[i];
        		currentAccount.setCreditLimit(50.0);
        		list[i] = currentAccount;
        	}
            System.out.println(list[i]);
        }
        System.out.println(); // Blank line added for convenience in reading output

        for (i = 0; i < 5; i++) {
            /* Insert code depositing 20.0 in each account */
        	list[i].deposit(20.0); // Deposits 20.0 into each account
            System.out.println(list[i]);
        }
        System.out.println(); // Blank line added for convenience in reading output
        
        for (i = 0; i < 5; i++) {
            /* Insert code withdrawing 25.0 from each account */
        	if(list[i] instanceof SavingsAccount) { // Performs SavingsAccount withdrawals
        		SavingsAccount currentAccount = (SavingsAccount) list[i];
        		currentAccount.withdraw(25.0);
        		list[i] = currentAccount;
        	}
        	else if(list[i] instanceof ChequeAccount) { // Performs ChequeAccount withdrawals
        		ChequeAccount currentAccount = (ChequeAccount) list[i];
        		currentAccount.withdraw(25.0);
        		list[i] = currentAccount;
        	}
            System.out.println(list[i]);
        }
        System.out.println(); // Blank line added for convenience in reading output

        for (i = 0; i < 5; i++) {
            /* Insert code adding 8.0% interest as applicable */
        	if(list[i] instanceof SavingsAccount) { // Adds interest only to SavingsAccounts
        		SavingsAccount currentAccount = (SavingsAccount) list[i];
        		currentAccount.addInterest(8.0);
        		list[i] = currentAccount;
        	}
            System.out.println(list[i]);
        }
        System.out.println(); // Blank line added for convenience in reading output

        /* Insert code to sort the accounts by owner's name */
        Arrays.sort(list); // Use provided Arrays.sort method to sort the list according to the BaseAccount compareTo method
        for (i = 0; i < 5; i++)
            System.out.println(list[i]);
    }
}

        