import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.Socket;
import java.util.Date;

public class Server
{
    private static int uID;
    private ArrayList<ClientThread> CTArray;
    private ServerGUI sGUI;
    private SimpleDateFormat sdf;
    private int port;
    private boolean cont;
    public Server(int p, ServerGUI s)
    {
        sGUI = s;
        port = p;
        sdf = new SimpleDateFormat("hh:mm:ss");
        CTArray = new ArrayList<ClientThread>();
    }
    public void start()
    {
        cont = true;
        try
        {
            ServerSocket servSock = new ServerSocket(port);
            while(cont)
            {
                display("Server waiting for clients on port "+ port +".");
                Socket socket = servSock.accept();
                if(!cont)
                    break;
                ClientThread ct = new ClientThread(socket);
                CTArray.add(ct);
                ct.start();
            }
            try
            {
                servSock.close();
                for(int i=0; i<CTArray.size(); i++)
                {
                    ClientThread ct = CTArray.get(i);
                    try
                    {
                        ct.ois.close();
                        ct.oos.close();
                        ct.socket.close();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            catch(Exception e)
            {
                display("Exception Closing the Server and Clients");
                e.printStackTrace();
            }
        }
        catch(IOException e)
        {
            String errMsg = sdf.format(new Date()) + " exception on new Server Socket";
            display(errMsg);
        }
    }
    @SuppressWarnings("resource")
    protected void stop()
    {
        cont = false;
        try
        {
            new Socket("localhost", port);
        }
        catch(Exception e)
        {

        }
    }
    private void display(String m)
    {
        String time = sdf.format(new Date()) + " " + m;
        sGUI.appendEvent(time + "\n");
    }
    private synchronized void broadcast(String message)
    {
        String time = sdf.format(new Date());
        String msg = time + " " + message + "\n";
        sGUI.appendMessage(msg);
        for(int i=CTArray.size()-1; i>=0; i--)
        {
            ClientThread ct = CTArray.get(i);
            if(!ct.writeMessage(msg))
            {
                CTArray.remove(i);
                display("Disconnected "+ct.username);
            }
        }
    }
    synchronized void logout(int id)
    {
        for(int i=0; i<CTArray.size(); i++)
        {
            ClientThread ct = CTArray.get(i);
            if(ct.userID== id)
            {
                CTArray.remove(i);
                return;
            }
        }
    }

    public class ClientThread extends Thread
    {
        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        int userID=1;
        String username;
        Message m;
        String date;
        ClientThread(Socket s)
        {
            userID = uID++;
            socket = s;
            try
            {
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                username = (String)ois.readObject();
                display(username + " entered the chatroom");
            }
            catch(IOException e)
            {
                e.printStackTrace();
                return;
            }
            catch(ClassNotFoundException e)
            {
                e.printStackTrace();
                return;
            }
            date = new Date().toString() + "\n";
        }
        public void run()
        {
            boolean cont = true;
            while(cont)
            {
                try
                {
                    m = (Message)ois.readObject();
                }
                catch(IOException e)
                {

                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                String message = m.getMessage();
                int tp = m.getType();
                if(tp == Message.MESSAGE)
                    broadcast(username + ":\n\t" + message);
                else if(tp == Message.LOGOUT)
                {
                    display(username + " left the chatroom.");
                    cont = false;
                }
                else if(tp == Message.CHECKUSER)
                {
                    writeMessage("List of users:");
                    for(int i=0; i<CTArray.size(); i++)
                    {
                        ClientThread ct = CTArray.get(i);
                        writeMessage((i+1) + ". "+ ct.username + " since "+ct.date);
                    }
                }
            }
            logout(userID);
            close();
        }
        private void close()
        {
            try
            {
                ois.close();
                oos.close();
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        private boolean writeMessage(String m)
        {
            if(socket.isClosed())
            {
                close();
                return false;
            }
            try
            {
                oos.writeObject(m);
            }
            catch(IOException e)
            {
                display("Error sending message to " + username);
                e.printStackTrace();
            }
            return true;
        }
    }
}

