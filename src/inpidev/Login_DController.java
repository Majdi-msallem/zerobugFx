/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inpidev;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static inpidev.Inpidev.Userconnected;
import inpidev.util.DataSource;
//import org.springframework.security.crypto.bcrypt.BCrypt;


/**
 * FXML Controller class
 *
 * @author majdi
 */
public class Login_DController implements Initializable {

    
        
private Statement stm;
    private PreparedStatement pst;
    private  Connection cnx ;
    private ResultSet rs;
         public static String mail;
    public static String code;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private Button tfloginbutton;
    @FXML
    private Label loginMessage;
    @FXML
    private Button btn_forgot;
    @FXML
    private Hyperlink ss;
    
public Login_DController() {
        cnx = DataSource.getInstance().getCnx();
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //affichage l image de l interface login 
        
     
        }          

    @FXML
    private void loginbtnOnAction(ActionEvent event) {
          String sql = "Select * from User where email  = ? and password  = ?";
        try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, tfemail.getText());
            pst.setString(2, tfpassword.getText());
        
            rs = pst.executeQuery();
            if (rs.next()){
                 Userconnected.setId(rs.getInt(1));
        Userconnected.setEmail(rs.getString(2));
        Userconnected.setRoles(rs.getString(3));
        Userconnected.setPassword(rs.getString(4));
        Userconnected.setCin(rs.getInt(5));
        Userconnected.setPhone(rs.getInt(6));
        Userconnected.setName(rs.getString(7));
        Userconnected.setGenre(rs.getString(8));
        Userconnected.setSuspension(rs.getString(9));
        Userconnected.setRaisonsuspension(rs.getString(10));
         tfloginbutton.getScene().getWindow().hide();
             if(rs.getString(3).equals("ROLE_USER")){
                 Parent root = FXMLLoader.load(getClass().getResource("profile_aff.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
             }
       if(rs.getString(3).equals("ROLE_ADMIN")){
                 Parent root = FXMLLoader.load(getClass().getResource("admincrud.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
             }
        
            } else 
                JOptionPane.showMessageDialog(null, "Invalide email or password");
        } catch (Exception e){
           
        }
        
    }

   

    @FXML
    private void forgot(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("forgetpwd.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(page1);
                mainStage.setScene(scene);
                mainStage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur mdp ob !!!");
            }
    }

    @FXML
    private void gosignup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    
}
