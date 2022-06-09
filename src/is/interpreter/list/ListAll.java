package is.interpreter.list;

import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

public class ListAll extends List
{

    @Override
    public String interpreta(ObjectManager manager) {
        StringBuilder sb = new StringBuilder();
        for(AbstractGraphicObject object : manager.getManagedObjects().values())
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
