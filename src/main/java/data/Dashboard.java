/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class Dashboard {
    int nbUsers = 0;
    
    public Dashboard() {
    }
    
    public Dashboard(Object connection) throws Exception {
        Connection c = ((Connexion)connection).getConnexion();
        getNbUsersFromDB(c);
        c.close();
    }
    
    public void getNbUsersFromDB(Connection c) throws Exception {
        PreparedStatement ps = c.prepareStatement("select COUNT(*) as nb from Utilisateur");
        ResultSet result = ps.executeQuery();
        while(result.next()){
            System.out.println(result.getInt("nb"));
            nbUsers = result.getInt("nb");
        }
        result.close();
        ps.close();
    }
    
    public int getNbUsers(){
        return nbUsers;
    }
}
