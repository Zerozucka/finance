package finance.banking.transactions;

import finance.FinancialInstitution;
import finance.banking.Bank;

import java.util.Date;

/**
 * Models an abstract transaction.
 *
 * @author ...
 */
public abstract class Transaction {

    /**
     * The  of the sender account
     */
    protected int accountNumber;
    /**
     * The amount of money transferred
     */
    protected double amount;
    /**
     * The transaction time
     */
    private Date date;

    /**
     * Creates a new transaction for the given account.
     *
     * @param accountNumber the account number of the affected account
     * @param amount the amount of money that has been transferred
     */
    public Transaction(int accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.date = new Date(System.currentTimeMillis());
    }

    /**
     * Returns the transaction time as date.
     *
     * @return the time of the transaction
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the amount of money that has been transferred.
     *
     * @return the transferred amount of money
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Executes this transaction on the given bank.
     *
     * @param bank the bank this transaction should be executed on
     * @return true if the transaction was successful, false otherwise
     */
    public abstract boolean execute(Bank bank);

    /**
     * Returns if this transaction affects the given account number.
     * E.g., a withdraw transaction affects the given number, if money
     * is withdrawn to the given account.
     *
     * @param accountNumber the account number to be checked
     * @return true, if the given account number is affected
     *         by this transaction, false otherwise
     */
    public abstract boolean affectsAccountNumber(int accountNumber);
}
