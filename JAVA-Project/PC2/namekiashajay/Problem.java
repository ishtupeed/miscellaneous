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


public class Problem {
	
	public final static int SOCKET_PORT_RECEIEVE_PROBLEM = 4800;
	public final static int SOCKET_PORT_REQUEST_PROBLEM = 4100;
	
	public final static String SERVER = "10.220.60.183";
	public final static int FILE_SIZE = 6022386;
	
	public final static String ProblemStatement_File = "C:/Users/ZICO/workspace/amarMatha/problemStatement.txt";
	public final static String GiveMeProblem_File = "C:/Users/ZICO/workspace/amarMatha/GiveMeProblem.txt";
	
	public void problemReceiver() throws UnknownHostException, IOException
	  {
		  int bytesRead;
		    int current = 0;
		    FileOutputStream fos = null;
		    BufferedOutputStream bos = null;
		    Socket sock = null;
		    try {
		      sock = new Socket(SERVER, SOCKET_PORT_RECEIEVE_PROBLEM);
		      System.out.println("Waiting for Problem ...");

		      // receive file
		      byte [] mybytearray  = new byte [FILE_SIZE];
		      InputStream is = sock.getInputStream();
		      fos = new FileOutputStream(ProblemStatement_File);
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
		      System.out.println("File " + ProblemStatement_File
		          + " downloaded (" + current + " bytes read)");
		      
		      ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "C:/Users/ZICO/workspace/amarMatha/problemStatement.txt");
		      pb.start();

		    }
		    finally {
		      if (fos != null) fos.close();
		      if (bos != null) bos.close();
		      if (sock != null) sock.close();
//		      if (servsock != null) servsock.close();
		    }

	  }
	
	
	public void GiveMeProblem() throws IOException
	  {
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    OutputStream os = null;
		    ServerSocket servsock = null;
		    Socket sock = null;
		    try {
		      servsock = new ServerSocket(SOCKET_PORT_REQUEST_PROBLEM);
//		      while (true) {
		        System.out.println("Waiting ...");
		        try {
		          sock = servsock.accept();
		          System.out.println("Accepted connection : " + sock);
		          // send file
		          File myFile = new File (GiveMeProblem_File);
		          byte [] mybytearray  = new byte [(int)myFile.length()];
		          fis = new FileInputStream(myFile);
		          bis = new BufferedInputStream(fis);
		          bis.read(mybytearray,0,mybytearray.length);
		          os = sock.getOutputStream();
		          System.out.println("Sending request" + GiveMeProblem_File + "(" + mybytearray.length + " bytes)");
		          os.write(mybytearray,0,mybytearray.length);
		          os.flush();
//		          System.out.println("Done.");
		        }
		        finally {
		          if (bis != null) bis.close();
		          if (os != null) os.close();
		          if (sock!=null) sock.close();
		          if (servsock != null) servsock.close();
		        }
		      }
//		    }
		    finally {
		      if (servsock != null) servsock.close();
		    }
	  }
}
