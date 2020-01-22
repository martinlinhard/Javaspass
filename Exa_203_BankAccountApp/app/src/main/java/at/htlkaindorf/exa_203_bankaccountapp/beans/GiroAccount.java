package at.htlkaindorf.exa_203_bankaccountapp.beans;


import java.io.Serializable;

public class GiroAccount extends Account implements Serializable {
    private double overdraft;

    public GiroAccount(String iban, double balance, float interest, double overdraft) {
        super(iban, balance, interest);
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    @Override
    public String toString() {
        return "GiroAccount{" +
                "overdraft=" + overdraft +
                '}';
    }
}
