/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pidev.Entity.User;
import pidev.pidev.util.DataSource;
import pidev.service.UserService;

/**
 *
 * @author majdi
 */
public class PIDEV extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("Login_D.fxml"));
       Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 //Connection cnx = DataSource.getInstance().getCnx();
 
 /*
 User  u = new User("email@gmmmaiil","Role_User","fghjkl",11111111,22222222,"mm","ghj");
         UserService us = new UserService();
        us.AddUser(u);
    }
   */
    
}
}
