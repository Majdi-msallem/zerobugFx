/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inpidev;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import inpidev.Entity.User;
import static inpidev.Inpidev.Userconnected;
import inpidev.service.UserService;
import inpidev.util.DataSource;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class AdmincrudController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableView<User> tuser;
    @FXML
    private TableColumn<User, String> em;
    @FXML
    private TableColumn<User, String> ro;
    @FXML
    private TableColumn<User, String> pa;
    @FXML
    private TableColumn<User, Integer> ci;
    @FXML
    private TableColumn<User, Integer> ph;
    @FXML
    private TableColumn<User, String> na;
    @FXML
    private TableColumn<User, String> ge;
    @FXML
    private TableColumn<User, String> su;
    @FXML
    private TableColumn<User, String> rs;
    @FXML
    private TextField tfem;
    @FXML
    private TextField tfna;
    @FXML
    private TextField tfge;
    @FXML
    private TextField tfpa;
    @FXML
    private TextField tfci;
    @FXML
    private TextField tfph;
    @FXML
    private TextField tfro;

    Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TextField idm;
    @FXML
    private TextField tfsu;
    @FXML
    private TextField tfrs;
    @FXML
    private TextField tfrch;
    @FXML
    private Button btnr;
    @FXML
    private Button tfrf;
    @FXML
    private Button stat;
    @FXML
    private Button btnlogout;
    @FXML
    private AnchorPane displayArea;
    private UserService sus = new UserService();
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
       
      ObservableList<User> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select  * from User ";
           
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
             list.add(new User( rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        tuser.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        em.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        ro.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
        pa.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        ci.setCellValueFactory(new PropertyValueFactory<User, Integer>("cin"));
        ph.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone"));
        na.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        ge.setCellValueFactory(new PropertyValueFactory<User, String>("genre"));
        su.setCellValueFactory(new PropertyValueFactory<User, String>("suspension"));
        rs.setCellValueFactory(new PropertyValueFactory<User, String>("raisonsuspension"));

        
    
                }    

    @FXML
    private void ajouterOnAction(ActionEvent event) throws IOException {
          if (tfem.getText().isEmpty() || tfro.getText().isEmpty() || tfpa.getText().isEmpty() ||tfna.getText().isEmpty() || tfci.getText().isEmpty() ||
             tfge.getText().isEmpty() || tfph.getText().isEmpty()       )
{
    JOptionPane.showMessageDialog(null, "verifier vos champs!!");
   
}
           else 
{
            User u =  new User(tfem.getText(),tfro.getText(),tfpa.getText(),Integer.parseInt(tfci.getText()) ,
                    Integer.parseInt(tfph.getText()),tfna.getText(), tfge.getText());
              UserService us = new UserService();
              us.AddUser(u);
          
          //  JOptionPane.showMessageDialog(null, "inscription avec succees!!");
             //FXMLLoader loader =  new FXMLLoader(getClass().getResource("admincrud.fxml"));
           //Parent  root = loader.load();
          // tfem.getScene().setRoot(root);
      
      
}
    }
    
 UserService s1 = new UserService();
    @FXML
    private void modifierOnAction(ActionEvent event) {
 int id_user= parseInt(idm.getText());
        String email=tfem.getText();
        String roles = tfro.getText();
        String password = tfpa.getText();
        int cin =  parseInt(tfci.getText());
        int phone= parseInt(tfph.getText());
        String name = tfna.getText();
                String genre = tfge.getText();
        String suspension = tfsu.getText();
        String raisonsuspension = tfrs.getText();

        User u1=new User(id_user, email, roles,password, cin, phone,name,genre,suspension,raisonsuspension);
       
        s1.modifier(u1);             }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
        s1.supprimer( parseInt(idm.getText()));
    }

   

    @FXML
    private void getselected(MouseEvent event) {
         User u = tuser.getSelectionModel().getSelectedItem();
        int index = tuser.getSelectionModel().getSelectedIndex();
        if (u == null) {
            return;
        }
        if (index <= -1) {

            return;

        } 
       idm.setText(id.getCellData(index).toString());
        tfna.setText(na.getCellData(index).toString());
        tfem.setText(em.getCellData(index).toString());
        tfpa.setText(pa.getCellData(index).toString());
        tfci.setText(ci.getCellData(index).toString());
        tfph.setText(ph.getCellData(index).toString());
        tfge.setText(ge.getCellData(index).toString());
         tfro.setText(ro.getCellData(index).toString());
          tfsu.setText(su.getCellData(index).toString());
           tfrs.setText(rs.getCellData(index).toString());
    }

 

    private void see(MouseEvent event) {
       }

    @FXML
    private void rchOnAction(ActionEvent event) {
       ObservableList<User> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select  * from User   where email like '%"+tfrch.getText()+"%' or genre like '%"+tfrch.getText()+"%' or roles like '%"+tfrch.getText()+"%'  " ;
           
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
             list.add(new User( rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        tuser.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        em.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        ro.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
        pa.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        ci.setCellValueFactory(new PropertyValueFactory<User, Integer>("cin"));
        ph.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone"));
        na.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        ge.setCellValueFactory(new PropertyValueFactory<User, String>("genre"));
         su.setCellValueFactory(new PropertyValueFactory<User, String>("suspension"));
        rs.setCellValueFactory(new PropertyValueFactory<User, String>("raisonsuspension"));
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
         ObservableList<User> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select  * from User ";
           
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
             list.add(new User( rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        tuser.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        em.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        ro.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
        pa.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        ci.setCellValueFactory(new PropertyValueFactory<User, Integer>("cin"));
        ph.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone"));
        na.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        ge.setCellValueFactory(new PropertyValueFactory<User, String>("genre"));
         su.setCellValueFactory(new PropertyValueFactory<User, String>("suspension"));
        rs.setCellValueFactory(new PropertyValueFactory<User, String>("raisonsuspension"));
    
    }

    @FXML
    private void tri(ActionEvent event) {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select  * from User Order by suspension  ";
           
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
             list.add(new User( rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        tuser.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        em.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        ro.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
        pa.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        ci.setCellValueFactory(new PropertyValueFactory<User, Integer>("cin"));
        ph.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone"));
        na.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        ge.setCellValueFactory(new PropertyValueFactory<User, String>("genre"));
         su.setCellValueFactory(new PropertyValueFactory<User, String>("suspension"));
        rs.setCellValueFactory(new PropertyValueFactory<User, String>("raisonsuspension"));
    
    }

    @FXML
    private void gotostat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Charts.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
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
//       @FXML
//     private void stat(ActionEvent event) throws IOException, SQLException {
//        final CategoryAxis xAxis = new CategoryAxis();
//        
//        final NumberAxis yAxis = new NumberAxis();
//        
//        final BarChart<String,Number> bc = 
//            new BarChart<String,Number>(xAxis,yAxis);
//        
//        
//         bc.setTitle("Nombre de femme et d'homme");
//        xAxis.setLabel("Genre");       
//        yAxis.setLabel("Total");
//        xAxis.setAnimated(true);
//        ResultSet rs = sus.totalGenre();
//        
//        while(rs.next()){
//            String genre = rs.getString("genre");
//            int total = rs.getInt("total");
//            
//            XYChart.Series series = new XYChart.Series();
//            //series.setName(nom+" "+prenom);
//            series.getData().add(new XYChart.Data(genre,total));
//            bc.getData().add(series);
//            
//            
//        }
//
//        
//        displayArea.getChildren().clear();
//        displayArea.getChildren().add(bc);
//    }
//
// 
    
}
