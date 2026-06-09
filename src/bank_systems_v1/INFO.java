/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank_systems_v1;

/**
 *
 * @author river
 */
public class INFO {
    private int ID;
    private String fname;
    private String lname;
    private String email;
    private String pnumber;
    private String acctype;
    private double balance;
    private int transactionID;
    private int accountTableAcountID;
    private String transactionType;
    private double transactionAmount;
    private String transactionDate;

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setAccountTableAcountID(int accountTableAcountID) {
        this.accountTableAcountID = accountTableAcountID;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public int getTransactionID() {
        return transactionID;
    }

    public int getAccountTableAcountID() {
        return accountTableAcountID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getID() {
        return ID;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPnumber() {
        return pnumber;
    }

    public String getAcctype() {
        return acctype;
    }

    public double getBalance() {
        return balance;
    }
    
    
}
