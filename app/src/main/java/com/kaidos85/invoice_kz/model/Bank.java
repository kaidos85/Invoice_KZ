package com.kaidos85.invoice_kz.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Aidos on 24.06.2016.
 */
public class Bank  extends RealmObject {
    @PrimaryKey
    private int id;
    private String Name;
    private String BIK;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBIK() {
        return BIK;
    }

    public void setBIK(String BIK) {
        this.BIK = BIK;
    }


}
