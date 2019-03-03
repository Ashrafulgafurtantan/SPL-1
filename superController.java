package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
public class superController {
  public  PrintStream socketOut = null;
    public  BufferedReader socketIn =null;
    public String SERVER_IP_ADDRESS = "127.0.0.1";
    int SERVER_PORT = 8003;
    public  Socket s=null;

   public void  Connect()
    {

        try {
            System.out.println("lolll 1");
            s = new Socket(SERVER_IP_ADDRESS, SERVER_PORT);
            System.out.println("lolll 1.5");

            socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("lolll 2");

            socketOut = new PrintStream(s.getOutputStream());
            if(socketOut==null)
                System.out.println("It is fing null");
            else
                System.out.println("Please kill yourself");

        }catch (IOException e) {
            System.out.println("Problem in Socket...");
            e.printStackTrace();
            System.out.println("baal 2");


        }
    }
    public Socket getS()
    {
        return s;
    }
   public BufferedReader getBufferedReader()
   {
       return socketIn;
   }
   public PrintStream getPrintStream()
   {
       return socketOut;
   }
}
