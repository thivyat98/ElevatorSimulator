package com.example.elevatorsimulator;
enum direction{
    UP,
    IDLE,
    DOWN
}

public abstract class Elevator {
    protected int elevatorID;
    protected String elevatorType;
    protected int maxCapacities;
    protected double elevatorRequestPercentage;
    private int currentFloor;
    public Elevator(){
        this.elevatorID = 0;
        this.elevatorType = "";
        this.maxCapacities = 0;
        this.elevatorRequestPercentage = 0;
        this.currentFloor = 1;
    }
    public Elevator(int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage) {
        this.elevatorID = elevatorID;
        this.elevatorType = elevatorType;
        this.maxCapacities = maxCapacities;
        this.elevatorRequestPercentage = elevatorRequestPercentage;
        this.currentFloor = 1;
    }
    public Elevator(int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage, int _currentFloor) {
        this.elevatorID = elevatorID;
        this.elevatorType = elevatorType;
        this.maxCapacities = maxCapacities;
        this.elevatorRequestPercentage = elevatorRequestPercentage;
        this.currentFloor = _currentFloor;
    }
    public int getelevatorID(){
        return elevatorID;
    }
    public int getCurrentFloor(){
        return currentFloor;
    }
    public double getElevatorRequestPercentage(){
        return elevatorRequestPercentage;
    }
    public abstract boolean move(direction _direction);
    @Override
    public String toString() {
        return "Elevator{" +
                "elevatorID=" + elevatorID +
                ", elevatorType='" + elevatorType + '\'' +
                ", maxCapacities=" + maxCapacities +
                ", elevatorRequestPercentage=" + elevatorRequestPercentage +
                ", currentFloor=" + currentFloor +
                '}';
    }
}
