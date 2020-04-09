package application;

import java.io.IOException; 
import java.net.URL;
import java.sql.ResultSet;

import java.util.ResourceBundle;


import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

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
String pseudotest;
@FXML
protected void  Close(ActionEvent e2) throws IOException{
	Stage stage=Home.getStage();
	stage.close();
	
}
@FXML	
	public void ValiderAction(ActionEvent e){
	  Task<?>  Charge = null;

	
	  
	String req="insert into connect (pseudo,mdp,nom,prenom)values('"+pseudo.getText()+"','"+pass.getText()+"','"+nom.getText()+"','"+prenom.getText()+"')";
String req1="select pseudo from connect where pseudo='"+pseudo.getText()+"' ";
	try {
		
	new Connect().connect(req1);	
	ResultSet rs=new Connect().getRs();
	while (rs.next()) {
		 pseudotest=rs.getString("pseudo");
		System.out.println(pseudotest);
	}

			if(nom.getText().isEmpty()){
				nom.setStyle("-fx-border-color:red");
				nomt.setText("Ce champ est obligatoire");
			}else if(prenom.getText().isEmpty()){
				nom.setStyle("-fx-border-color:green");
				nomt.setText("");
				prenom.setStyle("-fx-border-color:red");
				prenomt.setText("Ce champ est obligatoire");
			}else if((pseudo.getText().isEmpty())||((pseudotest.equals(pseudo.getText()))!=false)){
				nom.setStyle("-fx-border-color:green");
				nomt.setText("");
				prenom.setStyle("-fx-border-color:green");
				prenomt.setText("");
				pseudo.setStyle("-fx-border-color:red");
				pseudot.setText("Ce pseudo existe d�ja");
	
			}
				else if (pass.getText().isEmpty()){
				nom.setStyle("-fx-border-color:green");
				nomt.setText("");
				prenom.setStyle("-fx-border-color:green");
				prenomt.setText("");
				pseudo.setStyle("-fx-border-color:green");
				pseudot.setText("");
				pass.setStyle("-fx-border-color:red");
				passt.setText("Ce champ est obligatoire");
			}else if (passconf.getText().isEmpty()){
				nom.setStyle("-fx-border-color:green");
				nomt.setText("");
				prenom.setStyle("-fx-border-color:green");
				prenomt.setText("");
				pseudo.setStyle("-fx-border-color:green");
				pseudot.setText("");
				pass.setStyle("-fx-border-color:green");
				passt.setText("");
				passconf.setStyle("-fx-border-color:red");
				passconft.setText("Ce champ est obligatoire");
				}
			else if((pass.getText().equals(passconf.getText()))!=true){
				nom.setStyle("-fx-border-color:green");
				nomt.setText("");
				prenom.setStyle("-fx-border-color:green");
				prenomt.setText("");
				pseudo.setStyle("-fx-border-color:green");
				pseudot.setText("");
				pass.setStyle("-fx-border-color:green");
				passt.setText("");
				passconf.setStyle("-fx-border-color:green");
				passconft.setText("");
				pass.setStyle("-fx-border-color:red");
		passt.setText("Mot de passe non identique");
			}
		else {
					nom.setStyle("-fx-border-color:green");
					nomt.setText("");
					prenom.setStyle("-fx-border-color:green");
					prenomt.setText("");
					pseudo.setStyle("-fx-border-color:green");
					pseudot.setText("");
					pass.setStyle("-fx-border-color:green");
					passt.setText("");
					passconf.setStyle("-fx-border-color:green");
					passconft.setText("");
					pass.setStyle("-fx-border-color:green");
					passt.setText("");
					System.out.println("Connected");
						
					
		
		
			
			  
			progressindicator.setVisible(true);
			
				  Charge = Progres();
			       progressindicator.progressProperty().unbind();
			       progressindicator.progressProperty().bind(Charge.progressProperty());
			        new Thread(Charge).start();
			       
			        Charge.setOnSucceeded(f -> {
			        	 new Connect().update(req);
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
  @FXML	
	public void Retour(ActionEvent e1) throws IOException{
	  Stage s=Home.getStage();
	  Parent p=FXMLLoader.load(getClass().getResource("login.fxml"));
	  Scene sc=new Scene(p);
	  s.setScene(sc);
	  s.show();
  }

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	progressindicator.setVisible(false);
}
}
