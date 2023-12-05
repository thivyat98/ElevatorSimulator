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
import java.util.*;

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

public class ElevatorSimSceneController extends SimulationSettings{
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
    @FXML
    private ScrollPane HistoryLog;
    @FXML
    private TextArea historyLogTextArea;

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
    private ArrayList<AddElevator> esscAddElevator;
    private ArrayList<RequestPercentage> esscRequestPercentage;
    private ArrayList<PassengerRequestPercentage> esscPassengerRequestPercentage;
    private ArrayList<Elevator> essccurrentElevators = new ArrayList<>();;
    public ArrayList<StandardPassenger> sp = new ArrayList<>();;
    public ArrayList<VIPPassenger> vp = new ArrayList<>();;
    public ArrayList<GlassPassenger> gp = new ArrayList<>();;
    public ArrayList<FreightPassenger> fp= new ArrayList<>();;


//set and get methods-----------------------------------------------------------------------------------------
    // Constructor
    public ElevatorSimSceneController() {
        this.simulationSettings = new SimulationSettings();
    }
    public void setRun_simulation(int a) {
        this.Run_simulation = a;
    }
    public void setFloors(int Floors) {
        this.Floors = Floors;
    }
    public void setNumber_of_elevators(int Number_of_elevators) {
        this.Number_of_Elevators = Number_of_elevators;
    }
    public void setAddPassenger(ArrayList<AddPassenger> a){
        this.esscAddPassenger = new ArrayList<>(a);
    }
    public void setAddElevator(ArrayList<AddElevator> a){
        this.esscAddElevator = new ArrayList<>(a);
    }
    public void setRequestPercentage(ArrayList<RequestPercentage> a) {
        this.esscRequestPercentage = new ArrayList<>(a);
    }
    public void setPassengerRequestPercentage(ArrayList<PassengerRequestPercentage> a) {
        this.esscPassengerRequestPercentage = new ArrayList<>(a);
    }
    public void setcurrentElevators(ArrayList<Elevator> a){
        this.essccurrentElevators = new ArrayList<>(a);
    }
//initializeTimeline (to get helloController working)-------------------------------------------------------------------------
    public void initializeTimeline() {
        VBoxfloor = new VBox();
        floorsContainer.setContent(VBoxfloor);
        for (int a = 0; a < Floors; a++) {
            createFloor(a);
        }
        for (int a = 0; a < essccurrentElevators.size(); a++) {
            System.out.println(essccurrentElevators.get(a).toString());
        }
        //passengerRequestLogic();
        passengerRequestLogic();
        historyLogTextArea.setEditable(false);

        // Add the TextArea to your container (replace VBoxfloor with your actual container)
        HistoryLog.setContent(historyLogTextArea);
        //-------------------------------------------------------------------------------------------------------------------
        //TimeLine-----------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------------
        run_simulationCounterText.setText(String.valueOf(i));
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            i++;
            run_simulationCounterText.setText(String.valueOf(i));
            elevatorlogic();
            historyLogTextArea.appendText("Iterations: " + i + "\n");
            passengerRequestLogic();
            if (i == Run_simulation) {
                historyLogTextArea.appendText("Timeline finished\n");
            }
        }));
        timeline.setCycleCount(Run_simulation);
        timeline.play();
        System.out.println("Timeline played");
        historyLogTextArea.appendText("Timeline played \n");
    }
//----------------------------------------------------------------------------------------------------------------------------
//PauseTimeLine method--------------------------------------------------------------------------------------------------------
    @FXML
    public void PauseTimeline() {
        if (playButton.getText().contains("II") &&  timeline.getStatus() == Animation.Status.PAUSED) {
            timeline.play();
            playButton.setText("▶");
            System.out.println("Timeline resumed");
            historyLogTextArea.appendText("Timeline resumed \n");
        } else if (playButton.getText().contains("▶") && timeline.getStatus() == Animation.Status.RUNNING){
            timeline.pause();
            playButton.setText("II");
            System.out.println("Timeline paused");
            historyLogTextArea.appendText("Timeline paused \n");
        }
    }
//Floor-------------------------------------------------------------------------------------------------------------------------
    private void createFloor(int floorIndex) {
        HBox hBoxFloor = createFloorPane(floorIndex);
        VBoxfloor.getChildren().add(hBoxFloor);
        System.out.println("Floor " + (Floors-floorIndex ) + " created.");
        TextArea TextAreaFloorNum = new TextArea();
        TextAreaFloorNum.setText(String.valueOf(Floors - floorIndex));
        TextAreaFloorNum.setStyle("-fx-font-size: 20.5; -fx-font-weight: bold; -fx-alignment: center; -fx-control-inner-background: darkgray;");
        TextAreaFloorNum.setMaxWidth(50);
        TextAreaFloorNum.setMinWidth(50);
        TextAreaFloorNum.setEditable(false);
        HBox floorContentHBox = new HBox(TextAreaFloorNum);
        floorContentHBox.setAlignment(Pos.CENTER_LEFT);
        hBoxFloor.getChildren().add(floorContentHBox);
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
    private ToggleButton createElevatorToggleButton(int elevatorIndex, boolean firstfloor, String elevatortype) {
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
            //int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage
            //essccurrentElevators.add(elevatorIndex + 1, elevatortype, add_elevator);
        }
        elevatorButton.setStyle("-fx-background-color: lightgray; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color:#000; ");
        HBox.setMargin(elevatorButton, new Insets(0, 10, 0, 0));

        return elevatorButton;
    }

    private void createElevator(HBox hBoxFloor, int elevatorIndex, int floorNum, String elevatortype) {
        //-------------------------------------------------------------------------------------------------------------
        boolean firstfloor = false;
        int currentfloorNum = Floors - floorNum;
        if (currentfloorNum == 1){
            firstfloor = true;
        }
        //-------------------------------------------------------------------------------------------------------------
        Label elevatorLabel = new Label(elevatortype);
        elevatorLabel.setAlignment(Pos.CENTER);
        ToggleButton elevatorButton = createElevatorToggleButton(elevatorIndex, firstfloor, elevatortype);
        //-------------------------------------------------------------------------------------------------------------
        VBox elevatorVBox = new VBox();
        elevatorVBox.setAlignment(Pos.CENTER);
        elevatorVBox.getChildren().addAll(elevatorLabel, elevatorButton);
        hBoxFloor.getChildren().add(elevatorVBox);
        //-------------------------------------------------------------------------------------------------------------
        createElevatorData(elevatorIndex, elevatortype, floorNum);
        //-------------------------------------------------------------------------------------------------------------
        if (elevatorIndex + 1 == Number_of_Elevators) {
            for (int a = 0; a < esscAddPassenger.size(); a++) {
                AddPassenger passenger = esscAddPassenger.get(a);
                if (passenger.getstartFloor() == currentfloorNum + 1) {
                    createPassengerBox(passenger.getPassengerID(), Floors - passenger.getstartFloor(), passenger.getendFloor(),passenger);
                }
            }
        }
        elevatorButton.setOnAction(event -> {
            boolean isElevatorOnFloor = elevatorButton.isSelected();
            System.out.println("Elevator " + (elevatorIndex + 1) + " is on floor: " + currentfloorNum + ", " + isElevatorOnFloor);
        });
    }

//Passenger-------------------------------------------------------------------------------------------------
    private HBox createPassengerBox(int passengerID, int startFloor,int endFloor, AddPassenger passenger) {
        HBox passengerBox = new HBox();
        passengerBox.setAlignment(Pos.BOTTOM_CENTER);
        passengerBox.setPrefSize(90, 90);
        ImageView imageView = null;
        if (passenger.getpassengerType().equals("\"Standard\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\StandardPassenger.png");
            imageView = new ImageView(passengerImage);
            sp.add(new StandardPassenger(passengerID, Floors - startFloor, endFloor));
            System.out.println(sp.get(sp.size() - 1).toString());
        } else if (passenger.getpassengerType().equals("\"VIP\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\VIPPassenger.png");
            imageView = new ImageView(passengerImage);
            vp.add(new VIPPassenger(passengerID, Floors - startFloor,endFloor));
            System.out.println(vp.get(vp.size() - 1).toString());
        } else if (passenger.getpassengerType().equals("\"Freight\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\FreightPassenger.png");
            imageView = new ImageView(passengerImage);
            fp.add(new FreightPassenger(passengerID, Floors - startFloor,endFloor));
            System.out.println(fp.get(fp.size() - 1).toString());
        } else if (passenger.getpassengerType().equals("\"Glass\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\GlassPassenger.png");
            imageView = new ImageView(passengerImage);
            gp.add(new GlassPassenger(passengerID, Floors - startFloor,endFloor));
            System.out.println(gp.get(gp.size() - 1).toString());
        }

        if (imageView != null) {
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
//elevator logic and data methods---------------------------------------------------------------------------------

    private ArrayList<Elevator> createElevatorData(int elevatorid, String elevatortype, int floorNum){
        if (floorNum == 1) {
            if (elevatortype.equals("StandardElevator")) {
                for (int f = 0; f < esscAddElevator.size(); f++) {
                    if (esscAddElevator.get(f).getelevatorType().equals("\"StandardElevator\"")) {
                        StandardElevator currentElevator = new StandardElevator(
                                elevatorid,
                                esscAddElevator.get(f).getelevatorType(),
                                esscAddElevator.get(f).getmaxCapacities(),
                                esscAddElevator.get(f).getelevatorRequestPercentage()
                        );
                        essccurrentElevators.add(currentElevator);
                    }
                }
            } else if (elevatortype.equals("ExpressElevator")) {
                for (int f = 0; f < esscAddElevator.size(); f++) {
                    if (esscAddElevator.get(f).getelevatorType().equals("\"ExpressElevator\"")) {
                        ExpressElevator currentElevator = new ExpressElevator(
                                elevatorid,
                                esscAddElevator.get(f).getelevatorType(),
                                esscAddElevator.get(f).getmaxCapacities(),
                                esscAddElevator.get(f).getelevatorRequestPercentage()
                        );
                        essccurrentElevators.add(currentElevator);

                    }
                }
            } else if (elevatortype.equals("FreightElevator")) {
                for (int f = 0; f < esscAddElevator.size(); f++) {
                    if (esscAddElevator.get(f).getelevatorType().equals("\"FreightElevator\"")) {
                        FreightElevator currentElevator = new FreightElevator(
                                elevatorid,
                                esscAddElevator.get(f).getelevatorType(),
                                esscAddElevator.get(f).getmaxCapacities(),
                                esscAddElevator.get(f).getelevatorRequestPercentage()
                        );

                        essccurrentElevators.add(currentElevator);

                    }
                }
            } else if (elevatortype.equals("GlassElevator")) {
                for (int f = 0; f < esscAddElevator.size(); f++) {
                    if (esscAddElevator.get(f).getelevatorType().equals("\"GlassElevator\"")) {
                        GlassElevator currentElevator = new GlassElevator(
                                elevatorid,
                                esscAddElevator.get(f).getelevatorType(),
                                esscAddElevator.get(f).getmaxCapacities(),
                                esscAddElevator.get(f).getelevatorRequestPercentage()
                        );
                        essccurrentElevators.add(currentElevator);
                    }
                }

            }
        }
        return essccurrentElevators;
    }
    private void elevatorlogic(){

    }
//passengerRequestLogic------------------------------------------------------------------------------------------------------------------
    private void passengerRequestLogic() {
        Random random1 = new Random();
        boolean booleanPassengerRequest = false;
        ArrayList<Elevator> availableessccurrentElevators = new ArrayList<>();
        ;
        //for loop for the amount of sp (2)
        for (int t = 0; t < sp.size(); t++) {
            //for loop for the amount of PassengerRequestPercentage (4)
            for (int f = 0; f < esscPassengerRequestPercentage.size(); f++) {
                if (esscPassengerRequestPercentage.get(f).getpassengerType().equals("\"Standard\"")) {
                    double spasssengerPickElevator = esscPassengerRequestPercentage.get(f).getPassengerRequestpercentage();
                    double randomNum1 = random1.nextDouble() * 100;
                    for (int j = 0; j < essccurrentElevators.size(); j++) {
                        if (randomNum1 <= spasssengerPickElevator && sp.get(t).getstartFloor() > essccurrentElevators.get(j).getCurrentFloor() && sp.get(t).getPressedButton() == false) {
                            booleanPassengerRequest = true;
                            availableessccurrentElevators.add(essccurrentElevators.get(j));
                        }
                    }
                }
            }
            if (booleanPassengerRequest == true) {
                sp.get(t).setPressedButton(true);
                historyLogTextArea.appendText("PassengerID: " + sp.get(t).getPassengerID() + " PassengerType: Standard \n");
                System.out.println("PassengerID: " + sp.get(t).getPassengerID() + " PassengerType: Standard");

            }
            randomElevatorPick(availableessccurrentElevators);
            booleanPassengerRequest = false;
            availableessccurrentElevators.clear();
        }
        Random random2 = new Random();
        for (int t = 0; t < vp.size(); t++) {
            for (int f = 0; f < esscPassengerRequestPercentage.size(); f++) {//4
                if (esscPassengerRequestPercentage.get(f).getpassengerType().equals("\"VIP\"")) {
                    double vpasssengerPickElevator = esscPassengerRequestPercentage.get(f).getPassengerRequestpercentage();
                    double randomNum2 = random2.nextDouble() * 100;
                    for (int j = 0; j < essccurrentElevators.size(); j++) {
                        if (randomNum2 <= vpasssengerPickElevator && vp.get(t).getstartFloor() > essccurrentElevators.get(j).getCurrentFloor()&& vp.get(t).getPressedButton() == false) {
                            booleanPassengerRequest = true;
                            availableessccurrentElevators.add(essccurrentElevators.get(j));
                        }
                    }
                }
            }

            if (booleanPassengerRequest == true){
                vp.get(t).setPressedButton(true);
                historyLogTextArea.appendText("PassengerID: " + vp.get(t).getPassengerID() + " PassengerType: VIP \n");
                System.out.println("PassengerID: "+vp.get(t).getPassengerID() + " PassengerType: VIP");
            }
            randomElevatorPick(availableessccurrentElevators);
            booleanPassengerRequest = false;
            availableessccurrentElevators.clear();
        }
        Random random3 = new Random();
        for (int t = 0; t < fp.size(); t++) {
            for (int f = 0; f < esscPassengerRequestPercentage.size(); f++) {
                if (esscPassengerRequestPercentage.get(f).getpassengerType().equals("\"Freight\"")) {
                    double fpasssengerPickElevator = esscPassengerRequestPercentage.get(f).getPassengerRequestpercentage();
                    double randomNum3 = random3.nextDouble() * 100;
                    for (int j = 0; j < essccurrentElevators.size(); j++) {
                        if (randomNum3 <= fpasssengerPickElevator  && fp.get(t).getstartFloor() > essccurrentElevators.get(j).getCurrentFloor()&& fp.get(t).getPressedButton() == false) {
                            booleanPassengerRequest = true;
                            availableessccurrentElevators.add(essccurrentElevators.get(j));
                        }
                    }
                }
            }

            if (booleanPassengerRequest == true){
                fp.get(t).setPressedButton(true);
                historyLogTextArea.appendText("PassengerID: " + fp.get(t).getPassengerID() + " PassengerType: Freight \n");
                System.out.println("PassengerID: "+fp.get(t).getPassengerID() + " PassengerType: Freight");
            }
            randomElevatorPick(availableessccurrentElevators);
            booleanPassengerRequest = false;
            availableessccurrentElevators.clear();
        }
        Random random4 = new Random();
        for (int t = 0; t < gp.size(); t++) {
            //for loop for the amount of PassengerRequestPercentage (4)
            for (int f = 0; f < esscPassengerRequestPercentage.size(); f++) {
                if (esscPassengerRequestPercentage.get(f).getpassengerType().equals("\"Glass\"")) {
                    double gpasssengerPickElevator = esscPassengerRequestPercentage.get(f).getPassengerRequestpercentage();
                    double randomNum4 = random4.nextDouble() * 100;
                    for (int j = 0; j < essccurrentElevators.size(); j++) {
                        if (randomNum4 <= gpasssengerPickElevator && gp.get(t).getstartFloor() > essccurrentElevators.get(j).getCurrentFloor()&& gp.get(t).getPressedButton() == false) {
                            booleanPassengerRequest = true;
                            availableessccurrentElevators.add(essccurrentElevators.get(j));
                        }
                    }
                }
            }

            if (booleanPassengerRequest == true){
                gp.get(t).setPressedButton(true);
                historyLogTextArea.appendText("PassengerID: " + gp.get(t).getPassengerID() + " PassengerType: Glass \n");
                System.out.println("PassengerID: "+gp.get(t).getPassengerID()+ " PassengerType: Glass");
            }
            randomElevatorPick(availableessccurrentElevators);
            booleanPassengerRequest = false;
            availableessccurrentElevators.clear();
        }
    }


    private void randomElevatorPick(ArrayList<Elevator> availableessccurrentElevators) {
        Random random = new Random();
        double randomValue = random.nextDouble() * 100;
        for (int l = 0; l < availableessccurrentElevators.size(); l++) {
            double requestPercentage = availableessccurrentElevators.get(l).getElevatorRequestPercentage();
            if (randomValue <= requestPercentage) {
                System.out.println("Selected Elevator: " + availableessccurrentElevators.get(l).getelevatorID());
                break;
            }
            randomValue -= requestPercentage;
        }
    }




}
