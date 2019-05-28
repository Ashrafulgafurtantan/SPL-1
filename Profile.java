package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Profile implements Initializable {

    @FXML
    Label fN,lN,eM,Pa;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        superController.socketOut.println("Profile");
        String pro= null;
        try {
            pro = superController.socketIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String [] arrOfStr=null;
        arrOfStr = pro.split(",", 10);
        fN.setText(arrOfStr[0]);
        eM.setText(arrOfStr[1]);
        Pa.setText(arrOfStr[2]);


    }
}
