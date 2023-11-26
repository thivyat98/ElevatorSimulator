package com.example.elevatorsimulator;

public class AddElevator extends Elevator{
    public AddElevator(){
        this.elevatorType = "";
        this.maxCapacities = 0;
        this.elevatorRequestPercentage = 0.00;

    }
    public AddElevator(String _elevatorType,int _maxCapacities, double _elevatorRequestPercentage){
        this.elevatorType = _elevatorType;
        this.maxCapacities = _maxCapacities;
        this.elevatorRequestPercentage = _elevatorRequestPercentage;


    }

    @Override
    public boolean move(direction _direction) {
        return false;
    }

}
