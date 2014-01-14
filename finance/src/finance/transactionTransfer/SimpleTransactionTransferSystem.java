package finance.transactionTransfer;

import finance.banking.transactions.Transaction;
import finance.banking.transactions.TransactionExecutor;

/**
 * A simple TransactionTransferSystem.
 */
public class SimpleTransactionTransferSystem implements TransactionTransferSystem {

    /** Stores the registered executors (banks) */
    private TransactionExecutor[] executors;
    /** Stores the index for the next executor (bank) */
    private int nextIndex;

    /**
     * Creates a simple transaction transfer system for the
     * given maximum number of executors (banks).
     *
     * @param size the maximum number of executors (banks) that can be registered
     */
    public SimpleTransactionTransferSystem(int size){
        this.executors = new TransactionExecutor[size];
        this.nextIndex = 0;
    }

    @Override
    public boolean submitTransaction(int targetInstituteNumber, Transaction transaction) {
        if(targetInstituteNumber >= nextIndex || targetInstituteNumber < 0)
            return false;

        TransactionExecutor executor = executors[targetInstituteNumber];
        System.out.println("Executor selected: " + executor);

        return executor.executeTransaction(transaction);
    }

    @Override
    public int register(TransactionExecutor executor) {
        this.executors[nextIndex] = executor;
        nextIndex += 1;
        return nextIndex-1;
    }
}
