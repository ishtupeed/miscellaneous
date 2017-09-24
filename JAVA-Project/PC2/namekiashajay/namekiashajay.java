
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


import java.io.File;

import java.io.OutputStream;
import java.net.ServerSocket;


public class namekiashajay {
	public final static int SOCKET_PORT_CODE_SENDER = 4000;      // you may change this
	public final static int SOCKET_PORT_REQUEST_SENDER = 4100;
	public final static int SOCKET_PORT_VERDICT_RECIEVER = 4200;      // you may change this
	public final static int SOCKET_PORT_RESPONCE_RECIEVER = 4300;
	  public final static String SERVER = "10.220.60.183";  // localhost
	  public final static String
	       FILE_TO_RECEIVED = "c:/temp/Verdict.txt";  // you may change this, I give a
	                                                            // different name because i don't want to
	                                                            // overwrite the one used by server...
	  public final static int FILE_SIZE = 6022386; // file size temporary hard coded
	                                               // should bigger than the file to be downloaded
	  
	  
	   
	  public final static String FILE_TO_SEND_SOURCECODE = "C:/Users/ZICO/workspace/amarMatha/a.c";  // you may change this
	  public final static String FILE_TO_SEND_SOURCECODE_Request = "C:/Users/ZICO/workspace/amarMatha/Code.txt";
	  public final static String FILE_TO_RECIEVE_Request = "C:/Users/ZICO/workspace/amarMatha/Send.txt";
	  public void recieveVerdict() throws UnknownHostException, IOException
	  {
		  int bytesRead;
		    int current = 0;
		    FileOutputStream fos = null;
		    BufferedOutputStream bos = null;
		    Socket sock = null;
		    try {
		      sock = new Socket(SERVER, SOCKET_PORT_VERDICT_RECIEVER);
		      System.out.println("Waiting for Verdict...");

		      // receive file
		      byte [] mybytearray  = new byte [FILE_SIZE];
		      InputStream is = sock.getInputStream();
		      fos = new FileOutputStream(FILE_TO_RECEIVED);
		      bos = new BufferedOutputStream(fos);
		      bytesRead = is.read(mybytearray,0,mybytearray.length);
		      current = bytesRead;

		      do {
		         bytesRead =
		            is.read(mybytearray, current, (mybytearray.length-current));
		         if(bytesRead >= 0) current += bytesRead;
		      } while(bytesRead > -1);

		      bos.write(mybytearray, 0 , current);
		      bos.flush();
		      System.out.println("File " + FILE_TO_RECEIVED
		          + " downloaded (" + current + " bytes read)");
		      
		      FileReader fileReader =
		    		    new FileReader("C:/Temp/Verdict.txt");

		    		BufferedReader bufferedReader =
		    		    new BufferedReader(fileReader);
		    		String ss = "";
		    		String line;
		    		while((line = bufferedReader.readLine()) != null)
		    		{
		    		    ss += line;
		    		}
//		    		System.out.println(ss + " before");
		    		if(ss.equals("Accepted")) System.out.println("Accepted");
		    		else if(ss.equals("Wrong Answer")) System.out.println("Wrong Answer");
		    		else System.out.println("Compilation Error");
		    		bufferedReader.close();

		    }
		    finally {
		      if (fos != null) fos.close();
		      if (bos != null) bos.close();
		      if (sock != null) sock.close();
		    }

	  }
	  public void sendingSourceCode() throws IOException
	  {
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    OutputStream os = null;
		    ServerSocket servsock = null;
		    Socket sock = null;
		    try {
		      servsock = new ServerSocket(SOCKET_PORT_CODE_SENDER);
//		      while (true) {
		        System.out.println("Sending Source Code...");
		        try {
		          sock = servsock.accept();
		          System.out.println("Accepted connection : " + sock);
		          // send file
		          File myFile = new File (FILE_TO_SEND_SOURCECODE);
		          byte [] mybytearray  = new byte [(int)myFile.length()];
		          fis = new FileInputStream(myFile);
		          bis = new BufferedInputStream(fis);
		          bis.read(mybytearray,0,mybytearray.length);
		          os = sock.getOutputStream();
		          System.out.println("Sending " + FILE_TO_SEND_SOURCECODE + "(" + mybytearray.length + " bytes)");
		          os.write(mybytearray,0,mybytearray.length);
		          os.flush();
		          System.out.println("The SourceCode has been sent.");
		        }
		        finally {
		          if (bis != null) bis.close();
		          if (os != null) os.close();
		          if (sock!=null) sock.close();
		        }
		      }
//		    }
		    finally {
		      if (servsock != null) servsock.close();
		    }
	  }
	  
	  
	  
	  
	  
	  public void sendRequest() throws IOException
	  {
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    OutputStream os = null;
		    ServerSocket servsock = null;
		    Socket sock = null;
		    try {
		      servsock = new ServerSocket(SOCKET_PORT_REQUEST_SENDER);
//		      while (true) {
		        System.out.println("Waiting...");
		        try {
		          sock = servsock.accept();
		          System.out.println("Accepted connection : " + sock);
		          // send file
		          File myFile = new File (FILE_TO_SEND_SOURCECODE_Request);
		          byte [] mybytearray  = new byte [(int)myFile.length()];
		          fis = new FileInputStream(myFile);
		          bis = new BufferedInputStream(fis);
		          bis.read(mybytearray,0,mybytearray.length);
		          os = sock.getOutputStream();
		          System.out.println("Sending request" + FILE_TO_SEND_SOURCECODE_Request + "(" + mybytearray.length + " bytes)");
		          os.write(mybytearray,0,mybytearray.length);
		          os.flush();
//		          System.out.println("Done.");
		        }
		        finally {
		          if (bis != null) bis.close();
		          if (os != null) os.close();
		          if (sock!=null) sock.close();
		        }
		      }
//		    }
		    finally {
		      if (servsock != null) servsock.close();
		    }
	  }
	  
	  
	  
	  public boolean receieveRequest() throws UnknownHostException, IOException
	  {
		  int bytesRead;
		    int current = 0;
		    FileOutputStream fos = null;
		    BufferedOutputStream bos = null;
		    Socket sock = null;
		    try {
		      sock = new Socket(SERVER, SOCKET_PORT_RESPONCE_RECIEVER);
		      System.out.println("Connecting (to recieve request) ...");

		      // receive file
		      byte [] mybytearray  = new byte [FILE_SIZE];
		      InputStream is = sock.getInputStream();
		      fos = new FileOutputStream(FILE_TO_RECIEVE_Request);
		      bos = new BufferedOutputStream(fos);
		      bytesRead = is.read(mybytearray,0,mybytearray.length);
		      current = bytesRead;

		      do {
		         bytesRead =
		            is.read(mybytearray, current, (mybytearray.length-current));
		         if(bytesRead >= 0) current += bytesRead;
		      } while(bytesRead > -1);

		      bos.write(mybytearray, 0 , current);
		      bos.flush();
		      String line = null;
		      FileReader fileReader = 
		                new FileReader("C:/Users/ZICO/workspace/amarMatha/Send.txt");

		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = 
		                new BufferedReader(fileReader);
		            String ss = "";
		            while((line = bufferedReader.readLine()) != null) {
//		                System.out.println(line);
		                ss += line;
		            }  
//		            System.out.println(ss);
		            if(ss.equals("Send")){
		            	return true;
		            }
		            // Always close files.
		            bufferedReader.close(); 
		      System.out.println("File " + FILE_TO_RECEIVED
		          + " downloaded (" + current + " bytes read)");
		      
		    }
		    finally {
		      if (fos != null) fos.close();
		      if (bos != null) bos.close();
		      if (sock != null) sock.close();
		    }
		    return false;
	  }

}
