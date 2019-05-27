package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.*;
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
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
        import java.util.ResourceBundle;



public class messageContr implements Initializable {
    @FXML
    Button Send,Back;
    @FXML
    TextField Msg;
    @FXML
    ScrollPane scrollPane;
    @FXML
    FlowPane flow;
    @FXML
    MenuButton Mbutt;

    @FXML
    Label name;
    @FXML
    MenuItem file,photo;
    @FXML
    AnchorPane anchorPane;
    String v=null;
    AnimationTimer animationTimer;
    Thread ct;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("in msg contrller");
         ct = new MessageReceive();
        ct.start();


    }

    public void AttachFile(ActionEvent q) throws Exception
    {
        FileChooser fc=new FileChooser();
        File txtFile=fc.showOpenDialog(null);
        if(txtFile==null)
        {
            System.out.println("ints null bro...");
        }
        else
        {
            System.out.println(txtFile.getName());
        }

         BufferedReader br = null;
        br = new BufferedReader(new FileReader(txtFile));
        String g=null;
        g=br.readLine();
        while(g!=null)
        {
            System.out.println(g);
            g=null;
            g=br.readLine();


        }
       // sCurrentLine = br.readLine()
        
    }

    public void AttachPhoto() throws  Exception
    {
        int i=99;

        FileChooser fc=new FileChooser();
        File txtFile=fc.showOpenDialog(null);
        if(txtFile==null)
        {
            System.out.println("ints null bro...");
        }
        else
        {
            System.out.println(txtFile.getName());
        }



        FileInputStream fis = null;
        fis = new FileInputStream(txtFile);
        DataOutputStream os=null;
        os = new DataOutputStream(superController.s.getOutputStream());
        while (( i = fis.read()) > -1)
        {
            if(i<8)
                System.out.println(i);
            os.write(i);

        }


    }


    public void Back(ActionEvent ev)
    {
        superController.socketOut.println("end");
        ct.stop();
        //animationTimer.stop();
        /*FadeTransition fadeout = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        fadeout.play();*/
        System.out.println("My Shit  1");
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), anchorPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        System.out.println("My Shi t  2");

        try {
           // anchorPane.getChildren().clear();
           // AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("PERSON.fxml"));

           // anchorPane.getChildren().add(signupScreenPane);
        //   fadeIn.play();
            System.out.println("My Shit 3");


            AnchorPane signupScreenPane = null;
            System.out.println("My Shit 4");

            anchorPane.getChildren().clear();
            System.out.println("My Shit 4.5");

            signupScreenPane = FXMLLoader.load(getClass().getResource("PersonHandler.fxml"));
            if (anchorPane == null)
                System.out.println("Anchor pane is null");
            System.out.println("My Shit 5");

            anchorPane.getChildren().add(signupScreenPane);



        } catch (IOException e1) {
            e1.printStackTrace();
        }

       /*fadeout.setOnFinished(e-> {

        });*/
        System.out.println("My Shit 4");
    }


    public void pathao() throws IOException {


        Label ap  = null;
        System.out.println("msg .5");
        String msg=Msg.getText();

        superController.socketOut.println(msg);
        if(msg.equals("end"))
        {

        }
        else
        {
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
        }








    }
    class MessageReceive extends Thread {
        int nn=2;
        String FrndName=superController.FriendNoeConnected;

        String forTest=null;

        public MessageReceive()
        {
            super();

        }

       public void run()
       {
           System.out.println(FrndName+ " in thread bro");

           while(true)
           {

               try {
                   System.out.println("Waiting for msg...");
                   if(superController.socketIn==null)
                   {
                       System.out.println("problem problem ");
                   }
                   forTest=superController.socketIn.readLine();
                   System.out.println(forTest);
                   v=superController.socketIn.readLine();
                   System.out.println(forTest+ "\t"+ v+ "  in thread ");
                   if(forTest.equals(FrndName))
                   {
                       System.out.println("Read line = "+v);

                   }

               if(v.equals("end"))
               {
                   v=null;
                   break;
               }

               } catch (IOException e) {
                   e.printStackTrace();
           }

       }

    }

}


}
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