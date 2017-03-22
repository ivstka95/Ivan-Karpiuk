package com.example.ivan.myapplication.Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Ivan on 09.03.2017.
 */

public class Post {
    Calendar date;

    public Post() {
        this.date = new GregorianCalendar(2017, (int) (Math.random() * 13) + 1, (int) (Math.random() * 32) + 1);
    }

    public Calendar getDate() {
        return date;
    }
}
