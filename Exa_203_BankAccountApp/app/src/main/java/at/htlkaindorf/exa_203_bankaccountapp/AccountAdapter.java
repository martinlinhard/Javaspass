package at.htlkaindorf.exa_203_bankaccountapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.beans.GiroAccount;
import at.htlkaindorf.exa_203_bankaccountapp.beans.StudentAccount;

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder> {
    private List<Account> allAccounts;
    private List<Account> filteredAccounts;
    private ArrayList<String> ibansList;

    private FilterType filterType;

    public AccountAdapter(Context ctx) {
        this.allAccounts = IO_Handler.getAccounts(ctx);
        this.filteredAccounts = new ArrayList<Account>() {{
            addAll(allAccounts);
        }};
        filterType = FilterType.ALL;
        this.initializeIbans();
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false);
        ImageView image = layout.findViewById(R.id.accountImage);
        TextView accountType = layout.findViewById(R.id.accountType);
        TextView iban = layout.findViewById(R.id.iban);
        TextView balance = layout.findViewById(R.id.balance);
        TextView amountAvailable = layout.findViewById(R.id.amountAvailable);
        return new AccountViewHolder(layout, image, accountType, iban, balance, amountAvailable);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account a = this.filteredAccounts.get(position);
        if (a instanceof StudentAccount) {
            holder.getAccountImage().setImageResource(R.drawable.student_account);
            holder.getAccountType().setText("Student-Account");
            holder.getAmountAvailable().setText(String.format("€ %.2f", a.getBalance()).replace(".", ","));
        } else {
            holder.getAccountImage().setImageResource(R.drawable.giro_account);
            holder.getAccountType().setText("Giro-Account");
            holder.getAmountAvailable().setText(String.format("€ %.2f", ((GiroAccount) a).getOverdraft()).replace(".", ","));
        }

        holder.getIban().setText(a.getIban());
        TextView balance = holder.getBalance();
        balance.setText(String.format("€ %.2f", a.getBalance()).replace(".", ","));

        if (a.getBalance() > 0) {
            balance.setTextColor(Color.GREEN);
        } else {
            balance.setTextColor(Color.RED);
        }

        holder.setAcc(a);
        holder.setIbans(ibansList);
    }

    @Override
    public int getItemCount() {
        return this.filteredAccounts.size();
    }

    public void filter(FilterType f) {
        this.filterType = f;
        this.filterItems();
    }

    private void filterItems() {
        this.filteredAccounts.clear();
        this.filteredAccounts.addAll(allAccounts);

        switch (this.filterType) {
            case STUDENT:
                this.filteredAccounts.removeIf(student -> !(student instanceof StudentAccount));
                break;
            case GIRO:
                this.filteredAccounts.removeIf(student -> !(student instanceof GiroAccount));
                break;
        }
        this.notifyDataSetChanged();
    }

    private void initializeIbans() {
        ArrayList<String> ibans = new ArrayList<>();
        for (Account acc : this.allAccounts) {
            ibans.add(acc.getIban());
        }
        this.ibansList = ibans;
    }

    public void performTransaction(Account oldAcc, String target, double amount) {
        Account sender = this.getAccountByIBAN(oldAcc.getIban());
        Account destination = this.getAccountByIBAN(target);

        if (sender == null || destination == null) {
            Toast.makeText(MainActivity.main.getApplicationContext(), "Unable to perform transaction.", Toast.LENGTH_LONG).show();
            return;
        }

        sender.setBalance(sender.getBalance() - amount);
        destination.setBalance(destination.getBalance() + amount);
        this.filterItems();
    }

    private Account getAccountByIBAN(String iban) {
        for (Account c : this.allAccounts) if (c.getIban().equals(iban)) return c;
        return null;
    }
}
