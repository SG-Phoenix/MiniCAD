package is.interpreter.list;

import is.interpreter.Symbols;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;

public class ListType extends List {

    private String type;

    public ListType(String type) {
        this.type = type;
    }


    @Override
    public String interpreta(ObjectManager context) {
        StringBuilder sb = new StringBuilder();
        for(GraphicObject object : context.getManagedObjects().values())
        {
            if(object.getType().equalsIgnoreCase(type))
            {
                sb.append(object);
                sb.append("\n");
            }
            else if(object.getType().equalsIgnoreCase("group"))
            {
                for(GraphicObject child : ((GroupObject) object).getObjects())
                {
                    if(child.getType().equalsIgnoreCase(type))
                    {
                        sb.append(object);
                        sb.append("\n");
                    }
                }
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
