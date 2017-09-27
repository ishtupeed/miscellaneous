import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private JLabel textLabel;
    private JTextField textField;
    private JTextField serverField, portField;
    private JButton login, logout, checkuser;
    private JTextArea chatArea;
    private boolean conn;
    private Client client;
    private int defaultPort;
    private String defaultHost;

    ClientGUI(String host, int port)
    {
        super("Simple Chat Client");
        defaultPort = port;
        defaultHost = host;
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel northPanel = new JPanel(new GridLayout(3, 1));
        JPanel serverPortPanel = new JPanel(new GridLayout(3, 1));
        serverField = new JTextField(host);
        portField = new JTextField(""+port);
        portField.setHorizontalAlignment(SwingConstants.RIGHT);
        serverPortPanel.add(new JLabel("Server Address:   "));
        serverPortPanel.add(serverField);
        serverPortPanel.add(new JLabel("Port Number:   "));
        serverPortPanel.add(portField);
        serverPortPanel.add(new JLabel(""));
        northPanel.add(serverPortPanel);

        textLabel = new JLabel("Enter Username", SwingConstants.LEFT);
        northPanel.add(textLabel);
        textField = new JTextField("John Doe");
        textField.setBackground(Color.WHITE);
        northPanel.add(textField);
        add(northPanel, BorderLayout.NORTH);

        chatArea = new JTextArea("Chat Room\n", 100, 100);
        JPanel centerPanel = new JPanel(new GridLayout(1, 1));
        centerPanel.add(new JScrollPane(chatArea));
        chatArea.setEditable(false);
        add(centerPanel, BorderLayout.CENTER);

        login = new JButton("Login");
        login.addActionListener(this);
        logout = new JButton("Logout");
        logout.addActionListener(this);
        logout.setEnabled(false);
        checkuser = new JButton("Check Users");
        checkuser.addActionListener(this);
        checkuser.setEnabled(false);

        JPanel southPanel = new JPanel();
        southPanel.add(login);
        southPanel.add(logout);
        southPanel.add(checkuser);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
        textField.requestFocus();
    }
    void append(String s)
    {
        chatArea.append(s);
        chatArea.setCaretPosition(chatArea.getText().length() - 1);
    }

    void connectionFailed()
    {
        login.setEnabled(true);
        logout.setEnabled(false);
        checkuser.setEnabled(false);
        textLabel.setText("Enter Username");
        textField.setText("John Doe");
        portField.setText("" + defaultPort);
        portField.setEditable(true);
        serverField.setText(defaultHost);
        serverField.setEditable(true);
        textField.removeActionListener(this);
        conn = false;
    }

    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource();
        if(o == logout)
        {
            client.sendMessage(new Message(Message.LOGOUT, ""));
            return;
        }
        else if(o == checkuser)
        {
            client.sendMessage(new Message(Message.CHECKUSER, ""));
            return;
        }
        if(conn)
        {
            client.sendMessage(new Message(Message.MESSAGE, textField.getText()));
            textField.setText("");
            return;
        }
        String username = textField.getText().trim();
        if(username.equals(""))
            return;
        String server = serverField.getText().trim();
        if(server.equals(""))
            return;
        String portNumber = portField.getText().trim();
        if(portNumber.equals(""))
            return;
        int port = 0;
        try
        {
            port = Integer.parseInt(portNumber);
        }
        catch(Exception e1)
        {
            return;
        }
        client = new Client(server, port, username, this);
        if(!client.start())
            return;
        textLabel.setText("Type Your Message");
        textField.setText("");
        conn = true;
        login.setEnabled(false);
        logout.setEnabled(true);
        checkuser.setEnabled(true);
        serverField.setEditable(false);
        portField.setEditable(false);
        textField.addActionListener(this);
    }

    public static void main(String[] args)
    {
        new ClientGUI("10.220.63.163", 4100);
    }
}
