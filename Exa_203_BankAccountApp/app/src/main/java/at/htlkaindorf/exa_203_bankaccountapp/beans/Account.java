package at.htlkaindorf.exa_203_bankaccountapp.beans;

import java.io.Serializable;

public class Account implements Serializable {
    private String iban;
    private double balance;
    private float interest;

    public Account(String iban, double balance, float interest) {
        this.iban = iban;
        this.balance = balance;
        this.interest = interest;
    }

    public String getIban() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public float getInterest() {
        return interest;
    }

    public static Account parseAccount(String line) {
        //id,type,iban,amount,overdraft,debitcard,interest

        String[] tokens = line.split("\\,");

        if (tokens[1].equals("GIRO")) {
            //create giro account
            return new GiroAccount(tokens[2], Double.parseDouble(tokens[3]), Float.parseFloat(tokens[6]), Double.parseDouble(tokens[4]));
        } else {
            //create student account
            return new StudentAccount(tokens[2], Double.parseDouble(tokens[3]), Float.parseFloat(tokens[6]), tokens[5].equals("T") ? true : false);
        }
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "iban='" + iban + '\'' +
                ", balance=" + balance +
                ", interest=" + interest +
                '}';
    }
}
