package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class superController {
  public static PrintStream socketOut = null;
  public static Map<String ,String> StaredMap=new HashMap<>();
    public static BufferedReader socketIn =null,SocketInFORFrnds=null;
    public String SERVER_IP_ADDRESS = "127.0.0.1";
   public  static String FriendNoeConnected;
    int SERVER_PORT = 8005;
    public static Socket s=null,ExtraSocket;

    superController()
    {

    }

   public void  Connect()
    {

        try {
            System.out.println("lollllll 1");
            s = new Socket(SERVER_IP_ADDRESS, SERVER_PORT);
          //  ExtraSocket = new Socket(SERVER_IP_ADDRESS, 8009);
            if(ExtraSocket!=null)
            System .out.println("Also connected in 8009");

            System.out.println("lolllllll 1.5");

            socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
          //  SocketInFORFrnds=new BufferedReader(new InputStreamReader(ExtraSocket.getInputStream()));

            System.out.println("lollllll 2");

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
        if(s==null)
        {
            System.out.println("Its null in socket 3");
        }
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