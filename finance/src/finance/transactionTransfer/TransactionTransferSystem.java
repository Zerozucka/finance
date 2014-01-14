package finance.transactionTransfer;

import finance.banking.transactions.Transaction;
import finance.banking.transactions.TransactionExecutor;

/**
 * This class models a transfer system for banking transactions.
 * A FinancialInstitution can register itself with a transactionTransferSystem, which will provide a unique id for the institution.
 *
 */
public interface TransactionTransferSystem {

    /**
     * A Transaction will be pushed to the FinancialInstitution with the provided unique ID (targetInstituteNumber).
     * @param targetInstituteNumber the unique ID of a FinancialInstitution
     * @param transaction the Transaction to push to the FinancialInstitution
     * @return true if a FinancialInstitution with targetInstituteNumber exists AND the Transaction was executed successful, false otherwise.
     */
    boolean submitTransaction(int targetInstituteNumber, Transaction transaction);

    /**
     * This method can be used to register a FinancialInstitution to the TransactionTransferSystem. A unique ID for will be returned.
     * @param executor the institution to register
     * @return the unique ID for the institution
     */
    int register(TransactionExecutor executor);

}
