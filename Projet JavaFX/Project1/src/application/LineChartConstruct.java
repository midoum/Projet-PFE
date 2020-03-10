package application;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

 class LineChartConstruct extends JFrame {

  private static final long serialVersionUID = 1L;
  static  String Donne;
  static String Date;
  ResultSet rs;
 int x=1;
  int y;
  public LineChartConstruct() throws SQLException {

    String req="Select * from temp where date='"+Date+"';";
    new Connect().connect(req);
    rs=new Connect().getRs();
 
    JPanel chartPanel = createChartPanel();
    add(chartPanel, BorderLayout.CENTER);

    setSize(640, 480);
 
    setLocationRelativeTo(null);
}

private JPanel createChartPanel() throws SQLException {
	  String chartTitle = "Graphe de "+Donne;
	    String xAxisLabel = "" ;
	    String yAxisLabel = "";
	 
	    XYDataset dataset = createDataset();
	 
	    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
	            xAxisLabel, yAxisLabel, dataset);
	 
	    return new ChartPanel(chart);
}

private XYDataset createDataset() throws SQLException {
	   XYSeriesCollection dataset = new XYSeriesCollection();
	    XYSeries series1 = new XYSeries("");
	    while(rs.next()) {
	    	switch(Donne) {
	    	case "temperature":
	    		y=rs.getInt("temperature");
	    		break;
	    	case "humidite":
	    		y=rs.getInt("humidite");
	    		break;
	    	case "distance":
	    		y=rs.getInt("distance");
	    		break;
	    	case "luminosite":
	    		y=rs.getInt("luminosite");
	    		break;
	    	}
	    	 x=x+1;
	    	series1.add(x,y);
	    }
	  
	    
	    
	    dataset.addSeries(series1);
	
	 
	    return dataset;
}
       

           
    

  public void setDonne(String Donne) {
		this.Donne=Donne;
	}
  public void setDate(String Date) {
		this.Date=Date;
	}
 
}