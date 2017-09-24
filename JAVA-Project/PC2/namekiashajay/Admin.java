import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Admin {
	
	public final static int SOCKET_PORT_SENDING_PROBLEM = 4400;
	public final static int SOCKET_PORT_SENDING_INPUT = 4500;
	public final static int SOCKET_PORT_SENDING_OUTPUT = 4600;
	
	public final static String SERVER = "10.220.60.183";
	public final static int FILE_SIZE = 6022386;
	
	public final static String Problem_File = "C:/Users/ZICO/workspace/amarMatha/problem.txt";
	public final static String Input_File = "C:/Users/ZICO/workspace/amarMatha/input.txt";
	public final static String Output_File = "C:/Users/ZICO/workspace/amarMatha/output.txt";
	
	
	public void sendingProblemFile() throws IOException
	  {
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    OutputStream os = null;
		    ServerSocket servsock = null;
		    Socket sock = null;
		    try {
		      servsock = new ServerSocket(SOCKET_PORT_SENDING_PROBLEM);
		        System.out.println("Sending Problem ...");
		        try {
		          sock = servsock.accept();
//		          System.out.println("Accepted connection : " + sock);
		          File myFile = new File (Problem_File);
		          byte [] mybytearray  = new byte [(int)myFile.length()];
		          fis = new FileInputStream(myFile);
		          bis = new BufferedInputStream(fis);
		          bis.read(mybytearray,0,mybytearray.length);
		          os = sock.getOutputStream();
		          System.out.println("Sending " + Problem_File + "(" + mybytearray.length + " bytes)");
		          os.write(mybytearray,0,mybytearray.length);
		          os.flush();
		          System.out.println("The Problem has been sent.");
		        }
		        finally {
		          if (bis != null) bis.close();
		          if (os != null) os.close();
		          if (sock!=null) sock.close();
		          if(servsock != null) servsock.close();
		        }
		      }
		    catch(IOException A){
		    	A.printStackTrace();
		    }
		    finally {
		      if (servsock != null) servsock.close();
		    }
	  }
	
	public void sendingInputFIle() throws IOException
	  {
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    OutputStream os = null;
		    ServerSocket servsock = null;
		    Socket sock = null;
		    try {
		      servsock = new ServerSocket(SOCKET_PORT_SENDING_INPUT);
		        System.out.println("Sending Input File ...");
		        try {
		          sock = servsock.accept();
//		          System.out.println("Accepted connection : " + sock);
		          File myFile = new File (Input_File);
		          byte [] mybytearray  = new byte [(int)myFile.length()];
		          fis = new FileInputStream(myFile);
		          bis = new BufferedInputStream(fis);
		          bis.read(mybytearray,0,mybytearray.length);
		          os = sock.getOutputStream();
		          System.out.println("Sending " + Input_File + "(" + mybytearray.length + " bytes)");
		          os.write(mybytearray,0,mybytearray.length);
		          os.flush();
		          System.out.println("The Input has been sent.");
		        }
		        finally {
		          if (bis != null) bis.close();
		          if (os != null) os.close();
		          if (sock!=null) sock.close();
		          if(servsock != null) servsock.close();
		        }
		      }
		    catch(IOException A){
		    	A.printStackTrace();
		    }
		    finally {
		      if (servsock != null) servsock.close();
		    }
	  }
	
	
	
	public void sendingOutputFile() throws IOException
	  {
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    OutputStream os = null;
		    ServerSocket servsock = null;
		    Socket sock = null;
		    try {
		      servsock = new ServerSocket(SOCKET_PORT_SENDING_OUTPUT);
		        System.out.println("Sending Output ...");
		        try {
		          sock = servsock.accept();
		          System.out.println("Accepted connection : " + sock);
		          File myFile = new File (Output_File);
		          byte [] mybytearray  = new byte [(int)myFile.length()];
		          fis = new FileInputStream(myFile);
		          bis = new BufferedInputStream(fis);
		          bis.read(mybytearray,0,mybytearray.length);
		          os = sock.getOutputStream();
		          System.out.println("Sending " + Output_File + "(" + mybytearray.length + " bytes)");
		          os.write(mybytearray,0,mybytearray.length);
		          os.flush();
		          System.out.println("The Output has been sent.");
		        }
		        finally {
		          if (bis != null) bis.close();
		          if (os != null) os.close();
		          if (sock!=null) sock.close();
		          if(servsock != null) servsock.close();
		        }
		      }
		    catch(IOException A){
		    	A.printStackTrace();
		    }
		    finally {
		      if (servsock != null) servsock.close();
		    }
	  }
}
