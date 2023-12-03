package com.example.elevatorsimulator;

public class FreightPassenger extends Passenger {
    @Override
    public boolean requestElevator(direction _direction, SimulationSettings _settings) {
        return false;
    }
}
