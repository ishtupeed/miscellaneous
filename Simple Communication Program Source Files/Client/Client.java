import java.net.*;
import java.io.*;

public class Client
{
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket socket;
    private ClientGUI cGUI;
    private String server, username;
    private int port;

    Client(String s, int p, String u, ClientGUI c)
    {
        server = s;
        port = p;
        username = u;
        cGUI = c;
    }
    public boolean start()
    {
        try
        {
            socket = new Socket(server, port);
        }
        catch(Exception e)
        {
            display("Error Connecting to Server");
            return false;
        }
        String message = "Connection Accepted " + socket.getInetAddress() + ":" + socket.getPort();
        display(message);

        try
        {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        }
        catch(IOException e)
        {
            display("Exception Creating new Input/Output Streams");
            return false;
        }
        new ListenFromServer().start();
        try
        {
            oos.writeObject(username);
        }
        catch(IOException e)
        {
            display("Exception During Login");
            disconnect();
            return false;
        }
        return true;
    }
    private void display(String m)
    {
        cGUI.append(m + "\n");
    }
    void sendMessage(Message m)
    {
        try
        {
            oos.writeObject(m);
        }
        catch(IOException e)
        {
            display("Exception Sending Message to Server");
        }
    }
    private void disconnect()
    {
        try
        {
            ois.close();
            oos.close();
            socket.close();
        }
        catch(Exception e)
        {

        }
        cGUI.connectionFailed();
    }
    class ListenFromServer extends Thread
    {
        public void run()
        {
            while(true)
            {
                try
                {
                    String m = (String) ois.readObject();
                    cGUI.append(m);
                }
                catch(IOException e)
                {
                    display("Server has closed the connection");
                    cGUI.connectionFailed();
                    break;
                }
                catch(ClassNotFoundException e)
                {

                }
            }
        };
    };
}
