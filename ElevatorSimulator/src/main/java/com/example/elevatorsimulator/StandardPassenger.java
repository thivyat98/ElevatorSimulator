package com.example.elevatorsimulator;

public class StandardPassenger extends Passenger {
    @Override
    public boolean requestElevator(direction _direction, SimulationSettings _settings) {
        if(_direction == direction.UP){

        }
        else if(_direction == direction.DOWN)
        {

        }
        else{
            System.out.println("Incorrect Input");
            return false;
        }
        return true;
    }
}
