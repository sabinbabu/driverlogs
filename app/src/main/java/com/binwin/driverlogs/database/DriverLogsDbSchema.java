package com.binwin.driverlogs.database;

//database schema
public class DriverLogsDbSchema {
    public static final class DriverLogsTable {
        public static final String NAME = "driverLogs";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String VEHICLE_TYPE = "vehicleType";
            public static final String DRIVER_NAME = "driverName";
            public static final String REGO = "rego";
            public static final String START_TIME = "startTime";
            public static final String FIRST_BREAK = "firstBreak";
            public static final String SECOND_BREAK = "secondBreak";
            public static final String END_TIME = "endTime";

        }
    }
}
