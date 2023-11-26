package com.example.elevatorsimulator;

public class PassengerRequestPercentage {
    protected String passengerType;
    protected double PassengerRequestpercentage;
    public PassengerRequestPercentage(){
        this.passengerType = "";
        this.PassengerRequestpercentage = 0.00;

    }
    public PassengerRequestPercentage(String _passengerType,double _PassengerRequestpercentage){
        this.passengerType = _passengerType;
        this.PassengerRequestpercentage = _PassengerRequestpercentage;

    }

}
