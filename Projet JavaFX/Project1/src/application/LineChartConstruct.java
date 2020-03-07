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

 
 
public class LineChartConstruct  {
	static String Date;
	 static String Donne;
	 int yy;
	static LineChart<Number,Number>lineChart;
public  LineChartConstruct() throws SQLException { 
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
        lineChart.setTitle("");
   
        XYChart.Series series = new XYChart.Series();

       
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
        	
        	int x=rs.getInt("id");
       
        
        XYChart.Data<Number,Number>  data=new XYChart.Data(x,yy);
        
        series.getData().add(data );

        }
        
    
   
        
     //ajouter serie
        lineChart.getData().add(series);
        
   
 
   }
 public void setDate(String Date) {
this.Date=Date;

}
 public void setDonne(String Donne) {
this.Donne=Donne;

}


public LineChart<Number, Number> getP(){
	return lineChart;
}

}