package sample;



import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.FlowPane;

import javafx.scene.layout.VBox;


import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;

import java.net.URL;

import java.util.ResourceBundle;




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
import sample.superController;


import javax.swing.*;

import java.net.URL;

import java.util.ResourceBundle;


public class messageContr implements Initializable {

    @FXML

    TextField Msg;

    @FXML

    ScrollPane scrollPane;

    @FXML

    FlowPane flow;
    @FXML


    Label name;

    @FXML
    ChoiceBox cBox;
    @FXML

    AnchorPane anchorPane;

    String v=null;

    @FXML
    Button Send,Back;


    @FXML
    MenuButton Mbutt;


    @FXML
    MenuItem file,photo;
    String msg;
    Thread ct;
    String op;




    @Override

    public void initialize(URL location, ResourceBundle resources) {

        Msg.setOnKeyPressed(e -> {
            KeyCode code = e.getCode();
            if(code==KeyCode.ENTER)
            {
                Label ap  = null;
                try {
                    ap = FXMLLoader.load(getClass().getResource("Label.fxml"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                System.out.println("msg .5");

                msg=Msg.getText();
                Msg.setText(null);
                ap.setText(msg);
                ap.setStyle("-fx-background-color: #4169E1 ; -fx-background-radius: 13px;");

                superController.socketOut.println(msg);
                System.out.println("msg 1");


                Label finalAp = ap;
                ap.setOnMouseClicked(et->{
                    op=(String)cBox.getValue();

                    System.out.println("OPtion = "+op);
                    if(op.equals("Star"))
                    {
                        String msg= finalAp.getText();
                        System.out.println("started = "+msg);

                        superController.StaredMap.put(msg,superController.FriendNoeConnected);
                        System.out.println("star complete");
                    }
                    else {

                        String d= finalAp.getText();
                        finalAp.setText("Message was removed");
                        System.out.println("del complete");
                    }


                });

                HBox hb = new HBox();

                hb.getChildren().add(ap);


                hb.setAlignment(Pos.BOTTOM_LEFT);

                hb.setMinHeight(100);

                hb.setMinWidth(500);

                flow.setPadding(new Insets(5, 5, 5, 5));

                flow.getChildren().add(hb);


                System.out.println("Pathao ends");


            }
        });

        name.setText(superController.FriendNoeConnected);


        try {
            String pastMsg= superController.socketIn.readLine();
            if(pastMsg==null)
            {
                System.out.println("yeah null");
             //   pastMsg="ashraf,gafur,lol,hgtbdhs,lojsjs";
            }
            System.out.println("past = "+pastMsg);
            if(pastMsg==null) {

                System.out.println("yeah null 2");
            }
            else
            {
                System.out.println("so watthe line is = "+pastMsg);
            System.out.println(pastMsg);

            String [] arrOfStr=null;
            int j=0;
                arrOfStr = pastMsg.split(",", 10);

              int   i=arrOfStr.length;
                System.out.println("length = "+i);
              //  System.out.println("arr"+arrOfStr[0]);
                i--;
            while(j<=i) {
                arrOfStr = pastMsg.split(",", 10);

                Label ap = null;
                ap = FXMLLoader.load(getClass().getResource("Label.fxml"));


                System.out.println("msg .5");
                if(arrOfStr[j]==null) break;

                msg = arrOfStr[j];
                ap.setText(msg);
                ap.setStyle("-fx-background-color: #FFF600 ; -fx-background-radius: 13px;");

                System.out.println("msg 1");
                j++;
                HBox hb = new HBox();

                hb.getChildren().add(ap);


                hb.setAlignment(Pos.BOTTOM_LEFT);

                hb.setMinHeight(100);

                hb.setMinWidth(500);

                flow.setPadding(new Insets(5, 5, 5, 5));

                flow.getChildren().add(hb);

            }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



        cBox.getItems().addAll("Select Option","Delete","Star");
        cBox.setValue("Select Option");



        ct = new MessageReceive();

        ct.start();

        new AnimationTimer()

        {

            @Override

            public void handle(long now) {

                {

                    Label cp=null;


                    if(v==null ||v.equals(msg)){
                        // System.out.println("cant past bro");
                    }
                    else if(v.equals("exitfromMsg"))
                    {
                        this.stop();
                        System.out.println("v in anima = "+v);
                        v=null;
                        /*try {
                            System.out.println("v = " + v);
                            anchorPane.getChildren().clear();
                            AnchorPane signupScreenPane = FXMLLoader.load(getClass().getResource("PersonHandler.fxml"));


                            anchorPane.getChildren().add(signupScreenPane);
                            System.out.println("My Shi t  2.7");
                        }catch(Exception e)
                        {
                            System.out.println("problem in animation");
                        }*/

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
                        cp.setStyle("-fx-background-color: white ; -fx-background-radius: 13px;");


                        HBox Hb = new HBox();

                        Hb.getChildren().add(cp);

                        System.out.println("msg 3");
                        Hb.setAlignment(Pos.BOTTOM_RIGHT);

                        Hb.setMinHeight(100);

                        Hb.setMinWidth(500);



                        flow.getChildren().add(Hb);
                       // vBox.getChildren().add(cp);

                    }
                    v=null;

                }
              //  System.out.println("mora ja");

            }


        }.start();

        System.out.println("lol gafur");
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
        signupController.socketOut.println("Start");
            while ((g = br.readLine()) != null)
            {
                System.out.println(g);
                superController.socketOut.println(g);
            }
        signupController.socketOut.println("Finish");


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
        superController.socketOut.println("StartImage");



        FileInputStream fis = null;
        fis = new FileInputStream(txtFile);
        /*DataOutputStream os=null;
        os = new DataOutputStream(superController.s.getOutputStream());
        while (( i = fis.read()) > -1)
        {
            if(i<8)
                System.out.println(i);
            os.write(i);

        }*/
        while ((i = fis.read()) > -1)
            superController.socketOut.write(i);

        superController.socketOut.println("FinishImage");




    }

    /*@FXML
    public void Delete(ActionEvent ert)
    {

        System.out.println("del started");
        String  value=Delete.getText();
        Delete.setText("OK");

        if(value.equals("Delete"))
        {
            System.out.println("in process del");
            Label label=new Label() ;
            label.setOnMouseClicked(e->{

            });
        }
        else
        {
            Started.setText("Stared");

        }

    }
    @FXML
    public void Stared(ActionEvent er)
    {

        String  value=Started.getText();
        Started.setText("OK");

        if(value.equals("Stared"))
        {
            System.out.println("started");

            Label label=new Label() ;
            label.setOnMouseClicked(e->{
                String msg=label.getText();
                superController.StaredMap.put(msg,superController.FriendNoeConnected);
                System.out.println("star complete");
            });
        }
        else
        {
            Started.setText("Stared");

        }

    }*/
    @FXML
    public void Back(ActionEvent h)
    {
        superController.socketOut.println("exit");


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


    public void pathao(ActionEvent t) throws IOException {


       /* Label ap  = null;
        ap = FXMLLoader.load(getClass().getResource("Label.fxml"));



        System.out.println("msg .5");

         msg=Msg.getText();
         Msg.setText(null);
        ap.setText(msg);
        ap.setStyle("-fx-background-color: #4169E1 ; -fx-background-radius: 13px;");

        superController.socketOut.println(msg);
        System.out.println("msg 1");


        Label finalAp = ap;
        ap.setOnMouseClicked(e->{
            op=(String)cBox.getValue();

            System.out.println("OPtion = "+op);
            if(op.equals("Star"))
            {
                String msg= finalAp.getText();
                System.out.println("started = "+msg);

                superController.StaredMap.put(msg,superController.FriendNoeConnected);
                System.out.println("star complete");
            }
            else {

                String d= finalAp.getText();
                finalAp.setText("Message was removed");
                System.out.println("del complete");
            }


        });

        HBox hb = new HBox();

        hb.getChildren().add(ap);


        hb.setAlignment(Pos.BOTTOM_LEFT);

        hb.setMinHeight(100);

        hb.setMinWidth(500);

        flow.setPadding(new Insets(5, 5, 5, 5));

        flow.getChildren().add(hb);


        System.out.println("Pathao ends");

*/


    }

    class MessageReceive extends Thread {

        int nn=2;

        public MessageReceive()

        {

            super();



        }



        public void run()

        {
            Label cp=null;
            String na;

            while(true)

            {



                try {

                    v=superController.socketIn.readLine();

                    System.out.println("Read line in msg contr in  msg loop= "+v);

                    if(v.equals("Start") ||v.equals("Finish"))
                    {
                        BufferedWriter bw=null;
                        bw=new BufferedWriter(new FileWriter("Download.txt"));


                        while(true)
                        {
                            v=superController.socketIn.readLine();
                            System.out.println("Read line in msg contr in txt loop = "+v);

                            if(v.equals("Finish"))
                            {
                                bw.close();
                                v=null;
                                break;
                            }
                            bw.write(v);
                            bw.newLine();
                        }

                    }
                    else if(v.equals("StartImage") ||v.equals("FinishImage"))
                    {
                       FileOutputStream fout = new FileOutputStream("notification2.png");

                        int i;
                        while ( (i = superController.socketIn.read()) > -1) {
                            fout.write(i);

                        }
                        v=superController.socketIn.readLine();
                        if(v.equals("FinishImage"))
                        {
                            System.out.println("image complete");


                            v=null;
                        }


                    }

                    else if(v.equals("exitfromMsg")||v.equals("end"))

                        break;
                    else
                    {
                        cp = FXMLLoader.load(getClass().getResource("Label.fxml"));


                        System.out.println("msg 2");
                        cp.setText(v);
                        cp.setStyle("-fx-background-color: white ; -fx-background-radius: 13px;");
                        Label finalCp = cp;
                        cp.setOnMouseClicked(ee->{
                            op=(String)cBox.getValue();

                            System.out.println("OPtion = "+op);
                            if(op.equals("Star"))
                            {
                                String msg= finalCp.getText();
                                System.out.println("started = "+msg);
                                superController.StaredMap.put(msg,superController.FriendNoeConnected);
                                System.out.println("star complete");
                            }
                            else {

                                String d= finalCp.getText();
                                finalCp.setText("Message was removed");
                                System.out.println("del complete");
                            }


                        });



                        HBox Hb = new HBox();

                        Hb.getChildren().add(cp);

                        System.out.println("msg 3");



                        Hb.setAlignment(Pos.BOTTOM_RIGHT);

                        Hb.setMinHeight(100);

                        Hb.setMinWidth(500);
                        flow.setPadding(new Insets(5, 5, 5, 5));

                       // flow.getChildren().add(Hb);
                    }
                } catch (IOException e) {

                    e.printStackTrace();

                }




            }
            ct.stop();




        }



    }}