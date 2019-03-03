package sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.Node;

public class signupController extends superController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    ChoiceBox <String> choiceBox;
    Socket s;

    @FXML
    TextField firstName, lastName, Email, password,reEnterPassword;
    @FXML
    Button Back,SignUP;

   // DataBaseHandler pdb = new DataBaseHandler();

    public signupController()
    {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        choiceBox.getItems().addAll("Select Gender","Male","Female", "Other");
        choiceBox.setValue("Select Gender");

        /*try
        {
            System.out.println("baal 1");



        }catch (IOException e1) {
            e1.printStackTrace();
        }*/



    }

    public void backButtonAction(ActionEvent event)
    {
        FadeTransition fadeout = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        fadeout.play();
        System.out.println("My Shit  1");
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        System.out.println("My Shi t  2");

        try {
            anchorPane.getChildren().clear();
            AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("logInSystem.fxml"));

            anchorPane.getChildren().add(signupScreenPane);
            fadeIn.play();
            System.out.println("My Shit 3");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        fadeout.setOnFinished(e-> {

        });
        System.out.println("My Shit 4");
    }

    public void signupButtonAction()
    {

        String fName = firstName.getText();
        String lName = lastName.getText();
        String email = Email.getText();
        String pword = password.getText();
        String reenterPassword = reEnterPassword.getText();
        String type = choiceBox.getValue();



         if(fName.trim().equals("") || lName.trim().equals("") || email.trim().equals("") ||
                pword.trim().equals("") || type.trim().equals("Select Gender")|| reenterPassword.trim().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please fill all fields");
            alert.show();
            return ;
        }
        Connect();

        System.out.println("here in signup 2");

        /*try
        {
            if((s.equals(null)))
            {
                System.out.println("ass");
            }
          //  socketOut = new PrintStream(s.getOutputStream());

          //  socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));



        }catch (IOException e1) {
            e1.printStackTrace();
        }*/


        boolean idd;


        socketOut.println(email);

        System.out.println("here in signup 1");


        if (socketIn.markSupported()) {
            System.out.println("new mail");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Your email is already in use.");
            alert.show();
            return;
        }


      //  pdb.insert(fName,pword,email);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Account created!\nPlease login");



            socketOut.println(fName);
            socketOut.println(pword);
            socketOut.println(email);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.out.println("OK presssed");
            firstName.clear();
            lastName.clear();
            Email.clear();
            password.clear();
            reEnterPassword.clear();

            System.out.println("shit end");
        }
        System.out.println("Eat shit");

        }





}
