package lab4;

import javax.jms.JMSException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Publish {
    private static Connection con = null;
    private  String diverClass = "com.mysql.jdbc.Driver";
    private  String url = "jdbc:mysql://localhost:3306/UserData?autoReconnect=true&useSSL=false";
    private  String name = "root";
    private  String password = "";
    private  ConnFactory cf = new ConnFactory();
    public Publish() {
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

    public void publish_context(String userName){
        PreparedStatement state;
        System.out.println("The topics of "+userName);
        ArrayList<String> userTopics = new ArrayList();
        try {
            state = con.prepareStatement("select distinct(topics) from Topics");
            ResultSet result = state.executeQuery();
            int i = 1;
            while(result.next()){
                userTopics.add(result.getString("topics"));
                System.out.println(i++ + ". " + result.getString("topics"));
            }
            System.out.println("Which topic do you want to select:");
            int choice;
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            if(choice  > userTopics.size()){
                System.out.println("Invalid choice.");
                return;
            }
            else{
                System.out.println("What content do you want to publish:");
                String content = input.next();
                try {
                    Producer producer1 = new Producer(cf.createConnection(), userTopics.get(choice-1), userName);
                    producer1.start();
                    producer1.send(content);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
