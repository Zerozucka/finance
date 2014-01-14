package finance.banking.accounts;

import finance.Customer;

/**
 * This interface models a basic bank account
 * @author ...
 */
public interface BankAccount {

    /**
     * This method validates if a pin is valid for this account.
     * @param pin the pin that should be validated
     * @return true, iff the pin fits to this account, false otherwise
     */
    boolean validatePin(int pin);

    /**
     * Returns if this account is accessible from a terminal.
	 * This is always true for check accounts e.g.
	 *
     * @return true, iff this account is accessible from a terminal
     */
    boolean accessibleFromTerminal();

    /**
     * Returns the customer that owns this account.
	 *
     * @return the owner of this account
     */
    Customer getOwner();

    /**
     * Returns the account number of this account.
	 *
     * @return the account number of this account
     */
    int getAccountNumber();

    /**
	 * Returns the current balance of this account.
	 * @return the current balance of this account
	 */
    double getBalance();

    /**
     * This method removes money from this account. 
	 * This is only valid for positive numbers and if money <= balance.
     * @param money a positive amount of money
     * @return true if money <= balance and money > 0
     */
    void withdrawMoney(double money);

    /**
     * This method adds money to this account. 
	 * This is only valid for positive numbers.
     * @param money -  a positive amount of money
     * @return - true if a positive amount was added, false otherwise.
     */
    void depositMoney(double money);

    /**
     * This method returns a description of this account. 
     * @return a string description of this account
     */
    String getAccountInformation();

}
