package com.example.elevatorsimulator;

import java.util.ArrayList;

public class SimulationSettings {
    public String input;
    public int floors;
    public ArrayList<AddPassenger> add_passenger = new ArrayList<>();
    public ArrayList<AddElevator> elevator_type = new ArrayList<>();
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
    public ArrayList<AddPassenger> getAddPassengerList() {
        System.out.println(add_passenger);
        return add_passenger;
    }
    private HelloController helloController;

    public SimulationSettings(HelloController helloController) {
        this.helloController = helloController;
    }
    public void addAdd_passenger(AddPassenger a) {
        add_passenger.add(a);
    }
    public void addElevator_type(AddElevator a) {
        elevator_type.add(a);
    }
    public void addRequest_percentage(RequestPercentage a) {
        request_percentage.add(a);
    }
    public void addPassengerRequestPercentage(PassengerRequestPercentage a) {
        passenger_request_percentage.add(a);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
