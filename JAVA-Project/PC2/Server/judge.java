import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class judge 
{
	public static String userOutputRead()
	{
		BufferedReader br = null;
        String currentLine = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/output.txt"));
            while((currentLine = br.readLine())!=null)
            {
                ret+=currentLine;
            }
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
	public static String expectedOutputRead()
    {
        BufferedReader br = null;
        String currentLine = null;
        String ret = "";
        try
        {
            br = new BufferedReader(new FileReader("C:/Users/Bakhtiar/Documents/Eclipse/Server/expected_output.txt"));
            while((currentLine = br.readLine())!=null)
            {
                ret+=currentLine;
            }
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
