package mainViews;/**
 * Created by Tamar on 2016-11-10.
 */

import databaseConnection.OurConnection;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class LabManager extends Application implements User{

    public static void main(String[] args) {
        //launch(args);                      //PUT THIS BACK IN LATER!
        LabManager lb = new LabManager();

        //lb.addFridge(2, -10, 10);           //fix this, inserting into maintains table doesn't work right now
        //lb.addLabManager("Darius Bird");   //this works! yay
        //lb.addResearcher("Zac Efron");     //this works! yay
        //lb.removeLabManager(10);           //this works! yay
        lb.start(null);


    }

    @Override
    public void start(Stage primaryStage) {

        System.out.println("Boxless Samples: ");
        Map<String, String> boxless = findBoxlessSamples();
//        if (boxless == null) {
//            System.out.println("boxless is empty somehow ");
//        }
        for (String key : boxless.keySet()) {
            System.out.println(key + "  ,  " + boxless.get(key));
        }

        System.out.println("UnderCapacity: ");
        Map<String, String[]> notCapacity = findContainersUnderCapacity();
        for (String key : notCapacity.keySet()) {
            System.out.printf("%s", key);
            for(String element: notCapacity.get(key)){
                System.out.printf("%s", "     ,     ");
                System.out.printf("%s", element);
            }
            System.out.printf("%s\n", "");

        }

        System.out.println("Work List: ");
        Map<String, String[]> workerList = generateWorkList();

        for (String key : workerList.keySet()) {
            System.out.printf("%s", key);
            for(String element: workerList.get(key)){
                System.out.printf("%s", "     ,     ");
                System.out.printf("%s", element);
            }
            System.out.printf("%s\n", "");

        }

        System.out.println("Sample List: ");
        Map<String, String[]> sampleList = generateSampleList();
        for (String key : sampleList.keySet()) {
            System.out.printf("%s", key);
            for(String element: sampleList.get(key)){
                System.out.printf("%s", "     ,     ");
                System.out.printf("%s", element);
            }
            System.out.printf("%s\n", "");

        }

    }
    //TODO (Ksenia)
    public String addFridge(int fridgeOccupancy, int temperature, int employeeID){
        PreparedStatement ps1;
        PreparedStatement ps2;
        ResultSet rs;
        Statement stmt;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                //two more things: check if the fridgeID is already existing & add to maintains table

                con = connectionToDatabase.getConnection();

                stmt = con.createStatement();
                rs = stmt.executeQuery("select max(fr_id) as max from fridge2");
                int fridgeID = 0;
                if(rs.next()){
                    fridgeID = rs.getInt("max") + 1;
                }

                /*final String queryCheck = "SELECT * from fridge2 WHERE fr_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, fridgeID);
                final ResultSet resultSet = psCheck.executeQuery();*/
                /*if(resultSet.next()) {
                    return "Error_Already_Exists";

                }*/
                //else{
                    ps1 = con.prepareStatement("INSERT INTO fridge2 VALUES (?,?,?)");
                    ps1.setInt(1, fridgeID);
                    ps1.setInt(2, fridgeOccupancy);
                    ps1.setInt(3, temperature);

                    ps1.executeUpdate();
                    con.commit();

                    ps1.close();

                    ps2 = con.prepareStatement("INSERT INTO maintains VALUES (?,?,?)");
                    ps2.setInt(1, fridgeID);
                    ps2.setInt(2, fridgeOccupancy);
                    ps2.setInt(3, employeeID);  //This insert currently never works.

                    ps2.executeUpdate();
                    con.commit();

                    ps2.close();
                    return "OK";

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

        return "Error_Adding";
    }
    //TODO (Ksenia)
    public int removeFridge(){
        return 0;
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

                /*final String queryCheck = "SELECT * from researcher WHERE emp_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, employeeID);
                final ResultSet resultSet = psCheck.executeQuery();
                if(resultSet.next()) {
                    return "Error_Already_Exists";

                }*/
                //else{

                    ps1 = con.prepareStatement("INSERT INTO researcher VALUES (?,?)");
                    ps1.setInt(1, employeeID);
                    ps1.setString(2, employeeName);

                    ps1.executeUpdate();
                    con.commit();

                    ps1.close();
                    return "OK";

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

        return "Error_Adding";
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

                /*final String queryCheck = "SELECT * from lab_manager WHERE emp_id = ?";
                final PreparedStatement psCheck = con.prepareStatement(queryCheck);
                psCheck.setInt(1, employeeID);
                final ResultSet resultSet = psCheck.executeQuery();
                if(resultSet.next()) {
                    return "Error_Already_Exists";

                }*/
                //else{

                    ps1 = con.prepareStatement("INSERT INTO lab_manager VALUES (?,?)");
                    ps1.setInt(1, employeeID);
                    ps1.setString(2, employeeName);

                    ps1.executeUpdate();
                    con.commit();

                    ps1.close();
                    return "OK";

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

        return "Error_Adding";
    }

    public String removeLabManager(int employeeID){

        PreparedStatement ps1;

        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {

                con = connectionToDatabase.getConnection();

                ps1 = con.prepareStatement("DELETE FROM lab_manager WHERE emp_id = ?");
                ps1.setInt(1, employeeID);

                ps1.executeUpdate();
                con.commit();

                ps1.close();
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
    //TODO (Ksenia)
    public int addSampleToBox(){
        return 0;
    }
    //TODO (Ksenia)
    public int removeSampleFromBox(){
        return 0;
    }
    //TODO (Ksenia)
    public int addBox() {
        return 0;
    }

    //TODO (Ksenia)
    public int removeBox() {
        return 0;
    }

    //Query 10
    public Map<String, String> findBoxlessSamples(){
        Statement stmt;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_o1i0b", "a30662143")) {
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
                    if (!results.wasNull()) {
                    sampleID = "Sample ID: " + results.getString("samp_id");
                    dateCreated = "Date Created: " + results.getDate("date_cr").toString();
                        boxlessSamples.put(sampleID, dateCreated);
                    }
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
    //To the query in ExternalUser, but this workList generates employee IDs as well.
    public Map<String, String[]> generateWorkList() {
        Statement stmt1;
        Statement stmt2;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_o1i0b", "a30662143")) {
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
    //Query 13
    //TODO (Darius): Refer to the application functionality document in the Drive. Again, this one is similar
    //To the query in ExternalUser, but this generates more information about the sample, which may
    //require more complex querying (such as details about the sample).
    public Map<String, String[]> generateSampleList() {
        Statement stmt;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_o1i0b", "a30662143")) {
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
                    name = "Name: " + results.getString("name");
                    sampleID = "SampleID: " + results.getString("samp_id");
                    volume = "Volume: " + results.getString("volume");
                    composition = "Composition: " + results.getString("composition");
                    strain = "Strain: " + results.getString("strain");
                    concentration = "Concentration: " + results.getString("concentration");
                    antibiotic = "Antibiotic: " + results.getString("antibiotic");
                    res_enz_1 = "Restriction Enzyme 1: " + results.getString("res_enz_1");
                    res_enz_2 = "Restriction Enzyme 2: " + results.getString("res_enz_2");
                    origin = "Origin: " + results.getString("origin");
                    part1 = "Part1: " + results.getString("part1");
                    part2 = "Part2: " + results.getString("part2");
                    empID = "Researcher ID: " + results.getString("emp_id");
                    dateCreated = "Date Created: " + results.getString("date_cr");
                    if (empID != null) {
                        // There is someone resarching the sample
                        if (part1 != null) {
                            String[] sampleAttributes = {empID, dateCreated, concentration, part1, part2};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (origin != null) {
                            String[] sampleAttributes = {empID, dateCreated, concentration, origin};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (res_enz_1 != null) {
                            String[] sampleAttributes = {empID, dateCreated, concentration, res_enz_1, res_enz_2};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (name != null) {
                            String[] sampleAttributes = {empID, dateCreated, concentration, name, antibiotic};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (res_enz_1 != null) {
                            String[] sampleAttributes = {empID, dateCreated, concentration, res_enz_1, res_enz_2};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (concentration != null) {
                            String[] sampleAttributes = {empID, dateCreated, concentration};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (composition != null) {
                            String[] sampleAttributes = {empID, dateCreated, strain, composition};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (volume != null) {
                            String[] sampleAttributes = {empID, dateCreated, strain, volume};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (strain != null) {
                            String[] sampleAttributes = {empID, dateCreated, strain};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (sampleID != null) {
                            String[] sampleAttributes = {empID, dateCreated};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                    } else //There is nobody researching the sample
                    {
                        if (part1 != null) {
                            String[] sampleAttributes = {dateCreated, concentration, part1, part2};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (origin != null) {
                            String[] sampleAttributes = {dateCreated, concentration, origin};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (res_enz_1 != null) {
                            String[] sampleAttributes = {dateCreated, concentration, res_enz_1, res_enz_2};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (name != null) {
                            String[] sampleAttributes = {dateCreated, concentration, name, antibiotic};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (res_enz_1 != null) {
                            String[] sampleAttributes = {dateCreated, concentration, res_enz_1, res_enz_2};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (concentration != null) {
                            String[] sampleAttributes = {dateCreated, concentration};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (composition != null) {
                            String[] sampleAttributes = {dateCreated, strain, composition};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (volume != null) {
                            String[] sampleAttributes = {dateCreated, strain, volume};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (strain != null) {
                            String[] sampleAttributes = {dateCreated, strain};
                            sampleList.put(sampleID, sampleAttributes);
                        }
                        else if (sampleID != null) {
                            String[] sampleAttributes = {dateCreated};
                            sampleList.put(sampleID, sampleAttributes);
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
