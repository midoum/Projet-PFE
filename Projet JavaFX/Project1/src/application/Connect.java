package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Connect {
	public static String req;
	public static ResultSet rs;

public void connect(String req){
Connect.req=req;

	Connection conn=null;
		String url="jdbc:mysql://localhost/javafx?useUnicode=true"+
		"&useJDBCCompliantTimezoneShift=true"+
		"&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String u="root";
		String m="0000";
		
		
		
		try{
			conn=DriverManager.getConnection(url,u,m);
			if(conn!=null){
			Statement	st=conn.createStatement();
			rs= st.executeQuery(req);
		
}
			}catch(Exception e){
	System.out.println(e);
}
		}
		public void update(String req){
			Connect.req=req;

				Connection conn=null;
					String url="jdbc:mysql://localhost/javafx?useUnicode=true"+
					"&useJDBCCompliantTimezoneShift=true"+
					"&useLegacyDatetimeCode=false&serverTimezone=UTC";
					String u="root";
					String m="0000";
					
					
					
					try{
						conn=DriverManager.getConnection(url,u,m);
						if(conn!=null){
						Statement	st=conn.createStatement();
						st.executeUpdate(req);
					
			}
						}catch(Exception e1){
				System.out.println(e1);
			}
	
}
public ResultSet getRs(){
	return rs;
}
}