package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InscriptionController implements Initializable{
@FXML
private TextField nom;
@FXML
private TextField prenom;
@FXML
private TextField pseudo;
@FXML
private PasswordField pass;
@FXML
private PasswordField passconf;
@FXML
private ProgressIndicator progressindicator;
@FXML
private Label prenomt;
@FXML
private Label nomt;
@FXML
private Label pseudot;
@FXML
private Label passt;
@FXML
private Label passconft;

@FXML
protected void  Close(ActionEvent e2) throws IOException{
	Stage stage=Home.getStage();
	stage.close();
	
}
@FXML	
	public void ValiderAction(ActionEvent e){
	  Task<?>  Charge = null;

	
	  
	String req="insert into connect (pseudo,mdp,nom,prenom)values('"+pseudo.getText()+"','"+pass.getText()+"','"+nom.getText()+"','"+prenom.getText()+"')";
	try {
		
		new Connect().update(req);
		
		
			if(nom.getText().isEmpty()){
				nom.setStyle("-fx-border-color:red");
				nomt.setText("Ce ch	amp est obligatoire");
			}else if(prenom.getText().isEmpty()){
				prenom.setStyle("-fx-border-color:red");
				prenomt.setText("Ce champ est obligatoire");
			}else if(pseudo.getText().isEmpty()){
				pseudo.setStyle("-fx-border-color:red");
				pseudot.setText("Ce champ est obligatoire");
			}else if (pass.getText().isEmpty()){
				pass.setStyle("-fx-border-color:red");
				passt.setText("Ce champ est obligatoire");
			}else if (passconf.getText().isEmpty()){
				passconf.setStyle("-fx-border-color:red");
				passconft.setText("Ce champ est obligatoire");}
			else if((pass.getText()==(passconf.getText()))!=true){
				pass.setStyle("-fx-border-color:red");
				
				passconf.setStyle("-fx-border-color:red");
				passconft.setText("Le mot de passe doit �tre identique");
				}else{
					System.out.println("Connected");
						
	
		
		
			
			  
			progressindicator.setVisible(true);
			
				  Charge = Progres();
			       progressindicator.progressProperty().unbind();
			       progressindicator.progressProperty().bind(Charge.progressProperty());
			        new Thread(Charge).start();
			
			        Charge.setOnSucceeded(f -> {
			        	Parent r;
			        	Scene s2;
			        	Stage stage= Home.getStage();
						try {
							r= FXMLLoader.load(getClass().getResource("login.fxml"));
							s2=new Scene(r);
							
							 stage.setScene(s2);
							  stage.show();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
			        });
		}
			
		}catch (Exception e1){
		System.out.println(e1);
	}
	}

  public Task<?> Progres() {
	    return new Task<Object>() {
	      @Override
	      protected Object call() throws Exception {
	        for (int i = 0; i < 10; i++) {
	          Thread.sleep(200);
	               
	          updateProgress(i + 1, 10);
	         
	        }
	        return true;
	      }
	    };
	}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	progressindicator.setVisible(false);
}
}
