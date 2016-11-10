package mainViews;/**
 * Created by Tamar on 2016-11-10.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;

import java.util.Map;

public class Researcher1 extends Application implements User {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to LabDatabasePro3000");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
//collum, row
        //addSample
        HBox hbBtn = new HBox(10);
        Button addSample = new Button("Add Sample");
        hbBtn.getChildren().add(addSample);
        grid.add(hbBtn, 0, 0);

        //editSample
        HBox hbBtn1 = new HBox(10);
        Button editSample = new Button("Edit Sample");
        hbBtn1.getChildren().add(editSample);
        grid.add(hbBtn1, 1, 0);

        //addSampleResearch
        HBox hbBtn3 = new HBox(10);
        Button addSampleResearch = new Button("Reseach Sample");
        hbBtn3.getChildren().add(addSampleResearch);
        grid.add(hbBtn3, 0, 1);

        //SamplesCreatedSince
        HBox hbBtn4 = new HBox(10);
        Button samplesCreatedSince = new Button("Samples Created Since");
        hbBtn4.getChildren().add(samplesCreatedSince);
        grid.add(hbBtn4, 1, 1);

        //addBox
        HBox hbBtn5 = new HBox(10);
        Button addBox = new Button("Add Box");
        hbBtn5.getChildren().add(addBox);
        grid.add(hbBtn5, 0, 2);

        //removeBox
        HBox hbBtn6 = new HBox(10);
        Button removeBox = new Button("Remove Box");
        hbBtn6.getChildren().add(removeBox);
        grid.add(hbBtn6, 1, 2);

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();

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
