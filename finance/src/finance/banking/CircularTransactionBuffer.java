package finance.banking;

import finance.banking.transactions.Transaction;
import finance.banking.transactions.TransactionBuffer;

/**
 * A simple buffer with FIFO ordering.
 *
 * @author ...
 */
public class CircularTransactionBuffer implements TransactionBuffer {

    /**
     * The buffer array for the transactions
     */
    private Transaction[] buffer;
    /**
     * The position for the next transaction within the buffer
     */
    private int nextPosition;

    /**
     * Creates a new buffer with the given size.
     *
     * @param size the size of the buffer
     */
    public CircularTransactionBuffer(int size) {
        this.buffer = new Transaction[size];
    }

    /**
     * Adds a transaction to this buffer and overrides
     * the oldest transaction, if the buffer is full.
     *
     * @param transaction the transaction to be added to the buffer
     */
    public void addTransaction(Transaction transaction) {
        buffer[nextPosition] = transaction;
        nextPosition = (nextPosition+1)%buffer.length;
    }

    /**
     * Returns the transactions of this buffer in the correct order
     *
     * @return an array containing all transactions of this
     * buffer in the correct order
     */
    public Transaction[] getTransactions() {
        Transaction[] result;
        if (buffer[nextPosition] == null) {
            result = new Transaction[nextPosition];
            for(int i = 0; i < result.length; i++)
                result[i] = buffer[i];
        } else {
            result = new Transaction[buffer.length];
            int pos = nextPosition;
            for(int i = 0; i < result.length; i++, pos = (pos+1)%buffer.length) {
                result[i] = buffer[pos];
            }
        }
        return result;
    }
}
