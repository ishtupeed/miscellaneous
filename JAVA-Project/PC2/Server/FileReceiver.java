import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

/**
 * @author Bakhtiar
 * @version 1.0
 */
public class FileReceiver
{
    public static final int SOCKET_PORT_CODE_RECEIVER = 4000;
    public static final int SOCKET_PORT_REQUEST_RECEIVER = 4100;
    public static final int SOCKET_PORT_PROBLEM = 4400;
    public static final int SOCKET_PORT_INPUT= 4500;
    public static final int SOCKET_PORT_OUTPUT = 4600;
    public static final int SOCKET_PORT_CATEGORY = 4800;
    public static final int SOCKET_PORT_PROBLEM_NAME = 4900;
    public static final int SOCKET_PORT_NAME = 5500;
    public static final int SOCKET_PORT_USERNAME = 5200;
    public static final int SOCKET_PORT_PASSWORD = 5300;
    public static final int SOCKET_PORT_IMAGE = 5400;
    public static final int SOCKET_PORT_ID = 5600;
    public static final int SOCKET_PORT_TUTORIAL_NAME = 6100;
    public static final int SOCKET_PORT_TUTORIAL_STATEMENT = 6200;
    public static final int SOCKET_PORT_TUTORIAL_CATEGORY = 6300;
    public static final String SERVER = "10.220.60.106";
    public static final int FILE_SIZE = 6022386;
	public boolean requestReceive()
    {
        final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/request.txt";
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_REQUEST_RECEIVER);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
    }
    public String requestRead()
    {
        BufferedReader br = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/request.txt"));
            ret+=br.readLine();
            if(ret.equals("Code"))
            	ret+=br.readLine();
            else if(ret.equals("GiveMeProblem"))
            	ret+=br.readLine();
            else if(ret.equals("TT"))
            	ret+=br.readLine();
            File f = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/request.txt");
            f.delete();
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            if(br!=null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    return null;
                }
        }
        return ret;
    }
    @SuppressWarnings("finally")
	public boolean codeReceive()
    {
    	final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/a.c";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_CODE_RECEIVER);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
            return true;
        }
    }
    public boolean problemNameReceive()
    {
    	final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/problemname.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_PROBLEM_NAME);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
    }
	public boolean problemReceive()
    {
    	final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/problem.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_PROBLEM);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
    }
	public boolean inputReceive()
    {
    	final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/input.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_INPUT);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
    }
	public boolean outputReceive()
    {
    	final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/expected_output.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_OUTPUT);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
    }
	public boolean categoryReceive()
    {
    	final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/category.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_CATEGORY);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
    }
	public String categoryReader()
	{
		BufferedReader br = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/category.txt"));
            ret+=br.readLine();
            if(ret.equals("Code"))
            	ret+=br.readLine();
            File f = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/category.txt");
            f.delete();
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            if(br!=null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    return null;
                }
        }
        return ret;
	}
	public String problemNameReader()
	{
		BufferedReader br = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/problemname.txt"));
            ret+=br.readLine();
            if(ret.equals("Code"))
            	ret+=br.readLine();
            File f = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/problemname.txt");
            f.delete();
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            if(br!=null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    return null;
                }
        }
        return ret;
	}
	public boolean nameReceiver()
	{
		final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/name.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_NAME);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
	}
	public String nameReader()
	{
		BufferedReader br = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/name.txt"));
            ret=br.readLine();
            File f = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/name.txt");
            f.delete();
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            if(br!=null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    return null;
                }
        }
        return ret;
	}
	public boolean usernameReceiver()
	{
		final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/username.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_USERNAME);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
	}
	public String usernameReader()
	{
		BufferedReader br = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/username.txt"));
            ret=br.readLine();
            File f = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/username.txt");
            f.delete();
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            if(br!=null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    return null;
                }
        }
        return ret;
	}
	public boolean passwordReceiver()
	{
		final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/password.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_PASSWORD);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
	}
	public String passwordReader()
	{
		BufferedReader br = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/password.txt"));
            ret=br.readLine();
            File f = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/password.txt");
            f.delete();
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            if(br!=null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    return null;
                }
        }
        return ret;
	}
	public boolean imageReceiver()
	{
		final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/image.jpg";
		try {
			ServerSocket serverSocket = new ServerSocket(SOCKET_PORT_IMAGE);
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			
			byte[] sizeAr= new byte[4];
			inputStream.read(sizeAr);
			int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
			byte[] imageAr = new byte[size];
			inputStream.read(imageAr);
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
	        ImageIO.write(image, "jpg", new File(FILE_TO_RECEIVE));
	        serverSocket.close();
	        
	        return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean IDReceiver()
	{
		final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/id.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_ID);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
	}
	public String IDReader()
	{
		BufferedReader br = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/id.txt"));
            ret=br.readLine();
            File f = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/id.txt");
            f.delete();
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            if(br!=null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    return null;
                }
        }
        return ret;
	}
	public boolean tutorialNameReceiver()
	{
		final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/tutoname.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_TUTORIAL_NAME);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
	}
	public String tutorialNameReader()
	{
		BufferedReader br = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/tutoname.txt"));
            ret=br.readLine();
            File f = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/tutoname.txt");
            f.delete();
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            if(br!=null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    return null;
                }
        }
        return ret;
	}
	public boolean tutorialStatementReceiver()
	{
		final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/tutostate.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_TUTORIAL_STATEMENT);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
	}

	public boolean tutorialCatReceiver()
	{
		final String FILE_TO_RECEIVE = "C:/Users/Bakhtiar/Documents/Eclipse/Server/tutocat.txt";
    	int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock =null;
        try
        {
            sock = new Socket(SERVER, SOCKET_PORT_TUTORIAL_CATEGORY);
            byte [] inputByte = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(inputByte, 0, inputByte.length);
            current = bytesRead;
            do
            {
                bytesRead = is.read(inputByte, current, (inputByte.length-current));
                if(bytesRead>=0)
                    current+=bytesRead;
            }
            while(bytesRead > -1);

            bos.write(inputByte, 0, current);
            bos.flush();
            return true;
        }
        catch(UnknownHostException e)
        {
        	 return false;
        }
        catch(IOException e)
        {
        	return false;
        }
        finally
        {
            try
            {
            	if(fos!=null)
                    fos.close();
                if(bos!=null)
                    bos.close();
                if(sock!=null)
                    sock.close();
            }
            catch(final IOException e)
            {
            	return false;
            }
        }
	}
	public String tutorialCatReader()
	{
		BufferedReader br = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/tutocat.txt"));
            ret=br.readLine();
            File f = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/tutocat.txt");
            f.delete();
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            if(br!=null)
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    return null;
                }
        }
        return ret;
	}
}
