package is.interpreter.area;

import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

public class AreaObj extends Area {

    private String objID;

    public AreaObj(String objID) {
        this.objID = objID;
    }

    @Override
    public String interpreta(ObjectManager manager) {
        AbstractGraphicObject object = manager.getManagedObjects().get(objID);
        if(object != null)
            return String.valueOf(object.getArea());

        return "Object " + objID + " not found";
    }

    @Override
    public String toString()
    {
        return "AreaObj["+objID+"]";
    }


}
