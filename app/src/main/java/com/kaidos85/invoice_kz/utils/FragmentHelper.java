package com.kaidos85.invoice_kz.utils;

import android.support.v4.app.Fragment;

/**
 * Created by Aidos on 09.06.2016.
 */
public class FragmentHelper {
    public Fragment fragment;
    public String name;

    public FragmentHelper(Fragment _frag, String _name){
        fragment = _frag;
        name = _name;
    }
}
