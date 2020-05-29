package application;

import java.awt.Color;
import java.net.URL;   
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;




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
	       
	        chart.pack();
	        
	        
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
	             	
	    	    	String req="select date,luminosite,temperature,humidite,distance from temp where user='"+new HomeController().getUser()+"'";
	    	    	new Connect().connect(req);
	    	    	  
	    	    	   ResultSet rs=new Connect().getRs();
	    	    	   
	    	    		  DefaultTableModel dtm = new DefaultTableModel();

	    	    		   new Connect().connect(req);
	    	    		   rs=new Connect().getRs();
	    	    	try {
	    	    		ResultSetMetaData mt=rs.getMetaData();
	    	    		int count=mt.getColumnCount();
	    	    		 for (int columnIndex = 1; columnIndex <= count; columnIndex++){
	    	    	         dtm.addColumn(mt.getColumnName(columnIndex));
	    	    	         }
	    	    	while(rs.next()) {
	    	    		Object []data=new Object[count];
	    	    		for(int i=0;i<count;i++) {
	    	    			
	    	    			data[i]=rs.getObject(i+1);
	    	    		
	    	    			
	    	    	     }
	    	    		dtm.addRow(data);
	    	    		}
	    	    		}catch(Exception e) {
	    	    			
	    	    		}
	    	    		
	    	    		
	    	    	    JTable jt=new JTable(); 
	    	    	    
	    	    	    jt.setModel(dtm);
	    	      
	    	            jt.setRowHeight(50);
	    	            
	    	    	    JScrollPane sp=new JScrollPane(jt); 
	    	    	
	    	    	    swingNode.setContent(sp);
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