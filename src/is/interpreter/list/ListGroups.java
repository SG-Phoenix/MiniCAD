package is.interpreter.list;

import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class ListGroups extends List {


    @Override
    public String interpreta(ObjectManager context) {
        StringBuilder sb = new StringBuilder();
        for(GraphicObject object : context.getManagedObjects().values())
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
