package com.kaidos85.invoice_kz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kaidos85.invoice_kz.R;
import com.kaidos85.invoice_kz.model.Organization;

import java.util.List;

/**
 * Created by Aidos on 02.06.2016.
 */
public class OrgsAdapter extends ArrayAdapter<Organization> {
    private final Context context;
    private final List<Organization> items;
    private final LayoutInflater inf;

    public OrgsAdapter(Context _context, List<Organization> _items)
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
        Organization item = items.get(position);
        if(item != null){
            v = inf.inflate(R.layout.lv_orgs, null);
            TextView title = (TextView) v.findViewById(R.id.lv_org_name);
            TextView bin = (TextView) v.findViewById(R.id.lv_org_bin);

            title.setText(item.getName());
            bin.setText(String.valueOf(item.getBIN()));
        }
        return v;
    }
}
