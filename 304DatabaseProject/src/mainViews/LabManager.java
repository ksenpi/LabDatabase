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
    }

    @Override
    public void start(Stage primaryStage) {

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

    //Query
    //TODO (Darius): Refer to the application functionality document in the Drive.
    public int findBoxlessSamples(){
        return 0;
    }

    //Query
    //TODO (Darius): Refer to the application functionality document in the Drive.
    public Map<String, String[]> findBoxesUnderCapacity(){
        // TODO: FIX THIS I AM A BAD PERSON FOR COPYING THIS
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
