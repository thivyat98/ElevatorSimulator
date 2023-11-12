package com.example.elevatorsimulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    @FXML
    private AnchorPane floor_;
    @FXML
    private Button HelloButton;
    @FXML
    protected void onHelloButtonClick() {
        ElevatorSimulation _simulation = new ElevatorSimulation();
        _simulation.initSimulation("C:\\Users\\jyi95\\Downloads\\ElevatorSimulator\\src\\main\\java\\com\\example\\elevatorsimulator\\InputFile");

    }
}
