package application;

import java.net.URL;  
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;



import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import net.proteanit.sql.DbUtils;



public class ChartController  implements Initializable{
	  
	    @FXML
	    private ComboBox<String>date;
	    @FXML
	    private ComboBox<String>valeurs;
	   
	  @FXML 
	  private Pane p;

	   ResultSet rs;
	
	  
	    @FXML
	    protected void   rechercher(ActionEvent e) throws SQLException {
	    	String Date=date.getSelectionModel().getSelectedItem();
	    	String Donne=valeurs.getSelectionModel().getSelectedItem();
	    	new LineChartConstruct().setDonne(Donne);
	    	new LineChartConstruct().setDate(Date);
	    	
	    	
	    	LineChartConstruct chart=new LineChartConstruct();
	        chart.setAlwaysOnTop(true);
	        chart.pack();
	        chart.setSize(600, 400);
	        
	        chart.setVisible(true);
	    
	    }  
	    @FXML
	    protected void   MontrerTable(ActionEvent e1) throws SQLException{
	   
	    	 final SwingNode swingNode = new SwingNode();
	    	
	         createSwingContent(swingNode);
	         p.getChildren().add(swingNode);	   
	    
	    }
	    private void createSwingContent(final SwingNode swingNode) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	             	ResultSet rs = null;
	    	    	String req="select * from temp";
	    	    	new Connect().connect(req);
	    	    	JPanel p=new JPanel();
	    	    	
	    	    	JTable t=new JTable();
	    	    	
	    	    	 p.add(t);
	    	    	 swingNode.setContent(p);
	            }
	        });
	    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		String req="select distinct date from temp";
		
		new Connect().connect(req);
		 rs =new Connect().getRs();
					try{
						while(rs.next()){
					
						date.getItems().add(rs.getString("date"));
						
						
							
}
						valeurs.getItems().add("temperature");
						valeurs.getItems().add("humidite");
						valeurs.getItems().add("distance");
						valeurs.getItems().add("luminosite");
						valeurs.setValue("temperature");
						
					}
					
	
	catch(Exception e){
						
					}
		

	
}

}