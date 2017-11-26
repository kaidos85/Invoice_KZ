package com.kaidos85.invoice_kz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kaidos85.invoice_kz.adapters.BankAdapter;
import com.kaidos85.invoice_kz.model.Bank;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * A simple {@link Fragment} subclass.
 */
public class BankFragment extends Fragment implements IFragmentAdd{

    private Realm realm;
    private final String LOG_TAG = "Invoice_KZ";
    private View view = null;
    private ListView lv;
    List<Bank> banks;
    ArrayAdapter<Bank> adapter;

    public BankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy(){
        realm.close();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bank, container, false);
        init();
        return view;
    }

    private void init(){
        // Create a new empty instance of Realm
        realm = Realm.getDefaultInstance();
        lv = (ListView)view.findViewById(R.id.lv_bank);
        LoadData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bank bank = (Bank)parent.getItemAtPosition(position);
                startAct(bank.getId());
            }
        });
    }

    private void LoadData() {
        banks = realm.where(Bank.class).findAll();
        adapter = new BankAdapter(view.getContext(), banks);
        lv.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                LoadData();
                Log.d(LOG_TAG, "OK!!!");
            }
            if(resultCode == Activity.RESULT_CANCELED){
                Log.d(LOG_TAG, "Cancel!!!");
            }
        }
    }

    public void Add(){
        startAct(0);
    }

    private void startAct(int id)
    {
        Log.d(LOG_TAG, "id "+ String.valueOf(id));
        Intent intent = new Intent(view.getContext(), ActivityEditBank.class);
        intent.putExtra("id_bank", id);
        startActivityForResult(intent, 1);
    }

}
