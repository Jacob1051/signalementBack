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

/**
 *
 * @author Acer
 */
public class Admin {
    String id;
    String mdp;

    public Admin(){}
    public Admin(String id, String mdp) {
        setId(id);
        setMdp(mdp);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    public Admin checkLogin(Connexion connexion, String id, String mdp) throws Exception {
        Connection c = connexion.getConnexion();
        PreparedStatement ps = c.prepareStatement("select * from Admin where id = ? and mdp = sha1(?)");
        ps.setString(1, id);
        ps.setString(2, mdp);
        ResultSet result = ps.executeQuery();
        Admin admin = null;
        while(result.next()){
            admin = new Admin(result.getString("id"), result.getString("mdp"));
        }
        c.close();
        return admin; 
    }
}
