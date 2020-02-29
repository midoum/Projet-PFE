package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;

import javafx.scene.layout.Pane;



public class ChartController  implements Initializable{
	 
	  
	    @FXML
	    private ComboBox<String>date;
	  @FXML 
	  private Pane p;

	   ResultSet rs;
	
	  
	    @FXML
	    protected void   rechercher(ActionEvent e) throws SQLException {
	    String 	SelDate =date.getSelectionModel().getSelectedItem();
	    new LineChartSample().setDate(SelDate);
	    LineChart<?, ?> L1=new LineChartSample().getP();
       p.getChildren().add(L1);
	    	
	    
	    }  
	    @FXML
	    protected void   MontrerTable(ActionEvent e1) throws SQLException{
	   
	    
	    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String req="select distinct date from connect inner join temp on connect.id=temp.id;";
		
		new Connect().connect(req);
		 rs =new Connect().getRs();
					try{
						while(rs.next()){
					
						date.getItems().add(rs.getString("date"));
						
						
							
}
					}
	
	catch(Exception e){
						
					}
		

	
}}