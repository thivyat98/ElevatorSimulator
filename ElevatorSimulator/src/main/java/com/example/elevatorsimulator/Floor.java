package com.example.elevatorsimulator;

import java.util.ArrayList;

public class Floor {
    //On a floor the elevators starts a Queue and then it ends
    ArrayList<Passenger> waitQueue = new ArrayList<>();
    ArrayList<Passenger> completedQueue = new ArrayList<>();
    //
    ArrayList<Elevator> currentElevators = new ArrayList<>();

    public void setCurrentElevators(ArrayList<Elevator> currentElevators) {
        this.currentElevators = currentElevators;
    }
}
