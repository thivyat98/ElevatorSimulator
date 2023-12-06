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

public class ElevatorSimSceneController extends SimulationSettings {
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
    private int pickedElevatorID;
    private int pickerpassengerID;
    private int pickerpassengersf;
    private int pickerpassengeref;
    public HelloController helloController;
    public ElevatorSimSceneController elevatorSimSceneController;
    private SimulationSettings simulationSettings;
    public ElevatorSimulation elevatorsimulation;
    private ElevatorSimulation elevatorSimulation;
    private ArrayList<AddPassenger> esscAddPassenger;
    private ArrayList<AddElevator> esscAddElevator;
    private ArrayList<RequestPercentage> esscRequestPercentage;
    private ArrayList<PassengerRequestPercentage> esscPassengerRequestPercentage;
    private ArrayList<Elevator> essccurrentElevators = new ArrayList<>();
    public ArrayList<StandardPassenger> sp = new ArrayList<>();
    public ArrayList<VIPPassenger> vp = new ArrayList<>();
    public ArrayList<GlassPassenger> gp = new ArrayList<>();
    public ArrayList<FreightPassenger> fp = new ArrayList<>();
    public ArrayList<StandardElevator> se = new ArrayList<>();
    public ArrayList<ExpressElevator> ee = new ArrayList<>();
    public ArrayList<GlassElevator> ge = new ArrayList<>();
    public ArrayList<FreightElevator> fe = new ArrayList<>();
    public ArrayList<PickerPassenger> pickedPassengers = new ArrayList<>();
    public ArrayList<Passenger> waitQueue = new ArrayList<>();
    public ArrayList<Passenger> completedQueue = new ArrayList<>();
    public List<List<AddPassenger>> passengerLists = new ArrayList<>();

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

    public void setAddPassenger(ArrayList<AddPassenger> a) {
        this.esscAddPassenger = new ArrayList<>(a);
    }

    public void setAddElevator(ArrayList<AddElevator> a) {
        this.esscAddElevator = new ArrayList<>(a);
    }

    public void setRequestPercentage(ArrayList<RequestPercentage> a) {
        this.esscRequestPercentage = new ArrayList<>(a);
    }

    public void setPassengerRequestPercentage(ArrayList<PassengerRequestPercentage> a) {
        this.esscPassengerRequestPercentage = new ArrayList<>(a);
    }

    public void setcurrentElevators(ArrayList<Elevator> a) {
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
        for (int i = 0; i < Number_of_Elevators; i++) {
            List<AddPassenger> newPassengerList = new ArrayList<>();
            passengerLists.add(newPassengerList);
        }
        historyLogTextArea.setEditable(false);
        setElevatorClass();
        //elevatorlogic();

        // Add the TextArea to your container (replace VBoxfloor with your actual container)
        HistoryLog.setContent(historyLogTextArea);
        //-------------------------------------------------------------------------------------------------------------------
        //TimeLine-----------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------------
        run_simulationCounterText.setText(String.valueOf(i));
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            i++;
            historyLogTextArea.appendText("Iterations: " + i + "\n");
            run_simulationCounterText.setText(String.valueOf(i));
            System.out.println("ggggggggggggggggggggggggggggggggg" + i);
            //elevatorlogic();
            passengerRequestLogic();
            elevatorlogic();
            updateElevators();
            randomAddPassenger();

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
        if (playButton.getText().contains("II") && timeline.getStatus() == Animation.Status.PAUSED) {
            timeline.play();
            playButton.setText("▶");
            System.out.println("Timeline resumed");
            historyLogTextArea.appendText("Timeline resumed \n");
        } else if (playButton.getText().contains("▶") && timeline.getStatus() == Animation.Status.RUNNING) {
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
        System.out.println("Floor " + (Floors - floorIndex) + " created.");
        TextArea TextAreaFloorNum = new TextArea();
        TextAreaFloorNum.setText(String.valueOf(Floors - floorIndex));
        TextAreaFloorNum.setStyle("-fx-font-size: 20.5; -fx-font-weight: bold; -fx-alignment: center; -fx-control-inner-background: darkgray;");
        TextAreaFloorNum.setMaxWidth(50);
        TextAreaFloorNum.setMinWidth(50);
        TextAreaFloorNum.setEditable(false);
        HBox floorContentHBox = new HBox(TextAreaFloorNum);
        floorContentHBox.setAlignment(Pos.CENTER_LEFT);
        hBoxFloor.getChildren().add(floorContentHBox);
        for (int e = 0; e < Number_of_Elevators; e++) {
            int quarter = Number_of_Elevators / 4;
            if (e < quarter) {
                createElevator(hBoxFloor, e, floorIndex, "StandardElevator");
            } else if (e < 2 * quarter) {
                createElevator(hBoxFloor, e, floorIndex, "ExpressElevator");
            } else if (e < 3 * quarter) {
                createElevator(hBoxFloor, e, floorIndex, "FreightElevator");
            } else if (e < Number_of_Elevators - (Number_of_Elevators % 4)) {
                createElevator(hBoxFloor, e, floorIndex, "GlassElevator");
            } else if (e < Number_of_Elevators) {
                createElevator(hBoxFloor, e, floorIndex, "StandardElevator");
            }
        }
    }

    private HBox createFloorPane(int floorIndex) {
        HBox hBoxFloor = new HBox();
        hBoxFloor.setAlignment(Pos.BOTTOM_LEFT);
        hBoxFloor.setStyle("-fx-background-color: grey; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color:#ADD8E6; ");
        hBoxFloor.setPrefWidth(2000);
        hBoxFloor.setPrefHeight(150);
        return hBoxFloor;
    }

    //Elevator-------------------------------------------------------------------------------------------------
    private ToggleButton createElevatorToggleButton(int elevatorIndex, boolean firstfloor, String elevatortype) {
        ToggleButton elevatorButton = new ToggleButton();
        if (firstfloor == false) {
            Image elevatorImage = new Image("C:\\Users\\jyi95\\Downloads\\elevatorEmpty.png");
            ImageView imageView = new ImageView(elevatorImage);
            imageView.setFitWidth(80);//100
            imageView.setFitHeight(100);//120
            elevatorButton = new ToggleButton("", imageView);
            StackPane.setAlignment(imageView, Pos.BOTTOM_CENTER);
        } else if (firstfloor == true) {
            Image elevatorImageclosed = new Image("C:\\Users\\jyi95\\Downloads\\elevatorClosed.png");
            ImageView imageViewclosed = new ImageView(elevatorImageclosed);
            imageViewclosed.setFitWidth(80);
            imageViewclosed.setFitHeight(100);
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
        if (currentfloorNum == 1) {
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
                    createPassengerBox(passenger.getPassengerID(), Floors - passenger.getstartFloor(), passenger.getendFloor(), passenger);
                }
            }
        }
        elevatorButton.setOnAction(event -> {
            boolean isElevatorOnFloor = elevatorButton.isSelected();

            System.out.println("Elevator " + (elevatorIndex + 1) + " is on floor: " + currentfloorNum + ", " + isElevatorOnFloor);
        });
    }

    //Passenger-------------------------------------------------------------------------------------------------
    private HBox createPassengerBox(int passengerID, int startFloor, int endFloor, AddPassenger passenger) {
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
            vp.add(new VIPPassenger(passengerID, Floors - startFloor, endFloor));
            System.out.println(vp.get(vp.size() - 1).toString());
        } else if (passenger.getpassengerType().equals("\"Freight\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\FreightPassenger.png");
            imageView = new ImageView(passengerImage);
            fp.add(new FreightPassenger(passengerID, Floors - startFloor, endFloor));
            System.out.println(fp.get(fp.size() - 1).toString());
        } else if (passenger.getpassengerType().equals("\"Glass\"")) {
            Image passengerImage = new Image("C:\\Users\\jyi95\\Downloads\\GlassPassenger.png");
            imageView = new ImageView(passengerImage);
            gp.add(new GlassPassenger(passengerID, Floors - startFloor, endFloor));
            System.out.println(gp.get(gp.size() - 1).toString());
        }

        if (imageView != null) {
            imageView.setFitWidth(60);
            imageView.setFitHeight(60);
            passengerBox.getChildren().add(imageView);
        }
        HBox.setMargin(passengerBox, new Insets(10, 0, 0, 0));
        addPassengerToFloor(passengerBox, startFloor , passenger);
        return passengerBox;
    }

    private void addPassengerToFloor(HBox PassengerBox, int startFloor, AddPassenger passenger) {
        HBox floorContainer = (HBox) VBoxfloor.getChildren().get(startFloor - 1);
        floorContainer.getChildren().add(PassengerBox);
        System.out.println("Passenger " + passenger.getPassengerID() + " on floor " + passenger.getstartFloor());
    }
//elevator logic and data methods---------------------------------------------------------------------------------

    private ArrayList<Elevator> createElevatorData(int elevatorid, String elevatortype, int floorNum) {
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

    private void setElevatorClass(){
        for (int b = 0; b < essccurrentElevators.size();b++){
            if (Objects.equals(essccurrentElevators.get(b).getElevatorType(), "\"StandardElevator\"")){
                se.add(new StandardElevator(
                        essccurrentElevators.get(b).getelevatorID(),
                        essccurrentElevators.get(b).getElevatorType(),
                        essccurrentElevators.get(b).getMaxCapacities(),
                        essccurrentElevators.get(b).getElevatorRequestPercentage(),
                        essccurrentElevators.get(b).getCurrentFloor()));
            }else if (Objects.equals(essccurrentElevators.get(b).getElevatorType(), "\"ExpressElevator\"")){
                ee.add(new ExpressElevator(
                        essccurrentElevators.get(b).getelevatorID(),
                        essccurrentElevators.get(b).getElevatorType(),
                        essccurrentElevators.get(b).getMaxCapacities(),
                        essccurrentElevators.get(b).getElevatorRequestPercentage(),
                        essccurrentElevators.get(b).getCurrentFloor()));
            }else if (Objects.equals(essccurrentElevators.get(b).getElevatorType(), "\"FreightElevator\"")){
                fe.add(new FreightElevator(
                        essccurrentElevators.get(b).getelevatorID(),
                        essccurrentElevators.get(b).getElevatorType(),
                        essccurrentElevators.get(b).getMaxCapacities(),
                        essccurrentElevators.get(b).getElevatorRequestPercentage(),
                        essccurrentElevators.get(b).getCurrentFloor()));
            }else if (Objects.equals(essccurrentElevators.get(b).getElevatorType(), "\"GlassElevator\"")){
                ge.add(new GlassElevator(
                        essccurrentElevators.get(b).getelevatorID(),
                        essccurrentElevators.get(b).getElevatorType(),
                        essccurrentElevators.get(b).getMaxCapacities(),
                        essccurrentElevators.get(b).getElevatorRequestPercentage(),
                        essccurrentElevators.get(b).getCurrentFloor()));
            }
        }

    }
    private void elevatorlogic() {

        for (int y = 0; y < essccurrentElevators.size();y++){
            if (Objects.equals(essccurrentElevators.get(y).getElevatorType(), "\"StandardElevator\"")) {
                for ( int e = 0; e < se.size(); e++){
                    if (essccurrentElevators.get(y).getelevatorID() == se.get(e).getelevatorID()) {
                        for (int h =0; h < pickedPassengers.size();h++) {
                            if (pickedPassengers.get(h).getElevatorID() == se.get(e).getelevatorID())
                                if (se.get(e).getCurrentFloor() < pickedPassengers.get(h).getstartFloor()) {
                                    se.get(e).move(direction.UP);
                                    historyLogTextArea.appendText("\nElevator when up, ElevatorID: "
                                            + se.get(e).getelevatorID() +"\nElevatorType: " + se.get(e).getElevatorType()
                                            + "\nto floor: " + se.get(e).getCurrentFloor());
                                    break;
                                } else if (se.get(e).getCurrentFloor() > pickedPassengers.get(h).getstartFloor()) {
                                    se.get(e).move(direction.DOWN);
                                    historyLogTextArea.appendText("\nElevator when down, ElevatorID: "
                                            + se.get(e).getelevatorID() +"\nElevatorType: " + se.get(e).getElevatorType()
                                            + "\nto floor: " + se.get(e).getCurrentFloor());
                                    break;
                                } else if (se.get(e).getCurrentFloor() > pickedPassengers.get(h).getstartFloor()){
                                    se.get(e).move(direction.IDLE);
                                    break;
                                }

                        }
                    }
                }
            }else if (Objects.equals(essccurrentElevators.get(y).getElevatorType(), "\"ExpressElevator\"")) {
                for ( int e = 0; e < ee.size(); e++){
                    //pickedPassengers
                    if (essccurrentElevators.get(y).getelevatorID() == ee.get(e).getelevatorID()) {
                        for (int h =0; h < pickedPassengers.size();h++){
                            if (pickedPassengers.get(h).getElevatorID() == ee.get(e).getelevatorID()) {
                                if (ee.get(e).getCurrentFloor() < pickedPassengers.get(h).getstartFloor()) {
                                    ee.get(e).move(direction.UP);
                                    historyLogTextArea.appendText("Elevator when up, \nElevatorID: "
                                            + ee.get(e).getelevatorID() +"\nElevatorType: " + ee.get(e).getElevatorType()
                                            + "\nto floor: " + ee.get(e).getCurrentFloor());
                                    break;
                                } else if (ee.get(e).getCurrentFloor() > pickedPassengers.get(h).getstartFloor()) {
                                    ee.get(e).move(direction.DOWN);
                                    historyLogTextArea.appendText("Elevator when down, \nElevatorID: "
                                            + ee.get(e).getelevatorID() +"\nElevatorType: " + ee.get(e).getElevatorType()
                                            + "\nto floor: " + ee.get(e).getCurrentFloor());
                                    break;
                                } else if (ee.get(e).getCurrentFloor() == pickedPassengers.get(h).getstartFloor()){
                                    ee.get(e).move(direction.IDLE);
                                    break;
                                }
                            }


                        }
                    }
                }
            }else if (Objects.equals(essccurrentElevators.get(y).getElevatorType(), "\"FreightElevator\"")) {
                for ( int e = 0; e < fe.size(); e++){
                    if (essccurrentElevators.get(y).getelevatorID() == fe.get(e).getelevatorID()) {
                        for (int h =0; h < pickedPassengers.size();h++) {
                            if (pickedPassengers.get(h).getElevatorID() == fe.get(e).getelevatorID()) {
                                if (fe.get(e).getCurrentFloor() < pickedPassengers.get(h).getstartFloor()) {
                                    fe.get(e).move(direction.UP);
                                    historyLogTextArea.appendText("Elevator when up, \nElevatorID: "
                                            + fe.get(e).getelevatorID() +"\nElevatorType: " + fe.get(e).getElevatorType()
                                            + "\nto floor: " + fe.get(e).getCurrentFloor());
                                    break;
                                } else if (fe.get(e).getCurrentFloor() > pickedPassengers.get(h).getstartFloor()) {
                                    fe.get(e).move(direction.DOWN);
                                    historyLogTextArea.appendText("Elevator when down, \nElevatorID: "
                                            + fe.get(e).getelevatorID() +"\nElevatorType: " + fe.get(e).getElevatorType()
                                            + "\nto floor: " + fe.get(e).getCurrentFloor());
                                    break;
                                } else if (fe.get(e).getCurrentFloor() == pickedPassengers.get(h).getstartFloor()){
                                    fe.get(e).move(direction.IDLE);
                                    break;
                                }
                            }
                        }
                    }
                }
            }else if (Objects.equals(essccurrentElevators.get(y).getElevatorType(), "\"GlassElevator\"")) {
                for ( int e = 0; e < ge.size(); e++){
                    if (essccurrentElevators.get(y).getelevatorID() == ge.get(e).getelevatorID()) {
                        for (int h =0; h < pickedPassengers.size();h++) {
                            if (pickedPassengers.get(h).getElevatorID() == se.get(e).getelevatorID()) {
                                if (ge.get(e).getCurrentFloor() < pickedPassengers.get(h).getstartFloor()) {
                                    ge.get(e).move(direction.UP);
                                    historyLogTextArea.appendText("Elevator when up, \nElevatorID: "
                                            + ge.get(e).getelevatorID() +"\nElevatorType: " + ge.get(e).getElevatorType()
                                            + "\nto floor: " + ge.get(e).getCurrentFloor());
                                    break;
                                } else if (ge.get(e).getCurrentFloor() > pickedPassengers.get(h).getstartFloor()) {
                                    ge.get(e).move(direction.DOWN);
                                    historyLogTextArea.appendText("Elevator when down, \nElevatorID: "
                                            + ge.get(e).getelevatorID() +"\nElevatorType: " + ge.get(e).getElevatorType()
                                            + "\nto floor: " + ge.get(e).getCurrentFloor());
                                    break;
                                } else if(ge.get(e).getCurrentFloor() == pickedPassengers.get(h).getstartFloor()){
                                    ge.get(e).move(direction.IDLE);
                                    break;
                                }
                            }
                        }
                    }
                }

            }
        }
    }
    /*
        Image elevatorImage = new Image("C:\\Users\\jyi95\\Downloads\\elevatorEmpty.png");
        ImageView imageView = new ImageView(elevatorImage);
        imageView.setFitWidth(80);//100
        imageView.setFitHeight(100);//120
        elevatorButton = new ToggleButton("", imageView);
        StackPane.setAlignment(imageView, Pos.BOTTOM_CENTER);

        Image elevatorImageclosed = new Image("C:\\Users\\jyi95\\Downloads\\elevatorClosed.png");
        ImageView imageViewclosed = new ImageView(elevatorImageclosed);
        imageViewclosed.setFitWidth(80);
        imageViewclosed.setFitHeight(100);
        elevatorButton = new ToggleButton("", imageViewclosed);
        StackPane.setAlignment(imageViewclosed, Pos.BOTTOM_CENTER);
    private void changeimageEmpty(ToggleButton elevatorButton){
        Image elevatorImage = new Image("C:\\Users\\jyi95\\Downloads\\elevatorEmpty.png");
        ImageView imageView = new ImageView(elevatorImage);
        imageView.setFitWidth(80);//100
        imageView.setFitHeight(100);//120
        elevatorButton = new ToggleButton("", imageView);
        //imageView.setImage(newImage);

    }
    private void changeimageclosed(ToggleButton elevatorButton){
        Image elevatorImageclosed = new Image("C:\\Users\\jyi95\\Downloads\\elevatorClosed.png");
        ImageView imageViewclosed = new ImageView(elevatorImageclosed);
        imageViewclosed.setFitWidth(80);
        imageViewclosed.setFitHeight(100);
        elevatorButton = new ToggleButton("", imageViewclosed);
        StackPane.setAlignment(imageViewclosed, Pos.BOTTOM_CENTER);

    }
               double randomValue = random.nextDouble() * 100;
            double cumulativePercentage = 0.0;
            for (int i = 0; i < availableElevators.size(); i++) {
                double requestPercentage = availableElevators.get(i).getElevatorRequestPercentage();
                cumulativePercentage += requestPercentage;
                if (randomValue <= cumulativePercentage) {
                    pickedElevatorID = availableElevators.get(i).getelevatorID();
                    pickerpassengerID = pID;
                    pickerpassengersf = sf;
                    pickerpassengeref = ef;
                    pickedPassengers.add(new PickerPassenger(pickerpassengerID, sf, ef, true, false,pickedElevatorID));
                    System.out.println("Selected Elevator: " + availableElevators.get(i).getelevatorID() + " on FLoor: " + availableElevators.get(i).getCurrentFloor());
                    historyLogTextArea.appendText("Selected Elevator: " + availableElevators.get(i).getelevatorID() + " on FLoor: " + availableElevators.get(i).getCurrentFloor() + "\n");
                    return;
                }
            }
    */
    private void updateElevators(){
        Random random = new Random();
        double randomValue = random.nextDouble() * 100;

        for (int y = 0; y < essccurrentElevators.size();y++){
            //pick up passenger
            if (Objects.equals(essccurrentElevators.get(y).getElevatorType(), "\"StandardElevator\"")) {
                for ( int e = 0; e < se.size(); e++){
                        for (int h =0; h < esscAddPassenger.size();h++) {
                            if (se.get(e).getCurrentFloor() == esscAddPassenger.get(h).getstartFloor()){
                                for (int b = 0; b < request_percentage.size();b++){
                                    if (Objects.equals(request_percentage.get(b).getElevatorType(), "\"StandardElevator\"")){
                                        double requestPercentage = request_percentage.get(b).getRequestPercentagepercentage();
                                        if (randomValue <= requestPercentage){
                                            passengerLists.get(y).add(esscAddPassenger.get(h));
                                            esscAddPassenger.remove(h);
                                            System.out.println("ccccccccccccccccccc "+"Passenger entered elevator PassengerID: " + passengerLists.get(y).get(passengerLists.size()).getPassengerID()
                                                    + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                            historyLogTextArea.appendText("Passenger entered elevator PassengerID: " + passengerLists.get(y).get(passengerLists.size()).getPassengerID()
                                                    + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                            break;
                                        }
                                    }
                                    //Drop off passenger
                            if (se.get(e).getCurrentFloor() == esscAddPassenger.get(h).getendFloor()){
                                for (int w = 0; w < passengerLists.get(y).size(); w++){
                                    if (esscAddPassenger.get(h).getPassengerID() == passengerLists.get(y).get(w).getPassengerID()){
                                        esscAddPassenger.add(passengerLists.get(y).get(w));
                                        esscAddPassenger.get(esscAddPassenger.size() - 1).setStartFloor(se.get(e).getCurrentFloor());
                                        int newEndFloor;
                                        do {
                                            newEndFloor = random.nextInt(Floors) + 1;
                                        } while (newEndFloor == se.get(e).getCurrentFloor());
                                        esscAddPassenger.get(esscAddPassenger.size() - 1).setEndFloor(newEndFloor);
                                        passengerLists.get(y).remove(w);
                                        System.out.println("hhhhhhhhhhh "+ "Passenger left elevator PassengerID: " + esscAddPassenger.get(h).getPassengerID()
                                                + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                        historyLogTextArea.appendText("Passenger left elevator PassengerID: " + esscAddPassenger.get(h).getPassengerID()
                                        + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                        break;
                                    }
                                }
                            }
                                }
                            }
                        }
                    }
                }
            else if (Objects.equals(essccurrentElevators.get(y).getElevatorType(), "\"ExpressElevator\"")) {
                for ( int e = 0; e < ee.size(); e++){
                    for (int h =0; h < esscAddPassenger.size();h++) {
                        if (ee.get(e).getCurrentFloor() == esscAddPassenger.get(h).getstartFloor()){
                            for (int b = 0; b < request_percentage.size();b++){
                                if (Objects.equals(request_percentage.get(b).getElevatorType(), "\"ExpressElevator\"")){
                                    double requestPercentage = request_percentage.get(b).getRequestPercentagepercentage();
                                    if (randomValue <= requestPercentage){
                                        passengerLists.get(y).add(esscAddPassenger.get(h));
                                        esscAddPassenger.remove(h);
                                        System.out.println("ccccccccccccccccccc "+"Passenger entered elevator PassengerID: " + passengerLists.get(y).get(passengerLists.size()).getPassengerID()
                                                + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                        historyLogTextArea.appendText("Passenger entered elevator PassengerID: " + passengerLists.get(y).get(passengerLists.size()).getPassengerID()
                                                + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                        break;
                                    }
                                }
                                //Drop off passenger
                                if (se.get(e).getCurrentFloor() == esscAddPassenger.get(h).getendFloor()){
                                    for (int w = 0; w < passengerLists.get(y).size(); w++){
                                        if (esscAddPassenger.get(h).getPassengerID() == passengerLists.get(y).get(w).getPassengerID()){
                                            esscAddPassenger.add(passengerLists.get(y).get(w));
                                            esscAddPassenger.get(esscAddPassenger.size() - 1).setStartFloor(se.get(e).getCurrentFloor());
                                            int newEndFloor;
                                            do {
                                                newEndFloor = random.nextInt(Floors) + 1;
                                            } while (newEndFloor == se.get(e).getCurrentFloor());
                                            esscAddPassenger.get(esscAddPassenger.size() - 1).setEndFloor(newEndFloor);
                                            passengerLists.get(y).remove(w);
                                            System.out.println("hhhhhhhhhhh "+ "Passenger left elevator PassengerID: " + esscAddPassenger.get(h).getPassengerID()
                                                    + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                            historyLogTextArea.appendText("Passenger left elevator PassengerID: " + esscAddPassenger.get(h).getPassengerID()
                                                    + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (Objects.equals(essccurrentElevators.get(y).getElevatorType(), "\"GlassElevator\"")) {
                for ( int e = 0; e < ge.size(); e++){
                    for (int h =0; h < esscAddPassenger.size();h++) {
                        if (ge.get(e).getCurrentFloor() == esscAddPassenger.get(h).getstartFloor()){
                            for (int b = 0; b < request_percentage.size();b++){
                                if (Objects.equals(request_percentage.get(b).getElevatorType(), "\"GlassElevator\"")){
                                    double requestPercentage = request_percentage.get(b).getRequestPercentagepercentage();
                                    if (randomValue <= requestPercentage){
                                        passengerLists.get(y).add(esscAddPassenger.get(h));
                                        esscAddPassenger.remove(h);
                                        System.out.println("ccccccccccccccccccc "+"Passenger entered elevator PassengerID: " + passengerLists.get(y).get(passengerLists.size()).getPassengerID()
                                                + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                        historyLogTextArea.appendText("Passenger entered elevator PassengerID: " + passengerLists.get(y).get(passengerLists.size()).getPassengerID()
                                                + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                        break;
                                    }
                                }
                                //Drop off passenger
                                if (se.get(e).getCurrentFloor() == esscAddPassenger.get(h).getendFloor()){
                                    for (int w = 0; w < passengerLists.get(y).size(); w++){
                                        if (esscAddPassenger.get(h).getPassengerID() == passengerLists.get(y).get(w).getPassengerID()){
                                            esscAddPassenger.add(passengerLists.get(y).get(w));
                                            esscAddPassenger.get(esscAddPassenger.size() - 1).setStartFloor(se.get(e).getCurrentFloor());
                                            int newEndFloor;
                                            do {
                                                newEndFloor = random.nextInt(Floors) + 1;
                                            } while (newEndFloor == se.get(e).getCurrentFloor());
                                            esscAddPassenger.get(esscAddPassenger.size() - 1).setEndFloor(newEndFloor);
                                            passengerLists.get(y).remove(w);
                                            System.out.println("hhhhhhhhhhh "+ "Passenger left elevator PassengerID: " + esscAddPassenger.get(h).getPassengerID()
                                                    + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                            historyLogTextArea.appendText("Passenger left elevator PassengerID: " + esscAddPassenger.get(h).getPassengerID()
                                                    + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (Objects.equals(essccurrentElevators.get(y).getElevatorType(), "\"FreightElevator\"")) {
                for ( int e = 0; e < fe.size(); e++){
                    for (int h =0; h < esscAddPassenger.size();h++) {
                        if (fe.get(e).getCurrentFloor() == esscAddPassenger.get(h).getstartFloor()){
                            for (int b = 0; b < request_percentage.size();b++){
                                if (Objects.equals(request_percentage.get(b).getElevatorType(), "\"FreightElevator\"")){
                                    double requestPercentage = request_percentage.get(b).getRequestPercentagepercentage();
                                    if (randomValue <= requestPercentage){
                                        passengerLists.get(y).add(esscAddPassenger.get(h));
                                        esscAddPassenger.remove(h);
                                        System.out.println("ccccccccccccccccccc "+"Passenger entered elevator PassengerID: " + passengerLists.get(y).get(passengerLists.size()).getPassengerID()
                                                + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                        historyLogTextArea.appendText("Passenger entered elevator PassengerID: " + passengerLists.get(y).get(passengerLists.size()).getPassengerID()
                                                + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                        break;
                                    }
                                }
                                //Drop off passenger
                                if (se.get(e).getCurrentFloor() == esscAddPassenger.get(h).getendFloor()){
                                    for (int w = 0; w < passengerLists.get(y).size(); w++){
                                        if (esscAddPassenger.get(h).getPassengerID() == passengerLists.get(y).get(w).getPassengerID()){
                                            esscAddPassenger.add(passengerLists.get(y).get(w));
                                            esscAddPassenger.get(esscAddPassenger.size() - 1).setStartFloor(se.get(e).getCurrentFloor());
                                            int newEndFloor;
                                            do {
                                                newEndFloor = random.nextInt(Floors) + 1;
                                            } while (newEndFloor == se.get(e).getCurrentFloor());
                                            esscAddPassenger.get(esscAddPassenger.size() - 1).setEndFloor(newEndFloor);
                                            passengerLists.get(y).remove(w);
                                            System.out.println("hhhhhhhhhhh "+ "Passenger left elevator PassengerID: " + esscAddPassenger.get(h).getPassengerID()
                                                    + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                            historyLogTextArea.appendText("Passenger left elevator PassengerID: " + esscAddPassenger.get(h).getPassengerID()
                                                    + "\nElevatorID:" + essccurrentElevators.get(y).getelevatorID() + "\nOn floor "+ essccurrentElevators.get(y).getCurrentFloor());
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //passengerRequestLogic------------------------------------------------------------------------------------------------------------------
    private void passengerRequestLogic() {
        Random random = new Random();
        boolean booleanPassengerRequest = false;
        ArrayList<Elevator> availableessccurrentElevators = new ArrayList<>();
        //for loop for the amount of sp (2)
        for (int t = 0; t < sp.size(); t++) {
            //for loop for the amount of PassengerRequestPercentage (4)
            for (int f = 0; f < esscPassengerRequestPercentage.size(); f++) {
                if (esscPassengerRequestPercentage.get(f).getpassengerType().equals("\"Standard\"")) {
                    double spasssengerPickElevator = esscPassengerRequestPercentage.get(f).getPassengerRequestpercentage();
                    double randomNum1 = random.nextDouble() * 100;
                    for (int j = 0; j < essccurrentElevators.size(); j++) {
                        if (randomNum1 <= spasssengerPickElevator && !sp.get(t).getPressedButton()) {
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
            randomElevatorPick(availableessccurrentElevators, sp.get(t).getPassengerID(), sp.get(t).getstartFloor(),sp.get(t).getEndFloor());
            booleanPassengerRequest = false;
            availableessccurrentElevators.clear();
        }
        for (int t = 0; t < vp.size(); t++) {
            for (int f = 0; f < esscPassengerRequestPercentage.size(); f++) {//4
                if (esscPassengerRequestPercentage.get(f).getpassengerType().equals("\"VIP\"")) {
                    double vpasssengerPickElevator = esscPassengerRequestPercentage.get(f).getPassengerRequestpercentage();
                    double randomNum2 = random.nextDouble() * 100;
                    for (int j = 0; j < essccurrentElevators.size(); j++) {
                        if (randomNum2 <= vpasssengerPickElevator && !vp.get(t).getPressedButton()) {
                            booleanPassengerRequest = true;
                            availableessccurrentElevators.add(essccurrentElevators.get(j));
                        }
                    }
                }
            }

            if (booleanPassengerRequest == true) {
                vp.get(t).setPressedButton(true);
                historyLogTextArea.appendText("PassengerID: " + vp.get(t).getPassengerID() + " PassengerType: VIP \n");
                System.out.println("PassengerID: " + vp.get(t).getPassengerID() + " PassengerType: VIP");
            }
            randomElevatorPick(availableessccurrentElevators, vp.get(t).getPassengerID(), vp.get(t).getstartFloor(),vp.get(t).getEndFloor());
            booleanPassengerRequest = false;
            availableessccurrentElevators.clear();
        }
        for (int t = 0; t < fp.size(); t++) {
            for (int f = 0; f < esscPassengerRequestPercentage.size(); f++) {
                if (esscPassengerRequestPercentage.get(f).getpassengerType().equals("\"Freight\"")) {
                    double fpasssengerPickElevator = esscPassengerRequestPercentage.get(f).getPassengerRequestpercentage();
                    double randomNum3 = random.nextDouble() * 100;
                    for (int j = 0; j < essccurrentElevators.size(); j++) {
                        if (randomNum3 <= fpasssengerPickElevator && !fp.get(t).getPressedButton()) {
                            booleanPassengerRequest = true;
                            availableessccurrentElevators.add(essccurrentElevators.get(j));
                        }
                    }
                }
            }
            if (booleanPassengerRequest == true) {
                fp.get(t).setPressedButton(true);
                historyLogTextArea.appendText("PassengerID: " + fp.get(t).getPassengerID() + " PassengerType: Freight \n");
                System.out.println("PassengerID: " + fp.get(t).getPassengerID() + " PassengerType: Freight");
            }
            randomElevatorPick(availableessccurrentElevators, fp.get(t).getPassengerID(), fp.get(t).getstartFloor(),fp.get(t).getEndFloor());
            booleanPassengerRequest = false;
            availableessccurrentElevators.clear();
        }

        for (int t = 0; t < gp.size(); t++) {
            for (int f = 0; f < esscPassengerRequestPercentage.size(); f++) {
                if (esscPassengerRequestPercentage.get(f).getpassengerType().equals("\"Glass\"")) {
                    double gpasssengerPickElevator = esscPassengerRequestPercentage.get(f).getPassengerRequestpercentage();
                    double randomNum4 = random.nextDouble() * 100;
                    for (int j = 0; j < essccurrentElevators.size(); j++) {
                        if (randomNum4 <= gpasssengerPickElevator && !gp.get(t).getPressedButton()) {
                            booleanPassengerRequest = true;
                            availableessccurrentElevators.add(essccurrentElevators.get(j));
                        }
                    }
                }
            }
            if (booleanPassengerRequest == true) {
                gp.get(t).setPressedButton(true);
                historyLogTextArea.appendText("PassengerID: " + gp.get(t).getPassengerID() + " PassengerType: Glass \n");
                System.out.println("PassengerID: " + gp.get(t).getPassengerID() + " PassengerType: Glass");
            }
            randomElevatorPick(availableessccurrentElevators, gp.get(t).getPassengerID(), gp.get(t).getstartFloor(),gp.get(t).getEndFloor());
            booleanPassengerRequest = false;
            availableessccurrentElevators.clear();
        }
    }


        private void randomElevatorPick(ArrayList<Elevator> availableElevators, int pID, int sf, int ef) {
            Random random = new Random();
            double randomValue = random.nextDouble() * 100;
            double cumulativePercentage = 0.0;
            for (int i = 0; i < availableElevators.size(); i++) {
                double requestPercentage = availableElevators.get(i).getElevatorRequestPercentage();
                cumulativePercentage += requestPercentage;
                if (randomValue <= cumulativePercentage) {
                    pickedElevatorID = availableElevators.get(i).getelevatorID();
                    pickerpassengerID = pID;
                    pickerpassengersf = sf;
                    pickerpassengeref = ef;
                    pickedPassengers.add(new PickerPassenger(pickerpassengerID, sf, ef, true, false,pickedElevatorID));
                    System.out.println("Selected Elevator: " + availableElevators.get(i).getelevatorID() + " on FLoor: " + availableElevators.get(i).getCurrentFloor());
                    historyLogTextArea.appendText("Selected Elevator: " + availableElevators.get(i).getelevatorID() + " on FLoor: " + availableElevators.get(i).getCurrentFloor() + "\n");
                    return;
                }
            }

    }

    private void randomAddPassenger() {
        Random random = new Random();
        int randomPopAdd = random.nextInt(3);
        historyLogTextArea.appendText("randomPopAdd " + randomPopAdd + "\n");
        double cumulativePercentage = 0.0;
        for (int u = 0; u < randomPopAdd; u++) {
            int randomStartFloor = random.nextInt(Floors) + 1;
            int randomEndFloor;
            do {
                randomEndFloor = random.nextInt(Floors) + 1;
            } while (randomStartFloor == randomEndFloor);
            double randomValue = random.nextDouble() * 100;
            for (int l = 0; l < esscAddPassenger.size(); l++) {
                double addPopPercentage = esscAddPassenger.get(l).getAddPassengerpercentage();
                cumulativePercentage += addPopPercentage;

                if (randomValue <= cumulativePercentage) {
                    AddPassenger tempEsscAddPassenger = new AddPassenger(esscAddPassenger.size() + 1, randomStartFloor, randomEndFloor, esscAddPassenger.get(l).getpassengerType(), addPopPercentage, false);
                    esscAddPassenger.add(tempEsscAddPassenger);
                    System.out.println(esscAddPassenger.get(l));
                    addPassengerWhenRunning(esscAddPassenger.get(l));
                    break;
                }
            }
        }
    }
    private void addPassengerWhenRunning(AddPassenger a){
        for (int p = 0; p < esscAddPassenger.size();p++){
            for (int e = 0; e < Floors; e++){
                if(esscAddPassenger.get(p).getstartFloor() == e && !esscAddPassenger.get(p).getAdded()){
                    esscAddPassenger.get(p).setAdded(true);
                    createPassengerBox(esscAddPassenger.get(p).getPassengerID(), Floors - esscAddPassenger.get(p).getstartFloor() + 1, esscAddPassenger.get(p).getendFloor(), esscAddPassenger.get(p));
                    historyLogTextArea.appendText("New Passsenger, PassengerID: " + esscAddPassenger.get(p).getPassengerID() + ",\nPassenger Type: " + esscAddPassenger.get(p).getpassengerType()+ ",\n Starting Floor of " + esscAddPassenger.get(p).getstartFloor() + "\n");
                }
            }
        }
    }
    private void removePassengerWhenRunning(AddPassenger a,HBox passengerBox){
        for (int p = 0; p < esscAddPassenger.size();p++){
            for (int e = 0; e < Floors; e++){
                if(esscAddPassenger.get(p).getstartFloor() == e && !esscAddPassenger.get(p).getAdded()){
                    esscAddPassenger.get(p).setAdded(true);
                    passengerBox.getChildren().remove(p);

                }
            }
        }
    }
}








