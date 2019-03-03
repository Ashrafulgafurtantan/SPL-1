package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
//import personDataBase.personDBHandler;

public class Controller   implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button loginButton, signupButton;
    @FXML
    private TextField password;
    @FXML
    TextField username;

    //signupController suc;
    FXMLLoader loader;

  //  DataBaseHandler pdb = new DataBaseHandler();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    /*
        public void mouseHover()
        {
            System.out.println("Mouse hovering");
            //Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            //imageView.fitWidthProperty().bind(window.widthProperty());
            //imageView.fitHeightProperty().bind(window.heightProperty());

        }
    */
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
               //AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("signupScreen.fxml"));
               loader = new FXMLLoader(getClass().getResource("signupScreen.fxml"));
               AnchorPane signupScreenPane = loader.load();
               //suc = loader.getController();
               System.out.println("SUC IS READY");
               anchorPane.getChildren().clear();
               anchorPane.getChildren().add(signupScreenPane);
               fadeIn.play();
           } catch (IOException e1) {
               e1.printStackTrace();
           }
       });
}
    public void loginButtonAction(ActionEvent evn) /*throws IOException*/ {
        String name = username.getText();
        String pass = password.getText();
        if (name.trim().equals("") || pass.trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please fill all fields");
            alert.show();
        }



        signupController suc = loader.getController();
       Socket  s=suc.getS();
        System.out.println("lolll 1343");
       BufferedReader socketIn=suc.getBufferedReader();
       PrintStream socketOut=suc.getPrintStream();
       if(socketOut==null)
           System.out.println("it is null in controller");
       else
           System.out.println("It is not null in controller 2");

       /* try {
           // socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
           // socketOut = new PrintStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("in login name = "+name+"\t"+pass);

        System.out.println("hello i am here");

        socketOut.println(name);
        System.out.println("little bit complete.. 1");
        socketOut.println(pass);



        boolean idd=true;

        if(socketOut.equals(null))
        {
            System.out.println("NULL....");
        }
        if(s.equals(null))
        {
            System.out.println("NULL...ssqsq.");
        }
        System.out.println("little bit complete.. 1");

        System.out.println("here in signup 101");

        idd=socketIn.markSupported();
        System.out.println(idd);
        System.out.println("request send");

            if (idd) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("User name or password is incorrect");
                alert.show();
                return;
            }
            else
                {

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
                            AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("person.fxml"));

                            anchorPane.getChildren().add(signupScreenPane);
                            fadeIn.play();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                    System.out.println("Hoise...");
                }
    }


}


