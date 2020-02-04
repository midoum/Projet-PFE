package application;

import java.io.IOException;
import java.rmi.server.LoaderHandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AlertController {
@FXML
private Button Oui;
@FXML
private Button Non;
@FXML
public void OuiClick(ActionEvent event) throws IOException {
	Stage stage=Home.getStage();
	Parent r=FXMLLoader.load(getClass().getResource("Inscription.fxml"));
	Scene s=new Scene(r);
	stage.setScene(s);
}
@FXML
public void NonClick(ActionEvent e) throws IOException{

	Stage stage=Home.getStage();
	Parent r=FXMLLoader.load(getClass().getResource("login.fxml"));
	Scene s=new Scene(r);
	stage.setScene(s);

}
}
