package lab4;

import javax.jms.JMSException;
import javax.jms.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Subscribe {

    private static Connection con = null;
    private  String diverClass = "com.mysql.jdbc.Driver";
    private  String url = "jdbc:mysql://localhost:3306/UserData?autoReconnect=true&useSSL=false";
    private  String name = "root";
    private  String password = "";
    private ConnFactory cf = new ConnFactory();

    public Subscribe(){
        try {
            Class.forName(diverClass);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            this.con = DriverManager.getConnection(this.url, this.name, this.password);
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    public void subscribe_receive(String userName){
        PreparedStatement state;
        try {
            state = con.prepareStatement("select topic from UserTopic where name=?");
            state.setString(1, userName);
            ResultSet result = state.executeQuery();
            Consumer consumer1;
            while(result.next()){
                try {
                    consumer1 = new Consumer(cf.createConnection(), result.getString("topic"), userName);
                    consumer1.start();
                    ArrayList<Message> messages = consumer1.receive();
                    System.out.println(messages);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void subscribe_topic(String userName){
        System.out.println("Please choose which topic to subscribe:");
        System.out.println("1. Movies\n2. Music\n3. TV Series\n4. News");
        int choice;
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        switch (choice){
            case 1:
                System.out.println("Subscribing movies...");
                try {
                    Consumer consumer1 = new Consumer(cf.createConnection(), "Movies", userName);
                    consumer1.start();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Subscribing music...");
                try {
                    Consumer consumer2 = new Consumer(cf.createConnection(), "Music", userName);
                    consumer2.start();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Subscribing TV series...");
                try {
                    Consumer consumer3 = new Consumer(cf.createConnection(), "TV Series", userName);
                    consumer3.start();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            case 4:
                System.out.println("Subscribing news...");
                try {
                    Consumer consumer4 = new Consumer(cf.createConnection(), "News", userName);
                    consumer4.start();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                break;
            default: System.out.println("Invalid input");
        }
    }
}
