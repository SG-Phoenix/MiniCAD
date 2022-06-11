package is.interpreter.area;

import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class AreaObj extends Area {

    private String objID;

    public AreaObj(String objID) {
        this.objID = objID;
    }

    @Override
    public String interpreta(ObjectManager context) {
        GraphicObject object = context.getObject(objID);
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
