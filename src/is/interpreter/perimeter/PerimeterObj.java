package is.interpreter.perimeter;

import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class PerimeterObj extends Perimeter {

    private String objID;

    public PerimeterObj(String objID) {
        this.objID = objID;
    }

    @Override
    public String interpreta(ObjectManager context) {

        GraphicObject object = context.getObject(objID);

        if(object != null)
            return String.valueOf(object.getPerimeter());

        return "Object " + objID + " not found";
    }

    @Override
    public String toString()
    {
        return "PerimeterObj["+objID+"]";
    }
}
