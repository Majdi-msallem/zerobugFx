/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static pidev.NewFXMain.Userconnected;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pidev.Entity.User;
import pidev.service.UserService;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class ProfileController implements Initializable {

    @FXML
    private Button btnlogout;
    @FXML
    private TextField tfem;
    @FXML
    private TextField tfpa;
    @FXML
    private TextField tfci;
    @FXML
    private TextField tfph;
    @FXML
    private TextField tfna;
    @FXML
    private TextField tfge;
    @FXML
    private Button btnmodifier;
    UserService su = new UserService();
    @FXML
    private Button btnsupprimer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            tfem.setText(Userconnected.getEmail());
            tfpa.setText(Userconnected.getPassword());
            tfci.setText(String.valueOf(Userconnected.getCin()));
            tfph.setText(String.valueOf(Userconnected.getPhone()));
            tfna.setText(Userconnected.getName());
            tfge.setText(Userconnected.getGenre());
    }    

    @FXML
    private void logout(ActionEvent event) throws IOException, Exception  {
        
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

    @FXML
    private void modifierprofile(ActionEvent event) {
        //
    }

    @FXML
    private void sup(ActionEvent event) throws SQLException {
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
