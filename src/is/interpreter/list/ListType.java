package is.interpreter.list;

import is.interpreter.Symbols;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

public class ListType extends List {

    private Symbols type;

    public ListType(Symbols type) {
        this.type = type;
    }


    @Override
    public String interpreta(ObjectManager manager) {
        StringBuilder sb = new StringBuilder();
        for(AbstractGraphicObject object : manager.getManagedObjects().values())
        {
            if(object.getType().equals(type.name()))
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
        return "ListType["+type.toString()+"]";
    }


}
