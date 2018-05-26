package lab4;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;  
  
import javax.jms.Connection;  
import javax.jms.JMSException;  
import javax.jms.Message;  
import javax.jms.MessageConsumer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
  
public class Consumer {
    private String name;  
    private String dest;  
    private Connection conn;  
    private MessageConsumer consumer;  
    private Session session;      
    private Executor executor = Executors.newFixedThreadPool(10);  
      
    public Consumer(Connection conn, String dest, String name){
        this.conn = conn;  
        this.dest = dest;  
        this.name = name;  
    }  
      
    public void start() throws JMSException{  
        //使用Consumer之前，必须调用conn的start方法建立连接。  
        conn.start();  
          
        session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);        
        consumer = session.createConsumer(session.createTopic(dest));  
    }  
  
    public ArrayList<Message> receive() {
        final ArrayList<Message> messages = new ArrayList();
        executor.execute(new Runnable() {  
            @Override  
            public void run() {  
                while (true) {  
                    try {  
                        Message msg = consumer.receive();  
                        if (msg instanceof TextMessage) {  
                            System.out.println(name + " receive message {" + ((TextMessage)msg).getText() + "}");  
                        } else {
                            System.out.println("msg: " + msg);
                        }
                        messages.add(msg);
                    } catch (JMSException e) {  
                        e.printStackTrace();  
                    }
                }  
            }  
        });
        return messages;
    }
}  
