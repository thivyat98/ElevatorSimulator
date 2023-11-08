package com.example.elevatorsimulator;

import java.util.ArrayList;

public class SimulationSettings {
    public int floors;
    public ArrayList<AddPassenger> add_passenger = new ArrayList<>();
    public ArrayList<PassengerRequestPercentage> passenger_request_percentage = new ArrayList<>();
    public int number_of_elevators;
    public int run_simulation;

    @Override
    public String toString() {
        return super.toString();
    }
}
