package com.example.fitme.fitme.dbget;

/**
 * Created by Jonathan on 2/19/2017.
 */

public class Exercise_Type {
    private String exc_type;
    private int id;
    public Exercise_Type(String exc_type) {
        this.exc_type = exc_type;
    }
    public String getExc_type() {
        return exc_type;
    }

    public void setExc_type(String exc_type) {
        this.exc_type = exc_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
