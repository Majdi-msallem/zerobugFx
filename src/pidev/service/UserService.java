/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import pidev.Entity.User;
import pidev.pidev.util.DataSource;

/**
 *
 * @author majdi
 */
public class UserService  {
 Connection cnx = DataSource.getInstance().getCnx();

 
    public void AddUser(User u) {
        System.out.println("fghjk;:grs;,fhdskljhgbvjdkfml,;b,nvdf,c")   ; 
         try {
            String requete = "INSERT INTO User (email,roles,password,cin,phone,name,genre) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, u.getEmail());
            pst.setString(2, u.getRoles());
            pst.setString(3, u.getPassword());
            pst.setInt(4, u.getCin());
            pst.setInt(5, u.getPhone());
            pst.setString(6, u.getName());
            pst.setString(7, u.getGenre());
            pst.executeUpdate();
            System.out.println("User ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          }

    


    public List<User> AffUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updatemdp(String email, String mdp) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "UPDATE User SET password= '"+mdp+"' WHERE email='"+email+"'";
        stm.executeUpdate(query); 
        
    }
    public void modifier(User u) {
         try {

            String requete = "UPDATE user SET email=?,roles=?,password=? ,cin=?,phone=?,name=?,genre=?,suspension=?,raisonsuspension=?WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1, u.getEmail());
            pst.setString(2, u.getRoles());
            pst.setString(3,   u.getPassword());
            pst.setInt(4, u.getCin());
            pst.setInt(5, u.getPhone());
            pst.setString(6, u.getName());
            pst.setString(7, u.getGenre());
            pst.setString(8, u.getSuspension());
            pst.setString(9, u.getRaisonsuspension());
            pst.setInt(10, u.getId());
            
            pst.executeUpdate();
            System.out.println("suggestion modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
      
    public void supprimer( int ID_User) {
       try {
            String requete = "DELETE FROM User WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, ID_User);
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public Boolean VerifyUserByEmail(String email) throws SQLException {
		User u = new User();
		//Boolean found = false;  Statement stm = conn.createStatement();
            
                 Statement stm = cnx.createStatement();

            
		
		String query = "select * from User where email = '" + email + "'";
		try {
			 ResultSet rst = stm.executeQuery(query);
			if (rst.next()){ 
			return true;
                        }
		} catch (SQLException ex) {
                         System.out.println("erreur" + ex.getMessage());
                }
       return false;
	};
    
           public boolean updateByemail(String email, String mdp) throws SQLException {
        String qry = "UPDATE User SET  password = '"+mdp+"' WHERE email = '"+email+"'";
         Statement stm = cnx.createStatement();
        try {
            if (stm.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
                                   

        }
        return false;
    }
           
           //supprimer user 
    public boolean delete(User t) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("DELETE FROM `last`.`User` where id =? AND email =?");
        pre.setInt(1, t.getId());
        pre.setString(2, t.getEmail());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A User was deleted successfully!");
        }
        return true;
    }
    
      public int countMen() throws SQLException{
         int count=0;
       Statement stmt3 = cnx.createStatement();
ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM User WHERE `genre` = \"Homme\"");
    while(rs3.next()){
    count = rs3.getInt(1);
    }
            return count ; 
     }
public int countWomen() throws SQLException{
         int count=0;
       Statement stmt3 = cnx.createStatement();
ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM User WHERE `genre` = \"Femme\"");
    while(rs3.next()){
    count = rs3.getInt(1);
    }
            return count ; 
     }
}

    
    
    

