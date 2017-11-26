package com.kaidos85.invoice_kz.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Aidos on 08.06.2016.
 */
public class Organization extends RealmObject {
    @PrimaryKey
    private int id;
    private String Name;
    private String Address;
    private int BIN;
    private RealmList<BankAccount> Banks;
    private String Chef;
    private String ChefPost;
    private String Category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getChefPost() {
        return ChefPost;
    }

    public void setChefPost(String chefPost) {
        ChefPost = chefPost;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getBIN() {
        return BIN;
    }

    public void setBIN(int BIN) {
        this.BIN = BIN;
    }

    public String getChef() {
        return Chef;
    }

    public void setChef(String chef) {
        Chef = chef;
    }

    public RealmList<BankAccount> getBanks() {
        return Banks;
    }

    public void setBanks(RealmList<BankAccount> banks) {
        Banks = banks;
    }
}
