package com.example.elevatorsimulator;

import java.util.ArrayList;

public class StandardElevator extends Elevator{
    private int currentFloor;

    // Constructor for the subclass-------------------------------------------------------------------------------------------------------------
    public StandardElevator(int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage) {
        super(elevatorID, elevatorType, maxCapacities, elevatorRequestPercentage);
        this.currentFloor = 1;
    }
    public StandardElevator(int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage, int _currentFloor) {
        super(elevatorID, elevatorType, maxCapacities, elevatorRequestPercentage);
        this.currentFloor = _currentFloor;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean move(direction _direction) {
        if(_direction == direction.UP){
            currentFloor++;
            System.out.println("StandardElevator, ElevatorID: " + elevatorID + " is going up to " + currentFloor);
        }
        else if(_direction == direction.DOWN)
        {
            if (currentFloor != 1){
                currentFloor--;
                System.out.println("StandardElevator, ElevatorID: " + elevatorID + " is going down to " + currentFloor);
            }
            else
                System.out.println("Incorrect Input");
        }
        else  if(_direction == direction.IDLE){
            System.out.println("StandardElevator, ElevatorID: " + elevatorID + " is idle on " + currentFloor);
        }
        else{
            System.out.println("Incorrect Input");
            return false;
        }
        return true;
    }
    //toString method------------------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Standard Elevator{" +
                "elevatorID=" + elevatorID +
                ", elevatorType='" + elevatorType + '\'' +
                ", maxCapacities=" + maxCapacities +
                ", elevatorRequestPercentage=" + elevatorRequestPercentage +
                ", currentFloor=" + currentFloor +
                '}';
    }
}
