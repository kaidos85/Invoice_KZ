package com.kaidos85.invoice_kz;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.kaidos85.invoice_kz.model.Bank;
import com.kaidos85.invoice_kz.model.Organization;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ActivityEditOrgs extends AppCompatActivity {
    private Realm realm;
    private final String LOG_TAG = "Invoice_KZ";

    EditText name;
    EditText bin;
    EditText address;
    EditText chef;
    EditText chefpost;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_orgs);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Организация");
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

        name = (EditText)findViewById(R.id.edit_org_name);
        bin = (EditText)findViewById(R.id.edit_org_bin);
        address = (EditText)findViewById(R.id.edit_org_address);
        chef = (EditText)findViewById(R.id.edit_org_chef);
        chefpost = (EditText)findViewById(R.id.edit_org_chefpost);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id_org");
        if(id != 0){
            Organization org = realm.where(Organization.class).equalTo("id", id).findFirst();
            name.setText(org.getName());
            bin.setText(String.valueOf(org.getBIN()));
            address.setText(org.getAddress());
            chef.setText(org.getChef());
            chefpost.setText(org.getChefPost());
        }
    }

    private void Save()
    {
        Organization org;

        realm.beginTransaction();
        if(id == 0){
            org = realm.createObject(Organization.class);
            int key;
            try {
                key = realm.where(Organization.class).max("id").intValue() + 1;
            } catch(ArrayIndexOutOfBoundsException ex) {
                key = 0;
            }
            org.setId(key);
        }
        else{
            org = realm.where(Organization.class).equalTo("id", id).findFirst();
        }
        org.setName(name.getText().toString());
        org.setBIN(Integer.parseInt(bin.getText().toString()));
        org.setAddress(address.getText().toString());
        org.setChef(chef.getText().toString());
        org.setChefPost(chefpost.getText().toString());
        realm.copyToRealm(org);
        realm.commitTransaction();
        setResult(RESULT_OK, new Intent());
    }

    private void Cancel(){
        setResult(RESULT_CANCELED, new Intent());
    }


}
