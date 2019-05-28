package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class Peer2PeerChatBoxController implements Initializable {
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
        try {
            String ipAddress=superController.socketIn.readLine();
            ServerSocket ss=new ServerSocket(8006);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
