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
public class Signalement {
    String id;
    String idUtilisateur;
    String idCategory;
    String description;
    java.sql.Date dateSignalement;
    float latitude;
    float longitude;
    String nomCategory;

    public String getNomCategory() {
        return nomCategory;
    }

    public Signalement() {
    }
    
    public Signalement(String i, String idU, String idC, String desc, java.sql.Date dateS, float lat, float longitudes) {
        setId(i);
        setIdUtilisateur(idU);
        setIdCategory(idC);
        setDescription(desc);
        setDateSignalement(dateS);
        setLatitude(lat);
        setLongitude(longitudes);
    }
    
    public Signalement(String i, String idU, String idC, String desc, java.sql.Date dateS, float lat, float longitudes, String cat) {
        setId(i);
        setIdUtilisateur(idU);
        setIdCategory(idC);
        setDescription(desc);
        setDateSignalement(dateS);
        setLatitude(lat);
        setLongitude(longitudes);
        nomCategory = cat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(java.sql.Date dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
        //select * from Signalement where id not in(select idSignalement from Affectation);
    }
    
    public ArrayList<Signalement> getSignalementNonRepondu(Object connexion, String pagination, String nbItems) throws Exception {
        Connection c = ((Connexion)connexion).getDataSource().getConnection();
        PreparedStatement ps = c.prepareStatement("select *, Category.nom as nomC from Signalement join Category on Category.id = Signalement.idCategory where Signalement.id not in (select id from vSignalementRepondu) LIMIT ?,?");
        ps.setInt(1, Integer.parseInt(pagination));
        ps.setInt(2, Integer.parseInt(nbItems));
        ResultSet result = ps.executeQuery();
        ArrayList<Signalement> signalement = new ArrayList<Signalement>();
        Signalement s = null;
        while(result.next()){
            s = new Signalement(result.getString("id"),result.getString("idUtilisateur"),result.getString("idCategory"),result.getString("description"),
                                result.getDate("dateSignalement"),result.getFloat("latitude"),result.getFloat("longitude"), result.getString("nomC"));
            signalement.add(s);
        }
        c.close();
        return signalement; 
    }
}
