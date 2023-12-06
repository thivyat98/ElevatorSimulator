package com.example.elevatorsimulator;

public class ExpressElevator extends Elevator{
    private int currentFloor;

    // Constructor for the subclass-------------------------------------------------------------------------------------------------------------
    public ExpressElevator(int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage) {
        super(elevatorID, elevatorType, maxCapacities, elevatorRequestPercentage);
        this.currentFloor = 1;
    }
    public ExpressElevator(int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage, int _currentFloor) {
        super(elevatorID, elevatorType, maxCapacities, elevatorRequestPercentage);
        this.currentFloor = _currentFloor;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean move(direction _direction) {
        if(_direction == direction.UP){
            currentFloor++;
            System.out.println("ExpressElevator, ElevatorID: " + elevatorID + " is going up to " + currentFloor);
        }
        else if(_direction == direction.DOWN)
        {
            if (currentFloor != 1){
                currentFloor--;
                System.out.println("ExpressElevator, ElevatorID: " + elevatorID + " is going down to " + currentFloor);
            }
            else
                System.out.println("Incorrect Input");
        }
        else  if(_direction == direction.IDLE){
            System.out.println("ExpressElevator, ElevatorID: " + elevatorID + " is idle on " + currentFloor);
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
        return "Express Elevator{" +
                "elevatorID=" + elevatorID +
                ", elevatorType='" + elevatorType + '\'' +
                ", maxCapacities=" + maxCapacities +
                ", elevatorRequestPercentage=" + elevatorRequestPercentage +
                ", currentFloor=" + currentFloor +
                '}';
    }
}
