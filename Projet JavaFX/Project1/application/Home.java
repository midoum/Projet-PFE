package application;

import java.awt.MouseInfo; 

import java.awt.Point;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Home extends Application {
	private static  Stage stage;
	
	public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED);

     stage=primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Connection");
        Scene scene=new Scene(root);
        
        
        primaryStage.setScene(scene);
       
        primaryStage.show();
       
        primaryStage.addEventHandler(MouseEvent.MOUSE_PRESSED, (event)->{
        	
        	
        	double x=primaryStage.getX();
        double y=primaryStage.getY();
      	  primaryStage.addEventHandler(MouseEvent.MOUSE_DRAGGED, (e)->{
              
    		  Point  p = MouseInfo.getPointerInfo().getLocation();
    		    double xx=p.x;
    		    double yy=p.y;
    		    primaryStage.setX(xx-x);
    		    primaryStage.setY(yy-y);
          });

       });
  
    }

   
	
	
	public static Stage getStage() {
	   
		return stage;
	}

    public static void main(String[] args) {
        Application.launch(args);
    }

}
