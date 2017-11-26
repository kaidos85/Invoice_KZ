package com.kaidos85.invoice_kz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kaidos85.invoice_kz.R;
import com.kaidos85.invoice_kz.model.Bank;
import com.kaidos85.invoice_kz.model.Organization;

import java.util.List;

/**
 * Created by Aidos on 02.06.2016.
 */
public class BankAdapter extends ArrayAdapter<Bank> {
    private final Context context;
    private final List<Bank> items;
    private final LayoutInflater inf;

    public BankAdapter(Context _context, List<Bank> _items)
    {
        super(_context, 0, _items);
        context = _context;
        items = _items;
        inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        Bank item = items.get(position);
        if(item != null){
            v = inf.inflate(R.layout.lv_bank, null);
            TextView title = (TextView) v.findViewById(R.id.lv_bank_name);
            TextView bik = (TextView) v.findViewById(R.id.lv_bank_bik);

            title.setText(item.getName());
            bik.setText(item.getBIK());
        }
        return v;
    }
}
