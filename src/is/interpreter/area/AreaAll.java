package is.interpreter.area;

import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

public class AreaAll extends Area {


    @Override
    public String interpreta(ObjectManager manager) {
        double totArea = 0;
        for(AbstractGraphicObject object : manager.getManagedObjects().values())
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
