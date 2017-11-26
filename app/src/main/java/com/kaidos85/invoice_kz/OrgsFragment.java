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
import android.widget.TabHost;

import com.kaidos85.invoice_kz.adapters.OrgsAdapter;
import com.kaidos85.invoice_kz.model.Organization;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrgsFragment extends Fragment implements IFragmentAdd{

    private Realm realm;
    private final String LOG_TAG = "Invoice_KZ";
    private View view = null;
    private ListView lv;

    public OrgsFragment() {
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
        view = inflater.inflate(R.layout.fragment_orgs, container, false);
        init();

        TabHost host = (TabHost)view.findViewById(R.id.tabhost);
        host.setup();
        TabHost.TabSpec tab1 = host.newTabSpec("tag1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Организаций");
        host.addTab(tab1);
        return view;
    }

    private void init(){
        // Create a new empty instance of Realm
        realm = Realm.getDefaultInstance();
        lv = (ListView)view.findViewById(R.id.lv_org);
        LoadData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Organization org = (Organization)parent.getItemAtPosition(position);
                startAct(org.getId());
            }
        });

    }

    private void LoadData() {
        List<Organization> orgs = realm.where(Organization.class).findAll();
        ArrayAdapter<Organization> adapter = new OrgsAdapter(view.getContext(), orgs);
        lv.setAdapter(adapter);
    }

    public void Add(){
        startAct(0);
    }

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

    private void startAct(int id)
    {
        Log.d(LOG_TAG, "id "+ String.valueOf(id));
        Intent intent = new Intent(view.getContext(), ActivityEditOrgs.class);
        intent.putExtra("id_org", id);
        startActivityForResult(intent, 1);
    }

}
