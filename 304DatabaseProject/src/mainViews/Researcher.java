package mainViews;/**
 * Created by Tamar on 2016-11-10.
 */

import a.a.S;
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
    Button addSample, editSample, addSampleResearch, addBox, removeBox, addSampleBack, editSampleBack, addSampleResearchBack,  addBoxBack, removeBoxBack
            ,findResearchersResearchingAll, findOngoingResearch, generateWorkList, generateSampleList
            ,findResearchersResearchingAllBack, findOngoingResearchBack, generateWorkListBack, generateSampleListBack;
    Stage theStage;
    Scene entryScene, addSampleScene, editSampleScene, addSampleResearchScene, addBoxScene, removeBoxScene
            ,findResearchersResearchingAllScene, findOngoingResearchScene, generateWorkListScene, generateSampleListScene;
    GridPane entryPane, addSamplePane, editSamplePane, addSampleResearchPane,  addBoxPane, removeBoxPane
            ,findResearchersResearchingAllPane, findOngoingResearchPane, generateWorkListPane, generateSampleListPane;
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
        findResearchersResearchingAllBack = new Button("Go to Main Page");
        findResearchersResearchingAllBack.setOnAction(e->ButtonClicked(e));
        findOngoingResearchBack = new Button("Go to Main Page");
        findOngoingResearchBack.setOnAction(e->ButtonClicked(e));
        generateWorkListBack = new Button("Go to Main Page");
        generateWorkListBack.setOnAction(e->ButtonClicked(e));
        generateSampleListBack = new Button("Go to Main Page");
        generateSampleListBack.setOnAction(e->ButtonClicked(e));
        //entryScene////////////////////////////////////////////////////////////////////////////////////////////////////
        addSample = new Button("Add Sample");
        editSample = new Button("Edit Sample");
        addSampleResearch = new Button("Research Sample");
        addBox = new Button("Add Box");
        removeBox = new Button("Remove Box");

        findResearchersResearchingAll = new Button("Find Researchers Researching All Samples");
        findOngoingResearch = new Button("Find Ongoing Research");
        generateWorkList = new Button("See Worklist");
        generateSampleList = new Button("See Samplelist");

        //making button actions
        addSample.setOnAction(e-> ButtonClicked(e));
        editSample.setOnAction(e-> ButtonClicked(e));
        addSampleResearch.setOnAction(e-> ButtonClicked(e));
        addBox.setOnAction(e-> ButtonClicked(e));
        removeBox.setOnAction(e-> ButtonClicked(e));
        findResearchersResearchingAll.setOnAction(e-> ButtonClicked(e));
        findOngoingResearch.setOnAction(e-> ButtonClicked(e));
        generateWorkList.setOnAction(e-> ButtonClicked(e));
        generateSampleList.setOnAction(e-> ButtonClicked(e));

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
        entryPane.add(findResearchersResearchingAll, 2, 1);
        entryPane.add(findOngoingResearch, 0, 2);
        entryPane.add(generateWorkList, 1, 2);
        entryPane.add(generateSampleList, 2, 2);

        entryScene = new Scene(entryPane, 1000, 500);


        //addSampleScene////////////////////////////////////////////////////////////////////////////////////////////////
        Label addSampleType = new Label("Sample Type: ");
        ChoiceBox addSampleTypeChoices = new ChoiceBox();
        addSampleTypeChoices.setItems(FXCollections.observableArrayList("Bacterial Culture", "Glycerol Stock", "Plate", "DNA Sample", "Plasmid",
                "Digest", "Genomic", "Ligation"));
        Label addSampleStrain = new Label("Strain: "); //bacterial culture, glycerol stock, plate
        TextField addSampleStrainTxt = new TextField();
        Label addSampleVolume = new Label("Volume in mL: "); //glycerol stock
        TextField addSampleVolumeTxt = new TextField(); //todo
        addSampleVolumeTxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = addSampleVolumeTxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        addSampleVolumeTxt.setText(addSampleVolumeTxt.getText().substring(0,addSampleVolumeTxt.getText().length()-1));
                    }
                }
            }

        });
        Label addSampleComposition = new Label("Composition: "); //plate
        TextField addSampleCompositionTxt = new TextField();
        Label addSampleConcentration = new Label("Concentration in ng/uL: "); // DNA sample, plasmid, digest, genomic, ligation
        TextField addSampleConcentrationTxt = new TextField();
        addSampleConcentrationTxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = addSampleConcentrationTxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        addSampleConcentrationTxt.setText(addSampleConcentrationTxt.getText().substring(0,addSampleConcentrationTxt.getText().length()-1));
                    }
                }
            }

        });
        Label addSamplePlasmidName = new Label("Plasmid Name: "); // plasmid
        TextField addSamplePlasmidNameTxt = new TextField();
        Label addSamplePlasmidAntibiotic = new Label("Plasmid Antibiotic Resistance: "); //plasmid
        TextField addSamplePlasmidAntibioticTxt = new TextField();
        Label addSampleRez1 = new Label("Restriction Enzyme 1: "); //digest
        TextField addSampleRez1Txt = new TextField();
        Label addSampleRez2 = new Label("Restriction Enzyme 2: "); //digest
        TextField addSampleRez2Txt = new TextField();
        Label addSampleOrigin = new Label("Genomic Origin: "); //genomic
        TextField addSampleOriginTxt = new TextField();
        Label addSamplePart1 = new Label("Ligation Part 1: "); //ligation
        TextField addSamplePart1Txt = new TextField();
        Label addSamplePart2 = new Label("Ligation Part 1   : "); //ligation
        TextField addSamplePart2Txt = new TextField();

        Text addSampleResponse = new Text();
        Button enteraddSample = new Button("Enter");

        addSamplePane = new GridPane();
        addSamplePane.setAlignment(Pos.CENTER);
        addSamplePane.setHgap(10);
        addSamplePane.setVgap(10);
        addSamplePane.setPadding(new Insets(25, 25, 25, 25));
        addSamplePane.setVgap(10);


        addSamplePane.add(addSampleType,0,0);
        addSamplePane.add(addSampleTypeChoices,1,0);
        addSamplePane.add(addSampleBack,0,7);
        addSamplePane.add(enteraddSample,7,7);
        addSamplePane.add(addSampleResponse,7,8);

        addSampleTypeChoices.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                addSamplePane.getChildren().remove(5, addSamplePane.getChildren().size());

                if(addSampleTypeChoices.getValue().equals("Bacterial Culture")){
                    addSamplePane.add(addSampleStrain,0,1);
                    addSamplePane.add(addSampleStrainTxt,1,1);
                }
                if(addSampleTypeChoices.getValue().equals("Glycerol Stock")){
                    addSamplePane.add(addSampleStrain,0,1);
                    addSamplePane.add(addSampleStrainTxt,1,1);
                    addSamplePane.add(addSampleVolume,0,2);
                    addSamplePane.add(addSampleVolumeTxt,1,2);
                }
                if(addSampleTypeChoices.getValue().equals("Plate")){
                    addSamplePane.add(addSampleStrain,0,1);
                    addSamplePane.add(addSampleStrainTxt,1,1);
                    addSamplePane.add(addSampleComposition,0,2);
                    addSamplePane.add(addSampleCompositionTxt,1,2);
                }
                if(addSampleTypeChoices.getValue().equals("DNA Sample")){
                    addSamplePane.add(addSampleConcentration,0,1);
                    addSamplePane.add(addSampleConcentrationTxt,1,1);
                }
                if(addSampleTypeChoices.getValue().equals("Plasmid")){
                    addSamplePane.add(addSampleConcentration,0,1);
                    addSamplePane.add(addSampleConcentrationTxt,1,1);
                    addSamplePane.add(addSamplePlasmidName,0,2);
                    addSamplePane.add(addSamplePlasmidNameTxt,1,2);
                    addSamplePane.add(addSamplePlasmidAntibiotic,0,3);
                    addSamplePane.add(addSamplePlasmidAntibioticTxt,1,3);
                }
                if(addSampleTypeChoices.getValue().equals("Digest")){
                    addSamplePane.add(addSampleConcentration,0,1);
                    addSamplePane.add(addSampleConcentrationTxt,1,1);
                    addSamplePane.add(addSampleRez1,0,2);
                    addSamplePane.add(addSampleRez1Txt,1,2);
                    addSamplePane.add(addSampleRez2,0,3);
                    addSamplePane.add(addSampleRez2Txt,1,3);
                }
                if(addSampleTypeChoices.getValue().equals("Genomic")){
                    addSamplePane.add(addSampleConcentration,0,1);
                    addSamplePane.add(addSampleConcentrationTxt,1,1);
                    addSamplePane.add(addSampleOrigin,0,2);
                    addSamplePane.add(addSampleOriginTxt,1,2);
                }
                if(addSampleTypeChoices.getValue().equals("Ligation")){
                    addSamplePane.add(addSampleConcentration,0,1);
                    addSamplePane.add(addSampleConcentrationTxt,1,1);
                    addSamplePane.add(addSamplePart1,0,2);
                    addSamplePane.add(addSamplePart1Txt,1,2);
                    addSamplePane.add(addSamplePart2,0,3);
                    addSamplePane.add(addSamplePart2Txt,1,3);
                }
        }});


        enteraddSample.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int typepass = 8;
                String strainpass = null;
                int volumepass = 0;
                String compositionpass = null;
                int concentrationpass = 0;
                String plasmidNamepass = null;
                String plasmidAntibioticpass = null;
                String rez1pass = null;
                String rez2pass = null;
                String genomeOriginpass = null;
                String lig1pass = null;
                String lig2pass = null;
                if(addSampleTypeChoices.getValue() == "Bacterial Culture") {
                    typepass = 0;
                    strainpass = addSampleStrainTxt.getText();
                }
                if(addSampleTypeChoices.getValue() == "Glycerol Stock") {
                    typepass = 1;
                    strainpass = addSampleStrainTxt.getText();
                    volumepass = Integer.parseInt(addSampleVolumeTxt.getText());
                }
                if(addSampleTypeChoices.getValue() == "Plate") {
                    typepass = 2;
                    strainpass = addSampleStrainTxt.getText();
                    compositionpass = addSampleCompositionTxt.getText();
                }
                if(addSampleTypeChoices.getValue() == "DNA Sample"){
                    typepass = 3;
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                }
                if(addSampleTypeChoices.getValue() == "Plasmid") {
                    typepass = 4;
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                    plasmidNamepass = addSamplePlasmidNameTxt.getText();
                    plasmidAntibioticpass = addSamplePlasmidAntibioticTxt.getText();
                }
                if(addSampleTypeChoices.getValue() == "Digest") {
                    typepass = 5;
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                    rez1pass = addSampleRez1Txt.getText();
                    rez2pass = addSampleRez2Txt.getText();
                }
                if(addSampleTypeChoices.getValue() == "Genomic") {
                    typepass = 6;
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                    genomeOriginpass = addSampleOriginTxt.getText();
                }
                if(addSampleTypeChoices.getValue() == "Ligation") {
                    typepass = 7;
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                    lig1pass = addSamplePart1Txt.getText();
                    lig2pass = addSamplePart2Txt.getText();
                }

                addSampleResponse.setText(addSample(typepass, strainpass, volumepass ,compositionpass,concentrationpass, plasmidNamepass,
                        plasmidAntibioticpass, rez1pass, rez2pass, genomeOriginpass, lig1pass,lig2pass));

            }
        });

        addSampleScene = new Scene(addSamplePane, 1000, 500);

        //editSampleScene//////////////////////////////////////////////////////////////////////////////////////////
        Label editSampleType = new Label("Sample Type: ");
        ChoiceBox editSampleTypeChoices = new ChoiceBox();
        editSampleTypeChoices.setItems(FXCollections.observableArrayList("Bacterial Culture", "Glycerol Stock", "Plate", "DNA Sample", "Plasmid",
                "Digest", "Genomic", "Ligation"));
        Label editSampleStrain = new Label("Strain: "); //bacterial culture, glycerol stock, plate
        TextField editSampleStrainTxt = new TextField();
        Label editSampleVolume = new Label("Volume in mL: "); //glycerol stock
        TextField editSampleVolumeTxt = new TextField(); //todo
        editSampleVolumeTxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = editSampleVolumeTxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        editSampleVolumeTxt.setText(editSampleVolumeTxt.getText().substring(0,editSampleVolumeTxt.getText().length()-1));
                    }
                }
            }

        });
        Label editSampleComposition = new Label("Composition: "); //plate
        TextField editSampleCompositionTxt = new TextField();
        Label editSampleConcentration = new Label("Concentration in ng/uL: "); // DNA sample, plasmid, digest, genomic, ligation
        TextField editSampleConcentrationTxt = new TextField();
        editSampleConcentrationTxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = editSampleConcentrationTxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        editSampleConcentrationTxt.setText(editSampleConcentrationTxt.getText().substring(0,editSampleConcentrationTxt.getText().length()-1));
                    }
                }
            }

        });
        Label editSamplePlasmidName = new Label("Plasmid Name: "); // plasmid
        TextField editSamplePlasmidNameTxt = new TextField();
        Label editSamplePlasmidAntibiotic = new Label("Plasmid Antibiotic Resistance: "); //plasmid
        TextField editSamplePlasmidAntibioticTxt = new TextField();
        Label editSampleRez1 = new Label("Restriction Enzyme 1: "); //digest
        TextField editSampleRez1Txt = new TextField();
        Label editSampleRez2 = new Label("Restriction Enzyme 2: "); //digest
        TextField editSampleRez2Txt = new TextField();
        Label editSampleOrigin = new Label("Genomic Origin: "); //genomic
        TextField editSampleOriginTxt = new TextField();
        Label editSamplePart1 = new Label("Ligation Part 1: "); //ligation
        TextField editSamplePart1Txt = new TextField();
        Label editSamplePart2 = new Label("Ligation Part 1   : "); //ligation
        TextField editSamplePart2Txt = new TextField();
        Label editSampleID = new Label("Sample ID: "); // DNA sample, plasmid, digest, genomic, ligation
        TextField editSampleIDTxt = new TextField();
        editSampleIDTxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = editSampleIDTxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        editSampleIDTxt.setText(editSampleIDTxt.getText().substring(0,editSampleIDTxt.getText().length()-1));
                    }
                }
            }

        });

        Text editSampleResponse = new Text();
        Button entereditSample = new Button("Enter");

        editSamplePane = new GridPane();
        editSamplePane.setAlignment(Pos.CENTER);
        editSamplePane.setHgap(10);
        editSamplePane.setVgap(10);
        editSamplePane.setPadding(new Insets(25, 25, 25, 25));
        editSamplePane.setVgap(10);


        editSamplePane.add(editSampleType,0,0);
        editSamplePane.add(editSampleTypeChoices,1,0);
        editSamplePane.add(editSampleID,2,0);
        editSamplePane.add(editSampleIDTxt,3,0);
        editSamplePane.add(editSampleBack,0,7);
        editSamplePane.add(entereditSample,7,7);
        editSamplePane.add(editSampleResponse,7,8);

        editSampleTypeChoices.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                editSamplePane.getChildren().remove(7, editSamplePane.getChildren().size());

                if(editSampleTypeChoices.getValue().equals("Bacterial Culture")){
                    editSamplePane.add(editSampleStrain,0,1);
                    editSamplePane.add(editSampleStrainTxt,1,1);
                }
                if(editSampleTypeChoices.getValue().equals("Glycerol Stock")){
                    editSamplePane.add(editSampleStrain,0,1);
                    editSamplePane.add(editSampleStrainTxt,1,1);
                    editSamplePane.add(editSampleVolume,0,2);
                    editSamplePane.add(editSampleVolumeTxt,1,2);
                }
                if(editSampleTypeChoices.getValue().equals("Plate")){
                    editSamplePane.add(editSampleStrain,0,1);
                    editSamplePane.add(editSampleStrainTxt,1,1);
                    editSamplePane.add(editSampleComposition,0,2);
                    editSamplePane.add(editSampleCompositionTxt,1,2);
                }
                if(editSampleTypeChoices.getValue().equals("DNA Sample")){
                    addSamplePane.add(editSampleConcentration,0,1);
                    addSamplePane.add(editSampleConcentrationTxt,1,1);
                }
                if(editSampleTypeChoices.getValue().equals("Plasmid")){
                    editSamplePane.add(editSampleConcentration,0,1);
                    editSamplePane.add(editSampleConcentrationTxt,1,1);
                    editSamplePane.add(editSamplePlasmidName,0,2);
                    editSamplePane.add(editSamplePlasmidNameTxt,1,2);
                    editSamplePane.add(editSamplePlasmidAntibiotic,0,3);
                    editSamplePane.add(editSamplePlasmidAntibioticTxt,1,3);
                }
                if(editSampleTypeChoices.getValue().equals("Digest")){
                    editSamplePane.add(editSampleConcentration,0,1);
                    editSamplePane.add(editSampleConcentrationTxt,1,1);
                    editSamplePane.add(editSampleRez1,0,2);
                    editSamplePane.add(editSampleRez1Txt,1,2);
                    editSamplePane.add(editSampleRez2,0,3);
                    editSamplePane.add(editSampleRez2Txt,1,3);
                }
                if(editSampleTypeChoices.getValue().equals("Genomic")){
                    editSamplePane.add(editSampleConcentration,0,1);
                    editSamplePane.add(editSampleConcentrationTxt,1,1);
                    editSamplePane.add(editSampleOrigin,0,2);
                    editSamplePane.add(editSampleOriginTxt,1,2);
                }
                if(editSampleTypeChoices.getValue().equals("Ligation")){
                    editSamplePane.add(editSampleConcentration,0,1);
                    editSamplePane.add(editSampleConcentrationTxt,1,1);
                    editSamplePane.add(editSamplePart1,0,2);
                    editSamplePane.add(editSamplePart1Txt,1,2);
                    editSamplePane.add(editSamplePart2,0,3);
                    editSamplePane.add(editSamplePart2Txt,1,3);
                }
            }});


        entereditSample.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int typepass = Integer.parseInt(editSampleIDTxt.getText());
                String strainpass = null;
                int volumepass = 0;
                String compositionpass = null;
                int concentrationpass = 0;
                String plasmidNamepass = null;
                String plasmidAntibioticpass = null;
                String rez1pass = null;
                String rez2pass = null;
                String genomeOriginpass = null;
                String lig1pass = null;
                String lig2pass = null;
                if(editSampleTypeChoices.getValue() == "Bacterial Culture") {
                    strainpass = addSampleStrainTxt.getText();
                }
                if(editSampleTypeChoices.getValue() == "Glycerol Stock") {
                    strainpass = addSampleStrainTxt.getText();
                    volumepass = Integer.parseInt(addSampleVolumeTxt.getText());
                }
                if(editSampleTypeChoices.getValue() == "Plate") {
                    strainpass = addSampleStrainTxt.getText();
                    compositionpass = addSampleCompositionTxt.getText();
                }
                if(editSampleTypeChoices.getValue() == "DNA Sample"){
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                }
                if(editSampleTypeChoices.getValue() == "Plasmid") {
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                    plasmidNamepass = addSamplePlasmidNameTxt.getText();
                    plasmidAntibioticpass = addSamplePlasmidAntibioticTxt.getText();
                }
                if(editSampleTypeChoices.getValue() == "Digest") {
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                    rez1pass = addSampleRez1Txt.getText();
                    rez2pass = addSampleRez2Txt.getText();
                }
                if(editSampleTypeChoices.getValue() == "Genomic") {
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                    genomeOriginpass = addSampleOriginTxt.getText();
                }
                if(editSampleTypeChoices.getValue() == "Ligation") {
                    concentrationpass = Integer.parseInt(addSampleConcentrationTxt.getText());
                    lig1pass = addSamplePart1Txt.getText();
                    lig2pass = addSamplePart2Txt.getText();
                }

                editSampleResponse.setText(editSample(typepass, strainpass, volumepass ,compositionpass,concentrationpass, plasmidNamepass,
                        plasmidAntibioticpass, rez1pass, rez2pass, genomeOriginpass, lig1pass,lig2pass));

            }
        });

        editSampleScene = new Scene(editSamplePane, 1000, 500);

        //addSampleResearchScene///////////////////////////////////////////////////////////////////////////////////////

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

        addSampleResearchPane = new GridPane();
        addSampleResearchPane.setAlignment(Pos.CENTER);
        addSampleResearchPane.setHgap(10);
        addSampleResearchPane.setVgap(10);
        addSampleResearchPane.setPadding(new Insets(25, 25, 25, 25));
        addSampleResearchPane.setVgap(10);

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


        //findResearchersResearchingAll////////////////////////////////////////////////////////////////////////////////
        ListView<String> allResearchers = new ListView<String>();
        ObservableList<String> allResearchersItems = FXCollections.observableArrayList ();

        List<String> foundResearchers = findResearchersResearchingAll();
        for(String researcher:foundResearchers)
            allResearchersItems.add(researcher);
        //allResearchersItems.addAll(foundResearchers);

        allResearchers.setItems(allResearchersItems);

        findResearchersResearchingAllPane = new GridPane();
        findResearchersResearchingAllPane.setAlignment(Pos.CENTER);
        findResearchersResearchingAllPane.setHgap(10);
        findResearchersResearchingAllPane.setVgap(10);
        findResearchersResearchingAllPane.setPadding(new Insets(25, 25, 25, 25));
        findResearchersResearchingAllPane.setVgap(10);

        findResearchersResearchingAllPane.add(allResearchers,0,0);
        findResearchersResearchingAllPane.add(findResearchersResearchingAllBack,0,7);

        findResearchersResearchingAllScene = new Scene(findResearchersResearchingAllPane, 1000, 500);

        //findOngoingResearch /////////////////////////////////////////////////////////////////////////////////////////
        ListView<String> ongoingResearch = new ListView<String>();
        ObservableList<String> ongoingResearchItems = FXCollections.observableArrayList ();

        Label info = new Label("What information are you interested in seeing?");
        ComboBox choices = new ComboBox(FXCollections.observableArrayList("Start Date", "Duration"));
        Label advancedSearch = new Label("Advanced Search");
        ComboBox advancedSearchChoices = new ComboBox(FXCollections.observableArrayList("Duration", "Sample ID"));
        TextField advancedSearchTxt = new TextField();
        advancedSearchTxt.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = advancedSearchTxt.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        advancedSearchTxt.setText(advancedSearchTxt.getText().substring(0,advancedSearchTxt.getText().length()-1));
                    }
                }
            }

        });
        Button ongoingResearchEnter = new Button("Enter");
        ongoingResearchEnter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ongoingResearch.getItems().clear();
                ongoingResearchItems.removeAll();
                boolean fnstartDate = false;
                boolean fnduration = false;
                int fnattributeCase = 5;
                int fnvalue = 0;

                if(choices.getValue()=="Start Date")
                    fnstartDate = true;
                if(choices.getValue()=="Duration")
                    fnduration = true;
                if(advancedSearchChoices.getValue()=="Duration")
                    fnattributeCase = 0;
                if(advancedSearchChoices.getValue()=="Sample ID")
                    fnattributeCase = 1;
                if(!advancedSearchTxt.getText().isEmpty())
                    fnvalue = Integer.parseInt(advancedSearchTxt.getText());

                Map<String, String[]> findOngoingResult = findOngoingResearch(fnstartDate, fnduration, fnattributeCase, fnvalue);

                for (String key : findOngoingResult.keySet()) {
                    String sampleProperties = "";
                    for(String element: findOngoingResult.get(key)) {
                        //samplelistItems.add(key + ", " + element);
                        sampleProperties += element + ",   ";
                    }
                    ongoingResearchItems.add(key + " : " + sampleProperties);

                }

                ongoingResearch.setItems(ongoingResearchItems);
                findOngoingResearchPane.add(ongoingResearch,1,3);
            }});


        ongoingResearch.setItems(ongoingResearchItems);
        findOngoingResearchPane = new GridPane();
        findOngoingResearchPane.setAlignment(Pos.CENTER);
        findOngoingResearchPane.setHgap(10);
        findOngoingResearchPane.setVgap(10);
        findOngoingResearchPane.setPadding(new Insets(25, 25, 25, 25));
        findOngoingResearchPane.setVgap(10);

        findOngoingResearchPane.add(info,0,0);
        findOngoingResearchPane.add(choices,1,0);
        findOngoingResearchPane.add(advancedSearch,0,1);
        findOngoingResearchPane.add(advancedSearchChoices,1,1);
        findOngoingResearchPane.add(advancedSearchTxt,2,1);
        findOngoingResearchPane.add(ongoingResearchEnter,7,7);
        findOngoingResearchPane.add(findOngoingResearchBack,0,7);

        findOngoingResearchScene = new Scene(findOngoingResearchPane, 1000, 500);
        // generateWorkList/////////////////////////////////////////////////////////////////////////////////////////////
        ListView<String> WorkList = new ListView<String>();
        ObservableList<String> WorkListItems = FXCollections.observableArrayList ();

        Map<String, String[]> workList = generateWorkList();
        for (String key : workList.keySet()) {
            WorkListItems.add(key + "  ,   " + workList.get(key)[0]);
        }

        WorkList.setItems(WorkListItems);

        generateWorkListPane = new GridPane();
        generateWorkListPane.setAlignment(Pos.CENTER);
        generateWorkListPane.setHgap(10);
        generateWorkListPane.setVgap(10);
        generateWorkListPane.setPadding(new Insets(25, 25, 25, 25));
        generateWorkListPane.setVgap(10);

        generateWorkListPane.add(WorkList,0,0);
        generateWorkListPane.add(generateWorkListBack,0,7);

        generateWorkListScene = new Scene(generateWorkListPane, 1000, 500);

        // generateSampleList///////////////////////////////////////////////////////////////////////////////////////////
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
        samplelist.setItems(samplelistItems);

        generateSampleListPane = new GridPane();
        generateSampleListPane.setAlignment(Pos.CENTER);
        generateSampleListPane.setHgap(10);
        generateSampleListPane.setVgap(10);
        generateSampleListPane.setPadding(new Insets(25, 25, 25, 25));
        generateSampleListPane.setVgap(10);

        generateSampleListPane.add(samplelist,0,0);
        generateSampleListPane.add(generateSampleListBack,0,7);

        generateSampleListScene = new Scene(generateSampleListPane, 1000, 500);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        primaryStage.setScene(entryScene);

        primaryStage.show();

    }


    public void ButtonClicked(ActionEvent e) {
        if((e.getSource()==addSampleBack)||(e.getSource()==editSampleBack)||(e.getSource()==addSampleResearchBack)
                ||(e.getSource()==addBoxBack)|| (e.getSource()==removeBoxBack)
                || (e.getSource()==findResearchersResearchingAllBack)|| (e.getSource()==findOngoingResearchBack)|| (e.getSource()==generateWorkListBack)|| (e.getSource()==generateSampleListBack))
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
        if (e.getSource()==findResearchersResearchingAll)
            theStage.setScene(findResearchersResearchingAllScene);
        if (e.getSource()==findOngoingResearch)
            theStage.setScene(findOngoingResearchScene);
        if (e.getSource()==generateWorkList)
            theStage.setScene(generateWorkListScene);
        if (e.getSource()==generateSampleList)
            theStage.setScene(generateSampleListScene);
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
                    return "Error: Sample type selected is not valid";
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
                return "Sample added! The new sample ID is " + sampleID;


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

        return "Error adding the sample: Please try again";
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

                    return "Sample updated!";

                }
                else{
                    return "Error: This sample ID is non existent";
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

        return "Error updating the sample: Please try again";
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
                        return "Sample research added!";

                    }
                    else{
                        return "Error: Durations less than 0 or greater than 60 days are not permitted";
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

        return "Error adding sample research: Please try again";
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
                            return "Box added! The new box ID is " + containerID;

                        }
                        else{
                            return "Error: this fridge is at capacity";
                        }
                    }
                }
                else{
                    return "Error: this fridge is non-existent";
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

    //TODO (Ksenia) - write Documentation for this
    //attributeCase = 0 if Duration selected, =1 if SampleID is selected

    public Map<String, String[]> findOngoingResearch(boolean startDate, boolean duration,
                                                     int attributeCase, int value){
        PreparedStatement ps;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            //if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                Map<String, String[]> researchList = new HashMap<String, String[]>();

                if(startDate&&duration){
                    switch(attributeCase){
                        case 0:
                            ps = con.prepareStatement("SELECT emp_id, start_date, duration, samp_id " +
                                    "FROM researches WHERE duration > ?");
                            ps.setInt(1, value);
                            break;
                        case 1:
                            ps = con.prepareStatement("SELECT emp_id, start_date, duration, samp_id " +
                                    "FROM researches WHERE samp_id > ?");
                            ps.setInt(1, value);
                            break;
                        default:
                            ps = con.prepareStatement("SELECT emp_id, start_date, duration, samp_id " +
                                    "FROM researches");
                            break;
                    }

                    results = ps.executeQuery();
                    while (results.next()) {
                        String sampleID = "Sample ID: " + results.getString("samp_id");
                        String employeeID = "Researcher ID: " + results.getString("emp_id");
                        String startDate_query = "Start Date: " + results.getString("start_date");
                        String duration_query = "Duration: " + results.getString("duration");

                        if (!results.wasNull()) {
                            String[] researchAttributes = {startDate_query, duration_query};
                            researchList.put(sampleID + "  ,  " + employeeID, researchAttributes);
                        }
                    }

                }
                else if(startDate){
                    switch(attributeCase){
                        case 0:
                            ps = con.prepareStatement("SELECT emp_id, start_date, samp_id " +
                                    "FROM researches WHERE duration > ?");
                            ps.setInt(1, value);
                            break;
                        case 1:
                            ps = con.prepareStatement("SELECT emp_id, start_date, samp_id " +
                                    "FROM researches WHERE samp_id > ?");
                            ps.setInt(1, value);
                            break;
                        default:
                            ps = con.prepareStatement("SELECT emp_id, start_date, samp_id " +
                                    "FROM researches");
                            break;
                    }

                    results = ps.executeQuery();
                    while (results.next()) {
                        String sampleID = "Sample ID: " + results.getString("samp_id");
                        String employeeID = "Researcher ID: " + results.getString("emp_id");
                        String startDate_query = "Start Date: " + results.getString("start_date");

                        if (!results.wasNull()) {
                            String[] researchAttributes = {startDate_query};
                            researchList.put(sampleID + "  ,  " + employeeID, researchAttributes);
                        }
                    }

                }
                else if(duration){
                    switch(attributeCase){
                        case 0:
                            ps = con.prepareStatement("SELECT emp_id, duration, samp_id " +
                                    "FROM researches WHERE duration > ?");
                            ps.setInt(1, value);
                            break;
                        case 1:
                            ps = con.prepareStatement("SELECT emp_id, duration, samp_id " +
                                    "FROM researches WHERE samp_id > ?");
                            ps.setInt(1, value);
                            break;
                        default:
                            ps = con.prepareStatement("SELECT emp_id, duration, samp_id " +
                                    "FROM researches");
                            break;
                    }
                    results = ps.executeQuery();
                    while (results.next()) {
                        String sampleID = "Sample ID: " + results.getString("samp_id");
                        String employeeID = "Researcher ID: " + results.getString("emp_id");
                        String duration_query = "Duration: " + results.getString("duration");

                        if (!results.wasNull()) {
                            String[] researchAttributes = {duration_query};
                            researchList.put(sampleID + "  ,  " + employeeID, researchAttributes);
                        }
                    }

                }
                else{
                    switch(attributeCase){
                        case 0:
                            ps = con.prepareStatement("SELECT emp_id, samp_id " +
                                    "FROM researches WHERE samp_id > ?");
                            ps.setInt(1, value);
                            break;
                        case 1:
                            ps = con.prepareStatement("SELECT emp_id, samp_id " +
                                    "FROM researches WHERE samp_id > ?");
                            ps.setInt(1, value);
                            break;
                        default:
                            ps = con.prepareStatement("SELECT emp_id, samp_id " +
                                    "FROM researches");
                            break;
                    }
                    results = ps.executeQuery();
                    while (results.next()) {
                        String sampleID = "Sample ID: " + results.getString("samp_id");
                        String employeeID = "Researcher ID: " + results.getString("emp_id");

                        if (!results.wasNull()) {
                            String[] researchAttributes = {};
                            researchList.put(sampleID + "  ,  " + employeeID, researchAttributes);
                        }
                    }


                }

                ps.close();

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
