package at.htlkaindorf.exa_203_bankaccountapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;

public class IO_Handler {
    public static List<Account> getAccounts(Context ctx) {
        try {
            return new BufferedReader(new InputStreamReader(ctx.getAssets().open("account_data.csv")))
                    .lines()
                    .skip(1)
                    .map(Account::parseAccount)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            Log.i(MainActivity.class.getSimpleName(), e.toString());
        }
        return new ArrayList<>();
    }
}
