package com.example.elevatorsimulator;

public class RequestPercentage {
    protected String elevatorType;
    protected double RequestPercentagepercentage;
    public RequestPercentage(){
        this.elevatorType = "";
        this.RequestPercentagepercentage = 0.00;

    }
    public RequestPercentage(String _elevatorType,double _RequestPercentagepercentage){
        this.elevatorType = _elevatorType;
        this.RequestPercentagepercentage = _RequestPercentagepercentage;

    }
    public double getRequestPercentagepercentage(){
        return RequestPercentagepercentage;
    }
    public String getElevatorType(){
        return elevatorType;
    }
}
