package is.interpreter.area;

import is.interpreter.ExecutionResult;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;
import is.shapes.model.GraphicObject;

public class AreaObj implements Area {

    private String objID;

    public AreaObj(String objID) {
        this.objID = objID;
    }

    @Override
    public ExecutionResult execute(ObjectManager context) {
        GraphicObject object = null;
        try {
            object = context.getObject(objID);
            return new ExecutionResult(true, String.valueOf(object.getArea()));

        } catch (ObjectNotFoundException e) {
            return new ExecutionResult(false,e.getMessage());
        }
    }

    @Override
    public String toString()
    {
        return "AreaObj["+objID+"]";
    }

}
