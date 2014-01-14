package finance.banking.transactions;

/**
 * This interface abstracts a provider for transaction histories.
 *
 * @author ...
 */
public interface TransactionHistoryProvider {

    /**
     * Returns the transaction history for the account with
     * the given account number.
     *
     * @param accountNumber the number of the requested account
     * @return the transactions in the correct temporal order
     */
    Transaction[] getTransactionHistory(int accountNumber);
}
