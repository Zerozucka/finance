package finance.banking.transactions;

import finance.banking.Bank;

public class TransferTransaction extends Transaction{

	private int targetAccNumber;
	private String lastname;

	public TransferTransaction(int accountNumber,int targetAccNumber, String lastname , double amount) {
		super(accountNumber, amount);
		this.targetAccNumber = targetAccNumber;
		this.lastname = lastname;
	}

	@Override
	public boolean execute(Bank bank) {
		return bank.transfer(accountNumber, lastname, targetAccNumber, amount);
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
		return "Vom Konto mit der Kontonummer: "+accountNumber+" wurde der Betrag "+amount+" auf das Konto mit der Nummer: "+ targetAccNumber +" überwiesen";
		
	}
	
}
