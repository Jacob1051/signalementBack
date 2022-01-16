/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author orion
 */
public class Connexion {
    static DataSource ds;
    static Connection con;
    
    public Connexion(){
        try{
            InitialContext initialContext = new InitialContext();
            Context context = (Context)initialContext.lookup("java:comp/env");
            this.ds = (DataSource)context.lookup("connpool");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://db4free.net:3306/signalisation","zeus_db4free","mdpprom13");
            //con=DriverManager.getConnection("jdbc:mysql://localhost/signalisation","root","root");
            //con = ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public DataSource getDataSource(){
        return ds;
    }
}