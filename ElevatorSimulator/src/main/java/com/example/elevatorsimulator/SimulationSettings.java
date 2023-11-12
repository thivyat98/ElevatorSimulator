package com.example.elevatorsimulator;

import java.util.ArrayList;

public class SimulationSettings {
    public String input;
    public int floors;
    public ArrayList<AddPassenger> add_passenger = new ArrayList<>();
    public ArrayList<Elevator> elevator_type = new ArrayList<>();
    public ArrayList<RequestPercentage> request_percentage = new ArrayList<>();
    public ArrayList<PassengerRequestPercentage> passenger_request_percentage = new ArrayList<>();
    public int number_of_elevators;
    public int run_simulation;

    public SimulationSettings(){
        this.input = "";
    }
    public SimulationSettings(String input){
        this.input = input;
    }
    public String getInput(){
        return input;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
