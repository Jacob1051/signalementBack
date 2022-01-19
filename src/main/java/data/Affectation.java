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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Acer
 */
public class Affectation {
    String id;
    String idSignalement;
    String idAdmin;
    String idRegion;
    java.sql.Date dateValidation;

    public Affectation() {
    }

    public Affectation(String id, String idSignalement, String idAdmin, String idRegion, Date dateValidation) {
        setId(id);
        setIdSignalement(idSignalement);
        setIdAdmin(idAdmin);
        setIdRegion(idRegion);
        setDateValidation(dateValidation);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSignalement() {
        return idSignalement;
    }

    public void setIdSignalement(String idSignalement) {
        this.idSignalement = idSignalement;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }
    
    public int insert(Connection c) throws Exception {
        try {
            PreparedStatement ps = c.prepareStatement("insert into Affectation values(null,?,?,?,NOW())");
            ps.setString(1, idSignalement);
            ps.setString(2, idAdmin);
            ps.setString(3, idRegion);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
    
    public int insertFromPage(Object connexion, Map<String, String[]> data, String idAdmin) throws Exception {
        Connection c = ((Connexion)connexion).getConnexion();
        ArrayList<String> keys = new ArrayList<>(data.keySet());
        ArrayList<String[]> datas = new ArrayList<>(data.values());
        String idReport = "";
        Affectation a = null;
        int i = 0;
        try {
            for(i=0; i<keys.size(); i++){
                if(keys.get(i).toString().contains("_report")){
                    idReport = keys.get(i).toString().split("_report")[0];
                    a = new Affectation("", idReport, idAdmin, datas.get(i)[0], null);
                    a.insert(c);
                }
            }
            c.commit();
        } catch (Exception e) {
            c.rollback();
            System.out.println("Error on "+i+"-th loop");
            e.printStackTrace();
        } finally {
            c.close();
        }
        return 0;
    }
}
