package com.example.elevatorsimulator;

public class GlassPassenger extends Passenger {
    @Override
    public boolean requestElevator(direction _direction, SimulationSettings _settings) {
        return false;
    }
}
