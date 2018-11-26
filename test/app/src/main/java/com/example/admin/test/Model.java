package com.example.admin.test;

public class Model {
    public String id_cus;
    public String name_cus;
    public String lasname_cus;

    public Model (String id_cus,String name_cus,String lasname_cus){
        this.id_cus = id_cus;
        this.name_cus = name_cus;
        this.lasname_cus = lasname_cus;
    }

    public String getId_cus() {
        return id_cus;
    }

    public String getName_cus() {
        return name_cus;
    }

    public String getLasname_cus() {
        return lasname_cus;
    }
}
