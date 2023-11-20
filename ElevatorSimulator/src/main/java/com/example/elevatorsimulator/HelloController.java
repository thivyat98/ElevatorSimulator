package com.example.elevatorsimulator;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.Event;import com.example.elevatorsimulator.SimulationSettings;


public class HelloController implements Initializable {
    //FXML----------------------------------------------------------------------
    @FXML
    private AnchorPane floor_;
    @FXML
    private Button HelloButton;



    //variables--------------------------------------------------------------------
    private int run_simulation;
    private int floors;
    private int Number_of_elevators;
    private ElevatorSimSceneController elevatorSimSceneController;
    //set methods--------------------------------------------------------------------
    public void setRunSimulation(int run_simulation) {
        this.run_simulation = run_simulation;
    }
    public void setfloors(int floors) {
        this.floors = floors;
    }
    public void setNumber_of_elevators(int Number_of_elevators) {
        this.Number_of_elevators = Number_of_elevators;
    }
    public void setSimulationSettings(SimulationSettings simulationSettings) {

    }

    //get methods--------------------------------------------------------------------
    public int getRunSimulation(){
        return run_simulation;
    }
    public int getfloors(){
        return floors;
    }
    public int getNumber_of_elevators(){
        return Number_of_elevators;
    }

    //---------------------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ElevatorSimulation _simulation = new ElevatorSimulation(this);
        _simulation.initSimulation("C:\\Users\\jyi95\\Downloads\\ElevatorSimulator\\src\\main\\java\\com\\example\\elevatorsimulator\\InputFile");
        run_simulation = getRunSimulation();

    }
/*
    @FXML
    protected void onHelloButtonClick(ActionEvent event) {

        System.out.println("Button clicked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ElevatorSimScene.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        ElevatorSimSceneController elevatorSimSceneController = loader.getController();
        elevatorSimSceneController.setRun_simulation(getRunSimulation());
        elevatorSimSceneController.setHelloController(this);
        Scene ElevatorSimScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(ElevatorSimScene);
        stage.show();

    }

 */
@FXML
protected void onHelloButtonClick(ActionEvent event) {
    System.out.println("Button clicked");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ElevatorSimScene.fxml"));
    Parent root;
    try {
        root = loader.load();
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }
    ElevatorSimSceneController elevatorSimSceneController = loader.getController();
    SimulationSettings simulationSettings = new SimulationSettings();
    elevatorSimSceneController.setSimulationSettings(simulationSettings);
    elevatorSimSceneController.setRun_simulation(getRunSimulation());
    elevatorSimSceneController.setHelloController(this);
    Scene ElevatorSimScene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(ElevatorSimScene);
    stage.show();
}


}
