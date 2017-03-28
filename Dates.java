package com.example.fitme.fitme;

/**
 * Created by Jonathan on 3/26/2017.
 */

public class Dates {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGot_days() {
        return got_days;
    }

    public void setGot_days(int got_days) {
        this.got_days = got_days;
    }

    public int getGot_months() {
        return got_months;
    }

    public void setGot_months(int got_months) {
        this.got_months = got_months;
    }

    public int getGot_years() {
        return got_years;
    }

    public void setGot_years(int got_years) {
        this.got_years = got_years;
    }

    private int got_days;
    private int got_months;
    private int got_years;


    public Dates(int got_days, int got_months, int got_years) {
        this.got_days = got_days;
        this.got_months = got_months;
        this.got_years = got_years;
    }
}
