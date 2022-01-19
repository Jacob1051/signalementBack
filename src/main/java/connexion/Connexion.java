/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.*;
/**
 *
 * @author orion
 */
public class Connexion {
    //static DataSource ds;
    static Connection con;
    private BasicDataSource connectionPool;

    public Connexion(){
        try{
            //String dbUrl = "jdbc:mysql://db4free.net:3306/signalisation?serverTimezone=UTC";
            String dbUrl = "jdbc:mysql://localhost:3306/signalisation?serverTimezone=UTC";
            connectionPool = new BasicDataSource();
            //connectionPool.setUsername("zeus_db4free");
            connectionPool.setUsername("root");
            //connectionPool.setPassword("mdpprom13");
            connectionPool.setPassword("root");
            connectionPool.setDriverClassName("com.mysql.cj.jdbc.Driver");
            connectionPool.setUrl(dbUrl);
            connectionPool.setInitialSize(5);
            
            //Properties props = new Properties();
            //props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.camel.util.jndi.CamelInitialContextFactory");
            //props.setProperty(Context.PROVIDER_URL,"tcp://localhost:8080");
            //InitialContext initialContext = new InitialContext(props);
            //InitialContext initialContext = new InitialContext();
            //Context context = (Context) initialContext.lookup("java:comp/env");
            //this.ds = (DataSource)initialContext.lookup("java:comp/env/connpool");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://db4free.net:3306/signalisation","zeus_db4free","mdpprom13");
            //con=DriverManager.getConnection("jdbc:mysql://localhost/signalisation","root","root");
            //con = ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public Connection getConnexion() throws Exception {
        Connection c = connectionPool.getConnection();
        c.setAutoCommit(false);
        return c;
    }
}