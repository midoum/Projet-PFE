package application;

import java.sql.ResultSet; 
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.mysql.cj.exceptions.RSAException;
import com.sun.media.jfxmedia.events.NewFrameEvent;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
 

import javafx.scene.chart.LineChart;

 
 

public class XYLineChartExample extends JFrame {
	  private static final long serialVersionUID = 6294689542092367723L;
	  static String Date;
		 static String Donne;
		 ResultSet rs;
		 int yy;
			int x;
	  public XYLineChartExample(String title) throws SQLException {
		   super(title);
	
	
	String req="select * from temp  where date='"+Date+"'; ";
	new Connect().connect(req);
	 rs =new Connect().getRs();
              
       x=rs.getInt("id");

       
        while(rs.next()){
         	switch(Donne) {
        	case "temperature":
        		 yy=rs.getInt("temperature");
        		break;
        	case "humidite":
        		 yy=rs.getInt("humidite");
        		break;
        	case "luminosite":
        	 yy=rs.getInt("luminosite");
        		break;
        	case "distance":
        		 yy=rs.getInt("distance");
        		break;
        	}
        	
        
       
        
       

        }

    
   

        	 

        	    // Create dataset
        	    XYDataset dataset = createDataset();

        	    // Create chart
        	    JFreeChart chart = ChartFactory.createXYLineChart(
        	        "XY Line Chart Example",
        	        "X-Axis",
        	        "Y-Axis",
        	        dataset,
        	        PlotOrientation.VERTICAL,
        	        true, true, false);

        	    // Create Panel
        	    ChartPanel panel = new ChartPanel(chart);
        	    setContentPane(panel);
        	  }

        	  private XYDataset createDataset() {
        	    XYSeriesCollection dataset = new XYSeriesCollection();

        	    XYSeries series = new XYSeries("");
        	    series.add(x,yy);
        	   
  

        	    //Add series to dataset
        	    dataset.addSeries(series);
        	    
        	    return dataset;
        	  }

        	  public static void main(String[] args) {
        	    SwingUtilities.invokeLater(() -> {
        	      XYLineChartExample example = null;
				try {
					example = new XYLineChartExample("XY Chart Example | BORAJI.COM");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	      example.setSize(800, 400);
        	      example.setLocationRelativeTo(null);
        	      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        	      example.setVisible(true);
        	    });
        	  }
        	
 public void setDate(String Date) {
this.Date=Date;

}
 public void setDonne(String Donne) {
this.Donne=Donne;

}




}