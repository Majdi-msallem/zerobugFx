/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inpidev;

 
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import inpidev.Entity.Email;
import inpidev.Entity.User;
import inpidev.service.UserService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;
 

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
             tfgender.getText().isEmpty() || tfphone.getText().isEmpty()    )
{
    JOptionPane.showMessageDialog(null, "verifier vos champs!!");
   
}
           else 
{
    //BCrypt.hashpw(tfpassword.getText(), BCrypt.gensalt())
            User u =  new User(tfemail.getText(),"ROLE_USER", tfpassword.getText(),Integer.parseInt(tfcin.getText()) ,
                    Integer.parseInt(tfphone.getText()),tfname.getText(), tfgender.getText());
              UserService us = new UserService();
                  nb_valider = r.nextInt(10000);
        Email.mailingValider(tfemail.getText(), nb_valider);
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        String txt_CodeConfirmation = jop.showInputDialog(null, "Merci de saisir le code de verification !", "Verification Adresse Mail", JOptionPane.QUESTION_MESSAGE);
         if (Integer.parseInt(txt_CodeConfirmation) == nb_valider) {  
             us.AddUser(u);
             
             sendSMS(u);
//             smsSender s = new smsSender();
         //   s.smsSender("Welcome to FootTunisie : Tournoi  ajouté pour le  à " ,"b");
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
            private void sendSMS(User u)
    {
        try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  https://www.bulksms.com/developer/eapi/submission/character-encoding/
             */
            data += "username=" + URLEncoder.encode("majdimsallem", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("Esprit123", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode("Votre compte a été bien crée \n ", "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=+216"+u.getPhone();

            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            BufferedReader rd;
            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
                wr.write(data);
                wr.flush();
                // Get the response
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    // Print the response output...
                    System.out.println(line);
                }
            }
            rd.close();
        } catch (IOException e) {
            System.out.println("message non envoyé");
        }
//private Boolean testpassword() {
//       
//        
//        if ( tfpassword.getText().trim().length() >= 6) {
//            return true;
//        } else {
//            JOptionPane.showMessageDialog(null, "password 6 cara min");
//
//            return false;
//
//        }
//}
//private Boolean testcin() {
//    String num="";
//    String num1="" ;
//     for (int i = 0; i < tfcin.getText().trim().length(); i++) {
//            char ch = tfcin.getText().charAt(i);
//            if (Character.isDigit(ch)) {
//                num=num+ch;
//             
//            }
//          
//        }
//      System.out.println(num);
//                      
//
//        if (num.trim().length() == 8  && tfcin.getText().trim().length() == 8) {
//                       System.out.println("num 1 est"+num1);
//
//            return true;
//        } else {
//            JOptionPane.showMessageDialog(null, "Cin cotient 8 chiffre");
//     
//            return false;
//
//        }
//        
//        
//}
//            
//        
//
//
//
//private Boolean testphone() {
//    String num="";
//    String num1="" ;
//     for (int i = 0; i < tfphone.getText().trim().length(); i++) {
//            char ch = tfphone.getText().charAt(i);
//            if (Character.isDigit(ch)) {
//                num=num+ch;
//             
//            }
//          
//        }
//      System.out.println(num);
//                      
//
//        if (num.trim().length() == 8  && tfphone.getText().trim().length() == 8) {
//                       System.out.println("num 1 est"+num1);
//
//            return true;
//        } else {
//            JOptionPane.showMessageDialog(null, "Phone cotient 8 chiffre");
//     
//            return false;
//
//        }
    
//    private Boolean testgenre() {
//
//             String     g="";
//        for (int i = 0; i < tfgenre.getText().trim().length(); i++) {
//            char ch = tfgenre.getText().charAt(i);
//            g=g+ch;
//            if (g=="Homme"  ) {
//               
//                            System.out.println( "g est"+g);
//
//            }
//        }
//            s
//        if ( (g=="Homme")  ) {            
//
//            return true;
//        } else {
//           JOptionPane.showMessageDialog(null, "saisire Homme ou Femme");
//
//            return false;
//
//        }
//
//    }

 

}



}





