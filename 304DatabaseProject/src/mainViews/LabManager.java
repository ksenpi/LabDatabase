package mainViews;

import databaseConnection.OurConnection;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by kseniapinski on 2016-10-26.
 */
public class LabManager extends Application implements User {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO (Tamar): Generate window for LabManager. There should be a button associated with each of the
        //methods below.

    }

    public int addFridge(int fridgeID, int fridgeOccupancy, int temperature, int employeeID){
        int atCapacity;
        PreparedStatement ps;
        OurConnection connectionToDatabase = new OurConnection();
        Connection con = null;
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                con = connectionToDatabase.getConnection();
                ps = con.prepareStatement("INSERT INTO fridge VALUES (?,?,?,?)");
                ps.setInt(1, fridgeID);
                ps.setInt(2, fridgeOccupancy);
                ps.setInt(3, temperature);
                ps.setInt(4, 0);

                ps.executeUpdate();
                con.commit();

                ps.close();
                return 1; 
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

        return 0;
    }

    public int removeFridge(){
        return 0;
    }

    public int addResearcher(){
        return 0;
    }

    public int addLabManager(){
        return 0;
    }

    public int removeLabManager(){
        return 0;
    }

    public int addSampleToBox(){
        return 0;
    }

    public int removeSampleFromBox(){
        return 0;
    }

    public int addBox() {
        return 0;
    }

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
    public int findBoxesUnderCapacity(){
        return 0;
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
