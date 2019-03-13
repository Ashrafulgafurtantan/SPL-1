package sample;

package Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ClientReaderThread extends Thread {
    private Socket clientSocket;
    static List<PrintStream> clientWriters = new ArrayList<PrintStream>();
    public ClientReaderThread(Socket s) {
        super();
        this.clientSocket = s;
        try {
            PrintStream socketOut = new PrintStream(s.getOutputStream());
            clientWriters.add(socketOut);
        } catch (IOException e) {
            e.printStackTrace();
        }}
    @Override
    public void run() {
        BufferedReader socketIn;
        try {
            socketIn = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            while (true) {
                String line = socketIn.readLine();
                System.out.println("Read line from connection: " + this.cn);

                for(PrintStream cw: clientWriters) {
                    cw.println(line);
                }}}
                catch (Exception e) {
            e.printStackTrace();
}}}

public class MainServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8003);
            while (true) {
                Socket s = ss.accept();
                System.out.println(s.getInetAddress());
                Thread ct = new ClientReaderThread(s);
                ct.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }}}