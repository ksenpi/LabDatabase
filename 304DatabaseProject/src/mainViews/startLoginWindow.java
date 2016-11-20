package mainViews;

import javafx.application.Application;
import databaseConnection.OurConnection;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by kseniapinski on 2016-10-26.
 */
public class startLoginWindow extends Application {
    private Researcher researcher;
    private LabManager labManager;
    private ExternalUser externalUser;


    //TODO (Tamar): This will be the first thing a user sees when they open up the application. Currently the login
    //window is not what we want (I just copied an example off the internet), but it'll be in a similar layout.
    //Ideally, we want the user to select whether they are a Researcher, Lab Manager, or external user. If they
    //are a researcher or lab manager, they should be prompted to put in their employee id.
    //If this employee id does not exist in the table, login should be denied.
    //Their user type will determine the next window that is shown to them.

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to LabDatabasePro3000");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userType = new Label("User Type");
        grid.add(userType, 0, 1);

        final ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("Lab Manager", "Researcher", "External User");
        grid.add(comboBox, 1, 1);

        Label employeeID = new Label("Employee ID:");
        grid.add(employeeID, 0, 2);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);
        userTextField.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = userTextField.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    //Check if the new character is the number or other's
                    if(!(ch >= '0' && ch <= '9' )){

                        //if it's not number then just setText to previous one
                        userTextField.setText(userTextField.getText().substring(0,userTextField.getText().length()-1));
                    }
                }
            }

        });

        /*Label pw = new Label("Password:");
        grid.add(pw, 0, 3);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 3);*/

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (comboBox.getValue() == null) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("select User Type");
                }
                else if (comboBox.getValue().equals("External User")){
                    externalUser = new ExternalUser();
                    externalUser.start(ExternalUser.classStage);
                }
                else if (comboBox.getValue().equals("Researcher") && isIDValid(2,Integer.parseInt(userTextField.getText()))){
                        researcher = new Researcher(Integer.parseInt(userTextField.getText()));
                        researcher.start(Researcher.classStage);
                    }
                else if (comboBox.getValue().equals("Lab Manager") && isIDValid(1,Integer.parseInt(userTextField.getText()))){
                        labManager = new LabManager(Integer.parseInt(userTextField.getText()));
                        labManager.start(LabManager.classStage);}
                else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("access denied");
                }
            }

        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();



    }
    //type = 0 if externalUser, 1 if labManager, 2 if researcher
    public boolean isIDValid(int type, int id){
        PreparedStatement ps;
        ResultSet results;
        OurConnection connectionToDatabase = new OurConnection();
        if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            //if (connectionToDatabase.connect("ora_e5w9a", "a10682145")) {
            try {
                Connection con = connectionToDatabase.getConnection();
                switch(type){
                    case 0:
                        return true;
                    case 1:
                        ps = con.prepareStatement("SELECT * from lab_manager WHERE emp_id = ?");
                        ps.setInt(1, id);
                        results = ps.executeQuery();
                        if(results.next()){
                            return true;
                        }
                        else{
                            return false;
                        }
                    case 2:
                        ps = con.prepareStatement("SELECT * from researcher WHERE emp_id = ?");
                        ps.setInt(1, id);
                        results = ps.executeQuery();
                        if(results.next()){
                            return true;
                        }
                        else{
                            return false;
                        }
                }

            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());
            }

            }
       return false;
    }
}
