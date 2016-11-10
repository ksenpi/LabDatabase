package mainViews;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Created by kseniapinski on 2016-10-26.
 */
public class Researcher extends Application implements User {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to LabDatabasePro3000");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //HBox
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        //addSample
        Button addSample = new Button("Add Sample");
        hbBtn.getChildren().add(addSample);
        grid.add(hbBtn, 0, 0);

        //editSample
        Button editSample = new Button("Edit Sample");
        hbBtn.getChildren().add(editSample);
        grid.add(hbBtn, 1, 0);

        //addSampleResearch
        Button addSampleResearch = new Button("Reseach Sample");
        hbBtn.getChildren().add(addSampleResearch);
        grid.add(hbBtn, 0, 1);

        //SamplesCreatedSince
        Button samplesCreatedSince = new Button("Samples Created Since");
        hbBtn.getChildren().add(samplesCreatedSince);
        grid.add(hbBtn, 1, 1);

        //addBox
        Button addBox = new Button("Add Box");
        hbBtn.getChildren().add(addBox);
        grid.add(hbBtn, 0, 2);

        //removeBox
        Button removeBox = new Button("Remove");
        hbBtn.getChildren().add(removeBox);
        grid.add(hbBtn, 1, 2);

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();

        //TODO (Tamar): Generate window for Researcher. There should be a button associated with each of the
        //methods below.

    }
    //TODO(Ksenia): 6 methods below
    public int addSample(){
        return 0;
    }

    public int editSample(){
        return 0;
    }

    public int addSampleResearch(){
        return 0;
    }

    public int samplesCreatedBy(String name){
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
