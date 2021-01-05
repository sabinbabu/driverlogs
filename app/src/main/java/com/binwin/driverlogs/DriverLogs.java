package com.binwin.driverlogs;

import java.util.UUID;
//data class
public class DriverLogs {
    private UUID mId;
    private String mVehicleType;
    private String mDriverName;
    private String mRego;
    private String mStartTime;
    private String mFirstBreak;
    private String mSecondBreak;
    private String mEndTime;

    public DriverLogs( UUID mId,String mVehicleType, String mDriverName, String mRego, String mStartTime, String mFirstBreak, String mSecondBreak, String mEndTime) {
        this.mId = mId;
        this.mVehicleType = mVehicleType;
        this.mDriverName = mDriverName;
        this.mRego = mRego;
        this.mStartTime = mStartTime;
        this.mFirstBreak = mFirstBreak;
        this.mSecondBreak = mSecondBreak;
        this.mEndTime = mEndTime;
    }

    public UUID getmId() {
        return mId;
    }

    public DriverLogs(UUID id) {
        mId = id;
    }

    public DriverLogs() {
        this(UUID.randomUUID());
    }


    public String getmVehicleType() {
        return mVehicleType;
    }

    public void setmVehicleType(String mVehicleType) {
        this.mVehicleType = mVehicleType;
    }

    public String getmDriverName() {
        return mDriverName;
    }

    public void setmDriverName(String mDriverName) {
        this.mDriverName = mDriverName;
    }

    public String getmRego() {
        return mRego;
    }

    public void setmRego(String mRego) {
        this.mRego = mRego;
    }

    public String getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getmFirstBreak() {
        return mFirstBreak;
    }

    public void setmFirstBreak(String mFirstBreak) {
        this.mFirstBreak = mFirstBreak;
    }

    public String getmSecondBreak() {
        return mSecondBreak;
    }

    public void setmSecondBreak(String mSecondBreak) {
        this.mSecondBreak = mSecondBreak;
    }

    public String getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    @Override
    public String toString() {
        return ("\n"+this.getmVehicleType()+" "+this.getmDriverName()+" "+this.getmRego()+" "+this.getmStartTime()+" "+this.getmFirstBreak()+" "+this.getmSecondBreak()+" "+this.getmEndTime());
    }
}
