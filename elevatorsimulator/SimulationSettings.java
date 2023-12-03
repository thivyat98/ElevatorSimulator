package com.example.elevatorsimulator;

import java.util.ArrayList;

public class SimulationSettings {
    //variables and arraylists---------------------------------------------------------------
    public String input;
    public int floors;
    public int number_of_elevators;
    public int run_simulation;
    public SimulationSettings SimulationSettingssm;
    public ArrayList<AddPassenger> add_passenger = new ArrayList<>();
    public ArrayList<AddElevator> add_elevator = new ArrayList<>();
    public ArrayList<RequestPercentage> request_percentage = new ArrayList<>();
    public ArrayList<PassengerRequestPercentage> passenger_request_percentage = new ArrayList<>();
    private static final SimulationSettings instance = new SimulationSettings();
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
    public void setSm(SimulationSettings newSm) {
        this.SimulationSettingssm = newSm;
        System.out.println(SimulationSettingssm);

    }
    //set methods--------------------------------------------------------------------
    public void setRunSimulation(int run_simulation) {
        this.run_simulation = run_simulation;
    }
    public void setfloors(int floors) {
        this.floors = floors;
    }
    public void setNumber_of_elevators(int Number_of_elevators) {
        this.number_of_elevators = Number_of_elevators;
    }
    //get methods--------------------------------------------------------------------
    public int getRunSimulation(){
        return run_simulation;
    }
    public int getfloors(){
        return floors;
    }
    public int getNumber_of_elevators(){
        return number_of_elevators;
    }
    public SimulationSettings getSimulationSettings() {
        return SimulationSettingssm;
    }
    //toString print---------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        //printing out the contains of arraylist and variables
        //floors variable
        result.append("floors: " + floors + " ");
        result.append("\n \n");
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
        result.append("\n");
        //Number of elevators in the system
        result.append("number_of_elevatorss: " + number_of_elevators + " ");
        result.append("\n \n");
        //Run simulation for 60 iterations
        result.append("run_simulation " + run_simulation);

        return result.toString();
    }
}
