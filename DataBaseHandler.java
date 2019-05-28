package sample;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public  class DataBaseHandler {
    int i = 1;

    public static final String DB_URL = "jdbc:derby:database;create=true";
    public static Connection conn = null;
    public static Statement stmt = null;

    ResultSet rs;


    public DataBaseHandler() {
        createConnection();
        System.out.println("conn createdDFGTH");
       if (setupDrugTable()) {
        }
        printTable();
        // jointTable();
        //	deleteTable();
		/*String s = searchInteraction("DRUG_A", "DRUG_B");
		if(s!=null)
			System.out.println(s);
		else
			System.out.println("Not found");*/

    }
    public DataBaseHandler(int y)
    {
        createConnection();
        System.out.println("conn createdDFGTH");
        if (setupDrugTable()) {
            //  insert("dada","qeqw","qwdq");


        }
        System.out.println("lol 2");

        printTable();
        System.out.println("lol 3");

    }


    void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean setupDrugTable() {
        String tableName = "USERID";
        String TN = "CHAT";
      //  String email="Email";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table exists");
                return false;
            } else {
                String myShit = "CREATE TABLE " + tableName + "("
                        + "  ID int not null generated always as identity(start with 01, increment by 01 ),"
                        + "  Name varchar(200),"
                        + " Password varchar(200),"
                        + " IpAddress varchar(200),"

                        + " Email varchar(200),"
                        + "  constraint primary_key PRIMARY KEY(ID)"
                        +")";

                System.out.println(myShit);
                stmt.execute(myShit);
                System.out.println(" lol table created");



               /* String s="CREATE TABLE " + TN + "("
                        +"  ID int not null generated always as identity(start with 01, increment by 01 ),"
                        +"  Message varchar(200),"
                        +"  Sender varchar(200),"
                        +"  Receiver varchar(200),"
                        + "  constraint primary_key PRIMARY KEY(ID)"
                        +")";*/


                String s="CREATE TABLE " + TN + "("
                       // +"  ID int not null generated always as identity(start with 01, increment by 01 ),"
                        +"Message varchar(200),"
                        +"Sender varchar(200),"
                        +"Receiver varchar(200)"
                      //  + "  constraint primary_key PRIMARY KEY(ID)"
                        +")";
                System.out.println(s);
                stmt.execute(s);
                System.out.println("chat table created");




                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    String  sendMsg(Socket clientSocket,String Sender,String Receiver) throws Exception
    {
        String rt=null;
        System.out.println("ashcheeeee");
        PrintStream socketOut = new PrintStream(clientSocket.getOutputStream());
        try{

            stmt =null;
            stmt =conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM CHAT");


            System.out.println("ready");


            while(rs.next())
        {
            System.out.println("ashche 2");

            System.out.println(rs.getString("Message"));
            String s=rs.getString("Sender");
            String r=rs.getString("Receiver");
            System.out.println("Sender = "+s);
            System.out.println("receiver = "+r);

            if(((s.equals(Sender)) && (r.equals(Receiver)))||((s.equals(Receiver)) && (r.equals(Sender))))
            {
                System.out.print("msg in database...\n\n\n");
                System.out.println(rs.getString("Message"));
                rt=rt+","+rs.getString("Message");

            }
        }
        System.out.println("in the end");


    } catch (SQLException e) {
        e.printStackTrace();
    }

return rt;

    }
   void InsertMessage(String receiver,String sender,String Msg) throws SQLException {
       String TN="CHAT";



      String sr= "INSERT INTO " + TN+ "(Message , Sender, Receiver )" +" VALUES " + "("
               +
               "'"+  Msg + "',"+
              "'" + sender + "'," +
              "'" + receiver + "'" +")";
       System.out.println(sr);
       stmt.execute(sr);
       System.out.println("Inserted");
   }

    void insert(String name, String pass,String ipAdd,String email) {
        String tableName = "USERID";
        System.out.println(name + "\t " + pass);
        // String TN="CHAT";
        System.out.println("Insert started...");
        try {
            stmt = conn.createStatement();


            String s = "INSERT INTO " + tableName+ "(Name , Password,IpAddress , Email )" +" VALUES " + "("+
                   // + i + "," +

                    "'" + name + "'," +
                    "'" + pass + "'," +
                    "'" + ipAdd + "'," +

                    "'" + email + "'" +")";

            System.out.println(s);
            stmt.execute(s);


            System.out.println("Inserted ....");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String Profile(String name)
    {
        System.out.println("name =" + name );
        try {
            stmt =null;
            stmt =conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM USERID");
            while (rs.next()) {
                System.out.println("shit 1");
                System.out.println(rs.getString("Name"));
                if (name.equals(rs.getString("Name"))) {
                    System.out.println("mail milse...");
                    String hh=rs.getString("Name")+","+rs.getString("Email")+","+rs.getString("Password");
                    return hh;
                }
            }
        } catch (Exception e) {
            System.out.println("Problem in email Search...");
        }
        return null;
    }


    boolean MailSearch(String email)
    {
        System.out.println("mail =" + email );
        try {
            stmt =null;
            stmt =conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM USERID");
            while (rs.next()) {
                System.out.println("shit 1");
                System.out.println(rs.getString("Email"));
                if (email.equals(rs.getString("Email"))) {
                    System.out.println("mail milse...");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Problem in email Search...");
        }
        return true;
    }
    public String IpAddress(String name)
    {
        ResultSet ret = null;
        System.out.println("Name =" + name );
        try {
            stmt = conn.createStatement();

            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM USERID");
            while (rs.next()) {
                if ((name.equals(rs.getString("Name")))) {
                    System.out.println("Milse....");

                    return (rs.getString("IpAddress"));
                }
            }
        } catch (Exception e) {
            System.out.println("Problem in ipaddress Search...");
        }
        return "ip nai";
    }

    public boolean loginSearch(String name, String pass) throws SQLException {

        ResultSet ret = null;
        System.out.println("Name =" + name + "\t" + "pass = " + pass);
        try {
            stmt = conn.createStatement();

            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM USERID");
            while (rs.next()) {
                if ((name.equals(rs.getString("Name")) && (pass.equals(rs.getString("Password"))))) {
                        System.out.println("Milse....");

                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Problem in login Search...");
        }
        return true;
    }
    void jointTable() {
		/*
		 String tableName="USERID";
		String TN="CHAT";

		*/
        ResultSet ret = null;
        Statement state = null;
        try {
            stmt = conn.createStatement();
            state = conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM CHAT");
            while (rs.next()) {
                int a = rs.getInt("sender");
                System.out.println("a = " + a);
                ret = state.executeQuery("SELECT * FROM USERID");

                while (ret.next()) {
                    System.out.println(" hell = " + ret.getInt("ID"));
                    if (a == ret.getInt("ID")) {
                        System.out.println((ret.getString("Name")) + " : " + (rs.getString("Message")));

                        break;

                    }
                }
                System.out.println("ball 1");


            }
            state.close();
            stmt.close();



			/*
			String g="SELECT USERID.Name,CHAT.sender,CHAT.Message\n"
					+ "FROM USERID INNER JOIN CHAT ON USERID.ID=CHAT.sender";




			ResultSet rs = stmt.executeQuery(g);
			System.out.println("ready for the blust");
			while(rs.next())
			{
				System.out.println(rs.getString("Name"));
				System.out.println(rs.getString("Message"));

				System.out.println(rs.getInt(TN ));
				//System.out.println(rs.getInt(4));

			}
			*/


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void printTable()  {
        String tableName = "USERID";
        String TN = "CHAT";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERID");
            System.out.println("ready");
            while (rs.next()) {
               // System.out.println(rs.getString("ID"));

                System.out.println(rs.getString("Name"));
                System.out.println(rs.getString("Password"));
                System.out.println(rs.getString("Email"));
            }
                /*    rs = stmt.executeQuery("SELECT * FROM CHAT");
                    System.out.println("ready");


                    while(rs.next())
                    {
                        System.out.println(rs.getInt(1));
                        System.out.println(rs.getString("Message"));
                        System.out.println(rs.getInt(3));
                        System.out.println(rs.getInt(4));
                    }
                    System.out.println("in the end");


                } catch (SQLException e) {
                    e.printStackTrace();
                }*/

        }
        catch (Exception e)
        {
            System.out.println("Problem in print...");
        }


    }
}
/*void deleteTable()
	{
		try {
			stmt = conn.createStatement();
			stmt.execute("DROP TABLE " + tableName);
			System.out.println("Deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String searchInteraction(String drugA, String drugB)
	{
		try {
			stmt = conn.createStatement();
			String s10 = "SELECT * FROM Drug WHERE name= " + "'" + drugA + "'";
			ResultSet rs = stmt.executeQuery(s10);
			while(rs.next())
			{
				String s1=rs.getString("clash1");
				String s2=rs.getString("clash2");
				String s3=rs.getString("clash3");
				if(s1.equals(drugB))
					return s1+": " + rs.getString("clash1type");
				else if(s2.equals(drugB))
					return s2+": " + rs.getString("clash2type");
				else if(s3.equals(drugB))
					return s3 +": " + rs.getString("clash3type");

				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/