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

public class ControllController implements Initializable{
	static SerialPort chosenPort;
	int fe=0;
@FXML
ComboBox<String> portList;
@FXML
private TextField f;
@FXML
private Label temp;
@FXML
Button connectButton;

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
			
			// create a new thread for sending data to the arduino
			Thread thread = new Thread(){
				@Override public void run() {
					// wait after connecting, so the bootloader can finish
					try {Thread.sleep(100); } catch(Exception e) {}

					// enter an infinite loop that sends text to the arduino
					PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
					
					
					while(true) {
			         
					
						output.print(f.getText());
						output.flush();
						try {Thread.sleep(100); } catch(Exception e) {
							
						}
					}
				}
			};
			thread.start();
			Thread thred2 = new Thread(){
				@Override public void run() {
					// wait after connecting, so the bootloader can finish
					try {Thread.sleep(100); } catch(Exception e) {}

					// enter an infinite loop that sends text to the arduino
					
					BufferedReader input = new BufferedReader(new InputStreamReader(chosenPort.getInputStream()));
					
					while(true) {
			         
					try {
						 fe =input.read();
						
						 temp.setText(String.valueOf(fe));
						System.out.println(fe);
						
} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						try {Thread.sleep(100); } catch(Exception e) {
								
						}
					}
				}
			};
			thred2.start();
			
		}
	} else {
		// disconnect from the serial port
		chosenPort.closePort();	

		connectButton.setText("Connect");
	}
}


@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	SerialPort[] portNames = SerialPort.getCommPorts();
	for(int i = 0; i < portNames.length; i++)
		portList.getItems().add(portNames[i].getSystemPortName());
	
System.out.println(portList.getItems().toString());
	
}
}
