/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inpidev;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static inpidev.Inpidev.Userconnected;
import inpidev.service.UserService;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class Profile_affController implements Initializable {

    @FXML
    private Label lem;
    @FXML
    private Label lpa;
    @FXML
    private Label lci;
    @FXML
    private Label lph;
    @FXML
    private Label lna;
    @FXML
    private Label lge;
    @FXML
    private Button btnmod;
    @FXML
    private Hyperlink log;
    @FXML
    private TextField SearchBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            lem.setText(Userconnected.getEmail());
            lpa.setText(Userconnected.getPassword());
            lci.setText(String.valueOf(Userconnected.getCin()));
            lph.setText(String.valueOf(Userconnected.getPhone()));
            lna.setText(Userconnected.getName());
            lge.setText(Userconnected.getGenre()); 
       System.out.println(Userconnected.getEmail());
    }    

    @FXML
    private void modpro(ActionEvent event) throws IOException {
         FXMLLoader loader =  new FXMLLoader(getClass().getResource("profile.fxml"));
           Parent  root = loader.load();
           lem.getScene().setRoot(root);
    }

    @FXML
    private void logout(ActionEvent event) {
              Userconnected.setId(0);
         Userconnected.setEmail("");
        Userconnected.setRoles("");
        Userconnected.setPassword("");
        Userconnected.setCin(0);
        Userconnected.setPhone(0);
        Userconnected.setName("");
        Userconnected.setGenre("");
        Userconnected.setSuspension("");
        Userconnected.setRaisonsuspension("");

        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("Login_D.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            Login_DController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }
    
    
    
    UserService su = new UserService();
    @FXML
    private void deletc(ActionEvent event) throws SQLException {
          Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer Votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (su.delete(Userconnected)) {
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("Login_D.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                    Login_DController cntr = LOADER.getController();
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {

                }
            }

        }
    }
   
}
    

