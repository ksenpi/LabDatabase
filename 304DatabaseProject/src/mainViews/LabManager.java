package mainViews;/**
 * Created by Tamar on 2016-11-10.
 */

import databaseConnection.OurConnection;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LabManager extends Application implements User{
    int labManagerID;
    Stage theStage;
    Button addFridge, removeFridge, addResearcher, addLabManager, removeLabManager, addSampleToBox, removeSampleFromBox, addBox, removeBox,
            addFridgeBack, removeFridgeBack, addResearcherBack, addLabManagerBack, removeLabManagerBack, addSampleToBoxBack,
            removeSampleFromBoxBack, addBoxBack, removeBoxBack,
            findBoxlessSamples, findContainersUnderCapacity, generateWorkList, generateSampleList, findResearchDurationsByResearcher,updateTemperature
            ,findBoxlessSamplesBack, findContainersUnderCapacityBack, generateWorkListBack, generateSampleListBack, findResearchDurationsByResearcherBack, updateTemperatureBack;
    Scene entryScene, addFridgeScene, removeFridgeScene, addResearcherScene, addLabManagerScene, removeLabManagerScene,
            addSampleToBoxScene, removeSampleFromBoxScene, addBoxScene, removeBoxScene,
    findBoxlessSamplesScene, findContainersUnderCapacityScene, generateWorkListScene, generateSampleListScene, findResearchDurationsByResearcherScene, updateTemperatureScene;
    GridPane entryPane, addFridgePane, removeFridgePane, addResearcherPane, addLabManagerPane, removeLabManagerPane,
            addSampleToBoxPane, removeSampleFromBoxPane, addBoxPane, removeBoxPane,
    findBoxlessSamplesPane, findContainersUnderCapacityPane, generateWorkListPane, generateSampleListPane, findResearchDurationsByResearcherPane, updateTemperaturePane;


    public LabManager(int labManagerID) {
        this.labManagerID = labManagerID;
    }

    //added so we can call the class from another class
    static Stage classStage = new Stage();

    public static void main(String[] args) {
    //    launch(args);

    // Just for testing!
        LabManager lb = new LabManager(7);
        lb.start(null);

    }

    @Override
    public void start(Stage primaryStage) {

        String result = addFridge(30, 30);
        System.out.println("result: " + result);

        ResultSet results;
        Statement stmt;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt = con.createStatement();
                results = stmt.executeQuery("select temperature from fridge2 where fr_id = 2");;

                while (results.next()) {
                    int something = results.getInt("temperature");
                    System.out.println(something);
                }
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }


//added so we can call the class from another class
        LabManager.classStage = primaryStage ;

        primaryStage.setTitle("Welcome to LabDatabasePro3000");
        theStage = primaryStage;

        addFridgeBack = new Button("Go to Main Page");
        addFridgeBack.setOnAction(e->ButtonClicked(e));
        removeFridgeBack= new Button("Go to Main Page");
        removeFridgeBack.setOnAction(e->ButtonClicked(e));
        addResearcherBack= new Button("Go to Main Page");
        addResearcherBack.setOnAction(e->ButtonClicked(e));
        addLabManagerBack= new Button("Go to Main Page");
        addLabManagerBack.setOnAction(e->ButtonClicked(e));
        removeLabManagerBack= new Button("Go to Main Page");
        removeLabManagerBack.setOnAction(e->ButtonClicked(e));
        addSampleToBoxBack= new Button("Go to Main Page");
        addSampleToBoxBack.setOnAction(e->ButtonClicked(e));
        removeSampleFromBoxBack= new Button("Go to Main Page");
        removeSampleFromBoxBack.setOnAction(e->ButtonClicked(e));
        addBoxBack= new Button("Go to Main Page");
        addBoxBack.setOnAction(e->ButtonClicked(e));
        removeBoxBack= new Button("Go to Main Page");
        removeBoxBack.setOnAction(e->ButtonClicked(e));
        findBoxlessSamplesBack= new Button("Go to Main Page");
        findBoxlessSamplesBack.setOnAction(e->ButtonClicked(e));
        findContainersUnderCapacityBack= new Button("Go to Main Page");
        findContainersUnderCapacityBack.setOnAction(e->ButtonClicked(e));
        generateWorkListBack= new Button("Go to Main Page");
        generateWorkListBack.setOnAction(e->ButtonClicked(e));
        generateSampleListBack= new Button("Go to Main Page");
        generateSampleListBack.setOnAction(e->ButtonClicked(e));
        findResearchDurationsByResearcherBack= new Button("Go to Main Page");
        findResearchDurationsByResearcherBack.setOnAction(e->ButtonClicked(e));
        updateTemperatureBack= new Button("Go to Main Page");
        updateTemperatureBack.setOnAction(e->ButtonClicked(e));
        //entryScene////////////////////////////////////////////////////////////////////////////////////////////////////
        addFridge=new Button("Add Fridge");
        removeFridge = new Button("Remove Fridge");
        addResearcher = new Button("Add Researcher");
        addLabManager = new Button("Add Lab Manager");
        removeLabManager = new Button("Remove Lab Manger");
        addSampleToBox = new Button("Add Sample To Box");
        removeSampleFromBox = new Button("Remove Sample From Box");
        addBox = new Button("Add Box");
        removeBox = new Button("Remove Box");
        findBoxlessSamples = new Button("Find Boxless Samples");
        findContainersUnderCapacity = new Button("Find Containers with Space");
        generateWorkList = new Button("See Worklist");
        generateSampleList = new Button("See Samplelist");
        findResearchDurationsByResearcher = new Button("Find Research Durations");
        updateTemperature = new Button("Update Temperature");

        addFridge.setOnAction(e-> ButtonClicked(e));
        removeFridge.setOnAction(e-> ButtonClicked(e));
        addResearcher.setOnAction(e-> ButtonClicked(e));
        addLabManager.setOnAction(e-> ButtonClicked(e));
        removeLabManager.setOnAction(e-> ButtonClicked(e));
        addSampleToBox.setOnAction(e-> ButtonClicked(e));
        removeSampleFromBox.setOnAction(e-> ButtonClicked(e));
        addBox.setOnAction(e-> ButtonClicked(e));
        removeBox.setOnAction(e-> ButtonClicked(e));
        findBoxlessSamples.setOnAction(e-> ButtonClicked(e));
        findContainersUnderCapacity.setOnAction(e-> ButtonClicked(e));
        generateWorkList.setOnAction(e-> ButtonClicked(e));
        generateSampleList.setOnAction(e-> ButtonClicked(e));
        findResearchDurationsByResearcher.setOnAction(e-> ButtonClicked(e));
        updateTemperature.setOnAction(e-> ButtonClicked(e));

        entryPane = new GridPane();
        entryPane.setAlignment(Pos.CENTER);
        entryPane.setHgap(10);
        entryPane.setVgap(10);
        entryPane.setPadding(new Insets(25, 25, 25, 25));
        entryPane.setVgap(10);
        entryPane.add(addFridge,0,0);
        entryPane.add(removeFridge,1,0);
        entryPane.add(addResearcher,2,0);
        entryPane.add(addLabManager,0,1);
        entryPane.add(removeLabManager,1,1);
        entryPane.add(addSampleToBox,2,1);
        entryPane.add(removeSampleFromBox,0,2);
        entryPane.add(addBox,1,2);
        entryPane.add(removeBox,2,2);
        entryPane.add(findBoxlessSamples,0,3);
        entryPane.add(findContainersUnderCapacity,1,3);
        entryPane.add(generateWorkList,2,3);
        entryPane.add(generateSampleList,0,4);
        entryPane.add(findResearchDurationsByResearcher,1,4);
        entryPane.add(updateTemperature,2,4);

        entryScene = new Scene(entryPane, 1000, 500);
        //addFridgeScene   /////////////////////////////////////////////////////////////////////////////////////////////
        Label temp = new Label("Temperature in Celsius:");
        TextField temptxt = new TextField();
        temptxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = temptxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        temptxt.setText(temptxt.getText().substring(0,temptxt.getText().length()-1));
                    }
                }
            }

        });
        Text addFridgeResponse = new Text();
        Button addFridgeEnter = new Button("Enter");
        addFridgeEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(temptxt.getText().isEmpty())
                    addFridgeResponse.setText("Please enter a temperature value");
                else
                    addFridgeResponse.setText(addFridge(Integer.parseInt(temptxt.getText()),labManagerID));
            }});
        addFridgePane = new GridPane();
        addFridgePane.setAlignment(Pos.CENTER);
        addFridgePane.setHgap(10);
        addFridgePane.setVgap(10);
        addFridgePane.setPadding(new Insets(25, 25, 25, 25));
        addFridgePane.setVgap(10);

        addFridgePane.add(addFridgeResponse,7,8);
        addFridgePane.add(temp,0,0);
        addFridgePane.add(temptxt,1,0);
        addFridgePane.add(addFridgeBack,0,7);
        addFridgePane.add(addFridgeEnter,7,7);

        addFridgeScene = new Scene(addFridgePane, 1000, 500);

        // removeFridgeScene ///////////////////////////////////////////////////////////////////////////////////////////
        Label removeFridgeID = new Label("Fridge ID:");
        TextField removeFridgeIDtxt = new TextField();
        removeFridgeIDtxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = removeFridgeIDtxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        removeFridgeIDtxt.setText(removeFridgeIDtxt.getText().substring(0,removeFridgeIDtxt.getText().length()-1));
                    }
                }
            }

        });
        Text removeFridgeResponse = new Text();
        Button removeFridgeEnter = new Button("Enter");
        removeFridgeEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(removeFridgeIDtxt.getText().isEmpty())
                    removeFridgeResponse.setText("Please enter a fridge ID");
                else
                removeFridgeResponse.setText(removeFridge(Integer.parseInt(removeFridgeIDtxt.getText())));
            }});

        removeFridgePane = new GridPane();
        removeFridgePane.setAlignment(Pos.CENTER);
        removeFridgePane.setHgap(10);
        removeFridgePane.setVgap(10);
        removeFridgePane.setPadding(new Insets(25, 25, 25, 25));
        removeFridgePane.setVgap(10);

        removeFridgePane.add(removeFridgeResponse, 7, 8);
        removeFridgePane.add(removeFridgeID,0,0);
        removeFridgePane.add(removeFridgeIDtxt,1,0);
        removeFridgePane.add(removeFridgeBack,0,7);
        removeFridgePane.add(removeFridgeEnter,7,7);

        removeFridgeScene = new Scene(removeFridgePane, 1000, 500);

        // addResearcherScene //////////////////////////////////////////////////////////////////////////////////////////
        Label employeeNameLabel = new Label("Employee Name:");
        TextField employeeNametxt = new TextField();
        Text addResearcherResponse = new Text();
        Button addResearcherEnter = new Button("Enter");
        addResearcherEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(employeeNametxt.getText().isEmpty())
                    addResearcherResponse.setText("Please enter an employee name");
                else
                addResearcherResponse.setText(addResearcher(employeeNametxt.getText()));
            }});

        addResearcherPane = new GridPane();
        addResearcherPane.setAlignment(Pos.CENTER);
        addResearcherPane.setHgap(10);
        addResearcherPane.setVgap(10);
        addResearcherPane.setPadding(new Insets(25, 25, 25, 25));
        addResearcherPane.setVgap(10);

        addResearcherPane.add(addResearcherResponse, 7, 8);
        addResearcherPane.add(employeeNameLabel,0,0);
        addResearcherPane.add(employeeNametxt,1,0);
        addResearcherPane.add(addResearcherBack,0,7);
        addResearcherPane.add(addResearcherEnter,7,7);

        addResearcherScene = new Scene(addResearcherPane, 1000, 500);

        // addLabManagerScene //////////////////////////////////////////////////////////////////////////////////////////
        Label labmanagerNameLabel = new Label("Employee Name:");
        TextField labmanagerNametxt = new TextField();
        Text addlabmanagerResponse = new Text();
        Button addlabManagerEnter = new Button("Enter");
        addlabManagerEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(labmanagerNametxt.getText().isEmpty())
                    addlabmanagerResponse.setText("Please enter a lab manager name");
                else
                addlabmanagerResponse.setText(addLabManager(labmanagerNametxt.getText()));
            }});

        addLabManagerPane = new GridPane();
        addLabManagerPane.setAlignment(Pos.CENTER);
        addLabManagerPane.setHgap(10);
        addLabManagerPane.setVgap(10);
        addLabManagerPane.setPadding(new Insets(25, 25, 25, 25));
        addLabManagerPane.setVgap(10);

        addLabManagerPane.add(addlabmanagerResponse, 7, 8);
        addLabManagerPane.add(labmanagerNameLabel,0,0);
        addLabManagerPane.add(labmanagerNametxt,1,0);
        addLabManagerPane.add(addLabManagerBack,0,7);
        addLabManagerPane.add(addlabManagerEnter,7,7);

        addLabManagerScene = new Scene(addLabManagerPane, 1000, 500);
        // removeLabManagerScene ///////////////////////////////////////////////////////////////////////////////////////
        Label removeLabManagerID = new Label("Lab Manager ID:");
        TextField removeLabManagerIDtxt = new TextField();
        removeLabManagerIDtxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = removeLabManagerIDtxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        removeLabManagerIDtxt.setText(removeLabManagerIDtxt.getText().substring(0,removeLabManagerIDtxt.getText().length()-1));
                    }
                }
            }

        });
        Text removeLabManagerResponse = new Text();
        Button removeLabManagerEnter = new Button("Enter");
        removeLabManagerEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(removeLabManagerIDtxt.getText().isEmpty())
                    removeLabManagerResponse.setText("Please enter the lab manager ID");
                else
                removeLabManagerResponse.setText(removeLabManager(Integer.parseInt(removeLabManagerIDtxt.getText())));
            }});

        removeLabManagerPane = new GridPane();
        removeLabManagerPane.setAlignment(Pos.CENTER);
        removeLabManagerPane.setHgap(10);
        removeLabManagerPane.setVgap(10);
        removeLabManagerPane.setPadding(new Insets(25, 25, 25, 25));
        removeLabManagerPane.setVgap(10);

        removeLabManagerPane.add(removeLabManagerResponse, 7, 8);
        removeLabManagerPane.add(removeLabManagerID,0,0);
        removeLabManagerPane.add(removeLabManagerIDtxt,1,0);
        removeLabManagerPane.add(removeLabManagerBack,0,7);
        removeLabManagerPane.add(removeLabManagerEnter,7,7);

        removeLabManagerScene = new Scene(removeLabManagerPane, 1000, 500);
        // addSampleToBoxScene//////////////////////////////////////////////////////////////////////////////////////////
        Label containerIDLabel = new Label("Box ID:");
        TextField containerIDtxt = new TextField();
        containerIDtxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = containerIDtxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        containerIDtxt.setText(containerIDtxt.getText().substring(0,containerIDtxt.getText().length()-1));
                    }
                }
            }

        });
        Label sampleIDLabel = new Label("Sample ID:");
        TextField sampleIDtxt = new TextField();
        sampleIDtxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = sampleIDtxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        sampleIDtxt.setText(sampleIDtxt.getText().substring(0,sampleIDtxt.getText().length()-1));
                    }
                }
            }

        });

        Text addSampleToBoxResponse = new Text();
        Button addSampleToBoxEnter = new Button("Enter");
        addSampleToBoxEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(containerIDtxt.getText().isEmpty()||sampleIDtxt.getText().isEmpty())
                    addSampleToBoxResponse.setText("Please make sure no field is empty");
                else
                addSampleToBoxResponse.setText(addSampleToBox(Integer.parseInt(containerIDtxt.getText()),Integer.parseInt(sampleIDtxt.getText()),labManagerID));
            }});

        addSampleToBoxPane = new GridPane();
        addSampleToBoxPane.setAlignment(Pos.CENTER);
        addSampleToBoxPane.setHgap(10);
        addSampleToBoxPane.setVgap(10);
        addSampleToBoxPane.setPadding(new Insets(25, 25, 25, 25));
        addSampleToBoxPane.setVgap(10);

        addSampleToBoxPane.add(addSampleToBoxResponse, 7, 8);
        addSampleToBoxPane.add(containerIDLabel,0,0);
        addSampleToBoxPane.add(containerIDtxt,1,0);
        addSampleToBoxPane.add(sampleIDLabel,0,1);
        addSampleToBoxPane.add(sampleIDtxt,1,1);
        addSampleToBoxPane.add(addSampleToBoxBack,0,7);
        addSampleToBoxPane.add(addSampleToBoxEnter,7,7);

        addSampleToBoxScene = new Scene(addSampleToBoxPane, 1000, 500);
        // removeSampleFromBoxScene ////////////////////////////////////////////////////////////////////////////////////
        Label containerIDLabel1 = new Label("Box ID:");
        TextField containerIDtxt1 = new TextField();
        containerIDtxt1.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = containerIDtxt1.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        containerIDtxt1.setText(containerIDtxt1.getText().substring(0,containerIDtxt1.getText().length()-1));
                    }
                }
            }

        });
        Label sampleIDLabel1 = new Label("Sample ID:");
        TextField sampleIDtxt1 = new TextField();
        sampleIDtxt1.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = sampleIDtxt1.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        sampleIDtxt1.setText(sampleIDtxt1.getText().substring(0,sampleIDtxt1.getText().length()-1));
                    }
                }
            }

        });

        Text removeSamplefromBoxResponse = new Text();
        Button removeSamplefromBoxEnter = new Button("Enter");
        removeSamplefromBoxEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(containerIDtxt1.getText().isEmpty()||sampleIDtxt1.getText().isEmpty())
                    removeSamplefromBoxResponse.setText("Please make sure no field is empty");
                else
                removeSamplefromBoxResponse.setText(removeSampleFromBox(Integer.parseInt(containerIDtxt1.getText()),Integer.parseInt(sampleIDtxt1.getText())));
            }});

        removeSampleFromBoxPane = new GridPane();
        removeSampleFromBoxPane.setAlignment(Pos.CENTER);
        removeSampleFromBoxPane.setHgap(10);
        removeSampleFromBoxPane.setVgap(10);
        removeSampleFromBoxPane.setPadding(new Insets(25, 25, 25, 25));
        removeSampleFromBoxPane.setVgap(10);

        removeSampleFromBoxPane.add(removeSamplefromBoxResponse, 7, 8);
        removeSampleFromBoxPane.add(containerIDLabel1,0,0);
        removeSampleFromBoxPane.add(containerIDtxt1,1,0);
        removeSampleFromBoxPane.add(sampleIDLabel1,0,1);
        removeSampleFromBoxPane.add(sampleIDtxt1,1,1);
        removeSampleFromBoxPane.add(removeSampleFromBoxBack,0,7);
        removeSampleFromBoxPane.add(removeSamplefromBoxEnter,7,7);

        removeSampleFromBoxScene = new Scene(removeSampleFromBoxPane, 1000, 500);
        // addBoxScene /////////////////////////////////////////////////////////////////////////////////////////////////
        Label containerNameLabel = new Label("Box Name:");
        TextField containerNametxt = new TextField();
        Label fridgeIDLabel = new Label("Fridge ID:");
        TextField fridgeIDtxt = new TextField();
        fridgeIDtxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = fridgeIDtxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        fridgeIDtxt.setText(fridgeIDtxt.getText().substring(0,fridgeIDtxt.getText().length()-1));
                    }
                }
            }

        });
        Text addBoxResponse = new Text();
        Button addBoxEnter = new Button("Enter");
        addBoxEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(containerNametxt.getText().isEmpty()||fridgeIDtxt.getText().isEmpty())
                    addBoxResponse.setText("Please make sure no field is empty");
                else
                addBoxResponse.setText(addBox(containerNametxt.getText(),Integer.parseInt(fridgeIDtxt.getText())));
            }});

        addBoxPane = new GridPane();
        addBoxPane.setAlignment(Pos.CENTER);
        addBoxPane.setHgap(10);
        addBoxPane.setVgap(10);
        addBoxPane.setPadding(new Insets(25, 25, 25, 25));
        addBoxPane.setVgap(10);

        addBoxPane.add(addBoxResponse, 7, 8);
        addBoxPane.add(containerNameLabel,0,0);
        addBoxPane.add(containerNametxt,1,0);
        addBoxPane.add(fridgeIDLabel,0,1);
        addBoxPane.add(fridgeIDtxt,1,1);
        addBoxPane.add(addBoxBack,0,7);
        addBoxPane.add(addBoxEnter,7,7);

        addBoxScene = new Scene(addBoxPane, 1000, 500);
        // removeBoxScene///////////////////////////////////////////////////////////////////////////////////////////////
        //** removeBox(int containerID) **

        Label containerIDLabel2 = new Label("Box ID:");
        TextField containerIDtxt2 = new TextField();
        containerIDtxt2.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = containerIDtxt2.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        containerIDtxt2.setText(containerIDtxt2.getText().substring(0,containerIDtxt2.getText().length()-1));
                    }
                }
            }

        });
        Text removeBoxResponse = new Text();
        Button removeBoxEnter = new Button("Enter");
        removeBoxEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(containerIDtxt2.getText().isEmpty())
                    removeBoxResponse.setText("Please make sure no field is empty");
                else
                removeBoxResponse.setText(removeBox(Integer.parseInt(containerIDtxt2.getText())));
            }});

        removeBoxPane = new GridPane();
        removeBoxPane.setAlignment(Pos.CENTER);
        removeBoxPane.setHgap(10);
        removeBoxPane.setVgap(10);
        removeBoxPane.setPadding(new Insets(25, 25, 25, 25));
        removeBoxPane.setVgap(10);

        removeBoxPane.add(removeBoxResponse, 7, 8);
        removeBoxPane.add(containerIDLabel2,0,0);
        removeBoxPane.add(containerIDtxt2,1,0);
        removeBoxPane.add(removeBoxBack,0,7);
        removeBoxPane.add(removeBoxEnter,7,7);

        removeBoxScene = new Scene(removeBoxPane, 1000, 500);

        //findBoxlessSamples////////////////////////////////////////////////////////////////////////////////////////////
        ListView<String> boxlessSamples = new ListView<String>();
        ObservableList<String> boxlessSamplesItems = FXCollections.observableArrayList ();

        Map<String, String> boxless = findBoxlessSamples();
        for (String key : boxless.keySet()) {
            String result1 = key + " , " + boxless.get(key);
            boxlessSamplesItems.add(result1);
        }

        boxlessSamples.setItems(boxlessSamplesItems);

        findBoxlessSamplesPane = new GridPane();
        findBoxlessSamplesPane.setAlignment(Pos.CENTER);
        findBoxlessSamplesPane.setHgap(10);
        findBoxlessSamplesPane.setVgap(10);
        findBoxlessSamplesPane.setPadding(new Insets(25, 25, 25, 25));
        findBoxlessSamplesPane.setVgap(10);

        findBoxlessSamplesPane.add(boxlessSamples,0,0);
        findBoxlessSamplesPane.add(findBoxlessSamplesBack,0,7);

        findBoxlessSamplesScene = new Scene(findBoxlessSamplesPane, 1000, 500);

        //findContainersUnderCapacity//////////////////////////////////////////////////////////////////////////////////

        ListView<String> containersUnderCapacity = new ListView<String>();
        ObservableList<String> containersUnderCapacityItems = FXCollections.observableArrayList ();

        Map<String, String[]> under = findContainersUnderCapacity();
        for (String key : under.keySet()) {
            String value = "";
            for (int i = 0; i < under.get(key).length - 1; i++) {
                String next = under.get(key)[i];
                if (i == 0) {
                    value = next;
                }
                else {
                    value = value + " , " + next;
                }
            }
            containersUnderCapacityItems.add(key + " , " + value);
        }

        containersUnderCapacity.setItems(containersUnderCapacityItems);

        findContainersUnderCapacityPane = new GridPane();
        findContainersUnderCapacityPane.setAlignment(Pos.CENTER);
        findContainersUnderCapacityPane.setHgap(10);
        findContainersUnderCapacityPane.setVgap(10);
        findContainersUnderCapacityPane.setPadding(new Insets(25, 25, 25, 25));
        findContainersUnderCapacityPane.setVgap(10);

        findContainersUnderCapacityPane.add(containersUnderCapacity,0,0);
        findContainersUnderCapacityPane.add(findContainersUnderCapacityBack,0,7);

        findContainersUnderCapacityScene = new Scene(findContainersUnderCapacityPane, 1000, 500);
        // generateWorkList/////////////////////////////////////////////////////////////////////////////////////////////
        ListView<String> WorkList = new ListView<String>();
        ObservableList<String> WorkListItems = FXCollections.observableArrayList ();
        Button generateWorkListEnter = new Button("Enter");
        generateWorkListEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                WorkList.getItems().clear();
                WorkListItems.removeAll();
                Map<String, String[]> workList = generateWorkList();
                for (String key : workList.keySet()) {
                    WorkListItems.add(key + "  ,   " + workList.get(key)[0]);
                }

                WorkList.setItems(WorkListItems);
            }});
        /*
        Map<String, String[]> workList = generateWorkList();
        for (String key : workList.keySet()) {
            WorkListItems.add(key + "  ,   " + workList.get(key)[0]);
        }

        WorkList.setItems(WorkListItems);
*/
        generateWorkListPane = new GridPane();
        generateWorkListPane.setAlignment(Pos.CENTER);
        generateWorkListPane.setHgap(10);
        generateWorkListPane.setVgap(10);
        generateWorkListPane.setPadding(new Insets(25, 25, 25, 25));
        generateWorkListPane.setVgap(10);

        generateWorkListPane.add(WorkList,0,0);
        generateWorkListPane.add(generateWorkListBack,0,7);
        generateWorkListPane.add(generateWorkListEnter,7,7);

        generateWorkListScene = new Scene(generateWorkListPane, 1000, 500);

        // generateSampleList///////////////////////////////////////////////////////////////////////////////////////////
        ListView<String> samplelist = new ListView<>();
        ObservableList<String> samplelistItems =FXCollections.observableArrayList ();

        Button generateSampleListEnter = new Button("Enter");
        generateSampleListEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                samplelistItems.removeAll();
                samplelist.getItems().clear();
                Map<String, String[]> sampleList = generateSampleList();
                for (String key : sampleList.keySet()) {
                    String sampleProperties = "";
                    for(String element: sampleList.get(key)) {
                        //samplelistItems.add(key + ", " + element);
                        sampleProperties += element + ",   ";
                    }
                    samplelistItems.add(key + " : " + sampleProperties);

                }
                samplelist.setItems(samplelistItems);
            }});
        /*
        ListView<String> samplelist = new ListView<>();

        ObservableList<String> samplelistItems =FXCollections.observableArrayList ();

        Map<String, String[]> sampleList = generateSampleList();
        for (String key : sampleList.keySet()) {
            String sampleProperties = "";
            for(String element: sampleList.get(key)) {
                //samplelistItems.add(key + ", " + element);
                sampleProperties += element + ",   ";
            }
            samplelistItems.add(key + " : " + sampleProperties);

        }
        samplelist.setItems(samplelistItems);*/

        generateSampleListPane = new GridPane();
        generateSampleListPane.setAlignment(Pos.CENTER);
        generateSampleListPane.setHgap(10);
        generateSampleListPane.setVgap(10);
        generateSampleListPane.setPadding(new Insets(25, 25, 25, 25));
        generateSampleListPane.setVgap(10);

        generateSampleListPane.add(samplelist,0,0);
        generateSampleListPane.add(generateSampleListBack,0,7);
        generateSampleListPane.add(generateSampleListEnter,7,7);

        generateSampleListScene = new Scene(generateSampleListPane, 1000, 500);

        //findResearchDurationsByResearcher////////////////////////////////////////////////////////////////////////////
        ListView<String> ResearchDurationsByResearcher = new ListView<>();
        ObservableList<String> empty =FXCollections.observableArrayList ();
        ObservableList<String> ResearchDurationsByResearcherItems =FXCollections.observableArrayList ();

        Button average = new Button("Average Research Duration"); //0
        average.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ResearchDurationsByResearcher.getItems().clear();
                ResearchDurationsByResearcherItems.removeAll();
                Map<String, String[]> researcherduration = findResearchDurationsByResearcher(0);
                for (String key : researcherduration.keySet()) {
                    String sampleProperties = "";
                    for(String element: researcherduration.get(key)) {
                        sampleProperties += element + ",   ";
                    }
                    ResearchDurationsByResearcherItems.add(key + " : " + sampleProperties);

                }
                ResearchDurationsByResearcher.setItems(ResearchDurationsByResearcherItems);
                findResearchDurationsByResearcherPane.add(ResearchDurationsByResearcher,0,1);
            }});
        Button minimum = new Button("Minimum Research Duration"); //1
        minimum.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ResearchDurationsByResearcher.getItems().clear();
                ResearchDurationsByResearcherItems.removeAll();
                Map<String, String[]> researcherduration = findResearchDurationsByResearcher(1);
                for (String key : researcherduration.keySet()) {
                    String sampleProperties = "";
                    for(String element: researcherduration.get(key)) {
                        sampleProperties += element + ",   ";
                    }
                    ResearchDurationsByResearcherItems.add(key + " : " + sampleProperties);

                }
                ResearchDurationsByResearcher.setItems(ResearchDurationsByResearcherItems);
                findResearchDurationsByResearcherPane.add(ResearchDurationsByResearcher,1,1);
            }});
        Button maximum = new Button("Maximum Research Duration"); //2
        maximum.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ResearchDurationsByResearcher.getItems().clear();
                ResearchDurationsByResearcherItems.removeAll();
                Map<String, String[]> researcherduration = findResearchDurationsByResearcher(2);
                for (String key : researcherduration.keySet()) {
                    String sampleProperties = "";
                    for(String element: researcherduration.get(key)) {
                        sampleProperties += element + ",   ";
                    }
                    ResearchDurationsByResearcherItems.add(key + " : " + sampleProperties);

                }
                ResearchDurationsByResearcher.setItems(ResearchDurationsByResearcherItems);
                findResearchDurationsByResearcherPane.add(ResearchDurationsByResearcher,2,1);
            }});
        Button total = new Button("Total Research Number"); //3
        total.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ResearchDurationsByResearcher.getItems().clear();
                ResearchDurationsByResearcherItems.removeAll();
                Map<String, String[]> researcherduration = findResearchDurationsByResearcher(3);
                for (String key : researcherduration.keySet()) {
                    String sampleProperties = "";
                    for(String element: researcherduration.get(key)) {
                        sampleProperties += element + ",   ";
                    }
                    ResearchDurationsByResearcherItems.add(key + " : " + sampleProperties);

                }
                ResearchDurationsByResearcher.setItems(ResearchDurationsByResearcherItems);
                findResearchDurationsByResearcherPane.add(ResearchDurationsByResearcher,3,1);
            }});

        findResearchDurationsByResearcherPane = new GridPane();
        findResearchDurationsByResearcherPane.setAlignment(Pos.CENTER);
        findResearchDurationsByResearcherPane.setHgap(10);
        findResearchDurationsByResearcherPane.setVgap(10);
        findResearchDurationsByResearcherPane.setPadding(new Insets(25, 25, 25, 25));
        findResearchDurationsByResearcherPane.setVgap(10);

        findResearchDurationsByResearcherPane.add(average,0,0);
        findResearchDurationsByResearcherPane.add(minimum,1,0);
        findResearchDurationsByResearcherPane.add(maximum,2,0);
        findResearchDurationsByResearcherPane.add(total,3,0);
        findResearchDurationsByResearcherPane.add(findResearchDurationsByResearcherBack,0,7);

        findResearchDurationsByResearcherScene = new Scene(findResearchDurationsByResearcherPane, 1000, 500);
        // update Temperature////////////////////////////////////////////////////////////////////////////
        Label updateFridgeID = new Label("Fridge ID:");
        TextField updateFridgeIDtxt = new TextField();
        updateFridgeIDtxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = updateFridgeIDtxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        updateFridgeIDtxt.setText(updateFridgeIDtxt.getText().substring(0,updateFridgeIDtxt.getText().length()-1));
                    }
                }
            }

        });
        Label updateFridgeTemp = new Label("New Temperature:");
        TextField updateFridgeTemptxt = new TextField();
        updateFridgeTemptxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = updateFridgeTemptxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        updateFridgeTemptxt.setText(updateFridgeTemptxt.getText().substring(0,updateFridgeTemptxt.getText().length()-1));
                    }
                }
            }

        });
        Text updateFridgeResponse = new Text();
        Button updateFridgeEnter = new Button("Enter");
        updateFridgeEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if(updateFridgeIDtxt.getText().isEmpty()||updateFridgeTemptxt.getText().isEmpty())
                    updateFridgeResponse.setText("Make sure no field is empty");
                else{updateFridgeResponse.setText(updateTemperature(Integer.parseInt(updateFridgeIDtxt.getText()),
                        Integer.parseInt(updateFridgeTemptxt.getText())));}
            }});

        updateTemperaturePane = new GridPane();
        updateTemperaturePane.setAlignment(Pos.CENTER);
        updateTemperaturePane.setHgap(10);
        updateTemperaturePane.setVgap(10);
        updateTemperaturePane.setPadding(new Insets(25, 25, 25, 25));
        updateTemperaturePane.setVgap(10);

        updateTemperaturePane.add(updateFridgeResponse, 7, 8);
        updateTemperaturePane.add(updateFridgeID,0,0);
        updateTemperaturePane.add(updateFridgeIDtxt,1,0);
        updateTemperaturePane.add(updateFridgeTemp,0,1);
        updateTemperaturePane.add(updateFridgeTemptxt,1,1);
        updateTemperaturePane.add(updateTemperatureBack,0,7);
        updateTemperaturePane.add(updateFridgeEnter,7,7);

        updateTemperatureScene = new Scene(updateTemperaturePane, 1000, 500);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        primaryStage.setScene(entryScene);

        primaryStage.show();
    }

    public void ButtonClicked(ActionEvent e) {
        if((e.getSource()==addFridgeBack)||(e.getSource()==removeFridgeBack)||(e.getSource()==addResearcherBack)||(e.getSource()==addLabManagerBack)
                ||(e.getSource()==removeLabManagerBack)|| (e.getSource()==addSampleToBoxBack)|| (e.getSource()==removeSampleFromBoxBack)|| (e.getSource()==addBoxBack)
                || (e.getSource()==removeBoxBack)|| (e.getSource()==findBoxlessSamplesBack)|| (e.getSource()==findContainersUnderCapacityBack)|| (e.getSource()==generateWorkListBack)
                || (e.getSource()==generateSampleListBack)|| (e.getSource()==findResearchDurationsByResearcherBack)|| (e.getSource()==updateTemperatureBack))
            theStage.setScene(entryScene);
        if (e.getSource()==addFridge)
            theStage.setScene(addFridgeScene);
        if (e.getSource()==removeFridge)
            theStage.setScene(removeFridgeScene);
        if (e.getSource()==addResearcher)
            theStage.setScene(addResearcherScene);
        if (e.getSource()==addLabManager)
            theStage.setScene(addLabManagerScene);
        if (e.getSource()==removeLabManager)
            theStage.setScene(removeLabManagerScene);
        if (e.getSource()==addSampleToBox)
            theStage.setScene(addSampleToBoxScene);
        if (e.getSource()==removeSampleFromBox)
            theStage.setScene(removeSampleFromBoxScene);
        if (e.getSource()==addBox)
            theStage.setScene(addBoxScene);
        if (e.getSource()==removeBox)
            theStage.setScene(removeBoxScene);
        if (e.getSource()==findBoxlessSamples)
            theStage.setScene(findBoxlessSamplesScene);
        if (e.getSource()==findContainersUnderCapacity)
            theStage.setScene(findContainersUnderCapacityScene);
        if (e.getSource()==generateWorkList)
            theStage.setScene(generateWorkListScene);
        if (e.getSource()==generateSampleList)
            theStage.setScene(generateSampleListScene);
        if (e.getSource()==findResearchDurationsByResearcher)
            theStage.setScene(findResearchDurationsByResearcherScene);
        if (e.getSource()==updateTemperature)
            theStage.setScene(updateTemperatureScene);

    }

    // Added temperature constraint to fridge
    public String addFridge(int temperature, int employeeID){

        if(temperature >0){
           return "Invalid temperature input: The temperature must be negative";
        }
        PreparedStatement ps1;
        PreparedStatement ps2;
        ResultSet rs;
        Statement stmt;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {

                con = connectionToDatabase.getConnection();

                stmt = con.createStatement();
                rs = stmt.executeQuery("select max(fr_id) as max from fridge2");
                int fridgeID = 0;
                if(rs.next()){
                    fridgeID = rs.getInt("max") + 1;
                }

                    ps1 = con.prepareStatement("INSERT INTO fridge2 VALUES (?,?,?)");
                    ps1.setInt(1, fridgeID);
                    ps1.setInt(2, 0);
                    ps1.setInt(3, temperature);

                    ps1.executeUpdate();

                    ps1.close();

                    ps2 = con.prepareStatement("INSERT INTO maintains VALUES (?,?,?)");
                    ps2.setInt(1, fridgeID);
                    ps2.setInt(2, 0);
                    ps2.setInt(3, employeeID);

                    ps2.executeUpdate();
                    con.commit();

                    ps2.close();
                    return "Fridge added! The new fridge has id " + fridgeID;

                //}

            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the insert
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }

        return "Error adding the fridge: Please try again";
    }

    public String removeFridge(int fridgeID) {
        PreparedStatement ps1;

        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {

                con = connectionToDatabase.getConnection();

                final String queryCheck = "SELECT * from fridge2 WHERE fr_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, fridgeID);
                final ResultSet resultSet = psCheck.executeQuery();
                if (resultSet.next()) {
                    //int occupancy = resultSet.getInt("f_occupancy");
                    final String queryCheck2 = "SELECT * from container2 WHERE fr_id = ?";
                    final PreparedStatement psCheck2 = con.prepareStatement(queryCheck2);
                    psCheck2.setInt(1, fridgeID);
                    final ResultSet resultSet2 = psCheck2.executeQuery();
                    if(resultSet2.next()){
                        return "Error: There are still containers in this fridge.";

                    }
                    else{
                        ps1 = con.prepareStatement("DELETE FROM fridge2 WHERE fr_id = ?");
                        ps1.setInt(1, fridgeID);

                        ps1.executeUpdate();
                        con.commit();

                        ps1.close();
                        return "Fridge removed!";
                    }


                } else {
                    return "Error: This fridge ID is non-existent";
                }

            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the delete
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }
        return "Error removing the fridge: Please try again";
    }

    public String updateTemperature(int fridgeID, int temperature) {
        Statement stmt1;
        ResultSet results;

        PreparedStatement stmt;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt1 = con.createStatement();
                results = stmt1.executeQuery("select max(fr_id) from fridge2");
                if (results.next()) {
                    if (results.getInt("max(fr_id)") < fridgeID) {
                        return "Fridge ID is not valid!";
                    }
                }
                stmt = con.prepareStatement("update fridge2 set temperature = " +
                        temperature + "where fr_id = " + fridgeID);
                stmt.executeUpdate();
                con.commit();

                if (temperature > 0) {
                    return "New temperature is not valid, must be negative!";
                }

                stmt.close();
                return "Temperature updated!";

            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        return "Error!";
    }

    public String addResearcher(String employeeName){
        PreparedStatement ps1;
        ResultSet rs;
        Statement stmt;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                //two more things: check if the fridgeID is already existing & add to maintains table

                con = connectionToDatabase.getConnection();

                stmt = con.createStatement();
                rs = stmt.executeQuery("select max(emp_id) as max from researcher");
                int employeeID = 0;
                if(rs.next()){
                    employeeID = rs.getInt("max") + 1;
                }

                    ps1 = con.prepareStatement("INSERT INTO researcher VALUES (?,?)");
                    ps1.setInt(1, employeeID);
                    ps1.setString(2, employeeName);

                    ps1.executeUpdate();
                    con.commit();

                    ps1.close();
                    return "Researcher added! The new researcher has Employee ID " + employeeID;

                //}

            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the insert
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }

        return "Error adding the researcher: Please try again";
    }


    public String addLabManager(String employeeName){
        PreparedStatement ps1;
        ResultSet rs;
        Statement stmt;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                //two more things: check if the fridgeID is already existing & add to maintains table

                con = connectionToDatabase.getConnection();

                stmt = con.createStatement();
                rs = stmt.executeQuery("select max(emp_id) as max from lab_manager");
                int employeeID = 0;
                if(rs.next()){
                    employeeID = rs.getInt("max") + 1;
                }

                    ps1 = con.prepareStatement("INSERT INTO lab_manager VALUES (?,?)");
                    ps1.setInt(1, employeeID);
                    ps1.setString(2, employeeName);

                    ps1.executeUpdate();
                    con.commit();

                    ps1.close();
                    return "Lab manager added! The new lab manager has Employee ID " + employeeID;

                //}

            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the insert
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }

        return "Error adding the lab manager: Please try again";
    }

    public String removeLabManager(int employeeID){

        PreparedStatement ps1;

        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {

                con = connectionToDatabase.getConnection();

                final String queryCheck = "SELECT * from lab_manager WHERE emp_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, employeeID);
                final ResultSet resultSet = psCheck.executeQuery();
                if(resultSet.next()) {
                    ps1 = con.prepareStatement("DELETE FROM lab_manager WHERE emp_id = ?");
                    ps1.setInt(1, employeeID);

                    ps1.executeUpdate();
                    con.commit();

                    ps1.close();
                    return "Lab Manager removed!";


                }
                else{
                    return "Error: This Lab Manager ID is non-existent";
                }

            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the delete
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }

        return "Error removing the Lab Manager: Please try again";

    }

    public String addSampleToBox(int containerID, int sampleID, int employeeID){
        PreparedStatement ps0;
        PreparedStatement ps1;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                //two more things: check if the fridgeID is already existing & add to maintains table

                con = connectionToDatabase.getConnection();

                final String queryCheck = "SELECT * from sample WHERE samp_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, sampleID);
                final ResultSet resultSet = psCheck.executeQuery();
                if(resultSet.next()) {

                    final String queryCheck2 = "SELECT * from container2 WHERE c_id = ?";
                    final PreparedStatement psCheck2 = con.prepareStatement(queryCheck2);
                    psCheck2.setInt(1, containerID);
                    final ResultSet resultSet2 = psCheck2.executeQuery();

                    if(resultSet2.next()){
                        int fridgeID = resultSet2.getInt("fr_id");
                        int containerOccupancy = resultSet2.getInt("c_occupancy");
                        int fridgeOccupancy = resultSet2.getInt("f_occupancy");

                        final String queryCheck3 = "SELECT * from container1 WHERE c_occupancy = ?";
                        final PreparedStatement psCheck3 = con.prepareStatement(queryCheck3);
                        psCheck3.setInt(1, containerOccupancy);
                        final ResultSet resultSet3 = psCheck3.executeQuery();

                        if(resultSet3.next()){
                            int condition = resultSet3.getInt("at_capacity");
                            if(condition == 0){
                                /*ps0 = con.prepareStatement("UPDATE container2 SET c_occupancy = ? WHERE c_id = ?");

                                ps0.setInt(1, containerOccupancy + 1);
                                ps0.setInt(2, containerID);
                                ps0.executeUpdate();
                                con.commit();
                                ps0.close();*/

                                ps1 = con.prepareStatement("INSERT INTO contains VALUES (?,?,?,?,?,?)");
                                ps1.setInt(1, containerID);
                                ps1.setInt(2, fridgeID);
                                ps1.setInt(3, containerOccupancy);
                                ps1.setInt(4, fridgeOccupancy);
                                ps1.setInt(5, sampleID);
                                ps1.setInt(6, employeeID);

                                ps1.executeUpdate();
                                con.commit();

                                ps1.close();
                                return "Sample added to box!";
                            }
                            else{
                                return "Error: This box is at capacity";
                            }
                        }


                    }
                    else{
                        return "Error: This box ID is non-existent";
                    }

                }
                else{
                    return "Error: This sample ID is non-existent";
                }

            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the insert
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }
        return "Error adding sample to box: Please try again";
    }

    public String removeSampleFromBox(int containerID, int sampleID){
        PreparedStatement ps0;
        PreparedStatement ps1;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                //two more things: check if the fridgeID is already existing & add to maintains table

                con = connectionToDatabase.getConnection();

                final String queryCheck = "SELECT * from sample WHERE samp_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, sampleID);
                final ResultSet resultSet = psCheck.executeQuery();
                if(resultSet.next()) {

                    final String queryCheck2 = "SELECT * from container2 WHERE c_id = ?";
                    final PreparedStatement psCheck2 = con.prepareStatement(queryCheck2);
                    psCheck2.setInt(1, containerID);
                    final ResultSet resultSet2 = psCheck2.executeQuery();

                    if(resultSet2.next()){

                                ps1 = con.prepareStatement("DELETE FROM contains WHERE c_id = ? AND samp_id = ?");
                                ps1.setInt(1, containerID);
                                ps1.setInt(2, sampleID);

                                ps1.executeUpdate();
                                con.commit();

                                ps1.close();
                                return "Sample removed from box!";
                    }
                    else{
                        return "Error: This box ID is non-existent";
                    }

                }
                else{
                    return "Error: This sample ID is non-existent";
                }

            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the insert
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }
        return "Error removing the sample from box: Please try again";
    }

    public String addBox(String containerName, int fridgeID) {

        PreparedStatement ps1;
        ResultSet rs;
        Statement stmt;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                con = connectionToDatabase.getConnection();

                final String queryCheck = "SELECT * from fridge2 WHERE fr_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, fridgeID);
                final ResultSet resultSet = psCheck.executeQuery();
                if(resultSet.next()) {
                    int occupancy = resultSet.getInt("f_occupancy");
                    final String queryCheck2 = "SELECT * from fridge1 where f_occupancy = ?";
                    final PreparedStatement psCheck2 = con.prepareStatement(queryCheck2);
                    psCheck2.setInt(1, occupancy);
                    final ResultSet resultSet2 = psCheck2.executeQuery();

                    if(resultSet2.next()){
                        int condition = resultSet2.getInt("at_capacity");
                        if(condition == 0){

                            stmt = con.createStatement();
                            rs = stmt.executeQuery("select max(c_id) as max from container2");
                            int containerID = 0;
                            if(rs.next()){
                                containerID = rs.getInt("max") + 1;
                            }

                            java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());

                            ps1 = con.prepareStatement("INSERT INTO container2 VALUES (?,?,?,?,?,?)");
                            ps1.setInt(1, occupancy);
                            ps1.setString(2, containerName);
                            ps1.setInt(3, 0);
                            ps1.setInt(4, containerID);
                            ps1.setInt(5, fridgeID);
                            ps1.setDate(6, ourJavaDateObject);

                            ps1.executeUpdate();
                            con.commit();

                            ps1.close();
                            return "Box added! The new box has ID " + containerID;

                        }
                        else{
                            return "Error: this fridge is at capacity";
                        }
                    }
                }
                else{
                    return "Error: this fridge ID is non-existent";
                }


            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the insert
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }

        return "Error adding the box: Please try again";
    }

    public String removeBox(int containerID) {
        PreparedStatement ps1;
        ResultSet rs;
        Statement stmt;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                con = connectionToDatabase.getConnection();

                final String queryCheck = "SELECT * from container2 WHERE c_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, containerID);
                final ResultSet resultSet = psCheck.executeQuery();
                if(resultSet.next()) {
                    int occupancy = resultSet.getInt("c_occupancy");
                    if(occupancy == 0){
                        ps1 = con.prepareStatement("DELETE FROM container2 WHERE c_id = ?");
                        ps1.setInt(1, containerID);

                        ps1.executeUpdate();
                        con.commit();

                        ps1.close();
                        return "Box removed!";
                    }
                    else{
                        return "Error: the occupancy of the box is not 0";
                    }

                }
                else{
                    return "Error: this box ID is non-existent";
                }


            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the insert
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }

        return "Error removing the box: Please try again";
    }
    
    //Query 10
    public Map<String, String> findBoxlessSamples(){
        Statement stmt;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt = con.createStatement();
                String sampleID;
                String dateCreated;
                Map<String, String> boxlessSamples = new HashMap<String, String>();

                results = stmt.executeQuery("select s.samp_id, s.date_cr from sample s LEFT JOIN " +
                        "contains c on s.samp_id = c.samp_id where c.c_id IS NULL order by s.samp_id");
                while (results.next()) {
                    //Get the values at each row
                   // if (!results.wasNull()) {
                    sampleID = "Sample ID: " + results.getString("samp_id");
                    dateCreated = "Date Created: " + results.getDate("date_cr").toString();
                        boxlessSamples.put(sampleID, dateCreated);
                    //}
                }
                stmt.close();
                return boxlessSamples;

            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        return null;
    }

    //Query 11
    public Map<String, String[]> findContainersUnderCapacity(){
        Statement stmt;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt = con.createStatement();
                String containerID;
                String fridgeID;
                String fridgeOccupancy;
                String containerOccupancy;
                String containerName;

                Map<String, String[]> underCapacityContainers = new HashMap<String, String[]>();

                results = stmt.executeQuery("select c2.f_occupancy, c2.c_name, c2.c_occupancy, " +
                        "c2.c_id, c2.fr_id, c2.c_date from container1 c1 LEFT JOIN container2 c2 " +
                        "on c2.c_occupancy=c1.c_occupancy where c1.at_capacity=0 AND c2.f_occupancy " +
                        "IS NOT NULL");
                while (results.next()) {
                    containerID = "Container ID: " + results.getString("c_id");
                    fridgeID = results.getString("fr_id");
                    fridgeOccupancy = results.getString("f_occupancy");
                    containerOccupancy = results.getString("c_occupancy");
                    containerName = results.getString("c_name");
                    if (!results.wasNull()) {
                        String[] attributes = {"Container Name: " + containerName, "Container Occupancy: " +
                        containerOccupancy, "Fridge Occupancy: " + fridgeOccupancy, "FridgeID: " +
                        fridgeID};
                        underCapacityContainers.put(containerID, attributes);
                    }
                }
                stmt.close();

                return underCapacityContainers;
            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        return null;
    }

    @Override
    //Query 12
    public Map<String, String[]> generateWorkList() {
        Statement stmt1;
        Statement stmt2;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            //if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt1 = con.createStatement();
                stmt2 = con.createStatement();
                String name;
                String EmployeeID;
                Map<String, String[]> workerList = new HashMap<String, String[]>();

                results = stmt1.executeQuery("select name, emp_id from lab_manager");
                while (results.next()) {
                    name = results.getString("name");
                    EmployeeID = "EmployeeID: " + results.getString("emp_id");
                    if (!results.wasNull()) {
                        String[] workerAttributes = {"Name: " + name + " ,  " + "Type: Lab Manager"};
                        workerList.put(EmployeeID, workerAttributes);
                    }
                }
                stmt1.close();

                results = stmt2.executeQuery("select name, emp_id from researcher");
                while (results.next()) {
                    name = results.getString("name");
                    EmployeeID = "EmployeeID: " + results.getString("emp_id");
                    if (!results.wasNull()) {
                        if (workerList.get(EmployeeID) == null) {
                            String[] workerAttributes = {"Name: " + name + " ,  " + "Type: Researcher"};
                            workerList.put(EmployeeID, workerAttributes);
                        } else {
                            String[] workerAttributes = {"Name: " + name + " ,  " + "Type: Lab Manager and Researcher"};
                            workerList.put(EmployeeID, workerAttributes);
                        }
                    }
                }
                stmt1.close();
                return workerList;
            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        return null;
    }

    @Override
    //Query 13
    public Map<String, String[]> generateSampleList() {
        Statement stmt;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            //if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt = con.createStatement();
                String sampleID;
                String strain;
                String volume;
                String composition;
                String concentration;
                String name;
                String antibiotic;
                String res_enz_1;
                String res_enz_2;
                String origin;
                String part1;
                String part2;
                String empID;
                String dateCreated;
                Map<String, String[]> sampleList = new HashMap<String, String[]>();

                results = stmt.executeQuery("select s.samp_id, s.date_cr, p1.composition, b.strain, d1.concentration, " +
                        "p2.name, p2.antibiotic, d2.res_enz_1, d2.res_enz_2, g1.origin, l.part1, l.part2, " +
                        "r.emp_id, g2.volume from sample s LEFT JOIN plate p1 on s.samp_id = p1.sample_id " +
                        "LEFT JOIN bacterial_culture b on s.samp_id = b.sample_id LEFT JOIN dna_sample d1 on " +
                        "s.samp_id = d1.sample_id LEFT JOIN plasmid p2 on s.samp_id = p2.sample_id LEFT JOIN " +
                        "digest d2 on s.samp_id = d2.sample_id LEFT JOIN genomic g1 on s.samp_id = g1.sample_id " +
                        "LEFT JOIN ligation l on s.samp_id = l.sample_id LEFT JOIN researches r on s.samp_id = " +
                        "r.samp_id LEFT JOIN glycerol_stock g2 on s.samp_id = g2.sample_id");
                while (results.next()) {
                    //All the attributes
                    name = results.getString("name");
                    sampleID = results.getString("samp_id");
                    volume = results.getString("volume");
                    composition = results.getString("composition");
                    strain = results.getString("strain");
                    concentration = results.getString("concentration");
                    antibiotic = results.getString("antibiotic");
                    res_enz_1 = results.getString("res_enz_1");
                    res_enz_2 = results.getString("res_enz_2");
                    origin = results.getString("origin");
                    part1 = results.getString("part1");
                    part2 = results.getString("part2");
                    empID = results.getString("emp_id");
                    dateCreated = results.getDate("date_cr").toString();
                    if (empID != null) {
                        // There is someone resarching the sample
                        if (part1 != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated, "Concentration: " + concentration, "Part1: " + part1, "Part2: " + part2};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (origin != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated, "Concentration: " + concentration, "Origin: " + origin};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (res_enz_1 != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated, "Concentration: " + concentration, "Restriction Enzyme 1: " + res_enz_1, "Restriction Enzyme 2: " + res_enz_2};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (name != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated, "Concentration: " + concentration, "Name: " + name, "Antibiotic: " + antibiotic};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (res_enz_1 != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated, "Concentration: " + concentration, "Restriction Enzyme 1: " + res_enz_1, "Restriction Enzyme 2: " + res_enz_2};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (concentration != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated, "Concentration: " + concentration};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (composition != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated, "Strain: " + strain, "Composition: " + composition};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (volume != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated, "Strain: " + strain, "Volume: " + volume};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (strain != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated, "Strain: " + strain};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (sampleID != null) {
                            String[] sampleAttributes = {"Researcher ID: " + empID, "Date Created: " + dateCreated};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                    } else //There is nobody researching the sample
                    {
                        if (part1 != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated, "Concentration: " + concentration, "Part1: " + part1, "Part2: " + part2};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (origin != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated, "Concentration: " + concentration, "Origin: " + origin};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (res_enz_1 != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated, "Concentration: " + concentration, "Restriction Enzyme 1: " + res_enz_1, "Restriction Enzyme 2: " + res_enz_2};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (name != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated, "Concentration: " + concentration, "Name: " + name, "Antibiotic: " + antibiotic};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (res_enz_1 != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated, "Concentration: " + concentration, "Restriction Enzyme 1: " + res_enz_1, "Restriction Enzyme 2: " + res_enz_2};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (concentration != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated, concentration};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (composition != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated, "Strain: " + strain, "Composition: " + composition};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (volume != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated, "Strain: " + strain, "Volume: " + volume};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (strain != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated, "Strain: " + strain};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                        else if (sampleID != null) {
                            String[] sampleAttributes = {"Date Created: " + dateCreated};
                            sampleList.put("SampleID: " + sampleID, sampleAttributes);
                        }
                    }
                }
                stmt.close();
                return sampleList;

            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        return null;
    }
    //aggregationType = 0 for average, 1 for min, 2 for max, 3 for count
    public Map<String, String[]> findResearchDurationsByResearcher(int aggregationType){
        Statement stmt;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            //if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt = con.createStatement();
                Map<String, String[]> durationList = new HashMap<String, String[]>();


                switch(aggregationType){
                    case 0:
                        results = stmt.executeQuery("select emp_id, avg(duration) as special from researches group by emp_id");
                        while (results.next()) {
                            String employeeID = results.getString("emp_id");
                            String special = results.getString("special");
                            if (!results.wasNull()) {
                                String[] durationAttributes = {"Average Duration Requested: " + special};
                                durationList.put(employeeID, durationAttributes);
                            }
                        }

                        break;
                    case 1:
                        results = stmt.executeQuery("select emp_id, min(duration) as special from researches group by emp_id");
                        while (results.next()) {
                            String employeeID = results.getString("emp_id");
                            String special = results.getString("special");
                            if (!results.wasNull()) {
                                String[] durationAttributes = {"Minimum Duration Requested: " + special};
                                durationList.put(employeeID, durationAttributes);
                            }
                        }
                        break;
                    case 2:
                        results = stmt.executeQuery("select emp_id, max(duration) as special from researches group by emp_id");
                        while (results.next()) {
                            String employeeID = results.getString("emp_id");
                            String special = results.getString("special");
                            if (!results.wasNull()) {
                                String[] durationAttributes = {"Maximum Duration Requested: " + special};
                                durationList.put(employeeID, durationAttributes);
                            }
                        }
                        break;
                    case 3:
                        results = stmt.executeQuery("select emp_id, count(duration) as special from researches group by emp_id");
                        while (results.next()) {
                            String employeeID = results.getString("emp_id");
                            String special = results.getString("special");
                            if (!results.wasNull()) {
                                String[] durationAttributes = {"Total Number of Durations Requested: " + special};
                                durationList.put(employeeID, durationAttributes);
                            }
                        }
                        break;
                    default:
                        return null;
                }



                stmt.close();

                return durationList;
            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        return null;
    }
}
