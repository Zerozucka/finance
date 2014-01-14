package finance.banking.accounts;

import finance.Customer;

/**
 * This class models a check account implementing finance.banking.accounts.BankAccount.
 * For documentation of the implemented interface methods see the interface.
 *
 * @see BankAccount
 *
 * @author ...
 */
public class CheckAccount implements BankAccount {

    /** Stores the number of this account */
    private int accountNumber;
    /** Stores the pin code for this account */
    private int pin;
    /** Stores a reference to the customer that owns this account */
    private Customer owner;
    /** Stores the current balance of this account */
    private double balance;
    /** Stores the interest for overdrafts in percent */
    private double interest;
    /** Stores the overdraft limit for this account */
	private double overdraftLimit;

    /**
     * Creates a new check account for the given customer.
     *
     * @param accountNumber the account number
     * @param owner the owner of the account
     * @param pin the pin for the account
     */
    public CheckAccount(int accountNumber, Customer owner, int pin){
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.pin = pin;
        this.balance = 0;
        this.overdraftLimit = 0; // no creditworthiness at the moment
        interest = 0.12; // 12 % interest for overdrafts
    }

    public boolean validatePin(int pin) {
        return this.pin == pin;
    }

    public boolean accessibleFromTerminal() {
        return true;
    }

    public Customer getOwner() {
        return this.owner;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Returns the current interest for overdrafts.
     *
     * @return the current overdraft interest
     */
    public double getInterest() {
        return interest;
    }

    /**
     * Returns the current limit for overdrafts.
     *
     * @return the current limit for overdrafts
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Sets a new interest for overdrafts.
     *
     * @param interest the new overdraft interest
     */
    public void setInterest(double interest) {
        this.interest = interest;
    }

    /**
     * Sets a new limit for overdrafts.
     *
     * @param overdraftLimit the new limit for overdrafts
     */
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public void withdrawMoney(double money) {
        this.balance -= money;
    }

    public void depositMoney(double money) {
        this.balance += money;
    }

    public String getAccountInformation(){
        return "Account number: " + this.getAccountNumber()
                + "\nKontoinhaber: " + this.owner.getLastname()
                + "\nKontostand: " + this.getBalance()
                + "\nTerminal: " + this.accessibleFromTerminal();
    }
}
