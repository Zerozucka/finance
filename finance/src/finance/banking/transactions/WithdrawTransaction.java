package finance.banking.transactions;

import finance.banking.Bank;

public class WithdrawTransaction extends Transaction {


	/**Konstruktor**/
	public WithdrawTransaction(int accountNumber, double amount) {
		super(accountNumber, amount);
		this.accountNumber = accountNumber;
        this.amount = amount;
	}

	@Override
	public boolean execute(Bank bank) {
		return bank.withdraw(accountNumber, amount);
		
	}

	@Override
	public boolean affectsAccountNumber(int accountNumber) {
		if(this.accountNumber == accountNumber)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString(){
		return "Von dem Konto mit der Nummer: "+this.accountNumber+" wurde folgender Betrag abgehoben: "+this.amount;
	}
}
