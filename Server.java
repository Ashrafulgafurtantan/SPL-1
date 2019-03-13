package sample;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



class ClientReaderThread extends Thread
{
    private Socket clientSocket;
    private int cn;
    public static int f=0;
    static List<PrintStream> clientWriters = new ArrayList<PrintStream>();
    static List<String> clientNames = new ArrayList<String>();

    public ClientReaderThread()
    {

    }
    public ClientReaderThread(Socket s, int cn)
    {
        super();
        this.clientSocket = s;
        this.cn = cn;
    }

    @Override
    public void run() {
        BufferedReader socketIn;
        DataBaseHandler pdb=new DataBaseHandler(23);
        System.out.println("hoise 101");
        try {
            System.out.println("hoise 1");

            socketIn = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            System.out.println("hoise 2");

            PrintStream socketOut = new PrintStream(clientSocket.getOutputStream());
            System.out.println("hoise 3");

            System.out.println("IP Address = "+clientSocket.getInetAddress());
            String ipAdd=clientSocket.getInetAddress().getHostAddress();
            System.out.println("baal 4");

            int n=2;
            while(n==2)
            {
                String email=socketIn.readLine();
                System.out.println("from server = "+email);
                boolean idd=false;
                System.out.println("hoise 10");

                idd=pdb.MailSearch(email);
                System.out.println("hoise 11");

                if (idd) {
                    System.out.println("new mail");
                    n++;
                    socketOut.print(true);
                    System.out.println("notun mail..");


                }
                else
                {
                    socketOut.print(false);
                }
            }

            String name=socketIn.readLine();
            String pword=socketIn.readLine();
            String  email=socketIn.readLine();
            System.out.println("hoise 6");

            pdb.insert(name,pword,ipAdd,email);
            clientNames.add(name);

            System.out.println("hoise 7");
            clientWriters.add(socketOut);
            String code=null;
           while(true)
           {
               code=socketIn.readLine();
               System.out.println("c0de = "+code);

               if(code.equals("logIn"))
               {
                   name=socketIn.readLine();
                   pword=socketIn.readLine();
                   System.out.println("name in server ="+name+"\t"+pword);
                   int nn=0;
                   boolean idd = pdb.loginSearch(name, pword);
                   if(idd)
                   {
                       System.out.println("Thik de hala...");
                       socketOut.println("NotLogIn");
                   }

                   else
                   {
                       nn=2;
                       System.out.println("Thik dsos hala...");
                       int y=clientNames.size();
                       socketOut.println("LogIn");
                       socketOut.print(y);
                       System.out.println(y);
                       for(String cw: ClientReaderThread.clientNames)
                       {
                           socketOut.println(cw);
                           System.out.println(1+"\t bar");
                       }

                     /* else if()
                      {
                          String frndName=socketIn.readLine();
                          //  String ip= pdb.IpAddress(frndName);
                          //socketOut.println(ip);
                          String cw=null;
                          for( int i=0;i<clientNames.size();i++)
                          {
                              cw=clientNames.get(i);
                              if(frndName.equals(cw))
                              {
                                  PrintStream prst=clientWriters.get(i);
                                  socketOut.println(prst);
                              }
                          }
                      }*/



                   }

               }
               else if (code.equals("rqstFrnd"))
               {
                   String frndName=socketIn.readLine();
                   System.out.println("baal 1");
                   String cw=null;
                         /* PrintStream prst=null;
                          for( int i=0;i<clientNames.size();i++)
                          {
                              cw=clientNames.get(i);
                              if(frndName.equals(cw))
                              {
                                   prst=clientWriters.get(i);
                                  socketOut.println(prst);
                                  System.out.println("baal 2");

                                  break;
                              }
                          }
                          prst.println("Some one wants to be your frnd?");*/
                   System.out.println("Sender = "+name+"\treceiver = "+frndName);
                   while(true)
                   {
                       String msg=socketIn.readLine();
                       System.out.println("Msg from client 1 :"+msg);



                       //    pdb.InsertMessage(frndName,name,msg);
                       System.out.println("baal 3");

                       // prst.println(msg);
                       for(PrintStream cwt: clientWriters) {
                           System.out.println("Msg from client 3 :"+msg);
                           cwt.println(msg);
                       }
                       System.out.println("baal 4");

                   }


               }
               else if(code.equals("p2p"))
               {

                   String frndName=socketIn.readLine();
                   System.out.println("saal 1");
                   String cw=null;
                         PrintStream prst=null;
                          for( int i=0;i<clientNames.size();i++)
                          {
                              cw=clientNames.get(i);
                              if(frndName.equals(cw))
                              {
                                   prst=clientWriters.get(i);
                                  socketOut.println(prst);
                                  System.out.println("saal 2");

                                  break;
                              }
                          }
                   System.out.println("Sender = "+name+"\treceiver = "+frndName);
                   while(true)
                   {
                       String msg=socketIn.readLine();
                       System.out.println("Msg from client 1 :"+msg);



                       //    pdb.InsertMessage(frndName,name,msg);
                       System.out.println("baal 3");

                       // prst.println(msg);
                       for(PrintStream cwt: clientWriters) {
                           System.out.println("Msg from client 3 :"+msg);
                           cwt.println(msg);
                       }
                       System.out.println("baal 4");

                   }


               }





           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList getArr()
    {
        return (ArrayList) clientNames;
    }

}

public class Server {
    static final int LISTEN_PORT = 8003;
    public static void main(String[] args) {
        try {
           //DataBaseHandler pdb=new DataBaseHandler();
            System.out.println("baal 101");
            System.out.println("Listening on port: " + LISTEN_PORT);
            ServerSocket ss = new ServerSocket(LISTEN_PORT);

            int cnumber = 1;
            while (true) {
                Socket s = ss.accept();
                String host=s.getInetAddress().getHostAddress();


                System.out.println("Got a new connection, number: " + cnumber);
                System.out.println("host = "+host);

                Thread ct = new ClientReaderThread(s, cnumber);
                ct.start();
                cnumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
/*

Platform.runLater(new Runnable() {
    @Override
    public void run() {
      //Execute some script to hide a div
      webEngine.executeScript("document.getElementById('myDiv').style.display='none';");
    }
});


*/

/*

class ClientActiveThread extends Thread
{
    public ClientActiveThread()
    {
        super();
    }

    ClientReaderThread y=new ClientReaderThread();
    y.

}
*/
