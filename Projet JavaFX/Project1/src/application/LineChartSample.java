package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
 

import javafx.scene.chart.LineChart;

 
 
public class LineChartSample  {
	static String Date;
	
	static LineChart<Number,Number>lineChart;
public  LineChartSample() throws SQLException { 
	String req="select * from temp  where date='"+Date+"'; ";
	new Connect().connect(req);
	ResultSet rs =new Connect().getRs();
              
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Graphe ");
      //creer graphe
         lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
        //remplir s�rie de donn�       
        lineChart.setTitle("Stock Monitoring, 2010");
   
        XYChart.Series series = new XYChart.Series();
      
        series.setName("Temp�rature");
        while(rs.next()){
        	int x=rs.getInt("id");
        	int yy=rs.getInt("y");
        
        XYChart.Data<Number,Number>  data=new XYChart.Data(x,yy);
        
        series.getData().add(data );

        }
        
    
   
        
     //ajouter serie
        lineChart.getData().add(series);
        
   
 
   }
 public void setDate(String Date) {
this.Date=Date;

}
public LineChart<Number, Number> getP(){
	return lineChart;
}

}