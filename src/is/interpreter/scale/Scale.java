package is.interpreter.scale;

import is.interpreter.CommandIF;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class Scale implements CommandIF {

    private String objID;
    private double factor;

    public Scale(String objID, double factor) {
        this.objID = objID;
        this.factor = factor;
    }

    @Override
    public String interpreta(ObjectManager context) {

        GraphicObject object = context.getObject(objID);
        if(object != null)
        {
            object.scale(factor);
            return "Object "+ objID + " scaled";
        }
        return "Object "+ objID + " not found";
    }

    @Override
    public String toString()
    {
        return "Scale["+objID+" , "+ factor +"]";
    }


}
