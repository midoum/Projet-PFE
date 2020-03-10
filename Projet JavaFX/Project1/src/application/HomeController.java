package application;



import java.io.IOException;  
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class HomeController implements Initializable{
		static String uu="";
		static String pp;

	  @FXML
	    private Button connect;
	  @FXML
	    private Button inscrire;
	  @FXML
	    private Label lpseudo;
	  @FXML
	    private Label lpass;
	  @FXML
	    private Label p;
	  @FXML
	    private ProgressIndicator progressindicator;
	  @FXML
	  	private  TextField user;
	  @FXML
	  	private  PasswordField pass;
	  @FXML
	  protected void  Close(ActionEvent e2) throws IOException{
	  	Stage stage=Home.getStage();
	  	stage.close();
	  	
	  }
	  @FXML 
	 
	  protected void  ButtonConnect(ActionEvent event) throws IOException {
		  Task<?>  Charge = null;
		 
		
	
		  Node node = (Node) event.getSource();
		
		  Stage stage = (Stage) node.getScene().getWindow();
		  
		String req="select * from connect where pseudo='"+user.getText()+"'";
		try {
		
			new Connect().connect(req);
			ResultSet rs =new Connect().getRs();
			if(user.getText().isEmpty()){
				lpseudo.setText("Remplir votre pseudo");
				user.setStyle("-fx-border-color:red");
			
			} else if(pass.getText().isEmpty()){
				lpass.setText("Remplir votre mot de passe");
				pass.setStyle("-fx-border-color:red");
			}
			else if(rs.next()){
				if((rs.getString("mdp").equals(pass.getText())!=true)){
					pass.setStyle("-fx-border-color:red");
					lpass.setText("mauvais mot de passe");
				}else if((rs.getString("mdp").equals(pass.getText())!=false)){
				
				
				 
				
				progressindicator.setVisible(true);
				
					  Charge = Progres();
				       progressindicator.progressProperty().unbind();
				       progressindicator.progressProperty().bind(Charge.progressProperty());
				        new Thread(Charge).start();
				
				        Charge.setOnSucceeded(e -> {
				        	Scene s2;
							try {
								s2 = new Scene(FXMLLoader.load(getClass().getResource("Bonjour.fxml")));
								 stage.setScene(s2);
								  stage.show();
								  uu=rs.getString("pseudo");
								  pp=rs.getString("mdp");								 
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							 
				        });
						 
				
				 
			}}
			else {
				Scene s3;
				try {
					s3 = new Scene(FXMLLoader.load(getClass().getResource("Alert.fxml")));
					 stage.setScene(s3);
					  stage.show();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}catch (Exception e){
			System.out.println(e);
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
		 
	  protected void  ButtonInscrire(ActionEvent event1) throws IOException {
		  Stage stage=Home.getStage();
		  Parent p=FXMLLoader.load(getClass().getResource("Inscription.fxml"));
		  Scene s=new Scene(p);
		  stage.setScene(s);
		 
	  }
	
	  public static String getUser() {
		   
			
		  		return uu;
		}
	  public static String getPass() {
		   
			return pp;
		}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progressindicator.setVisible(false);
	}
}
