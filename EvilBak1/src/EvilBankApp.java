
import java.util.Scanner;
import java.util.ArrayList;

public class EvilBankApp {
	protected static ArrayList<Transaction> grandTransHistory = new ArrayList<Transaction>();
	protected static ArrayList<Account> acctList = new ArrayList<Account>();

	public static void displayTrans() {
		System.out.println("Transaction History");
		for (Transaction trans : grandTransHistory) {
			System.out.println(trans.toString());
		}
	}

	public static void main(String[] args){
		boolean acctLoop=true, transLoop=true;
		String acctNum="";
		Scanner keyboard=new Scanner(System.in);
		System.out.println("Welcome to Evil Bank!\nPlease create the user account(s)");
		//create accounts 
		while(acctLoop){
			Account acct= new Account();
			System.out.println("Enter account # or -1 to stop entering accounts: ");
			acctNum=keyboard.next();		
			if (acctNum.equals("-1")){
				acctLoop = false;
			} else {
				acct.setAcctNo(acctNum);
				System.out.println("Enter the name for acct # "+acctNum+": ");
				acct.setAcctName(keyboard.next());
				System.out.println("Enter the balance for acct # "+acctNum+": ");
				acct.setAcctBalance(keyboard.nextInt());
				acctList.add(acct);
			}
		}
		
		//looping for transactions
		String ltype="",laccount="",ldate="";
		double lamount=0.0;
		boolean acctExist;
		Account theAcct = new Account();
		while(transLoop){
			acctExist = false;
			System.out.println("Enter a transaction type(Check, Debit Card, Deposit, or Withdrawal) or -1 to finish: ");
			ltype=keyboard.next();
			if (ltype.equals("-1")) break;
			System.out.println("Enter the account #: ");
			laccount=keyboard.next();
			//check if account exist
			for (Account oneAcct : EvilBankApp.acctList){
				if (laccount.equals(oneAcct.getAcctNo())) {
					theAcct = oneAcct;
					acctExist = true;
					break;
				}
			}
			if(!acctExist) {
				System.out.println("The account does not exist. Try again."); 
				acctExist = false;
				continue; //go to next while loop
			}
			// finish the transaction and add to histories
			System.out.println("Enter the amount the check: ");
			lamount=keyboard.nextDouble();
//			if (ltype.equals("Withdrawal")&&lamount>theAcct.getAcctBalance()){
//				System.out.println("Insufficient funds! Withdrawal declined!");}
//			else{
			System.out.println("Enter the date of the check (MM/DD/YYYY): ");
			ldate=keyboard.next();
			Transaction trans=new Transaction(laccount,lamount,ltype,ldate);
			grandTransHistory.add(trans); //record the transaction
			theAcct.getTransHistory().add(trans); //save transaction to the account
//			trans.writeToFile(laccount);
//			theAcct.processTrans(trans);
		}
		displayTrans(); //display the total transaction records
		//looping through each account and process its transactions in time order 
		for (Account anAcct : EvilBankApp.acctList) {
			anAcct.sortTransactions(anAcct.getTransHistory());//sort into time order
			for (Transaction aTrans : anAcct.getTransHistory()){
				System.out.println("going to process: "+ aTrans.toString());
				anAcct.processTrans(aTrans);				
			}
			System.out.println("The balance for account "+anAcct.getAcctNo()+" is "+anAcct.getAcctBalance());
		}
		System.out.println("\nClosing Program.....");
		keyboard.close();
	}
}
