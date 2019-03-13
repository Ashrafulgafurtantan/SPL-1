package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.ScrollPane;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.FlowPane;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.VBox;

        import javax.swing.*;
        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;



public class messageContr implements Initializable {
    @FXML
    Button send;
    @FXML
    TextField Msg;
    @FXML
    ScrollPane scrollPane;
    @FXML
    FlowPane flow;

    @FXML
    Label name;
    @FXML
    AnchorPane anchorPane;
    String v=null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void pathao() throws IOException {


        Label ap  = null;
        System.out.println("msg .5");
        String msg=Msg.getText();
        superController.socketOut.println(msg);

        System.out.println("msg 1");

        ap = FXMLLoader.load(getClass().getResource("Label.fxml"));

        ap.setText(msg);
        HBox hb = new HBox();
        hb.getChildren().add(ap);
        System.out.println("msg 3");

        hb.setAlignment(Pos.BOTTOM_LEFT);
        hb.setMinHeight(100);
        hb.setMinWidth(500);
        flow.setPadding(new Insets(10, 10, 10, 10));
        flow.getChildren().add(hb);


        Thread ct = new MessageReceive();
        ct.start();

       new AnimationTimer()
       {
           @Override
           public void handle(long now) {
               {
                   Label cp=null;
                       System.out.println("Yusuf");
                       if(v==null)
                       {

                       }
                       else
                       {
                           try {
                               cp = FXMLLoader.load(getClass().getResource("Label.fxml"));
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                           System.out.println("msg 2");


                           cp.setText(v);
                           HBox Hb = new HBox();
                           Hb.getChildren().add(cp);
                           System.out.println("msg 3");

                           Hb.setAlignment(Pos.BOTTOM_RIGHT);
                           Hb.setMinHeight(100);
                           Hb.setMinWidth(500);

                           flow.getChildren().add(Hb);

                           v=null;

                       }

               }
           }


       }.start();
        System.out.println("lol gafur");



    }
    class MessageReceive extends Thread {
        int nn=2;
        public MessageReceive()
        {
            super();

        }

       public void run()
       {
           while(true)
           {

               try {
                   v=superController.socketIn.readLine();
                   System.out.println("Read line = "+v);

               if(v.equals("exit"))
                   break;
               } catch (IOException e) {
                   e.printStackTrace();
           }

       }

    }

}}
/* Label cp=null;
        String v=null;
        try {
            v=superController.socketIn.readLine();
            System.out.println("Read line = "+v);
            cp = FXMLLoader.load(getClass().getResource("Label.fxml"));
            System.out.println("msg 2");

        } catch (IOException e) {
            e.printStackTrace();
        }
        cp.setText(v);
        HBox Hb = new HBox();
        Hb.getChildren().add(cp);
        System.out.println("msg 3");

        Hb.setAlignment(Pos.BOTTOM_RIGHT);
        Hb.setMinHeight(100);
        Hb.setMinWidth(500);

        flow.setPadding(new Insets(10, 10, 10, 10));
        flow.getChildren().add(Hb);
*/