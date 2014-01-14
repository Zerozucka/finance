package finance.banking.transactions;

/**
 * Abstracts a transaction executor.
 *
 * @author ...
 */
public interface TransactionExecutor {

    /**
     * Executes the given transaction.
     *
     * @param transaction the transaction to be executed
     * @return true, if the execution was successful, false otherwise
     */
    boolean executeTransaction(Transaction transaction);

}
