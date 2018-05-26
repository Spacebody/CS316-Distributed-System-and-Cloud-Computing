package lab4;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class ConnFactory {  
    
    private ConnectionFactory factory;  
      
    public ConnFactory(){
            this.factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
    }  
      
    public Connection createConnection() throws JMSException{  
        return factory.createConnection();  
    }

}  
