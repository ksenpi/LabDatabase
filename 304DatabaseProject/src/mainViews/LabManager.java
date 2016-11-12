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
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        System.out.println("we got here!");

        Map<String, String> boxless = findBoxlessSamples();
        for (String key : boxless.keySet()) {
            System.out.println(key + "  ,  " + boxless.get(key).toString());
        }

        Map<String, String[]> notCapacity = findContainersUnderCapacity();
        for (String key : notCapacity.keySet()) {
            System.out.printf("%s", key);
            for(String element: notCapacity.get(key)){
                System.out.printf("%s", "     ,     ");
                System.out.printf("%s", element);
            }
            System.out.printf("%s\n", "");

        }

    }

    public String addFridge(int temperature, int employeeID){
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
                    ps1.setInt(2, 0);
                    ps1.setInt(3, temperature);

                    ps1.executeUpdate();
                    con.commit();

                    ps1.close();

                    ps2 = con.prepareStatement("INSERT INTO maintains VALUES (?,?,?)");
                    ps2.setInt(1, fridgeID);
                    ps2.setInt(2, 0);
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
                    int occupancy = resultSet.getInt("f_occupancy");
                    if(occupancy==0){
                        //Where the actual deleting happens
                        ps1 = con.prepareStatement("DELETE FROM fridge2 WHERE fr_id = ?");
                        ps1.setInt(1, fridgeID);

                        ps1.executeUpdate();
                        con.commit();

                        ps1.close();
                        return "OK";
                    }
                    else{
                        return "Error_Occupancy_NOT_0";
                    }


                } else {
                    return "Error_Does_NOT_Exist";
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
        return "Error_Removing";
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
                    return "OK";


                }
                else{
                    return "Error_Does_NOT_Exist";
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

        return "Error_Removing";

    }
    //TODO (Ksenia)
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
                                ps0 = con.prepareStatement("UPDATE container2 SET c_occupancy = ? WHERE c_id = ?");
                                ps0.setInt(1, containerOccupancy + 1);
                                ps0.setInt(2, containerID);
                                ps0.executeUpdate();
                                con.commit();
                                ps0.close();

                                ps1 = con.prepareStatement("INSERT INTO contains VALUES (?,?,?,?,?,?)");
                                ps1.setInt(1, containerID);
                                ps1.setInt(2, fridgeID);
                                ps1.setInt(3, containerOccupancy + 1);
                                ps1.setInt(4, fridgeOccupancy);
                                ps1.setInt(5, sampleID);
                                ps1.setInt(6, employeeID);

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
                    sampleID = results.getString("samp_id");
                    dateCreated = results.getDate("date_cr").toString();
                    if (!results.wasNull()) {
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
                    containerID = results.getString("c_id");
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
    //Query
    //TODO (Darius): Refer to the application functionality document in the Drive. This one is similar
    //To the query in ExternalUser, but this workList generates employee IDs as well.
    public Map<String, String[]> generateWorkList() {
        return null;
    }

    @Override
    //Query
    //TODO (Darius): Refer to the application functionality document in the Drive. Again, this one is similar
    //To the query in ExternalUser, but this generates more information about the sample, which may
    //require more complex querying (such as details about the sample).
    public Map<String, String[]> generateSampleList() {
        return null;
    }
}
