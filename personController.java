package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


import javax.swing.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class personController implements Initializable {

  @FXML
    FlowPane floePane;
  @FXML
  ScrollPane scrollPane;
  @FXML
    AnchorPane anchorPane;
    @FXML
    VBox vbox;
    @FXML
    Button back,p2p;
   @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       System.out.println("Started person handler");

       try {
           int bb=5;
           String cw=null;

           for(int i=0;i<1;i++)
           {
               int n=superController.socketIn.read();

               System.out.println("size = "+n);


               cw=superController.socketIn.readLine();
               if(cw==null)
               {
                   System.out.println("shes");
                   break;
               }


               System.out.println("print = "+cw);
               Label label=new Label() ;
               label.setOnMouseClicked(e->{
                   String Frnd=label.getText();


                       superController.socketOut.println("rqstFrnd");
                       superController.socketOut.println(Frnd);
//eta must
                   try {

                       anchorPane.getChildren().clear();
                       AnchorPane signupScreenPane = null;
                       signupScreenPane = FXMLLoader.load(getClass().getResource("fake.fxml"));
                       anchorPane.getChildren().add(signupScreenPane);
                        } catch (IOException e1) {
                       e1.printStackTrace();
                   }

                    //for some test start

                 /*  try {
                       superController.socketOut.println("hlw can u hear me");
                       System.out.println("Ball amr");

                       System.out.println(superController.socketIn.readLine());
                   } catch (IOException e1) {
                       e1.printStackTrace();
                   }*/

                       //end

               });
               label.setText(cw);
               cw=null;
               System.out.println("lol");
               label.setMaxSize(600,69);
               label.setPadding(new Insets(0, 20, 10, 20));
               label.setStyle("-fx-border-color:BLACK;");
               vbox.getChildren().add(label);
           }
       } catch (IOException e) {
           System.out.println("prob in person 1");       }

       System.out.println("Shit123");

    }
    @FXML
    public void p2p(ActionEvent event)throws Exception
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
            AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("Peer2Peer.fxml"));

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

    @FXML
    public void BackButtonAction(ActionEvent h)
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




}
