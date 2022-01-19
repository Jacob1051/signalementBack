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
public class Region {
    String id;
    String nom;

    public Region(){}
    public Region(String id, String nom) {
        setId(id);
        setNom(nom);
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public ArrayList<Region> getRegionFromDb(Object connexion) throws Exception {
        Connection c = ((Connexion)connexion).getConnexion();
        PreparedStatement ps = c.prepareStatement("select * from Region");
        ResultSet result = ps.executeQuery();
        ArrayList<Region> regions = new ArrayList<Region>();
        Region s = null;
        while(result.next()){
            s = new Region(result.getInt("id")+"", result.getString("nom"));
            regions.add(s);
        }
        c.close();
        return regions; 
    }
}
