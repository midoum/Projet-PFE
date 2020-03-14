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

  ResultSet rs;
 int x=0;
  int y;
  public LineChartConstruct()  {


 
    JPanel chartPanel = createChartPanel();
    add(chartPanel, BorderLayout.CENTER);

    setSize(740, 580);
 
    setLocationRelativeTo(null);
}

private JPanel createChartPanel()  {
	  String chartTitle = "Graphe de ";
	    String xAxisLabel = "id" ;
	    String yAxisLabel = "";
	    
	 
	    XYDataset dataset = createDataset();

	 
	    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
	            xAxisLabel, yAxisLabel, dataset);
	 
	    return new ChartPanel(chart);
}

private XYDataset createDataset()  {
	   XYSeriesCollection dataset = new XYSeriesCollection();
	    XYSeries series1 = new XYSeries("");
Boolean test=true;
		 while (test=true) {
	    		y=(int)(Math.random()*20);
	    		 System.out.println(y);

	    	 x=x+1;
	    	  series1.add(x,y);
		
		  }
	
		  test=false;
	    dataset.addSeries(series1);
	
	 
	    return dataset;
		  

}
       
 
public static void main(String[] args) {
	 LineChartConstruct p=new  LineChartConstruct();
	p.show();
	
}      
    


}