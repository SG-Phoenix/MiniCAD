package is.interpreter.list;

import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class ListObj extends List
{
    private String objID;

    public ListObj(String objID) {
        this.objID = objID;
    }

    @Override
    public String interpreta(ObjectManager context) {

        GraphicObject object = context.getObject(objID);
        if( object != null)
            return object.toString();
        return "Object " + objID + " not found";
    }

    @Override
    public String toString()
    {
        return "ListObj["+objID+"]";
    }


}
