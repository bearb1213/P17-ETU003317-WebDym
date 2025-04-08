package models;

import Util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Credit extends BaseObject {
    private String libelle;
    private double montant;

    // Constructeurs
    public Credit() {
        super();
    }

    public Credit(int id, String libelle, double montant) {
        super.setId(id);
        this.libelle = libelle;
        this.montant = montant;
    }

    // Getters et setters
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    // CRUD Operations
    
    public void save() throws Exception {
        Connection co = null;
        try {
            co = MyCoSQL.GetConnection();
            save(co);
        } catch (Exception e){
            throw e;
        } finally {
            if (co != null) co.close();
        }
    }
    
    public void save(Connection co) throws Exception {
        String sql = "INSERT INTO webDym_credit (libelle, montant) VALUES (?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = co.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.libelle);
            stmt.setDouble(2, this.montant);
            stmt.executeUpdate();
            
        } catch (Exception e){
            throw e;
        } 
        finally {
            if (stmt != null) stmt.close();
        }
    }
    
    public void findById() throws Exception {
        Connection co = null;
        try {
            co = MyCoSQL.GetConnection();
            findById(co);
        } catch (Exception e){
            throw e;
        } finally {
            if (co != null) co.close();
        }
    }
    
    public void findById(Connection co) throws Exception {
        String sql = "SELECT * FROM webDym_credit WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = co.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                this.libelle = rs.getString("libelle");
                this.montant = rs.getDouble("montant");
            } else {
                throw new Exception("Credit non trouvée avec l'ID: " + this.getId());
            }
        } catch (Exception e){
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    }
    
    public List<BaseObject> getAll() throws Exception {
        Connection co = null;
        try {
            co = MyCoSQL.GetConnection();
            return getAll(co);
        } catch (Exception e){
            throw e;
        } finally {
            if (co != null) co.close();
        }
    }
    
    public List<BaseObject> getAll(Connection co) throws Exception {
        List<BaseObject> credits = new ArrayList<>();
        String sql = "SELECT * FROM webDym_credit";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = co.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Credit credit = new Credit(
                    rs.getInt("id"),
                    rs.getString("libelle"),
                    rs.getDouble("montant")
                );
                credits.add(credit);
            }
        } catch (Exception e){
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return credits;
    }
    
    public void update() throws Exception {
        Connection co = null;
        try {
            co = MyCoSQL.GetConnection();
            update(co);
        } catch (Exception e){
            throw e;
        } finally {
            if (co != null) co.close();
        }
    }
    
    public void update(Connection co) throws Exception {
        String sql = "UPDATE webDym_credit SET libelle = ?, montant = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = co.prepareStatement(sql);
            stmt.setString(1, this.libelle);
            stmt.setDouble(2, this.montant);
            stmt.setInt(3, this.getId());
            stmt.executeUpdate();
            
        } catch (Exception e){
            throw e;
        } finally {
            if (stmt != null) stmt.close();
        }
    }
    
    public void delete() throws Exception {
        Connection co = null;
        try {
            co = MyCoSQL.GetConnection();
            delete(co);
        } catch (Exception e){
            throw e;
        } finally {
            if (co != null) co.close();
        }
    }
    
    public void delete(Connection co) throws Exception {
        String sql = "DELETE FROM webDym_credit WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = co.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            stmt.executeUpdate();
            
        } catch (Exception e){
            throw e;
        } finally {
            if (stmt != null) stmt.close();
        }
    }
    public String option(){
        return "<option value=\""+getId()+"\">"+getLibelle()+"</option>";
    }
}