package is.interpreter.remove;


import is.interpreter.ExecutionResult;
import is.interpreter.IntrCommand;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;
import is.shapes.model.GraphicObject;

public class Remove implements IntrCommand {

    private String objID;

    private GraphicObject removedObj;
    public Remove(String objID) {
        this.objID = objID;
    }

    @Override
    public ExecutionResult execute(ObjectManager context) {
        try{
            GraphicObject object = context.getObject(objID);
            removedObj = context.removeObject(objID);
            return new ExecutionResult(true, "Object with id "+objID+" deleted");
        }
        catch (ObjectNotFoundException e)
        {
            return new ExecutionResult(false, e.getMessage());
        }
    }

    @Override
    public String toString()
    {
        return "Remove["+objID+"]";
    }

}
