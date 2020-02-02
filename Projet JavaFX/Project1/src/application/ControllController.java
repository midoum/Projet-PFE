package application;

import java.io.BufferedReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.PrintWriter;
import com.fazecast.jSerialComm.SerialPort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class ControllController implements Initializable{
	static SerialPort chosenPort;
	int fe=0;
@FXML
ComboBox<String> portList;
@FXML
WebView web;
@FXML
private TextField f;
@FXML
private Label temp;
@FXML
private Button connectButton;
@FXML
private Button up;
@FXML
private Button down;
@FXML
private Button left;
@FXML
private Button right;

@FXML
protected void  RobotControll(ActionEvent e5) throws IOException{

	System.out.println(portList.getSelectionModel().getSelectedItem());
	if(connectButton.getText().equals("Connect")) {
		// attempt to connect to the serial port
		chosenPort = SerialPort.getCommPort(portList.getSelectionModel().getSelectedItem());
		chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		if(chosenPort.openPort()) {
			connectButton.setText("Disconnect");
			System.out.println(connectButton.getText());
		
			
		
	
			
			
		}
	} else {
		// disconnect from the serial port
		chosenPort.closePort();	

		connectButton.setText("Connect");
	}
	Thread thred2 = new Thread(){
		@Override public void run() {
			// wait after connecting, so the bootloader can finish
			try {Thread.sleep(100); } catch(Exception e) {}

			// enter an infinite loop that sends text to the arduino
			
			BufferedReader input = new BufferedReader(new InputStreamReader(chosenPort.getInputStream()));
			
			
	         
			try {
				 fe =input.read();
				input.close();
				
				
				System.out.println(fe);
				
				
} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				try {Thread.sleep(100); } catch(Exception e) {
						
				}
			}
		
	};
	thred2.start();
}
@FXML
protected void  up(ActionEvent e6) throws IOException{
Thread a=new Thread(){
	public void run() {Envoyer("0");
}};
a.start();

}
@FXML
protected void down(ActionEvent e7) throws IOException{
	Thread b=new Thread(){
		public void run() {Envoyer("1");
	}};
	b.start();	
}
@FXML
protected void left(ActionEvent e8) throws IOException{
	Thread c=new Thread(){
		public void run() {Envoyer("2");
	}};
	c.start();
}
@FXML
protected void right(ActionEvent e9) throws IOException{
	Thread d=new Thread(){
		public void run() {Envoyer("3");
	}};
	d.start();
}

@FXML
protected void  Stream(ActionEvent e10) throws IOException{
	web.getEngine().load(f.getText());

}
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	SerialPort[] portNames = SerialPort.getCommPorts();
	for(int i = 0; i < portNames.length; i++)
		portList.getItems().add(portNames[i].getSystemPortName());
	
System.out.println(portList.getItems().toString());
	
}
public void Envoyer(String message) {
	// wait after connecting, so the bootloader can finish
	try {Thread.sleep(100); } catch(Exception e) {}

	// enter an infinite loop that sends text to the arduino
	PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
	
	
	
    
	
		output.print(message);
		output.flush();
		try {Thread.sleep(100); } catch(Exception e) {
			
		}
	}

};

