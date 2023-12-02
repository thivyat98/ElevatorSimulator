package com.example.elevatorsimulator;

public class AddPassenger  {
    protected int passengerID;
    protected int startFloor;
    protected int endFloor;
    protected double AddPassengerpercentage;
    protected String passengerType;
    public AddPassenger() {
        this.passengerID = 0;
        this.startFloor = 0;
        this.endFloor = 0;
        this.passengerType = "";
        this.AddPassengerpercentage = 0.00;
    }
    public AddPassenger(int _passengerID,int _startFloor,int _endFloor,String _passengerType, double _AddPassengerpercentage) {
        this.passengerID = _passengerID;
        this.startFloor = _startFloor;
        this.endFloor = _endFloor;
        this.passengerType = _passengerType;
        this.AddPassengerpercentage = _AddPassengerpercentage;
    }

    public String toString() {
        return "AddPassenger{" +
                "passengerID=" + passengerID +
                ", startFloor=" + startFloor +
                ", endFloor=" + endFloor +
                ", passengerType='" + passengerType + '\'' +
                ", AddPassengerpercentage=" + AddPassengerpercentage +
                '}';
    }
    public int getPassengerID(){
        return passengerID;
    }
    public int getstartFloor(){
        return startFloor;
    }
    public int getendFloor(){
        return endFloor;
    }
}
