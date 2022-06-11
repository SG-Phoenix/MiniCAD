package is.interpreter.perimeter;

import is.interpreter.Symbols;
import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;

public class PerimeterType extends Perimeter {

    private String type;

    public PerimeterType(String type) {
        this.type = type;
    }

    @Override
    public String interpreta(ObjectManager context) {
        double totalPerimeter = 0;
        for(GraphicObject object : context.getManagedObjects().values())
        {
            if(object.getType().equalsIgnoreCase(type))
                totalPerimeter += object.getPerimeter();

            else if(object.getType().equalsIgnoreCase("group"))
            {
                for(GraphicObject child : ((GroupObject) object).getObjects())
                {
                    if(child.getType().equalsIgnoreCase(type))
                    {
                        totalPerimeter += child.getPerimeter();
                    }
                }
            }
        }


        return String.valueOf(totalPerimeter);
    }

    @Override
    public String toString()
    {
        return "PerimeterType["+type.toString()+"]";
    }
}
