import java.io.Serializable;

public class Message implements Serializable
{
    protected static final long serialVersionUID = 1112122200L;
    static final int CHECKUSER = 0, MESSAGE = 1, LOGOUT = 2;
    private int type;
    private String message;

    Message(int tp, String m)
    {
        type = tp;
        message = m;
    }
    int getType()
    {
        return type;
    }
    String getMessage()
    {
        return message;
    }
}
