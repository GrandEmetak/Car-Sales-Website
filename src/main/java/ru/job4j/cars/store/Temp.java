package ru.job4j.cars.store;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Temp {
    public static Date subtractDays() {
        Date date = new  Date(System.currentTimeMillis());
        int days  = 1;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    public static void main(String[] args) {
        System.out.println("Data day - one: " + subtractDays());
    }
}
