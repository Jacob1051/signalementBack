/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import connexion.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author Acer
 */
public class Valide {
    String idSignalement;
    String valeur;
    java.sql.Date dateReponse;
    
    public Valide(){}
    public Valide(String id, String v, java.sql.Date rep){
        setIdSignalement(id);
        setValeur(v);
        setDateReponse(rep);
    }
    public String getIdSignalement() {
        return idSignalement;
    }

    public void setIdSignalement(String idSignalement) {
        this.idSignalement = idSignalement;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Date getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(Date dateReponse) {
        this.dateReponse = dateReponse;
    }
    
    public int insert(Connexion co) throws Exception {
        Connection c = co.getConnexion();
        try {
            PreparedStatement ps = c.prepareStatement("insert into Valide values(?,?,NOW())");
            ps.setString(1, idSignalement);
            ps.setString(2, valeur);
            ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
            return 1;
        } finally{
            c.close();
        }
        return 0;
    }
}
