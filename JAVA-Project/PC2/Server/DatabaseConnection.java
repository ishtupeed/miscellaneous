import java.io.*;  
import java.sql.*;

/**
 * @author Bakhtiar
 * @since 31.10.16
 */
public class DatabaseConnection
{
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL= "jdbc:oracle:thin:@localhost:1521";
	static final String USER="bakhtiar";
	static final String PASS="hasan";
	public int problemIndex;
	public int userIndex=0;
	public int tutorialIndex;
	/**
	 * Constructor of the class. Used to fetch number
	 * of problems and number of tutorials from database.
	 */
	DatabaseConnection()
	{
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = con.createStatement();
			String sql = "Select count(*) as c from problems";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			problemIndex = rs.getInt("C");
			sql = "Select count(*) as c from tutorial";
			rs = stmt.executeQuery(sql);
			rs.next();
			tutorialIndex = rs.getInt("C");
			con.close();
			stmt.close();
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * @return true, if the method successfully stores user info in database, false otherwise
	 * This method is used to fetch user registration information
	 * and store it in the database
	 */
	public boolean store()
	{
		FileReceiver fr = new FileReceiver();
		fr.problemNameReceive();
		fr.problemReceive();
		fr.inputReceive();
		fr.outputReceive();
		fr.categoryReceive();
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			problemIndex++;
			String sql = "Insert into problems values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			String name = fr.problemNameReader();
			File problem = new File("problem.txt");
			FileReader frp = new FileReader(problem);
			File input = new File("input.txt");
			FileReader finp = new FileReader(input);
			File output = new File("expected_output.txt");
			FileReader fout = new FileReader(output);
			String category = fr.categoryReader();
			ps.setInt(1, problemIndex);
			ps.setString(2, name);
			ps.setCharacterStream(3, frp, (int)problem.length());
			ps.setCharacterStream(4, finp, (int)input.length());
			ps.setCharacterStream(5, fout, output.length());
			ps.setString(6, category);
			ps.executeQuery();
			con.close();
			ps.close();
			problem.delete();
			input.delete();
			output.delete();
			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	/**
	 * @param s String from which problem ID is extracted
	 * @return Problem ID
	 * This method is used to problem ID to fetch problem
	 * from the database.
	 */
	public int extractID(String s)
	{
		int id=0;
		int l=s.length();
		int mul=1;
		for(int i=l-1;s.charAt(i)!='m';i--)
		{
			id+=((s.charAt(i)-'0')*mul);
			mul*=10;
		}
		return id;
	}
	/**
	 * @param s String from which problem ID is extracted
	 * @return true, if the method is executed properly, false otherwise
	 * This method is used to retrieve problem statement
	 * from the database, which is then sent to the user. 
	 */
	public boolean retrieveProblem(String s)
	{
		int id = extractID(s);
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement ps = con.prepareStatement("Select * from problems where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Clob problem = rs.getClob(3);
			Reader pr = problem.getCharacterStream();
			FileWriter fp = new FileWriter("problem.txt");
			int i;
			while((i=pr.read())!=-1)
				fp.write((char)i);
			fp.close();
			con.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * @param id problem ID
	 * @return true, if the method is executed properly, false otherwise
	 * This method is used to retrieve input and expected
	 * output of a certain problem, which is used to compare with
	 * user output and provide verdict
	 */
	public boolean retrieveIO(int id)
	{
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement ps = con.prepareStatement("Select * from problems where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Clob input = rs.getClob(4);
			Reader ir = input.getCharacterStream();
			Clob output = rs.getClob(5);
			Reader or = output.getCharacterStream();
			FileWriter fi = new FileWriter("input.txt");
			FileWriter fo = new FileWriter("expected_output.txt");
			int i;
			while((i=ir.read())!=-1)
				fi.write((char)i);
			while((i=or.read())!=-1)
				fo.write((char)i);
			fi.close();
			fo.close();
			con.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	/**
	 * @param s String from which the username is extracted
	 * @return username
	 * This method is used to extract the username from concatenated
	 * string, which is later used for checking if the provided username
	 * exists in the database or not
	 */
	public String usernameExtract(String s)
	{
		String ret="";
		int i=0;
		while(s.charAt(i)!='$')
			i++;
		for(i++;s.charAt(i)!='$';i++)
			ret+=s.charAt(i);
		
		return ret;
	}
	/**
	 * @param s String from which the password is extracted
	 * @return password
	 * This method is used to extract the password from concatenated
	 * string, which is later used for checking if the provided password
	 * exists in the database or not
	 */
	public String passwordExtract(String s)
	{
		String ret="";
		int i=0;
		while(s.charAt(i)!='$')
			i++;
		i++;
		while(s.charAt(i)!='$')
			i++;
		for(i++;i<s.length();i++)
			ret+=s.charAt(i);
		
		return ret;
	}
	/**
	 * @return true, if the method is executed properly, false otherwise
	 * This method is used to register user in the database.
	 * It stores all the information, even image inside the database.
	 */
	public boolean registerUser()
	{
		FileReceiver fr = new FileReceiver();
		fr.nameReceiver();
		fr.usernameReceiver();
		fr.passwordReceiver();
		fr.imageReceiver();
		fr.IDReceiver();
		String name = fr.nameReader();
		String username = fr.usernameReader();
		String password = fr.passwordReader();
		String id = fr.IDReader();
		int type = 2;
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement ps = conn.prepareStatement("Insert into LOGININFO values(?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setInt(4, type);
			ps.setString(6, id);
			FileInputStream fin = new FileInputStream("image.jpg");
			ps.setBinaryStream(5, fin, fin.available());
			ps.executeQuery();
			conn.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * @param s concatenated code having username and password provided by the user
	 * @return Student ID to be stored for later purposes
	 * This method is used to verify the username and password provided
	 * by the user. It also detects whether the username is admin or user
	 * and sends response accordingly
	 */
	public String login(String s)
	{
		String username = usernameExtract(s);
		String password = passwordExtract(s);
		System.out.println(username);
		System.out.println(password);
		String[] u = new String[1000];
		String[] p = new String[1000];
		int[] t = new int[1000];
		String[] n = new String[1000];
		String[] id = new String[1000];
		Connection conn=null;
		Statement stmt=null;
		userIndex=0;
		try
		{
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
	    	  stmt=conn.createStatement();
	    	  String sql="SELECT NAME, USERNAME, PASSWORD, TYPE, ID FROM LOGININFO";
	    	  ResultSet rs=stmt.executeQuery(sql);
	    	  while(rs.next())
	    	  {
	    		  u[userIndex]= rs.getString("username");
	    		  p[userIndex]= rs.getString("password");
	    		  t[userIndex]= rs.getInt("type");
	    		  n[userIndex]=rs.getString("name");
	    		  id[userIndex]=rs.getString("id");
	    		  userIndex++;
	    	  }
	    	  System.out.println("Number of Users: " +userIndex);
	    	  String name="";
	    	  String currID="";
	    	  int type=0;
				for(int i=0;i<userIndex;i++)
				{
					System.out.println("DBUser: " +u[i]);
					System.out.println("DBPass: " + p[i]);
					System.out.println("DBName: " + n[i]);
					if(username.compareTo(u[i])==0 && password.compareTo(p[i])==0)
					{
						type=t[i];
						name=n[i];
						currID=id[i];
						break;
					}
				}
				try
				{
					PrintWriter writer = new PrintWriter("ver.txt");
					writer.println(type);
					if(type!=0)
						writer.println(name);
					writer.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return null;
				}
				FileSender fs = new FileSender();
				System.out.println(name);
				System.out.println(type);
				fs.loginVerification(type);
				return currID;
	      }
	      catch(SQLException se)
	      {
	    	  se.printStackTrace();
	    	  return null;
	      }
	      catch(Exception e)
	      {
	    	  e.printStackTrace();
	    	  return null;
	      }	
		}
	/**
	 * @return true, if the method is executed properly, false otherwise
	 * This method is used to generate problem List from the database.
	 * When the user wants to solve problems, this list is shown to the user.
	 */
	public boolean problemListGenerator()
	{
		try
		{
			PrintWriter writer = new PrintWriter("problemlist.txt");
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String sql = "SELECT ID, NAME, CATEGORY FROM PROBLEMS";
			ResultSet rs = stmt.executeQuery(sql);
			String s="";
			while(rs.next())
			{
				s=Integer.toString(rs.getInt("ID"));
				s+='$';
				s+=rs.getString("NAME");
				s+='$';
				s+=rs.getString("CATEGORY");
				writer.println(s);
			}
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * @param currentUser Student ID of the user that submitted the solution
	 * @param ID problem ID which user solved
	 * @return true, if the method is executed properly, false otherwise
	 * This method is used to store accepted problems to the database
	 * for the specific users
	 */
	public boolean updateRank(String currentUser, int ID)
	{
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String sql = "Insert into ranklist values ("+ currentUser +", "+ID+")";
			stmt.executeQuery(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * @return true, if the method is executed properly, false otherwise
	 * This method is used to generate the rank-list. It counts the number
	 * of distinct problems solved by the user and show them in descending order 
	 */
	public boolean generateRanklist()
	{
		System.out.println("Inside Ranklist Generator");
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = con.createStatement();
			String sql="select id, count(distinct problem) as c from ranklist group by id order by c desc";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Query Executed Ranklist Generator");
			PrintWriter writer = new PrintWriter("ranklist.txt");
			String s="";
			while(rs.next())
			{
				s=rs.getString("ID");
				s+='$';
				s+=rs.getInt("c");
				writer.println(s);
			}
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * @return true, if the method is executed properly, false otherwise
	 * This method is used to generate the list of tutorials.
	 * When the user wants to read tutorial, this list is generated and sent.
	 */
	public boolean tutorialListGenerator()
	{
		try
		{
			PrintWriter writer = new PrintWriter("tutoriallist.txt");
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String sql = "SELECT ID, NAME, CATEGORY FROM TUTORIAL";
			ResultSet rs = stmt.executeQuery(sql);
			String s="";
			while(rs.next())
			{
				s=Integer.toString(rs.getInt("ID"));
				s+='$';
				s+=rs.getString("NAME");
				s+='$';
				s+=rs.getString("CATEGORY");
				writer.println(s);
			}
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * @param s String containing concatenated tutorial ID
	 * @return tutorial ID
	 * This method is used to extract tutorial ID from the sent
	 * code, which is stored for sending the desired tutorial.
	 */
	public int extractTID(String s)
	{
		int id=0;
		int l=s.length();
		int mul=1;
		for(int i=l-1;s.charAt(i)!='T';i--)
		{
			id+=((s.charAt(i)-'0')*mul);
			mul*=10;
		}
		return id;
	}
	/**
	 * @param s contains tutorial ID that the user wanted.
	 * @return true, if the method is executed properly, false otherwise
	 * This method is used to retrieve tutorial statement from database.
	 */
	public boolean retrieveTutorial(String s)
	{
		int id = extractTID(s);
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement ps = con.prepareStatement("Select * from tutorial where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Clob tutorial = rs.getClob(3);
			Reader pr = tutorial.getCharacterStream();
			FileWriter fp = new FileWriter("tutorial.txt");
			int i;
			while((i=pr.read())!=-1)
				fp.write((char)i);
			fp.close();
			con.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean storeTutorial()
	{
		FileReceiver fr = new FileReceiver();
		fr.tutorialNameReceiver();
		System.out.println("Tutorial Name Received");
		fr.tutorialStatementReceiver();
		System.out.println("Tutorial Statement Received");
		fr.tutorialCatReceiver();
		System.out.println("Tutorial Category Received");
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			tutorialIndex++;
			String sql = "Insert into tutorial values(?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			String name = fr.tutorialNameReader();
			File statement = new File("tutostate.txt");
			FileReader fs = new FileReader(statement);
			String category = fr.tutorialCatReader();
			ps.setInt(1, tutorialIndex);
			ps.setString(2, name);
			ps.setCharacterStream(3, fs, (int)statement.length());
			ps.setString(4, category);
			ps.executeQuery();
			con.close();
			ps.close();
			statement.delete();
			
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public String oldPasswordExtract(String s)
	{
		String ret="";
		int i=0;
		while(s.charAt(i)!='$')
			i++;
		i++;
		for(;s.charAt(i)!='$';i++)
			ret+=s.charAt(i);
		return ret;
	}
	public String newPasswordExtract(String s)
	{
		String ret="";
		int i=0;
		while(s.charAt(i)!='$')
			i++;
		i++;
		while(s.charAt(i)!='$')
			i++;
		i++;
		for(;i<s.length();i++)
			ret+=s.charAt(i);
		
		return ret;
	}
	public boolean changePassword(String s, String currentUser)
	{
		String oldpass=oldPasswordExtract(s);
		System.out.println("OLD: "+oldpass);
		String newpass = newPasswordExtract(s);
		System.out.println("NEW: " + newpass);
		int result=0;
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "Select password from logininfo where id = "+currentUser;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			String pass = rs.getString("password");
			System.out.println("DBPass: " + pass);
			if(pass.equals(oldpass))
			{
				sql = "Update logininfo set password = '"+newpass+"' where id = "+currentUser;
				stmt.executeQuery(sql);
				result=1;
			}
			FileSender fs = new FileSender();
			System.out.println(result);
			fs.changeVerdict(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	}
	public boolean generateUserList()
	{
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "Select name, id from logininfo where type = 2";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			PrintWriter writer = new PrintWriter("userlist.txt");
			String s="";
			while(rs.next())
			{
				s=rs.getString("name");
				s+='$';
				s+=rs.getString("id");
				writer.println(s);
			}
			writer.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public String idExtractor(String s)
	{
		String ret="";
		for(int i=2;i<s.length();i++)
			ret+=s.charAt(i);
		
		return ret;
	}
	public boolean userRetrieve(String s)
	{
		String id = idExtractor(s);
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = con.createStatement();
			String sql= "select username from logininfo where id = '"+id+"'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String username = rs.getString("username");
			sql="select count(distinct problem) as c from ranklist group by id having id = '"+id+"'";
			rs=stmt.executeQuery(sql);
			int solved=0;
			if(rs.next())
				solved=rs.getInt("c");
			String send=username+'$'+Integer.toString(solved);
			PrintWriter writer = new PrintWriter("userinfo.txt");
			writer.println(send);
			writer.close();
			con.close();
			stmt.close();
			rs.close();
			imageRetrieve(id);
			
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean imageRetrieve(String id)
	{
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement ps = con.prepareStatement("select name, image from logininfo where id = '"+id+"'");
			ResultSet rs = ps.executeQuery();
			rs.next();
			Blob b = rs.getBlob(2);
			System.out.println(rs.getString("name"));
			byte barr[]=b.getBytes(1, (int)b.length());
			System.out.println((double)b.length()/1024);
			FileOutputStream fout = new FileOutputStream("sendImage.jpg");
			fout.write(barr);
			fout.close();
			con.close();
			rs.close();
			ps.close();
			System.out.println("User Image Generated");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
