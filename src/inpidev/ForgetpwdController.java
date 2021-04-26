/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inpidev;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import inpidev.Entity.Email;
import static inpidev.Login_DController.code;
import inpidev.service.UserService;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class ForgetpwdController implements Initializable {
    Email email = new Email();
    public static String c;
    public static String cc;
    @FXML
    private TextField codeclient;
    @FXML
    private Button verify;
    @FXML
    private TextField em;
    @FXML
    private Button envoyer;

    UserService crud =new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//code = email.getPassword();
        //email.sendEmail("melek.hentati@esprit.tn",code);

        // TODO    
    }    
 @FXML
    private void envouyercode(ActionEvent event) throws SQLException {
         cc=em.getText();
        code = email.getPassword();
        if (crud.VerifyUserByEmail(cc)){
            
             email.sendEmail(cc,code);
             
        }else{
            
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You Don't Have Account !!!");
        alert.show();
            
        }
    }
    @FXML
    private void getpass(ActionEvent event) {
         cc=em.getText();
        c=codeclient.getText();
        if(code.equals(c)){
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("changerpwd.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }
         
        }else{    
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Enter Correct Code !!!");
        alert.show();
        }
    }

   
    
}
