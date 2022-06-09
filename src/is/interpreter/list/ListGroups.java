package is.interpreter.list;

import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

public class ListGroups extends List {


    @Override
    public String interpreta(ObjectManager manager) {
        StringBuilder sb = new StringBuilder();
        for(AbstractGraphicObject object : manager.getManagedObjects().values())
        {
            if(object.getType().equals("Group"))
            {
                sb.append(object);
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString()
    {
        return "ListGroups";
    }


}
