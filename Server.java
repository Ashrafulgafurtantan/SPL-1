package sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


class FindFriend extends Thread {

    PrintStream socketOutForFrnds=null;
    BufferedReader socketIn;
    static List<String> clientNames ;
    static List<PrintStream>SendFrndsName;


    public FindFriend(PrintStream socketOutForFrnds, BufferedReader socketIn,   List<String> c,List<PrintStream> SendFrndsName ){

        super();
        this.socketIn=socketIn;
        this.socketOutForFrnds=socketOutForFrnds;
        clientNames=c;
        this.SendFrndsName=SendFrndsName;

    }
    @Override
    public void run()
    {
     //   int hudai=-1;
        while(true)
        {
           /* int y = clientNames.size();
            if(y!=hudai)
            {
                hudai=y;
                System.out.println("Change hoise vai....");

                System.out.println("size in server = "+y);


                System.out.println(y);

                for(PrintStream ps:SendFrndsName)
                {
                    ps.print(y);
                    for(String cw: ClientReaderThread.clientNames)
                    {
                        ps.println(cw);
                        System.out.println("name = "+cw);
                        System.out.println(1+"\t bar");
                    }
                }



            }*/
        }
    }

}

class ClientReaderThread extends Thread
{
    private Socket clientSocket;
    private int cn;
    public static int f=0,forJust2ndTimeCallfrndListThread=-1;
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
    public void run()  {

        BufferedReader socketIn;
        DataBaseHandler pdb=new DataBaseHandler(23);
      //  DataBaseHandler p=new DataBaseHandler()
        System.out.println("hoise 101");
        try {
            System.out.println("hoise 1");

            socketIn = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            System.out.println("hoise 2");

            PrintStream socketOut = new PrintStream(clientSocket.getOutputStream());
            //PrintStream socketOutForFrnds = new PrintStream(SC.getOutputStream());

            System.out.println("hoise 3");

            System.out.println("IP Address = "+clientSocket.getInetAddress());
            String ipAdd=clientSocket.getInetAddress().getHostAddress();
            System.out.println("hoise 4");

            int n=2;
            String name=null,pword=null,email=null;


            String code=null;

           while(true)
           {
               System.out.println("again first...");
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

                   else {
                       nn = 2;
                       System.out.println("Thik dsos hala...");
                       socketOut.println("LogIn");


                   }

               }
               else  if(code.equals("PersonHandler"))
               {

                   System.out.println("i am here in Person hand");
                   // if(forJust2ndTimeCallfrndListThread==-1)
                    {
                        forJust2ndTimeCallfrndListThread=22;
                        Thread frndList = new FindFriend(socketOut,socketIn,clientNames,clientWriters);
                       // frndList.start();
                        System.out.println("First time and last time in code...");


                        int y = clientNames.size();
                        int hudai=-1;
                        if(y!=hudai)
                        {
                            hudai=y;
                            System.out.println("Change hoise vai....");

                            System.out.println("size in server = "+y);
                            socketOut.print(y);
                            System.out.println("here i send size");

                           // for(PrintStream ps:clientWriters)
                            {
                               // ps.print(y);
                                for(String cw: ClientReaderThread.clientNames)
                                {
                                    socketOut.println(cw);
                                    System.out.println("name = "+cw);
                                    System.out.println(1+"\t bar");
                                }
                                System.out.println("here i send name");
                            }



                        }

                    }


               }
               else if(code.equals("SignIn"))
               {
                  // while(n==2)
                   {
                        email=socketIn.readLine();
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
                   if(n==3)
                   {
                       name=socketIn.readLine();
                       pword=socketIn.readLine();
                       email=socketIn.readLine();
                       System.out.println("hoise 6");

                       pdb.insert(name,pword,ipAdd,email);
                       System.out.println("name = "+name);
                       clientNames.add(name);

                       System.out.println("hoise 7");

                       clientWriters.add(socketOut);
                      // SendFrndsName.add(socketOutForFrnds);

                   }
               }

               else if (code.equals("rqstFrnd"))
               {
                   String frndName=socketIn.readLine();
                   System.out.println("baallllllll   1");
                   String cw=null;


                   System.out.println("Sender = "+name+"\treceiver = "+frndName);
                 //  pdb.sendMsg(clientSocket,name,frndName);
                   while(true)
                   {
                       String msg=socketIn.readLine();
                       System.out.println("Msg from client 1 test :"+msg);

                       if(msg.equals("end"))
                       {
                           System.out.println("loop broken ...");
                           break;
                       }



                       System.out.println("baal 3");

                         pdb.InsertMessage(frndName,name,msg);

                      /* for(PrintStream cwt: clientWriters) {

                           cwt.println(msg);
                       }
                       */
                       for(int i=0;i<clientNames.size();i++)
                       {
                           String Nm=clientNames.get(i);
                           if(Nm.equals(frndName))
                           {
                               System.out.println("Milse frnd ");
                               System.out.println(Nm);
                               for(int j=0;j<clientWriters.size();j++)
                               {
                                  // if(j==i)
                                   System.out.println("in looop");
                                   {
                                       PrintStream cwt=clientWriters.get(j);
                                       cwt.println(name);
                                       cwt.println(msg);


                                   }
                               }
                              // PrintStream cwt=clientWriters.get(i);
                               System.out.println(frndName+"\t"+name+"\t"+msg);


                           }
                       }
                       System.out.println("baal 4");

                   }





               }
               /*
               else if(code.equals("p2p"))
               {
                   System.out.println("saal .5");
                   int y=0;
                   String frndName=null;

                   for(String cw: ClientReaderThread.clientNames)
                   {
                       socketOut.println(cw);
                       y++;
                       System.out.println(1+"\t bar");
                   }
                   socketOut.print(y);
                   System.out.println("y = "+y);


                   System.out.println("saal 1");
                   frndName=socketIn.readLine();
                   System.out.println("hoilo...");
                  String IPAddress= pdb.IpAddress(frndName);*/
                        /* PrintStream prst=null;
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
*/
                  /*      System.out.println("Sender = "+name+"\treceiver = "+frndName);
                   while(true)
                   {
                       String msg=socketIn.readLine();
                       socketOut.println("end");

                       System.out.println("Msg from client 1 :"+msg);



                       //    pdb.InsertMessage(frndName,name,msg);
                       System.out.println("baal 3");

                       // prst.println(msg);
                       for(PrintStream cwt: clientWriters) {
                           //System.out.println("Msg from client 3 :"+msg);
                           cwt.println(msg);
                       }
                       System.out.println("baal 4");

                   }


               }*/
               else
               {
                   System.out.println("problem in code...");
               }
               code=null;


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
    static final int LISTEN_PORT = 8005;
    public static void main(String[] args) {
        try {
           //DataBaseHandler pdb=new DataBaseHandler();
            System.out.println("baal 101");
            System.out.println("Listening on port: " + LISTEN_PORT);
            ServerSocket ss = new ServerSocket(LISTEN_PORT);

           // ServerSocket SC = new ServerSocket(8009);

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
