package is.interpreter.area;

import is.interpreter.Symbols;
import is.manager.ObjectManager;
import is.shapes.model.AbstractGraphicObject;

public class AreaType extends Area {

    private Symbols type;

    public AreaType(Symbols type) {
        this.type = type;
    }

    @Override
    public String interpreta(ObjectManager manager) {
        double totArea = 0;
        for(AbstractGraphicObject object : manager.getManagedObjects().values())
        {
            if(object.getType().equals(type.name()))
                totArea += object.getArea();
        }

        return String.valueOf(totArea);
    }

    @Override
    public String toString()
    {
        return "AreaType["+type.toString()+"]";
    }


}
