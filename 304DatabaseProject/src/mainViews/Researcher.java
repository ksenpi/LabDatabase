package mainViews;/**
 * Created by Tamar on 2016-11-10.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Map;

public class Researcher extends Application implements User {
    Button addSample, editSample, addSampleResearch, samplesCreatedBy, addBox, removeBox, addSampleBack, editSampleBack, addSampleResearchBack, samplesCreatedByBack, addBoxBack, removeBoxBack;
    Stage theStage;
    Scene entryScene, addSampleScene, editSampleScene, addSampleResearchScene, samplesCreatedByScene, addBoxScene, removeBoxScene;
    GridPane entryPane, addSamplePane, editSamplePane, addSampleResearchPane, samplesCreatedByPane, addBoxPane, removeBoxPane;
    final int researcherID;

    public Researcher(int researcherID) {
        this.researcherID = researcherID;
    }
    //added so we can call the class from another class
    static Stage classStage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //added so we can call the class from another class
        Researcher.classStage = primaryStage ;

        primaryStage.setTitle("Welcome to LabDatabasePro3000");

        theStage = primaryStage;

        addSampleBack = new Button("Go to Main Page");
        addSampleBack.setOnAction(e->ButtonClicked(e));
        editSampleBack = new Button("Go to Main Page");
        editSampleBack.setOnAction(e->ButtonClicked(e));
        addSampleResearchBack = new Button("Go to Main Page");
        addSampleResearchBack.setOnAction(e->ButtonClicked(e));
        samplesCreatedByBack = new Button("Go to Main Page");
        samplesCreatedByBack.setOnAction(e->ButtonClicked(e));
        addBoxBack = new Button("Go to Main Page");
        addBoxBack.setOnAction(e->ButtonClicked(e));
        removeBoxBack = new Button("Go to Main Page");
        removeBoxBack.setOnAction(e->ButtonClicked(e));

        //entryScene////////////////////////////////////////////////////////////////////////////////////////////////////
        addSample = new Button("Add Sample");
        editSample = new Button("Edit Sample");
        addSampleResearch = new Button("Research Sample");
        samplesCreatedBy = new Button("Samples Created By");
        addBox = new Button("Add Box");
        removeBox = new Button("Remove Box");
        //making button actions
        addSample.setOnAction(e-> ButtonClicked(e));
        editSample.setOnAction(e-> ButtonClicked(e));
        addSampleResearch.setOnAction(e-> ButtonClicked(e));
        samplesCreatedBy.setOnAction(e-> ButtonClicked(e));
        addBox.setOnAction(e-> ButtonClicked(e));
        removeBox.setOnAction(e-> ButtonClicked(e));
        //entryPane (gridpane)
        entryPane = new GridPane();
        entryPane.setAlignment(Pos.CENTER);
        entryPane.setHgap(10);
        entryPane.setVgap(10);
        entryPane.setPadding(new Insets(25, 25, 25, 25));
        entryPane.setVgap(10);
        //adding all the buttons to the entry pane
        entryPane.add(addSample, 0, 0);
        entryPane.add(editSample, 0, 1);
        entryPane.add(addSampleResearch, 1, 0);
        entryPane.add(samplesCreatedBy, 1, 1);
        entryPane.add(addBox, 2, 0);
        entryPane.add(removeBox, 2, 1);
        entryScene = new Scene(entryPane, 1000, 500);


        //addSampleScene////////////////////////////////////////////////////////////////////////////////////////////////
        //addSampleScene////////////////////////////////////////////////////////////////////////////////////////////////
        Label sampleType = new Label("Sample Type");
        ChoiceBox sampleTypeBox = new ChoiceBox();
        sampleTypeBox.setItems(FXCollections.observableArrayList("Bacterial Culture", "Glycerol Stock", "Plate", "DNA Sample", "Plasmid", "Ligation", "Genomic", "Digest"));
        //sampleTypeBox.getItems().addAll("Bacterial Culture", "Glycerol Stock", "Plate", "DNA Sample", "Plasmid", "Ligation", "Genomic", "Digest");
        Label strain = new Label("Strain:");
        TextField strainTextField = new TextField();
        Label volume = new Label("Volume(mL):");
        TextField volumeTextField = new TextField();
        volumeTextField.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = volumeTextField.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        volumeTextField.setText(volumeTextField.getText().substring(0,volumeTextField.getText().length()-1));
                    }
                }
            }

        });
        Label composition = new Label("Plate Composition:");
        TextField compositionTextField = new TextField();
        Label concentration = new Label("Concentration(ng/uL):");
        TextField concentrationTextField = new TextField();
        concentrationTextField.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = concentrationTextField.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        concentrationTextField.setText(concentrationTextField.getText().substring(0,concentrationTextField.getText().length()-1));
                    }
                }
            }

        });
        Label plasmidName = new Label("Plasmid Name:");
        TextField plasmidTextField = new TextField();
        Label plasmidAntibiotic = new Label("Plasmid Antibiotic:");
        TextField antibioticTextField = new TextField();
        Label rez1 = new Label("Restriction Enzyme 1:");
        TextField rez1TextField = new TextField();
        Label rez2 = new Label("Restriction Enzyme 2:");
        TextField rez2TextField = new TextField();
        Label genomic = new Label("Origin:");
        TextField genomicTextField = new TextField();
        Label ligation1 = new Label("Ligation Part 1:");
        TextField ligation1TextField = new TextField();
        Label ligation2 = new Label("Ligation Part 2:");
        TextField ligation2TextField = new TextField();

        Button enterAddSample = new Button("Enter");

        //entryPane (gridpane)
        addSamplePane = new GridPane();
        addSamplePane.setAlignment(Pos.CENTER);
        addSamplePane.setHgap(10);
        addSamplePane.setVgap(10);
        addSamplePane.setPadding(new Insets(25, 25, 25, 25));
        addSamplePane.setVgap(10);

        //adding all the buttons to the entry pane
        addSamplePane.add(sampleType, 0, 0);
        addSamplePane.add(sampleTypeBox, 1, 0);

        addSamplePane.add(addSampleBack,0,7);                  //check the placement of this button
        addSamplePane.add(enterAddSample,7,7);                   //check the placement of this button
        enterAddSample.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                addSample(); //todo - tamar- add the right call now
            }
        });

        sampleTypeBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addSamplePane.getChildren().remove(4, addSamplePane.getChildren().size());

                if(sampleTypeBox.getValue()=="Bacterial Culture"){
                    addSamplePane.add(strain, 0, 1);
                    addSamplePane.add(strainTextField, 1, 1);
                }
                if(sampleTypeBox.getValue()=="Glycerol Stock"){
                    addSamplePane.add(strain, 0, 1);
                    addSamplePane.add(strainTextField, 1, 1);
                    addSamplePane.add(volume, 0, 2);
                    addSamplePane.add(volumeTextField, 1, 2);
                }
                if(sampleTypeBox.getValue()=="Plate"){
                    addSamplePane.add(strain, 0, 1);
                    addSamplePane.add(strainTextField, 1, 1);
                    addSamplePane.add(composition, 0, 2);
                    addSamplePane.add(compositionTextField, 1, 2);
                }
                if(sampleTypeBox.getValue()=="DNA Sample"){
                    addSamplePane.add(concentration, 0, 1);
                    addSamplePane.add(concentrationTextField, 1, 1);
                }
                if(sampleTypeBox.getValue()=="Plasmid"){
                    addSamplePane.add(concentration, 0, 1);
                    addSamplePane.add(concentrationTextField, 1, 1);
                    addSamplePane.add(plasmidName, 0, 2);
                    addSamplePane.add(plasmidTextField, 1, 2);
                    addSamplePane.add(plasmidAntibiotic, 0, 3);
                    addSamplePane.add(antibioticTextField, 1, 3);
                }
                if(sampleTypeBox.getValue()=="Ligation"){
                    addSamplePane.add(concentration, 0, 1);
                    addSamplePane.add(concentrationTextField, 1, 1);
                    addSamplePane.add(ligation1, 0, 2);
                    addSamplePane.add(ligation1TextField, 1, 2);
                    addSamplePane.add(ligation2, 0, 3);
                    addSamplePane.add(ligation2TextField, 1, 3);
                }
                if(sampleTypeBox.getValue()=="Genomic"){
                    addSamplePane.add(concentration, 0, 1);
                    addSamplePane.add(concentrationTextField, 1, 1);
                    addSamplePane.add(genomic, 0, 2);
                    addSamplePane.add(genomicTextField, 1, 2);
                }
                if(sampleTypeBox.getValue()=="Digest"){
                    addSamplePane.add(concentration, 0, 1);
                    addSamplePane.add(concentrationTextField, 1, 1);
                    addSamplePane.add(rez1, 0, 2);
                    addSamplePane.add(rez1TextField, 1, 2);
                    addSamplePane.add(rez2, 0, 3);
                    addSamplePane.add(rez2TextField, 1, 3);
                }

            }


        });

        addSampleScene = new Scene(addSamplePane, 1000, 500);

        // editSampleScene//////////////////////////////////////////////////////////////////////////////////////////////
        Label sampleType1 = new Label("Sample Type");
        ChoiceBox sampleTypeBox1 = new ChoiceBox();
        sampleTypeBox1.setItems(FXCollections.observableArrayList("Bacterial Culture", "Glycerol Stock", "Plate", "DNA Sample", "Plasmid", "Ligation", "Genomic", "Digest"));
        //sampleTypeBox.getItems().addAll("Bacterial Culture", "Glycerol Stock", "Plate", "DNA Sample", "Plasmid", "Ligation", "Genomic", "Digest");
        Label strain1 = new Label("Strain:");
        TextField strainTextField1 = new TextField();
        Label volume1 = new Label("Volume(mL):");
        TextField volumeTextField1 = new TextField();
        volumeTextField1.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = volumeTextField1.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        volumeTextField1.setText(volumeTextField1.getText().substring(0,volumeTextField1.getText().length()-1));
                    }
                }
            }

        });
        Label composition1 = new Label("Plate Composition:");
        TextField compositionTextField1 = new TextField();
        Label concentration1 = new Label("Concentration(ng/uL):");
        TextField concentrationTextField1 = new TextField();
        concentrationTextField1.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = concentrationTextField1.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        concentrationTextField1.setText(concentrationTextField1.getText().substring(0,concentrationTextField1.getText().length()-1));
                    }
                }
            }

        });
        Label plasmidName1 = new Label("Plasmid Name:");
        TextField plasmidTextField1 = new TextField();
        Label plasmidAntibiotic1 = new Label("Plasmid Antibiotic:");
        TextField antibioticTextField1 = new TextField();
        Label rez11 = new Label("Restriction Enzyme 1:");
        TextField rez1TextField1 = new TextField();
        Label rez21 = new Label("Restriction Enzyme 2:");
        TextField rez2TextField1 = new TextField();
        Label genomic1 = new Label("Origin:");
        TextField genomicTextField1 = new TextField();
        Label ligation11 = new Label("Ligation Part 1:");
        TextField ligation1TextField1 = new TextField();
        Label ligation21 = new Label("Ligation Part 2:");
        TextField ligation2TextField1 = new TextField();

        Button enterEditSample = new Button("Enter");


        Label sampleIDLabel = new Label("Sample ID: ");
        TextField sampleIDTextfield = new TextField();
        sampleIDTextfield.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = sampleIDTextfield.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        sampleIDTextfield.setText(sampleIDTextfield.getText().substring(0,sampleIDTextfield.getText().length()-1));
                    }
                }
            }

        });


        //editSamplePane (gridpane)
        editSamplePane = new GridPane();
        editSamplePane.setAlignment(Pos.CENTER);
        editSamplePane.setHgap(10);
        editSamplePane.setVgap(10);
        editSamplePane.setPadding(new Insets(25, 25, 25, 25));
        editSamplePane.setVgap(10);

        //adding all the buttons to the entry pane
        editSamplePane.add(sampleType1, 0, 0);
        editSamplePane.add(sampleTypeBox1, 1, 0);
        editSamplePane.add(sampleIDLabel,2,0);
        editSamplePane.add(sampleIDTextfield,3,0);

        editSamplePane.add(editSampleBack,0,7);                  //check the placement of this button
        editSamplePane.add(enterEditSample,7,7);                   //check the placement of this button
        enterEditSample.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                editSample(); //todo - tamar- add the right call now
            }
        });

        sampleTypeBox1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editSamplePane.getChildren().remove(6, editSamplePane.getChildren().size());

                if(sampleTypeBox1.getValue()=="Bacterial Culture"){
                    editSamplePane.add(strain1, 0, 1);
                    editSamplePane.add(strainTextField1, 1, 1);
                }
                if(sampleTypeBox1.getValue()=="Glycerol Stock"){
                    editSamplePane.add(strain1, 0, 1);
                    editSamplePane.add(strainTextField1, 1, 1);
                    editSamplePane.add(volume1, 0, 2);
                    addSamplePane.add(volumeTextField1, 1, 2);
                }
                if(sampleTypeBox1.getValue()=="Plate"){
                    editSamplePane.add(strain1, 0, 1);
                    editSamplePane.add(strainTextField1, 1, 1);
                    editSamplePane.add(composition1, 0, 2);
                    editSamplePane.add(compositionTextField1, 1, 2);
                }
                if(sampleTypeBox1.getValue()=="DNA Sample"){
                    editSamplePane.add(concentration1, 0, 1);
                    editSamplePane.add(concentrationTextField1, 1, 1);
                }
                if(sampleTypeBox1.getValue()=="Plasmid"){
                    editSamplePane.add(concentration1, 0, 1);
                    editSamplePane.add(concentrationTextField1, 1, 1);
                    editSamplePane.add(plasmidName1, 0, 2);
                    editSamplePane.add(plasmidTextField1, 1, 2);
                    editSamplePane.add(plasmidAntibiotic1, 0, 3);
                    editSamplePane.add(antibioticTextField1, 1, 3);
                }
                if(sampleTypeBox1.getValue()=="Ligation"){
                    editSamplePane.add(concentration1, 0, 1);
                    editSamplePane.add(concentrationTextField1, 1, 1);
                    editSamplePane.add(ligation11, 0, 2);
                    editSamplePane.add(ligation1TextField1, 1, 2);
                    editSamplePane.add(ligation21, 0, 3);
                    editSamplePane.add(ligation2TextField1, 1, 3);
                }
                if(sampleTypeBox1.getValue()=="Genomic"){
                    editSamplePane.add(concentration1, 0, 1);
                    editSamplePane.add(concentrationTextField1, 1, 1);
                    editSamplePane.add(genomic1, 0, 2);
                    editSamplePane.add(genomicTextField1, 1, 2);
                }
                if(sampleTypeBox1.getValue()=="Digest"){
                    editSamplePane.add(concentration1, 0, 1);
                    editSamplePane.add(concentrationTextField1, 1, 1);
                    editSamplePane.add(rez11, 0, 2);
                    editSamplePane.add(rez1TextField1, 1, 2);
                    editSamplePane.add(rez21, 0, 3);
                    editSamplePane.add(rez2TextField1, 1, 3);
                }

            }


        });

        editSampleScene = new Scene(editSamplePane, 1000, 500);

        // addSampleResearchScene///////////////////////////////////////////////////////////////////////////////////////
        //startDate, Duration, Samp_ID, Emp_ID
        DatePicker startDate = new DatePicker();
        Label duration = new Label("Duration in Days:");
        TextField durationText = new TextField();
        durationText.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = durationText.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        durationText.setText(durationText.getText().substring(0,durationText.getText().length()-1));
                    }
                }
            }

        });
        Label sampleIDLabel1 = new Label("Sample ID:");
        TextField SampleIDTextfield1 = new TextField();
        SampleIDTextfield1.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = SampleIDTextfield1.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        SampleIDTextfield1.setText(SampleIDTextfield1.getText().substring(0,SampleIDTextfield1.getText().length()-1));
                    }
                }
            }

        });
        Button enteraddSampleResearch = new Button("Enter");

        //addSamplePane
        addSampleResearchPane = new GridPane();
        addSampleResearchPane.setAlignment(Pos.CENTER);
        addSampleResearchPane.setHgap(10);
        addSampleResearchPane.setVgap(10);
        addSampleResearchPane.setPadding(new Insets(25, 25, 25, 25));
        addSampleResearchPane.setVgap(10);

        //adding all the buttons to the entry pane
        addSampleResearchPane.add(startDate, 0, 0);
        addSampleResearchPane.add(duration, 0, 1);
        addSampleResearchPane.add(durationText,1,1);
        addSampleResearchPane.add(sampleIDLabel,0,2);
        addSampleResearchPane.add(sampleIDTextfield,1,2);

        addSampleResearchPane.add(addSampleResearchBack,0,7);                  //check the placement of this button
        addSampleResearchPane.add(enteraddSampleResearch,7,7);                   //check the placement of this button
        enteraddSampleResearch.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                addSampleResearch(); //todo - tamar- add the right call now
            }
        });
        //addSampleResearchScene
        addSampleResearchScene = new Scene(addSampleResearchPane, 1000, 500);


        //samplesCreatedByScene////////////////////////////////////////////////////////////////////////////////////////
        //todo -tamar
        //addBoxScene//////////////////////////////////////////////////////////////////////////////////////////////////
        //(C_Name:varchar, C_Occupancy:integer, C_ID:integer, Fr_ID:integer, F_Occupancy:integer, C_Date:date)
        //Buttons
        Button enterAddBox = new Button("Enter");
        enterAddBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                addBox(); //todo - tamar- add the right call now
            }
        });
        Label boxName = new Label("Box Name:");
        TextField boxNametxt = new TextField();
        //addBoxPain
        addBoxPane = new GridPane();
        addBoxPane.setAlignment(Pos.CENTER);
        addBoxPane.setHgap(10);
        addBoxPane.setVgap(10);
        addBoxPane.setPadding(new Insets(25, 25, 25, 25));
        addBoxPane.setVgap(10);
        //adding the buttons
        addBoxPane.add(boxName,0,0);
        addBoxPane.add(boxNametxt,1,0);
        addBoxPane.add(addBoxBack,0,7);                  //check the placement of this button
        addBoxPane.add(enterAddBox,7,7);
        //addBoxPane.add();
        addBoxScene = new Scene(addBoxPane, 1000, 500);

        //removeBoxScene////////////////////////////////////////////////////////////////////////////////////////////////
        Button enterRemoveBox = new Button("Enter");
        enterRemoveBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                removeBox(); //todo - tamar- add the right call now
            }
        });
        Label boxID = new Label("Box ID:");
        TextField boxIDtxt = new TextField();
        boxIDtxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = boxIDtxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        boxIDtxt.setText(boxIDtxt.getText().substring(0,boxIDtxt.getText().length()-1));
                    }
                }
            }

        });
        Label fridgeID = new Label("Fridge ID:");
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
        //addBoxPain
        removeBoxPane = new GridPane();
        removeBoxPane.setAlignment(Pos.CENTER);
        removeBoxPane.setHgap(10);
        removeBoxPane.setVgap(10);
        removeBoxPane.setPadding(new Insets(25, 25, 25, 25));
        removeBoxPane.setVgap(10);
        //adding the buttons
        removeBoxPane.add(boxID,0,0);
        removeBoxPane.add(boxIDtxt,1,0);
        removeBoxPane.add(fridgeID,0,1);
        removeBoxPane.add(fridgeIDtxt,1,1);
        removeBoxPane.add(removeBoxBack,0,7);                  //check the placement of this button
        removeBoxPane.add(enterRemoveBox,7,7);

        removeBoxScene = new Scene(removeBoxPane, 1000, 500);

        primaryStage.setScene(entryScene);

        primaryStage.show();

    }


    public void ButtonClicked(ActionEvent e) {
        if((e.getSource()==addSampleBack)||(e.getSource()==editSampleBack)||(e.getSource()==addSampleResearchBack)||(e.getSource()==samplesCreatedByBack)
                ||(e.getSource()==addBoxBack)|| (e.getSource()==removeBoxBack))
            theStage.setScene(entryScene);
        if (e.getSource()==addSample)
            theStage.setScene(addSampleScene);
        if (e.getSource()==editSample)
            theStage.setScene(editSampleScene);
        if (e.getSource()==addSampleResearch)
            theStage.setScene(addSampleResearchScene);
        if (e.getSource()==addBox)
            theStage.setScene(addBoxScene);
        if (e.getSource()==removeBox)
            theStage.setScene(removeBoxScene);
    }

    //TODO(Ksenia): 6 methods below
    public int addSample(){
        return 0;
    }

    public int editSample(){
        return 0;
    }

    public int addSampleResearch(){
        return 0;
    }

    public int samplesCreatedBy(String name){
        return 0;
    }

    public int addBox() {
        return 0;
    }

    public int removeBox() {
        return 0;
    }

    @Override
    //Query
    //TODO (Darius): This could be very similar to your implementation in LabManager. You could add some small
    //Changes if you're feeling up for it.
    public Map<String, String[]> generateWorkList() {
        return null;
    }

    @Override
    //Query
    //TODO (Darius): This could be very similar to your implementation in LabManager. You could add some small
    //Changes if you're feeling up for it.
    public Map<String, String[]> generateSampleList() {
        return null;
    }
}
