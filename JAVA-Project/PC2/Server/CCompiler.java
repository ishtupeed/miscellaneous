import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Bakhtiar
 * @version 1.0
 */

public class CCompiler 
{
	public static boolean CFileCompile(String fileName)
	{
		String compileCommand = "gcc " + fileName;
		try
		{
			Process processCompile = Runtime.getRuntime().exec(compileCommand);
			BufferedReader brCompileError = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
			String errorCompile = brCompileError.readLine();
			if(errorCompile!=null)
				return false;
			BufferedReader brCompileRun = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
			String outputCompile = brCompileRun.readLine();
			if(outputCompile != null)
				return false;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public static boolean CFileRun(String fileName)
	{
		String runFileCommand ="./" + fileName.split(".c")[0];
		try
		{
			Process processRun = Runtime.getRuntime().exec(runFileCommand);
			BufferedReader brRun = new BufferedReader(new InputStreamReader(processRun.getErrorStream()));
            String errorRun = brRun.readLine();
            if (errorRun != null)
                return false;
            
            return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
