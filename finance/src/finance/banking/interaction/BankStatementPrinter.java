package finance.banking.interaction;

import finance.FinancialInstitution;
import finance.banking.transactions.Transaction;
import finance.banking.transactions.TransactionHistoryProvider;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Bank statement printer.
 *
 * @author ...
 */
public class BankStatementPrinter {

    private FinancialInstitution institution;

    public BankStatementPrinter(FinancialInstitution institution) {
        this.institution = institution;
    }

    public void printStatement(TransactionHistoryProvider historyProvider) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************************************");
        System.out.println("Welcome to "+institution.getInstitutionName());
        System.out.println("**********************************************************");
        System.out.println("Please enter your account number:");

        int accountNumber = scanner.nextInt();

        if (institution.validateAccount(accountNumber)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
            System.out.println(institution.getAccountInformation(accountNumber));

            for(Transaction t: historyProvider.getTransactionHistory(accountNumber)){
                System.out.println("---------------------------------------------------------------------");
                System.out.println(dateFormat.format(t.getDate()));
                System.out.println(t.toString());
            }
        }
    }
}
