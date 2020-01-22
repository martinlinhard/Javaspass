package at.htlkaindorf.exa_203_bankaccountapp.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class StudentAccount extends Account implements Serializable {
    private boolean debitCard;

    public StudentAccount(String iban, double balance, float interest, boolean debitCard) {
        super(iban, balance, interest);
        this.debitCard = debitCard;
    }

    @Override
    public String toString() {
        return "StudentAccount{" +
                "debitCard=" + debitCard +
                '}';
    }
}
