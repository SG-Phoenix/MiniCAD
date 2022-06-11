package is.interpreter.area;

import is.interpreter.Symbols;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;

public class AreaType extends Area {

    private String type;

    public AreaType(String type) {
        this.type = type;
    }

    @Override
    public String interpreta(ObjectManager context) {
        double totArea = 0;
        for(GraphicObject object : context.getManagedObjects().values())
        {
            if(object.getType().equalsIgnoreCase(type))
                totArea += object.getArea();
            else if(object.getType().equalsIgnoreCase("group"))
            {
                for(GraphicObject child : ((GroupObject) object).getObjects())
                {
                    if(child.getType().equalsIgnoreCase(type))
                    {
                        totArea += child.getArea();
                    }
                }
            }
        }

        return String.valueOf(totArea);
    }

    @Override
    public String toString()
    {
        return "AreaType["+type.toString()+"]";
    }


}
