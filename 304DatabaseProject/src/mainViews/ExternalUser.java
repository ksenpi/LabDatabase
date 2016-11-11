package mainViews;/**
 * Created by Tamar on 2016-11-10.
 */

import databaseConnection.OurConnection;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class ExternalUser extends Application implements User{


    @Override
    public void start(Stage primaryStage) {
        //TODO (Tamar): Generate window for ExternalUser. There should be a button associated with each of the
        //methods below. I have given an example for how you can handle the ResultSet handed back from a helper.

        Map<String, String[]> workList = generateWorkList();
        for (String key : workList.keySet()) {
            System.out.println(key + "  ,   " + workList.get(key)[0]);

        }

        Map<String, String[]> sampleList = generateSampleList();
        for (String key : sampleList.keySet()) {
            System.out.printf("%s", key);
            System.out.printf("%s", "     ,     ");
            for(String element: sampleList.get(key)){
                System.out.printf("%s", element);
                System.out.printf("%s", "     ,     ");
            }
            System.out.printf("%s\n", "");

        }


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
                //TODO (Ksenia): fix this query!

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
            }
        }
        return null;
    }


    //This won't be in the final version of our code, it's only here at the moment so that you guys can run the
    //code in this class and see what prints out. It should print out a list of all the workers in the lab. If it
    //doesn't, there might be something off with your ssh connection.
    public static void main(String args[]) throws Exception {
        ExternalUser user = new ExternalUser();
        user.start(null);

    }

    }

