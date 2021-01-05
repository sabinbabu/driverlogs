package com.binwin.driverlogs;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Util {
    //returns the  current date in 05/01/2021 10:12:24 format
    public String getCurrentDate() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return mSimpleDateFormat.format(currentDate);
    }
}
