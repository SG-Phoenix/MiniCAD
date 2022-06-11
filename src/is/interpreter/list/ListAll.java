package is.interpreter.list;

import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class ListAll extends List
{

    @Override
    public String interpreta(ObjectManager context) {
        StringBuilder sb = new StringBuilder();
        for(GraphicObject object : context.getManagedObjects().values())
        {
            sb.append(object);
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public String toString()
    {
        return "ListAll";
    }


}
