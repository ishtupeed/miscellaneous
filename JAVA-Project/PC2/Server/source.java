import java.io.File;
import javax.swing.*;
import java.awt.*;

/**
 * @author Bakhtiar
 *
 */
public class source
{
    private JFrame mainFrame=null;
    private JFrame temp=null;
    Toolkit tool = Toolkit.getDefaultToolkit();
    Image i = tool.getImage("pc2.jpg");
    ImageIcon logo = new ImageIcon(i);
    static final int height = 600;
    static final int width = 600;
    public String currentUser = "";
    public void createWelcome()
    {
        JLabel welcomeLabel;
        if(mainFrame!=null)
        {
            temp=mainFrame;
            mainFrame.dispose();
        }
        mainFrame = new JFrame("IUT PC²");
        mainFrame.setSize(width, height);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(temp);
        mainFrame.setIconImage(logo.getImage());
        mainFrame.setResizable(false);
        welcomeLabel = new JLabel("Welcome to IUT PC²", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.GRAY);
        Image iut = tool.getImage("iut2.png");
        ImageIcon ii = new ImageIcon(iut);
        JLabel iutLogo = new JLabel(ii);
        mainFrame.add(welcomeLabel);
        mainFrame.add(iutLogo);
        mainFrame.setVisible(true);
    }
    /**
     * @author Bakhtiar
     * @since 01.11.16
     */
    public void start()
    {
        FileReceiver fr = new FileReceiver();
        FileSender fs = new FileSender();
        source ss = new source();
        ss.createWelcome();
        boolean flag=false;
        while(true)
        {
            flag = fr.requestReceive();
            if(flag)
            {
                String s = fr.requestRead();
                System.out.println(s);
                if(s.charAt(0)=='L' && s.charAt(1)=='o')
                {
                    System.out.println("Login Requested");
                    System.out.println(s);
                    DatabaseConnection db = new DatabaseConnection();
                    currentUser = db.login(s);
                }
                else if(s.charAt(0)=='C' && s.charAt(1)=='o')
                {
                    System.out.println("Code Submission Requested");
                    fs.responseSend();
                    CodeChecker.checkCode(s, currentUser);
                }
                else if(s.charAt(0)=='C' && s.charAt(1)=='h')
                {
                    DatabaseConnection db = new DatabaseConnection();
                    System.out.println(currentUser);
                    db.changePassword(s, currentUser);
                }
                else if(s.charAt(0)=='S' && s.charAt(6)=='P')
                {
                    System.out.println("Problem Submission Requested");
                    fs.responseSend();
                    DatabaseConnection db = new DatabaseConnection();
                    db.store();
                }
                else if(s.charAt(0)=='G')
                {
                    DatabaseConnection db = new DatabaseConnection();
                    db.retrieveProblem(s);
                    flag = fs.problemSend();
                    File f = new File("problem.txt");
                    f.delete();
                }
                else if(s.charAt(0)=='R' && s.charAt(1)=='e')
                {
                    fs.responseSend();
                    DatabaseConnection db = new DatabaseConnection();
                    db.registerUser();
                }
                else if(s.charAt(0)=='L' && s.charAt(1)=='i')
                {
                    DatabaseConnection db = new DatabaseConnection();
                    db.problemListGenerator();
                    fs.problemlistSend();
                }
                else if(s.charAt(0)=='R' && s.charAt(1)=='a')
                {
                    DatabaseConnection db = new DatabaseConnection();
                    db.generateRanklist();
                    System.out.println("Ranklist Generated");
                    fs.ranklistSend();
                    System.out.println("Ranklist Sent");
                }
                else if(s.charAt(0)=='T' && s.charAt(1)=='T')
                {
                    DatabaseConnection db = new DatabaseConnection();
                    db.retrieveTutorial(s);
                    System.out.println("Tutorial Generated");
                    fs.tutorialSend();
                    System.out.println("Tutorial Sent");
                }
                else if(s.charAt(0)=='T' && s.charAt(8)=='L')
                {
                    DatabaseConnection db = new DatabaseConnection();
                    db.tutorialListGenerator();
                    System.out.println("Tutorial List Generated");
                    fs.tutorialListSend();
                    System.out.println("Tutorial List Sent");
                }
                else if(s.charAt(0)=='A' && s.charAt(3)=='T')
                {
                    fs.responseSend();
                    System.out.println("Response Sent");
                    DatabaseConnection db = new DatabaseConnection();
                    db.storeTutorial();
                    System.out.println("Tutorial Stored");
                }
                else if(s.charAt(0)=='U' && s.charAt(1)=='L')
                {
                    DatabaseConnection db = new DatabaseConnection();
                    db.generateUserList();
                    System.out.println("UserList Generated");
                    fs.sendUserList();
                    System.out.println("UserList Sent");
                }
                else if(s.charAt(0)=='U' && s.charAt(1)=='F')
                {
                    DatabaseConnection db = new DatabaseConnection();
                    db.userRetrieve(s);
                    System.out.println("UserInfo Generated");
                    fs.sendUserInfo();
                    System.out.println("UserInfo Sent");
                    fs.sendUserImage();
                    System.out.println("UserImage Sent");
                }
            }
        }
    }
    public static void main(String[] args)
    {
        source s = new source();
        s.start();
    }
}
