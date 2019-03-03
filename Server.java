package sample;
import javafx.scene.control.Alert;

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
    static List<PrintStream> clientWriters = new ArrayList<PrintStream>();
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
            InetAddress ipAdd=clientSocket.getInetAddress();
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

            pdb.insert(name,pword,email);
            System.out.println("hoise 7");
            clientWriters.add(socketOut);
           while(true)
           {
               name=socketIn.readLine();
               pword=socketIn.readLine();
               System.out.println("name in server ="+name+"\t"+pword);
               int nn=0;
              boolean idd = pdb.loginSearch(name, pword);
              if(idd)
              {
                  System.out.println("Thik de hala...");
                  socketOut.print(true);
              }
              else
              {
                  nn=2;
                  System.out.println("Thik dsos hala...");
                  socketOut.print(false);


              }


               while (n==2)
               {
                   String line = socketIn.readLine();
                   if(line.equals("e")) nn=3;
                   else
                   {
                       System.out.println("Read line from connection: " + this.cn);

                       for(PrintStream cw: clientWriters)
                       {
                           cw.println(line);
                       }
                   }

               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
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


                System.out.println("Got a new connection, number: " + cnumber);
                Thread ct = new ClientReaderThread(s, cnumber);
                ct.start();
                cnumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
