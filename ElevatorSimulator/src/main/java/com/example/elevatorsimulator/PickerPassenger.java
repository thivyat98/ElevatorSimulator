package com.example.elevatorsimulator;

public class PickerPassenger extends Passenger{
    protected boolean pickeduped;
    protected int ElevatorID;
    public PickerPassenger(int _passengerID, int _startFloor, int _endFloor, int _ElevatorID){
        this.passengerID =_passengerID;
        this.startFloor =_startFloor;
        this.endFloor =_endFloor;
        this.pressedButton = false;
        this.ElevatorID = _ElevatorID;
    }
    public PickerPassenger(int _passengerID, int _startFloor, int _endFloor, boolean _pressedButton,boolean _pickeduped, int _ElevatorID){
        this.passengerID =_passengerID;
        this.startFloor =_startFloor;
        this.endFloor =_endFloor;
        this.pressedButton = _pressedButton;
        this.pickeduped = _pickeduped;
        this.ElevatorID = _ElevatorID;
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
    public boolean getpickeduped(){
        return pickeduped;
    }
    public int getElevatorID(){
        return ElevatorID;
    };
    public void setPressedButton(boolean a){
        this.pressedButton = a;
    }
    @Override
    public String toString() {
        return "PickerPassenger{" +
                "passengerID=" + passengerID +
                ", startFloor=" + startFloor +
                ", endFloor=" + endFloor +
                ", pressedButton=" + pressedButton +
                ", pickeduped=" + pickeduped +
                ", ElevatorID=" + ElevatorID +
                '}';
    }
}
