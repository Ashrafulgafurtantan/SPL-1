package sample;

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

public class MsgController implements Initializable {
    @FXML
    Button send;
    @FXML
    TextField Msg;
    @FXML
    ScrollPane scrollPane;
    @FXML
    FlowPane flow;
    @FXML
    VBox vbox;
    @FXML
    Label name;
    @FXML
    AnchorPane anchorPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


  /*  public void SendButtonAction (Action event)throws Exception
    {
        System.out.println("msg .5");
        String msg=Msg.getText();
        System.out.println("msg 1");

        superController.socketOut.println(msg);



        System.out.println("Msg = "+msg);
        Label ap  = null;
        try {
            System.out.println("Read line = "+superController.socketIn.readLine());
            ap = FXMLLoader.load(getClass().getClassLoader().getResource("Label.fxml"));
            System.out.println("msg 2");

        } catch (IOException e) {
            e.printStackTrace();
        }
        ap.setText(msg);
        HBox hb = new HBox();
        hb.getChildren().add(ap);
        System.out.println("msg 3");

        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.setMinHeight(100);
        hb.setMinWidth(500);
        //flow.setMaxHeight(300);
        //flow.setMaxWidth(500);
        flow.setPadding(new Insets(10, 10, 10, 10));
        flow.getChildren().add(hb);
        //	System.out.println("Tor jibon britha: " + s);
        //out2.println(s);
        //out(msg);


    }*/
    @FXML
    public void b(Action w)
    {

        System.out.println("shit 1");
    }


    /*void receive() throws IOException {
       String msg= superController.socketIn.readLine();
        Label ap  = FXMLLoader.load(getClass().getClassLoader().getResource("Label.fxml"));
        ap.setText(msg);
        HBox hb = new HBox();
        hb.getChildren().add(ap);
        hb.setAlignment(Pos.BOTTOM_LEFT);
        hb.setMinHeight(100);
        hb.setMinWidth(500);
        //flow.setMaxHeight(300);
        //flow.setMaxWidth(500);
        flow.setPadding(new Insets(10, 10, 10, 10));
        flow.getChildren().add(hb);
    }*/
}
