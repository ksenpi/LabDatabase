package mainViews;/**
 * Created by Tamar on 2016-11-10.
 */

import databaseConnection.OurConnection;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Researcher extends Application implements User {
    Button addSample, editSample, addSampleResearch, addBox, removeBox, addSampleBack, editSampleBack, addSampleResearchBack,  addBoxBack, removeBoxBack;
    Stage theStage;
    Scene entryScene, addSampleScene, editSampleScene, addSampleResearchScene, addBoxScene, removeBoxScene;
    GridPane entryPane, addSamplePane, editSamplePane, addSampleResearchPane,  addBoxPane, removeBoxPane;
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
        addBoxBack = new Button("Go to Main Page");
        addBoxBack.setOnAction(e->ButtonClicked(e));
        removeBoxBack = new Button("Go to Main Page");
        removeBoxBack.setOnAction(e->ButtonClicked(e));

        //entryScene////////////////////////////////////////////////////////////////////////////////////////////////////
        addSample = new Button("Add Sample");
        editSample = new Button("Edit Sample");
        addSampleResearch = new Button("Research Sample");
        addBox = new Button("Add Box");
        removeBox = new Button("Remove Box");
        //making button actions
        addSample.setOnAction(e-> ButtonClicked(e));
        editSample.setOnAction(e-> ButtonClicked(e));
        addSampleResearch.setOnAction(e-> ButtonClicked(e));
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
        entryPane.add(editSample, 1, 0);
        entryPane.add(addSampleResearch, 2, 0);
        entryPane.add(addBox, 0, 1);
        entryPane.add(removeBox, 1, 1);
        entryScene = new Scene(entryPane, 1000, 500);


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
        Text addSampleResponse = new Text();
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
        addSamplePane.add(addSampleResponse,7,8);

        enterAddSample.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int type = 8;
                if(sampleTypeBox.getValue() == "Bacterial Culture")
                    type = 0;
                if(sampleTypeBox.getValue() == "Glycerol Stock")
                    type = 1;
                if(sampleTypeBox.getValue() == "Plate")
                    type = 2;
                if(sampleTypeBox.getValue() == "DNA Sample")
                    type = 3;
                if(sampleTypeBox.getValue() == "Plasmid")
                    type = 4;
                if(sampleTypeBox.getValue() == "Digest")
                    type = 5;
                if(sampleTypeBox.getValue() == "Genomic")
                    type = 6;
                if(sampleTypeBox.getValue() == "Ligation")
                    type = 7;

                addSampleResponse.setText(addSample(type, strainTextField.getText(),Integer.parseInt(volumeTextField.getText()),compositionTextField.getText()
                , Integer.parseInt(concentrationTextField.getText()), plasmidTextField.getText(),antibioticTextField.getText(),
                        rez1TextField.getText(), rez2TextField.getText(), genomicTextField.getText(), ligation1TextField.getText(), ligation2TextField.getText()));
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
        //make buttons
        Label sampleType1 = new Label("Sample Type");
        ChoiceBox sampleTypeBox1 = new ChoiceBox();
        sampleTypeBox1.setItems(FXCollections.observableArrayList("Bacterial Culture", "Glycerol Stock", "Plate", "DNA Sample", "Plasmid", "Ligation", "Genomic", "Digest"));
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
        Button enterEditSample = new Button("Enter");
        enterEditSample.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //editSample(); //todo - tamar- add the right call now
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        //entryPane (gridpane)
        editSamplePane = new GridPane();
        editSamplePane.setAlignment(Pos.CENTER);
        editSamplePane.setHgap(10);
        editSamplePane.setVgap(10);
        editSamplePane.setPadding(new Insets(25, 25, 25, 25));
        editSamplePane.setVgap(10);
        //adding all the buttons to the entry pane
        editSamplePane.add(sampleType1,0, 0);
        editSamplePane.add(sampleTypeBox1, 1, 0);
        editSamplePane.add(sampleIDLabel, 2, 0);
        editSamplePane.add(sampleIDTextfield, 3, 0);
        editSamplePane.add(editSampleBack,0,7);                  //check the placement of this button
        editSamplePane.add(enterEditSample,7,7);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
                    editSamplePane.add(volumeTextField1, 1, 2);
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
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        editSampleScene = new Scene(editSamplePane, 1000, 500);

        // addSampleResearchScene///////////////////////////////////////////////////////////////////////////////////////

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
        Text addSampleResearchResponse = new Text();
        Button enteraddSampleResearch = new Button("Enter");

        //addSamplePane
        addSampleResearchPane = new GridPane();
        addSampleResearchPane.setAlignment(Pos.CENTER);
        addSampleResearchPane.setHgap(10);
        addSampleResearchPane.setVgap(10);
        addSampleResearchPane.setPadding(new Insets(25, 25, 25, 25));
        addSampleResearchPane.setVgap(10);

        //adding all the buttons to the entry pane
        addSampleResearchPane.add(duration, 0, 1);
        addSampleResearchPane.add(durationText,1,1);
        addSampleResearchPane.add(sampleIDLabel1,0,2);
        addSampleResearchPane.add(SampleIDTextfield1,1,2);

        addSampleResearchPane.add(addSampleResearchBack,0,7);
        addSampleResearchPane.add(enteraddSampleResearch,7,7);
        addSampleResearchPane.add(addSampleResearchResponse,7,8);
        enteraddSampleResearch.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                addSampleResearchResponse.setText(addSampleResearch(researcherID,Integer.parseInt(durationText.getText()),Integer.parseInt(SampleIDTextfield1.getText())));
            }
        });
        //addSampleResearchScene
        addSampleResearchScene = new Scene(addSampleResearchPane, 1000, 500);

        //addBoxScene//////////////////////////////////////////////////////////////////////////////////////////////////
        //Buttons
        Label containerNameLabel = new Label("Container Name:");
        TextField containerNametxt = new TextField();
        Label fridgeIDLabel = new Label("Sample ID:");
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

        //removeBoxScene////////////////////////////////////////////////////////////////////////////////////////////////
        Label containerIDLabel2 = new Label("Sample ID:");
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

        primaryStage.setScene(entryScene);

        primaryStage.show();

    }


    public void ButtonClicked(ActionEvent e) {
        if((e.getSource()==addSampleBack)||(e.getSource()==editSampleBack)||(e.getSource()==addSampleResearchBack)
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

    //type is 0-7
    public String addSample(int type, String strain, int volume, String composition, int concentration,
                            String name, String antibiotic, String res_enz_1, String res_enz_2, String origin,
                            String part1, String part2){

        PreparedStatement ps1;
        PreparedStatement ps2;
        PreparedStatement ps3;
        ResultSet rs;
        Statement stmt;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {

                con = connectionToDatabase.getConnection();

                stmt = con.createStatement();
                rs = stmt.executeQuery("select max(samp_id) as max from sample");
                int sampleID = 0;
                if(rs.next()){
                    sampleID = rs.getInt("max") + 1;
                }

                java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());


                ps1 = con.prepareStatement("INSERT INTO sample VALUES (?,?)");
                ps1.setInt(1, sampleID);
                ps1.setDate(2, ourJavaDateObject);

                ps1.executeUpdate();
                ps1.close();

                if(type >= 0 && type <=2){

                    ps2 = con.prepareStatement("INSERT INTO bacterial_culture VALUES (?,?)");
                    ps2.setInt(1, sampleID);
                    ps2.setString(2, strain);

                    ps2.executeUpdate();
                    //con.commit();
                    ps2.close();

                }
                else if(type >=3 && type <=7){

                    ps2 = con.prepareStatement("INSERT INTO dna_sample VALUES (?,?)");
                    ps2.setInt(1, sampleID);
                    ps2.setInt(2, concentration);

                    ps2.executeUpdate();
                    //con.commit();
                    ps2.close();

                }
                else{
                    return "Error_Invalid_Type";
                }

                switch(type){
                    case 0:                  //bacterial_culture
                        break;
                    case 1:                  //glycerol_stock
                        ps3 = con.prepareStatement("INSERT INTO glycerol_stock VALUES (?,?)");
                        ps3.setInt(1, sampleID);
                        ps3.setInt(2, volume);

                        ps3.executeUpdate();
                        con.commit();
                        ps3.close();
                        break;
                    case 2:                  //plate
                        ps3 = con.prepareStatement("INSERT INTO plate VALUES (?,?)");
                        ps3.setInt(1, sampleID);
                        ps3.setString(2, composition);

                        ps3.executeUpdate();
                        con.commit();
                        ps3.close();
                        break;
                    case 3:                  //dna_sample
                        break;
                    case 4:                  //plasmid
                        ps3 = con.prepareStatement("INSERT INTO plasmid VALUES (?,?,?)");
                        ps3.setInt(1, sampleID);
                        ps3.setString(2, name);
                        ps3.setString(3, antibiotic);

                        ps3.executeUpdate();
                        con.commit();
                        ps3.close();
                        break;
                    case 5:                  //digest
                        ps3 = con.prepareStatement("INSERT INTO digest VALUES (?,?,?)");
                        ps3.setInt(1, sampleID);
                        ps3.setString(2, res_enz_1);
                        ps3.setString(3, res_enz_2);

                        ps3.executeUpdate();
                        con.commit();
                        ps3.close();
                        break;
                    case 6:                  //genomic
                        ps3 = con.prepareStatement("INSERT INTO genomic VALUES (?,?)");
                        ps3.setInt(1, sampleID);
                        ps3.setString(2, origin);

                        ps3.executeUpdate();
                        con.commit();
                        ps3.close();
                        break;
                    case 7:                  //ligation
                        ps3 = con.prepareStatement("INSERT INTO ligation VALUES (?,?,?)");
                        ps3.setInt(1, sampleID);
                        ps3.setString(2, part1);
                        ps3.setString(3, part2);

                        ps3.executeUpdate();
                        con.commit();
                        ps3.close();
                        break;

                }
                return "OK";


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

        return "Error_Adding";
    }

    public String editSample(int sampleID, String strain, int volume, String composition, int concentration,
                             String name, String antibiotic, String res_enz_1, String res_enz_2, String origin,
                             String part1, String part2){

        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                con = connectionToDatabase.getConnection();

                final String queryCheck = "SELECT * from sample WHERE samp_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, sampleID);
                final ResultSet resultSet = psCheck.executeQuery();
                if(resultSet.next()) {
                    if(strain!=null){
                        PreparedStatement ps = con.prepareStatement("UPDATE bacterial_culture " +
                                "SET strain = ? WHERE sample_id = ?");
                        ps.setString(1, strain);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();
                    }
                    if(volume!=0){
                        PreparedStatement ps = con.prepareStatement("UPDATE glycerol_stock " +
                                "SET volume = ? WHERE sample_id = ?");
                        ps.setInt(1, volume);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }
                    if(composition!=null){
                        PreparedStatement ps = con.prepareStatement("UPDATE plate " +
                                "SET composition = ? WHERE sample_id = ?");
                        ps.setString(1, composition);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }
                    if(concentration!=0){
                        PreparedStatement ps = con.prepareStatement("UPDATE dna_sample " +
                                "SET concentration = ? WHERE sample_id = ?");
                        ps.setInt(1, concentration);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }
                    if(name!=null){
                        PreparedStatement ps = con.prepareStatement("UPDATE plasmid " +
                                "SET name = ? WHERE sample_id = ?");
                        ps.setString(1, name);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }
                    if(antibiotic!=null){

                        PreparedStatement ps = con.prepareStatement("UPDATE plasmid " +
                                "SET antibiotic = ? WHERE sample_id = ?");
                        ps.setString(1, antibiotic);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }
                    if(res_enz_1!=null){

                        PreparedStatement ps = con.prepareStatement("UPDATE digest " +
                                "SET res_enz_1 = ? WHERE sample_id = ?");
                        ps.setString(1, res_enz_1);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }
                    if(res_enz_2!=null){
                        PreparedStatement ps = con.prepareStatement("UPDATE digest " +
                                "SET res_enz_2 = ? WHERE sample_id = ?");
                        ps.setString(1, res_enz_2);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }
                    if(origin!=null){
                        PreparedStatement ps = con.prepareStatement("UPDATE genomic " +
                                "SET origin = ? WHERE sample_id = ?");
                        ps.setString(1, origin);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }
                    if(part1!=null){
                        PreparedStatement ps = con.prepareStatement("UPDATE ligation " +
                                "SET part1 = ? WHERE sample_id = ?");
                        ps.setString(1, part1);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }
                    if(part2!=null){
                        PreparedStatement ps = con.prepareStatement("UPDATE ligation " +
                                "SET part2 = ? WHERE sample_id = ?");
                        ps.setString(1, part2);
                        ps.setInt(2, sampleID);
                        ps.executeUpdate();

                        con.commit();
                        ps.close();

                    }

                    return "OK";

                }
                else{
                    return "Error_Sample_NOT_Exist";
                }

            } catch (SQLException ex) {

                System.out.println("Message: " + ex.getMessage());
                try {
                    // undo the update
                    con.rollback();
                } catch (SQLException ex2) {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);

                }
            }
        }

        return "Error_Updating";
    }
    //TODO (Ksenia)
    public String addSampleResearch(int employeeID, int duration, int sampleID){
        PreparedStatement ps1;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                con = connectionToDatabase.getConnection();

                final String queryCheck = "SELECT * from sample WHERE samp_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, sampleID);
                final ResultSet resultSet = psCheck.executeQuery();
                if(resultSet.next()) {
                    if(duration <= 60 && duration >=0){
                        java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());

                        ps1 = con.prepareStatement("INSERT INTO researches VALUES (?,?,?,?)");
                        ps1.setInt(1, employeeID);
                        ps1.setDate(2, ourJavaDateObject);
                        ps1.setInt(3, duration);
                        ps1.setInt(4, sampleID);

                        ps1.executeUpdate();
                        con.commit();

                        ps1.close();
                        return "OK";

                    }
                    else{
                        return "Error_Invalid_Duration";
                    }
                }
                else{
                    return "Error_Sample_NOT_Exist";
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

        return "Error_Adding";
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
                            return "OK";

                        }
                        else{
                            return "Error_Fridge_At_Capacity";
                        }
                    }
                }
                else{
                    return "Error_Fridge_NOT_Exist";
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

        return "Error_Adding";
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
                        return "OK";
                    }
                    else{
                        return "Error_Occupancy_NOT_0";
                    }

                }
                else{
                    return "Error_Container_NOT_Exist";
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

        return "Error_Removing";
    }

    // returns the researchers who have researched every sample;
    // Division Query
    public List<String> findResearchersResearchingAll() {
        Statement stmt;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt = con.createStatement();
                String researcherName;
                List<String> researcherNames = new ArrayList<String>();

                results = stmt.executeQuery("select r.name from researcher r where NOT EXISTS " +
                        "(select * from sample s where NOT EXISTS (select r2.samp_id from " +
                        "researches r2 where r.emp_id=r2.emp_id and s.samp_id=r2.samp_id))");

                while (results.next()) {
                    researcherName = results.getString("name");
                    researcherNames.add(researcherName);
                }
                stmt.close();

                return researcherNames;

            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        return null;
    }

    //TODO (Ksenia) - incomplete

    public Map<String, String[]> findOngoingResearch(boolean employeeID, boolean startDate, boolean duration,
                                                     boolean sampleID, String attribute, String comparator,
                                                     int value){
        Statement stmt1;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            //if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt1 = con.createStatement();
                Map<String, String[]> researchList = new HashMap<String, String[]>();

                if(attribute != null && comparator != null && value != 0){
                    results = stmt1.executeQuery("select ? from researches where ?");
                }

                results = stmt1.executeQuery("select ? from researches");

                stmt1.close();
                return researchList;
            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        return null;

    }

    @Override
    // Currently the same as LabManager
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
                        String[] workerAttributes = {"Name: " + name, "Type: Lab Manager"};
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
                            String[] workerAttributes = {"Name: " + name, "Type: Researcher"};
                            workerList.put(EmployeeID, workerAttributes);
                        } else {
                            String[] workerAttributes = {"Name: " + name, "Type: Lab Manager and Researcher"};
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
    // Currently the same as LabManager
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
}
