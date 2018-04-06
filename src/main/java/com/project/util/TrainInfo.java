package com.project.util;

public class TrainInfo {

    private int trainId;
    private int date;
    private int seatNum;
    private int startStation;
    private int endStation;
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public void setStartStation(int startStation) {
        this.startStation = startStation;
    }

    public void setEndStation(int endStation) {
        this.endStation = endStation;
    }

    public int getTrainId() {

        return trainId;
    }

    public int getDate() {
        return date;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public int getStartStation() {
        return startStation;
    }

    public int getEndStation() {
        return endStation;
    }
}
