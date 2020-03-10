package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class BonjourController implements Initializable{


@FXML
private Button Acceuil;
@FXML
private Button Deco;
@FXML
private Button Settings;
@FXML
private Button ControllButton;
@FXML 
private BorderPane Bp;

@FXML
protected void  Click(ActionEvent event) throws IOException{
	FadeTransition f=new FadeTransition();
	f.setDuration(Duration.millis(1000));
	
	Pane s=FXMLLoader.load(getClass().getResource("Chart.fxml"));
	f.setNode(s);
	f.setFromValue(0);
	f.setToValue(1);
	f.play();
	 Bp.setCenter(s);
	 Acceuil.setStyle("-fx-background-color: orange;-fx-text-fill:white;-fx-border-color:orange");	
	 Settings.setStyle("-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white");
	 ControllButton.setStyle("-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white");
	
}
@FXML
protected void  Changersettings(ActionEvent e1) throws IOException{
	Pane s2=FXMLLoader.load(getClass().getResource("Settings.fxml"));
	FadeTransition f=new FadeTransition();
	f.setDuration(Duration.millis(1000));
	f.setNode(s2);
	f.setFromValue(0);
	f.setToValue(1);
	f.play();
	Bp.setCenter(s2);
	 Acceuil.setStyle("-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white");
	 Settings.setStyle("-fx-background-color: orange;-fx-text-fill:white;-fx-border-color:orange");
	 ControllButton.setStyle("-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white");
}
@FXML
protected void  Controll(ActionEvent e3) throws IOException{
	Pane s0=FXMLLoader.load(getClass().getResource("Controll.fxml"));
	FadeTransition f=new FadeTransition();
	f.setDuration(Duration.millis(1000));
	f.setNode(s0);
	f.setFromValue(0);
	f.setToValue(1);
	f.play();
	Bp.setCenter(s0);
	 Acceuil.setStyle("-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white");
	 Settings.setStyle("-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white");
	 ControllButton.setStyle("-fx-background-color: orange;-fx-text-fill:white;-fx-border-color:orange");
	
}
@FXML
protected void  Close(ActionEvent e2) throws IOException{
	Stage stage=Home.getStage();
	stage.close();
	
}
@FXML
protected void  Deconnexion(ActionEvent e20) throws IOException{
	Stage stage=Home.getStage();
	Parent r=FXMLLoader.load(getClass().getResource("login.fxml"));
	Scene s=new Scene(r);
	stage.setScene(s);
	
}
@Override
public void initialize(URL location, ResourceBundle resources) {
	
	Pane s3 = null;
	try {
		s3 = FXMLLoader.load(getClass().getResource("Greeting.fxml"));
	
	
		Bp.setCenter(s3);
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
Bp.setCenter(s3);

	
}
}
