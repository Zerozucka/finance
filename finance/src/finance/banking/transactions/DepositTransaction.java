

package finance.banking.transactions;

import finance.banking.Bank;

public class DepositTransaction extends Transaction{

	/**Konstruktor**/
	public DepositTransaction(int accountNumber, double amount) {
		super(accountNumber, amount);
	}

	@Override
	public boolean execute(Bank bank) {
		return bank.deposit(accountNumber, amount);
		
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
		return "Auf das Konto mit der Nummer: "+this.accountNumber+" wurde folgender Betrag überwiesen: "+this.amount;
	}

}
