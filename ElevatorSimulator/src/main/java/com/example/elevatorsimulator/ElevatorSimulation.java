package com.example.elevatorsimulator;

import javafx.beans.property.SimpleMapProperty;

public class ElevatorSimulation {
    SimulationSettings _simulationSettings = new SimulationSettings();
    public boolean initSimulation(String fileName){
        _simulationSettings =  readsettingsContent(fileName);
        runSimulation(_simulationSettings);
        return true;
    }
    private SimulationSettings readsettingsContent(String fileName){
        SimulationSettings _currentSettings = new SimulationSettings();
        //Read Information from file. Please use the FileMani provided in the previous class
        return _currentSettings;
    }
private boolean runSimulation(SimulationSettings _simulationSettings){

        return true;
}
    @Override
    public String toString() {
        return super.toString();
    }
}
