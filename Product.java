package com.example.fitme.fitme;

/**
 * Created by Jonathan on 2/18/2017.
 */

public class Product {
    private int id;
    //private String exc_name;
    private String body_type;
    //private String exc_type;
/*
    public Product(String exc_type, String exc_name, String body_type, int id) {
        this.exc_name = exc_name;
        this.body_type = body_type;
        this.exc_type = exc_type;
        this.id = id;
    }*/

    public Product(String body_type) {
        this.body_type = body_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    /*public String getExc_name() {
        return exc_name;
    }

    public void setExc_name(String exc_name) {
        this.exc_name = exc_name;
    }
*/
}