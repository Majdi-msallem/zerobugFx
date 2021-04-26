/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inpidev;

import inpidev.Entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author majdi
 */
public class Inpidev extends Application {
            static User Userconnected = new User();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
//        System.out.print("test");
//        SignupController cnt = new SignupController();
//         String password = "pwd1";
//  
//   String ch = ( BCrypt.hashpw(password, BCrypt.gensalt()));
//   
//   System.out.println(ch );

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
