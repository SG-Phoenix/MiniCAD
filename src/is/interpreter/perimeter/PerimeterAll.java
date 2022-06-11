package is.interpreter.perimeter;

import is.manager.ObjectManager;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class PerimeterAll extends Perimeter {

    @Override
    public String interpreta(ObjectManager context) {
        double totalPerimeter = 0;
        for(GraphicObject object : context.getManagedObjects().values())
        {
            totalPerimeter += object.getPerimeter();
        }

        return String.valueOf(totalPerimeter);
    }

    @Override
    public String toString()
    {
        return "PerimeterAll";
    }


}
