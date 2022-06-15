package is.interpreter.list;

import is.interpreter.ExecutionResult;
import is.manager.ObjectManager;
import is.manager.ObjectNotFoundException;
import is.shapes.model.GraphicObject;

public class ListObj extends List
{
    private String objID;

    public ListObj(String objID) {
        this.objID = objID;
    }

    @Override
    public ExecutionResult execute(ObjectManager context) {
        StringBuilder sb = new StringBuilder();
        GraphicObject object = null;
        try {
            object = context.getObject(objID);
            sb.append("Object "+ objID);
            sb.append("\n");
            sb.append(object);
            return new ExecutionResult(true, sb.toString());
        } catch (ObjectNotFoundException e) {
            return new ExecutionResult(false, e.getMessage());
        }

    }




    @Override
    public String toString()
    {
        return "ListObj["+objID+"]";
    }
}
