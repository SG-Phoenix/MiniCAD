package is.interpreter.ungroup;

import is.interpreter.CommandIF;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class Ungroup implements CommandIF {

    private String objID;

    public Ungroup(String objID) {
        this.objID = objID;
    }

    @Override
    public String interpreta(ObjectManager context) {

        GraphicObject object = context.getObject(objID);

        if(context.ungroupObject(objID))
            return "Group deleted";

        return "Group not found";

    }

    @Override
    public String toString()
    {
        return "Ungroup["+objID+"]";
    }


}
