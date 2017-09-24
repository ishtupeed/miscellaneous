import java.io.File;

public class CodeChecker
{
	/**
	 * @author Bakhtiar
	 * @param s Problem ID will be extracted from the code sent
	 * @return Problem ID of the specific problem
	 * This method is used to extract the problem ID of the 
	 * submitted code. It is stored to fetch problem input
	 * and expected Output
	 */
    public static int problemNumber(String s)
    {
        int l = s.length();
        int ID=0;
        int mul=1;
        for(int i=l-1; s.charAt(i)!='e'; i--)
        {
            ID+=(mul*(s.charAt(i)-'0'));
            mul*=10;
        }
        return ID;
    }
    /**
     * @author Bakhtiar
     * @param s The code from which problem ID will be extracted
     * @param currentUser
     * This method is used to compare the output of the source
     * code and expected output. Verdict is sent accordingly
     */
    public static void checkCode(String s, String currentUser)
    {
        int ID = problemNumber(s);
        boolean flag = false;
        FileReceiver fr = new FileReceiver();
        FileSender fs = new FileSender();
        DatabaseConnection db = new DatabaseConnection();
        db.retrieveIO(ID);
        flag = fr.codeReceive();
        if(flag)
        {
            flag = CCompiler.CFileCompile("a.c");
            if(flag)
            {
                flag = CCompiler.CFileRun("a.c");
                if(flag)
                {
                    if(judge.userOutputRead().equals(judge.expectedOutputRead()))
                    {
                    	fs.verdictSend("Accepted");
                    	db.updateRank(currentUser, ID);
                    }
                    else
                        fs.verdictSend("Wrong Answer");
                }
                else
                    fs.verdictSend("Runtime Error");
            }
            else
                fs.verdictSend("Compilation Error");
        }

        File f1 = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/a.c");
        f1.delete();
        File f2 = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/a.exe");
        f2.delete();
        File f3 = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/output.txt");
        f3.delete();
        File f4 = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/input.txt");
        f4.delete();
        File f5 = new File("C:/Users/Bakhtiar/Documents/Eclipse/Server/expected_output.txt");
        f5.delete();
    }
}
