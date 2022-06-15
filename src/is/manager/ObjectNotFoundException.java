package is.manager;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String objID)
    {
        super("Object with id "+ objID + " doesn't exist");
    }
}
