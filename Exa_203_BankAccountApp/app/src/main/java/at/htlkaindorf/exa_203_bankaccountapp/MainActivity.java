package at.htlkaindorf.exa_203_bankaccountapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AccountAdapter accountAdapter;


    public static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.accountAdapter = new AccountAdapter(getApplicationContext());

        main = this;

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setAdapter(this.accountAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAll:
                this.accountAdapter.filter(FilterType.ALL);
                break;
            case R.id.menuStudent:
                this.accountAdapter.filter(FilterType.STUDENT);
                break;
            case R.id.menuGiro:
                this.accountAdapter.filter(FilterType.GIRO);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i(MainActivity.class.getSimpleName(), resultCode + " resultcode");
        if(requestCode == 5 && resultCode == 3) {
            Account acc = (Account) data.getSerializableExtra("oldacc");
            String target = data.getStringExtra("target");
            double amount = Double.parseDouble(data.getStringExtra("amount"));
            this.accountAdapter.performTransaction(acc, target, amount);
        }
    }
}
