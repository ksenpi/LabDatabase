package mainViews;

import databaseConnection.OurConnection;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kseniapinski on 2016-10-26.
 */
public class ExternalUser extends Application implements User {


    @Override
    public void start(Stage primaryStage) throws Exception {
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

                rs = stmt1.executeQuery("select samp_id, strain, volume, composition, " +
                        "concentration, name, antibiotic, res_enz_1, res_enz_2, origin, " +
                        "part1, part2 from ligation full outer join " +
                        "(select samp_id, strain, volume, composition, " +
                        "concentration, name, antibiotic, res_enz_1, res_enz_2, origin " +
                        "from genomic full outer join " +
                        "(select samp_id, strain, volume, composition, concentration, " +
                        "name, antibiotic, res_enz_1, res_enz_2 from digest full outer join " +
                        "(select samp_id, strain, volume, composition, concentration, " +
                        "name, antibiotic from plasmid p full outer join " +
                        "(select samp_id, strain, volume, composition, concentration " +
                        "from dna_sample d full outer join (select samp_id, strain, volume, " +
                        "composition from plate full outer join (select samp_id, strain, " +
                        "volume from bacterial_culture b full outer join (select samp_id, " +
                        "volume from sample s full outer join glycerol_stock g on s.samp_id=g.sample_id) " +
                        "on b.sample_id = samp_id) on plate.sample_id = samp_id) on d.sample_id = samp_id) " +
                        "on p.sample_id = samp_id) on digest.sample_id = samp_id) " +
                        "on genomic.sample_id=samp_id) on ligation.sample_id = samp_id");
                while (rs.next()) {
                    sampleID = rs.getString("samp_id");
                    strain = rs.getString("strain");
                    volume = rs.getInt("volume");
                    composition = rs.getString("composition");
                    concentration = rs.getInt("concentration");
                    name = rs.getString("name");
                    antibiotic = rs.getString("antibiotic");
                    res_enz_1 = rs.getString("res_enz_1");
                    res_enz_2 = rs.getString("res_enz_2");
                    origin = rs.getString("origin");
                    part1 = rs.getString("part1");
                    part2 = rs.getString("part2");
                    String[] sampleDetails;
                    if (strain != null) {
                        sampleDetails = new String[]{"Bacterial Culture", "Strain: " + strain};
                    } else if (volume != 0) {
                        sampleDetails = new String[]{"Glycerol Stock", "Volume: " + volume};
                    } else if (composition != null) {
                        sampleDetails = new String[]{"Plate", "Composition: " + composition};
                    } else if (concentration != 0) {
                        sampleDetails = new String[]{"DNA Sample", "Concentration: " + concentration};
                    } else if (name != null || antibiotic != null) {
                        if (name == null) {
                            sampleDetails = new String[]{"Plasmid", "Antibiotic: " + antibiotic};
                        } else if (antibiotic == null) {
                            sampleDetails = new String[]{"Plasmid", "Name: " + name};
                        } else {
                            sampleDetails = new String[]{"Plasmid", "Antibiotic: " + antibiotic, "Name: " + name};
                        }
                    } else if (res_enz_1 != null || res_enz_2 != null) {
                        if (res_enz_1 == null) {
                            sampleDetails = new String[]{"Digest", "Restriction Enzyme 2: " + res_enz_2};
                        } else if (res_enz_2 == null) {
                            sampleDetails = new String[]{"Digest", "Restriction Enzyme 1: " + res_enz_1};
                        } else {
                            sampleDetails = new String[]{"Digest", "Restriction Enzyme 1: " + res_enz_1, "Restriction Enzyme 2: " + res_enz_2};
                        }
                    } else if (origin != null) {
                        sampleDetails = new String[]{"Genomic", "Origin: " + origin};
                    } else if (part1 != null || part2 != null) {
                        sampleDetails = new String[]{"Ligation", "Part 1: " + part1, "Part 2: " + part2};
                    } else {
                        sampleDetails = new String[]{};
                    }
                    sampleList.put(sampleID, sampleDetails);

                }
                stmt1.close();


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
