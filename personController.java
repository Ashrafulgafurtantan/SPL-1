package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ResourceBundle;

public class personController implements Initializable {

    String [] str=new String [5];
  @FXML
    FlowPane floePane;
  @FXML
  ScrollPane scrollPane;
  @FXML
    AnchorPane anchorPane;
    @FXML
    VBox vbox;
   @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        str[0]="Adewd";
        str[1]="Adewdfref";
        str[2]="fgtewd";
        str[3]="Adeferfwd";
        str[4]="Adewdferferf";


        for(int i=0;i<5;i++)
        {
            Label label=new Label() ;

            label.setText(str[i]);
            label.setMaxSize(600,69);
            label.setPadding(new Insets(0, 20, 10, 20));
            label.setStyle("-fx-border-color:BLACK;");
            vbox.getChildren().add(label);


        }
       System.out.println("Shit123");
       //floePane.getChildren().add(vbox);

    }



}
