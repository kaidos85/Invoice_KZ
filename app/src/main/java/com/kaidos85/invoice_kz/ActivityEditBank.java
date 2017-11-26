package com.kaidos85.invoice_kz;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.kaidos85.invoice_kz.model.Bank;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ActivityEditBank extends AppCompatActivity {

    private Realm realm;
    private final String LOG_TAG = "Invoice_KZ";

    EditText name;
    EditText bik;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bank);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Банк");
        Init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item_menu) {
        switch (item_menu.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                Cancel();
                this.finish();
                return true;
            case R.id.saveButton:
                Save();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item_menu);
        }
    }

    private void Init(){
        // Create a new empty instance of Realm
        realm = Realm.getDefaultInstance();

        name = (EditText)findViewById(R.id.edit_bank_name);
        bik = (EditText)findViewById(R.id.edit_bank_bik);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id_bank");
        if(id != 0){
            Bank bank = realm.where(Bank.class).equalTo("id", id).findFirst();
            name.setText(bank.getName());
            bik.setText(bank.getBIK());
        }
    }

    private void Save()
    {
        Bank bank;

        realm.beginTransaction();
        if(id == 0){
            bank = realm.createObject(Bank.class);
            int key;
            try {
                key = realm.where(Bank.class).max("id").intValue() + 1;
            } catch(ArrayIndexOutOfBoundsException ex) {
                key = 0;
            }
            bank.setId(key);
        }
        else{
            bank = realm.where(Bank.class).equalTo("id", id).findFirst();
        }
        bank.setName(name.getText().toString());
        bank.setBIK(bik.getText().toString());
        realm.copyToRealm(bank);
        realm.commitTransaction();
        setResult(RESULT_OK, new Intent());
    }

    private void Cancel(){
        setResult(RESULT_CANCELED, new Intent());
    }


}
