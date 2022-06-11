package is.interpreter.remove;

import is.interpreter.CommandIF;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class Remove implements CommandIF {

    private String objID;

    public Remove(String objID) {
        this.objID = objID;
    }

    @Override
    public String interpreta(ObjectManager context) {

        GraphicObject object = context.getObject(objID);
        if(object == null)
            return "Object "+objID+" not found";
        else
        {
            context.removeObject(objID);
            return  "Object "+objID+" removed";
        }

    }

    @Override
    public String toString()
    {
        return "Remove["+objID+"]";
    }


}
