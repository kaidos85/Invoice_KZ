package com.kaidos85.invoice_kz.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Aidos on 25.06.2016.
 */
public class BankAccount extends RealmObject {
    @PrimaryKey
    private int id;
    private Bank Bank;
    private String IIK;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public com.kaidos85.invoice_kz.model.Bank getBank() {
        return Bank;
    }

    public void setBank(com.kaidos85.invoice_kz.model.Bank bank) {
        Bank = bank;
    }

    public String getIIK() {
        return IIK;
    }

    public void setIIK(String IIK) {
        this.IIK = IIK;
    }


}
