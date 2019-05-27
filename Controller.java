package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import static java.lang.System.out;
//import personDataBase.personDBHandler;

public class Controller  extends superController  implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button loginButton, signupButton;
    @FXML
    private PasswordField password;
    @FXML
    TextField username;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("i m in login controller babe");
        Connect();

        if( superController.s == null )
        {
            System.out.println("its null in socet ");

        }
        else
        {
            System.out.println("Its not null ....");
        }

    }

    public void signupButtonAction(ActionEvent en) throws IOException {
        FadeTransition fadeout = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        fadeout.play();

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

       fadeout.setOnFinished(e-> {
           try {
             /*  AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("signupScreen.fxml"));
               loader = new FXMLLoader(getClass().getResource("signupScreen.fxml"));
               AnchorPane signupScreenPane = loader.load();
*/


               anchorPane.getChildren().clear();
               AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("SignUp.fxml"));

               anchorPane.getChildren().add(signupScreenPane);


               //suc = loader.getController();
               out.println("SUC IS READY");
               anchorPane.getChildren().clear();
               anchorPane.getChildren().add(signupScreenPane);
               fadeIn.play();
           } catch (IOException e1) {
               e1.printStackTrace();
           }
       });
}
    public void loginButtonAction(ActionEvent evn) /*throws IOException*/ {
        superController.socketOut.println("LogIn");
        String name = username.getText();
        String pass = password.getText();
        if (name.trim().equals("") || pass.trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please fill all fields");
            alert.show();
        }
        out.println("lolll 1343");


       if(superController.socketOut==null)
           System.out.println("it is null in controller");
       else
           System.out.println("It is not null in controller ");

        System.out.println("hello i am in loginCon");

        superController.socketOut.println("logIn");
        System.out.println("login gese");
        superController.socketOut.println(name);
        System.out.println("little bit complete.. 1");
        superController.socketOut.println(pass);
        System.out.println("in login name = "+name+"\t"+pass);


        if(superController.s==null)
        {
            System.out.println("plz conn");
            Connect();
        }
        else
        {
            System.out.println("conn ase");

        }
        if(superController.socketOut.equals(null))
        {
            System.out.println("NULL....");
        }
        if(superController.s.equals(null))
        {
            System.out.println("NULL...ssqsq.");
        }
        System.out.println("little bit complete.. 1");

        System.out.println("here in login 201");

        String ans=null;
        try {
             ans=superController.socketIn.readLine();
        } catch (IOException e) {
            System.out.println("problem in controller 146.");
        }
        System.out.println("request send");
        System.out.println("Answere = "+ans);

            if (ans.equals("trueNotLogIn")|| ans.equals("NotLogIn"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("User name or password is incorrect");
                alert.show();
                return;
            }

            else if(ans.equals("trueLogIn")||ans.equals("LogIn"))
            {
                    System.out.println("Person data base...");
                    FadeTransition fadeout = new FadeTransition(Duration.seconds(1.5), anchorPane);
                    fadeout.setFromValue(1);
                    fadeout.setToValue(0);
                    fadeout.play();

                     FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), anchorPane);
                    fadeIn.setFromValue(0);
                    fadeIn.setToValue(1);

                    fadeout.setOnFinished(e-> {
                        try {
                            anchorPane.getChildren().clear();
                            AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("PersonHandler.fxml"));

                            anchorPane.getChildren().add(signupScreenPane);





                            new AnimationTimer()
                            {
                                @Override
                                public void handle(long now) {
                                    {
                                    //  System.out.println("p2p2p2");

                                    }
                                }


                            }.start();




                            fadeIn.play();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                    System.out.println("Hoise...");
            }
                else
            {
                System.out.println("Problem in answere...");
            }
    }


}


