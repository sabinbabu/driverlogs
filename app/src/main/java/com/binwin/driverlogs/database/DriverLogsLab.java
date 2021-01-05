package com.binwin.driverlogs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.binwin.driverlogs.DriverLogs;

import java.util.ArrayList;
//contains necessary queries for data operation
public class DriverLogsLab {
    private static DriverLogsLab sDriverLogsLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DriverLogsLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DriverLogsHelper(mContext)
                .getWritableDatabase();
    }

    public static DriverLogsLab get(Context context) {
        if (sDriverLogsLab == null) {
            sDriverLogsLab = new DriverLogsLab(context);
        }
        return sDriverLogsLab;
    }

    private static ContentValues getContentValues(DriverLogs driverLogs) {
        ContentValues values = new ContentValues();
        values.put(DriverLogsDbSchema.DriverLogsTable.Cols.UUID, driverLogs.getmId().toString());
        values.put(DriverLogsDbSchema.DriverLogsTable.Cols.VEHICLE_TYPE, driverLogs.getmVehicleType());
        values.put(DriverLogsDbSchema.DriverLogsTable.Cols.DRIVER_NAME, driverLogs.getmDriverName());
        values.put(DriverLogsDbSchema.DriverLogsTable.Cols.REGO, driverLogs.getmRego());
        values.put(DriverLogsDbSchema.DriverLogsTable.Cols.START_TIME, driverLogs.getmStartTime());
        values.put(DriverLogsDbSchema.DriverLogsTable.Cols.FIRST_BREAK, driverLogs.getmFirstBreak());
        values.put(DriverLogsDbSchema.DriverLogsTable.Cols.SECOND_BREAK, driverLogs.getmSecondBreak());
        values.put(DriverLogsDbSchema.DriverLogsTable.Cols.END_TIME, driverLogs.getmEndTime());


        return values;
    }


    public void addDriverLogs(DriverLogs driverLogs) {
        ContentValues values = getContentValues(driverLogs);

        mDatabase.insert(DriverLogsDbSchema.DriverLogsTable.NAME, null, values);
    }



    public ArrayList<DriverLogs> getVehicleLog(String key) {
        ArrayList<DriverLogs> driverLogs = new ArrayList<>();

        try (DriverLogsCursorWrapper cursor = queryLogs(
                DriverLogsDbSchema.DriverLogsTable.Cols.VEHICLE_TYPE + " = ?",
                new String[]{key})) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                driverLogs.add(cursor.getLogs());
                cursor.moveToNext();
            }
        }

        return driverLogs;
    }

    public ArrayList<DriverLogs> getAllVehicleLog() {
        ArrayList<DriverLogs> driverLogs = new ArrayList<>();

        try (DriverLogsCursorWrapper cursor = queryLogs(
                null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                driverLogs.add(cursor.getLogs());
                cursor.moveToNext();
            }
        }

        return driverLogs;
    }

    public void deleteAllLogs() {
          mDatabase.delete(DriverLogsDbSchema.DriverLogsTable.NAME,null,null);
    }

    public void deleteSelectedLogs() {
        int key;

        Cursor cursor = mDatabase.rawQuery("SELECT MAX(" + "_id" + ") FROM " + DriverLogsDbSchema.DriverLogsTable.NAME,null);
        cursor.moveToFirst();
        key = cursor.getInt(0);
        cursor.close();

        mDatabase.delete(DriverLogsDbSchema.DriverLogsTable.NAME,"_id = ?",new String[]{String.valueOf(key)});
    }

    private DriverLogsCursorWrapper queryLogs(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DriverLogsDbSchema.DriverLogsTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new DriverLogsCursorWrapper(cursor);
    }
}
