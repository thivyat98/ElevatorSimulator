package com.example.elevatorsimulator;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;import com.example.elevatorsimulator.SimulationSettings;

public class ElevatorSimSceneController extends SimulationSettings implements Initializable{
//FMML-----------------------------------------------------------------------------------------
    @FXML
    private Text run_simulationCounterText;
    @FXML
    private Timeline timeline;
    @FXML
    private ScrollPane floorsContainer;
    @FXML
    private VBox vBoxElevators;
    @FXML
    private VBox VBoxfloor;
    @FXML
    public ToggleButton playButton;
//variables-----------------------------------------------------------------------------------------
    int i = 0;
    private int Run_simulation;
    private int Floors;
    private int Number_of_Elevators;
    public HelloController helloController;
    public ElevatorSimSceneController elevatorSimSceneController;
    private SimulationSettings simulationSettings;
    public ElevatorSimulation elevatorsimulation;
    private ElevatorSimulation elevatorSimulation;
    private ArrayList<AddPassenger> esscAddPassenger;


//set and get methods-----------------------------------------------------------------------------------------
    // Constructor
    public ElevatorSimSceneController() {
        this.simulationSettings = new SimulationSettings();
    }
    public void setSimulationSettings(SimulationSettings simulationSettings) {
        this.simulationSettings = simulationSettings;
    }
    public void setRun_simulation(int a) {
        this.Run_simulation = a;
    }
    public void setHelloController(SimulationSettings sm) {
        initializeTimeline();
    }
    public void setElevatorSimSceneController(ElevatorSimSceneController elevatorSimSceneController) {
        this.elevatorSimSceneController = elevatorSimSceneController;
    }
    public void setFloors(int Floors) {
        this.Floors = Floors;
    }
    public void setNumber_of_elevators(int Number_of_elevators) {
        this.Number_of_Elevators = Number_of_elevators;
    }
    public void setElevatorSimulation(ElevatorSimulation elevatorSimulation){
        this.elevatorSimulation = elevatorSimulation;
    }
    public void setAddPassenger(ArrayList<AddPassenger> a){
        this.esscAddPassenger = new ArrayList<>(a);
    }


//initializeTimeline (to get helloController working)-------------------------------------------------------------------------
    public void initializeTimeline() {


        VBoxfloor = new VBox();
        floorsContainer.setContent(VBoxfloor);
        for (int a = 0; a < Floors; a++) {
            createFloor(a);
        }




        //--------------------------------------------------------------
        run_simulationCounterText.setText(String.valueOf(i));
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            i++;
            run_simulationCounterText.setText(String.valueOf(i));
        }));
        timeline.setCycleCount(Run_simulation);
        timeline.play();
        System.out.println("Timeline played");
    }
    @FXML
    public void PauseTimeline() {
        if (playButton.getText().contains("II")) {
            timeline.play();
            playButton.setText("▶");
            System.out.println("Timeline resumed");
        } else if (playButton.getText().contains("▶")){
            timeline.pause();
            playButton.setText("II");
            System.out.println("Timeline paused");
        }
    }
//Floor-------------------------------------------------------------------------------------------------
    private void createFloor(int floorIndex) {
        HBox hBoxFloor = createFloorPane(floorIndex);
        VBoxfloor.getChildren().add(hBoxFloor);
        System.out.println("Floor " + (floorIndex + 1) + " created.");
        for (int e = 0; e < Number_of_Elevators ; e++) {
            int quarter = Number_of_Elevators / 4;
            if (e < quarter) {
                createElevator(hBoxFloor, e, floorIndex, "StandardElevator");
            } else if (e < 2 * quarter) {
                createElevator(hBoxFloor, e, floorIndex, "ExpressElevator");
            } else if (e < 3 * quarter) {
                createElevator(hBoxFloor, e, floorIndex, "FreightElevator");
            } else if (Number_of_Elevators % 2 == 1){
                createElevator(hBoxFloor, e, floorIndex, "StandardElevator");
            } else if (e <= Number_of_Elevators) {
                createElevator(hBoxFloor, e, floorIndex, "GlassElevator");
            }
        }
    }
    private HBox createFloorPane(int floorIndex) {
        HBox hBoxFloor = new HBox();
        hBoxFloor.setAlignment(Pos.BOTTOM_LEFT);
        hBoxFloor.setStyle("-fx-background-color: grey; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color:#ADD8E6; ");
        hBoxFloor.setPrefWidth(1200);
        hBoxFloor.setPrefHeight(150);
        return hBoxFloor;
    }
//Elevator-------------------------------------------------------------------------------------------------
    private ToggleButton createElevatorToggleButton(int elevatorIndex, boolean firstfloor) {
        ToggleButton elevatorButton = new ToggleButton();

        if (firstfloor == false){
            Image elevatorImage = new Image("C:\\Users\\jyi95\\Downloads\\elevatorEmpty.png");
            ImageView imageView = new ImageView(elevatorImage);
            imageView.setFitWidth(100);
            imageView.setFitHeight(120);
            elevatorButton = new ToggleButton("", imageView);
            StackPane.setAlignment(imageView, Pos.BOTTOM_CENTER);

        }
        else if (firstfloor == true){
            Image elevatorImageclosed = new Image("C:\\Users\\jyi95\\Downloads\\elevatorClosed.png");
            ImageView imageViewclosed  = new ImageView(elevatorImageclosed);
            imageViewclosed.setFitWidth(100);
            imageViewclosed.setFitHeight(120);
            elevatorButton = new ToggleButton("", imageViewclosed);

            StackPane.setAlignment(imageViewclosed, Pos.BOTTOM_CENTER);

        }
        elevatorButton.setStyle("-fx-background-color: lightgray; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color:#000; ");
        HBox.setMargin(elevatorButton, new Insets(0, 10, 0, 0));

        return elevatorButton;
    }

    private void createElevator(HBox hBoxFloor, int elevatorIndex, int floorNum, String elevatortype) {
        boolean firstfloor = false;
        int currentfloorNum = Floors - floorNum;
        if (currentfloorNum == 1){
            firstfloor = true;
        }
        Label elevatorLabel = new Label(elevatortype);
        elevatorLabel.setAlignment(Pos.CENTER);
        ToggleButton elevatorButton = createElevatorToggleButton(elevatorIndex, firstfloor);
        VBox elevatorVBox = new VBox();
        elevatorVBox.setAlignment(Pos.CENTER);
        elevatorVBox.getChildren().addAll(elevatorLabel, elevatorButton);
        hBoxFloor.getChildren().add(elevatorVBox);

        if (elevatorIndex + 1 == Number_of_Elevators) {
            for (int a = 0; a < esscAddPassenger.size(); a++) {
                AddPassenger passenger = esscAddPassenger.get(a);
                if (passenger.getstartFloor() == currentfloorNum + 1) {
                    createPassengerBox(passenger.getPassengerID(), Floors - passenger.getstartFloor(), passenger);
                }
            }
        }

        elevatorButton.setOnAction(event -> {
            boolean isElevatorOnFloor = elevatorButton.isSelected();
            System.out.println("Elevator " + (elevatorIndex + 1) + " is on floor: " + currentfloorNum + ", " + isElevatorOnFloor);
        });
    }



//Passenger-------------------------------------------------------------------------------------------------
    /*
    private HBox createPassengerBox(int passengerID, int startFloor, AddPassenger passenger) {
        HBox passengerBox = new HBox();
        passengerBox.setAlignment(Pos.CENTER);
        passengerBox.setStyle("-fx-background-color: #FFD700; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: #000;");
        passengerBox.setPrefSize(40, 40);
        Label label = new Label(String.valueOf(passengerID));
        passengerBox.getChildren().add(label);
        addPassengerToFloor(passengerBox, startFloor, passenger);

        return passengerBox;
    }

     */

    private HBox createPassengerBox(int passengerID, int startFloor, AddPassenger passenger) {
        HBox passengerBox = new HBox();
        passengerBox.setAlignment(Pos.BOTTOM_CENTER);

        passengerBox.setPrefSize(90, 90);
        System.out.println(passenger.getpassengerType());
        ImageView imageView = null;
        if (passenger.getpassengerType().equals("\"Standard\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\StandardPassenger.png");
            imageView = new ImageView(passengerImage);
        } else if (passenger.getpassengerType().equals("\"VIP\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\VIPPassenger.png");
            imageView = new ImageView(passengerImage);
        } else if (passenger.getpassengerType().equals("\"Freight\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\FreightPassenger.png");
            imageView = new ImageView(passengerImage);
        } else if (passenger.getpassengerType().equals("\"Glass\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\GlassPassenger.png");
            imageView = new ImageView(passengerImage);
        }

        if (imageView != null) {
            //System.out.println("sjukhnfiush");
            imageView.setFitWidth(60);
            imageView.setFitHeight(60);
            passengerBox.getChildren().add(imageView);
        }


        HBox.setMargin(passengerBox, new Insets(10, 0, 0, 0));
        addPassengerToFloor(passengerBox, startFloor, passenger);

        return passengerBox;
    }



    private void addPassengerToFloor(HBox PassengerBox, int startFloor, AddPassenger passenger) {
        HBox floorContainer = (HBox) VBoxfloor.getChildren().get(startFloor - 1);
        floorContainer.getChildren().add(PassengerBox);
        System.out.println("Passenger " + passenger.getPassengerID() + " on floor " + passenger.getstartFloor());
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
