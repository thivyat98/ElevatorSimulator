package com.example.elevatorsimulator;

public class AddElevator extends Elevator{
    public AddElevator(String _elevatorType,int _elevatorID,int _maxCapacities){
        this.elevatorType = _elevatorType;
        this.elevatorID = _elevatorID;
        this.maxCapacities = _maxCapacities;

    }

    @Override
    public boolean move(direction _direction) {
        return false;
    }

}
