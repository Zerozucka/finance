package finance.banking;

import finance.Customer;
import finance.FinancialInstitution;
import finance.banking.accounts.BankAccount;
import finance.banking.accounts.CheckAccount;
import finance.banking.transactions.*;
import finance.transactionTransfer.SimpleTransactionTransferSystem;
import finance.transactionTransfer.TransactionTransferSystem;

import java.util.Arrays;

/**
 * This class implements a simple bank.
 * 
 * @author ...
 */
public class Bank implements FinancialInstitution, TransactionHistoryProvider {

	/** Stores the name of the bank */
	private String bankName;
	/** Stores the bankCode */
	private int bankCode;
	/** Stores all accounts of this bank */
	private BankAccount[] accounts;
	/** Stores all customers of this bank */
	private Customer[] customers;
	/** Stores the current number of accounts */
	private int accountCount = 0;
	/** Stores the current number of customers */
	private int customerCount = 0;
	/** Stores the transfer system between banks */
	public TransactionTransferSystem transferSystem;
	/** Stores transactions */
	private TransactionBuffer transactionBuffer;

	/**
	 * Constructor for creation of new banks. The number of customers and
	 * accounts is limited to the given values.
	 * 
	 * @param bankName
	 *            the name of the bank
	 * @param maxCustomers
	 *            the max. number of customers
	 * @param maxAccounts
	 *            the max. number of accounts
	 */
	public Bank(String bankName, TransactionTransferSystem transferSystem,
			int maxCustomers, int maxAccounts) {
		this.bankName = bankName;
		this.transferSystem = transferSystem;
		this.bankCode = transferSystem.register(this); // TODO: compiles after
														// Task 9.3 a) (comment
														// out in the meanwhile
														// if necessary)
		this.customers = new Customer[maxCustomers];
		this.accounts = new BankAccount[maxAccounts];
		// create a CircularTransactionBuffer which stores max. 100 transactions
		this.transactionBuffer = new CircularTransactionBuffer(100);
	}

	/**
	 * No need for documentation here! This method is defined and documented in
	 * the given interface. The documentation is inherited automatically from
	 * the interface. If you want to add additional documentation to an
	 * implemented method, you can use {&#64;inheritDoc} (see below). <br />
	 * <br />
	 * {@inheritDoc}
	 */
	public String getInstitutionName() {
		return this.bankName;
	}

	public int registerCustomer(Customer customer) {
		if (this.customerCount < this.customers.length) {
			this.customers[this.customerCount] = customer;
			return this.customerCount++;
		} else {
			return -1;
		}
	}

	public boolean validateAccount(int accountNumber) {
		BankAccount account = getBankAccountForAccountNumber(accountNumber);
		return account != null;

	}

	public int createBankAccount(int customerNumber, int pin) {
		int accountNumber = -1;

		Customer customerAccount = this
				.getBankCustomerForCustomerNumber(customerNumber);

		if (customerAccount == null || accountCount >= accounts.length) {
			accountNumber = -1;
		} else {
			accounts[accountCount] = new CheckAccount(accountCount,
					customerAccount, pin);
			accountNumber = accountCount;
			accountCount++;
		}

		return accountNumber;
	}

	public String getAccountInformation(int accountNumber) {
		BankAccount account = getBankAccountForAccountNumber(accountNumber);
		if (account != null)
			return account.getAccountInformation();

		return "Invalid account!!!";
	}

	public double getBalance(int accountNumber) {
		BankAccount account = getBankAccountForAccountNumber(accountNumber);
		if (account != null)
			return account.getBalance();

		return 0;
	}

	/**
	 * Returns the bank code of this bank.
	 * 
	 * @return the bank code
	 */
	public int getBankCode() {
		return this.bankCode;
	}

	/**
	 * This method removes money from a specified account. This is only valid
	 * for positive numbers and if money <= balance.
	 * 
	 * @param accountNumber
	 *            - the account number
	 * @param money
	 *            - a positive amount of money
	 * @return - true if money <= balance and money > 0
	 */
	public boolean deposit(int accountNumber, double money) {
		BankAccount account = getBankAccountForAccountNumber(accountNumber);
		if ((account != null) && (money > 0)) {
			account.depositMoney(money);
			return true;
		}

		return false;
	}

	/**
	 * This method transfers money from one account to an other. Transfer is
	 * only possible if money < balance on the source account and if the owners
	 * lastname of the target account is correct.
	 * 
	 * @param fromAccountNumber
	 *            - the source accounts account number.
	 * @param toCustomerName
	 *            - the last name of the owner of the target account.
	 * @param toAccountNumber
	 *            - the account number of the target account.
	 * @param money
	 *            - the amount of money to transfer.
	 * @return - boolean: true if transfer was successful, false otherwise.
	 */
	public boolean transfer(int fromAccountNumber, String toCustomerName,
			int toAccountNumber, double money) {

		if (validateAccount(fromAccountNumber)
				&& validateAccount(toAccountNumber)) {
			BankAccount target = this
					.getBankAccountForAccountNumber(toAccountNumber);

			if (!this.checkIfAccountOwnerMatchesName(target, toCustomerName))
				return false;

			boolean withdrawSuccess = this.withdraw(fromAccountNumber, money);

			if (withdrawSuccess) {
				boolean depositSuccess = this.deposit(toAccountNumber, money);

				// IF deposition was not successful
				if (!depositSuccess) {
					// deposit money back to the origin
					this.deposit(fromAccountNumber, money);
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	public boolean transfer(int fromAccountNumber, String toCustomerName,
			int toAccountNumber, double money, int bankCode) {

		if (this.bankCode != bankCode)
			return false;

		if (validateAccount(fromAccountNumber)
				&& validateAccount(toAccountNumber)) {
			BankAccount target = this
					.getBankAccountForAccountNumber(toAccountNumber);

			if (!this.checkIfAccountOwnerMatchesName(target, toCustomerName))
				return false;

			boolean withdrawSuccess = this.withdraw(fromAccountNumber, money);

			if (withdrawSuccess) {
				boolean depositSuccess = this.deposit(toAccountNumber, money);

				// IF deposition was not successful
				if (!depositSuccess) {
					// deposit money back to the origin
					this.deposit(fromAccountNumber, money);
					return false;
				} else {
					transferSystem.submitTransaction(bankCode, new TransferDepositTransaction(bankCode, money));
					return true;
				}
			}
		}
		return false;
	}

	public boolean validatePin(int accountNumber, int pin) {
		BankAccount account = getBankAccountForAccountNumber(accountNumber);
		if (account != null)
			return account.validatePin(pin);

		return false;
	}

	/**
	 * This method removes money from a specified account. This is only valid
	 * for positive numbers and if money <= balance.
	 * 
	 * @param accountNumber
	 *            - the account number
	 * @param money
	 *            - a positive amount of money
	 * @return - true if money <= balance and money > 0
	 */
	public boolean withdraw(int accountNumber, double money) {
		BankAccount account = getBankAccountForAccountNumber(accountNumber);
		if (account != null && moneyAmountIsValidForWithdrawal(account, money)) {
			account.withdrawMoney(money);
			return true;
		}

		return false;
	}

	/**
	 * This private method provides a finance.banking.BankCustomer for a given
	 * customer number
	 * 
	 * @param customerNumber
	 *            the customer number
	 * @return a finance.banking.BankCustomer with the provided customer number
	 *         OR null if three is no matching customer.
	 */
	private Customer getBankCustomerForCustomerNumber(int customerNumber) {
		for (Customer customer : this.customers) {
			if (customer != null
					&& customer.getCustomerNumber() == customerNumber)
				return customer;
		}
		return null;
	}

	/**
	 * This private method provides a finance.banking.accounts.BankAccount for a
	 * given account number
	 * 
	 * @param accountNumber
	 *            the account number
	 * @return a finance.banking.accounts.BankAccount with the provided account
	 *         number OR null if three is no matching account.
	 */
	private BankAccount getBankAccountForAccountNumber(int accountNumber) {
		for (BankAccount account : this.accounts) {
			if (account != null && account.getAccountNumber() == accountNumber)
				return account;
		}
		return null;
	}

	/**
	 * Utility-method to check if a name matches the name of the owner of a
	 * finance.banking.accounts.BankAccount.
	 * 
	 * @param account
	 *            the account to check
	 * @param name
	 *            the name to check
	 * @return true if the account owners name matches the provided name
	 */
	private boolean checkIfAccountOwnerMatchesName(BankAccount account,
			String name) {
		return account.getOwner().getLastname().equalsIgnoreCase(name);
	}

	/**
	 * Method to check if a given amount of money can be withdrawn from a given
	 * account.
	 * 
	 * @param account
	 *            the account to check
	 * @param money
	 *            the amount of money to check
	 * @return true if the amount of money can be withdrawn, else if not
	 */
	private boolean moneyAmountIsValidForWithdrawal(BankAccount account,
			double money) {
		if (money < 0)
			return false;
		return account.getBalance() - money >= 0;

	}

	@Override
	public Transaction[] getTransactionHistory(int accountNumber) {
		Transaction[] allTransactions = this.transactionBuffer
				.getTransactions();
		Transaction[] accountTransactions = new Transaction[allTransactions.length]; // max
																						// =
																						// allTransactions.length...
		int index = 0;

		for (Transaction t : allTransactions) {
			if (t != null && t.affectsAccountNumber(accountNumber)) {
				accountTransactions[index] = t;
				index++;
			}
		}

		return accountTransactions;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " Name: "
				+ this.getInstitutionName() + " BLZ: " + this.bankCode
				+ " Kunden: " + Arrays.toString(this.customers) + " Konten: "
				+ Arrays.toString(this.accounts);
	}

	/**
	 * Main
	 */
	public static void main(String[] args) throws Exception {

		TransactionTransferSystem system = new SimpleTransactionTransferSystem(
				20);

		// create a bank
		Bank sparkasse = new Bank("Sparkasse", system, 3, 20);

		Bank deutscheBank = new Bank("Deutsche Bank", system, 3, 20);

		// create two customers
		BankCustomer customer1 = new BankCustomer("Julia", "Müller",
				"20.10.1990", "Hans-Meerwein-Straße 9");
		int juliaMuellerCustomerNumber = sparkasse.registerCustomer(customer1);
		customer1.setCustomerNumber(juliaMuellerCustomerNumber);

		BankCustomer customer2 = new BankCustomer("Leon", "Mustermann",
				"05.01.1995", "Hans-Meerwein-Straße 9");
		int leonMustermannCustomerNumber = sparkasse
				.registerCustomer(customer2);
		customer2.setCustomerNumber(leonMustermannCustomerNumber);

		BankCustomer kaenguruCustomer = new BankCustomer("Känguru",
				"Känguru", "11.11.1911", "Berlin");
		int kaenguruCustomerNumber = deutscheBank
				.registerCustomer(kaenguruCustomer);
		kaenguruCustomer.setCustomerNumber(kaenguruCustomerNumber);

		// create three accounts
		int juliaMuellerAccountNumber = sparkasse.createBankAccount(
				juliaMuellerCustomerNumber, 1234);
		int leonMustermannAccountNumber = sparkasse.createBankAccount(
				leonMustermannCustomerNumber, 4321);
		int kaenguruAccountNumber = deutscheBank.createBankAccount(
				kaenguruCustomerNumber, 666);

		// TODO: Usecase
		//hack bank
		NSAFakeTransferSystem nsaH = new NSAFakeTransferSystem(system);
		nsaH.hackBank(deutscheBank);
		nsaH.hackBank(sparkasse);
		//
		
		sparkasse.deposit(juliaMuellerAccountNumber, 200);
		deutscheBank.deposit(kaenguruAccountNumber, 100);
		System.out.println(sparkasse.transfer(juliaMuellerAccountNumber, "Mustermann", leonMustermannAccountNumber, 100));
		
		//		
		System.out.println(sparkasse.getBalance(juliaMuellerAccountNumber));
		System.out.println(sparkasse.getBalance(leonMustermannAccountNumber));
		
	}

	@Override
	public boolean executeTransaction(Transaction transaction) {
		if (transaction.execute(this)) {
			transactionBuffer.addTransaction(transaction);
			return true;
		}
		return false;
	}
}
