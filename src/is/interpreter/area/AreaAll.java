package is.interpreter.area;

import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class AreaAll extends Area {


    @Override
    public String interpreta(ObjectManager context) {
        double totArea = 0;
        for(GraphicObject object : context.getManagedObjects().values())
        {
            totArea += object.getArea();
        }

        return String.valueOf(totArea);
    }

    @Override
    public String toString()
    {
        return "AreaAll";
    }


}
