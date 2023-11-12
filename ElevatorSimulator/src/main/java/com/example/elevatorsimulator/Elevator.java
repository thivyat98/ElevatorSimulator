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

    public abstract boolean move(direction _direction);
}
