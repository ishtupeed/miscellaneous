import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class FileSender
{
	public static final int SOCKET_PORT_RESPONSE_SENDER = 4300;
	public static final int SOCKET_PORT_VERDICT_SENDER = 4200;
	public static final int SOCKET_PORT_PROBLEM_SENDER = 5100;
	public static final int SOCKET_PORT_VER_SENDER = 5000;
	public static final int SOCKET_PORT_PROBLEMLIST_SENDER = 5700;
	public static final int SOCKET_PORT_RANKLIST_SENDER = 5800;
	public static final int SOCKET_PORT_TUTORIAL_LIST = 5900;
	public static final int SOCKET_PORT_TUTORIAL = 6000;
	public static final int SOCKET_PORT_CHANGERESULT = 6400;
	public static final int SOCKET_PORT_USERLIST = 6500;
	public static final int SOCKET_PORT_USERINFO = 6600;
	public static final int SOCKET_PORT_USERIMAGE = 6700;
	public static final String SERVER = "10.220.60.106";
	@SuppressWarnings("resource")
	public boolean responseSend()
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    final String FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/SendMe.txt";
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_RESPONSE_SENDER);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		return false;
	    	}
	    	return true;
	    }
	    catch(IOException e)
	    {
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		return false;
	    	}
	    }
	}
	@SuppressWarnings("resource")
	public boolean verdictSend(String v)
	{
		String FILE_TO_SEND = null;
		if(v.equals("Accepted"))
			FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/AC.txt";
		else if(v.equals("Wrong Answer"))
			FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/WA.txt";
		else if(v.equals("Compilation Error"))
			FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/CE.txt";
		else
			FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/RE.txt";
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_VERDICT_SENDER);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    return true;
	}
	@SuppressWarnings("resource")
	public boolean problemSend()
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    final String FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/problem.txt";
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_PROBLEM_SENDER);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    return true;
	}
	@SuppressWarnings("resource")
	public boolean loginVerification(int t)
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    final String FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/ver.txt";
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_VER_SENDER);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		return false;
	    	}
	    	return true;
	    }
	    catch(IOException e)
	    {
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		return false;
	    	}
	    }
	}
	@SuppressWarnings("resource")
	public boolean problemlistSend()
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    final String FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/problemlist.txt";
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_PROBLEMLIST_SENDER);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    return true;
	}
	@SuppressWarnings("resource")
	public boolean ranklistSend()
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    final String FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/ranklist.txt";
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_RANKLIST_SENDER);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
		return true;
	}
	@SuppressWarnings("resource")
	public boolean tutorialListSend()
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    final String FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/tutoriallist.txt";
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_TUTORIAL_LIST);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
		return true;
	}
	@SuppressWarnings("resource")
	public boolean tutorialSend()
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    final String FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/tutorial.txt";
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_TUTORIAL);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
		return true;
	}
	@SuppressWarnings("resource")
	public boolean changeVerdict(int result)
	{
		String FILE_TO_SEND = null;
		if(result==1)
			FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/done.txt";
		else
			FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/notdone.txt";
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_CHANGERESULT);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    return true;
	}
	@SuppressWarnings("resource")
	public boolean sendUserList()
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    final String FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/userlist.txt";
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_USERLIST);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
		return true;
	}
	@SuppressWarnings("resource")
	public boolean sendUserInfo()
	{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    final String FILE_TO_SEND = "C:/Users/Bakhtiar/Documents/Eclipse/Server/userinfo.txt";
	    try
	    {
	    	servsock = new ServerSocket(SOCKET_PORT_USERINFO);
	    	try
	    	{
	    		sock = servsock.accept();
	    		File myFile = new File(FILE_TO_SEND);
	    		byte [] mybytearray  = new byte [(int)myFile.length()];
	    		fis = new FileInputStream(myFile);
		        bis = new BufferedInputStream(fis);
		        bis.read(mybytearray,0,mybytearray.length);
		        os = sock.getOutputStream();
		        os.write(mybytearray,0,mybytearray.length);
		        os.flush();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	    finally
	    {
	    	try
	    	{
	    		if (bis != null)
	    			bis.close();
		        if (os != null)
		        	os.close();
		        if (sock!=null)
		        	sock.close();
		        if(servsock!=null)
		        	servsock.close();
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    		return false;
	    	}
	    }
		return true;
	}
	public boolean sendUserImage()
	{
		final String FILE_TO_SEND = "sendImage.jpg";
		try 
		{
			Socket socket = new Socket(SERVER, SOCKET_PORT_USERIMAGE);
			OutputStream outputStream = socket.getOutputStream();
	        BufferedImage image = ImageIO.read(new File(FILE_TO_SEND));

	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        ImageIO.write(image, "jpg", byteArrayOutputStream);

	        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
	        outputStream.write(size);
	        outputStream.write(byteArrayOutputStream.toByteArray());
	        outputStream.flush();
	        socket.close();
	        return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
