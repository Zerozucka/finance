package finance.banking.transactions;

public class TransferDepositTransaction extends DepositTransaction {

	public TransferDepositTransaction(int accountNumber, double amount) {
		super(accountNumber, amount);
	}
	
	@Override
	public String toString(){
		return "Auf das Konto mit der Nummer: "+this.accountNumber+" wurde folgender Betrag überwiesen: "+this.amount;
	}

}
