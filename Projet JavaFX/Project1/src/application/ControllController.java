package application;

import java.awt.Desktop; 
import java.io.BufferedReader; 

import java.io.IOException;

import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.io.PrintWriter;
import com.fazecast.jSerialComm.SerialPort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ControllController implements Initializable{
	static SerialPort chosenPort;
	int fe=0;
@FXML
ComboBox<String> portList;
@FXML
private ProgressBar indicl;
@FXML
private ProgressBar indict;
@FXML
private ProgressBar indicd;
@FXML
private ProgressBar indich;
@FXML
private TextField f;
@FXML
private TextField lumi;
@FXML
private TextField temper;
@FXML
private TextField humi;
@FXML
private TextField distance;
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
private ImageView connectimg;
String [] mot;
int lum;
int tem;
int dis;
int hum;

@FXML
protected void  RobotControll(ActionEvent e5) throws IOException{

	System.out.println(portList.getSelectionModel().getSelectedItem());
	if(connectButton.getText().equals("Connect")) {
		// attempt to connect to the serial port
		chosenPort = SerialPort.getCommPort(portList.getSelectionModel().getSelectedItem());
		chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		if(chosenPort.openPort()) {
			connectButton.setText("Disconnect");
			connectimg.setImage(new Image("@../../application/icons/cross-mark-on-a-black-circle-background.png"));

		
			
		
	
			
			
		}
	} else {
		chosenPort.closePort();	
		connectButton.setText("Connect");
		connectimg.setImage(new Image("@../../application/icons/correct-symbol.png"));
		
	}}
	
	@FXML
	protected void  GetData(ActionEvent e11) throws IOException{
		Thread thread2 = new Thread(){
			@Override public void run() {
				Scanner scanner = new Scanner(chosenPort.getInputStream());
			while(scanner.hasNextLine()) {
				
					try {
						String line = scanner.nextLine();
					
				
						mot=line.split("=",4);
					
						System.out.println(mot[0]);
						System.out.println(mot[1]);
						System.out.println(mot[2]);
						System.out.println(mot[3]);
						lumi.setText(mot[0]);
						distance.setText(mot[1]);
						temper.setText(mot[2]);
						humi.setText(mot[3]);
						indicl.setProgress(lum*0.005);
						indict.setProgress(tem*0.01);
						indicd.setProgress(dis*0.004);
						indich.setProgress(hum*0.01);
					 lum=Integer.parseInt(mot[0]);
					 dis=Integer.parseInt(mot[1]);
					 tem=Integer.parseInt(mot[2]);
				hum=Integer.parseInt(mot[3]);
						
					} catch(Exception e) {}}
				
				scanner.close();
			}
		};
		thread2.start();
	}
	@FXML
	protected void Enregistrer(ActionEvent e12) throws IOException{
		Date Date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String Date1=format.format(Date);
 String req="insert into temp (date,luminosite,temperature,humidite,distance)values('"+Date1+"','"+lum+"','"+tem+"','"+hum+"','"+dis+"')";
new Connect().update(req);	
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
protected void Center(ActionEvent e33) throws IOException{
	Thread c=new Thread(){
		public void run() {Envoyer("4");
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
protected void  Stream(ActionEvent e10) throws IOException, URISyntaxException{

	if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
	    Desktop.getDesktop().browse(new URI(f.getText()));
	}

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

