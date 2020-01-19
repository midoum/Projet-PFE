package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.mysql.cj.protocol.Resultset;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;

import javafx.stage.Stage;

public class ChartController  implements Initializable{
	 
	    @FXML
	    private PieChart pieChart;
	   
	  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		 Connection conn=null;
			String url="jdbc:mysql://localhost/javafx?useUnicode=true"+
			"&useJDBCCompliantTimezoneShift=true"+
			"&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String u="root";
			String m="0000";
			String req="select date,y from connect inner join temp on connect.id=temp.id;";
			ResultSet rs;
			try{
				conn=DriverManager.getConnection(url,u,m);
				if(conn!=null){
				Statement	st=conn.createStatement();
				rs= st.executeQuery(req);
					while(rs.next()){
	PieChart.Data slice=new PieChart.Data(rs.getString("date"),rs.getInt("y"));
	pieChart.getData().add(slice);
		}}
		}catch(Exception e){
		}
	}
}