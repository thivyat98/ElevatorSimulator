package com.example.elevatorsimulator;

import java.util.ArrayList;

public class SimulationSettings {
    //variables and arraylists---------------------------------------------------------------
    public String input;
    public int floors;
    public ArrayList<AddPassenger> add_passenger = new ArrayList<>();
    public ArrayList<AddElevator> add_elevator = new ArrayList<>();
    public ArrayList<RequestPercentage> request_percentage = new ArrayList<>();
    public ArrayList<PassengerRequestPercentage> passenger_request_percentage = new ArrayList<>();
    public int number_of_elevators;
    public int run_simulation;
//-------------------------------------------------------------------------------------------------------------
    public SimulationSettings(){
        this.input = "";
    }
    public SimulationSettings(String input){
        this.input = input;
    }
    public String getInput(){
        return input;
    }
    private HelloController helloController;
    public SimulationSettings(HelloController helloController) {
        this.helloController = helloController;
    }
    //add methods--------------------------------------------------------------------------------------------------------
    public ArrayList<AddPassenger> getAddPassenger() {
        return this.add_passenger;
    }
    public void addElevator_type(AddElevator a) {
        add_elevator.add(a);
    }
    public void addRequest_percentage(RequestPercentage a) {
        request_percentage.add(a);
    }
    public void addPassengerRequestPercentage(PassengerRequestPercentage a) {
        passenger_request_percentage.add(a);
    }
    //sets and gets methods--------------------------------------------------------------------------------------------

    //toString print---------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        //printing out the contains of arraylist and variables
        // add_passenger
        result.append("AddPassenger\n");
        for (AddPassenger addPassenger : add_passenger) {
            result.append("passengerID: ").append(addPassenger.passengerID).append(" ");
            result.append("startFloor: ").append(addPassenger.startFloor).append(" ");
            result.append("endFloor: ").append(addPassenger.endFloor).append(" ");
            result.append("passengerType: ").append(addPassenger.passengerType).append(" ");
            result.append("AddPassengerpercentage: ").append(addPassenger.AddPassengerpercentage).append("\n");
        }
        result.append("\n");
        // Elevator types
        result.append("AddElevator\n");
        for (AddElevator addElevator : add_elevator) {
            result.append("elevatorType: ").append(addElevator.elevatorType).append(" ");
            result.append("maxCapacities: ").append(addElevator.maxCapacities).append(" ");
            result.append("elevatorRequestPercentage: ").append(addElevator.elevatorRequestPercentage).append("\n");
        }
        result.append("\n");
        // RequestPercentage
        result.append("RequestPercentage\n");
        for (RequestPercentage requestPercentage : request_percentage) {
            result.append("elevatorType: ").append(requestPercentage.elevatorType).append(" ");
            result.append("RequestPercentagepercentage: ").append(requestPercentage.RequestPercentagepercentage).append("\n");
        }
        result.append("\n");
        // PassengerRequestPercentage
        result.append("PassengerRequestPercentage\n");
        for (PassengerRequestPercentage passengerRequestPercentage : passenger_request_percentage) {
            result.append("passengerType: ").append(passengerRequestPercentage.passengerType).append(" ");
            result.append("PassengerRequestpercentage: ").append(passengerRequestPercentage.PassengerRequestpercentage).append("\n");
        }

        return result.toString();
    }
}
