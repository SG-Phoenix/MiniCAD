package is.interpreter.perimeter;

import is.interpreter.ExecutionResult;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;
import is.shapes.model.GraphicObject;

public class PerimeterObj implements Perimeter {

    private String objID;

    public PerimeterObj(String objID) {
        this.objID = objID;
    }

    @Override
    public ExecutionResult execute(ObjectManager context) {
        try {
            GraphicObject object = context.getObject(objID);
            return new ExecutionResult(true, String.valueOf(object.getPerimeter()));
        }catch (ObjectNotFoundException e)
        {
            return new ExecutionResult(false, e.getMessage());
        }
    }

    @Override
    public String toString()
    {
        return "PerimeterObj["+objID+"]";
    }
}
