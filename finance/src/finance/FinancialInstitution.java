package finance;

import finance.banking.transactions.TransactionExecutor;

/**
 * This interface models a general finance.FinancialInstitution with basic banking functionality.
 */
public interface FinancialInstitution extends TransactionExecutor{

    /**
     * This method returns the name the financial institution.
     * @return -  a String containing the institutions name.
     */
    String getInstitutionName();
	
	/**
	 * Registers a new customer and returns its (positive) customer number.
	 * @param customer the customer to be registered
	 * @return the new customer number or -1, if the registration
	 * was not successful
	 */
	int registerCustomer(Customer customer);

	/**
	 * Creates a new account for the customer with the given customer number.
	 * @param customerNumber the number of an existing customer
	 * @param pin the PIN code for the new account
	 * @return the account number of the new bank account
	 */
	int createBankAccount(int customerNumber, int pin);
	
    /**
     * This method checks if the given account number belongs to the financial institute.
     * @param accountNumber - the account number to validate
     * @return -  true: if the account number belongs to the bank, false: if not!
     */
    boolean validateAccount(int accountNumber);

    /**
     * This method returns a description of an account. If the specified account is invalid this is indicated by the returned String.
     * @param accountNumber - the account number
     * @return - a string description of the account
     */
    String getAccountInformation(int accountNumber);
	
	/**
	 * Returns the current balance of the account with the given account number.
	 * @param accountNumber the number of the account the balance should be returned for
	 * @return the current balance of the given account
	 */
	double getBalance(int accountNumber);

    /**
     * This method validates if a pin is valid for a specific account.
     * @param accountNumber the number of the account the pin should be validated against
     * @param pin the pin that should be validated
     * @return true, iff the pin fits to the given account, false otherwise
     */
    boolean validatePin(int accountNumber, int pin);

}
