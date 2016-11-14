package mainViews;/**
 * Created by Tamar on 2016-11-10.
 */

import databaseConnection.OurConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import databaseConnection.OurConnection;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ExternalUser extends Application implements User{
    Stage theStage;
    Button worklistBack, samplelistBack, worklist, samplelist;
    GridPane entryPane, worklistPane, samplelistPane;
    Scene entryScene, worklistScene, samplelistScene;

    public ExternalUser() {}

    //added so we can call the class from another class
    static Stage classStage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //added so we can call the class from another class
        ExternalUser.classStage = primaryStage ;
        primaryStage.setTitle("Welcome to LabDatabasePro3000");
        theStage = primaryStage;

        worklistBack = new Button("Go to Main Page");
        worklistBack.setOnAction(e->ButtonClicked(e));
        samplelistBack = new Button("Go to Main Page");
        samplelistBack.setOnAction(e->ButtonClicked(e));
        //EntryScene////////////////////////////////////////////////////////////////////////////////////////////////////
        worklist = new Button("See Worklist");
        samplelist = new Button("See Samplelist");
        worklist.setOnAction(e-> ButtonClicked(e));;
        samplelist.setOnAction(e-> ButtonClicked(e));;

        entryPane = new GridPane();
        entryPane.setAlignment(Pos.CENTER);
        entryPane.setHgap(10);
        entryPane.setVgap(10);
        entryPane.setPadding(new Insets(25, 25, 25, 25));
        entryPane.setVgap(10);

        entryPane.add(worklist,0,0);
        entryPane.add(samplelist,1,0);

        entryScene = new Scene(entryPane, 1000, 500);

        //worklist//////////////////////////////////////////////////////////////////////////////////////////////////////

        ListView<String> worklist = new ListView<>();

        ObservableList<String> worklistitems =FXCollections.observableArrayList ();

        Map<String, String[]> workList = generateWorkList();
        for (String key : workList.keySet()) {
            worklistitems.add(key + "  ,   " + workList.get(key)[0]);
        }

        worklist.setItems(worklistitems);

        worklistPane = new GridPane();
        worklistPane.setAlignment(Pos.CENTER);
        worklistPane.setHgap(10);
        worklistPane.setVgap(10);
        worklistPane.setPadding(new Insets(25, 25, 25, 25));
        worklistPane.setVgap(10);

        worklistPane.add(worklist,0,0);
        worklistPane.add(worklistBack,0,7);

        worklistScene = new Scene(worklistPane, 1000, 500);
        //samplelist////////////////////////////////////////////////////////////////////////////////////////////////////
        ListView<String> samplelist = new ListView<>();

        ObservableList<String> samplelistItems =FXCollections.observableArrayList ();

        Map<String, String[]> sampleList = generateSampleList();
        for (String key : sampleList.keySet()) {
            for(String element: sampleList.get(key)) {
                samplelistItems.add(key + ", " + element);
            }

        }
        samplelist.setItems(samplelistItems);

        samplelistPane = new GridPane();
        samplelistPane.setAlignment(Pos.CENTER);
        samplelistPane.setHgap(10);
        samplelistPane.setVgap(10);
        samplelistPane.setPadding(new Insets(25, 25, 25, 25));
        samplelistPane.setVgap(10);

        samplelistPane.add(samplelist,0,0);
        samplelistPane.add(samplelistBack,0,7);

        samplelistScene = new Scene(samplelistPane, 1000, 500);

        primaryStage.setScene(entryScene);

        primaryStage.show();
        //TODO (Tamar): Generate window for ExternalUser. There should be a button associated with each of the
        //methods below. I have given an example for how you can handle the ResultSet handed back from a helper.

/*
        Map<String, String[]> workList = generateWorkList();
        System.out.println(workList);
        for (String key : workList.keySet()) {
            System.out.println(key + "  ,   " + workList.get(key)[0]);

        }

        Map<String, String[]> sampleList = generateSampleList();
        for (String key : sampleList.keySet()) {
            System.out.printf("%s", key);
            for(String element: sampleList.get(key)){
                System.out.printf("%s", "     ,     ");
                System.out.printf("%s", element);
            }
            System.out.printf("%s\n", "");

        }
*/
    }

    public void ButtonClicked(ActionEvent e) {
        if((e.getSource()==samplelistBack)||(e.getSource()==worklistBack))
            theStage.setScene(entryScene);
        if (e.getSource()==samplelist)
            theStage.setScene(samplelistScene);
        if (e.getSource()==worklist)
            theStage.setScene(worklistScene);

    }
    @Override
    //TODO (Darius): This one's actually already complete, I just wanted to show an example of how to query the
    //database programmatically.
    public Map<String, String[]> generateWorkList() {
        Statement stmt1;
        Statement stmt2;
        ResultSet rs;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
        //if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt1 = con.createStatement();
                stmt2 = con.createStatement();
                String name;
                Map<String, String[]> workerList = new HashMap<String, String[]>();

                rs = stmt1.executeQuery("select name from lab_manager");
                while (rs.next()) {
                    name = rs.getString("name");
                    if (!rs.wasNull()) {
                        //System.out.printf("%-20.20s", name);
                        String[] typeOfWorker = {"Lab Manager"};
                        workerList.put(name, typeOfWorker);
                    }
                    //System.out.println("     ");
                }
                stmt1.close();

                rs = stmt2.executeQuery("select name from researcher");
                while (rs.next()) {
                    name = rs.getString("name");
                    if (!rs.wasNull()) {
                        if (workerList.get(name) == null) {
                            //System.out.printf("%-20.20s", name);
                            String[] typeOfWorker = {"Researcher"};
                            workerList.put(name, typeOfWorker);
                        } else {
                            //System.out.printf("%-20.20s", name);
                            String[] typeOfWorker = {"Lab Manager and Researcher"};
                            workerList.put(name, typeOfWorker);
                        }
                    }
                    //System.out.println("     ");
                }
                stmt1.close();

                return workerList;
            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
                return null;
            }
        }
        return null;
    }

    @Override
    //TODO (Darius): Same case here, this one's complete, but it's implemented a little strangely,
    // so you use it as a guide or refactor it at the end, if you'd like.
    public Map<String, String[]> generateSampleList() {
        Statement stmt1;
        Statement stmt2;
        ResultSet rs;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
        //if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                stmt1 = con.createStatement();
                stmt2 = con.createStatement();
                String sampleID;
                String strain;
                int volume;
                String composition;
                int concentration;
                String name;
                String antibiotic;
                String res_enz_1;
                String res_enz_2;
                String origin;
                String part1;
                String part2;
                Map<String, String[]> sampleList = new HashMap<String, String[]>();

                rs = stmt1.executeQuery("select b2.sample_id, strain, volume, composition " +
                        "from glycerol_stock g full outer join (select b.sample_id, strain, " +
                        "composition from plate p full outer join bacterial_culture b on p.sample_id = b.sample_id) " +
                        "b2 on g.sample_id = b2.sample_id");
                while (rs.next()) {
                    sampleID = rs.getString("sample_id");
                    strain = rs.getString("strain");
                    volume = rs.getInt("volume");
                    composition = rs.getString("composition");
                    String[] sampleDetails;
                    if (volume != 0) {
                        sampleDetails = new String[]{"Glycerol Stock", "Strain:" + strain, "Volume: " + volume};
                    } else if (composition != null) {
                        sampleDetails = new String[]{"Plate", "Strain:" + strain, "Composition: " + composition};
                    } else  if (strain != null) {
                        sampleDetails = new String[]{"Bacterial Culture", "Strain: " + strain};
                    } else{
                        sampleDetails = new String[]{};
                    }

                    sampleList.put(sampleID, sampleDetails);

                }
                stmt1.close();

                rs = stmt2.executeQuery("select d6.sample_id, concentration, name, antibiotic, res_enz_1, res_enz_2, " +
                        "origin, part1, part2 from plasmid p full outer join (select d5.sample_id, concentration, " +
                        "res_enz_1, res_enz_2, origin, part1, part2 from digest d full outer join " +
                        "(select d4.sample_id, concentration, origin, part1, part2 from genomic g full outer join " +
                        "(select d3.sample_id, concentration, part1, part2 from ligation l full outer join " +
                        "dna_sample d3 on d3.sample_id=l.sample_id) d4 on g.sample_id = d4.sample_id) d5 on " +
                        "d.sample_id = d5.sample_id) d6 on p.sample_id = d6.sample_id");

                while (rs.next()) {
                    sampleID = rs.getString("sample_id");
                    concentration = rs.getInt("concentration");
                    name = rs.getString("name");
                    antibiotic = rs.getString("antibiotic");
                    res_enz_1 = rs.getString("res_enz_1");
                    res_enz_2 = rs.getString("res_enz_2");
                    origin = rs.getString("origin");
                    part1 = rs.getString("part1");
                    part2 = rs.getString("part2");
                    String[] sampleDetails2;

                    if (name != null || antibiotic != null) {
                        if (name == null) {
                            sampleDetails2 = new String[]{"Plasmid", "Concentration: " + concentration, "Antibiotic: " + antibiotic};
                        } else if (antibiotic == null) {
                            sampleDetails2 = new String[]{"Plasmid", "Concentration: " + concentration, "Name: " + name};
                        } else {
                            sampleDetails2 = new String[]{"Plasmid", "Concentration: " + concentration, "Antibiotic: " + antibiotic, "Name: " + name};
                        }
                    } else if (res_enz_1 != null || res_enz_2 != null) {
                        if (res_enz_1 == null) {
                            sampleDetails2 = new String[]{"Digest", "Concentration: " + concentration, "Restriction Enzyme 2: " + res_enz_2};
                        } else if (res_enz_2 == null) {
                            sampleDetails2 = new String[]{"Digest", "Concentration: " + concentration, "Restriction Enzyme 1: " + res_enz_1};
                        } else {
                            sampleDetails2 = new String[]{"Digest", "Concentration: " + concentration, "Restriction Enzyme 1: " + res_enz_1, "Restriction Enzyme 2: " + res_enz_2};
                        }
                    } else if (origin != null) {
                        sampleDetails2 = new String[]{"Genomic", "Concentration: " + concentration, "Origin: " + origin};
                    } else if (part1 != null || part2 != null) {
                        sampleDetails2 = new String[]{"Ligation", "Concentration: " + concentration, "Part 1: " + part1, "Part 2: " + part2};
                    } else if (concentration != 0){
                        sampleDetails2 = new String[]{"DNA Sample", "Concentration: " + concentration};
                    } else {
                        sampleDetails2 = new String[]{};
                    }

                    sampleList.put(sampleID, sampleDetails2);
                }
                stmt2.close();


                return sampleList;
            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
                return null;
            }
        }
        return null;
    }


    }

