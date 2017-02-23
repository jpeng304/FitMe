package com.example.fitme.fitme.dbget;

/**
 * Created by Jonathan on 2/19/2017.
 */

public class Exercise_Name {
    private String exc_name;
    private int id;

    public Exercise_Name(String exc_name) {
        this.exc_name = exc_name;
    }

    public String getExc_name() {
        return exc_name;
    }

    public void setExc_name(String exc_name) {
        this.exc_name = exc_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
