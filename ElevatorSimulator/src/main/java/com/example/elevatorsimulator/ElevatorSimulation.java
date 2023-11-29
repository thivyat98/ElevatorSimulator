package com.example.elevatorsimulator;

import javafx.beans.property.SimpleMapProperty;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ElevatorSimulation extends SimulationSettings {
    public String line;
    public String fullLine = "";
    SimulationSettings _simulationSettings = new SimulationSettings();
    public boolean initSimulation(String fileName) {
        /*
        _simulationSettings =  readsettingsContent(fileName);
        runSimulation(_simulationSettings);

         */
        SimulationSettings simulationSettings = new SimulationSettings();
        simulationSettings = this.readsettingsContent(fileName);
        this.runSimulation(simulationSettings);
        return true;
    }

    //------------------------------------------------------------------------------
    private SimulationSettings readsettingsContent(String fileName) {
        SimulationSettings _currentSettings = new SimulationSettings();
        try {
            String filePath = fileName;
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("#") == false) {
                    fullLine = fullLine + line + " ";
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            //print the stack trace
            e.printStackTrace();
        }
        _currentSettings = new SimulationSettings(fullLine);
        //Read Information from file. Please use the file manipulators provided in the previous class
        return _currentSettings;
    }

    HelloController helloController;
    public ElevatorSimulation(HelloController helloController) {
        this.helloController = helloController;
    }
    public ElevatorSimulation() {}
    public HelloController hw;
    //---------------------------------------------------------------------------------------------------
    private boolean runSimulation(SimulationSettings _simulationSettings) {

        String runSimulationValue = _simulationSettings.getInput();
        String segments[] = runSimulationValue.split(" ");
        SimulationSettings sm = new SimulationSettings();
        for (int a = 0; a < segments.length; a++) {
            try {
                if (segments[a].contains("floors=")) {
                    String numericPart = segments[a].substring("floors=".length());
                    floors = Integer.parseInt(numericPart);
                    helloController.setfloors(floors);
                } else if (segments[a].contains("add_passenger(")) {
                    String numericPart = segments[a].substring("add_passenger(".length());
                    numericPart = numericPart.substring(0, numericPart.length() - 1);
                    String Add_Passengersegments[] = numericPart.split(",");
                    AddPassenger newPassenger = new AddPassenger(
                            Integer.parseInt(Add_Passengersegments[0]),
                            Integer.parseInt(Add_Passengersegments[1]),
                            Integer.parseInt(Add_Passengersegments[2]),
                            Add_Passengersegments[3],
                            Double.parseDouble(Add_Passengersegments[4])
                    );
                    sm.add_passenger.add(newPassenger);

                } else if (segments[a].contains("elevator_type(")) {
                    String numericPart = segments[a].substring("elevator_type(".length());
                    numericPart = numericPart.substring(0, numericPart.length() - 1);
                    String elevator_typesegments[] = numericPart.split(",");
                    AddElevator newelevator_type = new AddElevator(
                            elevator_typesegments[0],
                            Integer.parseInt(elevator_typesegments[1]),
                            Integer.parseInt(elevator_typesegments[2])
                    );
                    sm.add_elevator.add(newelevator_type);
                } else if (segments[a].contains("request_percentage(") && !segments[a].contains("_request_percentage")) {
                    String numericPart = segments[a].substring("request_percentage(".length());
                    numericPart = numericPart.substring(0, numericPart.length() - 1);
                    String request_percentagesegments[] = numericPart.split(",");
                    RequestPercentage newrequest_percentage = new RequestPercentage(
                            request_percentagesegments[0],
                            Double.parseDouble(request_percentagesegments[1])
                    );
                    sm.request_percentage.add(newrequest_percentage);
                } else if (segments[a].contains("passenger_request_percentage(")) {
                    String numericPart = segments[a].substring("passenger_request_percentage(".length());
                    numericPart = numericPart.substring(0, numericPart.length() - 1);
                    String passenger_request_percentagesegments[] = numericPart.split(",");
                    PassengerRequestPercentage newpassenger_request_percentage = new PassengerRequestPercentage(
                            passenger_request_percentagesegments[0],
                            Double.parseDouble(passenger_request_percentagesegments[1])
                    );
                    sm.passenger_request_percentage.add(newpassenger_request_percentage);
                } else if (segments[a].contains("number_of_elevators=")) {
                    String numericPart = segments[a].substring("number_of_elevators=".length());
                    number_of_elevators = Integer.parseInt(numericPart);
                    helloController.setNumber_of_elevators(number_of_elevators);
                } else if (segments[a].contains("run_simulation=")) {
                    String numericPart = segments[a].substring("run_simulation=".length());
                    run_simulation = Integer.parseInt(numericPart);
                    helloController.setRunSimulation(run_simulation);
                }
            } catch (Exception e) {
                System.out.println("Bad Input: can not read string value");
            }
        }
        System.out.println("fff" + sm + "fff");
        hw = new HelloController(sm);


//---------------------------------------------------------------------------------------------------

        //printing out the contains of arraylist and variables
        //Building parameters

        System.out.println("floors: " + floors);
        System.out.println("");
        System.out.println(sm.toString());
        //Number of elevators in the system
        System.out.println("number_of_elevatorss: " + number_of_elevators);
        System.out.println("");
        //Run simulation for 60 iterations
        System.out.println("run_simulation " + run_simulation);
        return true;
    }

    //-------------------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString();
    }
}

