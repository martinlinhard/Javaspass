package at.htlkaindorf.exa_203_bankaccountapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;

public class AccountViewHolder extends RecyclerView.ViewHolder {
    private ImageView accountImage;
    private TextView accountType;
    private TextView iban;
    private TextView balance;
    private TextView amountAvailable;

    private View itemView;
    private Account acc;
    private ArrayList<String> ibans;

    public AccountViewHolder(@NonNull View itemView, ImageView accountImage, TextView accountType, TextView iban, TextView balance, TextView amountAvailable) {
        super(itemView);
        this.itemView = itemView;
        this.accountImage = accountImage;
        this.accountType = accountType;
        this.iban = iban;
        this.balance = balance;
        this.amountAvailable = amountAvailable;

        itemView.setOnLongClickListener(view -> {
            Intent newIntent = new Intent(MainActivity.main.getApplicationContext(), TransferActivity.class);
            newIntent.putExtra("account", this.acc);
            newIntent.putStringArrayListExtra("ibans", this.ibans);
            ((Activity) itemView.getContext()).startActivityForResult(newIntent, 5);
            return true;
        });
    }

    public ImageView getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(ImageView accountImage) {
        this.accountImage = accountImage;
    }

    public TextView getAccountType() {
        return accountType;
    }

    public void setAccountType(TextView accountType) {
        this.accountType = accountType;
    }

    public TextView getIban() {
        return iban;
    }

    public void setIban(TextView iban) {
        this.iban = iban;
    }

    public TextView getBalance() {
        return balance;
    }

    public void setBalance(TextView balance) {
        this.balance = balance;
    }

    public TextView getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(TextView amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public void setIbans(ArrayList<String> ibans) {
        this.ibans = ibans;
    }
}
