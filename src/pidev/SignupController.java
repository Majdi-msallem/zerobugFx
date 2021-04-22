/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import pidev.Entity.Email;
import pidev.Entity.User;
import static pidev.NewFXMain.Userconnected;
import pidev.service.UserService;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class SignupController implements Initializable {

    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfcin;
    @FXML
    private Label tfgenre;
    @FXML
    private TextField tfphone;
    @FXML
    private Button tfsignup;
    @FXML
    private TextField tfgender;
String erreur;
    @FXML
    private ImageView checkemail;
    @FXML
    private ImageView checkname;
    @FXML
    private ImageView checkgenre;
    @FXML
    private ImageView checkpassword;
    @FXML
    private ImageView checkcin;
    @FXML
    private ImageView checkphone;
    
    //new 
    Random r = new Random();
    static int nb_valider;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signupbtnOnAction(ActionEvent event) throws IOException, Exception {
       
           if (tfemail.getText().isEmpty() || tfpassword.getText().isEmpty() ||tfname.getText().isEmpty() || tfcin.getText().isEmpty() ||
             tfgender.getText().isEmpty() || tfphone.getText().isEmpty()       )
{
    JOptionPane.showMessageDialog(null, "verifier vos champs!!");
   
}
           else 
{
    
            User u =  new User(tfemail.getText(),"ROLE_USER",tfpassword.getText(),Integer.parseInt(tfcin.getText()) ,
                    Integer.parseInt(tfphone.getText()),tfname.getText(), tfgender.getText());
              UserService us = new UserService();
                  nb_valider = r.nextInt(10000);
        Email.mailingValider(tfemail.getText(), nb_valider);
        
        
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        String txt_CodeConfirmation = jop.showInputDialog(null, "Merci de saisir le code de verification !", "Verification Adresse Mail", JOptionPane.QUESTION_MESSAGE);
         if (Integer.parseInt(txt_CodeConfirmation) == nb_valider) { 
             us.AddUser(u);
                FXMLLoader loader =  new FXMLLoader(getClass().getResource("Login_D.fxml"));
           Parent  root = loader.load();
           tfemail.getScene().setRoot(root);
      
         }else {
              Alert alert = new Alert(Alert.AlertType.ERROR, "Code incorrect", ButtonType.CLOSE);
                alert.show();
            }
             
          
          //  JOptionPane.showMessageDialog(null, "inscription avec succees!!");
         
      
      
}
          
    }
//    private Boolean testGenre() {
//       
//        for (int i = 1; i < tfgenre.getText().trim().length(); i++) {
//          String   ch = tfgenre.getText();
//            if (ch!="Homme" || ch!="Femme ") {
//                System.out.println("verifier votre champs ");
//              
//            }
//                      
//        }
//  return false;
//   
//        }

//    private Boolean testSaisie() {
//        erreur = "";
//        
//        if (!testGenre()) {
//            erreur = erreur + ("Saisir Homme ou Femme \n");
//        }
//        return   testGenre()  ;
//    }
}
    

