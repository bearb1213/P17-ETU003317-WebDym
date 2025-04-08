package models;

import Util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class Depense extends BaseObject {
    private int idCredit;
    private double montant;
    private Date dateDepense;

    // Constructeurs
    public Depense() {
        super();
    }

    public Depense(int id, int idCredit, double montant, Date dateDepense) {
        super.setId(id);
        this.idCredit = idCredit;
        this.montant = montant;
        this.dateDepense = dateDepense;
    }

    // Getters et setters
    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(Date dateDepense) {
        this.dateDepense = dateDepense;
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
        String sql = "INSERT INTO webDym_depense (id_credit, montant, date_depense) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = co.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, this.idCredit);
            stmt.setDouble(2, this.montant);
            stmt.setDate(3, new java.sql.Date(this.dateDepense.getTime()));
            stmt.executeUpdate();
            
            
        } catch (Exception e){
            throw e;
        } finally {
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
        String sql = "SELECT * FROM webDym_depense WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = co.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                this.idCredit = rs.getInt("id_credit");
                this.montant = rs.getDouble("montant");
                this.dateDepense = rs.getDate("date_depense");
            } else {
                throw new Exception("Dépense non trouvée avec l'ID: " + this.getId());
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
        List<BaseObject> depenses = new ArrayList<>();
        String sql = "SELECT * FROM webDym_depense";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = co.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Depense depense = new Depense(
                    rs.getInt("id"),
                    rs.getInt("id_credit"),
                    rs.getDouble("montant"),
                    rs.getDate("date_depense")
                );
                depenses.add(depense);
            }
        } catch (Exception e){
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return depenses;
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
        String sql = "UPDATE webDym_depense SET id_credit = ?, montant = ?, date_depense = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = co.prepareStatement(sql);
            stmt.setInt(1, this.idCredit);
            stmt.setDouble(2, this.montant);
            stmt.setDate(3, new java.sql.Date(this.dateDepense.getTime()));
            stmt.setInt(4, this.getId());
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
        String sql = "DELETE FROM webDym_depense WHERE id = ?";
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
    
    public void saveDep()throws Exception{
        Connection co =null;
        try {
            co = MyCoSQL.GetConnection();
            co.setAutoCommit(false);
            Credit credit = new Credit();
            credit.setId(idCredit);
            credit.findById(co);

            if (credit.getMontant()<montant){
                throw new Exception("Argent dans credit pas assez desole");
            } 
            
            credit.setMontant(credit.getMontant()-montant);
            credit.update(co);
            save(co);
            co.commit();
        } catch (Exception e) {
            co.rollback();
            throw e;
        } finally {

            if (co!=null) co.close(); 
        }
    }
    public String table(){
        Credit credit = new Credit();
            credit.setId(idCredit);
            try {
                credit.findById();
            } catch (Exception e) {
            }
        return "<tr>\n<td>"+credit.getLibelle()+"</td>\n<td>"+montant+"</td>\n</tr>\n";
    }
}