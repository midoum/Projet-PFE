package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SettingsController {
@FXML
private Button Valid;
@FXML
private Label usert;
@FXML
private Label oldpasst;
@FXML
private Label newpasst;
@FXML
private TextField user;
@FXML
private PasswordField newpass;
@FXML
private PasswordField oldpass;
@FXML
protected void Valider(ActionEvent evt) throws IOException{
	 Connection conn=null;
		String url="jdbc:mysql://localhost/javafx?useUnicode=true"+
		"&useJDBCCompliantTimezoneShift=true"+
		"&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String u="root";
		String m="0000";
		String req="Update connect set mdp='"+newpass.getText()+"' where pseudo='"+HomeController.getUser()+"'";
	
		try{
			conn=DriverManager.getConnection(url,u,m);
			if(conn!=null){
				if(user.getText().isEmpty()){
					user.setStyle("-fx-border-color:red");
					usert.setText("Ce champ est obligatoire");
				}else if(oldpass.getText().isEmpty()){
					oldpass.setStyle("-fx-border-color:red");
					oldpasst.setText("Ce champ est obligatoire");
				}else if(newpass.getText().isEmpty()){
					newpass.setStyle("-fx-border-color:red");
					newpasst.setText("Ce champ est obligatoire");
				
					}else if ((user.getText().equals(HomeController.getUser()))!=true){
						user.setStyle("-fx-border-color:red");
						usert.setText("Ce n'est pas votre Pseudo");
					}else if ((oldpass.getText().equals(HomeController.getPass()))!=true){
						oldpass.setStyle("-fx-border-color:red");
						oldpasst.setText("Ce n'est pas votre ancien mot de passe");
					}else if ((newpass.getText().equals(oldpass.getText()))!=false){
						newpass.setStyle("-fx-border-color:red");
						newpasst.setText("Choisissez un nouveau mot de passe");
					}else{
						System.out.println("Connected");
							Statement st=conn.createStatement();
							st.executeUpdate(req);
							Stage stage=Home.getStage();
							Parent r=FXMLLoader.load(getClass().getResource("login.fxml"));
							Scene s=new Scene(r);
							stage.setScene(s);
							
		}
				}
			}
		catch(Exception e){
			System.out.println(e);
			
		
		}
			

}
}
