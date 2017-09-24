import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.Calendar;

public class Main {
    
	public static void UserProcess() throws IOException{
		namekiashajay ob = new namekiashajay();
		ob.sendRequest();
		if(ob.receieveRequest()){
	       ob.sendingSourceCode();
	       ob.recieveVerdict();
		}
		else System.out.println("not found");

	}
	
	
	public static void AdminProcess() throws IOException{
		namekiashajay ob2 = new namekiashajay();
		ob2.sendRequest();
		ob2.receieveRequest();
		Admin ob = new Admin();
		ob.sendingProblemFile();
		ob.sendingInputFIle();
		ob.sendingOutputFile();
	}
	
	public static void GetProblem() throws IOException{
		Problem ob = new Problem();
		namekiashajay ob2 = new namekiashajay();
		ob.GiveMeProblem();
		ob2.receieveRequest();
	    ob.problemReceiver();
	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		UserProcess();
//		AdminProcess();
//		GetProblem();
	}

}
