package mainViews;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Map;

/**
 * Created by kseniapinski on 2016-10-26.
 */
public class Researcher extends Application implements User {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO (Tamar): Generate window for Researcher. There should be a button associated with each of the
        //methods below.

    }

    public int addSample(){
        return 0;
    }

    public int editSample(){
        return 0;
    }

    public int addSampleResearch(){
        return 0;
    }

    public int samplesCreatedSince(Date date){
        return 0;
    }

    public int addBox() {
        return 0;
    }

    public int removeBox() {
        return 0;
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
