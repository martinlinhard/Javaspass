package at.htlkaindorf.exa_201_contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Set;

import at.htlkaindorf.exa_201_contactsapp.bl.Contact;
import at.htlkaindorf.exa_201_contactsapp.bl.ContactAdapter;
import at.htlkaindorf.exa_201_contactsapp.bl.Gender;

public class DetailActivity extends AppCompatActivity {
    private Contact oldContact;
    private Contact newContact;

    private TextView firstname;
    private TextView lastname;
    private TextView phoneNumber;
    private TextView language;

    private RadioButton btnMale;
    private RadioButton btnFemale;
    private RadioButton btnUnknown;

    private Button btnSave;
    private Button btnCancel;

    private Gender gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.oldContact = (Contact) (getIntent().getExtras().get("contact"));
        this.initializeStuff();
        this.initializeHandlers();
        this.setValues();
    }

    private void initializeStuff() {
        this.firstname = findViewById(R.id.firstname);
        this.lastname = findViewById(R.id.lastname);
        this.phoneNumber = findViewById(R.id.phoneNumber);
        this.language = findViewById(R.id.language);
        this.btnMale = findViewById(R.id.rbMale);
        this.btnFemale = findViewById(R.id.rbFemale);
        this.btnUnknown = findViewById(R.id.rbUnknown);
        this.btnSave = findViewById(R.id.btnSave);
        this.btnCancel = findViewById(R.id.btnCancel);

        this.gender = oldContact.getGender();
    }

    private void initializeHandlers() {
        RBHandler rbHandler = new RBHandler();
        this.btnMale.setOnClickListener(rbHandler);
        this.btnFemale.setOnClickListener(rbHandler);
        this.btnUnknown.setOnClickListener(rbHandler);

        this.btnSave.setOnClickListener(v -> {
            this.alterValues();
            ContactAdapter.contactAdapter.updateContact(this.oldContact, this.newContact);
            this.finish();
        });

        this.btnCancel.setOnClickListener(v -> this.finish());
    }

    private void setValues() {
        this.firstname.setText(this.oldContact.getFirstname());
        this.lastname.setText(this.oldContact.getLastname());
        this.phoneNumber.setText(this.oldContact.getPhoneNumber());
        this.language.setText(this.oldContact.getLanguage());

        switch (this.oldContact.getGender()) {
            case MALE:
                this.btnMale.setChecked(true);
                break;
            case FEMALE:
                this.btnFemale.setChecked(true);
                break;
            case OTHER:
                this.btnUnknown.setChecked(true);
                break;
            default:
                Log.i(MainActivity.class.getSimpleName(), "shouldnt happen");
        }
    }

    private void alterValues() {
        this.newContact = Contact.fromContact(this.oldContact);

        newContact.setFirstname(firstname.getText().toString().equals("") ? this.oldContact.getFirstname() : firstname.getText().toString());
        newContact.setLastname(lastname.getText().toString().equals("") ? this.oldContact.getLastname() : lastname.getText().toString());
        newContact.setPhoneNumber(phoneNumber.getText().toString().equals("") ? this.oldContact.getPhoneNumber() : phoneNumber.getText().toString());
        newContact.setLanguage(language.getText().toString().equals("") ? this.oldContact.getLanguage() : language.getText().toString());
        newContact.setGender(gender);
    }

    public class RBHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (((Button) v).getText().toString()) {
                case "Male":
                    gender = Gender.MALE;
                    break;
                case "Female":
                    gender = Gender.FEMALE;
                    break;
                default:
                    gender = Gender.OTHER;
            }
        }
    }
}