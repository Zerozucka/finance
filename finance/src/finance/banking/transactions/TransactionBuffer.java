package finance.banking.transactions;

import finance.banking.transactions.Transaction;

/**
 * Abstracts a buffer for transaction.
 *
 * @author ...
 */
public interface TransactionBuffer {

    /**
     * Adds a transaction to this buffer and overrides
     * the oldest transaction, if the buffer is full.
     *
     * @param transaction the transaction to be added to the buffer
     */
    void addTransaction(Transaction transaction);

    /**
     * Returns the transactions of this buffer in the correct order
     *
     * @return an array containing all transactions of this
     * buffer in the correct order
     */
    Transaction[] getTransactions();
}
