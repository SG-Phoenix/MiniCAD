package is.interpreter.scale;

import is.interpreter.CommandIF;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

public class Scale implements CommandIF {

    private String objID;
    private double factor;

    public Scale(String objID, double factor) {
        this.objID = objID;
        this.factor = factor;
    }

    @Override
    public String interpreta(ObjectManager manager) {

        AbstractGraphicObject object = manager.getManagedObjects().get(objID);
        if(object != null)
        {
            object.scale(factor);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "Scale["+objID+" , "+ factor +"]";
    }


}
