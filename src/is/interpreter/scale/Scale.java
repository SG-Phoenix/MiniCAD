package is.interpreter.scale;

import is.interpreter.ExecutionResult;
import is.interpreter.IntrCommand;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;
import is.shapes.model.GraphicObject;


public class Scale implements IntrCommand {

    private String objID;
    private double factor;

    public Scale(String objID, double factor) {
        this.objID = objID;
        this.factor = factor;
    }

    @Override
    public ExecutionResult execute(ObjectManager context) {
        try {
            GraphicObject object = context.getObject(objID);
            object.scale(factor);
            return new ExecutionResult(true, "Object with id " + objID + " was scaled");
        }catch (ObjectNotFoundException e)
        {
            return new ExecutionResult(false, e.getMessage());
        }

    }


    @Override
    public String toString()
    {
        return "Scale["+objID+" , "+ factor +"]";
    }
}
