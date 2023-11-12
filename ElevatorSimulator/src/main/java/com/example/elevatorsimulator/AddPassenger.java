package com.example.elevatorsimulator;

public class AddPassenger extends Passenger {
    private double AddPassengerpercentage;
    private String passengerType;
    public AddPassenger(int _passengerID,int _startFloor,int _endFloor,String _passengerType, double _AddPassengerpercentage){
        this.passengerID = _passengerID;
        this.startFloor = _startFloor;
        this.endFloor = _endFloor;
        this.passengerType = _passengerType;
        this.AddPassengerpercentage = _AddPassengerpercentage;

    }
    public boolean requestElevator(direction _direction, SimulationSettings _settings) {
        return false;
    }

}
