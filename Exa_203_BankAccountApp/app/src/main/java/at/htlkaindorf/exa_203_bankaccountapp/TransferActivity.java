package at.htlkaindorf.exa_203_bankaccountapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.beans.GiroAccount;
import at.htlkaindorf.exa_203_bankaccountapp.beans.StudentAccount;

public class TransferActivity extends AppCompatActivity {
    private TextView accountType;
    private TextView iban;
    private TextView balance;
    private TextView amountAvailable;
    private AutoCompleteTextView actv;
    private EditText transferAmount;
    private Button btnTransfer;

    private ArrayList<String> ibans;
    private Account currAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        this.initializeVariables();
        this.initializeHandlers();
        this.retrieveData();
        this.setValues();
        this.initializeAutoComplete();
    }

    private void initializeVariables() {
        this.accountType = findViewById(R.id.accountTypeNew);
        this.iban = findViewById(R.id.ibanNew);
        this.balance = findViewById(R.id.balanceNew);
        this.amountAvailable = findViewById(R.id.amountAvailableNew);

        this.actv = findViewById(R.id.actv);
        this.transferAmount = findViewById(R.id.transferAmount);
        this.btnTransfer = findViewById(R.id.btnTransfer);
        this.btnTransfer.setEnabled(false);
    }

    private void initializeHandlers() {
        this.btnTransfer.setOnClickListener(view -> {
            //transaction allowed
            Intent intent = getIntent();
            intent.putExtra("oldacc", this.currAcc);
            intent.putExtra("target", this.actv.getText().toString());
            intent.putExtra("amount", this.transferAmount.getText().toString());
            this.setResult(3, intent);
            this.finish();
        });

        this.transferAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (transferAmount.getText().toString().equals("")) {
                    //Textfield is empty anyways
                    btnTransfer.setEnabled(false);
                    btnTransfer.setBackgroundColor(Color.GRAY);
                    balance.setText(String.format("€ %.2f", currAcc.getBalance()).replace(".", ","));
                } else {
                    try {
                        //Textfield is not empty --> decide on how to handle the input
                        if (currAcc instanceof StudentAccount) {
                            double new_amount = currAcc.getBalance() - Double.parseDouble(transferAmount.getText().toString().replace(",", "."));
                            if (new_amount >= 0) {
                                //transaction allowed
                                btnTransfer.setEnabled(true);
                                btnTransfer.setBackgroundColor(Color.GREEN);
                                balance.setTextColor(Color.GREEN);
                                balance.setText(String.format("€ %.2f", new_amount).replace(".", ","));
                            } else {
                                //transaction is not allowed
                                btnTransfer.setEnabled(false);
                                btnTransfer.setBackgroundColor(Color.GRAY);
                                balance.setTextColor(Color.RED);
                                balance.setText(String.format("€ %.2f", new_amount).replace(".", ","));
                            }
                        } else {
                            double new_amount = currAcc.getBalance() - Double.parseDouble(transferAmount.getText().toString().replace(",", "."));
                            if (new_amount + ((GiroAccount) currAcc).getOverdraft() >= 0) {
                                //transactions allowed
                                btnTransfer.setEnabled(true);
                                btnTransfer.setBackgroundColor(Color.GREEN);
                                balance.setTextColor(Color.GREEN);
                                balance.setText(String.format("€ %.2f", new_amount).replace(".", ","));
                            } else {
                                //transaction is not allowed
                                btnTransfer.setEnabled(false);
                                btnTransfer.setBackgroundColor(Color.GRAY);
                                balance.setTextColor(Color.RED);
                                balance.setText(String.format("€ %.2f", new_amount).replace(".", ","));
                            }
                        }
                    }
                    catch (NumberFormatException e) {
                        transferAmount.setText("");
                    }
                }
            }
        });
    }

    private void retrieveData() {
        this.currAcc = (Account) getIntent().getSerializableExtra("account");
        this.ibans = getIntent().getStringArrayListExtra("ibans");
    }

    private void setValues() {
        this.accountType.setText((this.currAcc instanceof StudentAccount) ? "Student-Account" : "Giro-Account");
        this.iban.setText(this.currAcc.getIban());
        this.balance.setText(this.currAcc.getBalance() + "");
        this.amountAvailable.setText((this.currAcc instanceof StudentAccount) ? (String.format("€ %.2f", currAcc.getBalance()).replace(".", ",")) : (String.format("€ %.2f", ((((GiroAccount) this.currAcc)).getOverdraft() + this.currAcc.getBalance())).replace(".", ",")));
    }

    private void initializeAutoComplete() {
        this.actv.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.select_dialog_item, this.ibans));
    }
}
