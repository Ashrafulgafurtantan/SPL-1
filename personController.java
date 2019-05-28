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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/*


class ListFrndName extends Thread {

    public ListFrndName( )

    {
        super();

    }
    @Override
   public void run ()
    {
        try{
        while(true)
        {
            n=superController.SocketInFORFrnds.read();
            if(n>20)
                n=n-48;
            System.out.println("More ja");

            System.out.println("size = "+n);

            for(int i=0;i<n;i++)
            {
                cw=superController.socketIn.readLine();

                System.out.println("Name = "+cw);

                if(cw==null)
                {
                    System.out.println("shes");
                    continue;
                }


                System.out.println("print = "+cw) ;
                Label label=new Label() ;
                label.setOnMouseClicked(e->{
                    String FrndIConnected=label.getText();


                    superController.socketOut.println("rqstFrnd");
                    superController.socketOut.println(FrndIConnected);
                    try {

                        anchorPane.getChildren().clear();
                        AnchorPane signupScreenPane = null;
                        signupScreenPane = FXMLLoader.load(getClass().getResource("MsgSendRecv.fxml"));
                        anchorPane.getChildren().add(signupScreenPane);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                label.setText(cw);
                System.out.println("lol");
                label.setMaxSize(600,69);
                label.setPadding(new Insets(0, 20, 10, 20));
                label.setStyle("-fx-border-color:BLACK;");
                vbox.getChildren().add(label);



            }
        }} catch (IOException e) {
        System.out.println("prob in person 1");       }

        System.out.println("Shit123");
        }



    }

}
*/







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
    Button back,Profile;
    @FXML
    Button Refresh,star;
    static int n=-1;
    static String cw=null;

    int bb=5;


    public   String FrndIConnected=null;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        List<String> Names = new ArrayList<String>();
        superController.socketOut.println("PersonHandler");


       System.out.println("Started person handler");

       try {

/*

         Thread f=new ListFrndName();
         f.start();

*/

                n=superController.socketIn.read();
           if(n>47)
             n=n-48;
           System.out.println("More ja");

           System.out.println("size = "+n);

         for(int i=0;i<n;i++)
           {
               cw=superController.socketIn.readLine();

               System.out.println("Name = "+cw);

               if(cw==null)
               {
                   System.out.println("shes");
                   continue;
               }


               System.out.println("print = "+cw) ;
               Label label=new Label() ;
               label.setOnMouseClicked(e->{
                    FrndIConnected=label.getText();
                   superController.FriendNoeConnected  =FrndIConnected;



                       superController.socketOut.println("rqstFrnd");
                       superController.socketOut.println(FrndIConnected);
                   System.out.println("here i send msg request");
                   try {

                       anchorPane.getChildren().clear();
                       AnchorPane signupScreenPane = null;
                       signupScreenPane = FXMLLoader.load(getClass().getResource("MsgSendRecv.fxml"));
                       anchorPane.getChildren().add(signupScreenPane);
                        } catch (IOException e1) {
                       e1.printStackTrace();
                   }
               });
               label.setText(cw);
               System.out.println("lol");
               label.setMaxSize(600,69);
               label.setPadding(new Insets(0, 20, 3, 20));
               label.setStyle("-fx-border-color:BLACK;");
               vbox.getChildren().add(label);



           }
       } catch (IOException e) {
           System.out.println("prob in person 1");       }

       System.out.println("Shit123");

    }
    @FXML
    public void Star(ActionEvent q)
    {
        /*FadeTransition fadeout = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        fadeout.play();
        System.out.println("My Shit  1111");
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);*/

        System.out.println("My Shit  2.22222");

        try {
            anchorPane.getChildren().clear();
            AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("Started.fxml"));
            System.out.println("your shit");


            anchorPane.getChildren().add(signupScreenPane);
            System.out.println("My Shi t  2.798");

           // fadeIn.play();
            System.out.println("My Shit 3..76g");
        } catch (IOException e1) {
            e1.printStackTrace();

            /*fadeout.setOnFinished(e-> {

            });*/
            System.out.println("My Shit 4");
        }

    }

    @FXML
    public void REfreshButtonAc(ActionEvent y) throws  Exception
    {
        System.out.println("its workingggg,,,");
        anchorPane.getChildren().clear();
        AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("PersonHandler.fxml"));

        anchorPane.getChildren().add(signupScreenPane);
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

        System.out.println("My Shit  2.0");

        try {
            anchorPane.getChildren().clear();
            AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("LogIn.fxml"));


            anchorPane.getChildren().add(signupScreenPane);
            System.out.println("My Shi t  2.7");

            fadeIn.play();
            System.out.println("My Shit 3");
        } catch (IOException e1) {

        fadeout.setOnFinished(e-> {

        });
        System.out.println("My Shit 4");
    }




}

}
