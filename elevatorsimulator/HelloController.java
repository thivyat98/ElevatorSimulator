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


public class HelloController extends SimulationSettings implements Initializable {
    //FXML----------------------------------------------------------------------
    @FXML
    private AnchorPane floor_;
    @FXML
    private Button HelloButton;

    //variables--------------------------------------------------------------------
    private ElevatorSimSceneController elevatorSimSceneController;
    public SimulationSettings Hellosm;

    //---------------------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ElevatorSimulation _simulation = new ElevatorSimulation(this);
        _simulation.initSimulation("C:\\Users\\jyi95\\Downloads\\ElevatorSimulator\\src\\main\\java\\com\\example\\elevatorsimulator\\InputFile");

    }
    public HelloController(){
        this.Hellosm = new SimulationSettings();
    }
    public void setSimulationSettings(SimulationSettings simulationSettings) {
        this.Hellosm = simulationSettings;
    }
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
    //elevatorSimSceneController.setSimulationSettings(Hellosm);
    elevatorSimSceneController.setRun_simulation(Hellosm.getRunSimulation());
    elevatorSimSceneController.setFloors(Hellosm.getfloors());
    elevatorSimSceneController.setNumber_of_elevators(Hellosm.getNumber_of_elevators());
    elevatorSimSceneController.setAddPassenger(Hellosm.getAddPassenger());
    elevatorSimSceneController.initializeTimeline();
    //elevatorSimSceneController.setHelloController(SimulationSettingssm);
    //elevatorSimSceneController.setSimulationSettings(sm);
    Scene ElevatorSimScene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(ElevatorSimScene);
    stage.show();
}


}
