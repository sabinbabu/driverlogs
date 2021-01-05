package com.binwin.driverlogs.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.binwin.driverlogs.database.DriverLogsDbSchema.DriverLogsTable;

//database helper class
public class DriverLogsHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "driverLogs.db";

    public DriverLogsHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DriverLogsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DriverLogsTable.Cols.UUID + ", " +
                DriverLogsTable.Cols.VEHICLE_TYPE + ", " +
                DriverLogsTable.Cols.DRIVER_NAME + ", " +
                DriverLogsTable.Cols.REGO + ", " +
                DriverLogsTable.Cols.START_TIME + ", " +
                DriverLogsTable.Cols.FIRST_BREAK + ", " +
                DriverLogsTable.Cols.SECOND_BREAK + ", " +
                DriverLogsTable.Cols.END_TIME +
                ")"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
