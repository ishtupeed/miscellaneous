import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ServerGUI extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private JButton startStopButton;
    private JTextArea chatArea, eventArea;
    private JTextField portNumberField;
    private Server server;
    ServerGUI(int port)
    {
        super("Simple Chat Server");
        server = null;
        setSize(600, 600);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("Port Number: "));
        portNumberField = new JTextField("  " + port);
        northPanel.add(portNumberField);
        startStopButton = new JButton("Start");
        startStopButton.addActionListener(this);
        northPanel.add(startStopButton);
        add(northPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        chatArea = new JTextArea(100, 100);
        chatArea.setEditable(false);
        appendMessage("Chat Room.\n");
        centerPanel.add(new JScrollPane(chatArea));
        eventArea = new JTextArea(100, 100);
        eventArea.setEditable(false);
        appendEvent("Events Log.\n");
        centerPanel.add(new JScrollPane(eventArea));
        add(centerPanel);
        addWindowListener(new WindowAdapter()
        {
      		public void windowClosing(WindowEvent e)
      		{
            		if(server != null)
            	        {
            	            server.stop();
            	            server = null;
            	        }
            	        	dispose();
            	        	System.exit(0);
            	}
        }
        		);
        
        setVisible(true);
    }
    void appendMessage(String s)
    {
        chatArea.append(s);
        chatArea.setCaretPosition(chatArea.getText().length() - 1);
    }
    void appendEvent(String s)
    {
        eventArea.append(s);
        eventArea.setCaretPosition(eventArea.getText().length() - 1);
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if(server != null)
        {
            server.stop();
            server = null;
            portNumberField.setEditable(true);
            startStopButton.setText("Start");
            return;
        }
        int port;
        try
        {
            port = Integer.parseInt(portNumberField.getText().trim());
        }
        catch(Exception e)
        {
            appendEvent("Invalid Port Number");
            return;
        }
        server = new Server(port, this);
        new ServerRunning().start();
        startStopButton.setText("Stop");
        portNumberField.setEditable(false);
    }
    public static void main(String[] args)
    {
        new ServerGUI(4100);
    }

    class ServerRunning extends Thread
    {
        public void run()
        {
            server.start();
            startStopButton.setText("Start");
            portNumberField.setEditable(true);
            appendEvent("Server Stopped.\n");
            
            server = null;
        }
    }
}
