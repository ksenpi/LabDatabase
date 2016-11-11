package mainViews;/**
 * Created by Tamar on 2016-11-10.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;

import java.util.Map;

public class Researcher extends Application implements User {
    Button goBack, addSample, editSample, addSampleResearch, samplesCreatedBy, addBox, removeBox;
    Stage theStage;
    Scene entryScene, addSampleScene, editSampleScene, addSampleResearchScene, samplesCreatedByScene, addBoxScene, removeBoxScene;
    GridPane entryPane, addSamplePane, editSamplePane, addSampleResearchPane, samplesCreatedByPane, addBoxPane, removeBoxPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to LabDatabasePro3000");

        theStage = primaryStage;

        goBack = new Button("Go to Main Page");
        goBack.setOnAction(e->ButtonClicked(e));

        //entryScene
        addSample = new Button("Add Sample");
        editSample = new Button("Edit Sample");
        addSampleResearch = new Button("Research Sample");
        samplesCreatedBy = new Button("Samples Created");
        addBox = new Button("Add Box");
        removeBox = new Button("Remove Box");
        //making button actions
        addSample.setOnAction(e-> ButtonClicked(e));
        editSample.setOnAction(e-> ButtonClicked(e));
        addSampleResearch.setOnAction(e-> ButtonClicked(e));
        samplesCreatedBy.setOnAction(e-> ButtonClicked(e));
        addBox.setOnAction(e-> ButtonClicked(e));
        removeBox.setOnAction(e-> ButtonClicked(e));
        //entryPane (gridpane)
        entryPane = new GridPane();
        entryPane.setAlignment(Pos.CENTER);
        entryPane.setHgap(10);
        entryPane.setVgap(10);
        entryPane.setPadding(new Insets(25, 25, 25, 25));
        entryPane.setVgap(10);
        //adding all the buttons to the entry pane
        entryPane.add(addSample, 0, 0);
        entryPane.add(editSample, 0, 1);
        entryPane.add(addSampleResearch, 1, 0);
        entryPane.add(samplesCreatedBy, 1, 1);
        entryPane.add(addBox, 2, 0);
        entryPane.add(removeBox, 2, 1);
        entryPane.add(goBack,0,7);                        //check the placement of this button
        entryScene = new Scene(entryPane, 1000, 500);


        //addSampleScene
        Label sampleType = new Label("Sample Type");
        ComboBox sampleTypeBox = new ComboBox();
        sampleTypeBox.getItems().addAll("Bacterial Culture", "Glycerol Stock", "Plate", "DNA Sample", "Plasmid", "Ligation", "Genomic", "Digest");
        Label strain = new Label("Strain:");
        TextField strainTextField = new TextField();
        Label volume = new Label("Volume(mL):");
        TextField volumeTextField = new TextField();
        volumeTextField.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = volumeTextField.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        volumeTextField.setText(volumeTextField.getText().substring(0,volumeTextField.getText().length()-1));
                    }
                }
            }

        });
        Label composition = new Label("Plate Composition:");
        TextField compositionTextField = new TextField();
        Label concentration = new Label("Concentration(ng/uL):");
        TextField concentrationTextField = new TextField();
        concentrationTextField.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = concentrationTextField.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        concentrationTextField.setText(concentrationTextField.getText().substring(0,concentrationTextField.getText().length()-1));
                    }
                }
            }

        });
        Label plasmidName = new Label("Plasmid Name:");
        TextField plasmidTextField = new TextField();
        Label plasmidAntibiotic = new Label("Plasmid Antibiotic:");
        TextField antibioticTextField = new TextField();
        Label rez1 = new Label("Restriction Enzyme 1:");
        TextField rez1TextField = new TextField();
        Label rez2 = new Label("Restriction Enzyme 2:");
        TextField rez2TextField = new TextField();
        Label genomic = new Label("Origin:");
        TextField genomicTextField = new TextField();
        Label ligation1 = new Label("Ligation Part 1:");
        TextField ligation1TextField = new TextField();
        Label ligation2 = new Label("Ligation Part 2:");
        TextField ligation2TextField = new TextField();

        Button enter = new Button("Enter");

        //entryPane (gridpane)
        addSamplePane = new GridPane();
        addSamplePane.setAlignment(Pos.CENTER);
        addSamplePane.setHgap(10);
        addSamplePane.setVgap(10);
        addSamplePane.setPadding(new Insets(25, 25, 25, 25));
        addSamplePane.setVgap(10);

        //adding all the buttons to the entry pane
        addSamplePane.add(sampleType, 0, 0);
        addSamplePane.add(sampleTypeBox, 1, 0);
        addSamplePane.add(strain, 2, 0);
        addSamplePane.add(strainTextField, 3, 0);
        addSamplePane.add(volume, 0, 1);
        addSamplePane.add(volumeTextField, 1, 1);
        addSamplePane.add(composition, 2, 1);
        addSamplePane.add(compositionTextField, 3, 1);
        addSamplePane.add(concentration, 0, 2);
        addSamplePane.add(concentrationTextField, 1, 2);
        addSamplePane.add(plasmidName, 2, 2);
        addSamplePane.add(plasmidTextField, 3, 2);
        addSamplePane.add(plasmidAntibiotic, 0, 3);
        addSamplePane.add(antibioticTextField, 1, 3);
        addSamplePane.add(rez1, 2, 3);
        addSamplePane.add(rez1TextField, 3, 3);
        addSamplePane.add(rez2, 0, 4);
        addSamplePane.add(rez2TextField, 1, 4);
        addSamplePane.add(genomic, 2, 4);
        addSamplePane.add(genomicTextField, 3, 4);
        addSamplePane.add(ligation1, 0, 5);
        addSamplePane.add(ligation1TextField, 1, 5);
        addSamplePane.add(ligation2, 2, 5);
        addSamplePane.add(ligation2TextField, 3, 5);
        addSamplePane.add(goBack,0,7);                  //check the placement of this button
        addSamplePane.add(enter,7,7);                   //check the placeent of this button
        addBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                addSample(); //todo - tamar- add the right call now
            }
        });
        addSampleScene = new Scene(addSamplePane, 1000, 500);

        // editSampleScene


        // addSampleResearchScene
        // samplesCreatedByScene
        // addBoxScene
        //removeBoxScene

        //collum, row

        primaryStage.setScene(entryScene);

        primaryStage.show();

    }

    public void ButtonClicked(ActionEvent e) {
        if(e.getSource()==goBack)
            theStage.setScene(entryScene);
        if (e.getSource()==addSample)
            theStage.setScene(addSampleScene);
        /*
        if (e.getSource()==btnscene1)
            thestage.setScene(scene2);
        else
            thestage.setScene(scene1);*/
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
