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
import javafx.stage.Stage;

import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class Researcher extends Application implements User {
    Button goBack, addSample, editSample, addSampleResearch, samplesCreatedBy, addBox, removeBox;
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

        goBack = new Button("Go to Main Page");
        goBack.setOnAction(e->ButtonClicked(e));

        //entryScene
        addSample = new Button("Add Sample");
        editSample = new Button("Edit Sample");
        addSampleResearch = new Button("Research Sample");
        samplesCreatedBy = new Button("Samples Created");
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
        entryPane.add(goBack,0,7);                        //check the placement of this button
        entryScene = new Scene(entryPane, 1000, 500);


        //addSampleScene
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

        Button enter = new Button("Enter");

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

        addSamplePane.add(goBack,0,7);                  //check the placement of this button
        addSamplePane.add(enter,7,7);                   //check the placement of this button
        enter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                addSample(); //todo - tamar- add the right call now
            }
        });
        /*addSamplePane.add(strain, 2, 0);
        addSamplePane.add(strainTextField, 3, 0);
        addSamplePane.add(volume, 0, 1);
        addSamplePane.add(volumeTextField, 1, 1);
        addSamplePane.add(composition, 2, 1);
        addSamplePane.add(compositionTextField, 3, 1);
        addSamplePane.add(concentration, 0, 2);
        addSamplePane.add(concentrationTextField, 1, 2);
        addSamplePane.add(plasmidName, 2, 2);
        addSamplePane.add(plasmidTextField, 3, 2);
        addSamplePane.add(plasmidAntibiotic, 0, 3);
        addSamplePane.add(antibioticTextField, 1, 3);
        addSamplePane.add(rez1, 2, 3);
        addSamplePane.add(rez1TextField, 3, 3);
        addSamplePane.add(rez2, 0, 4);
        addSamplePane.add(rez2TextField, 1, 4);
        addSamplePane.add(genomic, 2, 4);
        addSamplePane.add(genomicTextField, 3, 4);
        addSamplePane.add(ligation1, 0, 5);
        addSamplePane.add(ligation1TextField, 1, 5);
        addSamplePane.add(ligation2, 2, 5);
        addSamplePane.add(ligation2TextField, 3, 5);*/


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

        // editSampleScene


        // addSampleResearchScene
        // samplesCreatedByScene
        // addBoxScene
        //removeBoxScene

        //collum, row

        primaryStage.setScene(entryScene);

        primaryStage.show();

    }

    public void ButtonClicked(ActionEvent e) {
        if(e.getSource()==goBack)
            theStage.setScene(entryScene);
        if (e.getSource()==addSample)
            theStage.setScene(addSampleScene);
        /*
        if (e.getSource()==btnscene1)
            thestage.setScene(scene2);
        else
            thestage.setScene(scene1);*/
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
                    return "Error_Does_NOT_Exist";
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
                    if(duration <= 30 && duration >=0){
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
    //TODO (Ksenia) Do some more queries by input here!
    public int samplesCreatedBy(String name){
        return 0;
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
                            return "Error_Container_At_Capacity";
                        }
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
