
import java.util.ArrayList;

public class Account {
	private String acctNo;
	private String acctName;
	private double acctBalance;
	private double chargeBalance;
	private double fee=35.0;
	private ArrayList<Transaction> transHistory = new ArrayList<Transaction>();

	public Account(String acctNo,String acctName,Double acctBalance){
		this.setAcctNo(acctNo);
		this.setAcctName(acctName);
		this.setAcctBalance(acctBalance);
	}
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Transaction> getTransHistory() {
		return transHistory;
	}
	public void setTransHistory(ArrayList<Transaction> transHistory) {
		this.transHistory = transHistory;
	}	public Account(String acctNo){
		this.acctNo=acctNo;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public double getAcctBalance() {
		return acctBalance;
	}
	public void setAcctBalance(double acctBalance) {
		this.acctBalance = acctBalance;
	}
	public double getChargeBalance() {
		return chargeBalance;
	}
	public void setChargeBalance(double chargeBalance) {
		this.chargeBalance = chargeBalance;
	}
	public double accumulateCharge(Double x){
				
		return 0;
	}

	public void sortTransactions(ArrayList<Transaction> list) {
		Transaction temp;
		if (list.size() > 1) // check if the number of orders is larger than 1
		{
			for (int x = 0; x < list.size(); x++) // bubble sort outer loop
			{
				for (int i = 0; i < list.size() - i; i++) {
					if (list.get(i).compareTo(list.get(i + 1)) > 0) {
						temp = list.get(i);
						list.set(i, list.get(i + 1));
						list.set(i + 1, temp);
					}
				}
			}
		}
	}

	@Override
	public String toString(){
		return "  Account:"+acctNo+" Account Name: "+acctName;
	}
	public void processTrans(Transaction trans){
		switch(trans.getType()){
		case "Check": {
			acctBalance-=trans.getChangeAmt();
			if (acctBalance<0.0){
				chargeBalance+=fee;
				acctBalance-=fee;
			}
			System.out.println("Balance after Check: " + acctBalance);
		}break;
		case "Debit":{
			acctBalance-=trans.getChangeAmt();
			if (acctBalance<0.0){
				chargeBalance+=fee;
				acctBalance-=fee;
			}
			System.out.println("Balance after Debit: " + acctBalance);
			
		}break;
		case "Deposit":{
			acctBalance+=trans.getChangeAmt();
			System.out.println("Balance after Deposit: " + acctBalance);

		}break;
		case "Withdrawl":{
			if (trans.getChangeAmt()>acctBalance) System.out.println("Insufficient funds! Withdrawl declined.");
			else acctBalance-=trans.getChangeAmt();
			System.out.println("Balance after Withdrawl: " + acctBalance);

		}		
		}
	}
	
}
