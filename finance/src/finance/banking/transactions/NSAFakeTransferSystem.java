package finance.banking.transactions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import finance.banking.Bank;
import finance.transactionTransfer.TransactionTransferSystem;

public class NSAFakeTransferSystem implements TransactionTransferSystem
{

	TransactionTransferSystem transcationTransferSystem;
	private PrintStream printStream;
	private OutputStream OutPutStream;
	Date date;

	public NSAFakeTransferSystem(TransactionTransferSystem tts) throws FileNotFoundException
	{
		transcationTransferSystem = tts;
		createPrintStream();
		date = new Date();
		
	}

	@Override
	public boolean submitTransaction(int targetInstituteNumber, Transaction transaction)
	{

		printStream.print(transaction + " Datum: " + date);
		return true;
	}

	@Override
	public int register(TransactionExecutor executor)
	{

		return 0;
	}
	
	private void createPrintStream(){
		try
		{
			printStream = new PrintStream("log\\NSALog.log");
		} catch (FileNotFoundException e)
		{
			System.err.println("Hoppla! Da ist beim erstellen der Datei was schiefgelaufen!");

		}
	}

	public void hackBank(Bank bank)
	{
		bank.transferSystem = this;
		// Um die Schwachstelle zu beheben muss man in der Klasse Bank einfach
		// das Feld transferSystem von public zu privat, oder protected �ndern
	}
}
