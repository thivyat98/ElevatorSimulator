package com.example.elevatorsimulator;

public class FreightElevator extends Elevator {
    private int currentFloor;

    // Constructor for the subclass-------------------------------------------------------------------------------------------------------------
    public FreightElevator(int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage) {
        super(elevatorID, elevatorType, maxCapacities, elevatorRequestPercentage);
        this.currentFloor = 1;
    }
    public FreightElevator(int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage, int _currentFloor) {
        super(elevatorID, elevatorType, maxCapacities, elevatorRequestPercentage);
        this.currentFloor = _currentFloor;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean move(direction _direction) {
        if(_direction == direction.UP){
            currentFloor++;
            System.out.println("FreightElevator, ElevatorID: " + elevatorID + " is going up to " + currentFloor);
        }
        else if(_direction == direction.DOWN)
        {
            if (currentFloor != 1){
                currentFloor--;
                System.out.println("FreightElevator, ElevatorID: " + elevatorID + " is going down to " + currentFloor);
            }
            else
                System.out.println("Incorrect Input");
        }
        else  if(_direction == direction.IDLE){
            System.out.println("FreightElevator, ElevatorID: " + elevatorID + " is idle on " + currentFloor);
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
        return "Freight Elevator{" +
                "elevatorID=" + elevatorID +
                ", elevatorType='" + elevatorType + '\'' +
                ", maxCapacities=" + maxCapacities +
                ", elevatorRequestPercentage=" + elevatorRequestPercentage +
                ", currentFloor=" + currentFloor +
                '}';
    }
}
