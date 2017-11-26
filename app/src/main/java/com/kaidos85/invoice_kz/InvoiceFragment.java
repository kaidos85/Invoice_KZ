package com.kaidos85.invoice_kz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kaidos85.invoice_kz.adapters.OrgsAdapter;
import com.kaidos85.invoice_kz.model.Organization;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class InvoiceFragment extends Fragment implements IFragmentAdd{

    private Realm realm;
    private final String LOG_TAG = "Invoice_KZ";
    private View view = null;


    public InvoiceFragment() {
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
        view = inflater.inflate(R.layout.fragment_invoice, container, false);
        init();
        return view;
    }

    private void init(){
        // Create a new empty instance of Realm
        realm = Realm.getDefaultInstance();
    }

    public void Add(){
        Log.d(LOG_TAG, "Invoice add");
    }
}

