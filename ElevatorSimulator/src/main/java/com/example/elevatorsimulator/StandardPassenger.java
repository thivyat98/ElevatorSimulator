package com.example.elevatorsimulator;

public class StandardPassenger extends Passenger {
    public StandardPassenger(int _passengerID, int _startFloor, int _endFloor){
        this.passengerID =_passengerID;
        this.startFloor =_startFloor;
        this.endFloor =_endFloor;
        this.pressedButton = false;
    }
    public int getPassengerID(){
        return passengerID;
    };
    public int getstartFloor(){
        return startFloor;
    };
    public int getEndFloor(){
        return endFloor;
    };
    public boolean getPressedButton(){
        return pressedButton;
    }
    public void setPressedButton(boolean a){
        this.pressedButton = a;
    }
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
    @Override
    public String toString() {
        return "StandardPassenger{" +
                "passengerID=" + passengerID +
                ", startFloor=" + startFloor +
                ", endFloor=" + endFloor +
                '}';
    }
}
