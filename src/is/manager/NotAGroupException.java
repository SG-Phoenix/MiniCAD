package is.manager;

public class NotAGroupException extends RuntimeException{

    public NotAGroupException(String objID)
    {
        super("Object "+objID+" is not a group");
    }

}
