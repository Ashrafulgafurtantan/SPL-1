package sample;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.*;
public class StartedController implements Initializable {

    @FXML
    FlowPane floePane;
    @FXML
    ScrollPane scrollPane;
    @FXML
    AnchorPane anchorPane;
    @FXML
    VBox vbox;
    @FXML
    Button back;





    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("here in start contro");
        try{

        Label ap  = null;

        System.out.println("msg .5");

        Set< Map.Entry< String,String>  >st = superController.StaredMap.entrySet();

        for (Map.Entry< String,String> me:st) {

            String msg =        me.getValue()+" : "+me.getKey();
            System.out.println(msg);

            ap = FXMLLoader.load(getClass().getResource("Label.fxml"));
            ap.setText(msg);
            ap.setStyle("-fx-background-color: blue ; -fx-background-radius: 13px;");
            HBox hb = new HBox();

            hb.getChildren().add(ap);


            hb.setAlignment(Pos.BOTTOM_RIGHT);

            hb.setMinHeight(100);

            hb.setMinWidth(500);
            floePane.setPadding(new Insets(5, 5, 5, 5));

            floePane.getChildren().add(hb);
        }
        }catch(Exception q)
            {
                System.out.println("problem in stared");
            }



        }



    @FXML
    public void BackOnAction( )
    {
        FadeTransition fadeout = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        fadeout.play();
        System.out.println("My Shit  111111111");
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        System.out.println("My Shit  2.2222222222");

        try {
            anchorPane.getChildren().clear();
            AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("PersonHandler.fxml"));
            System.out.println("your shit");


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
