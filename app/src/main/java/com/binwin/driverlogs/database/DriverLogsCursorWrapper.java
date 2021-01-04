package com.binwin.driverlogs.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.binwin.driverlogs.DriverLogs;
import com.binwin.driverlogs.database.DriverLogsDbSchema.DriverLogsTable;

import java.util.UUID;

public class DriverLogsCursorWrapper extends CursorWrapper {
    public DriverLogsCursorWrapper(Cursor cursor){super(cursor);}
    public DriverLogs getLogs(){

        String uuidString = getString(getColumnIndex(DriverLogsTable.Cols.UUID));
        String vehicleType = getString(getColumnIndex(DriverLogsTable.Cols.VEHICLE_TYPE));
        String driverName = getString(getColumnIndex(DriverLogsTable.Cols.DRIVER_NAME));
        String rego = getString(getColumnIndex(DriverLogsTable.Cols.REGO));
        String startTime = getString(getColumnIndex(DriverLogsTable.Cols.START_TIME));
        String firstBreak = getString(getColumnIndex(DriverLogsTable.Cols.FIRST_BREAK));
        String secondTime = getString(getColumnIndex(DriverLogsTable.Cols.SECOND_BREAK));
        String endTime = getString(getColumnIndex(DriverLogsTable.Cols.END_TIME));


        DriverLogs driverLogs = new DriverLogs(UUID.fromString(uuidString));
        driverLogs.setmVehicleType(vehicleType);
        driverLogs.setmDriverName(driverName);
        driverLogs.setmRego(rego);
        driverLogs.setmStartTime(startTime);
        driverLogs.setmFirstBreak(firstBreak);
        driverLogs.setmSecondBreak(secondTime);
        driverLogs.setmEndTime(endTime);

        return driverLogs;
    }
}
