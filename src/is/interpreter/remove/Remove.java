package is.interpreter.remove;

import is.interpreter.CommandIF;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

public class Remove implements CommandIF {

    private String objID;

    public Remove(String objID) {
        this.objID = objID;
    }

    @Override
    public String interpreta(ObjectManager manager) {
        AbstractGraphicObject removed = manager.removeObject(objID);
        if(removed == null)
            return "Object "+objID+" not found";
        else
            return  "Object "+objID+" removed";
    }

    @Override
    public String toString()
    {
        return "Remove["+objID+"]";
    }


}
