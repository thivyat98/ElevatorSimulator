package com.example.elevatorsimulator;

public abstract class Passenger {
    protected int passengerID;
    protected int startFloor;
    protected int endFloor;
    public abstract boolean requestElevator(direction _direction, SimulationSettings _settings);
}
