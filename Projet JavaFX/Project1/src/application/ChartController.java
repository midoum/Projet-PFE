package application;

import java.io.IOException; 
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class ChartController  implements Initializable{
	 
	  
	    @FXML
	    private ComboBox<String>date;
	  @FXML 
	  private Pane p;
	   
	  
	    @FXML
	    protected void   rechercher(ActionEvent e) throws SQLException {
	    String 	SelDate =date.getSelectionModel().getSelectedItem();
	    new LineChartSample().setDate(SelDate);
	    	
	    	
	    	  Stage stage = new Stage();
	            stage.setScene(new Scene(new LineChartSample().getP()));
	            stage.setTitle("Second Window");
	            stage.show();
	    
	    }  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String req="select distinct date from connect inner join temp on connect.id=temp.id;";
		
		new Connect().connect(req);
		ResultSet rs =new Connect().getRs();
					try{
						while(rs.next()){
					
						date.getItems().add(rs.getString("date"));
						
						
							
}
					}
	
	catch(Exception e){
						
					}
		

	
}}