package is.interpreter.ungroup;

import is.interpreter.CommandIF;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

public class Ungroup implements CommandIF {

    private String objID;

    public Ungroup(String objID) {
        this.objID = objID;
    }

    @Override
    public String interpreta(ObjectManager manager) {
        AbstractGraphicObject object = manager.getManagedObjects().get(objID);

        if(object.getType().equals("Group"))
        {
            manager.getManagedObjects().remove(objID);
            return "Group deleted";
        }

        return "Group not found";

    }

    @Override
    public String toString()
    {
        return "Ungroup["+objID+"]";
    }


}
